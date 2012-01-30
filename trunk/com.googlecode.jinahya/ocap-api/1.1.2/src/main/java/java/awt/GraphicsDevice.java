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

import java.awt.image.ColorModel;

/** 
 * The <code>GraphicsDevice</code> class describes the graphics devices
 * that might be available in a particular graphics environment.  These
 * include screen and printer devices. Note that there can be many screens
 * and many printers in an instance of {@link GraphicsEnvironment}. Each
 * graphics device has one or more {@link GraphicsConfiguration} objects
 * associated with it.  These objects specify the different configurations
 * in which the <code>GraphicsDevice</code> can be used.
 * <p>  
 * In a multi-screen environment, the <code>GraphicsConfiguration</code>
 * objects can be used to render components on multiple screens.  The 
 * following code sample demonstrates how to create a
 * 
<!-- PBP/PP -->
 * <code>Frame</code>
 * object  on each screen
 * device in the <code>GraphicsEnvironment</code>:
 * <pre>
 *   GraphicsEnvironment ge = GraphicsEnvironment.
 *   getLocalGraphicsEnvironment();
 *   GraphicsDevice[] gs = ge.getScreenDevices();
 *   for (int j = 0; j < gs.length; j++) { 
 *      GraphicsDevice gd = gs[j];

<!-- PBP/PP -->
 *      Frame f = new
 *      Frame(gd.getDefaultConfiguration());
 *      f.show();
 *   }
 * </pre>
 * 
 <!-- PBP/PP 5091914 -->
 * <p>
 * <a name="clarifications">
 *
 * <h4>Clarifications</h4>
 * 
 * <em>
 * The following clarifications apply to implementations of this Profile:
 * <ul>
 *
 * <li> The size and location of Frame instances may be severely limited
 * in this profile.  Please see the note concerning
 * <a href="Window.html#geometry">size and location of <code>java.awt.Window</code></a>.
 * As a consequence, a <code>Frame</code> in full screen mode
 * need not span the entire screen.
 * <br>
 * See:
 * <ul>
 * <li> <a href="Window.html#geometry">Size and location of <code>java.awt.Window</code></a>
 * <li> {@link #setFullScreenWindow(Window)}
 * </ul>
 * </ul>
 * </em>
 * @see GraphicsEnvironment
 * @see GraphicsConfiguration
 * @version 1.27, 01/23/03
 */
public abstract class GraphicsDevice
{
    /** 
     * Device is a raster screen.
     */
    public static final int TYPE_RASTER_SCREEN = 0;

    /** 
     * Device is a printer.
     */
    public static final int TYPE_PRINTER = 1;

    /** 
     * Device is an image buffer.  This buffer can reside in device
     * or system memory but it is not physically viewable by the user.
     */
    public static final int TYPE_IMAGE_BUFFER = 2;

    /** 
     * This is an abstract class that cannot be instantiated directly.
     * Instances must be obtained from a suitable factory or query method.
     * @see GraphicsEnvironment#getScreenDevices
     * @see GraphicsEnvironment#getDefaultScreenDevice
     * @see GraphicsConfiguration#getDevice
     */
    protected GraphicsDevice() { }

    /** 
     * Returns the type of this <code>GraphicsDevice</code>.
     * @return the type of this <code>GraphicsDevice</code>, which can
     * either be TYPE_RASTER_SCREEN, TYPE_PRINTER or TYPE_IMAGE_BUFFER.
     * @see #TYPE_RASTER_SCREEN
     * @see #TYPE_PRINTER
     * @see #TYPE_IMAGE_BUFFER
     */
    public abstract int getType();

    /** 
     * Returns the identification string associated with this 
     * <code>GraphicsDevice</code>.
     * <p>
     * A particular program might use more than one 
     * <code>GraphicsDevice</code> in a <code>GraphicsEnvironment</code>.
     * This method returns a <code>String</code> identifying a
     * particular <code>GraphicsDevice</code> in the local
     * <code>GraphicsEnvironment</code>.  Although there is
     * no public method to set this <code>String</code>, a programmer can
     * use the <code>String</code> for debugging purposes.  Vendors of 
     * the Java<sup><font size=-2>TM</font></sup> Runtime Environment can
     * format the return value of the <code>String</code>.  To determine 
     * how to interpret the value of the <code>String</code>, contact the
     * vendor of your Java Runtime.  To find out who the vendor is, from
     * your program, call the 
     * {@link System#getProperty(String) getProperty} method of the
     * System class with "java.vendor".
     * @return a <code>String</code> that is the identification
     * of this <code>GraphicsDevice</code>.
     */
    public abstract String getIDstring();

    /** 
     * Returns all of the <code>GraphicsConfiguration</code>
     * objects associated with this <code>GraphicsDevice</code>.
     * @return an array of <code>GraphicsConfiguration</code>
     * objects that are associated with this 
     * <code>GraphicsDevice</code>.
     */
    public abstract GraphicsConfiguration[] getConfigurations();

    /** 
     * Returns the default <code>GraphicsConfiguration</code>
     * associated with this <code>GraphicsDevice</code>.
     * @return the default <code>GraphicsConfiguration</code>
     * of this <code>GraphicsDevice</code>.
     */
    public abstract GraphicsConfiguration getDefaultConfiguration();

