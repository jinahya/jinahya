   package org.dvb.dsmcc ;


/**
 * This event is generated if there are insufficient resources available
 * to load a DSMCCObject. e.g. if there is not enough memory.
 */
   public class InsufficientResourcesEvent extends AsynchronousLoadingEvent {
   
	/**
	 * Create an InsufficientResourcesException object.
	 *
	 * @param o the DSMCCObject that generated the event.
         */
   	public InsufficientResourcesEvent (DSMCCObject o) {super(o);}

	/**
	 * Returns the DSMCCObject that generated the event
         *
	 * @return the DSMCCObject that generated the event
	 */
	public java.lang.Object getSource() {return null;}


   }

