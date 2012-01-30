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
	  return null;
  }
}
