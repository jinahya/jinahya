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

/** 
 * This class defines the <i>Service Provider Interface</i> (<b>SPI</b>)
 * for the <code>Signature</code> class, which is used to provide the
 * functionality of a digital signature algorithm. Digital signatures are used
 * for authentication and integrity assurance of digital data.
 *.
 * <p> All the abstract methods in this class must be implemented by each
 * cryptographic service provider who wishes to supply the implementation
 * of a particular signature algorithm.
 *
 * @author Benjamin Renaud 
 *
 * @version 1.15, 05/03/00
 *
 * @see Signature
 */
public abstract class SignatureSpi
{
    /** 
     * Application-specified source of randomness. 
     */
    protected SecureRandom appRandom;

    public SignatureSpi() { }

    /** 
     * Initializes this signature object with the specified
     * public key for verification operations.
     *
     * @param publicKey the public key of the identity whose signature is
     * going to be verified.
     * 
     * @exception InvalidKeyException if the key is improperly
     * encoded, parameters are missing, and so on.  
     */
    protected abstract void engineInitVerify(PublicKey publicKey)
        throws InvalidKeyException;

    /** 
     * Initializes this signature object with the specified
     * private key for signing operations.
     *
     * @param privateKey the private key of the identity whose signature
     * will be generated.
     *
     * @exception InvalidKeyException if the key is improperly
     * encoded, parameters are missing, and so on. 
     */
    protected abstract void engineInitSign(PrivateKey privateKey)
        throws InvalidKeyException;

    /** 
     * Initializes this signature object with the specified
     * private key and source of randomness for signing operations.
     *
     * <p>This concrete method has been added to this previously-defined
     * abstract class. (For backwards compatibility, it cannot be abstract.)
     *
     * @param privateKey the private key of the identity whose signature
     * will be generated.
     * @param random the source of randomness
     *
     * @exception InvalidKeyException if the key is improperly
     * encoded, parameters are missing, and so on. 
     */
    protected void engineInitSign(PrivateKey privateKey, SecureRandom random)
        throws InvalidKeyException
    { }

    /** 
     * Updates the data to be signed or verified
     * using the specified byte.
     *
     * @param b the byte to use for the update.
     *
     * @exception SignatureException if the engine is not initialized
     * properly.
     */
    protected abstract void engineUpdate(byte b) throws SignatureException;

    /** 
     * Updates the data to be signed or verified, using the 
     * specified array of bytes, starting at the specified offset.
     *
     * @param b the array of bytes  
     * @param off the offset to start from in the array of bytes 
     * @param len the number of bytes to use, starting at offset
     *
     * @exception SignatureException if the engine is not initialized 
     * properly
     */
    protected abstract void engineUpdate(byte[] b, int off, int len)
        throws SignatureException;

    /** 
     * Returns the signature bytes of all the data
     * updated so far.    
     * The format of the signature depends on the underlying 
     * signature scheme.
     *
     * @return the signature bytes of the signing operation's result.
     *
     * @exception SignatureException if the engine is not
     * initialized properly.  
     */
    protected abstract byte[] engineSign() throws SignatureException;

    /** 
     * Finishes this signature operation and stores the resulting signature
     * bytes in the provided buffer <code>outbuf</code>, starting at
     * <code>offset</code>.
     * The format of the signature depends on the underlying 
     * signature scheme.
     * 
     * <p>The signature implementation is reset to its initial state
     * (the state it was in after a call to one of the
     * <code>engineInitSign</code> methods)
     * and can be reused to generate further signatures with the same private
     * key.
     *
     * This method should be abstract, but we leave it concrete for
     * binary compatibility.  Knowledgeable providers should override this
     * method.
     *
     * @param outbuf buffer for the signature result.
     *
     * @param offset offset into <code>outbuf</code> where the signature is
     * stored.
     *
     * @param len number of bytes within <code>outbuf</code> allotted for the
     * signature.
     * Both this default implementation and the SUN provider do not
     * return partial digests. If the value of this parameter is less
     * than the actual signature length, this method will throw a
     * SignatureException.
     * This parameter is ignored if its value is greater than or equal to
     * the actual signature length.
     *
     * @return the number of bytes placed into <code>outbuf</code>
     * 
     * @exception SignatureException if an error occurs or <code>len</code>
     * is less than the actual signature length.
     *
     * @since 1.2
     */
    protected int engineSign(byte[] outbuf, int offset, int len)
        throws SignatureException
    {
        return 0;
    }

    /** 
     * Verifies the passed-in signature.   
     * 
     * @param sigBytes the signature bytes to be verified.
     *
     * @return true if the signature was verified, false if not. 
     *
     * @exception SignatureException if the engine is not initialized 
     * properly, or the passed-in signature is improperly encoded or 
     * of the wrong type, etc.  
     */
    protected abstract boolean engineVerify(byte[] sigBytes)
        throws SignatureException;

    /** 
     * Verifies the passed-in signature in the specified array
     * of bytes, starting at the specified offset.
     *
     * <p> Note: Subclasses should overwrite the default implementation.
     *
     * 
     * @param sigBytes the signature bytes to be verified.
     * @param offset the offset to start from in the array of bytes.
     * @param length the number of bytes to use, starting at offset.
     *
     * @return true if the signature was verified, false if not. 
     *
     * @exception SignatureException if the engine is not initialized 
     * properly, or the passed-in signature is improperly encoded or 
     * of the wrong type, etc.  
     */
    protected boolean engineVerify(byte[] sigBytes, int offset, int length)
        throws SignatureException
    {
        return false;
    }

    /** 
     * <p>This method is overridden by providers to initialize
     * this signature engine with the specified parameter set.
     *
     * @param params the parameters
     *
     * @exception UnsupportedOperationException if this method is not
     * overridden by a provider
     *
     * @exception InvalidAlgorithmParameterException if this method is
     * overridden by a provider and the the given parameters
     * are inappropriate for this signature engine
     */
    protected void engineSetParameter(AlgorithmParameterSpec params)
        throws InvalidAlgorithmParameterException
    { }

    /** 
     * <p>This method is overridden by providers to return the
     * parameters used with this signature engine, or null 
     * if this signature engine does not use any parameters.
     *
     * <p>The returned parameters may be the same that were used to initialize
     * this signature engine, or may contain a combination of default and 
     * randomly generated parameter values used by the underlying signature
     * implementation if this signature engine requires algorithm parameters 
     * but was not initialized with any.
     * 
     * @return the parameters used with this signature engine, or null if this
     * signature engine does not use any parameters
     *
     * @exception UnsupportedOperationException if this method is
     * not overridden by a provider
     */
    protected AlgorithmParameters engineGetParameters() {
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
