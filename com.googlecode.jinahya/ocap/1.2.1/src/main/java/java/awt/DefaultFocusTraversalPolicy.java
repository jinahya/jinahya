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

// import java.awt.peer.ComponentPeer;

// PBP/PP 6214663

/** 
 * A FocusTraversalPolicy that determines traversal order based on the order
 * of child Components in a Container. From a particular focus cycle root, the
 * policy makes a pre-order traversal of the Component hierarchy, and traverses
 * a Container's children according to the ordering of the array returned by
 * <code>Container.getComponents()</code>. Portions of the hierarchy that are
 * not visible and displayable will not be searched.
 * <p>
 * If client code has explicitly set the focusability of a Component by either
 * overriding <code>Component.isFocusTraversable()</code> or
 * <code>Component.isFocusable()</code>, or by calling
 * <code>Component.setFocusable()</code>, then a DefaultFocusTraversalPolicy
 * behaves exactly like a ContainerOrderFocusTraversalPolicy. If, however, the
 * Component is relying on default focusability, then a
 * DefaultFocusTraversalPolicy will reject all Components with non-focusable
 * peers. This is the default FocusTraversalPolicy for all AWT Containers.
 * <p>
 * The focusability of a peer is implementation-dependent. Sun recommends that
 * all implementations for a particular native platform construct peers with
 * the same focusability. The recommendations for Windows and Unix are that
 * Canvases, Labels, Panels, Scrollbars, ScrollPanes, Windows, and lightweight
 * Components have non-focusable peers, and all other Components have focusable
 * peers. These recommendations are used in the Sun AWT implementations. Note
 * that the focusability of a Component's peer is different from, and does not
 * impact, the focusability of the Component itself.
 *
 * @author David Mendenhall
 * @version 1.3, 01/23/03
 *
 * @see Container#getComponents
 * @see Component#isFocusable
 * @see Component#setFocusable
 * @since 1.4
 */
public class DefaultFocusTraversalPolicy
    extends ContainerOrderFocusTraversalPolicy
{

    public DefaultFocusTraversalPolicy() { }

    /** 
     * Determines whether a Component is an acceptable choice as the new
     * focus owner. The Component must be visible, displayable, and enabled
     * to be accepted. If client code has explicitly set the focusability
     * of the Component by either overriding
     * <code>Component.isFocusTraversable()</code> or
     * <code>Component.isFocusable()</code>, or by calling
     * <code>Component.setFocusable()</code>, then the Component will be
     * accepted if and only if it is focusable. If, however, the Component is
     * relying on default focusability, then all Canvases, Labels, Panels,
     *  Windows, and lightweight Components will be
     * rejected.
     *
     * @param aComponent the Component whose fitness as a focus owner is to
     *        be tested
     * @return <code>true</code> if aComponent meets the above requirements;
     *         <code>false</code> otherwise
     */
    protected boolean accept(Component aComponent) { return false; }
}
