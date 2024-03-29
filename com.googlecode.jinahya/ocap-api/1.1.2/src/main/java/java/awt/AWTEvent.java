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

import java.util.EventObject;
// import java.awt.peer.ComponentPeer;
// import java.awt.peer.LightweightPeer;

/** 
 * The root event class for all AWT events.
 * This class and its subclasses supercede the original
 * java.awt.Event class.
 * Subclasses of this root AWTEvent class defined outside of the
 * java.awt.event package should define event ID values greater than
 * the value defined by RESERVED_ID_MAX.
 * <p>
 * The event masks defined in this class are needed by Component subclasses
 * which are using Component.enableEvents() to select for event types not
 * selected by registered listeners. If a listener is registered on a
 * component, the appropriate event mask is already set internally by the
 * component.
 * <p>
 * The masks are also used to specify to which types of events an
 * AWTEventListener should listen. The masks are bitwise-ORed together
 * and passed to Toolkit.addAWTEventListener.
 * 
 * @see Component#enableEvents
 * @see Toolkit#addAWTEventListener
 *
 * @see java.awt.event.ActionEvent
 * @see java.awt.event.AdjustmentEvent
 * @see java.awt.event.ComponentEvent
 * @see java.awt.event.ContainerEvent
 * @see java.awt.event.FocusEvent
 * @see java.awt.event.InputMethodEvent
 * @see java.awt.event.InvocationEvent
 * @see java.awt.event.ItemEvent
<!-- PBP/PP -->
 * @see java.awt.event.KeyEvent
 * @see java.awt.event.MouseEvent
 * @see java.awt.event.MouseWheelEvent
 * @see java.awt.event.PaintEvent
 * @see java.awt.event.TextEvent
 * @see java.awt.event.WindowEvent
 *
 * @author Carl Quinn
 * @author Amy Fowler
 * @version 1.50 01/27/03
 * @since 1.1
 */
public abstract class AWTEvent extends EventObject
{
    /** 
     * The event's id.
     * @serial
     * @see #getID()
     * @see #AWTEvent
     */
    protected int id;

    /** 
     * Controls whether or not the event is sent back down to the peer once the
     * source has processed it - false means it's sent to the peer; true means
     * it's not. Semantic events always have a 'true' value since they were
     * generated by the peer in response to a low-level event.
     * @serial
     * @see #consume
     * @see #isConsumed
     */
    protected boolean consumed;

    /** 
     * The event mask for selecting component events.
     */
    public static final long COMPONENT_EVENT_MASK = 0x01;

    /** 
     * The event mask for selecting container events.
     */
    public static final long CONTAINER_EVENT_MASK = 0x02;

    /** 
     * The event mask for selecting focus events.
     */
    public static final long FOCUS_EVENT_MASK = 0x04;

    /** 
     * The event mask for selecting key events.
     */
    public static final long KEY_EVENT_MASK = 0x08;

    /** 
     * The event mask for selecting mouse events.
     */
    public static final long MOUSE_EVENT_MASK = 0x10;

    /** 
     * The event mask for selecting mouse motion events.
     */
    public static final long MOUSE_MOTION_EVENT_MASK = 0x20;

    /** 
     * The event mask for selecting window events.
     */
    public static final long WINDOW_EVENT_MASK = 0x40;

    /** 
     * The event mask for selecting action events.
     */
    public static final long ACTION_EVENT_MASK = 0x80;

    /** 
     * The event mask for selecting adjustment events.
     */
    public static final long ADJUSTMENT_EVENT_MASK = 0x100;

    /** 
     * The event mask for selecting item events.
     */
    public static final long ITEM_EVENT_MASK = 0x200;

    /** 
     * The event mask for selecting text events.
     */
    public static final long TEXT_EVENT_MASK = 0x400;

     /** 
      * The event mask for selecting input method events.
      */
     public static final long INPUT_METHOD_EVENT_MASK = 0x800;

    /** 
     * The event mask for selecting paint events.
     */
    public static final long PAINT_EVENT_MASK = 0x2000;

    /** 
     * The event mask for selecting invocation events.
     */
    public static final long INVOCATION_EVENT_MASK = 0x4000;

    // /** 
     // * The event mask for selecting hierarchy events.
     // */
    // public static final long HIERARCHY_EVENT_MASK = -1;
// 
    // /** 
     // * The event mask for selecting hierarchy bounds events.
     // */
    // public static final long HIERARCHY_BOUNDS_EVENT_MASK = -1;

    /** 
     * The event mask for selecting mouse wheel events.
     * @since 1.4
     */
    public static final long MOUSE_WHEEL_EVENT_MASK = 0x20000;

    /** 
     * The event mask for selecting window state events.
     * @since 1.4
     */
//     public static final long WINDOW_STATE_EVENT_MASK = 0x40000;

    /** 
     * The event mask for selecting window focus events.
     * @since 1.4
     */
    public static final long WINDOW_FOCUS_EVENT_MASK = 0x80000;

    /** 
     * The maximum value for reserved AWT event IDs. Programs defining
     * their own event IDs should use IDs greater than this value.
     */
    public static final int RESERVED_ID_MAX = 1999;

    /*
     * JDK 1.1 serialVersionUID
     */
    private static final long serialVersionUID = -1825314779160409405L;


  // PBP/PP
  // [6246050] (This is a proprietary field.)
  // private byte[] bdata;

    // /** 
     // * Constructs an AWTEvent object from the parameters of a 1.0-style event.
     // * @param event the old-style event
     // */
    // public AWTEvent(Event event) {super(null); }

    /** 
     * Constructs an AWTEvent object with the specified source object and type.
     * @param source the object where the event originated
     * @id the event type
     */
    public AWTEvent(Object source, int id) { super(null);}

    /** 
     * Retargets an event to a new source. This method is typically used to
     * retarget an event to a lightweight child Component of the original
     * heavyweight source.
     * <p>
     * This method is intended to be used only by event targeting subsystems,
     * such as client-defined KeyboardFocusManagers. It is not for general
     * client use.
     *
     * @param newSource the new Object to which the event should be dispatched
     */
  // public void setSource(Object newSource) { }

    /** 
     * Returns the event type.
     */
    public int getID() { return 0; }

    /** 
     * Returns a String representation of this object.
     */
    public String toString() { return null; }

    /** 
     * Returns a string representing the state of this <code>Event</code>.
     * This method is intended to be used only for debugging purposes, and the 
     * content and format of the returned string may vary between 
     * implementations. The returned string may be empty but may not be 
     * <code>null</code>.
     * 
     * @return  a string representation of this event
     */
    public String paramString() { return null; }

    /** 
     * Consumes this event, if this event can be consumed. Only low-level,
     * system events can be consumed
     */
    protected void consume() { }

    /** 
     * Returns whether this event has been consumed.
     */
    protected boolean isConsumed() { return false; }
}
