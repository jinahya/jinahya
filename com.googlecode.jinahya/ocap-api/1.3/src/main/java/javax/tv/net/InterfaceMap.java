/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.net;

import javax.tv.locator.*;

import java.net.InetAddress;
import java.io.IOException;

/** 
 * Class <code>InterfaceMap</code> reports the local IP address
 * assigned to a given service component that carries IP data.
 * Applications may use the returned IP address to specify the network
 * interface to which an instance of
 * <code>java.net.DatagramSocket</code> or
 * <code>java.net.MulticastSocket</code> should bind.
 *
 * @see java.net.DatagramSocket#DatagramSocket(int, java.net.InetAddress)
 * java.net.DatagramSocket(int, java.net.InetAddress)
 *
 * @see java.net.MulticastSocket#setInterface(java.net.InetAddress)
 * java.net.MulticastSocket.setInterface(java.net.InetAddress)
 *
 * @author Jon Courtney courtney@eng.sun.com
 */
public class InterfaceMap
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private InterfaceMap() { }

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
        throws InvalidLocatorException, IOException
    {
        return null;
    }
}
