package org.ocap.dvr.event;

import java.util.Date;
import javax.tv.service.selection.ServiceContext;
import org.davic.net.tuning.NetworkInterface;
import org.ocap.dvr.BufferingRequest;
import org.ocap.net.OcapLocator;
import org.ocap.shared.dvr.RecordingRequest;

/**
 * This interface represents a session created to build an artificial carousel
 * with a DSMCCStreamEvent.
 */
public interface LightweightTriggerSession
{

    /**
     * Gets the locator for the artificial carousel.  The locator is only valid
     * during this session.  The locator returned is a valid Locator
     * that is implementation specific and unique to the artificial carousel.
     * The locator returned SHALL be valid and usable in a
     * <code>ServiceDomain</code> attach method call as long as the
     * service containing the stream is presenting or is accessible in a
     * time-shift buffer.
     * <p>
     * The return value is constant for the life of this object.
     * 
     * @return locator Locator of the artificial carousel.
     */
    public OcapLocator getLocator();
    
    /**
     * Gets the stream type this session was created for and that the
     * handler registered interest in.
     * <p>
     * The return value is constant for the life of this object.
     * 
     * @return Stream type for this session.
     */
    public abstract short getStreamType();

    /**
     * Get the array of PIDs for the streams with the stream type of
     * interest.  The array SHALL be ordered from lowest PID number to
     * highest.  Returns the PIDs for the stream type of interest and 
     * that are associated with this session.
     * <p>
     * The return value MAY change over the life of this object.  Changes
     * are signaled by invocation of {@link StreamChangeListener#notifyPIDsChanged}
     * on the {@link #setStreamChangeListener set} listener.
     * 
     * @return The PIDs for this session.
     */
    public abstract int [] getPIDs();

    /**
     * Gets the broadcast service for which this session was created.
     * A reference to a <code>Service</code> object representing the program 
     * for which this session was created SHALL be returned.  
     * <p>
     * The return value is constant for the life of this object.
     * 
     * @return a <code>Service</code> object representing the service
     */
    public javax.tv.service.Service getService();


    /**
     * Gets the <code>ServiceContext</code> for the stream type of
     * interest. 
     * <p>
     * The return value is constant for the life of this object.
     *  
     * @return If a <code>ServiceContext</code> has selected or is in the process
     *      of selecting the service containing the stream type of interest and the
     *      calling application has permission to access it, then the
     *      <code>ServiceContext</code> for this session is returned,
     *      otherwise this method returns null.
     */
    public abstract ServiceContext getServiceContext();

    /**
     * Gets the <code>BufferingRequest</code> for the stream type of
     * interest.
     * <p>
     * The return value is constant for the life of this object.
     *  
     * @return If the service carrying the stream type of interest is buffering in the
     *      background, then the
     *      <code>BufferingRequest</code> for the stream is returned,
     *      otherwise this method returns null.
     */
    public abstract BufferingRequest getBufferingRequest();

    /**
     * Gets the <code>NetworkInterface</code> for the stream type of
     * interest.
     * <p>
     * The return value is constant for the life of this object.
     *  
     * @return If a <code>NetworkInterface</code> is tuned to the service carrying the
     *      stream type of interest it is returned, otherwise this
     *      method returns null.
     */
    public abstract NetworkInterface getNetworkInterface();

    /**
     * Gets the <code>RecordingRequest</code> for the stream type of interest.
     * <p>
     * The return value is constant for the life of this object.
     * 
     * @return If an in-progress or transitioning-to-in-progress recording references the
     *      service carrying the stream type of interest, the associated <code>RecordingRequest</code>
     *      is returned; otherwise this method returns null.
     */
    public abstract RecordingRequest getRecordingRequest();

    /**
     * Returns an indication of service containing stream type is 
     * presenting to outputs or display, or if it is being buffered
     * or recorded in the background.
     * <p>
     * The return value MAY change over the life of this object.  Changes
     * are signaled by invocation of {@link StreamChangeListener#notifyPresentationChanged}
     * on the {@link #setStreamChangeListener set} listener.
     * 
     * @return True if the service containing stream type of interest
     *      is presenting,otherwise returns false.
     */
    public abstract boolean isPresenting();

    /**
     * Returns the CA authorization status for the stream(s) referenced by
     * {@link #getPIDs}.  
     * <p>
     * The return value MAY change over the life of this object.  Changes
     * are signaled by invocation of {@link StreamChangeListener#notifySessionStopped}
     * on the {@link #setStreamChangeListener set} listener.
     * 
     * @return True if all of the streams are authorized (i.e., can be decrypted); 
     *         false if otherwise.
     */
    public boolean isAuthorized();

    /**
     * Registers a synchronized event to the stream event list for this session.
     * 
     * @param date The time when the event is to be generated.  The
     *      implementation SHALL create JMF media time from this value for
     *      use with presenting broadcast and recorded services.
     * @param name A name for the event being registered.  This name SHALL
     *      appear in the list of events returned by the DSMCCStreamEvent
     *      getEventList method.
     * @param id The unique identifier of the event.
     * @param data Application specific data associated with the event. 
     *      This data will be delivered with the <code>StreamEvent</code>
     *      when the media time is incurred in the interested stream during
     *      playback.  The maximum size of this data is 4096 bytes.
     * 
     * @throws IllegalArgumentException if the name or id already exist, or
     *      if the data array contains more than 4096 byte entries.
     * @throws IllegalStateException if the session is not open, i.e. has
     *      been stopped by the implementation or an application.
     */
    public abstract void registerEvent(Date date,
                                       String name,
                                       int id,
                                       byte [] data);

    /**
     * Sets the listener for this session.
     * <p>
     * If the session has already been stopped by the implementation when the 
     * listener is set, then the listener SHALL be notified immediately
     * via {@link StreamChangeListener#notifySessionStopped}.  
     * 
     * @param listener The listener to set.  If null any previously set
     *      listener is removed.
     */
    public void setStreamChangeListener(StreamChangeListener listener);

    /**
     * Stops the session.  If an artificial carousel was created and the
     * store method was called the carousel is stored at this time.  If
     * the session was already stopped this method does nothing successfully.
     */
    public void stop();

    /**
     * Stores the artificial carousel created for an open session with any
     * permanent recordings made of any elementary streams in the same
     * program as the stream type associated with the open session.  The
     * implementation SHALL adjust the media times in the stored
     * <code>DSMCCStreamEvent</code> so that they occur at the same point in
     * the recording presentation as they did in the presentation recorded
     * from.  The implementation SHALL store all events added to the 
     * <code>DSMCCStreamEvent</code> while the session is active, i.e. events
     * added after the {@link LightweightTriggerHandler#notifyStreamType}
     * method was called and before the <code>stop</code> is called for the
     * session.  If a session does not contain any registered events that
     * fall within the duration of a recording when the session is stopped
     * the artificial carousel is not stored with the recording.  The
     * implementation SHALL update the stored carousel as soon as an
     * event is registered that falls within the duration of the recording.
     * It is illegal to call this method after a session has been closed.
     * 
     * @throws SecurityException if the calling application does not have
     *      file permission granted in its permission request file.
     * @throws IllegalStateException if this method is called after the
     *      session has been closed, or if there are no events in the 
     *      artificial carousel.
     */
    public abstract void store();

}
