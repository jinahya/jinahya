/*
 * @(#)InterfaceMapImpl.java	1.2 99/11/05
 * 
 * Copyright (c) 1999 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 */

package com.sun.tv.net;

import javax.tv.net.*;
import javax.tv.locator.*;
import java.net.InetAddress;
import java.util.*;

/**
 **  Class <code>InterfaceMap</code> reports the local IP address
 **   assigned to a given service component.  Applications may use the
 **  returned IP address to specify the network interface to which an
 **  instance of java.net.DatagramSocket or java.net.MulticastSocket
 **  should bind. 
 **/
public class InterfaceMapImpl {

    public static Hashtable addrUsed = new Hashtable();
    public static Vector    addrFree = new Vector();

    public static boolean isBcastEncapIP(InetAddress addr) {
	if ( addrUsed != null && addr != null) {
	    return addrUsed.contains(addr);
	}
	return false;
    }

    public static Locator getLocator(InetAddress addr) {
	if ( addrUsed == null || addr == null)
		return null;

	try {
		Enumeration list = addrUsed.keys();
		while (list.hasMoreElements()) {
			String key = (String)list.nextElement();
			InetAddress daddr = (InetAddress)addrUsed.get(key);
			if (daddr == null) 
				continue;
	
			if (daddr == addr) {
				LocatorFactory factory = LocatorFactory.getInstance();
				return factory.createLocator(key);
			}
		}
	} catch (Exception e) {
		;
	}
	return null;
    }
		
    static void returnToAddrPool(InetAddress addr) {
	if ( addrUsed == null || addr == null)
		return;

	Enumeration list = addrUsed.keys();
	while (list.hasMoreElements()) {
		String key = (String)list.nextElement();
		InetAddress daddr = (InetAddress)addrUsed.get(key);
		if (daddr == null) 
			continue;

		if (daddr == addr) {
			addrUsed.remove(key);
			addrFree.addElement(addr);
			break;
		}
	}
    }
}
