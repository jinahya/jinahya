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


  


package java.awt;

import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.ReplicateScaleFilter;

/** 
 * The abstract class <code>Image</code> is the superclass of all 
 * classes that represent graphical images. The image must be 
 * obtained in a platform-specific manner.
 *
 * @version 	1.33, 01/23/03
 * @author 	Sami Shaio
 * @author 	Arthur van Hoff
 * @since       JDK1.0
 */
public abstract class Image
{
    /** 
     * The <code>UndefinedProperty</code> object should be returned whenever a
     * property which was not defined for a particular image is fetched.
     */
    public static final Object UndefinedProperty = null;

    /** 
     * Use the default image-scaling algorithm.
     * @since JDK1.1
     */
    public static final int SCALE_DEFAULT = 1;

    /** 
     * Choose an image-scaling algorithm that gives higher priority
     * to scaling speed than smoothness of the scaled image.
     * @since JDK1.1
     */
    public static final int SCALE_FAST = 2;

    /** 
     * Choose an image-scaling algorithm that gives higher priority
     * to image smoothness than scaling speed.
     * @since JDK1.1
     */
    public static final int SCALE_SMOOTH = 4;

    /** 
     * Use the image scaling algorithm embodied in the 
     * <code>ReplicateScaleFilter</code> class.  
     * The <code>Image</code> object is free to substitute a different filter 
     * that performs the same algorithm yet integrates more efficiently
     * into the imaging infrastructure supplied by the toolkit.
     * @see        java.awt.image.ReplicateScaleFilter
     * @since      JDK1.1
     */
    public static final int SCALE_REPLICATE = 8;

    /** 
     * Use the Area Averaging image scaling algorithm.  The
     * image object is free to substitute a different filter that
     * performs the same algorithm yet integrates more efficiently
     * into the image infrastructure supplied by the toolkit.
     * @see java.awt.image.AreaAveragingScaleFilter
     * @since JDK1.1
     */
    public static final int SCALE_AREA_AVERAGING = 16;

    /**
     */
    public Image() { }

    /** 
     * Determines the width of the image. If the width is not yet known, 
     * this method returns <code>-1</code> and the specified   
     * <code>ImageObserver</code> object is notified later.
     * @param     observer   an object waiting for the image to be loaded.
     * @return    the width of this image, or <code>-1</code> 
     *                   if the width is not yet known.
     * @see       java.awt.Image#getHeight
     * @see       java.awt.image.ImageObserver
     */
    public abstract int getWidth(ImageObserver observer);

    /** 
     * Determines the height of the image. If the height is not yet known, 
     * this method returns <code>-1</code> and the specified  
     * <code>ImageObserver</code> object is notified later.
     * @param     observer   an object waiting for the image to be loaded.
     * @return    the height of this image, or <code>-1</code> 
     *                   if the height is not yet known.
     * @see       java.awt.Image#getWidth
     * @see       java.awt.image.ImageObserver
     */
    public abstract int getHeight(ImageObserver observer);

    /** 
     * Gets the object that produces the pixels for the image.
     * This method is called by the image filtering classes and by 
     * methods that perform image conversion and scaling.
     * @return     the image producer that produces the pixels 
     *                                  for this image.
     * @see        java.awt.image.ImageProducer
     */
    public abstract ImageProducer getSource();

    /** 
     * Creates a graphics context for drawing to an off-screen image. 
     * This method can only be called for off-screen images. 
     * @return  a graphics context to draw to the off-screen image. 
     * @see     java.awt.Graphics
     * @see     java.awt.Component#createImage(int, int)
     */
    public abstract Graphics getGraphics();

    /** 
     * Gets a property of this image by name. 
     * <p>
     * Individual property names are defined by the various image 
     * formats. If a property is not defined for a particular image, this 
     * method returns the <code>UndefinedProperty</code> object. 
     * <p>
     * If the properties for this image are not yet known, this method 
     * returns <code>null</code>, and the <code>ImageObserver</code> 
     * object is notified later. 
     * <p>
     * The property name <code>"comment"</code> should be used to store 
     * an optional comment which can be presented to the application as a 
     * description of the image, its source, or its author. 
     * @param       name   a property name.
     * @param       observer   an object waiting for this image to be loaded.
     * @return      the value of the named property.
     * @see         java.awt.image.ImageObserver
     * @see         java.awt.Image#UndefinedProperty
     */
    public abstract Object getProperty(String name, ImageObserver observer);

    /** 
     * Creates a scaled version of this image.
     * A new <code>Image</code> object is returned which will render 
     * the image at the specified <code>width</code> and 
     * <code>height</code> by default.  The new <code>Image</code> object
     * may be loaded asynchronously even if the original source image
     * has already been loaded completely.  If either the <code>width</code> 
     * or <code>height</code> is a negative number then a value is 
     * substituted to maintain the aspect ratio of the original image 
     * dimensions.
     * @param width the width to which to scale the image.
     * @param height the height to which to scale the image.
     * @param hints flags to indicate the type of algorithm to use
     * for image resampling.
     * @return     a scaled version of the image.
     * @see        java.awt.Image#SCALE_DEFAULT
     * @see        java.awt.Image#SCALE_FAST 
     * @see        java.awt.Image#SCALE_SMOOTH
     * @see        java.awt.Image#SCALE_REPLICATE
     * @see        java.awt.Image#SCALE_AREA_AVERAGING
     * @since      JDK1.1
     */
    public Image getScaledInstance(int width, int height, int hints) { return null; }

    /** 
     * Flushes all resources being used by this Image object.  This
     * includes any pixel data that is being cached for rendering to
     * the screen as well as any system resources that are being used
     * to store data or pixels for the image.  The image is reset to
     * a state similar to when it was first created so that if it is
     * again rendered, the image data will have to be recreated or
     * fetched again from its source.
     * <p>
     * This method always leaves the image in a state such that it can 
     * be reconstructed.  This means the method applies only to cached 
     * or other secondary representations of images such as those that 
     * have been generated from an <tt>ImageProducer</tt> (read from a 
     * file, for example). It does nothing for off-screen images that 
     * have only one copy of their data.
     */
    public abstract void flush();
}
