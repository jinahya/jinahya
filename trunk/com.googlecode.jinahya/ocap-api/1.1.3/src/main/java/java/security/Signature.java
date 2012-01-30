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

import java.util.*;
import java.io.*;

import java.security.spec.AlgorithmParameterSpec;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/** 
 * This Signature class is used to provide applications the functionality
 * of a digital signature algorithm. Digital signatures are used for
 * authentication and integrity assurance of digital data.
 *
 * <p> The signature algorithm can be, among others, the NIST standard
 * DSA, using DSA and SHA-1. The DSA algorithm using the
 * SHA-1 message digest algorithm can be specified as <tt>SHA1withDSA</tt>.
 * In the case of RSA, there are multiple choices for the message digest
 * algorithm, so the signing algorithm could be specified as, for example,
 * <tt>MD2withRSA</tt>, <tt>MD5withRSA</tt>, or <tt>SHA1withRSA</tt>.
 * The algorithm name must be specified, as there is no default.
 *
 * <p>Like other algorithm-based classes in Java Security, Signature 
 * provides implementation-independent algorithms, whereby a caller 
 * (application code) requests a particular signature algorithm
 * and is handed back a properly initialized Signature object. It is
 * also possible, if desired, to request a particular algorithm from a
 * particular provider. See the <code>getInstance </code> methods.
 *
 * <p>Thus, there are two ways to request a Signature algorithm object: by
 * specifying either just an algorithm name, or both an algorithm name
 * and a package provider. <ul>
 *
 * <li>If just an algorithm name is specified, the system will
 * determine if there is an implementation of the algorithm requested
 * available in the environment, and if there is more than one, if
 * there is a preferred one.<p>
 * 
 * <li>If both an algorithm name and a package provider are specified,
 * the system will determine if there is an implementation of the
 * algorithm in the package requested, and throw an exception if there
 * is not.
 *
 * </ul>
 *
 * <p>A Signature object can be used to generate and verify digital
 * signatures.
 *
 * <p>There are three phases to the use of a Signature object for
 * either signing data or verifying a signature:<ol>
 *
 * <li>Initialization, with either 
 *
 *     <ul>
 *
 *     <li>a public key, which initializes the signature for
 *     verification (see {@link #initVerify(PublicKey) initVerify}), or
 *
 *     <li>a private key (and optionally a Secure Random Number Generator),
 *     which initializes the signature for signing
 *     (see {@link #initSign(PrivateKey)}
 *     and {@link #initSign(PrivateKey, SecureRandom)}).
 *
 *     </ul><p>
 *
 * <li>Updating<p>
 *
 * <p>Depending on the type of initialization, this will update the
 * bytes to be signed or verified. See the 
 * {@link #update(byte) update} methods.<p>
 *
 * <li>Signing or Verifying a signature on all updated bytes. See the 
 * {@link #sign() sign} methods and the {@link #verify(byte[]) verify}
 * method.
 *
 * </ol>
 *
 * <p>Note that this class is abstract and extends from
 * <code>SignatureSpi</code> for historical reasons.
 * Application developers should only take notice of the methods defined in
 * this <code>Signature</code> class; all the methods in
 * the superclass are intended for cryptographic service providers who wish to
 * supply their own implementations of digital signature algorithms.
 *
 * @author Benjamin Renaud 
 *
 * @version 1.86, 05/03/00
 */
public abstract class Signature extends SignatureSpi
{
    /** 
     * Possible {@link #state} value, signifying that       
     * this signature object has not yet been initialized.
     */
    protected static final int UNINITIALIZED = 0;

    /** 
     * Possible {@link #state} value, signifying that       
     * this signature object has been initialized for signing.
     */
    protected static final int SIGN = 2;

    /** 
     * Possible {@link #state} value, signifying that       
     * this signature object has been initialized for verification.
     */
    protected static final int VERIFY = 3;

    /** 
     * Current state of this signature object.
     */
    protected int state;

    /** 
     * Creates a Signature object for the specified algorithm.
     *
     * @param algorithm the standard string name of the algorithm. 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     */
    protected Signature(String algorithm) { }

