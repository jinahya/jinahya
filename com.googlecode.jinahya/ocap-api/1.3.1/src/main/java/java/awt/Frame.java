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
// import javax.accessibility.*;
// 
// import java.awt.peer.FramePeer;
import java.util.Vector;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
// import sun.awt.AppContext;
import java.lang.ref.WeakReference;

/** 
 * A <code>Frame</code> is a top-level window with a title and a border.
 * <p>
 * The size of the frame includes any area designated for the
 * border.  The dimensions of the border area may be obtained 
 * using the <code>getInsets</code> method, however, since 
 * these dimensions are platform-dependent, a valid insets
 * value cannot be obtained until the frame is made displayable
 * by either calling <code>pack</code> or <code>show</code>. 
 * Since the border area is included in the overall size of the
 * frame, the border effectively obscures a portion of the frame,
 * constraining the area available for rendering and/or displaying
 * subcomponents to the rectangle which has an upper-left corner
 * location of <code>(insets.left, insets.top)</code>, and has a size of
 * <code>width - (insets.left + insets.right)</code> by 
 * <code>height - (insets.top + insets.bottom)</code>. 
 * <p>
 * The default layout for a frame is <code>BorderLayout</code>.
 * <p>
 * A frame may have its native decorations (i.e. <code>Frame</code>
 * and <code>Titlebar</code>) turned off
 * with <code>setUndecorated</code>. This can only be done while the frame
 * is not {@link Component#isDisplayable() displayable}.
 * <p>
 * In a multi-screen environment, you can create a <code>Frame</code>
 * on a different screen device by constructing the <code>Frame</code>
 * with {@link #Frame(GraphicsConfiguration)} or
 * {@link #Frame(String title, GraphicsConfiguration)}.  The
 * <code>GraphicsConfiguration</code> object is one of the
 * <code>GraphicsConfiguration</code> objects of the target screen
 * device.
 * <p>
 * In a virtual device multi-screen environment in which the desktop
 * area could span multiple physical screen devices, the bounds of all
 * configurations are relative to the virtual-coordinate system.  The
 * origin of the virtual-coordinate system is at the upper left-hand
 * corner of the primary physical screen.  Depending on the location
 * of the primary screen in the virtual device, negative coordinates
 * are possible, as shown in the following figure.
 * <p>
 * <img src="doc-files/MultiScreen.gif" 
 * alt="Diagram of virtual device encompassing three physical screens and one primary physical screen. The primary physical screen
 * shows (0,0) coords while a different physical screen shows (-80,-100) coords."
 * ALIGN=center HSPACE=10 VSPACE=7>
 * <p>
 * In such an environment, when calling <code>setLocation</code>, 
 * you must pass a virtual coordinate to this method.  Similarly, 
 * calling <code>getLocationOnScreen</code> on a <code>Frame</code>
 * returns virtual device coordinates.  Call the <code>getBounds</code>
 * method of a <code>GraphicsConfiguration</code> to find its origin in
 * the virtual coordinate system.
 * <p>
 * The following code sets the
 * location of the <code>Frame</code> at (10, 10) relative
 * to the origin of the physical screen of the corresponding
 * <code>GraphicsConfiguration</code>.  If the bounds of the
 * <code>GraphicsConfiguration</code> is not taken into account, the
 * <code>Frame</code> location would be set at (10, 10) relative to the
 * virtual-coordinate system and would appear on the primary physical
 * screen, which might be different from the physical screen of the
 * specified <code>GraphicsConfiguration</code>.
 *
 * <pre>
 *      Frame f = new Frame(GraphicsConfiguration gc);
 *      Rectangle bounds = gc.getBounds();
 *      f.setLocation(10 + bounds.x, 10 + bounds.y);
 * </pre>
 *
<!-- PBP/PP -->
<!-- [6187195] -->
 * <p>
 * Frames are capable of generating the following types of
 * <code>WindowEvent</code>s:
 * <ul>
 * <li><code>WINDOW_OPENED</code>
 * <li><code>WINDOW_CLOSING</code>
 * <li><code>WINDOW_CLOSED</code>
 * <li><code>WINDOW_ICONIFIED</code>
 * <li><code>WINDOW_DEICONIFIED</code>
 * <li><code>WINDOW_ACTIVATED</code>
 * <li><code>WINDOW_DEACTIVATED</code>
 * <li><code>WINDOW_GAINED_FOCUS</code>
 * <li><code>WINDOW_LOST_FOCUS</code>
 * 
 * </ul>
 *
<!-- PBP/PP -->
 * <p>
 * <a name="clarifications">
 * <h4>Clarifications</h4>
 * <em>
 * <ul>
 * <li> The size and location of Frame instances may be severely limited
 * in this profile.  Please see the note concerning
 * <a href="Window.html#geometry">size and location of <code>java.awt.Window</code></a>.
 *
 * <li> Instances of <code>Frame</code> are initially created in
 * "windowed" mode.  See:
 * <ul>
 * <li> {@link GraphicsDevice#setFullScreenWindow(Window)}
 * </ul>
 *
 * <li> The decoration, title, and resizability of a <code>Frame</code>
 * are under the control of the window management system.  Method calls
 * to change these properties are requests (not directives) which are
 * forwarded to the window management system.  As a result:
 * <ul>
 *
 * <li>The window management system may ignore the decoration of a Frame
 * as reported by {@link #isUndecorated}.
 * See:
 * <ul>
 * <li> {@link #setUndecorated}
 * <li> {@link #isUndecorated}
 * </ul>
 *
 * <li> The window management system may ignore the title of a Frame
 * as reported by {@link #getTitle}.
 * See:
 * <ul>
 * <li> {@link #Frame(String)}
 * <li> {@link #Frame(String, GraphicsConfiguration)}
 * <li> {@link #setTitle}
 * <li> {@link #getTitle}
 * </ul>
 *
 * <li> The window management system may ignore the resizability of a Frame
 * as reported by {@link #isResizable}.
 * See:
 * <ul>
 * <li> {@link #isResizable}
 * <li> {@link #setResizable(boolean)}
 * </ul>
 *
 * </ul>
 *
 * </ul>
 *
 * For more information, see
 * <a href="../../doc-files/properties.html#Optional_properties">Profile-specific properties</a>.
 * </em>
 * <p>
 * <a name="restrictions">
 * <h4>Restrictions</h4>
 * <em>
 * <ul>
 * <li> Only a single instance of <code>Frame</code> is
 * permitted per GraphicsDevice. Attempts to
 * construct a second Frame will cause the constructor to throw
 * <code>java.lang.UnsupportedOperationException</code>.  See:
 * <ul>
 * <li> {@link #Frame()}
 * <li> {@link #Frame(GraphicsConfiguration)}
 * <li> {@link #Frame(String)}
 * <li> {@link #Frame(String, GraphicsConfiguration)}
 * <li> <code>Frame.readObject()</code>
 * </ul>
 *
 * </ul>
 * </em>
 *
 * @version 	1.139, 01/23/03
 * @author 	Sami Shaio
 * @see WindowEvent
 * @see Window#addWindowListener
 * @since       JDK1.0
 */
