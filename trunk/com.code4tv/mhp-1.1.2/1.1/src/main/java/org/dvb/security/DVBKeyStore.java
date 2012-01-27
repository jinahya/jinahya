package org.dvb.security;

import java.security.*;
import java.io.IOException;
import java.security.cert.CertificateException;

/**
 * Extends KeyStore to allow loading the keystore using protection parameters.
 * @since MHP 1.1.2
 */

public class DVBKeyStore extends java.security.KeyStore {

/**
 * Creates a KeyStore object of the given type, and encapsulates the given provider implementation (SPI object) in it.
 * @param keyStoreSpi the provider implementation.
 * @param provider the provider.
 * @param type the keystore type.
 */
protected DVBKeyStore(KeyStoreSpi keyStoreSpi, Provider provider, String type)
{
	super(keyStoreSpi,provider,type);
}

/**
 * Loads this keystore using the given protection parameters
 * @param p protection parameters to use
 * @throws    IllegalArgumentException if the parameter p is not recognized 
 * @throws  IOException if there is an I/O or format problem with the keystore data 
 * @throws   NoSuchAlgorithmException if the algorithm used to check the integrity of the keystore cannot be found 
 * @throws    CertificateException if any of the certificates in the keystore could not be loaded
 */
public final void load(KeyStoreProtectionParameters p) throws IOException, NoSuchAlgorithmException, CertificateException
{
}

}

