
package org.dvb.application.storage;

/** 
  * Thrown if the downloading of the application failed.
  * E.g. a carousel error, a file in the application description file is
  * missing in the carousel, the application was being authenticated as 
  * part of downloading and this failed, etc
  *
  * @since MHP1.1
  */

public class ApplicationDownloadException extends Exception {

    /**
      * Constructs an <code>ApplicationDownloadException</code> with no detail
      * message.
      */
    public ApplicationDownloadException() {
	super();
    }

    /** 
      * Constructs an <code>ApplicationDownloadException</code> with a detail message
      *
      * @param s detail message
      */  
    public ApplicationDownloadException(String s) {
	super(s);
    }
}



