package org.ocap.hn.upnp.server;

import java.io.IOException;
import java.io.InputStream;
import org.ocap.hn.upnp.common.UPnPDevice;
import org.ocap.hn.upnp.common.UPnPAdvertisedDevice;
import java.net.InetAddress;


/**
 * This interface represents a locally hosted UPnP device 
 * created when a privileged application uses  
 * {@link UPnPDeviceManager#createDevice UPnPDeviceManager.createDevice}
 * to create a device.
 *  
 * <p>The UPnPManagedDevice corresponds to a single UPnP server 
 * device with a single set of handlers and a single state. This 
 * server may be visible on multiple interfaces and/or multiple 
 * IP addresses, depending upon the list of InetAddresses that 
 * are associated with this server device. 
 * The network representations of this device
 * ({@link org.ocap.hn.upnp.common.UPnPAdvertisedDevice UPnPAdvertisedDevice}),
 * one per {@code InetAddress}, are
 * available for query via the {@link #getAdvertisedDevices} method.
 *  
 * <p>Updating a device causes corresponding UPnP device 
 * byebye/alive pairs to be sent. To institute multiple changes 
 * without multiple removals/advertisements, the application 
 * should call the {@link #sendByeBye}
 * method prior to the first change, calling {@link #sendAlive}
 * following the last change.
 */
public interface UPnPManagedDevice extends UPnPDevice
{

    /**
     * Sends UPnP ssdp:alive messages from the physical device.  Sends a 3+2D+k
     * series of alive messages as defined by the UPnP Device Architecture
     * specification on each IP address and network interface 
     * associated with the <code>UPnPManagedDevice</code>. Enters 
     * the "alive" state once the first advertisements are initiated 
     * on the first InetAddress/interface. 
     *  
     * <p>If the <code>UPnPManagedDevice</code> has no associated
     * <code>InetAddress</code>, no advertisements are sent and the 
     * device does not enter the "alive" state.
     *  
     * <p>If the device is already in the "alive" state, the device still sends
     * advertisements and remains in the "alive" state.
     *  
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     *
     * @see #isAlive()
     */
    public void sendAlive() throws SecurityException;

    /**
     * Sends UPnP ssdp:byebye messages from this device.  Sends a byebye
     * message for each device and service corresponding to alive messages
     * that would be sent by the <code>sendAlive</code> method.
     *  
     * <p>Once the last byebye message has been sent on the last 
     * <code>InetAddress</code>, the device enters the "not alive" 
     * state and is no longer discoverable via
     * {@link UPnPDeviceManager#getDevices()}.
     * At this point, the UPnP implementation holds no internal
     * references to this {@code UPnPManagedDevice} object.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     *
     * @see #isAlive()
     */
    public void sendByeBye() throws SecurityException;

    /**
     * Sets the InetAddresses that this {@code UPnPManagedDevice}
     * is associated with.
     * The device will send advertisements (and de-advertise) on the most
     * appropriate interface for each of the addresses specified, and will
     * respond to actions on each of those interfaces. 
     *  
     * <p>The passed array replaces any prior addresses that the 
     * device was associated with, including any stack-provided defaults.
     * If the device is already active
     * on one or more addresses, it must de-advertise itself at any
     * addresses that are being removed, and advertise itself at 
     * any addresses that are being added. 
     *  
     * <p>The device description advertised on each interface will 
     * be identical with the exception of the URLs related to the 
     * device and its components, which will reflect the IP address
     * on which the device is advertised. 
     *  
     * <p>Note that it is the responsibility of the application to 
     * monitor for changes in IP addresses that the host responds 
     * to, and to update the {@code UPnPManagedDevice} with any
     * modifications using this method. 
     *  
     * @param addresses Array of <code>InetAddress</code> 
     *                   objects representing the IP addresses
     *                   that this device is to advertise as. May be
     *                   zero length.
     *  
     * @return Array of prior addresses that were associated with 
     *         the <code>UPnPManagedDevice</code>. If there were no
     *         prior addresses, returns a zero-length array.
     *
     * @throws NullPointerException if {@code addresses} or any of its
     * elements are {@code null}.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     *
     * @see #getInetAddresses()
     */
    public InetAddress[] setInetAddresses(InetAddress[] addresses)
    						throws SecurityException;

    /**
     * Gets the <code>InetAddress</code>es that this 
     * <code>UPnPManagedDevice</code> is associated with.
     * If {@link #setInetAddresses(java.net.InetAddress[])}
     * has not yet been called on this {@code UPnPManagedDevice},
     * this method will report the default InetAddresses provided by the
     * stack.
     *  
     * @return Array of <code>InetAddress</code> objects 
     *                   representing the network interfaces that
     *                   this device is to advertise on. If the
     *                   device has no associated network
     *                   interfaces, returns a zero length array.
     *  
     */
    public InetAddress[] getInetAddresses();

