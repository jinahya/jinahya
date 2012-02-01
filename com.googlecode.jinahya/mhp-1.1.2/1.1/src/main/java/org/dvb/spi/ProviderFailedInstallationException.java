


package org.dvb.spi;

/**
 * Thrown when there is a problem installing a provider.
 *
 * @see org.dvb.spi.ProviderRegistry#registerSystemBound(org.dvb.spi.SystemBoundProvider)
 * @see org.dvb.spi.ProviderRegistry#registerXletBound(org.dvb.spi.XletBoundProvider)
 **/
public class ProviderFailedInstallationException extends Exception {

    public ProviderFailedInstallationException() { }

    public ProviderFailedInstallationException(String s) { }
}

