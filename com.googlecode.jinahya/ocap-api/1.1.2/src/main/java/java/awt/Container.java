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

// import javax.accessibility.*;

import java.io.PrintStream;
import java.io.PrintWriter;
// import java.awt.peer.ContainerPeer;
// import java.awt.peer.ComponentPeer;
// import java.awt.peer.LightweightPeer;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.FocusEvent;
// import java.awt.event.HierarchyEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
// import java.awt.event.MouseWheelEvent;
import java.awt.event.ContainerListener;
import java.util.EventListener;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.awt.event.AWTEventListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
// import java.awt.dnd.DropTarget;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Iterator;
import java.beans.PropertyChangeListener;
// import javax.swing.JRootPane;
// import sun.awt.AppContext;
// import sun.awt.DebugHelper;
// import sun.awt.SunToolkit;
// import sun.awt.dnd.SunDropTargetEvent;

/** 
 * A generic Abstract Window Toolkit(AWT) container object is a component 
 * that can contain other AWT components.
 * <p>
 * Components added to a container are tracked in a list.  The order
 * of the list will define the components' front-to-back stacking order 
 * within the container.  If no index is specified when adding a
 * component to a container, it will be added to the end of the list
 * (and hence to the bottom of the stacking order).
 * @version 	1.239, 02/26/03
 * @author 	Arthur van Hoff
 * @author 	Sami Shaio
 * @see       #add(java.awt.Component, int)
 * @see       #getComponent(int)
 * @see       LayoutManager
 * @since     JDK1.0
 */
public class Container extends Component
{
    /** 
     * The number of components in this container.
     * This value can be null.
     * @serial
     * @see #getComponent
     * @see #getComponents
     * @see #getComponentCount
     */
     int ncomponents;

    /** 
     * The components in this container.
     * @serial
     * @see #add
     * @see #getComponents
     */
     Component[] component;

    /** 
     * Layout manager for this container.
     * @serial
     * @see #doLayout
     * @see #setLayout
     * @see #getLayout
     */
     LayoutManager layoutMgr;

    // /** 
     // * Event router for lightweight components.  If this container
     // * is native, this dispatcher takes care of forwarding and 
     // * retargeting the events to lightweight components contained
     // * (if any).
     // * @serial
     // */
    // private LightweightDispatcher dispatcher;

    private Dimension maxSize;

    /** 
     * Indicates whether this Component is the root of a focus traversal cycle.
     * Once focus enters a traversal cycle, typically it cannot leave it via
     * focus traversal unless one of the up- or down-cycle keys is pressed.
     * Normal traversal is limited to this Container, and all of this
     * Container's descendants that are not descendants of inferior focus cycle
     * roots.
     *
     * @serial
     * @see #setFocusCycleRoot
     * @see #isFocusCycleRoot
     * @since 1.4
     */
    private boolean focusCycleRoot;

    /**
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = 4613797578919906343L;

    /** 
     * Container Serial Data Version.
     * @serial
     */
    private int containerSerializedDataVersion;

    /** 
     * Constructs a new Container. Containers can be extended directly, 
     * but are lightweight in this case and must be contained by a parent
     * somewhere higher up in the component tree that is native.
     * (such as Frame for example).
     */
    public Container() { }

    /** 
     * Gets the number of components in this panel.
     * @return    the number of components in this panel.
     * @see       #getComponent
     * @since     JDK1.1
     */
    public int getComponentCount() { return 0; }

//    /** 
//     * @deprecated As of JDK version 1.1,
//     * replaced by getComponentCount().
//     */
//    public int countComponents() {return 0; }

    /** 
     * Gets the nth component in this container.
     * @param      n   the index of the component to get.
     * @return     the n<sup>th</sup> component in this container.
     * @exception  ArrayIndexOutOfBoundsException  
     *                 if the n<sup>th</sup> value does not exist.     
     */
    public Component getComponent(int n) { return null; }

    /** 
     * Gets all the components in this container.
     * @return    an array of all the components in this container.     
     */
    public Component[] getComponents() { return null;}

    /** 
     * Determines the insets of this container, which indicate the size 
     * of the container's border. 
     * <p>
     * A <code>Frame</code> object, for example, has a top inset that 
     * corresponds to the height of the frame's title bar. 
     * @return    the insets of this container.
     * @see       Insets
     * @see       LayoutManager
     * @since     JDK1.1
     */
    public Insets getInsets() { return null; }

//    /** 
//    * @deprecated As of JDK version 1.1,
//     * replaced by <code>getInsets()</code>.
//     */
//    public Insets insets() { return null; }

    // PBP/PP 6213237
    /** 
     * Appends the specified component to the end of this container. 
     * This is a convenience method for {@link #addImpl}.
     * <p>
     * Note: If a component has been added to a container that
     * has been displayed, <code>validate</code> must be
     * called on that container to display the new component.
     * If multiple components are being added, you can improve
     * efficiency by calling <code>validate</code> only once,
     * after all the components have been added.
     *
     * @param     comp   the component to be added
     * @see #addImpl
     * @see #validate
     * 
     * @return    the component argument
     */
    public Component add(Component comp) { return null; }

