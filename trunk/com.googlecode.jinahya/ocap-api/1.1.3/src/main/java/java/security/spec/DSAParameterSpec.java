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
 * This class specifies the set of parameters used with the DSA algorithm.
 * 
 * @author Jan Luehe
 *
 * @version 1.13, 02/02/00
 *
 * @see AlgorithmParameterSpec
 *
 * @since 1.2
 */
public class DSAParameterSpec
    implements AlgorithmParameterSpec, java.security.interfaces.DSAParams
{

    /** 
     * Creates a new DSAParameterSpec with the specified parameter values.
     * 
     * @param p the prime.
     * 
     * @param q the sub-prime.
     * 
     * @param g the base.
     */
    public DSAParameterSpec(BigInteger p, BigInteger q, BigInteger g) { }

    /** 
     * Returns the prime <code>p</code>.
     *
     * @return the prime <code>p</code>.
     */
    public BigInteger getP() {
        return null;
    }

    /** 
     * Returns the sub-prime <code>q</code>.
     *
     * @return the sub-prime <code>q</code>.
     */
    public BigInteger getQ() {
        return null;
    }

    /** 
     * Returns the base <code>g</code>.
     *
     * @return the base <code>g</code>.
     */
    public BigInteger getG() {
        return null;
    }
}
