package org.ocap.hardware;

import org.ocap.hardware.pod.POD;
import org.ocap.system.event.*;

/**
 * This class represents the host terminal device and provides access to the
 * Host ID, raw image data, the power state of the host and a 
 * java.util.Enumeration of references to VideoOutputPort instances.
 * See also org.ocap.OcapSystem to get the singleton instance.
 */
public class Host {
    
    /**
     * A constructor of this class.  An application must use the {@link 
     * Host#getInstance} method to create an instance.
     */
    protected Host () {
    }
    
    /**
     * This method returns a singleton system-wide instance of the Host class.
     *
     *@return   a singleton Host instance.
     */
    public static Host getInstance() {
        return null;
    }
    
    /**
     * Get a human-readable string representing the ID of this Host.  This
     * should be a string that could be read over the phone to an MSO that
     * uniquely identifies the Host.
     *
     * @return id String host id
     */
    public String getID (){
        return null;    
    }

    /**
     * @return the current power mode of the box (for example LOW_POWER).
     *
     * @see #FULL_POWER
     * @see #LOW_POWER
     */
    public int getPowerMode (){
        return 0;
    }

    /**
     * Gets the MAC address used by the Host for reverse channel unicast
     * communications.  This value SHALL match the value the Host would
     * use in DSG mode for a DHCP request. The format of the String returned
     * SHALL be six pairs of characters where each pair represents a
     * hexadecimal byte value of the address and where each pair is separated
     * by a colon.  For example "0D:0E:0F:10:11:12".  The first byte 
     * representation in the String starting at location 0 SHALL be the most
     * significant byte in the address.
     *
     * @return MAC address of the Host.
     */
    public String getReverseChannelMAC()
    {
        return null;
    }

    /**
     * Power mode constant for normal "on" mode.
     */
    public static final int FULL_POWER = 1;
    
    /**
     * Power mode constant for "standby" mode.
     */
    public static final int LOW_POWER  = 2;

    /**
     * Adds the PowerModeChangeListener to be called ({@link PowerModeChangeListener#powerModeChanged}
     * when the power mode of the box changes (for example when the user presses the Power button).
     *
     * @param l is an instance implementing PowerModeChangeListener whose
     *          powerModeChanged method will be called when the power mode of the Host Device changes.
     */
    public void addPowerModeChangeListener (PowerModeChangeListener l){}

    /**
     * Removes the previously-added PowerModeChangeListener.
     *
     * @param l is the PowerModeChangeListener to disable. Does nothing if
     *          l was never added, has been removed, or is null.
     */
    public void removePowerModeChangeListener (PowerModeChangeListener l){}

    
    /**
     * This method returns a java.util.Enumeration of 
     * VideoOutputPort instances representing all video output ports
     * physically present on the device.  The returned Enumeration 
     * SHALL reflect a 1 to 1 mapping between VideoOutputPort
     * instances and physical video output ports.  For example, 2 HDMI 
     * output ports driven by the same controller would report two distinct 
     * VideoOutputPort instances of type AV_OUTPUT_PORT_TYPE_HDMI.  
     * This method SHALL report all VideoOutputPort instances regardless of the 
     * enabled or disabled status of the port.
     *
     * @return the java.util.Enumeration of VideoOutputPort instances.
     */
    public java.util.Enumeration getVideoOutputPorts() {
        return null;
    }


        
    /**
     * <p>
     * This method initiates a reboot of the Host device. The method caller 
     * shall have the MonitorAppPermission("reboot"). 
     * </p><p>
     * Note that the {@link org.ocap.system.event.SystemEventListener#notifyEvent} 
     * method SHALL be called before the initiated reboot is performed by 
     * the Host device. The monitor application MAY clean up resources 
     * in the SystemEventListener.notifyEvent method call. 
     * After the SystemEventListener.notifyEvent method call returns, the 
     * Host device SHALL continue the reboot following the boot process described in 
     * the <i>Boot Process</i> Section of this specification. 
     * </p>
     *
     * @throws SecurityException if the caller does not have the 
     *         MonitorAppPermission("reboot").
     */
    public void reboot() {
    }
    
    
    /**
     * <p>
     * This method initiates a download of the operating software in the Host
     * as specified by [CCIF2.0].
     * 
     * @throws SecurityException if the caller does not have 
     * MonitorAppPermission("codeDownload").
     *
     */
    public void codeDownload() {
    }
    
