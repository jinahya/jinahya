/*
 * AuthProvider.java
 *
 * Created on 28 janvier 2005, 16:18
 */

package org.dvb.security;
import org.dvb.auth.callback.*;
/**
 *
 * This class defines login and logout for a provider.
 * While callers may invoke login directly, the provider may also invoke login on behalf of callers if it determines that
 * a login must be perfomed prior to certain operations. 
 * 
 */
public abstract class AuthProvider extends java.security.Provider {
    
    /**
     * Creates a new instance of AuthProvider.
     */
    protected AuthProvider(String name, double version, String info) 
    {
        super(name,version,info);
    }
    
    /**
     * log in to this provider 
     */
    public abstract void login(java.security.Principal identity, CallbackHandler handler) throws LoginException;
    
    
    /**
     * logout from this provider 
     */
    public abstract void logout() throws LoginException;
    
    
    /**
     * set a call back handler 
     */
    public abstract void setCallbackHandler(CallbackHandler handler);
        
    
    
}

