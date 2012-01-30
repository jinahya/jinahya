package org.dvb.si;

/**
 * This interface defines the constants corresponding to the different stream types
 * @see PMTElementaryStream
 * @see PMTElementaryStream#getStreamType
*/

public interface PMTStreamType {

    /** Constant value for the stream type as specified in ISO/IEC 13818-1
      */
    public static final byte MPEG1_VIDEO = 1;

    /** Constant value for the stream type as specified in ISO/IEC 13818-1
      */
    public static final byte MPEG2_VIDEO = 2;

    /** Constant value for the stream type as specified in ISO/IEC 13818-1
      */
    public static final byte MPEG1_AUDIO = 3;

    /** Constant value for the stream type as specified in ISO/IEC 13818-1
      */
    public static final byte MPEG2_AUDIO = 4;
}


