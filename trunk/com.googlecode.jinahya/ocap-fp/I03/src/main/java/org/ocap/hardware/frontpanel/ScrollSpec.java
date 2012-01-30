package org.ocap.hardware.frontpanel;

/**
 * This interface represents th scrolling specification of a front panel
 * text display.  In a single line display text will scroll from right
 * to left.  In a multi-line display text will scroll from bottom to top.
 * In this mode either text lines must not exceed the length of the display
 * or wrap must be turned on.
 */
public interface ScrollSpec
{
    /**
     * Gets the maximum number of times per minute characters can
     * scroll right to left across the display with zero hold time set.
     *
     * @return Number of horizontal scroll iterations per minute.  Returns
     *      zero if horizontal scroll is not supported.
     */
    public int getMaxHorizontalIterations();

    /**
     * Gets the maximum number of times per minute characters can
     * scroll bottom to top across the display with zero hold time set.
     *
     * @return Number of vertical scroll iterations per minute.  Returns -1
     *      if the display only supports one row.  Returns zero if vertical
     *      scroll is not supported.
     */
    public int getMaxVerticalIterations();

    /**
     * Gets the number of times per minute the characters are set to scroll
     * across the screen from right to left.
     *
     * @return Number of horizontal scroll iterations per minute.  A value of
     *      0 indicates horizontal scrolling is turned off.  A value of -1
     *      indicates there is more than one row displayed and characters
     *      will scroll vertically.
     */
    public int getHorizontalIterations();

    /**
     * Gets the number of times per minute the characters are set to scroll
     * across the screen from bottom to top.
     *
     * @return Number of vertical scroll iterations per minute.  A value of
     *      0 indicates vertical scrolling is turned off.  A value of -1
     *      indicates there is only one row of characters displayed and
     *      characters will scroll horizontally.
     */
    public int getVerticalIterations();

    /**
     * Sets the number of times per minute one character will scroll across
     * the display from right to left.
     *
     * @param iterations Number of horizontal scroll iterations per minute.
     *
     * @throws IllegalArgumentException if the iteration is negative or
     *      exceed the value returned by
     *      <code>getMaxHorizontalIterations</code>.
     */
    public void setHorizontalIterations(int iterations);

    /**
     * Sets the number of times per minute one character will scroll across
     * the display from bottom to top.
     *
     * @param iterations Number of vertical scroll iterations per minute.
     *
     * @throws IllegalArgumentException if the iteration is negative or
     *      exceed the value returned by
     *      <code>getMaxVerticalalIterations</code>.
     */
    public void setVerticalIterations(int iterations);

    /**
     * Gets the percentage of time the scroll will hold at each character
     * during one scroll iteration.
     *
     * @return Character hold duration.
     */
    public int getHoldDuration();

    /**
     * Sets the percentage of time to hold at each character before
     * scrolling it to the next position during one scroll iteration.
     *
     * @param duration Character hold percentage duration.  Setting this
     *      value causes a smooth scroll across all characters without a
     *      hold on any of them.
     *
     * @throws IllegalArgumentException if duration is negative or if the
     *      duration percentage is greater than 100 divided by the number
     *      of characters to scroll across during horizontal scroll, or
     *      the number of rows to scroll across during vertical scroll.
     */
    public void setHoldDuration(int duration);

}
