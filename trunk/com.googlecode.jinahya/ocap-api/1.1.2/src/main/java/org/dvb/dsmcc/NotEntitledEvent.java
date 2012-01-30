   package org.dvb.dsmcc;

   import java.util.*;

 /** 
  * This event is sent when an attempt to asynchronously load an object
  * has failed because the elementary stream carrying the object is scrambled
  * and the user is not entitled to access the content of the object.
  */
   public class NotEntitledEvent extends AsynchronousLoadingEvent {

	/**
	 * Creates a NotEntitledEvent object.
         *
	 * @param o the DSMCCObject that generated the event.
         */
   	public NotEntitledEvent(DSMCCObject o) {super(o);}

	/**
	 * Returns the DSMCCObject that generated the event.
         *
	 * @return the DSMCCObject that generated the event.
	 */
	public java.lang.Object getSource() {return null;}



   }

