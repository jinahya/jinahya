package org.ocap.dvr;

import javax.media.Time;
import javax.tv.service.Service;

/**
 * This interface represents a set of time-shift properties that can be set
 * for and queried from a <code>ServiceContext</code>.  Any Host device
 * that supports the OpenCable DVR extension SHALL implement this interface
 * by any class that also implements the ServiceContext interface. 
 */
public interface TimeShiftProperties
{


    /**
     * Adds a listener for time-shift events related to this
     * <code>TimeShiftProperties</code>.
     *
     * @param listener The listener to add.
     * 
     * @see #removeTimeShiftListener(TimeShiftListener)
     */
    public void addTimeShiftListener(TimeShiftListener listener);
    
    /**
     * Removes a previously added listener for time-shift events from this
     * <code>TimeShiftProperties</code>.
     * If the given listener has not previously been added then this method has
     * no effect.
     *
     * @param listener The listener to remove.
     * 
     * @see #addTimeShiftListener(TimeShiftListener)
     */
    public void removeTimeShiftListener(TimeShiftListener listener);

    /**
     * Gets the minimum content buffering duration.  If this method is called
     * before setMinimumDuration has ever been called, or if content buffering
     * is disabled for this <code>ServiceContext</code> the value returned
     * SHALL be 0.
     * 
     * @return The minimum content buffering duration in seconds.
     */
    public long getMinimumDuration();

    /**
     * Sets the minimum duration of content that SHALL be buffered for this
     * <code>ServiceContext</code>. Setting the minimum duration to 0 disables
     * time shifting on the ServiceContext.
     * </p><p>
     * This method MAY be called at any time regardless of service context state.  
     * However, enabling time-shifting or changing the minimum duration
     * SHALL NOT take affect until the <code>ServiceContext</code> is in the not
     * presenting state, presentation pending state, or a new service is selected.  
     * If the same service is selected it is implementation dependent regarding whether
     * time-shift enabling takes affect during the selection.
     * </p><p>
     * Disabling time shifting by setting the minimum duration to 0 SHOULD take effect
     * immediately. 
     * </p><p>
     * When enabling of time shifting by changing the minimum duration from zero to a
     * positive value takes effect, a TimeShiftControl SHALL be added to the associated
     * JMF player.  When time shifting is disabled by changing the minimum duration to
     * zero any existing TimeShiftControl SHALL be removed from the associated JMF
     * player.
     * </p><p>
     * An increase in minimum duration MUST NOT cause any loss of previously
     * buffered content for the current service.
     * </p><p>
     * 
     * @param minDuration Minimum duration in seconds.
     * 
     * @throws IllegalArgumentException If the parameter is greater than the current
     *      value and Host device does not have enough space to meet the request, or if
     *      the parameter is greater than the maximum duration set by the
     *      <code>setMaximumDuration</code> method, or if the parameter is less than the
     *      duration returned by {@link OcapRecordingManager#getSmallestTimeShiftDuration}.
     * @throws SecurityException if the calling application does not have 
     *      <code>ServiceContextPermission("*","own")</code> for the
     *      <code>ServiceContext</code> object that implements this
     *      <code>TimeShiftProperties</code>.
     */
    public void setMinimumDuration(long minDuration);

    /**
     * Gets the maximum content buffering duration.  If this method is called before
     * <code>setMaximumDuration</code> has ever been called, or if content buffering is
     * disabled for this <code>ServiceContext</code> the value returned
     * SHALL be 0.
     * 
     * @return The maximum content buffering duration in seconds.
     */
    public long getMaximumDuration();

    /**
     * Sets the maximum duration of content that MAY be buffered for this
     * <code>ServiceContext</code>.  Informs the implementation that storing more 
     * content than this is not needed by the application owning this
     * <code>ServiceContext</code>.
     * </p><p>
     * This method MAY be called at any time regardless of service context state.  
     * </p><p>
     * 
     * @param maxDuration Maximum duration in seconds.
     * 
     * @throws IllegalArgumentException if the parameter is less than the 
     *      duration set by the <code>setMinimumDuration</code> method, or if
     *      the parameter is less than the duration returned by
     *      {@link OcapRecordingManager#getSmallestTimeShiftDuration}.
     * @throws SecurityException if the calling application does not have 
     *      <code>ServiceContextPermission("*","own")</code> for the
     *      <code>ServiceContext</code> object that implements this
     *      <code>TimeShiftProperties</code>.
     */
    public void setMaximumDuration(long maxDuration);

    /**
     * Gets the "last" service buffered preference.
     *
     * @return Preference indication for recording the "last" service.
     *      Returns true if "last" service should be buffered,
     *      otherwise returns false.
     */
    public boolean getLastServiceBufferedPreference();

