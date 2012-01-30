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


  


package javax.microedition.io;

import java.lang.String;
import java.io.IOException;
import javax.microedition.pki.Certificate;
import javax.microedition.pki.CertificateException;

/** 
 * This interface defines methods
 * to access information about a secure network connection.
 * Protocols that implement secure connections may use this interface
 * to report the security parameters of the connection.
 * <p>
 * It provides the certificate, protocol, version, and cipher suite,
 * etc. in use.
 *
 * @see CertificateException
 * @see SecureConnection
 * @see HttpsConnection
 * @since MIDP 2.0
 */
public interface SecurityInfo
{

    /** 
     * Returns the <CODE>Certificate</CODE> used to establish the
     * secure connection with the server.
     *
     * @return the <CODE>Certificate</CODE> used to establish the
     * secure connection with the server.
     */
    public Certificate getServerCertificate();

    /** 
     * Returns the protocol version.
     * If appropriate, it should contain the major and minor versions
     * for the protocol separated with a "." (Unicode U+002E).
     * <pre>
     *     For SSL V3 it MUST return "3.0"
     *     For TLS 1.0 it MUST return "3.1"
     *     For WTLS (WAP-199) it MUST return "1"
     *     For WAP TLS Profile and Tunneling Specification it MUST return "3.1"
     *</pre>
     * @return a String containing the version of the protocol;
     *		the return value MUST NOT be <CODE>null</CODE>.
     */
    public java.lang.String getProtocolVersion();

    /** 
     * Returns the secure protocol name.
     *
     * @return a <code>String</code> containing the secure protocol identifier;
     * if TLS (RFC 2246) or WAP TLS Profile and Tunneling (WAP-219-TLS)
     * is used for the connection the return value is "TLS";
     * if SSL V3 (The SSL Protocol Version 3.0) is used for the connection;
     * the return value is "SSL");
     * if WTLS (WAP 199) is used for the connection the return value is "WTLS".
     */
    public java.lang.String getProtocolName();

    /** 
     * Returns the name of the cipher suite in use for the connection.
     * The name returned is from the CipherSuite column of the CipherSuite
     * definitions table in Appendix C of RFC 2246. If the cipher suite is
     * not in Appendix C, the name returned is non-null and its contents
     * are not specified. For non-TLS implementions the cipher suite name
     * should be selected according to the actual key exchange, cipher,
     * and hash
     * combination used to establish the connection, so that regardless of
     * whether the secure connection uses SSL V3
     * or TLS 1.0 or WTLS or WAP TLS Profile and Tunneling,
     * equivalent cipher suites have the same name.
     *
     * @return a <code>String</code> containing the name of the cipher suite
     * in use.
     */
    public java.lang.String getCipherSuite();
}
