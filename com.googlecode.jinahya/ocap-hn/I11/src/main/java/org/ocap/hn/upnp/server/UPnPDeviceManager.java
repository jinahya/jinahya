package org.ocap.hn.upnp.server;

import org.ocap.hn.upnp.common.UPnPIncomingMessageHandler;
import org.ocap.hn.upnp.common.UPnPOutgoingMessageHandler;
import java.io.InputStream;
import java.io.IOException;


/**
 * This class represents a manager that can create devices and services for
 * devices.  Creating a device does not cause corresponding UPnP
 * device advertisement until the 
 * <code>UPnPManagedDevice.sendByeBye()</code> method is called 
 * followed by a call to 
 * <code>UPnPManagedDevice.sendAlive()</code>. 
 */
public class UPnPDeviceManager
{
   /**
    * Construct the instance.
    */
   protected UPnPDeviceManager()
   {
      
   }

    /**
     * Obtain the local UPnP device manager. 
     *  
     * @return The singleton UPnPDeviceManager.
     *
     * @throws UnsupportedOperationException if this implementation does not
     * support UPnP server-side operations.
     */
    public static UPnPDeviceManager getInstance()
    {
       return null;
    }

    /**
     * Gets locally hosted UPnPManagedDevices.
     *
     * @return UPnPManagedDevice representations of UPnP root 
     *         devices local to the host. Each element in the
     *         array of <code>UPnPManagedDevice</code>s returned
     *         represents one root device exposed by the local host
     *         through UPnP advertisement. It does NOT return any
     *         UPnPManagedDevices which are not currently advertised
     *         on the home network. If there are no
     *         UPnPManagedDevices to return, returns a zero-length
     *         array.
     */
    public UPnPManagedDevice[] getDevices()
    {
        return null;
    }

    /**
     * Gets a server representation of all UPnP devices of the
     * specified type advertised by this host. This does not cause a
     * search to take place, but simply returns the currently known 
     * devices. 
     *  
     * @param type The type of devices to return. Of the form 
     *             urn:schemas-upnp-org:device:deviceType:v where
     *             deviceType is replaced with a type specific to
     *             the device being requested, and v is a version
     *             specifier as defined in UPnP Device Architecture.
     *  
     * @return The UPnPManagedDevices advertised on this host 
     *         matching the type specified, of the specified version
     *         or lower version number. Each element in the array of
     *         <code>UPnPManagedDevice</code>s returned represents
     *         one device advertised on the local host. If no
     *         devices matching the type are found, returns a
     *         zero-length array.
     */
    public UPnPManagedDevice[] getDevicesByType(String type)
    {
        return null;
    }

    /**
     * Gets a server representation of any UPnPManagedDevices of the
     * specified UDN advertised by this host. This does not cause a 
     * search to take place, but simply returns the currently known 
     * devices. 
     *  
     * <p>Note that normally a UDN is unique and would return a 
     * single device. While it is not valid to have multiple 
     * UPnPManagedDevices with the same UDN, the stack does not 
     * enforce this and consequently there is the potential to 
     * return more than one matching UPnPManagedDevice 
     *  
     * @param UDN The UDN of the devices to return. 
     *  
     * @return The UPnP devices advertised on this host matching the
     *         UDN specified. Each element in the array of
     *         <code>UPnPManagedDevice</code>s returned represents
     *         one device advertised by the local host. If no
     *         devices matching the UDN are found, returns a
     *         zero-length array.
     */
    public UPnPManagedDevice[] getDevicesByUDN(String UDN)
    {
        return null;
    }

    /**
     * Gets a server representation of any UPnPManagedDevices 
     * containing a service of the specified type, advertised by 
     * this host. This does not cause a search to take place, but 
     * simply returns the currently known devices. 
     *  
     * @param type The type of service to use in determining which 
     *             devices to return. Of the form
     *             urn:schemas-upnp-org:service:serviceType:v where
     *             serviceType is replaced with a type specific
     *             to the service being requested, and v is a
     *             version specifier as defined in UPnP Device
     *             Architecture.
     *  
     * @return The UPnPManagedDevices advertised by this host 
     *         containing a service matching the type specified, of
     *         the specified version or lower version number. Each
     *         element in the array of
     *         <code>UPnPManagedDevice</code>s returned represents
     *         one device advertised by the local host. Returns
     *         only devices directly containing a service of the
     *         matching type, not devices where only their embedded
     *         devices contain a service of the matching type. If no
     *         devices matching the criteria are found, returns a
     *         zero-length array.
     */
    public UPnPManagedDevice[] getDevicesByServiceType(String type)
    {
        return null;
    }

