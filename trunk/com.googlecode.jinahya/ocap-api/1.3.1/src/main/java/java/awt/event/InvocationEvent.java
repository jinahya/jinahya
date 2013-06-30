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


  


package java.awt.event;

import java.awt.ActiveEvent;
import java.awt.AWTEvent;

/** 
 * An event which executes the <code>run()</code> method on a <code>Runnable
 * </code> when dispatched by the AWT event dispatcher thread. This class can
 * be used as a reference implementation of <code>ActiveEvent</code> rather
 * than declaring a new class and defining <code>dispatch()</code>.<p>
 *
 * Instances of this class are placed on the <code>EventQueue</code> by calls
 * to <code>invokeLater</code> and <code>invokeAndWait</code>. Client code
 * can use this fact to write replacement functions for <code>invokeLater
 * </code> and <code>invokeAndWait</code> without writing special-case code
 * in any <code>AWTEventListener</code> objects.
 *
<!-- PBP/PP -->
<!-- [5088169] -->
 * <p>
 * <em>Note: The timestamps used by this class report the difference,
 * measured in milliseconds, between the time of the event and midnight,
 * January 1, 1970 UTC
 * (similar to {@link java.lang.System.currentTimeMillis}).</em>
 *  
 * @author	Fred Ecks
 * @author	David Mendenhall
 * @version	1.14, 01/23/03
 *
 * @see		java.awt.ActiveEvent
 * @see		java.awt.EventQueue#invokeLater
 * @see		java.awt.EventQueue#invokeAndWait
 * @see		AWTEventListener
 *
 * @since 	1.2
 */
public class InvocationEvent extends AWTEvent implements ActiveEvent
{
    /** 
     * Marks the first integer id for the range of invocation event ids.
     */
    public static final int INVOCATION_FIRST = 1200;

    /** 
     * The default id for all InvocationEvents.
     */
    public static final int INVOCATION_DEFAULT = INVOCATION_FIRST;

    /** 
     * Marks the last integer id for the range of invocation event ids.
     */
    public static final int INVOCATION_LAST = INVOCATION_DEFAULT;

    /** 
     * The Runnable whose run() method will be called.
     */
    protected Runnable runnable;

    /** 
     * The (potentially null) Object whose notifyAll() method will be called
     * immediately after the Runnable.run() method returns.
     */
    protected Object notifier;

    /** 
     * Set to true if dispatch() catches Exception and stores it in the
     * exception instance variable. If false, Exceptions are propagated up
     * to the EventDispatchThread's dispatch loop.
     */
    protected boolean catchExceptions;

    /** 
     * The (potentially null) Exception thrown during execution of the
     * Runnable.run() method. This variable will also be null if a particular
     * instance does not catch exceptions.
     */
    private Exception exception;

    /** 
     * The timestamp of when this event occurred.
     *
     * @serial
     * @see #getWhen
     */
    private long when;

    /*
     * JDK 1.1 serialVersionUID.
     */
    private static final long serialVersionUID = 436056344909459450L;

    /** 
     * Constructs an <code>InvocationEvent</code> with the specified
     * source which will execute the runnable's <code>run</code>
     * method when dispatched.
     *
     * @param source	the <code>Object</code> that originated the event
     * @param runnable	the <code>Runnable</code> whose <code>run</code>
     *                  method will be executed
     */
    public InvocationEvent(Object source, Runnable runnable) { super(null,0); }

    /** 
     * Constructs an <code>InvocationEvent</code> with the specified
     * source which will execute the runnable's <code>run</code>
     * method when dispatched.  If notifier is non-<code>null</code>,
     * <code>notifyAll()</code> will be called on it
     * immediately after <code>run</code> returns.
     *
     * @param source		the <code>Object</code> that originated
     *                          the event
     * @param runnable		the <code>Runnable</code> whose
     *                          <code>run</code> method will be
     *                          executed
     * @param notifier		the Object whose <code>notifyAll</code>
     *                          method will be called after
     *                          <code>Runnable.run</code> has returned
     * @param catchExceptions	specifies whether <code>dispatch</code>
     *                          should catch Exception when executing
     *                          the <code>Runnable</code>'s <code>run</code>
     *                          method, or should instead propagate those
     *                          Exceptions to the EventDispatchThread's
     *                          dispatch loop
     */
    public InvocationEvent(Object source, Runnable runnable, Object notifier,
        boolean catchExceptions)
    { super(null,0); }

    /** 
     * Constructs an <code>InvocationEvent</code> with the specified
     * source and ID which will execute the runnable's <code>run</code>
     * method when dispatched.  If notifier is non-<code>null</code>,
     * <code>notifyAll</code> will be called on it 
     * immediately after <code>run</code> returns.
     * <p>Note that passing in an invalid <code>id</code> results in
     * unspecified behavior.
     *
     * @param source		the <code>Object</code> that originated
     *                          the event
     * @param id		the ID for the event
     * @param runnable		the <code>Runnable</code> whose
     *                          <code>run</code> method will be executed
     * @param notifier		the <code>Object whose <code>notifyAll</code>
     *                          method will be called after
     *                          <code>Runnable.run</code> has returned
     * @param catchExceptions	specifies whether <code>dispatch</code>
     *                          should catch Exception when executing the
     *                          <code>Runnable</code>'s <code>run</code>
     *                          method, or should instead propagate those
     *                          Exceptions to the EventDispatchThread's
     *                          dispatch loop
     */
    protected InvocationEvent(Object source, int id, Runnable runnable, Object
        notifier, boolean catchExceptions)
    { super(null,0); }

    /** 
     * Executes the Runnable's <code>run()</code> method and notifies the
     * notifier (if any) when <code>run()</code> returns.
     */
    public void dispatch() { }

    /** 
     * Returns any Exception caught while executing the Runnable's <code>run()
     * </code> method.
     *
     * @return	A reference to the Exception if one was thrown; null if no
     *		Exception was thrown or if this InvocationEvent does not
     *		catch exceptions
     */
    public Exception getException() {return null;  }

    /** 
     * Returns the timestamp of when this event occurred.
     *
     * @return this event's timestamp
     * @since 1.4
     */
    public long getWhen() { return 0; }

    /** 
     * Returns a parameter string identifying this event.
     * This method is useful for event-logging and for debugging.
     *
     * @return  A string identifying the event and its attributes
     */
    public String paramString() {return null;  }
}
