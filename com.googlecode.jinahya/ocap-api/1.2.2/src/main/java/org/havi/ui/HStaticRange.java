package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Color;
import java.awt.Font;


/**
   The {@link org.havi.ui.HStaticRange HStaticRange} is a user interface
   component used to display a static value which is within a fixed
   range, but does <i>not</i> permit the user to navigate (focus) upon it. By
   default it uses the {@link org.havi.ui.HRangeLook HRangeLook} to render
   itself.

  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr>
  <td>x</td>
  <td>x-coordinate of top left hand corner of this component in pixels, 
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>y</td>
  <td>y-coordinate of top left hand corner of this component in pixels,
  relative to its parent container (subject to layout management).</td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>width</td>
  <td>width of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>
  <tr>
  <td>height</td>
  <td>height of this component in pixels (subject to layout management). 
  </td>
  <td>---</td>
  <td>java.awt.Component#setBounds</td>
  <td>java.awt.Component#getBounds</td>
  </tr>

  

<tr>
	<td>orientation</td>
	<td>The &quot;orientation&quot; of the range object.</td>
	<td>{@link HStaticRange#ORIENT_LEFT_TO_RIGHT ORIENT_LEFT_TO_RIGHT}</td>
	<td>{@link HStaticRange#setOrientation setOrientation}</td>
	<td>{@link HStaticRange#getOrientation getOrientation}</td>
</tr>
<tr>
	<td>minimum</td>
	<td>The minimum value that can be returned by this range object.</td>
	<td>0</td>
	<td>{@link HStaticRange#setRange setRange}</td>
	<td>{@link HStaticRange#getMinValue getMinValue}</td>
</tr>
<tr>
	<td>maximum</td>
	<td>The maximum value that can be returned by this range object.</td>
	<td>100</td>
	<td>{@link HStaticRange#setRange setRange}</td>
	<td>{@link HStaticRange#getMaxValue getMaxValue}</td>
</tr>
<tr>
	<td>value</td>
	<td>The current value  returned by this range object.</td>
	<td>0</td>
	<td>{@link HStaticRange#setValue setValue}</td>
	<td>{@link HStaticRange#getValue getValue}</td>
</tr>

  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
  <tr> 
  <td>Associated matte ({@link org.havi.ui.HMatte HMatte}).</td> 
  <td>none (i.e. getMatte() returns <code>null</code>)</td> 
  <td>{@link org.havi.ui.HComponent#setMatte setMatte}</td> 
  <td>{@link org.havi.ui.HComponent#getMatte getMatte}</td> 
  </tr>
   <tr>
       <td> The text layout manager responsible for text
       formatting.</td>
       <td> An {@link org.havi.ui.HDefaultTextLayoutManager}
       object.</td>
       <td> {@link org.havi.ui.HVisible#setTextLayoutManager}
       </td>
       <td> {@link org.havi.ui.HVisible#getTextLayoutManager}
       </td>
   </tr>

   <tr>
       <td>The background painting mode</td>
       <td>{@link org.havi.ui.HVisible#NO_BACKGROUND_FILL}</td>

       <td>{@link org.havi.ui.HVisible#setBackgroundMode}</td>
       <td>{@link org.havi.ui.HVisible#getBackgroundMode}</td>
   </tr>

   <tr>
       <td>The default preferred size</td>
       <td>not set (i.e. NO_DEFAULT_SIZE) unless specified by <code>width</code>
       and <code>height</code> parameters</td>
       <td>{@link org.havi.ui.HVisible#setDefaultSize}</td>
       <td>{@link org.havi.ui.HVisible#getDefaultSize}</td>
   </tr>

   <tr>
       <td>The horizontal content alignment</td>
       <td>{@link org.havi.ui.HVisible#HALIGN_CENTER}</td>
       <td>{@link org.havi.ui.HVisible#setHorizontalAlignment}</td>
       <td>{@link org.havi.ui.HVisible#getHorizontalAlignment}</td>
   </tr>

   <tr>
       <td>The vertical content alignment</td>
       <td>{@link org.havi.ui.HVisible#VALIGN_CENTER}</td>
       <td>{@link org.havi.ui.HVisible#setVerticalAlignment}</td>
       <td>{@link org.havi.ui.HVisible#getVerticalAlignment}</td>
   </tr>

   <tr>
       <td>The content scaling mode</td>
       <td>{@link org.havi.ui.HVisible#RESIZE_NONE}</td>
       <td>{@link org.havi.ui.HVisible#setResizeMode}</td>
       <td>{@link org.havi.ui.HVisible#getResizeMode}</td>
   </tr>

<tr>
    <td>The border mode</td>
    <td><code>true</code></td>
    <td>{@link org.havi.ui.HVisible#setBordersEnabled}</td>
    <td>{@link org.havi.ui.HVisible#getBordersEnabled}</td>
</tr>




<tr>
  <td>The default &quot;look&quot; for this class.</td>
  <td>A platform specific {@link org.havi.ui.HRangeLook HRangeLook}</td>
  <td>{@link org.havi.ui.HStaticRange#setDefaultLook HStaticRange.setDefaultLook}</td>
  <td>{@link org.havi.ui.HStaticRange#getDefaultLook HStaticRange.getDefaultLook}</td>
</tr>

<tr>
  <td>The &quot;look&quot; for this object.</td>
  <td>The {@link org.havi.ui.HRangeLook HRangeLook} returned from
  HStaticRange.getDefaultLook when this object was created.</td>
  <td>{@link org.havi.ui.HStaticRange#setLook HStaticRange.setLook}</td>
  <td>{@link org.havi.ui.HStaticRange#getLook HStaticRange.getLook}</td>
</tr>
<tr>
	<td>The offsets for the &quot;thumb&quot; of this range control</td>
	<td>min = 0, max = 0</td>
	<td>{@link HStaticRange#setThumbOffsets setThumbOffsets}</td>
	<td>{@link HStaticRange#getThumbMinOffset getThumbMinOffset} / 
            {@link HStaticRange#getThumbMaxOffset getThumbMaxOffset}</td>
</tr>

<tr>
	<td>The behavior of this range object with respect to its 
        &quot;thumb&quot; values</td>
	<td>{@link HStaticRange#SLIDER_BEHAVIOR SLIDER_BEHAVIOR}</td>
	<td>{@link HStaticRange#setBehavior setBehavior}</td>
	<td>{@link HStaticRange#getBehavior getBehavior}</td>
</tr>
  </table>

*/

