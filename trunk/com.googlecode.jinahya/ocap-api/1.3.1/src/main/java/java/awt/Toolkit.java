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
// import java.awt.peer.*;
import java.awt.*;

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.awt.im.InputMethodHighlight;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
// import java.awt.datatransfer.Clipboard;
// import java.awt.dnd.DnDConstants;
// import java.awt.dnd.DragSource;
// import java.awt.dnd.DragGestureRecognizer;
// import java.awt.dnd.DragGestureEvent;
// import java.awt.dnd.DragGestureListener;
// import java.awt.dnd.InvalidDnDOperationException;
// import java.awt.dnd.peer.DragSourceContextPeer;
import java.net.URL;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.EventListener;
import java.util.Map;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.List;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// PBP/PP
/** 
 * This class is the abstract superclass of all actual
 * implementations of the Abstract Window Toolkit. Subclasses of
 * <code>Toolkit</code> are used to bind the various components
 * to particular native toolkit implementations.
 * <p>
 * Most applications should not call any of the methods in this
 * class directly.  Some methods defined by
 * <code>Toolkit</code> query the native operating system directly.
 *
 * @version 	1.189, 01/23/03
 * @author	Sami Shaio
 * @author	Arthur van Hoff
 * @author	Fred Ecks
 * @since       JDK1.0
 */
public abstract class Toolkit
{
//     protected final Map desktopProperties = null;

//     protected final PropertyChangeSupport desktopPropsSupport = null;

    public Toolkit() { }

    // /** 
     // * Creates this toolkit's implementation of <code>Button</code> using
     // * the specified peer interface.
     // * @param     target the button to be implemented.
     // * @return    this toolkit's implementation of <code>Button</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Button
     // * @see       java.awt.peer.ButtonPeer
     // */
    // protected abstract ButtonPeer createButton(java.awt.Button target)
        // throws java.awt.HeadlessException;

    // /** 
     // * Creates this toolkit's implementation of <code>TextField</code> using
     // * the specified peer interface.
     // * @param     target the text field to be implemented.
     // * @return    this toolkit's implementation of <code>TextField</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.TextField
     // * @see       java.awt.peer.TextFieldPeer
     // */
    // protected abstract TextFieldPeer createTextField(java.awt.TextField target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Label</code> using
     // * the specified peer interface.
     // * @param     target the label to be implemented.
     // * @return    this toolkit's implementation of <code>Label</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Label
     // * @see       java.awt.peer.LabelPeer
     // */
    // protected abstract LabelPeer createLabel(java.awt.Label target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>List</code> using
     // * the specified peer interface.
     // * @param     target the list to be implemented.
     // * @return    this toolkit's implementation of <code>List</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.List
     // * @see       java.awt.peer.ListPeer
     // */
    // protected abstract ListPeer createList(java.awt.List target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Checkbox</code> using
     // * the specified peer interface.
     // * @param     target the check box to be implemented.
     // * @return    this toolkit's implementation of <code>Checkbox</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Checkbox
     // * @see       java.awt.peer.CheckboxPeer
     // */
    // protected abstract CheckboxPeer createCheckbox(java.awt.Checkbox target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Scrollbar</code> using
     // * the specified peer interface.
     // * @param     target the scroll bar to be implemented.
     // * @return    this toolkit's implementation of <code>Scrollbar</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Scrollbar
     // * @see       java.awt.peer.ScrollbarPeer
     // */
    // protected abstract ScrollbarPeer createScrollbar(java.awt.Scrollbar target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>ScrollPane</code> using
     // * the specified peer interface.
     // * @param     target the scroll pane to be implemented.
     // * @return    this toolkit's implementation of <code>ScrollPane</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.ScrollPane
     // * @see       java.awt.peer.ScrollPanePeer
     // * @since     JDK1.1
     // */
    // protected abstract ScrollPanePeer createScrollPane(java.awt.ScrollPane
        // target) throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>TextArea</code> using
     // * the specified peer interface.
     // * @param     target the text area to be implemented.
     // * @return    this toolkit's implementation of <code>TextArea</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.TextArea
     // * @see       java.awt.peer.TextAreaPeer
     // */
    // protected abstract TextAreaPeer createTextArea(java.awt.TextArea target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Choice</code> using
     // * the specified peer interface.
     // * @param     target the choice to be implemented.
     // * @return    this toolkit's implementation of <code>Choice</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Choice
     // * @see       java.awt.peer.ChoicePeer
     // */
    // protected abstract ChoicePeer createChoice(java.awt.Choice target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Frame</code> using
     // * the specified peer interface.
     // * @param     target the frame to be implemented.
     // * @return    this toolkit's implementation of <code>Frame</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Frame
     // * @see       java.awt.peer.FramePeer
     // */
    // protected abstract FramePeer createFrame(java.awt.Frame target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Canvas</code> using
     // * the specified peer interface.
     // * @param     target the canvas to be implemented.
     // * @return    this toolkit's implementation of <code>Canvas</code>.
     // * @see       java.awt.Canvas
     // * @see       java.awt.peer.CanvasPeer
     // */
    // protected abstract CanvasPeer createCanvas(java.awt.Canvas target);
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Panel</code> using
     // * the specified peer interface.
     // * @param     target the panel to be implemented.
     // * @return    this toolkit's implementation of <code>Panel</code>.
     // * @see       java.awt.Panel
     // * @see       java.awt.peer.PanelPeer
     // */
    // protected abstract PanelPeer createPanel(java.awt.Panel target);
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Window</code> using
     // * the specified peer interface.
     // * @param     target the window to be implemented.
     // * @return    this toolkit's implementation of <code>Window</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Window
     // * @see       java.awt.peer.WindowPeer
     // */
    // protected abstract WindowPeer createWindow(java.awt.Window target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Dialog</code> using
     // * the specified peer interface.
     // * @param     target the dialog to be implemented.
     // * @return    this toolkit's implementation of <code>Dialog</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Dialog
     // * @see       java.awt.peer.DialogPeer
     // */
    // protected abstract DialogPeer createDialog(java.awt.Dialog target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>MenuBar</code> using
     // * the specified peer interface.
     // * @param     target the menu bar to be implemented.
     // * @return    this toolkit's implementation of <code>MenuBar</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.MenuBar
     // * @see       java.awt.peer.MenuBarPeer
     // */
    // protected abstract MenuBarPeer createMenuBar(java.awt.MenuBar target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Menu</code> using
     // * the specified peer interface.
     // * @param     target the menu to be implemented.
     // * @return    this toolkit's implementation of <code>Menu</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.Menu
     // * @see       java.awt.peer.MenuPeer
     // */
    // protected abstract MenuPeer createMenu(java.awt.Menu target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>PopupMenu</code> using
     // * the specified peer interface.
     // * @param     target the popup menu to be implemented.
     // * @return    this toolkit's implementation of <code>PopupMenu</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.PopupMenu
     // * @see       java.awt.peer.PopupMenuPeer
     // * @since     JDK1.1
     // */
    // protected abstract PopupMenuPeer createPopupMenu(java.awt.PopupMenu target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>MenuItem</code> using
     // * the specified peer interface.
     // * @param     target the menu item to be implemented.
     // * @return    this toolkit's implementation of <code>MenuItem</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.MenuItem
     // * @see       java.awt.peer.MenuItemPeer
     // */
    // protected abstract MenuItemPeer createMenuItem(java.awt.MenuItem target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>FileDialog</code> using
     // * the specified peer interface.
     // * @param     target the file dialog to be implemented.
     // * @return    this toolkit's implementation of <code>FileDialog</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.FileDialog
     // * @see       java.awt.peer.FileDialogPeer
     // */
    // protected abstract FileDialogPeer createFileDialog(java.awt.FileDialog
        // target) throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates this toolkit's implementation of <code>CheckboxMenuItem</code> using
     // * the specified peer interface.
     // * @param     target the checkbox menu item to be implemented.
     // * @return    this toolkit's implementation of <code>CheckboxMenuItem</code>.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @see       java.awt.CheckboxMenuItem
     // * @see       java.awt.peer.CheckboxMenuItemPeer
     // */
    // protected abstract CheckboxMenuItemPeer
        // createCheckboxMenuItem(java.awt.CheckboxMenuItem target)
        // throws java.awt.HeadlessException;
// 
    // /** 
     // * Creates a peer for a component or container.  This peer is windowless
     // * and allows the Component and Container classes to be extended directly
     // * to create windowless components that are defined entirely in java.
     // *
     // * @param target The Component to be created.
     // */
    // protected LightweightPeer createComponent(java.awt.Component target) { }
// 
    // /** 
     // * Creates this toolkit's implementation of <code>Font</code> using
     // * the specified peer interface.
     // * @param     name the font to be implemented
     // * @param     style the style of the font, such as <code>PLAIN</code>,
     // *            <code>BOLD</code>, <code>ITALIC</code>, or a combination
     // * @return    this toolkit's implementation of <code>Font</code>
     // * @see       java.awt.Font
     // * @see       java.awt.peer.FontPeer
     // * @see       java.awt.GraphicsEnvironment#getAllFonts
     // * @deprecated  see java.awt.GraphicsEnvironment#getAllFonts
     // */
    // protected abstract FontPeer getFontPeer(String name, int style);