    /**
     * Query whether there is an AC Outlet on the STB.
     *
     * NOTE: AC Outlet refers to an external power plug on the STB. That is, a device such as a VCR can plug
     * into the STB for power.
     *
     * @return true if there is an AC Outlet, else false.
     *
    **/
    public boolean isACOutletPresent() {
        return false ;
    }
    
    /**
     * Query whether power to the AC Outlet, if present, is currently On (true) or Off (false)
     *
     * NOTE: AC Outlet refers to an external power plug on the STB. That is, a device such as a VCR can plug
     * into the STB for power.
     *
     * @return The current AC Outlet status (false = Off, true = On).
     *
     * @throws java.lang.IllegalStateException if this method is called when there is no AC Outlet.
     *
    **/
    public boolean getACOutlet()
        {
        return false ;
    }
        
    /**
    * Switch power to AC Outlet, if present, On (true) or Off (false)
    *
    * NOTE: AC Outlet refers to an external power plug on the STB. That is, a device such as a VCR can plug
    * into the STB for power.
    *
    * @param enable The power setting for the AC Outlet.
    *
    * @throws java.lang.IllegalStateException if this method is called when there is no AC Outlet.
    *
    **/
    public void setACOutlet (boolean enable)
        {
    }
    /**
    * Returns capability of RF bypass control on the host.
    *
    * @return true if the host can control RF bypass on/off, else false. 
    *
    **/
    public boolean getRFBypassCapability() {
        return false;
    }

    /**
     * Queries whether RF Bypass is currently enabled. If RF Bypass is enabled,  
     * the incoming RF signal is directly routed to the RF output port when the 
     * host is in a stand by mode, thereby totally bypassing the host. 
     *
     * @return true if RF Bypass is currently enabled, else false. If the host 
     *       doesn’t support RF bypass, false returns. 
     *
     **/
    public boolean getRFBypass() {
        return false;
    }

    /**
     * Enables or disables RF Bypass. If RF Bypass is enabled, the incoming RF 
     * signal is directly routed to the RF output port when the host is in a 
     * stand by mode, thereby totally bypassing the host. 
     *
     * @param enable If true, RF Bypass will be enabled. Otherwise it will be 
     *       disabled. 
     *
     * @throws java.lang.IllegalStateException if the host doesn’t support RF 
     *       bypass. 
     *
     **/
    public void setRFBypass(boolean enable) {
    }

    /**
     * Removes the XAIT saved to persistent storage.  If no XAIT is present
     * in persistent storage this method does nothing successfully.  This
     * method SHALL NOT affect a cached XAIT and any running applications.
     * 
     * @throws SecurityException if the calling application is not granted
     *      MonitorAppPermission("storage").
     */
    public void removeXAIT() {
    }

   /**
    * Transition the power mode of the system to the given mode.
    * <p>
    * 
    * If the power mode is already in the target mode, this method
    * SHALL do nothing.
    * 
    * Setting host power mode to low-power SHALL NOT disrupt 
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
    * @param mode The new power mode for the system. 
    *
    * @throws IllegalArgumentException if <i>mode</i> is not one of
    *         {@link Host#FULL_POWER} or {@link Host#LOW_POWER}
    * @throws SecurityException  if the caller does not have 
    *         <code>MonitorAppPermission("powerMode")</code> 
    */
   public void setPowerMode(int mode) {
   }
}