public class Frame extends Window 
{
//    /** 
//     * @deprecated   replaced by <code>Cursor.DEFAULT_CURSOR</code>.
//     */
//    public static final int DEFAULT_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.CROSSHAIR_CURSOR</code>.
//     */
//    public static final int CROSSHAIR_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.TEXT_CURSOR</code>.
//     */
//    public static final int TEXT_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.WAIT_CURSOR</code>.
//     */
//    public static final int WAIT_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.SW_RESIZE_CURSOR</code>.
//     */
//    public static final int SW_RESIZE_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.SE_RESIZE_CURSOR</code>.
//     */
//    public static final int SE_RESIZE_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.NW_RESIZE_CURSOR</code>.
//     */
//    public static final int NW_RESIZE_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.NE_RESIZE_CURSOR</code>.
//     */
//    public static final int NE_RESIZE_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.N_RESIZE_CURSOR</code>.
//     */
//    public static final int N_RESIZE_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.S_RESIZE_CURSOR</code>.
//     */
//    public static final int S_RESIZE_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.W_RESIZE_CURSOR</code>.
//     */
//    public static final int W_RESIZE_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.E_RESIZE_CURSOR</code>.
//     */
//    public static final int E_RESIZE_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.HAND_CURSOR</code>.
//     */
//    public static final int HAND_CURSOR = 0;

//    /** 
//     * @deprecated   replaced by <code>Cursor.MOVE_CURSOR</code>.
//     */
//    public static final int MOVE_CURSOR = 0;

