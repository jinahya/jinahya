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

// import java.awt.geom.AffineTransform;
// import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;

// PBP/PP
// [6237710]
/** 
 * A class to encapsulate symbolic colors representing the color of
 * native GUI objects on a system.  For systems which support the dynamic
 * update of the system colors (when the user changes the colors)
 * the actual RGB values of these symbolic colors will also change
 * dynamically.  In order to compare the "current" RGB value of a
 * <code>SystemColor</code> object with a non-symbolic Color object,
 * <code>getRGB</code> should be used rather than <code>equals</code>.
 * <p>
 * 
 * <em>Note: Because this profile may be used with tookits that lack
 * AWT UI concepts, implementations are free to assign arbitrary
 * values to the system colors.</em>
 * <p>
 * 
 *
 * @version 	1.23, 01/23/03
 * @author 	Carl Quinn
 * @author      Amy Fowler
 */
public final class SystemColor extends Color implements java.io.Serializable
{
    /** 
     * The array index for the
     * <a href="#desktop"><code>desktop</code></a> system color.
     * @see SystemColor#desktop
     */
    public static final int DESKTOP = 0;

    /** 
     * The array index for the
     * <a href="#activeCaption"><code>activeCaption</code></a> system color.
     * @see SystemColor#activeCaption
     */
    public static final int ACTIVE_CAPTION = 1;

    /** 
     * The array index for the
     * <a href="#activeCaptionText"><code>activeCaptionText</code></a> system color.
     * @see SystemColor#activeCaptionText
     */
    public static final int ACTIVE_CAPTION_TEXT = 2;

    /** 
     * The array index for the
     * <a href="#activeCaptionBorder"><code>activeCaptionBorder</code></a> system color.
     * @see SystemColor#activeCaptionBorder
     */
    public static final int ACTIVE_CAPTION_BORDER = 3;

    /** 
     * The array index for the
     * <a href="#inactiveCaption"><code>inactiveCaption</code></a> system color.
     * @see SystemColor#inactiveCaption
     */
    public static final int INACTIVE_CAPTION = 4;

    /** 
     * The array index for the
     * <a href="#inactiveCaptionText"><code>inactiveCaptionText</code></a> system color.
     * @see SystemColor#inactiveCaptionText
     */
    public static final int INACTIVE_CAPTION_TEXT = 5;

    /** 
     * The array index for the
     * <a href="#inactiveCaptionBorder"><code>inactiveCaptionBorder</code></a> system color.
     * @see SystemColor#inactiveCaptionBorder
     */
    public static final int INACTIVE_CAPTION_BORDER = 6;

    /** 
     * The array index for the
     * <a href="#window"><code>window</code></a> system color.
     * @see SystemColor#window
     */
    public static final int WINDOW = 7;

    /** 
     * The array index for the
     * <a href="#windowBorder"><code>windowBorder</code></a> system color.
     * @see SystemColor#windowBorder
     */
    public static final int WINDOW_BORDER = 8;

    /** 
     * The array index for the
     * <a href="#windowText"><code>windowText</code></a> system color.
     * @see SystemColor#windowText
     */
    public static final int WINDOW_TEXT = 9;

    /** 
     * The array index for the
     * <a href="#menu"><code>menu</code></a> system color.
     * @see SystemColor#menu
     */
    public static final int MENU = 10;

    /** 
     * The array index for the
     * <a href="#menuText"><code>menuText</code></a> system color.
     * @see SystemColor#menuText
     */
    public static final int MENU_TEXT = 11;

    /** 
     * The array index for the
     * <a href="#text"><code>text</code></a> system color.
     * @see SystemColor#text
     */
    public static final int TEXT = 12;

    /** 
     * The array index for the
     * <a href="#textText"><code>textText</code></a> system color.
     * @see SystemColor#textText
     */
    public static final int TEXT_TEXT = 13;

    /** 
     * The array index for the
     * <a href="#textHighlight"><code>textHighlight</code></a> system color.
     * @see SystemColor#textHighlight
     */
    public static final int TEXT_HIGHLIGHT = 14;

    /** 
     * The array index for the
     * <a href="#textHighlightText"><code>textHighlightText</code></a> system color.
     * @see SystemColor#textHighlightText
     */
    public static final int TEXT_HIGHLIGHT_TEXT = 15;

    /** 
     * The array index for the
     * <a href="#textInactiveText"><code>textInactiveText</code></a> system color.
     * @see SystemColor#textInactiveText
     */
    public static final int TEXT_INACTIVE_TEXT = 16;

    /** 
     * The array index for the
     * <a href="#control"><code>control</code></a> system color.
     * @see SystemColor#control
     */
    public static final int CONTROL = 17;

    /** 
     * The array index for the
     * <a href="#controlText"><code>controlText</code></a> system color.
     * @see SystemColor#controlText
     */
    public static final int CONTROL_TEXT = 18;

    /** 
     * The array index for the
     * <a href="#controlHighlight"><code>controlHighlight</code></a> system color.
     * @see SystemColor#controlHighlight
     */
    public static final int CONTROL_HIGHLIGHT = 19;

    /** 
     * The array index for the
     * <a href="#controlLtHighlight"><code>controlLtHighlight</code></a> system color.
     * @see SystemColor#controlLtHighlight
     */
    public static final int CONTROL_LT_HIGHLIGHT = 20;

    /** 
     * The array index for the
     * <a href="#controlShadow"><code>controlShadow</code></a> system color.
     * @see SystemColor#controlShadow
     */
    public static final int CONTROL_SHADOW = 21;

