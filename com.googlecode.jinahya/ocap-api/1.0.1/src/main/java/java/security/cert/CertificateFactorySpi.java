
package java.security.cert;

/**
 * <p>This is not an official specification document, and usage is restricted.
 * </p>
 * <a name="notice"><strong><center>
 * NOTICE
 * </center></strong><br>
 * <br>
 * 
 * (c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
 * <p>
 * Neither this file nor any files generated from it describe a complete
 * specification, and they may only be used as described below. For
 * example, no permission is given for you to incorporate this file, in
 * whole or in part, in an implementation of a Java specification.
 * <p>
 * Sun Microsystems Inc. owns the copyright in this file and it is provided
 * to you for informative, as opposed to normative, use. The file and any
 * files generated from it may be used to generate other informative
 * documentation, such as a unified set of documents of API signatures for
 * a platform that includes technologies expressed as Java APIs. The file
 * may also be used to produce "compilation stubs," which allow
 * applications to be compiled and validated for such platforms.
 * <p>
 * Any work generated from this file, such as unified javadocs or compiled
 * stub files, must be accompanied by this notice in its entirety.
 * <p>
 * This work corresponds to the API signatures of JSR 219: Foundation
 * Profile 1.1. In the event of a discrepency between this work and the
 * JSR 219 specification, which is available at
 * http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
 **/

public abstract class CertificateFactorySpi
{

    static {
        System.out.println("                               NOTICE");
        System.out.println("");
        System.out.println("(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.");
        System.out.println("");
        System.out.println("Neither this file nor any files generated from it describe a complete");
        System.out.println("specification, and they may only be used as described below. For");
        System.out.println("example, no permission is given for you to incorporate this file, in");
        System.out.println("whole or in part, in an implementation of a Java specification.");
        System.out.println("");
        System.out.println("Sun Microsystems Inc. owns the copyright in this file and it is provided");
        System.out.println("to you for informative, as opposed to normative, use. The file and any");
        System.out.println("files generated from it may be used to generate other informative");
        System.out.println("documentation, such as a unified set of documents of API signatures for");
        System.out.println("a platform that includes technologies expressed as Java APIs. The file");
        System.out.println("may also be used to produce \"compilation stubs,\" which allow");
        System.out.println("applications to be compiled and validated for such platforms.");
        System.out.println("");
        System.out.println("Any work generated from this file, such as unified javadocs or compiled");
        System.out.println("stub files, must be accompanied by this notice in its entirety.");
        System.out.println("");
        System.out.println("This work corresponds to the API signatures of JSR 219: Foundation");
        System.out.println("Profile 1.1. In the event of a discrepency between this work and the");
        System.out.println("JSR 219 specification, which is available at");
        System.out.println("http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.");
    }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public CertificateFactorySpi()
         { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract java.security.cert.CRL engineGenerateCRL(java.io.InputStream arg0) throws java.security.cert.CRLException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract java.security.cert.Certificate engineGenerateCertificate(java.io.InputStream arg0) throws java.security.cert.CertificateException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract java.util.Collection engineGenerateCRLs(java.io.InputStream arg0) throws java.security.cert.CRLException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract java.util.Collection engineGenerateCertificates(java.io.InputStream arg0) throws java.security.cert.CertificateException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.security.cert.CertPath engineGenerateCertPath(java.io.InputStream arg0) throws java.security.cert.CertificateException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.security.cert.CertPath engineGenerateCertPath(java.io.InputStream arg0, java.lang.String arg1) throws java.security.cert.CertificateException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.security.cert.CertPath engineGenerateCertPath(java.util.List arg0) throws java.security.cert.CertificateException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.util.Iterator engineGetCertPathEncodings()
        { return null; }


}
