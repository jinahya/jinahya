package org.dvb.media;

import javax.media.Control;
import org.havi.ui.HScreenRectangle;

/**
 * A control to support setting and querying the video presentation. <p> Note: For a component-based player the scaling and positioning of the video is done by manipulating the corresponding AWT component. The VideoPresentationControl only allows for the setting of the clipping region. <p> Note: If the hardware supports the positioning of interlaced video on even lines only (when counting from 0), then a component-based player is allowed to position the top of the video one line below where it should be. <p> For a background player there is the BackgroundVideoPresentationControl that allows for the setting of the clipping region, the position and the scaling of the video in one atomic action.
 */

public interface VideoPresentationControl extends Control 
{
	/**
	 * This method returns the dimensions of the video before any scaling 
	 * has taken place (but after ETR154 up-sampling). On 50Hz standard
	 * definition systems this method always returns 720 x 576.
	 * 
	 * @return the size of the decoded video before any scaling has taken
	 * place (but after ETR154 up-sampling)
	 */
	public abstract java.awt.Dimension getInputVideoSize();

	/**
	 * This method returns the size of the decoded video as it is being 
	 * presented to the user. It takes scaling and clipping into account.
	 * 
	 * @return the size of the decoded video as it is being presented to the user
	 */
	public abstract java.awt.Dimension getVideoSize();

	/**
	 * This method returns the size and location of the active video area.
	 * The active video area excludes any "bars" used for letterboxing or
	 * pillarboxing that the receiver knows about. Bars that are included
	 * in the broadcast stream and not signalled by active format descriptors
	 * are included in the active video area.
	 * The active video area may be larger/smaller than the 
	 * screen, and may possibly be offset. The offsets will 
	 * be negative if the origin of the active video area is above/left of the top, 
	 * left corner of the screen. In case of pan&scan, the value returned may vary over 
	 * time. This method only describes the relationship between the active video and the 
	 * screen. It does not describe which portion of the screen is displaying the video.<BR>
	 * Note: This method includes any video scaling.
	 * 
	 * @return an HScreenRectangle representing the active video area in the normalized
	 * coordinate space.
	 */
	public abstract HScreenRectangle getActiveVideoArea();
	
	/**
	 * This method returns the size and location of the active video area on-screen. 
     * The active video area excludes any "bars" used for letterboxing or
	 * pillarboxing that the receiver knows about. Bars that are included
	 * in the broadcast stream and not signalled by active format descriptors
	 * are included in the active video area.
	 * The active video area on-screen may be smaller than the 
	 * area of the screen, and may possibly be offset a positive 
	 * amount. This method only describes the area on-screen where active video is being 
	 * presented. It does not really describe which part of the video is being shown 
	 * on-screen. This is especially true for pan&scan.<BR>
	 * Note: This method includes any video scaling.
	 * 
	 * @return an HScreenRectangle representing the active video area on-screen in the 
	 * normalized coordinate space.
	 */
	public abstract HScreenRectangle getActiveVideoAreaOnScreen();	
	
	/**
	 * This method returns a relative size and location of the total video area, including 
	 * any "bars" used for letterboxing or pillarboxing that are included in the broadcast 
	 * stream, but excluding any "bars" introduced as a result of video filtering. This 
	 * may be larger or smaller than the size of the physical display device. 
	 * This method only describes the relationship between the total video and the screen. 
	 * It does not describe which 
	 * portion of the screen is displaying the video.<BR>
	 * Note: This method includes any video scaling.
	 * 
	 * @return an HScreenRectangle representing the total video area in the normalized
	 * coordinate space.
	 */
	public abstract HScreenRectangle getTotalVideoArea();
		
	/**
	 * This method returns a relative size and location of the total video area on-screen, 
	 * including any "bars" used for letterboxing or pillarboxing that are included in the 
	 * broadcast stream, but excluding any "bars" introduced as a result of video filtering. 
	 * This method only describes the area on-screen where 
	 * total video is being presented. This does not 
	 * really describe which part of the video is being shown on-screen. This is especially 
	 * true for pan&scan.<BR>
	 * Note: This method includes any video scaling.
	 * 
	 * @return an HScreenRectangle representing the total video area on-screen in the 
	 * normalized coordinate space.
	 */
	public abstract HScreenRectangle getTotalVideoAreaOnScreen();
	
	/**
	 * Test if the decoder supports clipping
	 *
	 * @return true if and only if the decoder supports clipping.
	 */
	public abstract boolean supportsClipping();

	/**
	 * Set the region of the decoded video that will be displayed. 
	 * If clipping is not supported, this method has no effect. If 
	 * the bounding box extends beyond the decoded video, the results
         * are implementation dependent. By 
	 * default, the clipping region is set to the dimensions of the 
	 * decoded video. This method returns the bounding box of the 
	 * clipping region that was actually set.
	 * Implementations may approximate the requested rectangle if they have 
	 * restrictions on video clipping.<p>
	 * If the player is a component-based player (as opposed to
	 * a background player), then the top left corner of the clip region 
	 * will be aligned with the top left corner of the java.awt.Component 
	 * returned by the method javax.media.Player.getVisualComponent().
	 * Hence changing the position of the clip region within the video 
	 * moves the video with respect to the coordinate space used by 
	 * java.awt.
	 *
	 * @param clipRect the bounding box of the clipping region. The coordinate
	 * space used to express the region is that of the decoded video after possible
	 * ETR154 up-sampling.
	 * 
	 * @return the set clipping region. If the requested clipping region
	 * is supported exactly, then the input parameter clipRect is returned, otherwise
	 * a newly created object will be returned.
	 */
	public abstract java.awt.Rectangle setClipRegion(java.awt.Rectangle clipRect);


