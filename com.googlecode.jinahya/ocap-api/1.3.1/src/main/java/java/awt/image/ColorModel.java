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


  


package java.awt.image;

import java.awt.Transparency;
import java.awt.color.ColorSpace;
// import java.awt.color.ICC_ColorSpace;
// import sun.awt.color.ICC_Transform;
// import sun.awt.color.CMM;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

// PBP/PP
/** 
 * The <code>ColorModel</code> abstract class encapsulates the
 * methods for translating a pixel value to color components 
 * (for example, red, green, and blue) and an alpha component.
 * In order to render an image to the screen, a printer, or another
 * image, pixel values must be converted to color and alpha components.
 * As arguments to or return values from methods of this class,
 * pixels are represented as 32-bit ints or as arrays of primitive types.
 * The number, order, and interpretation of color components for a 
 * <code>ColorModel</code> is specified by its <code>ColorSpace</code>.  
 * A <code>ColorModel</code> used with pixel data that does not include
 * alpha information treats all pixels as opaque, which is an alpha
 * value of 1.0.
 * <p>

 * <p>
 * Methods in the <code>ColorModel</code> class use two different
 * representations of color and alpha components - a normalized form
 * and an unnormalized form.  In the normalized form, each component is a 
 * <code>float</code> value between
<!-- PBP/PP [6217618] -->
 * <em>0.0 and 1.0.</em>

 * Normalized color component values are not premultiplied.
 * All <code>ColorModels</code> must support the normalized form.
 * <p>
 * In the unnormalized
 * form, each component is an unsigned integral value between 0 and
 * 2<sup>n</sup> - 1, where n is the number of significant bits for a
 * particular component.  If pixel values for a particular 
 * <code>ColorModel</code> represent color samples premultiplied by
 * the alpha sample, unnormalized color component values are
 * also premultiplied.  The unnormalized form is used only with instances
 * of <code>ColorModel</code> whose <code>ColorSpace</code> has minimum
 * component values of 0.0 for all components and maximum values of
 * 1.0 for all components.
 * The unnormalized form for color and alpha components can be a convenient
 * representation for <code>ColorModels</code> whose normalized component
 * values all lie
 * between 0.0 and 1.0.  In such cases the integral value 0 maps to 0.0 and
 * the value 2<sup>n</sup> - 1 maps to 1.0.  In other cases, such as
 * when the normalized component values can be either negative or positive,
 * the unnormalized form is not convenient.  Such <code>ColorModel</code>
 * objects throw an {@link IllegalArgumentException} when methods involving
 * an unnormalized argument are called.  Subclasses of <code>ColorModel</code>
 * must specify the conditions under which this occurs.
 *
 * @see IndexColorModel

 * @see DirectColorModel
 * @see java.awt.Image
 * @see BufferedImage

 * @see java.awt.color.ColorSpace

 * @see DataBuffer
 * @version 10 Feb 1997
 */
public abstract class ColorModel implements Transparency
{
    /** 
     * The total number of bits in the pixel.
     */
    protected int pixel_bits;

    // /** 
     // * Data type of the array used to represent pixel values.
     // */
    // protected int transferType;

    /** 
     * Constructs a <code>ColorModel</code> that translates pixels of the
     * specified number of bits to color/alpha components.  The color
     * space is the default RGB <code>ColorSpace</code>, which is sRGB.
     * Pixel values are assumed to include alpha information.  If color
     * and alpha information are represented in the pixel value as
     * separate spatial bands, the color bands are assumed not to be
     * premultiplied with the alpha value. The transparency type is
     * java.awt.Transparency.TRANSLUCENT.  The transfer type will be the
     * smallest of DataBuffer.TYPE_BYTE, DataBuffer.TYPE_USHORT, 
     * or DataBuffer.TYPE_INT that can hold a single pixel 
     * (or DataBuffer.TYPE_UNDEFINED if bits is greater
     * than 32).  Since this constructor has no information about the
     * number of bits per color and alpha component, any subclass calling
     * this constructor should override any method that requires this
     * information.
     * @param bits the number of bits of a pixel
     * @throws IllegalArgumentException if the number
     * 		of bits in <code>bits</code> is less than 1
     */
    public ColorModel(int bits) { }

    /** 
     * Constructs a <code>ColorModel</code> that translates pixel values
     * to color/alpha components.  Color components will be in the
     * specified <code>ColorSpace</code>. <code>pixel_bits</code> is the
     * number of bits in the pixel values.  The bits array
     * specifies the number of significant bits per color and alpha component.
     * Its length should be the number of components in the 
     * <code>ColorSpace</code> if there is no alpha information in the
     * pixel values, or one more than this number if there is alpha
     * information.  <code>hasAlpha</code> indicates whether or not alpha
     * information is present.  The <code>boolean</code> 
     * <code>isAlphaPremultiplied</code> specifies how to interpret pixel
     * values in which color and alpha information are represented as
     * separate spatial bands.  If the <code>boolean</code>
     * is <code>true</code>, color samples are assumed to have been
     * multiplied by the alpha sample.  The <code>transparency</code> 
     * specifies what alpha values can be represented by this color model.
     * The transfer type is the type of primitive array used to represent
     * pixel values.  Note that the bits array contains the number of
     * significant bits per color/alpha component after the translation
     * from pixel values.  For example, for an
     * <code>IndexColorModel</code> with <code>pixel_bits</code> equal to
     * 16, the bits array might have four elements with each element set
     * to 8.
     * @param pixel_bits the number of bits in the pixel values
     * @param bits array that specifies the number of significant bits
     *		per color and alpha component
     * @param cspace the specified <code>ColorSpace</code>
     * @param hasAlpha <code>true</code> if alpha information is present;
     *		<code>false</code> otherwise
     * @param isAlphaPremultiplied <code>true</code> if color samples are
     *		assumed to be premultiplied by the alpha samples;
     *		<code>false</code> otherwise
     * @param transparency what alpha values can be represented by this
     *		color model
     * @param transferType the type of the array used to represent pixel
     *		values
     * @throws IllegalArgumentException if the length of
     *		the bit array is less than the number of color or alpha
     *		components in this <code>ColorModel</code>, or if the
     *          transparency is not a valid value.
     * @throws IllegalArgumentException if the sum of the number
     * 		of bits in <code>bits</code> is less than 1 or if
     *          any of the elements in <code>bits</code> is less than 0.
     * @see java.awt.Transparency
     */
    protected ColorModel(int pixel_bits, int[] bits, ColorSpace cspace, boolean
        hasAlpha, boolean isAlphaPremultiplied, int transparency, int
        transferType)
    { }

