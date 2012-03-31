/*
 * DVBKeyManagerFactory.java
 *
 * Created on 28 janvier 2005, 19:48
 */

package org.dvb.net.ssl;

import javax.net.ssl.*;
import java.security.Provider;
import org.dvb.security.*;
/**
 *
 * This class is used to create a KeyManagerFactory initialized with an array of KeyStoreBuilder instances.
 * The (inherited) method getInstance shall return an instance of DVBKeyManagerFactory where provider 
 * is an instance of DVBPKCS11Provider.
 */
public class DVBKeyManagerFactory extends KeyManagerFactory {
    
    /** Creates a new instance of DVBKeyManagerFactory */
    protected DVBKeyManagerFactory(DVBKeyManagerFactorySpi factorySpi,
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

