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


  


package java.security.interfaces;

import java.security.*;

/** 
 * An interface to an object capable of generating DSA key pairs. 
 *
 * <p>The <code>initialize</code> methods may each be called any number 
 * of times. If no <code>initialize</code> method is called on a 
 * DSAKeyPairGenerator, the default is to generate 1024-bit keys, using 
 * precomputed p, q and g parameters and an instance of SecureRandom as 
 * the random bit source.
 * 
 * <p>Users wishing to indicate DSA-specific parameters, and to generate a key 
 * pair suitable for use with the DSA algorithm typically
 * 
 * <ol>
 * 
 * <li>Get a key pair generator for the DSA algorithm by calling the 
 * KeyPairGenerator <code>getInstance</code> method with "DSA" 
 * as its argument.<p> 
 * 
 * <li>Initialize the generator by casting the result to a DSAKeyPairGenerator
 * and calling one of the 
 * <code>initialize</code> methods from this DSAKeyPairGenerator interface.<p>
 * 
 * <li>Generate a key pair by calling the <code>generateKeyPair</code> 
 * method from the KeyPairGenerator class.
 * 
 * </ol> 
 *
 * <p>Note: it is not always necessary to do do algorithm-specific
 * initialization for a DSA key pair generator. That is, it is not always
 * necessary to call an <code>initialize</code> method in this interface.
 * Algorithm-independent initialization using the <code>initialize</code> method
 * in the KeyPairGenerator
 * interface is all that is needed when you accept defaults for algorithm-specific
 * parameters.
 * 
 * @see java.security.KeyPairGenerator
 */
public interface DSAKeyPairGenerator
{

    /** 
     * Initializes the key pair generator using p, q and g, the DSA
     * family parameters.
     *
     * @param params the parameters to use to generate the keys.
     *
     * @param random the random bit source to use to generate 
     * key bits.
     *
     * @exception InvalidParameterException if the parameters passed are
     * invalid or null.
     */
    public void initialize(DSAParams params, SecureRandom random)
        throws InvalidParameterException;

    /** 
     * Initializes the key pair generator for a given modulus length,
     * without parameters. 
     *
     * <p>If <code>genParams</code> is true, this method will generate new 
     * p, q and g parameters. If it is false, the method will use precomputed
     * parameters for the modulus length requested. If there are no
     * precomputed parameters for that modulus length, an exception will be 
     * thrown. It is guaranteed that there will always be
     * default parameters for modulus lengths of 512 and 1024 bits.
     *
     * @param modlen the modulus length, in bits. Valid values are any
     * multiple of 8 between 512 and 1024, inclusive.
     *
     * @param random the random bit source to use to generate 
     * key bits.
     *
     * @param genParams whether or not to generate new parameters for
     * the modulus length requested.
     *
     * @exception InvalidParameterException if the modulus length is not
     * between 512 and 1024, or if genParams is false and there are
     * not precomputed parameters for the modulus length requested.  
     */
    public void initialize(int modlen, boolean genParams, SecureRandom random)
        throws InvalidParameterException;
}
