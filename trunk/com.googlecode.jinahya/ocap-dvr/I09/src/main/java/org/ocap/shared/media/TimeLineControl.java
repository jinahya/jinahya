package org.ocap.shared.media;
/**
 * Provides access to the transmitted timelines in a piece of content 
 */
public interface TimeLineControl extends javax.media.Control {
	/**
	 * Returns all the transmitted timelines found in a piece of content. 
	 * If no transmitted timelines are present then an array of length 0
	 * is returned.
	 * @return an arry of timelines
	 */
	public TimeLine[] getTimeLines();
}

