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


  


package javax.microedition.pki;

import javax.microedition.pki.Certificate;
import java.lang.String;

/** 
 * The <CODE>CertificateException</CODE> encapsulates an error that
 * occurred while a <CODE>Certificate</CODE> is being used.  If multiple errors
 * are found within a <CODE>Certificate</CODE> the more significant error
 * should be reported in the exception. 
 * @since MIDP 2.0
 */
public class CertificateException extends java.io.IOException
{
    /** 
     * Indicates a certificate has unrecognized critical extensions.
     * The value is 1.
     */
    public static final byte BAD_EXTENSIONS = 1;

    /** 
     * Indicates the server certificate chain exceeds the length allowed
     * by an issuer's policy.
     * The value is 2.
     */
    public static final byte CERTIFICATE_CHAIN_TOO_LONG = 2;

    /** 
     * Indicates a certificate is expired.
     * The value is 3.
     */
    public static final byte EXPIRED = 3;

    /** 
     * Indicates an intermediate certificate in the chain does not have the
     * authority to be a intermediate CA. The value is 4.
     */
    public static final byte UNAUTHORIZED_INTERMEDIATE_CA = 4;

    /** 
     * Indicates a certificate object does not contain a signature.
     * The value is 5.
     */
    public static final byte MISSING_SIGNATURE = 5;

    /** 
     * Indicates a certificate is not yet valid.
     * The value is 6.
     */
    public static final byte NOT_YET_VALID = 6;

    /** 
     * Indicates a certificate does not contain the correct site name.
     * The value is 7.
     */
    public static final byte SITENAME_MISMATCH = 7;

    /** 
     * Indicates a certificate was issued by an unrecognized entity.
     * The value is 8.
     */
    public static final byte UNRECOGNIZED_ISSUER = 8;

    /** 
     * Indicates a certificate was signed using an unsupported algorithm.
     * The value is 9.
     */
    public static final byte UNSUPPORTED_SIGALG = 9;

    /** 
     * Indicates a certificate public key has been used in way deemed
     * inappropriate by the issuer. The value is 10.
     */
    public static final byte INAPPROPRIATE_KEY_USAGE = 10;

    /** 
     * Indicates a certificate in a chain was not issued by the next
     * authority in the chain. The value is 11.
     */
    public static final byte BROKEN_CHAIN = 11;

    /** 
     * Indicates the root CA's public key is expired. The value is 12.
     */
    public static final byte ROOT_CA_EXPIRED = 12;

    /** 
     * Indicates that type of the public key in a certificate is not
     * supported by the device. The value is 13.
     */
    public static final byte UNSUPPORTED_PUBLIC_KEY_TYPE = 13;

    /** 
     * Indicates a certificate failed verification.
     * The value is 14.
     */
    public static final byte VERIFICATION_FAILED = 14;

    /** The reason code for this exception  */
    private byte reason;

    /** 
     * The certificate that caused the exception
     */
    private javax.microedition.pki.Certificate cert;

    /** 
     * Create a new exception with a <CODE>Certificate</CODE>
     * and specific error reason. The descriptive message for the new exception
     * will be automatically provided, based on the reason.
     * @param certificate the certificate that caused the exception
     * @param status the reason for the exception;
     *  the status MUST be between BAD_EXTENSIONS and VERIFICATION_FAILED
     *  inclusive.
     */
    public CertificateException(javax.microedition.pki.Certificate certificate,
        byte status)
    { }

    /** 
     * Create a new exception with a message, <CODE>Certificate</CODE>,
     * and specific error reason.
     * @param message a descriptive message
     * @param certificate the certificate that caused the exception
     * @param status the reason for the exception;
     *  the status MUST be between BAD_EXTENSIONS and VERIFICATION_FAILED
     *  inclusive.
     */
    public CertificateException(java.lang.String message,
        javax.microedition.pki.Certificate certificate, byte status)
    { }

    /** 
     * Get the <CODE>Certificate</CODE> that caused the exception.
     * @return the <CODE>Certificate</CODE> that included the failure.
     */
    public javax.microedition.pki.Certificate getCertificate() {
        return null;
    }

    /** 
     * Get the reason code.
     * @return the reason code
     */
    public byte getReason() {
        return ' ';
    }
}
