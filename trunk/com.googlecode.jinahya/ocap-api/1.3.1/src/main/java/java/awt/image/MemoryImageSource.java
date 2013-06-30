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
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Enumeration;

/** 
 * This class is an implementation of the ImageProducer interface which
 * uses an array to produce pixel values for an Image.  Here is an example
 * which calculates a 100x100 image representing a fade from black to blue
 * along the X axis and a fade from black to red along the Y axis:
 * <pre>
 * 
 *	int w = 100;
 *	int h = 100;
 *	int pix[] = new int[w * h];
 *	int index = 0;
 *	for (int y = 0; y < h; y++) {
 *	    int red = (y * 255) / (h - 1);
 *	    for (int x = 0; x < w; x++) {
 *		int blue = (x * 255) / (w - 1);
 *		pix[index++] = (255 << 24) | (red << 16) | blue;
 *	    }
 *	}
 *	Image img = createImage(new MemoryImageSource(w, h, pix, 0, w));
 * 
 * </pre>
 * The MemoryImageSource is also capable of managing a memory image which
 * varies over time to allow animation or custom rendering.  Here is an
 * example showing how to set up the animation source and signal changes
 * in the data (adapted from the MemoryAnimationSourceDemo by Garth Dickie):
 * <pre>
 *
 *	int pixels[];
 *	MemoryImageSource source;
 *
 *	public void init() {
 *	    int width = 50;
 *	    int height = 50;
 *	    int size = width * height;
 *	    pixels = new int[size];
 *
 *	    int value = getBackground().getRGB();
 *	    for (int i = 0; i < size; i++) {
 *		pixels[i] = value;
 *	    }
 *
 *	    source = new MemoryImageSource(width, height, pixels, 0, width);
 *	    source.setAnimated(true);
 *	    image = createImage(source);
 *	}
 *
 *	public void run() {
 *	    Thread me = Thread.currentThread( );
 *	    me.setPriority(Thread.MIN_PRIORITY);
 *
 *	    while (true) {
 *		try {
 *		    thread.sleep(10);
 *		} catch( InterruptedException e ) {
 *		    return;
 *		}
 *
 *		// Modify the values in the pixels array at (x, y, w, h)
 *
 *		// Send the new data to the interested ImageConsumers
 *		source.newPixels(x, y, w, h);
 *	    }
 *	}
 *
 * </pre>
 *
 * @see ImageProducer
 *
 * @version	1.28 01/23/03
 * @author 	Jim Graham
 * @author	Animation capabilities inspired by the
 *		MemoryAnimationSource class written by Garth Dickie
 */
public class MemoryImageSource implements java.awt.image.ImageProducer
{

    /** 
     * Constructs an ImageProducer object which uses an array of bytes
     * to produce data for an Image object.
     * @param w the width of the rectangle of pixels
     * @param h the height of the rectangle of pixels
     * @param cm the specified <code>ColorModel</code>
     * @param pix an array of pixels
     * @param off the offset into the array of where to store the 
     *        first pixel
     * @param scan the distance from one row of pixels to the next in
     *        the array
     * @see java.awt.Component#createImage
     */
    public MemoryImageSource(int w, int h, java.awt.image.ColorModel cm, byte[]
        pix, int off, int scan)
    { }

    /** 
     * Constructs an ImageProducer object which uses an array of bytes
     * to produce data for an Image object.
     * @param w the width of the rectangle of pixels
     * @param h the height of the rectangle of pixels
     * @param cm the specified <code>ColorModel</code>
     * @param pix an array of pixels
     * @param off the offset into the array of where to store the 
     *        first pixel
     * @param scan the distance from one row of pixels to the next in
     *        the array
     * @param props a list of properties that the <code>ImageProducer</code>
     *        uses to process an image
     * @see java.awt.Component#createImage
     */
    public MemoryImageSource(int w, int h, java.awt.image.ColorModel cm, byte[]
        pix, int off, int scan, Hashtable props)
    { }

    /** 
     * Constructs an ImageProducer object which uses an array of integers
     * to produce data for an Image object.
     * @param w the width of the rectangle of pixels
     * @param h the height of the rectangle of pixels
     * @param cm the specified <code>ColorModel</code>
     * @param pix an array of pixels
     * @param off the offset into the array of where to store the 
     *        first pixel
     * @param scan the distance from one row of pixels to the next in
     *        the array
     * @see java.awt.Component#createImage
     */
    public MemoryImageSource(int w, int h, java.awt.image.ColorModel cm, int[]
        pix, int off, int scan)
    { }