    /** 
     * Adds the specified component to this container.
     * This is a convenience method for {@link #addImpl}.
     * <p>
     * This method is obsolete as of 1.1.  Please use the
     * method <code>add(Component, Object)</code> instead.
     * @see #add(Component, Object)
     */
    public Component add(String name, Component comp) { return null; }

    // PBP/PP 6213237
    /** 
     * Adds the specified component to this container at the given 
     * position. 
     * This is a convenience method for {@link #addImpl}.
     * <p>
     * Note: If a component has been added to a container that
     * has been displayed, <code>validate</code> must be
     * called on that container to display the new component.
     * If multiple components are being added, you can improve
     * efficiency by calling <code>validate</code> only once,
     * after all the components have been added.
     *
     * @param     comp   the component to be added
     * @param     index    the position at which to insert the component, 
     *                   or <code>-1</code> to append the component to the end
     * @return    the component <code>comp</code>
     * @see #addImpl
     * @see #remove
     * @see #validate
     * 
     */
    public Component add(Component comp, int index) {  return null; }

    // PBP/PP 6213237
    /** 
     * Adds the specified component to the end of this container.
     * Also notifies the layout manager to add the component to 
     * this container's layout using the specified constraints object.
     * This is a convenience method for {@link #addImpl}.
     * <p>
     * Note: If a component has been added to a container that
     * has been displayed, <code>validate</code> must be
     * called on that container to display the new component.
     * If multiple components are being added, you can improve
     * efficiency by calling <code>validate</code> only once,
     * after all the components have been added.
     *
     * @param     comp the component to be added
     * @param     constraints an object expressing 
     *                  layout contraints for this component
     * @see #addImpl
     * @see #validate
     * 
     * @see       LayoutManager
     * @since     JDK1.1
     */
    public void add(Component comp, Object constraints) { }

    // PBP/PP 6213237
    /** 
     * Adds the specified component to this container with the specified
     * constraints at the specified index.  Also notifies the layout 
     * manager to add the component to the this container's layout using 
     * the specified constraints object.
     * This is a convenience method for {@link #addImpl}.
     * <p>
     * Note: If a component has been added to a container that
     * has been displayed, <code>validate</code> must be
     * called on that container to display the new component.
     * If multiple components are being added, you can improve
     * efficiency by calling <code>validate</code> only once,
     * after all the components have been added.
     *
     * @param comp the component to be added
     * @param constraints an object expressing layout contraints for this
     * @param index the position in the container's list at which to insert
     * the component; <code>-1</code> means insert at the end
     * component
     * @see #addImpl
     * @see #validate
     * 
     * @see #remove
     * @see LayoutManager
     */
    public void add(Component comp, Object constraints, int index) { }

    /** 
     * Adds the specified component to this container at the specified
     * index. This method also notifies the layout manager to add 
     * the component to this container's layout using the specified 
     * constraints object via the <code>addLayoutComponent</code>
     * method.  The constraints are
     * defined by the particular layout manager being used.  For 
     * example, the <code>BorderLayout</code> class defines five
     * constraints: <code>BorderLayout.NORTH</code>,
     * <code>BorderLayout.SOUTH</code>, <code>BorderLayout.EAST</code>,
     * <code>BorderLayout.WEST</code>, and <code>BorderLayout.CENTER</code>.
     *
     * <p>Note that if the component already exists
     * in this container or a child of this container, 
     * it is removed from that container before
     * being added to this container. 
     * <p>
     * This is the method to override if a program needs to track 
     * every add request to a container as all other add methods defer
     * to this one. An overriding method should 
     * usually include a call to the superclass's version of the method:
     * <p>
     * <blockquote>
     * <code>super.addImpl(comp, constraints, index)</code>
     * </blockquote>
     * <p>
     * @param     comp       the component to be added
     * @param     constraints an object expressing layout constraints 
     *                 for this component
     * @param     index the position in the container's list at which to
     *                 insert the component, where <code>-1</code> 
     *                 means append to the end
     * @exception IllegalArgumentException if <code>index</code> is invalid
     * @exception IllegalArgumentException if adding the container's parent
     *			to itself
     * @exception IllegalArgumentException if adding a window to a container
     * @see       #add(Component)       
     * @see       #add(Component, int)       
     * @see       #add(Component, java.lang.Object)       
     * @see       LayoutManager
     * @see       LayoutManager2
     * @since     JDK1.1
     */
    protected void addImpl(Component comp, Object constraints, int index) { }

    /** 
     * Removes the component, specified by <code>index</code>, 
     * from this container. 
     * @param     index   the index of the component to be removed.
     * @see #add
     * @since JDK1.1
     */
    public void remove(int index) { }

    /** 
     * Removes the specified component from this container.
     * @param comp the component to be removed
     * @see #add
     */
    public void remove(Component comp) { }

    /** 
     * Removes all the components from this container.
     * @see #add
     * @see #remove
     */
    public void removeAll() { }

    /** 
     * Gets the layout manager for this container.  
     * @see #doLayout
     * @see #setLayout
     */
    public LayoutManager getLayout() { return null; }

    /** 
     * Sets the layout manager for this container.
     * @param mgr the specified layout manager
     * @see #doLayout
     * @see #getLayout
     */
    public void setLayout(LayoutManager mgr) { }

