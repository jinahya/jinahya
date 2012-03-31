package org.dvb.application.inner;

import org.havi.ui.HActionable;

/**
 * Represents various features which are common to the top level Container
 * of applications whether running stand-alone or embedded in a DVB-HTML
 * application. All integer constants used in this class are those defined
 * in the class org.havi.ui.HScene.
 */

public interface DVBScene
{    
    /**
     * Add a listener to receive any java.awt.event.WindowEvents sent
     * from this {@link org.dvb.application.inner.DVBScene DVBScene}. If the listener
     * has already been added further calls will add further
     * references to the listener, which will then receive multiple
     * copies of a single event.
     *
     * @param wl The java.awt.event.WindowListener to be notified of
     * any java.awt.event.WindowEvents. 
     */
    public void addWindowListener(java.awt.event.WindowListener wl);

    /**
     * Enables or disables all short cuts that are currently set on
     * the Scene. To enable or disable a single shortcut use {@link
     * org.dvb.application.inner.DVBScene#addShortcut addShortcut} or {@link
     * org.dvb.application.inner.DVBScene#removeShortcut removeShortcut}. 
     * <p> 
     * Note {@link org.dvb.application.inner.DVBScene#enableShortcuts
     * enableShortcuts(false)} does not remove existing added {@link
     * org.dvb.application.inner.DVBScene DVBScene} shortcuts - they are merely disabled
     * and may be subsequently re-enabled with {@link
     * org.dvb.application.inner.DVBScene#enableShortcuts enableShortcuts(true)}.
     * 
     * @param enable a value of true indicates all shortcuts are to be
     * enabled, and a value of false indicates all shortcuts are to be
     * disabled. 
     */
    public void enableShortcuts(boolean enable);

    /**
     * Returns all keycodes added in the DVBScene as shortcuts.
     *
     * @return all keycodes added in the DVBScene as shortcuts, there
     * are no ordering guarantees. 
     */
    public int[] getAllShortcutKeycodes();

    /**
     * Retrieve any image used as a background for this {@link
     * org.dvb.application.inner.DVBScene DVBScene}.
     * 
     * @return an image used as a background, or <code>null</code> if
     * no image is set. Note that depending on the current render mode
     * any image set may not actually be rendered.
     * @see DVBScene#setRenderMode(int)
     */
    public java.awt.Image getBackgroundImage();

    /**
     * Get the background mode of this {@link org.dvb.application.inner.DVBScene
     * DVBScene}. The return value specifies whether the paint method
     * should draw the background (i.e. a rectangle filling the bounds
     * of the {@link org.dvb.application.inner.DVBScene DVBScene}).
     * 
     * @return one of {@link org.havi.ui.HScene#NO_BACKGROUND_FILL
     * NO_BACKGROUND_FILL} or {@link
     * org.havi.ui.HScene#BACKGROUND_FILL BACKGROUND_FILL}. 
     */
    public int getBackgroundMode();

    /**
     * Returns the child component of this {@link org.dvb.application.inner.DVBScene
     * DVBScene} which has focus if and only if this
     * {@link org.dvb.application.inner.DVBScene DVBScene} is active.
     *
     * @return the component with focus, or null if no children have focus
     * assigned to them. 
     */
    public java.awt.Component getFocusOwner();

    /**
     * Get the rendering mode of any background image associated with
     * this {@link org.dvb.application.inner.DVBScene DVBScene}. 
     * 
     * @return the rendering mode, one of {@link
     * org.havi.ui.HScene#IMAGE_NONE IMAGE_NONE}, {@link
     * org.havi.ui.HScene#IMAGE_STRETCH IMAGE_STRETCH}, {@link
     * org.havi.ui.HScene#IMAGE_CENTER IMAGE_CENTER} or {@link
     * org.havi.ui.HScene#IMAGE_TILE IMAGE_TILE}. 
     */
    public int getRenderMode();

    /**
     * Retrieve the {@link org.havi.ui.HActionable HActionable}	
     * associated with the specified shortcut key. 
     *
     * @param keyCode the shortcut key code to be queried for an
     * associated {@link org.havi.ui.HActionable HActionable}.
     * 
     * @return the {@link org.havi.ui.HActionable HActionable}
     * associated with the specified key if <code>keyCode</code> is a
     * valid shortcut key for this {@link org.dvb.application.inner.DVBScene DVBScene},
     * <code>null</code> otherwise. 
     */
    public HActionable getShortcutComponent(int keyCode);