  // PBP/PP
  // [6237275]
    /** 
     * Fills in the integer array that is supplied as an argument
     * with the current system color values.
     *
     * <em>Note: The order of color values stored in the
     * <code>systemColors</code> array is implementation-dependent.</em>
     *
     * @param     systemColors an integer array.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     JDK1.1
     */
    protected void loadSystemColors(int[] systemColors)
        throws java.awt.HeadlessException
    { }

    // /** 
     // * Controls whether the layout of Containers is validated dynamically
     // * during resizing, or statically, after resizing is complete.
     // * Note that this feature is not supported on all platforms, and
     // * conversely, that this feature cannot be turned off on some platforms.
     // * On platforms where dynamic layout during resize is not supported
     // * (or is always supported), setting this property has no effect.
     // * Note that this feature can be set or unset as a property of the
     // * operating system or window manager on some platforms.  On such
     // * platforms, the dynamic resize property must be set at the operating
     // * system or window manager level before this method can take effect.
     // * This method does not change the underlying operating system or
     // * window manager support or settings.  The OS/WM support can be
     // * queried using getDesktopProperty("awt.dynamicLayoutSupported").
     // *
     // * @param     dynamic  If true, Containers should re-layout their
     // *            components as the Container is being resized.  If false,
     // *            the layout will be validated after resizing is finished.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // *            returns true
     // * @see       #isDynamicLayoutSet()
     // * @see       #isDynamicLayoutActive()
     // * @see       #getDesktopProperty(String propertyName)
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @since     1.4
     // */
    // public void setDynamicLayout(boolean dynamic)
        // throws java.awt.HeadlessException
    // { }

    // /** 
     // * Returns whether the layout of Containers is validated dynamically
     // * during resizing, or statically, after resizing is complete.
     // * Note: this method returns the value that was set programmatically;
     // * it does not reflect support at the level of the operating system
     // * or window manager for dynamic layout on resizing, or the current
     // * operating system or window manager settings.  The OS/WM support can
     // * be queried using getDesktopProperty("awt.dynamicLayoutSupported").
     // *
     // * @return    true if validation of Containers is done dynamically,
     // *            false if validation is done after resizing is finished.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // *            returns true
     // * @see       #setDynamicLayout(boolean dynamic)
     // * @see       #isDynamicLayoutActive()
     // * @see       #getDesktopProperty(String propertyName)
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @since     1.4
     // */
    // protected boolean isDynamicLayoutSet() throws java.awt.HeadlessException { }

    // /** 
     // * Returns whether dynamic layout of Containers on resize is
     // * currently active (both set programmatically, and supported
     // * by the underlying operating system and/or window manager).
     // * The OS/WM support can be queried using 
     // * getDesktopProperty("awt.dynamicLayoutSupported").
     // *
     // * @return    true if dynamic layout of Containers on resize is
     // *            currently active, false otherwise.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // *            returns true
     // * @see       #setDynamicLayout(boolean dynamic)
     // * @see       #isDynamicLayoutSet()
     // * @see       #getDesktopProperty(String propertyName)
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @since     1.4
     // */
    // public boolean isDynamicLayoutActive() throws java.awt.HeadlessException { }

    //      * @see       java.awt.GraphicsDevice#getDisplayMode
    // removed from below...
    
    /** 
     * Gets the size of the screen.  On systems with multiple displays, the
     * primary display is used.  Multi-screen aware display dimensions are
     * available from <code>GraphicsConfiguration</code> and
     * <code>GraphicsDevice</code>.
     * @return    the size of this toolkit's screen, in pixels.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsConfiguration#getBounds
     * @see       java.awt.GraphicsEnvironment#isHeadless
     */
    public abstract java.awt.Dimension getScreenSize()
        throws java.awt.HeadlessException;

