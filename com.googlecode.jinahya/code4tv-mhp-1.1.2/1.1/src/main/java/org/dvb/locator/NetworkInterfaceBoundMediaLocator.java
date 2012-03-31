package org.dvb.locator;

import org.davic.net.tuning.NetworkInterface;
import javax.media.MediaLocator;

/**
 * Class representing a MediaLocator combined with a network interface.
 * Used by applications that want to control which network interface is
 * used by JMF when multiple network interfaces exist which are connected
 * to the same actual network, for example a multi-tuner PVR.<p>
 * JMF players constructed using instances of this class shall only
 * use the specified network interface. If the media referenced by that
 * media locator is not available via that network interface, the JMF
 * player shall behave as if the media is not available at all, even if
 * it is in fact available by another network interface.
 * 
 * @since MHP 1.1.2
 */

public class NetworkInterfaceBoundMediaLocator extends javax.media.MediaLocator
{
	/**
	 * Construct an instance of this class
	 * @param locator the MediaLocator to be combined with a NetworkInterface
	 * @param ni a NetworkInterface
	 */
	public NetworkInterfaceBoundMediaLocator(MediaLocator locator, NetworkInterface ni)
	{super("");}

	/**	
	 * Return the network interface aspect of this MediaLocator
	 * @return the network interface passed into the constructor
	 */
	public NetworkInterface getNetworkInterface(){ return null;}
}

