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

import java.security.AccessController;
import java.security.AccessControlContext;
import java.util.Map;
import java.util.Collections;

/** 
 * A <i>thread</i> is a thread of execution in a program. The Java 
 * Virtual Machine allows an application to have multiple threads of 
 * execution running concurrently. 
 * <p>
 * Every thread has a priority. Threads with higher priority are 
 * executed in preference to threads with lower priority. Each thread 
 * may or may not also be marked as a daemon. When code running in 
 * some thread creates a new <code>Thread</code> object, the new 
 * thread has its priority initially set equal to the priority of the 
 * creating thread, and is a daemon thread if and only if the 
 * creating thread is a daemon. 
 * <p>
 * When a Java Virtual Machine starts up, there is usually a single 
 * non-daemon thread (which typically calls the method named 
 * <code>main</code> of some designated class). The Java Virtual 
 * Machine continues to execute threads until either of the following 
 * occurs: 
 * <ul>
 * <li>The <code>exit</code> method of class <code>Runtime</code> has been 
 *     called and the security manager has permitted the exit operation 
 *     to take place. 
 * <li>All threads that are not daemon threads have died, either by 
 *     returning from the call to the <code>run</code> method or by 
 *     throwing an exception that propagates beyond the <code>run</code>
 *     method.
 * </ul>
 * <p>
 * There are two ways to create a new thread of execution. One is to 
 * declare a class to be a subclass of <code>Thread</code>. This 
 * subclass should override the <code>run</code> method of class 
 * <code>Thread</code>. An instance of the subclass can then be 
 * allocated and started. For example, a thread that computes primes 
 * larger than a stated value could be written as follows: 
 * <p><hr><blockquote><pre>
 *     class PrimeThread extends Thread {
 *         long minPrime;
 *         PrimeThread(long minPrime) {
 *             this.minPrime = minPrime;
 *         }
 * 
 *         public void run() {
 *             // compute primes larger than minPrime
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote><hr>
 * <p>
 * The following code would then create a thread and start it running: 
 * <p><blockquote><pre>
 *     PrimeThread p = new PrimeThread(143);
 *     p.start();
 * </pre></blockquote>
 * <p>
 * The other way to create a thread is to declare a class that 
 * implements the <code>Runnable</code> interface. That class then 
 * implements the <code>run</code> method. An instance of the class can 
 * then be allocated, passed as an argument when creating 
 * <code>Thread</code>, and started. The same example in this other 
 * style looks like the following: 
 * <p><hr><blockquote><pre>
 *     class PrimeRun implements Runnable {
 *         long minPrime;
 *         PrimeRun(long minPrime) {
 *             this.minPrime = minPrime;
 *         }
 * 
 *         public void run() {
 *             // compute primes larger than minPrime
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </pre></blockquote><hr>
 * <p>
 * The following code would then create a thread and start it running: 
 * <p><blockquote><pre>
 *     PrimeRun p = new PrimeRun(143);
 *     new Thread(p).start();
 * </pre></blockquote>
 * <p>
 * Every thread has a name for identification purposes. More than 
 * one thread may have the same name. If a name is not specified when 
 * a thread is created, a new name is generated for it. 
 *
 * @author  unascribed
 * @version 1.114, 05/17/00
 * @see     java.lang.Runnable
 * @see     java.lang.Runtime#exit(int)
 * @see     java.lang.Thread#run()
 * @since   JDK1.0
 */
public class Thread implements java.lang.Runnable
{
    /** 
     * The minimum priority that a thread can have. 
     */
    public static final int MIN_PRIORITY = 1;

    /** 
     * The default priority that is assigned to a thread. 
     */
    public static final int NORM_PRIORITY = 5;

    /** 
     * The maximum priority that a thread can have. 
     */
    public static final int MAX_PRIORITY = 10;

