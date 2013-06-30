package org.ocap.hn;

/**
 * This class represents an event generated in response to an action.
 * NetActionEvent instances can only be created by the implementation.
 */
public class NetActionEvent extends java.util.EventObject
{
    /**
     * Action status for a completed action
     * @see #getActionStatus()
     */
    public static final int ACTION_COMPLETED = 0;

    /**
     * <code>ACTION_CANCELED</code> is returned by {@link #getActionStatus()}
     * when the action has been canceled.
     * @see #getActionStatus()
     */
    public static final int ACTION_CANCELED = ACTION_COMPLETED + 1;

    /**
     * <code>ACTION_FAILED</code>  is returned by {@link #getActionStatus()}
     * when the action has failed.
     * @see #getActionStatus()
     */
    public static final int ACTION_FAILED = ACTION_CANCELED + 1;

    /**
     * <code>ACTION_STATUS_NOT_AVAILABLE</code> is returned by
     * {@link #getActionStatus()} when the transaction has completed
     * successfully or failed sometime before this method was called and the
     * implementation is no longer maintaining a status for it.
     */
    public static final int ACTION_STATUS_NOT_AVAILABLE = ACTION_FAILED + 1;

    /**
     * <code>ACTION_IN_PROGRESS</code>  is returned by {@link #getActionStatus()}
     * when the action is currently on going.
     * @see #getActionStatus()
     */
    public static final int ACTION_IN_PROGRESS = ACTION_STATUS_NOT_AVAILABLE + 1;
                                                           

    /**
     * Two argument constructor.
     * 
     * @param request - NetActionRequest that instigated the response.
     * @param response - An object representing the response to the action and
     *                   which is specific to the action.
     * @param error - error code for this event if action failed
     * @param status - status of the associated net action
     *  
     */
    protected NetActionEvent(java.lang.Object request,
                             java.lang.Object response,
                             int error,
                             int status)
    {
        super(request);
    }

    /**
     * Returns the response of the Action. Object is dependent on the Action.
     * 
     * @return The response to an asynchronous action.
     */
    public Object getResponse()
    {
        return null;
    }

    /** 
     * Returns the ActionRequest which identifies the action instance.
     * 
     * @return the ActionRequest
     */
    public NetActionRequest getActionRequest()
    {
        return null;
    }

    /**
     * Returns the status of the requested action. 
     * 
     * @return the status of the action; for possible return values see
     *      ACTION_* constants in this class.
     *
     */
    public int getActionStatus()
    {
        return 0;
    }

    /**
     * Gets the error value when getActionStatus returns 
     * <code>NetActionEvent.ACTION_FAILED</code>.  If the action is not in
     * error this method SHALL return -1. Error code values are dependent
     * on the underlying network protocol error code values. 
     * 
     * @return The error value; -1 if no error.
     */
    public int getError()
    {
        return -1;
    }
}
