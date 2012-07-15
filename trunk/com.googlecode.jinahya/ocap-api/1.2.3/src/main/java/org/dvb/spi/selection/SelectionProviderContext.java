

package org.dvb.spi.selection;


/**
 * Platform implementations that wish to receive notifications from a
 * <code>SelectionProvider</code> register an instance that implements
 * this interface with the <code>SelectionProvider</code>.
 * @since MHP 1.1.3
 **/
public interface SelectionProviderContext {

    /**
     * Called by a source when the list of services changes.
     * The new list replaces any previous list.<p>
     * The services passed into this method shall be compared with the service list
     * returned from the call to getServiceList when this provider was first
     * registered. Where services are added, these shall be merged into the platform
     * service list as defined in the description of the getServiceList method.
     * Where services are removed, if the transport independent service is left with
     * no transport dependent services then it shall be removed from the platform
     * service list. In all cases where the platform service list changes,
     * SIChangeEvents shall be generated to appropriate listeners.
     *
     * @param serviceReferences The ServiceReference instances
     *		for the available services.  This array
     *		must not change after this method is invoked.
     **/
    public void serviceListChanged(ServiceReference[] serviceReferences);

    /**
     * Called by a source when service description information
     * is available to offer that information to the platform implementation.
     * The new list replaces any previous list.
     *
     * @param serviceReferences The ServiceReference instances
     *		for which descriptions are available.  This array
     *		must not change after this method is invoked.
     **/
    public void serviceDescriptionAvailable(ServiceReference[] serviceReferences);

     /**
      * Called by a source to update the details of a service
      * it supports. This includes changing the service between
      * one whose location is already known and one whose
      * location must be found when it is selected. It also includes
      * changing the location of a service whose location is
      * already known. ServiceReferences shall be matched using the
      * transport independent and transport dependent names.
      * @param service the new service reference
      * @throws IllegalArgumentException if a service with the
      * same service identifier and transport independent locator
      * has not been previously returned by this source to the
      * implementation
      */
    public void updateService( ServiceReference service );


}

