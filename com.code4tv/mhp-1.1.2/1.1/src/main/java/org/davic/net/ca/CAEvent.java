package org.davic.net.ca;

/** Base class for CA events
  */
public abstract class CAEvent extends java.util.EventObject{
  
  /* For javadoc to hide the non-public constructor */
  CAEvent() { super(null); }

  /** Returns the source of the event
    */
  public Object getSource() {
    return null;
  }
}

