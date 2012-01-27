package org.davic.net.ca;

/** The object in the application that wants to register to receive events
  * related to the MMI dialogues must implement this interface. 
  */
public interface MMIListener  extends java.util.EventListener {
	
  /** This method is called to send an event to the listener
    * @param event the event to be sent
    */
  public void receiveMMIEvent(MMIEvent event);
}

