package org.davic.net.ca;

/** This exception is raised when sendToModule() is called when the message
 * buffer is full.
  */
public class BufferFullException extends CAException {

  /** Default constructor for the event.
    */
  public BufferFullException() {
  }

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public BufferFullException(String reason) {
  }

}

