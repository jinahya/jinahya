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

/** 
 * The <code>Transparency</code> interface defines the common transparency
 * modes for implementing classes.
 * @version 1.19, 01/23/03
 */
public interface Transparency
{
    /** 
     * Represents image data that is guaranteed to be completely opaque,
     * meaning that all pixels have an alpha value of 1.0.
     */
    public static final int OPAQUE = 1;

    /** 
     * Represents image data that is guaranteed to be either completely
     * opaque, with an alpha value of 1.0, or completely transparent,
     * with an alpha value of 0.0.
     */
    public static final int BITMASK = 2;

    /** 
     * Represents image data that contains or might contain arbitrary
     * alpha values between and including 0.0 and 1.0.
     */
    public static final int TRANSLUCENT = 3;

    /** 
     * Returns the type of this <code>Transparency</code>.
     * @return the field type of this <code>Transparency</code>, which is
     *		either OPAQUE, BITMASK or TRANSLUCENT. 
     */
    public int getTransparency();
}
