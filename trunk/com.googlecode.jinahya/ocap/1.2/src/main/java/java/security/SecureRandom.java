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

import java.util.Enumeration;

/** 
 * <p>This class provides a cryptographically strong pseudo-random number
 * generator (PRNG). A cryptographically strong pseudo-random number
 * minimally complies with the statistical random number generator tests
 * specified in <a href="http://csrc.nist.gov/cryptval/140-2.htm"><i>FIPS 140-2, Security Requirements for Cryptographic Modules</i></a>, section 4.9.1.
 * Additionally, SecureRandom must produce non-deterministic 
 * output and therefore it is required that the seed material be unpredictable
 * and that output of SecureRandom be cryptographically strong sequences as
 * described in <a href="http://www.ietf.org/rfc/rfc1750.txt"><i>RFC 1750: Randomness Recommendations for Security</i></a>.
 * 
 * <p>Like other algorithm-based classes in Java Security, SecureRandom 
 * provides implementation-independent algorithms, whereby a caller 
 * (application code) requests a particular PRNG algorithm
 * and is handed back a SecureRandom object for that algorithm. It is
 * also possible, if desired, to request a particular algorithm from a
 * particular provider. See the <code>getInstance</code> methods.
 *
 * <p>Thus, there are two ways to request a SecureRandom object: by
 * specifying either just an algorithm name, or both an algorithm name
 * and a package provider.
 *
 * <ul>
 *
 * <li>If just an algorithm name is specified, as in:
 * <pre>
 *      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
 * </pre>
 * the system will determine if there is an implementation of the algorithm
 * requested available in the environment, and if there is more than one, if
 * there is a preferred one.<p>
 * 
 * <li>If both an algorithm name and a package provider are specified, as in:
 * <pre>
 *      SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
 * </pre>
 * the system will determine if there is an implementation of the
 * algorithm in the package requested, and throw an exception if there
 * is not.
 *
 * </ul>
 *
 * <p>The SecureRandom implementation attempts to completely
 * randomize the internal state of the generator itself unless
 * the caller follows the call to a <code>getInstance</code> method
 * with a call to the <code>setSeed</code> method:
 * <pre>
 *      SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
 *      random.setSeed(seed);
 * </pre>
 *
 * <p>After the caller obtains the SecureRandom object from the
 * <code>getInstance</code> call, it can call <code>nextBytes</code>
 * to generate random bytes:
 * <pre>
 *      byte bytes[] = new byte[20];
 *      random.nextBytes(bytes);
 * </pre>
 *
 * <p>The caller may also invoke the <code>generateSeed</code> method
 * to generate a given number of seed bytes (to seed other random number
 * generators, for example):
 * <pre>
 *      byte seed[] = random.generateSeed(20);
 * </pre>
 *
 * @see java.security.SecureRandomSpi
 * @see java.util.Random
 * 
 * @version 1.36, 02/02/00
 * @author Benjamin Renaud
 * @author Josh Bloch 
 */
public class SecureRandom extends java.util.Random
{
    /** 
     * The provider.
     *
     * @serial
     * @since 1.2
     */
    private Provider provider;

    /** 
     * The provider implementation.
     *
     * @serial
     * @since 1.2
     */
    private SecureRandomSpi secureRandomSpi;

    /** 
     * @serial
     */
    private byte[] state;

    /** 
     * @serial
     */
    private MessageDigest digest;

    /** 
     * @serial
     *
     * We know that the MessageDigest class does not implement
     * java.io.Serializable.  However, since this field is no longer
     * used, it will always be NULL and won't affect the serialization
     * of the SecureRandom class itself.
     */
    private byte[] randomBytes;

    /** 
     * @serial
     */
    private int randomBytesUsed;

    /** 
     * @serial
     */
    private long counter;

    // Declare serialVersionUID to be compatible with JDK1.1
    static final long serialVersionUID = 4940670005562187L;

    /** 
     * <p>By using this constructor, the caller obtains a SecureRandom object
     * containing the implementation from the highest-priority installed
     * provider that has a SecureRandom implementation.
     * 
     * <p>Note that this instance of SecureRandom has not been seeded.
     * A call to the <code>setSeed</code> method will seed the SecureRandom
     * object.  If a call is not made to <code>setSeed</code>, the first call
     * to the <code>nextBytes</code> method will force the SecureRandom object
     * to seed itself.
     *
     * <p>This constructor is provided for backwards compatibility. 
     * The caller is encouraged to use one of the alternative
     * <code>getInstance</code> methods to obtain a SecureRandom object.
     */
    public SecureRandom() { }

    /** 
     * <p>By using this constructor, the caller obtains a SecureRandom object
     * containing the implementation from the highest-priority installed
     * provider that has a SecureRandom implementation. This constructor 
     * uses a user-provided seed in
     * preference to the self-seeding algorithm referred to in the empty
     * constructor description. It may be preferable to the empty constructor
     * if the caller has access to high-quality random bytes from some physical
     * device (for example, a radiation detector or a noisy diode).
     * 
     * <p>This constructor is provided for backwards compatibility. 
     * The caller is encouraged to use one of the alternative
     * <code>getInstance</code> methods to obtain a SecureRandom object, and
     * then to call the <code>setSeed</code> method to seed it.
     * 
     * @param seed the seed.
     */
    public SecureRandom(byte[] seed) { }

    /** 
     * Creates a SecureRandom object.
     *
     * @param secureRandomSpi the SecureRandom implementation.
     * @param provider the provider.
     */
    protected SecureRandom(SecureRandomSpi secureRandomSpi, Provider provider)
    { }

