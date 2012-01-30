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


  


package java.awt;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
// import java.awt.event.InputMethodEvent;
import java.awt.event.InvocationEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.PaintEvent;
import java.awt.event.WindowEvent;
import java.awt.ActiveEvent;
// import java.awt.peer.ComponentPeer;
// import java.awt.peer.LightweightPeer;
import java.util.EmptyStackException;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
// import sun.awt.PeerEvent;
// import sun.awt.SunToolkit;
// import sun.awt.DebugHelper;
// import sun.awt.AWTAutoShutdown;
// import sun.awt.AppContext;

/** 
 * <code>EventQueue</code> is a platform-independent class
 * that queues events, both from the underlying peer classes
 * and from trusted application classes.
 * <p>
 * It encapsulates asynchronous event dispatch machinery which
 * extracts events from the queue and dispatches them by calling
 * {@link #dispatchEvent(AWTEvent) dispatchEvent(AWTEvent)} method
 * on this <code>EventQueue</code> with the event to be dispatched
 * as an argument.  The particular behavior of this machinery is
 * implementation-dependent.  The only requirements are that events
 * which were actually enqueued to this queue (note that events
 * being posted to the <code>EventQueue</code> can be coalesced)
 * are dispatched:
 * <dl>
 *   <dt> Sequentially.
 *   <dd> That is, it is not permitted that several events from
 *        this queue are dispatched simultaneously.
 *   <dt> In the same order as they are enqueued.
 *   <dd> That is, if <code>AWTEvent</code>&nbsp;A is enqueued
 *        to the <code>EventQueue</code> before
 *        <code>AWTEvent</code>&nbsp;B then event B will not be
 *        dispatched before event A.
 * </dl>
 * <p>
 * Some browsers partition applets in different code bases into 
 * separate contexts, and establish walls between these contexts.
 * In such a scenario, there will be one <code>EventQueue</code>
 * per context. Other browsers place all applets into the same
 * context, implying that there will be only a single, global
 * <code>EventQueue</code> for all applets. This behavior is
 * implementation-dependent.  Consult your browser's documentation
 * for more information.
 * <p>
 * For information on the threading issues of the event dispatch
 * machinery, see <a href="doc-files/AWTThreadIssues.html">AWT Threading
 * Issues</a>.
 *
 *
 * @author Thomas Ball
 * @author Fred Ecks
 * @author David Mendenhall
 *
 * @version 	1.88, 01/28/03
 * @since 	1.1
 */
public class EventQueue
{

    public EventQueue() { }

    /** 
     * Posts a 1.1-style event to the <code>EventQueue</code>. 
     * If there is an existing event on the queue with the same ID
     * and event source, the source <code>Component</code>'s
     * <code>coalesceEvents</code> method will be called.
     *
     * @param theEvent an instance of <code>java.awt.AWTEvent</code>,
     *		or a subclass of it
     * @throws NullPointerException if <code>theEvent</code> is <code>null</code>
     */
    public void postEvent(AWTEvent theEvent) { }

    /** 
     * Removes an event from the <code>EventQueue</code> and
     * returns it.  This method will block until an event has
     * been posted by another thread.
     * @return the next <code>AWTEvent</code>
     * @exception InterruptedException 
     *            if another thread has interrupted this thread
     */
    public AWTEvent getNextEvent() throws InterruptedException { return null; }

    /** 
     * Returns the first event on the <code>EventQueue</code>
     * without removing it.
     * @return the first event
     */
    public synchronized AWTEvent peekEvent() { return null; }

    /** 
     * Returns the first event with the specified id, if any.
     * @param id the id of the type of event desired
     * @return the first event of the specified id or <code>null</code>
     *    if there is no such event
     */
    public synchronized AWTEvent peekEvent(int id) { return null; }

    /** 
     * Dispatches an event. The manner in which the event is
     * dispatched depends upon the type of the event and the
     * type of the event's source object:
     * <p> </p>
     * <table border=1 summary="Event types, source types, and dispatch methods">
     * <tr>
     *     <th>Event Type</th>
     *     <th>Source Type</th> 
     *     <th>Dispatched To</th>
     * </tr>
     * <tr>
     *     <td>ActiveEvent</td>
     *     <td>Any</td>
     *     <td>event.dispatch()</td>
     * </tr>
     * <tr>
     *     <td>Other</td>
     *     <td>Component</td>
     *     <td>source.dispatchEvent(AWTEvent)</td>
     * </tr>
     * <tr>
     *     <td>Other</td>
     *     <td>MenuComponent</td>
     *     <td>source.dispatchEvent(AWTEvent)</td>
     * </tr>
     * <tr>
     *     <td>Other</td>
     *     <td>Other</td>
     *     <td>No action (ignored)</td>
     * </tr>
     * </table>
     * <p> </p>
     * @param event an instance of <code>java.awt.AWTEvent</code>,
     * 		or a subclass of it
     * @throws NullPointerException if <code>event</code> is <code>null</code>
     */
    protected void dispatchEvent(AWTEvent event) { }

