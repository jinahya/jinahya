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
     * ISO/IEC 13818-7 AAC Audio with ADTS transport syntax
     */
    public static final short AAC_ADTS_AUDIO = 0x0F;

    /**
     * ISO/IEC 14496-2 Visual
     */
    public static final short ISO_14496_VISUAL = 0x10;

    /**
     * ISO/IEC 14496-3 and ISO/IEC 13818-7 AAC Audio with the LATM transport syntax as defined in ISO/IEC 14496-3/AMD-1
     */
    public static final short AAC_AUDIO_LATM = 0x11;

    /**
     * ISO/IEC 14496-1 SL-packetized stream or FlexMux stream carried in PES packets
     */
    public static final short FLEXMUX_PES = 0x12;

    /**
     * ISO/IEC 14496-1 SL-packetized stream or FlexMux stream carried in ISO/IEC14496_sections
     */
    public static final short FLEXMUX_SECTIONS = 0x13;

    /**
     * ISO/IEC 13818-6 Synchronized Download Protocol
     */
    public static final short SYNCHRONIZED_DOWNLOAD = 0x14;

    /**
     * Metadata carried in PES packets
     */
    public static final short METADATA_PES = 0x15;

    /**
     * Metadata carried in metadata_sections
     */
    public static final short METADATA_SECTIONS = 0x16;

    /**
     * Metadata carried in ISO/IEC 13818-6 Data Carousel
     */
    public static final short METADATA_DATA_CAROUSEL = 0x17;

    /**
     * Metadata carried in ISO/IEC 13818-6 Object Carousel
     */
    public static final short METADATA_OBJECT_CAROUSEL = 0x18;

    /**
     * Metadata carried in ISO/IEC 13818-6 Synchronized Download Protocol
     */
    public static final short METADATA_SYNCH_DOWNLOAD = 0x19;

    /**
     * IPMP stream (defined in ISO/IEC 13818-11, MPEG-2 IPMP)
     */
    public static final short MPEG_2_IPMP = 0x1A;

    /**
     * AVC video stream as defined in ITU-T Rec. H.264 | ISO/IEC 14496-10
     */
    public static final short AVC_VIDEO = 0x1B;

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
     * Isochronous data (Methods for Isochronous Data Services Transport,
     * ANSI/SCTE 19 2006)
     */
    public static final short ISOCHRONOUS_DATA = 0x83;

    /**
     * Asynchronous data (Methods for Asynchronous Data Services Transport, 
     * ANSI/SCTE 53 2008) 
     */
    public static final short ASYNCHRONOUS_DATA = 0x84;


    /**
     * Enhanced ATSC A/53 audio (Enhanced AC-3 Audio, ATSC Standard A/53,
     * 2007).
     */
    public static final short ENHANCED_ATSC_AUDIO = 0x87;
}
