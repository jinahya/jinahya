package org.ocap.dvr.event;

    /**
     * This interface represents a handler that can register for interest in
     * specific stream types.  When notified of stream type activity of interest
     * this handler MAY create an artificial object carousel associated with the
     * stream and populate it with stream events that will be generated whenever
     * the stream is played back and encounters the JMF media time attached to one
     * of the stream events.
     */
    public interface LightweightTriggerHandler
    {

    /**
     * Notifies the handler when streams of the stream type for which it was
     * registered are signaled by the PMT for a program referenced by a service
     * context selection, recording, buffering request, or tuning operation.
     * 
     * @param session The object representing the session for the stream type
     *      of interest.
     */
    public abstract void notifyStreamType(LightweightTriggerSession session);
}
