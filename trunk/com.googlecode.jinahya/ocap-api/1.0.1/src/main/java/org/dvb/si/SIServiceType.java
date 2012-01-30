package org.dvb.si;

/**
 * This interface defines constants corresponding to the different
 * service types.
 * 
 * @see SIService#getSIServiceType
 */

public interface SIServiceType
{
    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short UNKNOWN = -1;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short DIGITAL_TELEVISION = 0x01;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short DIGITAL_RADIO_SOUND = 0x02;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short TELETEXT = 0x03;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short NVOD_REFERENCE = 0x04;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short NVOD_TIME_SHIFTED = 0x05;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short MOSAIC = 0x06;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short PAL = 0x07;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short SECAM = 0x08;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short D_D2_MAC = 0x09;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short FM_RADIO = 0x0A;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short NTSC = 0x0B;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short DATA_BROADCAST = 0x0C;

    /** Constant value for the service type as specified in EN 300 468
      */
    public final static short MHP_APPLICATION = 0x10;
}

