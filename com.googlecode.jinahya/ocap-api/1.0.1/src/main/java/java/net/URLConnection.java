
package java.net;

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

public abstract class URLConnection
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
    protected URLConnection(java.net.URL arg0)
         { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract void connect() throws java.io.IOException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public boolean getAllowUserInteraction()
        { return false; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public boolean getDefaultUseCaches()
        { return false; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public boolean getDoInput()
        { return false; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public boolean getDoOutput()
        { return false; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public boolean getUseCaches()
        { return false; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public int getContentLength()
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public int getHeaderFieldInt(java.lang.String arg0, int arg1)
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.io.InputStream getInputStream() throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.io.OutputStream getOutputStream() throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.Object getContent() throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.Object getContent(java.lang.Class[] arg0) throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String getContentEncoding()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String getContentType()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String getHeaderField(int arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String getHeaderField(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String getHeaderFieldKey(int arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String getRequestProperty(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String toString()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.net.URL getURL()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.security.Permission getPermission() throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.util.Map getHeaderFields()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.util.Map getRequestProperties()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public long getDate()
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public long getExpiration()
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public long getHeaderFieldDate(java.lang.String arg0, long arg1)
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public long getIfModifiedSince()
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public long getLastModified()
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static boolean getDefaultAllowUserInteraction()
        { return false; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static java.lang.String guessContentTypeFromName(java.lang.String arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static java.lang.String guessContentTypeFromStream(java.io.InputStream arg0) throws java.io.IOException
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static java.net.FileNameMap getFileNameMap()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static void setContentHandlerFactory(java.net.ContentHandlerFactory arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static void setDefaultAllowUserInteraction(boolean arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static void setFileNameMap(java.net.FileNameMap arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void addRequestProperty(java.lang.String arg0, java.lang.String arg1)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setAllowUserInteraction(boolean arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setDefaultUseCaches(boolean arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setDoInput(boolean arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setDoOutput(boolean arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setIfModifiedSince(long arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setRequestProperty(java.lang.String arg0, java.lang.String arg1)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setUseCaches(boolean arg0)
        { return; }


    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected boolean allowUserInteraction;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected boolean connected;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected boolean doInput;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected boolean doOutput;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected boolean useCaches;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected java.net.URL url;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected long ifModifiedSinceL;

}
