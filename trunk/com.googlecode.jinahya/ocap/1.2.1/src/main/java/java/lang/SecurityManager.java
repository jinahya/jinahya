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


  


package java.lang;

import java.security.*;
import java.lang.reflect.*;

import java.io.FileDescriptor;
import java.io.File;
import java.io.FilePermission;
import java.util.PropertyPermission;
import java.lang.RuntimePermission;
import java.net.SocketPermission;
import java.net.NetPermission;
import java.util.Hashtable;
import java.net.InetAddress;
import java.lang.reflect.Member;
import java.net.URL;

/** 
 * The security manager is a class that allows 
 * applications to implement a security policy. It allows an 
 * application to determine, before performing a possibly unsafe or 
 * sensitive operation, what the operation is and whether 
 * it is being attempted in a security context that allows the
 * operation to be performed. The 
 * application can allow or disallow the operation. 
 * <p>
 * The <code>SecurityManager</code> class contains many methods with 
 * names that begin with the word <code>check</code>. These methods 
 * are called by various methods in the Java libraries before those 
 * methods perform certain potentially sensitive operations. The 
 * invocation of such a <code>check</code> method typically looks like this: 
 * <p><blockquote><pre>
 *     SecurityManager security = System.getSecurityManager();
 *     if (security != null) {
 *         security.check<i>XXX</i>(argument, &nbsp;.&nbsp;.&nbsp;.&nbsp;);
 *     }
 * </pre></blockquote>
 * <p>
 * The security manager is thereby given an opportunity to prevent 
 * completion of the operation by throwing an exception. A security 
 * manager routine simply returns if the operation is permitted, but 
 * throws a <code>SecurityException</code> if the operation is not 
 * permitted. The only exception to this convention is 
 * <code>checkTopLevelWindow</code>, which returns a 
 * <code>boolean</code> value. 
 * <p>
 * The current security manager is set by the 
 * <code>setSecurityManager</code> method in class 
 * <code>System</code>. The current security manager is obtained 
 * by the <code>getSecurityManager</code> method. 
 * <p> 
 * The special method 
 * {@link SecurityManager#checkPermission(java.security.Permission)}
 * determines whether an access request indicated by a specified
 * permission should be granted or denied. The 
 * default implementation calls
 * 
 * <pre>
 *   AccessController.checkPermission(perm);
 * </pre>
 *
 * <p> 
 * If a requested access is allowed, 
 * <code>checkPermission</code> returns quietly. If denied, a 
 * <code>SecurityException</code> is thrown. 
 * <p>
 * As of Java 2 SDK v1.2, the default implementation of each of the other
 * <code>check</code> methods in <code>SecurityManager</code> is to 
 * call the <code>SecurityManager checkPermission</code> method
 * to determine if the calling thread has permission to perform the requested
 * operation.
 * <p> 
 * Note that the <code>checkPermission</code> method with
 * just a single permission argument always performs security checks
 * within the context of the currently executing thread.
 * Sometimes a security check that should be made within a given context
 * will actually need to be done from within a
 * <i>different</i> context (for example, from within a worker thread).
 * The {@link SecurityManager#getSecurityContext getSecurityContext} method 
 * and the {@link SecurityManager#checkPermission(java.security.Permission, 
 * java.lang.Object) checkPermission}
 * method that includes a context argument are provided 
 * for this situation. The 
 * <code>getSecurityContext</code> method returns a "snapshot"
 * of the current calling context. (The default implementation 
 * returns an AccessControlContext object.) A sample call is
 * the following:
 * 
 * <pre>
 *   Object context = null;
 *   SecurityManager sm = System.getSecurityManager();
 *   if (sm != null) context = sm.getSecurityContext(); 
 * </pre>
 * 
 * <p>
 * The <code>checkPermission</code> method
 * that takes a context object in addition to a permission 
 * makes access decisions based on that context,
 * rather than on that of the current execution thread.
 * Code within a different context can thus call that method,
 * passing the permission and the
 * previously-saved context object. A sample call, using the
 * SecurityManager <code>sm</code> obtained as in the previous example, 
 * is the following:
 * 
 * <pre>
 *   if (sm != null) sm.checkPermission(permission, context);
 * </pre> 
 *
 * <p>Permissions fall into these categories: File, Socket, Net, 
 * Security, Runtime, Property, AWT, Reflect, and Serializable. 
 * The classes managing these various
 * permission categories are <code>java.io.FilePermission</code>,
 * <code>java.net.SocketPermission</code>, 
 * <code>java.net.NetPermission</code>, 
 * <code>java.security.SecurityPermission</code>,
 * <code>java.lang.RuntimePermission</code>, 
 * <code>java.util.PropertyPermission</code>, 
 * <code>java.lang.reflect.ReflectPermission</code>, and
 * <code>java.io.SerializablePermission</code>. 
 * 
 * <p>All but the first two (FilePermission and SocketPermission) are 
 * subclasses of <code>java.security.BasicPermission</code>, which itself 
 * is an abstract subclass of the
 * top-level class for permissions, which is 
 * <code>java.security.Permission</code>. BasicPermission defines the 
 * functionality needed for all permissions that contain a name 
 * that follows the hierarchical property naming convention 
 * (for example, "exitVM", "setFactory", "queuePrintJob", etc). 
 * An asterisk 
 * may appear at the end of the name, following a ".", or by itself, to 
 * signify a wildcard match. For example: "a.*" or "*" is valid, 
 * "*a" or "a*b" is not valid.
 *
 * <p>FilePermission and SocketPermission are subclasses of the
 * top-level class for permissions 
 * (<code>java.security.Permission</code>). Classes like these
 * that have a more complicated name syntax than that used by
 * BasicPermission subclass directly from Permission rather than from
 * BasicPermission. For example, 
 * for a <code>java.io.FilePermission</code> object, the permission name is
 * the path name of a file (or directory).
 *
 * <p>Some of the permission classes have an "actions" list that tells 
 * the actions that are permitted for the object.  For example, 
 * for a <code>java.io.FilePermission</code> object, the actions list
 * (such as "read, write") specifies which actions are granted for the
 * specified file (or for files in the specified directory).
 * 
 * <p>Other permission classes are for "named" permissions - 
 * ones that contain a name but no actions list; you either have the
 * named permission or you don't.
 * 
 * <p>Note: There is also a <code>java.security.AllPermission</code>
 * permission that implies all permissions. It exists to simplify the work
 * of system administrators who might need to perform multiple
 * tasks that require all (or numerous) permissions.
 * <p>
 * See <a href ="../../../guide/security/permissions.html">
 * Permissions in the Java 2 SDK</a> for permission-related information.
 * This document includes, for example, a table listing the various SecurityManager
 * <code>check</code> methods and the permission(s) the default 
 * implementation of each such method requires. 
 * It also contains a table of all the version 1.2 methods
 * that require permissions, and for each such method tells 
 * which permission it requires.
 * <p>
 * For more information about <code>SecurityManager</code> changes made in 
 * the Java 2 SDK and advice regarding porting of 1.1-style security managers,
 * see the <a href="../../../guide/security/index.html">security documentation</a>.
 *
 * @author  Arthur van Hoff
 * @author  Roland Schemers
 *
 * @version 1.125, 05/16/00
 * @see     java.lang.ClassLoader
 * @see     java.lang.SecurityException
 * @see     java.lang.SecurityManager#checkTopLevelWindow(java.lang.Object)
 *  checkTopLevelWindow
 * @see     java.lang.System#getSecurityManager() getSecurityManager
 * @see     java.lang.System#setSecurityManager(java.lang.SecurityManager)
 *  setSecurityManager
 * @see     java.security.AccessController AccessController
 * @see     java.security.AccessControlContext AccessControlContext
 * @see     java.security.AccessControlException AccessControlException
 * @see     java.security.Permission 
 * @see     java.security.BasicPermission
 * @see     java.io.FilePermission
 * @see     java.net.SocketPermission
 * @see     java.util.PropertyPermission
 * @see     java.lang.RuntimePermission
 * @see     java.security.Policy Policy
 * @see     java.security.SecurityPermission SecurityPermission
 * @see     java.security.ProtectionDomain
 *
 * @since   JDK1.0
 */
