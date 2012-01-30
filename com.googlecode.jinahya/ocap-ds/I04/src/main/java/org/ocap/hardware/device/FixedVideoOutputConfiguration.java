/*
 * Created on Mar 20, 2007
 */
package org.ocap.hardware.device;

import org.havi.ui.event.HScreenConfigurationEvent;

/**
 * An instance of this class represents a video output configuration defined by
 * a fixed set of attributes.
 * <p>
 * When such a configuration is successfully applied to a <code>VideoOutputPort</code>,
 * the output resolution SHALL be suitably adjusted.  
 * This MAY in turn adjust the aspect ratio and resolution of the associated 
 * <code>HScreen</code> and its component <code>HScreenDevice</code>s.  
 * Any such changes will be announced via the dispatch of 
 * {@link HScreenConfigurationEvent}s.
 * 
 * @see VideoOutputSettings#getSupportedConfigurations()
 * @see VideoOutputSettings#setOutputConfiguration
 */
public interface FixedVideoOutputConfiguration 
    extends VideoOutputConfiguration
{
    /**
     * Get the fixed video resolution represented by this <code>VideoOuputConfiguration</code>.
     * 
     * @return the fixed video resolution represent by this configuration
     */
    public VideoResolution getVideoResolution();
}
