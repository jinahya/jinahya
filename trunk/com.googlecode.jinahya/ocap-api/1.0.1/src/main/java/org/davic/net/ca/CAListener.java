package org.davic.net.ca;

/** This interface is implemented by an object in the application
  * that can be registered to receive events related to CA
  */
public interface CAListener  extends java.util.EventListener {

  /** This method is called to send an event to the listener.
    * @param anEvent event to be sent to the listener
    */
  public void receiveCAEvent(CAEvent anEvent);
}

