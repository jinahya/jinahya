package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Graphics;


/**
   The {@link org.havi.ui.HRange HRange} is a user interface
   component used to display a value within a fixed range (as 
   org.havi.ui.HStaticRange HStaticRange}) which also enables a user to
   navigate to it, i.e. it can have the input focus.  By default it
   uses the {@link org.havi.ui.HRangeLook HRangeLook} to render itself.

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
  <td>{@link org.havi.ui.HRange#setDefaultLook HRange.setDefaultLook}</td>
  <td>{@link org.havi.ui.HRange#getDefaultLook HRange.getDefaultLook}</td>
</tr>

<tr>
  <td>The &quot;look&quot; for this object.</td>
  <td>The {@link org.havi.ui.HRangeLook HRangeLook} returned from
  HRange.getDefaultLook when this object was created.</td>
  <td>{@link org.havi.ui.HRange#setLook HRange.setLook}</td>
  <td>{@link org.havi.ui.HRange#getLook HRange.getLook}</td>
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
<tr>
  <td>The gain focus sound.</td>
  <td>null </td>
  <td>{@link org.havi.ui.HRange#setGainFocusSound setGainFocusSound}</td>
  <td>{@link org.havi.ui.HRange#getGainFocusSound getGainFocusSound}</td>
</tr>
<tr>
  <td>The lose focus sound. </td>
  <td>null </td>
  <td>{@link org.havi.ui.HRange#setLoseFocusSound setLoseFocusSound}</td>
  <td>{@link org.havi.ui.HRange#getLoseFocusSound getLoseFocusSound}</td>
</tr>
  </table>

  @see HStaticRange
  @see HNavigable
*/

public class HRange
    extends HStaticRange
    implements HNavigable
{    
    /**
     * Creates an {@link org.havi.ui.HRange HRange} object. See the
     * class description for details of constructor parameters and
     * default values.  
     */
    public HRange()
    {
    }

    /**
     * Creates an {@link org.havi.ui.HRange HRange} object. See the
     * class description for details of constructor parameters and
     * default values.
     */
    public HRange(int orientation, int minimum, int maximum, int value,
                  int x, int y, int width, int height)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HRange HRange} object. See the
     * class description for details of constructor parameters and
     * default values.
     */
    public HRange(int orientation, int minimum, int maximum, int value)
    {
    }

    /**
     * Sets the default {@link org.havi.ui.HLook HLook} for further
     * {@link org.havi.ui.HRange HRange} Components.
     *
     * @param look The {@link org.havi.ui.HLook HLook} that will be
     * used by default when creating a new {@link org.havi.ui.HRange
     * HRange} component.
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
     * HLook} for {@link org.havi.ui.HRange HRange} components.
     *
     * @return The {@link org.havi.ui.HLook HLook} that is used by
     * default when creating a new {@link org.havi.ui.HRange HRange}
     * component.  
     */
    public static HRangeLook getDefaultLook()
    {
        return (null);
    }

    /**
     * Defines the navigation path from the current {@link
     * org.havi.ui.HNavigable HNavigable} to another {@link
     * org.havi.ui.HNavigable HNavigable} when a particular key is
     * pressed. <p> Note that {@link
     * org.havi.ui.HNavigable#setFocusTraversal setFocusTraversal} is
     * equivalent to multiple calls to {@link
     * org.havi.ui.HNavigable#setMove setMove}, where the key codes
     * <code>VK_UP</code>, <code>VK_DOWN</code>, <code>VK_LEFT</code>,
     * <code>VK_RIGHT</code> are used.
     * 
     * @param keyCode The key code of the pressed key.  Any numerical
     * keycode is allowed, but the platform may not be able to
     * generate all keycodes. Application authors should only use keys
     * for which <code>HRcCapabilities.isSupported()</code> or
     * <code>HKeyCapabilities.isSupported()</code> returns true.
     * @param target The target {@link org.havi.ui.HNavigable
     * HNavigable} object that should be navigated to. If a target is
     * to be removed from a particular navigation path, then
     * <code>null</code> shall be specified.  
     */
    public void setMove(int keyCode, HNavigable target)
    {
        return;
    }

    /**
     * Provides the {@link org.havi.ui.HNavigable HNavigable} object
     * that is navigated to when a particular key is pressed.
     * 
     * @param keyCode The key code of the pressed key.
     * @return Returns the {@link org.havi.ui.HNavigable HNavigable}
     * object or <code>null</code> if no {@link org.havi.ui.HNavigable
     * HNavigable} is associated with the keyCode.
     */
    public HNavigable getMove(int keyCode)
    {
        return(null);
    }

    /**
     * Set the focus control for an {@link org.havi.ui.HNavigable
     * HNavigable} component. Note {@link
     * org.havi.ui.HNavigable#setFocusTraversal setFocusTraversal} is a
     * convenience function for application programmers where a standard
     * up, down, left and right focus traversal between components is
     * required.  
     * <p> 
     * Note {@link org.havi.ui.HNavigable#setFocusTraversal
     * setFocusTraversal} is equivalent to multiple calls to {@link
     * org.havi.ui.HNavigable#setMove setMove}, where the key codes
     * VK_UP, VK_DOWN, VK_LEFT, VK_RIGHT are used.  <p> Note that this
     * API does not prevent the creation of &quot;isolated&quot;
     * {@link org.havi.ui.HNavigable HNavigable} components --- authors
     * should endeavor to avoid confusing the user.
     * 
     * @param up The {@link org.havi.ui.HNavigable HNavigable} component
     * to move to, when the user generates a VK_UP KeyEvent. If there is no {@link
     * org.havi.ui.HNavigable HNavigable} component to move
     * &quot;up&quot; to, then null shall be specified.
     * @param down The {@link org.havi.ui.HNavigable HNavigable}
     * component to move to, when the user generates a VK_DOWN KeyEvent. If there
     * is no {@link org.havi.ui.HNavigable HNavigable} component to move
     * &quot;down&quot; to, then null shall be specified.
     * @param left The {@link org.havi.ui.HNavigable HNavigable}
     * component to move to, when the user generates a VK_LEFT KeyEvent. If there
     * is no {@link org.havi.ui.HNavigable HNavigable} component to move
     * &quot;left&quot; to, then null shall be specified.
     * @param right The {@link org.havi.ui.HNavigable HNavigable}
     * component to move to, when the user generates a VK_RIGHT KeyEvent. If there
     * is no {@link org.havi.ui.HNavigable HNavigable} component to move
     * &quot;right&quot; to, then null shall be specified.  
     */
    public void setFocusTraversal(HNavigable up, HNavigable down, HNavigable left, HNavigable right)
    {
        return;
    }

    /**
     * Indicates if this component has focus. 
     * 
     * @return <code>true</code> if the component has focus, otherwise
     * returns <code>false</code>.  
     */
    public boolean isSelected()
    {
        return(false);
    }

    /**
     * Associate a sound with gaining focus, i.e. when the {@link
     * org.havi.ui.HNavigable HNavigable} receives a
     * {@link org.havi.ui.event.HFocusEvent HFocusEvent} event of type
     * <code>FOCUS_GAINED</code>. This sound will start to be played
     * when an object implementing this interface gains focus. It is
     * not guaranteed to be played to completion. If the object
     * implementing this interface loses focus before the audio
     * completes playing, the audio will be truncated.  Applications
     * wishing to ensure the audio is always played to completion must
     * implement special logic to slow down the focus transitions. <p>
     * By default, an {@link org.havi.ui.HNavigable HNavigable} object
     * does not have any gain focus sound associated with it. <p> Note
     * that the ordering of playing sounds is dependent on the order
     * of the focus lost, gained events.
     * 
     * @param sound the sound to be played, when the component gains
     * focus. If sound content is already set, the original content is
     * replaced. To remove the sound specify a null {@link
     * org.havi.ui.HSound HSound}.  
     */
    public void setGainFocusSound(HSound sound)
    {
        return;
    }

    /**
     * Associate a sound with losing focus, i.e. when the {@link
     * org.havi.ui.HNavigable HNavigable} receives a
     * {@link org.havi.ui.event.HFocusEvent HFocusEvent} event of type
     * FOCUS_LOST. This sound will start to be played when an object 
     * implementing this interface loses focus. It is not guaranteed to be 
     * played to completion. It is implementation dependent whether and when 
     * this sound will be truncated by any gain focus sound played by the next
     * object to gain focus. <p> By default, an {@link
     * org.havi.ui.HNavigable HNavigable} object does not have any
     * lose focus sound associated with it. <p> Note that the ordering
     * of playing sounds is dependent on the order of the focus lost,
     * gained events.
     * 
     * @param sound the sound to be played, when the component loses
     * focus. If sound content is already set, the original content is
     * replaced. To remove the sound specify a null {@link
     * org.havi.ui.HSound HSound}.
     */
    public void setLoseFocusSound(HSound sound)
    {
        return;
    }

    /**
     * Get the sound associated with the gain focus event.
     * 
     * @return The sound played when the component gains focus. If no
     * sound is associated with gaining focus, then null shall be
     * returned.  
     */
    public HSound getGainFocusSound()
    {
        return(null);
    }

    /**
     * Get the sound associated with the lost focus event.
     * 
     * @return The sound played when the component loses focus. If no
     * sound is associated with losing focus, then null shall be
     * returned.  
     */
    public HSound getLoseFocusSound()
    {
        return(null);
    }

