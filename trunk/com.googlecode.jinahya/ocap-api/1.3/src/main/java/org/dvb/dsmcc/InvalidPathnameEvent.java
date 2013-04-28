   package org.dvb.dsmcc ;

/**
 * The pathname does not exist or the ServiceDomain has been detached.
 */ 
   public class InvalidPathnameEvent extends AsynchronousLoadingEvent {
	/**
	 * Create an InvalidPathnameEvent.
         *
         * @param o the DSCMCCObject that generated this event.
         */
   	public InvalidPathnameEvent (DSMCCObject o) {super(o);}

	/**
	 * Returns the DSMCCObject that generated the event.
         *
	 * @return the DSMCCObject that generated the event.
	 */
	public java.lang.Object getSource() {return null;}
   }

