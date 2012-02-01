/*
 * @(#)JMFProperties.java	1.21 98/10/08
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.media.util;

import java.lang.*;
import java.util.*;
import java.io.*; 

/**
* This class is a part of the porting layer implementation for JavaTV.
* This class is used to access and modify information about jmf settings
*/
public class JMFProperties {

    private static String filename = null;
    
    private static Properties jmfProps = null;
 //AD   private static boolean useNative = true; // VV Use native methods by def.
  private static boolean useNative = false; // AD.
    static {
	JMFSecurity.beginPrivileged();
	try {
	    JMFSecurity.loadLibrary("jmam");
	} catch (UnsatisfiedLinkError e) {
	    useNative = false;
	} catch (Exception e) {
	    useNative = false;
	}
	
	try {
	    readProperties();
	} catch (Exception e) {
	    System.err.println("JMFProperties: could not read properties.");
	}
	JMFSecurity.endPrivileged();
    }
     
    public JMFProperties() {
    }

    public static void setProperty(String prop, String value) {
	if (jmfProps == null)
	    readProperties();
	jmfProps.put(prop, value);
    }

    public static String getProperty(String prop) {
	if (jmfProps == null)
	    readProperties();
	// System.out.println("JMFProperties: getPropterty: " +jmfProps.getProperty(prop, null)); // VV
	return (String) jmfProps.getProperty(prop, null);
    }

    public static void readProperties_old() {
	JMFProperties foo = new JMFProperties();
	try {
	    java.lang.reflect.Method m =
		foo.getClass().getMethod("readProperties_1", null);
	    JMFSecurity.callPrivileged(m,foo, null);
	} catch (Exception e) {
	    System.err.println("A privileged call failed: "+e);
	    e.printStackTrace();
	}
    }

    public final static void readProperties() {
    	System.out.println("JMFProperties: readProperties");
	if (jmfProps != null)
	    return;
        if (useNative == false) { 
	    // Initialize jmfProps to default values
	    jmfProps = new Properties();
	    //jmfProps.put("content.prefixes", "javax|com.sun|test");
	    //jmfProps.put("protocol.prefixes", "javax|com.sun|test");
	    jmfProps.put("content.prefixes", "com.sun.tv");
	    jmfProps.put("protocol.prefixes", "com.sun.tv");
	    jmfProps.put("cache.limit", "2000000");
	    jmfProps.put("cache.dir", "/tmp");
	    jmfProps.put("cache.use", "N");
	    jmfProps.put("defaultfont.name", "Helvetica");
            return;
        }
	try {
	    JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
					       JMFSecurity.readPropArgs);
	} catch (Exception e) {}
	// Get the CLASSPATH from the system properties
	String classpath = null;
	try {
	    classpath = System.getProperty("java.class.path");
	} catch (Exception e) {
	    filename = null;
	    System.err.println("Error: Couldn't read the CLASSPATH "+e);
	    jmfProps = new Properties();
	    // use default properties since we couldnt find the CLASSPATH
	    jmfProps.put("content.prefixes", "javax|com.sun.tv|test");
	    jmfProps.put("protocol.prefixes", "javax|com.sun.tv|test");
	    jmfProps.put("cache.limit", "2000000");
	    jmfProps.put("cache.dir", "/tmp");
	    jmfProps.put("cache.use", "N");
	    jmfProps.put("defaultfont.name", "Helvetica");
	    return;
	}

	// Search the directories in CLASSPATH for the first available
	// jmf.properties
	StringTokenizer tokens = new StringTokenizer(classpath, File.pathSeparator);
	String dir;
	String strJMF = JMFI18N.getResource("jmf.properties");
	
