package org.ocap.shared.dvr;

import org.ocap.shared.dvr.navigation.RecordingList;

/**
 * This interface represents information corresponding to a parent recording
 * request.  The recording request represented by this interface may have
 * one or more child recording requests.
 * <p>
 * A parent recording request may be in the unresolved state, partially
 * resolved state, completely resolved state, or the cancelled state.
 * <p>
 * A recording request would be in the unresolved state if the
 * implementation does not have enough information to process this
 * recording request. A recording request in this state may  transition
 * to partially resolved state, completely resolved state, or failed state.
 * <p>
 * A recording request would be in the partially resolved state if the
 * implementation has enough information to schedule some, but not all,
 * child recording requests corresponding to this recording request. This
 * would be the case of a recording request for a series where some episodes
 * for the series are scheduled. A recording request is in completely
 * resolved state if all its child recordings are known and scheduled.
 */
public interface ParentRecordingRequest extends RecordingRequest
{
    /**
     * The implementation does not have enough information to process this
     * recording request.
     */
    public static final int UNRESOLVED_STATE = 9;

    /**
     * All child recordings corresponding to this recording request have
     * been scheduled.  A recording request is in completely resolved state,
     * if the implementation has enough information to schedule all
     * recordings corresponding to the recording request.  A recording
     * request in completely resolved state would have one or more child
     * recordings.
     */
    public static final int COMPLETELY_RESOLVED_STATE = 10;

    /**
     * Some recordings corresponding to this recording request have
     * been scheduled.  A recording request is in partially resolved state,
     * if the implementation has enough information to schedule some, but
     * not all recordings corresponding to the recording request.  A
     * recording request in partially resolved state may have zero, one or
     * more child recordings.
     */
    public static final int PARTIALLY_RESOLVED_STATE = 11;

    /**
     * A recording request is in cancelled state, if an application has
     * successfully called the cancel method for this recording request,
     * but not all child recording request have been deleted.  A recording
     * request in this state shall be deleted by the implementation once
     * all child recording requests have been deleted.
     */
    public static final int CANCELLED_STATE = 13;

    /**
     * Cancels the parent recording request. The implementation shall 
     * also cancel all the child recording requests through calling 
     * their cancel() method.  If any of these calls throw an exception, 
     * the process shall continue with the next child.
     * No more child recordings will be scheduled
     * for this recording request or for any of its child recordings. The
     * recording request will be deleted from the database once all child
     * recording requests have been deleted.  Canceling a parent recording
     * request does not delete any child recordings that cannot be
     * cancelled (i.e. if a child recording request is not in a pending
     * state). At the successful completion of this method the recording
     * request would be deleted from the database or changed state to
     * CANCELLED_STATE.
     *
     * @throws SecurityException if the calling application does not have 
     * RecordingPermission("cancel",..) or RecordingPermission("*",..)
     * @throws AccessDeniedException if the calling application is not 
     * permitted to perform this operation by RecordingRequest specific 
     * security attributes.
     * @throws IllegalStateException if the recording request
     *      is not in UNRESOLVED_STATE, PARTIALLY_RESOLVED_STATE or
     *      COMPLETELY_RESOLVED_STATE.
     */
    public void cancel() throws IllegalStateException,AccessDeniedException;

	
    /**
     * Gets all the immediate child Recordings corresponding to this parent RecordingRequest.
     * For a recording request in completely resolved state this method
     * returns all children that are still maintained in the recording
     * manager database (i.e. children removed from the database by calling
     * the delete() or cancel() method will not be included in the list of
     * child recordings).  For a recording request in partially resolved
     * state this method only returns currently known children for series.
     *
     * @return The list of child Recordings corresponding to this
     *      Recording; null if there are no child recording requests in
     *      the RecordingManager database.
     * @throws IllegalStateException if the recording request is not in
     *      PARTIALLY_RESOLVED_STATE or COMPLETELY_RESOLVED_STATE.
     */
    public RecordingList getKnownChildren() throws IllegalStateException;
}

