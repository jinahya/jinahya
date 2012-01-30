
package java.rmi.registry;

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
 * This work corresponds to the API signatures of JSR 217: Personal Basis
 * Profile 1.1. In the event of a discrepency between this work and the
 * JSR 217 specification, which is available at
 * http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
 **/

public interface Registry
        extends java.rmi.Remote
{


    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract java.lang.String[] list() throws java.rmi.AccessException, java.rmi.RemoteException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract java.rmi.Remote lookup(java.lang.String arg0) throws java.rmi.AccessException, java.rmi.NotBoundException, java.rmi.RemoteException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract void bind(java.lang.String arg0, java.rmi.Remote arg1) throws java.rmi.AccessException, java.rmi.AlreadyBoundException, java.rmi.RemoteException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract void rebind(java.lang.String arg0, java.rmi.Remote arg1) throws java.rmi.AccessException, java.rmi.RemoteException;

    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public abstract void unbind(java.lang.String arg0) throws java.rmi.AccessException, java.rmi.NotBoundException, java.rmi.RemoteException;


    /**
     * This is not an official specification document, and usage is
     * restricted. Please refer to the <a href="#notice">notice</a>
     * at the top of this file.
     **/
    public static final int REGISTRY_PORT = 1099;

}