    /**
     * Returns the keycode associated with the specified HActionable
     * component.
     *
     * @param comp the HActionable to return the keycode that it is
     * associated with.
     * @return the keycode associated with the specified HActionable
     * component, if it is currently a valid shortcut
     * &quot;target&quot;, otherwise return
     * java.awt.event.KeyEvent#VK_UNDEFINED. 
     */
    public int getShortcutKeycode(HActionable comp);

    /**
     * Returns <code>true</code> if all the drawing done during the update and
     * paint methods for this specific {@link org.dvb.application.inner.DVBScene
     * DVBScene} object is automatically double buffered.
     * 
     * @return <code>true</code> if all the drawing done during the
     * update and paint methods for this specific {@link
     * org.dvb.application.inner.DVBScene DVBScene} object is automatically
     * double buffered, or <code>false</code> if drawing is not double
     * buffered. The default value for the double buffering setting
     * is platform-specific. 
     */
    public boolean isDoubleBuffered();

    /**
     * Returns the status of all short cuts that are currently set on
     * the DVBScene.
     *
     * @return true if shortcuts are enabled, false otherwise.
     * @see DVBScene#enableShortcuts 
     */
    public boolean isEnableShortcuts();

    /** 
     * Returns true if the entire {@link org.dvb.application.inner.DVBScene DVBScene}
     * area, as given by the <code>java.awt.Component#getBounds</code>
     * method, is fully opaque, i.e. its paint method (or surrogate
     * methods) guarantee that all pixels are painted in an opaque
     * <code>Color</code>. 
     * <p> 
     * By default, the return value depends on the value of the
     * current background mode, as set by the
     * {@link org.dvb.application.inner.DVBScene#setBackgroundMode setBackgroundMode}
     * method. The return value should be overridden by subclasses
     * that can guarantee full opacity. The consequences of an invalid
     * overridden value are implementation specific.
     * 
     * @return <code>true</code> if all the pixels within the area
     * given by the <code>java.awt.Component#getBounds</code> method
     * are fully opaque, i.e. its paint method (or surrogate methods)
     * guarantee that all pixels are painted in an opaque Color,
     * otherwise <code>false</code>. 
     */
    public boolean isOpaque();

    /**
     * Determines if the {@link org.dvb.application.inner.DVBScene DVBScene} (or more
     * properly its added child components) is Visible. Initially an
     * {@link org.dvb.application.inner.DVBScene DVBScene} is invisible.
     *
     * @return true if the {@link org.dvb.application.inner.DVBScene DVBScene} is
     * visible; false otherwise. 
     */
    public boolean isVisible();

    /**
     * Removes the specified short-cut key. if the specified short-cut
     * key is not registered, the method has no effect
     * 
     * @param keyCode The keycode that represents the short cut
     */
    public void removeShortcut(int keyCode);

    /**
     * Remove a listener so that it no longer receives any
     * java.awt.event.WindowEvents. If the specified listener is not
     * registered, the method has no effect. If multiple references to
     * a single listener have been registered it should be noted that
     * this method will only remove one reference per call.
     *
     * @param wl The java.awt.event.WindowListener to be removed from
     * notification of any java.awt.event.WindowEvents. 
     */
    public void removeWindowListener(java.awt.event.WindowListener wl);

    /**
     * Set an image which shall be painted in the background of the
     * {@link org.dvb.application.inner.DVBScene DVBScene}, after the background has
     * been drawn according to the current mode set with {@link
     * org.dvb.application.inner.DVBScene#setBackgroundMode setBackgroundMode},
     * but before any children are drawn. The image is rendered
     * according to the current render mode set with {@link
     * org.dvb.application.inner.DVBScene#setRenderMode setRenderMode}.
     * <p>
     * Note that the use of a background image in this way may affect
     * the return value of the {@link org.dvb.application.inner.DVBScene#isOpaque isOpaque}
     * method, depending on the image and the current rendering mode.
     * 
     * @param image the image to be used as a background. If this
     * parameter is <code>null</code> any current image is
     * removed. Note that depending on the current render mode any
     * image set may not actually be rendered.
     * @see DVBScene#setRenderMode(int) 
     */
    public void setBackgroundImage(java.awt.Image image);

