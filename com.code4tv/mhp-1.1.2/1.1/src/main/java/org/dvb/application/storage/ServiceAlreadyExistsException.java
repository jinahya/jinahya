package org.dvb.application.storage;

/** 
  * Thrown if a StoredApplicationService already exists with 
  * the identifiers the application tried to create a new one.
  *
  * @since MHP1.1
  */

public class ServiceAlreadyExistsException extends Exception {

    /**
      * Constructs a <code>ServiceAlreadyExistsException</code> with no
      * detail message.
      */
    public ServiceAlreadyExistsException() {
	super();
    }

    /** 
      * Constructs a <code>ServiceAlreadyExistsException</code> with a
      * detail message
      *
      * @param s detail message
      */  
    public ServiceAlreadyExistsException(String s) {
	super(s);
    }
}


