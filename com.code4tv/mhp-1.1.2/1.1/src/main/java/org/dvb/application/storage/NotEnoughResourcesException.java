package org.dvb.application.storage;

/** 
  * Thrown if the MHP terminal does not have enough resources to install 
  * the application.  E.g. not enough storage space.
  *
  * @since MHP1.1
  */
public class NotEnoughResourcesException extends Exception {

    /**
      * Constructs a <code>NotEnoughResourcesException</code> with no detail
      * message.
      */
    public NotEnoughResourcesException() {
	super();
    }

    /** 
      * Constructs a <code>NotEnoughResourcesException</code> with a detail message
      *
      * @param s detail message
      */  
    public NotEnoughResourcesException(String s) {
	super(s);
    }
}


