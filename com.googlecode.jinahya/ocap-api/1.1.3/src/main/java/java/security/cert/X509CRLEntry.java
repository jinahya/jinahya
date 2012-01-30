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

import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

/** 
 * <p>Abstract class for a revoked certificate in a CRL (Certificate
 * Revocation List).
 *
 * The ASN.1 definition for <em>revokedCertificates</em> is:
 * <pre>
 * revokedCertificates    SEQUENCE OF SEQUENCE  {
 *     userCertificate    CertificateSerialNumber,
 *     revocationDate     ChoiceOfTime,
 *     crlEntryExtensions Extensions OPTIONAL
 *                        -- if present, must be v2
 * }  OPTIONAL
 *<p>
 * CertificateSerialNumber  ::=  INTEGER
 *<p>
 * Extensions  ::=  SEQUENCE SIZE (1..MAX) OF Extension
 *<p>
 * Extension  ::=  SEQUENCE  {
 *     extnId        OBJECT IDENTIFIER,
 *     critical      BOOLEAN DEFAULT FALSE,
 *     extnValue     OCTET STRING
 *                   -- contains a DER encoding of a value
 *                   -- of the type registered for use with
 *                   -- the extnId object identifier value
 * }
 * </pre>
 *
 * @see X509CRL
 * @see X509Extension
 *
 * @author Hemma Prafullchandra
 * @version 1.12 00/02/02
 */
public abstract class X509CRLEntry implements X509Extension
{

    public X509CRLEntry() { }

    /** 
     * Compares this CRL entry for equality with the given
     * object. If the <code>other</code> object is an
     * <code>instanceof</code> <code>X509CRLEntry</code>, then
     * its encoded form (the inner SEQUENCE) is retrieved and compared
     * with the encoded form of this CRL entry.
     *
     * @param other the object to test for equality with this CRL entry.
     * @return true iff the encoded forms of the two CRL entries
     * match, false otherwise.
     */
    public boolean equals(Object other) {
        return false;
    }

    /** 
     * Returns a hashcode value for this CRL entry from its
     * encoded form.
     *
     * @return the hashcode value.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the ASN.1 DER-encoded form of this CRL Entry,
     * that is the inner SEQUENCE.
     *
     * @return the encoded form of this certificate
     * @exception CRLException if an encoding error occurs.
     */
    public abstract byte[] getEncoded() throws CRLException;

    /** 
     * Gets the serial number from this X509CRLEntry,
     * the <em>userCertificate</em>.
     *
     * @return the serial number.
     */
    public abstract BigInteger getSerialNumber();

    /** 
     * Gets the revocation date from this X509CRLEntry,
     * the <em>revocationDate</em>.
     *
     * @return the revocation date.
     */
    public abstract Date getRevocationDate();

    /** 
     * Returns true if this CRL entry has extensions.
     *
     * @return true if this entry has extensions, false otherwise.
     */
    public abstract boolean hasExtensions();

    /** 
     * Returns a string representation of this CRL entry.
     *
     * @return a string representation of this CRL entry.
     */
    public abstract String toString();
}