    /** 
     * Allocates a new <code>Thread</code> object. This constructor has 
     * the same effect as <code>Thread(null, null,</code>
     * <i>gname</i><code>)</code>, where <b><i>gname</i></b> is 
     * a newly generated name. Automatically generated names are of the 
     * form <code>"Thread-"+</code><i>n</i>, where <i>n</i> is an integer. 
     *
     * @see     java.lang.Thread#Thread(java.lang.ThreadGroup,
     *          java.lang.Runnable, java.lang.String)
     */
    public Thread() { }

    /** 
     * Allocates a new <code>Thread</code> object. This constructor has 
     * the same effect as <code>Thread(null, target,</code>
     * <i>gname</i><code>)</code>, where <i>gname</i> is 
     * a newly generated name. Automatically generated names are of the 
     * form <code>"Thread-"+</code><i>n</i>, where <i>n</i> is an integer. 
     *
     * @param   target   the object whose <code>run</code> method is called.
     * @see     java.lang.Thread#Thread(java.lang.ThreadGroup, 
     *          java.lang.Runnable, java.lang.String)
     */
    public Thread(java.lang.Runnable target) { }

    /** 
     * Allocates a new <code>Thread</code> object. This constructor has 
     * the same effect as <code>Thread(group, target,</code>
     * <i>gname</i><code>)</code>, where <i>gname</i> is 
     * a newly generated name. Automatically generated names are of the 
     * form <code>"Thread-"+</code><i>n</i>, where <i>n</i> is an integer. 
     *
     * @param      group    the thread group.
     * @param      target   the object whose <code>run</code> method is called.
     * @exception  SecurityException  if the current thread cannot create a
     *             thread in the specified thread group.
     * @see        java.lang.Thread#Thread(java.lang.ThreadGroup, 
     *             java.lang.Runnable, java.lang.String)
     */
    public Thread(java.lang.ThreadGroup group, java.lang.Runnable target) { }

    /** 
     * Allocates a new <code>Thread</code> object. This constructor has 
     * the same effect as <code>Thread(null, null, name)</code>. 
     *
     * @param   name   the name of the new thread.
     * @see     java.lang.Thread#Thread(java.lang.ThreadGroup, 
     *          java.lang.Runnable, java.lang.String)
     */
    public Thread(java.lang.String name) { }

    /** 
     * Allocates a new <code>Thread</code> object. This constructor has 
     * the same effect as <code>Thread(group, null, name)</code> 
     *
     * @param      group   the thread group.
     * @param      name    the name of the new thread.
     * @exception  SecurityException  if the current thread cannot create a
     *               thread in the specified thread group.
     * @see        java.lang.Thread#Thread(java.lang.ThreadGroup, 
     *          java.lang.Runnable, java.lang.String)
     */
    public Thread(java.lang.ThreadGroup group, java.lang.String name) { }

    /** 
     * Allocates a new <code>Thread</code> object. This constructor has 
     * the same effect as <code>Thread(null, target, name)</code>. 
     *
     * @param   target   the object whose <code>run</code> method is called.
     * @param   name     the name of the new thread.
     * @see     java.lang.Thread#Thread(java.lang.ThreadGroup, 
     *          java.lang.Runnable, java.lang.String)
     */
    public Thread(java.lang.Runnable target, java.lang.String name) { }

