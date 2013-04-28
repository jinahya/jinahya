package org.ocap.hn.content;

import java.awt.Dimension;

/**
 * ContentResource to identify that a content item contains video/still image material.
 */
public interface VideoResource extends ContentResource {
	
	/**
	 * Returns the resolution of the video/still image.
	 * @return the resolution of the video/still image
	 */
	public Dimension getResolution();
	
	/**
	 * Returns the color depth (in bits) of the video/still image.
	 * @return the color depth (in bits) of the video/still image.
	 */
	public int getColorDepth();
	


}