    /** 
     * Returns the screen resolution in dots-per-inch.
     * @return    this toolkit's screen resolution, in dots-per-inch.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     */
    public abstract int getScreenResolution() throws java.awt.HeadlessException;

    /** 
     * Gets the insets of the screen.
     * @param     gc a <code>GraphicsConfiguration</code>
     * @return    the insets of this toolkit's screen, in pixels.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @since     1.4
     */
    public java.awt.Insets getScreenInsets(java.awt.GraphicsConfiguration gc)
        throws java.awt.HeadlessException
    {
      return null;
    }

    /** 
     * Determines the color model of this toolkit's screen.
     * <p>
     * <code>ColorModel</code> is an abstract class that
     * encapsulates the ability to translate between the
     * pixel values of an image and its red, green, blue,
     * and alpha components.
     * <p>
     * This toolkit method is called by the
     * <code>getColorModel</code> method
     * of the <code>Component</code> class.
     * @return    the color model of this toolkit's screen.
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true
     * @see       java.awt.GraphicsEnvironment#isHeadless
     * @see       java.awt.image.ColorModel
     * @see       java.awt.Component#getColorModel
     */
    public abstract ColorModel getColorModel()
        throws java.awt.HeadlessException;

    /** 
     * Returns the names of the available fonts in this toolkit.<p>
     * For 1.1, the following font names are deprecated (the replacement
     * name follows):
     * <ul>
     * <li>TimesRoman (use Serif)
     * <li>Helvetica (use SansSerif)
     * <li>Courier (use Monospaced)
     * </ul><p>
     * The ZapfDingbats fontname is also deprecated in 1.1 but the characters
     * are defined in Unicode starting at 0x2700, and as of 1.1 Java supports
     * those characters.
     * @return    the names of the available fonts in this toolkit.
     * @deprecated see {@link java.awt.GraphicsEnvironment#getAvailableFontFamilyNames()}
     * @see java.awt.GraphicsEnvironment#getAvailableFontFamilyNames()
     */
    public abstract String[] getFontList();

// PBP/PP 6216201 (fixed deprecated text)
   /** 
     * Gets the screen device metrics for rendering of the font.
     * @param     font   a font
     * @return    the screen metrics of the specified font in this toolkit
     * @deprecated  Deprecated. This returns integer metrics for 
     * the default screen.
     *
     * 
     * 
     * @see java.awt.GraphicsEnvironment#getScreenDevices
     */
    public abstract java.awt.FontMetrics getFontMetrics(java.awt.Font font);

    /** 
     * Synchronizes this toolkit's graphics state. Some window systems
     * may do buffering of graphics events.
     * <p>
     * This method ensures that the display is up-to-date. It is useful
     * for animation.
     */
    public abstract void sync();

    /** 
     * Gets the default toolkit.
     * <p>
     * If there is a system property named <code>"awt.toolkit"</code>,
     * that property is treated as the name of a class that is a subclass
     * of <code>Toolkit</code>.
     * <p>
     * If the system property does not exist, then the default toolkit
     * used is the class named <code>"sun.awt.motif.MToolkit"</code>,
     * which is a motif implementation of the Abstract Window Toolkit.
     * <p>
     * Also loads additional classes into the VM, using the property
     * 'assistive_technologies' specified in the Sun reference
     * implementation by a line in the 'accessibility.properties'
     * file.  The form is "assistive_technologies=..." where
     * the "..." is a comma-separated list of assistive technology
     * classes to load.  Each class is loaded in the order given
     * and a single instance of each is created using
     * Class.forName(class).newInstance().  This is done just after
     * the AWT toolkit is created.  All errors are handled via an
     * AWTError exception.
     * @return    the default toolkit.
     * @exception  AWTError  if a toolkit could not be found, or
     *                 if one could not be accessed or instantiated.
     */
    public static synchronized java.awt.Toolkit getDefaultToolkit() { return null; }

    /** 
     * Returns an image which gets pixel data from the specified file,
     * whose format can be either GIF, JPEG or PNG.
     * The underlying toolkit attempts to resolve multiple requests
     * with the same filename to the same returned Image.
     * Since the mechanism required to facilitate this sharing of
     * Image objects may continue to hold onto images that are no
     * longer of use for an indefinite period of time, developers
     * are encouraged to implement their own caching of images by
     * using the createImage variant wherever available.
     * @param     filename   the name of a file containing pixel data
     *                         in a recognized file format.
     * @return    an image which gets its pixel data from
     *                         the specified file.
     * 
     * <!-- PBP/PP -->
     * 
     * @throws SecurityException if a security manager exists and its
     * <code>checkRead</code> method denies read access to the filename.
     * 
     * @see #createImage(java.lang.String)
     * 
     * @see SecurityManager#checkRead(java.lang.String)
     */
    public abstract java.awt.Image getImage(String filename);

  // PBP/PP [6262540]
  // Fix for J2SE bug 6262530
    /** 
     * Returns an image which gets pixel data from the specified URL.
     * The pixel data referenced by the specified URL must be in one
     * of the following formats: GIF, JPEG or PNG.
     * The underlying toolkit attempts to resolve multiple requests
     * with the same URL to the same returned Image.
     * Since the mechanism required to facilitate this sharing of
     * Image objects may continue to hold onto images that are no
     * longer of use for an indefinite period of time, developers
     * are encouraged to implement their own caching of images by
     * using the createImage variant wherever available.
     * <p>
     * <em>
     * This method will throw {@link SecurityException} if the
     * caller does not have the permission obtained from
     * <code>url.openConnection.getPermission()</code>.
     * For compatibility with pre-1.2 security managers, if the permission
     * is a {@link java.io.FilePermission} or a
     * {@link java.net.SocketPermission}, then the 1.1-style
     * <code>SecurityManager.checkXXX</code> methods are called instead of
     * {@link SecurityManager#checkPermission}.
     * </em>
     *
     * <!-- PBP/PP -->
     * @throws SecurityException If the caller does not have permission to
     * access this URL.
     *
     * @param     url   the URL to use in fetching the pixel data.
     * @return    an image which gets its pixel data from
     *                         the specified URL.
     * @see #createImage(java.net.URL)
     */
    public abstract java.awt.Image getImage(URL url);

    /** 
     * Returns an image which gets pixel data from the specified file.
     * The returned Image is a new object which will not be shared
     * with any other caller of this method or its getImage variant.
     * @param     filename   the name of a file containing pixel data
     *                         in a recognized file format.
     * @return    an image which gets its pixel data from
     *                         the specified file.
     * <!-- PBP/PP -->
     * 
     * @throws SecurityException if a security manager exists and its
     * <code>checkRead</code> method denies read access to the filename.
     * 
     * @see #getImage(java.lang.String)
     * 
     * @see SecurityManager#checkRead(java.lang.String)
     */
    public abstract java.awt.Image createImage(String filename);

