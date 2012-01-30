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
 * A FocusTraversalPolicy defines the order in which Components with a
 * particular focus cycle root are traversed. Instances can apply the policy to
 * arbitrary focus cycle roots, allowing themselves to be shared across
 * Containers. They do not need to be reinitialized when the focus cycle roots
 * of a Component hierarchy change.
 * <p>
 * The core responsibility of a FocusTraversalPolicy is to provide algorithms
 * determining the next and previous Components to focus when traversing
 * forward or backward in a UI. Each FocusTraversalPolicy must also provide
 * algorithms for determining the first, last, and default Components in a
 * traversal cycle. First and last Components are used when normal forward and
 * backward traversal, respectively, wraps. The default Component is the first
 * to receive focus when traversing down into a new focus traversal cycle.
 * A FocusTraversalPolicy can optionally provide an algorithm for determining
 * a Window's initial Component. The initial Component is the first to receive
 * focus when a Window is first made visible.
 *
 * @author David Mendenhall
 * @version 1.4, 01/23/03
 *
 * @see Container#setFocusTraversalPolicy
 * @see Container#getFocusTraversalPolicy
 * @see KeyboardFocusManager#setDefaultFocusTraversalPolicy
 * @see KeyboardFocusManager#getDefaultFocusTraversalPolicy
 * @since 1.4
 */
public abstract class FocusTraversalPolicy
{

    public FocusTraversalPolicy() { }

    /** 
     * Returns the Component that should receive the focus after aComponent.
     * focusCycleRoot must be a focus cycle root of aComponent.
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
    public abstract Component getComponentAfter(Container focusCycleRoot,
        Component aComponent);

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
    public abstract Component getComponentBefore(Container focusCycleRoot,
        Component aComponent);

    /** 
     * Returns the first Component in the traversal cycle. This method is used
     * to determine the next Component to focus when traversal wraps in the
     * forward direction.
     *
     * @param focusCycleRoot the focus cycle root whose first Component is to
     *        be returned
     * @return the first Component in the traversal cycle when focusCycleRoot
     *         is the focus cycle root, or null if no suitable Component can be
     *         found
     * @throws IllegalArgumentException if focusCycleRoot is null
     */
    public abstract Component getFirstComponent(Container focusCycleRoot);

    /** 
     * Returns the last Component in the traversal cycle. This method is used
     * to determine the next Component to focus when traversal wraps in the
     * reverse direction.
     *
     * @param focusCycleRoot the focus cycle root whose last Component is to be
     *         returned
     * @return the last Component in the traversal cycle when focusCycleRoot is
     *         the focus cycle root, or null if no suitable Component can be
     *         found
     * @throws IllegalArgumentException if focusCycleRoot is null
     */
    public abstract Component getLastComponent(Container focusCycleRoot);

    /** 
     * Returns the default Component to focus. This Component will be the first
     * to receive focus when traversing down into a new focus traversal cycle
     * rooted at focusCycleRoot.
     *
     * @param focusCycleRoot the focus cycle root whose default Component is to
     *        be returned
     * @return the default Component in the traversal cycle when focusCycleRoot
     *         is the focus cycle root, or null if no suitable Component can
     *         be found
     * @throws IllegalArgumentException if focusCycleRoot is null
     */
    public abstract Component getDefaultComponent(Container focusCycleRoot);

    /** 
     * Returns the Component that should receive the focus when a Window is
     * made visible for the first time. Once the Window has been made visible
     * by a call to <code>show()</code> or <code>setVisible(true)</code>, the
     * initial Component will not be used again. Instead, if the Window loses
     * and subsequently regains focus, or is made invisible or undisplayable
     * and subsequently made visible and displayable, the Window's most
     * recently focused Component will become the focus owner. The default
     * implementation of this method returns the default Component.
     *
     * @param window the Window whose initial Component is to be returned
     * @return the Component that should receive the focus when window is made
     *         visible for the first time, or null if no suitable Component can
     *         be found
     * @see #getDefaultComponent
     * @see Window#getMostRecentFocusOwner
     * @throws IllegalArgumentException if window is null
     */
    public Component getInitialComponent(Window window) { return null; }
}