    /** 
     * Returns a <code>DirectColorModel</code> that describes the default
     * format for integer RGB values used in many of the methods in the
     * AWT image interfaces for the convenience of the programmer.
     * The color space is the default {@link ColorSpace}, sRGB.
     * The format for the RGB values is an integer with 8 bits
     * each of alpha, red, green, and blue color components ordered
     * correspondingly from the most significant byte to the least
     * significant byte, as in:  0xAARRGGBB.  Color components are
     * not premultiplied by the alpha component.  This format does not
     * necessarily represent the native or the most efficient
     * <code>ColorModel</code> for a particular device or for all images.
     * It is merely used as a common color model format.
     * @return a <code>DirectColorModel</code>object describing default
     *		RGB values.
     */
    public static ColorModel getRGBdefault() { return null;}

    /** 
     * Returns whether or not alpha is supported in this 
     * <code>ColorModel</code>.
     * @return <code>true</code> if alpha is supported in this
     * <code>ColorModel</code>; <code>false</code> otherwise.
     */
    public final boolean hasAlpha() { return false;}

    /** 
     * Returns whether or not the alpha has been premultiplied in the
     * pixel values to be translated by this <code>ColorModel</code>.  
     * If the boolean is <code>true</code>, this <code>ColorModel</code> 
     * is to be used to interpret pixel values in which color and alpha
     * information are represented as separate spatial bands, and color
     * samples are assumed to have been multiplied by the
     * alpha sample.
     * @return <code>true</code> if the alpha values are premultiplied
     *		in the pixel values to be translated by this
     *		<code>ColorModel</code>; <code>false</code> otherwise.
     */
    public final boolean isAlphaPremultiplied() {return false; }

    /** 
     * Returns the transfer type of this <code>ColorModel</code>.
     * The transfer type is the type of primitive array used to represent
     * pixel values as arrays.
     * @return the transfer type.
     */
    public final int getTransferType() { return 0;}

    /** 
     * Returns the number of bits per pixel described by this 
     * <code>ColorModel</code>.
     * @return the number of bits per pixel.
     */
    public int getPixelSize() { return 0; }

    /** 
     * Returns the number of bits for the specified color/alpha component.
     * Color components are indexed in the order specified by the 
     * <code>ColorSpace</code>.  Typically, this order reflects the name
     * of the color space type. For example, for TYPE_RGB, index 0
     * corresponds to red, index 1 to green, and index 2
     * to blue.  If this <code>ColorModel</code> supports alpha, the alpha
     * component corresponds to the index following the last color
     * component.
     * @param componentIdx the index of the color/alpha component
     * @return the number of bits for the color/alpha component at the
     *		specified index.
     * @throws ArrayIndexOutOfBoundsException if <code>componentIdx</code> 
     *         is greater than the number of components or
     *         less than zero
     * @throws NullPointerException if the number of bits array is 
     *         <code>null</code>
     */
    public int getComponentSize(int componentIdx) {return 0; }

    /** 
     * Returns an array of the number of bits per color/alpha component.  
     * The array contains the color components in the order specified by the
     * <code>ColorSpace</code>, followed by the alpha component, if
     * present.
     * @return an array of the number of bits per color/alpha component
     */
    public int[] getComponentSize() {return null; }

    /** 
     * Returns the transparency.  Returns either OPAQUE, BITMASK,
     * or TRANSLUCENT.
     * @return the transparency of this <code>ColorModel</code>.
     * @see Transparency#OPAQUE
     * @see Transparency#BITMASK
     * @see Transparency#TRANSLUCENT
     */
    public int getTransparency() { return 0;}

    /** 
     * Returns the number of components, including alpha, in this
     * <code>ColorModel</code>.  This is equal to the number of color
     * components, optionally plus one, if there is an alpha component.
     * @return the number of components in this <code>ColorModel</code>
     */
    public int getNumComponents() { return 0;}

    /** 
     * Returns the number of color components in this
     * <code>ColorModel</code>.
     * This is the number of components returned by
     * {@link ColorSpace#getNumComponents}.
     * @return the number of color components in this
     * <code>ColorModel</code>.
     * @see ColorSpace#getNumComponents
     */
    public int getNumColorComponents() { return 0; }

    /** 
     * Returns the red color component for the specified pixel, scaled
     * from 0 to 255 in the default RGB ColorSpace, sRGB.  A color conversion
     * is done if necessary.  The pixel value is specified as an int.
     * An <code>IllegalArgumentException</code> is thrown if pixel
     * values for this <code>ColorModel</code> are not conveniently
     * representable as a single int.  The returned value is not a
     * pre-multiplied value.  For example, if the
     * alpha is premultiplied, this method divides it out before returning
     * the value.  If the alpha value is 0, the red value is 0.
     * @param pixel a specified pixel
     * @return the value of the red component of the specified pixel.
     */
    public abstract int getRed(int pixel);

    /** 
     * Returns the green color component for the specified pixel, scaled
     * from 0 to 255 in the default RGB ColorSpace, sRGB.  A color conversion
     * is done if necessary.  The pixel value is specified as an int.
     * An <code>IllegalArgumentException</code> is thrown if pixel
     * values for this <code>ColorModel</code> are not conveniently
     * representable as a single int.  The returned value is a non
     * pre-multiplied value.  For example, if the alpha is premultiplied,
     * this method divides it out before returning
     * the value.  If the alpha value is 0, the green value is 0.
     * @param pixel the specified pixel
     * @return the value of the green component of the specified pixel.
     */
    public abstract int getGreen(int pixel);

