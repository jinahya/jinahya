package org.ocap.hardware.frontpanel;

import org.davic.resources.ResourceProxy;

/**
 * This interface represents an indicator in the front panel display and
 * allows its properties to be checked and set.
 */
public interface Indicator extends ResourceProxy
{
    /**
     * Gets the brightness specification for the front panel Indicator.
     * Changing values within the object returned by this method does not
     * take affect until one of the set methods in this interface
     * is called and the object is passed to the implementation.
     *
     * @return LED front panel brightness specification.
     */
    public BrightSpec getBrightSpec();

    /**
     * Sets the Brightness specification for the front panel Indicator.
     *
     * @param brightSpec Brightness specification. 
     *
     * @throws IllegalArgumentException if null is passed in.
     *
     * @throws IllegalStateException if the Indicator resource was lost.
     */
    public void setBrightSpec( BrightSpec brightSpec ) throws IllegalStateException;

    /**
     * Gets the Color specification for the front panel Indicator.
     * Changing values within the object returned by this method does not
     * take affect until one of the set methods in this interface
     * is called and the object is passed to the implementation.
     *
     * @return LED front panel Color specification or MAY return null if
     *       changing the color is not supported.
     */
    public ColorSpec getColorSpec();

    /**
     * Sets the Color specification for the front panel Indicator.
     *
     * @param colorSpec Color specification if color is desired.
     *
     * @throws IllegalArgumentException if null is passed in.
     *
     * @throws IllegalStateException if the Indicator resource was lost.
     */
    public void setColorSpec( ColorSpec colorSpec ) throws IllegalStateException;

    /**
     * Gets the blink specification for the front panel Indicator.
     * Changing values within the object returned by this method does not
     * take affect until one of the set display methods in this interface
     * is called and the object is passed to the implementation.
     *
     * @return LED front panel blink specification or MAY return null if
     *       blinking is not supported.
     */
    public BlinkSpec getBlinkSpec();

    /**
     * Sets the blink specification for the front panel Indicator.
     *
     * @param blinkSpec Blink specification if blinking is desired.  A value
     *      of null turns blinking off.
     *
     * @throws IllegalStateException if the Indicator resource was lost.
     */
    public void setBlinkSpec( BlinkSpec blinkSpec ) throws IllegalStateException;

}
