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

import java.util.EventListener;

/** 
 * The listener interface for receiving <code>WindowEvents</code>, including
 * <code>WINDOW_GAINED_FOCUS</code> and <code>WINDOW_LOST_FOCUS</code> events.
 * The class that is interested in processing a <code>WindowEvent</code>
 * either implements this interface (and
 * all the methods it contains) or extends the abstract
 * <code>WindowAdapter</code> class (overriding only the methods of interest).
 * The listener object created from that class is then registered with a
 * <code>Window</code>
 * using the <code>Window</code>'s <code>addWindowFocusListener</code> method.
 * When the <code>Window</code>'s
 * status changes by virtue of it being opened, closed, activated, deactivated,
 * iconified, or deiconified, or by focus being transfered into or out of the
 * <code>Window</code>, the relevant method in the listener object is invoked,
 * and the <code>WindowEvent</code> is passed to it.
 *
 * @author David Mendenhall
 * @version 1.5, 01/23/03
 *
 * @see WindowAdapter
 * @see WindowEvent
 * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/windowlistener.html">Tutorial: Writing a Window Listener</a>
 * @see <a href="http://www.awl.com/cp/javaseries/jcl1_2.html">Reference: The Java Class Libraries (update file)</a>
 *
 * @since 1.4
 */
public interface WindowFocusListener extends EventListener
{

    /** 
     * Invoked when the Window is set to be the focused Window, which means
     * that the Window, or one of its subcomponents, will receive keyboard
     * events.
     */
    public void windowGainedFocus(WindowEvent e);

    /** 
     * Invoked when the Window is no longer the focused Window, which means
     * that keyboard events will no longer be delivered to the Window or any of
     * its subcomponents.
     */
    public void windowLostFocus(WindowEvent e);
}
