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

import java.awt.event.*;

import java.lang.reflect.Array;
import java.util.EventListener;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.EventListener;

/** 
 * A class which implements efficient and thread-safe multi-cast event 
 * dispatching for the AWT events defined in the java.awt.event package.  
 * This class will manage an immutable structure consisting of a chain of 
 * event listeners and will dispatch events to those listeners.  Because
 * the structure is immutable, it is safe to use this API to add/remove
 * listeners during the process of an event dispatch operation.
 * However, event listeners added during the process of an event dispatch 
 * operation will not be notified of the event currently being dispatched.
 *
 * An example of how this class could be used to implement a new
 * component which fires "action" events:
 *
 * <pre><code>
 * public myComponent extends Component {
 *     ActionListener actionListener = null;
 *
 *     public synchronized void addActionListener(ActionListener l) {
 *	   actionListener = AWTEventMulticaster.add(actionListener, l);
 *     }
 *     public synchronized void removeActionListener(ActionListener l) {
 *  	   actionListener = AWTEventMulticaster.remove(actionListener, l);
 *     }
 *     public void processEvent(AWTEvent e) {
 *         // when event occurs which causes "action" semantic
 *         ActionListener listener = actionListener;
 *         if (listener != null) {
 *             listener.actionPerformed(new ActionEvent());
 *         }
 *     }
 * }
 * </code></pre>
 *
 * @author      John Rose
 * @author 	Amy Fowler
 * @version 	1.35, 01/23/03
 * @since 	1.1
 */
// public class AWTEventMulticaster
    // implements ComponentListener, ContainerListener, FocusListener, KeyListener,
    // MouseListener, MouseMotionListener, WindowListener, WindowFocusListener,
    // WindowStateListener, ActionListener, ItemListener, AdjustmentListener,
    // TextListener, InputMethodListener, HierarchyListener,
    // HierarchyBoundsListener, MouseWheelListener
