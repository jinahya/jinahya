package org.ocap.storage;

import java.util.EventListener;

/**
 * This interface represents a listener that can be set to listen for high water
 * level reached in persistent storage indicated by the dvb.persistent.root
 * property for all applications.
 */
public interface AvailableStorageListener extends EventListener
{
    /**
     * Notifies the listener a high water mark has been reached in the 
     * available memory indicated by dvb.persistent.root and available to
     * all applications.  The high water mark was set as a parameter in a
     * call to the <code>StorageManager.addAvailableStorageListener</code>
     * method.
     */
    public void notifyHighWaterMarkReached();
}

