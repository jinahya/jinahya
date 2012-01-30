package org.dvb.media;

/**
 * Event signalling that the aspect ratio of the transmitted video 
 * has changed
 */
public class AspectRatioChangedEvent extends VideoFormatEvent
{
	/**
	 * Construct the event
	 * 
	 * @param source the source of the event
	 * @param newRatio the new aspect ratio of the transmitted video
	 */
	public AspectRatioChangedEvent(Object source, int newRatio) {
		super(source);
	}
	
	/**
	 * Get the new aspect ratio of the transmitted video
	 * 
	 * @return the new aspect ratio of the video.  The value of this is 
	 * represented by one of the constants from the VideoFormatControl class
         * and shall be the value passed into the constructor of the event.
	 */
	public int getNewRatio() {
		return 0;
	}
}

