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

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Map;
import java.io.InputStream;
// import sun.java2d.HeadlessGraphicsEnvironment;
// import sun.java2d.SunGraphicsEnvironment;

/** 
 * The <code>GraphicsEnvironment</code> class describes the collection
 * of {@link GraphicsDevice} objects and {@link java.awt.Font} objects
 * available to a Java(tm) application on a particular platform.
 * The resources in this <code>GraphicsEnvironment</code> might be local
 * or on a remote machine.  <code>GraphicsDevice</code> objects can be
 * screens, printers or image buffers and are the destination of
 * {@link Graphics2D} drawing methods.  Each <code>GraphicsDevice</code>
 * has a number of {@link GraphicsConfiguration} objects associated with
 * it.  These objects specify the different configurations in which the
 * <code>GraphicsDevice</code> can be used.  
 * @see GraphicsDevice
 * @see GraphicsConfiguration
 * @version 	1.55, 01/23/03
 */
public abstract class GraphicsEnvironment
{

    /** 
     * This is an abstract class and cannot be instantiated directly.
     * Instances must be obtained from a suitable factory or query method.
     */
    protected GraphicsEnvironment() { }

    /** 
     * Returns the local <code>GraphicsEnvironment</code>.
     * @return the local <code>GraphicsEnvironment</code>
     */
    public static synchronized GraphicsEnvironment getLocalGraphicsEnvironment()
    { return null; }

// PBP/PP
// [6233000]
     /** 
      * Tests whether or not a display
      * <em>and some form of input device</em>
      * can be
      * supported in this environment.  If this method returns true,
      * a HeadlessException is thrown from areas of the Toolkit
      * and GraphicsEnvironment that are dependent on a display <em>or input device</em>.
      * @return <code>true</code> if this environment cannot support 
      * a display <em>and input device</em>; <code>false</code> 
      * otherwise
      * @see java.awt.HeadlessException
      * @since 1.4
      */
     public static boolean isHeadless() {return false;  }
// 
    // /** 
     // * Returns whether or not a display, keyboard, and mouse can be 
     // * supported in this graphics environment.  If this returns true,
     // * <code>HeadlessException</code> will be thrown from areas of the
     // * graphics environment that are dependent on a display, keyboard, or
     // * mouse.
     // * @return <code>true</code> if a display, keyboard, and mouse 
     // * can be supported in this environment; <code>false</code>
     // * otherwise
     // * @see java.awt.HeadlessException
     // * @see #isHeadless
     // * @since 1.4
     // */
    // public boolean isHeadlessInstance() { }

    /** 
     * Returns an array of all of the screen <code>GraphicsDevice</code>
     * objects.
     * @return an array containing all the <code>GraphicsDevice</code>
     * objects that represent screen devices
     * @exception HeadlessException if isHeadless() returns true
     * @see #isHeadless()
     */
    public abstract GraphicsDevice[] getScreenDevices()
        throws HeadlessException;

    /** 
     * Returns the default screen <code>GraphicsDevice</code>.
     * @return the <code>GraphicsDevice</code> that represents the
     * default screen device
     * @exception HeadlessException if isHeadless() returns true
     * @see #isHeadless()
     */
    public abstract GraphicsDevice getDefaultScreenDevice()
        throws HeadlessException;

    /** 
     * Returns a <code>Graphics2D</code> object for rendering into the
     * specified {@link BufferedImage}.
     * @param img the specified <code>BufferedImage</code>
     * @return a <code>Graphics2D</code> to be used for rendering into
     * the specified <code>BufferedImage</code>
     */
    public abstract Graphics2D createGraphics(BufferedImage img);

