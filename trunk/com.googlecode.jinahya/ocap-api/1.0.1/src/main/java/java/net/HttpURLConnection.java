
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

public abstract class HttpURLConnection
        extends java.net.URLConnection
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
    protected HttpURLConnection(java.net.URL arg0)
         { super(null); }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract boolean usingProxy();

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract void disconnect();

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public boolean getInstanceFollowRedirects()
        { return false; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public int getResponseCode() throws java.io.IOException
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.io.InputStream getErrorStream()
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
    public java.lang.String getHeaderFieldKey(int arg0)
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String getRequestMethod()
        { return null; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public java.lang.String getResponseMessage() throws java.io.IOException
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
    public long getHeaderFieldDate(java.lang.String arg0, long arg1)
        { return  0; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static boolean getFollowRedirects()
        { return false; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static void setFollowRedirects(boolean arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setInstanceFollowRedirects(boolean arg0)
        { return; }

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public void setRequestMethod(java.lang.String arg0) throws java.net.ProtocolException
        { return; }


    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected String method;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected String responseMessage;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected boolean instanceFollowRedirects;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    protected int responseCode;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_ACCEPTED = 202;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_BAD_GATEWAY = 502;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_BAD_METHOD = 405;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_BAD_REQUEST = 400;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_CLIENT_TIMEOUT = 408;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_CONFLICT = 409;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_CREATED = 201;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_ENTITY_TOO_LARGE = 413;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_FORBIDDEN = 403;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_GATEWAY_TIMEOUT = 504;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_GONE = 410;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_INTERNAL_ERROR = 500;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_LENGTH_REQUIRED = 411;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_MOVED_PERM = 301;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_MOVED_TEMP = 302;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_MULT_CHOICE = 300;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_NOT_ACCEPTABLE = 406;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_NOT_AUTHORITATIVE = 203;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_NOT_FOUND = 404;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_NOT_IMPLEMENTED = 501;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_NOT_MODIFIED = 304;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_NO_CONTENT = 204;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_OK = 200;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_PARTIAL = 206;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_PAYMENT_REQUIRED = 402;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_PRECON_FAILED = 412;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_PROXY_AUTH = 407;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_REQ_TOO_LONG = 414;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_RESET = 205;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_SEE_OTHER = 303;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_UNAUTHORIZED = 401;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_UNAVAILABLE = 503;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_UNSUPPORTED_TYPE = 415;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_USE_PROXY = 305;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int HTTP_VERSION = 505;

}