  // PBP/PP [6262540]
  // Fix for J2SE bug 6262530
    /** 
     * Returns an image which gets pixel data from the specified URL.
     * The returned Image is a new object which will not be shared
     * with any other caller of this method or its getImage variant.
     *
     * <p>
     * <em>
     * This method will throw {@link SecurityException} if the
     * caller does not have the permission obtained from
     * <code>url.openConnection.getPermission()</code>.
     * For compatibility with pre-1.2 security managers, if the permission
     * is a {@link java.io.FilePermission} or a
     * {@link java.net.SocketPermission}, then the 1.1-style
     * <code>SecurityManager.checkXXX</code> methods are called instead of
     * {@link SecurityManager#checkPermission}.
     * </em>
     *
     * <!-- PBP/PP -->
     * @throws SecurityException If the caller does not have permission to
     * access this URL.
     *
     * @param     url   the URL to use in fetching the pixel data.
     * @return    an image which gets its pixel data from
     *                         the specified URL.
     * @see #getImage(java.net.URL)
     */
    public abstract java.awt.Image createImage(URL url);

    /** 
     * Prepares an image for rendering.
     * <p>
     * If the values of the width and height arguments are both
     * <code>-1</code>, this method prepares the image for rendering
     * on the default screen; otherwise, this method prepares an image
     * for rendering on the default screen at the specified width and height.
     * <p>
     * The image data is downloaded asynchronously in another thread,
     * and an appropriately scaled screen representation of the image is
     * generated.
     * <p>
     * This method is called by components <code>prepareImage</code>
     * methods.
     * <p>
     * Information on the flags returned by this method can be found
     * with the definition of the <code>ImageObserver</code> interface.
     *
     * @param     image      the image for which to prepare a
     *                           screen representation.
     * @param     width      the width of the desired screen
     *                           representation, or <code>-1</code>.
     * @param     height     the height of the desired screen
     *                           representation, or <code>-1</code>.
     * @param     observer   the <code>ImageObserver</code>
     *                           object to be notified as the
     *                           image is being prepared.
     * @return    <code>true</code> if the image has already been
     *                 fully prepared; <code>false</code> otherwise.
     * @see       java.awt.Component#prepareImage(java.awt.Image,
     *                 java.awt.image.ImageObserver)
     * @see       java.awt.Component#prepareImage(java.awt.Image,
     *                 int, int, java.awt.image.ImageObserver)
     * @see       java.awt.image.ImageObserver
     */
    public abstract boolean prepareImage(java.awt.Image image, int width, int
        height, ImageObserver observer);

    /** 
     * Indicates the construction status of a specified image that is
     * being prepared for display.
     * <p>
     * If the values of the width and height arguments are both
     * <code>-1</code>, this method returns the construction status of
     * a screen representation of the specified image in this toolkit.
     * Otherwise, this method returns the construction status of a
     * scaled representation of the image at the specified width
     * and height.
     * <p>
     * This method does not cause the image to begin loading.
     * An application must call <code>prepareImage</code> to force
     * the loading of an image.
     * <p>
     * This method is called by the component's <code>checkImage</code>
     * methods.
     * <p>
     * Information on the flags returned by this method can be found
     * with the definition of the <code>ImageObserver</code> interface.
     * @param     image   the image whose status is being checked.
     * @param     width   the width of the scaled version whose status is
     *                 being checked, or <code>-1</code>.
     * @param     height  the height of the scaled version whose status
     *                 is being checked, or <code>-1</code>.
     * @param     observer   the <code>ImageObserver</code> object to be
     *                 notified as the image is being prepared.
     * @return    the bitwise inclusive <strong>OR</strong> of the
     *                 <code>ImageObserver</code> flags for the
     *                 image data that is currently available.
     * @see       java.awt.Toolkit#prepareImage(java.awt.Image,
     *                 int, int, java.awt.image.ImageObserver)
     * @see       java.awt.Component#checkImage(java.awt.Image,
     *                 java.awt.image.ImageObserver)
     * @see       java.awt.Component#checkImage(java.awt.Image,
     *                 int, int, java.awt.image.ImageObserver)
     * @see       java.awt.image.ImageObserver
     */
    public abstract int checkImage(java.awt.Image image, int width, int height,
        ImageObserver observer);

    /** 
     * Creates an image with the specified image producer.
     * @param     producer the image producer to be used.
     * @return    an image with the specified image producer.
     * @see       java.awt.Image
     * @see       java.awt.image.ImageProducer
     * @see       java.awt.Component#createImage(java.awt.image.ImageProducer)
     */
    public abstract java.awt.Image createImage(ImageProducer producer);

    /** 
     * Creates an image which decodes the image stored in the specified
     * byte array.
     * <p>
     * The data must be in some image format, such as GIF or JPEG,
     * that is supported by this toolkit.
     * @param     imagedata   an array of bytes, representing
     *                         image data in a supported image format.
     * @return    an image.
     * @since     JDK1.1
     */
    public java.awt.Image createImage(byte[] imagedata) { return null; }

    /** 
     * Creates an image which decodes the image stored in the specified
     * byte array, and at the specified offset and length.
     * The data must be in some image format, such as GIF or JPEG,
     * that is supported by this toolkit.
     * @param     imagedata   an array of bytes, representing
     *                         image data in a supported image format.
     * @param     imageoffset  the offset of the beginning
     *                         of the data in the array.
     * @param     imagelength  the length of the data in the array.
     * @return    an image.
     * @since     JDK1.1
     */
    public abstract java.awt.Image createImage(byte[] imagedata, int
        imageoffset, int imagelength);

