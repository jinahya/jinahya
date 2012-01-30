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


  


package java.security;


/** 
 * <p> The AccessController class is used for access control operations
 * and decisions.
 * 
 * <p> More specifically, the AccessController class is used for 
 * three purposes:
 * 
 * <ul>
 * <li> to decide whether an access to a critical system
 * resource is to be allowed or denied, based on the security policy
 * currently in effect,<p> 
 * <li>to mark code as being "privileged", thus affecting subsequent
 * access determinations, and<p>
 * <li>to obtain a "snapshot" of the current calling context so
 * access-control decisions from a different context can be made with
 * respect to the saved context. </ul>
 * 
 * <p> The {@link #checkPermission(Permission) checkPermission} method
 * determines whether the access request indicated by a specified
 * permission should be granted or denied. A sample call appears
 * below. In this example, <code>checkPermission</code> will determine 
 * whether or not to grant "read" access to the file named "testFile" in 
 * the "/temp" directory.
 * 
 * <pre>
 * 
 *    FilePermission perm = new FilePermission("/temp/testFile", "read");
 *    AccessController.checkPermission(perm);
 * 
 * </pre>
 *
 * <p> If a requested access is allowed, 
 * <code>checkPermission</code> returns quietly. If denied, an 
 * AccessControlException is
 * thrown. AccessControlException can also be thrown if the requested
 * permission is of an incorrect type or contains an invalid value.
 * Such information is given whenever possible.
 * 
 * Suppose the current thread traversed m callers, in the order of caller 1 
 * to caller 2 to caller m. Then caller m invoked the 
 * <code>checkPermission</code> method.
 * The <code>checkPermission </code>method determines whether access 
 * is granted or denied based on the following algorithm:
 * 
 * <pre>
 * i = m;
 * 
 * while (i > 0) {
 * 
 *      if (caller i's domain does not have the permission)
 *              throw AccessControlException
 * 
 *      else if (caller i is marked as privileged) {
 *              if (a context was specified in the call to doPrivileged) 
 *                 context.checkPermission(permission)
 *              return;
 *      }
 *      i = i - 1;
 * };
 *
 *    // Next, check the context inherited when
 *    // the thread was created. Whenever a new thread is created, the
 *    // AccessControlContext at that time is
 *    // stored and associated with the new thread, as the "inherited"
 *    // context.
 * 
 * inheritedContext.checkPermission(permission);
 * </pre>
 * 
 * <p> A caller can be marked as being "privileged" 
 * (see {@link #doPrivileged(PrivilegedAction) doPrivileged} and below). 
 * When making access control decisions, the <code>checkPermission</code>
 * method stops checking if it reaches a caller that 
 * was marked as "privileged" via a <code>doPrivileged</code> 
 * call without a context argument (see below for information about a
 * context argument). If that caller's domain has the
 * specified permission, no further checking is done and 
 * <code>checkPermission</code>
 * returns quietly, indicating that the requested access is allowed.
 * If that domain does not have the specified permission, an exception
 * is thrown, as usual.
 * 
 * <p> The normal use of the "privileged" feature is as follows. If you
 * don't need to return a value from within the "privileged" block, do 
 * the following:
 *
 * <pre>
 *   somemethod() {
 *        ...normal code here...
 *        AccessController.doPrivileged(new PrivilegedAction() {
 *            public Object run() {
 *                // privileged code goes here, for example:
 *                System.loadLibrary("awt");
 *                return null; // nothing to return
 *            }
 *        });
 *       ...normal code here...
 *  }
 * </pre>
 *
 * <p>
 * PrivilegedAction is an interface with a single method, named
 * <code>run</code>, that returns an Object.
 * The above example shows creation of an implementation
 * of that interface; a concrete implementation of the
 * <code>run</code> method is supplied.
 * When the call to <code>doPrivileged</code> is made, an 
 * instance of the PrivilegedAction implementation is passed
 * to it. The <code>doPrivileged</code> method calls the
 * <code>run</code> method from the PrivilegedAction 
 * implementation after enabling privileges, and returns the 
 * <code>run</code> method's return value as the 
 * <code>doPrivileged</code> return value (which is
 * ignored in this example).
 *
 * <p> If you need to return a value, you can do something like the following:
 *
 * <pre>
 *   somemethod() {
 *        ...normal code here...
 *        String user = (String) AccessController.doPrivileged(
 *          new PrivilegedAction() {
 *            public Object run() {
 *                return System.getProperty("user.name");
 *            }
 *          }
 *        );
 *        ...normal code here...
 *  }
 * </pre>
 *
 * <p>If the action performed in your <code>run</code> method could
 * throw a "checked" exception (those listed in the <code>throws</code> clause
 * of a method), then you need to use the 
 * <code>PrivilegedExceptionAction</code> interface instead of the
 * <code>PrivilegedAction</code> interface:
 * 
 * <pre>
 *   somemethod() throws FileNotFoundException {
 *        ...normal code here...
 *      try {
 *        FileInputStream fis = (FileInputStream) AccessController.doPrivileged(
 *          new PrivilegedExceptionAction() {
 *            public Object run() throws FileNotFoundException {
 *                return new FileInputStream("someFile");
 *            }
 *          }
 *        );
 *      } catch (PrivilegedActionException e) {
 *        // e.getException() should be an instance of FileNotFoundException,
 *        // as only "checked" exceptions will be "wrapped" in a
 *        // <code>PrivilegedActionException</code>.
 *        throw (FileNotFoundException) e.getException();
 *      }
 *        ...normal code here...
 *  }
 * </pre>
 * 
 * <p> Be *very* careful in your use of the "privileged" construct, and 
 * always remember to make the privileged code section as small as possible.
 * 
 * <p> Note that <code>checkPermission</code> always performs security checks
 * within the context of the currently executing thread.
 * Sometimes a security check that should be made within a given context
 * will actually need to be done from within a
 * <i>different</i> context (for example, from within a worker thread).
 * The {@link #getContext() getContext} method and 
 * AccessControlContext class are provided 
 * for this situation. The <code>getContext</code> method takes a "snapshot"
 * of the current calling context, and places
 * it in an AccessControlContext object, which it returns. A sample call is
 * the following:
 * 
 * <pre>
 * 
 *   AccessControlContext acc = AccessController.getContext()
 * 
 * </pre>
 * 
 * <p>
 * AccessControlContext itself has a <code>checkPermission</code> method
 * that makes access decisions based on the context <i>it</i> encapsulates,
 * rather than that of the current execution thread.
 * Code within a different context can thus call that method on the
 * previously-saved AccessControlContext object. A sample call is the
 * following:
 * 
 * <pre>
 * 
 *   acc.checkPermission(permission)
 * 
 * </pre> 
 *
 * <p> There are also times where you don't know a priori which permissions
 * to check the context against. In these cases you can use the
 * doPrivileged method that takes a context:
 * 
 * <pre>
 *   somemethod() {
 *         AccessController.doPrivileged(new PrivilegedAction() {
 *              public Object run() {
 *                 // Code goes here. Any permission checks within this
 *                 // run method will require that the intersection of the
 *                 // callers protection domain and the snapshot's
 *                 // context have the desired permission.
 *              }
 *         }, acc);
 *         ...normal code here...
 *   }
 * </pre>
 * 
 * @see AccessControlContext
 *
 * @version 1.48 00/05/03
 * @author Li Gong 
 * @author Roland Schemers
 */
