package org.ocap.hardware.frontpanel;

/**
 * This interface represents the front panel display Color
 * specification. 
 */
public interface ColorSpec
{
    /*
     * Standardized indicator colors.  Host devices MAY include addition
     * implementation specific colors.
     */
    /** Indicator color blue. */
    public final static byte BLUE   = 0x01;
    /** Indicator color green. */
    public final static byte GREEN  = 0x02;
    /** Indicator color red. */
    public final static byte RED    = 0x04;
    /** Indicator color yellow. */
    public final static byte YELLOW = 0x08;
    /** Indicator color orange. */
    public final static byte ORANGE = 0x10;

    /**
     * Gets the current color of the indicator.  See definitions of
     * {@link #BLUE}, {@link #GREEN},{@link #RED}, 
     * {@link #YELLOW}, and {@link #ORANGE} for possible
     * values.
     *
     * @return Indicator color.
     */
    public byte getColor();

    /**
     * Gets the supported colors.  The value returned SHALL contain values
     * for the possible color set OR'ed together. {@link #BLUE}, 
     * {@link #GREEN}, {@link #RED}, {@link #YELLOW}, and {@link #ORANGE} 
     * for possible values.
     *
     * @return Supported color set.
     */
    public byte getSupportedColors();

    /**
     * Sets the color of this indicator.
     *
     * @param color Indicator color.
     *
     * @throws IllegalArgumentException if the value of the color parameter
     *      is not in the supported color set.
     */
    public void setColor(byte color) throws IllegalArgumentException;
}