    /** 
     * Returns the blue color component for the specified pixel, scaled
     * from 0 to 255 in the default RGB ColorSpace, sRGB.  A color conversion
     * is done if necessary.  The pixel value is specified as an int.
     * An <code>IllegalArgumentException</code> is thrown if pixel values
     * for this <code>ColorModel</code> are not conveniently representable
     * as a single int.  The returned value is a non pre-multiplied
     * value, for example, if the alpha is premultiplied, this method
     * divides it out before returning the value.  If the alpha value is
     * 0, the blue value is 0.
     * @param pixel the specified pixel
     * @return the value of the blue component of the specified pixel.
     */
    public abstract int getBlue(int pixel);

    /** 
     * Returns the alpha component for the specified pixel, scaled
     * from 0 to 255.  The pixel value is specified as an int.
     * An <code>IllegalArgumentException</code> is thrown if pixel
     * values for this <code>ColorModel</code> are not conveniently
     * representable as a single int.
     * @param pixel the specified pixel
     * @return the value of alpha component of the specified pixel. 
     */
    public abstract int getAlpha(int pixel);

    /** 
     * Returns the color/alpha components of the pixel in the default
     * RGB color model format.  A color conversion is done if necessary.
     * The pixel value is specified as an int.
     * An <code>IllegalArgumentException</code> thrown if pixel values
     * for this <code>ColorModel</code> are not conveniently representable
     * as a single int.  The returned value is in a non
     * pre-multiplied format. For example, if the alpha is premultiplied,
     * this method divides it out of the color components.  If the alpha
     * value is 0, the color values are 0.
     * @param pixel the specified pixel
     * @return the RGB value of the color/alpha components of the 
     *		specified pixel.
     * @see ColorModel#getRGBdefault
     */
    public int getRGB(int pixel) { return 0;}

    /** 
     * Returns the red color component for the specified pixel, scaled
     * from 0 to 255 in the default RGB <code>ColorSpace</code>, sRGB.  A
     * color conversion is done if necessary.  The pixel value is
     * specified by an array of data elements of type transferType passed
     * in as an object reference.  The returned value is a non
     * pre-multiplied value.  For example, if alpha is premultiplied,
     * this method divides it out before returning
     * the value.  If the alpha value is 0, the red value is 0.
     * If <code>inData</code> is not a primitive array of type
     * transferType, a <code>ClassCastException</code> is thrown.  An
     * <code>ArrayIndexOutOfBoundsException</code> is thrown if 
     * <code>inData</code> is not large enough to hold a pixel value for
     * this <code>ColorModel</code>.
     * If this <code>transferType</code> is not supported, a       
     * <code>UnsupportedOperationException</code> will be
     * thrown.  Since
     * <code>ColorModel</code> is an abstract class, any instance
     * must be an instance of a subclass.  Subclasses inherit the
     * implementation of this method and if they don't override it, this
     * method throws an exception if the subclass uses a
     * <code>transferType</code> other than
     * <code>DataBuffer.TYPE_BYTE</code>,
     * <code>DataBuffer.TYPE_USHORT</code>, or 
     * <code>DataBuffer.TYPE_INT</code>. 
     * @param inData an array of pixel values
     * @return the value of the red component of the specified pixel.
     * @throws ClassCastException if <code>inData</code>
     * 	is not a primitive array of type <code>transferType</code>
     * @throws ArrayIndexOutOfBoundsException if
     *	<code>inData</code> is not large enough to hold a pixel value
     *	for this <code>ColorModel</code>
     * @throws UnsupportedOperationException if this
     *	<code>tranferType</code> is not supported by this
     *	<code>ColorModel</code>
     */
  // public int getRed(Object inData) { return 0;}

    /** 
     * Returns the green color component for the specified pixel, scaled
     * from 0 to 255 in the default RGB <code>ColorSpace</code>, sRGB.  A
     * color conversion is done if necessary.  The pixel value is
     * specified by an array of data elements of type transferType passed
     * in as an object reference.  The returned value will be a non
     * pre-multiplied value.  For example, if the alpha is premultiplied,
     * this method divides it out before returning the value.  If the
     * alpha value is 0, the green value is 0.  If <code>inData</code> is
     * not a primitive array of type transferType, a
     * <code>ClassCastException</code> is thrown.  An
     * <code>ArrayIndexOutOfBoundsException</code> is thrown if 
     * <code>inData</code> is not large enough to hold a pixel value for
     * this <code>ColorModel</code>.
     * If this <code>transferType</code> is not supported, a
     * <code>UnsupportedOperationException</code> will be
     * thrown.  Since
     * <code>ColorModel</code> is an abstract class, any instance
     * must be an instance of a subclass.  Subclasses inherit the
     * implementation of this method and if they don't override it, this
     * method throws an exception if the subclass uses a
     * <code>transferType</code> other than 
     * <code>DataBuffer.TYPE_BYTE</code>, 
     * <code>DataBuffer.TYPE_USHORT</code>, or  
     * <code>DataBuffer.TYPE_INT</code>.
     * @param inData an array of pixel values
     * @return the value of the green component of the specified pixel.
     * @throws <code>ClassCastException</code> if <code>inData</code>
     *  is not a primitive array of type <code>transferType</code>
     * @throws <code>ArrayIndexOutOfBoundsException</code> if
     *  <code>inData</code> is not large enough to hold a pixel value
     *  for this <code>ColorModel</code>
     * @throws <code>UnsupportedOperationException</code> if this
     *  <code>tranferType</code> is not supported by this 
     *  <code>ColorModel</code> 
     */
  // public int getGreen(Object inData) { return 0; }

