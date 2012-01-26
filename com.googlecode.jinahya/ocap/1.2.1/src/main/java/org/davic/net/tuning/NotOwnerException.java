

package org.davic.net.tuning;


/** This exception is raised when the application calls

  * a method and has no

  * control over the corresponding NetworkInterface.

  */

public class NotOwnerException extends NetworkInterfaceException {


  /** Default constructor for the exception

    */

  public NotOwnerException() {

  }


  /** Constructor for the exception with a specified reason

    * @param reason the reason why the exception was raised

    */

  public NotOwnerException(String reason) {}


}



