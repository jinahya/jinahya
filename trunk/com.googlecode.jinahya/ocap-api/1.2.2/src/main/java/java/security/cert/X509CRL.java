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

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.security.Principal;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;
import java.util.Arrays;

/** 
 * <p>
 * Abstract class for an X.509 Certificate Revocation List (CRL).
 * A CRL is a time-stamped list identifying revoked certificates.
 * It is signed by a Certificate Authority (CA) and made freely
 * available in a public repository.  
 * 
 * <p>Each revoked certificate is
 * identified in a CRL by its certificate serial number. When a
 * certificate-using system uses a certificate (e.g., for verifying a
 * remote user's digital signature), that system not only checks the
 * certificate signature and validity but also acquires a suitably-
 * recent CRL and checks that the certificate serial number is not on
 * that CRL.  The meaning of "suitably-recent" may vary with local
 * policy, but it usually means the most recently-issued CRL.  A CA
 * issues a new CRL on a regular periodic basis (e.g., hourly, daily, or
 * weekly).  Entries are added to CRLs as revocations occur, and an
 * entry may be removed when the certificate expiration date is reached.
 * <p>
 * The X.509 v2 CRL format is described below in ASN.1:
 * <pre>
 * CertificateList  ::=  SEQUENCE  {
 *     tbsCertList          TBSCertList,
 *     signatureAlgorithm   AlgorithmIdentifier,
 *     signature            BIT STRING  }
 * </pre>
 * <p>
 * More information can be found in RFC 2459,
 * "Internet X.509 Public Key Infrastructure Certificate and CRL
 * Profile" at <A HREF="http://www.ietf.org/rfc/rfc2459.txt">
 * http://www.ietf.org/rfc/rfc2459.txt </A>.    
 * <p>
 * The ASN.1 definition of <code>tbsCertList</code> is:
 * <pre>
 * TBSCertList  ::=  SEQUENCE  {
 *     version                 Version OPTIONAL,
 *                             -- if present, must be v2
 *     signature               AlgorithmIdentifier,
 *     issuer                  Name,
 *     thisUpdate              ChoiceOfTime,
 *     nextUpdate              ChoiceOfTime OPTIONAL,
 *     revokedCertificates     SEQUENCE OF SEQUENCE  {
 *         userCertificate         CertificateSerialNumber,
 *         revocationDate          ChoiceOfTime,
 *         crlEntryExtensions      Extensions OPTIONAL
 *                                 -- if present, must be v2
 *         }  OPTIONAL,
 *     crlExtensions           [0]  EXPLICIT Extensions OPTIONAL
 *                                  -- if present, must be v2
 *     }
 * </pre>
 * <p>
 * CRLs are instantiated using a certificate factory. The following is an
 * example of how to instantiate an X.509 CRL:
 * <pre><code> 
 * InputStream inStream = new FileInputStream("fileName-of-crl");
 * CertificateFactory cf = CertificateFactory.getInstance("X.509");
 * X509CRL crl = (X509CRL)cf.generateCRL(inStream);
 * inStream.close();
 * </code></pre>
 *
 * @author Hemma Prafullchandra
 *
 * @version 1.23, 03/12/05
 *
 * @see CRL
 * @see CertificateFactory
 * @see X509Extension
 */
public abstract class X509CRL extends CRL implements X509Extension
{

    /** 
     * Constructor for X.509 CRLs.
     */
    protected X509CRL() { 
	super("X.509");    
    }

    /** 
     * Compares this CRL for equality with the given 
     * object. If the <code>other</code> object is an 
     * <code>instanceof</code> <code>X509CRL</code>, then
     * its encoded form is retrieved and compared with the
     * encoded form of this CRL.
     * 
     * @param other the object to test for equality with this CRL.
     * 
     * @return true iff the encoded forms of the two CRLs
     * match, false otherwise.
     */
    public boolean equals(Object other) {
        return false;
    }

    /** 
     * Returns a hashcode value for this CRL from its
     * encoded form.
     *
     * @return the hashcode value.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the ASN.1 DER-encoded form of this CRL.
     *
     * @return the encoded form of this certificate
     * @exception CRLException if an encoding error occurs.
     */
    public abstract byte[] getEncoded() throws CRLException;

    /** 
     * Verifies that this CRL was signed using the 
     * private key that corresponds to the given public key.
     *
     * @param key the PublicKey used to carry out the verification.
     *
     * @exception NoSuchAlgorithmException on unsupported signature
     * algorithms.
     * @exception InvalidKeyException on incorrect key.
     * @exception NoSuchProviderException if there's no default provider.
     * @exception SignatureException on signature errors.
     * @exception CRLException on encoding errors.
     */
    public abstract void verify(PublicKey key)
        throws CRLException, NoSuchAlgorithmException, InvalidKeyException,
        NoSuchProviderException, SignatureException;