    /**
     * Gets whether the <code>UPnPManagedDevice</code> is 
     * active. The device is active if it has valid advertisements 
     * on one or more network interfaces.
     * <p>
     * While the device is in the "alive" state, the device:
     * <ul>
     * <li> responds to SSDP discovery requests on the home network,
     * conforming to the UPnP Device Architecture
     * <li> continues to automatically send SSDP "alive" messages
     * conforming to the UPnP Device Architecture, prior to the
     * expiration of prior alive messages
     * <li> may be discovered through {@link UPnPDeviceManager#getDevices()}.
     * </ul>
     *  
     * @return True if the <code>UPnPManagedDevice</code> has valid 
     *         advertisements on one or more network interfaces,
     *         else false.
     */
    public boolean isAlive();

    /**
     * Gets the representations of this device on the network interfaces
     * on which is it advertised.
     * Since the UPnP device description contains network-dependent
     * information, there can be multiple {@code UPnPAdvertisedDevice}
     * objects associated with a single {@code UPnPManagedDevice}.
     * The returned array corresponds to the set of UPnP device descriptions
     * published for this UPnP device.
     *
     * @return The network representations of this {@code UPnPManagedDevice}.
     *         Returns a zero-length array if this device has not been
     *         advertised on a network interface.
     */
    public UPnPAdvertisedDevice[] getAdvertisedDevices();

    /**
     * Gets any <code>UPnPManagedDevice</code> embedded devices for 
     * this <code>UPnPManagedDevice</code>. Returns an array of 
     * {@code UPnPManagedDevices}, one per device appearing in this device's
     * {@code deviceList} element. Does not recurse through embedded devices
     * of this device. 
     *
     * @return The embedded devices for this device. If there are no
     *         embedded devices of this device then returns a zero
     *         length array.
     */
    public UPnPManagedDevice[] getEmbeddedDevices();

    /**
     * Returns the parent device of this {@code UPnPManagedDevice}.
     *
     * @return A UPnPManagedDevice representing this device's parent
     *         device. Returns null if this device has no parent.
     */
    public UPnPManagedDevice getParentDevice();

    /**
     * Sets the UPnP deviceType of this device. This value is placed
     * in the {@code deviceType} element within the device description. Adds
     * the element if it is not already present. If the device is 
     * currently published on one or more network addresses, this 
     * causes a byebye/alive cycle for this device on all published 
     * addresses. 
     *  
     * @param type The deviceType to be set for this device. 
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setDeviceType(String type) throws SecurityException;

    /**
     * Sets the UPnP friendlyName of this device. This value is 
     * placed in the {@code friendlyName} element within the device
     * description.  Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param friendlyName The friendlyName to set for this device 
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setFriendlyName(String friendlyName)
    					throws SecurityException;

    /**
     * Sets the UPnP manufacturer of this device. This value is 
     * placed in the {@code manufacturer} element within the device
     * description.  Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param manufacturer The manufacturer to set for this device 
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setManufacturer(String manufacturer)
    					throws SecurityException;

    /**
     * Sets the UPnP manufacturer URL of this device. This value is 
     * placed in the {@code manufacturerURL} element within the device
     * description.  Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param manufacturerURL The manufacturerURL to set for this 
     *                        device
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setManufacturerURL(String manufacturerURL)
    					throws SecurityException;

    /**
     * Sets the UPnP model description of this device. This value is
     * placed in the {@code modelDescription} element within the device
     * description.  Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param modelDescription The modelDescription to set for this 
     *                         device
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setModelDescription(String modelDescription)
    					throws SecurityException;

    /**
     * Sets the UPnP model name of this device. This value is
     * placed in the {@code modelName} element within the device
     * description.  Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param modelName The modelName to set for this device 
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setModelName(String modelName) throws SecurityException;

    /**
     * Sets the UPnP model number of this device. This value is
     * placed in the {@code modelNumber} element within the device
     * description.  Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param modelNumber The modelNumber to set for this device.
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setModelNumber(String modelNumber) throws SecurityException;
    
    /**
     * Sets the UPnP model URL of this device. This value is placed 
     * in the {@code modelURL} element within the device description. Adds
     * the element if it is not already present. If the device is 
     * currently published on one or more network addresses, this 
     * causes a byebye/alive cycle for this device on all published 
     * addresses. 
     *  
     * @param modelURL The modelURL to set for this device. 
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setModelURL(String modelURL) throws SecurityException;

    /**
     * Sets the UPnP serial number of this device. This value is 
     * placed in the {@code serialNumber} element within the device
     * description. Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param serialNumber The serialNumber to set for this device. 
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setSerialNumber(String serialNumber)
    					throws SecurityException;

    /**
     * Sets the UPnP Unique Device Number of this device. This value
     * is placed in the UDN element within the device 
     * description. Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param UDN The UDN to set for this device. 
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setUDN(String UDN) throws SecurityException;

    /**
     * Sets the UPnP Universal Product Code of this device. This 
     * value is placed in the UPC element within the device 
     * description. Adds the element if it is not already present. 
     * If the device is currently published on one or more network 
     * addresses, this causes a byebye/alive cycle for this device 
     * on all published addresses. 
     *  
     * @param UPC The UPC to set for this device. 
     *
     * @return True if the setting caused a byebye/alive cycle for 
     *         this device, false if not.
     *
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public boolean setUPC(String UPC) throws SecurityException;

    /**
     * Sets the Icons for this device. The passed array of 
     * UPnPManagedDeviceIcons is used to generate the {@code iconList}
     * sub-document of the device. Replaces any previous {@code iconList}
     * for this device. If the array is of zero length, removes any 
     * prior {@code iconList} from the device description. If the device is
     * currently published on one or more network addresses, this 
     * causes a byebye/alive cycle for this device on all published 
     * addresses. 
     *  
     * @param icons An array of UPnPManagedDeviceIcons to be used 
     *              for the IconList of this device.
     *              Each icon in the array is copied into the
     *              {@code UPnPManagedDevice}; subsequent calls to
     *              {@link #getIcons()} will return
     *              different instances than those specified by the
     *              {@code icons} parameter.
     *              May be a zero-length array to indicate no icons.
     *
     * @return An array of UPnPManagedDeviceIcons representing the 
     *         previous device icons. If there were no previous
     *         device icons, returns a zero-length array.
     *
     * @throws SecurityException if the calling application has not 
     *      been granted
     *      MonitorAppPermission("handler.homenetwork").
     */
    public UPnPManagedDeviceIcon[] setIcons(UPnPManagedDeviceIcon[] icons)
    						throws SecurityException;

