
package org.dvb.application.storage;

 /** 
  * When an application is being installed into a service, this is thrown when:
  * <ul>
  * <li>The application does not include an application_storage_descriptor.
  * <li>The application is not identified as able to run stand-alone in it's application_storage_descriptor.
  * </ul>
  * <p>When an application is being stored in a cache this is thrown when
  * <ul>
  * <li>The application does not include an application_storage_descriptor.
  * <li>The application is not signalled as part of the same service as the calling application
  * </ul>
  *
  * @since MHP1.1
  */

public class InvalidApplicationException extends Exception {

    /**
      * Constructs an <code>InvalidApplicationException</code> with no detail
      * message.
      */
    public InvalidApplicationException() {
	super();
    }

    /** 
      * Construct a <code>InvalidApplicationException</code> with a detail message
      *
      * @param s detail message
      */  
    public InvalidApplicationException(String s) {
	super(s);
    }
}

