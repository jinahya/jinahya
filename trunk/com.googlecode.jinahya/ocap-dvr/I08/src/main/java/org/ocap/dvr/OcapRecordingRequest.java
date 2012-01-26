package org.ocap.dvr;

import org.ocap.shared.dvr.LeafRecordingRequest;
import org.ocap.shared.dvr.ParentRecordingRequest;
import org.ocap.shared.dvr.navigation.RecordingList;
import org.ocap.shared.dvr.AccessDeniedException;

/**
 * This interface represents a LeafRecordingRequest in OCAP.
 * <p>
 * When the implementation detects a schedule conflict, it either resolves
 * the conflict using the Application priority of the conflicting recordings,
 * or invokes the
 * {@link org.ocap.resource.ResourceContentionHandler} if one is set. The
 * resolution of the conflict by the implementation or the
 * ResourceContentionHandler will result in some of the overlapping
 * recordings to be pending without conflict and some to be pending
 * with conflict.
 */
public interface OcapRecordingRequest extends LeafRecordingRequest
{
    /**
     * This recording request is a test recording request. Actual recording
     * is not initiated for recording requests in this state.
     * RecordingRequests in this state do not transition to other states.
     * No events are generated when a recording request is added or deleted
     * in this state. A recording in this state is a leaf recording request.
     * Recordings in this states are the leaf recording requests
     * corresponding to invocation of the RecordingManager.record(..)
     * method with the priority value in OcapRecordingProperties set to
     * TEST_RECORDING.
     */
    public static final int TEST_STATE = 9;

    /**
     * This recording request was cancelled.  Transitioned to when the
     * cancel method completes successfully.
     */
    public static final int CANCELLED_STATE = 10;

    /**
     * Cancels a pending recording request.  The recording request will be not
     * be deleted from the database after the successful invocation of this
     * method.  Successful completion places this recording request in the
     * CANCELLED_STATE.
     *
     * Canceling a recording request may resolve one or more conflicts.  In
     * this case some pending recordings with conflicts would be changed to
     * pending without conflicts.
     *
     * @throws AccessDeniedException if the calling application is not
     *      permitted to perform this operation by RecordingRequest specific
     *      security attributes.
     * @throws SecurityException if the calling application does not have
     *      RecordingPermission("cancel",..) or RecordingPermission("*",..)
     * @throws IllegalStateException if the state of the recording is not in
     *      PENDING_STATE_NO_CONFLICT_STATE or PENDING_WITH_CONFLICT_STATE.
     */
public void cancel() throws AccessDeniedException;


    /**
     * Gets the estimated space, in bytes, required for the recording.
     *
     * @return Space required for the recording in bytes. This method
     *      returns zero if the recordings is in failed state.
     */
    public long getSpaceRequired();

    /**
     * Gets any other RecordingRequest that overlaps with the duration of this
     * recording request. This method will return null unless the recording
     * request is in the PENDING_WITH_CONFLICTS_STATE,
     * PENDING_NO_CONFLICTS_STATE, IN_PROGRESS_INSUFFICIENT_SPACE_STATE or
     * IN_PROGRESS_STATE. The returned list will contain only overlapping
     * recording requests for which the application has read access permission.
     * The RecordingList returned is only a copy of the list of overlapping
     * entries at the time of this method call. This list is not updated if
     * there are any changes. A new call to this method will be required to get
     * the updated list.
     *
     * @return a RecordingList
     */
    public RecordingList getOverlappingEntries();

    /**
     * Returns whether the destined <code>MediaStorageVolume</code> for this
     * recording is present and ready or not.  
     *
     * @return If the <code>MediaStorageVolume</code> destination of this
     *      recording request can be written to, assuming write permission,
     *      then this  method returns true, otherwise it returns
     *      false.  If the <code>getDestination</code> method returns null then
     *      the destination  <code>MediaStorageVolume</code> is a default volume
     *      on a default storage device as determined by the implementation.
     */
    public boolean isStorageReady();

    /**
     * Sets the parent for this recording request.  If the parent parameter is
     * null this leaf is orphaned from any previously set parent.  If the parent
     * parameter is null and this leaf does not have a parent, this method
     * does nothing and returns successfully.  If the parameter is not null and
     * the parent was already set by any method, this leaf is removed from the
     * previously set parent and added to the parent parameter. Unless otherwise
     * noted, the state of the previously set parent will not be affected.
     *
     * If, as a result of this method invocation, this
     * <code>OcapRecordingRequest</code> is removed from a
     * <code>ParentRecordingRequest</code> which is in the
     * COMPLETELY_RESOLVED_STATE, and which contains no other
     * <code>RecordingRequest</code>s, that <code>ParentRecordingRequest</code>
     * SHALL be transitioned to the PARTIALLY_RESOLVED_STATE.
     *
     * If, as a result of this method invocation, this
     * <code>OcapRecordingRequest</code>
     * is removed from a <code>ParentRecordingRequest</code> which is in the
     * CANCELLED_STATE and which contains no additional
     * <code>RecordingRequest</code>s,
     * that <code>ParentRecordingRequest</code> SHALL be deleted from the
     * recording database.
     *
     * @param parent The new parent of this leaf recording request or null if the
     *      leaf is to be orphaned.
     * @param resolutionParentState The state into which the parent recording
     *      parameter shall be transitioned to as a result of this method
     *      invocation. If the parent parameter in this method is null, this
     *      parameter is ignored.
     *
     * @throws SecurityException if the calling application does not have
     *      RecordingPermission("modify",..) or RecordingPermission("*",..).
     * @throws IllegalStateException if the parent parameter is in the
     *      CANCELLED_STATE.
     */
    public void setParent(ParentRecordingRequest parent, int resolutionParentState);

}
