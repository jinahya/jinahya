package org.davic.media;

/**
 * This interface should be implemented to enable the application to position the media position in time (timeline control). The method getControl and getControls return the object implementing this interface if it is supported. For an ordinary broadcast player it is unlikely that this control is supported.
 */

public interface MediaTimePositionControl extends javax.media.Control 
{
	/**
	 * Invocation of this method repositions the media time position as closely as possible to the 
 	 * requested media time (with as little disruption as possible if the player is playing). 
         * The time positions are specified as a javax.media.time.
	 * @param mediaTime the required media time position
	 * @return the position in time that was actually set.
	 */

	public javax.media.Time setMediaTimePosition(javax.media.Time mediaTime);
	
	/**
	 * @return the current media time.
	 */
	public javax.media.Time getMediaTimePosition();
}



