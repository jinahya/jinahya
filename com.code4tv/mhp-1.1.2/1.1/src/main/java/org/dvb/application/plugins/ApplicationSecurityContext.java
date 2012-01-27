

package org.dvb.application.plugins;

import javax.tv.locator.Locator;

import java.security.PrivilegedAction;

/**
 * This class represents the security context for an application
 * whose permissions are defined by the MHP security model, in
 * particular a permission request file. One example of this is
 * DVB-HTML applications managed by a plug-in application. This would
 * also apply to applications in other content format where the
 * permissions for that content format are signalled with MHP
 * mechanisms and governed by the MHP security model.
 **/

public class ApplicationSecurityContext {

    /**
     * Creates a new security context for an application whose code can
     * be found on the path supplied, and whose entry point directory is as given.
     * Under no circumstances will an application managed by a plug-in
     * be given access to resources not available to the plug-in itself.
     * This policy is reflected in the permissions granted to the
     * ApplicationSecurityContext, as well as the permissions granted to
     * any classes loaded by any class loaders managed by a security context.
     *<p>
     * If there is a permission request file in the directory identified by
     * the entryPoint, it will be processed in the same way as the permission
     * request file of a DVB-J application. i.e. reading it in, parsing it and
     * taking account of the access rights granted by the user as defined under
     * "General principles" in the main body of the present document. 
     *
     * @param path  The search path for locating resources within the application.
     *
     * @param entryPoint The directory containing the permission request file to use
     *
     * @param appID the application ID which the application is to run under
     *
     * @throws IOException when there is an IO error reading in the permission
     * request file or attempting to read in the permission request file or
     * attempting to discover the existence of a permission request file.
     * @throws NullPointerException if entryPoint is null, if path is null,
     *		or if any element of path is null.
     * @throws IllegalArgumentException if path.length < 1
     **/
    public ApplicationSecurityContext(java.net.URL[] path,
                                  java.net.URL entryPoint,
				  org.dvb.application.AppID appID) 
                                  throws java.io.IOException {
    }

    /**
     * Get a locator to the named resource, within the search path for this
     * application. Will return null if a resource with the given name that
     * is appropriately signed (if necessary) cannot be found.
     * <p>
     * Note (informative):  This method can be used, for example, by an
     * interoperable plug-in that needs to fetch part of an application
     * that is not loaded by a classloader. For example, it could be
     * used to get a locator to an HTML page, if and only if that page
     * is appropriately signed.
     *
     * @param name	The name of the resource (e.g. com/foo/MyPage.html)
     *
     * @param sameSigner True if this is code, or any other resource for
     *			 which the signer must be the same as the signer
     *			 of the entry point.
     *
     * @return URL to the named resource, or null.
     **/
    public java.net.URL getResource(String name, boolean sameSigner) {
	return null;
    }

    /**
     * Creates a context for an embedded part of an application, e.g. an
     * Xlet embedded within a DVB-HTML page. The set of permissions
     * granted to the application will be the same as the parent
     * ApplicationSecurityContext.
     *
     * @param path  The search path for locating resources within the application.
     *
     * @param entryPoint The resource of the entry point of this application.
     *
     * @return a context for the part of the application concerned
     *
     * @throws NullPointerException if entryPoint is null, if path is null,
     *		or if any element of path is null.
     * @throws IllegalArgumentException if path.length < 1
     **/
    public ApplicationSecurityContext 
	    createEmbeddedContext(java.net.URL[] path, java.net.URL entryPoint) {
		return null;
    }

    /**
     * Get a classloader that is appropriate for loading classes for the
     * application (or sub-application) represented by this application
     * security context object. If this method is called more than once,
     * the same instance will be returned.
     * <p>
     * It is important that embedded DVB-J code be prevented from accessing
     * classes that implement the plug-in application. To this end, the
     * plug-in may specify a list of forbidden packages. Classes loaded
     * by the returned classloader will be forbidden from loading or accessing
     * classes in the named packages.
     *
     * @param forbiddenPackages a list of forbidden package names, e.g.
     *		<code>{ "de.tu-bs.ing.ifn.plugin.impl" }</code>.
     *
     * @return A class loader that is appropriate for loading and DVB-J
     *		classes that are a part of
     *		this application or sub-application.
     **/
    public ClassLoader getClassLoader(String[] forbiddenPackages) {
	return null;
    }

    /**
     * Throws a <code>SecurityException</code> if the requested
     * access, specified by the given permission, is not permitted
     * to the application or sub-application represented by this
     * application security context object. The set of permissions
     * granted to an entity is be a function of receiver policy,
     * possibly influenced by user settings,
     * application signer, and permission request file.
     *
     * @param p	A permission object representing the resource for which
     *		access is being checked.
     *
     * @throws NullPointerException if p is null
     * @throws SecurityException if this application has not been
     * 		granted access to the resource represented by p.
     **/
    public void checkPermission(java.security.Permission p) {
    }
/**
 * Performs the specified PrivilegedAction with privileges enabled 
 * and restricted by the specified AccessControlContext. The action 
 * is performed with the intersection of the permissions possessed by 
 * the caller's protection domain, and those possessed by the domains 
 * represented by the specified AccessControlContext.
 * 
 * If the action's run method throws an (unchecked) exception, it will 
 * propagate through this method.
 * 
 * @param action the action to be performed.
 * @return the value returned by the action's run method.
 */

public Object doPrivileged( PrivilegedAction action){ return null;}

}

