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

import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;

/** 
 * This class provides an easy way to create an ImageFilter which modifies
 * the pixels of an image in the default RGB ColorModel.  It is meant to
 * be used in conjunction with a FilteredImageSource object to produce
 * filtered versions of existing images.  It is an abstract class that
 * provides the calls needed to channel all of the pixel data through a
 * single method which converts pixels one at a time in the default RGB
 * ColorModel regardless of the ColorModel being used by the ImageProducer.
 * The only method which needs to be defined to create a useable image
 * filter is the filterRGB method.  Here is an example of a definition
 * of a filter which swaps the red and blue components of an image:
 * <pre>
 *
 *	class RedBlueSwapFilter extends RGBImageFilter {
 *	    public RedBlueSwapFilter() {
 *		// The filter's operation does not depend on the
 *		// pixel's location, so IndexColorModels can be
 *		// filtered directly.
 *		canFilterIndexColorModel = true;
 *	    }
 *
 *	    public int filterRGB(int x, int y, int rgb) {
 *		return ((rgb & 0xff00ff00)
 *			| ((rgb & 0xff0000) >> 16)
 *			| ((rgb & 0xff) << 16));
 *	    }
 *	}
 *
 * </pre>
 *
 * @see FilteredImageSource
 * @see ImageFilter
 * @see ColorModel#getRGBdefault
 *
 * @version	1.22 01/23/03
 * @author 	Jim Graham
 */
public abstract class RGBImageFilter extends ImageFilter
{
    /** 
     * The <code>ColorModel</code> to be replaced by
     * <code>newmodel</code> when the user calls 
     * {@link #substituteColorModel(ColorModel, ColorModel) substituteColorModel}.
     */
    protected java.awt.image.ColorModel origmodel;

    /** 
     * The <code>ColorModel</code> with which to
     * replace <code>origmodel</code> when the user calls 
     * <code>substituteColorModel</code>.
     */
    protected java.awt.image.ColorModel newmodel;

    /** 
     * This boolean indicates whether or not it is acceptable to apply
     * the color filtering of the filterRGB method to the color table
     * entries of an IndexColorModel object in lieu of pixel by pixel
     * filtering.  Subclasses should set this variable to true in their
     * constructor if their filterRGB method does not depend on the
     * coordinate of the pixel being filtered.
     * @see #substituteColorModel
     * @see #filterRGB
     * @see IndexColorModel
     */
    protected boolean canFilterIndexColorModel;

    public RGBImageFilter() { }

    /** 
     * If the ColorModel is an IndexColorModel, and the subclass has
     * set the canFilterIndexColorModel flag to true, we substitute
     * a filtered version of the color model here and wherever
     * that original ColorModel object appears in the setPixels methods. Otherwise
     * overrides the default ColorModel used by the ImageProducer and
     * specifies the default RGB ColorModel instead.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose pixels 
     * are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     * @see ImageConsumer
     * @see ColorModel#getRGBdefault
     */
    public void setColorModel(java.awt.image.ColorModel model) { }

    /** 
     * Registers two ColorModel objects for substitution.  If the oldcm
     * is encountered during any of the setPixels methods, the newcm
     * is substituted and the pixels passed through
     * untouched (but with the new ColorModel object).
     * @param oldcm the ColorModel object to be replaced on the fly
     * @param newcm the ColorModel object to replace oldcm on the fly
     */
    public void substituteColorModel(java.awt.image.ColorModel oldcm,
        java.awt.image.ColorModel newcm)
    { }

    /** 
     * Filters an IndexColorModel object by running each entry in its
     * color tables through the filterRGB function that RGBImageFilter
     * subclasses must provide.  Uses coordinates of -1 to indicate that
     * a color table entry is being filtered rather than an actual
     * pixel value.
     * @param icm the IndexColorModel object to be filtered
     * @return a new IndexColorModel representing the filtered colors
     */
    public IndexColorModel filterIndexColorModel(IndexColorModel icm) { return null; }

    /** 
     * Filters a buffer of pixels in the default RGB ColorModel by passing
     * them one by one through the filterRGB method.
     * @param x,&nbsp;y the coordinates of the upper-left corner of the 
     *        region of pixels
     * @param w the width of the region of pixels
     * @param h the height of the region of pixels
     * @param pixels the array of pixels
     * @param off the offset into the <code>pixels</code> array
     * @param scansize the distance from one row of pixels to the next
     *        in the array
     * @see ColorModel#getRGBdefault
     * @see #filterRGB
     */
    public void filterRGBPixels(int x, int y, int w, int h, int[] pixels, int
        off, int scansize)
    { }

    /** 
     * If the ColorModel object is the same one that has already
     * been converted, then simply passes the pixels through with the
     * converted ColorModel. Otherwise converts the buffer of byte
     * pixels to the default RGB ColorModel and passes the converted
     * buffer to the filterRGBPixels method to be converted one by one.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose pixels 
     * are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     * @see ColorModel#getRGBdefault
     * @see #filterRGBPixels
     */
    public void setPixels(int x, int y, int w, int h, java.awt.image.ColorModel
        model, byte[] pixels, int off, int scansize)
    { }

    /** 
     * If the ColorModel object is the same one that has already
     * been converted, then simply passes the pixels through with the
     * converted ColorModel, otherwise converts the buffer of integer
     * pixels to the default RGB ColorModel and passes the converted
     * buffer to the filterRGBPixels method to be converted one by one.
     * Converts a buffer of integer pixels to the default RGB ColorModel
     * and passes the converted buffer to the filterRGBPixels method.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose pixels 
     * are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     * @see ColorModel#getRGBdefault
     * @see #filterRGBPixels
     */
    public void setPixels(int x, int y, int w, int h, java.awt.image.ColorModel
        model, int[] pixels, int off, int scansize)
    { }

    /** 
     * Subclasses must specify a method to convert a single input pixel
     * in the default RGB ColorModel to a single output pixel.
     * @param x,&nbsp;y the coordinates of the pixel
     * @param rgb the integer pixel representation in the default RGB
     *            color model
     * @return a filtered pixel in the default RGB color model.
     * @see ColorModel#getRGBdefault
     * @see #filterRGBPixels
     */
    public abstract int filterRGB(int x, int y, int rgb);
}