    // /** 
     // * Gets a <code>PrintJob</code> object which is the result of initiating
     // * a print operation on the toolkit's platform.
     // * <p>
     // * Each actual implementation of this method should first check if there 
     // * is a security manager installed. If there is, the method should call
     // * the security manager's <code>checkPrintJobAccess</code> method to
     // * ensure initiation of a print operation is allowed. If the default
     // * implementation of <code>checkPrintJobAccess</code> is used (that is,
     // * that method is not overriden), then this results in a call to the
     // * security manager's <code>checkPermission</code> method with a <code>
     // * RuntimePermission("queuePrintJob")</code> permission.
     // *
     // * @param	frame the parent of the print dialog. May not be null.
     // * @param	jobtitle the title of the PrintJob. A null title is equivalent
     // *		to "".
     // * @param	props a Properties object containing zero or more properties.
     // *		Properties are not standardized and are not consistent across
     // *		implementations. Because of this, PrintJobs which require job
     // *		and page control should use the version of this function which
     // *		takes JobAttributes and PageAttributes objects. This object
     // *		may be updated to reflect the user's job choices on exit. May
     // *		be null.
     // *
     // * @return	a <code>PrintJob</code> object, or <code>null</code> if the
     // *		user cancelled the print job.
     // * @throws	NullPointerException if frame is null.  This exception is
     // *          always thrown when GraphicsEnvironment.isHeadless() returns
     // *          true.
     // * @throws	SecurityException if this thread is not allowed to initiate a
     // *		print job request
     // * @see     java.awt.GraphicsEnvironment#isHeadless
     // * @see	java.awt.PrintJob
     // * @see	java.lang.RuntimePermission
     // * @since	JDK1.1
     // */
    // public abstract java.awt.PrintJob getPrintJob(java.awt.Frame frame, String
        // jobtitle, Properties props);

    // /** 
     // * Gets a <code>PrintJob</code> object which is the result of initiating
     // * a print operation on the toolkit's platform.
     // * <p>
     // * Each actual implementation of this method should first check if there 
     // * is a security manager installed. If there is, the method should call
     // * the security manager's <code>checkPrintJobAccess</code> method to
     // * ensure initiation of a print operation is allowed. If the default
     // * implementation of <code>checkPrintJobAccess</code> is used (that is,
     // * that method is not overriden), then this results in a call to the
     // * security manager's <code>checkPermission</code> method with a <code>
     // * RuntimePermission("queuePrintJob")</code> permission.
     // *
     // * @param	frame the parent of the print dialog. May be null if and only
     // *		if jobAttributes is not null and jobAttributes.getDialog()
     // *		returns	JobAttributes.DialogType.NONE or
     // *		JobAttributes.DialogType.COMMON.
     // * @param	jobtitle the title of the PrintJob. A null title is equivalent
     // *		to "".
     // * @param	jobAttributes a set of job attributes which will control the
     // *		PrintJob. The attributes will be updated to reflect the user's
     // *		choices as outlined in the JobAttributes documentation. May be
     // *		null.
     // * @param	pageAttributes a set of page attributes which will control the
     // *		PrintJob. The attributes will be applied to every page in the
     // *		job. The attributes will be updated to reflect the user's
     // *		choices as outlined in the PageAttributes documentation. May be
     // *		null.
     // *
     // * @return	a <code>PrintJob</code> object, or <code>null</code> if the
     // *		user cancelled the print job.
     // * @throws	NullPointerException if frame is null and either jobAttributes
     // *		is null or jobAttributes.getDialog() returns
     // *		JobAttributes.DialogType.NATIVE.
     // * @throws	IllegalArgumentException if pageAttributes specifies differing
     // *		cross feed and feed resolutions.  This exception is always
     // *          thrown when GraphicsEnvironment.isHeadless() returns true.
     // * @throws	SecurityException if this thread is not allowed to initiate a
     // *		print job request, or if jobAttributes specifies print to file,
     // *		and this thread is not allowed to access the file system
     // * @see	java.awt.PrintJob
     // * @see     java.awt.GraphicsEnvironment#isHeadless
     // * @see	java.lang.RuntimePermission
     // * @see	java.awt.JobAttributes
     // * @see	java.awt.PageAttributes
     // * @since	1.3
     // */
    // public java.awt.PrintJob getPrintJob(java.awt.Frame frame, String jobtitle,
        // java.awt.JobAttributes jobAttributes, java.awt.PageAttributes
        // pageAttributes)
    // { }

    /** 
     * Emits an audio beep.
     * @since     JDK1.1
     */
    public abstract void beep();

//    /** 
//     * Gets the singleton instance of the system Clipboard which interfaces
//     * with clipboard facilities provided by the native platform. This 
//     * clipboard enables data transfer between Java programs and native
//     * applications which use native clipboard facilities.
//     * <p>
//     * In addition to any and all formats specified in the flavormap.properties
//     * file, or other file specified by the <code>AWT.DnD.flavorMapFileURL
//     * </code> Toolkit property, text returned by the system Clipboard's <code>
//     * getTransferData()</code> method is available in the following flavors:
//     * <ul>
//     * <li>DataFlavor.stringFlavor</li>
//     * <li>DataFlavor.plainTextFlavor (<b>deprecated</b>)</li>
//     * </ul>
//     * As with <code>java.awt.datatransfer.StringSelection</code>, if the
//     * requested flavor is <code>DataFlavor.plainTextFlavor</code>, or an
//     * equivalent flavor, a Reader is returned. <b>Note:</b> The behavior of
//     * the system Clipboard's <code>getTransferData()</code> method for <code>
//     * DataFlavor.plainTextFlavor</code>, and equivalent DataFlavors, is
//     * inconsistent with the definition of <code>DataFlavor.plainTextFlavor
//     * </code>. Because of this, support for <code>
//     * DataFlavor.plainTextFlavor</code>, and equivalent flavors, is
//     * <b>deprecated</b>.
//     * <p>
//     * Each actual implementation of this method should first check if there
//     * is a security manager installed. If there is, the method should call
//     * the security manager's <code>checkSystemClipboardAccess</code> method
//     * to ensure it's ok to to access the system clipboard. If the default
//     * implementation of <code>checkSystemClipboardAccess</code> is used (that
//     * is, that method is not overriden), then this results in a call to the
//     * security manager's <code>checkPermission</code> method with an <code>
//     * AWTPermission("accessClipboard")</code> permission.
//     * 
//     * @return    the system Clipboard
//     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
//    * returns true
//     * @see       java.awt.GraphicsEnvironment#isHeadless
//    * @see       java.awt.datatransfer.Clipboard
//     * @see       java.awt.datatransfer.StringSelection
//     * @see       java.awt.datatransfer.DataFlavor#stringFlavor
//     * @see       java.awt.datatransfer.DataFlavor#plainTextFlavor
//     * @see       java.io.Reader
//     * @see       java.awt.AWTPermission
//     * @since     JDK1.1
//     */
//    public abstract Clipboard getSystemClipboard()
//        throws java.awt.HeadlessException;

