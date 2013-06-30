package org.dvb.ui;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.*;
import java.lang.*;
import java.util.*;



/**
 * The <code>DVBGraphics</code> class is a adapter class to support alpha compositing in an MHP device. Most methods directly delegate to java.awt.Graphics other methods could delegate to the appropriate methods in java.awt.Graphics2D where available or could be implemented in native code. In implementations where the class java.awt.Graphics2D is visible to MHP applications, org.dvb.ui.DVBGraphics inherits from java.awt.Graphics2D. Otherwise, org.dvb.ui.DVBGraphics  inherits from java.awt.Graphics. <b>In MHP devices all Graphics Objects are DVBGraphics objects.</b> Thus one can get a DVBGraphics by casting a given Graphics object.  The normal compositing rule used is <b>DVBAlphaComposite.SRC_OVER.</b> Note that the default rule of SRC_OVER may not give the highest performance. Under many circumstances, applications will find that the SRC rule will give higher performance. The intersection between setDVBCompsite in this class and the <code>setPaintMode</code> and <code>setXORMode</code> methods inherited from java.awt.Graphics  shall be as follows. <ul> <li>Calling <code>setPaintMode</code> on an instance of this class shall be equivalent to calling <code>setDVBComposite(DVBAlphaComposite.SrcOver)</code>.  <li>Calling <code>setXORMode</code> on an instance of this class shall be equivalent to calling  <code>setDVBComposite</code> with a special and implementation dependent DVBAlphaComposite object  which implements the semantics specified for this method in the parent class.  <li>Calling <code>getDVBComposite</code> when <code>setXORMode</code> is the last DVBComposite set shall return this implementation dependent object. Conformant MHP applications shall not do anything with or to this object including calling any methods on it.  <li>The present document does not tighten, refine or detail the definition of the  <code>setXORMode</code> beyond what is specified for the parent class.  </ul><p> Note: Implementations of XOR mode may change colours with alpha to without and vice versa (reversibly). 
 * @see java.awt.Graphics
 * @since        MHP1.0
 */
public abstract class DVBGraphics extends Graphics2D {

    

    /**
     * Constructs a new <code>DVBGraphics</code> object. 
     * This constructor is the default constructor for a graphics
     * context. 
     * <p>
     * Since <code>DVBGraphics</code> is an abstract class, applications 
     * cannot call this constructor directly. DVBGraphics contexts are
     * obtained from other DVBGraphics contexts or are created by casting java.awt.Graphics to DVBGraphics.
      * @see        java.awt.Graphics#create()
     * @see        java.awt.Component#getGraphics
     * @since MHP 1.0 
     */
    protected DVBGraphics() {
    }
    /**
    *  Returns all available Porter-Duff Rules for this specific Graphics context. E.g. a devices
    *  could support the SRC_OVER rule when using a destination which does not have Alpha or where the
    *  alpha is null, while this rule is not available when drawing on a graphic context where the destination
    *  has alpha.
    *  Which rules are supported for the different graphics objects is defined in the Minimum Platform Capabilities of
    *  the MHP spec.
    *
    *
    * @return all available Porter-Duff Rules for this specific Graphics context.
    * @since MHP 1.0
    */
    public abstract int[] getAvailableCompositeRules();

    /**
     * Returns the best match for the specified Color as a DVBColor, in a
      * device-dependent manner, as constrained by the MHP graphics reference model.
     *
     * @param c the specified Color.
     * @return the best DVBColor match for the specified Color.
     * @since MHP 1.0    
     */
    public DVBColor getBestColorMatch(java.awt.Color c)
    {

        return null;
    }
    /**
     * Gets this graphics context's current color. This will return a DVBColor cast to java.awt.Color.
     * @return    this graphics context's current color.
     * @see       DVBColor
     * @see       java.awt.Color
     * @see       #setColor
     * @since MHP 1.0 
     */
    public abstract java.awt.Color getColor();
     /**
     * Returns the current <code>DVBAlphaComposite</code> in the
     * <code>DVBGraphics</code> context.
     * This method could delegate to a java.awt.Graphics2D object where available
     * @return the current <code>DVBGraphics</code> <code>DVBAlphaComposite</code>,
     *              which defines a compositing style.
     * @see #setDVBComposite
     * @since MHP 1.0
     */
    public abstract DVBAlphaComposite getDVBComposite();
    /**
     * Returns the Sample Model (DVBBufferedImage.TYPE_BASE, DVBBufferedImage.TYPE_ADVANCED)
     * which is used in the on/off screen buffer this graphics object draws into.
     * @return the type of the Sample Model
     * @see org.dvb.ui.DVBBufferedImage
     * @since MHP 1.0
     */
public int getType() {
    return 0;
}
    /**
     * Sets this graphics context's current color to the specified 
     * color. All subsequent graphics operations using this graphics 
     * context use this specified color.
     * Note that color c can be a DVBColor
     * @param     c   the new rendering color.
     * @see       java.awt.Color
     * @see       DVBColor
     * @see       org.dvb.ui.DVBGraphics#getColor
     * @since MHP 1.0
     *
     */
    public abstract void setColor(java.awt.Color c);
     /**
     * Sets the <code>DVBAlphaComposite</code> for the <code>DVBGraphics</code> context.
     * The <code>DVBAlphaComposite</code> is used in all drawing methods such as
     * <code>drawImage</code>, <code>drawString</code>, <code>draw</code>,
     * and <code>fill</code>. It specifies how new pixels are to be combined
     * with the existing pixels on the graphics device during the rendering
     * process.
     * <p> This method could delegate to a Graphics2D object or to an native implementation
     * @param comp the <code>DVBAlphaComposite</code> object to be used for rendering
     * @throws UnsupportedDrawingOperationException when the requested Porter-Duff rule is not supported by this graphics context
     * @see java.awt.Graphics#setXORMode
     * @see java.awt.Graphics#setPaintMode
     * @see org.dvb.ui.DVBAlphaComposite
     * @since MHP 1.0
     */
    public abstract void setDVBComposite(DVBAlphaComposite comp) throws UnsupportedDrawingOperationException;

    /**
     * Returns a <code>String</code> object representing this 
     *                        <code>DVBGraphics</code> object's value.
     * @return       a string representation of this graphics context.
     * @since MHP 1.0
     */
    public String toString() {    
    return getClass().getName() + "[font=" + getFont() + ",color=" + getColor() + "]";
    }
}

