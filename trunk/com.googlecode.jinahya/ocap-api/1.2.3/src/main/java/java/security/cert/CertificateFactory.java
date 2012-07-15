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

import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.security.Provider;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

/** 
 * This class defines the functionality of a certificate factory, which is
 * used to generate certificate, certification path (<code>CertPath</code>)
 * and certificate revocation list (CRL) objects from their encodings.
 *
 * <p>For encodings consisting of multiple certificates, use
 * <code>generateCertificates</code> when you want to
 * parse a collection of possibly unrelated certificates.
 *
 * <p>A certificate factory for X.509 must return certificates that are an
 * instance of <code>java.security.cert.X509Certificate</code>, and CRLs
 * that are an instance of <code>java.security.cert.X509CRL</code>.
 *
 * <p>The following example reads a file with Base64 encoded certificates,
 * which are each bounded at the beginning by -----BEGIN CERTIFICATE-----, and
 * bounded at the end by -----END CERTIFICATE-----. We convert the
 * <code>FileInputStream</code> (which does not support <code>mark</code>
 * and <code>reset</code>) to a <code>BufferedInputStream</code> (which
 * supports those methods), so that each call to
 * <code>generateCertificate</code> consumes only one certificate, and the
 * read position of the input stream is positioned to the next certificate in
 * the file:<p>
 *
 * <pre>
 * FileInputStream fis = new FileInputStream(filename);
 * BufferedInputStream bis = new BufferedInputStream(fis);
 *
 * CertificateFactory cf = CertificateFactory.getInstance("X.509");
 *
 * while (bis.available() > 0) {
 *    Certificate cert = cf.generateCertificate(bis);
 *    System.out.println(cert.toString());
 * }
 * </pre>
 *
 * <p>The following example parses a PKCS#7-formatted certificate reply stored
 * in a file and extracts all the certificates from it:<p>
 *
 * <pre>
 * FileInputStream fis = new FileInputStream(filename);
 * CertificateFactory cf = CertificateFactory.getInstance("X.509");
 * Collection c = cf.generateCertificates(fis);
 * Iterator i = c.iterator();
 * while (i.hasNext()) {
 *    Certificate cert = (Certificate)i.next();
 *    System.out.println(cert);
 * }
 * </pre>
 *
 * @author Hemma Prafullchandra
 * @author Jan Luehe
 * @author Sean Mullan
 *
 * @version 1.15, 02/02/00
 *
 * @see Certificate
 * @see X509Certificate
 * @see CertPath
 * @see CRL
 * @see X509CRL
 *
 * @since 1.2
 */
public class CertificateFactory
{

    /** 
     * Creates a CertificateFactory object of the given type, and encapsulates
     * the given provider implementation (SPI object) in it.
     *
     * @param certFacSpi the provider implementation.
     * @param provider the provider.
     * @param type the certificate type.
     */
    protected CertificateFactory(CertificateFactorySpi certFacSpi, Provider
        provider, String type)
    { }

    /** 
     * Generates a certificate factory object that implements the
     * specified certificate type. If the default provider package
     * provides an implementation of the requested certificate type,
     * an instance of certificate factory containing that
     * implementation is returned.
     * If the type is not available in the default
     * package, other packages are searched.
     *
     * @param type the name of the requested certificate type.
     * See Appendix A in the <a href=
     * "../../../../guide/security/CryptoSpec.html#AppA">
     * Java Cryptography Architecture API Specification &amp; Reference </a>
     * for information about standard certificate types.
     *
     * @return a certificate factory object for the specified type.
     *
     * @exception CertificateException if the requested certificate type is
     * not available in the default provider package or any of the other
     * provider packages that were searched.
     */
    public static final CertificateFactory getInstance(String type)
        throws CertificateException
    {
        return null;
    }

    /** 
     * Generates a certificate factory object for the specified
     * certificate type from the specified provider.
     *
     * @param type the certificate type
     * @param provider the name of the provider.
     *
     * @return a certificate factory object for the specified type.
     *
     * @exception CertificateException if the certificate type is
     * not available from the specified provider.
     *
     * @exception NoSuchProviderException if the provider has not been
     * configured.
     *
     * @see Provider
     */
    public static final CertificateFactory getInstance(String type, String
        provider) throws CertificateException, NoSuchProviderException
    {
        return null;
    }

    /** 
     * Generates a certificate factory object for the specified
     * certificate type from the specified provider.
     * Note: the <code>provider</code> doesn't have to be registered.
     *
     * @param type the certificate type
     * @param provider the provider
     *
     * @return a certificate factory object for the specified type.
     *
     * @exception CertificateException if the certificate type is
     * not available from the specified provider.
     *
     * @exception IllegalArgumentException if the <code>provider</code> is
     * null.
     *
     * @see Provider
     *
     * @since 1.4
     */
    public static final CertificateFactory getInstance(String type, Provider
        provider) throws CertificateException
    {
        return null;
    }

