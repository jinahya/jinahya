/*
 * Created on Mar 28, 2007
 */
package org.ocap.hardware.device;

import org.ocap.hardware.VideoOutputPort;

/**
 * The callback interface to be implemented by application classes that wish
 * to receive notification of changes to the status of a <code>VideoOutputPort</code>.
 * 
 * @author Aaron Kamienski
 */
public interface VideoOutputPortListener extends java.util.EventListener
{
    /**
     * Method to be invoked when a display device is connected or disconnected.
     * <p>
     * Note that this method will not be invoked where such information cannot
     * be known by the Host device.
     * 
     * @param source the <code>VideoOutputPort</code> whose status has changed
     * @param status <code>true</code> when a display device is connected;
     *               <code>false</code> when a display device is disconnected
     *               
     * @see VideoOutputSettings#isDisplayConnected
     */
    public void connectionStatusChanged(VideoOutputPort source, boolean status);

    /**
     * Method to be invoked when the <code>VideoOutputPort</code> is enabled or
     * disabled.
     * 
     * @param source the <code>VideoOutputPort</code> whose status has changed
     * @param status <code>true</code> when the video output port is enabled;
     *               <code>false</code> when the video output port is disabled
     *               
     * @see VideoOutputPort#enable()
     * @see VideoOutputPort#disable()
     * @see VideoOutputPort#status()
     */
    public void enabledStatusChanged(VideoOutputPort source, boolean status);

    /**
     * Method to be invoked when the configuration of a <code>VideoOutputPort</code> changes.
     * 
     * @param source the <code>VideoOutputPort</code> whose status has changed
     * @param oldConfig the previous configuration
     * @param newConfig the new configuration
     */
    public void configurationChanged(VideoOutputPort source, 
                                     VideoOutputConfiguration oldConfig, VideoOutputConfiguration newConfig);
}
