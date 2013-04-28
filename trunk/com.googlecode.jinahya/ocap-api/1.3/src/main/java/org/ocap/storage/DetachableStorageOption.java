package org.ocap.storage;

import java.io.IOException;

/**
 * This interface represents an external device that can be detached.  The methods on this
 * interface allow a detachable device to be detached safely.  In addition, when a
 * detachable storage device is attached for the first time, its StorageProxy provides
 * a means to initialize the device.  If initialization is needed, the StorageProxy will
 * be in one of two states:
 * {@link StorageProxy#UNSUPPORTED_FORMAT UNSUPPORTED_FORMAT} or {@link
 * StorageProxy#UNINITIALIZED UNINITIALIZED}.  When the StorageProxy is in one of these two
 * states, the initialize method must be called before the device can be used.
 *
 **/

public interface DetachableStorageOption extends StorageOption
{
    /**
     * Determines whether the device associated with this storage proxy is ready to be
     * detached.
     *
     * @return Returns true when the device is currently ready to be detached, otherwise
     *      returns false.
     *
     **/
    public boolean isDetachable();

    /**
     * Makes the device safe to be detached.  Calling this method has extensive impact on
     * applications that may currently be using the associated storage device.
     * <ol><li>
     * Any in progress java.io operations that are not completed throw IOExceptions.
     * </li><li>
     * The corresponding storage proxy is either removed from the database or remains with
     * a status of {@link StorageProxy#OFFLINE OFFLINE}.  The latter indicates that the
     * device may be brought back online.  If it is removed from the database, attempts to
     * use the storage proxy result in an IOException.
     * </ol>
     * This call may block until the filesystem can be put into a consistent state.
     *
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("storage").
     *
     * @throws IOException if the system is unable to make the device safe to detach.
     **/
    public void makeDetachable() throws IOException;

    /**
     * Makes the device ready for use.  If a detachable device is connected and in the
     * {@link StorageProxy#OFFLINE OFFLINE} state, this method attempts to activate the
     * device and make it available.  For example, a device may be left in an OFFLINE state
     * after it has been made ready to detach, but not actually unplugged.  This method has
     * no effect if the device is already in the {@link StorageProxy#READY READY} state.
     *
     * @throws SecurityException if the calling application does not have
     *      MonitorAppPermission("storage").
     *
     * @throws IOException if the device was not in the READY or OFFLINE state when the
     *      method was called.
     **/
    public void makeReady() throws IOException;
}