    /** 
     * Returns the provider of this certificate factory.
     *
     * @return the provider of this certificate factory.
     */
    public final Provider getProvider() {
        return null;
    }

    /** 
     * Returns the name of the certificate type associated with this
     * certificate factory.
     *
     * @return the name of the certificate type associated with this
     * certificate factory.
     */
    public final String getType() {
        return null;
    }

    /** 
     * Generates a certificate object and initializes it with
     * the data read from the input stream <code>inStream</code>.
     *
     * <p>In order to take advantage of the specialized certificate format
     * supported by this certificate factory,
     * the returned certificate object can be typecast to the corresponding
     * certificate class. For example, if this certificate
     * factory implements X.509 certificates, the returned certificate object
     * can be typecast to the <code>X509Certificate</code> class.
     *
     * <p>In the case of a certificate factory for X.509 certificates, the
     * certificate provided in <code>inStream</code> must be DER-encoded and
     * may be supplied in binary or printable (Base64) encoding. If the
     * certificate is provided in Base64 encoding, it must be bounded at
     * the beginning by -----BEGIN CERTIFICATE-----, and must be bounded at
     * the end by -----END CERTIFICATE-----.
     *
     * <p>Note that if the given input stream does not support
     * {@link java.io.InputStream#mark(int) mark} and
     * {@link java.io.InputStream#reset() reset}, this method will
     * consume the entire input stream. Otherwise, each call to this 
     * method consumes one certificate and the read position of the
     * input stream is positioned to the next available byte after
     * the inherent end-of-certificate marker. If the data in the input stream
     * does not contain an inherent end-of-certificate marker (other
     * than EOF) and there is trailing data after the certificate is parsed, a 
     * <code>CertificateException</code> is thrown.
     *
     * @param inStream an input stream with the certificate data.
     *
     * @return a certificate object initialized with the data
     * from the input stream.
     *
     * @exception CertificateException on parsing errors.
     */
    public final Certificate generateCertificate(InputStream inStream)
        throws CertificateException
    {
        return null;
    }

    /** 
     * Returns an iteration of the <code>CertPath</code> encodings supported 
     * by this certificate factory, with the default encoding first. See 
     * Appendix A in the 
     * <a href="../../../../guide/security/certpath/CertPathProgGuide.html#AppA">
     * Java Certification Path API Programmer's Guide</a> for information about 
     * standard encoding names and their formats. 
     * <p>
     * Attempts to modify the returned <code>Iterator</code> via its 
     * <code>remove</code> method result in an 
     * <code>UnsupportedOperationException</code>.
     *
     * @return an <code>Iterator</code> over the names of the supported
     *         <code>CertPath</code> encodings (as <code>String</code>s)
     * @since 1.4
     */
    public final Iterator getCertPathEncodings() {
        return null;
    }

    /** 
     * Generates a <code>CertPath</code> object and initializes it with
     * the data read from the <code>InputStream</code> inStream. The data
     * is assumed to be in the default encoding. The name of the default
     * encoding is the first element of the <code>Iterator</code> returned by
     * the {@link #getCertPathEncodings getCertPathEncodings} method.
     *
     * @param inStream an <code>InputStream</code> containing the data
     * @return a <code>CertPath</code> initialized with the data from the
     *   <code>InputStream</code>
     * @exception CertificateException if an exception occurs while decoding
     * @since 1.4
     */
    public final CertPath generateCertPath(InputStream inStream)
        throws CertificateException
    {
        return null;
    }

    /** 
     * Generates a <code>CertPath</code> object and initializes it with
     * the data read from the <code>InputStream</code> inStream. The data
     * is assumed to be in the specified encoding. See Appendix A in the 
     * <a href="../../../../guide/security/certpath/CertPathProgGuide.html#AppA">
     * Java Certification Path API Programmer's Guide</a>
     * for information about standard encoding names and their formats.
     *
     * @param inStream an <code>InputStream</code> containing the data
     * @param encoding the encoding used for the data
     * @return a <code>CertPath</code> initialized with the data from the
     *   <code>InputStream</code>
     * @exception CertificateException if an exception occurs while decoding or
     *   the encoding requested is not supported
     * @since 1.4
     */
    public final CertPath generateCertPath(InputStream inStream, String
        encoding) throws CertificateException
    {
        return null;
    }

