

package org.dvb.application.plugins;

import java.awt.Container;

/**
 * This class provides a container that can be embedded in a widget
 * hierarchy, and can provide a container that can safely be used as the
 * top-level container of an embedded Xlet. This solves the problem
 * of an embedded Xlet being able to call getParent() to discover the
 * widget hierarchy in which it is embedded.
 *
 * @since MHP 1.1
 **/
public class XletContainer extends Container {

    /**
     * Construct a new XletContainer as a child of Parent. The XletContainer
     * contains exactly one widget:  A child container. The child container
     * is constrained to be at the same location and the same size as
     * the parent XletContainer.
     *
     * @param parent The widget that is to have an Xlet embedded within it.
     *
     * @see XletContainer#getXletContainer
     *
     * @throws NullPointerException if parent is null
     **/
    public XletContainer(Container parent) {
    }

    /**
     * Get the only child of the XletContainer. This container can safely
     * be used as the top-level container of an Xlet, e.g. an Xlet embedded
     * in a DVB-HTML page. Calling getParent() on this container from client
     * code will return null, unless the caller has been granted special access
     * via a platform-dependent mechanism.
     * <p>
     * If this method is invoked more than once for the same instance of
     * XletContainer, it will return the same instance.
     *
     * @return The child of this XletContainer.
     **/
    public Container getXletContainer() {
	return null;
    }

}

