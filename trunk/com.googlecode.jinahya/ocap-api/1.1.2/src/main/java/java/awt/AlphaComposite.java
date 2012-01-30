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

import java.awt.image.ColorModel;
// import sun.java2d.SunCompositeContext;

/** 
 * This <code>AlphaComposite</code> class implements the basic alpha 
 * compositing rules for combining source and destination pixels to achieve
 * blending and transparency effects with graphics and images.
 * The rules implemented by this class are the set of Porter-Duff
 * rules described in
 * T. Porter and T. Duff, "Compositing Digital Images", SIGGRAPH 84,
 * 253-259.
 *<p>
 * If any input does not have an alpha channel, an alpha value of 1.0, 
 * which is completely opaque, is assumed for all pixels.  A constant 
 * alpha value can also be specified to be multiplied with the alpha 
 * value of the source pixels.
 * <p>
 * The following abbreviations are used in the description of the rules:
 * <ul>
 * <li>Cs = one of the color components of the source pixel.
 * <li>Cd = one of the color components of the destination pixel.
 * <li>As = alpha component of the source pixel.
 * <li>Ad = alpha component of the destination pixel.
 * <li>Fs = fraction of the source pixel that contributes to the output.
 * <li>Fd = fraction of the input destination pixel that contributes to the
 * output.
 * </ul>
 *<p>
 * The color and alpha components produced by the compositing operation are
 * calculated as follows:
 *<pre>
 * 	Cd = Cs*Fs + Cd*Fd
 * 	Ad = As*Fs + Ad*Fd
 *</pre>
 * where Fs and Fd are specified by each rule.  The above equations assume
 * that both source and destination pixels have the color components
 * premultiplied by the alpha component.  Similarly, the equations expressed
 * in the definitions of compositing rules below assume premultiplied alpha.
 *<p>
 *
<!-- PBP/PP -->
<!-- Text deleted here. -->
 *
 * The alpha resulting from the compositing operation is stored in the
 * destination if the destination has an alpha channel. Otherwise, the
 * resulting color is divided by the resulting alpha before being
 * stored in the destination and the alpha is discarded. If the alpha
 * value is 0.0, the color values are set to 0.0.
 * <p>
 * <!- PBP/PP ->
 * <!- PBP/PP 4839443 ->
 *
 * <em>If either source or destination pixels have color components
 * not premultiplied by the alpha component, appropriate conversions
 * are performed before and after the compositing operation.</em>
 *
<!-- PBP/PP -->
 * <p>
 * <a name="restrictions">
 * <h4>Restrictions</h4>
 * <em>
 * Implementations of alpha compositing in this Profile exhibit
 * certain restrictions, specifically:
 * <ul>
 * <li> An implementation may approximate the result of the
 * <code>SRC_OVER</code> rule in the case that <code>0.0 < As < 1.0</code>.
 * See:
 * <ul>
 * <li> {@link #SRC_OVER}
 * </ul>
 * </ul>
 * For more information, see <a href="../../doc-files/properties.html">Profile-specific properties</a>.
 * </em>
 *
 * @see Composite
<!-- PBP/PP -->

 * @version 10 Feb 1997
 */
public final class AlphaComposite implements Composite
{
    /** 
     * Porter-Duff Clear rule.
     * Both the color and the alpha of the destination are cleared.
     * Neither the source nor the destination is used as input.
     *<p>
     * Fs = 0 and Fd = 0, thus:
     *<pre>
     * 	Cd = 0
     * 	Ad = 0
     *</pre>
     * 
     */
    public static final int CLEAR = 1;

    /** 
     * Porter-Duff Source rule.
     * The source is copied to the destination.
     * The destination is not used as input.
     *<p>
     * Fs = 1 and Fd = 0, thus:
     *<pre>
     * 	Cd = Cs
     * 	Ad = As
     *</pre>
     */
    public static final int SRC = 2;

    // /** 
     // * Porter-Duff Destination rule.
     // * The destination is left untouched.
     // *<p>
     // * Fs = 0 and Fd = 1, thus:
     // *<pre>
     // * 	Cd = Cd
     // * 	Ad = Ad
     // *</pre>
     // * @since 1.4
     // */
    // public static final int DST = 0;


// PBP/PP

    /** 
     * Porter-Duff Source Over Destination rule.
     * The source is composited over the destination.
     *<p>
     * Fs = 1 and Fd = (1-As), thus:
     *<pre>
     * 	Cd = Cs + Cd*(1-As)
     * 	Ad = As + Ad*(1-As)
     *</pre>
     *
     * <em>Note: This operation is subject to
     * <a href="#restrictions">restriction</a>
     * in this Profile.</em>
     */
    public static final int SRC_OVER = 3;

