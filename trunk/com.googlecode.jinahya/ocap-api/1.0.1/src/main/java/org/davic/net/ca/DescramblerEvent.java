package org.davic.net.ca;

/** Base class for events related to an on-going descrambling activity
  */
public abstract class DescramblerEvent extends java.util.EventObject {
  
  /* For javadoc to hide the non-public constructor */
  DescramblerEvent() {super(null);}

  /** Returns the source of the event
    */
  public Object getSource() {
    return null;
  }
}

