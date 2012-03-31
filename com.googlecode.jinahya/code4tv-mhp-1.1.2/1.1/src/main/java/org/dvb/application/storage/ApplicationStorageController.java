
package org.dvb.application.storage;

import org.dvb.application.AppProxy;
import org.dvb.application.AppID;
import org.davic.net.Locator;

/** 
  * Defines the methods for storing, listing and removing
  * applications to and from a service.
  &
  * @since MHP1.1.3
  */
public interface ApplicationStorageController
{
    /**
      * <p>Installs an application into this stored service.</p>
      * <p>Applications should be prepared for the platform consulting 
      * the end user of the MHP terminal for the permission
      * to install the application</p>
      * <p>Note: This method is synchronous and will block until the
      * installation is either completed or fails.
      *
      * <p>Successfully adding an application to a stored service that
      * is currently being presented shall cause the terminal to behave
      * as if the application was added to the AIT - i.e. it will send
      * an {@link org.dvb.application.AppsDatabaseEvent#APP_ADDED} event
      * to registered
      * listeners, and if the <code>autoStart</code> parameter is
      * <code>true</code> then the application shall be started.</p>
      * 
      * <p>If the same version of the same application is already
      * installed in this stored service, then (if the caller has
      * sufficient permissions to install the application) this
      * method shall update the stored representation of the AIT
      * from the <code>autoStart</code> and <code>args</code>
      * parameters, and return without throwing an exception.
      * Terminals shall not store the application again.  Terminals
      * are not expected (but are allowed) to prompt the user for
      * permission in this case.  If the stored service is currently
      * being presented, and the autostart setting is changed, then:
      * <ul>
      * <li>an {@link org.dvb.application.AppsDatabaseEvent#APP_CHANGED} event shall
      * be sent as usual to registered listeners</li>
      * <li>If the autostart setting is changed to <code>true</code>,
      * then the application shall be started as usual.  (Note that
      * the opposite is not true:  setting autostart to
      * <code>false</code> shall not cause the application to be
      * killed if it is running).</li>
      * </ul>
      * Note that calling this method with a new value for the
      * <code>args</code> parameter shall not have any effect on an
      * existing instance of an application.
      * </p>
      *
      * <p>If a different version of the same application is already
      * installed in this stored service, then this method shall
      * install the new version of the application as normal.  (Note
      * that for the purposes of this method description, "new" and
      * "old" versions refer to the version specified by the AppProxy
      * passed to this method and the version currently installed in
      * the service, respectively.  They do not refer to numeric
      * comparison of version numbers). On success, the old version
      * shall be removed from the service.
      * On failure, the old version of the application shall remain
      * as it was before this method was called.  If the stored
      * service is currently being presented, then on success:
      * <ul>
      * <li>an {@link org.dvb.application.AppsDatabaseEvent#APP_CHANGED} event shall
      * be sent as usual to registered listeners</li>
      * <li>If the old version of the application is running (or
      * paused) then it shall be terminated as if it had been
      * signalled as KILL in the AIT.</li>
      * <li>If the new version is signalled as autostart,
      * then the new version shall be started as usual, once
      * the old version has terminated.</li>
      * </ul></p>
      *
      * @param app an AppProxy representing the application to be 
      *            installed
      * @param autoStart true, if the application
      *                  becomes an autostart application in the 
      *                  stored application service; false, if the
      *                  application becomes a normal present 
      *                  (non-autostart) application in the stored
      *                  application service.
      * @param args parameters to be available to the application when started.
      * Passing in either null or an array of size zero indicates no parameters
      * are to be available. These parameters shall be available to applications
      * when running as part of the stored application service using the
      * Xlet property "dvb.installer.parameters".
      *
      * @throws InvalidApplicationException thrown if the application
      *         does not include an application_storage_descriptor,
      *         or if the application is not identified as able to run
      *         stand-alone in it's application_storage_desciptor.
      * @throws InvalidDescriptionFileException thrown if the application
      *         description file is missing, invalid or otherwise not
      *         conformant to the specification
      * @throws NotEnoughResourcesException thrown if the MHP terminal does 
      *         not have enough resources, e.g. storage space, available 
      *         for the application
      * @throws UserRejectedInstallException thrown if the end user rejects 
      *         the installation
      * @throws ApplicationDownloadException thrown if the downloading of the
      *         application files was notsuccessful (e.g. a carousel error,
      *         a file in the application description file is missing in the
      *         carousel, if the application was removed from the AIT or from 
      *         its transport mechanism while installation is in progress, etc)
      *         or if the application failed authentication while being downloaded
      * @throws java.lang.SecurityException thrown if the application
      *         calling this method does not have an
      *         {@link org.dvb.application.storage.ApplicationStoragePermission}
      *         with action "storeService" for the organisation_id of the
      *         application to be stored, and an <code>ApplicationStoragePermission</code>
      *         with action "manageService" for the organisation_id of this
      *         service.
      *
      * @since MHP1.1.2
      */
    public void store(AppProxy app, boolean autoStart, String args[]) 
       throws InvalidApplicationException, UserRejectedInstallException,
              NotEnoughResourcesException, InvalidDescriptionFileException,
	      ApplicationDownloadException;

