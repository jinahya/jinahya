package org.dvb.security.provider;

import org.dvb.spi.XletBoundProvider;
import org.dvb.security.pkcs11.DVBPKCS11Provider;

/**
 * This xlet-bound provider permits a cryptographic service provider
 * to be packaged and managed as part of the MHP provider framework
 * as supported through the org.dvb.spi package.
 * Xlet-bound providers shall not be visible to applications through
 * the methods of the java.security.Security class.
 */

public interface CryptographicServiceProviderProvider extends XletBoundProvider
{
	/**
	 * Obtain a DVBPKCS11Provider to be used by this Xlet
	 * @return a DVBPKCS11Provider
	 */
	public DVBPKCS11Provider getProvider();
}

