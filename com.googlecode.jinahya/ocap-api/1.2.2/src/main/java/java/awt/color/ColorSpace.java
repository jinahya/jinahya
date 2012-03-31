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


  


package java.awt.color;

// import sun.awt.color.CMM;

// PBP/PP
/** 
 * This abstract class is used to serve as a color space tag to identify the
 * specific color space of a Color object or, via a ColorModel object,
 * of an Image, a BufferedImage, or a GraphicsDevice.  
 * <p>

 * <p>
 * <em>Note: In this Profile, the ColorSpace class only supports the
 * sRGB color space.</em>
 * <p>
 * sRGB is a proposed standard RGB color space.  For more information,
 * see <A href="http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html">
 * http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html
 * </A>.

 * @version 10 Feb 1997
 */
public abstract class ColorSpace implements java.io.Serializable
{
    static final long serialVersionUID = -409452704308689724L;

    // /** 
     // * Any of the family of XYZ color spaces.
     // */
    // public static final int TYPE_XYZ = 0;
// 
    // /** 
     // * Any of the family of Lab color spaces.
     // */
    // public static final int TYPE_Lab = 0;
// 
    // /** 
     // * Any of the family of Luv color spaces.
     // */
    // public static final int TYPE_Luv = 0;
// 
    // /** 
     // * Any of the family of YCbCr color spaces.
     // */
    // public static final int TYPE_YCbCr = 0;
// 
    // /** 
     // * Any of the family of Yxy color spaces.
     // */
    // public static final int TYPE_Yxy = 0;
// 
     /** 
      * Any of the family of RGB color spaces.
      */
     public static final int TYPE_RGB = 5;
// 
    // /** 
     // * Any of the family of GRAY color spaces.
     // */
    // public static final int TYPE_GRAY = 0;
// 
    // /** 
     // * Any of the family of HSV color spaces.
     // */
    // public static final int TYPE_HSV = 0;
// 
    // /** 
     // * Any of the family of HLS color spaces.
     // */
    // public static final int TYPE_HLS = 0;
// 
    // /** 
     // * Any of the family of CMYK color spaces.
     // */
    // public static final int TYPE_CMYK = 0;
// 
    // /** 
     // * Any of the family of CMY color spaces.
     // */
    // public static final int TYPE_CMY = 0;
// 
    // /** 
     // * Generic 2 component color spaces.
     // */
    // public static final int TYPE_2CLR = 0;
// 
    // /** 
     // * Generic 3 component color spaces.
     // */
    // public static final int TYPE_3CLR = 0;
// 
    // /** 
     // * Generic 4 component color spaces.
     // */
    // public static final int TYPE_4CLR = 0;
// 
    // /** 
     // * Generic 5 component color spaces.
     // */
    // public static final int TYPE_5CLR = 0;
// 
    // /** 
     // * Generic 6 component color spaces.
     // */
    // public static final int TYPE_6CLR = 0;
// 
    // /** 
     // * Generic 7 component color spaces.
     // */
    // public static final int TYPE_7CLR = 0;
// 
    // /** 
     // * Generic 8 component color spaces.
     // */
    // public static final int TYPE_8CLR = 0;
// 
    // /** 
     // * Generic 9 component color spaces.
     // */
    // public static final int TYPE_9CLR = 0;
// 
    // /** 
     // * Generic 10 component color spaces.
     // */
    // public static final int TYPE_ACLR = 0;
// 
    // /** 
     // * Generic 11 component color spaces.
     // */
    // public static final int TYPE_BCLR = 0;
// 
    // /** 
     // * Generic 12 component color spaces.
     // */
    // public static final int TYPE_CCLR = 0;
// 
    // /** 
     // * Generic 13 component color spaces.
     // */
    // public static final int TYPE_DCLR = 0;
// 
    // /** 
     // * Generic 14 component color spaces.
     // */
    // public static final int TYPE_ECLR = 0;
// 
    // /** 
     // * Generic 15 component color spaces.
     // */
    // public static final int TYPE_FCLR = 0;
// 
     /** 
      * The sRGB color space defined at
      * <A href="http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html">
      * http://www.w3.org/pub/WWW/Graphics/Color/sRGB.html
      * </A>.
      */
     public static final int CS_sRGB = 1000;
// 
    // /** 
     // * A built-in linear RGB color space.  This space is based on the
     // * same RGB primaries as CS_sRGB, but has a linear tone reproduction curve.
     // */
    // public static final int CS_LINEAR_RGB = 0;
// 
    // /** 
     // * The CIEXYZ conversion color space defined above.
     // */
    // public static final int CS_CIEXYZ = 0;
// 
    // /** 
     // * The Photo YCC conversion color space.
     // */
    // public static final int CS_PYCC = 0;
// 
    // /** 
     // * The built-in linear gray scale color space.
     // */
    // public static final int CS_GRAY = 0;