/**
 * Adds the specified {@link org.havi.ui.event.HFocusListener HFocusListener} to 
 * receive {@link org.havi.ui.event.HFocusEvent HFocusEvent} events sent from 
 * this {@link org.havi.ui.HNavigable HNavigable}: If the listener has 
 * already been added further calls will add further references to the listener,  
 * which will then receive  multiple copies of a single event.
 * 
 * @param l the HFocusListener to add
 */

public void addHFocusListener(org.havi.ui.event.HFocusListener l)
    {
        return;
    }


/**
 * Removes the specified {@link org.havi.ui.event.HFocusListener HFocusListener}      
 * so that it no longer receives {@link org.havi.ui.event.HFocusEvent   
 * HFocusEvent} events from this {@link org.havi.ui.HNavigable HNavigable}. If    
 * the specified listener is not registered, the method has no effect. If 
 * multiple references to a single listener have been registered it should be 
 * noted that this method will only remove one reference per call.
 *
 * @param l the HFocusListener to remove
 */

public void removeHFocusListener(org.havi.ui.event.HFocusListener l)
    {
        return;
    }




    /**
     * Retrieve the set of key codes which this component maps to
     * navigation targets. 
     *
     * @return an array of key codes, or <code>null</code> if no
     * navigation targets are set on this component.  
     */
    public int[] getNavigationKeys()
    {
        return(null);
    }

     /**
     * Process an {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} sent to this {@link org.havi.ui.HRange
     * HRange}.
     * 
     * @param evt the {@link org.havi.ui.event.HFocusEvent
     * HFocusEvent} to process. 
     */
     
	public void processHFocusEvent(org.havi.ui.event.HFocusEvent evt)
    {
        return;
    }


}

