package org.davic.net.ca;


/** This exception is raised when there isn't sufficient

  * descrambling capacity available.

  */


public class NoCapacityException extends CAException {

  

  /** Default constructor for the exception

    */

  public NoCapacityException() {

  }


  /** Constructor for the exception with a specified reason

    * @param reason the reason why the exception was raised

    */

  public NoCapacityException(String reason) {

  }

}


