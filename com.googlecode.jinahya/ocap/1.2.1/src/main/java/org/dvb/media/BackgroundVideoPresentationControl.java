package org.dvb.media;

/**
 * A control to support the setting and querying of the video presentation for background players.
 */

public interface BackgroundVideoPresentationControl
extends VideoPresentationControl 
{
	/**
	 * Sets a new video transformation (clipping/scaling/positioning). If
	 * the new video transformation is not supported, then the video
	 * transformation will not be changed at all (no best effort attempt
	 * is made).
	 * 
	 * @param t the new video transformation
	 * 
	 * @return true if the video transformation is supported and has been
	 * set, false otherwise.
	 */
	public abstract boolean setVideoTransformation(VideoTransformation t);
	
	/**
	 * Return the current video transformation
	 *
	 * @return the video transformation (clipping/scaling/positioning)
	 * that is currently used for displaying the video.
	 */
	public abstract VideoTransformation getVideoTransformation();
	
	/**
	 * This method takes a video transformation and returns the closest
	 * match of that video transformation that can be supported for the 
	 * currently selected video. If the input video transformation can
	 * be supported, then the output video transformation will have the
	 * same parameters as the input video transformation. The definition
	 * of 'closest match' is implementation dependent.
	 * 
	 * @param t the input video transformation
	 * 
	 * @return the closest match to the input video transformation. If the
	 * input video transformation is supported, then the input video
	 * transformation will be returned (the same instance), otherwise
	 * a newly created instance will be returned.
	 */
	public abstract VideoTransformation getClosestMatch(VideoTransformation t);
}

