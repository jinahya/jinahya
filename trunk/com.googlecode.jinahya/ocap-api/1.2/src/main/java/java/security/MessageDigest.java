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

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

/** 
 * This MessageDigest class provides applications the functionality of a
 * message digest algorithm, such as MD5 or SHA.
 * Message digests are secure one-way hash functions that take arbitrary-sized
 * data and output a fixed-length hash value.
 *
 * <p>A MessageDigest object starts out initialized. The data is 
 * processed through it using the {@link #update(byte) update}
 * methods. At any point {@link #reset() reset} can be called
 * to reset the digest. Once all the data to be updated has been
 * updated, one of the {@link #digest() digest} methods should 
 * be called to complete the hash computation.
 *
 * <p>The <code>digest</code> method can be called once for a given number 
 * of updates. After <code>digest</code> has been called, the MessageDigest
 * object is reset to its initialized state.
 *
 * <p>Implementations are free to implement the Cloneable interface.
 * Client applications can test cloneability by attempting cloning
 * and catching the CloneNotSupportedException: <p>    
 *
 * <pre>
 * MessageDigest md = MessageDigest.getInstance("SHA");
 *
 * try {
 *     md.update(toChapter1);
 *     MessageDigest tc1 = md.clone();
 *     byte[] toChapter1Digest = tc1.digest();
 *     md.update(toChapter2);
 *     ...etc.
 * } catch (CloneNotSupportedException cnse) {
 *     throw new DigestException("couldn't make digest of partial content");
 * }
 * </pre>
 *
 * <p>Note that if a given implementation is not cloneable, it is
 * still possible to compute intermediate digests by instantiating
 * several instances, if the number of digests is known in advance.
 *
 * <p>Note that this class is abstract and extends from
 * <code>MessageDigestSpi</code> for historical reasons.
 * Application developers should only take notice of the methods defined in
 * this <code>MessageDigest</code> class; all the methods in
 * the superclass are intended for cryptographic service providers who wish to
 * supply their own implementations of message digest algorithms.
 *
 * @author Benjamin Renaud 
 *
 * @version 1.71, 02/02/00
 *
 * @see DigestInputStream
 * @see DigestOutputStream
 */
public abstract class MessageDigest extends MessageDigestSpi
{

    /** 
     * Creates a message digest with the specified algorithm name.
     * 
     * @param algorithm the standard name of the digest algorithm. 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     */
    protected MessageDigest(String algorithm) { }

    /** 
     * Generates a MessageDigest object that implements the specified digest
     * algorithm. If the default provider package
     * provides an implementation of the requested digest algorithm,
     * an instance of MessageDigest containing that implementation is returned.
     * If the algorithm is not available in the default 
     * package, other packages are searched.
     *
     * @param algorithm the name of the algorithm requested. 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     *
     * @return a Message Digest object implementing the specified
     * algorithm.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available in the caller's environment.  
     */
    public static MessageDigest getInstance(String algorithm)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Generates a MessageDigest object implementing the specified
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
     * @return a Message Digest object implementing the specified
     * algorithm.
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
    public static MessageDigest getInstance(String algorithm, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        return null;
    }

    /** 
     * Generates a MessageDigest object implementing the specified
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
     * @return a Message Digest object implementing the specified
     * algorithm.
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
    public static MessageDigest getInstance(String algorithm, Provider provider)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Returns the provider of this message digest object.
     * 
     * @return the provider of this message digest object
     */
    public final Provider getProvider() {
        return null;
    }

    /** 
     * Updates the digest using the specified byte.    
     * 
     * @param input the byte with which to update the digest.
     */
    public void update(byte input) { }

    /** 
     * Updates the digest using the specified array of bytes, starting
     * at the specified offset.
     * 
     * @param input the array of bytes.
     *
     * @param offset the offset to start from in the array of bytes.
     *
     * @param len the number of bytes to use, starting at 
     * <code>offset</code>.  
     */
    public void update(byte[] input, int offset, int len) { }

    /** 
     * Updates the digest using the specified array of bytes.
     * 
     * @param input the array of bytes.
     */
    public void update(byte[] input) { }

    /** 
     * Completes the hash computation by performing final operations
     * such as padding. The digest is reset after this call is made.
     *
     * @return the array of bytes for the resulting hash value.  
     */
    public byte[] digest() {
        return null;
    }

    /** 
     * Completes the hash computation by performing final operations
     * such as padding. The digest is reset after this call is made.
     *
     * @param buf output buffer for the computed digest
     *
     * @param offset offset into the output buffer to begin storing the digest
     *
     * @param len number of bytes within buf allotted for the digest
     *
     * @return the number of bytes placed into <code>buf</code>
     * 
     * @exception DigestException if an error occurs.
     */
    public int digest(byte[] buf, int offset, int len) throws DigestException {
        return 0;
    }

    /** 
     * Performs a final update on the digest using the specified array 
     * of bytes, then completes the digest computation. That is, this
     * method first calls {@link #update(byte[]) update(input)},
     * passing the <i>input</i> array to the <code>update</code> method,
     * then calls {@link #digest() digest()}.
     *
     * @param input the input to be updated before the digest is
     * completed.
     *
     * @return the array of bytes for the resulting hash value.  
     */
    public byte[] digest(byte[] input) {
        return null;
    }

    /** 
     * Returns a string representation of this message digest object.  
     */
    public String toString() {
        return null;
    }

    /** 
     * Compares two digests for equality. Does a simple byte compare.
     * 
     * @param digesta one of the digests to compare.
     * 
     * @param digestb the other digest to compare.    
     *
     * @return true if the digests are equal, false otherwise.
     */
    public static boolean isEqual(byte[] digesta, byte[] digestb) {
        return false;
    }

    /** 
     * Resets the digest for further use.
     */
    public void reset() { }

    /** 
     * Returns a string that identifies the algorithm, independent of
     * implementation details. The name should be a standard
     * Java Security name (such as "SHA", "MD5", and so on). 
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard algorithm names.
     *
     * @return the name of the algorithm
     */
    public final String getAlgorithm() {
        return null;
    }

    /** 
     * Returns the length of the digest in bytes, or 0 if this operation is
     * not supported by the provider and the implementation is not cloneable.
     *
     * @return the digest length in bytes, or 0 if this operation is not
     * supported by the provider and the implementation is not cloneable.
     * 
     * @since 1.2
     */
    public final int getDigestLength() {
        return 0;
    }

    /** 
     * Returns a clone if the implementation is cloneable.    
     * 
     * @return a clone if the implementation is cloneable.
     *
     * @exception CloneNotSupportedException if this is called on an
     * implementation that does not support <code>Cloneable</code>.
     */
    public Object clone() throws CloneNotSupportedException {
        return null;
    }
}
