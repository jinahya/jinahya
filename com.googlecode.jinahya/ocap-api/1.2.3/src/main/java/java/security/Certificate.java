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

import java.util.Date;

/** 
 * <p>This is an interface of abstract methods for managing a
 * variety of identity certificates.
 * An identity certificate is a guarantee by a principal that
 * a public key is that of another principal.  (A principal represents
 * an entity such as an individual user, a group, or a corporation.)
 *
 * <p>In particular, this interface is intended to be a common
 * abstraction for constructs that have different formats but
 * important common uses.  For example, different types of
 * certificates, such as X.509 certificates and PGP certificates,
 * share general certificate functionality (the need to encode and
 * decode certificates) and some types of information, such as a
 * public key, the principal whose key it is, and the guarantor
 * guaranteeing that the public key is that of the specified
 * principal. So an implementation of X.509 certificates and an
 * implementation of PGP certificates can both utilize the Certificate
 * interface, even though their formats and additional types and
 * amounts of information stored are different.
 *
 * <p><b>Important</b>: This interface is useful for cataloging and
 * grouping objects sharing certain common uses. It does not have any
 * semantics of its own. In particular, a Certificate object does not
 * make any statement as to the <i>validity</i> of the binding. It is
 * the duty of the application implementing this interface to verify
 * the certificate and satisfy itself of its validity.
 *
 * @version 	1.31, 02/02/00
 * @author Benjamin Renaud 
 * @deprecated A new certificate handling package is created in the Java 2 platform.
 *             This Certificate interface is entirely deprecated and
 *             is here to allow for a smooth transition to the new
 *             package.
 * @see java.security.cert.Certificate
 */
public interface Certificate
{

    /** 
     * Returns the guarantor of the certificate, that is, the principal
     * guaranteeing that the public key associated with this certificate
     * is that of the principal associated with this certificate. For X.509
     * certificates, the guarantor will typically be a Certificate Authority
     * (such as the United States Postal Service or Verisign, Inc.).
     *
     * @return the guarantor which guaranteed the principal-key
     * binding.
     */
    public Principal getGuarantor();

    /** 
     * Returns the principal of the principal-key pair being guaranteed by
     * the guarantor.
     *
     * @return the principal to which this certificate is bound.  
     */
    public Principal getPrincipal();

    /** 
     * Returns the key of the principal-key pair being guaranteed by
     * the guarantor.
     * 
     * @return the public key that this certificate certifies belongs
     * to a particular principal.  
     */
    public PublicKey getPublicKey();

    /** 
     * Encodes the certificate to an output stream in a format that can
     * be decoded by the <code>decode</code> method.
     *
     * @param stream the output stream to which to encode the
     * certificate.
     *
     * @exception KeyException if the certificate is not
     * properly initialized, or data is missing, etc.
     *
     * @exception IOException if a stream exception occurs while
     * trying to output the encoded certificate to the output stream.
     *
     * @see #decode 
     * @see #getFormat
     */
    public void encode(OutputStream stream) throws KeyException, IOException;

    /** 
     * Decodes a certificate from an input stream. The format should be
     * that returned by <code>getFormat</code> and produced by 
     * <code>encode</code>.
     *
     * @param stream the input stream from which to fetch the data
     * being decoded.
     * 
     * @exception KeyException if the certificate is not properly initialized,
     * or data is missing, etc.
     *
     * @exception IOException if an exception occurs while trying to input
     * the encoded certificate from the input stream.
     *
     * @see #encode 
     * @see #getFormat
     */
    public void decode(InputStream stream) throws KeyException, IOException;

    /** 
     * Returns the name of the coding format. This is used as a hint to find
     * an appropriate parser. It could be "X.509", "PGP", etc. This is
     * the format produced and understood by the <code>encode</code>
     * and <code>decode</code> methods.
     * 
     * @return the name of the coding format.
     */
    public String getFormat();

    /** 
     * Returns a string that represents the contents of the certificate.
     *
     * @param detailed whether or not to give detailed information
     * about the certificate
     * 
     * @return a string representing the contents of the certificate
     */
    public String toString(boolean detailed);
}
