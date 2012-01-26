package org.ocap.hn.recording;

import org.ocap.hn.NetActionEvent;
import org.ocap.hn.NetModule;
import org.ocap.hn.NetActionRequest;
import org.ocap.hn.NetActionHandler;
import org.ocap.hn.content.ContentEntry;

/**
 * <p>
 * An interface representing a NetModule which provides DVR functionality.
 * </p>
 * <p>
 * NetModules which implement this interface SHALL have a NetModule.PROP_NETMODULE_TYPE
 * property value of NetModule.CONTENT_RECORDER.
 * </p>
 */
public interface RecordingNetModule extends NetModule {

    /**
     * Requests that a recording be scheduled on this network recording device.
     * metadata added to the NetRecordingSpec prior to calling this method will
     * be utilized by the remote device in identifying the recording or
     * recordings to be scheduled. Upon completion of this operation, a NetActionEvent
     * SHALL be delivered to the given handler indicating success or failure.
     * Upon success, values returned by calls to  {@link NetActionEvent#getResponse()} 
     * SHALL contain a NetRecordingEntry representing the newly created recording.
     * 
     * @param recordingSpec
     *            a recording spec containing the metadata used to identify the
     *            recordings to be scheduled.
     * @param handler
     *            The NetActionHandler which gets informed once this request
     *            completes.
     * 
     * @return NetActionRequest to inform calling application of results.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recording")
     * 
     * @throws IllegalArgumentException if recordingSpec has an empty MetadataNode,
     *	or if MetadataNode which is associated with recordingSpec does not contain
     *  the necessary metadata entry such as scheduledChannelID, scheduledStartDateTime,
     *	scheduledDuration
     */
    NetActionRequest requestSchedule(NetRecordingSpec recordingSpec,
            NetActionHandler handler);

    /**
     * Requests that a recording be rescheduled on this network recording
     * device. Metadata added to the NetRecordingSpec prior to calling this
     * method will be utilized by the remote device in identifying changes the
     * recording or recordings to be rescheduled. Upon completion of this operation, 
     * a NetActionEvent SHALL be delivered to the given handler indicating success 
     * or failure.
     * 
     * @param recording
     *            the previously scheduled RecordingContentItem or NetRecordingEntry to be
     *            rescheduled.
     * @param recordingSpec
     *            a recording spec containing the metadata used to identify the
     *            changes to recordings to be rescheduled.
     * @param handler
     *            The NetActionHandler which gets informed once this request
     *            completes.
     * 
     * @return NetActionRequest to inform calling application of results.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recording")
     * 
     * @throws IllegalArgumentException if the recording parameter is neither 
     * 	the NetRecordingEntry with upnp:srsRecordScheduleID metadata entry
     *	nor the RecordingContentItem with upnp:srsRecordTaskID metadata entry
     *  in its own MetadataNode,or if recordingSpec has an empty MetadataNode,
     *  or if MetadataNode which is associated with recordingSpec does not contain
     *  the necessary metadata entry such as scheduledChannelID, scheduledStartDateTime,
     *	scheduledDuration
     */
    NetActionRequest requestReschedule(ContentEntry recording,
            NetRecordingSpec recordingSpec, NetActionHandler handler);

    /**
     * Requests that an in progress recording be disabled on this network
     * recording device. If the recording is in progress, this method requests
     * that the recording be stopped. If the recording is pending, this method
     * requests that the recording be canceled. Upon completion of this operation, 
     * a NetActionEvent SHALL be delivered to the given handler indicating success 
     * or failure.
     * 
     * @param recording
     *            a RecordingContentItem or NetRecordingEntry that identifies the recording(s) to be canceled.
     * 
     * @param handler
     *            The NetActionHandler which gets informed once this request
     *            completes.
     * 
     * @return NetActionRequest to inform calling application of results.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recording")
     * 
     * @throws IllegalArgumentException if the recording parameter is neither 
     * 	the NetRecordingEntry with upnp:srsRecordScheduleID metadata entry
     *	nor the RecordingContentItem with upnp:srsRecordTaskID metadata entry
     *  in its own MetadataNode
     */
    NetActionRequest requestDisable(ContentEntry recording,
            NetActionHandler handler);

    /**
     * Requests that content associated with a scheduled recording be deleted
     * from storage. Upon completion of this operation, a NetActionEvent
     * SHALL be delivered to the given handler indicating success or failure.
     * 
     * @param recording
     *            a RecordingContentItem or NetRecordingEntry that identifies the recording(s) to be deleted.
     * 
     * @param handler
     *            The NetActionHandler which gets informed once this request
     *            completes.
     * 
     * @return NetActionRequest to inform calling application of results.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recording")
     */
    NetActionRequest requestDeleteService(ContentEntry recording,
            NetActionHandler handler);

    /**
     * Requests that metadata associated with a scheduled recording be deleted
     * from storage. Upon completion of this operation, a NetActionEvent
     * SHALL be delivered to the given handler indicating success or failure.
     * 
     * @param recording
     *            a recording that identifies the recording to be deleted.
     * 
     * @param handler
     *            The NetActionHandler which gets informed once this request
     *            completes.
     * 
     * @return NetActionRequest to inform calling application of results.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recording")
     */
    NetActionRequest requestDelete(ContentEntry recording,
            NetActionHandler handler);

    /**
     * Requests that a group of scheduled individual recordings be prioritized on this
     * network recording device. Prioritization is determined by the ordering of
     * recordings in the array of RecordingContentItems, with highest priority
     * given to the entry at element 0 in the array. Upon completion of this 
     * operation, a NetActionEvent SHALL be delivered to the given handler 
     * indicating success or failure.
     * 
     * @param recordings
     *            a prioritized array of RecordingContentItems
     * @param handler
     *            The NetActionHandler which gets informed once this request
     *            completes.
     * 
     * @return NetActionRequest to inform calling application of results.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recording")
     */
    NetActionRequest requestPrioritize(RecordingContentItem recordings[],
            NetActionHandler handler);
    
    /**
     * Requests that a group of scheduled recording request be prioritized on this
     * network recording device, where each recording request may represent one
     * or more individual recordings on the remote device. Prioritization is 
     * determined by the ordering of recordings in the array of NetRecordingEntries
     * with highest priority given to the entry at element 0 in the array. 
     * Upon completion of this operation, a NetActionEvent SHALL be delivered 
     * to the given handler indicating success or failure.
     * 
     * @param recordings
     *            a prioritized array of NetRecordingEntries
     * @param handler
     *            The NetActionHandler which gets informed once this request
     *            completes.
     * 
     * @return NetActionRequest to inform calling application of results.
     * 
     * @throws SecurityException if the caller does not have 
     *         HomeNetPermission("recording")
     */
    NetActionRequest requestPrioritize(NetRecordingEntry recordings[],
            NetActionHandler handler);

}
