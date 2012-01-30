/*
 * @(#)AlphaColor.java	1.11 00/09/22
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
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
 * @version 1.11, 09/22/00
 */

public class AlphaColor extends java.awt.Color {

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
    	super(0f,0f,0f);
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
	super(0f,0f,0f); 
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
	super(0);
    }

    /**
     * Constructs a new <code>AlphaColor</code> using the specified
     * java.awt.Color.  If this color has no alpha value, alpha will
     * be set to 255 (opaque).
     *
     * @param c the color
     */
    public AlphaColor(java.awt.Color c) {
	super(0);
    }

    /**
     * Creates a brighter version of this color.  <p>
     * 
     * Although brighter and darker are inverse
     * operations, the results of a series of invocations of these two
     * methods may be inconsistent because of rounding errors.
     *
     * @return A new <code>AlphaColor</code> object
     *
     * @see javax.tv.graphics.AlphaColor#darker()
     */
    public java.awt.Color brighter() { 
    	return null;
    }

    /**
     * Creates a darker version of this color.  <p>
     * 
     * Although brighter and darker are inverse
     * operations, the results of a series of invocations of these two
     * methods may be inconsistent because of rounding errors.
     *
     * @return A new <code>AlphaColor</code> object
     *
     * @see javax.tv.graphics.AlphaColor#brighter()
     */
    public java.awt.Color darker() {
    	return null; 
    }

    /**
     * Determines whether another object is equal to this 
     * <code>AlphaColor</code>.  <p>
     *
     * The result is <code>true</code> if and only if the argument is not 
     * <code>null</code> and is a <code>AlphaColor</code> object that has 
     * the same red, green, blue and alpha values as this object. 
     *

     * @param obj The object to test for equality with this
     * <code>AlphaColor</code>
     * 
     * @return <code>true</code> if the objects are the same;
     * <code>false</code> otherwise.
     */
    public boolean equals(Object obj) { 
       return false; 
    }

    /**
     * Computes the hash code for this color.
     *
     * @return	a hash code for this object.
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Reports the alpha value of this <code>AlphaColor</code> instance.
     * @return The alpha value, in the range 0-255 inclusive.
     *
     * @see #getRGB
     */
    public int getAlpha() { 
       return 0; 
    }

    /**
     * Returns the RGB value representing the color in the default sRGB 
     * ColorModel. (Bits 24-31 are alpha, 16-23 are red, 8-15 are green, 
     * 0-7 are blue).
     *
     * @see java.awt.image.ColorModel#getRGBdefault()
     * @see java.awt.Color#getRed()
     * @see java.awt.Color#getGreen()
     * @see java.awt.Color#getBlue()
     */
    public int getRGB() { 
       return 0; 
    }

    /**
     * Creates a string that represents this <code>AlphaColor</code>.
     *
     * @return a representation of this color as a String object.
     */
    public String toString() { 
        return null;
    }

    private void testColorValue(int r, int g, int b, int a) {
    }  

    private void testColorValue(float r, float g, float b, float a) {
   }
}
