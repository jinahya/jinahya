package org.davic.net.ca;


/** This exception is raised when the called method

  * can not perform the action because the receiver

  * is not tuned to the transport stream that

  * carries the necessary information required to 

  * perform the action.

  */

public class NotTunedException extends CAException {

  

  /** Default constructor for the exception

    */

 public NotTunedException() {

 }


  /** Constructor for the exception with a specified reason

    * @param reason the reason why the exception was raised

    */

  public NotTunedException(String reason) {

  }

}


