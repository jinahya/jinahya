

package org.davic.net.tuning;


/** This exception is raised when a method that requires access

  * to a transport stream is called on a network interface that

  * is not tuned to any transport stream.

  */

public class NotTunedException extends NetworkInterfaceException {

  

  /** Default constructor for the exception

    */

  public NotTunedException() {}

 

  /** Constructor for the exception with a specified reason

    * @param reason the reason why the exception was raised

    */

  public NotTunedException(String reason) {}

 

}


