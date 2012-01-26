package org.ocap.storage;

import java.util.EventListener;

/**
 * This interface represents a listener for changes to the set of StorageProxies
 * ({@link StorageProxy}).  Each listener is notified when a storage proxy is added,
 * removed or changes state.
 */
public interface StorageManagerListener extends EventListener
{

    /**
     * The implementation calls this method to inform the listener when a storage proxy was
     * added, removed or changes state.
     * changed.
     *
     * @param sme Event indicating the change.
     *
     * @see StorageManagerEvent
     */
    public void notifyChange(StorageManagerEvent sme);
}