    /** 
     * Generates a <code>CertPath</code> object and initializes it with
     * a <code>List</code> of <code>Certificate</code>s.
     * <p>
     * The certificates supplied must be of a type supported by the
     * <code>CertificateFactory</code>. They will be copied out of the supplied
     * <code>List</code> object.
     *
     * @param certificates a <code>List</code> of <code>Certificate</code>s
     * @return a <code>CertPath</code> initialized with the supplied list of
     *   certificates
     * @exception CertificateException if an exception occurs
     * @since 1.4
     */
    public final CertPath generateCertPath(List certificates)
        throws CertificateException
    {
        return null;
    }

    /** 
     * Returns a (possibly empty) collection view of the certificates read
     * from the given input stream <code>inStream</code>.
     *
     * <p>In order to take advantage of the specialized certificate format
     * supported by this certificate factory, each element in
     * the returned collection view can be typecast to the corresponding
     * certificate class. For example, if this certificate
     * factory implements X.509 certificates, the elements in the returned
     * collection can be typecast to the <code>X509Certificate</code> class.
     *
     * <p>In the case of a certificate factory for X.509 certificates,
     * <code>inStream</code> may contain a sequence of DER-encoded certificates
     * in the formats described for
     * {@link #generateCertificate(java.io.InputStream) generateCertificate}.
     * In addition, <code>inStream</code> may contain a PKCS#7 certificate
     * chain. This is a PKCS#7 <i>SignedData</i> object, with the only
     * significant field being <i>certificates</i>. In particular, the
     * signature and the contents are ignored. This format allows multiple
     * certificates to be downloaded at once. If no certificates are present,
     * an empty collection is returned.
     *
     * <p>Note that if the given input stream does not support
     * {@link java.io.InputStream#mark(int) mark} and
     * {@link java.io.InputStream#reset() reset}, this method will
     * consume the entire input stream.
     *
     * @param inStream the input stream with the certificates.
     *
     * @return a (possibly empty) collection view of
     * java.security.cert.Certificate objects
     * initialized with the data from the input stream.
     *
     * @exception CertificateException on parsing errors.
     */
    public final Collection generateCertificates(InputStream inStream)
        throws CertificateException
    {
        return null;
    }

    /** 
     * Generates a certificate revocation list (CRL) object and initializes it
     * with the data read from the input stream <code>inStream</code>.
     *
     * <p>In order to take advantage of the specialized CRL format
     * supported by this certificate factory,
     * the returned CRL object can be typecast to the corresponding
     * CRL class. For example, if this certificate
     * factory implements X.509 CRLs, the returned CRL object
     * can be typecast to the <code>X509CRL</code> class.
     *
     * <p>Note that if the given input stream does not support
     * {@link java.io.InputStream#mark(int) mark} and
     * {@link java.io.InputStream#reset() reset}, this method will
     * consume the entire input stream. Otherwise, each call to this 
     * method consumes one CRL and the read position of the input stream
     * is positioned to the next available byte after the the inherent 
     * end-of-CRL marker. If the data in the
     * input stream does not contain an inherent end-of-CRL marker (other
     * than EOF) and there is trailing data after the CRL is parsed, a 
     * <code>CRLException</code> is thrown.
     *
     * @param inStream an input stream with the CRL data.
     *
     * @return a CRL object initialized with the data
     * from the input stream.
     *
     * @exception CRLException on parsing errors.
     */
    public final CRL generateCRL(InputStream inStream) throws CRLException {
        return null;
    }

    /** 
     * Returns a (possibly empty) collection view of the CRLs read
     * from the given input stream <code>inStream</code>.
     *
     * <p>In order to take advantage of the specialized CRL format
     * supported by this certificate factory, each element in
     * the returned collection view can be typecast to the corresponding
     * CRL class. For example, if this certificate
     * factory implements X.509 CRLs, the elements in the returned
     * collection can be typecast to the <code>X509CRL</code> class.
     *
     * <p>In the case of a certificate factory for X.509 CRLs,
     * <code>inStream</code> may contain a sequence of DER-encoded CRLs.
     * In addition, <code>inStream</code> may contain a PKCS#7 CRL
     * set. This is a PKCS#7 <i>SignedData</i> object, with the only
     * significant field being <i>crls</i>. In particular, the
     * signature and the contents are ignored. This format allows multiple
     * CRLs to be downloaded at once. If no CRLs are present,
     * an empty collection is returned.
     *
     * <p>Note that if the given input stream does not support
     * {@link java.io.InputStream#mark(int) mark} and
     * {@link java.io.InputStream#reset() reset}, this method will
     * consume the entire input stream.
     *
     * @param inStream the input stream with the CRLs.
     *
     * @return a (possibly empty) collection view of
     * java.security.cert.CRL objects initialized with the data from the input
     * stream.
     *
     * @exception CRLException on parsing errors.
     */
    public final Collection generateCRLs(InputStream inStream)
        throws CRLException
    {
        return null;
    }
}
