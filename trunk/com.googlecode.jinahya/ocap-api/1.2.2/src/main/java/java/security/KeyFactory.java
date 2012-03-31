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
 * Key factories are used to convert <I>keys</I> (opaque
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
 * <P> The following is an example of how to use a key factory in order to
 * instantiate a DSA public key from its encoding.
 * Assume Alice has received a digital signature from Bob.
 * Bob also sent her his public key (in encoded format) to verify
 * his signature. Alice then performs the following actions:
 *
 * <pre>
 * X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(bobEncodedPubKey);
 * KeyFactory keyFactory = KeyFactory.getInstance("DSA");
 * PublicKey bobPubKey = keyFactory.generatePublic(bobPubKeySpec);
 * Signature sig = Signature.getInstance("DSA");
 * sig.initVerify(bobPubKey);
 * sig.update(data);
 * sig.verify(signature);
 * </pre>
 *
 * @author Jan Luehe
 *
 * @version 1.28, 05/07/02
 *
 * @see Key
 * @see PublicKey
 * @see PrivateKey
 * @see java.security.spec.KeySpec
 * @see java.security.spec.DSAPublicKeySpec
 * @see java.security.spec.X509EncodedKeySpec
 *
 * @since 1.2
 */
public class KeyFactory
{

    /** 
     * Creates a KeyFactory object.
     *
     * @param keyFacSpi the delegate
     * @param provider the provider
     * @param algorithm the name of the algorithm
     * to associate with this <tt>KeyFactory</tt>
     */
    protected KeyFactory(KeyFactorySpi keyFacSpi, Provider provider, String
        algorithm)
    { }

    /** 
     * Generates a KeyFactory object that implements the specified 
     * algorithm. If the default provider package
     * provides an implementation of the requested algorithm,
     * an instance of KeyFactory containing that implementation is returned.
     * If the algorithm is not available in the default 
     * package, other packages are searched.
     *
     * @param algorithm the name of the requested key algorithm. 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     *
     * @return a KeyFactory object for the specified algorithm.
     *
     * @exception NoSuchAlgorithmException if the requested algorithm is
     * not available in the default provider package or any of the other
     * provider packages that were searched.  
     */
    public static KeyFactory getInstance(String algorithm)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Generates a KeyFactory object for the specified algorithm from the
     * specified provider.
     *
     * @param algorithm the name of the requested key algorithm. 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     *
     * @param provider the name of the provider.
     *
     * @return a KeyFactory object for the specified algorithm.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available from the specified provider.
     *
     * @exception NoSuchProviderException if the provider has not been 
     * configured.
     *
     * @exception IllegalArgumentException if the provider name is null
     * or empty. 
     * 
     * @see Provider 
     */
    public static KeyFactory getInstance(String algorithm, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        return null;
    }

    /** 
     * Generates a KeyFactory object for the specified algorithm from the
     * specified provider. Note: the <code>provider</code> doesn't have 
     * to be registered. 
     *
     * @param algorithm the name of the requested key algorithm. 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     *
     * @param provider the provider.
     *
     * @return a KeyFactory object for the specified algorithm.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available from the specified provider.
     *
     * @exception IllegalArgumentException if the <code>provider</code> is
     * null.
     * 
     * @see Provider
     *
     * @since 1.4
     */
    public static KeyFactory getInstance(String algorithm, Provider provider)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Returns the provider of this key factory object.
     * 
     * @return the provider of this key factory object
     */
    public final Provider getProvider() {
        return null;
    }

    /** 
     * Gets the name of the algorithm 
     * associated with this <tt>KeyFactory</tt>.
     *
     * @return the name of the algorithm associated with this
     * <tt>KeyFactory</tt>
     */
    public final String getAlgorithm() {
        return null;
    }

    /** 
     * Generates a public key object from the provided key specification
     * (key material).
     *
     * @param keySpec the specification (key material) of the public key.
     *
     * @return the public key.
     *
     * @exception InvalidKeySpecException if the given key specification
     * is inappropriate for this key factory to produce a public key.
     */
    public final PublicKey generatePublic(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        return null;
    }

    /** 
     * Generates a private key object from the provided key specification
     * (key material).
     *
     * @param keySpec the specification (key material) of the private key.
     *
     * @return the private key.
     *
     * @exception InvalidKeySpecException if the given key specification
     * is inappropriate for this key factory to produce a private key.
     */
    public final PrivateKey generatePrivate(KeySpec keySpec)
        throws InvalidKeySpecException
    {
        return null;
    }

    /** 
     * Returns a specification (key material) of the given key object.
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
     * inappropriate for the given key, or the given key cannot be processed
     * (e.g., the given key has an unrecognized algorithm or format).
     */
    public final KeySpec getKeySpec(Key key, Class keySpec)
        throws InvalidKeySpecException
    {
        return null;
    }

    /** 
     * Translates a key object, whose provider may be unknown or potentially
     * untrusted, into a corresponding key object of this key factory.
     *
     * @param key the key whose provider is unknown or untrusted.
     *
     * @return the translated key.
     *
     * @exception InvalidKeyException if the given key cannot be processed
     * by this key factory.
     */
    public final Key translateKey(Key key) throws InvalidKeyException {
        return null;
    }
}
