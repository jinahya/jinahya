package org.davic.net.ca;

/** This event informs an application that an MMI dialogue has to be started. 
  */
public class StartMMIEvent extends MMIEvent {

  /** Constructor for the event
    * @param mmiObject the MMI object
    * @param dialogueId a unique identifier for the dialogue
    * @param caModule the CAModule object representing the source of the event,
    * which shall be returned by the <code>getSource</code> method.
    */
  public StartMMIEvent(MMIObject mmiObject, int dialogueId, Object caModule) {
  }


  /** Returns a reference to the object that describes the MMI.
    * @return the MMI object describing the MMI dialogue to be presented
    */
  public MMIObject getMMIObject() {
    return null;
  }
}