    /** 
     * Returns the timestamp of the most recent event that had a timestamp, and
     * that was dispatched from the <code>EventQueue</code> associated with the
     * calling thread. If an event with a timestamp is currently being
     * dispatched, its timestamp will be returned. If no events have yet 
     * been dispatched, the EventQueue's initialization time will be 
     * returned instead.In the current version of 
     * the Java platform SDK, only <code>InputEvent</code>s,
     * <code>ActionEvent</code>s, and <code>InvocationEvent</code>s have
     * timestamps; however, future versions of the SDK may add timestamps to
     * additional event types. Note that this method should only be invoked
     * from an application's event dispatching thread. If this method is
     * invoked from another thread, the current system time (as reported by
     * <code>System.currentTimeMillis()</code>) will be returned instead.
     *
     * @return the timestamp of the last <code>InputEvent</code>,
     *         <code>ActionEvent</code>, or <code>InvocationEvent</code> to be
     *         dispatched, or <code>System.currentTimeMillis()</code> if this
     *         method is invoked on a thread other than an event dispatching
     *         thread
     * @see java.awt.event.InputEvent#getWhen
     * @see java.awt.event.ActionEvent#getWhen
     * @see java.awt.event.InvocationEvent#getWhen
     *
     * @since 1.4
     */
    public static long getMostRecentEventTime() {
      return 0;
    }

    /** 
     * Returns the the event currently being dispatched by the
     * <code>EventQueue</code> associated with the calling thread. This is
     * useful if a method needs access to the event, but was not designed to
     * receive a reference to it as an argument. Note that this method should
     * only be invoked from an application's event dispatching thread. If this
     * method is invoked from another thread, null will be returned.
     *
     * @return the event currently being dispatched, or null if this method is
     *         invoked on a thread other than an event dispatching thread
     * @since 1.4
     */
    public static AWTEvent getCurrentEvent() {
      return null;
    }

    /** 
     * Replaces the existing <code>EventQueue</code> with the specified one.
     * Any pending events are transferred to the new <code>EventQueue</code>
     * for processing by it.
     *
     * @param newEventQueue an <code>EventQueue</code>
     *		(or subclass thereof) instance to be use
     * @see      java.awt.EventQueue#pop
     * @throws NullPointerException if <code>newEventQueue</code> is <code>null</code>
     */
    public synchronized void push(EventQueue newEventQueue) { }

    /** 
     * Stops dispatching events using this <code>EventQueue</code>.
     * Any pending events are transferred to the previous
     * <code>EventQueue</code> for processing.  
     * <p>
     * Warning: To avoid deadlock, do not declare this method
     * synchronized in a subclass.
     *
     * @exception EmptyStackException if no previous push was made
     *	on this <code>EventQueue</code>
     * @see      java.awt.EventQueue#push
     */
    protected void pop() throws EmptyStackException { }

    /** 
     * Returns true if the calling thread is the current AWT 
     * <code>EventQueue</code>'s dispatch thread.  Use this
     * call the ensure that a given
     * task is being executed (or not being) on the current AWT
     * <code>EventDispatchThread</code>.
     *
     * @return true if running on the current AWT
     *  <code>EventQueue</code>'s dispatch thread
     */
    public static boolean isDispatchThread() { return false; }

    /** 
     * Causes <code>runnable</code> to have its <code>run</code>
     * method called in the dispatch thread of the <code>EventQueue</code>.
     * This will happen after all pending events are processed.
     *
     * @param runnable  the <code>Runnable</code> whose <code>run</code>
     *                  method should be executed
     *                  synchronously on the <code>EventQueue</code>
     * @see             #invokeAndWait
     * @since           1.2
     */
    public static void invokeLater(Runnable runnable) { }

    /** 
     * Causes <code>runnable</code> to have its <code>run</code>
     * method called in the dispatch thread of the <code>EventQueue</code>.
     * This will happen after all pending events are processed.
     * The call blocks until this has happened.  This method
     * will throw an Error if called from the event dispatcher thread.
     *
     * @param runnable  the <code>Runnable</code> whose <code>run</code>
     *                  method should be executed
     *                  synchronously on the <code>EventQueue</code>
     * @exception       InterruptedException  if another thread has
     *                  interrupted this thread
     * @exception       InvocationTargetException  if an exception is thrown
     *                  when running <code>runnable</code>
     * @see             #invokeLater
     * @since           1.2
     */
    public static void invokeAndWait(Runnable runnable)
        throws InterruptedException, InvocationTargetException
    { }
}
