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


  


package java.awt.event;

import java.awt.Adjustable;
import java.awt.AWTEvent;
// import java.awt.Event;

/** 
 * The adjustment event emitted by Adjustable objects.
 * @see java.awt.Adjustable
 * @see AdjustmentListener
 *
 * @author Amy Fowler
 * @version 1.25 01/23/03
 * @since 1.1
 */
public class AdjustmentEvent extends AWTEvent
{
    /** 
     * Marks the first integer id for the range of adjustment event ids.
     */
    public static final int ADJUSTMENT_FIRST = 601;

    /** 
     * Marks the last integer id for the range of adjustment event ids.
     */
    public static final int ADJUSTMENT_LAST = 601;

    /** 
     * The adjustment value changed event.
     */
    public static final int ADJUSTMENT_VALUE_CHANGED = ADJUSTMENT_FIRST;

    /** 
     * The unit increment adjustment type.
     */
    public static final int UNIT_INCREMENT = 1;

    /** 
     * The unit decrement adjustment type.
     */
    public static final int UNIT_DECREMENT = 2;

    /** 
     * The block decrement adjustment type.
     */
    public static final int BLOCK_DECREMENT = 3;

    /** 
     * The block increment adjustment type.
     */
    public static final int BLOCK_INCREMENT = 4;

    /** 
     * The absolute tracking adjustment type.
     */
    public static final int TRACK = 5;

    /** 
     * The adjustable object that fired the event.
     *
     * @serial
     * @see #getAdjustable
     */
     Adjustable adjustable;

    /** 
     * <code>value</code> will contain the new value of the
     * adjustable object.  This value will always be  in a
     * range associated adjustable object.
     *
     * @serial
     * @see #getValue
     */
     int value;

    /** 
     * The <code>adjustmentType</code> describes how the adjustable
     * object value has changed.
     * This value can be increased/decreased by a block or unit amount
     * where the block is associated with page increments/decrements,
     * and a unit is associated with line increments/decrements.
     *
     * @serial
     * @see #getAdjustmentType
     */
     int adjustmentType;

    /** 
     * The <code>isAdjusting</code> is true if the event is one
     * of the series of multiple adjustment events.
     *
     * @since 1.4
     * @serial
     * @see #getValueIsAdjusting
     */
  //boolean isAdjusting;

    /*
     * JDK 1.1 serialVersionUID 
     */
     private static final long serialVersionUID = 5700290645205279921L;

    /** 
     * Constructs an <code>AdjustmentEvent</code> object with the
     * specified <code>Adjustable</code> source, event type,
     * adjustment type, and value. 
     * <p>Note that passing in an invalid <code>id</code> results in
     * unspecified behavior.
     *
     * @param source the <code>Adjustable</code> object where the
     *               event originated
     * @param id     the event type
     * @param type   the adjustment type 
     * @param value  the current value of the adjustment
     */
    public AdjustmentEvent(Adjustable source, int id, int type, int value) { super(null,0); }

    // /** 
     // * Constructs an <code>AdjustmentEvent</code> object with the
     // * specified Adjustable source, event type, adjustment type, and value.
     // * <p>Note that passing in an invalid <code>id</code> results in
     // * unspecified behavior.
     // * 
     // * @param source the <code>Adjustable</code> object where the
     // *               event originated
     // * @param id     the event type
     // * @param type   the adjustment type 
     // * @param value  the current value of the adjustment
     // * @param isAdjusting <code>true</code> if the event is one
     // *               of a series of multiple adjusting events,
     // *               otherwise <code>false</code>
     // */
    // public AdjustmentEvent(Adjustable source, int id, int type, int value,
        // boolean isAdjusting)
    // { }

    /** 
     * Returns the <code>Adjustable</code> object where this event originated.
     *
     * @return the <code>Adjustable</code> object where this event originated
     */
    public Adjustable getAdjustable() { return null; }

    /** 
     * Returns the current value in the adjustment event.
     *
     * @return the current value in the adjustment event
     */
    public int getValue() { return 0; }

    /** 
     * Returns the type of adjustment which caused the value changed
     * event.  It will have one of the following values:
     * <ul>
     * <li>{@link #UNIT_INCREMENT}
     * <li>{@link #UNIT_DECREMENT}
     * <li>{@link #BLOCK_INCREMENT}
     * <li>{@link #BLOCK_DECREMENT}
     * <li>{@link #TRACK}
     * </ul>
     * @return one of the adjustment values listed above
     */
    public int getAdjustmentType() { return 0; }

    // /** 
     // * Returns <code>true</code> if this is one of multiple
     // * adjustment events.
     // *
     // * @return <code>true</code> if this is one of multiple
     // *         adjustment events, otherwise returns <code>false</code>
     // */
    // public boolean getValueIsAdjusting() { }

    public String paramString() { return null; }
}
