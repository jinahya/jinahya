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

// import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.VolatileImage;

/** 
 * The <code>GraphicsConfiguration</code> class describes the
 * characteristics of a graphics destination such as a printer or monitor.
 * There can be many <code>GraphicsConfiguration</code> objects associated
 * with a single graphics device, representing different drawing modes or
 * capabilities.  The corresponding native structure will vary from platform
 * to platform.  For example, on X11 windowing systems,
 * each visual is a different <code>GraphicsConfiguration</code>.  
 * On Microsoft Windows, <code>GraphicsConfiguration</code>s represent 
 * PixelFormats available in the current resolution and color depth.
 * <p>
 * In a virtual device multi-screen environment in which the desktop
 * area could span multiple physical screen devices, the bounds of the 
 * <code>GraphicsConfiguration</code> objects are relative to the
 * virtual coordinate system.  When setting the location of a 
 * component, use {@link #getBounds() getBounds} to get the bounds of 
 * the desired <code>GraphicsConfiguration</code> and offset the location
 * with the coordinates of the <code>GraphicsConfiguration</code>,
 * as the following code sample illustrates:
 * </p>
 *
 * <pre>
 *      Frame f = new Frame(gc);  // where gc is a GraphicsConfiguration
 *      Rectangle bounds = gc.getBounds();
 *      f.setLocation(10 + bounds.x, 10 + bounds.y); </pre>
 *
 * <p>
 * To determine if your environment is a virtual device
 * environment, call <code>getBounds</code> on all of the 
 * <code>GraphicsConfiguration</code> objects in your system.  If 
 * any of the origins of the returned bounds is not (0,&nbsp;0),
 * your environment is a virtual device environment.
 *
 * <p>
 * You can also use <code>getBounds</code> to determine the bounds
 * of the virtual device.  To do this, first call <code>getBounds</code> on all
 * of the <code>GraphicsConfiguration</code> objects in your
 * system.  Then calculate the union of all of the bounds returned
 * from the calls to <code>getBounds</code>.  The union is the
 * bounds of the virtual device.  The following code sample
 * calculates the bounds of the virtual device.
 *
 * <pre>
 *      Rectangle virtualBounds = new Rectangle();
 *      GraphicsEnvironment ge = GraphicsEnvironment.
 *              getLocalGraphicsEnvironment();
 *      GraphicsDevice[] gs =
 *              ge.getScreenDevices();
 *      for (int j = 0; j < gs.length; j++) { 
 *          GraphicsDevice gd = gs[j];
 *          GraphicsConfiguration[] gc =
 *              gd.getConfigurations();
 *          for (int i=0; i < gc.length; i++) {
 *              virtualBounds =
 *                  virtualBounds.union(gc[i].getBounds());
 *          }
 *      } </pre>                   
 *
 * @see Window
 * @see Frame
 * @see GraphicsEnvironment
 * @see GraphicsDevice
 */
public abstract class GraphicsConfiguration
{

  // PBP/PP
    /** 
     * This is an abstract class that cannot be instantiated directly.
     * Instances must be obtained from a suitable factory or query method.
     *
     * @see GraphicsDevice#getConfigurations
     * @see GraphicsDevice#getDefaultConfiguration

     * @see Graphics2D#getDeviceConfiguration
     */
    protected GraphicsConfiguration() { }

    /** 
     * Returns the {@link GraphicsDevice} associated with this
     * <code>GraphicsConfiguration</code>.
     * @return a <code>GraphicsDevice</code> object that is 
     * associated with this <code>GraphicsConfiguration</code>.
     */
    public abstract GraphicsDevice getDevice();

    /** 
     * Returns a {@link BufferedImage} with a data layout and color model
     * compatible with this <code>GraphicsConfiguration</code>.  This
     * method has nothing to do with memory-mapping
     * a device.  The returned <code>BufferedImage</code> has
     * a layout and color model that is closest to this native device
     * configuration and can therefore be optimally blitted to this
     * device.
     * @param width the width of the returned <code>BufferedImage</code>
     * @param height the height of the returned <code>BufferedImage</code>
     * @return a <code>BufferedImage</code> whose data layout and color
     * model is compatible with this <code>GraphicsConfiguration</code>.
     */
     public abstract BufferedImage createCompatibleImage(int width, int height);

     /** 
      * Returns a {@link VolatileImage} with a data layout and color model
      * compatible with this <code>GraphicsConfiguration</code>.  
      * The returned <code>VolatileImage</code> 
      * may have data that is stored optimally for the underlying graphics 
      * device and may therefore benefit from platform-specific rendering 
      * acceleration.
      * @param width the width of the returned <code>VolatileImage</code>
      * @param height the height of the returned <code>VolatileImage</code>
      * @return a <code>VolatileImage</code> whose data layout and color
      * model is compatible with this <code>GraphicsConfiguration</code>.
      * @see Component#createVolatileImage(int, int)
      */
     public abstract VolatileImage createCompatibleVolatileImage(int width, int
         height);

