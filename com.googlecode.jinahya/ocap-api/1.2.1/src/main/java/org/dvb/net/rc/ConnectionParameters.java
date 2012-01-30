package org.dvb.net.rc;

import java.net.InetAddress;

/**
 * This class encapsulates the parameters needed to specify the target
 * of a connection. 
 */

public class ConnectionParameters {
	/**
	 * Construct a set of connection parameters. Details of the DNS server to use are
	 * supplied by the server.
	 *
	 * @param number the target of the connection, e.g. a phone number
	 * @param username the username to use in connection setup
	 * @param password the password to use in connection setup
	 */
	public ConnectionParameters(String number, String username, String password)
	{
	}
	/**
	 * Construct a set of connection parameters.
	 *
	 * @param number the target of the connection, e.g. a phone number
	 * @param username the username to use in connection setup
	 * @param password the password to use in connection setup
	 * @param dns the list of DNS servers to try before reporting failure. The order in 
	 * which they are interrogated is not specified. Once one result has been obtained, 
	 * there is no requirement to try others.
	 */
	public ConnectionParameters(String number, String username, String password, InetAddress[] dns)
	{
	}
	/**
	 * Return the target of this connection for example a phone number.
         * The value returned shall be the one passed into the constructor of this instance.
	 *
	 * @return the target of the connection
	 */
	public String getTarget()
	{
		return null;
	}
	/**
	 * Return the username used in establishing this connection
         * The value returned shall be the one passed into the constructor of this instance.
	 *
	 * @return the username used in establishing the connection
	 */
	public String getUsername()
	{
		return null;
	}
	/**
	 * Return the password used in establishing this connection
         * The value returned shall be the one passed into the constructor of this instance.
	 *
	 * @return the password used in establishing this connection
	 */
	public String getPassword() 
	{
		return null;
	}
	/**
	 * Return the addresses of the DNS servers to use for the connection
	 *
	 * @return return the addresses of the DNS servers passed into the 
	 * constructor of the instance or null if none was provided.
	 */
	public InetAddress[] getDNSServer() 
	{
		return null;
	}
}