public class SecurityManager
{

    /** 
     * Constructs a new <code>SecurityManager</code>.
     *
     * <p> If there is a security manager already installed, this method first
     * calls the security manager's <code>checkPermission</code> method
     * with the <code>RuntimePermission("createSecurityManager")</code>
     * permission to ensure the calling thread has permission to create a new 
     * security manager.
     * This may result in throwing a <code>SecurityException</code>.
     *
     * @exception  java.lang.SecurityException if a security manager already 
     *             exists and its <code>checkPermission</code> method 
     *             doesn't allow creation of a new security manager.
     * @see        java.lang.System#getSecurityManager()
     * @see        #checkPermission(java.security.Permission) checkPermission
     * @see java.lang.RuntimePermission
     */
    public SecurityManager() { }

    /** 
     * Returns the current execution stack as an array of classes. 
     * <p>
     * The length of the array is the number of methods on the execution 
     * stack. The element at index <code>0</code> is the class of the 
     * currently executing method, the element at index <code>1</code> is 
     * the class of that method's caller, and so on. 
     *
     * @return  the execution stack.
     */
    protected java.lang.Class[] getClassContext() {
        return null;
    }

    /** 
     * Creates an object that encapsulates the current execution 
     * environment. The result of this method is used, for example, by the 
     * three-argument <code>checkConnect</code> method and by the 
     * two-argument <code>checkRead</code> method. 
     * These methods are needed because a trusted method may be called 
     * on to read a file or open a socket on behalf of another method. 
     * The trusted method needs to determine if the other (possibly 
     * untrusted) method would be allowed to perform the operation on its 
     * own. 
     * <p> The default implementation of this method is to return 
     * an <code>AccessControlContext</code> object.
     *
     * @return  an implementation-dependent object that encapsulates
     *          sufficient information about the current execution environment
     *          to perform some security checks later.
     * @see     java.lang.SecurityManager#checkConnect(java.lang.String, int, 
     *   java.lang.Object) checkConnect
     * @see     java.lang.SecurityManager#checkRead(java.lang.String, 
     *   java.lang.Object) checkRead
     * @see     java.security.AccessControlContext AccessControlContext
     */
    public java.lang.Object getSecurityContext() {
        return null;
    }

