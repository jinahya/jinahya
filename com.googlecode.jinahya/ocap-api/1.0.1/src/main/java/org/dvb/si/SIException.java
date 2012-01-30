package org.dvb.si;

/**
  * This class is the root of the SI exceptions hierarchy.
  */

public abstract class SIException extends java.lang.Exception {

    /** Default constructor for the exception 
      */
    public SIException() {
    }

    /** Constructor for the SI exception with a specified reason
      * @param reason the reason why the exception was raised
      */
    public SIException(String reason) {
    }

}

