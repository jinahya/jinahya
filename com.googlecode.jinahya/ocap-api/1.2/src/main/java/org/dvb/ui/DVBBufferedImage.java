package org.dvb.ui;

import java.awt.*;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.*;
import java.util.Hashtable;
import java.util.Vector;
import org.dvb.ui.*;


/**
 *
 * The <code>DVBBufferedImage</code> subclass describes an {@link Image} with
 * an accessible buffer of image data.
 * The DVBBufferedImage is an adapter class for java.awt.image.BufferedImage. 
 * It supports two different platform dependent sample models TYPE_BASE and TYPE_ADVANCED.
 * Buffered images with the TYPE_BASE have the same sample model as the on screen graphics buffer, thus
 * TYPE_BASE could be CLUT based. TYPE_ADVANCED has a direct color model but it is not specified
 * how many bits are used to store the different color components.
 * By default, a new DVBBufferedImage is transparent. All alpha values are set to 0;
 * Instances of DVBBufferedImage shall be considered to be off-screen images for the 
 * purpose of the inherited method <code>Image.getGraphics</code>.
 * @since MHP 1.0
 */

public class DVBBufferedImage extends java.awt.Image 

{
	/**
	 *	Represents an image stored in a best possible SampleModel (platform dependent)
	 *	The image has a DirectColorModel
	 *	with alpha. The color data in this image is considered not to be
	 *	pre-multiplied with alpha.
	 *  The data returned by getRGB()
	 *	will be in the TYPE_INT_ARGB color model that is alpha component in bits 24 to 31, the red component in bits 16 to 23,
	 *  the green component in bits 8 to 15, and the blue component in bits 0 to 7. The data for setRGB() shall be
	 *	in the TYPE_INT_ARGB color model as well.
	 * @since MHP 1.0
	 */
	public static final int TYPE_ADVANCED = 20;



	/**
	 *	Represents an image stored in a platform dependent Sample Model.
	 *	This color model is not visible to applications. The data returned by getRGB()
	 *	will be in the TYPE_INT_ARGB color model that is alpha component in bits 24 to 31, the red component in bits 16 to 23,
	 *  the green component in bits 8 to 15, and the blue component in bits 0 to 7. The data for setRGB() shall be
	 *	in the TYPE_INT_ARGB color model as well.
	 * @since MHP 1.0
	 */
	public static final int TYPE_BASE = 21;

	

	Hashtable properties;

	boolean    isAlphaPremultiplied;// If true, alpha has been premultiplied in
	// color channels
		


	//private BufferedImage bimg;

	/**
	 *	Constructs a DVBBufferedImage with the specified width and height.
	 *	The Sample Model used the image is the
	 *	native Sample Model (TYPE_BASE) of the implementation.
	 *  Note that a request can lead to an java.lang.OutOfMemoryError. Applications should be aware
	 *  of this.
	 *	@param width the width of the created image
	 *  @param height the height of the created image
	 * @since MHP 1.0
	 *	
	 */
	public DVBBufferedImage(int width, 
						 int height) {

		
//		bimg = new BufferedImage(width,height,TYPE_BASE);
	}
	/**
	 *	Constructs a new DVBBufferedImage with the specified width and height
	 *	in the Sample Model specified by type.
     *  Note that a request can lead to an java.lang.OutOfMemoryError. Applications should be aware
	 *  of this.
	 *	@param width the width of the DVBBufferedImage
	 *  @param height the height of the DVBBufferedImage
	 *  @param type the ColorSpace of the DVBBufferedImage
	 * @since MHP 1.0
	 *	
	 */
public DVBBufferedImage(int width, int height, int type) {}
//	private DVBBufferedImage(BufferedImage b)
//	{
//		bimg = b;
//	}
	/**
	 * Creates a <code>DVBGraphics</code>, which can be used to draw into
	 * this <code>DVBBufferedImage</code>. Calls to this method after calls to the 
	 * <code>dispose</code> method on the same instance shall return null.
	 * @return a <code>DVBGraphics</code>, used for drawing into this
	 *          image.
	 * @since MHP 1.0
	 */
	public DVBGraphics createGraphics() {

		return null;
	}
	/**
	 * Flushes all resources being used to cache optimization information.
	 * The underlying pixel data is unaffected. Calls to this method after calls to the 
	 * <code>dispose</code> method on the same instance shall fail silently.
	 */
	public void flush() {
		//bimg.flush();
	}
	/**
	 * This method returns a {@link Graphics}, it is here
	 * for backwards compatibility. {@link #createGraphics() createGraphics} is more
	 * convenient, since it is declared to return a 
	 * <code>DVBGraphics</code>. Calls to this method after calls to the 
	 * <code>dispose</code> method on the same instance shall return null.
	 * @return a <code>Graphics</code>, which can be used to draw into
	 *          this image.
	 */
	public java.awt.Graphics getGraphics() {
		return (Graphics)null;//bimg.createGraphics();
	}
	/**
	 * Returns the height of the <code>DVBBufferedImage</code>.
	 * @return the height of this <code>DVBBufferedImage</code>.
	 * @since MHP 1.0
	 */
	public int getHeight() {
		return 0;//bimg.getHeight();
	}

