/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.graphics;

import java.awt.Color;

/** 
 * A class that allows a very simple, interoperable form of 
 * compositing.  This is achieved by setting an alpha value for alpha
 * blending on a color.  Higher alpha values indicate greater opacity
 * of the color; lower values indicate greater transparency.  The
 * alpha value will be respected by all instances of java.awt.Graphics 
 * given to applications.  <p>
 *
 * In the final composition between the graphics and video, the
 * underlying video stream will be alpha-blended with the AWT graphics
 * plane using that pixel's alpha value by default, i.e.  <em>source
 * over</em> compositing will be used between the video plane and the
 * AWT graphics plane by default.  This behavior can be changed using
 * other APIs, possibly APIs defined outside of Java TV.  <p>
 *
 * This API supports up to 256 levels of alpha blending.  However, an
 * individual graphics system may support fewer levels.  Such systems
 * will round the alpha value specified in an <code>AlphaColor</code>
 * constructor to some nearest value when the <code>AlphaColor</code>
 * instance is used, e.g. rounding to the nearest implemented alpha
 * value. <p>
 *
 * Systems on which alpha blending is not supported will interpret 
 * alpha values other than 255 as if they were 255  (opaque)
 * instead.<p>
 *
 * The actual color used in rendering will depend on finding the 
 * best match given the color space available for a given output 
 * device.<p>
 *
 * Within the AWT graphics plane, the actual compositing done will be 
 * platform-dependent.
 *
 *
 * @see #getAlpha
 *
 * @version 1.13, 10/24/05
 */
public class AlphaColor extends Color
{

    /** 
     * Creates an sRGB color with the specified red, green, blue, and 
     * alpha values in the range [0.0 - 1.0]. 
     *
     * @param r The red component.
     * @param g The green component.
     * @param b The blue component.
     * @param a The alpha component.
     *
     * @throws IllegalArgumentException If any of the input parameters
     * are outside the range [0.0 - 1.0].
     *
     * @see java.awt.Color#getRed()
     * @see java.awt.Color#getGreen()
     * @see java.awt.Color#getBlue()
     * @see #getAlpha()
     * @see #getRGB()
     */
    public AlphaColor(float r, float g, float b, float a) { 
        super(r, g, b, a);
    }

    /** 
     * Creates an sRGB color with the specified red, green, blue, and 
     * alpha values in the range 0-255, inclusive. 
     *
     * @param r The red component.
     * @param g The green component.
     * @param b The blue component.
     * @param a The alpha component.
     *
     * @throws IllegalArgumentException If any of the input parameters
     * are outside the range [0 - 255].
     *
     * @see java.awt.Color#getRed()
     * @see java.awt.Color#getGreen()
     * @see java.awt.Color#getBlue()
     * @see #getAlpha()
     * @see #getRGB()
     */
    public AlphaColor(int r, int g, int b, int a) { 
        super(r, g, b, a);    
    }

    /** 
     * Creates an sRGB color with the specified combined RGBA value
     * consisting of the alpha component in bits 24-31, the red
     * component in bits 16-23, the green component in bits 8-15, and
     * the blue component in bits 0-7. If the <code>hasAlpha</code>
     * argument is <code>false</code>, alpha is set to 255.
     *
     * @param argb The combined ARGB components
     * @param hasAlpha <code>true</code> if the alpha bits are to be
     * used, <code>false</code> otherwise.
     *
     * @see java.awt.Color#getRed()
     * @see java.awt.Color#getGreen()
     * @see java.awt.Color#getBlue()
     * @see #getAlpha()
     * @see #getRGB()
     */
    public AlphaColor(int argb, boolean hasAlpha) { 
        super(argb, hasAlpha);    
    }

    /** 
     * Constructs a new <code>AlphaColor</code> using the specified
     * java.awt.Color.  If this color has no alpha value, alpha will
     * be set to 255 (opaque).
     *
     * @param c the color
     */
    public AlphaColor(Color c) { 
        super(c.getRGB());
    }

    /** 
     * Creates a brighter version of this color.
     * The alpha value of the original <code>AlphaColor</code> are preserved.
     * <p>
     * 
     * Although brighter and darker are inverse
     * operations, the results of a series of invocations of these two
     * methods may be inconsistent because of rounding errors.
     *
     * @return A new <code>AlphaColor</code> object
     *
     * @see javax.tv.graphics.AlphaColor#darker()
     */
    public Color brighter() {
        return null;
    }

    /** 
     * Creates a darker version of this color.
     * The alpha value of the original <code>AlphaColor</code> are preserved.
     * <p>
     * 
     * Although brighter and darker are inverse
     * operations, the results of a series of invocations of these two
     * methods may be inconsistent because of rounding errors.
     *
     * @return A new <code>AlphaColor</code> object
     *
     * @see javax.tv.graphics.AlphaColor#brighter()
     */
    public Color darker() {
        return null;
    }
}
