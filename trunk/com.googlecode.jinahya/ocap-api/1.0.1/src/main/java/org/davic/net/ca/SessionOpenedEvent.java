package org.davic.net.ca;

/** Event to notify the application that a session has been opened.
  * This is generated after the return from openMessageSession. 
  * For CA1, it will be delayed after the return from openMessageSession
  * until the time when the session has actual access to the smart card.
  */
public class SessionOpenedEvent extends MessageEvent {

  /** Constructor for the event
    * @param SessionID the sessionID of the session concerned
    * @param caModule the CAModule object representing the module that 
    *                 is the source of the event
    */
  public SessionOpenedEvent(int SessionID, Object caModule) {
  }

  /** This method returns the CAModule that is the source of the event 
    * @return the CAModule object representing the CA module that is 
    *         is the source of the event
    */
  public Object getSource() {
    return null;
  }

}

