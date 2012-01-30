/*
 * StreamType.java
 */
package org.ocap.si;

/**
 * This interface represents valid values for the stream_type field 
 * in the PMT, and returned by the getStreamType method from an 
 * implemented object of the ProgramMapTable interface. 
 */
public interface StreamType
{
    /**
     * ISO/IEC 11172-2 Video.
     */
    public static final short MPEG_1_VIDEO = 0x01;

    /**
     * ITU-T Rec. H.262 ISO/IEC 13818-2 Video or ISO/IEC 11172-2 
     * constrained parameter video
     * stream.
     */
    public static final short MPEG_2_VIDEO = 0x02;

    /**
     * ISO/IEC 11172-3 Audio.
     */
    public static final short MPEG_1_AUDIO = 0x03;

    /**
     * ISO/IEC 13818-3 Audio.
     */
    public static final short MPEG_2_AUDIO = 0x04;

    /**
     * ITU-T Rec. H.222.0 ISO/IEC 13818-1 private-sections.
     */
    public static final short MPEG_PRIVATE_SECTION = 0x05;

    /**
     * ITU-T Rec. H.222.0 ISO/IEC 13818-1 PES packets containing private data.
     */
    public static final short MPEG_PRIVATE_DATA = 0x06;

    /**
     * ISO/IEC 13522 MHEG.
     */
    public static final short MHEG = 0x07;

    /**
     * 13818-1 Annex A - DSM CC.
     */
    public static final short DSM_CC = 0x08;

    /**
     * ITU-T Rec. H.222.1.
     */
    public static final short H_222 = 0x09;

    /**
     * ISO/IEC 13818-6 type A (Multi-protocol Encapsulation).
     */
    public static final short DSM_CC_MPE = 0x0A;

    /**
     * ISO/IEC 13818-6 type B (DSM-CC U-N Messages).
     */
    public static final short DSM_CC_UN = 0x0B;

    /**
     * ISO/IEC 13818-6 type C (DSM-CC Stream Descriptors).
     */
    public static final short DSM_CC_STREAM_DESCRIPTORS = 0x0C;

    /**
     * ISO/IEC 13818-6 type D (DSM-CC Sections any type, including private data).
     */
    public static final short DSM_CC_SECTIONS = 0x0D;

    /**
     * ISO/IEC 13818-1 auxiliary.
     */
    public static final short AUXILIARY = 0x0E;

    /**
     * DigiCipher II video.
     */
    public static final short VIDEO_DCII = 0x80;

    /**
     * ATSC A/53 audio (ATSC Standard A/53, 1995, ATSC Digital Television 
     * Standard).
     */
    public static final short ATSC_AUDIO = 0x81;

    /**
     * Standard subtitle.
     */
    public static final short STD_SUBTITLE = 0x82;

    /**
     * Isochronous data (Data Service Extensions for MPEG-2 Transport, 
     * STD-096-011, Rev 1.0, General Instrument).
     */
    public static final short ISOCHRONOUS_DATA = 0x83;

    /**
     * Asynchronous data (Data Service Extensions for MPEG-2 Transport, 
     * STD-096-011, Rev 1.0, General Instrument).
     */
    public static final short ASYNCHRONOUS_DATA = 0x84;
}




