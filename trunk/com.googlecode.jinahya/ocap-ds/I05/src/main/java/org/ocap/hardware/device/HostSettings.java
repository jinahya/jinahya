package org.ocap.hardware.device;

import org.havi.ui.HScreen;
import org.ocap.hardware.Host;
import org.ocap.hardware.PowerModeChangeListener;
import org.ocap.hardware.VideoOutputPort;

/**
 * System-level extensions to OCAP <code>Host</code>.
 * On Host devices implementing this specification, the instance of <code>Host</code>
 * returned by {@link Host#getInstance} SHALL also implement this interface. 
 */
public interface HostSettings 
{
    /**
     * Constant representing a <i>narrow</i> range of volume control. 
     */
    public static final int RANGE_NARROW = 1;
    
    /**
     * Constant representing a <i>normal</i> range of volume control.
     */
    public static final int RANGE_NORMAL = 2;
    
    /**
     * Constant representing a <i>wide</i> range of volume control.
     */
    public static final int RANGE_WIDE = 3;

   /**
    * Transition the power mode of the system to the given mode.
    * <p>
    * 
    * If the power mode is already in the target mode, this method
    * SHALL do nothing.
    * 
    * Setting host power mode to low-power SHALL not disrupt 
    * any ongoing recording.
    *  
    * In devices where a separate power mode is maintained for standby 
    * recordings, setting the power mode to low-power SHALL transition 
    * to standby-recording power mode when a recording is in progress.
    * <p>
    * 
    * A change of power mode SHALL be communicated to installed 
    * {@link PowerModeChangeListener}s.
    * 
    * The power mode set via this method SHALL NOT be persisted and 
    * restored at boot time (see {@link #resetAllDefaults}).  The default
    * power mode at boot time is specified by the OCAP specification.  
    * 
    * @param mode The new power mode for the system. 
    *
    * @throws IllegalArgumentException if <i>mode</i> is not one of
    *         {@link Host#FULL_POWER} or {@link Host#LOW_POWER}
    * @throws SecurityException if the caller does not have 
    *         <code>MonitorAppPermission("powerMode” or "deviceController")</code> 
    */
   public void setPowerMode( int mode );


   /**
    * Get the set of individually controllable audio outputs for this host. 
    *
	* @return The set of <code>AudioOutputPort</code>s as an <code>Enumeration</code>.
	*
    * @throws SecurityException if the caller does not have 
	* <code>MonitorAppPermission("deviceController")</code> 
    */
   public java.util.Enumeration getAudioOutputs();

   /**
    * Enable or disable system handling of volume keys.
    * 
    * This method may be used by applications to disable the OCAP default
    * behavior of system volume level being handled by the OCAP device
    * based upon volume keys (i.e., <code>VK_VOLUME_UP</code> and <code>VK_VOLUME_DOWN</code>).
    * OCAP-defined behavior SHALL be the default if this method is never called.
    * <p>
    * If the system volume control is disabled, the device SHALL not process
    * volume key events internally.  In other words, the volume keys SHALL
    * no longer be considered <i>system</i> keys when system volume control
    * is disabled.  While disabled it is the responsibility of applications
    * to manage the master volume of the device via the {@link AudioOutputPort}
    * API.
    *
    * @param enable Enable or disable system handling of volume keys.
    *               If <code>true</code> is specified, then system volume SHALL
    *               be handled by the OCAP device (as is the default).
    *               If <code>false</code> is specified, then the system volume
    *               SHALL NOT be managed directly by the OCAP device based 
    *               upon volume keys.
	*
    * @throws SecurityException  If the caller does not have 
	* <code>MonitorAppPermission("deviceController")</code> 
    */
   public void setSystemVolumeKeyControl(boolean enable);
   
