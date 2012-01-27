package org.dvb.application.storage;

import org.dvb.application.AppID;

/**
 * Listener to receive reports on progress of application
 * storage operations.
 * When multiple applications are being stored, calls to this
 * listener shall be generated for each individual application.
 */
public interface ApplicationStorageListener
{
/**
 * Called at 1 second intervals after a storage operation starts
 * until it completes. The precise definition of completeness is
 * implementation dependent however it would typically reflect the
 * extent to which the data of the application has been downloaded.
 * In implementations where an application is first downloaded and
 * then stored, assuming the storage phase takes much less time than
 * the downloading phase, the storage phase can be ignored when
 * reporting completeness. If an application is (already) downloaded
 * and stored before the first call to this method becomes due,
 * this method may never be called.
 * @param id the application being stored
 * @param size the size in bytes of the application to be stored,
 * as calculated by adding the size attributes in the application
 * description file of the application
 * @param completion the extent to which the storage operation is
 * complete, 0 being not complete and 255 being almost complete.
 */
public void storageUpdate(AppID id, byte completion, int size );

/**
 * Called if a storage operation fails.
 * @param id the application whose storage failed
 * @param failureMode the reason for the failure as would be reported
 * by one of the checked exceptions thrown by the method
 * StoredApplicationService.store.
 */
public void storageFailed( AppID id, Exception failureMode);

/**
 * Called when a storage operation is successfully completed.
 * @param id the application which is now successfully stored
 */
public void storageCompleted(AppID id);
} 


