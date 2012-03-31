package org.dvb.net.rc;

/**
 * NoDialToneEvent - An event generated after an attempt to setup a connection for a
 * <code>ConnectionRCInterface</code> of <code>TYPE_PSTN</code> fails due to there not 
 * being a dial tone on the return channel concerned.
 */

public class NoDialToneEvent extends ConnectionFailedEvent {
	/**
	 * Construct an event.
	 *
	 * @param source the <code>ConnectionRCInterface</code> whose connection attempt failed
	 */
	public NoDialToneEvent(Object source){
		super(source);
	}
}