   /**
    * Enable or disable system handling of the mute key.
    * 
    * This method may be used by applications to disable the OCAP default
    * behavior of system volume muting being handled by the OCAP device
    * based upon the mute key (i.e., <code>VK_MUTE</code>).
    * OCAP-defined behavior SHALL be the default if this method is never called.
    * <p>
    * If the system volume mute control is disabled, the device SHALL not process
    * the volume mute events internally.  In other words, the mute key SHALL
    * no longer be considered a <i>system</i> key when system volume mute control
    * is disabled.  While disabled it is the responsibility of applications
    * to manage the master volume mute state of the device via the {@link AudioOutputPort}
    * API.
    *
    * @param enable Enable or disable system handling of the volume mute key.
    *               If <code>true</code> is specified, then system volume mute SHALL
    *               be handled by the OCAP device (as is the default).
    *               If <code>false</code> is specified, then the system volume mute
    *               SHALL NOT be managed directly by the OCAP device based 
    *               upon the mute key.
    *
    * @throws SecurityException  If the caller does not have 
    * <code>MonitorAppPermission("deviceController")</code> 
    */
   public void setSystemMuteKeyControl(boolean enable);
   
   /**
    * Set the range of volume level controlled by the system volume keys.
    * <p>
    * This method may be used by applications to control the range in dB levels
    * controlled by the system volume keys.
    * If system volume control is disabled (via {@link #setSystemVolumeKeyControl})
    * then this setting SHALL have no effect.
    * 
    * The following table describes the range values. 
    * <table border>
    * <tr><th>Range</th> <th>Description</th></tr>
    * <tr><td><code>RANGE_NARROW</code></td>
    *     <td>A very limited range of volume level is controllable via system volume keys.</td>
    * </tr>
    * <tr><td><code>RANGE_NORMAL</code></td>
    *     <td>A limited range of volume level is controllable via system volume keys.</td>
    * </tr>
    * <tr><td><code>RANGE_WIDE</code></td>
    *     <td>The full volume level is controllable via system volume keys.</td>
    * </tr>
    * </table>
    * 
    * @param range The desired control range.
    *              One of {@link #RANGE_NARROW}, {@link #RANGE_NORMAL}, or {@link #RANGE_WIDE}
    *
    * @throws IllegalArgumentException if an invalid range value is specified 
    * @throws SecurityException if the caller does not have
    * <code>MonitorAppPermission("deviceController")</code>
    */
   public void setSystemVolumeRange(int range);

   /**
    * Get the <i>main</i> video output port for the given <code>HScreen</code>.
    *
    * @return The instance of <code>VideoOutputPort</code> that represents the
    * <i>main</i> video output port for the given screen 
    */
   public VideoOutputPort getMainVideoOutputPort(HScreen screen);

   /**
    * Set the <i>main</i> video output port for the given <code>HScreen</code>.
    * <p>
    * Changing this setting MAY affect the configuration of <code>HScreenDevice</code>s
    * of the given <code>HScreen</code> to maintain consistent display
    * aspect ratios as described in the body of this specification.
    *
    * @param screen The specified <code>HScreen</code> for which to set the <i>main</i>
    * video output port. 
    * @param port The desired main <code>VideoOutputPort</code>.
	*
    * @throws SecurityException if the caller does not have 
	* <code>MonitorAppPermission("deviceController")</code> 
	* @throws FeatureNotSupportedException if the given setting is not supported
    */
   public void setMainVideoOutputPort(HScreen screen, VideoOutputPort port) throws FeatureNotSupportedException;

   /**
    * Reset all Host device settings to their factory default values.
    * <p>
    * Calling this method SHALL result in the Host restoring all configurable
    * scalar settings to their default values, regardless of storage location.
    * This includes both settings that persist and do no persist across Host
    * device reboots.  
    * <p>
    * This method SHALL affect the following:
    * <ul>
    * <li> All settings defined by this specification.  
    *      Including {@link AudioOutputPort audio} and {@link VideoOutputSettings video}
    *      as well as those defined by this class.
    * <li> All {@link Host} settings. 
    *      Including {@link Host#setRFBypass RF bypass} and {@link Host#setACOutlet AC outlet} settings.
    * <li> Closed-captioning settings controllable via 
    *      the {@link org.ocap.media.ClosedCaptioningAttribute ClosedCaptioningAttribute}.
    * <li> User preferences controllable via the 
    *      {@link org.dvb.user.UserPreferenceManager UserPreferenceManager}.
    * </ul>
    * Further, any subsequent operations that would be affected by this change
    * in settings SHALL be affected as if the corresponding API were invoked
    * directly.  
    *
    * @throws SecurityException if the caller does not have
    * <code>MonitorAppPermission("deviceController")</code>
    */
   public void resetAllDefaults();
}
