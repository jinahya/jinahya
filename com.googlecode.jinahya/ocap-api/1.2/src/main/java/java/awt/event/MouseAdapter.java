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

/** 
 * An abstract adapter class for receiving mouse events.
 * The methods in this class are empty. This class exists as
 * convenience for creating listener objects.
 * <P>
 * Mouse events let you track when a mouse is pressed, released, clicked, 
 * when it enters a component, and when it exits.
 * (To track mouse moves and mouse drags, use the MouseMotionAdapter.)
 * <P>
 * Extend this class to create a <code>MouseEvent</code> listener 
 * and override the methods for the events of interest. (If you implement the 
 * <code>MouseListener</code> interface, you have to define all of
 * the methods in it. This abstract class defines null methods for them
 * all, so you can only have to define methods for events you care about.)
 * <P>
 * Create a listener object using the extended class and then register it with 
 * a component using the component's <code>addMouseListener</code> 
 * method. When a mouse button is pressed, released, or clicked (pressed and
 * released), or when the mouse cursor enters or exits the component,
 * the relevant method in the listener object is invoked
 * and the <code>MouseEvent</code> is passed to it.
 *
 * @author Carl Quinn
 * @version 1.8 08/02/97
 *
 * @see MouseEvent 
 * @see MouseListener
 * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/mouselistener.html">Tutorial: Writing a Mouse Listener</a>
 * @see <a href="http://www.awl.com/cp/javaseries/jcl1_2.html">Reference: The Java Class Libraries (update file)</a>
 *
 * @since 1.1
 */
public abstract class MouseAdapter implements MouseListener
{

    public MouseAdapter() { }

    /** 
     * Invoked when the mouse has been clicked on a component.
     */
    public void mouseClicked(MouseEvent e) { }

    /** 
     * Invoked when a mouse button has been pressed on a component.
     */
    public void mousePressed(MouseEvent e) { }

    /** 
     * Invoked when a mouse button has been released on a component.
     */
    public void mouseReleased(MouseEvent e) { }

    /** 
     * Invoked when the mouse enters a component.
     */
    public void mouseEntered(MouseEvent e) { }

    /** 
     * Invoked when the mouse exits a component.
     */
    public void mouseExited(MouseEvent e) { }
}
