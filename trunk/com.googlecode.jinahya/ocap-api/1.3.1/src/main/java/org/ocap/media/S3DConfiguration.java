package org.ocap.media;

/**
 *  This interface represents a 3D frame packing payload as defined in [OCCEP].
 */
public interface S3DConfiguration
{
    /**
     * MPEG-2 S3D signaling data defined in
     * [OCCEP].
     */
    public static final int S3D_MPEG2_USER_DATA_TYPE = 2;

    /**
     * AVC SEI payload type for 3D frame packing arrangement defined in [OCCEP].
     */
    public static final int S3D_AVC_SEI_PAYLOAD_TYPE = 1;

    /**
     * Gets the data type of the 3D content.
     * Returns <code>S3D_MPEG2_USER_DATA_TYPE</code> when the stream type is
     *      <code>MPEG2_VIDEO</code>, or <code>S3D_AVC_SEI_PAYLOAD_TYPE</code>
     *      when the stream type is <code>AVC_VIDEO</code>.  Note: other data
     *      types may be added in the future.
     *
     * @return The data type of the 3D content.
     */
    public int getDataType();

    /**
     * Gets the 3D content format type.  See <code>S3DFormatTypes</code>
     * for possible return values.
     *
     * @return The 3D content format type.
     */
    public int getFormatType();

    /**
     * Gets the payload of the 3DTV information description message.  The byte
     * array format will match the definition for the data type returned by the
     * <code>getDataType</code> method.
     */
    public byte [] getPayload();
}
