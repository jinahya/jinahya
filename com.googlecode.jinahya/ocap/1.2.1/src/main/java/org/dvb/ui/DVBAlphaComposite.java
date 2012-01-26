package org.dvb.ui;

import java.awt.*;

/**
 * This <code>DVBAlphaComposite</code> class implements the basic alpha
 * compositing rules for combining source and destination pixels to achieve
 * blending and transparency effects with graphics, images and video.
 * The rules implemented by this class are a subset of the Porter-Duff
 * rules described in
 * T. Porter and T. Duff, "Compositing Digital Images", SIGGRAPH 84,
 * 253-259.
 *<p>
 * If any input does not have an alpha channel, an alpha value of 1,0, 
 * which is completely opaque, is assumed for all pixels. A constant 
 * alpha value can also be specified to be multiplied with the alpha 
 * value of the source pixels.
 * <p>
 * The following abbreviations are used in the description of the rules:
 * <ul>
 * <li>Cs = one of the color components of the source pixel without alpha.
 * <li>cs = color component of a source pixel premultimlied with alpha (cs = As*Ar*Cs)
 * <li>Cd = one of the color components of the destination pixel without alpha.
 * <li>cd = color component of a destination pixel premultimlied with alpha
 * <li>Cn = the new constructed color without alpha.
 * <li>cn = the new constructed color pre-multiplied with alpha
 * <li>As = alpha component of the source pixel.
 * <li>Ad = alpha component of the destination pixel.
 * <li>An = the new alpha after compositing
 * <li>Ar = alpha, specified by getInstance(int Rule, float Ar). Unless otherwise specified Ar = 1,0f
 * <li>Fs = fraction of the source pixel that contributes to the output.
 * <li>Fd = fraction of the input destination pixel that contributes to the
 * output.
 * </ul>
 *<p>
 * The color and alpha components produced by the compositing operation are
 * calculated as follows:
 *<pre>
 * 	cn = (As*Ar)*Cs*Fs + Ad*Cd*Fd
 * 	An = (As*Ar)*Fs + Ad*Fd
 *  Cn = cn/An
 *</pre>
 * where Fs and Fd are specified by each rule. 
 *<p>
 * The alpha resulting from the compositing operation is stored
 * in the destination if the destination has an alpha channel.
 * Otherwise, the resulting color is divided by the resulting
 * alpha before being stored in the destination and the alpha is discarded.
 * If the alpha value is 0,0, the color values are set to 0,0.
 * @version 10 Feb 1997
 * @see java.awt.AlphaComposite
 */


public final class DVBAlphaComposite {
	/**
	 * Porter-Duff Clear rule.
	 * Both the color and the alpha of the destination are cleared.
	 * Neither the source nor the destination is used as input.
	 *<p>
	 * Fs = 0 and Fd = 0, thus:
	 *<pre>
	 * 	cn = 0
	 * 	An = 0
	 *  Cn = 0
	 *</pre>
	 * <img src="CLEAR.jpg">
	 * <p> <b>Note that this operation is a fast drawing operation</b>
	 * This operation is the same as using a source with alpha= 0 and the SRC rule
	 * 
	 */
	public static final int	CLEAR		= 1;

	/**
	 * Porter-Duff Source rule.
	 * The source is copied to the destination.
	 * The destination is not used as input.
	 *<p>
	 * Fs = 1 and Fd = 0, thus:
	 *<pre>
	 * 	cn = (As*Ar)*Cs
	 * 	An = As*Ar
	 *  Cn = Cs
	 *</pre>
	 * <img src="SRC.jpg">
	 * <p> <b>Note that this is a fast drawing routine</b>
	 */
	public static final int	SRC		= 2;

	/**
	 * Porter-Duff Source Over Destination rule.
	 * The source is composited over the destination.
	 *<p>
	 * Fs = 1 and Fd = (1-(As*Ar)), thus:
	 *<pre>
	 * 	cn = (As*Ar)*Cs + Ad*Cd*(1-(As*Ar))
	 * 	An = (As*Ar) + Ad*(1-(As*Ar))
	 *</pre>
	 * <img src="SRC_OVER.jpg">
	 * <p> <b>Note that this can be a very slow drawing operation</b>
	 */
	public static final int	SRC_OVER	= 3;

