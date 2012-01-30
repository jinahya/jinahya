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
 * The listener interface for receiving action events. 
 * The class that is interested in processing an action event
 * implements this interface, and the object created with that
 * class is registered with a component, using the component's
 * <code>addActionListener</code> method. When the action event
 * occurs, that object's <code>actionPerformed</code> method is
 * invoked.
 *
 * @see ActionEvent
 * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/eventmodel.html">Tutorial: Java 1.1 Event Model</a>
 * @see <a href="http://www.awl.com/cp/javaseries/jcl1_2.html">Reference: The Java Class Libraries (update file)</a>
 *
 * @author Carl Quinn
 * @version 1.15 01/23/03
 * @since 1.1
 */
public interface ActionListener extends EventListener
{

    /** 
     * Invoked when an action occurs.
     */
    public void actionPerformed(ActionEvent e);
}
