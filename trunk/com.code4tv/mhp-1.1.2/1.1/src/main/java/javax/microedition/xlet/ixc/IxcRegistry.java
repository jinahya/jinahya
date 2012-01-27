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


package javax.microedition.xlet.ixc;

import java.rmi.*;
import java.rmi.registry.Registry;
import javax.microedition.xlet.XletContext;


/**
 * <code>IxcRegistry</code> is the bootstrap mechanism for obtaining
 * references to remote objects residing in other Xlets executing on
 * the same machine, but in separate classloaders.  The
 * <code>IxcRegistry</code> class provides access to a database
 * of objects exported for the purpose of inter-xlet communication (IXC).
 * A single database is shared for all IXC clients on a given machine.
 * Objects stored in the database are identified by a String name of
 * arbitrary format.
 * Each <code>IxcRegistry</code> instance is associated with a
 * single <code>XletContext</code>, in order to identify the
 * IXC client using it.
 * 
 * <p>
 * Instances of <code>IXCRegistry</code> are never accessible via
 * <code>java.rmi.Naming</code> or
 * <code>java.rmi.registry.LocateRegistry</code> if RMI functionality
 * is implemented.
 *
 * @see java.rmi.registry.Registry
 * @see IxcRegistry#getRegistry
 */
public abstract class IxcRegistry implements Registry {

    /**
     * Creates the IxcRegistry.
     * (This class cannot be instantiated directly.)
     *
     * @see #getRegistry
     */
    protected IxcRegistry() {}

    /**
     * Provides the inter-Xlet communication registry for use by the
     * calling Xlet.
     *
     * @param context The context of the Xlet requesting the registry.
     *
     * @return The inter-Xlet communication registry.
     * 
     * @throws NullPointerException If <code>context</code> or
     * <code>context.getClassLoader()</code> is <code>null</code>.
     */
    public static IxcRegistry getRegistry(XletContext context)
    {
	return null;
    }

    /**
     * Returns a reference, a stub, for the remote object associated
     * with the specified <code>name</code>.
     * <p>
     * First, if there is a security manager, its
     * <code>checkPermission</code> method is called with the permission
     * <code>IxcPermission(name, "lookup")</code>.
     * 
     * @param name the name of the remote object
     * @return a reference for a remote object
     * @exception NotBoundException If name is not currently bound
     * @exception StubException If a stub could not be generated.
     * @throws SecurityException If the caller does not have the permission
     *         <code>IxcPermission(name, "lookup")</code>
     *
     * @see IxcPermission
     */
    public abstract Remote lookup(String name)
	throws StubException, NotBoundException;

    /**
     * Binds the specified <code>name</code> to a remote object.
     * <p>
     * First, if there is a security manager, its
     * <code>checkPermission</code> method is called with the permission
     * <code>IxcPermission(name, "bind")</code>.
     * <p>
     * A pre-check is performed on the Remote object to
     * verify that it conforms to the rules for well-formed Remote
     * interfaces; if it does not, a <code>StubException</code> is
     * thrown.
     *
     * @param name the name of the remote object
     * @param obj a reference for the remote object (usually a stub)
     * @exception AlreadyBoundException if name is already bound
     * @exception StubException If a stub could not be generated.
     * @throws SecurityException If the caller does not have the permission
     *         <code>IxcPermission(name, "bind")</code>
     *
     * @see IxcPermission
     */
    public abstract void bind(String name, Remote obj)
	throws StubException, AlreadyBoundException;
    
    /**
     * Destroys the binding for the specified name that is associated
     * with a remote object.
     * <p>
     * First, if there is a security manager, its
     * <code>checkPermission</code> method is called with the permission
     * <code>IxcPermission(name, "bind")</code>.
     * However, names bound to objects exported by the calling xlet are
     * exempted from this permission check. In other words, xlets are always
     * allowed to unbind objects they have currently exported.
     *
     * @param name the name of the remote object
     * 
     * @throws SecurityException If name is not bound to an object exported
     * by the calling xlet and the caller does not have the permission
     * <code>IxcPermission(name, "bind")</code>.
     * @exception NotBoundException If name is not currently bound.
     * @exception AccessException If <code>name</code> is bound to an
     * object exported in a different registry.
     * 
     * @see IxcPermission
     */
    public abstract void unbind(String name)
	throws NotBoundException, AccessException;

    /**
     * Removes the bindings for all remote objects currently exported by
     * the calling Xlet.
     */
    public abstract void unbindAll();


    /** 
     * Rebinds the specified name to a new remote object. Any existing
     * binding for the name is replaced.
     * <p>
     * First, if there is a security manager, its
     * <code>checkPermission</code> method is called with the permission
     * <code>IxcPermission(name, "bind")</code>.
     * <p>
     * A pre-check is performed on the Remote object to
     * verify that it conforms to the rules for well-formed Remote
     * interfaces; if it does not, a <code>StubException</code> is
     * thrown.
     *
     * @param name the name of the remote object
     * @param obj new remote object to associate with the name
     * @exception StubException If a stub could not be generated.
     * @exception AccessException If <code>name</code> is bound to an
     * object exported in a different registry.
     * @throws SecurityException If the caller does not have the permission
     *         IxcPermission(name, "bind").  In such an event, the remote
     *         object remains bound as before.
     * 
     * @see IxcPermission
     */
    public abstract void rebind(String name, Remote obj)
	throws StubException, AccessException;

    /**
     * Returns an array of the names bound in the registry.
     * The array contains a snapshot of the names
     * present in the registry at the time of the call
     * for which the caller has the permission
     * <code>IxcPermission(name, "lookup")</code>.
     *
     * @return an array of names bound in the registry
     *
     * @see IxcPermission
     */
    public abstract String[] list();

}
