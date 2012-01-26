package org.ocap.hn;

import java.net.InetAddress;

/**
 * The <code>Device</code> interface represents a Homenetwork device that
 * supports homenetwork NetModules. A Device is a hierarchical structure with 
 * root device being the physical appliance, such as an OCAP_Terminal or an 
 * OCAP_HOST. The valid device types for an OCAP root device are OCAP_HOST 
 * and OCAP_Terminal. 
 * 
 * A root device may contain a number of sub-devices, such as a MediaServer 
 * or a MediaRenderer. Each sub-device may support one or more NetModule(s)
 * whereas each NetModule only represents one sub-device. A NetModule is some 
 * functional unit in the device and examples of NetModules are ContentList, 
 * ContentManager, etc. 
 * 
 * A device may also have certain capabilities and properties associated with it. 
 * An application can retrieve these capabilities and properties by using 
 * property filters
 * <p>
 * 
 *  
 */
public interface Device {
    /*
     * Define Device Capabilities
     */
    
    /**
     * A constant indicating streaming capability of the device.
     */
    public final static String CAP_STREAMING_SUPPORTED = "StreamingSupported";

    /**
     * A constant indicating if the device has a tuner.
     */
    public final static String CAP_TUNER_SUPPORTED = "TunerSupported";

    /**
     * A constant indicating remote storage capability.
     */
    public final static String CAP_REMOTE_STORAGE_SUPPORTED = "RemoteStorageSupported";

    /**
     * A constant indicating MSO content recording capability.
     */
    public final static String CAP_RECORDING_SUPPORTED = "RecordingSupported";

    /*
     * Define Device Properties
     */
    
    /**
     * A constant for a friendly name of the device.
     */
    public final static String PROP_FRIENDLY_NAME = "friendlyName";

    /**
     * A constant indicating the manufacturer of this device.
     */
    public final static String PROP_MANUFACTURER = "manufacturer";

    /**
     * A constant providing URL to the manufacturer's web site.
     */
    public final static String PROP_MANUFACTURER_URL = "manufacturerURL";

    /**
     * A constant providing description of the device.
     */
    public final static String PROP_MODEL_DESCRIPTION = "modelDescription";

    /**
     * A constant indicates device property: model name.
     */
    public final static String PROP_MODEL_NAME = "modelName";

    /**
     * A constant indicates device property: model number.
     */
    public final static String PROP_MODEL_NUMBER = "modelNumber";

    /**
     * A constant indicates device property: model URL.
     */
    public final static String PROP_MODEL_URL = "modelURL";
    
    /**
     * A constant indicates device property: serial number.
     */
    public final static String PROP_SERIAL_NUMBER = "serialNumber";

    /**
     * A constant indicates device property: unique device name.
     */
    public final static String PROP_UDN = "UDN";

    /**
     * A constant indicates device property: universal product code.
     */
    public final static String PROP_UPC = "UPC";

    /**
     * A constant indicates device property: presentation URL.
     */
    public final static String PROP_PRESENTATION_URL = "presentationURL";

    /**
     * A constant indicates device property: location of the device.
     */
    public final static String PROP_LOCATION = "location";

    /**
     * A constant indicates device property: middleware profile.
     */
    public final static String PROP_MIDDLEWARE_PROFILE = "middlewareProfile";

    /**
     * A constant indicates device property: middleware version.
     */
    public final static String PROP_MIDDLEWARE_VERSION = "middlewareVersion";
    
    /**
     * A constant indicates device property: device type
     */
    public final static String PROP_DEVICE_TYPE = "deviceType";

    /**
     * A constant representing a device version number 
     */
    public final static String PROP_DEVICE_VERSION = "deviceVersion";
    
    /*
     * Define device types
     */
    
    /**
     * A constant indicates device type: Heater-Vent-Air Conditioning System.
     */
    public final static String TYPE_HVAC_SYSTEM = "HVAC_System";

    /**
     * A constant indicates device type: Heater-Vent-Air Conditioning Thermostat.
     */
    public final static String TYPE_HVAC_ZONE_THERMOSTAT = "HVAC_ZoneThermostat";

    /**
     * A constant indicates device type: Internet gateway device.
     */
    public final static String TYPE_INTERNET_GATEWAY_DEVICE = "InternetGatewayDevice";

    /**
     * A constant indicates device type: LAN device.
     */
    public final static String TYPE_LAN_DEVICE = "LANDevice";

    /**
     * A constant indicates device type: WAN connection device.
     */
    public final static String TYPE_WAN_CONNECTION_DEVICE = "WANConnectionDevice";

    /**
     * A constant indicates device type: WAN device.
     */
    public final static String TYPE_WAN_DEVICE = "WANDevice";

    /**
     * A constant indicates device type: Binary Light (on/off).
     */
    public final static String TYPE_BINARY_LIGHT = "BinaryLight";

    /**
     * A constant indicates device type: Dimmable Light (light intensity control).
     */
    public final static String TYPE_DIMMABLE_LIGHT = "DimmableLight";

    /**
     * A constant indicates device type: Media Server.
     */
    public final static String TYPE_MEDIA_SERVER = "MediaServer";

    /**
     * A constant indicates device type: Media Renderer.
     */
    public final static String TYPE_MEDIA_RENDERER = "MediaRenderer";

