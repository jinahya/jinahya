package org.dvb.net.rc;

/**
 * ConnectionFailedEvent - An event generated after an attempt to setup a connection for a
 * <code>ConnectionRCInterface</code> fails.
 */

public class ConnectionFailedEvent extends ConnectionRCEvent {
	/**
	 * Construct an event.
	 *
	 * @param source the <code>ConnectionRCInterface</code> whose connection attempt failed
	 */
	public ConnectionFailedEvent(Object source){
		super(source);
	}
}

