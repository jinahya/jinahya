/*
 * @(#)PackageManager.java	1.16 98/08/10
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

package javax.media;

import java.util.*;
import com.sun.tv.media.util.JMFProperties;

/**
 * A <CODE>PackageManager</CODE> maintains a persistent store of
 * package-prefix lists. A package prefix specifies the
 * prefix for a complete class name. A factory uses
 * a package-prefix list to find a class that
 * might belong to any of the packages that are referenced
 * in the prefix list.<p>
 *
 * The <code>Manager</code> uses package-prefix lists
 * to find protocol handlers and content handlers for time-based
 * media.<p>
 *
 * The current version of a package-prefix list is obtained with
 * the <code>get&lt;package-prefix&gt;List</code> method. This method returns the prefix
 * list in use; any changes to the list take effect immediately.
 *
 * Unless it is made persistent with
 * <code>commit&lt;package-prefix&gt;List</code>, a package-prefix list is only valid 
 * while the <code>Manager</code> is referenced.
 * 
 * The <code>commit&lt;package-prefix&gt;List</code> method ensures that any changes made 
 * to a package-prefix list are still visible the next time that
 * the <code>Manager</code> is referenced.
 *
 * @see Manager
 * @version 1.16, 98/08/10.
 */


public class PackageManager {

    /**
     * The package prefix used when searching for protocol
     * handlers.
     *
     * @see Manager
     */
    static Vector protoPrefixList;
    static {
	protoPrefixList = getPersistentProtocolPrefixList();
    }

    /**
     * Get the current value of the protocol package-prefix list.
     * <p>
     * @return The protocol package-prefix list.
     */
    static public Vector getProtocolPrefixList() {
	SecurityManager security = System.getSecurityManager();
	//if (security != null)
	//    security.checkPropertiesAccess();
	return protoPrefixList;
    }

    /**
     * Set the protocol package-prefix list.
     * This is required for changes to take effect.
     *
     * @param list The new package-prefix list to use.
     */
    static public void setProtocolPrefixList(Vector list) {
	/* Causing security exceptions in Netscape - removed for 1.1
	SecurityManager security = System.getSecurityManager();
	if (security != null)
	    security.checkPropertiesAccess();
	*/
	protoPrefixList = (Vector)list.clone();
    }

    /**
     * Make changes to the protocol package-prefix list persistent.
     * <p>
     * This method throws a <code>SecurityException</code> if the calling thread
     * does not have access to system properties.
     *
     */
    static public void commitProtocolPrefixList() {
	SecurityManager security = System.getSecurityManager();
	if (security != null)
	    security.checkPropertiesAccess();
	setPersistentProtocolPrefixList(protoPrefixList);
    }

    
    /**
     * The package prefix used when searching for content
     * handlers.
     *
     * @see Manager
     */
    static Vector contentPrefixList;
    static {
	contentPrefixList = getPersistentContentPrefixList();
    }

    /**
     * Get the current value of the content package-prefix list.
     * Any changes made to this list take effect immediately.
     * <p>
     *
     * @return The content package-prefix list.
     */
    static public Vector getContentPrefixList() {
	SecurityManager security = System.getSecurityManager();
	//if (security != null)
	//    security.checkPropertiesAccess();
	return contentPrefixList;
    }

    /**
     * Set the current value of the content package-prefix list.
     * This is required for changes to take effect.
     *
     * @param list The content package-prefix list to set.
     */
    static public void setContentPrefixList(Vector list) {
	/* Causing security exceptions in Netscape - removed for 1.1
	SecurityManager security = System.getSecurityManager();
	if (security != null)
	    security.checkPropertiesAccess();
	*/
	contentPrefixList = (Vector)list.clone();
    }
    
    /**
     * Make changes to the content prefix-list persistent.
     * <p>
     * This method throws a <code>SecurityException</code> if the calling thread
     * does not have access to system properties.
     *
     */
    static public void commitContentPrefixList() {
	SecurityManager security = System.getSecurityManager();
	if (security != null)
	    security.checkPropertiesAccess();
	setPersistentContentPrefixList(contentPrefixList);
    }

    //
    // Methods to implement persistence.
    // $jdr: Obviously we need to figure out how to implement
    // these.

    /*
     * Protocol list.
     */
    static private void setPersistentProtocolPrefixList(Vector protoList) {
	JMFProperties.setProperty("protocol.prefixes", list2str(protoList));
	JMFProperties.writeProperties();
    }
    
    static private Vector getPersistentProtocolPrefixList() {
	return str2list(JMFProperties.getProperty("protocol.prefixes"));
    }

    
    /*
     * Content list
     */
    static private void setPersistentContentPrefixList(Vector contentList) {
	JMFProperties.setProperty("content.prefixes", list2str(contentList));
	JMFProperties.writeProperties();
    }

    static private Vector getPersistentContentPrefixList() {
	return str2list(JMFProperties.getProperty("content.prefixes"));
    }


    static private String list2str(Vector list) {
	Enumeration elist = list.elements();
	String value = null;
	while (elist.hasMoreElements()) {
	    String name = (String)elist.nextElement();
	    if (value == null)
		value = new String(name);
	    else
		value = value + "|" + name;
	}
	return value;
    }

    static private Vector str2list(String str) {
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
