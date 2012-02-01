package org.dvb.internet;

/**
 * Interface for objects that wish to receive <code>InternetClientEvents</code>
 */
public interface InternetClientListener extends java.util.EventListener	
{
	
	/**
	 * The method to be called when an <code>InternetClientEvent</code> is received.
	 * 
	 * @param event the received <code>InternetClientEvent</code>
	 */
	public void receiveInternetClientEvent(InternetClientEvent event);
	
}