    // /** 
     // * Returns the "best" configuration possible that passes the
     // * criteria defined in the {@link GraphicsConfigTemplate}.
     // * @param gct the <code>GraphicsConfigTemplate</code> object
     // * used to obtain a valid <code>GraphicsConfiguration</code>
     // * @return a <code>GraphicsConfiguration</code> that passes
     // * the criteria defined in the specified
     // * <code>GraphicsConfigTemplate</code>.
     // * @see GraphicsConfigTemplate
     // */
    // public GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate
        // gct)
    // { }

     /** 
      * Returns <code>true</code> if this <code>GraphicsDevice</code>
      * supports full-screen exclusive mode.
      * @return whether full-screen exclusive mode is available for
      * this graphics device
      * @since 1.4
      */
     public abstract boolean isFullScreenSupported();

    // PBP/PP
    // 5091914 removed assertion referring to setDisplayMode
    // removed:  * @see #setDisplayMode
     /** 
      * Enter full-screen mode, or return to windowed mode.
      * <p>
      * If <code>isFullScreenSupported</code> returns <code>true</code>, full
      * screen mode is considered to be <i>exclusive</i>, which implies:
      * <ul>
      * <li>Windows cannot overlap the full-screen window.  All other application
      * windows will always appear beneath the full-screen window in the Z-order.
      * <li>Input method windows are disabled.  It is advisable to call
      * <code>Component.enableInputMethods(false)</code> to make a component
      * a non-client of the input method framework.
      * </ul>
      * <p>
      * If <code>isFullScreenSupported</code> returns
      * <code>false</code>, full-screen exclusive mode is simulated by resizing
      * the window to the size of the screen and positioning it at (0,0).
      * <p>
      * 
      *
      <!-- PBP/PP -->
      * <em>Please note the <a href="#clarifications">clarification</a>
      * for this method.</em>
      *
      * @param w a window to use as the full-screen window; <code>null</code>
      * if returning to windowed mode.
      * @see #isFullScreenSupported
      * @see #getFullScreenWindow
      * @see Component#enableInputMethods
      * @since 1.4
      */
     public abstract void setFullScreenWindow(Window w);

  // PBP/PP
     /** 
      * Returns the <code>Window</code> object representing the 
      * full-screen window if the device is in full-screen mode
      * <em>and the <code>Window</code> is in the same context
      * as the calling thread</em>.
      *
      * @return the full-screen window 
      * <em>if the device is in full-screen mode
      * and the <code>Window</code> is in the same context
      * as the calling thread; <code>null</code> otherwise.</em>
      * @see #setFullScreenWindow(Window)
      * @since 1.4
      */
     public abstract Window getFullScreenWindow();

    // /** 
     // * Returns <code>true</code> if this <code>GraphicsDevice</code>
     // * supports low-level display changes.
     // * @return whether low-level display changes are supported for this
     // * graphics device.  Note that this may or may not be dependent on
     // * full-screen exclusive mode.
     // * @see #setDisplayMode
     // * @since 1.4
     // */
    // public boolean isDisplayChangeSupported() { }
// 
    // /** 
     // * Sets the display mode of this graphics device.  This may only be allowed
     // * in full-screen, exclusive mode.
     // * @param dm the new display mode of this graphics device
     // * @exception IllegalArgumentException if the <code>DisplayMode</code>
     // * supplied is <code>null</code>, or is not available in the array returned
     // * by <code>getDisplayModes</code>
     // * @exception UnsupportedOperationException if
     // * <code>isDisplayChangeSupported</code> returns <code>false</code>
     // * @see #getDisplayMode
     // * @see #getDisplayModes
     // * @see #isDisplayChangeSupported
     // * @since 1.4
     // */
    // public void setDisplayMode(DisplayMode dm) { }
// 
    // /** 
     // * Returns the current display mode of this 
     // * <code>GraphicsDevice</code>.
     // * @return the current display mode of this graphics device.
     // * @see #setDisplayMode(DisplayMode)
     // * @since 1.4
     // */
    // public DisplayMode getDisplayMode() { }
// 
    // /** 
     // * Returns all display modes available for this      
     // * <code>GraphicsDevice</code>.
     // * @return all of the display modes available for this graphics device.
     // * @since 1.4
     // */
    // public DisplayMode[] getDisplayModes() { }
// 
     /** 
      * This method returns the number of bytes available in
      * accelerated memory on this device.
      * Some images are created or cached
      * in accelerated memory on a first-come,
      * first-served basis.  On some operating systems,
      * this memory is a finite resource.  Calling this method
      * and scheduling the creation and flushing of images carefully may
      * enable applications to make the most efficient use of
      * that finite resource.
      * <br>
      * Note that the number returned is a snapshot of how much
      * memory is available; some images may still have problems
      * being allocated into that memory.  For example, depending
      * on operating system, driver, memory configuration, and
      * thread situations, the full extent of the size reported
      * may not be available for a given image.  There are further
      * inquiry methods on the {@link ImageCapabilities} object
      * associated with a VolatileImage that can be used to determine
      * whether a particular VolatileImage has been created in accelerated
      * memory.
      * @return number of bytes available in accelerated memory.
      * A negative return value indicates that accelerated memory
      * is unlimited.
      * @see java.awt.image.VolatileImage#flush
      * @see ImageCapabilities#isAccelerated
      */
     public abstract int getAvailableAcceleratedMemory();
}