    // /** 
     // * Gets the singleton instance of the system selection as a
     // * <code>Clipboard</code> object. This allows an application to read and
     // * modify the current, system-wide selection.
     // * <p>
     // * An application is responsible for updating the system selection whenever
     // * the user selects text, using either the mouse or the keyboard.
     // * Typically, this is implemented by installing a
     // * <code>FocusListener</code> on all <code>Component</code>s which support
     // * text selection, and, between <code>FOCUS_GAINED</code> and
     // * <code>FOCUS_LOST</code> events delivered to that <code>Component</code>,
     // * updating the system selection <code>Clipboard</code> when the selection
     // * changes inside the <code>Component</code>. Properly updating the system
     // * selection ensures that a Java application will interact correctly with
     // * native applications and other Java applications running simultaneously
     // * on the system. Note that <code>java.awt.TextComponent</code> and
     // * <code>javax.swing.text.JTextComponent</code> already adhere to this
     // * policy. When using these classes, and their subclasses, developers need
     // * not write any additional code.
     // * <p>
     // * Some platforms do not support a system selection <code>Clipboard</code>.
     // * On those platforms, this method will return <code>null</code>. In such a
     // * case, an application is absolved from its responsibility to update the
     // * system selection <code>Clipboard</code> as described above.
     // * <p>
     // * Each actual implementation of this method should first check if there
     // * is a <code>SecurityManager</code> installed. If there is, the method
     // * should call the <code>SecurityManager</code>'s
     // * <code>checkSystemClipboardAccess</code> method to ensure that client
     // * code has access the system selection. If the default implementation of
     // * <code>checkSystemClipboardAccess</code> is used (that is, if the method
     // * is not overridden), then this results in a call to the
     // * <code>SecurityManager</code>'s <code>checkPermission</code> method with
     // * an <code>AWTPermission("accessClipboard")</code> permission.
     // * 
     // * @return the system selection as a <code>Clipboard</code>, or
     // *         <code>null</code> if the native platform does not support a
     // *         system selection <code>Clipboard</code>
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // *            returns true
     // *
     // * @see java.awt.datatransfer.Clipboard
     // * @see java.awt.event.FocusListener
     // * @see java.awt.event.FocusEvent#FOCUS_GAINED
     // * @see java.awt.event.FocusEvent#FOCUS_LOST
     // * @see TextComponent
     // * @see javax.swing.text.JTextComponent
     // * @see AWTPermission
     // * @see GraphicsEnvironment#isHeadless
     // * @since 1.4
     // */
    // public Clipboard getSystemSelection() throws java.awt.HeadlessException { }

//    /** 
//     * Determines which modifier key is the appropriate accelerator
//     * key for menu shortcuts.
//     * <p>
//     * Menu shortcuts, which are embodied in the
//     * <code>MenuShortcut</code> class, are handled by the
//     * <code>MenuBar</code> class.
//     * <p>
//     * By default, this method returns <code>Event.CTRL_MASK</code>.
//     * Toolkit implementations should override this method if the
//     * <b>Control</b> key isn't the correct key for accelerators.
//     * @return    the modifier mask on the <code>Event</code> class
//     *                 that is used for menu shortcuts on this toolkit.
//     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
//     * returns true
//     * @see       java.awt.GraphicsEnvironment#isHeadless
//     * @see       java.awt.MenuBar
//     * @see       java.awt.MenuShortcut
//     * @since     JDK1.1
//     */
//    public int getMenuShortcutKeyMask() throws java.awt.HeadlessException { return 0; }

    // /** 
     // * Returns whether the given locking key on the keyboard is currently in
     // * its "on" state.
     // * Valid key codes are
     // * {@link java.awt.event.KeyEvent#VK_CAPS_LOCK VK_CAPS_LOCK},
     // * {@link java.awt.event.KeyEvent#VK_NUM_LOCK VK_NUM_LOCK},
     // * {@link java.awt.event.KeyEvent#VK_SCROLL_LOCK VK_SCROLL_LOCK}, and
     // * {@link java.awt.event.KeyEvent#VK_KANA_LOCK VK_KANA_LOCK}.
     // *
     // * @exception java.lang.IllegalArgumentException if <code>keyCode</code>
     // * is not one of the valid key codes
     // * @exception java.lang.UnsupportedOperationException if the host system doesn't
     // * allow getting the state of this key programmatically, or if the keyboard
     // * doesn't have this key
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @since 1.3
     // */
    // public boolean getLockingKeyState(int keyCode)
        // throws UnsupportedOperationException
    // { }

    // /** 
     // * Sets the state of the given locking key on the keyboard.
     // * Valid key codes are
     // * {@link java.awt.event.KeyEvent#VK_CAPS_LOCK VK_CAPS_LOCK},
     // * {@link java.awt.event.KeyEvent#VK_NUM_LOCK VK_NUM_LOCK},
     // * {@link java.awt.event.KeyEvent#VK_SCROLL_LOCK VK_SCROLL_LOCK}, and
     // * {@link java.awt.event.KeyEvent#VK_KANA_LOCK VK_KANA_LOCK}.
     // * <p>
     // * Depending on the platform, setting the state of a locking key may
     // * involve event processing and therefore may not be immediately
     // * observable through getLockingKeyState.
     // *
     // * @exception java.lang.IllegalArgumentException if <code>keyCode</code>
     // * is not one of the valid key codes
     // * @exception java.lang.UnsupportedOperationException if the host system doesn't
     // * allow setting the state of this key programmatically, or if the keyboard
     // * doesn't have this key
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @since 1.3
     // */
    // public void setLockingKeyState(int keyCode, boolean on)
        // throws UnsupportedOperationException
    // { }

    // /** 
     // * Give native peers the ability to query the native container
     // * given a native component (eg the direct parent may be lightweight).
     // */
    // protected static java.awt.Container getNativeContainer(java.awt.Component c)
    // { }

    // /** 
     // * Creates a new custom cursor object.
     // * If the image to display is invalid, the cursor will be hidden (made
     // * completely transparent), and the hotspot will be set to (0, 0). 
     // *
     // * <p>Note that multi-frame images are invalid and may cause this
     // * method to hang.
     // *
     // * @param cursor the image to display when the cursor is actived
     // * @param hotSpot the X and Y of the large cursor's hot spot; the
     // *   hotSpot values must be less than the Dimension returned by
     // *   <code>getBestCursorSize</code>
     // * @param     name a localized description of the cursor, for Java Accessibility use
     // * @exception IndexOutOfBoundsException if the hotSpot values are outside
     // *   the bounds of the cursor
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @since     1.2
     // */
    // public java.awt.Cursor createCustomCursor(java.awt.Image cursor,
        // java.awt.Point hotSpot, String name)
        // throws IndexOutOfBoundsException, java.awt.HeadlessException
    // { }