    /** 
     * Causes this container to lay out its components.  Most programs 
     * should not call this method directly, but should invoke 
     * the <code>validate</code> method instead.
     * @see LayoutManager#layoutContainer
     * @see #setLayout
     * @see #validate
     * @since JDK1.1
     */
    public void doLayout() { }

//    /** 
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>doLayout()</code>.
//     */
//    public void layout() { }

    /** 
     * Invalidates the container.  The container and all parents
     * above it are marked as needing to be laid out.  This method can
     * be called often, so it needs to execute quickly.
     * @see #validate
     * @see LayoutManager
     */
    public void invalidate() { }

    // PBP/PP 6213237
    /** 
     * Validates this container and all of its subcomponents.
     * <p>
     * The <code>validate</code> method is used to cause a container
     * to lay out its subcomponents again. It should be invoked when
     * this container's subcomponents are modified (added to or
     * removed from the container, or layout-related information
     * changed) after the container has been displayed.
     *
     * @see #add(java.awt.Component)
     * @see Component#invalidate
     * 
     */
    public void validate() { }

    /** 
     * Recursively descends the container tree and recomputes the
     * layout for any subtrees marked as needing it (those marked as
     * invalid).  Synchronization should be provided by the method
     * that calls this one:  <code>validate</code>.
     */
    protected void validateTree() { }

    /** 
     * Sets the font of this container.
     * @param f The font to become this container's font.
     * @see Component#getFont
     * @since JDK1.0
     */
    public void setFont(Font f) { }

    /** 
     * Returns the preferred size of this container.  
     * @return    an instance of <code>Dimension</code> that represents 
     *                the preferred size of this container.
     * @see       #getMinimumSize       
     * @see       #getLayout
     * @see       LayoutManager#preferredLayoutSize(Container)
     * @see       Component#getPreferredSize
     */
    public Dimension getPreferredSize() { return null; }

//    /** 
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>getPreferredSize()</code>.
//     */
//    public Dimension preferredSize() { return null; }

    /** 
     * Returns the minimum size of this container.  
     * @return    an instance of <code>Dimension</code> that represents 
     *                the minimum size of this container.
     * @see       #getPreferredSize       
     * @see       #getLayout
     * @see       LayoutManager#minimumLayoutSize(Container)
     * @see       Component#getMinimumSize
     * @since     JDK1.1
     */
    public Dimension getMinimumSize() { return null; }

//    /** 
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>getMinimumSize()</code>.
//     */
//    public Dimension minimumSize() { return null; }

    /** 
     * Returns the maximum size of this container.  
     * @see #getPreferredSize
     */
    public Dimension getMaximumSize() {return null;  }

    /** 
     * Returns the alignment along the x axis.  This specifies how
     * the component would like to be aligned relative to other 
     * components.  The value should be a number between 0 and 1
     * where 0 represents alignment along the origin, 1 is aligned
     * the furthest away from the origin, 0.5 is centered, etc.
     */
    public float getAlignmentX() { return 0;}

    /** 
     * Returns the alignment along the y axis.  This specifies how
     * the component would like to be aligned relative to other 
     * components.  The value should be a number between 0 and 1
     * where 0 represents alignment along the origin, 1 is aligned
     * the furthest away from the origin, 0.5 is centered, etc.
     */
    public float getAlignmentY() {return 0; }

    /** 
     * Paints the container. This forwards the paint to any lightweight
     * components that are children of this container. If this method is
     * reimplemented, super.paint(g) should be called so that lightweight
     * components are properly rendered. If a child component is entirely
     * clipped by the current clipping setting in g, paint() will not be
     * forwarded to that child.
     *
     * @param g the specified Graphics window
     * @see   Component#update(Graphics)
     */
    public void paint(Graphics g) { }

    /** 
     * Updates the container.  This forwards the update to any lightweight
     * components that are children of this container.  If this method is
     * reimplemented, super.update(g) should be called so that lightweight
     * components are properly rendered.  If a child component is entirely
     * clipped by the current clipping setting in g, update() will not be
     * forwarded to that child.
     *
     * @param g the specified Graphics window
     * @see   Component#update(Graphics)
     */
    public void update(Graphics g) { }

    /** 
     * Prints the container. This forwards the print to any lightweight
     * components that are children of this container. If this method is
     * reimplemented, super.print(g) should be called so that lightweight
     * components are properly rendered. If a child component is entirely
     * clipped by the current clipping setting in g, print() will not be
     * forwarded to that child.
     *
     * @param g the specified Graphics window
     * @see   Component#update(Graphics)
     */
    public void print(Graphics g) { }

    /** 
     * Paints each of the components in this container.
     * @param     g   the graphics context.
     * @see       Component#paint
     * @see       Component#paintAll
     */
    public void paintComponents(Graphics g) { }

    /** 
     * Prints each of the components in this container. 
     * @param     g   the graphics context.
     * @see       Component#print
     * @see       Component#printAll
     */
    public void printComponents(Graphics g) { }

    /** 
     * Adds the specified container listener to receive container events
     * from this container.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param    l the container listener
     *
     * @see #removeContainerListener
     */
    public synchronized void addContainerListener(ContainerListener l) { }