    /**
     * Set the background mode of this {@link org.dvb.application.inner.DVBScene
     * DVBScene}. The value specifies whether the paint method
     * should draw the background (i.e. a rectangle filling the bounds
     * of the {@link org.dvb.application.inner.DVBScene DVBScene}).
     * <p>
     * Note that the background mode will affect the return value of
     * the {@link org.dvb.application.inner.DVBScene#isOpaque isOpaque} method, depending
     * on the value of the <code>mode</code> parameter. A fill mode of
     * {@link org.havi.ui.HScene#BACKGROUND_FILL BACKGROUND_FILL}
     * implies that {@link org.dvb.application.inner.DVBScene#isOpaque isOpaque} must
     * return <code>true</code>.
     * 
     * @param mode one of {@link org.havi.ui.HScene#NO_BACKGROUND_FILL
     * NO_BACKGROUND_FILL} or {@link
     * org.havi.ui.HScene#BACKGROUND_FILL BACKGROUND_FILL}. If mode is
     * not a valid value, an IllegalArgumentException will be thrown. 
     */
    public void setBackgroundMode(int mode);

    /**
     * Set the rendering mode of any background image associated with
     * this {@link org.dvb.application.inner.DVBScene DVBScene}. <p> Note that the
     * minimum requirement is to support only the 
     * <code>IMAGE_NONE</code> mode. Support of the other modes is
     * platform and implementation specific. 
     * 
     * @param mode the rendering mode, one of {@link
     * org.havi.ui.HScene#IMAGE_NONE IMAGE_NONE}, {@link
     * org.havi.ui.HScene#IMAGE_STRETCH IMAGE_STRETCH}, {@link
     * org.havi.ui.HScene#IMAGE_CENTER IMAGE_CENTER} or {@link
     * org.havi.ui.HScene#IMAGE_TILE IMAGE_TILE}. 
     * @return <code>true</code> if the mode was set successfully,
     * <code>false</code> if the mode is not supported by the
     * platform. 
     */
    public boolean setRenderMode(int mode) ;

    /**
     * Adds a shortcut key to action the specified {@link
     * org.havi.ui.HActionable HActionable}. Generating the defined
     * java.awt.KeyEvent or {@link org.havi.ui.event.HRcEvent
     * HRcEvent} keycode causes the specified component to become
     * actioned --- potentially any keyCode may have a shortcut
     * associated with it.  The shortcut will only be added if the
     * {@link org.havi.ui.HActionable HActionable} component is a child
     * component in the container hierarchy of which the {@link
     * org.havi.ui.HScene HScene} is the root container. If this is
     * not the case this method shall return false.
     * <p> 
     * Note that a maximum of one {@link org.havi.ui.HActionable
     * HActionable} may be associated with a given keyCode. An {@link
     * org.havi.ui.HActionable HActionable} can have at most one
     * associated shortcut keycode. Calling {@link
     * org.havi.ui.HScene#addShortcut addShortcut} repeatedly with the
     * same {@link org.havi.ui.HActionable HActionable} will result in
     * the previous short cut being removed. A short cut can be set on
     * an invisible {@link org.havi.ui.HActionable HActionable} and
     * therefore it is possible to provide short-cuts that have no
     * user representation.
     * <p> 
     * If the relevant keyCode is received by the {@link
     * org.havi.ui.HScene HScene}, then the {@link
     * org.havi.ui.HActionable HActionable} will be actioned by the
     * {@link org.havi.ui.HScene HScene} sending it an {@link
     * org.havi.ui.event.HActionEvent#ACTION_PERFORMED ACTION_PERFORMED}.
     *
     *  Note that it is the responsibility of the application to ensure that 
     *  the keyCode used is included in the set registered with the setKeyEvents 
     *  method and is one which the platform can generate, i.e. where the method
     *  HRcCapabilities.isSupported() returns true.

     * @param keyCode the keycode that represents the short cut. If
     * keycode is java.awt.event.KeyEvent#VK_UNDEFINED, then the
     * shortcut will not be added, any existing shortcut for this
     * component will not be changed and this method shall return false. 
     * @param comp The actionable component that will be actioned.  
     * @return true if the shortcut was added, false otherwise. 
     */
    public boolean addShortcut(int keyCode, HActionable comp);
	
}