    /** 
     * Frame is in the "normal" state.  This symbolic constant names a
     * frame state with all state bits cleared.
     * <!- PBP/PP 6209089 ->
     * 
     * 
     */
    public static final int NORMAL = 0;

    /** 
     * This state bit indicates that frame is iconified.
     * <!- PBP/PP 6209089 ->
     * 
     * 
     */
    public static final int ICONIFIED = 1;

    // /** 
     // * This state bit indicates that frame is maximized in the
     // * horizontal direction.
     // * @see #setExtendedState(int)
     // * @see #getExtendedState
     // * @since 1.4
     // */
    // public static final int MAXIMIZED_HORIZ = 0;
// 
    // /** 
     // * This state bit indicates that frame is maximized in the
     // * vertical direction.
     // * @see #setExtendedState(int)
     // * @see #getExtendedState
     // * @since 1.4
     // */
    // public static final int MAXIMIZED_VERT = 0;
// 
    // /** 
     // * This state bit mask indicates that frame is fully maximized
     // * (that is both horizontally and vertically).  It is just a
     // * convenience alias for
     // * <code>MAXIMIZED_VERT&nbsp;|&nbsp;MAXIMIZED_HORIZ</code>.
     // *
     // * <p>Note that the correct test for frame being fully maximized is
     // * <pre>
     // *     (state & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH
     // * </pre>
     // *
     // * <p>To test is frame is maximized in <em>some</em> direction use
     // * <pre>
     // *     (state & Frame.MAXIMIZED_BOTH) != 0
     // * </pre>
     // * 
     // * @see #setExtendedState(int)
     // * @see #getExtendedState
     // * @since 1.4
     // */
    // public static final int MAXIMIZED_BOTH = 0;

    /** 
     * Maximized bounds for this frame.
     * @see     #setMaximizedBounds(Rectangle)
     * @see     #getMaximizedBounds
     * @serial
     * @since 1.4
     */
  // Rectangle maximizedBounds;

    /** 
     * This is the title of the frame.  It can be changed
     * at any time.  <code>title</code> can be null and if
     * this is the case the <code>title</code> = "".
     *
     * @serial
     * @see #getTitle
     * @see #setTitle(String)
     */
     String title;

    // /** 
     // * The frames menubar.  If <code>menuBar</code> = null
     // * the frame will not have a menubar.
     // *
     // * @serial
     // * @see #getMenuBar
     // * @see #setMenuBar(MenuBar)
     // */
     // MenuBar menuBar;

    /** 
     * This field indicates whether the frame is resizable.
     * This property can be changed at any time.
     * <code>resizable</code> will be true if the frame is
     * resizable, otherwise it will be false.
     *
     * @serial
     * @see #isResizable()
     */
     boolean resizable;

    /** 
     * This field indicates whether the frame is undecorated.
     * This property can only be changed while the frame is not displayable.
     * <code>undecorated</code> will be true if the frame is
     * undecorated, otherwise it will be false.
     *
     * @serial
     * @see #setUndecorated(boolean)
     * @see #isUndecorated()
     * @see Component#isDisplayable()
     * @since 1.4
     */
     boolean undecorated;

    /** 
     * <code>mbManagement</code> is only used by the Motif implementation.
     *
     * @serial
     */
     boolean mbManagement;

    private int state;
    // PBP/PP 6203778
    // Vector ownedWindows;