    /** 
     * Returns the blue color component for the specified pixel, scaled
     * from 0 to 255 in the default RGB <code>ColorSpace</code>, sRGB.  A
     * color conversion is done if necessary.  The pixel value is
     * specified by an array of data elements of type transferType passed
     * in as an object reference.  The returned value is a non
     * pre-multiplied value.  For example, if the alpha is premultiplied,
     * this method divides it out before returning the value.  If the
     * alpha value is 0, the blue value will be 0.  If 
     * <code>inData</code> is not a primitive array of type transferType,
     * a <code>ClassCastException</code> is thrown.  An
     * <code>ArrayIndexOutOfBoundsException</code> is
     * thrown if <code>inData</code> is not large enough to hold a pixel
     * value for this <code>ColorModel</code>.
     * If this <code>transferType</code> is not supported, a
     * <code>UnsupportedOperationException</code> will be 
     * thrown.  Since
     * <code>ColorModel</code> is an abstract class, any instance
     * must be an instance of a subclass.  Subclasses inherit the
     * implementation of this method and if they don't override it, this
     * method throws an exception if the subclass uses a
     * <code>transferType</code> other than 
     * <code>DataBuffer.TYPE_BYTE</code>, 
     * <code>DataBuffer.TYPE_USHORT</code>, or  
     * <code>DataBuffer.TYPE_INT</code>.
     * @param inData an array of pixel values
     * @return the value of the blue component of the specified pixel.
     * @throws ClassCastException if <code>inData</code>
     *  is not a primitive array of type <code>transferType</code>
     * @throws ArrayIndexOutOfBoundsException if
     *  <code>inData</code> is not large enough to hold a pixel value
     *  for this <code>ColorModel</code>
     * @throws UnsupportedOperationException if this
     *  <code>tranferType</code> is not supported by this 
     *  <code>ColorModel</code> 
     */
  // public int getBlue(Object inData) { return 0; }

    /** 
     * Returns the alpha component for the specified pixel, scaled
     * from 0 to 255.  The pixel value is specified by an array of data
     * elements of type transferType passed in as an object reference.
     * If inData is not a primitive array of type transferType, a
     * <code>ClassCastException</code> is thrown.  An
     * <code>ArrayIndexOutOfBoundsException</code> is thrown if 
     * <code>inData</code> is not large enough to hold a pixel value for
     * this <code>ColorModel</code>.
     * If this <code>transferType</code> is not supported, a
     * <code>UnsupportedOperationException</code> will be 
     * thrown.  Since
     * <code>ColorModel</code> is an abstract class, any instance
     * must be an instance of a subclass.  Subclasses inherit the
     * implementation of this method and if they don't override it, this
     * method throws an exception if the subclass uses a
     * <code>transferType</code> other than 
     * <code>DataBuffer.TYPE_BYTE</code>, 
     * <code>DataBuffer.TYPE_USHORT</code>, or  
     * <code>DataBuffer.TYPE_INT</code>.
     * @param inData the specified pixel
     * @return the alpha component of the specified pixel, scaled from
     * 0 to 255.
     * @throws ClassCastException if <code>inData</code> 
     *  is not a primitive array of type <code>transferType</code>
     * @throws ArrayIndexOutOfBoundsException if
     *  <code>inData</code> is not large enough to hold a pixel value   
     *  for this <code>ColorModel</code>
     * @throws UnsupportedOperationException if this
     *  <code>tranferType</code> is not supported by this
     *  <code>ColorModel</code>
     */
  // public int getAlpha(Object inData) { return 0; }

    /** 
     * Returns the color/alpha components for the specified pixel in the
     * default RGB color model format.  A color conversion is done if
     * necessary.  The pixel value is specified by an array of data
     * elements of type transferType passed in as an object reference.
     * If inData is not a primitive array of type transferType, a
     * <code>ClassCastException</code> is thrown.  An
     * <code>ArrayIndexOutOfBoundsException</code> is
     * thrown if <code>inData</code> is not large enough to hold a pixel
     * value for this <code>ColorModel</code>.
     * The returned value will be in a non pre-multiplied format, i.e. if
     * the alpha is premultiplied, this method will divide it out of the
     * color components (if the alpha value is 0, the color values will be 0).
     * @param inData the specified pixel
     * @return the color and alpha components of the specified pixel.
     * @see ColorModel#getRGBdefault
     */
  // public int getRGB(Object inData) { return 0; }

    // /** 
     // * Returns a data element array representation of a pixel in this
     // * <code>ColorModel</code>, given an integer pixel representation in
     // * the default RGB color model.
     // * This array can then be passed to the 
     // * {@link WritableRaster#setDataElements} method of
     // * a {@link WritableRaster} object.  If the pixel variable is 
     // * <code>null</code>, a new array will be allocated.  If 
     // * <code>pixel</code> is not
     // * <code>null</code>, it must be a primitive array of type
     // * <code>transferType</code>; otherwise, a
     // * <code>ClassCastException</code> is thrown.  An
     // * <code>ArrayIndexOutOfBoundsException</code> is thrown if 
     // * <code>pixel</code> is
     // * not large enough to hold a pixel value for this
     // * <code>ColorModel</code>. The pixel array is returned.
     // * If this <code>transferType</code> is not supported, a
     // * <code>UnsupportedOperationException</code> will be 
     // * thrown.  Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param rgb the integer pixel representation in the default RGB
     // * color model
     // * @param pixel the specified pixel
     // * @return an array representation of the specified pixel in this
     // *	<code>ColorModel</code>.
     // * @throws ClassCastException if <code>pixel</code>
     // *  is not a primitive array of type <code>transferType</code>
     // * @throws ArrayIndexOutOfBoundsException if
     // *  <code>pixel</code> is not large enough to hold a pixel value
     // *  for this <code>ColorModel</code>
     // * @throws UnsupportedOperationException if this
     // *  method is not supported by this <code>ColorModel</code> 
     // * @see WritableRaster#setDataElements
     // * @see SampleModel#setDataElements
     // */
    // public Object getDataElements(int rgb, Object pixel) { }

