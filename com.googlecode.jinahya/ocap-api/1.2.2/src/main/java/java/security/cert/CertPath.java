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


  


package java.security.cert;

import java.io.ByteArrayInputStream;
import java.io.NotSerializableException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/** 
 * An immutable sequence of certificates (a certification path).
 * <p>
 * This is an abstract class that defines the methods common to all
 * <code>CertPath</code>s. Subclasses can handle different kinds of
 * certificates (X.509, PGP, etc.).
 * <p>
 * All <code>CertPath</code> objects have a type, a list of 
 * <code>Certificate</code>s, and one or more supported encodings. Because the 
 * <code>CertPath</code> class is immutable, a <code>CertPath</code> cannot 
 * change in any externally visible way after being constructed. This 
 * stipulation applies to all public fields and methods of this class and any 
 * added or overridden by subclasses.
 * <p>
 * The type is a <code>String</code> that identifies the type of
 * <code>Certificate</code>s in the certification path. For each
 * certificate <code>cert</code> in a certification path <code>certPath</code>,
 * <code>cert.getType().equals(certPath.getType())</code> must be
 * <code>true</code>.
 * <p>
 * The list of <code>Certificate</code>s is an ordered <code>List</code> of
 * zero or more <code>Certificate</code>s. This <code>List</code> and all
 * of the <code>Certificate</code>s contained in it must be immutable.
 * <p>
 * Each <code>CertPath</code> object must support one or more encodings
 * so that the object can be translated into a byte array for storage or
 * transmission to other parties. Preferably, these encodings should be
 * well-documented standards (such as PKCS#7). One of the encodings supported
 * by a <code>CertPath</code> is considered the default encoding. This
 * encoding is used if no encoding is explicitly requested (for the
 * {@link #getEncoded() getEncoded()} method, for instance).
 * <p>
 * All <code>CertPath</code> objects are also <code>Serializable</code>. 
 * <code>CertPath</code> objects are resolved into an alternate 
 * {@link CertPathRep CertPathRep} object during serialization. This allows
 * a <code>CertPath</code> object to be serialized into an equivalent
 * representation regardless of its underlying implementation.
 * <p>
 * By convention, X.509 <code>CertPath</code>s (consisting of
 * <code>X509Certificate</code>s), are ordered starting with the target 
 * certificate and ending with a certificate issued by the trust anchor. That 
 * is, the issuer of one certificate is the subject of the following one. 
 * Unvalidated X.509 <code>CertPath</code>s 
 * may not follow these conventions.
 * <p>
 * <b>Concurrent Access</b>
 * <p>
 * All <code>CertPath</code> objects must be thread-safe. That is, multiple
 * threads may concurrently invoke the methods defined in this class on a
 * single <code>CertPath</code> object (or more than one) with no
 * ill effects. This is also true for the <code>List</code> returned by
 * <code>CertPath.getCertificates</code>.
 * <p>
 * Requiring <code>CertPath</code> objects to be immutable and thread-safe
 * allows them to be passed around to various pieces of code without worrying
 * about coordinating access.  Providing this thread-safety is
 * generally not difficult, since the <code>CertPath</code> and
 * <code>List</code> objects in question are immutable.
 *
 * @see CertificateFactory
 *
 * @version 1.9 03/12/05
 * @author	Yassir Elley
 * @since	1.4
 */
public abstract class CertPath implements Serializable
{
    private String type;

    /** 
     * Creates a <code>CertPath</code> of the specified type.
     * <p>
     * This constructor is protected because most users should use a
     * <code>CertificateFactory</code> to create <code>CertPath</code>s.
     *
     * @param type the standard name of the type of
     * <code>Certificate</code>s in this path
     */
    protected CertPath(String type) { }

    /** 
     * Returns the type of <code>Certificate</code>s in this certification
     * path. This is the same string that would be returned by
     * {@link java.security.cert.Certificate#getType() cert.getType()}
     * for all <code>Certificate</code>s in the certification path.
     *
     * @return the type of <code>Certificate</code>s in this certification
     * path (never null)
     */
    public String getType() {
        return null;
    }

