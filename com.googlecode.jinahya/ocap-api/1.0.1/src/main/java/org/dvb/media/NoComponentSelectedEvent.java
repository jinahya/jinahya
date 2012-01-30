package org.dvb.media;

import javax.media.StopEvent;

import javax.media.Controller;

/**
 * This event is generated whenever presentation of a stream stops because there 
 * are no selected components to present. One example of this would be use of the
 * <code>javax.tv.media.MediaSelectControl.remove</code> method to remove all components
 * of a service.
 * Generation of this event informs the application that the Player is no longer 
 * presenting any content.
 * @since MHP 1.0.1
 */

public class NoComponentSelectedEvent extends StopEvent {

	/**
	 * Construct an event.
	 *
	 * @param source the controller which was presenting the service
	 * @param stream the locator of the stream whose presentation has stopped
         * @param previous the previous state of the controller
         * @param current the current state of the controller
         * @param target the target state of the controller
	 */

	public NoComponentSelectedEvent(Controller source, int previous, int current, int target, javax.media.MediaLocator stream)
	{
		super(source,0,0,0,null);
	}

	/**
	 * This method returns the stream whose presentation has stopped
	 * 
	 * @return the locator for the stream concerned
	 */

	public javax.media.MediaLocator getStream()
	{
		return null;
	}
}



