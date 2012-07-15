   package org.dvb.dsmcc;

   import java.util.*;

 /** 
  * This class described an Object event which is used to notify the loading
  * of a DSMCC object.
  */
   public abstract class AsynchronousLoadingEvent extends java.util.EventObject {

	/**
	 * Creates an AsynchronousLoadingEvent.
         *
	 * @param o the DSMCCObject that generated the event.
         */
   	public AsynchronousLoadingEvent(DSMCCObject o) {super(o);}

	/**
	 * Returns the DSMCCObject that generated the event.
         *
	 * @return the DSMCCObject that generated the event.
	 */
	public java.lang.Object getSource() {return null;}



   }

