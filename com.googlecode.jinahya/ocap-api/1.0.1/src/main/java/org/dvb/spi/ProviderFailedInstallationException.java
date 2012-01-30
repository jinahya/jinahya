


package org.dvb.spi;

/**
 * Thrown when there is a problem installing a provider.
 *
 * @see org.dvb.spi.ProviderRegistry#registerSystemBound(org.dvb.spi.SystemBoundProvider)
 * @see org.dvb.spi.ProviderRegistry#registerXletBound(org.dvb.spi.XletBoundProvider)
 * @since MHP 1.1.3
 **/
public class ProviderFailedInstallationException extends Exception {

	/**
	 * Constructs an exception with no reason.
	 */
    public ProviderFailedInstallationException() { }

	/**
	 * Constructs an exception with a reason.
	 * @param s the reason why the exception was thrown
	 */
    public ProviderFailedInstallationException(String s) { }
}

