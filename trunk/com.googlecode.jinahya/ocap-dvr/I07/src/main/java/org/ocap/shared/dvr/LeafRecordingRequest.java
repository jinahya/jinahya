package org.ocap.shared.dvr;

import org.ocap.shared.dvr.navigation.RecordingList;

/**
 * This interface represents information corresponding to a leaf level recording request.
 * The recording request represented by this interface corresponds to a recording
 * request that has been completely resolved to a single recording.
 *<p>
 * A leaf recording request may be pending (i.e. waiting for the start-time to occur), in-progress,
 * completed, incomplete, or failed.
 * <p>
 * While in pending state, a recording request may be in conflict for resources with other
 * recordings.  Any such conflicts must be resolved before the scheduled start time of the
 * recording, if not, the pending recording request is expected to result in a failed recording.
 */
public interface LeafRecordingRequest extends RecordingRequest
{
    /**
     * The recording request is Pending. Recording for this request is expected to complete
     * successfully.
     */
    public static final int PENDING_NO_CONFLICT_STATE = 1;

    /**
     * The recording request may not be intiated due to resource conflicts. The implementation has
     * detected a resource conflict for the scheduled time of this recording request and the current
     * resolution of the conflict does not allow this recording request to be initiated
     * successfully.
     */
    public static final int PENDING_WITH_CONFLICT_STATE = 2;

    /**
     * Recording has been initiated for this recording request and is ongoing. Recording
     * is expected to complete successfully.
     */
    public static final int IN_PROGRESS_STATE = 4;

    /**
     * Recording has been initiated for this recording request and is ongoing, but the
     * implementation has detected
     * that storage space may not be sufficient to complete the recording.
     */
    public static final int IN_PROGRESS_INSUFFICIENT_SPACE_STATE = 5;

    /**
     * Recording for this recording request was initiated but failed.
     */
    public static final int INCOMPLETE_STATE = 6;

    /**
     * Recording for this recording request has completed successfully.
     */
    public static final int COMPLETED_STATE = 7;

    /**
     * The recording request has failed.
     */
    public static final int FAILED_STATE = 8;

    /**
     * The recorded service corresponding to this recording request has 
	 * been deleted.
     */
    public static final int DELETED_STATE = 14;
    /**
     * The recording request is in progress but recording cannot take place due
     * to some error; e.g. lack of resources.
     */
    public static final int IN_PROGRESS_WITH_ERROR_STATE = 15;
    
    /**
     * The recording request is in progress and recording, but cannot be completed
     * because it was started after the start_time or it was interrupted and 
     * re-started.
     */
    public static final int IN_PROGRESS_INCOMPLETE_STATE = 16;

    /**
     * Cancels a pending recording request.
     * If this method completes successfully without throwing an exception then the
     * recording request will have been deleted from the database.
     * Cancelling a recording request may resolve one or more conflicts. In this case some
     * pending recordings with conflicts would be changed to pending without conflicts.
     *
     * @throws AccessDeniedException if the calling application is not permitted to 
     * perform this operation by RecordingRequest specific security attributes.
     * @throws SecurityException if the calling application does not have 
     * RecordingPermission("cancel",..) or RecordingPermission("*",..)
     * @throws IllegalStateException if the stateof the recording is not in
     *      PENDING_NO_CONFLICT_STATE or PENDING_WITH_CONFLICT_STATE.
     */
    public void cancel() throws IllegalStateException, AccessDeniedException;

    /**
     * Stops the recording for an in-progress recording request regardless of how
     * much of the duration has been recorded.  Moves the recording to the INCOMPLETE_STATE.
     *
     * @throws AccessDeniedException if the calling application is not permitted to perform 
     * this operation by RecordingRequest specific security attributes.
     * @throws SecurityException if the calling application does not have 
     * RecordingPermission("cancel",..) or RecordingPermission("*",..)
     * @throws IllegalStateException if the recording is not in the
     *      IN_PROGRESS_STATE, or IN_PROGRESS_INSUFFICIENT_SPACE_STATE
     *      or IN_PROGRESS_WITH_ERROR_STATE, or IN_PROGRESS_INCOMPLETE_STATE..
     */
    public void stop() throws IllegalStateException,AccessDeniedException;

    /**
     * Gets the exception that caused the recording request to enter the <code>FAILED_STATE</code>,
     * or <code>INCOMPLETE_STATE</code> or <code>IN_PROGRESS_WITH_ERROR_STATE</code>, or 
     * <code>IN_PROGRESS_INCOMPLETE_STATE</code>.
     *
     * @return The exception that caused the failure. The exception returned will be a
     *      RecordingFailedException.
     *
     *
     * @throws IllegalStateException if the recording request is not in the
     *      FAILED_STATE or INCOMPLETE_STATE or IN_PROGRESS_WITH_ERROR_STATE, 
     *      or IN_PROGRESS_INCOMPLETE_STATE.
     */
    public Exception getFailedException() throws IllegalStateException;

    /**
     * Returns the {@link SegmentedRecordedService} corresponding to the recording request.
     *
     * @return The recorded service associated with the recording request.
     * @throws IllegalStateException if the recording request is not in 
     * INCOMPLETE_STATE, IN_PROGRESS_STATE, IN_PROGRESS_INSUFFICIENT_SPACE_STATE,
     * IN_PROGRESS_WITH_ERROR_STATE, IN_PROGRESS_INCOMPLETE_STATE,
     * or COMPLETED_STATE.
     * @throws AccessDeniedException if the calling application is not permitted 
     * to perform this operation by RecordingRequest specific security attributes.
     */
    public SegmentedRecordedService getService() throws IllegalStateException,AccessDeniedException;


    /**
     * Gets detailed information about the deletion of the recorded service 
	 * corresponding to this recording request.
     *
     * @return The deletion details for this recording request. 
     *
     * @throws IllegalStateException if the recording request is not in the
     *      DELETED_STATE.
     */
    public DeletionDetails getDeletionDetails() throws IllegalStateException;

}

