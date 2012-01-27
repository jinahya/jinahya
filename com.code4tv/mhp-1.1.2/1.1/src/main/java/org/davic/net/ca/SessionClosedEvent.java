package org.davic.net.ca;

/** Event to notify the application that a session has been closed. 
  */
public class SessionClosedEvent extends MessageEvent {

  /** Constructor for the event
    * @param SessionID the sessionID of the session concerned
    * @param caModule the CAModule object representing the module that 
    *                 is the source of the event
    */
  public SessionClosedEvent(int SessionID, Object caModule) {
  }

  /** This method returns the CAModule that is the source of the event 
    * @return the CAModule object representing the CA module that is 
    *         is the source of the event
    */
  public Object getSource() {
    return null;
  }

}

