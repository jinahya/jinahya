package org.dvb.si;

/**
 * This exception is thrown when one or more of the arguments passed to 
 * a method are invalid (e.g. numeric identifiers out of range, etc.)
 *
 */

public class SIIllegalArgumentException extends SIException {
	
    /** Default constructor for the exception
      */
    public SIIllegalArgumentException() {
	super();
    }
    
    /** Constructor for the exception with a specified reason
      * @param reason the reason why the exception was raised
      */
    public SIIllegalArgumentException(String reason) {
	super(reason);
    }
    
}

