package org.ocap;

import java.io.*;

/**
 * This class provides system utility functions.
 *
 * @author Brent Foust
 * @author Frank Sandoval
 * @author Shigeaki Watanabe
 */
public final class OcapSystem
{
   /**
    * Do not use.  This is to prevent a public constructor from being 
    * generated.
    */
   private OcapSystem()
   {
   }

/**
    * Called by the monitor application to inform the OCAP implementation
    * that it is configuring and the boot process may resume after it
    * calls the <code>monitorConfiguredSignal</code> method, see Section
    * 20.2.2.3 Boot Process while connected to the cable network – CableCARD
    * device present.  
    * <br>
    * On invocation of this method, the APIs used for conformance testing, 
    * specifically, <code>org.ocap.test.OCAPTest</code> SHALL be initialized
    * for use.  This means that the implementation SHALL perform the 
    * following actions:
    * <ul>
    * <li>a. Open a socket for receiving UDP datagrams on a port, the value  
    * of which is specified in the <code>port</code> parameter passed to this 
    * method.</li>
    * <li>b. Wait to receive a datagram that contains a string formatted
    * thus: <code>ate:a.b.c.d:xxxx:ppp</code> (string may be null-terminated),
    * where 'a.b.c.d' represents an IPv4 address, and 'xxxx' represents an
    * IP port number, and 'ppp' represents protocol type ('TCP' for TCP/IP and 
    * 'UDP' for UDP/IP).  Any received datagrams which do not contain a 
    * properly formatted payload string SHALL be ignored.  Once a datagram
    * with a properly formatted string has been received, the datagram
    * socket SHALL be closed.</li>
    * <li>c. Attempt to establish a TCP or UDP socket connection to the test system 
    * using the IPv4 address and port number obtained in b. The protocol type 
    * for the socket connection is specified by 'ppp' string in b. This connected 
    * socket SHALL be used solely to transmit and receive data originating 
    * from the <code>org.ocap.test.OCAPTest</code> APIs and SHALL NOT be 
    * accessible to applications through other APIs.  The TCP or UDP socket 
    * connection  shall have a timeout of 'infinite'.  If this method 
    * does not complete within the specified timeout period, an 
    * <code>IOException</code> SHALL be thrown.</li>
    * <li>d. Return control to the caller.</li>
    * </ul>   
    * <br>
    * If this method is called with both the <code>port</code> and 
    * <code>timeout</code> parameters set to 0, then the  OCAP 
    * implementation SHALL not enable the conformance testing APIs, which 
    * SHALL just return silently, without performing any action.  
    * <br>


    * If the monitor application does not call this method in the time 
    * specified in section 20.2.2.3 Boot Process while connected to the
    * cable network - CableCARD device present, then the OCAP
    * implementation SHALL behave the same as if this method had been called
    * with 0 specified for both the <code>port</code> and <code>timeout</code> 
    * parameters.



    * If the monitor application does not call this method in the time
    * specified in section 20.2.2.3 Boot Process while connected to the
    * cable network - CableCARD device present, then the implementation
    * SHALL behave the same as if this method had been called with 0
    * specified for both the <code>port</code> and <code>timeout</code> 
    * parameters.
    *
    * @param port the IP port number to listen for datagrams from the test
    * system on.
    * 
    * @param timeout the time, in seconds to allow for a communications 
    * channel to be established with the test system.  
    *
    * @throws SecurityException if application does not have 
    * MonitorAppPermission("signal.configured").
    *
    * @throws IOException if a communications channel cannot be established
    * with the test system within the amount of time specified by the 
    * <code>timeout</code> parameter. 
    */
    public static void monitorConfiguringSignal(int port, int timeout)
        throws IOException
    {
    }

   /**
    * Called by the Initial Monitor Application to inform the OCAP
    * implementation it has completed its configuration process and
    * that the boot processing may resume.  It is recommended that
    * the monitor call this method as soon as possible after the
    * <code>monitorConfiguringSignal</code> method has been 
    * called.
    */    public static void monitorConfiguredSignal()
    {
    }
}
