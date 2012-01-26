package org.ocap.storage;

/**
 * This interface represents a removable storage media bay that supports 
 * insertion or removal of storage media while power is applied.
 * Examples of removable storage media would be memory stick, CD, or DVD.  
 * When an instance of the appropriate storage media is inserted into or 
 * removed from the bay a
 * {@link StorageManagerEvent} SHALL be generated with an event type of
 * STORAGE_PROXY_CHANGED.
 */
public interface RemovableStorageOption extends StorageOption
{
    /**
     * Prepares the storage media to be physically ejected from the bay in
     * an implementation specific fashion, if applicable to the hardware.
     * If eject is not applicable to the storage device hardware this method
     * does nothing and returns successfully
     */
    public void eject();

    /**
     * Returns a presence indication for the removable storage.
     *
     * @return True if a removable storage media is present in the
     *      corresponding bay, otherwise returns false.
     */
    public boolean isPresent();
}