    /**
     * A constant indicates device type: Printer.
     */
    public final static String TYPE_PRINTER = "printer";

    /**
     * A constant indicates device type: Remote UI Client Device, 
     * Allows for basic operations on a Remote UI client including: 
     * user interface connection management, optionally user interface 
     * availability management and optionally basic user interaction.
     */
    public final static String TYPE_REMOTE_UI_CLIENT_DEVICE = "RemoteUIClientDevice";

    /**
     * A constant indicates device type: Remote UI Server Device.
     * @see #TYPE_REMOTE_UI_CLIENT_DEVICE
     */
    public final static String TYPE_REMOTE_UI_SERVER_DEVICE = "RemoteUIServerDevice";

    /**
     * A constant indicates device type: Scanner.
     */
    public final static String TYPE_SCANNER = "Scanner";

    /**
     * A constant indicates device type: WAN access point device.
     */
    public final static String TYPE_WLAN_ACCESS_POINT_DEVICE = "WLANAccessPointDevice";

    /**
     * A constant indicates device type: OCAP Host.
     */
    public final static String TYPE_OCAP_HOST = "OCAP_Host";

    /**
     * A constant indicates device type: OCAP terminal.
     */
    public final static String TYPE_OCAP_TERMINAL = "OCAP_Terminal";

    /**
     * Returns capabilities of this device in <code>Enumeration</code>.
     * Capabilities are defined in <code>Device</code>.
     *  
     * @return An enumeration of String objects representing capabilities 
     * 			of this device.
     */
    public java.util.Enumeration getCapabilities();

    /**
     * Returns the name of this device. The naming rule is proprietary. 
     * For example,"LivingRoom:OCAP_HOST1".
     * 
     * @return name of this device
     */
    public String getName();

    /**
     * Returns property of this device specified by a key. Minimum supported keys
     * are defined in <code>Device</code>, like PROP_MANUFACTURER, 
     * PROP_MODEL_NUMBER, etc.
     * 
     * @param key
     *            key of the property
     * @return property value specified by the key
     */
    public String getProperty(String key);

    /**
     * Returns all property keys supported by this device in
     * <code>Enumeration</code>. Keys returned may include standardized
     * keys (as documented with constants in this interface), as well
     * as additional keys supported by this device.
     * 
     * @return An enumeration of String objects representing all
     * 			property keys supported by this device
     */
    public java.util.Enumeration getKeys();

    /**
     * Returns Locator for this device.
     * 
     * @return Locator for this device
     */
    //public javax.tv.locator.Locator getLocator();

    /**
     * Returns the list of NetModules supported by this device.
     * 
     * @return <code>NetList</code> supported by this device
     */
    public NetList getNetModuleList();

    /**
     * Returns the NetModule by module id. Module id is unique within a device.
     * 
     * @param moduleId
     * 			unique id of a NetModule
     * @return NetModule by id, if specified NetModule is not supported by
     * 			this device, then null is returned.
     */
    public NetModule getNetModule(String moduleId);

    /**
     * Returns a list of sub devices hosted by this device.
     * 
     * @return list of sub-devices.
     */
    public NetList getSubDevices();
    
    /**
     * Returns the parent of this device.
     * 
     * @return the parent device, or null if this device has no parent.
     */
    public Device getParentDevice();

    /**
     * Returns the type of this device, for example, MediaRenderer, MediaServer,
     * etc. All OCAP-HN device types are defined in <code>Device</code>.
     * 
     * @return type of this device
     */
    public String getType();

    /**
     * Returns the version number associated with this Device's device type.
     *  
     * @return a String representing the version of this Device's device type
     */
    public String getVersion();
    
    /**
     * Returns true when this is the local device.
     *  
     * @return true if this is the local device
     */
    public boolean isLocal();
    
    /**
     * Adds a DeviceEventListener instance to this Device. 
     * If the listener passed in is already registered with this Device,
     * this method does nothing.  
     *
     * @param listener  a DeviceEventListener instance to be notified 
     *             of DeviceEvents. 
     */
    public void addDeviceEventListener(DeviceEventListener listener);

    /**
     * Removes a DeviceEventListener instance from this Device. 
     * If the specified instance is not registered with this Device, 
     * this method does nothing.
     * 
     * @param listener  a DeviceEventListener instance to be removed 
     *             from this Device.
     */
    public void removeDeviceEventListener(DeviceEventListener listener);

    /**
     * Returns the IP address for this device. 
     * 
     * @return an InetAddress representing this device's IP address
     */
    public InetAddress getInetAddress();

    /**
     * Sets the value of the PROP_FRIENDLY_NAME property.
     * When network applications make use of the NetManager.getDevice method,
     * operators are advised to provide an application that uses this method
     * to set a device friendly name to a home network unique value.
     *
     * @param value The value to set the property to.
     *
     * @throws IllegalArgumentException if the parameter violates the format
     *      specified by protocol mapping.
     * @throws UnsupportedOperationException if the <code>Device</code> is not
     *      local; see the <code>isLocal</code> method.
     * @throws SecurityException if the calling application has not been
     *      granted HomeNetPermission("contentmanagement").
     */
    public void setFriendlyName(String value);
}