     /** 
      * Returns a {@link VolatileImage} with a data layout and color model 
      * compatible with this <code>GraphicsConfiguration</code>, using 
      * the specified image capabilities. 
      * The returned <code>VolatileImage</code> has 
      * a layout and color model that is closest to this native device 
      * configuration and can therefore be optimally blitted to this 
      * device. 
      * @return a <code>VolatileImage</code> whose data layout and color 
      * model is compatible with this <code>GraphicsConfiguration</code>. 
      * @param width the width of the returned <code>VolatileImage</code> 
      * @param height the height of the returned <code>VolatileImage</code> 
      * @param caps the image capabilities 
      * @exception AWTException if the supplied image capabilities could not 
      * be met by this graphics configuration 
      * @since 1.4 
      */
     public VolatileImage createCompatibleVolatileImage(int width, int height,
         ImageCapabilities caps) throws AWTException
     { return null; }

    // /** 
     // * Returns a <code>BufferedImage</code> that supports the specified
     // * transparency and has a data layout and color model
     // * compatible with this <code>GraphicsConfiguration</code>.  This
     // * method has nothing to do with memory-mapping
     // * a device. The returned <code>BufferedImage</code> has a layout and
     // * color model that can be optimally blitted to a device
     // * with this <code>GraphicsConfiguration</code>.
     // * @param width the width of the returned <code>BufferedImage</code>
     // * @param height the height of the returned <code>BufferedImage</code>
     // * @param transparency the specified transparency mode
     // * @return a <code>BufferedImage</code> whose data layout and color  
     // * model is compatible with this <code>GraphicsConfiguration</code>
     // * and also supports the specified transparency.
     // * @see Transparency#OPAQUE
     // * @see Transparency#BITMASK
     // * @see Transparency#TRANSLUCENT
     // */
    // public abstract BufferedImage createCompatibleImage(int width, int height,
        // int transparency);

    /** 
     * Returns the {@link ColorModel} associated with this 
     * <code>GraphicsConfiguration</code>.
     * @return a <code>ColorModel</code> object that is associated with
     * this <code>GraphicsConfiguration</code>.
     */
    public abstract ColorModel getColorModel();

    // /** 
     // * Returns the <code>ColorModel</code> associated with this
     // * <code>GraphicsConfiguration</code> that supports the specified
     // * transparency.
     // * @param transparency the specified transparency mode
     // * @return a <code>ColorModel</code> object that is associated with
     // * this <code>GraphicsConfiguration</code> and supports the 
     // * specified transparency.
     // */
    // public abstract ColorModel getColorModel(int transparency);

    // /** 
     // * Returns the default {@link AffineTransform} for this 
     // * <code>GraphicsConfiguration</code>. This
     // * <code>AffineTransform</code> is typically the Identity transform
     // * for most normal screens.  The default <code>AffineTransform</code>
     // * maps coordinates onto the device such that 72 user space
     // * coordinate units measure approximately 1 inch in device
     // * space.  The normalizing transform can be used to make
     // * this mapping more exact.  Coordinates in the coordinate space
     // * defined by the default <code>AffineTransform</code> for screen and
     // * printer devices have the origin in the upper left-hand corner of
     // * the target region of the device, with X coordinates
     // * increasing to the right and Y coordinates increasing downwards.
     // * For image buffers not associated with a device, such as those not
     // * created by <code>createCompatibleImage</code>,
     // * this <code>AffineTransform</code> is the Identity transform.
     // * @return the default <code>AffineTransform</code> for this
     // * <code>GraphicsConfiguration</code>.
     // */
    // public abstract AffineTransform getDefaultTransform();

    // /** 
     // * Returns a <code>AffineTransform</code> that can be concatenated
     // * with the default <code>AffineTransform</code>
     // * of a <code>GraphicsConfiguration</code> so that 72 units in user
     // * space equals 1 inch in device space.  
     // * <p>
     // * For a particular {@link Graphics2D}, g, one
     // * can reset the transformation to create
     // * such a mapping by using the following pseudocode:
     // * <pre>
     // *      GraphicsConfiguration gc = g.getGraphicsConfiguration();
     // *
     // *      g.setTransform(gc.getDefaultTransform());
     // *      g.transform(gc.getNormalizingTransform());
     // * </pre>
     // * Note that sometimes this <code>AffineTransform</code> is identity,
     // * such as for printers or metafile output, and that this 
     // * <code>AffineTransform</code> is only as accurate as the information
     // * supplied by the underlying system.  For image buffers not
     // * associated with a device, such as those not created by
     // * <code>createCompatibleImage</code>, this
     // * <code>AffineTransform</code> is the Identity transform
     // * since there is no valid distance measurement.
     // * @return an <code>AffineTransform</code> to concatenate to the
     // * default <code>AffineTransform</code> so that 72 units in user
     // * space is mapped to 1 inch in device space.
     // */
    // public abstract AffineTransform getNormalizingTransform();

    /** 
     * Returns the bounds of the <code>GraphicsConfiguration</code>
     * in the device coordinates. In a multi-screen environment
     * with a virtual device, the bounds can have negative X
     * or Y origins.
     * @return the bounds of the area covered by this
     * <code>GraphicsConfiguration</code>.
     * @since 1.3
     */
    public abstract Rectangle getBounds();
// 
//      /** 
//       * Returns the buffering capabilities of this
//       * <code>GraphicsConfiguration</code>.
//       * @return the buffering capabilities of this graphics
//       * configuration object
//       * @since 1.4
//       */
//      public BufferCapabilities getBufferCapabilities() { return null; }
// 
     /** 
      * Returns the image capabilities of this
      * <code>GraphicsConfiguration</code>.
      * @return the image capabilities of this graphics
      * configuration object
      * @since 1.4
      */
     public ImageCapabilities getImageCapabilities() { return null; }
}
