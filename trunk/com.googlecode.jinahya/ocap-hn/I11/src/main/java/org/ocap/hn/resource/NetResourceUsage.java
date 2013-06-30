package org.ocap.hn.resource;

import org.dvb.application.AppID;
import org.ocap.resource.ResourceUsage;
import org.ocap.net.OcapLocator;
import java.net.InetAddress;
import java.util.Date;

/**
 * This interface represents a usage of resources on a specific home network
 * activity.
 */
public interface NetResourceUsage extends ResourceUsage
{
    /**
     * Usage type is presentation.
     */
    public final static String USAGE_TYPE_PRESENTATION = "Presentation";

    /**
     * Returns the network address of the device from which the request was
     * originated for this usage.
     * 
     * @return The network address of the device requesting resources.
     */
    public InetAddress getInetAddress();

    /**
     * Gets the usage type associated with this usage.
     * <p>
     * This method is intended to eventually report one of multiple
     * usage types; at present, it returns only
     * <code>USAGE_TYPE_PRESENTATION</code>.
     *
     * @return Returns <code>USAGE_TYPE_PRESENTATION</code>.
     */
    public String getUsageType();

    /**
     * Gets the <code>OcapLocator</code> of the service associated with this
     * usage.
     *
     * @return The <code>OcapLocator</code>.
     */
    public OcapLocator getOcapLocator();
    
    
    
    ///////// Methods inherited from ResourceUsage //////////
    
    /**
     * Returns <code>null</code>.
     *
     * @return <code>null</code>
     */
    public AppID getAppID();
}
