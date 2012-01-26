package org.dvb.net.rc;

import org.davic.resources.*;

import java.io.IOException;

/**
 * This class models a connection based return channel network interface for use in 
 * receiving and transmitting IP packets over a return channel.
 * Targets for connections are specified as strings including the number to dial. These 
 * strings can only include either numbers or a "+" character (as the first character only).
 * <p>When a <code>ConnectionRCInterface</code> instance is first obtained by an application, the 
 * current target shall be set to the default. Applications which wish to use a non-default target 
 * need to set this target before attempting to reserve the <code>ConnectionRCInterface</code>. 
 * This is because if the application does not have the permission to use the default target, 
 * the <code>reserve()</code> method is required throw a <code>SecurityException</code>.
 */

public class ConnectionRCInterface extends RCInterface implements 
	ResourceProxy {

	/**
	 * Constructor for instances of this class. This constructor is provided for
         * the use of implementations and specifications which extend the present document.
         * Applications shall not define sub-classes of this class. Implementations are not
         * required to behave correctly if any such application defined sub-classes are used.
	 */
	protected ConnectionRCInterface()
	{
	}
	/**
	 * Check if this interface is connected. Connected means able to
	 * receive and transmit packets.
	 *
	 * @return true if the interface is connected, otherwise false
	 */
	public boolean isConnected()
	{
		return false;
	}
	/**
	 * Obtain an estimate of the setup time for a successful connection for this interface in seconds.
	 *
	 * @return an estimate of the setup time for a successful connection for this interface in seconds.
 	 */
	public float getSetupTimeEstimate()
	{
		return 0.0f;
	}

	/**
	 * Request the right to control this return channel interface.
	 * If the right to control the return channel interface has already been reserved 
         * then this method shall have no effect.
         * <p>The details of the current connection target shall be obtained from the 
	 * <code>ConnectionParameters</code> instance which is the current target
	 * during the call to this method. Hence changes to that <code>ConnectionParameters</code>
	 * instance before a call to this method shall be taken account of during the 
	 * method call. Changes after the call to this method shall have no effect on that connection.
	 * @param c the object to be notified when resources are removed
	 * @param requestData Used by the Resource Notification API in the 
	 * requestRelease method of the ResourceClient interface.
	 * The usage of this parameter is optional and a null reference 
	 * may be supplied.
	 * @throws PermissionDeniedException if this interface cannot be reserved
	 * @throws SecurityException if the application is
 	 * denied access to the resource by security policy.
	 */
	public void reserve(ResourceClient c, Object requestData) throws
		PermissionDeniedException
	{
	}

	/**
	 * Release the right to control this return channel interface. If
	 * this object does not have the right to control this return channel
	 * interface then this method shall have no effect.
	 */
	public void release()
	{
	}

	/**
 	 * Connect this return channel to the current target. 
	 * If this ResourceProxy does not have the underlying resource reserved 
	 * then a PermissionDeniedException will be thrown. 
	 * Where the underlying resource is reserved but at the time
	 * the method is called, it is known that connection is impossible then
	 * an IOException will be thrown. 
	 * Apart from this, this method is asynchronous
	 * and completion or failure is reported through the event listener on this
	 * class.
	 * If a connection is already established when this method is called then
 	 * the method call shall have no effect.
	 * @throws PermissionDeniedException if this application does not own the resource
	 * @throws IOException if connection is known to be impossible at the time
	 * when the method is called
	 */
	public void connect() throws IOException, PermissionDeniedException
	{
	}

	/**
	 * Disconnect this return channel from the current target. This method is
	 * asynchronous and completion is reported through the event listener on this
	 * class. This method does not release the underlying resource from the
	 * ResourceProxy. If no connection is established then this method shall have no effect.
	 * 
	 * @throws PermissionDeniedException if this application does not own the resource
	 */
	public void disconnect() throws PermissionDeniedException
	{
	}

	/**
	 * Get the current target for connections. 
	 * <p>If this ConnectionRCInterface is connected then this method shall return the target 
	 * to which the connection was made. If this ConnectionRCInterface is not connected 
	 * then this method shall return the last target set by the setTarget method (if any) 
	 * otherwise the default.
	 * <p>This returns either the default target or the last target set by this 
	 * application calling the setTarget method on this instance before the 
	 * connection was established. This applies regardless of whether the connection 
	 * was established by another MHP application or if some of the connection 
	 * parameters have been supplied by the server.
	 * @return the current set of connection target parameters
	 * @throws IncompleteTargetException if the current target is not completely configured
	 * @throws SecurityException if the application is not allowed to read the current target
	 * as defined by the security policy of the platform
	 */
	public ConnectionParameters getCurrentTarget() throws IncompleteTargetException
	{
		return null;
	}
	
	/**
 	 * Set a non-default target for connections. <p>
 	 * If this method is called for a ConnectionRCInterface which is connected then 
         * successful calls to this method shall not interrupt that connection. The 
         * newly set target shall just be stored until either the next time a connection 
	 * is established with that ConnectionRCInterface instance or until a subsequent call 
	 * to setTarget on that ConnectionRCInterface.<p>
         * The details of the current connection target shall be obtained from the newly set 
         * target during the call to this method. Changes to that instance after the call to 
         * this method shall have no effect on the ConnectionRCInterface unless/until 
         * setTarget is called again for that instance or it is released and reserved again.
	 * @param target the new set of connection target parameters
	 * @throws IncompleteTargetException if the application owns the resource but the target is not completely specified
	 * @throws PermissionDeniedException this exception shall never be thrown
	 * @throws SecurityException if the application is not allowed to modify the target
	 * as defined by the security policy of the platform
	 */
	public void setTarget(ConnectionParameters target) throws IncompleteTargetException, 
		PermissionDeniedException
	{

	}
	/**
 	 * Set the target for connections to the default. 
 	 *
	 * @throws PermissionDeniedException if this application does not own the resource
	 * @throws SecurityException if the application is not allowed to connect to the default target
	 */
	public void setTargetToDefault() throws
		PermissionDeniedException
	{

	}

	/**
	 * Return the time an interface has been connected
	 *
	 * @return the time in seconds for which this interface has been connected or
	 * -1 if the device is not connected
	 */
	public int getConnectedTime()
	{
		return 0;
	}

	/**
	 * Return the object which asked to be notified about withdrawal of the
	 * underlying resource. This is the object provided as the first parameter to
	 * the last call to the reserve method on this object. If this object 
	 * does not have the underlying resource reserved then null is returned.
	 *
	 * @return the object which asked to be notified about withdrawal of the 
	 * underlying physical resource from this resource proxy or null
	 */

	public ResourceClient getClient()
	{
		return null;
	}

	/**
	 * Add a listener for events related to connections of this interface.
	 *
	 * @param l the listener for the connection related events
	 */
	public void addConnectionListener( ConnectionListener l )
	{
	}
	/**
	 * Remove a listener for events related to connections of this interface.
	 * If the listener specified is not currently receiving these events then
	 * this method has no effect.
	 *
	 * @param l the listener for the connection related events
	 */
	public void removeConnectionListener( ConnectionListener l )
	{
	}

}