     /**
      * <p>Installs several applications into this stored service.</p>
      * <p>Applications should be prepared for the platform consulting
      * the end user of the MHP terminal for the permission
      * to install the applications.</p>
      * <p>Note: This method is synchronous and will block until the
      * installation is either completed or fails.
      * <p>
      * This method either succeeds or fails completely.  It will
      * never install some applications but not others.</p>
      * <p>For each specified application, if that application is already
      * installed in the service (whether the same version or a different
      * version, then the rules specified in the <code>store()</code>
      * method that stores a single application shall apply regarding
      * updating the application and/or the stored representation of the AIT
      * for that application.  Note that the preceeding statement that this
      * method either completely succeeds or completely fails still applies.
      * </p><p>
      * If this service is being presented, then on success the rules in
      * the {@link #store(org.dvb.application.AppProxy, boolean, String[]) store()}
      * method that stores a single application 
      * regarding sending <code>AppsDatabaseEvent</code>s and starting and killing
      * applications shall apply.
      * </p>
      *
      * @param apps an array of AppProxys representing the applications to
      *       be installed.  May not be <code>null</code> or zero-length.
      * @param autoStart An array that is the same length as <code>apps</code>
      *       where each element is <code>true</code>, if the corresponding
      *       application in <code>apps</code> becomes an autostart application
      *       in the stored application service; or <code>false</code>, if the
      *       application becomes a normal present (non-autostart) application.
      *       May not be <code>null</code>.
      * @param args parameters to be available to the applications when started.
      *       May be <code>null</code>, indicating all applications take no
      *       parameters.  Otherwise it is an array that is the same length as
      *       <code>apps</code>, where each element is the arguments for the
      *       corresponding application in <code>apps</code>.  If the element
      *       is either null or a subarray of size zero, that indicates no
      *       parameters are to be available.  These parameters shall be
      *       available to applications when running as part of the stored
      *       application service using the Xlet property
      *       "dvb.installer.parameters".
      *
      * @throws java.lang.IllegalArgumentException If the arrays passed to
      *         this method have different lengths, or if they have length
      *         zero.
      * @throws InvalidApplicationException thrown if any of the applications
      *         do not include an application_storage_descriptor, or if
      *         any of the applications are not identified as able to run
      *         stand-alone in their application_storage_desciptor.
      * @throws InvalidDescriptionFileException thrown if one of the application
      *         description files is missing, invalid or otherwise not
      *         conformant to the specification
      * @throws NotEnoughResourcesException thrown if the MHP terminal does
      *         not have enough resources, e.g. storage space, available
      *         for the applications
      * @throws UserRejectedInstallException thrown if the end user rejects
      *         the installation
      * @throws ApplicationDownloadException thrown if the downloading of the
      *         application files was notsuccessful (e.g. a carousel error,
      *         a file in the application description file is missing in the
      *         carousel, if the application was removed from the AIT or from its 
      *         transport mechanism while installation is in progress, etc)
      *         or if the application failed authentication while being downloaded
      * @throws java.lang.SecurityException thrown if the application
      *         calling this method does not have
      *         {@link org.dvb.application.storage.ApplicationStoragePermission}s
      *         with action "storeService" for the organisation_ids of all the
      *         applications to be stored, and an <code>ApplicationStoragePermission<code>
      *         with action "manageService" for the organisation_id of this
      *         service.
      *
      * @since MHP1.1.2
      */
   public void store(AppProxy[] apps, boolean[] autoStart, String[][] args)
      throws InvalidApplicationException, UserRejectedInstallException,
            NotEnoughResourcesException, InvalidDescriptionFileException,
           ApplicationDownloadException;

