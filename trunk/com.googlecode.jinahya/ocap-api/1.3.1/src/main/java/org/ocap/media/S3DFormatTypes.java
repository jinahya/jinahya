package org.ocap.media;

/**
 * The interface contains constants representing video formats for
 * stereoscopic 3D streams.
 * </p>
 * <b>Note:</b> Constants can be added from multiple standards for format
 * types supported by tru2way.
 */
public interface S3DFormatTypes
{
    /**
     * Video format is unknown.
     */
    public static final int UNKNOWN = 0;
    
    /**
     * Video format is 2D rather than 3D.
     */
    public static final int FORMAT_2D = 1;

    /**
     * S3D frame packing arrangement is Side by Side; see [OCCEP].
     */
    public static final int SIDE_BY_SIDE = 3;
    
    /**
     * S3D frame packing arrangement is Top and Bottom; see [OCCEP].
     */
    public static final int TOP_AND_BOTTOM = 4;
}
