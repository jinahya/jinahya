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
This work corresponds to the API signatures of CDC Security
(Java Secure Socket Extension - JSSE) Optional Package interfaces and modules.

*/




/*
 * @(#)X509Certificate.java	1.7 05/03/12
 */
  
/*
 * NOTE:
 * Because of various external restrictions (i.e. US export
 * regulations, etc.), the actual source code can not be provided
 * at this time. This file represents the skeleton of the source
 * file, so that javadocs of the API can be created.
 */

package javax.security.cert;

import java.io.InputStream;
import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.Security;
import java.math.BigInteger;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.security.PublicKey;
import java.util.BitSet;
import java.util.Date;

/** 
 * Abstract class for X.509 v1 certificates. This provides a standard
 * way to access all the version 1 attributes of an X.509 certificate.
 * Attributes that are specific to X.509 v2 or v3 are not available
 * through this interface. Future API evolution will provide full access to
 * complete X.509 v3 attributes.
 * <p>
 * The basic X.509 format was defined by
 * ISO/IEC and ANSI X9 and is described below in ASN.1:
 * <pre>
 * Certificate  ::=  SEQUENCE  {
 *     tbsCertificate       TBSCertificate,
 *     signatureAlgorithm   AlgorithmIdentifier,
 *     signature            BIT STRING  }
 * </pre>
 * <p>
 * These certificates are widely used to support authentication and
 * other functionality in Internet security systems. Common applications
 * include Privacy Enhanced Mail (PEM), Transport Layer Security (SSL),
 * code signing for trusted software distribution, and Secure Electronic
 * Transactions (SET).
 * <p>
 * These certificates are managed and vouched for by <em>Certificate
 * Authorities</em> (CAs). CAs are services which create certificates by
 * placing data in the X.509 standard format and then digitally signing
 * that data. CAs act as trusted third parties, making introductions
 * between principals who have no direct knowledge of each other.
 * CA certificates are either signed by themselves, or by some other
 * CA such as a "root" CA.
 * <p>
 * The ASN.1 definition of <code>tbsCertificate</code> is:
 * <pre>
 * TBSCertificate  ::=  SEQUENCE  {
 *     version         [0]  EXPLICIT Version DEFAULT v1,
 *     serialNumber         CertificateSerialNumber,
 *     signature            AlgorithmIdentifier,
 *     issuer               Name,
 *     validity             Validity,
 *     subject              Name,
 *     subjectPublicKeyInfo SubjectPublicKeyInfo,
 *     }
 * </pre>
 * <p>
 * Here is sample code to instantiate an X.509 certificate:
 * <pre> 
 * InputStream inStream = new FileInputStream("fileName-of-cert");
 * X509Certificate cert = X509Certificate.getInstance(inStream);
 * inStream.close();
 * </pre>
 * OR
 * <pre>
 * byte[] certData = &lt;certificate read from a file, say&gt;
 * X509Certificate cert = X509Certificate.getInstance(certData);
 * </pre>
 * <p>
 * In either case, the code that instantiates an X.509 certificate
 * consults the Java security properties file to locate the actual
 * implementation or instantiates a default implementation.
 * <p>
 * The Java security properties file is located in the file named
 * &lt;JAVA_HOME&gt;/lib/security/java.security, where &lt;JAVA_HOME&gt;
 * refers to the directory where the JDK was installed.
 * In the Security properties file, a default implementation
 * for X.509 v1 may be given such as:
 * <pre>
 * cert.provider.x509v1=com.sun.security.cert.internal.x509.X509V1CertImpl
 * </pre>
 * <p>
 * The value of this <code>cert.provider.x509v1</code> property has to be 
 * changed to instatiate another implementation. If this security
 * property is not set, a default implementation will be used.
 * Currently, due to possible security restrictions on access to
 * Security properties, this value is looked up and cached at class
 * initialization time and will fallback on a default implementation if
 * the Security property is not accessible.
 *
 * <p><em>Note: The classes in the package <code>javax.security.cert</code>
 * exist for compatibility with earlier versions of the
 * Java Secure Sockets Extension (JSSE). New applications should instead
 * use the standard J2SE certificate classes located in
 * <code>java.security.cert</code>.</em></p>
 *
 * @author Hemma Prafullchandra
 * @version 1.28
 * @since 1.4
 * @see Certificate
 * @see java.security.cert.X509Extension
 */
public abstract class X509Certificate extends Certificate
{

