package org.davic.net.ca;

/** This exception is raised when a method is called and
  * the module is no longer available
  */
public class ModuleUnavailableException extends CAException {

  /** Default constructor for the exception
    */
  public ModuleUnavailableException() {
  }

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public ModuleUnavailableException(String reason) {}
}