    /** 
     * The array index for the
     * <a href="#controlDkShadow"><code>controlDkShadow</code></a> system color.
     * @see SystemColor#controlDkShadow
     */
    public static final int CONTROL_DK_SHADOW = 22;

    /** 
     * The array index for the
     * <a href="#scrollbar"><code>scrollbar</code></a> system color.
     * @see SystemColor#scrollbar
     */
    public static final int SCROLLBAR = 23;

    /** 
     * The array index for the
     * <a href="#info"><code>info</code></a> system color.
     * @see SystemColor#info
     */
    public static final int INFO = 24;

    /** 
     * The array index for the
     * <a href="#infoText"><code>infoText</code></a> system color.
     * @see SystemColor#infoText
     */
    public static final int INFO_TEXT = 25;

    /** 
     * The number of system colors in the array.
     */
    public static final int NUM_COLORS = 26;

    /** 
     * The color rendered for the background of the desktop.
     */
    public static final SystemColor desktop = null;

    /** 
     * The color rendered for the window-title background of the currently active window.
     */
    public static final SystemColor activeCaption = null;

    /** 
     * The color rendered for the window-title text of the currently active window.
     */
    public static final SystemColor activeCaptionText = null;

    /** 
     * The color rendered for the border around the currently active window.
     */
    public static final SystemColor activeCaptionBorder = null;

    /** 
     * The color rendered for the window-title background of inactive windows.
     */
    public static final SystemColor inactiveCaption = null;

    /** 
     * The color rendered for the window-title text of inactive windows.
     */
    public static final SystemColor inactiveCaptionText = null;

    /** 
     * The color rendered for the border around inactive windows.
     */
    public static final SystemColor inactiveCaptionBorder = null;

    /** 
     * The color rendered for the background of interior regions inside windows.
     */
    public static final SystemColor window = null;

    /** 
     * The color rendered for the border around interior regions inside windows.
     */
    public static final SystemColor windowBorder = null;

    /** 
     * The color rendered for text of interior regions inside windows.
     */
    public static final SystemColor windowText = null;

    /** 
     * The color rendered for the background of menus.
     */
    public static final SystemColor menu = null;

    /** 
     * The color rendered for the text of menus.
     */
    public static final SystemColor menuText = null;

    /** 
     * The color rendered for the background of text control objects, such as
     * textfields and comboboxes.
     */
    public static final SystemColor text = null;

    /** 
     * The color rendered for the text of text control objects, such as textfields
     * and comboboxes.
     */
    public static final SystemColor textText = null;

    /** 
     * The color rendered for the background of selected items, such as in menus,
     * comboboxes, and text.
     */
    public static final SystemColor textHighlight = null;

    /** 
     * The color rendered for the text of selected items, such as in menus, comboboxes,
     * and text.
     */
    public static final SystemColor textHighlightText = null;

    /** 
     * The color rendered for the text of inactive items, such as in menus.
     */
    public static final SystemColor textInactiveText = null;

    /** 
     * The color rendered for the background of control panels and control objects,
     * such as pushbuttons.
     */
    public static final SystemColor control = null;

    /** 
     * The color rendered for the text of control panels and control objects,
     * such as pushbuttons.
     */
    public static final SystemColor controlText = null;

    /** 
     * The color rendered for light areas of 3D control objects, such as pushbuttons.
     * This color is typically derived from the <code>control</code> background color
     * to provide a 3D effect.
     */
    public static final SystemColor controlHighlight = null;

    /** 
     * The color rendered for highlight areas of 3D control objects, such as pushbuttons.
     * This color is typically derived from the <code>control</code> background color
     * to provide a 3D effect.
     */
    public static final SystemColor controlLtHighlight = null;

    /** 
     * The color rendered for shadow areas of 3D control objects, such as pushbuttons.
     * This color is typically derived from the <code>control</code> background color
     * to provide a 3D effect.
     */
    public static final SystemColor controlShadow = null;

    /** 
     * The color rendered for dark shadow areas on 3D control objects, such as pushbuttons.
     * This color is typically derived from the <code>control</code> background color
     * to provide a 3D effect.
     */
    public static final SystemColor controlDkShadow = null;

    /** 
     * The color rendered for the background of scrollbars.
     */
    public static final SystemColor scrollbar = null;

    /** 
     * The color rendered for the background of tooltips or spot help.
     */
    public static final SystemColor info = null;

    /** 
     * The color rendered for the text of tooltips or spot help.
     */
    public static final SystemColor infoText = null;

    /*
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 4503142729533789064L;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private SystemColor() { super(0); }

    /** 
     * Gets the "current" RGB value representing the symbolic color.
     * (Bits 24-31 are 0xff, 16-23 are red, 8-15 are green, 0-7 are blue).
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see java.awt.Color#getBlue()
     * @see java.awt.Color#getGreen()
     * @see java.awt.Color#getRed()
     */
    public int getRGB() { return 0; }

    // /** 
     // * Creates and returns a <code>PaintContext</code> used to generate
     // * a solid color pattern.  This enables a Color object to be used
     // * as an argument to any method requiring an object implementing
     // * the Paint interface.
     // * @see Paint
     // * @see PaintContext
     // * @see Graphics2D#setPaint
     // */
    // public PaintContext createContext(ColorModel cm, Rectangle r, Rectangle2D
        // r2d, AffineTransform xform, RenderingHints hints)
    // { }

    /** 
     * Returns a string representation of this <code>Color</code>'s values.
     * This method is intended to be used only for debugging purposes,
     * and the content and format of the returned string may vary between
     * implementations.
     * The returned string may be empty but may not be <code>null</code>.
     *
     * @return  a string representation of this <code>Color</code>
     */
    public String toString() { return null; }
}