    // PBP/PP 6205191
     private int type;

     private int numComponents;

    /** 
     * Constructs a ColorSpace object given a color space type
     * and the number of components.
     * @param type One of the <CODE>ColorSpace</CODE> type constants.
     * @param numcomponents The number of components in the color space.
     */
    protected ColorSpace(int type, int numcomponents) { }

// PBP/PP
// [6187199]

    /**
     * Returns a ColorSpace representing one of the specific
     * predefined color spaces.
     * @param colorspace a specific color space identified by one of
     *        the predefined class constants 
     *
     * @return The requested <CODE>ColorSpace</CODE> object. 
     */
    public static ColorSpace getInstance(int colorspace) { return null; }

    /** 
     * Returns true if the ColorSpace is CS_sRGB.
     * @return <CODE>true</CODE> if this is a <CODE>CS_sRGB</CODE> color 
     * space, <code>false</code> if it is not.
     */
    public boolean isCS_sRGB() {return false;  }

    // /** 
     // * Transforms a color value assumed to be in this ColorSpace
     // * into a value in the default CS_sRGB color space.
     // * <p>
     // * This method transforms color values using algorithms designed
     // * to produce the best perceptual match between input and output
     // * colors.  In order to do colorimetric conversion of color values,
     // * you should use the <code>toCIEXYZ</code>
     // * method of this color space to first convert from the input 
     // * color space to the CS_CIEXYZ color space, and then use the 
     // * <code>fromCIEXYZ</code> method of the CS_sRGB color space to 
     // * convert from CS_CIEXYZ to the output color space. 
     // * See {@link #toCIEXYZ(float[]) toCIEXYZ} and
     // * {@link #fromCIEXYZ(float[]) fromCIEXYZ} for further information.
     // * <p>
     // * @param colorvalue a float array with length of at least the number
     // *        of components in this ColorSpace
     // * @return a float array of length 3
     // * @throws ArrayIndexOutOfBoundsException if array length is not
     // * at least the number of components in this ColorSpace.
     // */
    // public abstract float[] toRGB(float[] colorvalue);

    // /** 
     // * Transforms a color value assumed to be in the default CS_sRGB
     // * color space into this ColorSpace.
     // * <p>
     // * This method transforms color values using algorithms designed
     // * to produce the best perceptual match between input and output
     // * colors.  In order to do colorimetric conversion of color values,
     // * you should use the <code>toCIEXYZ</code>
     // * method of the CS_sRGB color space to first convert from the input
     // * color space to the CS_CIEXYZ color space, and then use the
     // * <code>fromCIEXYZ</code> method of this color space to
     // * convert from CS_CIEXYZ to the output color space.
     // * See {@link #toCIEXYZ(float[]) toCIEXYZ} and
     // * {@link #fromCIEXYZ(float[]) fromCIEXYZ} for further information.
     // * <p>
     // * @param rgbvalue a float array with length of at least 3
     // * @return a float array with length equal to the number of
     // *         components in this ColorSpace
     // * @throws ArrayIndexOutOfBoundsException if array length is not
     // * at least 3.
     // */
    // public abstract float[] fromRGB(float[] rgbvalue);

