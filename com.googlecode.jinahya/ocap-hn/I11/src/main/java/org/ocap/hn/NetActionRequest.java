package org.ocap.hn;

/**
 * All asynchronous actions in the Home networking API return an NetActionRequest.
 * The NetActionRequest can be used a) to cancel any pending action or b) to identify
 * which Action got completed.
 * 
 * @see NetActionHandler 
 * @see NetActionEvent
 */
public interface NetActionRequest {


    /**
     * Cancels the Action associated with this ActionRequest. Returns false if 
     * the action can't be canceled.
     * 
     * @return false if action can't be canceled, otherwise returns true.
     */
    public boolean cancel();


    /**
     * Gets the progress of the action in percent (0.0 - 1.0).
     * If the progress of an action can't be determined, -1.0 shall be returned.
     * 
     * @return the progress of the action (0.0 - 1.0) or -1.0 if the progress can't
     *      be determined.
     *
     */
    public float getProgress();

    /**
     * Gets the current status of the requested action.
     * 
     * @return the current action status; see ACTION_* constants in
     *      <code>NetActionEvent</code> for possible return values.
     * 
     */
    public int getActionStatus();

    /**
     * Gets the error value when getActionStatus returns 
     * <code>NetActionEvent.ACTION_FAILED</code>. The error code returned will
     * be equivalent to the error code returned by 
     * {@link NetActionEvent#getError()} for the NetActionEvent associated
     * with the completion of this action request. If the action is not in error
     * or has not completed, this method SHALL return -1.  
     * 
     * @return The error value; -1 if no error, 
     */
    public int getError();
}
