package org.dvb.media;

/**
 * Report that a subtitle event has happened.
 */

public interface SubtitleListener 
		extends java.util.EventListener {
	/**
	 * Report a subtitle event has happened.
	 *
	 * @param event the event which happened
	 */
	public void subtitleStatusChanged(java.util.EventObject event);
}


