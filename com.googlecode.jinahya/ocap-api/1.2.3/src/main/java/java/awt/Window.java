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
// import javax.accessibility.*;

// import java.applet.Applet;
// import java.awt.peer.WindowPeer;
// import java.awt.image.BufferStrategy;
import java.util.Vector;
import java.util.Locale;
import java.util.EventListener;
import java.util.Set;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.OptionalDataException;
import java.awt.im.InputContext;
import java.util.ResourceBundle;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.beans.PropertyChangeListener;
// import sun.awt.DebugHelper;

// PBP/PP
/** 
 * A <code>Window</code> object is a top-level window with no borders and no
 * menubar.  
 * The default layout for a window is <code>BorderLayout</code>.

 * <!-- PBP/PP [4692065] -->
 * <!-- The following two paragraphs are borrowed from J2SE 1.5. -->
 * <a name="geometry">
 * <em>
 * Note: the location and size of top-level windows (including
 * <code>Window</code>s, <code>Frame</code>s, and <code>Dialog</code>s)
 * are under the control of the  window management system.
 * Calls to <code>setLocation</code>, <code>setSize</code>, and
 * <code>setBounds</code> are requests (not directives) which are
 * forwarded to the window management system.    In some cases the window
 * management system may ignore such requests, or modify the requested
 * geometry in order to place and size the <code>Window</code> 
 * <em>appropriately</em>.
 *
 * Due to the asynchronous nature of native event handling, the results
 * returned by <code>getBounds</code>, <code>getLocation</code>,
 * <code>getLocationOnScreen</code>, and <code>getSize</code> might not 
 * reflect the actual geometry of the Window on screen until the last
 * request has been processed.  During the processing of subsequent
 * requests these values might change accordingly while the window
 * management system fulfills the requests.
 * </em>
 * <p>
 * <!-- The following language is specific to PBP, but might be picked up
 * in J2SE 1.6.-->
 * <em>
 * An application may set the size and location of an
 * invisible <code>Window</code> arbitrarily,
 * but the window management system may subsequently change
 * its size and/or location if and when the <code>Window</code> is
 * made visible.  One or more <code>ComponentEvent</code>s will
 * be generated to indicate the new geometry.
 * </em>
 *
 * <p>
 * Windows are capable of generating the following WindowEvents:
 * WindowOpened, WindowClosed, WindowGainedFocus, WindowLostFocus.
 *
 * @version 	1.184, 01/28/03
 * @author 	Sami Shaio
 * @author 	Arthur van Hoff
 * @see WindowEvent
 * @see #addWindowListener
 * @see java.awt.BorderLayout
 * @since       JDK1.0
 */
