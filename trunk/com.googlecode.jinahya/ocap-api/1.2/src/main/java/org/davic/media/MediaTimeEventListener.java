package org.davic.media;

/**
 * This interface should be implemented by the application in order to receive the events associated to 
 * a media position by the application.
 */

public interface MediaTimeEventListener  extends java.util.EventListener
{
	/**
	 * This method is invoked to signal the occurrence of an application associated event. 
	 * The application needs to implement this interface in order to receive the events.
         */

	public void receiveMediaTimeEvent(MediaTimeEvent e); 
}

