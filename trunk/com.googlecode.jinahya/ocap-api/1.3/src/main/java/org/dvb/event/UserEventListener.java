package org.dvb.event ;

/**
 * The listener interface for receiving user inputs.
 */
public interface UserEventListener extends java.util.EventListener {
    
	/**
	 * Called by the platform when a user input is received.
	 *
	 * @param e the user input event which was received
	 */
    public void userEventReceived (UserEvent e) ;
}

