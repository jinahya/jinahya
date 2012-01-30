package org.ocap.media;

/**
 * This interface extends <code>VideoFormatControl</code> with constant 
 * definitions representing additional decoder format conversion (DFC)
 * options.  
 * These constants SHALL be accepted by any method that accepts 
 * <code>VideoFormatControl.DFC_*</code> constants and MAY be returned
 * by methods that return such constants.
 * <p>
 * This control extends the capability of
 * <code>org.dvb.media.VideoFormatControl</code> and instances of this
 * control SHALL be available for both MPEG-2 and non-MPEG-2 streams.  
 * <p>
 * In OCAP Host devices, all instances of 
 * <code>org.dvb.media.VideoFormatControl</code> SHALL also be instances 
 * of <code>org.ocap.media.VideoFormatControl</code>.
 *
 * @see VideoFormatControl#getDecoderFormatConversion()
 * @see VideoFormatControl#getVideoTransformation(int)
 * 
 * @author Aaron Kamienski
 */
public interface VideoFormatControl extends org.dvb.media.VideoFormatControl
{
    /**
     * Constant representing the pillarbox conversion of the 4:3 input video 
     * to 16:9 output video.
     * The 4:3 input video SHALL be framed within the center of the 16:9 output 
     * video, such that the original pixel aspect ratio is maintained. 
     */
    public static final int DFC_PROCESSING_PILLARBOX_4_3 = 100;
    
    /**
     * Constant representing the wide conversion of the 4:3 input video 
     * to 16:9 output video.
     * In this mode the 4:3 input video SHALL be expanded horizontally to 
     * fill the 16:9 output frame, with pixels toward the outside edges 
     * stretched more than those toward the center.   
     */
    public static final int DFC_PROCESSING_WIDE_4_3 = 101;
}
