package org.dvb.media;

/**
 * The listener used to receive video format events
 */

public interface VideoFormatListener extends java.util.EventListener
{
	/**
	 * receive a VideoFormatEvent
	 * 
	 * @param anEvent the VideoFormatEvent that has been received
	 */
	public void receiveVideoFormatEvent(VideoFormatEvent anEvent);
	
}


