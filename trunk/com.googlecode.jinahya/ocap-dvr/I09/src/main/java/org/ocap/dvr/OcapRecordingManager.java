package org.ocap.dvr;

import org.ocap.dvr.storage.SpaceAllocationHandler;
import org.ocap.shared.dvr.RecordingRequest;
import org.ocap.shared.dvr.RecordingSpec;
import org.ocap.dvr.RequestResolutionHandler;
import org.ocap.shared.dvr.RecordingManager;
import org.ocap.resource.ResourceUsage;
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.Service;
import org.ocap.shared.dvr.RecordedService;
import org.ocap.shared.dvr.AccessDeniedException;
import java.io.Serializable;
import org.ocap.shared.dvr.navigation.RecordingList;

/**
 * RecordingManager represents the entity that performs recordings and
 * maintains a database of recordings. An instance of this class is
 * returned when an application calls the RecordingManager.getInstance()
 * class in OCAP platforms.
 * </p><p>
 * The setPrioitization method is intended to be used with the current
 * set of prioritized resource usages that would be returned by a call
 * to the getPrioritizedUsages method.  However, the set of resource
 * usages returned by a call to the getPrioritizedResourceUsages MAY be
 * invalid in a subsequent call to the setPrioritization method as the
 * set MAY have changed during the time between the two method calls.
 */
public abstract class OcapRecordingManager extends RecordingManager
{
    /**
     * Adds an event listener for receiving events corresponding to a
     * transition from a pending state to an in-progress state or a
     * failed state. The listener parameter will only be informed of
     * these events for entries the calling application has read file
     * access permission to.
     *
     * @param ral The listener to be registered.
     */
    public abstract void addRecordingAlertListener(RecordingAlertListener ral);

    /**
     * Adds an event listener for receiving events corresponding to a
     * transition from a pending state to an in-progress state or a
     * failed state. The listener parameter will only be informed of
     * these events for entries the calling application has read file
     * access permission to.
     *
     * @param ral The listener to be registered.
     *
     * @param alertBefore Time in milliseconds for the alert to be generated
     *      before the start of the scheduled event.
     */
    public abstract void addRecordingAlertListener(RecordingAlertListener ral,
                                                   long alertBefore);

    /**
     * Removes a registered event listener for receiving recording events.
     * If the listener specified is not registered then this method has
     * no effect.
     *
     * @param ral the listener to be removed.
     */
    public abstract void removeRecordingAlertListener(
                                                     RecordingAlertListener ral);

    /**
     * Adds an event listener for receiving events corresponding to a
     * recording playback start. The listener parameter will only be informed
     * of these events for service contexts and services that the calling
     * application respectively owns and has access to.
     * 
     * @param listener The listener to add.
     */
    public abstract void addRecordingPlaybackListener(
                                        RecordingPlaybackListener listener);

    /**
     * Removes a registered event listener for receiving recording playback
     * events. If the listener specified is not registered then this method
     * has no effect.
     *
     * @param listener The listener to be removed.
     */
    public abstract void removeRecordingPlaybackListener(
                                        RecordingPlaybackListener listener);

    /**
     * Set the SpaceAllocationHandler that will be invoked when any application
     * attempts to allocate space in any MediaStorageVolume. At most one
     * instance of this handler can be set. Subsequent calls to this method
     * replace the previous instance with the new one.
     *
     * @param sah the space reservation handler.
     *
     * @throws SecurityException if the caller does not have
     *      MonitorAppPermission("handler.recording").
     */
    public abstract void setSpaceAllocationHandler(SpaceAllocationHandler sah);

    /**
     * Set the RequestResolutionHandler that will be invoked when any
     * application calls the RecordingManager.record method. At most only one
     * instance of this handler can be set. Subsequent calls to this method
     * replaces the previous instance with the new one.
     *
     * @param rrh the request resolution handler.
     *
     * @throws SecurityException if the caller does not have
     *      MonitorAppPermission("handler.recording").
     */
    public abstract void setRequestResolutionHandler(RequestResolutionHandler rrh);
    
    /**
     * Schedule a child recording request corresponding to an unresolved or
     * partially resolved recording request. This method is called either
     * by the RequestResolutionHandler or by an application that has enough
     * information to provide request resolutions. The implementation
     * SHALL generate a recording request corresponding to each successful
     * invocation of this method and make that recording request a child
     * of the RecordingRequest passed in as the first parameter. If the
     * implementation has enough information to resolve the newly created
     * recording request, the implementation should resolve the recording
     * request.
     * <p>
     * Implementation should set the state of the recording request
     * "request" to "resolutionState" before the return of this call.
     *
     * @param request the RecordingRequest for which the resolution is
     *      provided.
     * @param spec the RecordingSpec for the child recording request.
     * @param resolutionState the state of the RecordingRequest after
     *      the return of this method. The possible values for this
     *      parameter are the states defined in ParentRecordingRequest.
     *
     * @return the newly scheduled recording request.
     *
     * @throws SecurityException if the caller does not have
     *      MonitorAppPermission("handler.recording").
     * @throws IllegalArgumentException if the resolutionState is not a
     *      state defined in ParentRecordingRequest, or if the
     *      request is not in unresolved or partially resolved state.
     */
    public abstract RecordingRequest resolve(RecordingRequest request,
                                             RecordingSpec spec, int resolutionState);

