/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.awt;

import java.awt.event.*;

/** 
 * The interface for objects which have an adjustable numeric value
 * contained within a bounded range of values.
 *
 * @version 1.15 01/23/03
 * @author Amy Fowler
 * @author Tim Prinzing
 */
public interface Adjustable
{
    /** 
     * Indicates that the <code>Adjustable</code> has horizontal orientation.  
     */
    public static final int HORIZONTAL = 0;

    /** 
     * Indicates that the <code>Adjustable</code> has vertical orientation.  
     */
    public static final int VERTICAL = 1;

   /** 
     * Indicates that the <code>Adjustable</code> has no orientation.  
     */
    public static final int NO_ORIENTATION = 2;

    /** 
     * Gets the orientation of the adjustable object.
     * @return the orientation of the adjustable object;
     *   either <code>HORIZONTAL</code>, <code>VERTICAL</code>,
     *   or <code>NO_ORIENTATION</code>
     */
    public int getOrientation();

    /** 
     * Sets the minimum value of the adjustable object.
     * @param min the minimum value
     */
    public void setMinimum(int min);

    /** 
     * Gets the minimum value of the adjustable object.
     * @return the minimum value of the adjustable object
     */
    public int getMinimum();

    /** 
     * Sets the maximum value of the adjustable object.
     * @param max the maximum value
     */
    public void setMaximum(int max);

    /** 
     * Gets the maximum value of the adjustable object.
     * @return the maximum value of the adjustable object
     */
    public int getMaximum();

    /** 
     * Sets the unit value increment for the adjustable object.
     * @param u the unit increment
     */
    public void setUnitIncrement(int u);

    /** 
     * Gets the unit value increment for the adjustable object.
     * @return the unit value increment for the adjustable object
     */
    public int getUnitIncrement();

    /** 
     * Sets the block value increment for the adjustable object.
     * @param b the block increment
     */
    public void setBlockIncrement(int b);

    /** 
     * Gets the block value increment for the adjustable object.
     * @return the block value increment for the adjustable object
     */
    public int getBlockIncrement();

    /** 
     * Sets the length of the proportional indicator of the
     * adjustable object.
     * @param v the length of the indicator
     */
    public void setVisibleAmount(int v);

    /** 
     * Gets the length of the proportional indicator.
     * @return the length of the proportional indicator
     */
    public int getVisibleAmount();

    /** 
     * Sets the current value of the adjustable object. This
     * value must be within the range defined by the minimum and
     * maximum values for this object.
     * @param v the current value 
     */
    public void setValue(int v);

    /** 
     * Gets the current value of the adjustable object.
     * @return the current value of the adjustable object
     */
    public int getValue();

    /** 
     * Adds a listener to receive adjustment events when the value of
     * the adjustable object changes.
     * @param l the listener to receive events
     * @see AdjustmentEvent
     */
    public void addAdjustmentListener(AdjustmentListener l);

    /** 
     * Removes an adjustment listener.
     * @param l the listener being removed
     * @see AdjustmentEvent
     */
    public void removeAdjustmentListener(AdjustmentListener l);
}
