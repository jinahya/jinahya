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


  


package javax.security.auth.x500;

import java.io.*;
import java.security.Principal;

/** 
 * <p> This class represents an X.500 <code>Principal</code>.
 * <code>X500Principal</code>s are represented by distinguished names such as
 * "CN=Duke, OU=JavaSoft, O=Sun Microsystems, C=US".
 *
 * <p> This class can be instantiated by using a string representation
 * of the distinguished name, or by using the ASN.1 DER encoded byte
 * representation of the distinguished name.  The current specification
 * for the string representation of a distinguished name is defined in
 * <a href="http://www.ietf.org/rfc/rfc2253.html">RFC 2253</a>.
 * This class, however, accepts string formats from both RFC 2253 and
 * <a href="http://www.ietf.org/rfc/rfc1779.html">RFC 1779</a>,
 * and also recognizes attribute type keywords whose OIDs
 * (Object Identifiers) are defined in
 * <a href="http://www.ietf.org/rfc/rfc2459.html">RFC 2459</a>.
 *
 * <p> The string representation for this <code>X500Principal</code>
 * can be obtained by calling the <code>getName</code> methods.
 *
 * <p> Note that the <code>getSubjectX500Principal</code> and
 * <code>getIssuerX500Principal</code> methods of
 * <code>X509Certificate</code> return X500Principals representing the
 * issuer and subject fields of the certificate.
 *
 * @version 1.20, 03/12/05
 * @see java.security.cert.X509Certificate
 * @since 1.4
 */
public final class X500Principal implements Principal, Serializable
{
    private static final long serialVersionUID = -500463348111345721L;

    /** 
     * RFC 1779 String format of Distinguished Names. 
     */
    public static final String RFC1779 = "RFC1779";

    /** 
     * RFC 2253 String format of Distinguished Names. 
     */
    public static final String RFC2253 = "RFC2253";

    /** 
     * Canonical String format of Distinguished Names.
     */
    public static final String CANONICAL = "CANONICAL";

    /** 
     * Creates an <code>X500Principal</code> from a string representation of
     * an X.500 distinguished name (ex: 
     * "CN=Duke, OU=JavaSoft, O=Sun Microsystems, C=US").
     * The distinguished name must be specified using the grammar defined in
     * RFC 1779 or RFC 2253 (either format is acceptable). 
     *
     * <p>This constructor recognizes the attribute type keywords
     * defined in RFC 1779 and RFC 2253
     * (and listed in {@link #getName(String format) getName(String format)}),
     * as well as the T, DNQ or DNQUALIFIER, SURNAME, GIVENNAME, INITIALS,
     * GENERATION, EMAILADDRESS, and SERIALNUMBER keywords whose OIDs are 
     * defined in RFC 2459 and its successor.
     * Any other attribute type must be specified as an OID.
     *
     * @param name an X.500 distinguished name in RFC 1779 or RFC 2253 format
     * @exception NullPointerException if the <code>name</code>
     *			is <code>null</code>
     * @exception IllegalArgumentException if the <code>name</code>
     *			is improperly specified
     */
    public X500Principal(String name) { }

    /** 
     * Creates an <code>X500Principal</code> from a distinguished name in 
     * ASN.1 DER encoded form. The ASN.1 notation for this structure is as 
     * follows.
     * <pre><code>
     * Name ::= CHOICE {
     *   RDNSequence }
     *
     * RDNSequence ::= SEQUENCE OF RelativeDistinguishedName
     *
     * RelativeDistinguishedName ::=
     *   SET SIZE (1 .. MAX) OF AttributeTypeAndValue
     *
     * AttributeTypeAndValue ::= SEQUENCE {
     *   type     AttributeType,
     *   value    AttributeValue }
     *
     * AttributeType ::= OBJECT IDENTIFIER
     *
     * AttributeValue ::= ANY DEFINED BY AttributeType
     * ....
     * DirectoryString ::= CHOICE {
     *       teletexString           TeletexString (SIZE (1..MAX)),
     *       printableString         PrintableString (SIZE (1..MAX)),
     *       universalString         UniversalString (SIZE (1..MAX)),
     *       utf8String              UTF8String (SIZE (1.. MAX)),
     *       bmpString               BMPString (SIZE (1..MAX)) }
     * </code></pre>
     *
     * @param name a byte array containing the distinguished name in ASN.1 
     * DER encoded form
     * @throws IllegalArgumentException if an encoding error occurs
     *		(incorrect form for DN)
     */
    public X500Principal(byte[] name) { }

    /** 
     * Creates an <code>X500Principal</code> from an <code>InputStream</code>
     * containing the distinguished name in ASN.1 DER encoded form.
     * The ASN.1 notation for this structure is supplied in the
     * documentation for
     * {@link #X500Principal(byte[] name) X500Principal(byte[] name)}.
     *
     * <p> The read position of the input stream is positioned
     * to the next available byte after the encoded distinguished name.
     *
     * @param is an <code>InputStream</code> containing the distinguished
     *		name in ASN.1 DER encoded form
     *
     * @exception NullPointerException if the <code>InputStream</code>
     *		is <code>null</code>
     * @exception IllegalArgumentException if an encoding error occurs
     *		(incorrect form for DN)
     */
    public X500Principal(InputStream is) { }

