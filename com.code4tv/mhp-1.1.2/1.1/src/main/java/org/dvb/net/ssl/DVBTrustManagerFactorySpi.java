/*
 * DVBTrustManagerFactorySpi.java
 *
 * Created on 4 mars 2005, 18:23
 */

package org.dvb.net.ssl;

import javax.net.ssl.*;
import org.dvb.security.*;
/**
 * This class defines the Service Provider Interface for the DVBTrustManagerFactory class.
 */
public abstract class DVBTrustManagerFactorySpi extends TrustManagerFactorySpi {
    
    /** Creates a new instance of DVBTrustManagerFactorySpi */
    public DVBTrustManagerFactorySpi() {
    }
    
     /**
     * This method is used to initialize the factory with an array of KeyStoreBuilder instances.
     * @param builders an array of <code>KeyStoreBuilder</code>s.
     */ 
    protected  abstract void engineInit(KeyStoreBuilder[] builders);
}

