
package org.dvb.spi.selection;

import org.dvb.spi.SystemBoundProvider;
//import org.dvb.spi.si.simple.ProgramReference;
//import org.dvb.spi.si.simple.ServiceComponentDescription;
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.ServiceContextEvent;
import javax.tv.media.MediaSelectControl;
import javax.tv.media.MediaSelectEvent;
import javax.tv.locator.Locator;


/**
 * A provider of service selection.  For example, a SelectionProvider
 * can be installed in an always-running service-unbound xlet to
 * provide the ability to reach services that do not have standarized
 * SI signalling in an IPTV network.
 * <p>
 * The process for determining which Provider (if any) shall be used
 * to present a transport independent service is defined by the following 
 * steps in the order given.
 * <p>
 * 1) Consult the service list. If the service appears in the service
 * list then make an selection between the available sources of that
 * Service as defined by ServiceContext.select(Service).
 * <p>
 * 2) If there one Provider registered to support the locator scheme
 * of the locator for the Service, ask that Provider to present that
 * service. The service selection operation shall fail if the provider
 * cannot present it.
 * <p>
 * 3) If more than one Provider has registed as supporting the locator
 * scheme of the locator for the Service, offer the Service to each 
 * Provider in turn until either one of them does not fail or no more
 * Providers are available in which case the service selection operation
 * shall fail.
 * <p>
 * NOTE: If the performance penalty of polling multiple Providers
 * for the same locator scheme is undesirable, service providers / operators
 * using multiple Providers should ensure they use different Locator schemes.
 * <p>
 * Providers should select the transport dependent locators
 * returned such that there is no collision the transport dependent
 * locators returned by other Providers that may offer the same 
 * transport independent service. Where an MHP terminal is managed
 * by a service provider / operator, the service provider / operator
 * should enforce this if they permit more than one Provider to be
 * installed.
 * <p>
 * Where there is a collision and a transport dependent locator
 * can be accessed by more than one Provider, the selection of 
 * which to use is implementation dependent.
 * 
 * <p>A SelectionProvider may inform the implementation of the
 * actual location of a service by two mechanisms.
 * <ul>
 * <li>For services which are already present in the network,
 * the actual location can be passed in ServiceReference
 * objects. These can be provided by the getServiceList
 * method when a provider is first installed and later by
 * the SelectionProviderContext. For these services, the
 * implementation shall use this actual location to present
 * the service without calling the select method. A session
 * shall be created using the ServiceReference used to present
 * the service in order that the provider can be notified
 * when the service is no longer used. The session instance
 * should be created once the platform has started finding
 * the service (e.g. during tuning or after sending an IGMP
 * join request) in order to avoid delaying the presentation
 * of the service. 
 * <li>For services whose actual location is not passed
 * in a ServiceReference (e.g. services not already present
 * in the network), the implementation shall use the select
 * method to obtain the actual location.
 * </ul>
 * The mechanism by which the actual location of a service
 * is determined may change dynamically, e.g. as the set of
 * services available in the network changes.
 *
 * @see org.dvb.spi.Provider#getServiceProviderInterfaces()
 * @since MHP 1.1.3
 **/


public interface SelectionProvider extends SystemBoundProvider {

    /** 
     * Called by the platform to register its handler for events
     * originating in the provider. This method shall be called
     * after providerRegistered and before any other methods on
     * the provider are called. Only one handler can be registered
     * at any one time.
     * @param c the handler
     **/
    public void init(SelectionProviderContext c);

    /**
     * Give a list of the services provided by this provider.  
     * The services returned from this method shall be merged into the platform
     * service list as returned by javax.tv.service.SIManager.filterServices (null).
     * Where the transport independent identification of a service is equal to one
     * already in the service list then that transport independent service shall
     * acquire an additional transport dependent service.
     * Where the transport independent identification of a service is not equal to
     * one already in the service list, a new service shall be added to the service
     * list and SIChangeEvents generated to appropriate listeners.
     * <p>The list of services returned shall include both those
     * where the actual location is returned in the ServiceReference
     * and those where the actual location is to be returned from
     * a later call to the select method.
     * @return a list of ServiceReference instances.
     *
     * @see  org.dvb.spi.selection.SelectionProviderContext#serviceListChanged(ServiceReference[])
     **/
    public ServiceReference[] getServiceList();

    /**
     * Called by the platform to create a session to manage the presentation
     * of a service. A new session shall be created for each service to be
     * presented by a provider.
     * @param service the service whose presentation is managed through this session 
     * @return a session
     */
    public SelectionSession newSession(ServiceReference service);


    /**
     * Returns the list of locator schemes handled by this provider.
     * This list should not change over time; it is expected that platforms
     * will usually call this method exactly once, after installation of
     * a provider.  There is no method to unregister
     * a scheme other than unregistering the provider.
     **/
    public LocatorScheme[] getSupportedLocatorSchemes();



    /**
     * Called by the terminal to request service description
     * information from an SI source.
     * The output shall be returned in an array of the same size
     * and order as the input. If information is not available on
     * any service listed in the input then the corresponding
     * entry in the results array shall be null.
     *
     * @param services  References
     * identifying the services whose descriptions are requested
     *
     * @return an array of service descriptions
     */
    public ServiceDescription[] getServiceDescriptions( 
	    ServiceReference[] services);
}