    // /** 
     // * Returns an array containing a one-point size instance of all fonts
     // * available in this <code>GraphicsEnvironment</code>.  Typical usage
     // * would be to allow a user to select a particular font.  Then, the
     // * application can size the font and set various font attributes by
     // * calling the <code>deriveFont</code> method on the choosen instance.
     // * <p>
     // * This method provides for the application the most precise control
     // * over which <code>Font</code> instance is used to render text.
     // * If a font in this <code>GraphicsEnvironment</code> has multiple
     // * programmable variations, only one
     // * instance of that <code>Font</code> is returned in the array, and
     // * other variations must be derived by the application.
     // * <p>
     // * If a font in this environment has multiple programmable variations,
     // * such as Multiple-Master fonts, only one instance of that font is
     // * returned in the <code>Font</code> array.  The other variations
     // * must be derived by the application.
     // *
     // * @return an array of <code>Font</code> objects
     // * @see #getAvailableFontFamilyNames
     // * @see java.awt.Font
     // * @see java.awt.Font#deriveFont
     // * @see java.awt.Font#getFontName
     // * @since 1.2
     // */
    // public abstract Font[] getAllFonts();

// PBP/PP
    /** 
     * Returns an array containing the names of all font families available
     * in this <code>GraphicsEnvironment</code>.
     * Typical usage would be to allow a user to select a particular family
     * name and allow the application to choose related variants of the
     * same family when the user specifies style attributes such
     * as Bold or Italic.
     * <p>
     * This method provides for the application some control over which
     * <code>Font</code> instance is used to render text, but allows the 
     * <code>Font</code> object more flexibility in choosing its own best
     * match among multiple fonts in the same font family.
     *
     * @return an array of <code>String</code> containing names of font
     * families

     * @see java.awt.Font
     * @see java.awt.Font#getFamily
     * @since 1.2
     */
    public abstract String[] getAvailableFontFamilyNames();

// PBP/PP
    /** 
     * Returns an array containing the localized names of all font families
     * available in this <code>GraphicsEnvironment</code>.
     * Typical usage would be to allow a user to select a particular family
     * name and allow the application to choose related variants of the
     * same family when the user specifies style attributes such
     * as Bold or Italic.
     * <p>
     * This method provides for the application some control over which
     * <code>Font</code> instance used to render text, but allows the 
     * <code>Font</code> object more flexibility in choosing its own best
     * match among multiple fonts in the same font family.
     * If <code>l</code> is <code>null</code>, this method returns an 
     * array containing all font family names available in this
     * <code>GraphicsEnvironment</code>.
     *
     * @param l a {@link Locale} object that represents a
     * particular geographical, political, or cultural region
     * @return an array of <code>String</code> objects containing names of
     * font families specific to the specified <code>Locale</code>

     * @see java.awt.Font
     * @see java.awt.Font#getFamily
     * @since 1.2
     */
    public abstract String[] getAvailableFontFamilyNames(Locale l);

    // /** 
     // * Returns the Point where Windows should be centered.
     // * It is recommended that centered Windows be checked to ensure they fit
     // * within the available display area using getMaximumWindowBounds().
     // * @return the point where Windows should be centered
     // *
     // * @exception HeadlessException if isHeadless() returns true
     // * @see #getMaximumWindowBounds
     // * @since 1.4
     // */
    // public Point getCenterPoint() throws HeadlessException { }
// 
    // /** 
     // * Returns the maximum bounds for centered Windows.
     // * These bounds account for objects in the native windowing system such as
     // * task bars and menu bars.  The returned bounds will reside on a single
     // * display with one exception: on multi-screen systems where Windows should
     // * be centered across all displays, this method returns the bounds of the
     // * entire display area.
     // * <p>
     // * To get the usable bounds of a single display, use 
     // * <code>GraphicsConfiguration.getBounds()</code> and
     // * <code>Toolkit.getScreenInsets()</code>.
     // * @return  the maximum bounds for centered Windows
     // *
     // * @exception HeadlessException if isHeadless() returns true
     // * @see #getCenterPoint
     // * @see GraphicsConfiguration#getBounds
     // * @see Toolkit#getScreenInsets
     // * @since 1.4
     // */
    // public Rectangle getMaximumWindowBounds() throws HeadlessException { }
}
