package org.dvb.application.storage;

import org.dvb.application.AppProxy;
import org.dvb.application.AppID;

/**
 * A cache to store applications so that they may be started
 * faster when signalled in broadcast.
 * <p>
 * Applications stored via this mechanism cannot be started
 * directly, only via an AIT entry in a broadcast service.
 * <p>
 * Note that applications are globally uniquely identified by a
 * combination of organisation ID, application ID, and version number.
 * Application authors should ensure that their storable applications
 * are consistently broadcast.
 * <p>
 * Each instance of ApplicationCache can store only a single
 * version of an application - i.e. within an <code>ApplicationCache</code>,
 * an organisation ID and application ID pair can uniquely
 * identify an application.
 * Successfully storing a different version of a cached application than 
 * the current one in an ApplicationCache shall result in the current one 
 * being removed from that ApplicationCache instance. If the call to 
 * store fails then the current version shall not be removed.
 * <p>
 * A single MHP terminal will support many instances of <code>ApplicationCache</code>.
 * These shall share a single underlying application store, and that
 * underlying store shall use reference-counting or similar techniques to
 * allow several stored services and <code>ApplicationCache</code> instances to contain
 * the same application without wasting storage space by storing the same
 * application multiple times.
 * Space permitting, the underlying application store shall support storing
 * several different versions of a single application (i.e. same organisation
 * ID and application ID but different version numbers) where these are
 * stored via different <code>ApplicationCache</code> instances or form part of different
 * stored services.
 * The underlying application store shall manage the removal of versions of 
 * stored applications which are not used in any stored services or application caches.
 * <p>
 * If an application is already cached in one
 * <code>ApplicationCache</code>, storing the same version of the same application in a
 * different <code>ApplicationCache</code> shall not fail due to insufficient disk space,
 * and if all files in the ADF are labelled as critical, it shall not fail
 * due to inability to download files listed in the ADF and shall complete
 * quickly (without waiting for the files listed in the ADF to be broadcast).
 * <p>
 *
 * @since MHP1.1.2
 */
public abstract class ApplicationCache {

   /**
    * Interoperable applications shall not call this constructor.
    *
    * @since MHP1.1.2
    */
   protected ApplicationCache(){}

   /**
    * Get the default cache that this application can use.
    * Note that in MHP 1.1, applications cannot access any cache
    * other than the default one.
    * <p>
    * The object returned depends on the organisation_id of the
    * calling application.  Applications with the same organisation_id
    * shall recieve the same <code>ApplicationCache</code>.  Applications
    * with different organisation_id shall recieve different, independent
    * instances of <code>ApplicationCache</code>.   (I.e. if one application stores
    * an application or removes a stored application, that operation shall
    * be visible to another application via the methods on this class if
    * and only if the other application has the same organisation_id as
    * the first application).
    *
    * @return An <code>ApplicationCache</code> instance.
    *
    * @throws java.lang.SecurityException  Thrown if the application
    *         calling this method does not have an
    *         {@link org.dvb.application.storage.ApplicationStoragePermission}
    *         with action "manageCache" for it's own organisation_id.
    *
    * @since MHP1.1.2
    */
   public static ApplicationCache getDefaultCache() {
      return null;
   }

