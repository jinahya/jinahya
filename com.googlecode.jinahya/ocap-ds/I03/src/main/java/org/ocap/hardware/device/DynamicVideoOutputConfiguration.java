/*
 * Created on Mar 20, 2007
 */
package org.ocap.hardware.device;

import java.util.Enumeration;

import org.dvb.media.VideoFormatControl;

/**
 * An instance of this class may be used to represent a dynamic selection of video
 * output configurations based upon specified input video.
 * An instance of <code>DynamicVideoOutputConfiguration</code> would mainly be used
 * to allow for the <code>HScreen</code> resolution and the video output port resolution
 * to closely match the resolution of the input video, generally with the intention of
 * letting the display monitor connected to the output port manage aspect ratio conversions.
 * <p>
 * Such configurations are only valid for the current 
 * {@link HostSettings#getMainVideoOutputPort main} video output port for 
 * a given <code>HScreen</code>.
 * If a video output port is not the current <i>main</i> output port or 
 * ceases to be the <i>main</i>, then this configuration setting SHALL be 
 * effectively ignored and output resolution settings SHALL revert to an
 * implementation-specific configuration.
 * <p>
 * Application of the input-to-output video resolution mapping described by an
 * instance of <code>DynamicVideoOutputConfiguration</code> MAY result in configuration
 * changes for the component <code>HScreenDevice</code>s of the relevant
 * <code>HScreen</code> as if the output resolution were selected via a
 * {@link FixedVideoOutputConfiguration static} configuration.
 */
public class DynamicVideoOutputConfiguration implements VideoOutputConfiguration
{
    /**
     * Constant representing any Standard Definition input video.
     */
    public static final VideoResolution INPUT_SD = new VideoResolution(null, -1, 0F, VideoResolution.SCANMODE_UNKNOWN);
    
    /**
     * Constant reprsenting any High Definition input video.
     */
    public static final VideoResolution INPUT_HD = new VideoResolution(null, -1, 0F, VideoResolution.SCANMODE_UNKNOWN);

    /**
     * Construct a new instance of <code>DynamicVideoOutputConfiguration</code>.
     * The newly created <code>DynamicVideoOutputConfiguration</code> contains 
     * no mappings from input video to output video configuration.
     */
    public DynamicVideoOutputConfiguration()
    {
        // to be implemented
    }
    
    /**
     * Returns "Dynamic".
     * @return "Dynamic"
     * @see org.ocap.hardware.device.VideoOutputConfiguration#getName()
     */
    public String getName()
    {
        return "Dynamic";
    }

    /**
     * Add a desired input video resolution to output video resolution mapping.
     * If this configuration is {@link VideoOutputSettings#setOutputConfiguration applied}
     * successfully, the video output port would use the given output resolution whenever
     * the main video input resolution matched the given input resolution.
     * <p>
     * The desired video output resolution is specified as an instance of 
     * <code>FixedVideoOutputConfiguration</code>.  Valid configurations are those
     * returned by {@link VideoOutputSettings#getSupportedConfigurations()} for a
     * given video output port instance.
     * This class does not guard against addition of an invalid video
     * resolution configuration.  
     * Instead, the instance of <code>DynamicVideoOutputConfiguration</code>
     * would be rejected by the video output port.
     * <p>
     * For a given input resolution, wildcard values may be specified for some attributes.
     * The following table documents accepted wildcard (or "don't care") values.
     * <table border>
     * <tr><th>Attribute</th> <th>Wildcard value</th></tr>
     * <tr><td>{@link VideoResolution#getPixelResolution()}</td>
     *     <td><code>null</code></td></tr>
     * <tr><td>{@link VideoResolution#getAspectRatio()}</td>
     *     <td><code>{@link VideoFormatControl#ASPECT_RATIO_UNKNOWN}</code></td></tr>
     * <tr><td>{@link VideoResolution#getRate()}</td>
     *     <td><code><= 0.0F</code></td></tr>
     * <tr><td>{@link VideoResolution#getScanMode()}</td>
     *     <td><code>{@link VideoResolution#SCANMODE_UNKNOWN}</code></td></tr>
     * </table>
     *
     * @param inputResolution The given input video resolution.
     *                        May be an application-created instance of <code>VideoResolution</code>;
     *                        or one of {@link #INPUT_SD} or {@link #INPUT_HD} may be specified to
     *                        indicate a wildcard value covering all SD or HD resolutions.
     * @param outputResolution The desired output configuration that video of the given input
     *                         resolution should be mapped.
     * 
     * @see #getOutputResolution
     */
    public void addOutputResolution(VideoResolution inputResolution, FixedVideoOutputConfiguration outputResolution)
    {
        // to be implemented
    }
    
    /**
     * Get the output configuration that should be applied for the given input resolution
     * if this configuration were successfully set on a video output port.
     *
     * @param inputResolution The given input video resolution.
     *                        May be an application-created instance of <code>VideoResolution</code>;
     *                        or one of {@link #INPUT_SD} or {@link #INPUT_HD} may be specified to
     *                        indicate a wildcard value covering all SD or HD resolutions.
     * @return The output video configuration mapped to by the given input resolution
     * or <code>null</code> if no mapping is defined.
     * 
     * @see #addOutputResolution
     */
    public FixedVideoOutputConfiguration getOutputResolution(VideoResolution inputResolution)
    {
        return null;
    }
    
    /**
     * Get the set of input video resolutions for which a mapping to output configuration
     * is specified.
     * 
     * @return A non-<code>null</code> <code>Enumeration</code> of <code>VideoResolution</code> instances.
     */
    public Enumeration getInputResolutions()
    {
        return null;
    }
}
