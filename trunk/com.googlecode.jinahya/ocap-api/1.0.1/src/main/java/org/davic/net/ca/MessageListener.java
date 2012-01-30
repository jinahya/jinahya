package org.davic.net.ca;

/** Objects of the application that want to be registered to 
  * receive events related to message passing with CA modules
  * must implement this interface.
  */
public interface MessageListener extends java.util.EventListener {
	
  /** This method is called to send a message to the listener.
    * @param module the CA module that is the sender of the message
    * @param event the message event to be sent
    */
  public void receiveMessage(CAModule module, MessageEvent event);
}


