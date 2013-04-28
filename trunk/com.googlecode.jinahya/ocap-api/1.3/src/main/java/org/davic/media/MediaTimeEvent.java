package org.davic.media;

/** The MediaTimeEvent class describes events that are associated with the stream by the application. 
 *  These events are posted whenever the Controller encounters a media position which has been 
 *  associated with the stream by the local application. The event includes the associated media time 
 * encoded as a long and the event identification encoded as an int. 
*/

public class MediaTimeEvent
{
	/**
	 * Make an event
	 *
	 * @param source the object generating the evenT
	 * @param eventTime the media time at which the event fired
	 * @param ID the event identification
	 */

	public MediaTimeEvent(Object source, long eventTime, int ID)
	{
	}

	/**
	 * @return the time with which this event was associated
	 */
	public long getEventTime() {
		return (long) 0;
	}

	/** 
	 * @returns the identification of this event
	 */
	public int getEventId() {
		return 0;
	}
	/**
	 * This method returns the source for the event. It may be
	 * inherited from sunw.util.EventObject or java.util.EventObject
	 * @return the source of the event 
	 */
	public Object getSource() {
		return null;
	}
}

