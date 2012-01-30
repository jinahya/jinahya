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
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
// import java.awt.geom.Rectangle2D;
// import java.awt.geom.Point2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Hashtable;
import java.util.Vector;
// import sun.awt.image.BytePackedRaster;
// import sun.awt.image.ShortComponentRaster;
// import sun.awt.image.ByteComponentRaster;
// import sun.awt.image.IntegerComponentRaster;
// import sun.awt.image.OffScreenImageSource;

// PBP/PP
// [6187206]

/** 
 * The <code>BufferedImage</code> subclass describes an {@link
 * java.awt.Image Image} with an accessible buffer of image data.

 * All <code>BufferedImage</code> objects have an upper left corner
 * coordinate of (0,&nbsp;0).

 * @see ColorModel

 * @version 10 Feb 1997
 */
public class BufferedImage extends java.awt.Image
    // public class BufferedImage extends java.awt.Image
    // implements WritableRenderedImage
{
    /** 
     * Image type is not recognized so it must be a customized
     * image.  This type is only used as a return value for the getType()
     * method.
     */
    public static final int TYPE_CUSTOM = 0;

    /** 
     * Represents an image with 8-bit RGB color components packed into
     * integer pixels.  The image has a {@link DirectColorModel} without
     * alpha.
     */
    public static final int TYPE_INT_RGB = 1;

    // PBP/PP 6217661
    /** 
     * Represents an image with 8-bit RGBA color components packed into
     * integer pixels.  The image has a <code>DirectColorModel</code> 
     * with alpha. The color data in this image is considered not to be
     * premultiplied with alpha.  
     */
    public static final int TYPE_INT_ARGB = 2;

    /** 
     * Represents an image with 8-bit RGBA color components packed into
     * integer pixels.  The image has a <code>DirectColorModel</code> 
     * with alpha.  The color data in this image is considered to be
     * premultiplied with alpha.
     */
    public static final int TYPE_INT_ARGB_PRE = 3;

    /** 
     * Represents an image with 8-bit RGB color components, corresponding 
     * to a Windows- or Solaris- style BGR color model, with the colors
     * Blue, Green, and Red packed into integer pixels.  There is no alpha.
     * The image has a {@link DirectColorModel}.
     */
    public static final int TYPE_INT_BGR = 4;

    // /** 
     // * Represents an image with 8-bit RGB color components, corresponding 
     // * to a Windows-style BGR color model) with the colors Blue, Green,
     // * and Red stored in 3 bytes.  There is no alpha.  The image has a
     // * <code>ComponentColorModel</code>.
     // */
    // public static final int TYPE_3BYTE_BGR = 0;
// 
    // /** 
     // * Represents an image with 8-bit RGBA color components with the colors
     // * Blue, Green, and Red stored in 3 bytes and 1 byte of alpha.  The
     // * image has a <code>ComponentColorModel</code> with alpha.  The
     // * color data in this image is considered not to be premultiplied with
     // * alpha.  The byte data is interleaved in a single 
     // * byte array in the order A, B, G, R
     // * from lower to higher byte addresses within each pixel.
     // */
    // public static final int TYPE_4BYTE_ABGR = 0;
// 
    // /** 
     // * Represents an image with 8-bit RGBA color components with the colors
     // * Blue, Green, and Red stored in 3 bytes and 1 byte of alpha.  The
     // * image has a <code>ComponentColorModel</code> with alpha. The color
     // * data in this image is considered to be premultiplied with alpha.
     // * The byte data is interleaved in a single byte array in the order
     // * A, B, G, R from lower to higher byte addresses within each pixel.
     // */
    // public static final int TYPE_4BYTE_ABGR_PRE = 0;

    /** 
     * Represents an image with 5-6-5 RGB color components (5-bits red,
     * 6-bits green, 5-bits blue) with no alpha.  This image has
     * a <code>DirectColorModel</code>.
     */
    public static final int TYPE_USHORT_565_RGB = 8;

    /** 
     * Represents an image with 5-5-5 RGB color components (5-bits red,
     * 5-bits green, 5-bits blue) with no alpha.  This image has
     * a <code>DirectColorModel</code>.
     */
    public static final int TYPE_USHORT_555_RGB = 9;

    // /** 
     // * Represents a unsigned byte grayscale image, non-indexed.  This
     // * image has a <code>ComponentColorModel</code> with a CS_GRAY
     // * {@link ColorSpace}.
     // */
    // public static final int TYPE_BYTE_GRAY = 0;
// 
    // /** 
     // * Represents an unsigned short grayscale image, non-indexed).  This
     // * image has a <code>ComponentColorModel</code> with a CS_GRAY
     // * <code>ColorSpace</code>.
     // */
    // public static final int TYPE_USHORT_GRAY = 0;

   // PBP/PP 6217661
     /** 
     * Represents an opaque byte-packed 1, 2, or 4 bit image.  The
     * image has an {@link IndexColorModel} without alpha.  
     *
     * 
     *
     * <p> Images with 8 bits per pixel should use the image type
     * <code>TYPE_BYTE_INDEXED</code>.
     */
    public static final int TYPE_BYTE_BINARY = 12;


   // PBP/PP 6217661
    /** 
     * Represents an indexed byte image.  
     */
    public static final int TYPE_BYTE_INDEXED = 13;


    /**
     * PBP: Placeholder to hide constructor.
     */
    BufferedImage() {}


    // /** 
     // * Constructs a <code>BufferedImage</code> of one of the predefined
     // * image types.  The <code>ColorSpace</code> for the image is the
     // * default sRGB space.
     // * @param width     width of the created image
     // * @param height    height of the created image
     // * @param imageType type of the created image
     // * @see ColorSpace
     // * @see #TYPE_INT_RGB
     // * @see #TYPE_INT_ARGB
     // * @see #TYPE_INT_ARGB_PRE
     // * @see #TYPE_INT_BGR
     // * @see #TYPE_3BYTE_BGR
     // * @see #TYPE_4BYTE_ABGR
     // * @see #TYPE_4BYTE_ABGR_PRE
     // * @see #TYPE_BYTE_GRAY
     // * @see #TYPE_USHORT_GRAY
     // * @see #TYPE_BYTE_BINARY
     // * @see #TYPE_BYTE_INDEXED
     // * @see #TYPE_USHORT_565_RGB
     // * @see #TYPE_USHORT_555_RGB
     // */
    // public BufferedImage(int width, int height, int imageType) { }
// 
    // /** 
     // * Constructs a <code>BufferedImage</code> of one of the predefined
     // * image types:
     // * TYPE_BYTE_BINARY or TYPE_BYTE_INDEXED.
     // *
     // * <p> If the image type is TYPE_BYTE_BINARY, the number of
     // * entries in the color model is used to determine whether the
     // * image should have 1, 2, or 4 bits per pixel.  If the color model
     // * has 1 or 2 entries, the image will have 1 bit per pixel.  If it
     // * has 3 or 4 entries, the image with have 2 bits per pixel.  If
     // * it has between 5 and 16 entries, the image will have 4 bits per
     // * pixel.  Otherwise, an IllegalArgumentException will be thrown.
     // *
     // * @param width     width of the created image
     // * @param height    height of the created image
     // * @param imageType type of the created image
     // * @param cm        <code>IndexColorModel</code> of the created image
     // * @throws IllegalArgumentException   if the imageType is not
     // * TYPE_BYTE_BINARY or TYPE_BYTE_INDEXED or if the imageType is
     // * TYPE_BYTE_BINARY and the color map has more than 16 entries.
     // * @see #TYPE_BYTE_BINARY
     // * @see #TYPE_BYTE_INDEXED
     // */
    // public BufferedImage(int width, int height, int imageType, IndexColorModel
        // cm)
    // { }
// 
    // /** 
     // * Constructs a new <code>BufferedImage</code> with a specified 
     // * <code>ColorModel</code> and <code>Raster</code>.  If the number and
     // * types of bands in the <code>SampleModel</code> of the 
     // * <code>Raster</code> do not match the number and types required by
     // * the <code>ColorModel</code> to represent its color and alpha
     // * components, a {@link RasterFormatException} is thrown.  This
     // * method can multiply or divide the color <code>Raster</code> data by
     // * alpha to match the <code>alphaPremultiplied</code> state
     // * in the <code>ColorModel</code>.  Properties for this 
     // * <code>BufferedImage</code> can be established by passing
     // * in a {@link Hashtable} of <code>String</code>/<code>Object</code> 
     // * pairs.
     // * @param cm <code>ColorModel</code> for the new image
     // * @param raster     <code>Raster</code> for the image data
     // * @param isRasterPremultiplied   if <code>true</code>, the data in
     // *                  the raster has been premultiplied with alpha.
     // * @param properties <code>Hashtable</code> of
     // *                  <code>String</code>/<code>Object</code> pairs. 
     // * @exception <code>RasterFormatException</code> if the number and
     // * types of bands in the <code>SampleModel</code> of the
     // * <code>Raster</code> do not match the number and types required by
     // * the <code>ColorModel</code> to represent its color and alpha
     // * components.
     // * @exception <code>IllegalArgumentException</code> if 
     // *		<code>raster</code> is incompatible with <code>cm</code>
     // * @see ColorModel
     // * @see Raster
     // * @see WritableRaster
     // */
    // public BufferedImage(ColorModel cm, WritableRaster raster, boolean
        // isRasterPremultiplied, Hashtable properties)
    // { }

// PBP/PP
    /** 
     * Returns the image type.  If it is not one of the known types,
     * TYPE_CUSTOM is returned.
     * @return the image type of this <code>BufferedImage</code>.
     * @see #TYPE_INT_RGB
     * @see #TYPE_INT_ARGB
     * @see #TYPE_INT_ARGB_PRE
     * @see #TYPE_INT_BGR

     * @see #TYPE_BYTE_BINARY
     * @see #TYPE_BYTE_INDEXED

     * @see #TYPE_USHORT_565_RGB
     * @see #TYPE_USHORT_555_RGB
     * @see #TYPE_CUSTOM
     */
    public int getType() {return 0;  }

    /** 
     * Returns the <code>ColorModel</code>.
     * @return the <code>ColorModel</code> of this
     *  <code>BufferedImage</code>.
     */
    public ColorModel getColorModel() { return null; }

    // /** 
     // * Returns the {@link WritableRaster}.
     // * @return the <code>WriteableRaster</code> of this
     // *  <code>BufferedImage</code>.
     // */
    // public WritableRaster getRaster() { }

    // /** 
     // * Returns a <code>WritableRaster</code> representing the alpha
     // * channel for <code>BufferedImage</code> objects
     // * with <code>ColorModel</code> objects that support a separate
     // * spatial alpha channel, such as <code>ComponentColorModel</code> and
     // * <code>DirectColorModel</code>.  Returns <code>null</code> if there
     // * is no alpha channel associated with the <code>ColorModel</code> in
     // * this image.  This method assumes that for all 
     // * <code>ColorModel</code> objects other than 
     // * <code>IndexColorModel</code>, if the <code>ColorModel</code> 
     // * supports alpha, there is a separate alpha channel
     // * which is stored as the last band of image data.
     // * If the image uses an <code>IndexColorModel</code> that
     // * has alpha in the lookup table, this method returns
     // * <code>null</code> since there is no spatially discrete alpha
     // * channel.  This method creates a new
     // * <code>WritableRaster</code>, but shares the data array.
     // * @return a <code>WritableRaster</code> or <code>null</code> if this
     // *          <code>BufferedImage</code> has no alpha channel associated
     // *          with its <code>ColorModel</code>.
     // */
    // public WritableRaster getAlphaRaster() { }

    /** 
     * Returns an integer pixel in the default RGB color model
     * (TYPE_INT_ARGB) and default sRGB colorspace.  Color
     * conversion takes place if this default model does not match
     * the image <code>ColorModel</code>.  There are only 8-bits of
     * precision for each color component in the returned data when using
     * this method.
     * @param x,&nbsp;y the coordinates of the pixel from which to get
     *          the pixel in the default RGB color model and sRGB
     *          color space
     * @return an integer pixel in the default RGB color model and
     *          default sRGB colorspace. 
     * @see #setRGB(int, int, int)
     * @see #setRGB(int, int, int, int, int[], int, int)
     */
    public int getRGB(int x, int y) { return 0; }

    /** 
     * Returns an array of integer pixels in the default RGB color model
     * (TYPE_INT_ARGB) and default sRGB color space,
     * from a portion of the image data.  Color conversion takes
     * place if the default model does not match the image 
     * <code>ColorModel</code>.  There are only 8-bits of precision for
     * each color component in the returned data when
     * using this method.  With a specified coordinate (x,&nbsp;y) in the
     * image, the ARGB pixel can be accessed in this way:
     * <pre>
     *    pixel   = rgbArray[offset + (y-startY)*scansize + (x-startX)];
     * </pre>
     * @param startX,&nbsp;startY the starting coordinates
     * @param w           width of region
     * @param h           height of region
     * @param rgbArray    if not <code>null</code>, the rgb pixels are 
     *          written here
     * @param offset      offset into the <code>rgbArray</code>
     * @param scansize    scanline stride for the <code>rgbArray</code>
     * @return            array of RGB pixels. 
     * @exception <code>IllegalArgumentException</code> if an unknown
     *		datatype is specified
     * @see #setRGB(int, int, int)
     * @see #setRGB(int, int, int, int, int[], int, int)
     */
    public int[] getRGB(int startX, int startY, int w, int h, int[] rgbArray,
        int offset, int scansize)
    { return null; }

    /** 
     * Sets a pixel in this <code>BufferedImage</code> to the specified   
     * RGB value. The pixel is assumed to be in the default RGB color
     * model, TYPE_INT_ARGB, and default sRGB color space.  For images
     * with an <code>IndexColorModel</code>, the index with the nearest
     * color is chosen.
     * @param x,&nbsp;y the coordinates of the pixel to set
     * @param rgb the RGB value 
     * @see #getRGB(int, int)
     * @see #getRGB(int, int, int, int, int[], int, int)
     */
    public synchronized void setRGB(int x, int y, int rgb) { }

    /** 
     * Sets an array of integer pixels in the default RGB color model
     * (TYPE_INT_ARGB) and default sRGB color space,
     * into a portion of the image data.  Color conversion takes place
     * if the default model does not match the image 
     * <code>ColorModel</code>.  There are only 8-bits of precision for
     * each color component in the returned data when
     * using this method.  With a specified coordinate (x,&nbsp;y) in the   
     * this image, the ARGB pixel can be accessed in this way:
     * <pre>
     *    pixel   = rgbArray[offset + (y-startY)*scansize + (x-startX)];
     * </pre>
     * WARNING: No dithering takes place.
     *
     * @param startX,&nbsp;startY the starting coordinates
     * @param w           width of the region
     * @param h           height of the region
     * @param rgbArray    the rgb pixels
     * @param offset      offset into the <code>rgbArray</code>
     * @param scansize    scanline stride for the <code>rgbArray</code> 
     * @see #getRGB(int, int)
     * @see #getRGB(int, int, int, int, int[], int, int)
     */
    public void setRGB(int startX, int startY, int w, int h, int[] rgbArray, int
        offset, int scansize)
    { }

    /** 
     * Returns the width of the <code>BufferedImage</code>.
     * @return the width of this <code>BufferedImage</code>
     */
    public int getWidth() {return 0;  }

    /** 
     * Returns the height of the <code>BufferedImage</code>.
     * @return the height of this <code>BufferedImage</code>
     */
    public int getHeight() {return 0;  }

    /** 
     * Returns the width of the <code>BufferedImage</code>.  
     * @param observer ignored
     * @return the width of this <code>BufferedImage</code> 
     */
    public int getWidth(ImageObserver observer) {return 0;  }

    /** 
     * Returns the height of the <code>BufferedImage</code>.  
     * @param observer ignored
     * @return the height of this <code>BufferedImage</code> 
     */
    public int getHeight(ImageObserver observer) {return 0;  }

    /** 
     * Returns the object that produces the pixels for the image.  
     * @return the {@link ImageProducer} that is used to produce the
     * pixels for this image.
     * @see ImageProducer
     */
    public ImageProducer getSource() { return null; }

    /** 
     * Returns a property of the image by name.  Individual property names
     * are defined by the various image formats.  If a property is not
     * defined for a particular image, this method returns the
     * <code>UndefinedProperty</code> field.  If the properties
     * for this image are not yet known, then this method returns
     * <code>null</code> and the <code>ImageObserver</code> object is
     * notified later.  The property name "comment" should be used to
     * store an optional comment that can be presented to the user as a
     * description of the image, its source, or its author.
     * @param name the property name
     * @param observer the <code>ImageObserver</code> that receives
     *  notification regarding image information
     * @return an {@link Object} that is the property referred to by the
     *          specified <code>name</code> or <code>null</code> if the   
     *          properties of this image are not yet known. 
     * @see ImageObserver
     * @see java.awt.Image#UndefinedProperty
     */
    public Object getProperty(String name, ImageObserver observer) { return null; }

    /** 
     * Returns a property of the image by name.
     * @param name the property name
     * @return an <code>Object</code> that is the property referred to by
     *          the specified <code>name</code>. 
     */
    public Object getProperty(String name) { return null; }

    /** 
     * Flushes all resources being used to cache optimization information.
     * The underlying pixel data is unaffected.
     */
    public void flush() { }

    /** 
     * This method returns a {@link Graphics2D}, but is here
     * for backwards compatibility.  {@link #createGraphics() createGraphics} is more
     * convenient, since it is declared to return a 
     * <code>Graphics2D</code>.
     * @return a <code>Graphics2D</code>, which can be used to draw into
     *          this image.
     */
    public java.awt.Graphics getGraphics() { return null; }

    /** 
     * Creates a <code>Graphics2D</code>, which can be used to draw into
     * this <code>BufferedImage</code>.
     * @return a <code>Graphics2D</code>, used for drawing into this
     *          image. 
     */
    public Graphics2D createGraphics() { return null; }

    /** 
     * Returns a subimage defined by a specified rectangular region.
     * The returned <code>BufferedImage</code> shares the same
     * data array as the original image.
     * @param x,&nbsp;y the coordinates of the upper-left corner of the
     *          specified rectangular region
     * @param w the width of the specified rectangular region 
     * @param h the height of the specified rectangular region
     * @return a <code>BufferedImage</code> that is the subimage of this
     *          <code>BufferedImage</code>. 
     * @exception <code>RasterFormatException</code> if the specified
     * area is not contained within this <code>BufferedImage</code>.
     */
    public BufferedImage getSubimage(int x, int y, int w, int h) { return null; }

    // /** 
     // * Returns whether or not the alpha has been premultiplied.  It
     // * returns <code>true</code> if there is no alpha since the
     // * default alpha is OPAQUE.
     // * @return <code>true</code> if the alpha has been premultiplied;   
     // *          <code>false</code> otherwise. 
     // */
    // public boolean isAlphaPremultiplied() { }

    // /** 
     // * Forces the data to match the state specified in the
     // * <code>isAlphaPremultiplied</code> variable.  It may multiply or
     // * divide the color raster data by alpha, or do nothing if the data is
     // * in the correct state.
     // * @param isAlphaPremultiplied <code>true</code> if the alpha has been
     // *          premultiplied; <code>false</code> otherwise. 
     // */
    // public void coerceData(boolean isAlphaPremultiplied) { }

    /** 
     * Returns a <code>String</code> representation of this
     * <code>BufferedImage</code> object and its values.   
     * @return a <code>String</code> representing this
     *          <code>BufferedImage</code>.
     */
    public String toString() { return null; }

    // /** 
     // * Returns a {@link Vector} of {@link RenderedImage} objects that are
     // * the immediate sources, not the sources of these immediate sources, 
     // * of image data for this <code>BufferedImage</code>.  This
     // * method returns <code>null</code> if the <code>BufferedImage</code> 
     // * has no information about its immediate sources.  It returns an
     // * empty <code>Vector</code> if the <code>BufferedImage</code> has no
     // * immediate sources.
     // * @return a <code>Vector</code> containing immediate sources of
     // *          this <code>BufferedImage</code> object's image date, or
     // *          <code>null</code> if this <code>BufferedImage</code> has
     // *          no information about its immediate sources, or an empty
     // *          <code>Vector</code> if this <code>BufferedImage</code>   
     // *          has no immediate sources. 
     // */
    // public Vector getSources() { }

    /** 
     * Returns an array of names recognized by 
     * {@link #getProperty(String) getProperty(String)}
     * or <code>null</code>, if no property names are recognized.
     * @return a <code>String</code> array containing all of the property
     *          names that <code>getProperty(String)</code> recognizes;
     *		or <code>null</code> if no property names are recognized.
     */
    public String[] getPropertyNames() { return null; }

    // /** 
     // * Returns the minimum x coordinate of this
     // * <code>BufferedImage</code>.  This is always zero.
     // * @return the minimum x coordinate of this
     // *          <code>BufferedImage</code>.
     // */
    // public int getMinX() { }
// 
    // /** 
     // * Returns the minimum y coordinate of this
     // * <code>BufferedImage</code>.  This is always zero.
     // * @return the minimum y coordinate of this
     // *          <code>BufferedImage</code>.
     // */
    // public int getMinY() { }

    // /** 
     // * Returns the <code>SampleModel</code> associated with this
     // * <code>BufferedImage</code>. 
     // * @return the <code>SampleModel</code> of this
     // *          <code>BufferedImage</code>.
     // */
    // public SampleModel getSampleModel() { }
// 
    // /** 
     // * Returns the number of tiles in the x direction.
     // * This is always one.
     // * @return the number of tiles in the x direction.
     // */
    // public int getNumXTiles() { }
// 
    // /** 
     // * Returns the number of tiles in the y direction.
     // * This is always one.
     // * @return the number of tiles in the y direction.
     // */
    // public int getNumYTiles() { }
// 
    // /** 
     // * Returns the minimum tile index in the x direction.
     // * This is always zero.
     // * @return the minimum tile index in the x direction.
     // */
    // public int getMinTileX() { }
// 
    // /** 
     // * Returns the minimum tile index in the y direction.
     // * This is always zero.
     // * @return the mininum tile index in the y direction.
     // */
    // public int getMinTileY() { }

    // /** 
     // * Returns the tile width in pixels.
     // * @return the tile width in pixels.
     // */
    // public int getTileWidth() { }
// 
    // /** 
     // * Returns the tile height in pixels.
     // * @return the tile height in pixels.
     // */
    // public int getTileHeight() { }
// 
    // /** 
     // * Returns the x offset of the tile grid relative to the origin,
     // * For example, the x coordinate of the location of tile 
     // * (0,&nbsp;0).  This is always zero.
     // * @return the x offset of the tile grid.
     // */
    // public int getTileGridXOffset() { }
// 
    // /** 
     // * Returns the y offset of the tile grid relative to the origin,
     // * For example, the y coordinate of the location of tile 
     // * (0,&nbsp;0).  This is always zero.
     // * @return the y offset of the tile grid.
     // */
    // public int getTileGridYOffset() { }
// 
    // /** 
     // * Returns tile (<code>tileX</code>,&nbsp;<code>tileY</code>).  Note
     // * that <code>tileX</code> and <code>tileY</code> are indices
     // * into the tile array, not pixel locations.  The <code>Raster</code> 
     // * that is returned is live, which means that it is updated if the
     // * image is changed.
     // * @param tileX the x index of the requested tile in the tile array
     // * @param tileY the y index of the requested tile in the tile array
     // * @return a <code>Raster</code> that is the tile defined by the
     // *          arguments <code>tileX</code> and <code>tileY</code>. 
     // * @exception <code>ArrayIndexOutOfBoundsException</code> if both
     // * 		<code>tileX</code> and <code>tileY</code> are not
     // *		equal to 0
     // */
    // public Raster getTile(int tileX, int tileY) { }

    // /** 
     // * Returns the image as one large tile.  The <code>Raster</code> 
     // * returned is a copy of the image data is not updated if the
     // * image is changed.
     // * @return a <code>Raster</code> that is a copy of the image data. 
     // * @see #setData(Raster)
     // */
    // public Raster getData() { }
// 
    // /** 
     // * Computes and returns an arbitrary region of the 
     // * <code>BufferedImage</code>.  The <code>Raster</code> returned is a
     // * copy of the image data and is not updated if the image is
     // * changed.
     // * @param rect the region of the <code>BufferedImage</code> to be
     // * returned.
     // * @return a <code>Raster</code> that is a copy of the image data of
     // *          the specified region of the <code>BufferedImage</code> 
     // * @see #setData(Raster)
     // */
    // public Raster getData(Rectangle rect) { }
// 
    // /** 
     // * Computes an arbitrary rectangular region of the 
     // * <code>BufferedImage</code> and copies it into a specified
     // * <code>WritableRaster</code>.  The region to be computed is
     // * determined from the bounds of the specified
     // * <code>WritableRaster</code>.  The specified 
     // * <code>WritableRaster</code> must have a
     // * <code>SampleModel</code> that is compatible with this image.  If
     // * <code>outRaster</code> is <code>null</code>,
     // * an appropriate <code>WritableRaster</code> is created.
     // * @param outRaster a <code>WritableRaster</code> to hold the returned
     // *          part of the image, or <code>null</code>
     // * @return a reference to the supplied or created
     // *          <code>WritableRaster</code>. 
     // */
    // public WritableRaster copyData(WritableRaster outRaster) { }
// 
    // /** 
     // * Sets a rectangular region of the image to the contents of the
     // * specified <code>Raster</code> <code>r</code>, which is
     // * assumed to be in the same coordinate space as the
     // * <code>BufferedImage</code>. The operation is clipped to the bounds
     // * of the <code>BufferedImage</code>.
     // * @param r the specified <code>Raster</code> 
     // * @see #getData
     // * @see #getData(Rectangle)
     // */
    // public void setData(Raster r) { }

    // /** 
     // * Adds a tile observer.  If the observer is already present,
     // * it receives multiple notifications.
     // * @param to the specified {@link TileObserver}
     // */
    // public void addTileObserver(TileObserver to) { }
// 
    // /** 
     // * Removes a tile observer.  If the observer was not registered,
     // * nothing happens.  If the observer was registered for multiple
     // * notifications, it is now registered for one fewer notification.
     // * @param to the specified <code>TileObserver</code>.
     // */
    // public void removeTileObserver(TileObserver to) { }
// 
    // /** 
     // * Returns whether or not a tile is currently checked out for writing.
     // * @param tileX the x index of the tile.
     // * @param tileY the y index of the tile.
     // * @return <code>true</code> if the tile specified by the specified
     // *          indices is checked out for writing; <code>false</code>
     // *          otherwise. 
     // * @exception <code>ArrayIndexOutOfBoundsException</code> if both
     // * 		<code>tileX</code> and <code>tileY</code> are not equal
     // *		to 0
     // */
    // public boolean isTileWritable(int tileX, int tileY) { }
// 
    // /** 
     // * Returns an array of {@link Point} objects indicating which tiles
     // * are checked out for writing.  Returns <code>null</code> if none are
     // * checked out.
     // * @return a <code>Point</code> array that indicates the tiles that
     // *          are checked out for writing, or <code>null</code> if no
     // *          tiles are checked out for writing. 
     // */
    // public Point[] getWritableTileIndices() { }
// 
    // /** 
     // * Returns whether or not any tile is checked out for writing.
     // * Semantically equivalent to 
     // * <pre>
     // * (getWritableTileIndices() != null).
     // * </pre>
     // * @return <code>true</code> if any tile is checked out for writing;
     // *          <code>false</code> otherwise. 
     // */
    // public boolean hasTileWriters() { }
// 
    // /** 
     // * Checks out a tile for writing.  All registered 
     // * <code>TileObservers</code> are notified when a tile goes from having
     // * no writers to having one writer.
     // * @param tileX the x index of the tile
     // * @param tileY the y index of the tile
     // * @return a <code>WritableRaster</code> that is the tile, indicated by
     // *            the specified indices, to be checked out for writing. 
     // */
    // public WritableRaster getWritableTile(int tileX, int tileY) { }
// 
    // /** 
     // * Relinquishes permission to write to a tile.  If the caller 
     // * continues to write to the tile, the results are undefined.
     // * Calls to this method should only appear in matching pairs
     // * with calls to {@link #getWritableTile(int, int) getWritableTile(int, int)}.  Any other leads
     // * to undefined results.  All registered <code>TileObservers</code>
     // * are notified when a tile goes from having one writer to having no
     // * writers.
     // * @param tileX the x index of the tile
     // * @param tileY the y index of the tile
     // */
    // public void releaseWritableTile(int tileX, int tileY) { }
}
