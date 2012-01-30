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
 * The listener interface for receiving window events.
 * The class that is interested in processing a window event
 * either implements this interface (and all the methods it
 * contains) or extends the abstract <code>WindowAdapter</code> class
 * (overriding only the methods of interest).
 * The listener object created from that class is then registered with a
 * Window using the window's <code>addWindowListener</code> 
 * method. When the window's status changes by virtue of being opened,
 * closed, activated or deactivated, iconified or deiconified, 
 * the relevant method in the listener object is invoked, and the 
 * <code>WindowEvent</code> is passed to it.
 *
 * @author Carl Quinn
 * @version 1.19, 01/23/03
 *
 * @see WindowAdapter
 * @see WindowEvent
 * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/windowlistener.html">Tutorial: Writing a Window Listener</a>
 * @see <a href="http://www.awl.com/cp/javaseries/jcl1_2.html">Reference: The Java Class Libraries (update file)</a>
 *
 * @since 1.1
 */
public interface WindowListener extends EventListener
{

    /** 
     * Invoked the first time a window is made visible.
     */
    public void windowOpened(WindowEvent e);

    /** 
     * Invoked when the user attempts to close the window
     * from the window's system menu.  If the program does not 
     * explicitly hide or dispose the window while processing 
     * this event, the window close operation will be cancelled.
     */
    public void windowClosing(WindowEvent e);

    /** 
     * Invoked when a window has been closed as the result
     * of calling dispose on the window.
     */
    public void windowClosed(WindowEvent e);

    /** 
     * Invoked when a window is changed from a normal to a
     * minimized state. For many platforms, a minimized window 
     * is displayed as the icon specified in the window's 
     * iconImage property.
     * @see java.awt.Frame#setIconImage
     */
    public void windowIconified(WindowEvent e);

    /** 
     * Invoked when a window is changed from a minimized
     * to a normal state.
     */
    public void windowDeiconified(WindowEvent e);
    
    // PBP/PP 6214654
    /** 
     * Invoked when the Window is set to be the active Window. Only a Frame can be the active Window. The native windowing system may
     * denote the active Window or its children with special decorations, such
     * as a highlighted title bar. The active Window is always either the
     * focused Window, or the first Framethat is an owner of the
     * focused Window.
     */
    public void windowActivated(WindowEvent e);

    // PBP/PP 6214654
    /** 
     * Invoked when a Window is no longer the active Window. Only a Frame can be the active Window. The native windowing system may denote
     * the active Window or its children with special decorations, such as a
     * highlighted title bar. The active Window is always either the focused
     * Window, or the first Frame that is an owner of the focused
     * Window.
     */
    public void windowDeactivated(WindowEvent e);
}