    /** 
     * Allocates a new <code>Thread</code> object so that it has 
     * <code>target</code> as its run object, has the specified 
     * <code>name</code> as its name, and belongs to the thread group 
     * referred to by <code>group</code>.
     * <p>
     * If <code>group</code> is <code>null</code> and there is a 
     * security manager, the group is determined by the security manager's 
     * <code>getThreadGroup</code> method. If <code>group</code> is 
     * <code>null</code> and there is not a security manager, or the
     * security manager's <code>getThreadGroup</code> method returns 
     * <code>null</code>, the group is set to be the same ThreadGroup 
     * as the thread that is creating the new thread.
     * 
     * <p>If there is a security manager, its <code>checkAccess</code> 
     * method is called with the ThreadGroup as its argument.
     * This may result in a SecurityException.
     * <p>
     * If the <code>target</code> argument is not <code>null</code>, the 
     * <code>run</code> method of the <code>target</code> is called when 
     * this thread is started. If the target argument is 
     * <code>null</code>, this thread's <code>run</code> method is called 
     * when this thread is started. 
     * <p>
     * The priority of the newly created thread is set equal to the 
     * priority of the thread creating it, that is, the currently running 
     * thread. The method <code>setPriority</code> may be used to 
     * change the priority to a new value. 
     * <p>
     * The newly created thread is initially marked as being a daemon 
     * thread if and only if the thread creating it is currently marked 
     * as a daemon thread. The method <code>setDaemon </code> may be used 
     * to change whether or not a thread is a daemon. 
     *
     * @param      group     the thread group.
     * @param      target   the object whose <code>run</code> method is called.
     * @param      name     the name of the new thread.
     * @exception  SecurityException  if the current thread cannot create a
     *               thread in the specified thread group.
     * @see        java.lang.Runnable#run()
     * @see        java.lang.Thread#run()
     * @see        java.lang.Thread#setDaemon(boolean)
     * @see        java.lang.Thread#setPriority(int)
     * @see        java.lang.ThreadGroup#checkAccess()
     * @see        SecurityManager#checkAccess
     */
    public Thread(java.lang.ThreadGroup group, java.lang.Runnable target,
        java.lang.String name)
    { }

    /** 
     * Allocates a new <code>Thread</code> object so that it has
     * <code>target</code> as its run object, has the specified
     * <code>name</code> as its name, belongs to the thread group referred to
     * by <code>group</code>, and has the specified <i>stack size</i>.
     *
     * <p>This constructor is identical to {@link
     * #Thread(ThreadGroup,Runnable,String)} with the exception of the fact
     * that it allows the thread stack size to be specified.  The stack size
     * is the approximate number of bytes of address space that the virtual
     * machine is to allocate for this thread's stack.  <b>The effect of the
     * <tt>stackSize</tt> parameter, if any, is highly platform dependent.</b>
     *
     * <p>On some platforms, specifying a higher value for the
     * <tt>stackSize</tt> parameter may allow a thread to achieve greater
     * recursion depth before throwing a {@link StackOverflowError}.
     * Similarly, specifying a lower value may allow a greater number of
     * threads to exist concurrently without throwing an an {@link
     * OutOfMemoryError} (or other internal error).  The details of
     * the relationship between the value of the <tt>stackSize</tt> parameter
     * and the maximum recursion depth and concurrency level are
     * platform-dependent.  <b>On some platforms, the value of the
     * <tt>stackSize</tt> parameter may have no effect whatsoever.</b>
     * 
     * <p>The virtual machine is free to treat the <tt>stackSize</tt>
     * parameter as a suggestion.  If the specified value is unreasonably low
     * for the platform, the virtual machine may instead use some
     * platform-specific minimum value; if the specified value is unreasonably
     * high, the virtual machine may instead use some platform-specific
     * maximum.  Likewise, the virtual machine is free to round the specified
     * value up or down as it sees fit (or to ignore it completely).
     *
     * <p>Specifying a value of zero for the <tt>stackSize</tt> parameter will
     * cause this constructor to behave exactly like the
     * <tt>Thread(ThreadGroup, Runnable, String)</tt> constructor.
     *
     * <p><i>Due to the platform-dependent nature of the behavior of this
     * constructor, extreme care should be exercised in its use.
     * The thread stack size necessary to perform a given computation will
     * likely vary from one JRE implementation to another.  In light of this
     * variation, careful tuning of the stack size parameter may be required,
     * and the tuning may need to be repeated for each JRE implementation on
     * which an application is to run.</i>
     *
     * <p>Implementation note: Java platform implementers are encouraged to
     * document their implementation's behavior with respect to the
     * <tt>stackSize parameter</tt>.
     *
     * @param      group    the thread group.
     * @param      target   the object whose <code>run</code> method is called.
     * @param      name     the name of the new thread.
     * @param      stackSize the desired stack size for the new thread, or
     *             zero to indicate that this parameter is to be ignored.
     * @exception  SecurityException  if the current thread cannot create a
     *               thread in the specified thread group.
     */
    public Thread(java.lang.ThreadGroup group, java.lang.Runnable target,
        java.lang.String name, long stackSize)
    { }

