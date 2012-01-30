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

import java.io.PrintStream;

/** 
 * A thread group represents a set of threads. In addition, a thread 
 * group can also include other thread groups. The thread groups form 
 * a tree in which every thread group except the initial thread group 
 * has a parent. 
 * <p>
 * A thread is allowed to access information about its own thread 
 * group, but not to access information about its thread group's 
 * parent thread group or any other thread groups. 
 *
 * @author  unascribed
 * @version 1.55 07/27/01
 * @since   JDK1.0
 */
public class ThreadGroup
{

    /** 
     * Constructs a new thread group. The parent of this new group is 
     * the thread group of the currently running thread. 
     * <p>
     * The <code>checkAccess</code> method of the parent thread group is 
     * called with no arguments; this may result in a security exception. 
     *
     * @param   name   the name of the new thread group.
     * @exception  SecurityException  if the current thread cannot create a
     *               thread in the specified thread group.
     * @see     java.lang.ThreadGroup#checkAccess()
     * @since   JDK1.0
     */
    public ThreadGroup(java.lang.String name) { }

    /** 
     * Creates a new thread group. The parent of this new group is the 
     * specified thread group. 
     * <p>
     * The <code>checkAccess</code> method of the parent thread group is 
     * called with no arguments; this may result in a security exception. 
     *
     * @param     parent   the parent thread group.
     * @param     name     the name of the new thread group.
     * @exception  NullPointerException  if the thread group argument is
     *               <code>null</code>.
     * @exception  SecurityException  if the current thread cannot create a
     *               thread in the specified thread group.
     * @see     java.lang.SecurityException
     * @see     java.lang.ThreadGroup#checkAccess()
     * @since   JDK1.0
     */
    public ThreadGroup(java.lang.ThreadGroup parent, java.lang.String name) { }

    /** 
     * Returns the name of this thread group.
     *
     * @return  the name of this thread group.
     * @since   JDK1.0
     */
    public final java.lang.String getName() {
        return null;
    }

    /** 
     * Returns the parent of this thread group.
     * <p>
     * First, if the parent is not <code>null</code>, the 
     * <code>checkAccess</code> method of the parent thread group is 
     * called with no arguments; this may result in a security exception. 
     *
     * @return  the parent of this thread group. The top-level thread group
     *          is the only thread group whose parent is <code>null</code>.
     * @exception  SecurityException  if the current thread cannot modify
     *               this thread group.
     * @see        java.lang.ThreadGroup#checkAccess()
     * @see        java.lang.SecurityException
     * @see        java.lang.RuntimePermission
     * @since   JDK1.0
     */
    public final java.lang.ThreadGroup getParent() {
        return null;
    }

    /** 
     * Returns the maximum priority of this thread group. Threads that are
     * part of this group cannot have a higher priority than the maximum
     * priority.
     *
     * @return  the maximum priority that a thread in this thread group
     *          can have.
     * @see     #setMaxPriority
     * @since   JDK1.0
     */
    public final int getMaxPriority() {
        return 0;
    }

    /** 
     * Tests if this thread group is a daemon thread group. A 
     * daemon thread group is automatically destroyed when its last 
     * thread is stopped or its last thread group is destroyed. 
     *
     * @return  <code>true</code> if this thread group is a daemon thread group;
     *          <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public final boolean isDaemon() {
        return false;
    }

    /** 
     * Tests if this thread group has been destroyed.
     *
     * @return  true if this object is destroyed
     * @since   JDK1.1
     */
    public synchronized boolean isDestroyed() {
        return false;
    }

    /** 
     * Changes the daemon status of this thread group.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is 
     * called with no arguments; this may result in a security exception. 
     * <p>
     * A daemon thread group is automatically destroyed when its last 
     * thread is stopped or its last thread group is destroyed. 
     *
     * @param      daemon   if <code>true</code>, marks this thread group as
     *                      a daemon thread group; otherwise, marks this
     *                      thread group as normal.
     * @exception  SecurityException  if the current thread cannot modify
     *               this thread group.
     * @see        java.lang.SecurityException
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     */
    public final void setDaemon(boolean daemon) { }