    // /** 
     // * Returns an array of unnormalized color/alpha components given a pixel 
     // * in this <code>ColorModel</code>.  The pixel value is specified as
     // * an <code>int</code>.  An <code>IllegalArgumentException</code>
     // * will be thrown if pixel values for this <code>ColorModel</code> are
     // * not conveniently representable as a single <code>int</code> or if
     // * color component values for this <code>ColorModel</code> are not
     // * conveniently representable in the unnormalized form.
     // * For example, this method can be used to retrieve the
     // * components for a specific pixel value in a 
     // * <code>DirectColorModel</code>.  If the components array is 
     // * <code>null</code>, a new array will be allocated.  The
     // * components array will be returned.  Color/alpha components are
     // * stored in the components array starting at <code>offset</code> 
     // * (even if the array is allocated by this method).  An
     // * <code>ArrayIndexOutOfBoundsException</code> is thrown if  the
     // * components array is not <code>null</code> and is not large
     // * enough to hold all the color and alpha components (starting at offset).
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must   
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param pixel the specified pixel
     // * @param components the array to receive the color and alpha
     // * components of the specified pixel
     // * @param offset the offset into the <code>components</code> array at
     // * which to start storing the color and alpha components
     // * @return an array containing the color and alpha components of the
     // * specified pixel starting at the specified offset.
     // * @throws UnsupportedOperationException if this
     // *		method is not supported by this <code>ColorModel</code>
     // */
    // public int[] getComponents(int pixel, int[] components, int offset) { }

    // /** 
     // * Returns an array of unnormalized color/alpha components given a pixel
     // * in this <code>ColorModel</code>.  The pixel value is specified by
     // * an array of data elements of type transferType passed in as an
     // * object reference.  If <code>pixel</code> is not a primitive array
     // * of type transferType, a <code>ClassCastException</code> is thrown.
     // * An <code>IllegalArgumentException</code> will be thrown if color
     // * component values for this <code>ColorModel</code> are not
     // * conveniently representable in the unnormalized form.
     // * An <code>ArrayIndexOutOfBoundsException</code> is
     // * thrown if <code>pixel</code> is not large enough to hold a pixel
     // * value for this <code>ColorModel</code>.
     // * This method can be used to retrieve the components for a specific
     // * pixel value in any <code>ColorModel</code>.  If the components
     // * array is <code>null</code>, a new array will be allocated.  The
     // * components array will be returned.  Color/alpha components are
     // * stored in the <code>components</code> array starting at 
     // * <code>offset</code> (even if the array is allocated by this
     // * method).  An <code>ArrayIndexOutOfBoundsException</code>
     // * is thrown if  the components array is not <code>null</code> and is
     // * not large enough to hold all the color and alpha components
     // * (starting at <code>offset</code>).
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must   
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param pixel the specified pixel
     // * @param components an array that receives the color and alpha
     // * components of the specified pixel
     // * @param offset the index into the <code>components</code> array at
     // * which to begin storing the color and alpha components of the 
     // * specified pixel
     // * @return an array containing the color and alpha components of the
     // * specified pixel starting at the specified offset.
     // * @throws UnsupportedOperationException if this
     // *		method is not supported by this <code>ColorModel</code>
     // */
    // public int[] getComponents(Object pixel, int[] components, int offset) { }

    // /** 
     // * Returns an array of all of the color/alpha components in unnormalized
     // * form, given a normalized component array.  Unnormalized components
     // * are unsigned integral values between 0 and 2<sup>n</sup> - 1, where
     // * n is the number of bits for a particular component.  Normalized
     // * components are float values between a per component minimum and
     // * maximum specified by the <code>ColorSpace</code> object for this
     // * <code>ColorModel</code>.  An <code>IllegalArgumentException</code>
     // * will be thrown if color component values for this
     // * <code>ColorModel</code> are not conveniently representable in the
     // * unnormalized form.  If the 
     // * <code>components</code> array is <code>null</code>, a new array
     // * will be allocated.  The <code>components</code> array will
     // * be returned.  Color/alpha components are stored in the 
     // * <code>components</code> array starting at <code>offset</code> (even
     // * if the array is allocated by this method). An
     // * <code>ArrayIndexOutOfBoundsException</code> is thrown if the
     // * <code>components</code> array is not <code>null</code> and is not
     // * large enough to hold all the color and alpha
     // * components (starting at <code>offset</code>).  An
     // * <code>IllegalArgumentException</code> is thrown if the
     // * <code>normComponents</code> array is not large enough to hold
     // * all the color and alpha components starting at
     // * <code>normOffset</code>.
     // * @param normComponents an array containing normalized components
     // * @param normOffset the offset into the <code>normComponents</code> 
     // * array at which to start retrieving normalized components
     // * @param components an array that receives the components from
     // * <code>normComponents</code>
     // * @param offset the index into <code>components</code> at which to
     // * begin storing normalized components from 
     // * <code>normComponents</code>
     // * @return an array containing unnormalized color and alpha 
     // * components.
     // * @throws IllegalArgumentException If the component values for this
     // * <CODE>ColorModel</CODE> are not conveniently representable in the
     // * unnormalized form.
     // * @throws IllegalArgumentException if the length of 
     // *          <code>normComponents</code> minus <code>normOffset</code>
     // *          is less than <code>numComponents</code>
     // * @throws UnsupportedOperationException if the
     // *          constructor of this <code>ColorModel</code> called the
     // *          <code>super(bits)</code> constructor, but did not 
     // *          override this method.  See the constructor, 
     // *          {@link #ColorModel(int)}.
     // */
    // public int[] getUnnormalizedComponents(float[] normComponents, int
        // normOffset, int[] components, int offset)
    // { }

