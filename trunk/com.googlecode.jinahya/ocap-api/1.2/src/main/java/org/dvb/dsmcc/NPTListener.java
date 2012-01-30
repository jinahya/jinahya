
package org.dvb.dsmcc;

import java.util.*;



/** 
  * Objects that implement the <code>NPTListener</code> interface can receive
  * <code>NPTStatusEvent</code> and <code>NPTRateChangedEvent</code> events.
  * @since MHP 1.0.1
  */

public interface NPTListener extends java.util.EventListener {
  
	/**
	 * Send a <code>NPTRateChangeEvent</code> to a registered listener.
 	 * @param e the <code>NPTRateChangeEvent</code> event.
	 */
	public void receiveRateChangedEvent( NPTRateChangeEvent e);

	/**
	 * Send a <code>NPTStatusEvent</code> to a registered listener.
	 * @param e a <code>NPTStatusEvent</code> describing the status change
	 */
	public void receiveNPTStatusEvent( NPTStatusEvent e);
}