    /** 
     * Throws a <code>SecurityException</code> if the requested
     * access, specified by the given permission, is not permitted based
     * on the security policy currently in effect.
     * <p>
     * This method calls <code>AccessController.checkPermission</code> 
     * with the given permission.
     *
     * @param     perm   the requested permission.
     * @exception SecurityException if access is not permitted based on
     *		  the current security policy.
     * @exception NullPointerException if the permission argument is
     *            <code>null</code>.
     * @since     1.2
     */
    public void checkPermission(Permission perm) { }

    /** 
     * Throws a <code>SecurityException</code> if the
     * specified security context is denied access to the resource
     * specified by the given permission.
     * The context must be a security 
     * context returned by a previous call to 
     * <code>getSecurityContext</code> and the access control
     * decision is based upon the configured security policy for
     * that security context.
     * <p>
     * If <code>context</code> is an instance of 
     * <code>AccessControlContext</code> then the
     * <code>AccessControlContext.checkPermission</code> method is
     * invoked with the specified permission.
     * <p>
     * If <code>context</code> is not an instance of 
     * <code>AccessControlContext</code> then a
     * <code>SecurityException</code> is thrown. 
     *
     * @param      perm      the specified permission
     * @param      context   a system-dependent security context.
     * @exception  SecurityException  if the specified security context
     *             is not an instance of <code>AccessControlContext</code>
     *             (e.g., is <code>null</code>), or is denied access to the
     *             resource specified by the given permission.
     * @exception  NullPointerException if the permission argument is
     *             <code>null</code>.
     * @see        java.lang.SecurityManager#getSecurityContext()
     * @see java.security.AccessControlContext#checkPermission(java.security.Permission) 
     * @since      1.2
     */
    public void checkPermission(Permission perm, java.lang.Object context) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to create a new class loader. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>RuntimePermission("createClassLoader")</code>
     * permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkCreateClassLoader</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @exception SecurityException if the calling thread does not 
     *             have permission
     *             to create a new class loader.
     * @see        java.lang.ClassLoader#ClassLoader()
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkCreateClassLoader() { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to modify the thread argument. 
     * <p>
     * This method is invoked for the current security manager by the 
     * <code>stop</code>, <code>suspend</code>, <code>resume</code>, 
     * <code>setPriority</code>, <code>setName</code>, and 
     * <code>setDaemon</code> methods of class <code>Thread</code>. 
     * <p>
     * If the thread argument is a system thread (belongs to
     * the thread group with a <code>null</code> parent) then 
     * this method calls <code>checkPermission</code> with the
     * <code>RuntimePermission("modifyThread")</code> permission.
     * If the thread argument is <i>not</i> a system thread,
     * this method just returns silently.
     * <p>
     * Applications that want a stricter policy should override this
     * method. If this method is overridden, the method that overrides
     * it should additionally check to see if the calling thread has the
     * <code>RuntimePermission("modifyThread")</code> permission, and
     * if so, return silently. This is to ensure that code granted
     * that permission (such as the SDK itself) is allowed to
     * manipulate any thread.
     * <p>
     * If this method is overridden, then 
     * <code>super.checkAccess</code> should
     * be called by the first statement in the overridden method, or the 
     * equivalent security check should be placed in the overridden method.
     *
     * @param      t   the thread to be checked.
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to modify the thread.
     * @exception  NullPointerException if the thread argument is
     *             <code>null</code>.
     * @see        java.lang.Thread#setDaemon(boolean) setDaemon
     * @see        java.lang.Thread#setName(java.lang.String) setName
     * @see        java.lang.Thread#setPriority(int) setPriority
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkAccess(java.lang.Thread t) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to modify the thread group argument. 
     * <p>
     * This method is invoked for the current security manager when a 
     * new child thread or child thread group is created, and by the 
     * <code>setDaemon</code>, <code>setMaxPriority</code>, 
     * <code>stop</code>, <code>suspend</code>, <code>resume</code>, and 
     * <code>destroy</code> methods of class <code>ThreadGroup</code>. 
     * <p>
     * If the thread group argument is the system thread group (
     * has a <code>null</code> parent) then 
     * this method calls <code>checkPermission</code> with the
     * <code>RuntimePermission("modifyThreadGroup")</code> permission.
     * If the thread group argument is <i>not</i> the system thread group,
     * this method just returns silently.
     * <p>
     * Applications that want a stricter policy should override this
     * method. If this method is overridden, the method that overrides
     * it should additionally check to see if the calling thread has the
     * <code>RuntimePermission("modifyThreadGroup")</code> permission, and
     * if so, return silently. This is to ensure that code granted
     * that permission (such as the SDK itself) is allowed to
     * manipulate any thread.
     * <p>
     * If this method is overridden, then 
     * <code>super.checkAccess</code> should
     * be called by the first statement in the overridden method, or the 
     * equivalent security check should be placed in the overridden method.
     *
     * @param      g   the thread group to be checked.
     * @exception  SecurityException  if the calling thread does not have
     *             permission to modify the thread group.
     * @exception  NullPointerException if the thread group argument is
     *             <code>null</code>.
     * @see        java.lang.ThreadGroup#destroy() destroy
     * @see        java.lang.ThreadGroup#setDaemon(boolean) setDaemon
     * @see        java.lang.ThreadGroup#setMaxPriority(int) setMaxPriority
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkAccess(java.lang.ThreadGroup g) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to cause the Java Virtual Machine to 
     * halt with the specified status code. 
     * <p>
     * This method is invoked for the current security manager by the 
     * <code>exit</code> method of class <code>Runtime</code>. A status 
     * of <code>0</code> indicates success; other values indicate various 
     * errors. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>RuntimePermission("exitVM")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkExit</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      status   the exit status.
     * @exception SecurityException if the calling thread does not have 
     *              permission to halt the Java Virtual Machine with 
     *              the specified status.
     * @see        java.lang.Runtime#exit(int) exit
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkExit(int status) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to create a subprocess. 
     * <p>
     * This method is invoked for the current security manager by the 
     * <code>exec</code> methods of class <code>Runtime</code>.
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>FilePermission(cmd,"execute")</code> permission
     * if cmd is an absolute path, otherwise it calls 
     * <code>checkPermission</code> with 
     * <code>FilePermission("&lt;&lt;ALL FILES&gt;&gt;","execute")</code>.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkExec</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      cmd   the specified system command.
     * @exception  SecurityException if the calling thread does not have 
     *             permission to create a subprocess.
     * @exception  NullPointerException if the <code>cmd</code> argument is
     *             <code>null</code>.
     * @see     java.lang.Runtime#exec(java.lang.String)
     * @see     java.lang.Runtime#exec(java.lang.String, java.lang.String[])
     * @see     java.lang.Runtime#exec(java.lang.String[])
     * @see     java.lang.Runtime#exec(java.lang.String[], java.lang.String[])
     * @see     #checkPermission(java.security.Permission) checkPermission
     */
    public void checkExec(java.lang.String cmd) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to dynamic link the library code 
     * specified by the string argument file. The argument is either a 
     * simple library name or a complete filename. 
     * <p>
     * This method is invoked for the current security manager by 
     * methods <code>load</code> and <code>loadLibrary</code> of class 
     * <code>Runtime</code>. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>RuntimePermission("loadLibrary."+lib)</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkLink</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      lib   the name of the library.
     * @exception  SecurityException if the calling thread does not have
     *             permission to dynamically link the library.
     * @exception  NullPointerException if the <code>lib</code> argument is
     *             <code>null</code>.
     * @see        java.lang.Runtime#load(java.lang.String)
     * @see        java.lang.Runtime#loadLibrary(java.lang.String)
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkLink(java.lang.String lib) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to read from the specified file 
     * descriptor. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>RuntimePermission("readFileDescriptor")</code>
     * permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkRead</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      fd   the system-dependent file descriptor.
     * @exception  SecurityException  if the calling thread does not have
     *             permission to access the specified file descriptor.
     * @exception  NullPointerException if the file descriptor argument is
     *             <code>null</code>.
     * @see        java.io.FileDescriptor
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkRead(java.io.FileDescriptor fd) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to read the file specified by the 
     * string argument. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>FilePermission(file,"read")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkRead</code>
     * at the point the overridden method would normally throw an
     * exception. 
     *
     * @param      file   the system-dependent file name.
     * @exception  SecurityException if the calling thread does not have 
     *             permission to access the specified file.
     * @exception  NullPointerException if the <code>file</code> argument is
     *             <code>null</code>.
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkRead(java.lang.String file) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * specified security context is not allowed to read the file 
     * specified by the string argument. The context must be a security 
     * context returned by a previous call to 
     * <code>getSecurityContext</code>. 
     * <p> If <code>context</code> is an instance of 
     * <code>AccessControlContext</code> then the
     * <code>AccessControlContext.checkPermission</code> method will
     * be invoked with the <code>FilePermission(file,"read")</code> permission.
     * <p> If <code>context</code> is not an instance of 
     * <code>AccessControlContext</code> then a
     * <code>SecurityException</code> is thrown. 
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkRead</code>
     * at the point the overridden method would normally throw an
     * exception. 
     *
     * @param      file      the system-dependent filename.
     * @param      context   a system-dependent security context.
     * @exception  SecurityException  if the specified security context
     *             is not an instance of <code>AccessControlContext</code>
     *             (e.g., is <code>null</code>), or does not have permission
     *             to read the specified file.
     * @exception  NullPointerException if the <code>file</code> argument is
     *             <code>null</code>.
     * @see        java.lang.SecurityManager#getSecurityContext()
     * @see        java.security.AccessControlContext#checkPermission(java.security.Permission)
     */
    public void checkRead(java.lang.String file, java.lang.Object context) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to write to the specified file 
     * descriptor. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>RuntimePermission("writeFileDescriptor")</code>
     * permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkWrite</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      fd   the system-dependent file descriptor.
     * @exception SecurityException  if the calling thread does not have
     *             permission to access the specified file descriptor.
     * @exception  NullPointerException if the file descriptor argument is
     *             <code>null</code>.
     * @see        java.io.FileDescriptor
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkWrite(java.io.FileDescriptor fd) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to write to the file specified by 
     * the string argument. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>FilePermission(file,"write")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkWrite</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      file   the system-dependent filename.
     * @exception  SecurityException  if the calling thread does not 
     *             have permission to access the specified file.
     * @exception  NullPointerException if the <code>file</code> argument is
     *             <code>null</code>.
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkWrite(java.lang.String file) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to delete the specified file. 
     * <p>
     * This method is invoked for the current security manager by the 
     * <code>delete</code> method of class <code>File</code>.
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>FilePermission(file,"delete")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkDelete</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      file   the system-dependent filename.
     * @exception  SecurityException if the calling thread does not 
     *             have permission to delete the file.
     * @exception  NullPointerException if the <code>file</code> argument is
     *             <code>null</code>.
     * @see        java.io.File#delete()
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkDelete(java.lang.String file) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to open a socket connection to the 
     * specified host and port number. 
     * <p>
     * A port number of <code>-1</code> indicates that the calling 
     * method is attempting to determine the IP address of the specified 
     * host name. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>SocketPermission(host+":"+port,"connect")</code> permission if
     * the port is not equal to -1. If the port is equal to -1, then
     * it calls <code>checkPermission</code> with the
     * <code>SocketPermission(host,"resolve")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkConnect</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      host   the host name port to connect to.
     * @param      port   the protocol port to connect to.
     * @exception  SecurityException  if the calling thread does not have
     *             permission to open a socket connection to the specified
     *               <code>host</code> and <code>port</code>.
     * @exception  NullPointerException if the <code>host</code> argument is
     *             <code>null</code>.
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkConnect(java.lang.String host, int port) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * specified security context is not allowed to open a socket 
     * connection to the specified host and port number. 
     * <p>
     * A port number of <code>-1</code> indicates that the calling 
     * method is attempting to determine the IP address of the specified 
     * host name. 
     * <p> If <code>context</code> is not an instance of 
     * <code>AccessControlContext</code> then a
     * <code>SecurityException</code> is thrown.
     * <p>
     * Otherwise, the port number is checked. If it is not equal
     * to -1, the <code>context</code>'s <code>checkPermission</code>
     * method is called with a 
     * <code>SocketPermission(host+":"+port,"connect")</code> permission.
     * If the port is equal to -1, then
     * the <code>context</code>'s <code>checkPermission</code> method 
     * is called with a
     * <code>SocketPermission(host,"resolve")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkConnect</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      host      the host name port to connect to.
     * @param      port      the protocol port to connect to.
     * @param      context   a system-dependent security context.
     * @exception  SecurityException if the specified security context
     *             is not an instance of <code>AccessControlContext</code>
     *             (e.g., is <code>null</code>), or does not have permission
     *             to open a socket connection to the specified
     *             <code>host</code> and <code>port</code>.
     * @exception  NullPointerException if the <code>host</code> argument is
     *             <code>null</code>.
     * @see        java.lang.SecurityManager#getSecurityContext()
     * @see        java.security.AccessControlContext#checkPermission(java.security.Permission)
     */
    public void checkConnect(java.lang.String host, int port, java.lang.Object
        context)
    { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to wait for a connection request on 
     * the specified local port number. 
     * <p>
     * If port is not 0, this method calls
     * <code>checkPermission</code> with the
     * <code>SocketPermission("localhost:"+port,"listen")</code>.
     * If port is zero, this method calls <code>checkPermission</code>
     * with <code>SocketPermission("localhost:1024-","listen").</code>
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkListen</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      port   the local port.
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to listen on the specified port.
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkListen(int port) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not permitted to accept a socket connection from 
     * the specified host and port number. 
     * <p>
     * This method is invoked for the current security manager by the 
     * <code>accept</code> method of class <code>ServerSocket</code>. 
     * NOTE: <B>java.net.ServerSocket</B> is found in J2ME CDC profiles such as 
     * J2ME Foundation Profile.
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>SocketPermission(host+":"+port,"accept")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkAccept</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      host   the host name of the socket connection.
     * @param      port   the port number of the socket connection.
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to accept the connection.
     * @exception  NullPointerException if the <code>host</code> argument is
     *             <code>null</code>.
     * @see        java.net.ServerSocket#accept()
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkAccept(java.lang.String host, int port) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to use
     * (join/leave/send/receive) IP multicast.
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>java.net.SocketPermission(maddr.getHostAddress(), 
     * "accept,connect")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkMulticast</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      maddr  Internet group address to be used.
     * @exception  SecurityException  if the calling thread is not allowed to 
     *  use (join/leave/send/receive) IP multicast.
     * @exception  NullPointerException if the address argument is
     *             <code>null</code>.
     * @since      JDK1.1
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkMulticast(InetAddress maddr) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to use
     * (join/leave/send/receive) IP multicast.
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>java.net.SocketPermission(maddr.getHostAddress(), 
     * "accept,connect")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkMulticast</code>
     * at the point the overridden method would normally throw an
     * exception. 
     *
     * @param      maddr  Internet group address to be used.
     * @param      ttl        value in use, if it is multicast send.
     * Note: this particular implementation does not use the ttl
     * parameter. 
     * @exception  SecurityException  if the calling thread is not allowed to 
     *  use (join/leave/send/receive) IP multicast.
     * @exception  NullPointerException if the address argument is
     *             <code>null</code>.
     * @since      JDK1.1
     * @deprecated Use #checkPermission(java.security.Permission) instead
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkMulticast(InetAddress maddr, byte ttl) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to access or modify the system 
     * properties. 
     * <p>
     * This method is used by the <code>getProperties</code> and 
     * <code>setProperties</code> methods of class <code>System</code>. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>PropertyPermission("*", "read,write")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkPropertiesAccess</code>
     * at the point the overridden method would normally throw an
     * exception.
     * <p>
     *
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to access or modify the system properties.
     * @see        java.lang.System#getProperties()
     * @see        java.lang.System#setProperties(java.util.Properties)
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkPropertiesAccess() { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to access the system property with 
     * the specified <code>key</code> name. 
     * <p>
     * This method is used by the <code>getProperty</code> method of 
     * class <code>System</code>. 
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>PropertyPermission(key, "read")</code> permission.
     * <p>
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkPropertyAccess</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param      key   a system property key.
     *
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to access the specified system property.
     * @exception  NullPointerException if the <code>key</code> argument is
     *             <code>null</code>.
     * @exception  IllegalArgumentException if <code>key</code> is empty.
     *
     * @see        java.lang.System#getProperty(java.lang.String)
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkPropertyAccess(java.lang.String key) { }

    /** 
     * Returns <code>false</code> if the calling 
     * thread is not trusted to bring up the top-level window indicated 
     * by the <code>window</code> argument, or the AWT package is not
     * available.
     * In this case, the caller can 
     * still decide to show the window, but the window should include 
     * some sort of visual warning. If the method returns 
     * <code>true</code>, then the window can be shown without any 
     * special restrictions. 
     * <p>
     * See class <code>Window</code> for more information on trusted and 
     * untrusted windows. 
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkTopLevelWindow</code>
     * at the point the overridden method would normally return 
     * <code>false</code>, and the value of 
     * <code>super.checkTopLevelWindow</code> should
     * be returned.
     *
     * @param      window   the new window that is being created.
     * @return     <code>true</code> if the calling thread is trusted to put up
     *             top-level windows; <code>false</code> otherwise.
     * @exception  NullPointerException if the <code>window</code> argument is
     *             <code>null</code>.
     * @see        java.awt.Window
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public boolean checkTopLevelWindow(java.lang.Object window) {
        return false;
    }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to initiate a print job request.
     * <p>
     * This method calls
     * <code>checkPermission</code> with the
     * <code>RuntimePermission("queuePrintJob")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkPrintJobAccess</code>
     * at the point the overridden method would normally throw an
     * exception.
     * <p>
     *
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to initiate a print job request.
     * @since   JDK1.1
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkPrintJobAccess() { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to access the system clipboard, or
     * the AWT package is not available.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkSystemClipboardAccess</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @since   JDK1.1
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to access the system clipboard.
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkSystemClipboardAccess() { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to access the AWT event queue, or
     * the AWT package is not available.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkAwtEventQueueAccess</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @since   JDK1.1
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to access the AWT event queue.
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkAwtEventQueueAccess() { }

    /** 
     * Throws a <code>SecurityException</code> if the
     * calling thread is not allowed to access the package specified by
     * the argument.
     * <p>
     * This method is used by the <code>loadClass</code> method of class
     * loaders.
     * <p>
     * This method first gets a list of
     * restricted packages by obtaining a comma-separated list from
     * a call to
     * <code>java.security.Security.getProperty("package.access")</code>,
     * and checks to see if <code>pkg</code> starts with or equals
     * any of the restricted packages. If it does, then
     * <code>checkPermission</code> gets called with the
     * <code>RuntimePermission("accessClassInPackage."+pkg)</code>
     * permission.
     * <p>
     * If this method is overridden, then
     * <code>super.checkPackageAccess</code> should be called
     * as the first line in the overridden method.
     *
     * @param      pkg   the package name.
     * @exception  SecurityException  if the calling thread does not have
     *             permission to access the specified package.
     * @exception  NullPointerException if the package name argument is
     *             <code>null</code>.
     * @see        java.lang.ClassLoader#loadClass(java.lang.String, boolean)
     *  loadClass
     * @see        java.security.Security#getProperty getProperty
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkPackageAccess(java.lang.String pkg) { }

    /** 
     * Throws a <code>SecurityException</code> if the
     * calling thread is not allowed to define classes in the package
     * specified by the argument.
     * <p>
     * This method is used by the <code>loadClass</code> method of some
     * class loaders.
     * <p>
     * This method first gets a list of restricted packages by
     * obtaining a comma-separated list from a call to
     * <code>java.security.Security.getProperty("package.definition")</code>,
     * and checks to see if <code>pkg</code> starts with or equals
     * any of the restricted packages. If it does, then
     * <code>checkPermission</code> gets called with the
     * <code>RuntimePermission("defineClassInPackage."+pkg)</code>
     * permission.
     * <p>
     * If this method is overridden, then
     * <code>super.checkPackageDefinition</code> should be called
     * as the first line in the overridden method.
     *
     * @param      pkg   the package name.
     * @exception  SecurityException  if the calling thread does not have
     *             permission to define classes in the specified package.
     * @see        java.lang.ClassLoader#loadClass(java.lang.String, boolean)
     * @see        java.security.Security#getProperty getProperty
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkPackageDefinition(java.lang.String pkg) { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to set the socket factory used by 
     * <code>ServerSocket</code> or <code>Socket</code>, or the stream 
     * handler factory used by <code>URL</code>. 
     * NOTE: <B>java.net.ServerSocket</B> is found in J2ME CDC profiles such as 
     * J2ME Foundation Profile.
     * <p>
     * This method calls <code>checkPermission</code> with the
     * <code>RuntimePermission("setFactory")</code> permission.
     * <p>
     * If you override this method, then you should make a call to 
     * <code>super.checkSetFactory</code>
     * at the point the overridden method would normally throw an
     * exception.
     * <p>
     *
     * @exception  SecurityException  if the calling thread does not have 
     *             permission to specify a socket factory or a stream 
     *             handler factory.
     *
     * @see        java.net.ServerSocket#setSocketFactory(java.net.SocketImplFactory) setSocketFactory
     * @see        java.net.Socket#setSocketImplFactory(java.net.SocketImplFactory) setSocketImplFactory
     * @see        java.net.URL#setURLStreamHandlerFactory(java.net.URLStreamHandlerFactory) setURLStreamHandlerFactory
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkSetFactory() { }

    /** 
     * Throws a <code>SecurityException</code> if the 
     * calling thread is not allowed to access members. 
     * <p>
     * The default policy is to allow access to PUBLIC members, as well
     * as access to classes that have the same class loader as the caller.
     * In all other cases, this method calls <code>checkPermission</code> 
     * with the <code>RuntimePermission("accessDeclaredMembers")
     * </code> permission.
     * <p>
     * If this method is overridden, then a call to 
     * <code>super.checkMemberAccess</code> cannot be made,
     * as the default implementation of <code>checkMemberAccess</code>
     * relies on the code being checked being at a stack depth of
     * 4.
     * 
     * @param clazz the class that reflection is to be performed on.
     *
     * @param which type of access, PUBLIC or DECLARED.
     *
     * @exception  SecurityException if the caller does not have
     *             permission to access members.
     * @exception  NullPointerException if the <code>clazz</code> argument is
     *             <code>null</code>.
     * @see java.lang.reflect.Member
     * @since JDK1.1
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkMemberAccess(java.lang.Class clazz, int which) { }

    /** 
     * Determines whether the permission with the specified permission target
     * name should be granted or denied.
     *
     * <p> If the requested permission is allowed, this method returns
     * quietly. If denied, a SecurityException is raised. 
     *
     * <p> This method creates a <code>SecurityPermission</code> object for
     * the given permission target name and calls <code>checkPermission</code>
     * with it.
     *
     * <p> See the documentation for
     * <code>{@link java.security.SecurityPermission}</code> for
     * a list of possible permission target names.
     * 
     * <p> If you override this method, then you should make a call to 
     * <code>super.checkSecurityAccess</code>
     * at the point the overridden method would normally throw an
     * exception.
     *
     * @param target the target name of the <code>SecurityPermission</code>.
     *
     * @exception SecurityException if the calling thread does not have
     * permission for the requested access.
     * @exception NullPointerException if <code>target</code> is null.
     * @exception IllegalArgumentException if <code>target</code> is empty.
     *
     * @since   JDK1.1
     * @see        #checkPermission(java.security.Permission) checkPermission
     */
    public void checkSecurityAccess(java.lang.String target) { }

    /** 
     * Returns the thread group into which to instantiate any new
     * thread being created at the time this is being called.
     * By default, it returns the thread group of the current
     * thread. This should be overridden by a specific security
     * manager to return the appropriate thread group.
     *
     * @return  ThreadGroup that new threads are instantiated into
     * @since   JDK1.1
     * @see     java.lang.ThreadGroup
     */
    public java.lang.ThreadGroup getThreadGroup() {
        return null;
    }
}
