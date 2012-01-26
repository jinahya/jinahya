package org.davic.net;


/**

 * This exception is thrown when one or more parameters to construct

 * a Locator are invalid.

 *

 */


public class InvalidLocatorException extends Exception {

	

  public InvalidLocatorException() {

    super();

  }


  /** Constructor for the exception with a specified reason

    * @param reason the reason why the exception was raised

    */

  public InvalidLocatorException(String reason) {

    super(reason);

  }


}


