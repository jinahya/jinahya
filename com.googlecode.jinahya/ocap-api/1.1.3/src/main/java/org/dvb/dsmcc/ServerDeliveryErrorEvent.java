package org.dvb.dsmcc ;

   
/**
 * The local machine can not communicate with the server. 
 * This event is only used with files implemented by delivery over bi-directional
 * IP connections. For the object carousel the <code>MPEGDeliveryErrorEvent</code> is used instead.
 */
public class ServerDeliveryErrorEvent extends AsynchronousLoadingEvent {

	/**
	 * Creates a ServerDeliveryEvent object.
         *
         * @param o the DSMCCObject that generated the event.
         */
	public ServerDeliveryErrorEvent (DSMCCObject o) {super(o);}

	/**
	 * Returns the DSMCCObject that generated the event.
         *
	 * @return the DSMCCObject that generated the event.
	 */
	public java.lang.Object getSource() {return null;}
}