    /**
     * Get the prioritized list of overlapping ResourceUsages corresponding
     * to a particular recording request. The list of resource usages may
     * include RecordingResourceUsages and other types of ResourceUsages.
     * The ResourceUsage corresponding to the specified recording request
     * is also included in the prioritized list. The prioritized list is
     * sorted in descending order of prioritization. The prioritization for
     * resource usages is based on OCAP resource management.
     *
     * @param recording the RecordingRequest for which overlapping resource
     *      usages are sought.
     *
     * @return the list of ResourceUsages overlapping with the specified
     *      RecordingRequest, including the ResourceUsage corresponding to
     *      the specified RecordingRequest, sorted in descending order of
     *      prioritization, null if the RecordingRequest is not in one
     *      of the pending or in-progress states.
     *
     * @throws SecurityException if the caller does not have
     *      MonitorAppPermission("handler.recording").
     * @throws IllegalArgumentException if the parameter is null.
     */
    public abstract ResourceUsage[] getPrioritizedResourceUsages(
        RecordingRequest recording);

    /**
     * Sets the relative priorities for a set of ResourceUsages. This
     * method MAY be used by an application with
     * MonitorAppPermission("handler.recording") to set the relative priorities
     * for a set of overlapping resource usages. The implementation SHOULD
     * use the specified prioritization scheme to resolve conflicts
     * (resource conflicts as well as conflicts for RecordingRequests)
     * between these overlapping resource usages.  This call MAY change
     * the relative priorities specified by the contention handler or a
     * previous call to this method. Changing the relative priorities for
     * the resource usages MAY result in one or more recording requests
     * changing states.  The implementation SHALL only change the
     * ordering of the ResourceUsages passed in the resourceUsageList
     * parameter.  This method is meant to be used with the
     * getPrioritizedResourceUsages
     * method.
     * </p>
     *
     * @param resourceUsageList a list of ResourceUsages sorted in
     *      descending order of prioritization
     *
     * @throws SecurityException if the caller does not have
     *      MonitorAppPermission("handler.recording").
     * @throws IllegalArgumentException if the parameter does not match a
     *      current set of overlapping ResourceUsages.
     */
    public abstract void setPrioritization(ResourceUsage[] resourceUsageList);

    /**
     * Requests the implementation to start buffering a service using implementation
     * specific time-shift storage.  If successful, the service will
     * be buffered, but audio and video presentation will not take place. 
     * 
     * @param request The <@link BufferingRequest> to make active.
     * 
     * @throws SecurityException if the calling application does not have the 
     *      "file" element set to true in its permission request file.
     */
    public abstract void requestBuffering(BufferingRequest request);

    /**
     * Gets a set of buffering requests that were passed to the 
     * requestBuffering method and have not been cancelled.
     * 
     * @return An array of active buffering requests, or a 0 length array
     *      if no buffering requests are active.
     */
    public abstract BufferingRequest[] getBufferingRequests();

    /**
     * Cancels an active buffering request.  If the <@link BufferingRequest>
     * parameter is not active this method does nothing and returns
     * successfully.
     * 
     * @param request The <code>BufferingRequest</code> to cancel.
     * 
     * @throws SecurityException if the calling application does not
     *      have write permission for the request as determined by the
     *      <code>ExtendedFileAccessPermissions</code> returned by
     *      the <code>getExtendedFileAccessPermissions</code> method
     *      in the parameter, or if the calling application does not
     *      have MonitorAppPermission("handler.recording").
     */
    public abstract void cancelBufferingRequest(BufferingRequest request);

    /**
     * Gets the maximum bit rate the implementation will use for duration to space in
     * calculations.
     * 
     * @return Maximum bit-rate in bits per second.
     */
    public abstract long getMaxBitRate();