     /**
      * <p>Installs several applications into this stored service.</p>
      * <p>Applications should be prepared for the platform consulting
      * the end user of the MHP terminal for the permission
      * to install the applications.</p>
      * <p>This method is asynchronous with completion reported via the ApplicationStorageListener
      * <p>
      * This method either succeeds or fails completely.  It will
      * never install some applications but not others.</p>
      * <p>For each specified application, if that application is already
      * installed in the service (whether the same version or a different
      * version, then the rules specified in the <code>store()</code>
      * method that stores a single application shall apply regarding
      * updating the application and/or the stored representation of the AIT
      * for that application.  Note that the preceeding statement that this
      * method either completely succeeds or completely fails still applies.
      * </p><p>
      * If this service is being presented, then on success the rules in
      * the {@link #store(org.dvb.application.AppProxy, boolean, String[]) store()}
      * method that stores a single application 
      * regarding sending <code>AppsDatabaseEvent</code>s and starting and killing
      * applications shall apply.
      * </p>
      *
      * @param apps an array of AppProxys representing the applications to
      *       be installed.  May not be <code>null</code> or zero-length.
      * @param autoStart An array that is the same length as <code>apps</code>
      *       where each element is <code>true</code>, if the corresponding
      *       application in <code>apps</code> becomes an autostart application
      *       in the stored application service; or <code>false</code>, if the
      *       application becomes a normal present (non-autostart) application.
      *       May not be <code>null</code>.
      * @param args parameters to be available to the applications when started.
      *       May be <code>null</code>, indicating all applications take no
      *       parameters.  Otherwise it is an array that is the same length as
      *       <code>apps</code>, where each element is the arguments for the
      *       corresponding application in <code>apps</code>.  If the element
      *       is either null or a subarray of size zero, that indicates no
      *       parameters are to be available.  These parameters shall be
      *       available to applications when running as part of the stored
      *       application service using the Xlet property
      *       "dvb.installer.parameters".
      * @param listener the listener to be notified of the success or failure
      *       of this installation.
      *
      * @throws java.lang.IllegalArgumentException If the arrays passed to
      *         this method have different lengths, or if they have length
      *         zero.
      * @throws java.lang.SecurityException thrown if the application
      *         calling this method does not have
      *         {@link org.dvb.application.storage.ApplicationStoragePermission}s
      *         with action "storeService" for the organisation_ids of all the
      *         applications to be stored, and an <code>ApplicationStoragePermission<code>
      *         with action "manageService" for the organisation_id of this
      *         service.
      *
      * @since MHP1.1.3
      */
   public void store(AppProxy[] apps, boolean[] autoStart, String[][] args,
	ApplicationStorageListener listener);

     /**
      * <p>Installs several applications into this stored service.
      * The applications are from a service which need not be selected.</p>
      * <p>Applications should be prepared for the platform consulting
      * the end user of the MHP terminal for the permission
      * to install the applications.</p>
      * <p>This method is asynchronous with completion reported via the ApplicationStorageListener
      * <p>
      * This method either succeeds or fails completely.  It will
      * never install some applications but not others.</p>
      * <p>For each specified application, if that application is already
      * installed in the service (whether the same version or a different
      * version, then the rules specified in the <code>store()</code>
      * method that stores a single application shall apply regarding
      * updating the application and/or the stored representation of the AIT
      * for that application.  Note that the preceeding statement that this
      * method either completely succeeds or completely fails still applies.
      * </p><p>
      * If this service is being presented, then on success the rules in
      * the {@link #store(org.dvb.application.AppProxy, boolean, String[]) store()}
      * method that stores a single application 
      * regarding sending <code>AppsDatabaseEvent</code>s and starting and killing
      * applications shall apply.
      * </p>
      *
      * @param locator the locator of the service from which the applications
      *       are to be stored
      * @param apps an array of application ids identifying the applications
      *       to be stored
      * @param autoStart An array that is the same length as <code>apps</code>
      *       where each element is <code>true</code>, if the corresponding
      *       application in <code>apps</code> becomes an autostart application
      *       in the stored application service; or <code>false</code>, if the
      *       application becomes a normal present (non-autostart) application.
      *       May not be <code>null</code>.
      * @param args parameters to be available to the applications when started.
      *       May be <code>null</code>, indicating all applications take no
      *       parameters.  Otherwise it is an array that is the same length as
      *       <code>apps</code>, where each element is the arguments for the
      *       corresponding application in <code>apps</code>.  If the element
      *       is either null or a subarray of size zero, that indicates no
      *       parameters are to be available.  These parameters shall be
      *       available to applications when running as part of the stored
      *       application service using the Xlet property
      *       "dvb.installer.parameters".
      * @param listener the listener to be notified of the success or failure
      *       of this installation.
      *
      * @throws java.lang.IllegalArgumentException If the arrays passed to
      *         this method have different lengths, or if they have length
      *         zero.
      * @throws java.lang.SecurityException thrown if the application
      *         calling this method does not have
      *         {@link org.dvb.application.storage.ApplicationStoragePermission}s
      *         with action "storeService" for the organisation_ids of all the
      *         applications to be stored, and an <code>ApplicationStoragePermission<code>
      *         with action "manageService" for the organisation_id of this
      *         service.
      *
      * @since MHP1.1.3
      */
   public void store(Locator locator, AppID[] apps, boolean[] autoStart, String[][] args,
	ApplicationStorageListener listener);