    /**
     * Sets a preference to buffer the last service.  This method has
     * no effect if the size of the time-shift buffer associated with
     * the <code>ServiceContext</code> object implementing this
     * interface is set to zero.
     * 
     * @param buffer If true the implementation will buffer the service
     *      selected by the <code>ServiceContext</code> object implementing
     *      this interface, based on time-shift buffer availability;
     *      see the OCAP DVR API specification time-shift buffer
     *      requirements. If false the last service will not be 
     *      buffered.
     * @throws SecurityException if the calling application does not have 
     *      <code>ServiceContextPermission("*","own")</code> for the
     *      <code>ServiceContext</code> object that implements this
     *      <code>TimeShiftProperties</code>.
     */
    public void setLastServiceBufferedPreference(boolean buffer);

    /**
     * Gets the save time-shift contents at service change preference.
     *
     * @return True if save time-shift contents at service selection preference is
     *      enabled, otherwise returns false.
     */
    public boolean getSavePreference();

    /**
     * Sets a preference to retain the time-shift contents for the
     * <code>ServiceContext</code> when a new service is selected.
     * When enabled the time-shift contents are saved back to the
     * value returned by the <code>getMaxTimeShiftDuration</code> method.
     * 
     * @param save If true the implementation will retain the time-shift contents
     *      for the <code>ServiceContext</code> when a new service is selected.  If
     *      false the time-shift contents are flushed when a new service is selected.
     * 
     * @throws IllegalArgumentException if the parameter is true and the Host device
     *      does not have the hardware resources to support the preference.
     * @throws SecurityException if the calling application does not have 
     *      <code>ServiceContextPermission("*","own")</code> for the
     *      <code>ServiceContext</code> object that implements this
     *      <code>TimeShiftProperties</code>.
     */
    public void setSavePreference(boolean save);

    /**
     * Sets the JMF media time location from where the playback
     * will begin when a specific service is selected with this service
     * context.  Also sets the rate of that playback.  If an instance of
     * Time corresponding to value of 0 nanoseconds, or a negative value is
     * set, the playback will begin at the live point.  The default values
     * for the time and rate values is live point and normal playback
     * respectively.  Calling this method for the same service multiple
     * times sets the values to the most recent call.
     * </p><p>
     * The implementation SHALL NOT allow content to be started in the past
     * and beyond the duration set in this <code>ServiceContext</code>, even
     * if content with the time parameter is buffered.  In that case
     * presentation SHALL begin at the duration in the past or at the live
     * point as determined by the action parameter.
     * 
     * @param service The service to set the media time for.
     * @param time The time the service presentation will start at.
     * @param rate The rate at which to start play back.
     * @param action Indicates what to do when the media time is not
     *      buffered when the service is selected.  If true presentation
     *      starts at the beginning of the buffer, otherwise presentation
     *      starts at the live point.
     * @param persistent If true the time and rate apply to every selection
     *      of the service, otherwise they will only apply to the selection
     *      following a call to this method.  In the latter case, once the
     *      values are applied to one service selection they are returned to
     *      their default values.
     * 
     * @throws SecurityException if the calling application does not have 
     *      <code>ServiceContextPermission("*","own")</code> for the
     *      <code>ServiceContext</code> object that implements this
     *      <code>TimeShiftProperties</code>.
     */
    public void setPresentation(Service service,
                                Time time,
                                float rate,
                                boolean action,
                                boolean persistent);

    /**
     * Gets the <code>NetworkInterface</code> currently associated with this
     * <code>ServiceContext</code> corresponding to live or time-shifted content.
     * <p>
     * When the <code>NetworkInterface</code> corresponding to live content is
     * requested, this method SHALL return the interface currently reserved by
     * this <code>ServiceContext</code>, if any.  
     * This <code>NetworkInterface</code> SHALL be one of the interfaces returned 
     * by {@link org.davic.net.tuning.NetworkInterfaceManager#getNetworkInterfaces()}.
     * That is, this SHALL be the same as would be returned by
     * {@link org.dvb.service.selection.DvbServiceContext#getNetworkInterface()} when
     * called by an application executing outside of this service context.
     * <p>
     * When the <code>NetworkInterface</code> corresponding to time-shifted content
     * is requested, this method SHALL return a reference to a "special" 
     * <code>NetworkInterface</code> as defined in the main body of the specification
     * for {@link org.dvb.service.selection.DvbServiceContext#getNetworkInterface()}.
     * That is, this SHALL be the same as would be returned by
     * <code>DvbServiceContext.getNetworkInterface</code> when called by an 
     * application executing within this service context.
     * 
     * @param presentation <code>false</code> indicates that the <code>NetworkInterface</code>
     *           corresponding to live content is to be returned; 
     *           <code>true</code> indicates that the <code>NetworkInterface</code>
     *           corresponding to time-shifted content is to be returned.
     * @return the specified <code>NetworkInterface</code> or <code>null</code>
     */
    public org.davic.net.tuning.NetworkInterface getNetworkInterface(boolean presentation);

}