    // /** 
     // * Returns an array of all of the color/alpha components in normalized
     // * form, given an unnormalized component array.  Unnormalized components
     // * are unsigned integral values between 0 and 2<sup>n</sup> - 1, where
     // * n is the number of bits for a particular component.  Normalized
     // * components are float values between a per component minimum and
     // * maximum specified by the <code>ColorSpace</code> object for this
     // * <code>ColorModel</code>.  An <code>IllegalArgumentException</code>
     // * will be thrown if color component values for this
     // * <code>ColorModel</code> are not conveniently representable in the
     // * unnormalized form.  If the
     // * <code>normComponents</code> array is <code>null</code>, a new array
     // * will be allocated.  The <code>normComponents</code> array
     // * will be returned.  Color/alpha components are stored in the
     // * <code>normComponents</code> array starting at
     // * <code>normOffset</code> (even if the array is allocated by this
     // * method).  An <code>ArrayIndexOutOfBoundsException</code> is thrown
     // * if the <code>normComponents</code> array is not <code>null</code> 
     // * and is not large enough to hold all the color and alpha components
     // * (starting at <code>normOffset</code>).  An
     // * <code>IllegalArgumentException</code> is thrown if the 
     // * <code>components</code> array is not large enough to hold all the
     // * color and alpha components starting at <code>offset</code>.
     // * <p>
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  The default implementation
     // * of this method in this abstract class assumes that component values
     // * for this class are conveniently representable in the unnormalized
     // * form.  Therefore, subclasses which may
     // * have instances which do not support the unnormalized form must
     // * override this method.
     // * @param components an array containing unnormalized components
     // * @param offset the offset into the <code>components</code> array at
     // * which to start retrieving unnormalized components
     // * @param normComponents an array that receives the normalized components
     // * @param normOffset the index into <code>normComponents</code> at
     // * which to begin storing normalized components
     // * @return an array containing normalized color and alpha 
     // * components. 
     // * @throws IllegalArgumentException If the component values for this
     // * <CODE>ColorModel</CODE> are not conveniently representable in the
     // * unnormalized form.
     // * @throws UnsupportedOperationException if the
     // *          constructor of this <code>ColorModel</code> called the
     // *          <code>super(bits)</code> constructor, but did not
     // *          override this method.  See the constructor,
     // *          {@link #ColorModel(int)}.
     // * @throws UnsupportedOperationException if this method is unable
     // *          to determine the number of bits per component
     // */
    // public float[] getNormalizedComponents(int[] components, int offset, float[]
        // normComponents, int normOffset)
    // { }

    // /** 
     // * Returns a pixel value represented as an <code>int</code> in this
     // * <code>ColorModel</code>, given an array of unnormalized color/alpha
     // * components.  This method will throw an 
     // * <code>IllegalArgumentException</code> if component values for this
     // * <code>ColorModel</code> are not conveniently representable as a
     // * single <code>int</code> or if color component values for this
     // * <code>ColorModel</code> are not conveniently representable in the
     // * unnormalized form.  An 
     // * <code>ArrayIndexOutOfBoundsException</code> is thrown if  the
     // * <code>components</code> array is not large enough to hold all the
     // * color and alpha components (starting at <code>offset</code>).
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param components an array of unnormalized color and alpha
     // * components
     // * @param offset the index into <code>components</code> at which to
     // * begin retrieving the color and alpha components
     // * @return an <code>int</code> pixel value in this
     // * <code>ColorModel</code> corresponding to the specified components.  
     // * @throws IllegalArgumentException if
     // * 	pixel values for this <code>ColorModel</code> are not 
     // * 	conveniently representable as a single <code>int</code>
     // * @throws IllegalArgumentException if
     // * 	component values for this <code>ColorModel</code> are not 
     // * 	conveniently representable in the unnormalized form
     // * @throws ArrayIndexOutOfBoundsException if
     // *  the <code>components</code> array is not large enough to 
     // *	hold all of the color and alpha components starting at
     // *	<code>offset</code> 
     // * @throws UnsupportedOperationException if this
     // * 	method is not supported by this <code>ColorModel</code>
     // */
    // public int getDataElement(int[] components, int offset) { }

    // /** 
     // * Returns a data element array representation of a pixel in this
     // * <code>ColorModel</code>, given an array of unnormalized color/alpha
     // * components.  This array can then be passed to the
     // * <code>setDataElements</code> method of a <code>WritableRaster</code> 
     // * object.  This method will throw an <code>IllegalArgumentException</code>
     // * if color component values for this <code>ColorModel</code> are not
     // * conveniently representable in the unnormalized form.
     // * An <code>ArrayIndexOutOfBoundsException</code> is thrown
     // * if the <code>components</code> array is not large enough to hold
     // * all the color and alpha components (starting at
     // * <code>offset</code>).  If the <code>obj</code> variable is
     // * <code>null</code>, a new array will be allocated.  If
     // * <code>obj</code> is not <code>null</code>, it must be a primitive
     // * array of type transferType; otherwise, a 
     // * <code>ClassCastException</code> is thrown.  An
     // * <code>ArrayIndexOutOfBoundsException</code> is thrown if 
     // * <code>obj</code> is not large enough to hold a pixel value for this
     // * <code>ColorModel</code>.
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param components an array of unnormalized color and alpha
     // * components
     // * @param offset the index into <code>components</code> at which to
     // * begin retrieving color and alpha components
     // * @param obj the <code>Object</code> representing an array of color
     // * and alpha components
     // * @return an <code>Object</code> representing an array of color and
     // * alpha components.
     // * @throws ClassCastException if <code>obj</code>
     // *  is not a primitive array of type <code>transferType</code>
     // * @throws ArrayIndexOutOfBoundsException if
     // *  <code>obj</code> is not large enough to hold a pixel value
     // *  for this <code>ColorModel</code> or the <code>components</code>
     // *	array is not large enough to hold all of the color and alpha
     // *	components starting at <code>offset</code> 
     // * @throws IllegalArgumentException if
     // * 	component values for this <code>ColorModel</code> are not 
     // * 	conveniently representable in the unnormalized form
     // * @throws UnsupportedOperationException if this 
     // *  method is not supported by this <code>ColorModel</code> 
     // * @see WritableRaster#setDataElements
     // * @see SampleModel#setDataElements
     // */
    // public Object getDataElements(int[] components, int offset, Object obj) { }

