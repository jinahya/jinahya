package org.davic.media;

/** This interface describes methods for the application to associate events with the media time 
 *  of the current stream.
 */
public interface MediaTimeEventControl extends javax.media.Control
{
	/** This method allows the application to associate an event with the specified media time. 
   	* The application can supply an	identification that will be associated with this event.
	*
	* @param i the listener to notify when the event happens
	* @param mediaTime the media time which will be associated with the event
	* @param id an identification for application use
	*/
	public void notifyWhen(MediaTimeEventListener i, long mediaTime, int id);

	/** This method allows the application to associate an event with the specified media time. 
	* The identification that will be associated with this event always equals 0.
	*
	* @param i the listener to notify when the event happens
	* @param mediaTime the media time which will be associated with the event
	*/
	public void notifyWhen(MediaTimeEventListener i, long mediaTime);
}

