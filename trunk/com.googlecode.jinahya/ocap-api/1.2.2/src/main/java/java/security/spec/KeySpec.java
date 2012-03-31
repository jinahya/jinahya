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
 * A (transparent) specification of the key material
 * that constitutes a cryptographic key.
 *
 * <p>If the key is stored on a hardware device, its
 * specification may contain information that helps identify the key on the
 * device.
 *
 * <P> A key may be specified in an algorithm-specific way, or in an
 * algorithm-independent encoding format (such as ASN.1).
 * For example, a DSA private key may be specified by its components
 * <code>x</code>, <code>p</code>, <code>q</code>, and <code>g</code>
 * (see {@link DSAPrivateKeySpec}), or it may be
 * specified using its DER encoding
 * (see {@link PKCS8EncodedKeySpec}).
 *
 * <P> This interface contains no methods or constants. Its only purpose
 * is to group (and provide type safety for) all key specifications.
 * All key specifications must implement this interface.
 *
 * @author Jan Luehe
 *
 * @version 1.14, 02/02/00
 *
 * @see java.security.Key
 * @see java.security.KeyFactory
 * @see EncodedKeySpec
 * @see X509EncodedKeySpec
 * @see PKCS8EncodedKeySpec
 * @see DSAPrivateKeySpec
 * @see DSAPublicKeySpec
 *
 * @since 1.2
 */
public interface KeySpec
{
}
