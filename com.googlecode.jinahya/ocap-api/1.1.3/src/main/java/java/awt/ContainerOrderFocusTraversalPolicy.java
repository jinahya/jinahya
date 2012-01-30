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
 * A FocusTraversalPolicy that determines traversal order based on the order
 * of child Components in a Container. From a particular focus cycle root, the
 * policy makes a pre-order traversal of the Component hierarchy, and traverses
 * a Container's children according to the ordering of the array returned by
 * <code>Container.getComponents()</code>. Portions of the hierarchy that are
 * not visible and displayable will not be searched.
 * <p>
 * By default, ContainerOrderFocusTraversalPolicy implicitly transfers focus
 * down-cycle. That is, during normal forward focus traversal, the Component
 * traversed after a focus cycle root will be the focus-cycle-root's default
 * Component to focus. This behavior can be disabled using the
 * <code>setImplicitDownCycleTraversal</code> method.
 * <p>
 * By default, methods of this class with return a Component only if it is
 * visible, displayable, enabled, and focusable. Subclasses can modify this
 * behavior by overriding the <code>accept</code> method.
 *
 * @author David Mendenhall
 * @version 1.3, 01/23/03
 *
 * @see Container#getComponents
 * @since 1.4
 */
public class ContainerOrderFocusTraversalPolicy extends FocusTraversalPolicy
    implements java.io.Serializable
{
    /*
     * JDK 1.4 serialVersionUID 
     */
    private static final long serialVersionUID = 486933713763926351L;

    private boolean implicitDownCycleTraversal;

    public ContainerOrderFocusTraversalPolicy() { }

    /** 
     * Returns the Component that should receive the focus after aComponent.
     * focusCycleRoot must be a focus cycle root of aComponent.
     * <p>
     * By default, ContainerOrderFocusTraversalPolicy implicitly transfers
     * focus down-cycle. That is, during normal forward focus traversal, the
     * Component traversed after a focus cycle root will be the focus-cycle-
     * root's default Component to focus. This behavior can be disabled using
     * the <code>setImplicitDownCycleTraversal</code> method.
     *
     * @param focusCycleRoot a focus cycle root of aComponent
     * @param aComponent a (possibly indirect) child of focusCycleRoot, or
     *        focusCycleRoot itself
     * @return the Component that should receive the focus after aComponent, or
     *         null if no suitable Component can be found
     * @throws IllegalArgumentException if focusCycleRoot is not a focus cycle
     *         root of aComponent, or if either focusCycleRoot or aComponent is
     *         null
     */
    public Component getComponentAfter(Container focusCycleRoot, Component
        aComponent)
    { return null; }

    /** 
     * Returns the Component that should receive the focus before aComponent.
     * focusCycleRoot must be a focus cycle root of aComponent.
     *
     * @param focusCycleRoot a focus cycle root of aComponent
     * @param aComponent a (possibly indirect) child of focusCycleRoot, or
     *        focusCycleRoot itself
     * @return the Component that should receive the focus before aComponent,
     *         or null if no suitable Component can be found
     * @throws IllegalArgumentException if focusCycleRoot is not a focus cycle
     *         root of aComponent, or if either focusCycleRoot or aComponent is
     *         null
     */
    public Component getComponentBefore(Container focusCycleRoot, Component
        aComponent)
    { return null; }

    /** 
     * Returns the first Component in the traversal cycle. This method is used
     * to determine the next Component to focus when traversal wraps in the
     * forward direction.
     *
     * @param focusCycleRoot the focus cycle root whose first Component is to
     *         be returned
     * @return the first Component in the traversal cycle when focusCycleRoot
     *         is the focus cycle root, or null if no suitable Component can be
     *         found
     * @throws IllegalArgumentException if focusCycleRoot is null
     */
    public Component getFirstComponent(Container focusCycleRoot) { return null; }

    /** 
     * Returns the last Component in the traversal cycle. This method is used
     * to determine the next Component to focus when traversal wraps in the
     * reverse direction.
     *
     * @param focusCycleRoot the focus cycle root whose last Component is to be
     *        returned
     * @return the last Component in the traversal cycle when focusCycleRoot is
     *         the focus cycle root, or null if no suitable Component can be
     *         found
     * @throws IllegalArgumentException if focusCycleRoot is null
     */
    public Component getLastComponent(Container focusCycleRoot) { return null; }

    /** 
     * Returns the default Component to focus. This Component will be the first
     * to receive focus when traversing down into a new focus traversal cycle
     * rooted at focusCycleRoot. The default implementation of this method
     * returns the same Component as <code>getFirstComponent</code>.
     *
     * @param focusCycleRoot the focus cycle root whose default Component is to
     *        be returned
     * @return the default Component in the traversal cycle when focusCycleRoot
     *         is the focus cycle root, or null if no suitable Component can be
     *         found
     * @see #getFirstComponent
     * @throws IllegalArgumentException if focusCycleRoot is null
     */
    public Component getDefaultComponent(Container focusCycleRoot) {return null;  }

    /** 
     * Sets whether this ContainerOrderFocusTraversalPolicy transfers focus
     * down-cycle implicitly. If <code>true</code>, during normal forward focus
     * traversal, the Component traversed after a focus cycle root will be the
     * focus-cycle-root's default Component to focus. If <code>false</code>,
     * the next Component in the focus traversal cycle rooted at the specified
     * focus cycle root will be traversed instead. The default value for this
     * property is <code>true</code>.
     *
     * @param implicitDownCycleTraversal whether this
     *        ContainerOrderFocusTraversalPolicy transfers focus down-cycle
     *        implicitly
     * @see #getImplicitDownCycleTraversal
     * @see #getFirstComponent
     */
    public void setImplicitDownCycleTraversal(boolean
        implicitDownCycleTraversal)
    { }

    /** 
     * Returns whether this ContainerOrderFocusTraversalPolicy transfers focus
     * down-cycle implicitly. If <code>true</code>, during normal forward focus
     * traversal, the Component traversed after a focus cycle root will be the
     * focus-cycle-root's default Component to focus. If <code>false</code>,
     * the next Component in the focus traversal cycle rooted at the specified
     * focus cycle root will be traversed instead.
     *
     * @return whether this ContainerOrderFocusTraversalPolicy transfers focus
     *         down-cycle implicitly
     * @see #setImplicitDownCycleTraversal
     * @see #getFirstComponent
     */
    public boolean getImplicitDownCycleTraversal() { return false; }

    /** 
     * Determines whether a Component is an acceptable choice as the new
     * focus owner. By default, this method will accept a Component if and
     * only if it is visible, displayable, enabled, and focusable.
     *
     * @param aComponent the Component whose fitness as a focus owner is to
     *        be tested
     * @return <code>true</code> if aComponent is visible, displayable,
     *         enabled, and focusable; <code>false</code> otherwise
     */
    protected boolean accept(Component aComponent) { return false; }
}
