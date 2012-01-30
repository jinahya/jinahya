package org.dvb.net.rc;

/**
 * ConnectionTerminatedEvent - An event generated after a connected
 * <code>ConnectionRCInterface</code> is disconnected.
 */

public class ConnectionTerminatedEvent extends ConnectionRCEvent {
	/**
	 * Construct an event.
	 *
	 * @param source the <code>ConnectionRCInterface</code> whose status changed
	 */
	public ConnectionTerminatedEvent(Object source){
		super(source);
	}
}

