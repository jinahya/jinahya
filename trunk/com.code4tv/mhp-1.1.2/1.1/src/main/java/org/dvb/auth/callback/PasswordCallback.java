/*
 * PasswordCallback.java
 *
 * Created on 28 janvier 2005, 15:26
 */

package org.dvb.auth.callback;

/**
 * Underlying security services instantiate and pass a PasswordCallback to the handle method of a CallbackHandler to retrieve password information. 
 * The CallbackHandler uses this to communicate to the security services a password obtained from the end-user.
 */
public class PasswordCallback implements Callback {
    
    /** Creates a new instance of PasswordCallback */
    public PasswordCallback(String prompt, boolean echoOn) 
    {
    }
    
    /**
     * Get the prompt.
     */
    public String getPrompt()
    {
        return(null);
    }
    
    /**
     * return whether the password should be displayed as being typed.
     */
    public boolean isEchoOn()
    {
        return(false);
    }
    
    /**
     * Set the retrieved password.
     */
    public void setPassword(char[] password)
    {
        
    }
    
    /**
     * Get the retrieved password.
     * @return the last password previously set by setPassword or null if none has been set
     */
    public char[] getPassword()
    {
        return(null);
    }
    
    /**
     * Clear the retrieved password.
     */
    public void clearPassord()
    {
    
    }
}

