package org.ocap.hardware.frontpanel;

/**
 * This interface represents the front panel display brightness
 * specification. If the Indicator supports just turn on/off, the brightness 
 * value {@link #OFF} represents turn off and value 1 represents turn on, and 
 * a brightness level shall be 2. If the Indicator supports bright/dark/off 
 * brightness, the brightness value {@link #OFF} represents turn off and 
 * value 1 represents dark and value 2 represents bright, and a brightness 
 * level shall be 3. The brightness level can be any levels corresponding to 
 * the Indicator's capability. 
 */
public interface BrightSpec
{
    /** Brightness OFF setting that represents the Indicator is off. */
    public final static int OFF    = 0x00;
    
    /**
     * Gets the current brightness of the Indicator.  Possible return values 
     * shall be an integer that meets: 
     * <BR> {@link #OFF} =< brightness =< getBrightnessLevels()-1
     *
     * @return a current brightness value of Indicator.
     */
    public int getBrightness();

    /**
     * Gets the number of brightness levels supported.  The minimum support
     * brightness level SHALL be 2, i.e., two levels that includes {@link #OFF} 
     * and brightness=1 (on). This provides an on/off capability. 
     *
     * @return Supported indicator brightness levels.
     */
    public int getBrightnessLevels();

    /**
     * Sets the brightness of the indicator.  Setting the brightness
     * level to {@link #OFF} turns the indicator off.
     *
     * @param brightness Indicator brightness.
     *
     * @throws IllegalArgumentException if the brightness value is not 
     *         an integer that meets: 
     *         <BR> {@link #OFF} =< brightness =< getBrightnessLevels()-1
     */
    public void setBrightness(int brightness) throws IllegalArgumentException;

}
