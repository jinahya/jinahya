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



package javax.microedition.xlet;

/** 
 * An interface that provides methods allowing an Xlet to discover
 * information about its environment. An XletContext is passed
 * to an Xlet when the Xlet is initialized. It provides an Xlet with a
 * mechanism to retrieve properties, as well as a way to signal
 * internal state changes.
 *
 * @see javax.microedition.xlet.Xlet
 */



public interface XletContext {

    /**
     * The property key used to obtain initialization arguments for the
     * Xlet.  The call
     * <code>XletContext.getXletProperty(XletContext.ARGS)</code> will
     * return the arguments as an array of Strings.  If there are
     * no arguments, then an array of length 0 will be returned.
     *
     * @see #getXletProperty
     */
    public static final String ARGS = "javax.microedition.xlet.args";

    /**
     *
     * Used by an application to notify its manager that it
     * has entered into the
     * <i>Destroyed</i> state.  The application manager will not
     * call the Xlet's <code>destroy</code> method, and all resources
     * held by the Xlet will be considered eligible for reclamation.  
     * Before calling this method,
     * the Xlet must have performed the same operations
     * (clean up, releasing of resources etc.) it would have if the
     * <code>Xlet.destroyXlet()</code> had been called.
     *  
     */
    public void notifyDestroyed();

    /**    
     * Notifies the manager that the Xlet does not want to be active and has
     * entered the <i>Paused</i> state.  Invoking this method will
     * have no effect if the Xlet is destroyed, or if it has not
     * yet been started. <p>
     *
     * If an Xlet calls <code>notifyPaused()</code>, in the
     * future it may receive an <i>Xlet.startXlet()</i> call to request
     * it to become active, or an <i>Xlet.destroyXlet()</i> call to request
     * it to destroy itself.
     * 
     */

    public void notifyPaused();

    /**
     * Provides an Xlet with a mechanism to retrieve named
     * properties from the XletContext.
     *
     * @param key The name of the property.
     *
     * @return A reference to an object representing the property.
     * <code>null</code> is returned if no value is available for key.
     * 
     * @throws NullPointerException If <code>key</code> is <code>null</code>.
     * @throws IllegalArgumentException If <code>key</code> is an empty string.
     */
    public Object getXletProperty(String key);
    
    /** 
     * Provides the Xlet with a mechanism to indicate that it is
     * interested in entering the <i>Active</i> state. Calls to this
     * method can be used by an application manager to determine which
     * Xlets to move to <i>Active</i> state.
     * If the request is fulfilled, the application manager will
     * subsequently call <code>Xlet.startXlet()</code>
     * via a different thread than the one used to call
     * <code>resumeRequest()</code>.
     * 
     * @see Xlet#startXlet
     */
    public void resumeRequest();

    /**
     * Get the parent container for an Xlet to put its AWT components
     * in.  Xlets without a graphical representation need not call
     * this method.  If successful, this method returns an instance
     * <code>c</code> of
     * <code>java.awt.Container</code> that is initially invisible, with
     * an arbitrary size and position.  Calling
     * <code>c.setVisible(true)</code> will make the container visible.
     * <p>
     * If this method is called multiple times on the same XletContext
     * instance, the same container will be returned each time.  Note
     * that the platform need not support more than a single displayable
     * Xlet at a time, however, at least the first call to this method
     * platform-wide is guaranteed to succeed.  The behavior for subsequent
     * calls to this method is implementation-dependent.
     * <p>
     * The methods for setting the size and position of the xlet's parent
     * container shall attempt to change the shape of the container, but
     * they may fail silently or make platform specific approximations.
     * To discover if a request to change the size or position
     * has succeeded, the Xlet should query the container for the result.
     * <p>
<!-- [6233187] -->
     * Because the container is initially invisible, an
     * xlet that makes the container visible and that wishes to receive
     * the AWT focus should request this explicitly, i.e. by calling
     * {@link java.awt.Component#requestFocus}.
     * <p>
<!-- [6208460] -->
     *
     * In order to protect UI elements that
     * are shared between multiple xlets,
     * implementations must prohibit access to resources that
     * belong to a different application context than the calling thread.
     * Therefore, xlets should be aware that an enclosing Container whose
     * <code>getParent()</code> and <code>getFocusCycleRootAncestor()</code>
     * methods return <code>null</code> may not actually be the top of the
     * AWT containment hierarchy.
     * <p>
     * 
     * This method throws {@link UnavailableContainerException} If
     * policy or screen real estate does not permit a
     * Container to be granted to the Xlet, or if
     * {@link java.awt.GraphicsEnvironment#isHeadless} returns
     * <code>true</code>.
     * <p>
     * 
<!-- Note: PBP specific -->
<!-- [4526422] -->
     * Note: Personal Basis Profile permits only a single instance of
     * {@link java.awt.Frame} per {@link GraphicsDevice}.
     * In the case of Xlets, this single <code>Frame</code> instance
     * is created in advance by the xlet manager to serve as the parent
     * of the containers returned by this method.
     * Therefore, attempts by an Xlet to create an instance of
     * {@link java.awt.Frame} will always fail.
     *
     * @return A container with an arbitrary size and position.
     * 
     * @throws UnavailableContainerException If a Container cannot be
     * granted to the Xlet.
     * 
     * @see java.awt.Container#getParent
     * @see java.awt.Container#getFocusCycleRootAncestor
     */
    public java.awt.Container getContainer() throws UnavailableContainerException;

    /**
     * Returns the base class loader of the Xlet.  This class loader
     * will be dedicated exclusively to the xlet.  All the xlet's classes
     * are loaded by this classloader or by a classloader that has this
     * classloader as its ancestor.
     */
    public java.lang.ClassLoader getClassLoader();

}

