package org.ocap.hn;

/**
 * The NetManager is a singleton class that registers all the 
 * Devices and NetModules within a home network. It maintains an implementation
 * dependent database of devices and NetModules. <p>
 * The NetManager may be used to retrieve list of <code>NetModule</code> and <code>
 * Device</code> in the network. The application can filter the list by specifying
 * a name or by applying filtering rules. For example, 
 * "modelNumber = h6315, location = LivingRoom". Application can monitor 
 * availability of NetModules by registering as a listener to NetManager 
 * instance.  
 */
public abstract class NetManager {

	/**
	 *	Returns the singleton NetManager. This is the entry point
	 *	for home network. If the calling application is unsigned,
	 *  this method SHALL return null.
	 *  
	 *	@return		Singleton instance of NetManager or null 
	 *              if the calling application is unsigned.
	 */
	public synchronized static NetManager getInstance() {
		return null;
	}
	
	/**
	 *	Returns NetModules that match all properties set by a given filter.
	 *	Passing a null filter will return a NetList with all known NetModules.
	 *	@param filter	
	 *				Filter to select out NetModules from all 
	 *				available NetModules
	 *	@return		List of NetModules satisfying filter
	 */
	public abstract NetList getNetModuleList(PropertyFilter filter);
	
	/**
	 *	Returns all NetModules that match the specified device name and module identifier.
	 *
	 *	Passing a null or empty device name with a non-null module identifier will result in a <code>NetList</code> 
	 *	containing all <code>NetModule</code>s whose module ids match the non-null module identifier.
	 *
	 *	Passing a null or empty module identifier with a non-null device name will result in a <code>NetList</code>
	 *	containing all <code>NetModule</code>s whose devices match the non-null device name.
	 *
	 *  Passing a null or empty module identifer and null or empty device name will return a
	 *  <code>NetList</code> containing all known <code>NetModule</code>s. 
	 *
	 *  @param deviceName name of the device hosting the module to retrieve
	 *	@param moduleID module identifier
	 *
	 *	@return		List of NetModules satisfying device name and module identifier
	 */
	public abstract NetList getNetModuleList(String deviceName, String moduleID);

	/**
	 *	Returns NetModule by device and module ID. If multiple devices have the same device name
	 *  and share the same module identifier, then the value returned by this method is
	 *  implementation specific.
	 *
	 *  @param deviceName name of the device hosting the module to retrieve
	 *	@param moduleID module identifier 
	 *
	 *	@return 	NetModule with the specified identifier
	 */
	public abstract NetModule getNetModule(String deviceName, String moduleID);
	
	/**
	 *	Returns devices that match all properties set by a given filter.
	 *  All known devices and sub-devices are passed through the given
	 *  filter. Passing a null filter will return a <code>NetList</code> with all
	 *  known devices and sub-devices.
	 *	
	 *	@param filter
	 *				Filter to select out devices from all
	 *				connected devices
	 *	@return		List of devices satisfying filter
	 */
	public abstract NetList getDeviceList(PropertyFilter filter);

	/**
	 *	Returns all devices that match the specified device name.
	 *  Passing a null or empty device name will result in an
	 *  empty <code>NetList</code>.
	 *	
	 *	@param name
	 *				Device name.
	 *	@return		List of devices satisfying device name
	 */
	public abstract NetList getDeviceList(String name);

	/**
	 *	Returns device by name, for example, "BallRoom:DVD_PLAYER1".
	 *  If multiple devices have the same name, then the value returned
     *  by this method is implementation specific. 
	 *	
	 *	@param name
	 *				Device name
	 *	@return		Device matching the specified name
	 */
	public abstract Device getDevice(String name);
	
	/**
	 *	Adds a NetModule event listener to NetManager. Listener will receive a
	 *  NetModuleEvent when a new NetModule is registered or an old NetModule 
	 *  is removed from home network. If listener is already registered,
	 *  no action is performed.
	 *	
	 *	@param	listener
	 *			Listener which listens to NetModule change events on
	 *			home network
	 *	@see #removeNetModuleEventListener
	 */
	public abstract void addNetModuleEventListener(NetModuleEventListener 
	        listener);
	
	/**
	 *	Removes a NetModule event listener from NetManager. If the listener is 
	 *  not registered yet, no action is performed.
	 *	
	 *	@param	listener
	 *			Listener which listens to NetModule change events on 
	 *			home network
	 *	@see #addNetModuleEventListener
	 */
	public abstract void removeNetModuleEventListener(NetModuleEventListener 
	        listener);

	/**
	 *	Adds a Device event listener to NetManager. Listener will receive a
	 *  DeviceEvent when a new Device is registered,  an existing Device 
	 *  is removed from home network, or a Device's internal state has changed. 
	 *  If the listener passed in is already registered, no action is performed.
	 *  
	 *  When a device listener is registered, the implementation SHALL NOT
	 *  generate DEVICE_ADDED events for devices previously discovered by 
	 *  the implementation.
	 *	
	 *	@param	listener
	 *			Listener which listens to Device change events on the
	 *			home network
	 *	@see #removeDeviceEventListener
	 */
	public abstract void addDeviceEventListener(DeviceEventListener 
	        listener);
	
	/**
	 *	Removes a Device event listener from NetManager. If the listener is 
	 *  not registered yet, no action is performed.
	 *	
	 *	@param	listener
	 *			Listener which listens to Device change events on 
	 *			home network
	 *	@see #addDeviceEventListener
	 */
	public abstract void removeDeviceEventListener(DeviceEventListener 
	        listener);	
	
	/**
	 * Requests that the NetManager proactively refresh its local database of 
	 * connected devices. This operation will be performed asynchronously. 
	 * Any listeners registered with the NetManager changes to connected
	 * Devices or NetModules will be notified of any changes discovered during this process.
	 */
	public abstract void updateDeviceList();
}
