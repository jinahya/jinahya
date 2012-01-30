package org.ocap.storage;

import java.io.IOException;
import org.ocap.storage.ExtendedFileAccessPermissions;

/**
 * This interface represents a logical volume on a storage device.
 * Each {@link StorageProxy} corresponding to a storage device may
 * contain LogicalStorageVolumes.  A logical volume is a construct for
 * organizing files on a disk and corresponds to a directory subtree
 * that is treated as a whole for some purposes.
 *
 **/

public interface LogicalStorageVolume
{
    /**
     * Gets the absolute path of the volume.  This path must be unique across all
     * LogicalStorageVolume instances regardless of the StorageProxy they are contained
     * within.
     *
     * @return Absolute directory path of the volume.
     */
    public String getPath();

    /**
     * Gets the {@link StorageProxy} the volume is a part of.
     *
     * @return Containing storage proxy.
     */
    public StorageProxy getStorageProxy();

    /**
     * Gets the file access permissions of the logical volume.
     *
     * @return File access permissions of the volume.
     */
    public ExtendedFileAccessPermissions getFileAccessPermissions();

    /**
     * Sets the file access permissions of the volume.
     *
     * @param fap New file access permissions.
     *
     * @throws SecurityException if the caller is not the owner of the volume
     *      or does not have MonitorAppPermission("storage").
     */
    public void setFileAccessPermissions(ExtendedFileAccessPermissions fap);
}
