package org.havi.ui;

/*
 * Copyright 2000-2003 by HAVi, Inc. Java is a trademark of Sun
 * Microsystems, Inc. All rights reserved.  
 */

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;


/**
   An {@link org.havi.ui.HScene HScene} is a container representing
   the displayable area on-screen within which the application can
   display itself and thus interact with the user. 

   <p>
   {@link org.havi.ui.HScene HScene} may be
   regarded as a simple connection to the window management policy
   within the device, acting as a &quot;screen resource reservation
   mechanism&quot; denoting the area within which an application may
   present components.

   <h3>Rendering Behavior</h3>

   By default, {@link org.havi.ui.HScene HScene} does not paint itself
   on-screen, only its added &quot;child&quot; components and hence
   its only immediate graphical effect is to &quot;clip&quot; its
   child components. However, it is possible to request that the
   entire {@link org.havi.ui.HScene HScene} be painted in the current
   background color before any drawing takes place, and/or that
   a background image be drawn before the children are rendered.
   <p>

   An application which sets both of
   setBackgroundMode(NO_BACKGROUND_FILL) and setImageMode(IMAGE_NONE)
   shall be responsible for ensuring that all pixels in the HScene are
   filled with a value which is either opaque or transparent only to
   video. Such an application cannot make any assumptions about the
   previous contents of the graphics objects into which it is
   drawing. For implementations which double-buffer the display of
   graphics, these existing contents are implementation dependent.   
   
   <h3>Class Behavior</h3>

   For all interoperable applications, the {@link org.havi.ui.HScene
   HScene} is considered the top-level component of the
   application. No parent component to an {@link org.havi.ui.HScene
   HScene} should be accessible to applications. Interoperable
   applications should not use the getParent method in {@link
   org.havi.ui.HScene HScene}, since results are implementation
   dependent and valid implementations may generate a run-time error.

   <p>
   Although {@link org.havi.ui.HScene HScene} is a subclass of
   <code>java.awt.Container</code>, implementations are allowed to
   insert extra classes in the inheritance tree between
   {@link org.havi.ui.HScene HScene} and <code>Container</code>. It is
   allowed that this may result in {@link org.havi.ui.HScene HScene}
   inheriting additional methods beyond those specified here. This
   allows platforms with only one native <code>java.awt.Frame</code>
   to use {@link org.havi.ui.HScene HScene} as specified, whereas platforms
   with support for multiple <code>java.awt.Frame</code> or
   <code>java.awt.Window</code> classes can use an
   {@link org.havi.ui.HScene HScene} class derived from the appropriate class.

   <p> 

   {@link org.havi.ui.HScene HScenes} follow the design pattern of the
   <code>java.awt.Window</code> class. They are not a scarce resource
   on the platform. On platforms which only support one {@link
   org.havi.ui.HScene HScene} being visible at one time the current
   {@link org.havi.ui.HScene HScene} both loses the input focus and is
   hidden (e.g. iconified) when another application successfully
   requests the input focus. Two
   <code>java.awt.event.WindowEvent</code> events, with ids
   <code>WINDOW_DEACTIVATED</code> and <code>WINDOW_ICONIFIED</code>,
   shall be generated and sent to the {@link org.havi.ui.HScene
   HScene} which has lost the focus and the {@link
   org.havi.ui.HScene#isShowing isShowing} method for that HScene
   shall return false.

   <p>
   In terms of delegation, the {@link org.havi.ui.HScene HScene} shall
   behave like a <code>Window</code> with a native peer
   implementation, in that it will not appear to delegate any
   functionality to any parent object. Components which do not specify
   default characteristics inherit default values transitively from
   their parent objects. Therefore, the implementation of {@link
   org.havi.ui.HScene HScene} must have valid defaults defined for all
   characteristics, e.g. <code>Font</code>, foreground
   <code>Color</code>, background <code>Color</code>,
   <code>ColorModel</code>, <code>Cursor</code> and
   <code>Locale</code>.

   <h3>Additional Z-order support</h3>

   {@link org.havi.ui.HScene HScene} extends the
   <code>java.awt.Container</code> class by providing additional
   Z-ordering capabilities, which are required since components in the
   HAVi user-interface are explicitly allowed to overlap each
   other. The Z-ordering capabilities are defined by the {@link
   org.havi.ui.HComponentOrdering HComponentOrdering} interface. 
   
   <p> 
   Note that these Z-ordering capabilities (<code>addBefore, addAfter,
   pop, popInFrontOf, popToFront, push, pushBehind and
   pushToBack</code>) must be implemented by (implicitly) reordering
   the child Components within the {@link org.havi.ui.HScene HScene},
   so that the standard AWT convention that the Z-order is defined as
   the order in which Components are added to a given Container is
   maintained. See the description for {@link
   org.havi.ui.HComponentOrdering HComponentOrdering} for more
   details.

   <h3>Shortcut Keys</h3>

   It is an implementation option for HAVi systems to implement
   shortcut keys by forwarding <code>java.awt.KeyEvent</code> events
   to the parent {@link org.havi.ui.HScene HScene} of an
   application. Under these circumstances it is the responsibility of
   the application designer to ensure that the relevant
   <code>java.awt.KeyEvent</code> events used for &quot;shortcut
   keys&quot; are not consumed by any custom components.
   
   <p>
   Implementations of the standard HAVi UI components which process
   <code>java.awt.event.KeyEvent</code> events shall not consume any
   KeyEvent, thus allowing their use as a shortcut key on
   implementation which rely on KeyEvents being available in this way.

   <h3>Event Handling</h3>

   The mechanism by which input events are passed to the {@link
   org.havi.ui.HScene HScene} and its component hierarchy is not
   specified.

   <p> 
   Note that whether the {@link org.havi.ui.HScene HScene} is visible
   or not (as determined by the {@link org.havi.ui.HScene#isVisible
   isVisible} method) does not guarantee that it has the input focus
   and is receiving events.
   
   <p> 
   When the application initially gains the input focus, this is
   indicated by the system sending a
   <code>java.awt.event.WindowEvent</code> of type
   <code>WINDOW_ACTIVATED</code> to the {@link org.havi.ui.HScene
   HScene}. The {@link org.havi.ui.HScene HScene} should request that
   a child component gain the focus, if one is available. However, the
   mechanism by which this occurs is intentionally not specified here.

   <p>
   When the entire application loses the user's focus, the system
   shall notify the {@link org.havi.ui.HScene HScene} that it is no
   longer receiving events by sending a java.awt.event.WindowEvent of
   type WINDOW_DEACTIVATED to the {@link org.havi.ui.HScene HScene}.


   <h3>Acquiring and Displaying HScenes</h3>

   <p>
   There is no public constructor for {@link org.havi.ui.HScene
   HScene}, it is constructed by an {@link org.havi.ui.HSceneFactory
   HSceneFactory}. Only one {@link org.havi.ui.HScene HScene} per {@link
   org.havi.ui.HGraphicsDevice HGraphicsDevice} can be acquired at any
   one time for each application.

   <p> 
   The application may request that it be made visible by calling the
   the {@link org.havi.ui.HScene#show show} method. This method should
   ensure that the {@link org.havi.ui.HScene HScene} is completely
   visible to the user, e.g. by expanding an icon, or changing the
   stacking order between competing overlapping applications.

   <p> 
   Making the {@link org.havi.ui.HScene HScene} visible shall not
   automatically cause it to receive or even request input
   focus. Input focus can be requested by the application by calling
   the {@link org.havi.ui.HScene#requestFocus requestFocus}
   method at any time. 

   
  <hr>
  The parameters to the constructors are as follows, in cases where
  parameters are not used, then the constructor should use the default
  values.
  <p>
  <h3>Default parameter values exposed in the constructors</h3>
  <table border>
  <tr><th>Parameter</th><th>Description</th><th>Default value</th> 
  <th>Set method</th><th>Get method</th></tr>
  <tr><td colspan=5>None.</td></tr>
  </table>

  <h3>Default parameter values not exposed in the constructors</h3>
  <table border>
  <tr><th>Description</th><th>Default value</th><th>Set method</th>
  <th>Get method</th></tr>
<tr>
  <td>Visibility of the {@link org.havi.ui.HScene HScene}</td>
  <td>false</td>
  <td>setVisible</td>
  <td>isVisible</td>
</tr>
<tr>
  <td>Activity of associated shortcuts</td>
  <td>Shortcuts are active</td>
  <td>{@link org.havi.ui.HScene#enableShortcuts enableShortcuts}</td>
  <td>{@link org.havi.ui.HScene#isEnableShortcuts isEnableShortcuts}</td>
</tr>
<tr>
  <td>Associated layout manager</td>
  <td>null</td>
  <td>java.awt.Container#setLayout</td>
  <td>java.awt.Container#getLayout</td>
</tr>
<tr>
  <td>Background image mode</td>
  <td>IMAGE_NONE</td>
  <td>{@link org.havi.ui.HScene#setRenderMode setRenderMode}</td>
  <td>{@link org.havi.ui.HScene#getRenderMode getRenderMode}</td>
</tr>
  </table>

*/

