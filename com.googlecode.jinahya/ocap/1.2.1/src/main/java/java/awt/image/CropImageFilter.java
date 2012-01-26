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
import java.util.Hashtable;
import java.awt.Rectangle;

/** 
 * An ImageFilter class for cropping images.
 * This class extends the basic ImageFilter Class to extract a given
 * rectangular region of an existing Image and provide a source for a
 * new image containing just the extracted region.  It is meant to
 * be used in conjunction with a FilteredImageSource object to produce
 * cropped versions of existing images.
 *
 * @see FilteredImageSource
 * @see ImageFilter
 *
 * @version	1.13 01/23/03
 * @author 	Jim Graham
 */
public class CropImageFilter extends ImageFilter
{

    /** 
     * Constructs a CropImageFilter that extracts the absolute rectangular
     * region of pixels from its source Image as specified by the x, y,
     * w, and h parameters.
     * @param x the x location of the top of the rectangle to be extracted
     * @param y the y location of the top of the rectangle to be extracted
     * @param w the width of the rectangle to be extracted
     * @param h the height of the rectangle to be extracted
     */
    public CropImageFilter(int x, int y, int w, int h) { }

    /** 
     * Passes along  the properties from the source object after adding a
     * property indicating the cropped region.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose pixels 
     * are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     */
    public void setProperties(Hashtable props) { }

    /** 
     * Override the source image's dimensions and pass the dimensions
     * of the rectangular cropped region to the ImageConsumer.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose 
     * pixels are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     * @see ImageConsumer
     */
    public void setDimensions(int w, int h) { }

    /** 
     * Determine whether the delivered byte pixels intersect the region to
     * be extracted and passes through only that subset of pixels that
     * appear in the output region.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose 
     * pixels are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     */
    public void setPixels(int x, int y, int w, int h, java.awt.image.ColorModel
        model, byte[] pixels, int off, int scansize)
    { }

    /** 
     * Determine if the delivered int pixels intersect the region to
     * be extracted and pass through only that subset of pixels that
     * appear in the output region.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose 
     * pixels are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     */
    public void setPixels(int x, int y, int w, int h, java.awt.image.ColorModel
        model, int[] pixels, int off, int scansize)
    { }
}
