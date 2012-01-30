/*
 * @(#)TVContainer.java	1.12 00/06/23
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

package javax.tv.graphics;


/** 
 * A class that allows an Xlet to get the root container for its AWT
 * components.
 *
 * @version     1.12, 06/23/00
 */

public class TVContainer {

    /**
     * Get the parent container for an Xlet to put its AWT components
     * in, if the Xlet has a graphical representation.  Xlets without a
     * graphical representation should never call this method.  If the Xlet
     * is the only Xlet that is currently active to invoke this method,
     * it will return an instance of <code>java.awt.Container</code> that
     * is initially invisible, with an undefined
     * size and position.  If another Xlet that is currently
     * active has requested a root container (via this API, or some
     * other platform-specific API), this method may return null.
     * <p>
     * If this method is called multiple times for the same XletContext, 
     * the same container will be returned each time.
     * <p>
     * The methods for setting the size and position of the xlet's root
     * container shall attempt to change the shape of the container, but
     * they may fail silently or make platform specific approximations.
     * For example, a request to change the shape of the root container might
     * result in its overlapping with another root container, and overlapping
     * root containers might not be supported by the hardware.  An application
     * that needs to discover if a request to change the size or position
     * has succeeded should query the component for the result.
     *
     * @param ctx The XletContext for the Xlet requesting the container.  See
     *		  the package documentation for javax.tv.xlet for a description
     *            of the relationship between Xlet and XletContext.
     *
     * @return An invisible container with an undefined size and position, or
     * null if a container is unavailable.
     */
    public static java.awt.Container 
    getRootContainer(javax.tv.xlet.XletContext ctx) {
	return null;
    }
}

