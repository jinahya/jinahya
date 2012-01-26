package org.ocap.dvr.event;

/**
 * This interface represents a listener an application can set in order
 * to listen for events to do with a stream of interest.
 */
public interface StreamChangeListener
{
        /**
     * The transport stream a session was opened for was lost.  Most likely
     * caused by a tune to a different transport stream.
     */
    public final int TRANSPORT_STREAM_LOST_REASON = 0;

    /**
     * The stream type was lost within the transport stream.  Indicates the
     * PMT changed and there is no longer a stream signaled with a stream
     * type the handler is interested in.
     */
    public final int STREAM_TYPE_LOST_REASON = 1;

    /**
     * Activity that caused the stream a session was opened for has stopped.
     * This could be caused by a background recording being stopped due to 
     * successful completion or premature termination.  If stream activity ends
     * due to tune away the implementation SHALL generate the
     * <code>TRANSPORT_STREAM_LOST_REASON</code> only.  If stream activity ends
     * for some other reason such as lack of storage the implementation SHALL
     * generate this reason.
     */
    public final int STREAM_ACTIVITY_ENDED_REASON = 2;


    /**
     * Notifies a change in the PIDs for the service containing the stream
     * type it is interested in. Changes which result in no such PIDs SHALL
     * result in the session being stopped.  The array returned contains all
     * PIDs in the service with the stream type of interest.
     * 
     * @param pids An array of new PID or PIDs with the stream type for
     *      which the application has registered interest.  The array SHALL be
     *      ordered from the lowest PID to the highest.
     */
    public void notifyPIDsChanged(int[] pids);

    /**
     * Notifies the presentation status has changed.
     * 
     * @param presenting When true this parameter indicates the service
     *      containing the stream type of interest is presenting to one
     *      or more outputs.  When false the stream is active as a
     *      background stream with no presentation, e.g. background
     *      recording.
     */
    public void notifyPresentationChanged(boolean presenting);

    /**
     * Notifies the handler the implementation had to stop a session.  The
     * implementation SHALL stop a session if and only if one of the reasons
     * described by the constants in this interface is encountered.  If the
     * {@link LightweightTriggerSession#store} method was called for
     * this session the artificial carousel is stored by this method.
     * 
     * @param reason The reason the session was stopped.
     */
    public void notifySessionStopped(int reason);
}
