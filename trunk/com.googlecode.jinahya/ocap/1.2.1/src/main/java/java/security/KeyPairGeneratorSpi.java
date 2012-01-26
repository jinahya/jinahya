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


  


package java.security;

import java.security.spec.AlgorithmParameterSpec;

/** 
 * <p> This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the <code>KeyPairGenerator</code> class, which is used to generate
 * pairs of public and private keys.
 *
 * <p> All the abstract methods in this class must be implemented by each
 * cryptographic service provider who wishes to supply the implementation
 * of a key pair generator for a particular algorithm.
 * 
 * <p> In case the client does not explicitly initialize the KeyPairGenerator
 * (via a call to an <code>initialize</code> method), each provider must
 * supply (and document) a default initialization.
 * For example, the <i>Sun</i> provider uses a default modulus size (keysize)
 * of 1024 bits.
 *
 * @author Benjamin Renaud
 *
 * @version 1.11, 02/02/00
 *
 * @see KeyPairGenerator
 * @see java.security.spec.AlgorithmParameterSpec
 */
public abstract class KeyPairGeneratorSpi
{

    public KeyPairGeneratorSpi() { }

    /** 
     * Initializes the key pair generator for a certain keysize, using
     * the default parameter set.
     *
     * @param keysize the keysize. This is an
     * algorithm-specific metric, such as modulus length, specified in
     * number of bits.
     *
     * @param random the source of randomness for this generator.
     *
     * @exception InvalidParameterException if the <code>keysize</code> is not
     * supported by this KeyPairGeneratorSpi object.
     */
    public abstract void initialize(int keysize, SecureRandom random);

    /** 
     * Initializes the key pair generator using the specified parameter
     * set and user-provided source of randomness.
     *
     * <p>This concrete method has been added to this previously-defined
     * abstract class. (For backwards compatibility, it cannot be abstract.)
     * It may be overridden by a provider to initialize the key pair 
     * generator. Such an override
     * is expected to throw an InvalidAlgorithmParameterException if
     * a parameter is inappropriate for this key pair generator.
     * If this method is not overridden, it always throws an
     * UnsupportedOperationException.
     *
     * @param params the parameter set used to generate the keys.
     *
     * @param random the source of randomness for this generator.
     *
     * @exception InvalidAlgorithmParameterException if the given parameters
     * are inappropriate for this key pair generator.
     *
     * @since 1.2
     */
    public void initialize(AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidAlgorithmParameterException
    { }

    /** 
     * Generates a key pair. Unless an initialization method is called
     * using a KeyPairGenerator interface, algorithm-specific defaults
     * will be used. This will generate a new key pair every time it
     * is called.
     *
     * @return the newly generated <tt>KeyPair</tt>
     */
    public abstract KeyPair generateKeyPair();
}
