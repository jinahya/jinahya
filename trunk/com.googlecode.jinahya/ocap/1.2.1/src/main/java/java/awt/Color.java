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

import java.io.*;

import java.awt.image.ColorModel;
// import java.awt.geom.AffineTransform;
// import java.awt.geom.Rectangle2D;
import java.awt.color.ColorSpace;

/** 
 * The <code>Color</code> class is used encapsulate colors in the default
 * sRGB color space or colors in arbitrary color spaces identified by a
 * {@link ColorSpace}.  Every color has an implicit alpha value of 1.0 or
 * an explicit one provided in the constructor.  The alpha value
 * defines the transparency of a color and can be represented by
 * a float value in the range 0.0&nbsp;-&nbsp;1.0 or 0&nbsp;-&nbsp;255.
 * An alpha value of 1.0 or 255 means that the color is completely
 * opaque and an alpha value of 0 or 0.0 means that the color is 
 * completely transparent.
 * When constructing a <code>Color</code> with an explicit alpha or
 * getting the color/alpha components of a <code>Color</code>, the color
 * components are never premultiplied by the alpha component.
 * <p>
 * The default color space for the Java 2D(tm) API is sRGB, a proposed
 * standard RGB color space.  For further information on sRGB,
 * see <A href="http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html">
 * http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html
 * </A>.
 * <p>
 * @version 	10 Feb 1997
 * @author 	Sami Shaio
 * @author 	Arthur van Hoff
 * @see		ColorSpace
 * @see         AlphaComposite
 */