    /** 
     * <code>Frame</code>'s Serialized Data Version.
     *
     * @serial
     */
    private int frameSerializedDataVersion;

    /*
     * JDK 1.1 serialVersionUID 
     */
     private static final long serialVersionUID = 2673458971256075116L;

// PBP/PP
    /** 
     * Constructs a new instance of <code>Frame</code> that is 
     * initially invisible.  The title of the <code>Frame</code>
     * is empty.
     * <p>
     * <em>Note: This operation is subject to
     * <a href="#restrictions">restriction</a>
     * in Personal Basis Profile.</em>
     * @exception HeadlessException when GraphicsEnvironment.isHeadless()
     * returns true
     * @see java.awt.GraphicsEnvironment#isHeadless()
     * @see Component#setSize
     * @see Component#setVisible(boolean)
     */
    public Frame() throws HeadlessException { super(null); }

// PBP/PP
    /** 
     * Create a <code>Frame</code> with the specified 
     * <code>GraphicsConfiguration</code> of
     * a screen device.
     *
     * <p>
     * <em>Note: This operation is subject to
     * <a href="#restrictions">restriction</a>
     * in Personal Basis Profile.</em>
     *
     * @param gc the <code>GraphicsConfiguration</code> 
     * of the target screen device. If <code>gc</code> 
     * is <code>null</code>, the system default 
     * <code>GraphicsConfiguration</code> is assumed.
     * @exception IllegalArgumentException if 
     * <code>gc</code> is not from a screen device.
     * This exception is always thrown
     * when GraphicsEnvironment.isHeadless() returns true
     * @see java.awt.GraphicsEnvironment#isHeadless()
     * @since     1.3
     */
    public Frame(GraphicsConfiguration gc) { super(null);}

// PBP/PP
    /** 
     * Constructs a new, initially invisible <code>Frame</code> object 
     * with the specified title.
     *
     * <p>
     * <em>Note: This operation is subject to
     * <a href="#restrictions">restriction</a>
     * in Personal Basis Profile.</em>
     *
     * @param title the title to be displayed in the frame's border.
     *              A <code>null</code> value
     *              is treated as an empty string, "".
     * @exception HeadlessException when GraphicsEnvironment.isHeadless()
     * returns true
     * @see java.awt.GraphicsEnvironment#isHeadless()
     * @see java.awt.Component#setSize
     * @see java.awt.Component#setVisible(boolean)
     * @see java.awt.GraphicsConfiguration#getBounds
     */
    public Frame(String title) throws HeadlessException {super(null); }

// PBP/PP
    /** 
     * Constructs a new, initially invisible <code>Frame</code> object 
     * with the specified title and a 
     * <code>GraphicsConfiguration</code>.
     * <p>
     * <em>Note: This operation is subject to
     * <a href="#restrictions">restriction</a>
     * in Personal Basis Profile.</em>
     *
     * @param title the title to be displayed in the frame's border.
     *              A <code>null</code> value
     *              is treated as an empty string, "".
     * @param gc the <code>GraphicsConfiguration</code> 
     * of the target screen device.  If <code>gc</code> is 
     * <code>null</code>, the system default 
     * <code>GraphicsConfiguration</code> is assumed.
     * @exception IllegalArgumentException if <code>gc</code> 
     * is not from a screen device.
     * This exception is always thrown
     * when GraphicsEnvironment.isHeadless() returns true
     * @see java.awt.GraphicsEnvironment#isHeadless()
     * @see java.awt.Component#setSize
     * @see java.awt.Component#setVisible(boolean)
     * @see java.awt.GraphicsConfiguration#getBounds
     */
    public Frame(String title, GraphicsConfiguration gc) {super(null); }

//    /** 
//     * We have to remove the (hard) reference to weakThis in the
//     * Vector, otherwise the WeakReference instance will never get
//     * garbage collected.
//     */
//    protected void finalize() throws Throwable { }

