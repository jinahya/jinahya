package org.davic.net.ca;

/** This exception is thrown when a method is called and the 
  * CA module does not have the required capacity to perform the action
  */  

public class NoFreeCapacityException extends CAException {

  /** Default constructor for the exception
    */
  public NoFreeCapacityException() {
  }

  /** Constructor for the exception with a specified reason
    * @param reason the reason why the exception was raised
    */
  public NoFreeCapacityException(String reason) {
  }

}