    /**
     * Gets the icons for this device.
     *  
     * @return An array of UPnPManagedDeviceIcons representing the 
     *         current device icons. If no icons are present on the
     *         device, returns a zero-length array.
     */
    public UPnPManagedDeviceIcon[] getIcons();

    /**
     * Gets the services supported by this device.  Does not
     * return services contained in embedded devices of this device.
     *
     * @return An array of {@code UPnPManagedService} objects.  If
     *      there are no services associated with this
     *      {@code UPnPManagedDevice}, this method returns a zero length
     *      array.
     */
    public UPnPManagedService[] getServices();

    /**
     * Creates a UPnP service associated with this device.
     * If this device is currently advertised, causes a byebye/alive
     * process to be followed.
     * Note that the {@code USN} and {@code serviceId} elements are generated
     * by the stack and not provided as parameters.
     *
     * @param serviceType The UPnP service type of the service to be
     *                    populated in the serviceType element of
     *                    the device description.
     * @param description The service description as defined by the
     *                    UPnP Device Architecture specification.
     *                    The <code>InputStream</code> format is an
     *                    XML document representing the description.
     *
     * @param handler The action handler that will handle action
     *                requests for the service.
     *
     * @return The UPnP service created from the description.
     * Returns null if the host platform does not support service advertisement.
     *
     * @throws IllegalArgumentException if the <code>description</code>
     *      parameter does not comply with a service description as defined by
     *      the UPnP Device Architecture specification.
     * @throws IOException if an I/O error occurs on the description.
     * @throws SecurityException if the calling application has not been
     *      granted MonitorAppPermission("handler.homenetwork").
     */
    public UPnPManagedService createService(String serviceType,
                                            InputStream description,
                                            UPnPActionHandler handler)
                                            throws IOException,
                                            SecurityException;



    //////////  Methods inherited from UPnPDevice  ////////

    /**
     * {@inheritDoc}
     *
     * @return The type of this device.
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getDeviceType()}.
     */
    public abstract String getDeviceType();

    /**
     * {@inheritDoc}
     *
     * @return The {@code friendlyName} of this device.
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getFriendlyName()}.
     */
    String getFriendlyName();

    /**
     * {@inheritDoc}
     *
     * @return the manufacturer of this device.
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getManufacturer()}.
     */
    String getManufacturer();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getManufacturerURL()}.
     */
    String getManufacturerURL();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getModelDescription()}.
     */
    String getModelDescription();

    /**
     * {@inheritDoc}
     *
     * @return The {@code modelName} of this device.
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getModelName()}.
     */
    String getModelName();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getModelNumber()}.
     */
    String getModelNumber();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getModelURL()}.
     */
    String getModelURL();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getSerialNumber()}.
     */
    String getSerialNumber();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getSpecVersion()}.
     */
    String getSpecVersion();

    /**
     * {@inheritDoc}
     *
     * @return The UDN of this device.
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getUDN()}.
     */
    String getUDN();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].getUPC()}.
     */
    String getUPC();

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * If the device has been advertised, this method returns the same value
     * as {@code getAdvertisedDevices()[0].isRootDevice()}.
     */
    boolean isRootDevice();

}
