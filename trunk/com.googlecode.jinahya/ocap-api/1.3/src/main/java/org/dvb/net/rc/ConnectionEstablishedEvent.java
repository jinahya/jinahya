package org.dvb.net.rc;

/**
 * ConnectionEstablishedEvent - An event generated after a connection is established
 * for a <code>ConnectionRCInterface</code>.
 */

public class ConnectionEstablishedEvent extends ConnectionRCEvent {
	/**
	 * Construct an event.
	 *
	 * @param source the <code>ConnectionRCInterface</code> whose connection was established
	 */
	public ConnectionEstablishedEvent(Object source){
		super(source);
	}
}

