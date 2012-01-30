package org.dvb.media;

import org.havi.ui.HScreenPoint;

/**
 * VideoTransformation objects express video transformations, i.e.
 * the clipping, the horizontal and vertical scaling and the position
 * of the video.
 * All transformations are to be applied after possible ETR154 up-sampling.
 * <p>
 * Note: Instances of VideoTransformation can represent pan and scan, but
 * an application cannot create such instances itself. An application
 * can get a VideoTransformation representing pan and scan, by calling
 * the VideoFormatControl.getVideoTransformation() method with the
 * pan and scan Decoder Format Conversion constant.
 */

public class VideoTransformation
{
	/**
	 * Creates a VideoTransformation object with default parameters.
	 * Clipping is disabled, both the horizontal and the vertical
	 * scaling factors are 1, and the video position is (0,0) in the 
	 * normalised coordinate space.
	 */
	public VideoTransformation()
	{
	}
	
	/**
	 * Creates a VideoTransformation object with the supplied
	 * parameters.
	 * 
	 * @param clipRect the bounding box of the clipping region. The coordinate
	 * space used to express the region is that of the decoded video after possible
	 * ETR154 up-sampling. A non-null ClipRect enables clipping. A null ClipRect disables it.
	 * 
	 * @param horizontalScalingFactor the horizontal scaling factor.
	 * @param verticalScalingFactor the vertical scaling factor.
	 * @param location the location of the video on the screen in the
	 * normalised coordinate space.
	 */
	public VideoTransformation(java.awt.Rectangle clipRect,
							   float horizontalScalingFactor,
							   float verticalScalingFactor,
							   HScreenPoint location)
	{
	}
	
	/**
	 * Sets the clipping region.
	 * <p>
	 * If this video transformation represents pan and scan, then it will
	 * no longer represent pan and scan when this method is called.
	 * A non-null ClipRect enables clipping. A null ClipRect disables it.
	 * 
	 * @param clipRect the bounding box of the clipping region. The coordinate
	 * space used to express the region is that of the decoded video after possible
	 * ETR154 up-sampling.
	 */
	public void setClipRegion(java.awt.Rectangle clipRect)
	{
	}
	
	/**
	 * Gets the clipping region.
	 * 
	 * @return the bounding box of the clipping region. The coordinate space
	 * used to express the region is that of the decoded video after possible
	 * ETR154 up-sampling. null is returned if this video transformation
	 * represents pan and scan or if clipping is disabled.
	 */
	public java.awt.Rectangle getClipRegion()
	{
		return null;
	}
	
	/**
	 * Sets the horizontal and vertical scaling factors.
	 * 
	 * @param horizontalScalingFactor the horizontal scaling factor.
	 * @param verticalScalingFactor the vertical scaling factor.
	 */
	public void setScalingFactors(float horizontalScalingFactor,
								  float verticalScalingFactor)
	{
	}
	
	/**
	 * Gets the horizontal and vertical scaling factors.
	 * 
	 * @return an array with two elements. The first element contains
	 * the horizontal scaling factor, the second element the vertical
	 * scaling factor.
	 */
	public float[] getScalingFactors()
	{
		return null;
	}
	
	/**
	 * Sets the video position.
	 * 
	 * @param location the location of the video on the screen in the
	 * normalised coordinate space.
	 */
	public void setVideoPosition(HScreenPoint location)
	{
	}
	
	/**
	 * Returns the video position.
	 * 
	 * @return the location of the video on the screen in the normalised
	 * coordinate space.
	 */
	public HScreenPoint getVideoPosition()
	{
		return null;
	}
	
	/**
	 * Returns whether this video transformation represents pan and scan.
	 * 
	 * @return true is this video transformation represents pan and scan,
	 * false otherwise.
	 */
	public boolean isPanAndScan()
	{
		return false;
	}
}

