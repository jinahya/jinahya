package org.dvb.media;

import javax.media.StopEvent;

import javax.media.Controller;

/**
 * This event is generated whenever presentation of a stream stops because
 * the player has lost so many resources that it cannot continue.
 * Generation of this event informs the application that the Player is no 
 * longer presenting any content.
 * @since MHP 1.0.1
 */

public class StopByResourceLossEvent extends StopEvent {

	/**
	 * Construct an event.
	 *
	 * @param source the controller which was presenting the service
	 * @param stream the locator of the stream which was being presented
         * @param previous the previous state of the controller
         * @param current the current state of the controller
         * @param target the target state of the controller
	 */

	public StopByResourceLossEvent(Controller source, int previous, int current, int target, javax.media.MediaLocator stream)
	{
		super(source,0,0,0,null);
	}

	/**
	 * This method returns the stream which was being presented
	 * 
	 * @return the locator for the stream concerned
	 */

	public javax.media.MediaLocator getStream()
	{
		return null;
	}
}



