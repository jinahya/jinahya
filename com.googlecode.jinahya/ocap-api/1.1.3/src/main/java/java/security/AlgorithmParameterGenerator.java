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
 * The <code>AlgorithmParameterGenerator</code> class is used to generate a
 * set of
 * parameters to be used with a certain algorithm. Parameter generators
 * are constructed using the <code>getInstance</code> factory methods
 * (static methods that return instances of a given class).
 * 
 * <P>The object that will generate the parameters can be initialized
 * in two different ways: in an algorithm-independent manner, or in an
 * algorithm-specific manner:
 *
 * <ul>
 * <li>The algorithm-independent approach uses the fact that all parameter
 * generators share the concept of a "size" and a
 * source of randomness. The measure of size is universally shared 
 * by all algorithm parameters, though it is interpreted differently
 * for different algorithms. For example, in the case of parameters for
 * the <i>DSA</i> algorithm, "size" corresponds to the size
 * of the prime modulus (in bits).
 * When using this approach, algorithm-specific parameter generation
 * values - if any - default to some standard values, unless they can be
 * derived from the specified size.<P>
 *
 * <li>The other approach initializes a parameter generator object
 * using algorithm-specific semantics, which are represented by a set of
 * algorithm-specific parameter generation values. To generate
 * Diffie-Hellman system parameters, for example, the parameter generation
 * values usually
 * consist of the size of the prime modulus and the size of the
 * random exponent, both specified in number of bits.
 * </ul>
 *
 * <P>In case the client does not explicitly initialize the
 * AlgorithmParameterGenerator
 * (via a call to an <code>init</code> method), each provider must supply (and
 * document) a default initialization. For example, the Sun provider uses a
 * default modulus prime size of 1024 bits for the generation of DSA
 * parameters.
 *
 * @author Jan Luehe
 *
 * @version 1.36, 02/02/00
 *
 * @see AlgorithmParameters
 * @see java.security.spec.AlgorithmParameterSpec
 *
 * @since 1.2
 */
public class AlgorithmParameterGenerator
{

    /** 
     * Creates an AlgorithmParameterGenerator object.
     *
     * @param paramGenSpi the delegate
     * @param provider the provider
     * @param algorithm the algorithm
     */
    protected AlgorithmParameterGenerator(AlgorithmParameterGeneratorSpi
        paramGenSpi, Provider provider, String algorithm)
    { }

    /** 
     * Returns the standard name of the algorithm this parameter
     * generator is associated with.
     * 
     * @return the string name of the algorithm. 
     */
    public final String getAlgorithm() {
        return null;
    }

    /** 
     * Generates an AlgorithmParameterGenerator object that implements the 
     * specified digest algorithm. If the default provider package
     * provides an implementation of the requested digest algorithm,
     * an instance of AlgorithmParameterGenerator containing that
     * implementation 
     * is returned. If the algorithm is not available in the default 
     * package, other packages are searched.
     *
     * @param algorithm the string name of the algorithm this
     * parameter generator is associated with.
     *
     * @return the new AlgorithmParameterGenerator object.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available in the environment.  
     */
    public static AlgorithmParameterGenerator getInstance(String algorithm)
        throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Generates an AlgorithmParameterGenerator object for the requested
     * algorithm, as supplied from the specified provider, 
     * if such a parameter generator is available from the provider.
     *
     * @param algorithm the string name of the algorithm.
     *
     * @param provider the string name of the provider.
     *
     * @return the new AlgorithmParameterGenerator object.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available from the provider.
     *
     * @exception NoSuchProviderException if the provider is not
     * available in the environment.
     *
     * @exception IllegalArgumentException if the provider name is null
     * or empty.
     * 
     * @see Provider
     */
    public static AlgorithmParameterGenerator getInstance(String algorithm,
        String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException
    {
        return null;
    }

    /** 
     * Generates an AlgorithmParameterGenerator object for the requested
     * algorithm, as supplied from the specified provider, 
     * if such a parameter generator is available from the provider.
     * Note: the <code>provider</code> doesn't have to be registered. 
     *
     * @param algorithm the string name of the algorithm.
     *
     * @param provider the provider.
     *
     * @return the new AlgorithmParameterGenerator object.
     *
     * @exception NoSuchAlgorithmException if the algorithm is
     * not available from the provider.
     *
     * @exception IllegalArgumentException if the <code>provider</code> is
     * null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public static AlgorithmParameterGenerator getInstance(String algorithm,
        Provider provider) throws NoSuchAlgorithmException
    {
        return null;
    }

    /** 
     * Returns the provider of this algorithm parameter generator object.
     * 
     * @return the provider of this algorithm parameter generator object
     */
    public final Provider getProvider() {
        return null;
    }

    /** 
     * Initializes this parameter generator for a certain size.
     * To create the parameters, the <code>SecureRandom</code>
     * implementation of the highest-priority installed provider is used as
     * the source of randomness.
     * (If none of the installed providers supply an implementation of
     * <code>SecureRandom</code>, a system-provided source of randomness is
     * used.)
     *
     * @param size the size (number of bits).
     */
    public final void init(int size) { }

    /** 
     * Initializes this parameter generator for a certain size and source
     * of randomness.
     *
     * @param size the size (number of bits).
     * @param random the source of randomness.
     */
    public final void init(int size, SecureRandom random) { }

    /** 
     * Initializes this parameter generator with a set of algorithm-specific
     * parameter generation values.
     * To generate the parameters, the <code>SecureRandom</code>
     * implementation of the highest-priority installed provider is used as
     * the source of randomness.
     * (If none of the installed providers supply an implementation of
     * <code>SecureRandom</code>, a system-provided source of randomness is
     * used.)
     *
     * @param genParamSpec the set of algorithm-specific parameter generation values.
     *
     * @exception InvalidAlgorithmParameterException if the given parameter
     * generation values are inappropriate for this parameter generator.
     */
    public final void init(AlgorithmParameterSpec genParamSpec)
        throws InvalidAlgorithmParameterException
    { }

    /** 
     * Initializes this parameter generator with a set of algorithm-specific
     * parameter generation values.
     *
     * @param genParamSpec the set of algorithm-specific parameter generation values.
     * @param random the source of randomness.
     *
     * @exception InvalidAlgorithmParameterException if the given parameter
     * generation values are inappropriate for this parameter generator.
     */
    public final void init(AlgorithmParameterSpec genParamSpec, SecureRandom
        random) throws InvalidAlgorithmParameterException
    { }

    /** 
     * Generates the parameters.
     *
     * @return the new AlgorithmParameters object.
     */
    public final AlgorithmParameters generateParameters() {
        return null;
    }
}