    // /** 
     // * Makes this Frame displayable by connecting it to
     // * a native screen resource.  Making a frame displayable will
     // * cause any of its children to be made displayable.
     // * This method is called internally by the toolkit and should
     // * not be called directly by programs.
     // * @see Component#isDisplayable
     // * @see #removeNotify
     // */
    // public void addNotify() { }

    /** 
     * Gets the title of the frame.  The title is displayed in the
     * frame's border.
     * @return    the title of this frame, or an empty string ("")
     *                if this frame doesn't have a title.
     * @see       #setTitle(String)
     */
    public String getTitle() { return null; }

    /** 
     * Sets the title for this frame to the specified string.
     *
     * @param title the title to be displayed in the frame's border.
     *              A <code>null</code> value
     *              is treated as an empty string, "".
     * @see      #getTitle
     */
    public void setTitle(String title) { }

    /** 
     * Gets the image to be displayed in the minimized icon
     * for this frame.
     * @return    the icon image for this frame, or <code>null</code> 
     *                    if this frame doesn't have an icon image.
     * @see       #setIconImage(Image)
     */
    public Image getIconImage() { return null; }

    /** 
     * Sets the image to be displayed in the minimized icon for this frame. 
     * Not all platforms support the concept of minimizing a window.
     * @param     image the icon image to be displayed.
     *            If this parameter is <code>null</code> then the
     *            icon image is set to the default image, which may vary
     *            with platform.            
     * @see       #getIconImage
     */
    public synchronized void setIconImage(Image image) { }

//    /** 
//     * Gets the menu bar for this frame.
//     * @return    the menu bar for this frame, or <code>null</code> 
//     *                   if this frame doesn't have a menu bar.
//     * @see       #setMenuBar(MenuBar)
//     */
//    public MenuBar getMenuBar() { return null; }

//    /** 
//     * Sets the menu bar for this frame to the specified menu bar.
//     * @param     mb the menu bar being set.
//     *            If this parameter is <code>null</code> then any
//     *            existing menu bar on this frame is removed.
//     * @see       #getMenuBar
//     */
//    public void setMenuBar(MenuBar mb) { }

    /** 
     * Indicates whether this frame is resizable by the user.  
     * By default, all frames are initially resizable. 
     * @return    <code>true</code> if the user can resize this frame; 
     *                        <code>false</code> otherwise.
     * @see       java.awt.Frame#setResizable(boolean)
     */
    public boolean isResizable() { return false; }

    /** 
     * Sets whether this frame is resizable by the user.  
     *
     * @param    resizable   <code>true</code> if this frame is resizable; 
     *                       <code>false</code> otherwise.
     * @see      java.awt.Frame#isResizable
     */
    public void setResizable(boolean resizable) { }

// PBP/PP
// [6187196]
    /** 
     * Sets the state of this frame.
     *
     *
     *
     * <!-- PBP/PP -->
     * <!-- [6234429] -->
     * <!-- The following is borrowed from the spec for setExtendedState() -->
     * <p>
     * <em>Note that if the state is not supported on a
     * given platform, nothing will happen. The application
     * may determine if a specific state is available via
     * {@link Toolkit#isFrameStateSupported(int state)}.
     * </em>
     *
     * @param state either <code>Frame.NORMAL</code> or
     *     <code>Frame.ICONIFIED</code>.
     * @see #getState
     */
    public synchronized void setState(int state) { }