    /** 
     * Removes the specified container listener so it no longer receives
     * container events from this container.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param 	l the container listener
     *
     * @see #addContainerListener
     */
    public synchronized void removeContainerListener(ContainerListener l) { }

    /** 
     * Returns an array of all the container listeners
     * registered on this container.
     *
     * @return all of this container's <code>ContainerListener</code>s
     *         or an empty array if no container
     *         listeners are currently registered
     *
     * @see #addContainerListener
     * @see #removeContainerListener
     * @since 1.4
     */
    public synchronized ContainerListener[] getContainerListeners() {
      return null;
    }

    // /** 
     // * Returns an array of all the objects currently registered
     // * as <code><em>Foo</em>Listener</code>s
     // * upon this <code>Container</code>.
     // * <code><em>Foo</em>Listener</code>s are registered using the
     // * <code>add<em>Foo</em>Listener</code> method.
     // *
     // * <p>
     // * You can specify the <code>listenerType</code> argument
     // * with a class literal, such as
     // * <code><em>Foo</em>Listener.class</code>.
     // * For example, you can query a
     // * <code>Container</code> <code>c</code>
     // * for its container listeners with the following code:
     // *
     // * <pre>ContainerListener[] cls = (ContainerListener[])(c.getListeners(ContainerListener.class));</pre>
     // *
     // * If no such listeners exist, this method returns an empty array.
     // *
     // * @param listenerType the type of listeners requested; this parameter
     // *          should specify an interface that descends from
     // *          <code>java.util.EventListener</code>
     // * @return an array of all objects registered as
     // *          <code><em>Foo</em>Listener</code>s on this container,
     // *          or an empty array if no such listeners have been added
     // * @exception ClassCastException if <code>listenerType</code>
     // *          doesn't specify a class or interface that implements
     // *          <code>java.util.EventListener</code>
     // *
     // * @see #getContainerListeners
     // *
     // * @since 1.3
     // */
    // public EventListener[] getListeners(Class listenerType) { }

    /** 
     * Processes events on this container. If the event is a
     * <code>ContainerEvent</code>, it invokes the
     * <code>processContainerEvent</code> method, else it invokes 
     * its superclass's <code>processEvent</code>.
     * <p>Note that if the event parameter is <code>null</code>
     * the behavior is unspecified and may result in an
     * exception.
     *
     * @param e the event
     */
    protected void processEvent(AWTEvent e) { }

    /** 
     * Processes container events occurring on this container by
     * dispatching them to any registered ContainerListener objects.
     * NOTE: This method will not be called unless container events
     * are enabled for this component; this happens when one of the
     * following occurs:
     * <ul>
     * <li>A ContainerListener object is registered via
     *     <code>addContainerListener</code>
     * <li>Container events are enabled via <code>enableEvents</code>
     * </ul>
     * <p>Note that if the event parameter is <code>null</code>
     * the behavior is unspecified and may result in an
     * exception.
     *
     * @param e the container event
     * @see Component#enableEvents
     */
    protected void processContainerEvent(ContainerEvent e) { }

//    /** 
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>dispatchEvent(AWTEvent e)</code>
//     */
//    public void deliverEvent(Event e) { }

    /** 
     * Locates the component that contains the x,y position.  The
     * top-most child component is returned in the case where there
     * is overlap in the components.  This is determined by finding
     * the component closest to the index 0 that claims to contain
     * the given point via Component.contains(), except that Components
     * which have native peers take precedence over those which do not
     * (i.e., lightweight Components).
     *
     * @param x the <i>x</i> coordinate
     * @param y the <i>y</i> coordinate
     * @return null if the component does not contain the position.
     * If there is no child component at the requested point and the 
     * point is within the bounds of the container the container itself 
     * is returned; otherwise the top-most child is returned.
     * @see Component#contains
     * @since JDK1.1
     */
    public Component getComponentAt(int x, int y) { return null; }

//    /** 
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>getComponentAt(int, int)</code>.
//     */
//    public Component locate(int x, int y) {return null;  }

    /** 
     * Gets the component that contains the specified point.
     * @param      p   the point.
     * @return     returns the component that contains the point,
     *                 or <code>null</code> if the component does 
     *                 not contain the point. 
     * @see        Component#contains 
     * @since      JDK1.1 
     */
    public Component getComponentAt(Point p) { return null; }

    /** 
     * Locates the visible child component that contains the specified
     * position.  The top-most child component is returned in the case 
     * where there is overlap in the components.  If the containing child 
     * component is a Container, this method will continue searching for 
     * the deepest nested child component.  Components which are not
     * visible are ignored during the search.<p>
     *
     * The findComponentAt method is different from getComponentAt in
     * that getComponentAt only searches the Container's immediate
     * children; if the containing component is a Container, 
     * findComponentAt will search that child to find a nested component.
     *
     * @param x the <i>x</i> coordinate
     * @param y the <i>y</i> coordinate
     * @return null if the component does not contain the position.
     * If there is no child component at the requested point and the 
     * point is within the bounds of the container the container itself 
     * is returned.
     * @see Component#contains
     * @see #getComponentAt
     * @since 1.2
     */
    public Component findComponentAt(int x, int y) { return null; }

