/*
 * DVBKeyManagerFactorySpi.java
 *
 * Created on 4 mars 2005, 18:23
 */

package org.dvb.net.ssl;
import javax.net.ssl.*;
import org.dvb.security.*;

/**
 * This class defines the Service Provider Interface for the DVBKeyManagerFactory class.
 */
public abstract class DVBKeyManagerFactorySpi extends KeyManagerFactorySpi {
    
    /** Creates a new instance of DVBKeyManagerFactorySpi */
    public DVBKeyManagerFactorySpi() {
    }
    
    /**
     * This method is used to initialize the factory with an array of KeyStoreBuilder instances.
     * @param builders an array of KeyStoreBuilder.
     */ 
    protected  abstract void engineInit(KeyStoreBuilder[] builders);
    
}

