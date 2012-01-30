
package org.davic.net.tuning;

/** This exception is raised when a NetworkInterface 
  * that could be reserved by the application, 
  * cannot be found.
  */
public class NoFreeInterfaceException extends NetworkInterfaceException {
  
  /** Default constructor for the exception
    */
  public NoFreeInterfaceException() {}

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public NoFreeInterfaceException(String reason) {}
 
}


