package org.davic.net.ca;

/** Base class of events related to message passing in the CA API
  */
public abstract class MessageEvent extends java.util.EventObject {


  /* For javadoc to hide the non-public constructor */
  MessageEvent() {super(null);}

  /** Returns the session_id of the message session
    * @return the session_id
    */
  public int getSessionId() {	
    return 0;
  }

  /** Returns the source of the event 
    * @return the CAModule object representing the CA module that is 
    *         the source of this event
    */
  public Object getSource() {
    return null;
  }

}

