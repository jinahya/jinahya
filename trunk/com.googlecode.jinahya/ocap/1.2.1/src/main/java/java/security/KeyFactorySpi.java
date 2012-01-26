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

import java.security.spec.KeySpec;
import java.security.spec.InvalidKeySpecException;

/** 
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the <code>KeyFactory</code> class.
 * All the abstract methods in this class must be implemented by each 
 * cryptographic service provider who wishes to supply the implementation
 * of a key factory for a particular algorithm.
 *
 * <P> Key factories are used to convert <I>keys</I> (opaque
 * cryptographic keys of type <code>Key</code>) into <I>key specifications</I>
 * (transparent representations of the underlying key material), and vice
 * versa.
 *
 * <P> Key factories are bi-directional. That is, they allow you to build an
 * opaque key object from a given key specification (key material), or to
 * retrieve the underlying key material of a key object in a suitable format.
 *
 * <P> Multiple compatible key specifications may exist for the same key.
 * For example, a DSA public key may be specified using
 * <code>DSAPublicKeySpec</code> or
 * <code>X509EncodedKeySpec</code>. A key factory can be used to translate
 * between compatible key specifications.
 *
 * <P> A provider should document all the key specifications supported by its
 * key factory.
 *
 * @author Jan Luehe
 *
 * @version 1.8, 02/02/00
 *
 * @see KeyFactory
 * @see Key
 * @see PublicKey
 * @see PrivateKey
 * @see java.security.spec.KeySpec
 * @see java.security.spec.DSAPublicKeySpec
 * @see java.security.spec.X509EncodedKeySpec
 *
 * @since 1.2
 */
public abstract class KeyFactorySpi
{

    public KeyFactorySpi() { }

    /** 
     * Generates a public key object from the provided key
     * specification (key material).
     *
     * @param keySpec the specification (key material) of the public key.
     *
     * @return the public key.
     *
     * @exception InvalidKeySpecException if the given key specification
     * is inappropriate for this key factory to produce a public key.
     */
    protected abstract PublicKey engineGeneratePublic(KeySpec keySpec)
        throws InvalidKeySpecException;

    /** 
     * Generates a private key object from the provided key
     * specification (key material).
     *
     * @param keySpec the specification (key material) of the private key.
     *
     * @return the private key.
     *
     * @exception InvalidKeySpecException if the given key specification
     * is inappropriate for this key factory to produce a private key.
     */
    protected abstract PrivateKey engineGeneratePrivate(KeySpec keySpec)
        throws InvalidKeySpecException;

    /** 
     * Returns a specification (key material) of the given key
     * object.
     * <code>keySpec</code> identifies the specification class in which 
     * the key material should be returned. It could, for example, be
     * <code>DSAPublicKeySpec.class</code>, to indicate that the
     * key material should be returned in an instance of the 
     * <code>DSAPublicKeySpec</code> class.
     *
     * @param key the key.
     *
     * @param keySpec the specification class in which 
     * the key material should be returned.
     *
     * @return the underlying key specification (key material) in an instance
     * of the requested specification class.
     *
     * @exception InvalidKeySpecException if the requested key specification is
     * inappropriate for the given key, or the given key cannot be dealt with
     * (e.g., the given key has an unrecognized format).
     */
    protected abstract KeySpec engineGetKeySpec(Key key, Class keySpec)
        throws InvalidKeySpecException;

    /** 
     * Translates a key object, whose provider may be unknown or
     * potentially untrusted, into a corresponding key object of this key
     * factory.
     *
     * @param key the key whose provider is unknown or untrusted.
     *
     * @return the translated key.
     *
     * @exception InvalidKeyException if the given key cannot be processed
     * by this key factory.
     */
    protected abstract Key engineTranslateKey(Key key)
        throws InvalidKeyException;
}
