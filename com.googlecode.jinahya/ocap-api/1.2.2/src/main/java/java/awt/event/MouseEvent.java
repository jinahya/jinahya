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

import java.awt.Component;
// import java.awt.Event;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;

/** 
 * An event which indicates that a mouse action occurred in a component.
 * A mouse action is considered to occur in a particular component if and only
 * if the mouse cursor is over the unobscured part of the component's bounds
 * when the action happens.
 * Component bounds can be obscurred by the visible component's children or by a
 * menu or by a top-level window.
 * This event is used both for mouse events (click, enter, exit) and mouse 
 * motion events (moves and drags). 
 * <P>
 * This low-level event is generated by a component object for:
 * <ul>
 * <li>Mouse Events
 *     <ul>
 *     <li>a mouse button is pressed
 *     <li>a mouse button is released
 *     <li>a mouse button is clicked (pressed and released)
 *     <li>the mouse cursor enters the unobscured part of component's geometry
 *     <li>the mouse cursor exits the unobscured part of component's geometry
 *     </ul>
 * <li> Mouse Motion Events
 *     <ul>
 *     <li>the mouse is moved
 *     <li>the mouse is dragged
 *     </ul>
 * </ul>
 * <P>
 * A <code>MouseEvent</code> object is passed to every
 * <code>MouseListener</code>
 * or <code>MouseAdapter</code> object which is registered to receive 
 * the "interesting" mouse events using the component's 
 * <code>addMouseListener</code> method.
 * (<code>MouseAdapter</code> objects implement the 
 * <code>MouseListener</code> interface.) Each such listener object 
 * gets a <code>MouseEvent</code> containing the mouse event.
 * <P>
 * A <code>MouseEvent</code> object is also passed to every
 * <code>MouseMotionListener</code> or
 * <code>MouseMotionAdapter</code> object which is registered to receive 
 * mouse motion events using the component's
 * <code>addMouseMotionListener</code>
 * method. (<code>MouseMotionAdapter</code> objects implement the 
 * <code>MouseMotionListener</code> interface.) Each such listener object 
 * gets a <code>MouseEvent</code> containing the mouse motion event.
 * <P>
 * When a mouse button is clicked, events are generated and sent to the
 * registered <code>MouseListener</code>s.
 * The state of modal keys can be retrieved using {@link InputEvent#getModifiers}
 * and {@link InputEvent#getModifiersEx}.
 * The button mask returned by {@link InputEvent#getModifiers} reflects
 * only the button that changed state, not the current state of all buttons.
 * (Note: Due to overlap in the values of ALT_MASK/BUTTON2_MASK and
 * META_MASK/BUTTON3_MASK, this is not always true for mouse events involving
 * modifier keys).
 * To get the state of all buttons and modifier keys, use
 * {@link InputEvent#getModifiersEx}.
 * The button which has changed state is returned by {@link MouseEvent#getButton}
 * <P> 
 * For example, if the first mouse button is pressed, events are sent in the
 * following order:
 * <PRE>
 *    <b   >id           </b   >   <b   >modifiers   </b   > <b   >button </b   >          
 *    <code>MOUSE_PRESSED</code>:  <code>BUTTON1_MASK</code> <code>BUTTON1</code>
 *    <code>MOUSE_RELEASED</code>: <code>BUTTON1_MASK</code> <code>BUTTON1</code>
 *    <code>MOUSE_CLICKED</code>:  <code>BUTTON1_MASK</code> <code>BUTTON1</code>
 * </PRE>
 * When multiple mouse buttons are pressed, each press, release, and click
 * results in a separate event. 
 * <P> 
 * For example, if the user presses <b>button 1</b> followed by
 * <b>button 2</b>, and then releases them in the same order,
 * the following sequence of events is generated:
 * <PRE>
 *    <b   >id           </b   >   <b   >modifiers   </b   > <b   >button </b   >          
 *    <code>MOUSE_PRESSED</code>:  <code>BUTTON1_MASK</code> <code>BUTTON1</code>
 *    <code>MOUSE_PRESSED</code>:  <code>BUTTON2_MASK</code> <code>BUTTON2</code>
 *    <code>MOUSE_RELEASED</code>: <code>BUTTON1_MASK</code> <code>BUTTON1</code>
 *    <code>MOUSE_CLICKED</code>:  <code>BUTTON1_MASK</code> <code>BUTTON1</code>
 *    <code>MOUSE_RELEASED</code>: <code>BUTTON2_MASK</code> <code>BUTTON2</code>
 *    <code>MOUSE_CLICKED</code>:  <code>BUTTON2_MASK</code> <code>BUTTON2</code>
 * </PRE>
 * If <b>button 2</b> is released first, the
 * <code>MOUSE_RELEASED</code>/<code>MOUSE_CLICKED</code> pair
 * for <code>BUTTON2_MASK</code> arrives first,
 * followed by the pair for <code>BUTTON1_MASK</code>.
 * <p>
 *
 * <code>MOUSE_DRAGGED</code> events are delivered to the <code>Component</code> 
 * in which the mouse button was pressed until the mouse button is released 
 * (regardless of whether the mouse position is within the bounds of the 
 * <code>Component</code>).  Due to platform-dependent Drag&Drop implementations, 
 * <code>MOUSE_DRAGGED</code> events may not be delivered during a native 
 * Drag&Drop operation.  
 * 
 * In a multi-screen environment mouse drag events are delivered to the
 * <code>Component</code> even if the mouse position is outside the bounds of the
 * <code>GraphicsConfiguration</code> associated with that 
 * <code>Component</code>. However, the reported position for mouse drag events
 * in this case may differ from the actual mouse position: 
 * <ul>
 * <li>In a multi-screen environment without a virtual device:
 * <br>
 * The reported coordinates for mouse drag events are clipped to fit within the
 * bounds of the <code>GraphicsConfiguration</code> associated with 
 * the <code>Component</code>.
 * <li>In a multi-screen environment with a virtual device:
 * <br>
 * The reported coordinates for mouse drag events are clipped to fit within the
 * bounds of the virtual device associated with the <code>Component</code>.   
 * </ul>
 *
 * <p>
 * <a name="restrictions">
 * <h4>Restrictions</h4>
 * <em>
 * Implementations of this Profile exhibit certain restrictions with respect
 * to mouse events, specifically:
 * <ul>
 * <li> An implementation need not support the generation of all mouse
 * events.  In such a case, it may partially support the generation of
 * mouse events or not support them at all.
 * <p>
 * Regardless of this restriction, applications may still
 * create MouseEvent objects and post them to the system event queue.
 * </ul>
 * For more information, see <a href="../../../doc-files/properties.html">Profile-specific properties</a>.
 * <p>
 * <b>Note:</b> These restrictions and their corresponding properties
 * apply only to the <code>MouseEvent</code> class, and not to its subclass,
 * {@link MouseWheelEvent}.
 * </em>
 * @author Carl Quinn
 * 1.45, 01/23/03
 *   
 * @see MouseAdapter
 * @see MouseListener
 * @see MouseMotionAdapter
 * @see MouseMotionListener
 * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/mouselistener.html">Tutorial: Writing a Mouse Listener</a>
 * @see <a href="http://java.sun.com/docs/books/tutorial/post1.0/ui/mousemotionlistener.html">Tutorial: Writing a Mouse Motion Listener</a>
 * @see <a href="http://www.awl.com/cp/javaseries/jcl1_2.html">Reference: The Java Class Libraries (update file)</a>
 *
 * @since 1.1
 */