public class Color implements Serializable, Transparency
// public class Color implements Paint, Serializable
{
    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color white.  In the default sRGB space.
     */
    public static final Color white = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color white.  In the default sRGB space.
     */
     public static final Color WHITE = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color light gray.  In the default sRGB space.
     */
    public static final Color lightGray = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color light gray.  In the default sRGB space.
     */
     public static final Color LIGHT_GRAY = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color gray.  In the default sRGB space.
     */
    public static final Color gray = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color gray.  In the default sRGB space.
     */
    public static final Color GRAY = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color dark gray.  In the default sRGB space.
     */
    public static final Color darkGray = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color dark gray.  In the default sRGB space.
     */
    public static final Color DARK_GRAY = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color black.  In the default sRGB space.
     */
    public static final Color black = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color black.  In the default sRGB space.
     */
    public static final Color BLACK = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color red.  In the default sRGB space.
     */
    public static final Color red = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color red.  In the default sRGB space.
     */
    public static final Color RED = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color pink.  In the default sRGB space.
     */
    public static final Color pink = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color pink.  In the default sRGB space.
     */
    public static final Color PINK = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color orange.  In the default sRGB space.
     */
    public static final Color orange = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color orange.  In the default sRGB space.
     */
     public static final Color ORANGE = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color yellow.  In the default sRGB space.
     */
    public static final Color yellow = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color yellow.  In the default sRGB space.
     */
     public static final Color YELLOW = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color green.  In the default sRGB space.
     */
    public static final Color green = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color green.  In the default sRGB space.
     */
     public static final Color GREEN = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color magenta.  In the default sRGB space.
     */
    public static final Color magenta = null;
 
    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color magenta.  In the default sRGB space.
     */
     public static final Color MAGENTA = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color cyan.  In the default sRGB space.
     */
    public static final Color cyan = null;

     /** 
      * <em>An object of type <code>Color</code> representing</em>
      * the color cyan.  In the default sRGB space.
      */
     public static final Color CYAN = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color blue.  In the default sRGB space.
     */
    public static final Color blue = null;

    /** 
     * <em>An object of type <code>Color</code> representing</em>
     * the color blue.  In the default sRGB space.
     */
     public static final Color BLUE = null;

    /** 
     * The color value.
     * @serial
     * @see #getRGB
     */
     int value;

    /** 
     * The color value in the default sRGB <code>ColorSpace</code> as
     * <code>float</code> components (no alpha).
     * If <code>null</code> after object construction, this must be an
     * sRGB color constructed with 8-bit precision, so compute from the
     * <code>int</code> color value. 
     * @serial
     * @see #getRGBColorComponents
     * @see #getRGBComponents
     */
    private float[] frgbvalue;

    /** 
     * The color value in the native <code>ColorSpace</code> as
     * <code>float</code> components (no alpha).
     * If <code>null</code> after object construction, this must be an
     * sRGB color constructed with 8-bit precision, so compute from the
     * <code>int</code> color value. 
     * @serial
     * @see #getRGBColorComponents
     * @see #getRGBComponents  
     */
    private float[] fvalue;

    /** 
     * The alpha value as a <code>float</code> component.
     * If <code>frgbvalue</code> is <code>null</code>, this is not valid
     * data, so compute from the <code>int</code> color value.
     * @serial
     * @see #getRGBComponents
     * @see #getComponents
     */
    private float falpha;

    /** 
     * The <code>ColorSpace</code>.  If <code>null</code>, then it's
     * default is sRGB.
     * @serial
     * @see #getColor
     * @see #getColorSpace
     * @see #getColorComponents
     */
    // PBP/PP 6203095: unintentionally added back to the spec
    // private ColorSpace cs;

    /*
     * JDK 1.1 serialVersionUID 
     */
     private static final long serialVersionUID = 118526816881161077L;

    /** 
     * Creates an opaque sRGB color with the specified red, green, 
     * and blue values in the range (0 - 255).  
     * The actual color used in rendering depends
     * on finding the best match given the color space 
     * available for a given output device.  
     * Alpha is defaulted to 255.
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public Color(int r, int g, int b) { }

    /** 
     * Creates an sRGB color with the specified red, green, blue, and alpha
     * values in the range (0 - 255).
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @param a the alpha component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlpha
     * @see #getRGB
     */
    public Color(int r, int g, int b, int a) { }

    /** 
     * Creates an opaque sRGB color with the specified combined RGB value
     * consisting of the red component in bits 16-23, the green component
     * in bits 8-15, and the blue component in bits 0-7.  The actual color
     * used in rendering depends on finding the best match given the
     * color space available for a particular output device.  Alpha is
     * defaulted to 255.
     * @param rgb the combined RGB components
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public Color(int rgb) { }

    /** 
     * Creates an sRGB color with the specified combined RGBA value consisting
     * of the alpha component in bits 24-31, the red component in bits 16-23,
     * the green component in bits 8-15, and the blue component in bits 0-7.
     * If the <code>hasalpha</code> argument is <code>false</code>, alpha
     * is defaulted to 255.
     * @param rgba the combined RGBA components
     * @param hasalpha <code>true</code> if the alpha bits are valid;
     * <code>false</code> otherwise
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlpha
     * @see #getRGB
     */
    public Color(int rgba, boolean hasalpha) { }

    /** 
     * Creates an opaque sRGB color with the specified red, green, and blue
     * values in the range (0.0 - 1.0).  Alpha is defaulted to 1.0.  The
     * actual color used in rendering depends on finding the best
     * match given the color space available for a particular output
     * device.
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getRGB
     */
    public Color(float r, float g, float b) { }

    /** 
     * Creates an sRGB color with the specified red, green, blue, and
     * alpha values in the range (0.0 - 1.0).  The actual color
     * used in rendering depends on finding the best match given the
     * color space available for a particular output device.
     * @param r the red component
     * @param g the green component
     * @param b the blue component
     * @param a the alpha component
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @see #getAlpha
     * @see #getRGB
     */
    public Color(float r, float g, float b, float a) { }

    // /** 
     // * Creates a color in the specified <code>ColorSpace</code>
     // * with the color components specified in the <code>float</code>
     // * array and the specified alpha.  The number of components is
     // * determined by the type of the <code>ColorSpace</code>.  For 
     // * example, RGB requires 3 components, but CMYK requires 4 
     // * components.
     // * @param cspace the <code>ColorSpace</code> to be used to
     // *			interpret the components
     // * @param components an arbitrary number of color components
     // *                      that is compatible with the 
     // * @param alpha alpha value
     // * @throws IllegalArgumentException if any of the values in the 
     // *         <code>components</code> array or <code>alpha</code> is 
     // *         outside of the range 0.0 to 1.0
     // * @see #getComponents
     // * @see #getColorComponents
     // */
    // public Color(ColorSpace cspace, float[] components, float alpha) { }

    /** 
     * Returns the red component in the range 0-255 in the default sRGB
     * space.
     * @return the red component.
     * @see #getRGB
     */
    public int getRed() { return 0; }

    /** 
     * Returns the green component in the range 0-255 in the default sRGB
     * space.
     * @return the green component.
     * @see #getRGB
     */
    public int getGreen() { return 0;  }

    /** 
     * Returns the blue component in the range 0-255 in the default sRGB
     * space.
     * @return the blue component.
     * @see #getRGB
     */
    public int getBlue() { return 0; }

    /** 
     * Returns the alpha component in the range 0-255.
     * @return the alpha component.
     * @see #getRGB
     */
    public int getAlpha() { return 0; }

    /** 
     * Returns the RGB value representing the color in the default sRGB
     * {@link ColorModel}.
     * (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 0-7 are
     * blue).
     * @return the RGB value of the color in the default sRGB
     *         <code>ColorModel</code>.
     * @see java.awt.image.ColorModel#getRGBdefault
     * @see #getRed
     * @see #getGreen
     * @see #getBlue
     * @since JDK1.0
     */
    public int getRGB() { return 0; }

    /** 
     * Creates a new <code>Color</code> that is a brighter version of this
     * <code>Color</code>.
     * <p>
     * This method applies an arbitrary scale factor to each of the three RGB 
     * components of this <code>Color</code> to create a brighter version
     * of this <code>Color</code>. Although <code>brighter</code> and
     * <code>darker</code> are inverse operations, the results of a
     * series of invocations of these two methods might be inconsistent
     * because of rounding errors. 
     * @return     a new <code>Color</code> object that is  
     *                 a brighter version of this <code>Color</code>.
     * @see        java.awt.Color#darker
     * @since      JDK1.0
     */
    public Color brighter() { return null;}

    /** 
     * Creates a new <code>Color</code> that is a darker version of this
     * <code>Color</code>.
     * <p>
     * This method applies an arbitrary scale factor to each of the three RGB 
     * components of this <code>Color</code> to create a darker version of
     * this <code>Color</code>.  Although <code>brighter</code> and
     * <code>darker</code> are inverse operations, the results of a series 
     * of invocations of these two methods might be inconsistent because
     * of rounding errors. 
     * @return  a new <code>Color</code> object that is 
     *                    a darker version of this <code>Color</code>.
     * @see        java.awt.Color#brighter
     * @since      JDK1.0
     */
    public Color darker() { return null;}

    /** 
     * Computes the hash code for this <code>Color</code>.
     * @return     a hash code value for this object.
     * @since      JDK1.0
     */
    public int hashCode() { return 0; }

    /** 
     * Determines whether another object is equal to this 
     * <code>Color</code>.
     * <p>
     * The result is <code>true</code> if and only if the argument is not 
     * <code>null</code> and is a <code>Color</code> object that has the same 
     * red, green, blue, and alpha values as this object. 
     * @param       obj   the object to test for equality with this
     *				<code>Color</code>
     * @return      <code>true</code> if the objects are the same; 
     *                             <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public boolean equals(Object obj) { return false;}

    /** 
     * Returns a string representation of this <code>Color</code>. This
     * method is intended to be used only for debugging purposes.  The 
     * content and format of the returned string might vary between 
     * implementations. The returned string might be empty but cannot 
     * be <code>null</code>.
     * 
     * @return  a string representation of this <code>Color</code>.
     */
    public String toString() { return null;}

    /** 
     * Converts a <code>String</code> to an integer and returns the 
     * specified opaque <code>Color</code>. This method handles string
     * formats that are used to represent octal and hexidecimal numbers.
     * @param      nm a <code>String</code> that represents 
     *                            an opaque color as a 24-bit integer
     * @return     the new <code>Color</code> object.
     * @see        java.lang.Integer#decode
     * @exception  NumberFormatException  if the specified string cannot
     *                      be interpreted as a decimal, 
     *                      octal, or hexidecimal integer.
     * @since      JDK1.1
     */
    public static Color decode(String nm) throws NumberFormatException { return null;}

    /** 
     * Finds a color in the system properties. 
     * <p>
     * The argument is treated as the name of a system property to 
     * be obtained. The string value of this property is then interpreted 
     * as an integer which is then converted to a <code>Color</code>
     * object. 
     * <p>
     * If the specified property is not found or could not be parsed as 
     * an integer then <code>null</code> is returned. 
     * @param    nm the name of the color property
     * @return   the <code>Color</code> converted from the system 
     * 		property.
     * @see      java.lang.System#getProperty(java.lang.String)
     * @see      java.lang.Integer#getInteger(java.lang.String)
     * @see      java.awt.Color#Color(int)
     * @since    JDK1.0
     */
    public static Color getColor(String nm) { return null;}

    /** 
     * Finds a color in the system properties. 
     * <p>
     * The first argument is treated as the name of a system property to 
     * be obtained. The string value of this property is then interpreted 
     * as an integer which is then converted to a <code>Color</code>
     * object. 
     * <p>
     * If the specified property is not found or cannot be parsed as 
     * an integer then the <code>Color</code> specified by the second
     * argument is returned instead. 
     * @param    nm the name of the color property
     * @param    v    the default <code>Color</code>
     * @return   the <code>Color</code> converted from the system
     *		property, or the specified <code>Color</code>.
     * @see      java.lang.System#getProperty(java.lang.String)
     * @see      java.lang.Integer#getInteger(java.lang.String)
     * @see      java.awt.Color#Color(int)
     * @since    JDK1.0
     */
    public static Color getColor(String nm, Color v) { return null;}

    /** 
     * Finds a color in the system properties. 
     * <p>
     * The first argument is treated as the name of a system property to 
     * be obtained. The string value of this property is then interpreted 
     * as an integer which is then converted to a <code>Color</code>
     * object. 
     * <p>
     * If the specified property is not found or could not be parsed as 
     * an integer then the integer value <code>v</code> is used instead, 
     * and is converted to a <code>Color</code> object.
     * @param    nm  the name of the color property
     * @param    v   the default color value, as an integer
     * @return   the <code>Color</code> converted from the system
     *		property or the <code>Color</code> converted from
     *		the specified integer.
     * @see      java.lang.System#getProperty(java.lang.String)
     * @see      java.lang.Integer#getInteger(java.lang.String)
     * @see      java.awt.Color#Color(int)
     * @since    JDK1.0
     */
    public static Color getColor(String nm, int v) { return null;}

    /** 
     * Converts the components of a color, as specified by the HSB 
     * model, to an equivalent set of values for the default RGB model. 
     * <p>
     * The <code>saturation</code> and <code>brightness</code> components
     * should be floating-point values between zero and one
     * (numbers in the range 0.0-1.0).  The <code>hue</code> component
     * can be any floating-point number.  The floor of this number is
     * subtracted from it to create a fraction between 0 and 1.  This
     * fractional number is then multiplied by 360 to produce the hue
     * angle in the HSB color model.
     * <p>
     * The integer that is returned by <code>HSBtoRGB</code> encodes the 
     * value of a color in bits 0-23 of an integer value that is the same 
     * format used by the method {@link #getRGB() <code>getRGB</code>}.
     * This integer can be supplied as an argument to the
     * <code>Color</code> constructor that takes a single integer argument. 
     * @param     hue   the hue component of the color
     * @param     saturation   the saturation of the color
     * @param     brightness   the brightness of the color
     * @return    the RGB value of the color with the indicated hue, 
     *                            saturation, and brightness.
     * @see       java.awt.Color#getRGB()
     * @see       java.awt.Color#Color(int)
     * @see       java.awt.image.ColorModel#getRGBdefault()
     * @since     JDK1.0
     */
    public static int HSBtoRGB(float hue, float saturation, float brightness)
    { return 0;}

    /** 
     * Converts the components of a color, as specified by the default RGB 
     * model, to an equivalent set of values for hue, saturation, and 
     * brightness that are the three components of the HSB model. 
     * <p>
     * If the <code>hsbvals</code> argument is <code>null</code>, then a 
     * new array is allocated to return the result. Otherwise, the method 
     * returns the array <code>hsbvals</code>, with the values put into 
     * that array. 
     * @param     r   the red component of the color
     * @param     g   the green component of the color
     * @param     b   the blue component of the color
     * @param     hsbvals  the array used to return the 
     *                     three HSB values, or <code>null</code>
     * @return    an array of three elements containing the hue, saturation, 
     *                     and brightness (in that order), of the color with 
     *                     the indicated red, green, and blue components.
     * @see       java.awt.Color#getRGB()
     * @see       java.awt.Color#Color(int)
     * @see       java.awt.image.ColorModel#getRGBdefault()
     * @since     JDK1.0
     */
    public static float[] RGBtoHSB(int r, int g, int b, float[] hsbvals) { return null;}

    /** 
     * Creates a <code>Color</code> object based on the specified values 
     * for the HSB color model. 
     * <p>
     * The <code>s</code> and <code>b</code> components should be
     * floating-point values between zero and one 
     * (numbers in the range 0.0-1.0).  The <code>h</code> component 
     * can be any floating-point number.  The floor of this number is 
     * subtracted from it to create a fraction between 0 and 1.  This 
     * fractional number is then multiplied by 360 to produce the hue
     * angle in the HSB color model.
     * @param  h   the hue component
     * @param  s   the saturation of the color
     * @param  b   the brightness of the color
     * @return  a <code>Color</code> object with the specified hue, 
     *                                 saturation, and brightness.
     * @since   JDK1.0
     */
    public static Color getHSBColor(float h, float s, float b) { return null;}

    /** 
     * Returns a <code>float</code> array containing the color and alpha
     * components of the <code>Color</code>, as represented in the default
     * sRGB color space.
     * If <code>compArray</code> is <code>null</code>, an array of length
     * 4 is created for the return value.  Otherwise, 
     * <code>compArray</code> must have length 4 or greater,
     * and it is filled in with the components and returned.
     * @param compArray an array that this method fills with 
     *			color and alpha components and returns
     * @return the RGBA components in a <code>float</code> array.
     */
    public float[] getRGBComponents(float[] compArray) { return null;}

    /** 
     * Returns a <code>float</code> array containing only the color
     * components of the <code>Color</code>, in the default sRGB color
     * space.  If <code>compArray</code> is <code>null</code>, an array of
     * length 3 is created for the return value.  Otherwise,
     * <code>compArray</code> must have length 3 or greater, and it is
     * filled in with the components and returned.
     * @param compArray an array that this method fills with color 
     *		components and returns
     * @return the RGB components in a <code>float</code> array.
     */
    public float[] getRGBColorComponents(float[] compArray) { return null;}

    /** 
     * Returns a <code>float</code> array containing the color and alpha
     * components of the <code>Color</code>, in the
     * <code>ColorSpace</code> of the <code>Color</code>.
     * If <code>compArray</code> is <code>null</code>, an array with
     * length equal to the number of components in the associated
     * <code>ColorSpace</code> plus one is created for
     * the return value.  Otherwise, <code>compArray</code> must have at
     * least this length and it is filled in with the components and
     * returned.
     * @param compArray an array that this method fills with the color and
     *		alpha components of this <code>Color</code> in its
     *		<code>ColorSpace</code> and returns
     * @return the color and alpha components in a <code>float</code> 
     * 		array.
     */
    public float[] getComponents(float[] compArray) {return null; }

    /** 
     * Returns a <code>float</code> array containing only the color
     * components of the <code>Color</code>, in the
     * <code>ColorSpace</code> of the <code>Color</code>.
     * If <code>compArray</code> is <code>null</code>, an array with
     * length equal to the number of components in the associated
     * <code>ColorSpace</code> is created for
     * the return value.  Otherwise, <code>compArray</code> must have at
     * least this length and it is filled in with the components and
     * returned.
     * @param compArray an array that this method fills with the color
     *		components of this <code>Color</code> in its
     *		<code>ColorSpace</code> and returns
     * @return the color components in a <code>float</code> array.
     */
    public float[] getColorComponents(float[] compArray) { return null;}

    // /** 
     // * Returns a <code>float</code> array containing the color and alpha
     // * components of the <code>Color</code>, in the 
     // * <code>ColorSpace</code> specified by the <code>cspace</code> 
     // * parameter.  If <code>compArray</code> is <code>null</code>, an
     // * array with length equal to the number of components in 
     // * <code>cspace</code> plus one is created for the return value.
     // * Otherwise, <code>compArray</code> must have at least this
     // * length, and it is filled in with the components and returned.
     // * @param cspace a specified <code>ColorSpace</code>
     // * @param compArray an array that this method fills with the
     // *		color and alpha components of this <code>Color</code> in
     // *		the specified <code>ColorSpace</code> and returns
     // * @return the color and alpha components in a <code>float</code> 
     // * 		array.
     // */
    // public float[] getComponents(ColorSpace cspace, float[] compArray) { }

    // /** 
     // * Returns a <code>float</code> array containing only the color
     // * components of the <code>Color</code> in the 
     // * <code>ColorSpace</code> specified by the <code>cspace</code> 
     // * parameter. If <code>compArray</code> is <code>null</code>, an array
     // * with length equal to the number of components in 
     // * <code>cspace</code> is created for the return value.  Otherwise,
     // * <code>compArray</code> must have at least this length, and it is
     // * filled in with the components and returned.
     // * @param cspace a specified <code>ColorSpace</code>
     // * @param compArray an array that this method fills with the color
     // *		components of this <code>Color</code> in the specified
     // * 		<code>ColorSpace</code>
     // * @return the color components in a <code>float</code> array.
     // */
    // public float[] getColorComponents(ColorSpace cspace, float[] compArray) { }

    /** 
     * Returns the <code>ColorSpace</code> of this <code>Color</code>.
     * @return this <code>Color</code> object's <code>ColorSpace</code>.
     */
    public ColorSpace getColorSpace() { return null;}

    // /** 
     // * Creates and returns a {@link PaintContext} used to generate a solid
     // * color pattern.  This enables a <code>Color</code> object to be used
     // * as an argument to any method requiring an object implementing the
     // * {@link Paint} interface.
     // * The same <code>PaintContext</code> is returned, regardless of
     // * whether or not <code>r</code>, <code>r2d</code>,
     // * <code>xform</code>, or <code>hints</code> are <code>null</code>.
     // * @param cm the specified <code>ColorModel</code>
     // * @param r the specified {@link Rectangle}
     // * @param r2d the specified {@link Rectangle2D}
     // * @param xform the specified {@link AffineTransform}
     // * @param hints the specified {@link RenderingHints}
     // * @return a <code>PaintContext</code> that is used to generate a
     // *		solid color pattern.
     // * @see Paint
     // * @see PaintContext
     // * @see Graphics2D#setPaint
     // */
    // public synchronized PaintContext createContext(ColorModel cm, Rectangle r,
        // Rectangle2D r2d, AffineTransform xform, RenderingHints hints)
    // { }

// PBP/PP

    /** 
     * Returns the transparency mode for this <code>Color</code>.  
     * @return this <code>Color</code> object's transparency mode.
     * 
     * @see Transparency
     */
    public int getTransparency() { return 0; }
}
