package org.ocap.dvr.event;

/**
 * This class represents a manager that can be used by a privileged
 * application to create an artificial object carousel containing
 * a DSMCCStreamEvent in the top level.  The DSMCCStreamEvent can
 * be populated by a privileged application.
 * 
 * NOTE this is an expanded version of the GEM lightweight binding of the
 * trigger API (GEM clause P.2.3.1 Lightweight binding of trigger API).
 */
public abstract class LightweightTriggerManager
{

    /**
     * Protected constructor not callable by applications.
     */
    protected LightweightTriggerManager()
    {
    }

    /**
     * Gets an instance of the manager.
     * 
     * @return An instance of the light-weight trigger manager.
     */
    public static LightweightTriggerManager getInstance()
    {
        return null;
    }

    /**
     * Registers a handler interested in services with streams listed in the
     * PMT with this stream type.
     * <p>
     * A separate notification of the handler SHALL be made for each service
     * selection (with or without timeshift enabled), recording, buffering
     * request, or tune that references a service containing streams of the given
     * stream type.
     * The provided <code>LightweightTriggerSession</code> SHALL reflect the relevant
     * <code>ServiceContext</code>, <code>RecordingRequest</code>,
     * <code>BufferingRequest</code>, or <code>NetworkInterface</code>.
     * 
     * @param handler Handler to register.
     * @param streamType a stream type as signaled in the PMT.
     * 
     * @throws IllegalArgumentException if streamType is not in the
     *	    range 0x0 to 0xFF.
     * @throws NullPointerException if handler is null.
     * @throws SecurityException if the calling application is not
     *      signed.
     */
    public abstract void registerHandler(LightweightTriggerHandler handler,
                                         short streamType);

    /**
     * Unregisters a handler that was previously registered by the
     * registerHandler method.
     * 
     * @param handler The handle to unregister.
     * 
     * @throws IllegalArgumentException if the handler was not previously
     *      registered.
     */
    public abstract void unregisterHandler(LightweightTriggerHandler handler);
}
