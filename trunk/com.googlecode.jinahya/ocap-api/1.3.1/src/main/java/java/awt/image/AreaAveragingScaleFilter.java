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
 * An ImageFilter class for scaling images using a simple area averaging
 * algorithm that produces smoother results than the nearest neighbor
 * algorithm.
 * <p>This class extends the basic ImageFilter Class to scale an existing
 * image and provide a source for a new image containing the resampled
 * image.  The pixels in the source image are blended to produce pixels
 * for an image of the specified size.  The blending process is analogous
 * to scaling up the source image to a multiple of the destination size
 * using pixel replication and then scaling it back down to the destination
 * size by simply averaging all the pixels in the supersized image that
 * fall within a given pixel of the destination image.  If the data from
 * the source is not delivered in TopDownLeftRight order then the filter
 * will back off to a simple pixel replication behavior and utilize the
 * requestTopDownLeftRightResend() method to refilter the pixels in a
 * better way at the end.
 * <p>It is meant to be used in conjunction with a FilteredImageSource
 * object to produce scaled versions of existing images.  Due to
 * implementation dependencies, there may be differences in pixel values 
 * of an image filtered on different platforms.
 *
 * @see FilteredImageSource
 * @see ReplicateScaleFilter
 * @see ImageFilter
 *
 * @version	1.14 01/23/03
 * @author 	Jim Graham
 */
public class AreaAveragingScaleFilter extends ReplicateScaleFilter
{

    /** 
     * Constructs an AreaAveragingScaleFilter that scales the pixels from
     * its source Image as specified by the width and height parameters.
     * @param width the target width to scale the image
     * @param height the target height to scale the image
     */
    public AreaAveragingScaleFilter(int width, int height) { super(0,0); }

    /** 
     * Detect if the data is being delivered with the necessary hints
     * to allow the averaging algorithm to do its work.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> whose 
     * pixels are being filtered.  Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere      
     * with the filtering operation.  
     * @see ImageConsumer#setHints
     */
    public void setHints(int hints) { }

    /** 
     * Combine the components for the delivered byte pixels into the
     * accumulation arrays and send on any averaged data for rows of
     * pixels that are complete.  If the correct hints were not
     * specified in the setHints call then relay the work to our
     * superclass which is capable of scaling pixels regardless of
     * the delivery hints.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> 
     * whose pixels are being filtered.  Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     * @see ReplicateScaleFilter
     */
    public void setPixels(int x, int y, int w, int h, java.awt.image.ColorModel
        model, byte[] pixels, int off, int scansize)
    { }

    /** 
     * Combine the components for the delivered int pixels into the
     * accumulation arrays and send on any averaged data for rows of
     * pixels that are complete.  If the correct hints were not
     * specified in the setHints call then relay the work to our
     * superclass which is capable of scaling pixels regardless of
     * the delivery hints.
     * <p>
     * Note: This method is intended to be called by the 
     * <code>ImageProducer</code> of the <code>Image</code> 
     * whose pixels are being filtered.  Developers using
     * this class to filter pixels from an image should avoid calling
     * this method directly since that operation could interfere
     * with the filtering operation.
     * @see ReplicateScaleFilter
     */
    public void setPixels(int x, int y, int w, int h, java.awt.image.ColorModel
        model, int[] pixels, int off, int scansize)
    { }
}
