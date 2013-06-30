package org.ocap.dvr;

import java.util.Date;
import javax.tv.service.Service;
import org.dvb.application.AppID;
import org.davic.net.tuning.NetworkInterface;
import org.ocap.storage.ExtendedFileAccessPermissions;

/**
 * This class represents an application request for buffering.  An application
 * can call the <code>createInstance</code> method to create a request.
 */
public abstract class BufferingRequest
{
    /**
     * Protected constructor, not to be used by applications.
     */
    protected BufferingRequest()
    {
    }

    /**
     * Creates a BufferingRequest object.
     * 
     * @param service The service to buffer.
     * @param minDuration Minimum duration in seconds to buffer.
     * @param maxDuration Maximum duration in seconds to buffer.
     * @param efap Extended file access permissions for this request.  If this 
     *      parameter is null, no write permissions are given to this request.
     *      Read permissions for <code>BufferingRequest</code> instances are
     *      always world regardless of read permissions set by this parameter. 
     * @throws IllegalArgumentException if the service parameter is not a valid
     *      <code>Service</code>, or if <code>minDuration</code> is less than
     *      {@link OcapRecordingManager#getSmallestTimeShiftDuration}, or if
     *      <code>maxDuration</code> is less than <code>minDuration</code>.
     */
    public static BufferingRequest createInstance(Service service,
                                                  long minDuration,
                                                  long maxDuration,
                                                  ExtendedFileAccessPermissions efap)
    {
        return null;
    }

    /**
     * Gets the Service this request is attempting to buffer.
     * 
     * @return Service being buffered for this request.
     */
    public abstract Service getService();

    /**
     * Sets the Service this request is attempting to buffer.
     * 
     * @param service The <code>Service</code> to buffer for this request.
     * 
     * @throws IllegalArgumentException if the parameter is not a valid
     *      <code>Service</code>.
     * @throws SecurityException if the calling applications does not have one 
     *      of the write ExtendedFileAccessPermissions set by the 
     *      <code>createInstance</code> or
     *      <code>setExtendedFileAccessPermissions</code> methods.
     */
    public abstract void setService(Service service);

    /**
     * Gets the minimum content buffering duration for this request. 
     * 
     * @return The minimum content buffering duration in seconds.
     */
    public abstract long getMinimumDuration();

    /**
     * Sets the minimum duration of content that SHALL be buffered for this
     * request.  If this method necessitates a buffer re-size the implementation
     * MAY flush the contents of the buffer.
     * 
     * @param minDuration Minimum duration in seconds.
     * 
     * @throws IllegalArgumentException If the parameter is greater than the current
     *      value and Host device does not have enough space to meet the request, or if
     *      the parameter is greater than the maximum duration set by the
     *      <code>createInstance</code> or <code>setMaximumDuration</code> methods,
     *      or if the parameter is less than the duration returned by
     *      {@link OcapRecordingManager#getSmallestTimeShiftDuration}.
     * 
     * @throws SecurityException if the calling application does not have one 
     *      of the write ExtendedFileAccessPermissions set by the 
     *      <code>createInstance</code> or
     *      <code>setExtendedFileAccessPermissions</code> methods.
     */
    public abstract void setMinimumDuration(long minDuration);
    
    /**
     * Gets the maximum duration to buffer for this request.  Returns the
     * value set by the <code>createInstance</code> or
     * <code>setMaximumDuration</code> methods.
     * 
     * @return Maximum duration in seconds.
     */
    public abstract long getMaxDuration();

    /**
     * Sets the maximum duration of content that MAY be buffered for this
     * <code>BufferingRequest</code>.  Informs the implementation that
     * storing more content than this is not needed by the application
     * owning this <code>BufferingRequest</code>.
     * 
     * @param duration The maximum duration in seconds.
     * 
     * @throws IllegalArgumentException if the duration parameter is negative
     *      or if the parameter is less than the minimum duration set by the
     *      <code>createInstance</code> or <code>setMaximumDuration</code>
     *      methods, or if the parameter is less than the duration returned by
     *      {@link OcapRecordingManager#getSmallestTimeShiftDuration}.
     * @throws SecurityException if the calling application does not have one 
     *      of the write ExtendedFileAccessPermissions set by the 
     *      <code>createInstance</code> or
     *      <code>setExtendedFileAccessPermissions</code> methods.
     */
    public abstract void setMaxDuration(long duration);

    /**
     * Gets the ExtendedFileAccessPermissions for this request.
     * 
     * @return The ExtendedFileAccessPermissions.
     */
    public abstract ExtendedFileAccessPermissions getExtendedFileAccessPermissions();

    /**
     * Sets the ExtendedFileAccessPermissions for this request.
     * 
     * @param efap The ExtendedFileAccessPermissions for this request.
     * 
     * @throws IllegalArgumentException if the parameter is null;
     * @throws SecurityException if the calling application is not the creator
     *      of this request.
     */
    public abstract void setExtendedFileAccessPermissions(
                                        ExtendedFileAccessPermissions efap);

    /**
     * Gets the AppID of the application that created the request.  If null is
     * returned the implementation created the request.
     * 
     * @return AppID of the owning application.     
     */
    public abstract AppID getAppID();
}
