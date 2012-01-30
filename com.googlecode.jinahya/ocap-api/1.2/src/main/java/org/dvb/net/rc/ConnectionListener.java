package org.dvb.net.rc;

/**
 * This interface should be implemented by objects wishing to be notified
 * about the connection status of a <code>ConnectionRCInterface</code>.
 *
 */

public interface ConnectionListener extends java.util.EventListener{
	/**
	 * This method is called to report events related to the setup
	 * and termination of return channel interface connections.
	 * 
	 * @param e the event which happened
	 */
	public abstract void connectionChanged(ConnectionRCEvent e);
}


