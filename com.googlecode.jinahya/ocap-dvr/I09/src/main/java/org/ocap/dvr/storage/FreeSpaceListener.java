package org.ocap.dvr.storage;

import java.util.EventListener;

/**
 * This interface represents a listener that will be notified
 * when a media volume has reached a specified level of 
 * remaining free space.
 */
public interface FreeSpaceListener extends EventListener
{
    /**
     * Notifies the listener the remaining free space has
     * reached the level specified by
     * {@link MediaStorageVolume#addFreeSpaceListener}.
     */
    public void notifyFreeSpace();
}