    // /** 
     // * Sets the state of this frame. The state is
     // * represented as a bitwise mask.
     // * <ul>
     // * <li><code>NORMAL</code>
     // * <br>Indicates that no state bits are set.
     // * <li><code>ICONIFIED</code>
     // * <li><code>MAXIMIZED_HORIZ</code>
     // * <li><code>MAXIMIZED_VERT</code>
     // * <li><code>MAXIMIZED_BOTH</code>
     // * <br>Concatenates <code>MAXIMIZED_HORIZ</code>
     // * and <code>MAXIMIZED_VERT</code>.
     // * </ul>
     // * <p>Note that if the state is not supported on a
     // * given platform, nothing will happen. The application
     // * may determine if a specific state is available via
     // * the <code>java.awt.Toolkit#isFrameStateSupported(int state)</code>
     // * method.
     // * 
     // * @param state a bitwise mask of frame state constants
     // * @see     #getExtendedState
     // * @see     java.awt.Toolkit#isFrameStateSupported(int)
     // * @since   1.4
     // */
    // public synchronized void setExtendedState(int state) { }

// PBP/PP
// [6187196]
    /** 
     * Gets the state of this frame.
     * 
     *
     *
     * @return  <code>Frame.NORMAL</code> or <code>Frame.ICONIFIED</code>.
     * @see     #setState(int)
     */
    public synchronized int getState() {return 0;  }

    // /** 
     // * Gets the state of this frame. The state is
     // * represented as a bitwise mask.
     // * <ul>
     // * <li><code>NORMAL</code>
     // * <br>Indicates that no state bits are set.
     // * <li><code>ICONIFIED</code>
     // * <li><code>MAXIMIZED_HORIZ</code>
     // * <li><code>MAXIMIZED_VERT</code>
     // * <li><code>MAXIMIZED_BOTH</code>
     // * <br>Concatenates <code>MAXIMIZED_HORIZ</code>
     // * and <code>MAXIMIZED_VERT</code>.
     // * </ul>
     // *
     // * @return	a bitwise mask of frame state constants
     // * @see     #setExtendedState(int)
     // * @since 1.4
     // */
    // public synchronized int getExtendedState() { }

    // /** 
     // * Sets the maximized bounds for this frame.
     // * <p>
     // * When a frame is in maximized state the system supplies some
     // * defaults bounds.  This method allows some or all of those
     // * system supplied values to be overridden.
     // * <p>
     // * If <code>bounds</code> is <code>null</code>, accept bounds
     // * supplied by the system.  If non-<code>null</code> you can
     // * override some of the system supplied values while accepting
     // * others by setting those fields you want to accept from system
     // * to <code>Integer.MAX_VALUE</code>.
     // * <p>
     // * On some systems only the size portion of the bounds is taken
     // * into account.
     // * 
     // * @param bounds  bounds for the maximized state
     // * @see #getMaximizedBounds()
     // * @since 1.4
     // */
    // public synchronized void setMaximizedBounds(Rectangle bounds) { }
// 
    // /** 
     // * Gets maximized bounds for this frame.
     // * Some fields may contain <code>Integer.MAX_VALUE</code> to indicate
     // * that system supplied values for this field must be used.
     // *
     // * @return	maximized bounds for this frame;  may be <code>null</code>
     // * @see     #setMaximizedBounds(Rectangle)
     // * @since   1.4
     // */
    // public Rectangle getMaximizedBounds() { }

// PBP/PP
// [6187219]

     /** 
      * Disables or enables decorations for this frame.
      * This method can only be called while the frame is not displayable.
      * @param  undecorated <code>true</code> if no frame decorations are 
      *         to be enabled;
      *         <code>false</code> if frame decorations are to be enabled.
      * @throws <code>IllegalComponentStateException</code> if the frame
      *         is displayable.
      * @see    #isUndecorated
      * @see    Component#isDisplayable	 
      * 
      *
      * @since 1.4
      */
     public void setUndecorated(boolean undecorated) { }
// 
    
    // PBP/PP
    // 5087511 deleted assertion saying frame decorated by default
     /** 
      * Indicates whether this frame is undecorated.  
      *  
      * @return    <code>true</code> if frame is undecorated; 
      *                        <code>false</code> otherwise.
      * @see       java.awt.Frame#setUndecorated(boolean)
      * @since 1.4
      */
     public boolean isUndecorated() { return false; }

//    /** 
//     * Removes the specified menu bar from this frame.
//     * @param    m   the menu component to remove.
//     *           If <code>m</code> is <code>null</code>, then 
//     *           no action is taken
//     */
//    public void remove(MenuComponent m) { }

