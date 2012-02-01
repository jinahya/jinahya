/*
 * @(#)InterfaceMap.java	1.14 00/08/06
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.net;

import java.net.InetAddress;
import javax.tv.locator.*;
import java.io.IOException;
import java.util.*;

/**
 * Class <code>InterfaceMap</code> reports the local IP address
 * assigned to a given service component that carries IP data.
 * Applications may use the returned IP address to specify the network
 * interface to which an instance of
 * <code>java.net.DatagramSocket</code> or
 * <code>java.net.MulticastSocket</code> should bind.
 *
 * @see java.net.DatagramSocket#DatagramSocket(int, java.net.InetAddress)
 * @see java.net.MulticastSocket#setInterface
 *
 */
public class InterfaceMap {

  private static int fourthNumber = 0;
  private static int thirdNumber = 1;

  private InterfaceMap() {}

  /**
   * Reports the local IP address assigned to the given service
   * component.
   * 
   * @param locator The service component for which the local IP
   * address mapping is required.
   * 
   * @return The IP address assigned to this service component.
   *     
   * @throws InvalidLocatorException If the given locator does not
   * refer to a valid source of IP data, or if this system does not
   * support the reception of broadcast IP data.
   *
   * @throws IOException If a local IP address is not available to
   * be assigned to the source of IP data.
   *    
   */
  public static InetAddress getLocalAddress(Locator locator)
    throws InvalidLocatorException, IOException {

	if (locator == null) {
		throw new NullPointerException();
	}
	if (locator.toExternalForm() == null) {
		throw new NullPointerException();
	}

	if (com.sun.tv.LocatorImpl.isServiceComponent(locator) == false) {
		throw new InvalidLocatorException(locator, "Not a ServiceComponent locator");
	}

	if (com.sun.tv.net.EncapIPStream.isIPStreamLocator(locator) == false) {
		throw new InvalidLocatorException(locator, "Not a source of IP data");
	}

	Hashtable addrUsed = com.sun.tv.net.InterfaceMapImpl.addrUsed;

	InetAddress addr = (InetAddress)addrUsed.get(locator.toExternalForm());
	if (addr == null) {
		addr = getNextIP();
		if (addr != null) {
			addrUsed.put(locator.toExternalForm(), addr);
		}
	}
	if (addr == null) {
		throw new IOException("Can't get the next local IP address");
	}
	return addr;
  }

    /**
     * Expand the address pool by adding the next POOL_EXPN_SIZE addresses in 
     * 192.168.x.x class to the addrPool variable. 
     * 
     * @return true if expansion is successful, false if out of address
     */
    private static InetAddress getNextIP() {
	InetAddress addr = null;

	Vector addrFree = com.sun.tv.net.InterfaceMapImpl.addrFree;
	if (addrFree.size() > 0) {
		addr = (InetAddress)addrFree.elementAt(0);
		addrFree.removeElementAt(0);
		return addr;
	}

	fourthNumber++;

	// start with the first 256 addresses
	if (fourthNumber >= 0xff) {
		fourthNumber = 1;
		thirdNumber++;
		if ( thirdNumber > 0xff ) {
			fourthNumber = 1;
			thirdNumber = 1;
		}
	}

	String ip = "192.168."+thirdNumber+"."+fourthNumber;
	try {
		addr = InetAddress.getByName(ip);
	} catch (Exception e) {
		;
	}
	return addr;
    }
}
