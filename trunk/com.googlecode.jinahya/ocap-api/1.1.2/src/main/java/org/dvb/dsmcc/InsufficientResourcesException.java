package org.dvb.dsmcc;

/**
 * This exception gets thrown when a request to perform a DSMCC related operation 
 * cannot be completed due to resource limitations.
 * For example, no section filters or system 
 * timers may be available.  
 * @since MHP 1.0.1
 */
public class InsufficientResourcesException extends DSMCCException {
        
        /**
         * Construct an InsufficientResourcesException with no detail message
         */
        public InsufficientResourcesException() {
                super();
        }
        
        /**
         * Construct an InsufficientResourcesException with the specified detail message
	 * @param message the message for the exception
         */
        public InsufficientResourcesException(String message) {
                super(message);
        }

}