    /** 
     * Generates a Signature object that implements the specified digest
     * algorithm. If the default provider package
     * provides an implementation of the requested digest algorithm,
     * an instance of Signature containing that implementation is returned.
     * If the algorithm is not available in the default 
     * package, other packages are searched.
     *
     * @param algorithm the standard name of the algorithm requested. 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     *
     * @return the new Signature object.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available in the environment.
     */
    public static Signature getInstance(String algorithm)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Generates a Signature object implementing the specified
     * algorithm, as supplied from the specified provider, if such an 
     * algorithm is available from the provider.
     *
     * @param algorithm the name of the algorithm requested.
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     *
     * @param provider the name of the provider.
     *
     * @return the new Signature object.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available in the package supplied by the requested
     * provider.
     *
     * @exception NoSuchProviderException if the provider is not
     * available in the environment.
     *
     * @exception IllegalArgumentException if the provider name is null
     * or empty.
     * 
     * @see Provider 
     */
    public static Signature getInstance(String algorithm, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        return null;
    }

    /** 
     * Generates a Signature object implementing the specified
     * algorithm, as supplied from the specified provider, if such an 
     * algorithm is available from the provider. Note: the 
     * <code>provider</code> doesn't have to be registered. 
     *
     * @param algorithm the name of the algorithm requested.
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     *
     * @param provider the provider.
     *
     * @return the new Signature object.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available in the package supplied by the requested
     * provider.
     *
     * @exception IllegalArgumentException if the <code>provider</code> is
     * null.
     * 
     * @see Provider
     *
     * @since 1.4
     */
    public static Signature getInstance(String algorithm, Provider provider)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Returns the provider of this signature object.
     * 
     * @return the provider of this signature object
     */
    public final Provider getProvider() {
        return null;
    }

    /** 
     * Initializes this object for verification. If this method is called
     * again with a different argument, it negates the effect
     * of this call.
     *
     * @param publicKey the public key of the identity whose signature is
     * going to be verified.
     *
     * @exception InvalidKeyException if the key is invalid.
     */
    public final void initVerify(PublicKey publicKey) throws InvalidKeyException
    { }

    /** 
     * Initializes this object for verification, using the public key from
     * the given certificate.
     * <p>If the certificate is of type X.509 and has a <i>key usage</i>
     * extension field marked as critical, and the value of the <i>key usage</i>
     * extension field implies that the public key in
     * the certificate and its corresponding private key are not
     * supposed to be used for digital signatures, an <code>InvalidKeyException</code>
     * is thrown.
     *
     * @param certificate the certificate of the identity whose signature is
     * going to be verified.
     *
     * @exception InvalidKeyException  if the public key in the certificate
     * is not encoded properly or does not include required  parameter
     * information or cannot be used for digital signature purposes.
     */
    public final void initVerify(Certificate certificate)
        throws InvalidKeyException
    { }

    /** 
     * Initialize this object for signing. If this method is called
     * again with a different argument, it negates the effect
     * of this call.
     *
     * @param privateKey the private key of the identity whose signature
     * is going to be generated.
     * 
     * @exception InvalidKeyException if the key is invalid.  
     */
    public final void initSign(PrivateKey privateKey) throws InvalidKeyException
    { }

    /** 
     * Initialize this object for signing. If this method is called
     * again with a different argument, it negates the effect
     * of this call.
     *
     * @param privateKey the private key of the identity whose signature
     * is going to be generated.
     * 
     * @param random the source of randomness for this signature.
     * 
     * @exception InvalidKeyException if the key is invalid.  
     */
    public final void initSign(PrivateKey privateKey, SecureRandom random)
        throws InvalidKeyException
    { }

    /** 
     * Returns the signature bytes of all the data updated.
     * The format of the signature depends on the underlying 
     * signature scheme.
     * 
     * <p>A call to this method resets this signature object to the state 
     * it was in when previously initialized for signing via a
     * call to <code>initSign(PrivateKey)</code>. That is, the object is 
     * reset and available to generate another signature from the same 
     * signer, if desired, via new calls to <code>update</code> and 
     * <code>sign</code>.     
     *
     * @return the signature bytes of the signing operation's result.
     *
     * @exception SignatureException if this signature object is not
     * initialized properly.
     */
    public final byte[] sign() throws SignatureException {
        return null;
    }

    /** 
     * Finishes the signature operation and stores the resulting signature
     * bytes in the provided buffer <code>outbuf</code>, starting at
     * <code>offset</code>. 
     * The format of the signature depends on the underlying 
     * signature scheme.
     * 
     * <p>This signature object is reset to its initial state (the state it
     * was in after a call to one of the <code>initSign</code> methods) and
     * can be reused to generate further signatures with the same private key.
     *
     * @param outbuf buffer for the signature result.
     *
     * @param offset offset into <code>outbuf</code> where the signature is
     * stored.
     *
     * @param len number of bytes within <code>outbuf</code> allotted for the
     * signature.
     *
     * @return the number of bytes placed into <code>outbuf</code>.
     *
     * @exception SignatureException if an error occurs or <code>len</code>
     * is less than the actual signature length.
     *
     * @since 1.2
     */
    public final int sign(byte[] outbuf, int offset, int len)
        throws SignatureException
    {
        return 0;
    }