	/**
	 * Returns the height of the image. 
	 * @param observer the <code>ImageObserver</code> that receives
	 *          information about the image
	 * @return the height of the image or <code>-1</code> if the height
	 *          is not yet known. 
	 * @see java.awt.Image#getWidth(ImageObserver)
	 * @see ImageObserver
	 */
	public int getHeight(ImageObserver observer) {
		return 0;//bimg.getHeight(observer);
	}
	/**
	  * Returns a java.awt.Image representing this buffered image.
	  * In implementations which implement java.awt.image.BufferedImage this returns a
	  * java.awt.image.BufferedImage cast to a java.awt.Image. Otherwise it is 
	  * implementation dependent whether it returns this image
	  * or whether it returns an instance of an underlying platform specific
	  * sub-class of java.awt.Image. Calls to this method after calls to the 
	  * <code>dispose</code> method on the same instance shall return null.
	  *
	  *  @return a java.awt.image representing this buffered image
	  *  @since MHP 1.0
	  */
	public Image getImage()
	{
		return (Image)null;//bimg;
	}
	/**
	 * Disposes of this buffered image. This method releases the resources
	 * (e.g. pixel memory) underlying this buffered image. After calling this
	 * method ;
	 * <ul>
	 * <li>the image concerned may not be used again
	 * <li>the image shall be considered to have a width and height of -1, -1
	 * as specified for instances of java.awt.Image where the width and
	 * height are not yet known. 
	 * <li>the <code>getGraphics</code> method may return null
	 * </ul>
	 * @since MHP 1.0.1
	 */
	public void dispose()
	{
	}

