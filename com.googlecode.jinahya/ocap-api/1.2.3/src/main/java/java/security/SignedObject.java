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

import java.io.*;

/** 
 * <p> SignedObject is a class for the purpose of creating authentic
 * runtime objects whose integrity cannot be compromised without being
 * detected.
 *
 * <p> More specifically, a SignedObject contains another Serializable
 * object, the (to-be-)signed object and its signature.
 *
 * <p> The signed object is a "deep copy" (in serialized form) of an
 * original object.  Once the copy is made, further manipulation of
 * the original object has no side effect on the copy.
 *
 * <p> The underlying signing algorithm is designated by the Signature
 * object passed to the constructor and the <code>verify</code> method.
 * A typical usage for signing is the following:
 *
 * <p> <code> <pre>
 * Signature signingEngine = Signature.getInstance(algorithm,
 *                                                 provider);
 * SignedObject so = new SignedObject(myobject, signingKey,
 *                                    signingEngine);
 * </pre> </code>
 *
 * <p> A typical usage for verification is the following (having
 * received SignedObject <code>so</code>):
 *
 * <p> <code> <pre>
 * Signature verificationEngine =
 *     Signature.getInstance(algorithm, provider);
 * if (so.verify(publickey, verificationEngine))
 *     try {
 *         Object myobj = so.getObject();
 *     } catch (java.lang.ClassNotFoundException e) {};
 * </pre> </code>
 *
 * <p> Several points are worth noting.  First, there is no need to
 * initialize the signing or verification engine, as it will be
 * re-initialized inside the constructor and the <code>verify</code>
 * method. Secondly, for verification to succeed, the specified
 * public key must be the public key corresponding to the private key
 * used to generate the SignedObject.
 *
 * <p> More importantly, for flexibility reasons, the
 * constructor and <code>verify</code> method allow for
 * customized signature engines, which can implement signature
 * algorithms that are not installed formally as part of a crypto
 * provider.  However, it is crucial that the programmer writing the
 * verifier code be aware what <code>Signature</code> engine is being
 * used, as its own implementation of the <code>verify</code> method
 * is invoked to verify a signature.  In other words, a malicious
 * <code>Signature</code> may choose to always return true on
 * verification in an attempt to bypass a security check.
 *
 * <p> The signature algorithm can be, among others, the NIST standard
 * DSA, using DSA and SHA-1.  The algorithm is specified using the
 * same convention as that for signatures. The DSA algorithm using the
 * SHA-1 message digest algorithm can be specified, for example, as
 * "SHA/DSA" or "SHA-1/DSA" (they are equivalent).  In the case of
 * RSA, there are multiple choices for the message digest algorithm,
 * so the signing algorithm could be specified as, for example,
 * "MD2/RSA", "MD5/RSA" or "SHA-1/RSA".  The algorithm name must be
 * specified, as there is no default.
 *
 * <p> The name of the Cryptography Package Provider is designated
 * also by the Signature parameter to the constructor and the
 * <code>verify</code> method.  If the provider is not
 * specified, the default provider is used.  Each installation can
 * be configured to use a particular provider as default.
 *
 * <p> Potential applications of SignedObject include: 
 * <ul>
 * <li> It can be used
 * internally to any Java runtime as an unforgeable authorization
 * token -- one that can be passed around without the fear that the
 * token can be maliciously modified without being detected. 
 * <li> It
 * can be used to sign and serialize data/object for storage outside
 * the Java runtime (e.g., storing critical access control data on
 * disk). 
 * <li> Nested SignedObjects can be used to construct a logical
 * sequence of signatures, resembling a chain of authorization and
 * delegation.
 * </ul>
 *
 * @see Signature
 *
 * @version 	1.37, 02/02/00
 * @author Li Gong
 */
public final class SignedObject implements Serializable
{
    private byte[] content;

    private byte[] signature;

    private String thealgorithm;

    /** 
     * Constructs a SignedObject from any Serializable object.
     * The given object is signed with the given signing key, using the
     * designated signature engine.
     *
     * @param object the object to be signed. 
     * @param signingKey the private key for signing.
     * @param signingEngine the signature signing engine.
     *
     * @exception IOException if an error occurs during serialization
     * @exception InvalidKeyException if the key is invalid.
     * @exception SignatureException if signing fails.
     */
    public SignedObject(Serializable object, PrivateKey signingKey, Signature
        signingEngine)
        throws IOException, InvalidKeyException, SignatureException
    { }

    /** 
     * Retrieves the encapsulated object.
     * The encapsulated object is de-serialized before it is returned.
     * 
     * @return the encapsulated object.
     *
     * @exception IOException if an error occurs during de-serialization
     * @exception ClassNotFoundException if an error occurs during 
     * de-serialization
     */
    public Object getObject() throws IOException, ClassNotFoundException {
        return null;
    }

    /** 
     * Retrieves the signature on the signed object, in the form of a
     * byte array.
     * 
     * @return the signature.
     */
    public byte[] getSignature() {
        return null;
    }

    /** 
     * Retrieves the name of the signature algorithm.
     *
     * @return the signature algorithm name.
     */
    public String getAlgorithm() {
        return null;
    }

    /** 
     * Verifies that the signature in this SignedObject is the valid
     * signature for the object stored inside, with the given
     * verification key, using the designated verification engine.
     * 
     * @param verificationKey the public key for verification.
     * @param verificationEngine the signature verification engine.
     *
     * @exception SignatureException if signature verification failed.
     * @exception InvalidKeyException if the verification key is invalid.
     *
     * @return <tt>true</tt> if the signature 
     * is valid, <tt>false</tt> otherwise
     */
    public boolean verify(PublicKey verificationKey, Signature
        verificationEngine) throws InvalidKeyException, SignatureException
    {
        return false;
    }

    /** 
     * readObject is called to restore the state of the SignedObject from
     * a stream.
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }
}