public class MouseEvent extends InputEvent
{
    /** 
     * The first number in the range of ids used for mouse events.
     */
    public static final int MOUSE_FIRST = 500;

    /** 
     * The last number in the range of ids used for mouse events.
     */
    public static final int MOUSE_LAST = 507;

    /** 
     * The "mouse clicked" event. This <code>MouseEvent</code>
     * occurs when a mouse button is pressed and released.
     */
    public static final int MOUSE_CLICKED = MOUSE_FIRST;

    /** 
     * The "mouse pressed" event. This <code>MouseEvent</code>
     * occurs when a mouse button is pushed down.
     */
    public static final int MOUSE_PRESSED = 1 + MOUSE_FIRST;

    /** 
     * The "mouse released" event. This <code>MouseEvent</code>
     * occurs when a mouse button is let up.
     */
    public static final int MOUSE_RELEASED = 2 + MOUSE_FIRST;

    /** 
     * The "mouse moved" event. This <code>MouseEvent</code>
     * occurs when the mouse position changes.
     */
    public static final int MOUSE_MOVED = 3 + MOUSE_FIRST;

    /** 
     * The "mouse entered" event. This <code>MouseEvent</code>
     * occurs when the mouse cursor enters the unobscured part of component's
     * geometry. 
     */
    public static final int MOUSE_ENTERED = 4 + MOUSE_FIRST;

