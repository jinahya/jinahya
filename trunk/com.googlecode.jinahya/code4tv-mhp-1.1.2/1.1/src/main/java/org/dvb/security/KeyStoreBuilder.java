/*
 * KeyStoreBuilder.java
 *
 * Created on 28 janvier 2005, 16:27
 */

package org.dvb.security;
import java.security.KeyStore;
import java.security.Provider;

/**
 *
 *  An instance of this class encapsulates the information needed to instance and initialize a KeyStore object.
 * That process is triggered when the getKeyStore method is called.
 *
 * This makes it possible to decouple configuration from KeyStore object creation and delay password 
 * prompt until it is needed.
 */
public abstract class KeyStoreBuilder {
    
    /** Creates a new instance of KeyStoreBuilder */
    protected KeyStoreBuilder() {
    }
    
    /**
     * Returns the KeyStore described by this object.
     * @throws KeyStoreException if an error occurred, e.g. if an error 
     * occurred in the constructor or the load method of the KeyStore
     */
    public abstract KeyStore getKeyStore() throws KeyStoreException;
    
    /**
     * Returns a new builder object.
     * Each call to the <code>getKeyStore</code> method on the returned builder will return 
     * a new org.dvb.security.DVBKeyStore object of type type. Its <code>load</code> method is 
     * invoked with the protection parameter used to construct this KeyStoreBuilder.
     * @param type the type of the KeyStore to be constructed. The type parameter is concatenated 
     * with the string "KeyStore." and then passed to the get method of the specified Provider 
     * in order to obtain the fully qualified name of the KeyStoreSpi implementation.
     * For more details, see "How to Implement a Provider for the JavaTM Cryptography Architecture"
     * @param provider the provider from which the keyStore is to be instantiated.
     * @param protection the protection parameter securing the Keystore.
     * @throws IllegalArgumentException if protection is an application defined class
     * @throws NullPointerException if type, provider or protection are null
     */
    public static KeyStoreBuilder newInstance(String type,
                                                Provider provider,
                                                KeyStoreProtectionParameters protection)
    {
        return(null);
    }
            
}