    /** 
     * Sets the maximum priority of the group. Threads in the thread
     * group that already have a higher priority are not affected.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is
     * called with no arguments; this may result in a security exception.
     * <p>
     * If the <code>pri</code> argument is less than
     * {@link Thread#MIN_PRIORITY} or greater than
     * {@link Thread#MAX_PRIORITY}, the maximum priority of the group
     * remains unchanged.
     * <p>
     * Otherwise, the priority of this ThreadGroup object is set to the
     * smaller of the specified <code>pri</code> and the maximum permitted
     * priority of the parent of this thread group. (If this thread group
     * is the system thread group, which has no parent, then its maximum
     * priority is simply set to <code>pri</code>.) Then this method is
     * called recursively, with <code>pri</code> as its argument, for
     * every thread group that belongs to this thread group.
     *
     * @param      pri   the new priority of the thread group.
     * @exception  SecurityException  if the current thread cannot modify
     *               this thread group.
     * @see        #getMaxPriority
     * @see        java.lang.SecurityException
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     */
    public final void setMaxPriority(int pri) { }

    /** 
     * Tests if this thread group is either the thread group 
     * argument or one of its ancestor thread groups. 
     *
     * @param   g   a thread group.
     * @return  <code>true</code> if this thread group is the thread group
     *          argument or one of its ancestor thread groups;
     *          <code>false</code> otherwise.
     * @since   JDK1.0
     */
    public final boolean parentOf(java.lang.ThreadGroup g) {
        return false;
    }

    /** 
     * Determines if the currently running thread has permission to 
     * modify this thread group. 
     * <p>
     * If there is a security manager, its <code>checkAccess</code> method 
     * is called with this thread group as its argument. This may result 
     * in throwing a <code>SecurityException</code>. 
     *
     * @exception  SecurityException  if the current thread is not allowed to
     *               access this thread group.
     * @see        java.lang.SecurityManager#checkAccess(java.lang.ThreadGroup)
     * @since      JDK1.0
     */
    public final void checkAccess() { }

    /** 
     * Returns an estimate of the number of active threads in this
     * thread group.
     *
     * @return  the number of active threads in this thread group and in any
     *          other thread group that has this thread group as an ancestor.
     * @since   JDK1.0
     */
    public int activeCount() {
        return 0;
    }

    /** 
     * Copies into the specified array every active thread in this 
     * thread group and its subgroups. 
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is 
     * called with no arguments; this may result in a security exception. 
     * <p>
     * An application should use the <code>activeCount</code> method to 
     * get an estimate of how big the array should be. If the array is 
     * too short to hold all the threads, the extra threads are silently 
     * ignored. 
     *
     * @param   list   an array into which to place the list of threads.
     * @return  the number of threads put into the array.
     * @exception  SecurityException  if the current thread does not
     *               have permission to enumerate this thread group.
     * @see     java.lang.ThreadGroup#activeCount()
     * @see     java.lang.ThreadGroup#checkAccess()
     * @since   JDK1.0
     */
    public int enumerate(java.lang.Thread[] list) {
        return 0;
    }

    /** 
     * Copies into the specified array every active thread in this 
     * thread group. If the <code>recurse</code> flag is 
     * <code>true</code>, references to every active thread in this 
     * thread's subgroups are also included. If the array is too short to 
     * hold all the threads, the extra threads are silently ignored. 
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is 
     * called with no arguments; this may result in a security exception. 
     * <p>
     * An application should use the <code>activeCount</code> method to 
     * get an estimate of how big the array should be. 
     *
     * @param   list      an array into which to place the list of threads.
     * @param   recurse   a flag indicating whether also to include threads
     *                    in thread groups that are subgroups of this
     *                    thread group.
     * @return  the number of threads placed into the array.
     * @exception  SecurityException  if the current thread does not
     *               have permission to enumerate this thread group.
     * @see     java.lang.ThreadGroup#activeCount()
     * @see     java.lang.ThreadGroup#checkAccess()
     * @since   JDK1.0
     */
    public int enumerate(java.lang.Thread[] list, boolean recurse) {
        return 0;
    }

    /** 
     * Returns an estimate of the number of active groups in this
     * thread group.
     *
     * @return  the number of active thread groups with this thread group as
     *          an ancestor.
     * @since   JDK1.0
     */
    public int activeGroupCount() {
        return 0;
    }

