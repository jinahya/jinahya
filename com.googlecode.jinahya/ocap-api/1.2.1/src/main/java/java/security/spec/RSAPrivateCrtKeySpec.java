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

/** 
 * This class specifies an RSA private key, as defined in the PKCS#1
 * standard, using the Chinese Remainder Theorem (CRT) information values for
 * efficiency.
 *
 * @author Jan Luehe
 *
 * @version 1.8 00/02/02
 *
 * @see java.security.Key
 * @see java.security.KeyFactory
 * @see KeySpec
 * @see PKCS8EncodedKeySpec
 * @see RSAPrivateKeySpec
 * @see RSAPublicKeySpec
 */
public class RSAPrivateCrtKeySpec extends RSAPrivateKeySpec
{

    /** 
     * Creates a new <code>RSAPrivateCrtKeySpec</code>
     * given the modulus, publicExponent, privateExponent,
     * primeP, primeQ, primeExponentP, primeExponentQ, and
     * crtCoefficient as defined in PKCS#1.
     *
     * @param modulus the modulus n
     * @param publicExponent the public exponent e
     * @param privateExponent the private exponent d
     * @param primeP the prime factor p of n
     * @param primeQ the prime factor q of n
     * @param primeExponentP this is d mod (p-1)
     * @param primeExponentQ this is d mod (q-1)
     * @param crtCoefficient the Chinese Remainder Theorem
     * coefficient q-1 mod p
     */
    public RSAPrivateCrtKeySpec(BigInteger modulus, BigInteger publicExponent,
        BigInteger privateExponent, BigInteger primeP, BigInteger primeQ,
        BigInteger primeExponentP, BigInteger primeExponentQ, BigInteger
        crtCoefficient) { 
	super(modulus, privateExponent);        
    }

    /** 
     * Returns the public exponent.
     *
     * @return the public exponent
     */
    public BigInteger getPublicExponent() {
        return null;
    }

    /** 
     * Returns the primeP.
     *
     * @return the primeP
     */
    public BigInteger getPrimeP() {
        return null;
    }

    /** 
     * Returns the primeQ.
     *
     * @return the primeQ
     */
    public BigInteger getPrimeQ() {
        return null;
    }

    /** 
     * Returns the primeExponentP.
     *
     * @return the primeExponentP
     */
    public BigInteger getPrimeExponentP() {
        return null;
    }

    /** 
     * Returns the primeExponentQ.
     *
     * @return the primeExponentQ
     */
    public BigInteger getPrimeExponentQ() {
        return null;
    }

    /** 
     * Returns the crtCoefficient.
     *
     * @return the crtCoefficient
     */
    public BigInteger getCrtCoefficient() {
        return null;
    }
}