    /** 
     * Generates a SecureRandom object that implements the specified
     * Pseudo Random Number Generator (PRNG) algorithm. If the default
     * provider package provides an implementation of the requested PRNG,
     * an instance of SecureRandom containing that implementation is returned.
     * If the PRNG is not available in the default 
     * package, other packages are searched.
     *
     * <p>Note that the returned instance of SecureRandom has not been seeded.
     * A call to the <code>setSeed</code> method will seed the SecureRandom
     * object.  If a call is not made to <code>setSeed</code>, the first call
     * to the <code>nextBytes</code> method will force the SecureRandom object
     * to seed itself.
     *
     * @param algorithm the name of the PRNG algorithm.
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard PRNG algorithm names.
     *
     * @return the new SecureRandom object.
     *
     * @exception NoSuchAlgorithmException if the PRNG algorithm is
     * not available in the caller's environment.
     *
     * @since 1.2
     */
    public static SecureRandom getInstance(String algorithm)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Generates a SecureRandom object for the specified PRNG
     * algorithm, as supplied from the specified provider, if such a
     * PRNG implementation is available from the provider.
     *
     * <p>Note that the returned instance of SecureRandom has not been seeded.
     * A call to the <code>setSeed</code> method will seed the SecureRandom
     * object.  If a call is not made to <code>setSeed</code>, the first call
     * to the <code>nextBytes</code> method will force the SecureRandom object
     * to seed itself.
     *
     * @param algorithm the name of the PRNG algorithm.
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard PRNG algorithm names.
     *
     * @param provider the name of the provider.
     *
     * @return the new SecureRandom object.
     *
     * @exception NoSuchAlgorithmException if the requested PRNG
     * implementation is not available from the provider.
     *
     * @exception NoSuchProviderException if the provider has not been
     * configured.
     *
     * @exception IllegalArgumentException if the provider name is null
     * or empty.
     *
     * @see Provider
     *
     * @since 1.2
     */
    public static SecureRandom getInstance(String algorithm, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        return null;
    }

    /** 
     * Generates a SecureRandom object for the specified PRNG
     * algorithm, as supplied from the specified provider, if such a
     * PRNG implementation is available from the provider.
     * Note: the <code>provider</code> doesn't have to be registered. 
     *
     * <p>Note that the returned instance of SecureRandom has not been seeded.
     * A call to the <code>setSeed</code> method will seed the SecureRandom
     * object.  If a call is not made to <code>setSeed</code>, the first call
     * to the <code>nextBytes</code> method will force the SecureRandom object
     * to seed itself.
     *
     * @param algorithm the name of the PRNG algorithm.
     * See Appendix A in the <a href=
     * "../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a> 
     * for information about standard PRNG algorithm names.
     *
     * @param provider the provider.
     *
     * @return the new SecureRandom object.
     *
     * @exception NoSuchAlgorithmException if the requested PRNG
     * implementation is not available from the provider.
     *
     * @exception IllegalArgumentException if the <code>provider</code> is
     * null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public static SecureRandom getInstance(String algorithm, Provider provider)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Returns the provider of this SecureRandom object.
     *
     * @return the provider of this SecureRandom object.
     */
    public final Provider getProvider() {
        return null;
    }

    /** 
     * Reseeds this random object. The given seed supplements, rather than
     * replaces, the existing seed. Thus, repeated calls are guaranteed
     * never to reduce randomness.
     *
     * @param seed the seed.
     *
     * @see #getSeed
     */
    public synchronized void setSeed(byte[] seed) { }

    /** 
     * Reseeds this random object, using the eight bytes contained 
     * in the given <code>long seed</code>. The given seed supplements, 
     * rather than replaces, the existing seed. Thus, repeated calls 
     * are guaranteed never to reduce randomness. 
     * 
     * <p>This method is defined for compatibility with 
     * <code>java.util.Random</code>.
     *
     * @param seed the seed.
     *
     * @see #getSeed
     */
    public void setSeed(long seed) { }

    /** 
     * Generates a user-specified number of random bytes.  This method is
     * used as the basis of all random entities returned by this class
     * (except seed bytes).
     * 
     * @param bytes the array to be filled in with random bytes.
     */
    public synchronized void nextBytes(byte[] bytes) { }

    /** 
     * Generates an integer containing the user-specified number of
     * pseudo-random bits (right justified, with leading zeros).  This
     * method overrides a <code>java.util.Random</code> method, and serves
     * to provide a source of random bits to all of the methods inherited
     * from that class (for example, <code>nextInt</code>,
     * <code>nextLong</code>, and <code>nextFloat</code>).
     *
     * @param numBits number of pseudo-random bits to be generated, where
     * 0 <= <code>numBits</code> <= 32.
     *
     * @return an <code>int</code> containing the user-specified number
     * of pseudo-random bits (right justified, with leading zeros).
     */
    protected final int next(int numBits) {
        return 0;
    }

    /** 
     * Returns the given number of seed bytes, computed using the seed
     * generation algorithm that this class uses to seed itself.  This
     * call may be used to seed other random number generators.
     *
     * <p>This method is only included for backwards compatibility. 
     * The caller is encouraged to use one of the alternative
     * <code>getInstance</code> methods to obtain a SecureRandom object, and
     * then call the <code>generateSeed</code> method to obtain seed bytes
     * from that object.
     *
     * @param numBytes the number of seed bytes to generate.
     * 
     * @return the seed bytes.
     *
     * @see #setSeed
     */
    public static byte[] getSeed(int numBytes) {
        return null;
    }

    /** 
     * Returns the given number of seed bytes, computed using the seed
     * generation algorithm that this class uses to seed itself.  This
     * call may be used to seed other random number generators.
     *
     * @param numBytes the number of seed bytes to generate.
     * 
     * @return the seed bytes.
     */
    public byte[] generateSeed(int numBytes) {
        return null;
    }
}
