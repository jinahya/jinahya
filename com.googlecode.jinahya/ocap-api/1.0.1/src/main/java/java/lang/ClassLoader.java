
package java.lang;

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

public abstract class ClassLoader
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
    protected ClassLoader()
         { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected ClassLoader(java.lang.ClassLoader arg0)
         { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected final java.lang.Class defineClass(java.lang.String arg0, byte[] arg1, int arg2, int arg3) throws java.lang.ClassFormatError
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected final java.lang.Class defineClass(java.lang.String arg0, byte[] arg1, int arg2, int arg3, java.security.ProtectionDomain arg4) throws java.lang.ClassFormatError
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected final java.lang.Class findLoadedClass(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected final java.lang.Class findSystemClass(java.lang.String arg0) throws java.lang.ClassNotFoundException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected final void resolveClass(java.lang.Class arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected final void setSigners(java.lang.Class arg0, java.lang.Object[] arg1)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.lang.Class findClass(java.lang.String arg0) throws java.lang.ClassNotFoundException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.lang.Class loadClass(java.lang.String arg0, boolean arg1) throws java.lang.ClassNotFoundException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.lang.Package definePackage(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5, java.lang.String arg6, java.net.URL arg7) throws java.lang.IllegalArgumentException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.lang.Package getPackage(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.lang.Package[] getPackages()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.lang.String findLibrary(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.net.URL findResource(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.util.Enumeration findResources(java.lang.String arg0) throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public final java.lang.ClassLoader getParent()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public final java.util.Enumeration getResources(java.lang.String arg0) throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.io.InputStream getResourceAsStream(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.Class loadClass(java.lang.String arg0) throws java.lang.ClassNotFoundException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.net.URL getResource(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static java.io.InputStream getSystemResourceAsStream(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static java.lang.ClassLoader getSystemClassLoader()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static java.net.URL getSystemResource(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static java.util.Enumeration getSystemResources(java.lang.String arg0) throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void clearAssertionStatus()
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setClassAssertionStatus(java.lang.String arg0, boolean arg1)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setDefaultAssertionStatus(boolean arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setPackageAssertionStatus(java.lang.String arg0, boolean arg1)
        { return; }


}