    // /** 
     // * Makes this Frame undisplayable by removing its connection
     // * to its native screen resource. Making a Frame undisplayable
     // * will cause any of its children to be made undisplayable. 
     // * This method is called by the toolkit internally and should
     // * not be called directly by programs.
     // * @see Component#isDisplayable
     // * @see #addNotify
     // */
    // public void removeNotify() { }

    /** 
     * Returns a string representing the state of this <code>Frame</code>.
     * This method is intended to be used only for debugging purposes, and the 
     * content and format of the returned string may vary between 
     * implementations. The returned string may be empty but may not be 
     * <code>null</code>.
     *
     * @return the parameter string of this frame
     */
    protected String paramString() { return null; }

//    /** 
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>Component.setCursor(Cursor)</code>.
//     */
//    public void setCursor(int cursorType) { }

//    /** 
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>Component.getCursor()</code>.
//     */
//    public int getCursorType() { return 0; }

    // /** 
     // * Returns an array containing all Frames created by the application.
     // * If called from an applet, the array will only include the Frames
     // * accessible by that applet.
     // * @since 1.2
     // */
    // public static Frame[] getFrames() { }
// 
    // /** 
     // * Gets the AccessibleContext associated with this Frame. 
     // * For frames, the AccessibleContext takes the form of an 
     // * AccessibleAWTFrame. 
     // * A new AccessibleAWTFrame instance is created if necessary.
     // *
     // * @return an AccessibleAWTFrame that serves as the 
     // *         AccessibleContext of this Frame
     // */
    // public AccessibleContext getAccessibleContext() { }

// PBP/PP
    /** 
     * Reads the <code>ObjectInputStream</code>.  Tries
     * to read an  <em>icon <code>Image</code></em>, which is optional
     * data available as of 1.4.  If an <code><em>Image</em></code>
     * is not available, but anything other than an EOF
     * is detected, an <code>OptionalDataException</code>
     * will be thrown..
     * Unrecognized keys or values will be ignored.
     * <p>
     * <em>Note: This operation is subject to
     * <a href="java/awt/Frame.html#restrictions">restriction</a>
     * in Personal Basis Profile.</em>
     *
     * @param s the <code>ObjectInputStream</code> to read
     * @exception OptionalDataException if an <code><em>Image</em></code>
     *   is not available, but anything other than an EOF
     *   is detected
     * @exception HeadlessException if
     *   <code>GraphicsEnvironment.isHeadless</code> returns
     *   <code>true</code>
     * @see java.awt.GraphicsEnvironment#isHeadless()
     * 
     * @see java.awt.Image
     * @see #writeObject(ObjectOutputStream)
     */
    private void readObject(ObjectInputStream s)
        throws ClassNotFoundException, IOException, HeadlessException
    { }

// PBP/PP
// 6203787
    /** 
     * Writes default serializable fields to stream.  Writes
     * an optional serializable  <em>icon <code>Image</code></em>, which is
     * available as of 1.4.
     *
     * @param s the <code>ObjectOutputStream</code> to write
     * @serialData an optional <code>Image</code>
     * 
     * @see java.awt.Image
     * @see #readObject(ObjectInputStream)
     */
    private void writeObject(ObjectOutputStream s) throws IOException { }

    // /** 
     // * This class implements accessibility support for the 
     // * <code>Frame</code> class.  It provides an implementation of the 
     // * Java Accessibility API appropriate to frame user-interface elements.
     // */
    // protected class AccessibleAWTFrame extends Window.AccessibleAWTWindow
    // {
// 
        // protected AccessibleAWTFrame() { }
// 
        // /** 
         // * Get the role of this object.
         // *
         // * @return an instance of AccessibleRole describing the role of the 
         // * object
         // * @see AccessibleRole
         // */
        // public AccessibleRole getAccessibleRole() { }
// 
        // /** 
         // * Get the state of this object.
         // *
         // * @return an instance of AccessibleStateSet containing the current
         // * state set of the object
         // * @see AccessibleState
         // */
        // public AccessibleStateSet getAccessibleStateSet() { }
    // }
}
