
package javax.microedition.io;

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

public interface SocketConnection
        extends javax.microedition.io.StreamConnection
{


    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract int getLocalPort() throws java.io.IOException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract int getPort() throws java.io.IOException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract int getSocketOption(byte arg0) throws java.io.IOException, java.lang.IllegalArgumentException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract java.lang.String getAddress() throws java.io.IOException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract java.lang.String getLocalAddress() throws java.io.IOException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract void setSocketOption(byte arg0, int arg1) throws java.io.IOException, java.lang.IllegalArgumentException;


    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final byte DELAY = 0;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final byte KEEPALIVE = 2;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final byte LINGER = 1;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final byte RCVBUF = 3;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final byte SNDBUF = 4;

}