    // /** 
     // * Porter-Duff Destination Over Source rule.
     // * The destination is composited over the source and
     // * the result replaces the destination.
     // *<p>
     // * Fs = (1-Ad) and Fd = 1, thus:
     // *<pre>
     // * 	Cd = Cs*(1-Ad) + Cd
     // * 	Ad = As*(1-Ad) + Ad
     // *</pre>
     // */
    // public static final int DST_OVER = 0;

    // /** 
     // * Porter-Duff Source In Destination rule.
     // * The part of the source lying inside of the destination replaces
     // * the destination.
     // *<p>
     // * Fs = Ad and Fd = 0, thus:
     // *<pre>
     // * 	Cd = Cs*Ad
     // * 	Ad = As*Ad
     // *</pre>
     // */
    // public static final int SRC_IN = 0;

    // /** 
     // * Porter-Duff Destination In Source rule.
     // * The part of the destination lying inside of the source
     // * replaces the destination.
     // *<p>
     // * Fs = 0 and Fd = As, thus:
     // *<pre>
     // * 	Cd = Cd*As
     // * 	Ad = Ad*As
     // *</pre>
     // */
    // public static final int DST_IN = 0;

    // /** 
     // * Porter-Duff Source Held Out By Destination rule.
     // * The part of the source lying outside of the destination
     // * replaces the destination.
     // *<p>
     // * Fs = (1-Ad) and Fd = 0, thus:
     // *<pre>
     // * 	Cd = Cs*(1-Ad)
     // * 	Ad = As*(1-Ad)
     // *</pre>
     // */
    // public static final int SRC_OUT = 0;

    // /** 
     // * Porter-Duff Destination Held Out By Source rule.
     // * The part of the destination lying outside of the source
     // * replaces the destination.
     // *<p>
     // * Fs = 0 and Fd = (1-As), thus:
     // *<pre>
     // * 	Cd = Cd*(1-As)
     // * 	Ad = Ad*(1-As)
     // *</pre>
     // */
    // public static final int DST_OUT = 0;

    // /** 
     // * Porter-Duff Source Atop Destination rule.
     // * The part of the source lying inside of the destination
     // * is composited onto the destination.
     // *<p>
     // * Fs = Ad and Fd = (1-As), thus:
     // *<pre>
     // * 	Cd = Cs*Ad + Cd*(1-As)
     // * 	Ad = As*Ad + Ad*(1-As) = Ad
     // *</pre>
     // * @since 1.4
     // */
    // public static final int SRC_ATOP = 0;

    // /** 
     // * Porter-Duff Destination Atop Source rule.
     // * The part of the destination lying inside of the source
     // * is composited over the source and replaces the destination.
     // *<p>
     // * Fs = (1-Ad) and Fd = As, thus:
     // *<pre>
     // * 	Cd = Cs*(1-Ad) + Cd*As
     // * 	Ad = As*(1-Ad) + Ad*As = As
     // *</pre>
     // * @since 1.4
     // */
    // public static final int DST_ATOP = 0;

    // /** 
     // * Porter-Duff Source Xor Destination rule.
     // * The part of the source that lies outside of the destination
     // * is combined with the part of the destination that lies outside
     // * of the source.
     // *<p>
     // * Fs = (1-Ad) and Fd = (1-As), thus:
     // *<pre>
     // * 	Cd = Cs*(1-Ad) + Cd*(1-As)
     // * 	Ad = As*(1-Ad) + Ad*(1-As)
     // *</pre>
     // * @since 1.4
     // */
    // public static final int XOR = 0;

    /** 
     * <code>AlphaComposite</code> object that implements the opaque CLEAR rule
     * with an alpha of 1.0f.
     * @see #CLEAR
     */
    public static final AlphaComposite Clear = null;

    /** 
     * <code>AlphaComposite</code> object that implements the opaque SRC rule
     * with an alpha of 1.0f.
     * @see #SRC
     */
    public static final AlphaComposite Src = null;
// 
    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque DST rule
     // * with an alpha of 1.0f.
     // * @see #DST
     // * @since 1.4
     // */
    // public static final AlphaComposite Dst = null;