    /** 
     * Locates the visible child component that contains the specified
     * point.  The top-most child component is returned in the case 
     * where there is overlap in the components.  If the containing child 
     * component is a Container, this method will continue searching for 
     * the deepest nested child component.  Components which are not
     * visible are ignored during the search.<p>
     *
     * The findComponentAt method is different from getComponentAt in
     * that getComponentAt only searches the Container's immediate
     * children; if the containing component is a Container, 
     * findComponentAt will search that child to find a nested component.
     *
     * @param      p   the point.
     * @return null if the component does not contain the position.
     * If there is no child component at the requested point and the 
     * point is within the bounds of the container the container itself 
     * is returned.
     * @see Component#contains
     * @see #getComponentAt
     * @since 1.2
     */
    public Component findComponentAt(Point p) { return null; }

    // /** 
     // * Makes this Container displayable by connecting it to
     // * a native screen resource.  Making a container displayable will
     // * cause all of its children to be made displayable.
     // * This method is called internally by the toolkit and should
     // * not be called directly by programs.
     // * @see Component#isDisplayable
     // * @see #removeNotify
     // */
    // public void addNotify() { }
// 
    // /** 
     // * Makes this Container undisplayable by removing its connection
     // * to its native screen resource.  Making a container undisplayable
     // * will cause all of its children to be made undisplayable. 
     // * This method is called by the toolkit internally and should
     // * not be called directly by programs.
     // * @see Component#isDisplayable
     // * @see #addNotify
     // */
    // public void removeNotify() { }

    /** 
     * Checks if the component is contained in the component hierarchy of
     * this container.
     * @param c the component
     * @return     <code>true</code> if it is an ancestor; 
     *             <code>false</code> otherwise.
     * @since      JDK1.1
     */
    public boolean isAncestorOf(Component c) { return false; }

    /** 
     * Returns a string representing the state of this <code>Container</code>.
     * This method is intended to be used only for debugging purposes, and the 
     * content and format of the returned string may vary between 
     * implementations. The returned string may be empty but may not be 
     * <code>null</code>.
     *
     * @return    the parameter string of this container
     */
    protected String paramString() { return null; }

    /** 
     * Prints a listing of this container to the specified output 
     * stream. The listing starts at the specified indentation. 
     * @param    out      a print stream.
     * @param    indent   the number of spaces to indent.
     * @see      Component#list(java.io.PrintStream, int)
     * @since    JDK1.0
     */
    public void list(PrintStream out, int indent) { }

    /** 
     * Prints out a list, starting at the specified indention, to the specified
     * print writer.
     */
    public void list(PrintWriter out, int indent) { }

    /** 
     * Sets the focus traversal keys for a given traversal operation for this
     * Container.
     * <p>
     * The default values for a Container's focus traversal keys are
     * implementation-dependent. Sun recommends that all implementations for a
     * particular native platform use the same default values. The
     * recommendations for Windows and Unix are listed below. These
     * recommendations are used in the Sun AWT implementations.
     *
     * <table border=1 summary="Recommended default values for a Container's focus traversal keys">
     * <tr>
     *    <th>Identifier</th>
     *    <th>Meaning</th>
     *    <th>Default</th>
     * </tr>
     * <tr>
     *    <td>KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS</td>
     *    <td>Normal forward keyboard traversal</td>
     *    <td>TAB on KEY_PRESSED, CTRL-TAB on KEY_PRESSED</td>
     * </tr>
     * <tr>
     *    <td>KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS</td>
     *    <td>Normal reverse keyboard traversal</td>
     *    <td>SHIFT-TAB on KEY_PRESSED, CTRL-SHIFT-TAB on KEY_PRESSED</td>
     * </tr>
     * <tr>
     *    <td>KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS</td>
     *    <td>Go up one focus traversal cycle</td>
     *    <td>none</td>
     * </tr>
     * <tr>
     *    <td>KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS<td>
     *    <td>Go down one focus traversal cycle</td>
     *    <td>none</td>
     * </tr>
     * </table>
     *
     * To disable a traversal key, use an empty Set; Collections.EMPTY_SET is
     * recommended.
     * <p>
     * Using the AWTKeyStroke API, client code can specify on which of two
     * specific KeyEvents, KEY_PRESSED or KEY_RELEASED, the focus traversal
     * operation will occur. Regardless of which KeyEvent is specified,
     * however, all KeyEvents related to the focus traversal key, including the
     * associated KEY_TYPED event, will be consumed, and will not be dispatched
     * to any Container. It is a runtime error to specify a KEY_TYPED event as
     * mapping to a focus traversal operation, or to map the same event to
     * multiple default focus traversal operations.
     * <p>
     * If a value of null is specified for the Set, this Container inherits the
     * Set from its parent. If all ancestors of this Container have null
     * specified for the Set, then the current KeyboardFocusManager's default
     * Set is used.
     *
     * @param id one of KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
     *        KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
     *        KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS
     * @param keystrokes the Set of AWTKeyStroke for the specified operation
     * @see #getFocusTraversalKeys
     * @see KeyboardFocusManager#FORWARD_TRAVERSAL_KEYS
     * @see KeyboardFocusManager#BACKWARD_TRAVERSAL_KEYS
     * @see KeyboardFocusManager#UP_CYCLE_TRAVERSAL_KEYS
     * @see KeyboardFocusManager#DOWN_CYCLE_TRAVERSAL_KEYS
     * @throws IllegalArgumentException if id is not one of
     *         KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
     *         KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
     *         KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS, or if keystrokes
     *         contains null, or if any Object in keystrokes is not an
     *         AWTKeyStroke, or if any keystroke represents a KEY_TYPED event,
     *         or if any keystroke already maps to another focus traversal
     *         operation for this Container
     * @since 1.4
     * @beaninfo
     *       bound: true
     */
    public void setFocusTraversalKeys(int id, Set keystrokes) { }

