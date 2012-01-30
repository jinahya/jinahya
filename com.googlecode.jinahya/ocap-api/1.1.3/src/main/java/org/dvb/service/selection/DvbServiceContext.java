package org.dvb.service.selection;

/**
 * An extension to ServiceContext permitting the discovery of the
 * network interface (if any) being used by a service context.







 */

public interface DvbServiceContext extends 
	javax.tv.service.selection.ServiceContext {

	/**
	 * Return the NetworkInterface reserved by this ServiceContext.
	 * The NetworkInterface instance returned shall be .equals() to one of those 
	 * returned by NetworkInterfaceManager.getNetworkInterfaces().
	 * @return a NetworkInterface object or null if this ServiceContext has no NetworkInterface reserved
	 */
	public org.davic.net.tuning.NetworkInterface getNetworkInterface();
/**
 * Sets the default video transformation to be applied to the new service following
 * a service selection operation. The identity of the last application to call this method 
 * for each service context instance shall be remembered by the MHP terminal. 
 * If this is set then the presentation of the video of the new service will use the
 * video transformation of the existing service until it is known whether that application
 * survives. If that application survives then this transformation 
 * shall be applied to the video of the new service.
 * If that application does not survive then the video transformation of the new service will be reset 
 * to the default for the platform and the value set by the call to this method discarded.
 * This method shall have no effect if the ServiceMediaHandler for the service context is 
 * a component based player when service selection happens.
 * @since MHP 1.1.2
 * @param vt the video default transformation or null to reset to the default for the platform
 */
public void setDefaultVideoTransformation(org.dvb.media.VideoTransformation vt);


















}


