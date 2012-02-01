package org.davic.net.ca;

/** This exception is raised when the application tries to 
  * close or send a message to an unknown session.
  */
public class UnknownSessionException extends CAException {

  /** Default constructor for the event.
    */
  public UnknownSessionException() {
  }

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public UnknownSessionException(String reason) {
  }

}

