/*
<p>This is not an official specification document, and usage is restricted.
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
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.security.spec;

/** 
 * This class represents a public or private key in encoded format.
 *
 * @author Jan Luehe
 *
 * @version 1.16, 02/02/00
 *
 * @see java.security.Key
 * @see java.security.KeyFactory
 * @see KeySpec
 * @see X509EncodedKeySpec
 * @see PKCS8EncodedKeySpec
 *
 * @since 1.2
 */
public abstract class EncodedKeySpec implements KeySpec
{

    /** 
     * Creates a new EncodedKeySpec with the given encoded key.
     *
     * @param encodedKey the encoded key.
     */
    public EncodedKeySpec(byte[] encodedKey) { }

    /** 
     * Returns the encoded key.
     *
     * @return the encoded key.
     */
    public byte[] getEncoded() {
        return null;
    }

    /** 
     * Returns the name of the encoding format associated with this
     * key specification.
     *
     * <p>If the opaque representation of a key
     * (see {@link java.security.Key Key}) can be transformed
     * (see {@link java.security.KeyFactory KeyFactory})
     * into this key specification (or a subclass of it),
     * <code>getFormat</code> called
     * on the opaque key returns the same value as the
     * <code>getFormat</code> method
     * of this key specification.
     *
     * @return a string representation of the encoding format.
     */
    public abstract String getFormat();
}
