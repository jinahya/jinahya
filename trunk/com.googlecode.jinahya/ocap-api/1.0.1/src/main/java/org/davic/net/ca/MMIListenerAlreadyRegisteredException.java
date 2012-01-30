package org.davic.net.ca;


/** This exception is raised if an application tries to register

  * a listener for the MMI events and there is already a listener

  * registered.

  */


public class MMIListenerAlreadyRegisteredException extends CAException {


  /** Default constructor for the event

    */

  public MMIListenerAlreadyRegisteredException() {

  }


  /** Constructor for the exception with a specified reason

    * @param reason the reason why the exception was raised

    */

  public MMIListenerAlreadyRegisteredException(String reason) {}

}


