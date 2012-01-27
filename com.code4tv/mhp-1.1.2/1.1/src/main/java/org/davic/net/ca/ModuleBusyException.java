package org.davic.net.ca;

/** This exception is raised when a method is called
  * and the module is busy and can not perform the requested
  * action.
  */
public class ModuleBusyException extends CAException {
  
  /** Default constructor for the exception
    */
  public ModuleBusyException() {
  }

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public ModuleBusyException(String reason) {
  }
}