    public X509Certificate() { }

    /** 
     * Instantiates an X509Certificate object, and initializes it with
     * the data read from the input stream <code>inStream</code>.
     * The implementation (X509Certificate is an abstract class) is
     * provided by the class specified as the value of the 
     * <code>cert.provider.x509v1</code>
     * property in the security properties file.
     *
     * <p>Note: Only one DER-encoded
     * certificate is expected to be in the input stream.
     * Also, all X509Certificate
     * subclasses must provide a constructor of the form:
     * <code><pre>
     * public &lt;subClass&gt;(InputStream inStream) ...
     * </pre></code>
     *   
     * @param inStream an input stream with the data to be read to
     *        initialize the certificate.
     * @return an X509Certificate object initialized with the data
     *         from the input stream.
     * @exception CertificateException if a class initialization
     *            or certificate parsing error occurs.
     */
    public static final X509Certificate getInstance(InputStream inStream)
        throws CertificateException
    { return null; }

    /** 
     * Instantiates an X509Certificate object, and initializes it with
     * the specified byte array.
     * The implementation (X509Certificate is an abstract class) is
     * provided by the class specified as the value of the 
     * <code>cert.provider.x509v1</code>
     * property in the security properties file.
     *
     * <p>Note: All X509Certificate
     * subclasses must provide a constructor of the form:
     * <code><pre>
     * public &lt;subClass&gt;(InputStream inStream) ...
     * </pre></code>
     *   
     * @param certData a byte array containing the DER-encoded
     *        certificate.
     * @return an X509Certificate object initialized with the data
     *         from <code>certData</code>.
     * @exception CertificateException if a class initialization
     *            or certificate parsing error occurs.
     */
    public static final X509Certificate getInstance(byte[] certData)
        throws CertificateException
    { return null; }

    /** 
     * Checks that the certificate is currently valid. It is if
     * the current date and time are within the validity period given in the
     * certificate.
     * <p>
     * The validity period consists of two date/time values: 
     * the first and last dates (and times) on which the certificate 
     * is valid. It is defined in
     * ASN.1 as:
     * <pre>
     * validity             Validity<p>
     * Validity ::= SEQUENCE {
     *     notBefore      CertificateValidityDate,
     *     notAfter       CertificateValidityDate }<p>
     * CertificateValidityDate ::= CHOICE {
     *     utcTime        UTCTime,
     *     generalTime    GeneralizedTime }
     * </pre>
     * 
     * @exception CertificateExpiredException if the certificate has expired.
     * @exception CertificateNotYetValidException if the certificate is not
     *            yet valid.
     */
    public abstract void checkValidity()
        throws CertificateExpiredException, CertificateNotYetValidException;

    /** 
     * Checks that the specified date is within the certificate's
     * validity period. In other words, this determines whether the 
     * certificate would be valid at the specified date/time.
     *
     * @param date the Date to check against to see if this certificate
     *        is valid at that date/time.
     * @exception CertificateExpiredException if the certificate has expired
     *            with respect to the <code>date</code> supplied.
     * @exception CertificateNotYetValidException if the certificate is not
     *            yet valid with respect to the <code>date</code> supplied.
     * @see #checkValidity()
     */
    public abstract void checkValidity(Date date)
        throws CertificateExpiredException, CertificateNotYetValidException;

    /** 
     * Gets the <code>version</code> (version number) value from the
     * certificate. The ASN.1 definition for this is:
     * <pre>
     * version         [0]  EXPLICIT Version DEFAULT v1<p>
     * Version  ::=  INTEGER  {  v1(0), v2(1), v3(2)  }
     * </pre>
     *
     * @return the version number from the ASN.1 encoding, i.e. 0, 1 or 2.
     */
    public abstract int getVersion();

    /** 
     * Gets the <code>serialNumber</code> value from the certificate.
     * The serial number is an integer assigned by the certification
     * authority to each certificate. It must be unique for each
     * certificate issued by a given CA (i.e., the issuer name and
     * serial number identify a unique certificate).
     * The ASN.1 definition for this is:
     * <pre>
     * serialNumber     CertificateSerialNumber<p>
     * 
     * CertificateSerialNumber  ::=  INTEGER
     * </pre>
     *
     * @return the serial number.
     */
    public abstract BigInteger getSerialNumber();

