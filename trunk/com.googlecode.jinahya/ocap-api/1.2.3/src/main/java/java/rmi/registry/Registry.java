/*
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
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.rmi.registry;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

// PBP/PP
/** 
 * <code>Registry</code> is a remote interface to a simple remote
 * object registry that provides methods for storing and retrieving
 * remote object references bound with arbitrary string names.  The
 * <code>bind</code>, <code>unbind</code>, and <code>rebind</code>
 * methods are used to alter the name bindings in the registry, and
 * the <code>lookup</code> and <code>list</code> methods are used to
 * query the current name bindings.
 *

 *
 * <p>A <code>Registry</code> implementation may choose to restrict
 * access to some or all of its methods (for example, methods that
 * mutate the registry's bindings may be restricted to calls
 * originating from the local host).  If a <code>Registry</code>
 * method chooses to deny access for a given invocation, its
 * implementation may throw {@link java.rmi.AccessException}.
 *
 * <p>The names used for bindings in a <code>Registry</code> are pure
 * strings, not parsed.  A service which stores its remote reference
 * in a <code>Registry</code> may wish to use a package name as a
 * prefix in the name binding to reduce the likelihood of name
 * collisions in the registry.
 *
 * @author	Ann Wollrath
 * @author	Peter Jones
 * @version	1.17, 03/01/23
 * @since	JDK1.1
 * @see		
 */
public interface Registry extends Remote
{
    /** Well known port for registry.  */
    public static final int REGISTRY_PORT = 1099;

    /** 
     * Returns the remote reference bound to the specified
     * <code>name</code> in this registry.
     *
     * @param	name the name for the remote reference to look up
     *
     * @return	a reference to a remote object
     *
     * @throws	NotBoundException if <code>name</code> is not currently bound
     *
     * @throws	RemoteException if remote communication with the
     * registry failed
     *
     * @throws	AccessException if this registry is local and it denies
     * the caller access to perform this operation
     *
     * @throws	NullPointerException if <code>name</code> is <code>null</code>
     */
    public Remote lookup(String name)
        throws java.rmi.RemoteException, NotBoundException, AccessException;

    /** 
     * Binds a remote reference to the specified <code>name</code> in
     * this registry.
     *
     * @param	name the name to associate with the remote reference
     * @param	obj a reference to a remote object (usually a stub)
     *
     * @throws	AlreadyBoundException if <code>name</code> is already bound
     *
     * @throws	RemoteException if remote communication with the
     * registry failed
     *
     * @throws	AccessException if this registry is local and it denies
     * the caller access to perform this operation
     *
     * @throws	NullPointerException if <code>name</code> is
     * <code>null</code>, or if <code>obj</code> is <code>null</code>
     */
    public void bind(String name, Remote obj)
        throws java.rmi.RemoteException, AlreadyBoundException, AccessException;

    /** 
     * Removes the binding for the specified <code>name</code> in
     * this registry.
     *
     * @param	name the name of the binding to remove
     *
     * @throws	NotBoundException if <code>name</code> is not currently bound
     *
     * @throws	RemoteException if remote communication with the
     * registry failed
     *
     * @throws	AccessException if this registry is local and it denies
     * the caller access to perform this operation
     *
     * @throws	NullPointerException if <code>name</code> is <code>null</code>
     */
    public void unbind(String name)
        throws java.rmi.RemoteException, NotBoundException, AccessException;

    /** 
     * Replaces the binding for the specified <code>name</code> in
     * this registry with the supplied remote reference.  If there is
     * an existing binding for the specified <code>name</code>, it is
     * discarded.
     *
     * @param	name the name to associate with the remote reference
     * @param	obj a reference to a remote object (usually a stub)
     *
     * @throws	RemoteException if remote communication with the
     * registry failed
     *
     * @throws	AccessException if this registry is local and it denies
     * the caller access to perform this operation
     *
     * @throws	NullPointerException if <code>name</code> is
     * <code>null</code>, or if <code>obj</code> is <code>null</code>
     */
    public void rebind(String name, Remote obj)
        throws java.rmi.RemoteException, AccessException;

    /** 
     * Returns an array of the names bound in this registry.  The
     * array will contain a snapshot of the names bound in this
     * registry at the time of the given invocation of this method.
     *
     * @return	an array of the names bound in this registry
     *
     * @throws	RemoteException if remote communication with the
     * registry failed
     *
     * @throws	AccessException if this registry is local and it denies
     * the caller access to perform this operation
     */
    public String[] list() throws java.rmi.RemoteException, AccessException;
}