public class HStaticRange
    extends HVisible 
    implements HNoInputPreferred, HOrientable
{
    /**
     * The {@link org.havi.ui.HStaticRange HStaticRange} shall
     * behave as a slider, i.e. the allowable values that may be
     * set / returned for the {@link org.havi.ui.HStaticRange
     * HStaticRange} shall not be affected by the
     * &quot;thumb&quot; offsets, and hence its value shall be
     * able to vary between <code>[minimum, maximum]</code>.  
     */
    public final static int SLIDER_BEHAVIOR = 0;
    
    /**
     * The {@link org.havi.ui.HStaticRange HStaticRange} shall
     * behave as a scrollbar, i.e. the allowable values that may
     * be set / returned for the {@link org.havi.ui.HStaticRange
     * HStaticRange} shall be affected by the &quot;thumb&quot;
     * offsets, and hence its value shall be able to vary between
     * <code>[minimum + minThumbOffset,  maximum - maxThumbOffset]</code>.  
     */
    public final static int SCROLLBAR_BEHAVIOR = 1;
    
    /**
     * Creates an {@link org.havi.ui.HStaticRange HStaticRange}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HStaticRange()
    {
    }
    
    /**
     * Creates an {@link org.havi.ui.HStaticRange HStaticRange}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HStaticRange(int orientation, int minimum, int maximum, int value,
			int x, int y, int width, int height)
    {
    }
    
    /**
     * Creates an {@link org.havi.ui.HStaticRange HStaticRange}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HStaticRange(int orientation, int minimum, int maximum, int value)
    {
    }
    
    /**
     * Sets the {@link org.havi.ui.HLook HLook} for this component.
     *
     * @param hlook The {@link org.havi.ui.HLook HLook} that is to be
     * used for this component.
     * Note that this parameter may be null, in which case the
     * component will not draw itself until a look is set.
     * @exception HInvalidLookException If the Look is not an {@link
     * org.havi.ui.HRangeLook HRangeLook}.  
     */
    public void setLook(HLook hlook) throws HInvalidLookException
    {
    }

    /**
     * Sets the default {@link org.havi.ui.HLook HLook} for further
     * {@link org.havi.ui.HStaticRange HStaticRange} Components.
     * 
     * @param look The {@link org.havi.ui.HLook HLook} that will be
     * used by default when creating a new {@link
     * org.havi.ui.HStaticRange HStaticRange} component.
     * Note that this parameter may be null, in which case newly
     * created components shall not draw themselves until a non-null
     * look is set using the {@link
     * org.havi.ui.HStaticRange#setLook setLook} method.
     */
    public static void setDefaultLook(HRangeLook look) 
    {
    }

    /**
     * Returns the currently set default {@link org.havi.ui.HLook
     * HLook} for {@link org.havi.ui.HStaticRange HStaticRange}
     * components.
     * 
     * @return The {@link org.havi.ui.HLook HLook} that is used by
     * default when creating a new {@link org.havi.ui.HStaticRange
     * HStaticRange} component.  
     */
    public static HRangeLook getDefaultLook()
    {
        return (null);
    }

    /**
     * Retrieve the orientation of the {@link org.havi.ui.HStaticRange
     * HStaticRange}. The orientation controls how the associated
     * <code>HLook</code> lays out the component.
     *
     * @return one of {@link
     * org.havi.ui.HOrientable#ORIENT_LEFT_TO_RIGHT
     * ORIENT_LEFT_TO_RIGHT}, {@link
     * org.havi.ui.HOrientable#ORIENT_RIGHT_TO_LEFT
     * ORIENT_RIGHT_TO_LEFT}, {@link
     * org.havi.ui.HOrientable#ORIENT_TOP_TO_BOTTOM
     * ORIENT_TOP_TO_BOTTOM}, or {@link
     * org.havi.ui.HOrientable#ORIENT_BOTTOM_TO_TOP
     * ORIENT_BOTTOM_TO_TOP}.  
     */
    public int getOrientation()
    {
        return(0);
    }

    /**
     * Set the orientation of the {@link org.havi.ui.HStaticRange
     * HStaticRange}. The orientation controls the layout of the component.
     *
     * @param orient one of {@link
     * org.havi.ui.HOrientable#ORIENT_LEFT_TO_RIGHT
     * ORIENT_LEFT_TO_RIGHT}, {@link
     * org.havi.ui.HOrientable#ORIENT_RIGHT_TO_LEFT
     * ORIENT_RIGHT_TO_LEFT}, {@link
     * org.havi.ui.HOrientable#ORIENT_TOP_TO_BOTTOM
     * ORIENT_TOP_TO_BOTTOM}, or {@link
     * org.havi.ui.HOrientable#ORIENT_BOTTOM_TO_TOP
     * ORIENT_BOTTOM_TO_TOP}.  
     */
    public void setOrientation(int orient)
    {
        return;
    }

    /**
     * Sets the range of values for the control. If the maximum is
     * greater than the minimum and the value of the  control is outside
     * the new range (subject to the control's current behavior), then
     * the value is changed to the closest valid value.
     *
     * @param   minimum The minimum value of the range control
     * @param   maximum The maximum value of the range control
     * @return Indicates if the min and max values have been set
     * correctly.  Returns false if the minimum value is greater than
     * or equal to the maximum value, otherwise returns true 
     */
    public boolean setRange(int minimum, int maximum)
    {
	return (true);
    }
       
    /**
     * Gets the minimum of the range.
     *
     * @return The minimum value for the range
     */
    public int getMinValue()
    {
	return (0);
    }
    
    /**
     * Get the maximum value of the range
     *
     * @return The maximum value of the range.
     */
    public int getMaxValue()
    {
	return (0);
    }
        
    /**
     * Sets the value of the control, subject to its current behavior.
     * If the specified value is not valid, then the method shall round
     * it to the closest valid value. An application can retrieve the
     * corrected value by means of method <code>getValue()</code>.     
     * 
     * @param value the value for this <code>HStaticRange</code>
     * @see org.havi.ui.HStaticRange#SLIDER_BEHAVIOR
     * @see org.havi.ui.HStaticRange#SCROLLBAR_BEHAVIOR 
     */
    public void setValue(int value)
    {
    }
    
    /**
     * Gets the value of the control. Note that the recovered value is
     * subject to the control's current behavior.
     * 
     * @see org.havi.ui.HStaticRange#SLIDER_BEHAVIOR
     * @see org.havi.ui.HStaticRange#SCROLLBAR_BEHAVIOR 
     */
    public int getValue()	
    {
	return (0);
    }

    /**
     * Set the offsets for the &quot;thumb&quot; area on this range
     * control. The &quot;thumb&quot; is then drawn from (value -
     * minOffset), to (value + maxOffset) positions outside of the
     * HRange values [minimum:maximum] are clipped to the closest
     * value.
     * <p>
     * There is no requirement that minOffset == maxOffset. For example, both
     * offsets may be zero, yielding a thermometer-like range
     * object. All measurements are in the same units as the minimum /
     * maximum values on the {@link org.havi.ui.HStaticRange
     * HStaticRange} object. The size of the &quot;thumb&quot; is the
     * application author's responsibility. By default the
     * &quot;thumb&quot; does not affect the range over which the
     * value of the {@link org.havi.ui.HStaticRange HStaticRange} may
     * be modified. It is recommended that the {@link
     * org.havi.ui.HRangeLook HRangeLook} provides mechanisms to
     * denote the value of the {@link org.havi.ui.HStaticRange
     * HStaticRange}, in addition to indicating the extent of the
     * thumb as defined by the offsets.
     * <p>
     * If this control's behavior is <code>SCROLLBAR_BEHAVIOR</code>, then the
     * following rules apply: If the thumb offsets are illegal,
     * i.e. <code>minimum + thumbMinOffset</code> is equal or greater
     * than <code>maximum - thumbMaxOffset</code>, then an
     * <code>IllegalArgumentException</code> shall be thrown. If the
     * control's value is not valid for the specified offsets, then the
     * value shall be changed to the closest valid value.
     * 
     * @see HStaticRange#setBehavior
     */
    public void setThumbOffsets(int minOffset, int maxOffset)
    {
    }
    
    /**
     * Returns the thumb offset for its minimum value.
     * 
     * @return the thumb offset for its minimum value.  
     */
    public int getThumbMinOffset()
    {
	return(0);
    }
    
    /**
     * Returns the thumb offset for its maximum value.
     * 
     * @return the thumb offset for its maximum value.  
     */
    public int getThumbMaxOffset()
    {
	return(0);
    }
    
    /**
     * Sets the behavior for this {@link org.havi.ui.HStaticRange
     * HStaticRange}. If the new behavior is
     * <code>SCROLLBAR_BEHAVIOR</code> and the control's settings for
     * range and thumb offsets are illegal, i.e. <code>minimum +
     * thumbMinOffset</code> is equal or greater than <code>maximum -
     * thumbMaxOffset</code>, then an
     * <code>IllegalArgumentException</code> shall be thrown. If the
     * control's value is not valid for the offsets, then the value
     * shall be changed to the closest valid value.
     * 
     * @param behavior the behavior for this {@link
     * org.havi.ui.HStaticRange HStaticRange} ({@link
     * org.havi.ui.HStaticRange#SLIDER_BEHAVIOR SLIDER_BEHAVIOR} or
     * {@link org.havi.ui.HStaticRange#SCROLLBAR_BEHAVIOR
     * SCROLLBAR_BEHAVIOR}).  
     */
    public void setBehavior(int behavior)
    {
    }
    
    /**
     * Returns the behavior for this {@link org.havi.ui.HStaticRange
     * HStaticRange}.
     * 
     * @return the behavior for this {@link org.havi.ui.HStaticRange
     * HStaticRange}.  
     */
    public int getBehavior()
    {
	return(0);
    }
    
}







