package org.dvb.dsmcc ;

/** 
 * An MPEGDeliveryErrorEvent indicates that an error (for instance, a time out or accessing the data would
 * require tuning) has occurred while loading data
 * from an MPEG Stream.
 */
public class MPEGDeliveryErrorEvent extends AsynchronousLoadingEvent {

	/**
	 * Creates an MPEGDeliveryEvent.
	 *
	 * @param o the DSMCCObject that generated the event.
         */
	public MPEGDeliveryErrorEvent (DSMCCObject o) {super(o);}

	/**
	 * Returns the DSMCCObject that generated the event.
         * 
	 * @return the DSMCCObject that generated the event.
	 */
	public java.lang.Object getSource() {return null;}
}