    /** 
     * Verifies the passed-in signature. 
     * 
     * <p>A call to this method resets this signature object to the state 
     * it was in when previously initialized for verification via a
     * call to <code>initVerify(PublicKey)</code>. That is, the object is 
     * reset and available to verify another signature from the identity
     * whose public key was specified in the call to <code>initVerify</code>.
     *      
     * @param signature the signature bytes to be verified.
     *
     * @return true if the signature was verified, false if not. 
     *
     * @exception SignatureException if this signature object is not 
     * initialized properly, or the passed-in signature is improperly 
     * encoded or of the wrong type, etc.
     */
    public final boolean verify(byte[] signature) throws SignatureException {
        return false;
    }

    /** 
     * Verifies the passed-in signature in the specified array
     * of bytes, starting at the specified offset.
     * 
     * <p>A call to this method resets this signature object to the state 
     * it was in when previously initialized for verification via a
     * call to <code>initVerify(PublicKey)</code>. That is, the object is 
     * reset and available to verify another signature from the identity
     * whose public key was specified in the call to <code>initVerify</code>.
     *
     *      
     * @param signature the signature bytes to be verified.
     * @param offset the offset to start from in the array of bytes.
     * @param length the number of bytes to use, starting at offset.
     *
     * @return true if the signature was verified, false if not. 
     *
     * @exception SignatureException if this signature object is not 
     * initialized properly, or the passed-in signature is improperly 
     * encoded or of the wrong type, etc.
     * @exception IllegalArgumentException if the <code>signature</code>
     * byte array is null, or the <code>offset</code> or <code>length</code>
     * is less than 0, or the sum of the <code>offset</code> and 
     * <code>length</code> is greater than the length of the
     * <code>signature</code> byte array.
     */
    public final boolean verify(byte[] signature, int offset, int length)
        throws SignatureException
    {
        return false;
    }

    /** 
     * Updates the data to be signed or verified by a byte.
     *
     * @param b the byte to use for the update.
     * 
     * @exception SignatureException if this signature object is not 
     * initialized properly.     
     */
    public final void update(byte b) throws SignatureException { }

    /** 
     * Updates the data to be signed or verified, using the specified
     * array of bytes.
     *
     * @param data the byte array to use for the update.       
     * 
     * @exception SignatureException if this signature object is not 
     * initialized properly.          
     */
    public final void update(byte[] data) throws SignatureException { }

    /** 
     * Updates the data to be signed or verified, using the specified
     * array of bytes, starting at the specified offset.  
     *
     * @param data the array of bytes.  
     * @param off the offset to start from in the array of bytes.  
     * @param len the number of bytes to use, starting at offset.
     *  
     * @exception SignatureException if this signature object is not 
     * initialized properly.          
     */
    public final void update(byte[] data, int off, int len)
        throws SignatureException
    { }

    /** 
     * Returns the name of the algorithm for this signature object.
     * 
     * @return the name of the algorithm for this signature object.
     */
    public final String getAlgorithm() {
        return null;
    }

    /** 
     * Returns a string representation of this signature object,       
     * providing information that includes the state of the object       
     * and the name of the algorithm used.       
     * 
     * @return a string representation of this signature object.
     */
    public String toString() {
        return null;
    }

    /** 
     * Initializes this signature engine with the specified parameter set.
     *
     * @param params the parameters
     *
     * @exception InvalidAlgorithmParameterException if the given parameters
     * are inappropriate for this signature engine
     *
     * @see #getParameters
     */
    public final void setParameter(AlgorithmParameterSpec params)
        throws InvalidAlgorithmParameterException
    { }

    /** 
     * Returns the parameters used with this signature object.
     *
     * <p>The returned parameters may be the same that were used to initialize
     * this signature, or may contain a combination of default and randomly
     * generated parameter values used by the underlying signature 
     * implementation if this signature requires algorithm parameters but 
     * was not initialized with any.
     *
     * @return the parameters used with this signature, or null if this
     * signature does not use any parameters.
     *
     * @see #setParameter(AlgorithmParameterSpec)
     */
    public final AlgorithmParameters getParameters() {
        return null;
    }

    /** 
     * Returns a clone if the implementation is cloneable.
     * 
     * @return a clone if the implementation is cloneable.
     *
     * @exception CloneNotSupportedException if this is called
     * on an implementation that does not support <code>Cloneable</code>.
     */
    public Object clone() throws CloneNotSupportedException {
        return null;
    }
}
