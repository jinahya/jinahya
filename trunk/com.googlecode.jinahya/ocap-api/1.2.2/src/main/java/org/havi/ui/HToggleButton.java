package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;



/**
   The {@link org.havi.ui.HToggleButton} is a user interface
   component representing a &quot;check box&quot;, or with the support
   of the {@link org.havi.ui.HToggleGroup} class,
   &quot;radio buttons&quot;. It displays static read-only graphical
   content. This component can be navigated to, i.e. it can have the
   input focus, and it can be actioned as defined by the {@link
   org.havi.ui.HSwitchable HSwitchable} interface. This means that the
   interaction state persists after {@link
   org.havi.ui.event.HActionEvent} event processing is
   complete.

   <p>
   The current switchable state can be manipulated using {@link
   org.havi.ui.HToggleButton#setSwitchableState}
   and {@link org.havi.ui.HToggleButton#getSwitchableState}

   <p>
   By default it uses the {@link org.havi.ui.HGraphicLook} class to
   render itself.

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
  <td>image</td>
  <td>The image to be used as the content for every
  state of this component.</td>
  <td>null</td>
  <td>{@link org.havi.ui.HVisible#setGraphicContent setGraphicContent}</td>
  <td>{@link org.havi.ui.HVisible#getGraphicContent getGraphicContent}</td>
</tr>
<tr>
    <td>imageNormal</td>
    <td>The image to be used as the content for the {@link
    org.havi.ui.HState#NORMAL_STATE HState.NORMAL_STATE} state of
    this component.</td>    
    <td>null</td>
    <td>{@link org.havi.ui.HVisible#setGraphicContent setGraphicContent}</td>
    <td>{@link org.havi.ui.HVisible#getGraphicContent getGraphicContent}</td>
</tr>
<tr>
    <td>imageFocused</td>
    <td>The image to be used as the content for the 
        {@link HState#FOCUSED_STATE} and
        {@link HState#DISABLED_FOCUSED_STATE} states of this component.</td>
    <td>null</td>
    <td>{@link org.havi.ui.HVisible#setGraphicContent setGraphicContent}</td>
    <td>{@link org.havi.ui.HVisible#getGraphicContent getGraphicContent}</td>
</tr>
<tr>
    <td>imageActioned</td>
    <td>The image to be used as the content for the
        {@link HState#ACTIONED_FOCUSED_STATE} and
        {@link HState#DISABLED_ACTIONED_FOCUSED_STATE} states of this
	component.</td>
    <td>null</td>
    <td>{@link org.havi.ui.HVisible#setGraphicContent setGraphicContent}</td>
    <td>{@link org.havi.ui.HVisible#getGraphicContent getGraphicContent}</td>
</tr>
<tr>
    <td>imageNormalActioned</td>
    <td>The image to be used as the content for the
        {@link HState#ACTIONED_STATE} and 
	{@link HState#DISABLED_ACTIONED_STATE} states of this component.</td>
    <td>null</td>
    <td>{@link org.havi.ui.HVisible#setGraphicContent setGraphicContent}</td>
    <td>{@link org.havi.ui.HVisible#getGraphicContent getGraphicContent}</td>
</tr>

<tr>
  <td>state</td>
  <td>The switchable state of this {@link org.havi.ui.HToggleButton}.</td>
  <td>false</td>
  <td>{@link org.havi.ui.HToggleButton#setSwitchableState}</td>
  <td>{@link org.havi.ui.HToggleButton#getSwitchableState}</td>
 </tr>

<tr>
  <td>group</td>
  <td>The {@link org.havi.ui.HToggleGroup} with which to
  associate this {@link org.havi.ui.HToggleButton}.</td>
  <td>null</td>
  <td>{@link org.havi.ui.HToggleButton#setToggleGroup}</td>
  <td>{@link org.havi.ui.HToggleButton#getToggleGroup}</td>
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
  <td>A platform specific {@link org.havi.ui.HGraphicLook HGraphicLook}</td>
  <td>{@link org.havi.ui.HToggleButton#setDefaultLook HToggleButton.setDefaultLook}</td>
  <td>{@link org.havi.ui.HToggleButton#getDefaultLook HToggleButton.getDefaultLook}</td>
</tr>

<tr>
  <td>The &quot;look&quot; for this object.</td>
  <td>The {@link org.havi.ui.HGraphicLook HGraphicLook} returned from
  HToggleButton.getDefaultLook when this object was created.</td>
  <td>{@link org.havi.ui.HToggleButton#setLook HToggleButton.setLook}</td>
  <td>{@link org.havi.ui.HToggleButton#getLook HToggleButton.getLook}</td>
</tr>
<tr>
  <td>The gain focus sound.</td>
  <td>null </td>
  <td>{@link org.havi.ui.HToggleButton#setGainFocusSound setGainFocusSound}</td>
  <td>{@link org.havi.ui.HToggleButton#getGainFocusSound getGainFocusSound}</td>
</tr>
<tr>
  <td>The lose focus sound. </td>
  <td>null </td>
  <td>{@link org.havi.ui.HToggleButton#setLoseFocusSound setLoseFocusSound}</td>
  <td>{@link org.havi.ui.HToggleButton#getLoseFocusSound getLoseFocusSound}</td>
</tr>
<tr>
  <td>The action sound.</td>
  <td>null </td>
  <td>{@link org.havi.ui.HToggleButton#setActionSound setActionSound}</td>
  <td>{@link org.havi.ui.HToggleButton#getActionSound getActionSound}</td>
</tr>
<tr>
  <td>The unset action sound.</td>
  <td>null </td>
  <td>{@link org.havi.ui.HToggleButton#setUnsetActionSound setUnsetActionSound}</td>
  <td>{@link org.havi.ui.HToggleButton#getUnsetActionSound getUnsetActionSound}</td>
</tr>
  </table>

  @see HStaticIcon
  @see HIcon
  @see HNavigable
  @see HActionable
  @see HSwitchable
 
*/