    /** 
     * The "mouse exited" event. This <code>MouseEvent</code>
     * occurs when the mouse cursor exits the unobscured part of component's
     * geometry.
     */
    public static final int MOUSE_EXITED = 5 + MOUSE_FIRST;

    /** 
     * The "mouse dragged" event. This <code>MouseEvent</code>
     * occurs when the mouse position changes while a mouse button is pressed.
     */
    public static final int MOUSE_DRAGGED = 6 + MOUSE_FIRST;

    /** 
     * The "mouse wheel" event.  This is the only <code>MouseWheelEvent</code>.
     * It occurs when a mouse equipped with a wheel has its wheel rotated.
     * @since 1.4
     */
    public static final int MOUSE_WHEEL = 7 + MOUSE_FIRST;

    /** 
     * Indicates no mouse buttons; used by {@link #getButton}. 
     * @since 1.4
     */
    public static final int NOBUTTON = 0;

    /** 
     * Indicates mouse button #1; used by {@link #getButton}.
     * @since 1.4
     */
    public static final int BUTTON1 = 1;

    /** 
     * Indicates mouse button #2; used by {@link #getButton}.
     * @since 1.4
     */
    public static final int BUTTON2 = 2;

    /** 
     * Indicates mouse button #3; used by {@link #getButton}.
     * @since 1.4
     */
    public static final int BUTTON3 = 3;

    /** 
     * The mouse event's x coordinate.
     * The x value is relative to the component that fired the event.
     *
     * @serial
     * @see #getX()
     */
     int x;

    /** 
     * The mouse event's y coordinate.
     * The y value is relative to the component that fired the event.
     *
     * @serial
     * @see #getY()
     */
     int y;

    /** 
     * Indicates the number of quick consecutive clicks of
     * a mouse button.
     * clickCount will be valid for only three mouse events :<BR>
     * <code>MOUSE_CLICKED</code>,
     * <code>MOUSE_PRESSED</code> and
     * <code>MOUSE_RELEASED</code>.
     * For the above, the <code>clickCount</code> will be at least 1. 
     * For all other events the count will be 0.
     *
     * @serial
     * @see #getClickCount().
     */
     int clickCount;

    /** 
     * Indicates which, if any, of the mouse buttons has changed state.
     *
     * The only legal values are the following constants:
     * <code>NOBUTTON</code>,
     * <code>BUTTON1</code>,
     * <code>BUTTON2</code> or
     * <code>BUTTON3</code>.
     * @serial
     * @see #getButton().
     */
     int button;

    /** 
     * A property used to indicate whether a Popup Menu
     * should appear  with a certain gestures.
     * If <code>popupTrigger</code> = <code>false</code>,
     * no popup menu should appear.  If it is <code>true</code>
     * then a popup menu should appear.
     *
     * @serial
     * @see java.awt.PopupMenu
     * @see #isPopupTrigger()
     */
     boolean popupTrigger;

    /*
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = -991214153494842848L;

    /** 
     * Constructs a <code>MouseEvent</code> object with the
     * specified source component,
     * type, modifiers, coordinates, and click count.
     * <p>Note that passing in an invalid <code>id</code> results in
     * unspecified behavior.
     *
     * @param source       the <code>Component</code> that originated the event
     * @param id           the integer that identifies the event
     * @param when         a long int that gives the time the event occurred
     * @param modifiers    the modifier keys down during event (e.g. shift, ctrl,
     *                     alt, meta)
     *                     Either extended _DOWN_MASK or old _MASK modifiers
     *                     should be used, but both models should not be mixed
     *                     in one event. Use of the extended modifiers is
     *                     preferred.
     * @param x            the horizontal x coordinate for the mouse location
     * @param y            the vertical y coordinate for the mouse location
     * @param clickCount   the number of mouse clicks associated with event
     * @param popupTrigger a boolean, true if this event is a trigger for a
     *                     popup menu 
     * @param button       which of the mouse buttons has changed state.
     *                      <code>NOBUTTON</code>,
     *                      <code>BUTTON1</code>,
     *                      <code>BUTTON2</code> or
     *                      <code>BUTTON3</code>.
     * @exception IllegalArgumentException if if an invalid <code>button</code> 
     *            value is passed in.
     * @since 1.4
     */
    public MouseEvent(Component source, int id, long when, int modifiers, int x,
        int y, int clickCount, boolean popupTrigger, int button)
    {
      super(null, 0, 0, 0);
    }

