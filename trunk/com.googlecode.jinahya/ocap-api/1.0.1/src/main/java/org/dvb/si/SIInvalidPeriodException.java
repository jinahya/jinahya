package org.dvb.si;

/**
  * This exception is thrown when a specified period is invalid 
  * (for example, start time is after the end time)
  */

public class SIInvalidPeriodException extends SIException {
	
    /** Default constructor for the exception 
      */
    public SIInvalidPeriodException() {
	super();
    }
    
    /** Constructor for the exception with a specified reason
      * @param reason the reason why the exception was raised
      */
    public SIInvalidPeriodException(String reason) {
	super(reason);
    }

}

