

package org.dvb.spi;

/**
  * Interface for all Xlet-bound providers.  An instance of an
  * Xlet-bound provider
  * provides its services for exactly one xlet:  The xlet with which
  * that provider is registered.
  * The classes of the provider are in the classloader
  * hierarchy of the xlet itself.  This leads to a very simple mechanism,
  * because there are no issues with instance sharing between the provider
  * and the xlet that uses the provider.  However, it has the disadvantage
  * that every xlet that needs the services provided must carry its own
  * copy of the provider.
  *
  * @see SystemBoundProvider
  * @since MHP 1.1.3
  **/

public interface XletBoundProvider extends Provider {

    /**
     * A valid provider shall return a non-null value from at least
     * one of the get...XletContext methods.
     * @return the xlet context of the xlet to which the provider is bound
     **/
    public javax.tv.xlet.XletContext getBoundXletContext();

    /**
     * A valid provider shall return a non-null value from at least
     * one of the get...XletContext methods.
     * @return the xlet context of the xlet to which the provider is bound
     **/
    /** OCAP ECN OCAP1.0-N-06.0973-6 adds the org.dvb.spi package but
     * specifically excludes the following method:
     *
     * public javax.microedition.xlet.XletContext getBoundPBPXletContext();
     *
     **/

}

