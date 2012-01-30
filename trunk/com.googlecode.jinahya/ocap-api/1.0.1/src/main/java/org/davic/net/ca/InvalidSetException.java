package org.davic.net.ca;


/** This exception is raised if an application tries to set

  * an invalid response value in the MMI object methods or

  * calls the methods to set the response more than once.

  */


public class InvalidSetException extends CAException {


  /** Default constructor for the event

    */

  public InvalidSetException() {

  }


  /** Constructor for the exception with a specified reason

    * @param reason the reason why the exception was raised

    */

  public InvalidSetException(String reason) {}

}


