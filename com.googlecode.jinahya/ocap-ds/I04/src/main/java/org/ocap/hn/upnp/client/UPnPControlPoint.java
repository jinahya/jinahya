
package org.ocap.hn.upnp.client;

import org.ocap.hn.upnp.common.UPnPIncomingMessageHandler;
import org.ocap.hn.upnp.common.UPnPOutgoingMessageHandler;
import java.net.InetAddress;

/**
 * This class represents a device control point that can 
 * discover devices and services. It also offers a facility to 
 * directly monitor and modify communication between the control 
 * point and any devices. 
 */
public class UPnPControlPoint
{
   /**
    * Construct the instance.
    */
   protected UPnPControlPoint()
   {
   }

    /**
     * Obtain the local UPnP device control point. 
     *  
     * @return The singleton UPnPControlPoint. 
     */
    public static UPnPControlPoint getInstance()
    {
       return null;
    }

    /**
     * Sets the InetAddresses that the 
     * <code>UPnPControlPoint</code> is associated with. The control
     * point will only send searches and listen for device 
     * advertisements on the most appropriate 
     * interface for each of the addresses specified. 
     *  
     * <p>The passed array replaces any prior addresses that the 
     * control point was associated with. The control point may need
     * to perform searches and update its list of devices in 
     * response to this method being invoked. 
     *  
     * <p>Note that the control point defaults to all home network 
     * interfaces with all their associated IP addresses. A client 
     * application would not normally need to invoke this method. 
     *  
     * @param addresses Array of <code>InetAddress</code> 
     *                   objects representing the IP addresses
     *                   that the control point is associated with.
     *                   May be zero length.
     *  
     * @return Array of prior addresses that were associated with 
     *         the <code>UPnPControlPoint</code>. If there were no
     *         prior addresses, returns a zero-length array.
     *
     * @throws NullPointerException if {@code addresses} or any of its
     * elements is {@code null}.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public InetAddress[] setInetAddresses(InetAddress[] addresses)
    						throws SecurityException
    {
        return null;
    }
    
    /**
     * Gets the <code>InetAddress</code>es that this 
     * <code>UPnPControlPoint</code> is associated with. 
     *  
     * @return Array of <code>InetAddress</code> objects 
     *                   representing the network interfaces
     *                   that this control point is associated with.
     *                   If the control point has no associated
     *                   network interfaces, returns a zero length array.
     *  
     */
    public InetAddress[] getInetAddresses()
    {
        return null;
    }

    /**
     * Gets a client representation of all UPnP root devices visible
     * to this host. This does not cause a search to take place, but
     * simply returns the currently known devices. 
     *
     * @return The UPnP devices visible to this host.  Each element 
     *         in the array of <code>UPnPClientDevice</code>s returned
     *         represents one root device found by the local host
     *         via UPnP discovery. If no root devices are found,
     *         returns a zero-length array.
     */
    public UPnPClientDevice[] getDevices()
    {
        return null;
    }

    /**
     * Gets a client representation of all UPnP devices of the 
     * specified type visible to this host. This does not cause a 
     * search to take place, but simply returns the currently known 
     * devices. 
     *  
     * @param type The type of devices to return. Of the form 
     *             urn:schemas-upnp-org:device:deviceType:v where
     *             deviceType is replaced with a type specific to
     *             the device being requested, and v is a version
     *             specifier as defined in UPnP Device Architecture.
     *  
     * @return The UPnP devices visible to this host matching the 
     *         type specified, of the specified version or lower
     *         version number. Each element in the array of
     *         <code>UPnPClientDevice</code>s returned represents one
     *         device found by the local host via UPnP discovery. If
     *         no devices matching the type are found, returns a
     *         zero-length array.
     */
    public UPnPClientDevice[] getDevicesByType(String type)
    {
        return null;
    }