public class AWTEventMulticaster
    implements ComponentListener, ContainerListener, FocusListener, KeyListener,
    MouseListener, MouseMotionListener, WindowListener, WindowFocusListener,
    ActionListener, ItemListener, InputMethodListener, AdjustmentListener,
    TextListener, MouseWheelListener
    {
    protected final java.util.EventListener a = null;

    protected final java.util.EventListener b = null;

    /** 
     * Creates an event multicaster instance which chains listener-a
     * with listener-b. Input parameters <code>a</code> and <code>b</code> 
     * should not be <code>null</code>, though implementations may vary in 
     * choosing whether or not to throw <code>NullPointerException</code> 
     * in that case.
     * @param a listener-a
     * @param b listener-b
     */
    protected AWTEventMulticaster(java.util.EventListener a,
        java.util.EventListener b)
    { }

    /** 
     * Removes a listener from this multicaster and returns the
     * resulting multicast listener.
     * @param oldl the listener to be removed
     */
    protected java.util.EventListener remove(java.util.EventListener oldl) { return null;}

    /** 
     * Handles the componentResized event by invoking the
     * componentResized methods on listener-a and listener-b.
     * @param e the component event
     */
    public void componentResized(ComponentEvent e) { }

    /** 
     * Handles the componentMoved event by invoking the
     * componentMoved methods on listener-a and listener-b.
     * @param e the component event
     */
    public void componentMoved(ComponentEvent e) { }

    /** 
     * Handles the componentShown event by invoking the
     * componentShown methods on listener-a and listener-b.
     * @param e the component event
     */
    public void componentShown(ComponentEvent e) { }

    /** 
     * Handles the componentHidden event by invoking the
     * componentHidden methods on listener-a and listener-b.
     * @param e the component event
     */
    public void componentHidden(ComponentEvent e) { }

    /** 
     * Handles the componentAdded container event by invoking the
     * componentAdded methods on listener-a and listener-b.
     * @param e the component event
     */
    public void componentAdded(ContainerEvent e) { }

    /** 
     * Handles the componentRemoved container event by invoking the
     * componentRemoved methods on listener-a and listener-b.
     * @param e the component event
     */
    public void componentRemoved(ContainerEvent e) { }

    /** 
     * Handles the focusGained event by invoking the
     * focusGained methods on listener-a and listener-b.
     * @param e the focus event
     */
    public void focusGained(FocusEvent e) { }

    /** 
     * Handles the focusLost event by invoking the
     * focusLost methods on listener-a and listener-b.
     * @param e the focus event
     */
    public void focusLost(FocusEvent e) { }

    /** 
     * Handles the keyTyped event by invoking the
     * keyTyped methods on listener-a and listener-b.
     * @param e the key event
     */
    public void keyTyped(KeyEvent e) { }

    /** 
     * Handles the keyPressed event by invoking the
     * keyPressed methods on listener-a and listener-b.
     * @param e the key event
     */
    public void keyPressed(KeyEvent e) { }

    /** 
     * Handles the keyReleased event by invoking the
     * keyReleased methods on listener-a and listener-b.
     * @param e the key event
     */
    public void keyReleased(KeyEvent e) { }

    /** 
     * Handles the mouseClicked event by invoking the
     * mouseClicked methods on listener-a and listener-b.
     * @param e the mouse event
     */
    public void mouseClicked(MouseEvent e) { }

    /** 
     * Handles the mousePressed event by invoking the
     * mousePressed methods on listener-a and listener-b.
     * @param e the mouse event
     */
    public void mousePressed(MouseEvent e) { }

    /** 
     * Handles the mouseReleased event by invoking the
     * mouseReleased methods on listener-a and listener-b.
     * @param e the mouse event
     */
    public void mouseReleased(MouseEvent e) { }

    /** 
     * Handles the mouseEntered event by invoking the
     * mouseEntered methods on listener-a and listener-b.
     * @param e the mouse event
     */
    public void mouseEntered(MouseEvent e) { }

    /** 
     * Handles the mouseExited event by invoking the
     * mouseExited methods on listener-a and listener-b.
     * @param e the mouse event
     */
    public void mouseExited(MouseEvent e) { }

    /** 
     * Handles the mouseDragged event by invoking the
     * mouseDragged methods on listener-a and listener-b.
     * @param e the mouse event
     */
    public void mouseDragged(MouseEvent e) { }

    /** 
     * Handles the mouseMoved event by invoking the
     * mouseMoved methods on listener-a and listener-b.
     * @param e the mouse event
     */
    public void mouseMoved(MouseEvent e) { }

    /** 
     * Handles the windowOpened event by invoking the
     * windowOpened methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowOpened(WindowEvent e) { }

    /** 
     * Handles the windowClosing event by invoking the
     * windowClosing methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowClosing(WindowEvent e) { }

    /** 
     * Handles the windowClosed event by invoking the
     * windowClosed methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowClosed(WindowEvent e) { }

    /** 
     * Handles the windowIconified event by invoking the
     * windowIconified methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowIconified(WindowEvent e) { }

    /** 
     * Handles the windowDeiconfied event by invoking the
     * windowDeiconified methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowDeiconified(WindowEvent e) { }

    /** 
     * Handles the windowActivated event by invoking the
     * windowActivated methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowActivated(WindowEvent e) { }

    /** 
     * Handles the windowDeactivated event by invoking the
     * windowDeactivated methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowDeactivated(WindowEvent e) { }

    /** 
     * Handles the windowStateChanged event by invoking the
     * windowStateChanged methods on listener-a and listener-b.
     * @param e the window event
     */
      // public void windowStateChanged(WindowEvent e) { }

    /** 
     * Handles the windowGainedFocus event by invoking the windowGainedFocus
     * methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowGainedFocus(WindowEvent e) { }

    /** 
     * Handles the windowLostFocus event by invoking the windowLostFocus
     * methods on listener-a and listener-b.
     * @param e the window event
     */
    public void windowLostFocus(WindowEvent e) { }

    /** 
     * Handles the actionPerformed event by invoking the
     * actionPerformed methods on listener-a and listener-b.
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) { }

    /** 
     * Handles the itemStateChanged event by invoking the
     * itemStateChanged methods on listener-a and listener-b.
     * @param e the item event
     */
    public void itemStateChanged(ItemEvent e) { }

    /** 
     * Handles the adjustmentValueChanged event by invoking the
     * adjustmentValueChanged methods on listener-a and listener-b.
     * @param e the adjustment event
     */
    public void adjustmentValueChanged(AdjustmentEvent e) { }

    public void textValueChanged(TextEvent e) { }

     /** 
      * Handles the inputMethodTextChanged event by invoking the
      * inputMethodTextChanged methods on listener-a and listener-b.
      * @param e the item event
      */
     public void inputMethodTextChanged(InputMethodEvent e) { }

     /** 
      * Handles the caretPositionChanged event by invoking the
      * caretPositionChanged methods on listener-a and listener-b.
      * @param e the item event
      */
     public void caretPositionChanged(InputMethodEvent e) { }

    // /** 
     // * Handles the hierarchyChanged event by invoking the
     // * hierarchyChanged methods on listener-a and listener-b.
     // * @param e the item event
     // */
    // public void hierarchyChanged(HierarchyEvent e) { }

    // /** 
     // * Handles the ancestorMoved event by invoking the
     // * ancestorMoved methods on listener-a and listener-b.
     // * @param e the item event
     // */
    // public void ancestorMoved(HierarchyEvent e) { }
// 
    // /** 
     // * Handles the ancestorResized event by invoking the
     // * ancestorResized methods on listener-a and listener-b.
     // * @param e the item event
     // */
    // public void ancestorResized(HierarchyEvent e) { }

    /** 
     * Handles the mouseWheelMoved event by invoking the
     * mouseWheelMoved methods on listener-a and listener-b.
     * @param e the mouse event
     * @since 1.4
     */
    public void mouseWheelMoved(MouseWheelEvent e) { }

    /** 
     * Adds component-listener-a with component-listener-b and
     * returns the resulting multicast listener.
     * @param a component-listener-a
     * @param b component-listener-b
     */
    public static ComponentListener add(ComponentListener a, ComponentListener
        b)
    {return null; }

    /** 
     * Adds container-listener-a with container-listener-b and
     * returns the resulting multicast listener.
     * @param a container-listener-a
     * @param b container-listener-b
     */
    public static ContainerListener add(ContainerListener a, ContainerListener
        b)
    {return null; }

    /** 
     * Adds focus-listener-a with focus-listener-b and
     * returns the resulting multicast listener.
     * @param a focus-listener-a
     * @param b focus-listener-b
     */
    public static FocusListener add(FocusListener a, FocusListener b) {return null; }

    /** 
     * Adds key-listener-a with key-listener-b and
     * returns the resulting multicast listener.
     * @param a key-listener-a
     * @param b key-listener-b
     */
    public static KeyListener add(KeyListener a, KeyListener b) { return null;}

    /** 
     * Adds mouse-listener-a with mouse-listener-b and
     * returns the resulting multicast listener.
     * @param a mouse-listener-a
     * @param b mouse-listener-b
     */
    public static MouseListener add(MouseListener a, MouseListener b) { return null;}

    /** 
     * Adds mouse-motion-listener-a with mouse-motion-listener-b and
     * returns the resulting multicast listener.
     * @param a mouse-motion-listener-a
     * @param b mouse-motion-listener-b
     */
    public static MouseMotionListener add(MouseMotionListener a,
        MouseMotionListener b)
    { return null;}

    /** 
     * Adds window-listener-a with window-listener-b and
     * returns the resulting multicast listener.
     * @param a window-listener-a
     * @param b window-listener-b
     */
    public static WindowListener add(WindowListener a, WindowListener b) {return null; }

    /** 
     * Adds window-state-listener-a with window-state-listener-b
     * and returns the resulting multicast listener.
     * @param a window-state-listener-a
     * @param b window-state-listener-b
     */
//     public static WindowStateListener add(WindowStateListener a,
//         WindowStateListener b)
//     { return null;}

    /** 
     * Adds window-focus-listener-a with window-focus-listener-b
     * and returns the resulting multicast listener.
     * @param a window-focus-listener-a
     * @param b window-focus-listener-b
     */
    public static WindowFocusListener add(WindowFocusListener a,
        WindowFocusListener b)
    { return null;}

    /** 
     * Adds action-listener-a with action-listener-b and
     * returns the resulting multicast listener.
     * @param a action-listener-a
     * @param b action-listener-b
     */
    public static ActionListener add(ActionListener a, ActionListener b) { return null;}

    /** 
     * Adds item-listener-a with item-listener-b and
     * returns the resulting multicast listener.
     * @param a item-listener-a
     * @param b item-listener-b
     */
    public static ItemListener add(ItemListener a, ItemListener b) {return null; }

    /** 
     * Adds adjustment-listener-a with adjustment-listener-b and
     * returns the resulting multicast listener.
     * @param a adjustment-listener-a
     * @param b adjustment-listener-b
     */
    public static AdjustmentListener add(AdjustmentListener a,
        AdjustmentListener b)
    { return null;}

    public static TextListener add(TextListener a, TextListener b) {return null; }

     /** 
      * Adds input-method-listener-a with input-method-listener-b and
      * returns the resulting multicast listener.
      * @param a input-method-listener-a
      * @param b input-method-listener-b
      */
     public static InputMethodListener add(InputMethodListener a,
         InputMethodListener b)
     { return null; }

    // /** 
     // * Adds hierarchy-listener-a with hierarchy-listener-b and
     // * returns the resulting multicast listener.
     // * @param a hierarchy-listener-a
     // * @param b hierarchy-listener-b
     // */
    // public static HierarchyListener add(HierarchyListener a, HierarchyListener
        // b)
    // { }
// 
    // /** 
     // * Adds hierarchy-bounds-listener-a with hierarchy-bounds-listener-b and
     // * returns the resulting multicast listener.
     // * @param a hierarchy-bounds-listener-a
     // * @param b hierarchy-bounds-listener-b
     // */
    // public static HierarchyBoundsListener add(HierarchyBoundsListener a,
        // HierarchyBoundsListener b)
    // { }

    /** 
     * Adds mouse-wheel-listener-a with mouse-wheel-listener-b and
     * returns the resulting multicast listener.
     * @param a mouse-wheel-listener-a
     * @param b mouse-wheel-listener-b
     * @since 1.4
     */
    public static MouseWheelListener add(MouseWheelListener a,
        MouseWheelListener b)
    { return null; }

    /** 
     * Removes the old component-listener from component-listener-l and
     * returns the resulting multicast listener.
     * @param l component-listener-l
     * @param oldl the component-listener being removed
     */
    public static ComponentListener remove(ComponentListener l,
        ComponentListener oldl)
    { return null;}

    /** 
     * Removes the old container-listener from container-listener-l and
     * returns the resulting multicast listener.
     * @param l container-listener-l
     * @param oldl the container-listener being removed
     */
    public static ContainerListener remove(ContainerListener l,
        ContainerListener oldl)
    {return null; }

    /** 
     * Removes the old focus-listener from focus-listener-l and
     * returns the resulting multicast listener.
     * @param l focus-listener-l
     * @param oldl the focus-listener being removed
     */
    public static FocusListener remove(FocusListener l, FocusListener oldl) {return null; }

    /** 
     * Removes the old key-listener from key-listener-l and
     * returns the resulting multicast listener.
     * @param l key-listener-l
     * @param oldl the key-listener being removed
     */
    public static KeyListener remove(KeyListener l, KeyListener oldl) { return null;}

    /** 
     * Removes the old mouse-listener from mouse-listener-l and
     * returns the resulting multicast listener.
     * @param l mouse-listener-l
     * @param oldl the mouse-listener being removed
     */
    public static MouseListener remove(MouseListener l, MouseListener oldl) { return null;}

    /** 
     * Removes the old mouse-motion-listener from mouse-motion-listener-l 
     * and returns the resulting multicast listener.
     * @param l mouse-motion-listener-l
     * @param oldl the mouse-motion-listener being removed
     */
    public static MouseMotionListener remove(MouseMotionListener l,
        MouseMotionListener oldl)
    {return null; }

    /** 
     * Removes the old window-listener from window-listener-l and
     * returns the resulting multicast listener.
     * @param l window-listener-l
     * @param oldl the window-listener being removed
     */
    public static WindowListener remove(WindowListener l, WindowListener oldl)
    { return null;}

    /** 
     * Removes the old window-state-listener from window-state-listener-l
     * and returns the resulting multicast listener.
     * @param l window-state-listener-l
     * @param oldl the window-state-listener being removed
     */
//     public static WindowStateListener remove(WindowStateListener l,
//         WindowStateListener oldl)
//     {return null; }

    /** 
     * Removes the old window-focus-listener from window-focus-listener-l
     * and returns the resulting multicast listener.
     * @param l window-focus-listener-l
     * @param oldl the window-focus-listener being removed
     */
    public static WindowFocusListener remove(WindowFocusListener l,
        WindowFocusListener oldl)
    { return null;}

    /** 
     * Removes the old action-listener from action-listener-l and
     * returns the resulting multicast listener.
     * @param l action-listener-l
     * @param oldl the action-listener being removed
     */
    public static ActionListener remove(ActionListener l, ActionListener oldl)
    {return null; }

    /** 
     * Removes the old item-listener from item-listener-l and
     * returns the resulting multicast listener.
     * @param l item-listener-l
     * @param oldl the item-listener being removed
     */
    public static ItemListener remove(ItemListener l, ItemListener oldl) {return null; }

    /** 
     * Removes the old adjustment-listener from adjustment-listener-l and
     * returns the resulting multicast listener.
     * @param l adjustment-listener-l
     * @param oldl the adjustment-listener being removed
     */
    public static AdjustmentListener remove(AdjustmentListener l,
        AdjustmentListener oldl)
    {return null; }

    public static TextListener remove(TextListener l, TextListener oldl) {return null; }

     /** 
      * Removes the old input-method-listener from input-method-listener-l and
      * returns the resulting multicast listener.
      * @param l input-method-listener-l
      * @param oldl the input-method-listener being removed
      */
     public static InputMethodListener remove(InputMethodListener l,
         InputMethodListener oldl)
     { return null; }
// 
    // /** 
     // * Removes the old hierarchy-listener from hierarchy-listener-l and
     // * returns the resulting multicast listener.
     // * @param l hierarchy-listener-l
     // * @param oldl the hierarchy-listener being removed
     // */
    // public static HierarchyListener remove(HierarchyListener l,
        // HierarchyListener oldl)
    // { }
// 
    // /** 
     // * Removes the old hierarchy-bounds-listener from
     // * hierarchy-bounds-listener-l and returns the resulting multicast
     // * listener.
     // * @param l hierarchy-bounds-listener-l
     // * @param oldl the hierarchy-bounds-listener being removed
     // */
    // public static HierarchyBoundsListener remove(HierarchyBoundsListener l,
        // HierarchyBoundsListener oldl)
    // { }

    /** 
     * Removes the old mouse-wheel-listener from mouse-wheel-listener-l 
     * and returns the resulting multicast listener.
     * @param l mouse-wheel-listener-l
     * @param oldl the mouse-wheel-listener being removed
     * @since 1.4
     */
    public static MouseWheelListener remove(MouseWheelListener l,
        MouseWheelListener oldl)
    { return null; }

    /** 
     * Returns the resulting multicast listener from adding listener-a
     * and listener-b together.  
     * If listener-a is null, it returns listener-b;  
     * If listener-b is null, it returns listener-a
     * If neither are null, then it creates and returns
     * a new AWTEventMulticaster instance which chains a with b.
     * @param a event listener-a
     * @param b event listener-b
     */
    protected static java.util.EventListener addInternal(java.util.EventListener
        a, java.util.EventListener b)
    {return null; }

    /** 
     * Returns the resulting multicast listener after removing the
     * old listener from listener-l.
     * If listener-l equals the old listener OR listener-l is null, 
     * returns null.
     * Else if listener-l is an instance of AWTEventMulticaster, 
     * then it removes the old listener from it.
     * Else, returns listener l.
     * @param l the listener being removed from
     * @param oldl the listener being removed
     */
    protected static java.util.EventListener
        removeInternal(java.util.EventListener l, java.util.EventListener oldl)
    { return null;}

    protected void saveInternal(ObjectOutputStream s, String k)
        throws IOException
    { }

    protected static void save(ObjectOutputStream s, String k,
        java.util.EventListener l) throws IOException
    { }

    /** 
     * Returns an array of all the objects chained as
     * <code><em>Foo</em>Listener</code>s by the specified
     * <code>java.util.EventListener</code>.
     * <code><em>Foo</em>Listener</code>s are chained by the
     * <code>AWTEventMulticaster</code> using the
     * <code>add<em>Foo</em>Listener</code> method. 
     * If a <code>null</code> listener is specified, this method returns an
     * empty array. If the specified listener is not an instance of
     * <code>AWTEventMulticaster</code>, this method returns an array which
     * contains only the specified listener. If no such listeners are chanined,
     * this method returns an empty array.
     *
     * @param l the specified <code>java.util.EventListener</code>
     * @param listenerType the type of listeners requested; this parameter
     *          should specify an interface that descends from
     *          <code>java.util.EventListener</code>
     * @return an array of all objects chained as
     *          <code><em>Foo</em>Listener</code>s by the specified multicast
     *          listener, or an empty array if no such listeners have been
     *          chained by the specified multicast listener
     * @exception ClassCastException if <code>listenerType</code>
     *          doesn't specify a class or interface that implements
     *          <code>java.util.EventListener</code>
     *
     * @since 1.4
     */
    public static java.util.EventListener[] getListeners(java.util.EventListener
        l, Class listenerType)
    { return null; }
}