    /** 
     * Returns a string representation of the X.500 distinguished name using
     * the format defined in RFC 2253.
     *
     * <p>This method is equivalent to calling
     * <code>getName(X500Principal.RFC2253)</code>.
     *
     * @return the distinguished name of this <code>X500Principal</code>
     */
    public String getName() {
        return null;
    }

    /** 
     * Returns a string representation of the X.500 distinguished name
     * using the specified format. Valid values for the format are
     * "RFC1779", "RFC2253", and "CANONICAL" (case insensitive).
     *
     * <p> If "RFC1779" is specified as the format,
     * this method emits the attribute type keywords defined in
     * RFC 1779 (CN, L, ST, O, OU, C, STREET).
     * Any other attribute type is emitted as an OID.
     *
     * <p> If "RFC2253" is specified as the format,
     * this method emits the attribute type keywords defined in
     * RFC 2253 (CN, L, ST, O, OU, C, STREET, DC, UID).
     * Any other attribute type is emitted as an OID.
     * Under a strict reading, RFC 2253 only specifies a UTF-8 string
     * representation. The String returned by this method is the
     * Unicode string achieved by decoding this UTF-8 representation.
     *
     * <p> If "CANONICAL" is specified as the format,
     * this method returns an RFC 2253 conformant string representation
     * with the following additional canonicalizations:
     *
     * <p><ol>
     * <li> Leading zeros are removed from attribute types
     *		that are encoded as dotted decimal OIDs
     * <li> DirectoryString attribute values of type
     *		PrintableString and UTF8String are not
     *		output in hexadecimal format
     * <li> DirectoryString attribute values of types
     *		other than PrintableString and UTF8String
     *		are output in hexadecimal format
     * <li> Leading and trailing white space characters
     *		are removed from non-hexadecimal attribute values
     *		(unless the value consists entirely of white space characters)
     * <li> Internal substrings of one or more white space characters are
     *		converted to a single space in non-hexadecimal
     *		attribute values
     * <li> Relative Distinguished Names containing more than one
     *		Attribute Value Assertion (AVA) are output in the
     *		following order: an alphabetical ordering of AVAs
     *		containing standard keywords, followed by a numeric
     *		ordering of AVAs containing OID keywords.
     * <li> The only characters in attribute values that are escaped are
     *		those which section 2.4 of RFC 2253 states must be escaped
     *		(they are escaped using a preceding backslash character)
     * <li> The entire name is converted to upper case
     *		using <code>String.toUpperCase(Locale.US)</code>
     * <li> The entire name is converted to lower case
     *		using <code>String.toLowerCase(Locale.US)</code>
     * <li> The name is finally normalized using normalization form KD,
     *		as described in the Unicode Standard and UAX #15
     * </ol>
     *
     * <p> Additional standard formats may be introduced in the future.
     *
     * @param format the format to use
     *
     * @return a string representation of this <code>X500Principal</code>
     *		using the specified format
     * @throws IllegalArgumentException if the specified format is invalid
     */
    public String getName(String format) {
        return null;
    }

    /** 
     * Returns the distinguished name in ASN.1 DER encoded form. The ASN.1
     * notation for this structure is supplied in the documentation for
     * {@link #X500Principal(byte[] name) X500Principal(byte[] name)}.
     *
     * <p>Note that the byte array returned is cloned to protect against
     * subsequent modifications.
     *
     * @return a byte array containing the distinguished name in ASN.1 DER 
     * encoded form
     */
    public byte[] getEncoded() {
        return null;
    }

    /** 
     * Return a user-friendly string representation of this
     * <code>X500Principal</code>.
     *
     * @return a string representation of this <code>X500Principal</code>
     */
    public String toString() {
        return null;
    }

    /** 
     * Compares the specified <code>Object</code> with this 
     * <code>X500Principal</code> for equality.
     *
     * <p> Specifically, this method returns <code>true</code> if
     * the <code>Object</code> <i>o</i> is an <code>X500Principal</code>
     * and if the respective canonical string representations
     * (obtained via the <code>getName(X500Principal.CANONICAL)</code> method)
     * of this object and <i>o</i> are equal.
     *
     * <p> This implementation is compliant with the requirements of RFC 2459.
     *
     * @param o Object to be compared for equality with this
     *		<code>X500Principal</code>
     *
     * @return <code>true</code> if the specified <code>Object</code> is equal 
     *		to this <code>X500Principal</code>, <code>false</code> otherwise
     */
    public boolean equals(Object o) {
        return false;
    }

    /** 
     * Return a hash code for this <code>X500Principal</code>.
     *
     * <p> The hash code is calculated via:
     * <code>getName(X500Principal.CANONICAL).hashCode()</code>
     *
     * @return a hash code for this <code>X500Principal</code>
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Reads this object from a stream (i.e., deserializes it).
     */
    private void readObject(ObjectInputStream s)
        throws IOException, NotActiveException, ClassNotFoundException
    { }

    /** 
     * Save the X500Principal object to a stream.
     *
     * @serialData this <code>X500Principal</code> is serialized
     *		by writing out its DER-encoded form
     *		(the value of <code>getEncoded</code> is serialized).
     */
    private void writeObject(ObjectOutputStream s) throws IOException { }
}
