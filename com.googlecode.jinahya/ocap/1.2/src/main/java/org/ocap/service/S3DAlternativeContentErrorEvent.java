package org.ocap.service;

import javax.tv.service.selection.ServiceContext;

/**
 * <code>S3DAlternativeContentErrorEvent</code> is generated to indicate that
 * "alternative" content may be presenting due to a detected incompatibility
 * between the selected 3D content and the display device.
 * The configuration of the Host device determines the nature of the
 * alternative content presented; e.g., no video or the 3D video as
 * requested.  If the Host device attempts to present the requested 3D video,
 * it is possible that it is being incorrectly displayed on the display device.
 * </p>
 * A device detects 3D content metadata (i.e., frame packing) based on signaling
 * as defined in [OCCEP].  The implementation SHALL compare the signaled content
 * format to HDMI display device capabilities reported in E-EDID and generate this
 * event as warranted.  This event will be generated due to the following
 * situations:
 * 
 * <ul>
 * <li> 3D video content selected but 3D video not supported by the HDMI display device.
 * <li> 3D video content selected but 3D format not supported by the HDMI display device.
 * <li> 3D video content selected but no display device connected to an HDMI port.
 * </ul>
 * 
 * Such events are not considered selection or presentation failures.
 * </p>
 * Note (informative):
 * <ul>
 * <li> If 3D video is selected for presentation but the Host device detects
 * that no display devices are connected to an HDMI port,
 * it will black out the video and mute the audio on any other connected outputs.
 * See [HOST 2.1]
 * <li> If the Host device detects a possible incompatibility between the 3D content
 * format and the display device, it may black out the video or send the requested
 * 3D video to the display device, depending on the Host device configuration
 * per [HOST 2.1] and [MIB-HOST].
 * </ul>
 * 
 */
public class S3DAlternativeContentErrorEvent extends AlternativeContentErrorEvent
{
    /**
     * Reason code: 3D video content has been selected but 3D video is not
     * supported by the HDMI display device.
     */
    // Value should be AlternativeContentErrorEvent.TUNING_FAILURE + 1.
    public static final int S3D_NOT_SUPPORTED = 104 + 1;

    /**
     * Reason code: 3D video content has been selected but its 3D format is not
     * supported by the HDMI display device.
     */
    public static final int S3D_FORMAT_NOT_SUPPORTED = S3D_NOT_SUPPORTED + 1;
    
    /**
     * Reason code : 3D video content has been selected but no display is
     * connected to an HDMI port.
     */
    public static final int S3D_NO_HDMI_CONNECTION = S3D_FORMAT_NOT_SUPPORTED + 1;


    /**
     * Constructs an event with a reason code.
     * 
     * @param source The <code>ServiceContext</code> that generated the event.
     *
     * @param reason The reason why alternative content is potentially being
     *      presented.
     */
    public S3DAlternativeContentErrorEvent(ServiceContext source, int reason)
    {
        super(source, reason);
    }

}
