package org.ocap.storage;

import java.util.EventObject;

/**
 * Event sent to a {@link StorageManagerListener} registered with the {@link
 * StorageManager} that a {@link StorageProxy} was added, removed or changed
 * state.
 **/
public class StorageManagerEvent extends EventObject
{
    /**
     * A StorageProxy was added.
     **/
    public final static int STORAGE_PROXY_ADDED = 0;

    /**
     * A StorageProxy was removed.
     **/
    public final static int STORAGE_PROXY_REMOVED = 1;

    /**
     * A StorageProxy changed state.
     **/
    public final static int STORAGE_PROXY_CHANGED = 2;

    // Type of event generated.
    int eventType;

    /**
     * Constructs the event.
     *
     * @param proxy The <code>StorageProxy</code> that
     * caused the event.
     * @param eventType The type of event.
     **/
    public StorageManagerEvent(StorageProxy proxy, int eventType) {
	super(proxy);
	this.eventType = eventType;
    }

    /**
     * Returns the event type.  Possible values include STORAGE_PROXY_ADDED,
     * STORAGE_PROXY_REMOVED or STORAGE_PROXY_CHANGED.
     *
     * @return The event type.
     **/
    public int getEventType()
    {
	return eventType;
    }

    /**
     * Returns the <code>StorageProxy</code> that caused the event.
     *
     * @return The <code>StorageProxy</code> that caused the event.
     **/
    public StorageProxy getStorageProxy()
    {
	return (StorageProxy)super.getSource();
    }
}
