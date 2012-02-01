package org.dvb.net.rc;

/**
 * ConnectionDroppedEvent - An event generated after an attempt to setup a connection for a
 * <code>ConnectionRCInterface</code> fails due to the connection being
 * dropped by the target. For a ConnectionRCInterface of type <code>TYPE_PSTN</code>,
 * this includes the following conditions:
 * <ul>
 * <li>Connection was dropped after ringing but before it was answered
 * <li>Connection rang but was never answered and timed-out
 * <li>Connection was dropped after answering but before the IP connection was established
 * <li>Connection was answered but the IP connection was never established and timed-out
 * </ul>
 */

public class ConnectionDroppedEvent extends ConnectionFailedEvent {
	/**
	 * Construct an event.
	 *
	 * @param source the <code>ConnectionRCInterface</code> whose connection attempt failed
	 */
	public ConnectionDroppedEvent(Object source){
		super(source);
	}
}