    /** 
     * Gets the <code>issuer</code> (issuer distinguished name) value from 
     * the certificate. The issuer name identifies the entity that signed (and
     * issued) the certificate. 
     * 
     * <p>The issuer name field contains an
     * X.500 distinguished name (DN).
     * The ASN.1 definition for this is:
     * <pre>
     * issuer    Name<p>
     *
     * Name ::= CHOICE { RDNSequence }
     * RDNSequence ::= SEQUENCE OF RelativeDistinguishedName
     * RelativeDistinguishedName ::=
     *     SET OF AttributeValueAssertion
     *
     * AttributeValueAssertion ::= SEQUENCE {
     *                               AttributeType,
     *                               AttributeValue }
     * AttributeType ::= OBJECT IDENTIFIER
     * AttributeValue ::= ANY
     * </pre>
     * The <code>Name</code> describes a hierarchical name composed of
     * attributes, such as country name, and corresponding values, such as US.
     * The type of the <code>AttributeValue</code> component is determined by
     * the <code>AttributeType</code>; in general it will be a 
     * <code>directoryString</code>. A <code>directoryString</code> is usually 
     * one of <code>PrintableString</code>,
     * <code>TeletexString</code> or <code>UniversalString</code>.
     * 
     * @return a Principal whose name is the issuer distinguished name.
     */
    public abstract Principal getIssuerDN();

    /** 
     * Gets the <code>subject</code> (subject distinguished name) value 
     * from the certificate.
     * The ASN.1 definition for this is:
     * <pre>
     * subject    Name
     * </pre>
     * 
     * <p>See <a href = "#getIssuerDN">getIssuerDN</a> for <code>Name</code> 
     * and other relevant definitions.
     * 
     * @return a Principal whose name is the subject name.
     * @see #getIssuerDN()
     */
    public abstract Principal getSubjectDN();

    /** 
     * Gets the <code>notBefore</code> date from the validity period of 
     * the certificate.
     * The relevant ASN.1 definitions are:
     * <pre>
     * validity             Validity<p>
     * 
     * Validity ::= SEQUENCE {
     *     notBefore      CertificateValidityDate,
     *     notAfter       CertificateValidityDate }<p>
     * CertificateValidityDate ::= CHOICE {
     *     utcTime        UTCTime,
     *     generalTime    GeneralizedTime }
     * </pre>
     *
     * @return the start date of the validity period.
     * @see #checkValidity()
     */
    public abstract Date getNotBefore();

    /** 
     * Gets the <code>notAfter</code> date from the validity period of 
     * the certificate. See <a href = "#getNotBefore">getNotBefore</a>
     * for relevant ASN.1 definitions.
     *
     * @return the end date of the validity period.
     * @see #checkValidity()
     */
    public abstract Date getNotAfter();

    /** 
     * Gets the signature algorithm name for the certificate
     * signature algorithm. An example is the string "SHA-1/DSA".
     * The ASN.1 definition for this is:
     * <pre>
     * signatureAlgorithm   AlgorithmIdentifier<p>
     * AlgorithmIdentifier  ::=  SEQUENCE  {
     *     algorithm               OBJECT IDENTIFIER,
     *     parameters              ANY DEFINED BY algorithm OPTIONAL  }
     *                             -- contains a value of the type
     *                             -- registered for use with the
     *                             -- algorithm object identifier value
     * </pre>
     * 
     * <p>The algorithm name is determined from the <code>algorithm</code>
     * OID string.
     *
     * @return the signature algorithm name.
     */
    public abstract String getSigAlgName();

    /** 
     * Gets the signature algorithm OID string from the certificate.
     * An OID is represented by a set of positive whole numbers separated
     * by periods.
     * For example, the string "1.2.840.10040.4.3" identifies the SHA-1
     * with DSA signature algorithm, as per the PKIX part I.
     * 
     * <p>See <a href = "#getSigAlgName">getSigAlgName</a> for 
     * relevant ASN.1 definitions.
     *
     * @return the signature algorithm OID string.
     */
    public abstract String getSigAlgOID();

    /** 
     * Gets the DER-encoded signature algorithm parameters from this
     * certificate's signature algorithm. In most cases, the signature
     * algorithm parameters are null; the parameters are usually
     * supplied with the certificate's public key.
     * 
     * <p>See <a href = "#getSigAlgName">getSigAlgName</a> for 
     * relevant ASN.1 definitions.
     *
     * @return the DER-encoded signature algorithm parameters, or
     *         null if no parameters are present.
     */
    public abstract byte[] getSigAlgParams();
}
