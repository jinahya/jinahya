package org.davic.net.ca;

/** This exception is raised when the resource requested by
  * the application in the message passing functions does not
  * exist in the module.  This is also raised in DVB CI / 
  * DAVIC CA0 based systems if the resource requested is not a valid target
  * for message passing. One example of this would be addressing a public resource.
  */
public class ModuleResourceNonExistentException extends CAException {
  
  /** Default constructor for the exception
    */ 
  public ModuleResourceNonExistentException() {
  }

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public ModuleResourceNonExistentException(String reason) {
  }
}