    /** 
     * Constructs an ImageProducer object which uses an array of integers
     * to produce data for an Image object.
     * @param w the width of the rectangle of pixels
     * @param h the height of the rectangle of pixels
     * @param cm the specified <code>ColorModel</code>
     * @param pix an array of pixels
     * @param off the offset into the array of where to store the 
     *        first pixel
     * @param scan the distance from one row of pixels to the next in
     *        the array
     * @param props a list of properties that the <code>ImageProducer</code>
     *        uses to process an image
     * @see java.awt.Component#createImage
     */
    public MemoryImageSource(int w, int h, java.awt.image.ColorModel cm, int[]
        pix, int off, int scan, Hashtable props)
    { }

    /** 
     * Constructs an ImageProducer object which uses an array of integers
     * in the default RGB ColorModel to produce data for an Image object.
     * @param w the width of the rectangle of pixels
     * @param h the height of the rectangle of pixels
     * @param pix an array of pixels
     * @param off the offset into the array of where to store the 
     *        first pixel
     * @param scan the distance from one row of pixels to the next in
     *        the array
     * @see java.awt.Component#createImage
     * @see ColorModel#getRGBdefault
     */
    public MemoryImageSource(int w, int h, int[] pix, int off, int scan) { }

    /** 
     * Constructs an ImageProducer object which uses an array of integers
     * in the default RGB ColorModel to produce data for an Image object.
     * @param w the width of the rectangle of pixels
     * @param h the height of the rectangle of pixels
     * @param pix an array of pixels
     * @param off the offset into the array of where to store the 
     *        first pixel
     * @param scan the distance from one row of pixels to the next in
     *        the array
     * @param props a list of properties that the <code>ImageProducer</code>
     *        uses to process an image
     * @see java.awt.Component#createImage
     * @see ColorModel#getRGBdefault
     */
    public MemoryImageSource(int w, int h, int[] pix, int off, int scan,
        Hashtable props)
    { }

    /** 
     * Adds an ImageConsumer to the list of consumers interested in
     * data for this image.
     * @param ic the specified <code>ImageConsumer</code>
     * @see ImageConsumer
     */
    public synchronized void addConsumer(java.awt.image.ImageConsumer ic) { }

    /** 
     * Determines if an ImageConsumer is on the list of consumers currently
     * interested in data for this image.
     * @param ic the specified <code>ImageConsumer</code>
     * @return <code>true</code> if the <code>ImageConsumer</code>
     * is on the list; <code>false</code> otherwise.
     * @see ImageConsumer
     */
    public synchronized boolean isConsumer(java.awt.image.ImageConsumer ic) { return false; }

    /** 
     * Removes an ImageConsumer from the list of consumers interested in
     * data for this image.
     * @param ic the specified <code>ImageConsumer</code>
     * @see ImageConsumer
     */
    public synchronized void removeConsumer(java.awt.image.ImageConsumer ic) { }

    /** 
     * Adds an ImageConsumer to the list of consumers interested in
     * data for this image and immediately starts delivery of the
     * image data through the ImageConsumer interface.
     * @param ic the specified <code>ImageConsumer</code>
     * image data through the ImageConsumer interface.
     * @see ImageConsumer
     */
    public void startProduction(java.awt.image.ImageConsumer ic) { }

    /** 
     * Requests that a given ImageConsumer have the image data delivered
     * one more time in top-down, left-right order.
     * @param ic the specified <code>ImageConsumer</code>
     * @see ImageConsumer
     */
    public void requestTopDownLeftRightResend(java.awt.image.ImageConsumer ic)
    { }

    /** 
     * Changes this memory image into a multi-frame animation or a
     * single-frame static image depending on the animated parameter.
     * <p>This method should be called immediately after the
     * MemoryImageSource is constructed and before an image is
     * created with it to ensure that all ImageConsumers will
     * receive the correct multi-frame data.  If an ImageConsumer
     * is added to this ImageProducer before this flag is set then
     * that ImageConsumer will see only a snapshot of the pixel
     * data that was available when it connected.
     * @param animated <code>true</code> if the image is a 
     *       multi-frame animation
     */
    public synchronized void setAnimated(boolean animated) { }