	/**
	 * Porter-Duff Destination Over Source rule.
	 * The destination is composited over the source and
	 * the result replaces the destination.
	 *<p>
	 * Fs = (1-Ad) and Fd = 1, thus:
	 *<pre>
	 * 	cn = (As*Ar)*Cs*(1-Ad) + Ad*Cd
	 * 	An = (As*Ar)*(1-Ad) + Ad
	 *</pre>
	 * <img src="DST_OVER.jpg">
	 * <p> <b>Note that this can be a very slow drawing operation</b>
	 */
	public static final int	DST_OVER	= 4;

	/**
	 * Porter-Duff Source In Destination rule.
	 * The part of the source lying inside of the destination replaces
	 * the destination.
	 *<p>
	 * Fs = Ad and Fd = 0, thus:
	 *<pre>
	 * 	cn = (As*Ar)*Cs*Ad
	 * 	An = (As*Ar)*Ad
	 *  Cn = Cs
	 *</pre>
	 * <img src="SRC_IN.jpg">
	 * <p> <b>Note that this operation is faster than e.g. SRC_OVER but slower than SRC</b>
	 */
	public static final int	SRC_IN		= 5;

	/**
	 * Porter-Duff Destination In Source rule.
	 * The part of the destination lying inside of the source
	 * replaces the destination.
	 *<p>
	 * Fs = 0 and Fd = (As*Ar), thus:
	 *<pre>
	 * 	cn = Ad*Cd*(As*Ar)
	 * 	An = Ad*(As*Ar)
	 *  Cn = Cd
	 *</pre>
	 * <img src="DST_IN.jpg">
	 * <p> <b>Note that this operation is faster than e.g. SRC_OVER but slower than SRC</b>
	 */
	public static final int	DST_IN		= 6;

	/**
	 * Porter-Duff Source Held Out By Destination rule.
	 * The part of the source lying outside of the destination
	 * replaces the destination.
	 *<p>
	 * Fs = (1-Ad) and Fd = 0, thus:
	 *<pre>
	 * 	cn = (As*Ar)*Cs*(1-Ad)
	 * 	An = (As*Ar)*(1-Ad)
	 *  Cn = Cs
	 *</pre>
	 * <img src="SRC_OUT.jpg">
	 * <p> <b>Note that this operation is faster than e.g. SRC_OVER but slower than SRC</b>
	 */
	public static final int	SRC_OUT		= 7;

	/**
	 * Porter-Duff Destination Held Out By Source rule.
	 * The part of the destination lying outside of the source
	 * replaces the destination.
	 *<p>
	 * Fs = 0 and Fd = (1-(As*Ar)), thus:
	 *<pre>
	 * 	cn = Ad*Cd*(1-(As*Ar))
	 * 	An = Ad*(1-(As*Ar))
	 *  Cn = Cd
	 *</pre>
	 * <img src="DST_OUT.jpg">
	 * <p> <b>Note that this operation is faster than e.g. SRC_OVER but slower than SRC</b>
	 */
	public static final int	DST_OUT		= 8;

	/**
	 * <code>DVBAlphaComposite</code> object that implements the opaque CLEAR rule
	 * with an alpha (Ar) of 1,0f.
	 * @see #CLEAR
	 */
	public static final DVBAlphaComposite Clear	= new DVBAlphaComposite(CLEAR);

	/**
	 * <code>DVBAlphaComposite</code> object that implements the opaque SRC rule
	 * with an alpha (Ar) of 1,0f.
	 * @see #SRC
	 */
	public static final DVBAlphaComposite Src	= new DVBAlphaComposite(SRC);

	/**
	 * <code>DVBAlphaComposite</code> object that implements the opaque SRC_OVER rule
	 * with an alpha (Ar) of 1,0f.
	 * @see #SRC_OVER
	 */
	public static final DVBAlphaComposite SrcOver	= new DVBAlphaComposite(SRC_OVER);

	/**
	 * <code>DVBAlphaComposite</code> object that implements the opaque DST_OVER rule
	 * with an alpha (Ar) of 1,0f.
	 * @see #DST_OVER
	 */
	public static final DVBAlphaComposite DstOver	= new DVBAlphaComposite(DST_OVER);

	/**
	 * <code>DVBAlphaComposite</code> object that implements the opaque SRC_IN rule
	 * with an alpha (Ar) of 1,0f.
	 * @see #SRC_IN
	 */
	public static final DVBAlphaComposite SrcIn	= new DVBAlphaComposite(SRC_IN);

