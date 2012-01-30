package org.dvb.net.rc;

import java.net.*;

import org.davic.resources.*;

/**
 * This class is the factory and manager for all return channel interfaces in the system.
 * The methods on this class which return instances of the <code>RCInterface</code>
 * will only return new instances of that class under the following conditions:
 * <ul>
 * <li>on the first occasion an instance needs to be returned to a particular application for a particular interface.
 * <li>when new return channel interfaces are added to the system
 * </ul>
 */

public class RCInterfaceManager implements org.davic.resources.ResourceServer {
	/**
	 * package scope constructor to stop javadoc generating one
	 */
	RCInterfaceManager()
	{
	}

	/**
	 * Factory method to obtain a manager.
	 * The RCInterfaceManager is either a singleton for each MHP application or a singleton for the MHP terminal.
	 *
	 * @return an instance of an RCInterfaceManager
	 */
	public static RCInterfaceManager getInstance()
	{
		return null;
	}
	/**
	 * Factory method to return a list of all return channel interfaces
	 * visible to this application. The number of entries in the array will 
	 * exactly match the number of return channel interfaces visible to 
	 * the application. Null is returned if no interfaces are visible to 
	 * this application. 
	 *
	 * @return an array of available return channel interfaces
 	 */
	public RCInterface[] getInterfaces()
	{
		return null;
	}
	/**
	 * Return the interface which will be used when connecting to a particular host.
	 * Null is returned if this is not known when the method is called. 
	 *
	 * @param addr the IP address of the host to connect to
	 * @return the interface which will be used or null if this is not known
	 */
	public RCInterface getInterface(InetAddress addr) 
	{
		return null;
	}
	/**
	 * Return the interface which is used for a particular socket.
	 *
	 * @param s the socket to use
	 * @return the interface which is used or null if the socket is not connected
	 */
	public RCInterface getInterface(Socket s) 
	{
		return null;
	}
	/**
	 * Return the interface which is used for a particular URLConnection
	 *
	 * @param u the URLConnection to use
	 * @return the interface which is used or null if the URLConnection is not connected
	 */
	public RCInterface getInterface(URLConnection u) 
	{
		return null;
	}
	/**
	 * This method informs a resource server that a particular object should be 
	 * informed of changes in the state of the resources managed by that server.
	 *
	 * @param listener the object to be informed of state changes
	 */

	public void addResourceStatusEventListener (ResourceStatusListener listener)
	{
	}

	/**
	 * This method informs a resource server that a particular object is no 
	 * longer interested in being informed about changes in state of resources 
	 * managed by that server. If the object had not registered its interest 
	 * initially then this method has no effect.
	 *
	 * @param listener the object which is no longer interested
	 */

	public void removeResourceStatusEventListener(ResourceStatusListener listener)
	{
	}
}