    /** 
     * Constructs a <code>MouseEvent</code> object with the
     * specified source component,
     * type, modifiers, coordinates, and click count.
     * <p>Note that passing in an invalid <code>id</code> results in
     * unspecified behavior.
     *
     * @param source       the <code>Component</code> that originated the event
     * @param id           the integer that identifies the event
     * @param when         a long int that gives the time the event occurred
     * @param modifiers    the modifier keys down during event (e.g. shift, ctrl,
     *                     alt, meta)
     *                     Either extended _DOWN_MASK or old _MASK modifiers
     *                     should be used, but both models should not be mixed
     *                     in one event. Use of the extended modifiers is
     *                     preferred.
     * @param x            the horizontal x coordinate for the mouse location
     * @param y            the vertical y coordinate for the mouse location
     * @param clickCount   the number of mouse clicks associated with event
     * @param popupTrigger a boolean, true if this event is a trigger for a
     *                     popup menu 
     */
    public MouseEvent(Component source, int id, long when, int modifiers, int x,
        int y, int clickCount, boolean popupTrigger)
    { super(null, 0, 0, 0); }

    /** 
     * Returns the horizontal x position of the event relative to the 
     * source component.
     *
     * @return x  an integer indicating horizontal position relative to
     *            the component
     */
    public int getX() { return 0; }

    /** 
     * Returns the vertical y position of the event relative to the
     * source component.
     *
     * @return y  an integer indicating vertical position relative to
     *            the component
     */
    public int getY() { return 0; }

    /** 
     * Returns the x,y position of the event relative to the source component.
     *
     * @return a <code>Point</code> object containing the x and y coordinates 
     *         relative to the source component 
     *
     */
    public Point getPoint() { return null; }

    /** 
     * Translates the event's coordinates to a new position
     * by adding specified <code>x</code> (horizontal) and <code>y</code>
     * (vertical) offsets.
     *
     * @param x the horizontal x value to add to the current x
     *          coordinate position
     * @param y the vertical y value to add to the current y
     *                coordinate position
     */
    public synchronized void translatePoint(int x, int y) { }

    /** 
     * Returns the number of mouse clicks associated with this event.
     *
     * @return integer value for the number of clicks
     */
    public int getClickCount() { return 0; }

    /** 
     * Returns which, if any, of the mouse buttons has changed state.
     *
     * @return one of the following constants:
     * <code>NOBUTTON</code>,
     * <code>BUTTON1</code>,
     * <code>BUTTON2</code> or
     * <code>BUTTON3</code>.
     * @since 1.4
     */
    public int getButton() { return 0; }

    /** 
     * Returns whether or not this mouse event is the popup menu
     * trigger event for the platform.
     * <p><b>Note</b>: Popup menus are triggered differently
     * on different systems. Therefore, <code>isPopupTrigger</code>
     * should be checked in both <code>mousePressed</code>
     * and <code>mouseReleased</code>
     * for proper cross-platform functionality.
     *
     * @return boolean, true if this event is the popup menu trigger
     *         for this platform
     */
    public boolean isPopupTrigger() { return false; }

    /** 
     * Returns a String describing the modifier keys and mouse buttons 
     * that were down during the event, such as "Shift", or "Ctrl+Shift".  
     * These strings can be localized by changing the awt.properties file.
     *
     * @param modifiers a modifier mask describing the modifier keys and 
     *                  mouse buttons that were down during the event
     * @return string   a text description of the combination of modifier
     *                  keys and mouse buttons that were down during the event
     * @since 1.4
     */
    public static String getMouseModifiersText(int modifiers) { return null; }

    /** 
     * Returns a parameter string identifying this event.
     * This method is useful for event-logging and for debugging.
     *
     * @return a string identifying the event and its attributes
     */
    public String paramString() { return null; }

    /** 
     * Sets new modifiers by the old ones.
     * @serial
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException
    { }
}