    /** 
     * Returns the Set of focus traversal keys for a given traversal operation
     * for this Container. (See
     * <code>setFocusTraversalKeys</code> for a full description of each key.)
     * <p>
     * If a Set of traversal keys has not been explicitly defined for this
     * Container, then this Container's parent's Set is returned. If no Set
     * has been explicitly defined for any of this Container's ancestors, then
     * the current KeyboardFocusManager's default Set is returned.
     *
     * @param id one of KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
     *        KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
     *        KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS
     * @return the Set of AWTKeyStrokes for the specified operation. The Set
     *         will be unmodifiable, and may be empty. null will never be
     *         returned.
     * @see #setFocusTraversalKeys
     * @see KeyboardFocusManager#FORWARD_TRAVERSAL_KEYS
     * @see KeyboardFocusManager#BACKWARD_TRAVERSAL_KEYS
     * @see KeyboardFocusManager#UP_CYCLE_TRAVERSAL_KEYS
     * @see KeyboardFocusManager#DOWN_CYCLE_TRAVERSAL_KEYS
     * @throws IllegalArgumentException if id is not one of
     *         KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
     *         KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
     *         KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS
     * @since 1.4
     */
    public Set getFocusTraversalKeys(int id) { return null; }

    /** 
     * Returns whether the Set of focus traversal keys for the given focus
     * traversal operation has been explicitly defined for this Container. If
     * this method returns <code>false</code>, this Container is inheriting the
     * Set from an ancestor, or from the current KeyboardFocusManager.
     *
     * @param id one of KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
     *        KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
     *        KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS
     * @return <code>true</code> if the the Set of focus traversal keys for the
     *         given focus traversal operation has been explicitly defined for
     *         this Component; <code>false</code> otherwise.
     * @throws IllegalArgumentException if id is not one of
     *         KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
     *        KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
     *        KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, or
     *        KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS
     * @since 1.4
     */
    public boolean areFocusTraversalKeysSet(int id) { return false; }

    /** 
     * Returns whether the specified Container is the focus cycle root of this
     * Container's focus traversal cycle. Each focus traversal cycle has only
     * a single focus cycle root and each Container which is not a focus cycle
     * root belongs to only a single focus traversal cycle. Containers which
     * are focus cycle roots belong to two cycles: one rooted at the Container
     * itself, and one rooted at the Container's nearest focus-cycle-root
     * ancestor. This method will return <code>true</code> for both such
     * Containers in this case.
     *
     * @param container the Container to be tested
     * @return <code>true</code> if the specified Container is a focus-cycle-
     *         root of this Container; <code>false</code> otherwise
     * @see #isFocusCycleRoot()
     * @since 1.4
     */
    public boolean isFocusCycleRoot(Container container) { return false; }

    public void transferFocusBackward() { }

    /** 
     * Sets the focus traversal policy that will manage keyboard traversal of
     * this Container's children, if this Container is a focus cycle root. If
     * the argument is null, this Container inherits its policy from its focus-
     * cycle-root ancestor. If the argument is non-null, this policy will be 
     * inherited by all focus-cycle-root children that have no keyboard-
     * traversal policy of their own (as will, recursively, their focus-cycle-
     * root children).
     * <p>
     * If this Container is not a focus cycle root, the policy will be
     * remembered, but will not be used or inherited by this or any other
     * Containers until this Container is made a focus cycle root.
     *
     * @param policy the new focus traversal policy for this Container
     * @see #getFocusTraversalPolicy
     * @see #setFocusCycleRoot
     * @see #isFocusCycleRoot
     * @since 1.4
     * @beaninfo
     *       bound: true
     */
    public void setFocusTraversalPolicy(FocusTraversalPolicy policy) { }

    /** 
     * Returns the focus traversal policy that will manage keyboard traversal
     * of this Container's children, or null if this Container is not a focus
     * cycle root. If no traversal policy has been explicitly set for this
     * Container, then this Container's focus-cycle-root ancestor's policy is
     * returned. 
     *
     * @return this Container's focus traversal policy, or null if this
     *         Container is not a focus cycle root.
     * @see #setFocusTraversalPolicy
     * @see #setFocusCycleRoot
     * @see #isFocusCycleRoot
     * @since 1.4
     */
    public FocusTraversalPolicy getFocusTraversalPolicy() { return null; }

    /** 
     * Returns whether the focus traversal policy has been explicitly set for
     * this Container. If this method returns <code>false</code>, this
     * Container will inherit its focus traversal policy from an ancestor.
     *
     * @return <code>true</code> if the focus traversal policy has been
     *         explicitly set for this Container; <code>false</code> otherwise.
     * @since 1.4
     */
    public boolean isFocusTraversalPolicySet() { return false; }