    /** 
     * Returns an iteration of the encodings supported by this certification 
     * path, with the default encoding first. Attempts to modify the returned
     * <code>Iterator</code> via its <code>remove</code> method result in an
     * <code>UnsupportedOperationException</code>.
     *
     * @return an <code>Iterator</code> over the names of the supported
     *         encodings (as Strings)
     */
    public abstract Iterator getEncodings();

    /** 
     * Compares this certification path for equality with the specified
     * object. Two <code>CertPath</code>s are equal if and only if their
     * types are equal and their certificate <code>List</code>s (and by
     * implication the <code>Certificate</code>s in those <code>List</code>s)
     * are equal. A <code>CertPath</code> is never equal to an object that is
     * not a <code>CertPath</code>.
     * <p>
     * This algorithm is implemented by this method. If it is overridden,
     * the behavior specified here must be maintained.
     *
     * @param other the object to test for equality with this certification path
     * @return true if the specified object is equal to this certification path,
     * false otherwise
     */
    public boolean equals(Object other) {
        return false;
    }

    /** 
     * Returns the hashcode for this certification path. The hash code of
     * a certification path is defined to be the result of the following
     * calculation:
     * <pre><code>
     *  hashCode = path.getType().hashCode();
     *  hashCode = 31*hashCode + path.getCertificates().hashCode();
     * </code></pre>
     * This ensures that <code>path1.equals(path2)</code> implies that
     * <code>path1.hashCode()==path2.hashCode()</code> for any two certification
     * paths, <code>path1</code> and <code>path2</code>, as required by the
     * general contract of <code>Object.hashCode</code>.
     *
     * @return the hashcode value for this certification path
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns a string representation of this certification path.
     * This calls the <code>toString</code> method on each of the
     * <code>Certificate</code>s in the path.
     *
     * @return a string representation of this certification path
     */
    public String toString() {
        return null;
    }

    /** 
     * Returns the encoded form of this certification path, using the default
     * encoding.
     *
     * @return the encoded bytes
     * @exception CertificateEncodingException if an encoding error occurs
     */
    public abstract byte[] getEncoded() throws CertificateEncodingException;

    /** 
     * Returns the encoded form of this certification path, using the
     * specified encoding.
     *
     * @param encoding the name of the encoding to use
     * @return the encoded bytes
     * @exception CertificateEncodingException if an encoding error occurs or
     *   the encoding requested is not supported
     */
    public abstract byte[] getEncoded(String encoding)
        throws CertificateEncodingException;

    /** 
     * Returns the list of certificates in this certification path.
     * The <code>List</code> returned must be immutable and thread-safe.
     *
     * @return an immutable <code>List</code> of <code>Certificate</code>s
     *         (may be empty, but not null)
     */
    public abstract List getCertificates();

    /** 
     * Replaces the <code>CertPath</code> to be serialized with a 
     * <code>CertPathRep</code> object.
     *
     * @return the <code>CertPathRep</code> to be serialized
     *
     * @throws ObjectStreamException if a <code>CertPathRep</code> object 
     * representing this certification path could not be created
     */
    protected Object writeReplace() throws ObjectStreamException {
        return null;
    }


    /** 
     * Alternate <code>CertPath</code> class for serialization.
     */
    protected static class CertPathRep implements Serializable
    {
        /** The Certificate type  */
        private String type;

        /** The encoded form of the cert path  */
        private byte[] data;

        /** 
         * Creates a <code>CertPathRep</code> with the specified 
         * type and encoded form of a certification path.
         *
         * @param type the standard name of a <code>CertPath</code> type
         * @param data the encoded form of the certification path
         */
        protected CertPathRep(String type, byte[] data) { }

        /** 
         * Returns a <code>CertPath</code> constructed from the type and data.
         *
         * @return the resolved <code>CertPath</code> object
         *
         * @throws ObjectStreamException if a <code>CertPath</code> could not
         * be constructed
         */
        protected Object readResolve() throws ObjectStreamException {
            return null;
        }

    }
}