    /** 
     * Returns a reference to the currently executing thread object.
     *
     * @return  the currently executing thread.
     */
    public static java.lang.Thread currentThread() {
        return null;
    }

    /** 
     * Causes the currently executing thread object to temporarily pause 
     * and allow other threads to execute. 
     */
    public static void yield() { }

    /** 
     * Causes the currently executing thread to sleep (temporarily cease 
     * execution) for the specified number of milliseconds. The thread 
     * does not lose ownership of any monitors.
     *
     * @param      millis   the length of time to sleep in milliseconds.
     * @exception  InterruptedException if another thread has interrupted
     *             the current thread.  The <i>interrupted status</i> of the
     *             current thread is cleared when this exception is thrown.
     * @see        java.lang.Object#notify()
     */
    public static void sleep(long millis) throws java.lang.InterruptedException
    { }

    /** 
     * Causes the currently executing thread to sleep (cease execution) 
     * for the specified number of milliseconds plus the specified number 
     * of nanoseconds. The thread does not lose ownership of any monitors.
     *
     * @param      millis   the length of time to sleep in milliseconds.
     * @param      nanos    0-999999 additional nanoseconds to sleep.
     * @exception  IllegalArgumentException  if the value of millis is 
     *             negative or the value of nanos is not in the range 
     *             0-999999.
     * @exception  InterruptedException if another thread has interrupted
     *             the current thread.  The <i>interrupted status</i> of the
     *             current thread is cleared when this exception is thrown.
     * @see        java.lang.Object#notify()
     */
    public static void sleep(long millis, int nanos)
        throws java.lang.InterruptedException
    { }

    /** 
     * Causes this thread to begin execution; the Java Virtual Machine 
     * calls the <code>run</code> method of this thread. 
     * <p>
     * The result is that two threads are running concurrently: the 
     * current thread (which returns from the call to the 
     * <code>start</code> method) and the other thread (which executes its 
     * <code>run</code> method). 
     *
     * @exception  IllegalThreadStateException  if the thread was already
     *               started.
     * @see        java.lang.Thread#run()
     */
    public void start() { }

    /** 
     * If this thread was constructed using a separate 
     * <code>Runnable</code> run object, then that 
     * <code>Runnable</code> object's <code>run</code> method is called; 
     * otherwise, this method does nothing and returns. 
     * <p>
     * Subclasses of <code>Thread</code> should override this method. 
     *
     * @see     java.lang.Thread#start()
     * @see     java.lang.Thread#Thread(java.lang.ThreadGroup, 
     *          java.lang.Runnable, java.lang.String)
     * @see     java.lang.Runnable#run()
     */
    public void run() { }

    /** 
     * Interrupts this thread.
     * 
     * <p> First the {@link #checkAccess() checkAccess} method of this thread
     * is invoked, which may cause a {@link SecurityException} to be thrown.
     *
     * <p> If this thread is blocked in an invocation of the {@link
     * Object#wait() wait()}, {@link Object#wait(long) wait(long)}, or {@link
     * Object#wait(long, int) wait(long, int)} methods of the {@link Object}
     * class, or of the {@link #join()}, {@link #join(long)}, {@link
     * #join(long, int)}, {@link #sleep(long)}, or {@link #sleep(long, int)},
     * methods of this class, then its interrupt status will be cleared and it
     * will receive an {@link InterruptedException}.
     *
     * <p> If none of the previous conditions hold then this thread's interrupt
     * status will be set. </p>
     * 
     * @throws  SecurityException
     *          if the current thread cannot modify this thread
     *
     * @revised 1.4
     * @spec JSR-51
     */
    public void interrupt() { }

