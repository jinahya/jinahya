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

/** 
 * An interface for events that know how to dispatch themselves.
 * By implementing this interface an event can be placed upon the event
 * queue and its <code>dispatch()</code> method will be called when the event
 * is dispatched, using the <code>EventDispatchThread</code>.
 * <p>
 * This is a very useful mechanism for avoiding deadlocks. If
 * a thread is executing in a critical section (i.e., it has entered
 * one or more monitors), calling other synchronized code may
 * cause deadlocks. To avoid the potential deadlocks, an 
 * <code>ActiveEvent</code> can be created to run the second section of
 * code at later time. If there is contention on the monitor,
 * the second thread will simply block until the first thread
 * has finished its work and exited its monitors.
 * <p>
 * For security reasons, it is often desirable to use an <code>ActiveEvent</code> 
 * to avoid calling untrusted code from a critical thread. For
 * instance, peer implementations can use this facility to avoid 
 * making calls into user code from a system thread. Doing so avoids
 * potential deadlocks and denial-of-service attacks.
 *
 * @author  Timothy Prinzing
 * @version 1.12 01/23/03
 * @since   1.2
 */
public interface ActiveEvent
{

    /** 
     * Dispatch the event to its target, listeners of the events source, 
     * or do whatever it is this event is supposed to do.
     */
    public void dispatch();
}