    /** 
     * <code>AlphaComposite</code> object that implements the opaque SRC_OVER rule
     * with an alpha of 1.0f.
     * @see #SRC_OVER
     */
    public static final AlphaComposite SrcOver = null;

    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque DST_OVER rule
     // * with an alpha of 1.0f.
     // * @see #DST_OVER
     // */
    // public static final AlphaComposite DstOver = null;
// 
    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque SRC_IN rule
     // * with an alpha of 1.0f.
     // * @see #SRC_IN
     // */
    // public static final AlphaComposite SrcIn = null;
// 
    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque DST_IN rule
     // * with an alpha of 1.0f.
     // * @see #DST_IN
     // */
    // public static final AlphaComposite DstIn = null;

    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque SRC_OUT rule
     // * with an alpha of 1.0f.
     // * @see #SRC_OUT
     // */
    // public static final AlphaComposite SrcOut = null;
// 
    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque DST_OUT rule
     // * with an alpha of 1.0f.
     // * @see #DST_OUT
     // */
    // public static final AlphaComposite DstOut = null;
// 
    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque SRC_ATOP rule
     // * with an alpha of 1.0f.
     // * @see #SRC_ATOP
     // * @since 1.4
     // */
    // public static final AlphaComposite SrcAtop = null;
// 
    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque DST_ATOP rule
     // * with an alpha of 1.0f.
     // * @see #DST_ATOP
     // * @since 1.4
     // */
    // public static final AlphaComposite DstAtop = null;
// 
    // /** 
     // * <code>AlphaComposite</code> object that implements the opaque XOR rule
     // * with an alpha of 1.0f.
     // * @see #XOR
     // * @since 1.4
     // */
    // public static final AlphaComposite Xor = null;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private AlphaComposite() { }

// PBP/PP
    /** 
     * Creates an <code>AlphaComposite</code> object with the specified rule.
     * @param rule the compositing rule
     * @throws IllegalArgumentException if <code>rule</code> is not one of 
     *         the following:  {@link #CLEAR}, {@link #SRC},
     * 
     *         <em>or</em> {@link #SRC_OVER}
     * 
     */
    public static AlphaComposite getInstance(int rule) { return null;}

// PBP/PP
    /** 
     * Creates an <code>AlphaComposite</code> object with the specified rule and
     * the constant alpha to multiply with the alpha of the source.
     * The source is multiplied with the specified alpha before being composited
     * with the destination.
     * @param rule the compositing rule
     * @param alpha the constant alpha to be multiplied with the alpha of
     * the source. <code>alpha</code> must be a floating point number in the
     * inclusive range [0.0,&nbsp;1.0]. 
     * @throws IllegalArgumentException if 
     *         <code>alpha</code> is less than 0.0 or greater than 1.0, or if
     *         <code>rule</code> is not one of 
     *         the following:  {@link #CLEAR}, {@link #SRC},
     * 
     *         <em>or</em> {@link #SRC_OVER}
     * 
     */
    public static AlphaComposite getInstance(int rule, float alpha) {return null; }

    // /** 
     // * Creates a context for the compositing operation.
     // * The context contains state that is used in performing
     // * the compositing operation.
     // * @param srcColorModel  the {@link ColorModel} of the source
     // * @param dstColorModel  the <code>ColorModel</code> of the destination
     // * @return the <code>CompositeContext</code> object to be used to perform
     // * compositing operations.
     // */
    // public CompositeContext createContext(ColorModel srcColorModel, ColorModel
        // dstColorModel, RenderingHints hints)
    // { }

    /** 
     * Returns the alpha value of this <code>AlphaComposite</code>.  If this
     * <code>AlphaComposite</code> does not have an alpha value, 1.0 is returned.
     * @return the alpha value of this <code>AlphaComposite</code>.
     */
    public float getAlpha() { return 0; }

    /** 
     * Returns the compositing rule of this <code>AlphaComposite</code>.
     * @return the compositing rule of this <code>AlphaComposite</code>.
     */
    public int getRule() { return 0; }

    /** 
     * Returns the hashcode for this composite.
     * @return      a hash code for this composite.
     */
    public int hashCode() { return 0; }

    /** 
     * Determines whether the specified object is equal to this 
     * <code>AlphaComposite</code>.
     * <p>
     * The result is <code>true</code> if and only if
     * the argument is not <code>null</code> and is an
     * <code>AlphaComposite</code> object that has the same
     * compositing rule and alpha value as this object.
     *
     * @param obj the <code>Object</code> to test for equality
     * @return <code>true</code> if <code>obj</code> equals this
     * <code>AlphaComposite</code>; <code>false</code> otherwise.
     */
    public boolean equals(Object obj) { return false; }
}
