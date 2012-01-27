package org.davic.net.ca;

/** This event informs that an MMI user dialog has ended
  */
public class MMIInactiveEvent extends CAEvent {

  /** Constructor for the event
    * @param caModule the CAModule or CAModuleManager object
    * representing the source of the event
    */
  public MMIInactiveEvent(Object caModule) {
  }


  /** Returns the CAModule or CAModuleManager that is the source of the event 
    */
  public Object getSource() {
    return null;
  }

}

