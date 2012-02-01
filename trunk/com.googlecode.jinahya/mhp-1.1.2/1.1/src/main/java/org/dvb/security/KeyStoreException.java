/*
 * KeyStoreException.java
 *
 * Created on 28 janvier 2005, 17:41
 */

package org.dvb.security;

/**
 *
 * Generic KeyStore exception. 
 */
public class KeyStoreException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>KeyStoreException</code> without detail message.
     */
    public KeyStoreException() {
    }
    
    
    /**
     * Constructs an instance of <code>KeyStoreException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public KeyStoreException(String msg) {
        super(msg);
    }
}