   /**
    * <p>Store an application in the MHP terminal. </p>
    * <p>MHP terminals may prompt the user for permission to free up 
    * resources if and only if all of the following conditions hold:
    * <ul>
    * <li><code>canPrompt</code> is true
    * <li>the caller has the necessary permissions (i.e. the
    *    <code>SecurityException</code> will not be thrown)</li>
    * <li>the application is valid (i.e. the
    *    <code>InvalidApplicationException</code> will not be
    *    thrown)</li>
    * <li>the application description file is valid (i.e. the
    *    <code>InvalidDescriptionFileException</code> will not be
    *    thrown)</li>
    * <li>the MHP terminal determines that there are insufficient
    *    resources to store the application, and it cannot or
    *    will not silently free up enough resources (i.e. the
    *    <code>NotEnoughResourcesException</code> would be thrown)</li>
    * <li>the MHP terminal determines that it could free up
    *    sufficient resources to store the application (i.e. to
    *    ensure the <code>NotEnoughResourcesException</code> would
    *    not be thrown) if it had the user's consent</li>
    * </ul>
    * Prompting the end-user for permission is not required however 
    * applications setting canPrompt to true  should be prepared for 
    * the possibility of it happening. Note that if the user decides 
    * not to allow the terminal to free up resources, the 
    * NotEnoughResourcesException will be thrown (as if the terminal 
    * had not asked the user at all).
    *
    * <p>Note: This method is synchronous and will block until the
    * storing is either completed or fails.</p>
    *
    * <p>Storing (the same version of) an application that is already stored
    * elsewhere (e.g. in another ApplicationCache, or in a
    * StoredApplicationService) shall use the already-stored files.</p>
    *
    * @param app an AppProxy representing the application to be
    *            installed
    * @param canPrompt If <code>true</code>, the terminal is allowed
    *       to prompt the user for permission to free up space in order
    *       to store this application.  If <code>false</code>, the
    *       terminal shall not prompt the user for permission to cache
    *       this application.
    *
    * @throws InvalidApplicationException thrown if the specified
    *         application is not signalled as capable of being stored,
    *         or if the specified application is not signalled as part
    *         of the same service as the calling application.
    * @throws NotEnoughResourcesException thrown if the MHP terminal does
    *         not have enough resources, e.g. storage space, available
    *         for the application
    * @throws InvalidDescriptionFileException thrown if the application 
    *         description file is missing, invalid or otherwise not conformant to the specification
    * @throws ApplicationDownloadException thrown if the downloading of the
    *         application files was not successful (e.g. a carousel error,
    *         a file in the application description file is missing in the
    *         carousel, if the application was removed from the AIT or from its 
    *         transport mechanism while installation is in progress, etc).
    * @throws java.lang.SecurityException  Thrown if the application
    *         calling this method does not have an
    *         {@link org.dvb.application.storage.ApplicationStoragePermission}
    *         with action "storeCache" or "*" for the organisation_id of
    *         the application to be stored.
    *
    * @since MHP1.1.2
    */
   public abstract void store(AppProxy app, boolean canPrompt)
      throws InvalidApplicationException,
           NotEnoughResourcesException, InvalidDescriptionFileException,
           ApplicationDownloadException;


   /**
    * <p>Requests the MHP terminal to initiate the removal of
    * an application stored in the MHP terminal from this cache.
    * <p>The platform is not expected to consult the end user of
    * the MHP terminal for the permission to remove the application.</p>
    * <p>This specification does not prevent a terminal keeping an
    * application in cache even after all references to it have been
    * removed via this API.</p>
    * <p>If the application identified by the AppID passed in as
    * a parameter is not installed in this cache, the method
    * shall fail silently.
    * <p>If the application is already stored elsewhere (e.g.
    * in another ApplicationCache, or in a StoredApplicationService)
    * then those caches and stored services shall not be affected
    * by calling this method.</p>
    *
    * @param appId <code>AppID</code> of the application to be removed
    *
    * @throws java.lang.SecurityException thrown if the application
    *         calling this method does not have an
    *         {@link org.dvb.application.storage.ApplicationStoragePermission}
    *         with action "removeCache" for the organisation_id of the
    *         application to be removed and an ApplicationStoragePermission 
    *         with action "manageCache"
    *
    * @since MHP1.1.2
    */
   public abstract void remove(AppID appId);


   /**
    * Lists the <code>AppID</code>s of the applications that are stored
    * within this cache.
    *
    * @return an array of <code>AppID</code> objects representing the stored
    *         applications
    *
    * @since MHP1.1.2
    */
   public abstract AppID[] getStoredAppIDs();


   /**
    * Return the version number of the stored application whose
    * <code>AppID</code> is given as a parameter.
    *
    * @param appId the <code>AppID</code> of the application whose version is queried
    * @return the version number of the stored application,
    *         returns -1 if the application given as a parameter
    *         is not stored as part of this cache.
    *
    * @since MHP1.1.2
    */
   public abstract int getVersionNumber(AppID appId);
}


