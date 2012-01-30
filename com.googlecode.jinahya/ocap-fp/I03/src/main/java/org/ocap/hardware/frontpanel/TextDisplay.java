package org.ocap.hardware.frontpanel;

import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;

/**
 * This interface represents one line of characters in a front panel display.
 */
public interface TextDisplay extends ResourceProxy
{
    /*
     * Modes.
     */
    /**
     * This line will display the network time using a standard 12 hour
     * HH:MM format.
     */
    public final static byte TWELVE_HOUR_CLOCK       = 0x00;

    /**
     * This line will display the network time using a military 24 hour
     * HH:MM format.
     */
    public final static byte TWENTYFOUR_HOUR_CLOCK   = 0x01;

    /**
     * The line can be set using a string of displayable characters.
     */
    public final static byte STRING_MODE             = 0x02;


    /**
     * Gets the brightness specification for the front panel text display.
     * Changing values within the object returned by this method does not
     * take affect until one of the set methods in this interface
     * is called and the object is passed to the implementation.
     *
     * @return LED front panel brightness specification.
     */
    public BrightSpec getBrightSpec();

    /**
     * Gets the Color specification for the front panel text display.
     * Changing values within the object returned by this method does not
     * take affect until one of the set methods in this interface
     * is called and the object is passed to the implementation.
     *
     * @return LED front panel Color specification or MAY return null if
     *       changing the color is not supported.
     */
    public ColorSpec getColorSpec();

    /**
     * Gets the blink specification for the front panel text display.
     * Changing values within the object returned by this method does not
     * take affect until one of the set display methods in this interface
     * is called and the object is passed to the implementation.
     *
     * @return LED front panel blink specification or MAY return null if
     *       blinking is not supported.
     */
    public BlinkSpec getBlinkSpec();

    /**
     * Gets the scroll specification for the front panel text display.
     * Changing values within the object returned by this method does not
     * take affect until one of the set display methods in this interface
     * is called and the object is passed to the implementation.
     *
     * @return LED front panel scroll specification.
     */
    public ScrollSpec getScrollSpec();


    /**
     * Gets the text display mode.  See definitions of
     * {@link #TWELVE_HOUR_CLOCK}, {@link #TWENTYFOUR_HOUR_CLOCK},
     * and {@link #STRING_MODE} for possible return values.
     *
     * @return Text display mode.
     */
    public int getMode();

    /**
     * Gets the number of columns (characters) per line in the text display.
     * The text is considered fixed font by this method.  Dynamic font sizes
     * can be supported and the calculation for this method uses the
     * largest character size for the given font.
     *
     * @return Number of columns.
     */
    public int getNumberColumns();

    /**
     * Gets the number of rows (i.e. lines) in the text display.
     *
     * @return Number of rows.
     */
    public int getNumberRows();

    /**
     * Gets the set of characters supported by the display.  This API does
     * not contain font support and this method is the only way to discover
     * the character set supported by the front panel.  In addition,
     * certain types of displays do not support the entire alphabet or
     * symbol set, e.g. seven segment LEDs.
     *
     * @return Supported character set.
     */
    public String getCharacterSet();

    /**
     * Displays the current system time on the front panel text display.
     * The display is formatted to the mode parameter.
     *
     * @param mode One of the clock modes; {@link #TWELVE_HOUR_CLOCK}, or
     *      {@link #TWENTYFOUR_HOUR_CLOCK}.
     * @param blinkSpec Blink specification if blinking is desired.  A value
     *      of null turns blinking off.
     * @param scrollSpec Scroll specification if scrolling is desired.  A
     *      value of null turns scrolling off.  If there is only one line
     *      of text scrolling will be from right to left.  If there is more
     *      than one line of text scrolling will be from bottom to top.
     *      Passing in null turns scrolling off.
     * @param brightSpec Brightness specification if a change in brightness 
     *      is desired.  A value of null results in no change to current 
     *      brightness.  
     * @param colorSpec Color specification if a change in color 
     *      is desired.  A value of null results in no change to current color.  
     *
     * @throws IllegalArgumentException if the mode parameter is not one of
     *      {@link #TWELVE_HOUR_CLOCK}, {@link #TWENTYFOUR_HOUR_CLOCK}.
     *
     * @throws IllegalStateException if the TextDisplay resource was lost.
     */
    public void setClockDisplay(byte mode,
                                BlinkSpec blinkSpec,
                                ScrollSpec scrollSpec,
                                BrightSpec brightSpec,
                                ColorSpec colorSpec) throws IllegalStateException;

    /**
     * Displays text on the front panel display.  If multiple fonts are
     * possible the implementation SHALL determine which will be used.
     * Sets the LED front panel to the text mode; see {@link #STRING_MODE}.
     * The text parameter will be used to display text characters in the
     * display.  Wrapping occurs if there is more than one line, wrapping
     * is turned on, and the text over-fills at least one line.
     *
     * @param text String of characters to display.  Each string in the array
     *      represents a line of text.  text[0] represents the top line,
     *      text[1] represents the next line down, and so forth.
     * @param blinkSpec Blink specification if blinking is desired.  Passing
     *      in null turns blinking off.
     * @param scrollSpec Scroll specification if scrolling is desired.  If
     *      there is only one line of text scrolling will be from right to
     *      left.  If there is more than one line of text scrolling will be
     *      from bottom to top.  Passing in null turns scrolling off.
     * @param brightSpec Brightness specification if a change in brightness 
     *      is desired.  A value of null results in no change to current 
     *      brightness.  
     * @param colorSpec Color specification if a change in color 
     *      is desired.  A value of null results in no change to current color.  
     *
     * @throws IllegalArgumentException if the text array contains more than
     *      1 line and one or more lines are longer than the display and
     *      wrap is turned off.
     *
     * @throws IllegalStateException if the TextDisplay resource was lost.
     *
     */
    public void setTextDisplay(String [] text,
                               BlinkSpec blinkSpec,
                               ScrollSpec scrollSpec,
                               BrightSpec brightSpec,
                               ColorSpec colorSpec) throws IllegalStateException;

    /**
     * Sets wrapping on or off.
     *
     * @param wrap If wrap is true wrapping is turned on, otherwise wrapping
     *      is turned off.
     */
    public void setWrap(boolean wrap);

    /**
     * Removes characters from the text display.
     *
     */
    public void eraseDisplay();
}
