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
 * The listener interface for receiving "interesting" mouse events 
 * (press, release, click, enter, and exit) on a component.
 * (To track mouse moves and mouse drags, use the
 * <code>MouseMotionListener</code>.)
 * <P>
 * The class that is interested in processing a mouse event
 * either implements this interface (and all the methods it
 * contains) or extends the abstract <code>MouseAdapter</code> class
 * (overriding only the methods of interest).
 * <P>
 * The listener object created from that class is then registered with a
 * component using the component's <code>addMouseListener</code> 
 * method. A mouse event is generated when the mouse is pressed, released
 * clicked (pressed and released). A mouse event is also generated when
 * the mouse cursor enters or leaves a component. When a mouse event
 * occurs, the relevant method in the listener object is invoked, and 
 * the <code>MouseEvent</code> is passed to it.
 *
 * @author Carl Quinn
 * @version 1.16, 01/23/03
 *
 * @see MouseAdapter
 * @see MouseEvent
 * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/mouselistener.html">Tutorial: Writing a Mouse Listener</a>
 * @see <a href="http://www.awl.com/cp/javaseries/jcl1_2.html">Reference: The Java Class Libraries (update file)</a>
 *
 * @since 1.1
 */
public interface MouseListener extends EventListener
{

    /** 
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    public void mouseClicked(MouseEvent e);

    /** 
     * Invoked when a mouse button has been pressed on a component.
     */
    public void mousePressed(MouseEvent e);

    /** 
     * Invoked when a mouse button has been released on a component.
     */
    public void mouseReleased(MouseEvent e);

    /** 
     * Invoked when the mouse enters a component.
     */
    public void mouseEntered(MouseEvent e);

    /** 
     * Invoked when the mouse exits a component.
     */
    public void mouseExited(MouseEvent e);
}
