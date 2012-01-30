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

import java.awt.Image;
import java.awt.image.ImageFilter;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;
import java.util.Hashtable;
import java.awt.image.ColorModel;

/** 
 * This class is an implementation of the ImageProducer interface which
 * takes an existing image and a filter object and uses them to produce
 * image data for a new filtered version of the original image.
 * Here is an example which filters an image by swapping the red and
 * blue compents:
 * <pre>
 * 
 *	Image src = getImage("doc:///demo/images/duke/T1.gif");
 *	ImageFilter colorfilter = new RedBlueSwapFilter();
 *	Image img = createImage(new FilteredImageSource(src.getSource(),
 *							colorfilter));
 * 
 * </pre>
 *
 * @see ImageProducer
 *
 * @version	1.25 01/23/03
 * @author 	Jim Graham
 */
public class FilteredImageSource implements java.awt.image.ImageProducer
{

    /** 
     * Constructs an ImageProducer object from an existing ImageProducer
     * and a filter object.
     * @param orig the specified <code>ImageProducer</code>
     * @param imgf the specified <code>ImageFilter</code>
     * @see ImageFilter
     * @see java.awt.Component#createImage
     */
    public FilteredImageSource(java.awt.image.ImageProducer orig,
        java.awt.image.ImageFilter imgf)
    { }

    /** 
     * Adds an ImageConsumer to the list of consumers interested in
     * data for this image.
     * @see ImageConsumer
     */
    public synchronized void addConsumer(java.awt.image.ImageConsumer ic) { }

    /** 
     * Determines whether an ImageConsumer is on the list of consumers 
     * currently interested in data for this image.
     * @param ic the specified <code>ImageConsumer</code>
     * @return true if the ImageConsumer is on the list; false otherwise
     * @see ImageConsumer
     */
    public synchronized boolean isConsumer(java.awt.image.ImageConsumer ic) { return false; }

    /** 
     * Removes an ImageConsumer from the list of consumers interested in
     * data for this image.
     * @see ImageConsumer
     */
    public synchronized void removeConsumer(java.awt.image.ImageConsumer ic) { }

    /** 
     * Adds an ImageConsumer to the list of consumers interested in
     * data for this image, and immediately starts delivery of the
     * image data through the ImageConsumer interface.
     * @see ImageConsumer
     */
    public void startProduction(java.awt.image.ImageConsumer ic) { }

    /** 
     * Requests that a given ImageConsumer have the image data delivered
     * one more time in top-down, left-right order.  The request is
     * handed to the ImageFilter for further processing, since the
     * ability to preserve the pixel ordering depends on the filter.
     * @see ImageConsumer
     */
    public void requestTopDownLeftRightResend(java.awt.image.ImageConsumer ic)
    { }
}