	/**
	 * This method returns the area of the decoded video that will be 
	 * displayed. If clipping is not supported, the dimensions of the 
	 * bounding box will be the same as the displayed video.
	 * Note that when the MHP terminal is in {@link org.dvb.media.VideoFormatControl#DFC_PROCESSING_PAN_SCAN pan & scan mode}, the return value of 
	 * this method will be out of date almost as soon as the method has returned.
	 * @return area of the decoded video that will be displayed. The 
	 * coordinate space used to express the region is that of the decoded 
	 * video after possible ETR154 up-sampling.
	 */
	public abstract java.awt.Rectangle getClipRegion();
	
	/**
	 * This method gives information about whether arbitrary horizontal
	 * scaling is supported for the currently playing video.
	 * If arbitrary horizontal scaling is supported, then an array with
	 * two elements in returned. The first element returns the smallest
	 * allowed scaling factor (e.g. 0,5) and the second element returns
	 * the largest allowed scaling factor (e.g. 4). If arbitrary
	 * horizontal scaling is not supported, null is returned. In that
	 * case the method getHorizontalScalingFactors can be used to query
	 * which discrete scaling factors are supported.
	 * 
	 * @return an array with the minimum and maximum allowed horizontal
	 * scaling factor, or null if arbitrary horizontal scaling is not
	 * supported.
	 */
	public abstract float[] supportsArbitraryHorizontalScaling();
		
	/**
	 * This method gives information about whether arbitrary vertical
	 * scaling is supported for the currently playing video.
	 * If arbitrary vertical scaling is supported, then an array with
	 * two elements in returned. The first element returns the smallest
	 * allowed scaling factor (e.g. 0,5) and the second element returns
	 * the largest allowed scaling factor (e.g. 2). If arbitrary
	 * vertical scaling is not supported, null is returned. In that
	 * case the method getVerticalScalingFactors can be used to query
	 * which discrete scaling factors are supported.
	 * 
	 * @return an array with the minimum and maximum allowed vertical
	 * scaling factor, or null if arbitrary vertical scaling is not
	 * supported.
	 */
	public abstract float[] supportsArbitraryVerticalScaling();
	
	/**
	 * This method gives information about the supported discrete 
	 * horizontal scaling factors in case arbitrary horizontal 
	 * scaling is not supported.
	 * 
	 * @return an array with the supported discrete horizontal scaling
	 * factors (including the scaling factor 1), sorted in ascending 
	 * order. null is returned when arbitrary horizontal scaling is supported.
	 */
	public abstract float[] getHorizontalScalingFactors();
	
	/**
	 * This method gives information about the supported discrete 
	 * vertical scaling factors in case arbitrary vertical 
	 * scaling is not supported.
	 * 
	 * @return an array with the supported discrete vertical scaling
	 * factors (including the scaling factor 1), sorted in ascending 
	 * order. null is returned when arbitrary vertical scaling is supported.
	 */
	public abstract float[] getVerticalScalingFactors();
	
	/**
	 * Constant representing that the video can be positioned anywhere on
	 * the screen, even if a part of the video is off screen as a result
	 * of that.
	 */
	public static final byte POS_CAP_FULL = 0;
	
	/**
	 * Constant representing that the video can be positioned anywhere
	 * on screen as long as all the video is on screen.
	 */
	public static final byte POS_CAP_FULL_IF_ENTIRE_VIDEO_ON_SCREEN = 1;
	
	/**n
	 * Constant representing that the video can be positioned anywhere on
	 * the screen, even if a part of the video is off screen as a result
	 * of that, with the restriction that the field order is respected. This
	 * implies that interlaced video can be positioned on even lines only
	 * (when counting from 0).
	 */
	public static final byte POS_CAP_FULL_EVEN_LINES = 3;
	
	/**
	 * Constant representing that the video can be positioned anywhere
	 * on screen as long as all the video is on screen, with the restriction
	 * that the field order is respected. This implies that interlaced
	 * video can be positioned on even lines only (when counting from 0).
	 */
	public static final byte POS_CAP_FULL_EVEN_LINES_IF_ENTIRE_VIDEO_ON_SCREEN = 4;
	
	/**
	 * Constant representing that the video positioning capability
	 * cannot be expressed by another POS_CAP_* constant.
	 */
	public static final byte POS_CAP_OTHER = -1;
	
	/**
	 * This method gives information about how the video can be positioned
	 * on screen.
	 * 
	 * @return the positioning capability for the currently selected video as
	 * one of the POS_CAP_* constants.
	 */
	public byte getPositioningCapability();
}

