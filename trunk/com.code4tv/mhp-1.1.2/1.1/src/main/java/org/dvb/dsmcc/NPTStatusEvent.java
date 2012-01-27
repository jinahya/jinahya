package org.dvb.dsmcc;

/**

 * Sent when an MHP terminal detects a change of status in the broadcast timeline of a stream.

 * @since MHP 1.0.1
 */

public abstract class NPTStatusEvent extends java.util.EventObject {
	/**
	 * Construct an event.

	 * @param source the stream whose broadcast timeline status changed

	 */
	public NPTStatusEvent( DSMCCStream source ){ super( (Object) source);}
	/**

	 * Return the stream whose broadcast timeline status changed.

	 *
	 * @return the <code>DSMCCStream</code> whose status changed
	 */
	public Object getSource(){return null;}
}