    /** 
     * Sets whether this Container is the root of a focus traversal cycle. Once
     * focus enters a traversal cycle, typically it cannot leave it via focus
     * traversal unless one of the up- or down-cycle keys is pressed. Normal
     * traversal is limited to this Container, and all of this Container's
     * descendants that are not descendants of inferior focus cycle roots. Note
     * that a FocusTraversalPolicy may bend these restrictions, however. For
     * example, ContainerOrderFocusTraversalPolicy supports implicit down-cycle
     * traversal.
     *
     * @param focusCycleRoot indicates whether this Container is the root of a
     *        focus traversal cycle
     * @see #isFocusCycleRoot()
     * @see #setFocusTraversalPolicy
     * @see #getFocusTraversalPolicy
     * @see ContainerOrderFocusTraversalPolicy
     * @since 1.4
     * @beaninfo
     *       bound: true
     */
    public void setFocusCycleRoot(boolean focusCycleRoot) { }

    /** 
     * Returns whether this Container is the root of a focus traversal cycle.
     * Once focus enters a traversal cycle, typically it cannot leave it via
     * focus traversal unless one of the up- or down-cycle keys is pressed.
     * Normal traversal is limited to this Container, and all of this
     * Container's descendants that are not descendants of inferior focus
     * cycle roots. Note that a FocusTraversalPolicy may bend these
     * restrictions, however. For example, ContainerOrderFocusTraversalPolicy
     * supports implicit down-cycle traversal.
     *
     * @return whether this Container is the root of a focus traversal cycle
     * @see #setFocusCycleRoot
     * @see #setFocusTraversalPolicy
     * @see #getFocusTraversalPolicy
     * @see ContainerOrderFocusTraversalPolicy
     * @since 1.4
     */
    public boolean isFocusCycleRoot() { return false; }

    /** 
     * Transfers the focus down one focus traversal cycle. If this Container is
     * a focus cycle root, then the focus owner is set to this Container's
     * default Component to focus, and the current focus cycle root is set to
     * this Container. If this Container is not a focus cycle root, then no
     * focus traversal operation occurs.
     *
     * @see       Component#requestFocus()
     * @see       #isFocusCycleRoot
     * @see       #setFocusCycleRoot
     * @since     1.4
     */
    public void transferFocusDownCycle() { }

    // /** 
     // * Sets the <code>ComponentOrientation</code> property of this container
     // * and all components contained within it.
     // *
     // * @param o the new component orientation of this container and
     // *        the components contained within it.
     // * @exception NullPointerException if <code>orientation</code> is null.
     // * @see Component#setComponentOrientation
     // * @see Component#getComponentOrientation
     // * @since 1.4
     // */
    // public void applyComponentOrientation(ComponentOrientation o) { }

    // /** 
     // * Adds a PropertyChangeListener to the listener list. The listener is
     // * registered for all bound properties of this class, including the
     // * following:
     // * <ul>
     // *    <li>this Container's font ("font")</li>
     // *    <li>this Container's background color ("background")</li>
     // *    <li>this Container's foreground color ("foreground")</li>
     // *    <li>this Container's focusability ("focusable")</li>
     // *    <li>this Container's focus traversal keys enabled state
     // *        ("focusTraversalKeysEnabled")</li>
     // *    <li>this Container's Set of FORWARD_TRAVERSAL_KEYS
     // *        ("forwardFocusTraversalKeys")</li>
     // *    <li>this Container's Set of BACKWARD_TRAVERSAL_KEYS
     // *        ("backwardFocusTraversalKeys")</li>
     // *    <li>this Container's Set of UP_CYCLE_TRAVERSAL_KEYS
     // *        ("upCycleFocusTraversalKeys")</li>
     // *    <li>this Container's Set of DOWN_CYCLE_TRAVERSAL_KEYS
     // *        ("downCycleFocusTraversalKeys")</li>
     // *    <li>this Container's focus traversal policy ("focusTraversalPolicy")
     // *        </li>
     // *    <li>this Container's focus-cycle-root state ("focusCycleRoot")</li>
     // * </ul>
     // * Note that if this Container is inheriting a bound property, then no
     // * event will be fired in response to a change in the inherited property.
     // * <p>
     // * If listener is null, no exception is thrown and no action is performed.
     // *
     // * @param    listener  the PropertyChangeListener to be added
     // *
     // * @see Component#removePropertyChangeListener
     // * @see #addPropertyChangeListener(java.lang.String,java.beans.PropertyChangeListener)
     // */
    // public void addPropertyChangeListener(PropertyChangeListener listener) { }
// 
    // /** 
     // * Adds a PropertyChangeListener to the listener list for a specific
     // * property. The specified property may be user-defined, or one of the
     // * following defaults:
     // * <ul>
     // *    <li>this Container's font ("font")</li>
     // *    <li>this Container's background color ("background")</li>
     // *    <li>this Container's foreground color ("foreground")</li>
     // *    <li>this Container's focusability ("focusable")</li>
     // *    <li>this Container's focus traversal keys enabled state
     // *        ("focusTraversalKeysEnabled")</li>
     // *    <li>this Container's Set of FORWARD_TRAVERSAL_KEYS
     // *        ("forwardFocusTraversalKeys")</li>
     // *    <li>this Container's Set of BACKWARD_TRAVERSAL_KEYS
     // *        ("backwardFocusTraversalKeys")</li>
     // *    <li>this Container's Set of UP_CYCLE_TRAVERSAL_KEYS
     // *        ("upCycleFocusTraversalKeys")</li>
     // *    <li>this Container's Set of DOWN_CYCLE_TRAVERSAL_KEYS
     // *        ("downCycleFocusTraversalKeys")</li>
     // *    <li>this Container's focus traversal policy ("focusTraversalPolicy")
     // *        </li>
     // *    <li>this Container's focus-cycle-root state ("focusCycleRoot")</li>
     // * </ul>
     // * Note that if this Container is inheriting a bound property, then no
     // * event will be fired in response to a change in the inherited property.
     // * <p>
     // * If listener is null, no exception is thrown and no action is performed.
     // *
     // * @param propertyName one of the property names listed above
     // * @param listener the PropertyChangeListener to be added
     // *
     // * @see #addPropertyChangeListener(java.beans.PropertyChangeListener)
     // * @see Component#removePropertyChangeListener
     // */
    // public void addPropertyChangeListener(String propertyName,
        // PropertyChangeListener listener)
    // { }
          // 
     /** 
      * Deserializes this <code>Container</code> from the specified
      * <code>ObjectInputStream</code>.
      * <ul>
      *    <li>Reads default serializable fields from the stream.</li>
      *    <li>Reads a list of serializable ContainerListener(s) as optional
      *        data. If the list is null, no Listeners are installed.</li>
      *    <li>Reads this Container's FocusTraversalPolicy, which may be null,
      *        as optional data.</li>
      * </ul>
      *
      * @param s the <code>ObjectInputStream</code> to read
      * @serial
      * @see #addContainerListener
      * @see #writeObject(ObjectOutputStream)
      */
     private void readObject(ObjectInputStream s)
         throws ClassNotFoundException, IOException
    { }

