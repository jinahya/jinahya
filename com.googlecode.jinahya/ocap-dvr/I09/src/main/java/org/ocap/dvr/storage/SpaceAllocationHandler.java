package org.ocap.dvr.storage;

import org.dvb.application.AppID;
import org.ocap.storage.LogicalStorageVolume;

/**
 * A class implementing this interface decides whether requests to allocate storage space
 * should be allowed or not.
 */
public interface SpaceAllocationHandler {

  /**
   * This method should be used by the implementation to allow the SpaceAllocationHandler to
   * grant a request to reserve space.
   *
   * @param volume The LogicalStorageVolume on which the reserved space is requested.
   * @param app The requesting application.
   * @param spaceRequested The new value of the reservation if the request is granted.
   *
   * @return the space granted.
   */
  public long allowReservation(LogicalStorageVolume volume, AppID app, long spaceRequested );

}
