package org.ocap.hardware.frontpanel;

/**
 * This interface represents the front panel display blinking
 * specification. If BlinkSpec is for {@link TextDisplay}, all characters blink 
 * at the same time.
 */
public interface BlinkSpec
{
    /**
     * Gets the number of times per minute the display will blink.
     *
     * @return Number of blink iterations per minute.
     */
    public int getIterations();

    /**
     * Gets the maximum number of times per minute all segments in an LED can
     * blink.
     *
     * @return Maximum number of blink iterations per minute.  Returns zero
     *      if blinking is not supported.
     */
    public int getMaxCycleRate();

    /**
     * Sets the number of times per minute data will blink across all of the
     * LEDs.
     *
     * @param iterations Number of blink iterations per minute.
     *
     * @throws IllegalArgumentException if the iteration is negative or cannot
     *      be supported by the front panel.
     */
    public void setIterations(int iterations);

    /**
     * Gets the percentage of time the text will be on during one blink
     * iteration.
     *
     * @return Text display blink on percentage duration.
     */
    public int getOnDuration();

    /**
     * Sets the percentage of time the text display will remain on
     * during one blink iteration.
     *
     * @param duration Text display blink on percentage duration.  Setting
     *      this value to 100 sets the display no solid, no blinking.  Setting
     *      this value to 0 effectively turns off the front panel display.
     *      Subtracting this value from 100 yields the percentage of off
     *      time during one blink iteration.
     *
     * @throws IllegalArgumentException if the duration is negative or
     *      exceeds 100.
     */
    public void setOnDuration(int duration);

}
