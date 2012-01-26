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
 * An ImageFilter class for scaling images using the simplest algorithm.
 * This class extends the basic ImageFilter Class to scale an existing
 * image and provide a source for a new image containing the resampled
 * image.  The pixels in the source image are sampled to produce pixels
 * for an image of the specified size by replicating rows and columns of
 * pixels to scale up or omitting rows and columns of pixels to scale
 * down.
 * <p>It is meant to be used in conjunction with a FilteredImageSource
 * object to produce scaled versions of existing images.  Due to
 * implementation dependencies, there may be differences in pixel values 
 * of an image filtered on different platforms.
 *
 * @see FilteredImageSource
 * @see ImageFilter
 *
 * @version	1.16 01/23/03
 * @author 	Jim Graham
 */
public class ReplicateScaleFilter extends ImageFilter
{
    /** 
     * The width of the source image.
     */
    protected int srcWidth;

    /** 
     * The height of the source image.
     */
    protected int srcHeight;

    /** 
     * The target width to scale the image.
     */
    protected int destWidth;

    /** 
     * The target height to scale the image.
     */
    protected int destHeight;

    /** 
     * An <code>int</code> array containing information about a 
     * row of pixels.
     */
    protected int[] srcrows;

    /** 
     * An <code>int</code> array containing information about a
     * column of pixels.
     */
    protected int[] srccols;

    /** 
     * A <code>byte</code> array initialized with a size of
     * {@link #destWidth} and used to deliver a row of pixel
     * data to the {@link ImageConsumer}.
     */
    protected Object outpixbuf;

    /** 
     * Constructs a ReplicateScaleFilter that scales the pixels from
     * its source Image as specified by the width and height parameters.
     * @param width the target width to scale the image
     * @param height the target height to scale the image
     * @throws IllegalArgumentException if <code>width</code> equals 
     *         zero or <code>height</code> equals zero
     */
    public ReplicateScaleFilter(int width, int height) { }

    /** 
     * Passes along the properties from the source object after adding a
     * property indicating the scale applied.
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
     * Override the dimensions of the source image and pass the dimensions
     * of the new scaled size to the ImageConsumer.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose pixels 
     * are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     * @see ImageConsumer
     */
    public void setDimensions(int w, int h) { }

    /** 
     * Choose which rows and columns of the delivered byte pixels are
     * needed for the destination scaled image and pass through just
     * those rows and columns that are needed, replicated as necessary.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose pixels 
     * are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     */
    public void setPixels(int x, int y, int w, int h, java.awt.image.ColorModel
        model, byte[] pixels, int off, int scansize)
    { }

    /** 
     * Choose which rows and columns of the delivered int pixels are
     * needed for the destination scaled image and pass through just
     * those rows and columns that are needed, replicated as necessary.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose pixels 
     * are being filtered. Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     */
    public void setPixels(int x, int y, int w, int h, java.awt.image.ColorModel
        model, int[] pixels, int off, int scansize)
    { }
}