    /**
     * Records the stream or streams according to the source
     * parameter.  The concrete sub-class of RecordingSpec MAY define
     * additional semantics to be applied when instances of that sub-class
     * are used.  Overloaded from the
     * <code>org.ocap.shared.dvr.RecordingManager.record</code>
     * method.  This method is identical to that method except for the 
     * key and appData parameters used to add application specific
     * private data.
     * </p><p>
     * The keys and appData parameters are parallel arrays where the first entry
     * in the keys array corresponds to the first entry in the appData array
     * and so forth.  When a <code>RecordingRequest</code> is created from a
     * call to this method and then delivered to a
     * <code>RecordingChangedListener</code>, the request SHALL contain the
     * application data passed to this method. This method SHALL add the
     * new RecordingRequest to the recording database maintained by this
     * manager before returning and it SHALL include the appData parameter in
     * the RecordingRequest in the database at that time. If conflicts are
     * detected during this method, the appData SHALL be made available in the 
     * recording database for application access before any OCAP handler
     * application is called, e.g., resource contention handler application.
     *
     * @param source specification of stream or streams to be recorded
     *      and how they are to be recorded.
     * @param keys the IDs under which the application data is to be added.
     * @param appData the private application data to be added.
     *
     * @return an instance of RecordingRequest that represents the added
     *      recording.
     *
     * @throws IllegalArgumentException if the source is an application
     *      defined class or as defined in the concrete sub-class of
     *      RecordingSpec for instances of that class.  Also throws this
     *      exception if the keys or appData parameters are null or not
     *      the same length.
     * @throws AccessDeniedException if the calling application is not 
     *      permitted to perform this operation by RecordingRequest specific 
     *      security attributes.
     * @throws SecurityException if the calling application does not have 
     *      RecordingPermission("create",..) or RecordingPermission("*",..)
     */
    public abstract RecordingRequest record(RecordingSpec source,
                                            String [] keys,
                                            Serializable [] appData)
    throws AccessDeniedException;

    /**
     * Sets the amount of time to delay the start of scheduled recordings
     * after the initial monitor application is running.  Calling this
     * method more than once over-writes the previous setting.
     * 
     * @param seconds Number of seconds to delay.
     * 
     * @throws SecurityException if the calling application does not
     *      have MonitorAppPermission("recording").
     * @throws IllegalArgumentException is the parameter is negative.
     */
    public abstract void setRecordingDelay(long seconds);

    /**
     * Informs the implementation it SHALL start scheduled recordings
     * if it hasn't already done so.  Terminates timeout of the delay
     * set by the #setRecordingDelay method if it is still in effect.
     * 
     * @throws SecurityException if the calling application does not
     *      have MonitorAppPermission("recording").
     */
    public abstract void signalRecordingStart();

    /**
     * Gets the smallest time-shift duration supported by the
     * implementation.  This method SHALL return a value greater than
     * zero.
     * 
     * @return The smallest time-shift duration in seconds that is supported
     *      by the implementation.
     */
    public abstract long getSmallestTimeShiftDuration();

    /**
     * Enables time-shift buffering and buffering without presentation.  The
     * default is buffering is enabled.
     * 
     * @throws SecurityException if the calling application does not have
     *       MonitorAppPermission("recording").
     */
    public abstract void enableBuffering();

    /**
     * Disables time-shift buffering and buffering without presentation.  All
     * time-shift operations cease immediately and any presenting services
     * that are time-shifted SHALL be taken to the live point.  Any buffering
     * without presentation activities SHALL cease to be honored.  Any content
     * in a time-shift buffer before this method was called SHALL not be
     * accessible if the <code>enableBuffering</code> method is called. If
     * an implementation uses time-shift buffering for recording creation it
     * MAY segment the recording.
     * 
     * @throws SecurityException if the calling application does not have
     *       MonitorAppPermission("recording").
     */
    public abstract void disableBuffering();

    /**
     * Deletes multiple recordings.  The implementation SHALL execute the
     * equivalent of the <code>RecordingRequest.delete</code> method for each
     * <code>RecordingRequest</code> in the <code>requests</code> parameter.
     * </p><p>
     * The recordings SHALL be deleted in incrementing array index order from
     * the first element at <code>requests[0]</code>.
     *
     * @param requests List of <code>RecordingRequest</code> recordings
     *      to delete.
     * 
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("handler.recording").
     */
    public abstract void deleteRecordings(RecordingList requests);

    /**
     * Deletes all recordings.  The implementation SHALL execute the
     * equivalent of the <code>RecordingRequest.delete</code> method
     * for each <code>RecordingRequest</code> in the database of
     * recordings maintained by this manager and delete all of the recordings
     * in the database.
     * </p><p>
     * To avoid asynchronous race conditions while deleting <code>RecordingRequests</code> that are
     * pending due to resource contention (i.e. tuner and storage availability),
     * recordings that have acquired resources SHALL be deleted after all other
     * <code>LeafRecordingRequests</code> have been deleted.
     * </p><p>
     * The implementation SHALL also delete all <code>RecordingRequests</code> in the DELETED_STATE.
     * </p><p>
     * Once all <code>LeafRecordingRequests</code> have been successfully deleted, the <code>ParentRecordingRequests      *	</code>
     * SHALL then be deleted last.
     * </p>
     *
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("handler.recording").
     */
    public abstract void deleteAllRecordings();

}
