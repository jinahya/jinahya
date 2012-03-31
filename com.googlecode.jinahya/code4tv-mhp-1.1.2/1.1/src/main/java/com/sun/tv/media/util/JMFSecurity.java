/*
 * @(#)JMFSecurity.java	1.13 98/12/07
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

import java.lang.reflect.*;

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * This class is used to monitor security when calling thread, connection,
 * file access, event queue and window and object methods. 
*/
public class JMFSecurity {

    public static Method enablePrivilege = null;

    public static Object privilegeManager = null;

    public static Object [] threadArgs = null;

    public static Object [] threadGroupArgs = null;

    public static Object [] linkArgs = null;

    public static Object [] superArgs = null;

    public static Object [] connectArgs = null;

    public static Object [] readPropArgs = null;

    public static Object [] readFileArgs = null;

    public static Object [] eventQueueArgs = null;

    public static Object [] writePropArgs = null;

    public static Object [] windowArgs = null;

    public static Object [] multicastArgs = null;

    static {
	try {
	    Class secMan;
            secMan = Class.forName("com.sun.tv.media.util.JMFSecurity");
            ClassLoader cl = secMan.getClassLoader();
            if (cl  == null ) { // The class was loaded locally
	    secMan = Class.forName("netscape.security.PrivilegeManager");
	    Class [] args = new Class[1];
	    privilegeManager = secMan;
	    args[0] = java.lang.String.class;
	    enablePrivilege = secMan.getMethod("enablePrivilege", args);
	    threadArgs = new Object[1];
	    threadArgs[0] = new String("UniversalThreadAccess");
	    threadGroupArgs = new Object[1];
	    threadGroupArgs[0] = new String("UniversalThreadGroupAccess");
	    linkArgs = new Object[1];
	    linkArgs[0] = new String("UniversalLinkAccess");
	    superArgs = new Object[1];
	    superArgs[0] = new String("SuperUser");
	    connectArgs = new Object[1];
	    connectArgs[0] = new String("UniversalConnect");
	    readPropArgs= new Object[1];
	    readPropArgs[0] = new String("UniversalPropertyRead");
	    readFileArgs= new Object[1];
	    readFileArgs[0] = new String("UniversalFileRead");
	    writePropArgs = new Object[1];
	    writePropArgs[0] = new String("UniversalPropertyWrite");
	    windowArgs = new Object[1];
	    windowArgs[0] = new String("UniversalTopLevelWindow");
	    eventQueueArgs = new Object[1];
	    eventQueueArgs[0] = new String("UniversalAwtEventQueueAccess");
	    multicastArgs = new Object[1];
	    multicastArgs[0] = new String("UniversalMulticast");
          }
	} catch (ClassNotFoundException e) { // sounds like not Netscape
	    try {
	    Class secMan1 =
		Class.forName("java.security.AccessController");
	    privilegeManager = secMan1;
	    enablePrivilege = secMan1.getMethod("beginPrivileged", null);
	    } catch (Exception e1) {//JDK 1.1 or other system without AC
	    }

	} catch (Exception e) {
	    System.out.println("Could not get the method enablePrivilege");
	}
    }

    public static void beginPrivileged() {}

    public static void endPrivileged() {}

    public static void loadLibrary(String name) throws UnsatisfiedLinkError{
	//System.out.println("About to load a library "+name);
	//Thread.dumpStack();
	// Disable loadLibrary if it's pure java.
	loadNativeLibrary(name,true);

    }

    public static void loadNativeLibrary(String name,boolean enablePrintout)
	throws UnsatisfiedLinkError {

        try {
	    enablePrivilege.invoke(privilegeManager, linkArgs);
	} catch (Exception e) {}

        try {
System.out.println("Loading library " + name);
	    System.loadLibrary(name);
System.out.println("Loaded library " + name);
        } catch (UnsatisfiedLinkError e) {
	    if (enablePrintout)
		System.err.println("JMFSecurity Error: Cannot Load Library " + name); // VV
	    // throw new Exception();
	    throw new UnsatisfiedLinkError();
        }

    }

    public static Object callPrivileged(Method m, Object o, Object [] args)
	throws Exception {
	try {
	    enablePrivilege.invoke(privilegeManager, superArgs);
	} catch (Exception e) {}
	return m.invoke(o, args);
    }

}