    /** 
     * Copies into the specified array references to every active 
     * subgroup in this thread group. 
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is 
     * called with no arguments; this may result in a security exception. 
     * <p>
     * An application should use the <code>activeGroupCount</code> 
     * method to get an estimate of how big the array should be. If the 
     * array is too short to hold all the thread groups, the extra thread 
     * groups are silently ignored. 
     *
     * @param   list   an array into which to place the list of thread groups.
     * @return  the number of thread groups put into the array.
     * @exception  SecurityException  if the current thread does not
     *               have permission to enumerate this thread group.
     * @see     java.lang.ThreadGroup#activeGroupCount()
     * @see     java.lang.ThreadGroup#checkAccess()
     * @since   JDK1.0
     */
    public int enumerate(java.lang.ThreadGroup[] list) {
        return 0;
    }

    /** 
     * Copies into the specified array references to every active 
     * subgroup in this thread group. If the <code>recurse</code> flag is 
     * <code>true</code>, references to all active subgroups of the 
     * subgroups and so forth are also included. 
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is 
     * called with no arguments; this may result in a security exception. 
     * <p>
     * An application should use the <code>activeGroupCount</code> 
     * method to get an estimate of how big the array should be. 
     *
     * @param   list      an array into which to place the list of threads.
     * @param   recurse   a flag indicating whether to recursively enumerate
     *                    all included thread groups.
     * @return  the number of thread groups put into the array.
     * @exception  SecurityException  if the current thread does not
     *               have permission to enumerate this thread group.
     * @see     java.lang.ThreadGroup#activeGroupCount()
     * @see     java.lang.ThreadGroup#checkAccess()
     * @since   JDK1.0
     */
    public int enumerate(java.lang.ThreadGroup[] list, boolean recurse) {
        return 0;
    }

    /** 
     * Interrupts all threads in this thread group.
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is 
     * called with no arguments; this may result in a security exception. 
     * <p>
     * This method then calls the <code>interrupt</code> method on all the 
     * threads in this thread group and in all of its subgroups.
     *
     * @exception  SecurityException  if the current thread is not allowed
     *               to access this thread group or any of the threads in
     *               the thread group.
     * @see        java.lang.Thread#interrupt()
     * @see        java.lang.SecurityException
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      1.2
     */
    public final void interrupt() { }

    /** 
     * Destroys this thread group and all of its subgroups. This thread 
     * group must be empty, indicating that all threads that had been in 
     * this thread group have since stopped. 
     * <p>
     * First, the <code>checkAccess</code> method of this thread group is 
     * called with no arguments; this may result in a security exception. 
     *
     * @exception  IllegalThreadStateException  if the thread group is not
     *               empty or if the thread group has already been destroyed.
     * @exception  SecurityException  if the current thread cannot modify this
     *               thread group.
     * @see        java.lang.ThreadGroup#checkAccess()
     * @since      JDK1.0
     */
    public final void destroy() { }

    /** 
     * Prints information about this thread group to the standard 
     * output. This method is useful only for debugging. 
     *
     * @since   JDK1.0
     */
    public void list() { }

    /** 
     * Called by the Java Virtual Machine when a thread in this 
     * thread group stops because of an uncaught exception. 
     * <p>
     * The <code>uncaughtException</code> method of 
     * <code>ThreadGroup</code> does the following: 
     * <ul>
     * <li>If this thread group has a parent thread group, the
     *     <code>uncaughtException</code> method of that parent is called
     *     with the same two arguments. 
     * <li>Otherwise, this method determines if the <code>Throwable</code>
     *     argument is an instance of <code>ThreadDeath</code>. If so, nothing
     *     special is done. Otherwise, the <code>Throwable</code>'s
     *     <code>printStackTrace</code> method is called to print a stack
     *     backtrace to the standard error stream.
     * </ul>
     * <p>
     * Applications can override this method in subclasses of 
     * <code>ThreadGroup</code> to provide alternative handling of 
     * uncaught exceptions. 
     *
     * @param   t   the thread that is about to exit.
     * @param   e   the uncaught exception.
     * @see     java.lang.System#err
     * @see     java.lang.ThreadDeath
     * @see     java.lang.Throwable#printStackTrace(java.io.PrintStream)
     * @since   JDK1.0
     */
    public void uncaughtException(java.lang.Thread t, java.lang.Throwable e) { }

    /** 
     * Returns a string representation of this Thread group.
     *
     * @return  a string representation of this thread group.
     * @since   JDK1.0
     */
    public java.lang.String toString() {
        return null;
    }
}
