
package org.dvb.spi.selection;

import javax.tv.locator.Locator;

/**
 * A reference to a service that is reached with the mediation of a SelectionProvider.
 *
 * @see org.dvb.spi.selection.SelectionProvider
 * @since MHP 1.1.3
 **/
public class ServiceReference {

    /**
     * Create a ServiceReference for a service.
     * The two strings shall both be the external form of Locators,
     * for example a transport independent "dvb:" locator string
     * and provider specific locator string.
     *
     * @param transportIndependent The transport-independent locator of the service.
     * @param transportDependent The transport-dependent locator of the service.
     *
     **/
    public ServiceReference(String transportIndependent, String transportDependent) { }

    /**
     * Return the transport independent locator of the service
     * @return the transportIndependent string passed into the constructor.
     **/
    public String getServiceIdentifier() {
	return null;
    }

    /**
     * Gives the transport-dependent locator of the service
     * @return the trasnport dependent string passed into the constructor
     **/
    public Locator getLocator() {
	return null;
    }
}

