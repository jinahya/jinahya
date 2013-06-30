package org.ocap.hn;

/**
 * NetModule
 *
 * @author			Luyang Li (lly@research.panasonic.com)
 * @version			1.0
 *
 */

import java.util.Enumeration;

/**
 * NetModule is an abstraction of functionality that is provided by a 
 * <code>Device</code>. It is a group of related actions. A NetModule is 
 * always associated with a homenetwork <code>Device</code>. Application 
 * may monitor a NetModule's status by subscribing as a listener to this
 * NetModule. 
 */
public interface NetModule {
	
	/**
	 * 	A constant indicating content listing NetModule.
	 */
	public final static String CONTENT_LIST 	= "ContentList";
	
	/**
	 * 	A constant indicating content manager NetModule.
	 */
	public final static String CONTENT_MANAGER 	= "ContentManager";
	
	/**
	 * 	A constant indicating content renderer NetModule.
	 */
	public final static String CONTENT_RENDERER = "ContentRenderer";
	
	/**
	 * 	A constant indicating content server NetModule.
	 */
	public final static String CONTENT_SERVER = "ContentServer";
	
	/**
	 * 	A constant indicating content recording NetModule.
	 */
	public final static String CONTENT_RECORDER = "ContentRecorder";

	/**
	 * 	A constant indicating NetModuleID.
	 */
	public final static String PROP_NETMODULE_ID = "NetModuleId";
	
	/**
	 * 	A constant providing URL for NetModule description.
	 */
	public final static String PROP_DESCRIPTION_URL = "DescriptionURL";
	
	/**
	 * 	A constant providing URL for NetModule control.
	 */
	public final static String PROP_CONTROL_URL = "ControlURL";
	
	/**
	 *  A constant providing URL for NetModule eventing.
	 */
	public final static String PROP_EventSub_URL = "EventSubURL";
	
	/**
	 *  A constant providing this NetModule's type.
	 */
	public final static String PROP_NETMODULE_TYPE = "NetModuleType";

	/**
	 * Returns the device that provides this NetModule.
	 * @return	device that offers this NetModule 
	 */
	public Device getDevice();
	
	/**
	 * Returns the property keys supported by this NetModule.
	 * @return	An enumeration of String object representing property keys 
	 * 			for this NetModule
	 */
	public Enumeration getKeys();

	/**
	 * Returns the property value for specified key.
	 * @param key	specified property key
	 * @return property value for specified key
	 */
	public String getProperty(String key);
	
	/**
	 * Returns the type of this NetModule. The allowed types are defined  
	 * as constant field in <code>NetModule</code>, for example,
	 * CONTENT_MANAGER, CONTENT_LIST.
	 * @return	type of this NetModule
	 */
	public String getNetModuleType();
	
	/**
	 * Returns the id of this NetModule, which is unique within the device.
	 * An example could be, ContentListing1.
	 * @return	id of this NetModule
	 */
	public String getNetModuleId();
	
    /**
     * Adds a NetModuleEventListener instance to this NetModule. 
     * If the listener passed in is already registered with this NetModule,
     * this method does nothing.  
     *
     * @param listener  a NetModuleEventListener instance to be notified 
     *             of NetModuleEvents. 
     */
    void addNetModuleEventListener(NetModuleEventListener listener);


    /**
     * Removes a NetModuleEventListener instance from this NetModule. 
     * If the specified instance is not registered with this NetModule, 
     * this method does nothing.
     * 
     * @param listener  a NetModuleEventListener instance to be removed 
     *             from this NetModule.
     */
    void removeNetModuleEventListener(NetModuleEventListener listener);
    
    /**
     * Returns true if this NetModule is hosted on the local device.
     *  
     * @return true if this NetModule is hosted on the local device, false otherwise.
     */
    public boolean isLocal();    
}