	/**
	 * <code>DVBAlphaComposite</code> object that implements the opaque DST_IN rule
	 * with an alpha (Ar) of 1,0f.
	 * @see #DST_IN
	 */
	public static final DVBAlphaComposite DstIn	= new DVBAlphaComposite(DST_IN);

	/**
	 * <code>DVBAlphaComposite</code> object that implements the opaque SRC_OUT rule
	 * with an alpha (Ar) of 1,0f.
	 * @see #SRC_OUT
	 */
	public static final DVBAlphaComposite SrcOut	= new DVBAlphaComposite(SRC_OUT);

	/**
	 * <code>DVBAlphaComposite</code> object that implements the opaque DST_OUT rule
	 * with an alpha (Ar) of 1,0f.
	 * @see #DST_OUT
	 */
	public static final DVBAlphaComposite DstOut	= new DVBAlphaComposite(DST_OUT);

	private static final int MIN_RULE = CLEAR;
	private static final int MAX_RULE = DST_OUT;

	float extraAlpha;
	int rule;

	private DVBAlphaComposite(int rule) {
	this(rule, 1.0f);
	}
	private DVBAlphaComposite(int rule, float alpha) {
	if (alpha < 0.0f || alpha > 1.0f) {
	    throw new IllegalArgumentException("alpha value out of range");
	}
	if (rule < MIN_RULE || rule > MAX_RULE) {
	    throw new IllegalArgumentException("unknown composite rule");
	}
	this.rule = rule;
	this.extraAlpha = alpha;
	}
	/**
	 * Tests if the specified {@link Object} is equal to this 
	 * <code>DVBAlphaComposite</code> object.
	 * @param obj the <code>Object</code> to test for equality
	 * @return <code>true</code> if <code>obj</code> is a DVBAlphaComposite
	 * and has the same values for rule and alpha as this object. 
	 * Otherwise <code>false</code> shall be returned.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof DVBAlphaComposite)) {
			return false;
		}

		DVBAlphaComposite ac = (DVBAlphaComposite) obj;

		if (rule != ac.rule) {
			return false;
		}

		if (extraAlpha != ac.extraAlpha) {
			return false;
		}

		return true;
	}
	/**
	 * Returns the alpha value of this <code>DVBAlphaComposite</code>. If this
	 * <code>DVBAlphaComposite</code> does not have an alpha value, 1,0 is returned.
	 * @return the alpha value of this <code>DVBAlphaComposite</code>.
	 */
	public float getAlpha() {
	return extraAlpha;
	}
	/**
	 * Creates an <code>DVBAlphaComposite</code> object with the specified rule.
	 * The value for alpha shall be 1,0f.
	 * @param rule the compositing rule
	 * @return an <code>DVBAlphaComposite</code> object with the specified rule.
	 */
	public static DVBAlphaComposite getInstance(int rule) {
	switch (rule) {
	case CLEAR:
	    return Clear;
	case SRC:
	    return Src;
	case SRC_OVER:
	    return SrcOver;
	case DST_OVER:
	    return DstOver;
	case SRC_IN:
	    return SrcIn;
	case DST_IN:
	    return DstIn;
	case SRC_OUT:
	    return SrcOut;
	case DST_OUT:
	    return DstOut;
	default:
	    throw new IllegalArgumentException("unknown composite rule");
	}
	}
	/**
	 * Creates an <code>DVBAlphaComposite</code> object with the specified rule and
	 * the constant alpha (Ar) to multiply with the alpha of the source (As).
	 * The source is multiplied with the specified alpha before being composited
	 * with the destination.
	 * @param rule the compositing rule
	 * @param alpha the constant alpha (Ar) to be multiplied with the alpha of
	 * the source (As). <code>alpha</code> must be a floating point number in the
	 * inclusive range [0,0,&nbsp;1,0]. 
	 * @return an <code>DVBAlphaComposite</code> object with the specified rule and
	 * the constant alpha to multiply with the alpha of the source.
	 */
	public static DVBAlphaComposite getInstance(int rule, float alpha) {
	if (alpha == 1.0f) {
	    return getInstance(rule);
	}
	return new DVBAlphaComposite(rule, alpha);
	}
	/**
	 * Returns the compositing rule of this <code>DVBAlphaComposite</code>.
	 * @return the compositing rule of this <code>DVBAlphaComposite</code>.
	 */
	public int getRule() {
		return rule;
	}
}

