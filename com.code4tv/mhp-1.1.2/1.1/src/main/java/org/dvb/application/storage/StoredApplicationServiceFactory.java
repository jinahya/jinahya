package org.dvb.application.storage;

/**
  * This factory creates new Service objects
  * representing stand-alone stored application services.
  * Services thus created shall appear in the list of services maintained by the
  * SIManager until removed using <code>StoredApplicationService.remove</code> or some MHP
  * terminal specific mechanism. i.e. they shall be returned by <code>filterServices</code>
  * both when passed an instance of <code>ServiceTypeFilter</code> constructed with the type
  * <code>StoredApplicationServiceType.STORED_APPLICATION_SERVICE</code> and when passed null to
  * list all known services.
  *
  * @since MHP1.1.2
  */
public abstract class StoredApplicationServiceFactory {

	/**
	 * This constructor is provided for the use of implementations and specifications 
	 * which extend the present document. Applications shall not define sub-classes of 
 	 * this class. Implementations are not required to behave correctly if any such 
	 * application defined sub-classes are used.
	 */
	protected StoredApplicationServiceFactory() {
	}

    /**
     * Get the singleton instance of this class, or null if and only if
     * this MHP implementation does not support stand-alone stored
     * applications.
     */
    public static StoredApplicationServiceFactory getInstance() {
        return null;
    }

    /**
      * Creates a new stored application service.
      *
      * @return the stored application service created
      *
      * @param organisation_id the organisation_id of the organisation
      *                        to whom this service belongs to
      * @param service_id unique identifier for this service within the
      *                   organisation
      * @param serviceName a name for the service that can be displayed to
      *                    the end user to identify this service
      *
      * @throws ServiceAlreadyExistsException thrown if a stored application
      *                                      service with the same
      *                                      organisation_id and service_id
      *                                      already exists in the terminal
      * @throws java.lang.SecurityException thrown if the application
      * calling this method does not have an ApplicationStoragePermission
      * with action "createService" for the organisation_id passed in
      * as the parameter.
      */
    public abstract StoredApplicationService createStoredApplicationService(
            int organisation_id, int service_id, String serviceName)
            throws ServiceAlreadyExistsException;

}

