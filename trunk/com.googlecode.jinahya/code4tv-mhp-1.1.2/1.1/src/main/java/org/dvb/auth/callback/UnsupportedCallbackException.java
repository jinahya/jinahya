/*
 * UnsupportedCallbackException.java
 *
 * Created on 28 janvier 2005, 15:28
 */

package org.dvb.auth.callback;

/**
 *
 *
 */
public class UnsupportedCallbackException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>UnsupportedCallbackException</code> without detail message.
     */
    public UnsupportedCallbackException(Callback callback) {
    }
    
    
    /**
     * Constructs an instance of <code>UnsupportedCallbackException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UnsupportedCallbackException(Callback callback, String msg) {
        super(msg);
    }
    
    /**
     * Returns the Callback passed in to the constructor.
     * @return a callback
     */

    public Callback getCallback()
    {
        return(null);
    }
    
}