    /**
     * Creates a UPnP device in the local host.  The object created 
     * implements the <code>UPnPManagedDevice</code> interface. 
     *  
     * <p>The <code>description</code> parameter applies to this 
     * device only and does not contain embedded device 
     * descriptions. Embedded devices are created when this method 
     * is called with the <code>parent</code> parameter referencing 
     * a <code>UPnPManagedDevice</code> object and a root device is 
     * created when the <code>parent</code> parameter is null.</p> 
     *
     * <p>When the <code>parent</code> is not null this device is added to the
     * parent as an embedded device and the parent 
     * <code>getManagedEmbeddedDevices</code> method return value 
     * will include the new embedded device. 
     *  
     * <p>The <code>description</code> parameter must not include 
     * any serviceList or service elements. Services are added to
     * the device description by calling
     * {@link UPnPManagedDevice#createService} on the returned
     * {@code UPnPManagedDevice}. 
     *  
     * <p>Since the stack does not provide any persistence of the 
     * device description, it is the responsibility of the 
     * application to provide the UDN element within the device 
     * description, and implement any persistence requirements for 
     * UDN as described by the UPnP Device architecture or other 
     * related specifications. 
     *  
     * <p>The <code>description</code> parameter must not include 
     * any iconList or icon elements. Icons are added to the device
     * description based upon the passed <code>icons</code> 
     * parameter, or through calling setIcons() on the
     * returned <code>UPnPManagedDevice</code>. 
     *  
     * <p>Within the description, the contents of the URLBase 
     * element (if present) are ignored and will be replaced by the 
     * stack if the element is present. As the URLBase element is 
     * optional, the stack will not add the element if it was 
     * absent from the passed description. 
     *  
     * <p>All other URL-type elements of the device description are 
     * unmodified. It is the responsibility of the application to 
     * handle requests for the manufacturerURL, modelURL and 
     * presentationURL where these are relative URLs as defined in 
     * UPnP Device Architecture.
     *
     * @param parent The parent device of this device.  If the 
     *               parent parameter is null then the device to be
     *               created is a root device, otherwise it is
     *               created as an embedded device of the parent.
     *               The device is advertised as defined by the UPnP
     *               Device Architecture specification.
     * @param description The device description as defined by the 
     *                    UPnP Device Architecture specification.
     *                    The <code>InputStream</code> format is an
     *                    XML document representing the description.
     * @param icons The icons to be associated with this device.
     *              Each icon in the array is copied into the resulting
     *              {@code UPnPManagedDevice}; subsequent calls to
     *              {@link UPnPManagedDevice#getIcons()} will return
     *              different instances than those specified by the
     *              {@code icons} parameter.
     *              May be a zero length array to create a device with
     *              no icons.
     *
     * @return The UPnPManagedDevice created from the description.
     *      Null is returned if the host platform does not support
     *      device advertisement.
     *
     * @throws IllegalArgumentException if the <code>description</code>
     *      parameter does not comply with a device description as defined by
     *      the UPnP Device Architecture specification, or if the
     *      <code>description</code> parameter includes service
     *      descriptions, or if one or more of the services
     *      specified is already associated with another
     *      UPnPManagedDevice.
     * @throws IOException if an I/O error occurs on the description.
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public UPnPManagedDevice createDevice(UPnPManagedDevice parent,
                                                 InputStream description,
                                                 UPnPManagedDeviceIcon[] icons)
                                                 throws IOException,
                                                 SecurityException
    {
        return null;
    }

    /**
     * Adds a listener for additions or removals of locally hosted 
     * server devices. Each <code>UPnPManagedDeviceListener</code> 
     * is notified when a UPnPManagedDevice on the local host is 
     * added to or removed from a home network through calling the 
     * UPnPManagedDevice sendAlive() or sendByeBye() methods. 
     *  
     * <p>Adding a listener which is the same instance as a 
     * previously added (and not removed) listener has no effect. 
     *
     * @param listener The listener to add.
     */
    public void addDeviceListener(UPnPManagedDeviceListener listener)
    {
    	
    }

    /**
     * Removes a previously registered 
     * UPnPManagedDeviceListener. 
     *
     * @param listener The listener to remove.
     */
    public void removeDeviceListener(UPnPManagedDeviceListener listener)
    {
    	
    }

    /**
     * Sets a message handler for incoming messages (advertisements, 
     * evented state variables, action responses, device and service
     * descriptions). Calls to set the message handler replace any 
     * prior incoming message handler. 
     *  
     * <p>A message handler may be removed by passing null as the 
     * inHandler.  In the absence of a registered message handler the
     * stack will parse the incoming messages. 
     *  
     * <p>If the application-provided handler throws any exceptions 
     * during execution, the stack will attempt to process the 
     * message with the default (stack-provided) handler. 
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
