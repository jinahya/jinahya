
package org.dvb.spi.selection;

/**
 * This class is used to represent a locator scheme that is supported
 * by a SelectionProvider.
 * @since MHP 1.1.3
 **/
public class LocatorScheme  {

    /**
     * Construct a LocatorScheme.
     *
     * @param scheme the name of the locator scheme which is handled,
     * not including the ":" character
     * @param providerFirst true if this provider shall handle the scheme before
     * any handler in the platform implementation, false if this provider
     * shall handle the scheme after any handler in the platform
     * implementation and only for locators not handled by the platform
     * implementation.
     */
    public LocatorScheme(String scheme, boolean providerFirst) {
    }

    /**
     * Returns the scheme.
     * @return scheme the name of the locator scheme which is handled,
     * not including the ":" character
     **/
    public String getScheme() { return null; }

    /**
     * Returns whether the provider is to handle the scheme before
     * any platform handler for the scheme.
     * @return true if this provider shall handle the scheme before
     * any handler in the platform implementation, false if this provider
     * shall handle the scheme after any handler in the platform
     * implementation and only for locators not handled by the platform
     * implementation.
     **/
    public boolean getProviderFirst() { return false; }

}

