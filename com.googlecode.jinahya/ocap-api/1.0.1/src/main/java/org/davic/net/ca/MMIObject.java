package org.davic.net.ca;

/** The base class of all MMI classes.
  */
public class MMIObject {

  /* For javadoc to hide the non-public constructor */
  MMIObject() {}

  /** Closes the MMI object and informs the CA API implementation
    * that the application intends to close or has closed the 
    * corresponding MMI screen. <p>
    * It is a request to the CA API implementation to send the 
    * next MMIEvent (either StartMMIEvent or CloseMMIEvent) in this dialogue.
    */
  public void close() {
  }
}