    // /** 
     // * Returns a pixel value represented as an <code>int</code> in this
     // * <code>ColorModel</code>, given an array of normalized color/alpha
     // * components.  This method will throw an 
     // * <code>IllegalArgumentException</code> if pixel values for this
     // * <code>ColorModel</code> are not conveniently representable as a
     // * single <code>int</code>.  An 
     // * <code>ArrayIndexOutOfBoundsException</code> is thrown if  the
     // * <code>normComponents</code> array is not large enough to hold all the
     // * color and alpha components (starting at <code>normOffset</code>).
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  The default implementation
     // * of this method in this abstract class first converts from the
     // * normalized form to the unnormalized form and then calls
     // * <code>getDataElement(int[], int)</code>.  Subclasses which may
     // * have instances which do not support the unnormalized form must
     // * override this method.
     // * @param normComponents an array of normalized color and alpha
     // * components
     // * @param normOffset the index into <code>normComponents</code> at which to
     // * begin retrieving the color and alpha components
     // * @return an <code>int</code> pixel value in this
     // * <code>ColorModel</code> corresponding to the specified components.  
     // * @throws IllegalArgumentException if
     // * 	pixel values for this <code>ColorModel</code> are not 
     // * 	conveniently representable as a single <code>int</code>
     // * @throws ArrayIndexOutOfBoundsException if
     // *  the <code>normComponents</code> array is not large enough to 
     // *	hold all of the color and alpha components starting at
     // *	<code>normOffset</code> 
     // * @since 1.4
     // */
    // public int getDataElement(float[] normComponents, int normOffset) { }

    // /** 
     // * Returns a data element array representation of a pixel in this
     // * <code>ColorModel</code>, given an array of normalized color/alpha
     // * components.  This array can then be passed to the
     // * <code>setDataElements</code> method of a <code>WritableRaster</code> 
     // * object.  An <code>ArrayIndexOutOfBoundsException</code> is thrown
     // * if the <code>normComponents</code> array is not large enough to hold
     // * all the color and alpha components (starting at
     // * <code>normOffset</code>).  If the <code>obj</code> variable is
     // * <code>null</code>, a new array will be allocated.  If
     // * <code>obj</code> is not <code>null</code>, it must be a primitive
     // * array of type transferType; otherwise, a 
     // * <code>ClassCastException</code> is thrown.  An
     // * <code>ArrayIndexOutOfBoundsException</code> is thrown if 
     // * <code>obj</code> is not large enough to hold a pixel value for this
     // * <code>ColorModel</code>.
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  The default implementation
     // * of this method in this abstract class first converts from the
     // * normalized form to the unnormalized form and then calls
     // * <code>getDataElement(int[], int, Object)</code>.  Subclasses which may
     // * have instances which do not support the unnormalized form must
     // * override this method.
     // * @param normComponents an array of normalized color and alpha
     // * components
     // * @param normOffset the index into <code>normComponents</code> at which to
     // * begin retrieving color and alpha components
     // * @param obj a primitive data array to hold the returned pixel
     // * @return an <code>Object</code> which is a primitive data array
     // * representation of a pixel
     // * @throws ClassCastException if <code>obj</code>
     // *  is not a primitive array of type <code>transferType</code>
     // * @throws ArrayIndexOutOfBoundsException if
     // *  <code>obj</code> is not large enough to hold a pixel value
     // *  for this <code>ColorModel</code> or the <code>normComponents</code>
     // *	array is not large enough to hold all of the color and alpha
     // *	components starting at <code>normOffset</code> 
     // * @see WritableRaster#setDataElements
     // * @see SampleModel#setDataElements
     // * @since 1.4
     // */
    // public Object getDataElements(float[] normComponents, int normOffset, Object
        // obj)
    // { }

    // /** 
     // * Returns an array of all of the color/alpha components in normalized
     // * form, given a pixel in this <code>ColorModel</code>.  The pixel
     // * value is specified by an array of data elements of type transferType
     // * passed in as an object reference.  If pixel is not a primitive array
     // * of type transferType, a <code>ClassCastException</code> is thrown.
     // * An <code>ArrayIndexOutOfBoundsException</code> is thrown if
     // * <code>pixel</code> is not large enough to hold a pixel value for this
     // * <code>ColorModel</code>.
     // * Normalized components are float values between a per component minimum
     // * and maximum specified by the <code>ColorSpace</code> object for this
     // * <code>ColorModel</code>.  If the
     // * <code>normComponents</code> array is <code>null</code>, a new array
     // * will be allocated.  The <code>normComponents</code> array
     // * will be returned.  Color/alpha components are stored in the
     // * <code>normComponents</code> array starting at
     // * <code>normOffset</code> (even if the array is allocated by this
     // * method).  An <code>ArrayIndexOutOfBoundsException</code> is thrown
     // * if the <code>normComponents</code> array is not <code>null</code> 
     // * and is not large enough to hold all the color and alpha components
     // * (starting at <code>normOffset</code>).
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  The default implementation
     // * of this method in this abstract class first retrieves color and alpha
     // * components in the unnormalized form using
     // * <code>getComponents(Object, int[], int)</code> and then calls
     // * <code>getNormalizedComponents(int[], int, float[], int)</code>.
     // * Subclasses which may
     // * have instances which do not support the unnormalized form must
     // * override this method.
     // * @param pixel the specified pixel
     // * @param normComponents an array to receive the normalized components
     // * @param normOffset the offset into the <code>normComponents</code>
     // * array at which to start storing normalized components
     // * @return an array containing normalized color and alpha 
     // * components. 
     // * @throws ClassCastException if <code>pixel</code> is not a primitive
     // *          array of type transferType
     // * @throws ArrayIndexOutOfBoundsException if
     // *          <code>normComponents</code> is not large enough to hold all
     // *          color and alpha components starting at <code>normOffset</code>
     // * @throws ArrayIndexOutOfBoundsException if
     // *          <code>pixel</code> is not large enough to hold a pixel
     // *          value for this <code>ColorModel</code>.
     // * @throws UnsupportedOperationException if the
     // *          constructor of this <code>ColorModel</code> called the
     // *          <code>super(bits)</code> constructor, but did not
     // *          override this method.  See the constructor,
     // *          {@link #ColorModel(int)}.
     // * @throws UnsupportedOperationException if this method is unable
     // *          to determine the number of bits per component
     // * @since 1.4
     // */
    // public float[] getNormalizedComponents(Object pixel, float[] normComponents,
        // int normOffset)
    // { }