public final class AccessController
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private AccessController() { }

    /** 
     * Performs the specified <code>PrivilegedAction</code> with privileges
     * enabled. The action is performed with <i>all</i> of the permissions 
     * possessed by the caller's protection domain.
     * <p>
     * If the action's <code>run</code> method throws an (unchecked) exception,
     * it will propagate through this method.
     *
     * @param action the action to be performed.
     * @return the value returned by the action's <code>run</code> method.
     * @see #doPrivileged(PrivilegedAction,AccessControlContext)
     * @see #doPrivileged(PrivilegedExceptionAction)
     */
    public static Object doPrivileged(PrivilegedAction action) {
        return null;
    }

    /** 
     * Performs the specified <code>PrivilegedAction</code> with privileges
     * enabled and restricted by the specified <code>AccessControlContext</code>.
     * The action is performed with the intersection of the permissions
     * possessed by the caller's protection domain, and those possessed
     * by the domains represented by the specified
     * <code>AccessControlContext</code>.
     * <p>
     * If the action's <code>run</code> method throws an (unchecked) exception,
     * it will propagate through this method.
     *
     * @param action the action to be performed.
     * @param context an <i>access control context</i> representing the
     *		      restriction to be applied to the caller's domain's
     *		      privileges before performing the specified action.
     * @return the value returned by the action's <code>run</code> method.
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     */
    public static Object doPrivileged(PrivilegedAction action,
        AccessControlContext context)
    {
        return null;
    }

    /** 
     * Performs the specified <code>PrivilegedExceptionAction</code> with
     * privileges enabled.  The action is performed with <i>all</i> of the 
     * permissions possessed by the caller's protection domain.
     * <p>
     * If the action's <code>run</code> method throws an <i>unchecked</i>
     * exception, it will propagate through this method.
     *
     * @param action the action to be performed
     * @return the value returned by the action's <code>run</code> method
     * @throws PrivilegedActionException if the specified action's
     *         <code>run</code> method threw a <i>checked</i> exception
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     */
    public static Object doPrivileged(PrivilegedExceptionAction action)
        throws PrivilegedActionException
    {
        return null;
    }

    /** 
     * Performs the specified <code>PrivilegedExceptionAction</code> with 
     * privileges enabled and restricted by the specified
     * <code>AccessControlContext</code>.  The action is performed with the
     * intersection of the the permissions possessed by the caller's
     * protection domain, and those possessed by the domains represented by the
     * specified <code>AccessControlContext</code>.
     * <p>
     * If the action's <code>run</code> method throws an <i>unchecked</i>
     * exception, it will propagate through this method.
     *
     * @param action the action to be performed
     * @param context an <i>access control context</i> representing the
     *		      restriction to be applied to the caller's domain's
     *		      privileges before performing the specified action
     * @return the value returned by the action's <code>run</code> method
     * @throws PrivilegedActionException if the specified action's
     *         <code>run</code> method
     *	       threw a <i>checked</i> exception
     * @see #doPrivileged(PrivilegedAction)
     * @see #doPrivileged(PrivilegedExceptionAction,AccessControlContext)
     */
    public static Object doPrivileged(PrivilegedExceptionAction action,
        AccessControlContext context) throws PrivilegedActionException
    {
        return null;
    }

    /** 
     * This method takes a "snapshot" of the current calling context, which
     * includes the current Thread's inherited AccessControlContext,
     * and places it in an AccessControlContext object. This context may then
     * be checked at a later point, possibly in another thread.
     *
     * @see AccessControlContext
     *
     * @return the AccessControlContext based on the current context.
     */
    public static AccessControlContext getContext() {
        return null;
    }

    /** 
     * Determines whether the access request indicated by the
     * specified permission should be allowed or denied, based on
     * the security policy currently in effect. 
     * This method quietly returns if the access request
     * is permitted, or throws a suitable AccessControlException otherwise. 
     *
     * @param perm the requested permission.
     * 
     * @exception AccessControlException if the specified permission
     * is not permitted, based on the current security policy.
     */
    public static void checkPermission(Permission perm)
        throws AccessControlException
    { }
}
