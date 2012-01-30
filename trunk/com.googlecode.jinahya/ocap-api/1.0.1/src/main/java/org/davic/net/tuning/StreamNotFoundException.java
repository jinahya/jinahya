
package org.davic.net.tuning;

/** This exception is raised when a reference to a transport stream
  * from a Locator can not be resolved because
  * the transport stream does not exist in the database of known 
  * transport streams.
  */
public class StreamNotFoundException extends NetworkInterfaceException {

  /** Default constructor for the exception
    */
  public StreamNotFoundException() {}

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public StreamNotFoundException(String reason) {}
 
}