    /** 
     * Tests whether the current thread has been interrupted.  The
     * <i>interrupted status</i> of the thread is cleared by this method.  In
     * other words, if this method were to be called twice in succession, the
     * second call would return false (unless the current thread were
     * interrupted again, after the first call had cleared its interrupted
     * status and before the second call had examined it).
     *
     * @return  <code>true</code> if the current thread has been interrupted;
     *          <code>false</code> otherwise.
     * @see java.lang.Thread#isInterrupted()
     */
    public static boolean interrupted() {
        return false;
    }

    /** 
     * Tests whether this thread has been interrupted.  The <i>interrupted
     * status</i> of the thread is unaffected by this method.
     *
     * @return  <code>true</code> if this thread has been interrupted;
     *          <code>false</code> otherwise.
     * @see     java.lang.Thread#interrupted()
     */
    public boolean isInterrupted() {
        return false;
    }

    /** 
     * Destroys this thread, without any cleanup. Any monitors it has 
     * locked remain locked. (This method is not implemented.)
     */
    public void destroy() { }

    /** 
     * Tests if this thread is alive. A thread is alive if it has 
     * been started and has not yet died. 
     *
     * @return  <code>true</code> if this thread is alive;
     *          <code>false</code> otherwise.
     */
    public final boolean isAlive() {
        return false;
    }

    /** 
     * Changes the priority of this thread. 
     * <p>
     * First the <code>checkAccess</code> method of this thread is called 
     * with no arguments. This may result in throwing a 
     * <code>SecurityException</code>. 
     * <p>
     * Otherwise, the priority of this thread is set to the smaller of 
     * the specified <code>newPriority</code> and the maximum permitted 
     * priority of the thread's thread group. 
     *
     * @param newPriority priority to set this thread to
     * @exception  IllegalArgumentException  If the priority is not in the
     *               range <code>MIN_PRIORITY</code> to
     *               <code>MAX_PRIORITY</code>.
     * @exception  SecurityException  if the current thread cannot modify
     *               this thread.
     * @see        #getPriority
     * @see        java.lang.Thread#checkAccess()
     * @see        java.lang.Thread#getPriority()
     * @see        java.lang.Thread#getThreadGroup()
     * @see        java.lang.Thread#MAX_PRIORITY
     * @see        java.lang.Thread#MIN_PRIORITY
     * @see        java.lang.ThreadGroup#getMaxPriority()
     */
    public final void setPriority(int newPriority) { }

    /** 
     * Returns this thread's priority.
     *
     * @return  this thread's priority.
     * @see     #setPriority
     * @see     java.lang.Thread#setPriority(int)
     */
    public final int getPriority() {
        return 0;
    }

    /** 
     * Changes the name of this thread to be equal to the argument 
     * <code>name</code>. 
     * <p>
     * First the <code>checkAccess</code> method of this thread is called 
     * with no arguments. This may result in throwing a 
     * <code>SecurityException</code>. 
     *
     * @param      name   the new name for this thread.
     * @exception  SecurityException  if the current thread cannot modify this
     *               thread.
     * @see        #getName
     * @see        java.lang.Thread#checkAccess()
     * @see        java.lang.Thread#getName()
     */
    public final void setName(java.lang.String name) { }

    /** 
     * Returns this thread's name.
     *
     * @return  this thread's name.
     * @see     #setName
     * @see     java.lang.Thread#setName(java.lang.String)
     */
    public final java.lang.String getName() {
        return null;
    }

