/*
 * DVBTrustManagerFactory.java
 *
 * Created on 28 janvier 2005, 18:57
 */

package org.dvb.net.ssl;

import javax.net.ssl.*;
import java.security.Provider;
import org.dvb.security.*;
import java.security.InvalidAlgorithmParameterException;
/**
 *
 * This class is used to create a KeyManagerFactory initialized with an array of KeyStoreBuilder instances.
 * The inherited method getInstance shall return an instance of DVBTrustManagerFactory when the
 * provider is an instance of DVBPKCS11Provider.
 */
public class DVBTrustManagerFactory extends TrustManagerFactory {
    
    /** Creates a new instance of DVBTrustManagerFactory */
    protected DVBTrustManagerFactory(DVBTrustManagerFactorySpi factorySpi,
                                Provider provider,
                                String algorithm) 
    {
        super(factorySpi, provider, algorithm);
    }
    
     /**
      * This method is used to initialize the factory with an array of KeyStoreBuilder instances.
      * @param builders an array of KeyStoreBuilder instances.
      */ 
    public final void init(KeyStoreBuilder[] builders)
    {
        
    }
}