    /** 
     * Specifies whether this animated memory image should always be
     * updated by sending the complete buffer of pixels whenever
     * there is a change.
     * This flag is ignored if the animation flag is not turned on
     * through the setAnimated() method.
     * <p>This method should be called immediately after the
     * MemoryImageSource is constructed and before an image is
     * created with it to ensure that all ImageConsumers will
     * receive the correct pixel delivery hints.
     * @param fullbuffers <code>true</code> if the complete pixel 
     *             buffer should always
     * be sent
     * @see #setAnimated
     */
    public synchronized void setFullBufferUpdates(boolean fullbuffers) { }

    /** 
     * Sends a whole new buffer of pixels to any ImageConsumers that
     * are currently interested in the data for this image and notify
     * them that an animation frame is complete.
     * This method only has effect if the animation flag has been
     * turned on through the setAnimated() method.
     * @see #newPixels(int, int, int, int, boolean)
     * @see ImageConsumer
     * @see #setAnimated
     */
    public void newPixels() { }

    /** 
     * Sends a rectangular region of the buffer of pixels to any
     * ImageConsumers that are currently interested in the data for
     * this image and notify them that an animation frame is complete.
     * This method only has effect if the animation flag has been
     * turned on through the setAnimated() method.
     * If the full buffer update flag was turned on with the
     * setFullBufferUpdates() method then the rectangle parameters
     * will be ignored and the entire buffer will always be sent.
     * @param x the x coordinate of the upper left corner of the rectangle
     * of pixels to be sent
     * @param y the y coordinate of the upper left corner of the rectangle
     * of pixels to be sent
     * @param w the width of the rectangle of pixels to be sent
     * @param h the height of the rectangle of pixels to be sent
     * @see #newPixels(int, int, int, int, boolean)
     * @see ImageConsumer
     * @see #setAnimated
     * @see #setFullBufferUpdates
     */
    public synchronized void newPixels(int x, int y, int w, int h) { }

    /** 
     * Sends a rectangular region of the buffer of pixels to any
     * ImageConsumers that are currently interested in the data for
     * this image.
     * If the framenotify parameter is true then the consumers are
     * also notified that an animation frame is complete.
     * This method only has effect if the animation flag has been
     * turned on through the setAnimated() method.
     * If the full buffer update flag was turned on with the
     * setFullBufferUpdates() method then the rectangle parameters
     * will be ignored and the entire buffer will always be sent.
     * @param x the x coordinate of the upper left corner of the rectangle
     * of pixels to be sent
     * @param y the y coordinate of the upper left corner of the rectangle
     * of pixels to be sent
     * @param w the width of the rectangle of pixels to be sent
     * @param h the height of the rectangle of pixels to be sent
     * @param framenotify <code>true</code> if the consumers should be sent a
     * {@link ImageConsumer#SINGLEFRAMEDONE SINGLEFRAMEDONE} notification
     * @see ImageConsumer
     * @see #setAnimated
     * @see #setFullBufferUpdates
     */
    public synchronized void newPixels(int x, int y, int w, int h, boolean
        framenotify)
    { }

    /** 
     * Changes to a new byte array to hold the pixels for this image.
     * If the animation flag has been turned on through the setAnimated()
     * method, then the new pixels will be immediately delivered to any
     * ImageConsumers that are currently interested in the data for
     * this image.
     * @param newpix the new pixel array
     * @param newmodel the specified <code>ColorModel</code>
     * @param offset the offset into the array
     * @param scansize the distance from one row of pixels to the next in
     * the array
     * @see #newPixels(int, int, int, int, boolean)
     * @see #setAnimated
     */
    public synchronized void newPixels(byte[] newpix, java.awt.image.ColorModel
        newmodel, int offset, int scansize)
    { }

    /** 
     * Changes to a new int array to hold the pixels for this image.
     * If the animation flag has been turned on through the setAnimated()
     * method, then the new pixels will be immediately delivered to any
     * ImageConsumers that are currently interested in the data for
     * this image.
     * @param newpix the new pixel array
     * @param newmodel the specified <code>ColorModel</code>
     * @param offset the offset into the array
     * @param scansize the distance from one row of pixels to the next in
     * the array
     * @see #newPixels(int, int, int, int, boolean)
     * @see #setAnimated
     */
    public synchronized void newPixels(int[] newpix, java.awt.image.ColorModel
        newmodel, int offset, int scansize)
    { }
}
