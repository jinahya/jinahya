package org.dvb.media;

import javax.media.StopEvent;

import javax.media.Controller;

/**
 * This event is generated whenever access to a service is withdrawn by the 
 * CA system, e.g. at the end of a free preview period. It is not 
 * generated when an attempt to construct a Player or DataSource fails due 
 * to CA restrictions, or when only some of the presented content is not 
 * available or alternate content is presented. Generation of this event informs the application that the Player is no longer presenting any content.
 */

public class CAStopEvent extends StopEvent {

	/**
	 * Construct an event.
	 *
	 * @param source the controller which was presenting the service
	 */
	public CAStopEvent(Controller source){
		super(source,0,0,0,null);
	}
	/**
	 * Construct an event.
	 *
	 * @param source the controller which was presenting the service
	 * @param stream the locator of the stream from which access has been withdrawn. 
         * @param previous the previous state of the controller
         * @param current the current state of the controller
         * @param target the target state of the controller
	 */

	public CAStopEvent(Controller source, int previous, int current, int target, javax.media.MediaLocator stream)
	{
		super(source,previous,current,target,null);
	}

	/**
	 * This method returns the stream from which access has been withdrawn.
	 * 
	 * @return the locator for the stream concerned
	 */

	public javax.media.MediaLocator getStream()
	{
		return null;
	}
}