public class Window extends Container
// public class Window extends Container implements Accessible
{
    /** 
     * This represents the warning message that is
     * to be displayed in a non secure window. ie :
     * a window that has a security manager installed for
     * which calling SecurityManager.checkTopLevelWindow()
     * is false.  This message can be displayed anywhere in
     * the window.
     *
     * @serial
     * @see #getWarningString
     */
     String warningString;

  // PBP/PP [6205182]

  // boolean syncLWRequests;

    /** 
     * An Integer value representing the Window State.
     *
     * @serial
     * @since 1.2
     * @see #show
     */
     int state;

    // /** 
     // * Unused. Maintained for serialization backward-compatibility.
     // *
     // * @serial
     // * @since 1.2
     // */
    // private FocusManager focusMgr;

    /** 
     * Indicates whether this Window can become the focused Window.
     *
     * @serial
     * @see #getFocusableWindowState
     * @see #setFocusableWindowState
     * @since 1.4
     */
    private boolean focusableWindowState;

    /** 
     * The window serialized data version.
     *
     * @serial
     */
    private int windowSerializedDataVersion;

    /*
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = 4497834738069338734L;

//    /** 
//     * Constructs a new invisible window with the specified
//     * <code>Frame</code> as its owner. The Window will not be focusable 
//     * unless its owner is showing on the screen.
//     * <p>
//     * If there is a security manager, this method first calls 
//     * the security manager's <code>checkTopLevelWindow</code> 
//     * method with <code>this</code> 
//     * as its argument to determine whether or not the window 
//     * must be displayed with a warning banner. 
//     * 
//     * @param owner the <code>Frame</code> to act as owner
//     * @exception IllegalArgumentException if the <code>owner</code>'s
//     *    <code>GraphicsConfiguration</code> is not from a screen device
//     * @exception java.lang.IllegalArgumentException if 
//     *    <code>owner</code> is <code>null</code>; this exception
//     *    is always thrown when <code>GraphicsEnvironment.isHeadless</code>
//     *    returns true
//     * @see java.awt.GraphicsEnvironment#isHeadless
//     * @see java.lang.SecurityManager#checkTopLevelWindow
//     * @see #isShowing
//     */
 Window(Frame owner) { }

//    /** 
//     * Constructs a new invisible window with the specified
//     * <code>Window</code> as its owner. The Window will not be focusable 
//     * unless its nearest owning Frame or Dialog is showing on the screen.
//     * <p>
//     * If there is a security manager, this method first calls 
//     * the security manager's <code>checkTopLevelWindow</code> 
//     * method with <code>this</code> 
//     * as its argument to determine whether or not the window 
//     * must be displayed with a warning banner. 
//     * 
//     * @param     owner   the <code>Window</code> to act as owner
//     * @exception IllegalArgumentException if the <code>owner</code>'s
//     *    <code>GraphicsConfiguration</code> is not from a screen device
//     * @exception java.lang.IllegalArgumentException if <code>owner</code> 
//     *            is <code>null</code>.  This exception is always thrown
//     *            when GraphicsEnvironment.isHeadless() returns true.
//     * @see       java.awt.GraphicsEnvironment#isHeadless
//     * @see       java.lang.SecurityManager#checkTopLevelWindow
//     * @see       #isShowing
//     * @since     1.2
//     */
//    public Window(Window owner) { }

//    /** 
//     * Constructs a new invisible window with the specified
//     * window as its owner and a 
//     * <code>GraphicsConfiguration</code> of a screen device. The Window will
//     * not be focusable unless its nearest owning Frame or Dialog is showing on
//     * the screen.
//     * <p>
//     * If there is a security manager, this method first calls 
//     * the security manager's <code>checkTopLevelWindow</code> 
//     * method with <code>this</code> 
//     * as its argument to determine whether or not the window 
//     * must be displayed with a warning banner.
//     *
//     * @param     owner   the window to act as owner
//     * @param gc the <code>GraphicsConfiguration</code>
//     *   of the target screen device; if <code>gc</code> is 
//     *   <code>null</code>, the system default 
//     *   <code>GraphicsConfiguration</code> is assumed
//     * @throws IllegalArgumentException if
//     *            <code>owner</code> is <code>null</code>
//     * @throws IllegalArgumentException if <code>gc</code> is not from
//     *   a screen device; this exception is always thrown when
//     *   <code>GraphicsEnvironment.isHeadless</code> returns
//     *   <code>true</code>
//     * @see       java.awt.GraphicsEnvironment#isHeadless
//     * @see       java.lang.SecurityManager#checkTopLevelWindow
//     * @see       GraphicsConfiguration#getBounds
//     * @see       #isShowing
//     * @since     1.3
//     */
//    public Window(Window owner, GraphicsConfiguration gc) { }

    // /** 
     // * Disposes of the input methods and context, and removes the WeakReference
     // * which formerly pointed to this Window from the parent's owned Window
     // * list.
     // */
    // protected void finalize() throws Throwable { }
// 
    // /** 
     // * Makes this Window displayable by creating the connection to its
     // * native screen resource.  
     // * This method is called internally by the toolkit and should
     // * not be called directly by programs.
     // * @see Component#isDisplayable
     // * @see Container#removeNotify
     // * @since JDK1.0
     // */
    // public void addNotify() { }

    /** 
     * Causes this Window to be sized to fit the preferred size
     * and layouts of its subcomponents.  If the window and/or its owner
     * are not yet displayable, both are made displayable before
     * calculating the preferred size.  The Window will be validated
     * after the preferredSize is calculated.
     * @see Component#isDisplayable
     */
    public void pack() { }

    /** 
     * Makes the Window visible. If the Window and/or its owner
     * are not yet displayable, both are made displayable.  The 
     * Window will be validated prior to being made visible.  
     * If the Window is already visible, this will bring the Window 
     * to the front.
     * @see       Component#isDisplayable
     * @see       #toFront
     * @see       Component#setVisible
     */
    public void show() { }

//    /** 
//     * Hide this Window, its subcomponents, and all of its owned children. 
//     * The Window and its subcomponents can be made visible again
//     * with a call to <code>show</code>. 
//     * </p>
//     * @see #show
//     * @see #dispose
//     */
//    public void hide() { }

    /** 
     * Releases all of the native screen resources used by this
     * <code>Window</code>, its subcomponents, and all of its owned
     * children. That is, the resources for these <code>Component</code>s
     * will be destroyed, any memory they consume will be returned to the
     * OS, and they will be marked as undisplayable.
     * <p>
     * The <code>Window</code> and its subcomponents can be made displayable
     * again by rebuilding the native resources with a subsequent call to
     * <code>pack</code> or <code>show</code>. The states of the recreated
     * <code>Window</code> and its subcomponents will be identical to the
     * states of these objects at the point where the <code>Window</code>
     * was disposed (not accounting for additional modifications between
     * those actions).
     * <p>
     * <b>Note</b>: When the last displayable window
     * within the Java virtual machine (VM) is disposed of, the VM may
     * terminate.  See <a href="doc-files/AWTThreadIssues.html">
     * AWT Threading Issues</a> for more information.
     * @see Component#isDisplayable
     * @see #pack
     * @see #show
     */
    public void dispose() { }

    // PBP/PP 6214654
    /** 
     * If this Window is visible, brings this Window to the front and may make
     * it the focused Window.
     * <p>
     * Places this Window at the top of the stacking order and shows it in
     * front of any other Windows in this VM. No action will take place if this
     * Window is not visible. Some platforms do not allow Windows which own
     * other Windows to appear on top of those owned Windows. Some platforms
     * may not permit this VM to place its Windows above windows of native
     * applications, or Windows of other VMs. This permission may depend on
     * whether a Window in this VM is already focused. Every attempt will be
     * made to move this Window as high as possible in the stacking order;
     * however, developers should not assume that this method will move this
     * Window above all other windows in every situation.
     * <p>
     * Because of variations in native windowing systems, no guarantees about
     * changes to the focused and active Windows can be made. Developers must
     * never assume that this Window is the focused or active Window until this
     * Window receives a WINDOW_GAINED_FOCUS or WINDOW_ACTIVATED event. On
     * platforms where the top-most window is the focused window, this method
     * will <b>probably</b> focus this Window, if it is not already focused. On
     * platforms where the stacking order does not typically affect the focused
     * window, this method will <b>probably</b> leave the focused and active
     * Windows unchanged.
     * <p>
     * If this method causes this Window to be focused, and this Window is a
     * Frame it will also become activated. If this Window is
     * focused, but it is not a Frame then the first Frame that is an owner of this Window will be activated.
     *
     * @see       #toBack
     */
    public void toFront() { }

    /** 
     * If this Window is visible, sends this Window to the back and may cause
     * it to lose focus or activation if it is the focused or active Window.
     * <p>
     * Places this Window at the bottom of the stacking order and shows it
     * behind any other Windows in this VM. No action will take place is this
     * Window is not visible. Some platforms do not allow Windows which are
     * owned by other Windows to appear below their owners. Every attempt will
     * be made to move this Window as low as possible in the stacking order;
     * however, developers should not assume that this method will move this
     * Window below all other windows in every situation.
     * <p>
     * Because of variations in native windowing systems, no guarantees about
     * changes to the focused and active Windows can be made. Developers must
     * never assume that this Window is no longer the focused or active Window
     * until this Window receives a WINDOW_LOST_FOCUS or WINDOW_DEACTIVATED
     * event. On platforms where the top-most window is the focused window,
     * this method will <b>probably</b> cause this Window to lose focus. In
     * that case, the next highest, focusable Window in this VM will receive
     * focus. On platforms where the stacking order does not typically affect
     * the focused window, this method will <b>probably</b> leave the focused
     * and active Windows unchanged.
     *
     * @see       #toFront
     */
    public void toBack() { }

    /** 
     * Returns the toolkit of this frame.
     * @return    the toolkit of this window.
     * @see       Toolkit
     * @see       Toolkit#getDefaultToolkit
     * @see       Component#getToolkit
     */
    public Toolkit getToolkit() { return null; }

    /** 
     * Gets the warning string that is displayed with this window. 
     * If this window is insecure, the warning string is displayed 
     * somewhere in the visible area of the window. A window is 
     * insecure if there is a security manager, and the security 
     * manager's <code>checkTopLevelWindow</code> method returns 
     * <code>false</code> when this window is passed to it as an
     * argument.
     * <p>
     * If the window is secure, then <code>getWarningString</code>
     * returns <code>null</code>. If the window is insecure, this
     * method checks for the system property 
     * <code>awt.appletWarning</code> 
     * and returns the string value of that property.
     * @return    the warning string for this window.
     * @see       java.lang.SecurityManager#checkTopLevelWindow(java.lang.Object)
     */
    public final String getWarningString() { return null; }

    /** 
     * Gets the <code>Locale</code> object that is associated 
     * with this window, if the locale has been set.
     * If no locale has been set, then the default locale 
     * is returned.
     * @return    the locale that is set for this window.
     * @see       java.util.Locale
     * @since     JDK1.1
     */
    public Locale getLocale() { return null; }

     /** 
      * Gets the input context for this window. A window always has an input context,
      * which is shared by subcomponents unless they create and set their own.
      * @see Component#getInputContext
      * @since 1.2
      */
     public InputContext getInputContext() { return null; }

    /** 
     * Set the cursor image to a specified cursor.
     * @param     cursor One of the constants defined
     *            by the <code>Cursor</code> class. If this parameter is null
     *            then the cursor for this window will be set to the type
     *            Cursor.DEFAULT_CURSOR.
     * @see       Component#getCursor
     * @see       Cursor
     * @since     JDK1.1
     */
    public void setCursor(Cursor cursor) { }

    // PBP/PP 5029995 -- It was agreed that this addition to PBP was not
    //                   necessary
    /** 
     * Returns the owner of this window.
     * @since 1.2
     */
    // public Window getOwner() { return null; }

    // /** 
     // * Return an array containing all the windows this
     // * window currently owns.
     // * @since 1.2
     // */
    // public Window[] getOwnedWindows() { }

    /** 
     * Adds the specified window listener to receive window events from
     * this window.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param 	l the window listener
     * @see #removeWindowListener
     */
    public synchronized void addWindowListener(WindowListener l) { }

    // /** 
     // * Adds the specified window state listener to receive window
     // * events from this window.  If <code>l</code> is </code>null</code>,
     // * no exception is thrown and no action is performed.
     // *
     // * @param   l the window state listener
     // * @see #removeWindowStateListener
     // * @see #getWindowStateListeners
     // * @since 1.4
     // */
    // public synchronized void addWindowStateListener(WindowStateListener l) { }

    /** 
     * Adds the specified window focus listener to receive window events
     * from this window.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param   l the window focus listener
     * @see #removeWindowFocusListener
     * @see #getWindowFocusListeners
     */
    public synchronized void addWindowFocusListener(WindowFocusListener l) { }

    /** 
     * Removes the specified window listener so that it no longer
     * receives window events from this window.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param 	l the window listener
     * @see #addWindowListener
     */
    public synchronized void removeWindowListener(WindowListener l) { }

    // /** 
     // * Removes the specified window state listener so that it no
     // * longer receives window events from this window.  If
     // * <code>l</code> is <code>null</code>, no exception is thrown and
     // * no action is performed.
     // *
     // * @param   l the window state listener
     // * @see #addWindowStateListener
     // * @see #getWindowStateListeners
     // * @since 1.4
     // */
    // public synchronized void removeWindowStateListener(WindowStateListener l)
    // { }

    /** 
     * Removes the specified window focus listener so that it no longer
     * receives window events from this window.
     * If l is null, no exception is thrown and no action is performed.
     *
     * @param   l the window focus listener
     * @see #addWindowFocusListener
     * @see #getWindowFocusListeners
     */
    public synchronized void removeWindowFocusListener(WindowFocusListener l)
    { }

    /** 
     * Returns an array of all the window listeners
     * registered on this window.
     *
     * @return all of this window's <code>WindowListener</code>s
     *         or an empty array if no window
     *         listeners are currently registered
     *
     * @see #addWindowListener
     * @see #removeWindowListener
     * @since 1.4
     */
    public synchronized WindowListener[] getWindowListeners() {
      return null;
    }

    /** 
     * Returns an array of all the window focus listeners
     * registered on this window.
     *
     * @return all of this window's <code>WindowFocusListener</code>s
     *         or an empty array if no window focus
     *         listeners are currently registered
     *
     * @see #addWindowFocusListener
     * @see #removeWindowFocusListener
     * @since 1.4
     */
    public synchronized WindowFocusListener[] getWindowFocusListeners() {return null;  }

    /** 
     * Returns an array of all the window state listeners
     * registered on this window.
     *
     * @return all of this window's <code>WindowStateListener</code>s
     *         or an empty array if no window state
     *         listeners are currently registered
     *
     * @since 1.4
     */
//     public synchronized WindowStateListener[] getWindowStateListeners() { return null; }

    // /** 
     // * Returns an array of all the objects currently registered
     // * as <code><em>Foo</em>Listener</code>s
     // * upon this <code>Window</code>.
     // * <code><em>Foo</em>Listener</code>s are registered using the
     // * <code>add<em>Foo</em>Listener</code> method.
     // *
     // * <p>
     // *
     // * You can specify the <code>listenerType</code> argument
     // * with a class literal, such as
     // * <code><em>Foo</em>Listener.class</code>.
     // * For example, you can query a
     // * <code>Window</code> <code>w</code>
     // * for its window listeners with the following code:
     // *
     // * <pre>WindowListener[] wls = (WindowListener[])(w.getListeners(WindowListener.class));</pre>
     // *
     // * If no such listeners exist, this method returns an empty array.
     // *
     // * @param listenerType the type of listeners requested; this parameter
     // *          should specify an interface that descends from
     // *          <code>java.util.EventListener</code>
     // * @return an array of all objects registered as
     // *          <code><em>Foo</em>Listener</code>s on this window,
     // *          or an empty array if no such
     // *          listeners have been added
     // * @exception ClassCastException if <code>listenerType</code>
     // *          doesn't specify a class or interface that implements
     // *          <code>java.util.EventListener</code>
     // *
     // * @see #getWindowListeners
     // * @since 1.3
     // */
    // public EventListener[] getListeners(Class listenerType) { }

    /** 
     * Processes events on this window. If the event is an
     * <code>WindowEvent</code>, it invokes the
     * <code>processWindowEvent</code> method, else it invokes its
     * superclass's <code>processEvent</code>.
     * <p>Note that if the event parameter is <code>null</code>
     * the behavior is unspecified and may result in an
     * exception.
     *
     * @param e the event
     */
    protected void processEvent(AWTEvent e) { }

    /** 
     * Processes window events occurring on this window by
     * dispatching them to any registered WindowListener objects.
     * NOTE: This method will not be called unless window events
     * are enabled for this component; this happens when one of the
     * following occurs:
     * <ul>
     * <li>A WindowListener object is registered via
     *     <code>addWindowListener</code>
     * <li>Window events are enabled via <code>enableEvents</code>
     * </ul>
     * <p>Note that if the event parameter is <code>null</code>
     * the behavior is unspecified and may result in an
     * exception.
     *
     * @param e the window event
     * @see Component#enableEvents
     */
    protected void processWindowEvent(WindowEvent e) { }

    /** 
     * Processes window focus event occuring on this window by
     * dispatching them to any registered WindowFocusListener objects.
     * NOTE: this method will not be called unless window focus events
     * are enabled for this window. This happens when one of the
     * following occurs:
     * <ul>
     * <li>a WindowFocusListener is registered via
     *     <code>addWindowFocusListener</code>
     * <li>Window focus events are enabled via <code>enableEvents</code>
     * </ul>
     * <p>Note that if the event parameter is <code>null</code>
     * the behavior is unspecified and may result in an
     * exception.
     *
     * @param e the window focus event
     * @see Component#enableEvents
     */
    protected void processWindowFocusEvent(WindowEvent e) { }

    // /** 
     // * Processes window state event occuring on this window by
     // * dispatching them to any registered <code>WindowStateListener</code>
     // * objects.
     // * NOTE: this method will not be called unless window state events
     // * are enabled for this window.  This happens when one of the
     // * following occurs:
     // * <ul>
     // * <li>a <code>WindowStateListener</code> is registered via
     // *    <code>addWindowStateListener</code>
     // * <li>window state events are enabled via <code>enableEvents</code>
     // * </ul>
     // * <p>Note that if the event parameter is <code>null</code>
     // * the behavior is unspecified and may result in an
     // * exception.
     // *
     // * @param e the window state event
     // * @see java.awt.Component#enableEvents
     // * @since 1.4
     // */
    // protected void processWindowStateEvent(WindowEvent e) { }

    /** 
     * Returns the child Component of this Window that has focus if this Window
     * is focused; returns null otherwise.
     *
     * @return the child Component with focus, or null if this Window is not
     *         focused
     * @see #getMostRecentFocusOwner
     * @see #isFocused
     */
    public Component getFocusOwner() { return null; }

    /** 
     * Returns the child Component of this Window that will receive the focus
     * when this Window is focused. If this Window is currently focused, this
     * method returns the same Component as <code>getFocusOwner()</code>. If
     * this Window is not focused, then the child Component that most recently 
     * requested focus will be returned. If no child Component has ever
     * requested focus, and this is a focusable Window, then this Window's
     * initial focusable Component is returned. If no child Component has ever
     * requested focus, and this is a non-focusable Window, null is returned.
     *
     * @return the child Component that will receive focus when this Window is
     *         focused
     * @see #getFocusOwner
     * @see #isFocused
     * @see #isFocusableWindow
     * @since 1.4
     */
    public Component getMostRecentFocusOwner() { return null; }


    // PBP/PP 6214654
     /** 
      * Returns whether this Window is active. Only a Frame may be
      * active. The native windowing system may denote the active Window or its
      * children with special decorations, such as a highlighted title bar. The
      * active Window is always either the focused Window, or the first Frame that is an owner of the focused Window.
      *
      * @return whether this is the active Window.
      * @see #isFocused
      * @since 1.4
      */
     public boolean isActive() { return false; }

    // PBP/PP 6214654
    /** 
     * Returns whether this Window is focused. If there exists a focus owner,
     * the focused Window is the Window that is, or contains, that focus owner.
     * If there is no focus owner, then no Window is focused.
     * <p>
     * If the focused Window is a Frame it is also the active
     * Window. Otherwise, the active Window is the first Frame that
     * is an owner of the focused Window.
     *
     * @return whether this is the focused Window.
     * @since 1.4
     */
    public boolean isFocused() { return false; }

    /** 
     * Gets a focus traversal key for this Window. (See <code>
     * setFocusTraversalKeys</code> for a full description of each key.)
     * <p>
     * If the traversal key has not been explicitly set for this Window,
     * then this Window's parent's traversal key is returned. If the
     * traversal key has not been explicitly set for any of this Window's
     * ancestors, then the current KeyboardFocusManager's default traversal key
     * is returned.
     *
     * @param id one of KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
     *         KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
     *         KeyboardFocusManager.UP_CYCLE_TRAVERSAL_KEYS, or
     *         KeyboardFocusManager.DOWN_CYCLE_TRAVERSAL_KEYS
     * @return the AWTKeyStroke for the specified key
     * @see Container#setFocusTraversalKeys
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
     * Does nothing because Windows must always be roots of a focus traversal
     * cycle. The passed-in value is ignored.
     *
     * @param focusCycleRoot this value is ignored
     * @see #isFocusCycleRoot
     * @see Container#setFocusTraversalPolicy
     * @see Container#getFocusTraversalPolicy
     * @since 1.4
     */
    public final void setFocusCycleRoot(boolean focusCycleRoot) { }

    /** 
     * Always returns <code>true</code> because all Windows must be roots of a
     * focus traversal cycle.
     *
     * @return <code>true</code>
     * @see #setFocusCycleRoot
     * @see Container#setFocusTraversalPolicy
     * @see Container#getFocusTraversalPolicy
     * @since 1.4
     */
    public final boolean isFocusCycleRoot() { return false; }

    /** 
     * Always returns <code>null</code> because Windows have no ancestors; they
     * represent the top of the Component hierarchy.
     *
     * @return <code>null</code>
     * @see Container#isFocusCycleRoot()
     * @since 1.4
     */
    public final Container getFocusCycleRootAncestor() { return null; }

    // PBP/PP 6214654
    /** 
     * Returns whether this Window can become the focused Window, that is,
     * whether this Window or any of its subcomponents can become the focus
     * owner. For a Frame to be focusable, its focusable Window state
     * must be set to <code>true</code>. For a Window which is not a Frame to be focusable, its focusable Window state must be set to
     * <code>true</code>, its nearest owning Frame must be
     * showing on the screen, and it must contain at least one Component in
     * its focus traversal cycle. If any of these conditions is not met, then
     * neither this Window nor any of its subcomponents can become the focus
     * owner.
     *
     * @return <code>true</code> if this Window can be the focused Window;
     *         <code>false</code> otherwise
     * @see #getFocusableWindowState
     * @see #setFocusableWindowState
     * @see #isShowing
     * @see Component#isFocusable
     * @since 1.4
     */
    public final boolean isFocusableWindow() { return false; }

    /** 
     * Returns whether this Window can become the focused Window if it meets
     * the other requirements outlined in <code>isFocusableWindow</code>. If
     * this method returns <code>false</code>, then
     * <code>isFocusableWindow</code> will return <code>false</code> as well.
     * If this method returns <code>true</code>, then
     * <code>isFocusableWindow</code> may return <code>true</code> or
     * <code>false</code> depending upon the other requirements which must be
     * met in order for a Window to be focusable.
     * <p>
     * By default, all Windows have a focusable Window state of
     * <code>true</code>.
     *
     * @return whether this Window can be the focused Window
     * @see #isFocusableWindow
     * @see #setFocusableWindowState
     * @see #isShowing
     * @see Component#setFocusable
     * @since 1.4
     */
    public boolean getFocusableWindowState() { return false; }

    /** 
     * Sets whether this Window can become the focused Window if it meets
     * the other requirements outlined in <code>isFocusableWindow</code>. If
     * this Window's focusable Window state is set to <code>false</code>, then
     * <code>isFocusableWindow</code> will return <code>false</code>. If this
     * Window's focusable Window state is set to <code>true</code>, then
     * <code>isFocusableWindow</code> may return <code>true</code> or
     * <code>false</code> depending upon the other requirements which must be
     * met in order for a Window to be focusable.
     * <p>
     * Setting a Window's focusability state to <code>false</code> is the
     * standard mechanism for an application to identify to the AWT a Window
     * which will be used as a floating palette or toolbar, and thus should be
     * a non-focusable Window.
     *
     * @param focusableWindowState whether this Window can be the focused
     *        Window
     * @see #isFocusableWindow
     * @see #getFocusableWindowState
     * @see #isShowing
     * @see Component#setFocusable
     * @since 1.4
     */
    public void setFocusableWindowState(boolean focusableWindowState) { }

    // /** 
     // * Adds a PropertyChangeListener to the listener list. The listener is
     // * registered for all bound properties of this class, including the
     // * following:
     // * <ul>
     // *    <li>this Window's font ("font")</li>
     // *    <li>this Window's background color ("background")</li>
     // *    <li>this Window's foreground color ("foreground")</li>
     // *    <li>this Window's focusability ("focusable")</li>
     // *    <li>this Window's focus traversal keys enabled state
     // *        ("focusTraversalKeysEnabled")</li>
     // *    <li>this Window's Set of FORWARD_TRAVERSAL_KEYS
     // *        ("forwardFocusTraversalKeys")</li>
     // *    <li>this Window's Set of BACKWARD_TRAVERSAL_KEYS
     // *        ("backwardFocusTraversalKeys")</li>
     // *    <li>this Window's Set of UP_CYCLE_TRAVERSAL_KEYS
     // *        ("upCycleFocusTraversalKeys")</li>
     // *    <li>this Window's Set of DOWN_CYCLE_TRAVERSAL_KEYS
     // *        ("downCycleFocusTraversalKeys")</li>
     // *    <li>this Window's focus traversal policy ("focusTraversalPolicy")
     // *        </li>
     // *    <li>this Window's focusable Window state ("focusableWindowState")
     // *        </li>
     // * </ul>
     // * Note that if this Window is inheriting a bound property, then no
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

    // /** 
     // * Adds a PropertyChangeListener to the listener list for a specific
     // * property. The specified property may be user-defined, or one of the
     // * following:
     // * <ul>
     // *    <li>this Window's font ("font")</li>
     // *    <li>this Window's background color ("background")</li>
     // *    <li>this Window's foreground color ("foreground")</li>
     // *    <li>this Window's focusability ("focusable")</li>
     // *    <li>this Window's focus traversal keys enabled state
     // *        ("focusTraversalKeysEnabled")</li>
     // *    <li>this Window's Set of FORWARD_TRAVERSAL_KEYS
     // *        ("forwardFocusTraversalKeys")</li>
     // *    <li>this Window's Set of BACKWARD_TRAVERSAL_KEYS
     // *        ("backwardFocusTraversalKeys")</li>
     // *    <li>this Window's Set of UP_CYCLE_TRAVERSAL_KEYS
     // *        ("upCycleFocusTraversalKeys")</li>
     // *    <li>this Window's Set of DOWN_CYCLE_TRAVERSAL_KEYS
     // *        ("downCycleFocusTraversalKeys")</li>
     // *    <li>this Window's focus traversal policy ("focusTraversalPolicy")
     // *        </li>
     // *    <li>this Window's focusable Window state ("focusableWindowState")
     // *        </li>
     // * </ul>
     // * Note that if this Window is inheriting a bound property, then no
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

//    /** 
//     * @deprecated As of JDK version 1.1
//     * replaced by <code>dispatchEvent(AWTEvent)</code>.
//     */
//    public boolean postEvent(Event e) { return false; }

    /** 
     * Checks if this Window is showing on screen.
     * @see Component#setVisible
     */
    public boolean isShowing() { return false; }

    // /** 
     // * Applies the ResourceBundle's ComponentOrientation
     // * to this Window and all components contained within it.
     // *
     // * @see ComponentOrientation
     // * @since 1.2
     // *
     // * @deprecated As of J2SE 1.4, replaced by
     // * {@link Component#applyComponentOrientation Component.applyComponentOrientation}.
     // */
    // public void applyResourceBundle(ResourceBundle rb) { }

    // /** 
     // * Loads the ResourceBundle with the given name using the default locale
     // * and applies its ComponentOrientation
     // * to this Window and all components contained within it.
     // *
     // * @see ComponentOrientation
     // * @since 1.2
     // *
     // * @deprecated As of J2SE 1.4, replaced by
     // * {@link Component#applyComponentOrientation Component.applyComponentOrientation}.
     // */
    // public void applyResourceBundle(String rbName) { }

    // /** 
     // * Gets the AccessibleContext associated with this Window. 
     // * For windows, the AccessibleContext takes the form of an 
     // * AccessibleAWTWindow. 
     // * A new AccessibleAWTWindow instance is created if necessary.
     // *
     // * @return an AccessibleAWTWindow that serves as the 
     // *         AccessibleContext of this Window
     // */
    // public AccessibleContext getAccessibleContext() { }

    /** 
     * This method returns the GraphicsConfiguration used by this Window.
     */
    public GraphicsConfiguration getGraphicsConfiguration() { return null; }

    // /** 
     // * Sets the location of the window relative to the specified
     // * component. If the component is not currently showing,
     // * or <code>c</code> is <code>null</code>, the 
     // * window is centered on the screen.  If the bottom of the
     // * component is offscreen, the window is placed to the
     // * side of the <code>Component</code> that is closest
     // * to the center of the screen.  So if the 
     // * <code>Component</code> is on the right part of the
     // * screen, the <code>Window</code> is placed to its left,
     // * and visa versa.
     // *
     // * @param c  the component in relation to which the window's location
     // *           is determined
     // * @since 1.4
     // */
    // public void setLocationRelativeTo(Component c) { }
// 
     /** 
      * Creates a new strategy for multi-buffering on this component.
      * Multi-buffering is useful for rendering performance.  This method
      * attempts to create the best strategy available with the number of
      * buffers supplied.  It will always create a <code>BufferStrategy</code>
      * with that number of buffers.
      * A page-flipping strategy is attempted first, then a blitting strategy
      * using accelerated buffers.  Finally, an unaccelerated blitting
      * strategy is used.
      * <p>
      * Each time this method is called,
      * the existing buffer strategy for this component is discarded.
      * @param numBuffers number of buffers to create
      * @exception IllegalArgumentException if numBuffers is less than 1.
      * @exception IllegalStateException if the component is not displayable
      * @see #isDisplayable
      * @see #getBufferStrategy
      * @since 1.4
      */
    //     public void createBufferStrategy(int numBuffers) { }
 
//      /** 
//       * Creates a new strategy for multi-buffering on this component with the
//       * required buffer capabilities.  This is useful, for example, if only
//       * accelerated memory or page flipping is desired (as specified by the
//       * buffer capabilities).
//       * <p>
//       * Each time this method
//       * is called, the existing buffer strategy for this component is discarded.
//       * @param numBuffers number of buffers to create, including the front buffer
//       * @param caps the required capabilities for creating the buffer strategy;
//       * cannot be <code>null</code>
//       * @exception AWTException if the capabilities supplied could not be
//       * supported or met; this may happen, for example, if there is not enough
//       * accelerated memory currently available, or if page flipping is specified
//       * but not possible.
//       * @exception IllegalArgumentException if numBuffers is less than 1, or if
//       * caps is <code>null</code>
//       * @see #getBufferStrategy
//       * @since 1.4
//       */
//      public void createBufferStrategy(int numBuffers, BufferCapabilities caps)
//          throws AWTException
//      { }
 
//      /** 
//       * @return the buffer strategy used by this component
//       * @see #createBufferStrategy
//       * @since 1.4
//       */
//      public BufferStrategy getBufferStrategy() { return null; }
// 
     /** 
      * Reads the <code>ObjectInputStream</code> and an optional
      * list of listeners to receive various events fired by
      * the component; also reads a list of
      * (possibly <code>null</code>) child windows.
      * Unrecognized keys or values will be ignored.
      *
      * @param s the <code>ObjectInputStream</code> to read
      * @exception HeadlessException if
      *   <code>GraphicsEnvironment.isHeadless</code> returns
      *   <code>true</code>
      * @see java.awt.GraphicsEnvironment#isHeadless
      * @see #writeObject
      */
     private void readObject(ObjectInputStream s)
         throws ClassNotFoundException, IOException, HeadlessException
     { }
// 
     /** 
      * Writes default serializable fields to stream.  Writes
      * a list of serializable <code>WindowListener</code>s and
      * <code>WindowFocusListener</code>s as optional data.
      * Writes a list of child windows as optional data.
      *
      * @param s the <code>ObjectOutputStream</code> to write
      * @serialData <code>null</code> terminated sequence of
      *    0 or more pairs; the pair consists of a <code>String</code>
      *    and and <code>Object</code>; the <code>String</code>
      *    indicates the type of object and is one of the following:
      *    <code>windowListenerK</code> indicating a
      *      <code>WindowListener</code> object;
      *    <code>windowFocusWindowK</code> indicating a
      *      <code>WindowFocusListener</code> object;
      *    <code>ownedWindowK</code> indicating a child
      *      <code>Window</code> object
      *
      * @see AWTEventMulticaster#save(java.io.ObjectOutputStream, java.lang.String, java.util.EventListener)
      * @see Component#windowListenerK
      * @see Component#windowFocusListenerK
      * @see Component#ownedWindowK
      * @see #readObject(ObjectInputStream)
      */
     private void writeObject(ObjectOutputStream s) throws IOException { }
// 
    // /** 
     // * This class implements accessibility support for the 
     // * <code>Window</code> class.  It provides an implementation of the 
     // * Java Accessibility API appropriate to window user-interface elements.
     // */
    // protected class AccessibleAWTWindow extends Container.AccessibleAWTContainer
    // {
// 
        // protected AccessibleAWTWindow() { }
// 
        // /** 
         // * Get the role of this object.
         // *
         // * @return an instance of AccessibleRole describing the role of the 
         // * object
         // * @see javax.accessibility.AccessibleRole
         // */
        // public AccessibleRole getAccessibleRole() { }
// 
        // /** 
         // * Get the state of this object.
         // *
         // * @return an instance of AccessibleStateSet containing the current 
         // * state set of the object
         // * @see javax.accessibility.AccessibleState
         // */
        // public AccessibleStateSet getAccessibleStateSet() { }
    // }
}
