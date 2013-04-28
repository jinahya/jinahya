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
 * The listener interface for receiving keyboard events (keystrokes).
 * The class that is interested in processing a keyboard event
 * either implements this interface (and all the methods it
 * contains) or extends the abstract <code>KeyAdapter</code> class
 * (overriding only the methods of interest).
 * <P>
 * The listener object created from that class is then registered with a
 * component using the component's <code>addKeyListener</code> 
 * method. A keyboard event is generated when a key is pressed, released,
 * or typed (pressedn and released). The relevant method in the listener 
 * object is then invoked, and the <code>KeyEvent</code> is passed to it.
 *
 * @author Carl Quinn
 * @version 1.16 01/23/03
 *
 * @see KeyAdapter
 * @see KeyEvent
 * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/keylistener.html">Tutorial: Writing a Key Listener</a>
 * @see <a href="http://www.awl.com/cp/javaseries/jcl1_2.html">Reference: The Java Class Libraries (update file)</a>
 *
 * @since 1.1
 */
public interface KeyListener extends EventListener
{

    /** 
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of 
     * a key typed event.
     */
    public void keyTyped(KeyEvent e);

    /** 
     * Invoked when a key has been pressed. 
     * See the class description for {@link KeyEvent} for a definition of 
     * a key pressed event.
     */
    public void keyPressed(KeyEvent e);

    /** 
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of 
     * a key released event.
     */
    public void keyReleased(KeyEvent e);
}
