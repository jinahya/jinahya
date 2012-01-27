/*
 * LoginException.java
 *
 * Created on 28 janvier 2005, 16:25
 */

package org.dvb.security;

/**
 *
 *  Basic login exception.
 */
public class LoginException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>LoginException</code> without detail message.
     */
    public LoginException() {
    }
    
    
    /**
     * Constructs an instance of <code>LoginException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public LoginException(String msg) {
        super(msg);
    }
}

