package org.ocap.hardware.device;

import java.awt.Dimension;
import org.ocap.media.S3DFormatTypes;
import org.dvb.media.VideoFormatControl;


/**
 * Specifies the attributes of a video stream.
 * Instances of <code>VideoResolution</code> may be used to describe attributes
 * of input or output video.
 *
 * @see FixedVideoOutputConfiguration#getVideoResolution()
 * @see DynamicVideoOutputConfiguration#getInputResolutions()
 * @see DynamicVideoOutputConfiguration#addOutputResolution(VideoResolution, FixedVideoOutputConfiguration)
 */
public class VideoResolution
{
    /**
     * Constant indicating an unknown or unspecified line scan mode.
     */
    public static final int SCANMODE_UNKNOWN = 0;
    
    /**
     * Constant indicating interlaced line scan mode.
     */
    public static final int SCANMODE_INTERLACED = 1;

    /**
     * Constant indicating progressive line scan mode.
     */
    public static final int SCANMODE_PROGRESSIVE = 2;
    

    /**
     * Creates an instance of <code>VideoResolution</code> based upon
     * the given attributes.  The stereoscopic mode is set to the default
     * value of {@link S3DFormatTypes#FORMAT_2D}.
     * 
     * @param rez The desired pixel resolution; 
     *            <code>null</code> MAY be specified to indicate a <i>wildcard</i> value.
     * @param ar The desired aspect ratio.
     *           {@link VideoFormatControl#ASPECT_RATIO_UNKNOWN} MAY be specified
     *           to indicate a <i>wildcard</i> value.
     * @param rate The desired field or frame rate.
     *             Values less than or equal to 0.0F may be specified to indicate
     *             a <i>wildcard</i> value.
     * @param scan The desired scan mode.
     *             {@link #SCANMODE_UNKNOWN} MAY be specified to indicate a <i>wildcard</i> value.
     */
    public VideoResolution(Dimension rez, int ar, float rate, int scan)
    {
        // To be implemented
    }
    
    /**
     * Creates an instance of <code>VideoResolution</code> based upon
     * the given attributes.
     * 
     * @param rez The desired pixel resolution; 
     *            <code>null</code> MAY be specified to indicate a <i>wildcard</i> value.
     * @param ar The desired aspect ratio.
     *           {@link VideoFormatControl#ASPECT_RATIO_UNKNOWN} MAY be specified
     *           to indicate a <i>wildcard</i> value.
     * @param rate The desired field or frame rate.
     *             Values less than or equal to 0.0F may be specified to indicate
     *             a <i>wildcard</i> value.
     * @param scan The desired scan mode.
     *             {@link #SCANMODE_UNKNOWN} MAY be specified to indicate a <i>wildcard</i> value.}
     * @param stereoscopicMode The desired stereoscopicMode, indicated by one
     * 		of the video format types defined by {@link S3DFormatTypes}
     * 		(e.g., {@link S3DFormatTypes#FORMAT_2D},
     * 		{@link S3DFormatTypes#TOP_AND_BOTTOM}, etc.).
     */
    public VideoResolution(Dimension rez, int ar, float rate, int scan,
                                           int stereoscopicMode)
    {
    }
    
    /**
     * Return the pixel resolution of the video.
     * <p>
     * A value of <code>null</code> MAY be returned, indicating that the resolution is 
     * unknown or unspecified.    
     * 
     * @return an instance of <code>Dimension</code> specifying the pixel resolution
     *         or <code>null</code>
     */
    public Dimension getPixelResolution()
    {
        return null;
    }
    
    /**
     * Return the aspect ratio of the output video as specified by this object.
     * <p>
     * A value of <code>ASPECT_RATIO_UNKNOWN</code> MAY be returned, indicating that
     * the aspect ratio is unknown or unspecified.
     *
     * @return one of {@link org.dvb.media.VideoFormatControl#ASPECT_RATIO_UNKNOWN},
     *                {@link org.dvb.media.VideoFormatControl#ASPECT_RATIO_4_3},
     *                {@link org.dvb.media.VideoFormatControl#ASPECT_RATIO_16_9},
     *             or {@link org.dvb.media.VideoFormatControl#ASPECT_RATIO_2_21_1}.
     */
    public int getAspectRatio()
    {
        return 0;
    }

    /**
     * Return the frame or field rate of the video as specified by this object.
     * <p>
     * Possible return values are: 
     * <ul>
     * <li>24000F/1001 (23.976...)
     * <li>24F
     * <li>25F
     * <li>30000F/1001 (29.97...)
     * <li>30F
     * <li>50F
     * <li>60000F/1001 (59.94...)
     * <li>60F
     * </ul>
     * <p>
     * A value of less then or equal to 0.0F may be returned, indicating that the
     * rate is unknown or unspecified.
     * <p>
     * Return value specifies the field rate if <code>getScanMode()</code> returns 
     * {@link #SCANMODE_INTERLACED} and the frame rate if <code>getScanMode()</code>
     * returns {@link #SCANMODE_PROGRESSIVE}.  
     * 
     * @return the frame or field rate of the output video
     * 
     * @see #getScanMode
     */
    public float getRate()
    {
        return 0F;
    }
    
    /**
     * Return the video scan mode, as specified by this object.
     * <p>
     * A value of <code>SCANMODE_UNKNOWN</code> MAY be returned, indicating that
     * the scan mode is unknown or unspecified.
     * 
     * @return one of {@link #SCANMODE_UNKNOWN},
     *                {@link #SCANMODE_INTERLACED},
     *             or {@link #SCANMODE_PROGRESSIVE}.
     */
    public int getScanMode()
    {
        return 0;
    }
    
    /**
     * Reports the stereoscopic mode of this VideoResolution object. 
     * 
     * @return one of the video format types specified in {@link S3DFormatTypes}
     *
     * @see S3DFormatTypes
     */
    public int getStereoscopicMode()
    {
        return 0;
    }
}
