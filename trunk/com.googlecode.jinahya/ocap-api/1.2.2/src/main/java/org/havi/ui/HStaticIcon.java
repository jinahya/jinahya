package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Image;


/**
   The {@link org.havi.ui.HStaticIcon HStaticIcon} is a user interface
   component used to display static graphical content which does
   <i>not</i> permit the user to navigate (focus) upon it. By default
   it uses the {@link org.havi.ui.HGraphicLook HGraphicLook} to render itself.

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
    <td>imageNormal</td>
    <td>The image to be used as the content for the {@link
    org.havi.ui.HState#NORMAL_STATE HState.NORMAL_STATE} state of
    this component.</td>    
    <td>null</td>
    <td>{@link org.havi.ui.HVisible#setGraphicContent setGraphicContent}</td>
    <td>{@link org.havi.ui.HVisible#getGraphicContent getGraphicContent}</td>
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
  <td>{@link org.havi.ui.HStaticIcon#setDefaultLook HStaticIcon.setDefaultLook}</td>
  <td>{@link org.havi.ui.HStaticIcon#getDefaultLook HStaticIcon.getDefaultLook}</td>
</tr>

<tr>
  <td>The &quot;look&quot; for this object.</td>
  <td>The {@link org.havi.ui.HGraphicLook HGraphicLook} returned from
  HStaticIcon.getDefaultLook when this object was created.</td>
  <td>{@link org.havi.ui.HStaticIcon#setLook HStaticIcon.setLook}</td>
  <td>{@link org.havi.ui.HStaticIcon#getLook HStaticIcon.getLook}</td>
</tr>
  </table>
*/

public class HStaticIcon 
    extends HVisible 
    implements HNoInputPreferred
{
    /**
     * Creates an {@link org.havi.ui.HStaticIcon HStaticIcon}
     * object. See the class description for details of constructor
     * parameters and default values. For constructors which specify 
     * content as parameters, see 'State-based content' in HVisible for 
     * unspecified content associated with other HStates. 
     */
    public HStaticIcon()
    {
    }
    
    /**
     * Creates an {@link org.havi.ui.HStaticIcon HStaticIcon}
     * object. See the class description for details of constructor
     * parameters and default values. For constructors which specify 
     * content as parameters, see 'State-based content' in HVisible for 
     * unspecified content associated with other HStates. 
     */
    public HStaticIcon(Image imageNormal, int x, int y, int width, int height)
    {
    }

    /**
     * Creates an {@link org.havi.ui.HStaticIcon HStaticIcon}
     * object. See the class description for details of constructor
     * parameters and default values. For constructors which specify 
     * content as parameters, see 'State-based content' in HVisible for 
     * unspecified content associated with other HStates. 
     */
    public HStaticIcon(Image imageNormal)
    {
    }

    /**
     * Sets the {@link org.havi.ui.HLook HLook} for this component.
     *
     * @param hlook The {@link org.havi.ui.HLook HLook} that is to be
     * used for this component.
     * Note that this parameter may be null, in which case the
     * component will not draw itself until a look is set.
     * @exception HInvalidLookException If the {@link
     * org.havi.ui.HLook HLook} is not an {@link
     * org.havi.ui.HGraphicLook HGraphicLook}.  */
    public void setLook(HLook hlook) throws HInvalidLookException
    {
    }

    /**
     * Sets the default {@link org.havi.ui.HLook HLook} for further
     * {@link org.havi.ui.HStaticIcon HStaticIcon} Components.
     * 
     * @param hlook The {@link org.havi.ui.HLook HLook} that will be
     * used by default when creating a new {@link
     * org.havi.ui.HStaticIcon HStaticIcon} component.
     * Note that this parameter may be null, in which case newly
     * created components shall not draw themselves until a non-null
     * look is set using the {@link
     * org.havi.ui.HStaticIcon#setLook setLook} method.
     */
    public static void setDefaultLook(HGraphicLook hlook) 
    {
    }

    /**
     * Returns the currently set default {@link org.havi.ui.HLook
     * HLook} for {@link org.havi.ui.HStaticIcon HStaticIcon}
     * components.
     * 
     * @return The {@link org.havi.ui.HLook HLook} that is used by
     * default when creating a new {@link org.havi.ui.HStaticIcon
     * HStaticIcon} component.  
     */
    public static HGraphicLook getDefaultLook()
    {
        return (null);
    }
}

