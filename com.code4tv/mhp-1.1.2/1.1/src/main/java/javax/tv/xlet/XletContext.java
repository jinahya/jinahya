/*
 * @(#)XletContext.java	1.12 00/10/09
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package javax.tv.xlet;

/** 
 * An interface that provides methods allowing an Xlet to discover
 * information about its environment. An XletContext is passed
 * to an Xlet when the Xlet is initialized. It provides an Xlet with a
 * mechanism to retrieve properties, as well as a way to signal
 * internal state changes.
 *
 * @see javax.tv.xlet.Xlet
 * @see javax.tv.graphics.TVContainer
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
    public static final String ARGS = "javax.tv.xlet.args";

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
     */
    public Object getXletProperty(String key);
    
    /** 
     * Provides the Xlet with a mechanism to indicate that it is
     * interested in entering the <i>Active</i> state. Calls to
     * this method can be used by an application manager to determine which
     * Xlets to move to <i>Active</i> state.
     */

    public void resumeRequest();

}

