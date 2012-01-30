package org.davic.net.ca;

/** Base class for events related to MMI dialogs 
  */
public abstract class MMIEvent extends java.util.EventObject {

  /* For javadoc to hide the non-public constructor */
  MMIEvent() {super(null);}
	
  /** Returns the identifier of the dialogue. 
    * @return the identifier of the dialogue
    */
  public int getDialogueId() {
    return 0;
  }

  /** Returns the CAModule or CAModuleManager that is the source of the event 
    */
  public Object getSource() {
    return null;
  }

}