public class HToggleButton
    extends HGraphicButton
    implements HSwitchable
{
    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton()
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image image, int x, int y, int width, int height)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image image)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image image, int x, int y, int width, int height,
                         boolean state)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image imageNormal, Image imageFocused,
                         Image imageActioned, Image imageNormalActioned,
                         int x, int y, int width, int height, boolean state)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image imageNormal, Image imageFocused,
                         Image imageActioned, Image imageNormalActioned,
                         boolean state)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image image, int x, int y, int width, int height,
                         boolean state, HToggleGroup group)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image image, boolean state, HToggleGroup group)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image imageNormal, Image imageFocused,
                         Image imageActioned, Image imageNormalActioned,
                         int x, int y, int width, int height, boolean state,
                         HToggleGroup group)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HToggleButton}
     * object. See the class description for details of constructor
     * parameters and default values.  
     */
    public HToggleButton(Image imageNormal, Image imageFocused,
                         Image imageActioned, Image imageNormalActioned,
                         boolean state, HToggleGroup group)
    {
    }

    /**
     * Associates the {@link org.havi.ui.HToggleButton}
     * with an {@link org.havi.ui.HToggleGroup}.  If this
     * {@link org.havi.ui.HToggleButton} is already in a
     * different {@link org.havi.ui.HToggleGroup}, it is
     * first taken out of that group.
     * 
     * @param group The {@link org.havi.ui.HToggleGroup}
     * the {@link org.havi.ui.HToggleButton} is to be
     * associated with.  
     */
    public void setToggleGroup(HToggleGroup group)
    {
    }

    /**
     * Gets the {@link org.havi.ui.HToggleGroup} the
     * {@link org.havi.ui.HToggleButton} is associated
     * with.
     * 
     * @return The {@link org.havi.ui.HToggleGroup} the
     * {@link org.havi.ui.HToggleButton} is associated
     * with, or null if the {@link org.havi.ui.HToggleButton}
     * is not associated with an {@link
     * org.havi.ui.HToggleGroup}.
     */
    public HToggleGroup getToggleGroup()
    {
        return (null);
    }

    /**
     * Removes the button from the toggle group that it has been added
     * to. This method does nothing if the button had not been
     * previously added to an {@link org.havi.ui.HToggleGroup}.  
     */
    public void removeToggleGroup()
    {
    }

    /**
     * Sets the default {@link org.havi.ui.HLook} for further {@link
     * org.havi.ui.HToggleButton} components.
     *
     * @param hlook The {@link org.havi.ui.HLook} that will be
     * used by default when creating a new {@link
     * org.havi.ui.HToggleButton} component.
     * Note that this parameter may be null, in which case newly
     * created components shall not draw themselves until a non-null
     * look is set using the {@link
     * org.havi.ui.HStaticIcon#setLook} method.
     */
    public static void setDefaultLook(HGraphicLook hlook)
    {
    }

    /**
     * Returns the currently set default {@link org.havi.ui.HLook}
     * for {@link org.havi.ui.HToggleButton}
     * components.
     *
     * @return The {@link org.havi.ui.HLook} that is used by
     * default when creating a new {@link org.havi.ui.HToggleButton}
     * component.  
     */
    public static HGraphicLook getDefaultLook()
    {
        return (null);
    }

    /**       
     * Returns the current switchable state of this {@link
     * org.havi.ui.HSwitchable HSwitchable}.
     * 
     * @return the current switchable state of this {@link
     * org.havi.ui.HSwitchable HSwitchable}.     
     */
    public boolean getSwitchableState()
    {
        return(false);
    }
    
    /**
     * Sets the current state of the button. Note that ActionListeners
     * are only called when an ACTION_PERFORMED event is received, or
     * if they are called directly, e.g. via <code>processActionEvent</code>,
     * they are not called by {@link
     * org.havi.ui.HToggleButton#setSwitchableState
     * setSwitchableState}.  
     */
    public void setSwitchableState(boolean state)
    {
        return;
    }
    
    /**
     * Associate a sound to be played when the interaction state of
     * the {@link org.havi.ui.HSwitchable HSwitchable} makes the
     * following transitions:
     * <p><ul>
     * <li> {@link org.havi.ui.HState#ACTIONED_STATE ACTIONED_STATE} to {@link org.havi.ui.HState#NORMAL_STATE NORMAL_STATE}
     * <li> {@link org.havi.ui.HState#ACTIONED_FOCUSED_STATE ACTIONED_FOCUSED_STATE} to {@link org.havi.ui.HState#FOCUSED_STATE FOCUSED_STATE}
     * </ul><p>
     * 
     * @param sound a sound to be played when the {@link
     * org.havi.ui.HSwitchable HSwitchable} transitions from an
     * actioned state.  If sound content is already set, the original
     * content is replaced. To remove the sound specify a null {@link
     * org.havi.ui.HSound HSound}.  
     */
    public void setUnsetActionSound(HSound sound)
    {
        return;
    }
    
    /**       
     * Get the sound to be played when the interaction state of
     * the {@link org.havi.ui.HSwitchable HSwitchable} makes the
     * following transitions:
     * <p><ul>
     * <li> {@link org.havi.ui.HState#ACTIONED_STATE ACTIONED_STATE} to {@link org.havi.ui.HState#NORMAL_STATE NORMAL_STATE}
     * <li> {@link org.havi.ui.HState#ACTIONED_FOCUSED_STATE ACTIONED_FOCUSED_STATE} to {@link org.havi.ui.HState#FOCUSED_STATE FOCUSED_STATE}
     * </ul><p>
     * 
     * @return the sound to be played when the {@link
     * org.havi.ui.HSwitchable HSwitchable} transitions from an
     * actioned state.
     */
    public HSound getUnsetActionSound()
    {
        return(null);
    }

}

