
package org.dvb.application.storage;

import org.dvb.application.AppProxy;
import org.dvb.application.AppID;

/** 
  * Defines the information about a stored application service.
  * <p>
  * Stored application services can be created by applications
  * that have a permission to store applications for an 
  * organisation_id.
  * <p>
  * Stored application services are uniquely identified within 
  * the terminal by the combination of the organisation_id
  * and service_id. 
  *
  * @since MHP1.1
  */
public interface StoredApplicationService extends javax.tv.service.Service, ApplicationStorageController
{

    /** 
      * Return the organisation id of this stored application service
      *
      * @return the organisation id of this stored application service
      */
    public int getOrganisationId();

    /** 
      * Return the service id of this stored application service
      *
      * @return the service id of this stored application service
      */
    public int getServiceId();

    /**
      * Gets the locator for this stored application service. This locator
      * is opaque and platform specific. It shall be an instance of
      * {@link org.davic.net.Locator} or a subclass.  It is not required to
      * be  an instance of any other public locator class in the platform
      * (e.g. {@link org.davic.net.dvb.DvbLocator}).
      *
      * @return a locator for this stored application service
      */
    public javax.tv.locator.Locator getLocator();

    /** 
      * Returns the name of the stored application service as defined 
      * when the stored application service was created.
      *
      * @return the name of the stored application service
      */
    public java.lang.String getName();

    /**
      * Returns the service information format of this object. 
      * This shall always return <code>ServiceInformationType.UNKNOWN</code>.
      *
      * @return the service information format
      */
    public javax.tv.service.ServiceInformationType getServiceInformationType();


    /**
      * Returns the type of this service. For stored application 
      * services, this method shall always return
      * {@link org.dvb.application.storage.StoredApplicationServiceType#STORED_APPLICATION_SERVICE}.
      *
      * @return service type of this service
      */
    public javax.tv.service.ServiceType getServiceType();

    /**
      * Indicates whether the service represented by this 
      * Service is available on multiple transports. For stored
      * application services, this shall always return false.
      *
      * @return false
      */
    public boolean hasMultipleInstances();

    /**
      * Remove the whole stored application service 
      * from the terminal. Removal of the service results in the
      * removal of all the applications from within that service.
      * <p>If there are any applications installed in the stored
      * application service, then the application calling this method should be prepared
      * for the platform consulting the end user of the MHP terminal
      * for permission to remove the stored service.
      * <p>The platform shall not prompt the user for permission if
      * no applications are installed in the service.
      * <p>If the end user is asked and does not give permission to 
      * remove the service, none of the applications in the service shall be removed.

      * @throws SecurityException if the caller does not have an
      *         {@link org.dvb.application.storage.ApplicationStoragePermission}
      *         with action "deleteService" and an organisation_id which
      *         matches that of this service.  Also thrown if the caller does
      *         not have ApplicationStoragePermission with action
      *         "removeService" for the organisation IDs of every application
      *         installed in this service.
      * @throws UserRejectedInstallException If the user chose not to
      *         remove the applications in the service.
      */
    public void removeService()
        throws UserRejectedInstallException;

    /** 
      * This method retrieves additional information about the service. 
      * This shall result in failure with the
      * <code>SIRequestFailureType</code>
      * <code>DATA_UNAVAILABLE</code>.
      *
      * @param requestor The SIRequestor to be notified when this 
      *                  operation completes.
      * @return A SIRequest object identifying this request.
      */
    public javax.tv.service.SIRequest retrieveDetails(
        javax.tv.service.SIRequestor requestor);

  /**
    * Test whether this service is selectable.
    * StoredApplicationServices are selectable if they contain at
    * least one autostart application and are not selectable if they
    * do not contain any autostart applications. Applications which offer
    * the end-user a choice of services to select should not include
    * services which are not selectable in that list.
    * @return false if the service is not selectable otherwise true
    * @since MHP 1.1.3
    */
  public boolean isSelectable();

}