     /** 
      * Serializes this <code>Container</code> to the specified
      * <code>ObjectOutputStream</code>.
      * <ul>
      *    <li>Writes default serializable fields to the stream.</li>
      *    <li>Writes a list of serializable ContainerListener(s) as optional
      *        data. The non-serializable ContainerListner(s) are detected and
      *        no attempt is made to serialize them.</li>
      *    <li>Write this Container's FocusTraversalPolicy if and only if it
      *        is Serializable; otherwise, <code>null</code> is written.</li>
      * </ul>
      *
      * @param s the <code>ObjectOutputStream</code> to write
      * @serialData <code>null</code> terminated sequence of 0 or more pairs;
      *   the pair consists of a <code>String</code> and <code>Object</code>;
      *   the <code>String</code> indicates the type of object and
      *   is one of the following:
      *   <code>containerListenerK</code> indicating an
      *     <code>ContainerListener</code> object;
      *   the <code>Container</code>'s <code>FocusTraversalPolicy</code>,
      *     or <code>null</code>
      *
      * @see AWTEventMulticaster#save(java.io.ObjectOutputStream, java.lang.String, java.util.EventListener)
      * @see Container#containerListenerK
      * @see #readObject(ObjectInputStream)
      */
     private void writeObject(ObjectOutputStream s) throws IOException { }

    // /** 
     // * Inner class of Container used to provide default support for
     // * accessibility.  This class is not meant to be used directly by
     // * application developers, but is instead meant only to be
     // * subclassed by container developers.
     // * <p>
     // * The class used to obtain the accessible role for this object,
     // * as well as implementing many of the methods in the
     // * AccessibleContainer interface.
     // */
    // protected class AccessibleAWTContainer
        // extends Component.AccessibleAWTComponent
    // {
        // protected ContainerListener accessibleContainerHandler;
// 
        // protected AccessibleAWTContainer() { }
// 
        // /** 
         // * Returns the number of accessible children in the object.  If all
         // * of the children of this object implement <code>Accessible</code>,
         // * then this method should return the number of children of this object.
         // *
         // * @return the number of accessible children in the object
         // */
        // public int getAccessibleChildrenCount() { }
// 
        // /** 
         // * Returns the nth <code>Accessible</code> child of the object.
         // *
         // * @param i zero-based index of child
         // * @return the nth <code>Accessible</code> child of the object
         // */
        // public Accessible getAccessibleChild(int i) { }
// 
        // /** 
         // * Returns the <code>Accessible</code> child, if one exists,
         // * contained at the local coordinate <code>Point</code>.
         // *
         // * @param p the point defining the top-left corner of the 
         // *    <code>Accessible</code>, given in the coordinate space
         // *    of the object's parent
         // * @return the <code>Accessible</code>, if it exists,
         // *    at the specified location; else <code>null</code>
         // */
        // public Accessible getAccessibleAt(Point p) { }
// 
        // /** 
         // * Fire <code>PropertyChange</code> listener, if one is registered,
         // * when children are added or removed.
         // */
        // protected class AccessibleContainerHandler implements ContainerListener
        // {
// 
            // protected AccessibleContainerHandler() { }
// 
            // public void componentAdded(ContainerEvent e) { }
// 
            // public void componentRemoved(ContainerEvent e) { }
        // }
    // // }
}
