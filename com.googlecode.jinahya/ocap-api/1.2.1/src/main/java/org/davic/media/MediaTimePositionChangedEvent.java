package org.davic.media;

/**
 * This event is generated whenever the media position is changed (when the invocation of the 
 * setMediaPosition resulted in a change in the media position).
 */

public class MediaTimePositionChangedEvent extends javax.media.RestartingEvent 
{
	/** 
	 * Construct an event.
	 *
	 * @param source the controller whose media position was changed
	 */
	public MediaTimePositionChangedEvent(javax.media.Controller source)
	{
		super(source,0,0,0,null);
	}
	/**
	 * Construct an event.
	 *
	 * @param from the controller whose media position was changed
	 * @param previous the state the controller was in before this event
	 * @param current the state the controller was in at the time the event was generated
	 * @param target the state that the controller is heading to
	 * @param mediaTime the media time after the change
	 */
	public MediaTimePositionChangedEvent (javax.media.Controller from,
		int previous, int current, int target, javax.media.Time mediaTime)
	{
		super(from,previous,current,target,mediaTime);
	}
}


