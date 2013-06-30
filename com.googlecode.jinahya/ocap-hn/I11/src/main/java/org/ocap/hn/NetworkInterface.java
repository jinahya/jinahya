package org.ocap.hn;

import java.net.InetAddress;

/**
 * This class represents a home network interface including MoCA, wired
 * ethernet, and wireless ethernet.  Reverse channel interfaces are not
 * represented by objects of this class. For each wired ethernet, wireless
 * ethernet, MoCA interface, or interface that is not a reverse channel
 * interface the HNIMP SHALL create an instance of this class.
 *
 */
public class NetworkInterface
{
    /**
     * Unknown network type.
     */
    public final static int UNKNOWN = 0;

    /**
     * Network interface type for hard-wired and MoCA based.
     */
    public final static int MOCA = 1;

    /**
     * Network interface type for hard-wired and ethernet based.
     */
    public final static int WIRED_ETHERNET = 2;

    /**
     * Network interface type for wireless and ethernet based.
     */
    public final static int WIRELESS_ETHERNET = 3;
    
    /**
     * Protected constructor.
     */
    protected NetworkInterface() {}

    /**
     * Gets an array of <code>NetworkInterface</code> instances that represent
     * all of the network interfaces supported by the device.
     *
     * @return An array of NetworkInterface instances.
     */
    public static NetworkInterface [] getNetworkInterfaces()
    {
        return null;
    }

    /**
     * Gets the type of this network interface.  Possibilities include
     * UNKNOWN, MOCA, WIRED_ETHERNET, WIRELESS_ETHERNET.
     *
     * @return The type of this interface.
     */
    public int getType()
    {
        return -1;
    }

    /**
     * Gets a humanly readable name for this interface, e.g. "ie0".
     *
     * @return The display name of this interface.
     */
    public String getDisplayName()
    {
        return null;
    }

    /**
     * Gets the <code>InetAddress</code> of this interface.  Returns one of the
     * <code>InetAddress</code> instances in the array returned by the 
     * <code>getInetAddresses</code> method.  If the array contains multiple
     * <code>InetAddress</code> instances, unless specified elsewhere, the
     * determination of which <code>InetAddress</code> to return is
     * implementation specific.
     *
     * @return The <code>InetAddress</code> of this interface.
     * @see  NetAuthorizationHandler2#notifyActivityStart
     * @see  NetAuthorizationHandler2#notifyAction
     * @see  HttpRequestResolutionHandler#resolveHttpRequest
     */
    public InetAddress getInetAddress()
    {
        return null;
    }

    /**
     * Gets an array of <code>InetAddress</code> containing all of the IP addresses
     * configured for this <code>NetworkInterface</code>.
     *
     * @return The array of <code>InetAddress</code> for this interface.
     */
    public InetAddress [] getInetAddresses()
    {
        return null;
    }
    
    /**
     * Gets the MAC address of this interface.
     *
     * @return The MAC address of this interface.
     */
    public String getMacAddress()
    {
        return null;
    }
}