public class HScene
    extends java.awt.Container
    implements HComponentOrdering
{    
    /** 
     * A constant for use with {@link org.havi.ui.HScene#setRenderMode
     * setRenderMode} which specifies that a background image should
     * not be rendered in this {@link org.havi.ui.HScene HScene}.
     */
    public static final int IMAGE_NONE = 0;

    /** 
     * A constant for use with {@link org.havi.ui.HScene#setRenderMode
     * setRenderMode} which specifies that any background image
     * rendered in this {@link org.havi.ui.HScene HScene} should be
     * stretched to fill the extents of the {@link org.havi.ui.HScene
     * HScene}.  
     */
    public static final int IMAGE_STRETCH = 1; 

    /** 
     * A constant for use with {@link org.havi.ui.HScene#setRenderMode
     * setRenderMode} which specifies that any background image
     * rendered in this {@link org.havi.ui.HScene HScene} should be
     * centered in the extents of the {@link org.havi.ui.HScene
     * HScene}. No tiling or scaling of the image shall be performed.  
     */
    public static final int IMAGE_CENTER = 2; 

    /** 
     * A constant for use with {@link org.havi.ui.HScene#setRenderMode
     * setRenderMode} which specifies that any background image
     * rendered in this {@link org.havi.ui.HScene HScene} should be
     * tiled starting from the top left origin of the {@link
     * org.havi.ui.HScene HScene}. No scaling of the image shall be
     * performed.  
     */
    public static final int IMAGE_TILE = 3; 

    /**
     * A constant for use with the {@link
     * org.havi.ui.HScene#setBackgroundMode setBackgroundMode} method
     * which indicates that the {@link org.havi.ui.HScene HScene}
     * should not fill its bounding rectangle with any
     * color before painting any background image and children. 
     */
    public static final int NO_BACKGROUND_FILL = 0;
    
    /**
     * A constant for use with the {@link
     * org.havi.ui.HScene#setBackgroundMode setBackgroundMode} method
     * which indicates that the {@link org.havi.ui.HScene HScene}
     * should fill its bounding rectangle with its current background
     * color before painting any background image and children. 
     */
    public static final int BACKGROUND_FILL = 1;

    /**
     * It is not intended that applications should directly construct
     * {@link org.havi.ui.HScene HScene} objects.  {@link
     * org.havi.ui.HScene HScene} objects should be constructed via
     * the {@link org.havi.ui.HSceneFactory HSceneFactory} classes
     * factory methods.
     * <p>
     * Creates an {@link org.havi.ui.HScene HScene} object. See the
     * class description for details of constructor parameters and
     * default values.
     *
     * @see org.havi.ui.HSceneFactory 
     */
    protected HScene()
    {
    }

    /**
     * Shows or hides this {@link org.havi.ui.HScene HScene} depending
     * on the value of the input parameter <code>visible</code>.  An
     * {@link org.havi.ui.HScene HScene} is initially not visible, a
     * call to <code>setVisible</code> should be used to make it
     * visible. This method does <em>not</em> cause the
     * {@link org.havi.ui.HScene HScene} to request the input focus.
     *
     * @param visible If true, makes this {@link org.havi.ui.HScene
     * HScene} visible; otherwise, hides this {@link
     * org.havi.ui.HScene HScene}.  
     * @see HScene#show 
     */
    public void setVisible(boolean visible)
    {
    }

    /**
     * Determines if the {@link org.havi.ui.HScene HScene} (or more
     * properly its added child components) is Visible. Initially an
     * {@link org.havi.ui.HScene HScene} is invisible.
     *
     * @return true if the {@link org.havi.ui.HScene HScene} is
     * visible; false otherwise.  
     */
    public boolean isVisible()
    {
        return (true);
    }
    
    /**
     * {@link org.havi.ui.HScene HScene} objects override the paint
     * method (defined in <code>java.awt.Component</code>) to paint
     * the added &quot;child&quot; components on top of an optional
     * background color or image. The paint behavior is as follows:
     * <p><ol>
     * <li>If the current background mode is {@link
     * org.havi.ui.HScene#BACKGROUND_FILL BACKGROUND_FILL}, the entire
     * {@link org.havi.ui.HScene HScene} is first filled using the
     * current background color.
     * <li>If a background image has been set using the {@link
     * org.havi.ui.HScene#setBackgroundImage setBackgroundImage}
     * method, <em>and</em> the current image rendering mode as set
     * using {@link org.havi.ui.HScene#setRenderMode setRenderMode} is
     * not IMAGE_NONE, the specified image is painted. Scaling and
     * tiling are performed according to the render mode set.
     * <li>Finally any children of the HScene are rendered in z-order. 
     * </ol>
     *
     * @param g the graphics context to use for painting.  
     */
    public void paint(java.awt.Graphics g)
    {
    }

    /**
     * Get the background mode of this {@link org.havi.ui.HScene
     * HScene}. The return value specifies whether the paint method
     * should draw the background (i.e. a rectangle filling the bounds
     * of the {@link org.havi.ui.HScene HScene}).
     * 
     * @return one of {@link org.havi.ui.HScene#NO_BACKGROUND_FILL
     * NO_BACKGROUND_FILL} or {@link
     * org.havi.ui.HScene#BACKGROUND_FILL BACKGROUND_FILL}.  
     */
    public int getBackgroundMode()
    {
	return (0);
    }

    /**
     * Set the background mode of this {@link org.havi.ui.HScene
     * HScene}. The value specifies whether the paint method
     * should draw the background (i.e. a rectangle filling the bounds
     * of the {@link org.havi.ui.HScene HScene}).
     * <p>
     * Note that the background mode will affect the return value of
     * the {@link org.havi.ui.HScene#isOpaque isOpaque} method, depending
     * on the value of the <code>mode</code> parameter. A fill mode of
     * {@link org.havi.ui.HScene#BACKGROUND_FILL BACKGROUND_FILL}
     * implies that {@link org.havi.ui.HScene#isOpaque isOpaque} must
     * return <code>true</code>.
     * 
     * @param mode one of {@link org.havi.ui.HScene#NO_BACKGROUND_FILL
     * NO_BACKGROUND_FILL} or {@link
     * org.havi.ui.HScene#BACKGROUND_FILL BACKGROUND_FILL}. If mode is
     * not a valid value, an IllegalArgumentException will be thrown. 
     */
    public void setBackgroundMode(int mode)
    {
    }

    /**
     * Set an image which shall be painted in the background of the
     * {@link org.havi.ui.HScene HScene}, after the background has
     * been drawn according to the current mode set with {@link
     * org.havi.ui.HScene#setBackgroundMode setBackgroundMode},
     * but before any children are drawn. The image is rendered
     * according to the current render mode set with {@link
     * org.havi.ui.HScene#setRenderMode setRenderMode}.
     * <p>
     * Note that the use of a background image in this way may affect
     * the return value of the {@link org.havi.ui.HScene#isOpaque isOpaque}
     * method, depending on the image and the current rendering mode.
     * 
     * @param image the image to be used as a background. If this
     * parameter is <code>null</code> any current image is
     * removed. Note that depending on the current render mode any
     * image set may not actually be rendered.
     * @see HScene#setRenderMode(int) 
     */
    public void setBackgroundImage(java.awt.Image image)
    {
    }

    /**
     * Retrieve any image used as a background for this {@link
     * org.havi.ui.HScene HScene}.
     * 
     * @return an image used as a background, or <code>null</code> if
     * no image is set. Note that depending on the current render mode
     * any image set may not actually be rendered.
     * @see HScene#setRenderMode(int)
     */
    public java.awt.Image getBackgroundImage()
    {
	return (null);
    }

    /**
     * Set the rendering mode of any background image associated with
     * this {@link org.havi.ui.HScene HScene}.  <p> Note that the
     * minimum requirement is to support only the 
     * <code>IMAGE_NONE</code> mode. Support of the other modes is
     * platform and implementation specific. 
     * 
     * @param mode the rendering mode, one of {@link
     * org.havi.ui.HScene#IMAGE_NONE IMAGE_NONE}, {@link
     * org.havi.ui.HScene#IMAGE_STRETCH IMAGE_STRETCH}, {@link
     * org.havi.ui.HScene#IMAGE_CENTER IMAGE_CENTER} or {@link
     * org.havi.ui.HScene#IMAGE_TILE IMAGE_TILE}. 
     * @return <code>true</code> if the mode was set successfully,
     * <code>false</code> if the mode is not supported by the
     * platform. 
     */
    public boolean setRenderMode(int mode) 
    {
	return (false);
    }

    /**
     * Get the rendering mode of any background image associated with
     * this {@link org.havi.ui.HScene HScene}.  
     * 
     * @return the rendering mode, one of {@link
     * org.havi.ui.HScene#IMAGE_NONE IMAGE_NONE}, {@link
     * org.havi.ui.HScene#IMAGE_STRETCH IMAGE_STRETCH}, {@link
     * org.havi.ui.HScene#IMAGE_CENTER IMAGE_CENTER} or {@link
     * org.havi.ui.HScene#IMAGE_TILE IMAGE_TILE}.  
     */
    public int getRenderMode()
    {
	return (0);
    }

    /**
     * Returns <code>true</code> if all the drawing done during the update and
     * paint methods for this specific {@link org.havi.ui.HScene
     * HScene} object is automatically double buffered.
     * 
     * @return <code>true</code> if all the drawing done during the
     * update and paint methods for this specific {@link
     * org.havi.ui.HScene HScene} object is automatically
     * double buffered, or <code>false</code> if drawing is not double
     * buffered.  The default value for the double buffering setting
     * is platform-specific.  
     */
    public boolean isDoubleBuffered()
    {
	return(false);
    }
    
    /** 
     * Returns true if the entire {@link org.havi.ui.HScene HScene}
     * area, as given by the <code>java.awt.Component#getBounds</code>
     * method, is fully opaque, i.e. its paint method (or surrogate
     * methods) guarantee that all pixels are painted in an opaque
     * <code>Color</code>.  
     * <p> 
     * By default, the return value depends on the value of the
     * current background mode, as set by the
     * {@link org.havi.ui.HScene#setBackgroundMode setBackgroundMode}
     * method. The return value should be overridden by subclasses
     * that can guarantee full opacity. The consequences of an invalid
     * overridden value are implementation specific.
     * 
     * @return <code>true</code> if all the pixels within the area
     * given by the <code>java.awt.Component#getBounds</code> method
     * are fully opaque, i.e. its paint method (or surrogate methods)
     * guarantee that all pixels are painted in an opaque Color,
     * otherwise <code>false</code>.  
     */
    public boolean isOpaque()
    {
	return (false);
    }

    /**      
     * Adds a <code>java.awt.Component</code> to this {@link
     * org.havi.ui.HScene HScene} directly in front of a
     * previously added <code>java.awt.Component</code>.
     * <p>
     * If <code>component</code> has already been added to this
     * container, then <code>addBefore</code> moves
     * <code>component</code> in front of <code>behind</code>.  If
     * <code>behind</code> and <code>component</code> are the same
     * component which was already added to this container,
     * <code>addBefore</code> does not change the ordering of the
     * components and returns <code>component</code>.
     * <p>
     * This method affects the Z-order of the
     * <code>java.awt.Component</code> children within the {@link
     * org.havi.ui.HScene HScene}, and may also implicitly
     * change the numeric ordering of those children.
     * 
     * @param component is the <code>java.awt.Component</code> to be
     * added to the {@link org.havi.ui.HScene HScene}
     * @param behind is the <code>java.awt.Component</code>, which
     * <code>component</code> will be placed in front of, i.e.
     * <code>behind</code> will be directly behind the added
     * <code>java.awt.Component</code>
     * @return If the <code>java.awt.Component</code> is successfully
     * added or was already present, then it will be returned from
     * this call. If the
     * <code>java.awt.Component</code> is not successfully added,
     * e.g. <code>behind</code> is not a
     * <code>java.awt.Component</code> currently added to the {@link
     * org.havi.ui.HScene HScene}, then <code>null</code> will
     * be returned.  <p> This method must be implemented in a thread
     * safe manner.
     */
    public java.awt.Component addBefore(java.awt.Component component, java.awt.Component behind)
    {
        return(null);
    }

    /**
     * Adds a <code>java.awt.Component</code> to this {@link
     * org.havi.ui.HScene HScene} directly behind a previously
     * added <code>java.awt.Component</code>.     
     * <p>
     * If <code>component</code> has already been added to this
     * container, then addAfter moves <code>component</code> behind
     * <code>front</code>.  If <code>front</code> and
     * <code>component</code> are the same component which was already
     * added to this container, <code>addAfter</code> does not change
     * the ordering of the components and returns
     * <code>component</code>.
     * <p> 
     * This method affects the Z-order of the
     * <code>java.awt.Component</code> children within the {@link
     * org.havi.ui.HScene HScene}, and may also implicitly
     * change the numeric ordering of those children.
     *     
     * @param component is the <code>java.awt.Component</code> to be
     * added to the {@link org.havi.ui.HScene HScene}
     * @param front is the <code>java.awt.Component</code>, which
     * <code>component</code> will be placed behind, i.e.
     * <code>front</code> will be directly in front of the added
     * <code>java.awt.Component</code>
     * @return If the <code>java.awt.Component</code> is successfully
     * added or was already present, then it will be returned from
     * this call. If the
     * <code>java.awt.Component</code> is not successfully added,
     * e.g. front is not a <code>java.awt.Component</code> currently
     * added to the {@link org.havi.ui.HScene HScene}, then
     * <code>null</code> will be returned. 
     * <p> 
     * This method must be implemented in a thread safe manner. 
     */
    public java.awt.Component addAfter(java.awt.Component component, java.awt.Component front)
    {
        return(null);
    }
    
    /**
     * Brings the specified <code>java.awt.Component</code> to the
     * &quot;front&quot; of the Z-order in this {@link
     * org.havi.ui.HScene HScene}.
     * <p>
     * If <code>component</code> is already at the front of the
     * Z-order, the order is unchanged and <code>popToFront</code>
     * returns <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to bring to the
     * &quot;front&quot; of the Z-order of this {@link org.havi.ui.HScene
     * HScene}.
     * 
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HScene HScene}. If this method
     * fails, the Z-order is unchanged.  
     */
    public boolean popToFront(java.awt.Component component)
    {
        return(true);
    }
    
    /**
     * Place the specified <code>java.awt.Component</code> at the
     * &quot;back&quot; of the Z-order in this {@link
     * org.havi.ui.HScene HScene}.
     * <p>
     * If <code>component</code> is already at the back the Z-order is
     * unchanged and <code>pushToBack</code> returns <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to place
     * at the &quot;back&quot; of the Z-order of this {@link org.havi.ui.HScene
     * HScene}.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HScene HScene}. If the component was
     * not added to the container <code>pushToBack</code> does not change the
     * Z-order.  
     */
    public boolean pushToBack(java.awt.Component component)
    {
        return(true);
    }
    
    /**
     * Moves the specified <code>java.awt.Component</code> one
     * component nearer in the Z-order, i.e. swapping it with the
     * <code>java.awt.Component</code> that was directly in front of
     * it.     
     * <p>
     * If <code>component</code> is already at the front of the
     * Z-order, the order is unchanged and <code>pop</code> returns
     * <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to be moved.
     * @return returns <code>true</code> on success, <code>false</code> on failure, for example
     * if the <code>java.awt.Component</code> has yet to be added to
     * the {@link org.havi.ui.HScene HScene}.  
     */
    public boolean pop(java.awt.Component component)
    {
        return(true);
    }
    
    /**
     * Moves the specified <code>java.awt.Component</code> one
     * component further away in the Z-order, i.e. swapping it with
     * the <code>java.awt.Component</code> that was directly behind
     * it.
     * <p>
     * If <code>component</code> is already at the back of the
     * Z-order, the order is unchanged and <code>push</code> returns
     * <code>true</code>.
     * 
     * @param component The <code>java.awt.Component</code> to be
     * moved.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example if the
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HScene HScene}.  
     */
    public boolean push(java.awt.Component component)
    {
        return(true);
    }
    
    /**       
     * Puts the specified <code>java.awt.Component</code> in front of
     * another <code>java.awt.Component</code> in the Z-order of this
     * {@link org.havi.ui.HScene HScene}.
     * <p>
     * If <code>move</code> and <code>behind</code> are the same
     * component which has been added to the container
     * <code>popInFront</code> does not change the Z-order and returns
     * <code>true</code>.
     * 
     * @param move The <code>java.awt.Component</code> to be moved
     * directly in front of the &quot;behind&quot; Component in the
     * Z-order of this {@link org.havi.ui.HScene HScene}.
     * @param behind The <code>java.awt.Component</code> which the
     * &quot;move&quot; Component should be placed directly in front
     * of.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when either
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HScene HScene}. If this method
     * fails, the Z-order is unchanged.  
     */
    public boolean popInFrontOf(java.awt.Component move, java.awt.Component behind)
    {
        return(true);
    }
    
    /**       
     * Puts the specified <code>java.awt.Component</code> behind
     * another <code>java.awt.Component</code> in the Z-order of this
     * {@link org.havi.ui.HScene HScene}. 
     * <p>
     * If <code>move</code> and <code>front</code> are the same
     * component which has been added to the container
     * <code>pushBehind</code> does not change the Z-order and returns
     * <code>true</code>.
     * 
     * @param move The <code>java.awt.Component</code> to be moved
     * directly behind the &quot;front&quot; Component in the Z-order
     * of this {@link org.havi.ui.HScene HScene}.
     * @param front The <code>java.awt.Component</code> which the
     * &quot;move&quot; Component should be placed directly behind.
     * @return returns <code>true</code> on success,
     * <code>false</code> on failure, for example when either
     * <code>java.awt.Component</code> has yet to be added to the
     * {@link org.havi.ui.HScene HScene}.  
     */
    public boolean pushBehind(java.awt.Component move, java.awt.Component front)
    {
        return(true);
    }

    /**
     * Add a listener to receive any java.awt.event.WindowEvents sent
     * from this {@link org.havi.ui.HScene HScene}. If the listener
     * has already been added further calls will add further
     * references to the listener, which will then receive multiple
     * copies of a single event.
     *
     * @param wl The java.awt.event.WindowListener to be notified of
     * any java.awt.event.WindowEvents.  */
    public void addWindowListener(java.awt.event.WindowListener wl)
    {
    }

    /**
     * Remove a listener so that it no longer receives any
     * java.awt.event.WindowEvents. If the specified listener is not
     * registered, the method has no effect. If multiple references to
     * a single listener have been registered it should be noted that
     * this method will only remove one reference per call.
     *
     * @param wl The java.awt.event.WindowListener to be removed from
     * notification of any java.awt.event.WindowEvents.  
     */
    public void removeWindowListener(java.awt.event.WindowListener wl)
    {
    }

    /**
     * Process a java.awt.event.WindowEvent for this {@link
     * org.havi.ui.HScene HScene}.
     *
     * @param we the java.awt.event.WindowEvent to be processed.
     */
    protected void processWindowEvent(java.awt.event.WindowEvent we)
    {
    }

    /**
     * Returns the child component of this {@link org.havi.ui.HScene
     * HScene} which has focus if and only if this
     * {@link org.havi.ui.HScene HScene} is active.
     *
     * @return the component with focus, or null if no children have focus
     * assigned to them.  
     */
    public java.awt.Component getFocusOwner()
    {
	return (null);
    }

    /**
     * Shows this {@link org.havi.ui.HScene HScene}, and brings it to the
     * front. 
     * <p> 
     * If this {@link org.havi.ui.HScene HScene} is not yet visible,
     * <code>show</code> makes it visible. If this
     * {@link org.havi.ui.HScene HScene} is already visible, then this
     * method brings it to the front.
     * @see HScene#setVisible
     */
    public void show()
    {
    }

    /**
     * Disposes of this HScene. This method must be called at
     * application exit to release the resources that are used for the
     * HScene. Calling this method will be equivalent to calling {@link 
     * org.havi.ui.HSceneFactory#dispose HSceneFactory.dispose}.
     * Calling this method on an HScene that was already disposed
     * will have no effect.
     */
    public void dispose()
    {
    }

    /**
     * Adds a shortcut key to action the specified {@link
     * org.havi.ui.HActionable HActionable}. Generating the defined
     * java.awt.KeyEvent or {@link org.havi.ui.event.HRcEvent
     * HRcEvent} keycode causes the specified component to become
     * actioned --- potentially any keyCode may have a shortcut
     * associated with it.  The shortcut will only be added if the
     * {@link org.havi.ui.HActionable HActionable} component is a child
     * component in the container hierarchy of which the {@link
     * org.havi.ui.HScene HScene} is the root container. If this is
     * not the case this method shall return false.
     * <p> 
     * Note that a maximum of one {@link org.havi.ui.HActionable
     * HActionable} may be associated with a given keyCode. An {@link
     * org.havi.ui.HActionable HActionable} can have at most one
     * associated shortcut keycode. Calling {@link
     * org.havi.ui.HScene#addShortcut addShortcut} repeatedly with the
     * same {@link org.havi.ui.HActionable HActionable} will result in
     * the previous short cut being removed. A short cut can be set on
     * an invisible {@link org.havi.ui.HActionable HActionable} and
     * therefore it is possible to provide short-cuts that have no
     * user representation.
     * <p> 
     * If the relevant keyCode is received by the {@link
     * org.havi.ui.HScene HScene}, then the {@link
     * org.havi.ui.HActionable HActionable} will be actioned by the
     * {@link org.havi.ui.HScene HScene} sending it an {@link
     * org.havi.ui.event.HActionEvent#ACTION_PERFORMED ACTION_PERFORMED}.
     *
     *  Note that it is the responsibility of the application to ensure that 
     *  the keyCode used is included in the set registered with the setKeyEvents 
     *  method and is one which the platform can generate, i.e. where the method
     *  HRcCapabilities.isSupported() returns true.

     * @param keyCode the keycode that represents the short cut. If
     * keycode is java.awt.event.KeyEvent#VK_UNDEFINED, then the
     * shortcut will not be added, any existing shortcut for this
     * component will not be changed and this method shall return false. 
     * @param comp The actionable component that will be actioned.  
     * @return true if the shortcut was added, false otherwise. 
     */
    public boolean addShortcut(int keyCode, HActionable comp)
    {
	return (false);
    }

    /**
     * Removes the specified short-cut key. if the specified short-cut
     * key is not registered, the method has no effect
     * 
     * @param keyCode The keycode that represents the short cut
     */
    public void removeShortcut(int keyCode)
    {
    }

    /**
     * Retrieve the {@link org.havi.ui.HActionable HActionable}	
     * associated with the specified shortcut key. 
     *
     * @param keyCode the shortcut key code to be queried for an
     * associated {@link org.havi.ui.HActionable HActionable}.
     * 
     * @return the {@link org.havi.ui.HActionable HActionable}
     * associated with the specified key if <code>keyCode</code> is a
     * valid shortcut key for this {@link org.havi.ui.HScene HScene},
     * <code>null</code> otherwise.  
     */
    public HActionable getShortcutComponent(int keyCode)
    {
        return (null);
    }
    
    /**
     * Enables or disables all short cuts that are currently set on
     * the Scene. To enable or disable a single shortcut use {@link
     * org.havi.ui.HScene#addShortcut addShortcut} or {@link
     * org.havi.ui.HScene#removeShortcut removeShortcut}.  
     * <p> 
     * Note {@link org.havi.ui.HScene#enableShortcuts
     * enableShortcuts(false)} does not remove existing added {@link
     * org.havi.ui.HScene HScene} shortcuts - they are merely disabled
     * and may be subsequently re-enabled with {@link
     * org.havi.ui.HScene#enableShortcuts enableShortcuts(true)}.
     * 
     * @param enable a value of true indicates all shortcuts are to be
     * enabled, and a value of false indicates all shortcuts are to be
     * disabled.  
     */
    public void enableShortcuts(boolean enable)
    {
    }

    /**
     * Returns the status of all short cuts that are currently set on
     * the HScene.
     *
     * @return true if shortcuts are enabled, false otherwise.
     * @see HScene#enableShortcuts 
     */
    public boolean isEnableShortcuts()
    {
        return (true);
    }

    /**
     * Returns the keycode associated with the specified HActionable
     * component.
     *
     * @param comp the HActionable to return the keycode that it is
     * associated with.
     * @return the keycode associated with the specified HActionable
     * component, if it is currently a valid shortcut
     * &quot;target&quot;, otherwise return
     * java.awt.event.KeyEvent#VK_UNDEFINED.  
     */
    public int getShortcutKeycode(HActionable comp)
    {
        return (0);
    }
    
    /**
     * Returns all keycodes added in the HScene as shortcuts.
     *
     * @return all keycodes added in the HScene as shortcuts, there
     * are no ordering guarantees.  
     */
    public int[] getAllShortcutKeycodes()
    {
        return (null);
    }

    /**
     * Returns an HScreenRectangle which corresponds to the graphics
     * (AWT) pixel area specified by the parameter in this HScene. 
     * (i.e. within the HScene's coordinate space).
     *
     * @param r the AWT pixel area within this HScene (i.e. within the
     * HScene's coordinate space), specified as an java.awt.Rectangle.
     * @return an HScreenRectangle which corresponds to the graphics
     * (AWT) pixel area specified by the parameter in this HScene
     * (i.e. within the HScene's coordinate space).  
     */
    public HScreenRectangle getPixelCoordinatesHScreenRectangle(Rectangle r)
    {
        return (null);
    }

    /**
     * Return an {@link org.havi.ui.HSceneTemplate HSceneTemplate}
     * describing this {@link org.havi.ui.HScene HScene}. This
     * template can be queried in order to obtain the size & position
     * of the {@link org.havi.ui.HScene HScene} in screen coordinates
     * and the display device used for the {@link org.havi.ui.HScene
     * HScene}, etc.
     *
     * @return an {@link org.havi.ui.HSceneTemplate HSceneTemplate}
     * describing of the {@link org.havi.ui.HScene HScene}.  
     */
    public HSceneTemplate getSceneTemplate()
    {
        return (null);
    }
  /**
   * Set whether the HScene is prepared to accept focus. Changing this from
   * false to true shall not imply requesting focus for the HScene. Changing
   * this from true to false shall result in an HScene with focus losing it.
   * If focus is lost in this way, it shall be notified by sending a
   * java.awt.event.WindowEvent of type WINDOW_DEACTIVATED to the HScene. 
   * Calling the requestFocus method on an HScene where the last value
   * passed to setActive was false shall have no effect. HScenes where this method has
   * never been called are always prepared to accept focus and hence applications 
   * which are always prepared to accept focus never need call this method.
   *
   * @param focus true if the HScene can accept focus otherwise false
   */
    public void setActive(boolean focus){}

  /**
   * Define the key codes which this HScene is interested in receiving when
   * it has input focus. The key codes shall be defined in terms of the 
   * key code constants for HRcEvent and its parent classes. Applications
   * which omit a key code known to be supported by a particular platform
   * make that key code available to other applications but all other mechanisms
   * and semantics involved in this are outside the scope of this specification.
   * For example, an application omitting the number keys from the set which it
   * is interested in, would make those available to other applications, for 
   * instance a television channel surfing application.
   *
   * <p>Applications may not receive all the keys in which they have declared an
   * interest for a number of reasons.
   * <ul>
   * <li>Because the system is incapable of generating the key concerned
   * <li>Because the key concerned is never available to applications
   * due to some platform policy.
   * <li>Because another application has the successfully obtained the right to 
   * exclusively receive a particular key via some mechanism outside the scope 
   * of this specification.
   * </ul>
   * <p>The default set of key codes is a subset of the key codes for which
   * HRcCapabilities.isSupported(keyCode) returns true as modified by platform
   * policy. It represents the maximum set of key codes which an application
   * can receive. Hence applications which never wish to receive fewer keys
   * than this need not use this method. 
   * <p>Defining a key code in this method shall not result in a "virtual" 
   * keyboard being displayed. It is implementation dependent whether this 
   * method has an effect on key events generated from virtual 
   * keyboards where implementations provide these.
   * <p>Listing a key code more than once in the input
   * to this method shall have the same effect as listing it once. Unknown
   * key codes shall be ignored and shall not cause an error condition.
   * Applications may still receive key codes in which they have not declared 
   * an interest however this is platform dependent.
   *
   * @param keyCodes the key codes which this HScene is interested in receiving
   * 
   */
   public void setKeyEvents(org.havi.ui.event.HEventGroup keyCodes) { }

  /**
   * Return the key codes which this HScene is interested in receiving when it
   * has input focus. If the method setKeyEvents has been previously called for
   * this HScene instance then the return value of this method shall be the last
   * input to that method. Otherwise the return value shall be the default set
   * of key codes.
   *
   * @return the key codes which this HScene is interested in receiving
   */
   public org.havi.ui.event.HEventGroup getKeyEvents() {
   	return (null);
   }

}