    /** 
     * Returns the thread group to which this thread belongs. 
     * This method returns null if this thread has died
     * (been stopped).
     *
     * @return  this thread's thread group.
     */
    public final java.lang.ThreadGroup getThreadGroup() {
        return null;
    }

    /** 
     * Returns the number of active threads in the current thread's thread
     * group.
     *
     * @return  the number of active threads in the current thread's thread
     *          group.
     */
    public static int activeCount() {
        return 0;
    }

    /** 
     * Copies into the specified array every active thread in 
     * the current thread's thread group and its subgroups. This method simply 
     * calls the <code>enumerate</code> method of the current thread's thread 
     * group with the array argument. 
     * <p>
     * First, if there is a security manager, that <code>enumerate</code>
     * method calls the security
     * manager's <code>checkAccess</code> method 
     * with the thread group as its argument. This may result 
     * in throwing a <code>SecurityException</code>. 
     *
     * @param tarray an array of Thread objects to copy to
     * @return  the number of threads put into the array
     * @exception  SecurityException  if a security manager exists and its  
     *             <code>checkAccess</code> method doesn't allow the operation.
     * @see     java.lang.ThreadGroup#enumerate(java.lang.Thread[])
     * @see     java.lang.SecurityManager#checkAccess(java.lang.ThreadGroup)
     */
    public static int enumerate(java.lang.Thread[] tarray) {
        return 0;
    }

    /** 
     * Waits at most <code>millis</code> milliseconds for this thread to 
     * die. A timeout of <code>0</code> means to wait forever. 
     *
     * @param      millis   the time to wait in milliseconds.
     * @exception  InterruptedException if another thread has interrupted
     *             the current thread.  The <i>interrupted status</i> of the
     *             current thread is cleared when this exception is thrown.
     */
    public final void join(long millis) throws java.lang.InterruptedException
    { }

    /** 
     * Waits at most <code>millis</code> milliseconds plus 
     * <code>nanos</code> nanoseconds for this thread to die. 
     *
     * @param      millis   the time to wait in milliseconds.
     * @param      nanos    0-999999 additional nanoseconds to wait.
     * @exception  IllegalArgumentException  if the value of millis is negative
     *               the value of nanos is not in the range 0-999999.
     * @exception  InterruptedException if another thread has interrupted
     *             the current thread.  The <i>interrupted status</i> of the
     *             current thread is cleared when this exception is thrown.
     */
    public final void join(long millis, int nanos)
        throws java.lang.InterruptedException
    { }

    /** 
     * Waits for this thread to die. 
     *
     * @exception  InterruptedException if another thread has interrupted
     *             the current thread.  The <i>interrupted status</i> of the
     *             current thread is cleared when this exception is thrown.
     */
    public final void join() throws java.lang.InterruptedException { }

    /** 
     * Prints a stack trace of the current thread. This method is used 
     * only for debugging. 
     *
     * @see     java.lang.Throwable#printStackTrace()
     */
    public static void dumpStack() { }

    /** 
     * Marks this thread as either a daemon thread or a user thread. The 
     * Java Virtual Machine exits when the only threads running are all 
     * daemon threads. 
     * <p>
     * This method must be called before the thread is started. 
     * <p>
     * This method first calls the <code>checkAccess</code> method 
     * of this thread 
     * with no arguments. This may result in throwing a 
     * <code>SecurityException </code>(in the current thread). 
     * <p>
     * If process model exists, Thread.setDaemon() should be used
     * as it is defined in J2SE specification.
     * <p>
     * If process model does not exist, Thread.setDaemon() should be
     * used to set threads as daemon threads if the programmer uses
     * good coding techniques to a) guarantee that the daemon thread will 
     * exit and clean up its resources before either a System.exit() is 
     * called and/or all the user threads have exited, and b) override the 
     * security manager's default behavior to throw SecurityException 
     * when System.exit() is called.
     * <p>
     * @param      on   if <code>true</code>, marks this thread as a
     *                  daemon thread.
     * @exception  IllegalThreadStateException  if this thread is active.
     * @exception  SecurityException  if the current thread cannot modify
     *               this thread.
     * @see        java.lang.Thread#isDaemon()
     * @see          #checkAccess
     */
    public final void setDaemon(boolean on) { }

