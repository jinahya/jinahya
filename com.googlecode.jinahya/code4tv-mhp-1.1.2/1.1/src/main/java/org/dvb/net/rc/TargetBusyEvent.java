package org.dvb.net.rc;

/**
 * TargetBusyEvent - An event generated after an attempt to setup a connection for a
 * <code>ConnectionRCInterface</code> fails due to the target of the 
 * connection being busy.
 */

public class TargetBusyEvent extends ConnectionFailedEvent {
	/**
	 * Construct an event.
	 *
	 * @param source the <code>ConnectionRCInterface</code> whose connection attempt failed
	 */
	public TargetBusyEvent(Object source){
		super(source);
	}
}

