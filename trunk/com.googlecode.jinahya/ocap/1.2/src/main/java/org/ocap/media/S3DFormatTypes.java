package org.ocap.media;

/**
 * The interface contains constants representing 3D formatting types defined
 * in [OCCEP].
 * </p>
 * <b>Note:</b> Constants can be added from multiple standards for format
 * types supported by tru2way.
 */
public interface S3DFormatTypes
{

    /**
     * Frame packing arrangement is Side by Side; see [OCCEP].
     */
    public static final int SIDE_BY_SIDE = 3;
    
    /**
     * Frame packing arrangement is Top and Bottom; see [OCCEP].
     */
    public static final int TOP_AND_BOTTOM = 4;
}
