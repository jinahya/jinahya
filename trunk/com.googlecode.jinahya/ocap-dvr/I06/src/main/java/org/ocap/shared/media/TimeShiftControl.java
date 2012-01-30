package org.ocap.shared.media;

import javax.media.Control;
import javax.media.Time;

/**
 * This interface represents a trick-mode control that can be used for retrieving more 
 * information corresponding to the playback of the time-shift buffer. This control will
 * only be available if the service being presented on the service context is a broadcast
 * service and if there is a time-shift buffer associated with the service context.
 */
public interface TimeShiftControl extends Control
{
    /**
     * Get the media time corresponding to the current beginning of the time-shift buffer.
     * This could be the media time corresponding to start of the buffer, before the buffer 
     * wrap around or the media time corresponding to the beginning of the valid buffer area
     * after the wrap around.
     *
     * @return media time corresponding to the beginning of the time-shift buffer.
     */
    public abstract Time getBeginningOfBuffer();

    /**
     * Get the media time corresponding to the end of the time-shift buffer. This could be
     * the current system time if the time-shift recording is still ongoing or the media
     * time corresponding to the end point for the valid area of the time-shift buffer.
     *
     * @return media time corresponding to the end of the time-shift buffer.
     */
    public abstract Time getEndOfBuffer();

	/**
	 * Get the duration of content currently in the time-shift buffer.
	 * The value returned is the content's duration when played at a  
	 * rate of 1.0.
	 *
	 * @return A Time object representing the duration.
	 */
	public abstract Time getDuration();

	/**
	 * Get the estimated value for the maximum duration of content 
	 * that could be buffered using this time-shift buffer.
	 * The value returned is the content's duration when played at a  
	 * rate of 1.0. 
	 *
	 * @return A Time object representing the maximum value for duration.
	 */
	public abstract Time getMaxDuration();
}

