/*
 * KeyStoreCallbackHandlerProtection.java
 *
 * Created on 28 janvier 2005, 17:51
 */

package org.dvb.security;
import org.dvb.auth.callback.*;

/**
 * A protection parameter encapsulating a CallbackHandler.
 * 
 */
public class KeyStoreCallbackHandlerProtection implements KeyStoreProtectionParameters {
    
    /** Creates a new instance of KeyStoreCallbackHandlerProtection */
    public KeyStoreCallbackHandlerProtection(CallbackHandler handler) 
    {
    }
    
    /**
     * Returns the callback handler.
     */
    public CallbackHandler getCallbackHandler()
    {
        return(null);
    }
}