    /** 
     * Tests if the specified <code>Object</code> is an instance of
     * <code>ColorModel</code> and if it equals this
     * <code>ColorModel</code>.
     * @param obj the <code>Object</code> to test for equality
     * @return <code>true</code> if the specified <code>Object</code>
     * is an instance of <code>ColorModel</code> and equals this
     * <code>ColorModel</code>; <code>false</code> otherwise.
     */
    public boolean equals(Object obj) { return false; }

    /** 
     * Returns the hash code for this ColorModel.
     *
     * @return    a hash code for this ColorModel.
     */
    public int hashCode() {return 0; }

    /** 
     * Returns the <code>ColorSpace</code> associated with this 
     * <code>ColorModel</code>.
     * @return the <code>ColorSpace</code> of this
     * <code>ColorModel</code>.
     */
    public final ColorSpace getColorSpace() { return null; }

    // /** 
     // * Forces the raster data to match the state specified in the
     // * <code>isAlphaPremultiplied</code> variable, assuming the data is
     // * currently correctly described by this <code>ColorModel</code>.  It
     // * may multiply or divide the color raster data by alpha, or do
     // * nothing if the data is in the correct state.  If the data needs to
     // * be coerced, this method will also return an instance of this
     // * <code>ColorModel</code> with the <code>isAlphaPremultiplied</code> 
     // * flag set appropriately.  This method will throw a
     // * <code>UnsupportedOperationException</code> if it is not supported
     // * by this <code>ColorModel</code>.
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param raster the <code>WritableRaster</code> data
     // * @param isAlphaPremultiplied <code>true</code> if the alpha is
     // * premultiplied; <code>false</code> otherwise
     // * @return a <code>ColorModel</code> object that represents the
     // * coerced data.
     // */
    // public ColorModel coerceData(WritableRaster raster, boolean
        // isAlphaPremultiplied)
    // { }

    // /** 
     // * Returns <code>true</code> if <code>raster</code> is compatible
     // * with this <code>ColorModel</code> and <code>false</code> if it is
     // * not.
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param raster the {@link Raster} object to test for compatibility
     // * @return <code>true</code> if <code>raster</code> is compatible
     // * with this <code>ColorModel</code>.
     // * @throws UnsupportedOperationException if this
     // *		method has not been implemented for this 
     // *		<code>ColorModel</code>
     // */
    // public boolean isCompatibleRaster(Raster raster) { }

    // /** 
     // * Creates a <code>WritableRaster</code> with the specified width and
     // * height that has a data layout (<code>SampleModel</code>) compatible
     // * with this <code>ColorModel</code>.
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param w the width to apply to the new <code>WritableRaster</code>
     // * @param h the height to apply to the new <code>WritableRaster</code>
     // * @return a <code>WritableRaster</code> object with the specified
     // * width and height.  
     // * @throws UnsupportedOperationException if this
     // * 		method is not supported by this <code>ColorModel</code>
     // * @see WritableRaster
     // * @see SampleModel
     // */
    // public WritableRaster createCompatibleWritableRaster(int w, int h) { }

    // /** 
     // * Creates a <code>SampleModel</code> with the specified width and
     // * height that has a data layout compatible with this 
     // * <code>ColorModel</code>.
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param w the width to apply to the new <code>SampleModel</code>
     // * @param h the height to apply to the new <code>SampleModel</code>
     // * @return a <code>SampleModel</code> object with the specified
     // * width and height.
     // * @throws UnsupportedOperationException if this
     // * 		method is not supported by this <code>ColorModel</code>  
     // * @see SampleModel
     // */
    // public SampleModel createCompatibleSampleModel(int w, int h) { }

    // /** 
     // *Checks if the <code>SampleModel</code> is compatible with this
     // * <code>ColorModel</code>.
     // * Since <code>ColorModel</code> is an abstract class,
     // * any instance is an instance of a subclass.  Subclasses must
     // * override this method since the implementation in this abstract
     // * class throws an <code>UnsupportedOperationException</code>.
     // * @param sm the specified <code>SampleModel</code>
     // * @return <code>true</code> if the specified <code>SampleModel</code>
     // * is compatible with this <code>ColorModel</code>; <code>false</code>
     // * otherwise.
     // * @throws UnsupportedOperationException if this
     // * 		method is not supported by this <code>ColorModel</code>
     // * @see SampleModel 
     // */
    // public boolean isCompatibleSampleModel(SampleModel sm) { }

    /** 
     * Disposes of system resources associated with this 
     * <code>ColorModel</code> once this <code>ColorModel</code> is no
     * longer referenced.
     */
    public void finalize() { }

    // /** 
     // * Returns a <code>Raster</code> representing the alpha channel of an
     // * image, extracted from the input <code>Raster</code>, provided that
     // * pixel values of this <code>ColorModel</code> represent color and
     // * alpha information as separate spatial bands (e.g.
     // * {@link ComponentColorModel} and <code>DirectColorModel</code>).
     // * This method assumes that <code>Raster</code> objects associated
     // * with such a <code>ColorModel</code> store the alpha band, if
     // * present, as the last band of image data.  Returns <code>null</code> 
     // * if there is no separate spatial alpha channel associated with this
     // * <code>ColorModel</code>.  If this is an
     // * <code>IndexColorModel</code> which has alpha in the lookup table,
     // * this method will return <code>null</code> since
     // * there is no spatially discrete alpha channel.
     // * This method will create a new <code>Raster</code> (but will share
     // * the data array).
     // * Since <code>ColorModel</code> is an abstract class, any instance 
     // * is an instance of a subclass.  Subclasses must override this 
     // * method to get any behavior other than returning <code>null</code>
     // * because the implementation in this abstract class returns
     // * <code>null</code>.
     // * @param raster the specified <code>Raster</code>
     // * @return a <code>Raster</code> representing the alpha channel of
     // * an image, obtained from the specified <code>Raster</code>.
     // */
    // public WritableRaster getAlphaRaster(WritableRaster raster) { }

    /** 
     * Returns the <code>String</code> representation of the contents of
     * this <code>ColorModel</code>object.
     * @return a <code>String</code> representing the contents of this
     * <code>ColorModel</code> object.
     */
    public String toString() { return null; }
}