	while (tokens.hasMoreTokens()) {
	    dir = tokens.nextToken();
	    File file;
	    String caps = dir.toUpperCase();
	    // If its not a directory, then we need to get rid of
	    // the file name and get the directory.
	    try {
		if (caps.indexOf(".ZIP") > 0 || caps.indexOf(".JAR") > 0 ) {
		    int sep = dir.lastIndexOf(File.separator);
		    if (sep == -1 && ! File.separator.equals("/")) // if someone uses a slash in DOS
			                                           // instead of a backslash
			sep = dir.lastIndexOf("/");
		    
		    if (sep == -1) {		      // no separator
			sep = dir.lastIndexOf(":");	      // is there a ":" ?
			if (sep == -1) {		      // no ":"
			    dir = strJMF;		      // its just a xxx.jar or xxx.zip
			} else {			      // there is a ":"
			    dir = dir.substring(0, sep) + ":" + strJMF;
			}
		    } else {
			dir = dir.substring(0, sep) + File.separator + strJMF;
		    }
		} else
		    dir = dir + File.separator + strJMF;
	    } catch (Exception e) {
		dir = dir + File.separator + strJMF;
	    }
	    
	    file = new File(dir);
	    if (nFileExists(dir)) {
		filename = dir;
		readTheFile(dir, file);
		return;
	    }
	    // Note: If the jmf.properties_<locale> cannot be found.
	    // use the default one.
	    if (!strJMF.equals("jmf.properties")){
		dir = dir.substring(0, dir.length() - 3);

	        if (nFileExists(dir)) {
                    filename = dir;
                    readTheFile(dir, file);
                    return;
                }
	    }
	    
	    
	}
	// We couldn't find the file, so atleast lets not try reading it again.
	jmfProps = new Properties();
	// since we couldnt find the file, use default values.
	jmfProps.put("content.prefixes", "javax|com.sun.tv|test");
	jmfProps.put("protocol.prefixes", "javax|com.sun.tv|test");
	jmfProps.put("cache.limit", "2000000");
	jmfProps.put("cache.dir", "/tmp");
	jmfProps.put("cache.use", "N");
	jmfProps.put("defaultfont.name", "Helvetica");
    }

    private static void readTheFile(String filename, File file) {
	// Can we read in java?
	jmfProps = new Properties();
	/* // Commented out to avoid security exception
	try {
	    if (file.canRead()) {
		// Read the file a line at a time.
		BufferedReader br = new BufferedReader( new FileReader(file) );
		String line;
		while ((line = br.readLine()) != null) {
		    // Get the key and value from each line and add it to jmfProps
		    int equal = line.indexOf("=");
		    if (equal > 0 && equal < line.length() - 1) {
			String key = line.substring(0, equal);
			String value = line.substring(equal+1, line.length());
			jmfProps.put(key, value);
		    }
		}
		br.close();
	    } else
		pleaseReadTheFile(filename, file);
	} catch (Exception e) {
	    if (e instanceof SecurityException) {
		// Cant read from java, use native code
		pleaseReadTheFile(filename, file);
	    }
	}
	*/
	pleaseReadTheFile(filename, file);
    }
    
    public static boolean writeProperties() {
	if (jmfProps == null)
	    readProperties();
	if (jmfProps != null && filename != null) {
	    File file = new File(filename);
	    /* // Commented out to avoid security exception
	    try {
		if (file.canWrite()) {
		    BufferedWriter bw = new BufferedWriter( new FileWriter(file) );
		    Enumeration enum = jmfProps.propertyNames();
		    String header = "#JMF Properties";
		    bw.write(header, 0, header.length());
		    bw.newLine();
		    while (enum.hasMoreElements()) {
			String key = (String) enum.nextElement();
			String value = jmfProps.getProperty(key);
			if (key != null && value != null) {
			    bw.write(key, 0, key.length());
			    bw.write("=", 0, 1);
			    bw.write(value, 0, value.length());
			    bw.newLine();
			}
		    }
		    bw.close();
		    return true;
		} else
		    pleaseWriteTheFile(filename, file);
	    } catch (Exception e) {
	    }
	    */
	    return pleaseWriteTheFile(filename, file);
	}
	return false;
    }

    private static native boolean nFileExists(String s);
    private static native int nOpenFile(String s, int write);
    private static native String nReadLine(int id);
    private static native void nCloseFile(int id);
    private static native void nWriteLine(int id, String line);

    private synchronized static void pleaseReadTheFile(String filename, File file) {
	int id;
	if ((id = nOpenFile(filename, 0)) != 0) {
	    String line;
	    while ((line = nReadLine(id)) != null) {
		int equal = line.indexOf("=");
		if (equal > 0 && equal < line.length() - 1) {
		    String key = line.substring(0, equal);
		    String value = line.substring(equal+1, line.length());
		    jmfProps.put(key, value);
		}
	    }
	    nCloseFile(id);
	} else {
	    System.err.println("Can't read jmf.properties file");
	}
    }

    private static boolean pleaseWriteTheFile(String filename, File file) {
	int id;
	if ((id = nOpenFile(filename, 1)) != 0) {
	    nWriteLine(id, "#JMFProperties");
	    Enumeration enum = jmfProps.propertyNames();
	    while (enum.hasMoreElements()) {
		String key = (String) enum.nextElement();
		String value = jmfProps.getProperty(key);
		if (key != null && value != null) {
		    nWriteLine(id, key + "=" + value);
		}
	    }
	    nCloseFile(id);
	    return true;
	} else {
	    System.err.println("Can't write jmf.properties file");
	    return false;
	}
    }

    static public Vector str2list(String str) {
	Vector list = new Vector();
	if (str == null) return list;
	int i = 0;
	while ((i = str.indexOf("|")) != -1) {
	    list.addElement(str.substring(0, i));
	    str = str.substring(i+1);
	}
	list.addElement(str);
	return list;
    }
}
    
