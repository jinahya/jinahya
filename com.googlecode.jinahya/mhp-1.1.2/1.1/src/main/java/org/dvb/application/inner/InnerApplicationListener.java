package org.dvb.application.inner;

/**
 * Listener for events related to inner applications
 */

public interface InnerApplicationListener {
	/**
	 * Called when an inner application exits.
	 *
	 * @param e an event object encapsulating what happened
	 */
	public void InnerApplicationExited(InnerApplicationEvent e );
}