    // /** 
     // * Returns the supported cursor dimension which is closest to the desired
     // * sizes.  Systems which only support a single cursor size will return that
     // * size regardless of the desired sizes.  Systems which don't support custom
     // * cursors will return a dimension of 0, 0. <p>
     // * Note:  if an image is used whose dimensions don't match a supported size
     // * (as returned by this method), the Toolkit implementation will attempt to
     // * resize the image to a supported size.
     // * Since converting low-resolution images is difficult,
     // * no guarantees are made as to the quality of a cursor image which isn't a
     // * supported size.  It is therefore recommended that this method
     // * be called and an appropriate image used so no image conversion is made.
     // *
     // * @param     preferredWidth the preferred cursor width the component would like
     // * to use.
     // * @param     preferredHeight the preferred cursor height the component would like
     // * to use.
     // * @return    the closest matching supported cursor size, or a dimension of 0,0 if
     // * the Toolkit implementation doesn't support custom cursors.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @since     1.2
     // */
    // public java.awt.Dimension getBestCursorSize(int preferredWidth, int
        // preferredHeight) throws java.awt.HeadlessException
    // { }

    // /** 
     // * Returns the maximum number of colors the Toolkit supports in a custom cursor
     // * palette.<p>
     // * Note: if an image is used which has more colors in its palette than
     // * the supported maximum, the Toolkit implementation will attempt to flatten the
     // * palette to the maximum.  Since converting low-resolution images is difficult,
     // * no guarantees are made as to the quality of a cursor image which has more
     // * colors than the system supports.  It is therefore recommended that this method
     // * be called and an appropriate image used so no image conversion is made.
     // *
     // * @return    the maximum number of colors, or zero if custom cursors are not
     // * supported by this Toolkit implementation.
     // * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     // * returns true
     // * @see       java.awt.GraphicsEnvironment#isHeadless
     // * @since     1.2
     // */
    // public int getMaximumCursorColors() throws java.awt.HeadlessException { }

  // PBP/PP
    /** 
     * Returns whether Toolkit supports this state for
     * <code>Frame</code>s.  This method tells whether the <em>UI
     * concept</em> of, say,  iconification is
     * supported.  
     *
     * @param state one of named frame state constants.
     * @return <code>true</code> is this frame state is supported by
     *     this Toolkit implementation, <code>false</code> otherwise.
     * @exception HeadlessException
     *     if <code>GraphicsEnvironment.isHeadless()</code>
     *     returns <code>true</code>.
     * 
     * @since	1.4
     */
    public boolean isFrameStateSupported(int state)
        throws java.awt.HeadlessException
    { return false; }

    /** 
     * Gets a property with the specified key and default.
     * This method returns defaultValue if the property is not found.
     */
    public static String getProperty(String key, String defaultValue) { return null;}

    /** 
     * Get the application's or applet's EventQueue instance.
     * Depending on the Toolkit implementation, different EventQueues
     * may be returned for different applets.  Applets should
     * therefore not assume that the EventQueue instance returned
     * by this method will be shared by other applets or the system.
     * 
     * <p>First, if there is a security manager, its 
     * <code>checkAwtEventQueueAccess</code> 
     * method is called. 
     * If  the default implementation of <code>checkAwtEventQueueAccess</code> 
     * is used (that is, that method is not overriden), then this results in
     * a call to the security manager's <code>checkPermission</code> method
     * with an <code>AWTPermission("accessEventQueue")</code> permission.
     * 
     * @return    the <code>EventQueue</code> object.
     * @throws  SecurityException
     *          if a security manager exists and its <code>{@link
     *          java.lang.SecurityManager#checkAwtEventQueueAccess}</code> method denies
     *          access to the EventQueue.
     * @see       java.awt.AWTPermission
     */
    public final java.awt.EventQueue getSystemEventQueue() { return null;}

    protected abstract java.awt.EventQueue getSystemEventQueueImpl();

    // /** 
     // * Creates the peer for a DragSourceContext.
     // * Always throws InvalidDndOperationException if
     // * GraphicsEnvironment.isHeadless() returns true.
     // * @see java.awt.GraphicsEnvironment#isHeadless
     // */
    // public abstract DragSourceContextPeer
        // createDragSourceContextPeer(DragGestureEvent dge)
        // throws InvalidDnDOperationException;
// 
    // /** 
     // * Creates a concrete, platform dependent, subclass of the abstract
     // * DragGestureRecognizer class requested, and associates it with the
     // * DragSource, Component and DragGestureListener specified. 
     // *
     // * subclasses should override this to provide their own implementation
     // *
     // * @param abstractRecognizerClass The abstract class of the required recognizer
     // * @param ds		      The DragSource
     // * @param c			      The Component target for the DragGestureRecognizer
     // * @param srcActions	      The actions permitted for the gesture
     // * @param dgl		      The DragGestureListener
     // *
     // * @return the new object or null.  Always returns null if
     // * GraphicsEnvironment.isHeadless() returns true.
     // * @see java.awt.GraphicsEnvironment#isHeadless
     // */
    // public DragGestureRecognizer createDragGestureRecognizer(Class
        // abstractRecognizerClass, DragSource ds, java.awt.Component c, int
        // srcActions, DragGestureListener dgl)
    // { }

    // /** 
     // * Obtains a value for the specified desktop property.
     // *
     // * A desktop property is a uniquely named value for a resource that
     // * is Toolkit global in nature. Usually it also is an abstract 
     // * representation for an underlying platform dependent desktop setting. 
     // */
    // public final synchronized Object getDesktopProperty(String propertyName) { }
// 
    // /** 
     // * Sets the named desktop property to the specified value and fires a
     // * property change event to notify any listeners that the value has changed. 
     // */
    // protected final void setDesktopProperty(String name, Object newValue) { }
// 
    // /** 
     // * an opportunity to lazily evaluate desktop property values.
     // */
    // protected Object lazilyLoadDesktopProperty(String name) { }
// 
    // /** 
     // * initializeDesktopProperties
     // */
    // protected void initializeDesktopProperties() { }
// 
    // /** 
     // * Adds the specified property change listener for the named desktop 
     // * property.  
     // * If pcl is null, no exception is thrown and no action is performed.
     // *
     // * @param 	name The name of the property to listen for
     // * @param	pcl The property change listener
     // * @since	1.2
     // */
    // public synchronized void addPropertyChangeListener(String name,
        // PropertyChangeListener pcl)
    // { }

