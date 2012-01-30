package org.davic.net.ca;

/** This exception is raised when the version of the resource
  * requested by the application in the message passing functions
  * is lower than what the application requested. This exception is
  * based on table 7.7 of the common interface specification.
  */
public class ModuleResourceVersionTooLowException extends CAException {

  /** Default constructor for the exception
    */
  public ModuleResourceVersionTooLowException() {
  }

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public ModuleResourceVersionTooLowException(String reason) {
  }
}

