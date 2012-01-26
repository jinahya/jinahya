
package org.dvb.spi.selection;

import javax.tv.locator.Locator;

/**
 * A reference to a service that is reached with the mediation of a SelectionProvider.
 * This class represents services whose location is already known
 * to the provider without needing to ask a server or head-end.
 *
 * @see org.dvb.spi.selection.SelectionProvider
 * @since MHP 1.1.3
 **/
public class KnownServiceReference extends ServiceReference {

    /**
     * Create a ServiceReference for the given service identified
     * by the combination of a transport independent and transport
     * dependent identifiers.
     *
     * @param transportIndependent The transport-independent locator of the service.
     * @param transportDependent The transport-dependent locator of the service.
     * @param actualLocation     The actual location of the service.
     **/
    public KnownServiceReference(String transportIndependent, String transportDependent,
	Locator actualLocation) 
    { 
	super(transportIndependent,transportDependent);
    }

    /**
     * @return the serviceIdentifier string passed into the constructor.
     **/
    public String getServiceIdentifier() {
	return null;
    }

    /**
     * Gives the transport-dependent locator that will be used to represent 
     * this service through the Java TV APIs, both in this xlet and in
     * others.
     **/
    public Locator getLocator() {
	return null;
    }

    /**
     * Return the actualLocation as provided when this ServiceReference
     * was constructed.
     * @return a Locator
     */
    public Locator getActualLocation() { return null;  }
}