    // /** 
     // * Removes the specified property change listener for the named 
     // * desktop property. 
     // * If pcl is null, no exception is thrown and no action is performed.
     // *
     // * @param 	name The name of the property to remove
     // * @param	pcl The property change listener
     // * @since	1.2
     // */
    // public synchronized void removePropertyChangeListener(String name,
        // PropertyChangeListener pcl)
    // { }
// 
    // /** 
     // * Returns an array of all the property change listeners
     // * registered on this toolkit.
     // *
     // * @return all of this toolkit's <code>PropertyChangeListener</code>s
     // *         or an empty array if no property change 
     // *         listeners are currently registered
     // *
     // * @since 1.4
     // */
    // public PropertyChangeListener[] getPropertyChangeListeners() { }

    // /** 
     // * Returns an array of all the <code>PropertyChangeListener</code>s
     // * associated with the named property.
     // *
     // * @param  propertyName the named property
     // * @return all of the <code>PropertyChangeListener</code>s associated with
     // *         the named property or an empty array if no such listeners have
     // *         been added
     // * @since 1.4
     // */
    // public synchronized PropertyChangeListener[]
        // getPropertyChangeListeners(String propertyName)
    // { }
//     * @see      #getAWTEventListeners
    /** 
     * Adds an AWTEventListener to receive all AWTEvents dispatched
     * system-wide that conform to the given <code>eventMask</code>.
     * <p>
     * First, if there is a security manager, its <code>checkPermission</code> 
     * method is called with an 
     * <code>AWTPermission("listenToAllAWTEvents")</code> permission.
     * This may result in a SecurityException. 
     * <p>
     * <code>eventMask</code> is a bitmask of event types to receive.
     * It is constructed by bitwise OR-ing together the event masks
     * defined in <code>AWTEvent</code>.
     * <p>
     * Note:  event listener use is not recommended for normal
     * application use, but are intended solely to support special
     * purpose facilities including support for accessibility,
     * event record/playback, and diagnostic tracing.
     *
     * If listener is null, no exception is thrown and no action is performed.
     *
     * @param    listener   the event listener.
     * @param    eventMask  the bitmask of event types to receive
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @see      #removeAWTEventListener
     * @see      SecurityManager#checkPermission
     * @see      java.awt.AWTEvent
     * @see      java.awt.AWTPermission
     * @see      java.awt.event.AWTEventListener
     * @see      java.awt.event.AWTEventListenerProxy
     * @since    1.2
     */
    public void addAWTEventListener(AWTEventListener listener, long eventMask)
    { }

    //     * @see      #getAWTEventListeners
    
    /** 
     * Removes an AWTEventListener from receiving dispatched AWTEvents.
     * <p>
     * First, if there is a security manager, its <code>checkPermission</code> 
     * method is called with an 
     * <code>AWTPermission("listenToAllAWTEvents")</code> permission.
     * This may result in a SecurityException. 
     * <p>
     * Note:  event listener use is not recommended for normal
     * application use, but are intended solely to support special
     * purpose facilities including support for accessibility,
     * event record/playback, and diagnostic tracing.
     *
     * If listener is null, no exception is thrown and no action is performed.
     *
     * @param    listener   the event listener.
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @see      #addAWTEventListener
     * @see      SecurityManager#checkPermission
     * @see      java.awt.AWTEvent
     * @see      java.awt.AWTPermission
     * @see      java.awt.event.AWTEventListener
     * @see      java.awt.event.AWTEventListenerProxy
     * @since    1.2
     */
    public void removeAWTEventListener(AWTEventListener listener) { }

  // PBP/PP
  // [6237573]
  // Text in <em></em> below is quoted from addAWTEventListener(), and
  // matches J2SE RI.
    /** 
     * Returns an array of all the <code>AWTEventListener</code>s 
     * registered on this toolkit.
     * <p>
     * <em>First, if there is a security manager, its
     * <code>checkPermission</code> 
     * method is called with an 
     * <code>AWTPermission("listenToAllAWTEvents")</code> permission.
     * This may result in a SecurityException.</em>
     * <p>
     * Listeners can be returned 
     * within <code>AWTEventListenerProxy</code> objects, which also contain 
     * the event mask for the given listener.
     * Note that listener objects
     * added multiple times appear only once in the returned array.
     *
     * @return all of the <code>AWTEventListener</code>s or an empty
     *         array if no listeners are currently registered 
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @see      #addAWTEventListener
     * @see      #removeAWTEventListener
     * @see      SecurityManager#checkPermission
     * @see      java.awt.AWTEvent
     * @see      java.awt.AWTPermission
     * @see      java.awt.event.AWTEventListener
     * @see      java.awt.event.AWTEventListenerProxy
     * @since 1.4
     */
    public AWTEventListener[] getAWTEventListeners() {
      return null;
    }

  // PBP/PP
  // [6237573]
  // Text in <em></em> below is quoted from addAWTEventListener(), and
  // matches J2SE RI.
    /** 
     * Returns an array of all the <code>AWTEventListener</code>s 
     * registered on this toolkit which listen to all of the event
     * types indicates in the <code>eventMask</code> argument.
     * <p>
     * <em>First, if there is a security manager, its
     * <code>checkPermission</code> 
     * method is called with an 
     * <code>AWTPermission("listenToAllAWTEvents")</code> permission.
     * This may result in a SecurityException.</em>
     * <p>
     * Listeners can be returned
     * within <code>AWTEventListenerProxy</code> objects, which also contain
     * the event mask for the given listener.
     * Note that listener objects
     * added multiple times appear only once in the returned array.
     *
     * @param  eventMask the bitmask of event types to listen for
     * @return all of the <code>AWTEventListener</code>s registered
     *         on this toolkit for the specified
     *         event types, or an empty array if no such listeners
     *         are currently registered
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow the operation.
     * @see      #addAWTEventListener
     * @see      #removeAWTEventListener
     * @see      SecurityManager#checkPermission
     * @see      java.awt.AWTEvent
     * @see      java.awt.AWTPermission
     * @see      java.awt.event.AWTEventListener
     * @see      java.awt.event.AWTEventListenerProxy
     * @since 1.4
     */
    public AWTEventListener[] getAWTEventListeners(long eventMask) {
      return null;
    }

     /** 
      * Returns a map of visual attributes for the abstract level description
      * of the given input method highlight, or null if no mapping is found.
      * The style field of the input method highlight is ignored. The map
      * returned is unmodifiable.
      * @param highlight input method highlight
      * @return style attribute map, or <code>null</code>
      * @exception HeadlessException if
      *     <code>GraphicsEnvironment.isHeadless</code> returns true
      * @see       java.awt.GraphicsEnvironment#isHeadless
      * @since 1.3
      */
//      public abstract Map mapInputMethodHighlight(InputMethodHighlight highlight)
//          throws java.awt.HeadlessException;
}