    /**
     * Gets a client representation of the UPnP devices of the 
     * specified UDN visible to this host. This does not cause a
     * search to take place, but simply returns the currently known 
     * devices. 
     *  
     * <p>Note that normally a UDN is unique and would return a 
     * single device. In multi-homed server and control point 
     * environments, a single server may be visible over multiple 
     * interfaces, resulting in multiple {@code UPnPClientDevice}
     * representations.
     *  
     * @param UDN The UDN of the devices to return. 
     *  
     * @return The UPnP devices visible to this host matching the 
     *         UDN specified. Each element in the array of
     *         <code>UPnPClientDevice</code>s returned represents one
     *         device found by the local host via UPnP discovery. If
     *         no devices matching the UDN are found, returns a
     *         zero-length array.
     */
    public UPnPClientDevice[] getDevicesByUDN(String UDN)
    {
        return null;
    }

    /**
     * Gets a client representation of all UPnP devices containing a
     * service of the specified type, visible to this host. This 
     * does not cause a search to take place, but simply returns the
     * currently known devices. 
     *  
     * @param type The type of service to use in determining which 
     *             devices to return. Of the form
     *             urn:schemas-upnp-org:service:serviceType:v where
     *             serviceType is replaced with a type specific
     *             to the service being requested, and v is a
     *             version specifier as defined in UPnP Device
     *             Architecture.
     *  
     * @return The UPnP devices visible to this host containing a 
     *         service matching the type specified, of the specified
     *         version or lower version number. Each element in the
     *         array of <code>UPnPClientDevice</code>s returned represents
     *         one device found by the local host via UPnP
     *         discovery. Returns only devices directly containing
     *         a service of the matching type, not devices where
     *         only their embedded devices contain a service of the
     *         matching type. If no devices matching the criteria
     *         are found, returns a zero-length array.
     */
    public UPnPClientDevice[] getDevicesByServiceType(String type)
    {
        return null;
    }

    /**
     * Initiate a UPnP M-SEARCH for UPnP root devices. The UPnP
     * stack constantly monitors for device arrival and departure. 
     * This method is used to assist with detection of devices which 
     * may not renew advertisements correctly and only respond to 
     * search requests. 
     *  
     * @param mx The maximum time in seconds for client devices to 
     *           respond to this search.
     *
     */
    public void search(int mx)
    {
        return;
    }

    /**
     * Adds a listener for device changes. 
     * Each <code>UPnPClientDeviceListener</code>is notified when a UPnP
     * device is added to or removed from a home network. 
     *  
     * <p>Adding a listener which is the same instance as a 
     * previously added (and not removed) listener has no effect. 
     *
     * @param listener The listener to add.
     */
    public void addDeviceListener(UPnPClientDeviceListener listener)
    {
    	
    }

    /**
     * Removes a device listener.
     *
     * @param listener The listener to remove.
     */
    public void removeDeviceListener(UPnPClientDeviceListener listener)
    {
    	
    }


    /**
     * Sets a message handler for incoming messages (advertisements, 
     * evented state variables, action responses, device and service
     * descriptions). Calls to set the message handler replace any 
     * prior incoming message handler. 
     *  
     * <p>A message handler may be removed by passing null as the 
     * inHandler. In the absence of a registered message handler the 
     * stack will parse the incoming messages. 
     *  
     * <p>If the application-provided handler throws any exceptions 
     * during execution, the stack will attempt to process the 
     * message with the default (stack-provided) handler.  
     *  
     * @param inHandler The incoming message handler to set. 
     * 
     * @throws SecurityException if the calling application has not
     *      been granted
     *      MonitorAppPermission("handler.homenetwork").
     */
    public void setIncomingMessageHandler(UPnPIncomingMessageHandler inHandler)
    				throws SecurityException
    {
    	
    }

    /**
     * Sets a message handler for outgoing messages (action 
     * invocations, subscription requests, device and service 
     * retrievals). Calls to set the message handler replace any 
     * prior outgoing message handler.  
     *  
     * <p>A message handler may be removed by passing null as the 
     * outHandler. In the absence of a registered message handler the
     * stack will process the outgoing messages. 
     *  
     * <p>If the application-provided handler throws any exceptions 
     * during execution, the stack will attempt to process the 
     * message with the default (stack-provided) handler.  
     *  
     * @param outHandler The outgoing message handler to set.
     *
     * @throws SecurityException if the calling application has not
     *      been granted
     *      MonitorAppPermission("handler.homenetwork").
     */
    public void setOutgoingMessageHandler(UPnPOutgoingMessageHandler outHandler)
    				throws SecurityException
    {
    	
    }
}