    /** 
     * Tests if this thread is a daemon thread.
     *
     * @return  <code>true</code> if this thread is a daemon thread;
     *          <code>false</code> otherwise.
     * @see     java.lang.Thread#setDaemon(boolean)
     */
    public final boolean isDaemon() {
        return false;
    }

    /** 
     * Determines if the currently running thread has permission to 
     * modify this thread. 
     * <p>
     * If there is a security manager, its <code>checkAccess</code> method 
     * is called with this thread as its argument. This may result in 
     * throwing a <code>SecurityException</code>. 
     * <p>
     * Note: This method was mistakenly non-final in JDK 1.1.
     * It has been made final in the Java 2 Platform.
     *
     * @exception  SecurityException  if the current thread is not allowed to
     *               access this thread.
     * @see        java.lang.SecurityManager#checkAccess(java.lang.Thread)
     */
    public final void checkAccess() { }

    /** 
     * Returns a string representation of this thread, including the 
     * thread's name, priority, and thread group.
     *
     * @return  a string representation of this thread.
     */
    public java.lang.String toString() {
        return null;
    }

    /** 
     * Returns the context ClassLoader for this Thread. The context
     * ClassLoader is provided by the creator of the thread for use
     * by code running in this thread when loading classes and resources.
     * If not set, the default is the ClassLoader context of the parent
     * Thread. The context ClassLoader of the primordial thread is
     * typically set to the class loader used to load the application.
     *
     * <p>First, if there is a security manager, and the caller's class
     * loader is not null and the caller's class loader is not the same as or
     * an ancestor of the context class loader for the thread whose
     * context class loader is being requested, then the security manager's
     * <code>checkPermission</code> 
     * method is called with a 
     * <code>RuntimePermission("getClassLoader")</code> permission
     *  to see if it's ok to get the context ClassLoader.. 
     *
     * @return the context ClassLoader for this Thread
     *
     * @throws SecurityException
     *        if a security manager exists and its 
     *        <code>checkPermission</code> method doesn't allow 
     *        getting the context ClassLoader.
     * @see #setContextClassLoader
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     * 
     * @since 1.2
     */
    public java.lang.ClassLoader getContextClassLoader() {
        return null;
    }

    /** 
     * Sets the context ClassLoader for this Thread. The context
     * ClassLoader can be set when a thread is created, and allows
     * the creator of the thread to provide the appropriate class loader
     * to code running in the thread when loading classes and resources.
     *
     * <p>First, if there is a security manager, its <code>checkPermission</code> 
     * method is called with a 
     * <code>RuntimePermission("setContextClassLoader")</code> permission
     *  to see if it's ok to set the context ClassLoader.. 
     *
     * @param cl the context ClassLoader for this Thread
     * 
     * @exception  SecurityException  if the current thread cannot set the 
     * context ClassLoader.
     * @see #getContextClassLoader
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     * 
     * @since 1.2 
     */
    public void setContextClassLoader(java.lang.ClassLoader cl) { }

    /** 
     * Returns <tt>true</tt> if and only if the current thread holds the
     * monitor lock on the specified object.
     *
     * <p>This method is designed to allow a program to assert that
     * the current thread already holds a specified lock:
     * <pre>
     *     assert Thread.holdsLock(obj);
     * </pre>
     *
     * @param  obj the object on which to test lock ownership
     * @throws NullPointerException if obj is <tt>null</tt>
     * @return <tt>true</tt> if the current thread holds the monitor lock on
     *         the specified object.
     * @since 1.4
     */
    public static boolean holdsLock(java.lang.Object obj) {
        return false;
    }
}