    // /** 
     // * Transforms a color value assumed to be in this ColorSpace
     // * into the CS_CIEXYZ conversion color space.
     // * <p>
     // * This method transforms color values using relative colorimetry,
     // * as defined by the International Color Consortium standard.  This
     // * means that the XYZ values returned by this method are represented
     // * relative to the D50 white point of the CS_CIEXYZ color space.
     // * This representation is useful in a two-step color conversion
     // * process in which colors are transformed from an input color
     // * space to CS_CIEXYZ and then to an output color space.  This
     // * representation is not the same as the XYZ values that would
     // * be measured from the given color value by a colorimeter.
     // * A further transformation is necessary to compute the XYZ values
     // * that would be measured using current CIE recommended practices.
     // * See the {@link ICC_ColorSpace#toCIEXYZ(float[]) toCIEXYZ} method of
     // * <code>ICC_ColorSpace</code> for further information.
     // * <p>
     // * @param colorvalue a float array with length of at least the number
     // *        of components in this ColorSpace
     // * @return a float array of length 3
     // * @throws ArrayIndexOutOfBoundsException if array length is not
     // * at least the number of components in this ColorSpace.
     // */
    // public abstract float[] toCIEXYZ(float[] colorvalue);
// 
    // /** 
     // * Transforms a color value assumed to be in the CS_CIEXYZ conversion
     // * color space into this ColorSpace.
     // * <p>
     // * This method transforms color values using relative colorimetry,
     // * as defined by the International Color Consortium standard.  This
     // * means that the XYZ argument values taken by this method are represented
     // * relative to the D50 white point of the CS_CIEXYZ color space.
     // * This representation is useful in a two-step color conversion
     // * process in which colors are transformed from an input color
     // * space to CS_CIEXYZ and then to an output color space.  The color
     // * values returned by this method are not those that would produce
     // * the XYZ value passed to the method when measured by a colorimeter.
     // * If you have XYZ values corresponding to measurements made using
     // * current CIE recommended practices, they must be converted to D50
     // * relative values before being passed to this method.
     // * See the {@link ICC_ColorSpace#fromCIEXYZ(float[]) fromCIEXYZ} method of
     // * <code>ICC_ColorSpace</code> for further information.
     // * <p>
     // * @param colorvalue a float array with length of at least 3
     // * @return a float array with length equal to the number of
     // *         components in this ColorSpace
     // * @throws ArrayIndexOutOfBoundsException if array length is not
     // * at least 3.
     // */
    // public abstract float[] fromCIEXYZ(float[] colorvalue);

// PBP/PP
// [6187199]
    /** 
     * Returns the color space type of this ColorSpace.  The type defines the
     * number of components of the color space and the interpretation,
     * e.g. TYPE_RGB identifies a color space with three components - red,
     * green, and blue.  It does not define the particular color
     * characteristics of the space, e.g. the chromaticities of the
     * primaries.
     * @return The type constant that represents the type of this 
     *         <CODE>ColorSpace</CODE>.
     */
    public int getType() {return 0;  }

    /** 
     * Returns the number of components of this ColorSpace.
     * @return The number of components in this <CODE>ColorSpace</CODE>.
     */
    public int getNumComponents() {return 0;  }

    /** 
     * Returns the name of the component given the component index.
     * @param idx The component index.
     * @return The name of the component at the specified index.
     */
    public String getName(int idx) { return null; }

    // /** 
     // * Returns the minimum normalized color component value for the
     // * specified component.  The default implementation in this abstract
     // * class returns 0.0 for all components.  Subclasses should override
     // * this method if necessary.
     // * @param component The component index.
     // * @return The minimum normalized component value.
     // * @throws IllegalArgumentException if component is less than 0 or
     // *         greater than numComponents - 1.
     // * @since 1.4
     // */
    // public float getMinValue(int component) { }
// 
    // /** 
     // * Returns the maximum normalized color component value for the
     // * specified component.  The default implementation in this abstract
     // * class returns 1.0 for all components.  Subclasses should override
     // * this method if necessary.
     // * @param component The component index.
     // * @return The maximum normalized component value.
     // * @throws IllegalArgumentException if component is less than 0 or
     // *         greater than numComponents - 1.
     // * @since 1.4
     // */
    // public float getMaxValue(int component) { }
}
