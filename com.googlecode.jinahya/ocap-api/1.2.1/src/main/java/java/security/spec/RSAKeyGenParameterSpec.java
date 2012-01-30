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

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/** 
 * This class specifies the set of parameters used to generate an RSA
 * key pair.
 * 
 * @author Jan Luehe
 * @version 1.5 02/02/00
 *
 * @see java.security.KeyPairGenerator#initialize(java.security.spec.AlgorithmParameterSpec)
 *
 * @since 1.3
 */
public class RSAKeyGenParameterSpec
    implements java.security.spec.AlgorithmParameterSpec
{
    /** 
     * The public-exponent value F0 = 3.
     */
    public static final BigInteger F0 = null;

    /** 
     * The public exponent-value F4 = 65537.
     */
    public static final BigInteger F4 = null;

    /** 
     * Constructs a new <code>RSAParameterSpec</code> object from the
     * given keysize and public-exponent value.
     *
     * @param keysize the modulus size (specified in number of bits)
     * @param publicExponent the public exponent
     */
    public RSAKeyGenParameterSpec(int keysize, BigInteger publicExponent) { }

    /** 
     * Returns the keysize.
     *
     * @return the keysize.
     */
    public int getKeysize() {
        return 0;
    }

    /** 
     * Returns the public-exponent value.
     *
     * @return the public-exponent value.
     */
    public BigInteger getPublicExponent() {
        return null;
    }
}
