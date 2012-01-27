
package org.dvb.application.storage;

/** 
  * Thrown if the end user rejects the request to install
  * or remove an application.
  *
  * @since MHP1.1
  */

public class UserRejectedInstallException extends Exception {

    /**
      * Constructs a <code>UserRejectedInstallException</code> with no detail
      * message.
      */
    public UserRejectedInstallException() {
	super();
    }

    /** 
      * Constructs a <code>UserRejectedInstallException</code> with a detail
      * message
      *
      * @param s detail message
      */  
    public UserRejectedInstallException(String s) {
	super(s);
    }
}


