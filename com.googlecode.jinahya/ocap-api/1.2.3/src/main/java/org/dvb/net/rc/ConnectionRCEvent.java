package org.dvb.net.rc;

/**
 * ConnectionRCEvent - the base class for events related to connection
 * oriented return channels.
 */

public class ConnectionRCEvent extends java.util.EventObject {
	/**
	 * Construct an event
	 *
	 * @param source the <code>ConnectionRCInterface</code>
	 * for which the event was generated.
	 */
	public ConnectionRCEvent(Object source){
		super(source);
	}
}

