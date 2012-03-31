/*
 * CallbackHandler.java
 *
 * Created on 28 janvier 2005, 15:27
 */

package org.dvb.auth.callback;

import java.io.IOException;

/**
 *
 * An application implements a CallbackHandler and passes it to underlying security services so that they may interact with the application to retrieve specific authentication
 * data, such as usernames and passwords, or to display certain information, such as error and warning messages.  
 */
public interface CallbackHandler {
    
/**
 * Called when a callback needs handling.
 * @param callbacks - an array of Callback objects provided by an underlying security service which contains the information requested to be retrieved or displayed. 
 * @exception IOException if an input or output error occurs when retrieving the password
 * @exception UnsupportedCallbackException if one of the callbacks in the array is not supported by the handler
 */    
    void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException;
    
}

