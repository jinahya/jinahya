package org.dvb.net;

import java.net.*;


/**
 * This class provides additional control over buffering for <code>DatagramSocket</code>s.
 */

public class DatagramSocketBufferControl {

	/**
	 * non-public constructor to stop javadoc generating a public one
	 */
	DatagramSocketBufferControl(){}

  /**
    Sets the SO_RCVBUF option to the specified value for this
    DatagramSocket. The SO_RCVBUF option is used by the platform's
    networking code as a hint for the size to use when allocating the
    underlying network I/O buffers.<p>

    Increasing buffer size can increase the performance of network I/O
    for high-volume connection, while decreasing it can help reduce
    the backlog of incoming data.  For UDP, this sets the buffer size
    for received packets.<p>

    Because SO_RCVBUF is a hint, applications that want to verify what
    size the buffers were set to should call getReceiveBufferSize.

    This method shall throw IllegalArgumentException - if size is 0 or is negative.

    @param d The DatagramSocket for which to change the receive buffer
    size.

    @param size The requested size of the receive buffer, in bytes.

    @throws SocketException - If there is an error when setting the
    SO_RCVBUF option.

  */
  public static void setReceiveBufferSize(DatagramSocket d, int size)
    throws java.net.SocketException {}

  /**

    Get value of the SO_RCVBUF option for this socket, that is the
    buffer size used by the platform for input on the this Socket.
    The value returned need not be the value previously set by setReceiveBufferSize (if any).

    @param d The DatagramSocket for which to query the receive buffer
    size.

    @return The size of the receive buffer, in bytes.

    @throws SocketException - If there is an error when querying the
    SO_RCVBUF option.
  */

  public static int getReceiveBufferSize(DatagramSocket d)
    throws java.net.SocketException {return 0;}
}


