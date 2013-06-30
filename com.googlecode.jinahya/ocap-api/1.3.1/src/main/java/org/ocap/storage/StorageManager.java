package org.ocap.storage;

import org.ocap.storage.StorageProxy;
import org.ocap.storage.StorageManagerListener;

/**
 * This class represents the storage manager which keeps track of the storage
 * devices attached to the system.
 **/
public abstract class StorageManager
{
    /**
     * Protected default constructor.
     **/
    protected StorageManager()
    {
    }

    /**
     * Gets the singleton instance of the storage manager.  The singleton MAY be
     * implemented using application or implementation scope.
     *
     * @return The storage manager.
     **/
    public static StorageManager getInstance()
    {
        return null;
    }

    /**
     * Gets the set of {@link StorageProxy} instances representing all
     * of the currently attached or embedded storage devices.
     *
     * @return An array of StorageProxy objects. If no application 
     * accessible storage proxies are available, returns a 0 length array.
     **/
    public abstract StorageProxy [] getStorageProxies();

    /**
     * Adds a listener to receive StorageManagerEvents when a storage proxy
     * is added, removed or changes state.
     *
     * @param listener The storage manager listener to be added.
     *
     * @throws IllegalArgumentException if the listener parameter is null.
     **/
    public abstract void addStorageManagerListener(StorageManagerListener listener);

    /**
     * Removes a listener so that it no longer receives
     * StorageManagerEvents when storage proxies change.
     * This method has no effect if the given listener had not been added.
     *
     * @param listener The storage manager listener to be removed.
     *
     * @throws IllegalArgumentException if the listener parameter is null.
     **/
    public abstract void removeStorageManagerListener(StorageManagerListener listener);

    /**
     * Gets the total amount of persistent storage under the location
     * indicated by the dvb.persistent.root property and that is usable
     * by all OCAP-J applications.  This value SHALL remain constant.
     * 
     * @return Amount of total persistent storage in bytes.
     */
    public abstract long getTotalPersistentStorage();

    /**
     * Gets the available amount of persistent storage under the location
     * indicated by the dvb.persistent.root property that is available to
     * all OCAP-J applications.  The value returned by this method can be
     * incorrect as soon as this method returns and SHOULD be interpreted by
     * applications as an approximation.
     * 
     * @return Amount of available persistent storage in bytes.
     */
    public abstract long getAvailablePersistentStorage();

    /**
     * Adds a listener for high water mark reached in available persistent
     * storage indicated by the dvb.persistent.root property.  This is a 
     * system wide indication.  Listeners are informed when a percentage of
     * the total persistent storage has been allocated for application use.
     * Listeners are only informed when the high water mark is reached or
     * exceeded.
     * 
     * @param listener The listener to add.
     * @param highWaterMark Percentage of the available persistent storage
     *      remaining when the listener is to be informed.  For instance,
     *      if the total available persistent storage is 1MB and the high
     *      water mark is 75 then high water listeners will be informed when
     *      750KB have been allocated for application use.
     * 
     * @throws IllegalArgumentException if the listener parameter could not be
     *      added or is null.
     */
    public abstract void addAvailableStorageListener(
                                        AvailableStorageListener listener,
                                        int highWaterMark);

    /**
     * Removes an available storage listener that was registered using the
     * <code>addAvailableStorageListener</code> method.  If the parameter
     * is not currently registered this method does nothing successfully.
     * 
     * @param listener The listener to remove.
     * 
     * @throws IllegalArgumentException if the parameter is null.
     */
    public abstract void removeAvailableStorageListener(
                                        AvailableStorageListener listener);
}