	/**
	 * Returns a property of the image by name. Individual property names
	 * are defined by the various image formats. If a property is not
	 * defined for a particular image, this method returns the
	 * <code>UndefinedProperty</code> field. If the properties
	 * for this image are not yet known, then this method returns
	 * <code>null</code> and the <code>ImageObserver</code> object is
	 * notified later. The property name "comment" should be used to
	 * store an optional comment that can be presented to the user as a
	 * description of the image, its source, or its author.
         * Calls to this method after calls to the 
	 * <code>dispose</code> method on the same instance shall return null.
	 * @param name the property name
	 * @param observer the <code>ImageObserver</code> that receives
	 *  notification regarding image information
	 * @return an {@link Object} that is the property referred to by the
	 *          specified <code>name</code> or <code>null</code> if the   
	 *          properties of this image are not yet known. 
	 * @see ImageObserver
	 * @see java.awt.Image#UndefinedProperty
	 */
	public Object getProperty(String name, ImageObserver observer) {
		return null;//bimg.getProperty(name,observer);
	}
	/**
	 *	Returns the specified integer pixel in the default RGB color model
	 * 	(TYPE_INT_ARGB) and default sRGB colorspace. Color
	 *	conversion takes place if the used Sample Model is not 8-bit for each color component
	 *	There are only 8-bits of
	 *	precision for each color component in the returned data when using
	 *	this method. Note that when a lower precision is used in this buffered image
	 *  getRGB may return different values than those used in setRGB()
	 * @param x the x-coordinate of the pixel
	 * @param y the y-coordinate of the pixel
	 * @return an integer pixel in the default RGB color model (TYPE_INT_ARGB) and
	 *          default sRGB colorspace.
	 * @throws ArrayIndexOutOfBoundsException if x or y is out of bounds or if the dispose
	 * method has been called on this instance
	 * @since MHP 1.0
	 */
	public int getRGB(int x, int y) {
		return 0;//bimg.getRGB(x,y);
	}
	/**
	 * Returns an array of integer pixels in the default RGB color model
	 * (TYPE_INT_ARGB) and default sRGB color space,
	 * from a rectangular region of the image data.
	 * There are only 8-bits of precision for
	 * each color component in the returned data when
	 * using this method. With a specified coordinate (x,&nbsp;y) in the
	 * image, the ARGB pixel can be accessed in this way:
	 * <pre>
	 *    pixel   = rgbArray[offset + (y-startY)*scansize + (x-startX)];
	 * </pre>
	 * @param startX the x-coordinate of the upper-left corner of the
	 *          specified rectangular region
	 * @param startY the y-coordinate of the upper-left corner of the
	 *          specified rectangular region
	 * @param w the width of the specified rectangular region 
	 * @param h the height of the specified rectangular region
	 * @param rgbArray    if not <code>null</code>, the rgb pixels are 
	 *          written here
	 * @param offset      offset into the <code>rgbArray</code>
	 * @param scansize    scanline stride for the <code>rgbArray</code>
	 * @return            array of ARGB pixels. 
	 * @throws ArrayIndexOutOfBoundsException if the specified portion of the image
	 * data is out of bounds or if the dispose method has been called on this instance
	 * @since MHP 1.0
	 */
	public int[] getRGB(int startX, int startY, int w, int h,
						int[] rgbArray, int offset, int scansize) {



		return null;//bimg.getRGB(startX,startY,w,h,rgbArray,offset,scansize);
	}
	/**
	 * Returns the object that produces the pixels for the image. 
	 * @return the {@link ImageProducer} that is used to produce the
	 * pixels for this image.<p>If the <code>dispose</code> method has been called
  	 * on this instance then null shall be returned.
         * The source returned by this method is platform generated to provide access 
         * to the current contents of the <code>DVBBufferedImage</code> buffer.
	 * @see ImageProducer
	 */
	public ImageProducer getSource() {
	   return null;//bimg.getSource();
	}
	/**
	 * Returns a subimage defined by a specified rectangular region.
	 * The returned <code>DVBBufferedImage</code> shares the same
	 * data array as the original image. If the <code>dispose</code> method has been called
  	 * on this instance then null shall be returned.
	 * @param x the x-coordinate of the upper-left corner of the
	 *          specified rectangular region
	 * @param y the y-coordinate of the upper-left corner of the
	 *          specified rectangular region
	 * @param w the width of the specified rectangular region 
	 * @param h the height of the specified rectangular region
	 * @return a <code>DVBBufferedImage</code> that is the subimage of this
	 *          <code>DVBBufferedImage</code>.
	 * @exception DVBRasterFormatException if the specified
	 * area is not contained within this <code>DVBBufferedImage</code>.
	 * @since MHP 1.0
	 */
	public DVBBufferedImage getSubimage (int x, int y, int w, int h) throws DVBRasterFormatException {
		return null;//new BufferedImage(bimg.getSubimage(x,y,w,h));
	}
	/**
	 * Returns the width of the <code>DVBBufferedImage</code>.
	 * @return the width of this <code>DVBBufferedImage</code>.
	 * @since MHP 1.0
	 */
	public int getWidth() {
		return 0;//bimg.getWidth();
	}
	/**
	 * Returns the width of the image. If the width is not known
	 * yet then the {@link ImageObserver} is notified later and 
	 * <code>-1</code> is returned.
	 * @param observer the <code>ImageObserver</code> that receives
	 *          information about the image
	 * @return the width of the image or <code>-1</code> if the width
	 *          is not yet known. 
	 * @see java.awt.Image#getHeight(ImageObserver)
	 * @see ImageObserver
	 */
	public int getWidth(ImageObserver observer) {
		return 0;//bimg.getWidth(observer);
	}
	/**
	 * Sets a pixel in this <code>DVBBufferedImage</code> to the specified   
	 * ARGB value. The pixel is assumed to be in the default RGB color
	 * model, TYPE_INT_ARGB, and default sRGB color space. Calls to this method after 
	 * calls to the <code>dispose</code> method on the same instance shall throw
	 * an <code>ArrayIndexOutOfBoundsException</code>.
	 * @param x the x-coordinate of the pixel to set
	 * @param y the y-coordinate of the pixel to set
	 * @param rgb the ARGB value
	 * @since MHP 1.0
	 */
	public synchronized void setRGB(int x, int y, int rgb) {
//		bimg.setRGB(x,y,rgb);
	}
	/**
	 * Sets an array of integer pixels in the default RGB color model
	 * (TYPE_INT_ARGB) and default sRGB color space,
	 * into a rectangular portion of the image data. There are only 8-bits of precision for
	 * each color component in the returned data when
	 * using this method. With a specified coordinate (x,&nbsp;y) in the   
	 * this image, the ARGB pixel can be accessed in this way:
	 * <pre>
	 *    pixel   = rgbArray[offset + (y-startY)*scansize + (x-startX)];
	 * </pre>
	 * WARNING: No dithering takes place.<p>
	 * Calls to this method after 
	 * calls to the <code>dispose</code> method on the same instance shall throw
	 * an <code>ArrayIndexOutOfBoundsException</code>.	 
	 *
	 * @param startX the x-coordinate of the upper-left corner of the
	 *          specified rectangular region
	 * @param startY the y-coordinate of the upper-left corner of the
	 *          specified rectangular region
	 * @param w the width of the specified rectangular region 
	 * @param h the height of the specified rectangular region
	 * @param rgbArray    the ARGB pixels
	 * @param offset      offset into the <code>rgbArray</code>
	 * @param scansize    scanline stride for the <code>rgbArray</code>
	 * @since MHP 1.0
	 */
	public void setRGB(int startX, int startY, int w, int h,
						int[] rgbArray, int offset, int scansize) {
//		bimg.setRGB(startX,startY,w,h,rgbArray,offset,scansize);
	}
	/**
	 * Returns a <code>String</code> representation of this
	 * <code>DVBBufferedImage</code> object and its values. 
	 * @return a <code>String</code> representing this
	 *          <code>DVBBufferedImage</code>.
	 */
	public String toString() {
		return new String("DVBBufferedImage");
	}

  /**
    * Creates a scaled version of this image.
    * A new <code>Image</code> object is returned which will render
    * the image at the specified <code>width</code> and
    * <code>height</code> by default. The new <code>Image</code> object
    * may be loaded asynchronously even if the original source image
    * has already been loaded completely. If either the <code>width</code>
    * or <code>height</code> is a negative number then a value is
    * substituted to maintain the aspect ratio of the original image
    * dimensions. If the <code>dispose</code> method has been called
    * on this instance then null shall be returned.
    * @param width the width to which to scale the image.
    * @param height the height to which to scale the image.
    * @param hints flags to indicate the type of algorithm to use
    * for image resampling.
    * @return     a scaled version of the image.
    */
  public java.awt.Image getScaledInstance(int width, int height, int hints) {
     return null;
  }

}