    /** 
     * Verifies that this CRL was signed using the 
     * private key that corresponds to the given public key.
     * This method uses the signature verification engine
     * supplied by the given provider.
     *
     * @param key the PublicKey used to carry out the verification.
     * @param sigProvider the name of the signature provider.
     * 
     * @exception NoSuchAlgorithmException on unsupported signature
     * algorithms.
     * @exception InvalidKeyException on incorrect key.
     * @exception NoSuchProviderException on incorrect provider.
     * @exception SignatureException on signature errors.
     * @exception CRLException on encoding errors.
     */
    public abstract void verify(PublicKey key, String sigProvider)
        throws CRLException, NoSuchAlgorithmException, InvalidKeyException,
        NoSuchProviderException, SignatureException;

    /** 
     * Gets the <code>version</code> (version number) value from the CRL.
     * The ASN.1 definition for this is:
     * <pre>
     * version    Version OPTIONAL,
     *             -- if present, must be v2<p>
     * Version  ::=  INTEGER  {  v1(0), v2(1), v3(2)  }
     *             -- v3 does not apply to CRLs but appears for consistency
     *             -- with definition of Version for certs
     * </pre>
     *
     * @return the version number, i.e. 1 or 2.
     */
    public abstract int getVersion();

    /** 
     * Gets the <code>issuer</code> (issuer distinguished name) value from 
     * the CRL. The issuer name identifies the entity that signed (and
     * issued) the CRL. 
     * 
     * <p>The issuer name field contains an
     * X.500 distinguished name (DN).
     * The ASN.1 definition for this is:
     * <pre>
     * issuer    Name
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
     * attributes,
     * such as country name, and corresponding values, such as US.
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
     * Returns the issuer (issuer distinguished name) value from the
     * CRL as an <code>X500Principal</code>.
     * <p>
     * It is recommended that subclasses override this method to provide 
     * an efficient implementation.
     *
     * @return an <code>X500Principal</code> representing the issuer
     *		distinguished name
     * @since 1.4
     */
    public X500Principal getIssuerX500Principal() {
        return null;
    }

    /** 
     * Gets the <code>thisUpdate</code> date from the CRL.
     * The ASN.1 definition for this is:
     * <pre>
     * thisUpdate   ChoiceOfTime
     * ChoiceOfTime ::= CHOICE {
     *     utcTime        UTCTime,
     *     generalTime    GeneralizedTime }
     * </pre>
     *
     * @return the <code>thisUpdate</code> date from the CRL.
     */
    public abstract Date getThisUpdate();

    /** 
     * Gets the <code>nextUpdate</code> date from the CRL.
     *
     * @return the <code>nextUpdate</code> date from the CRL, or null if
     * not present.
     */
    public abstract Date getNextUpdate();

    /** 
     * Gets the CRL entry, if any, with the given certificate serialNumber.
     *
     * @param serialNumber the serial number of the certificate for which a CRL entry
     * is to be looked up
     * @return the entry with the given serial number, or null if no such entry
     * exists in this CRL.
     * @see X509CRLEntry
     */
    public abstract X509CRLEntry getRevokedCertificate(BigInteger serialNumber);

    /** 
     * Gets all the entries from this CRL.
     * This returns a Set of X509CRLEntry objects.
     *
     * @return all the entries or null if there are none present.
     * @see X509CRLEntry
     */
    public abstract Set getRevokedCertificates();

    /** 
     * Gets the DER-encoded CRL information, the
     * <code>tbsCertList</code> from this CRL.
     * This can be used to verify the signature independently.
     *
     * @return the DER-encoded CRL information.
     * @exception CRLException if an encoding error occurs.
     */
    public abstract byte[] getTBSCertList() throws CRLException;

    /** 
     * Gets the <code>signature</code> value (the raw signature bits) from 
     * the CRL.
     * The ASN.1 definition for this is:
     * <pre>
     * signature     BIT STRING  
     * </pre>
     *
     * @return the signature.
     */
    public abstract byte[] getSignature();

    /** 
     * Gets the signature algorithm name for the CRL
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
     * Gets the signature algorithm OID string from the CRL.
     * An OID is represented by a set of nonnegative whole numbers separated
     * by periods.
     * For example, the string "1.2.840.10040.4.3" identifies the SHA-1
     * with DSA signature algorithm, as per RFC 2459.
     * 
     * <p>See {@link getSigAlgName()#getSigAlgName} for 
     * relevant ASN.1 definitions.
     *
     * @return the signature algorithm OID string.
     */
    public abstract String getSigAlgOID();

    /** 
     * Gets the DER-encoded signature algorithm parameters from this
     * CRL's signature algorithm. In most cases, the signature
     * algorithm parameters are null; the parameters are usually
     * supplied with the public key.
     * If access to individual parameter values is needed then use
     * {@link java.security.AlgorithmParameters AlgorithmParameters}
     * and instantiate with the name returned by
     * {@link getSigAlgName()#getSigAlgName}.
     * 
     * <p>See {@link getSigAlgName()#getSigAlgName} for 
     * relevant ASN.1 definitions.
     *
     * @return the DER-encoded signature algorithm parameters, or
     *         null if no parameters are present.
     */
    public abstract byte[] getSigAlgParams();
}