    /**
      * <p>Removes a stored application from this service.
      * <p>Applications should be prepared for the platform consulting 
      * the end user of the MHP terminal for the permission
      * to remove the application</p>
      * <p>If the application identified by the <code>AppID</code> passed in as
      * a parameter is not installed in this service, and the caller
      * has the permissions that would be needed to remove the
      * application if it was installed, the method
      * shall fail silently.</p>
      * <p>Successfully removing an application from a stored service
      * that is currently being presented shall cause the terminal to
      * send an <code>AppsDatabaseEvent.APP_DELETED</code> event
      * to registered listeners, and if the application is Loaded,
      * Active or Paused then it shall be destroyed as if it had been
      * signalled as KILL in the AIT.  (Note that there
      * is no special-case for if an application removes itself from
      * a stored service - this is not an error and shall be handled
      * normally).</p>
      * 
      * @param appId <code>AppID</code> of the application to be removed
      *
      * @throws UserRejectedInstallException If the user chose not to
      *         remove the application.
      * @throws java.lang.SecurityException Thrown if the application
      *         calling this method does not have an
      *         {@link org.dvb.application.storage.ApplicationStoragePermission}
      *         with action "removeService" for the organisation_id of the
      *         application to be removed, and an
      *         <code>ApplicationStoragePermission</code> with action
      *         "manageService" for the organisation_id of this service.
      *
      * @since MHP1.1.2
      */
    public void remove(AppID appId)
        throws UserRejectedInstallException;

    /**
     * <p>Removes multiple stored applications from this service.
     * <p>Applications should be prepared for the platform consulting
     * the end user of the MHP terminal for permission
     * to remove the applications</p>
     * <p>If an application identified by the <code>AppID</code>s passed in as
     * a parameter is not installed in this service, and the caller
     * has the permissions that would be needed to remove the
     * application if it was installed, then this method shall
     * ignore that <code>AppID</code>.</p>
     * <p>This method either succeeds or fails completely.  It will
     * never remove some installed applications but not other
     * installed applications.</p>
     * <p>Successfully removing application(s) from a stored service
     * that is currently being presented shall cause the terminal to
     * send an <code>AppsDatabaseEvent.APP_DELETED</code> event for
     * each application to registered listeners, and if any of the
     * removed applications are Loaded, Active or Paused then they
     * shall be destroyed as if they had been signalled as KILL
     * in the AIT.  (Note that there is no special-case for if an
     * application removes itself from a stored service - this is
     * not an error and shall be handled normally).</p>
     *
     * @param appIds <code>AppID</code>s of the applications to be removed
     *
     * @throws java.lang.IllegalArgumentException If the array passed to
     *         this method has length zero.
     * @throws java.lang.SecurityException thrown if the application
     *         calling this method does not have
     *         {@link org.dvb.application.storage.ApplicationStoragePermission}s
     *         with action "removeService" for the organisation_ids of all the
     *         applications to be removed, and an
     *         <code>ApplicationStoragePermission</code> with
     *         action "manageService" for the organisation_id of this service.
     *
     * @throws UserRejectedInstallException If the user chose not to remove the  application.
     * @since MHP1.1.2
     */
   public void remove(AppID appIds[]) throws UserRejectedInstallException;

    /**
      * Lists the <code>AppID</code>s of the applications that are stored
      * within this service.
      *
      * @return an array of <code>AppID</code> object representing the stored
      *         application
      *
      * @throws java.lang.SecurityException Thrown if the application
      *         calling this method does not have an
      *         {@link org.dvb.application.storage.ApplicationStoragePermission}
      *         with action "manageService" for the organisation_id of this service.
      *
      * @since MHP1.1.2
      */
    public AppID[] getStoredAppIDs();

    /**
      * Return the version number of the stored application whose
      * <code>AppID</code> is given as a parameter.
      * 
      * @param appId the <code>AppID</code> of the application whose version is queried
      * @return the version number of the stored application,
      *         returns -1 if the application given as a parameter
      *         is not stored
      * @throws java.lang.SecurityException Thrown if the application
      *         calling this method does not have an
      *         {@link org.dvb.application.storage.ApplicationStoragePermission}
      *         with action "manageService" for the organisation_id of this service.
      *
      * @since MHP1.1.2
      */
    public int getVersionNumber(AppID appId);
}


