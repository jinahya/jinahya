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
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the <code>AlgorithmParameterGenerator</code> class, which 
 * is used to generate a set of parameters to be used with a certain algorithm.
 *
 * <p> All the abstract methods in this class must be implemented by each 
 * cryptographic service provider who wishes to supply the implementation
 * of a parameter generator for a particular algorithm.
 *
 * <p> In case the client does not explicitly initialize the
 * AlgorithmParameterGenerator (via a call to an <code>engineInit</code>
 * method), each provider must supply (and document) a default initialization.
 * For example, the Sun provider uses a default modulus prime size of 1024
 * bits for the generation of DSA parameters.
 *
 * @author Jan Luehe
 *
 * @version 1.11, 02/02/00
 *
 * @see AlgorithmParameterGenerator
 * @see AlgorithmParameters
 * @see java.security.spec.AlgorithmParameterSpec
 *
 * @since 1.2
 */
public abstract class AlgorithmParameterGeneratorSpi
{

    public AlgorithmParameterGeneratorSpi() { }

    /** 
     * Initializes this parameter generator for a certain size
     * and source of randomness.
     *
     * @param size the size (number of bits).
     * @param random the source of randomness.
     */
    protected abstract void engineInit(int size, SecureRandom random);

    /** 
     * Initializes this parameter generator with a set of
     * algorithm-specific parameter generation values.
     *
     * @param genParamSpec the set of algorithm-specific parameter generation values.
     * @param random the source of randomness.
     *
     * @exception InvalidAlgorithmParameterException if the given parameter
     * generation values are inappropriate for this parameter generator.
     */
    protected abstract void engineInit(AlgorithmParameterSpec genParamSpec,
        SecureRandom random) throws InvalidAlgorithmParameterException;

    /** 
     * Generates the parameters.
     *
     * @return the new AlgorithmParameters object.
     */
    protected abstract AlgorithmParameters engineGenerateParameters();
}
