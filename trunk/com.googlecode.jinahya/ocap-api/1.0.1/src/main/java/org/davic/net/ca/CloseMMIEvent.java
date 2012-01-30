package org.davic.net.ca;

/** This event informs application(s) that an MMI dialogue has to be closed. 
  * During normal operation, an MMI dialogue is closed either after 
  * receiving a new StartMMIEvent or after receiving a 
  * CloseMMIEvent. 
  */
public class CloseMMIEvent extends MMIEvent {

  /** Constructor for the event
    * @param caModule the CAModule or CAModuleManager object representing 
    * the source of the event.
    * @param DialogueID the dialogue which has been closed
    */
  public CloseMMIEvent(Object caModule, int DialogueID) {
  }
}

