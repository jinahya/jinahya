
package org.dvb.application.plugins;

import javax.tv.xlet.XletContext;

/**
 * This class permits user code to intercept certain system calls
 * initiated by an embedded Xlet that need to be serviced by a
 * support application. For example, a DVB-HTML plug-in application
 * needs to service requests that are made by an embedded Xlet, typically
 * via static method calls.
 *
 * @since MHP1.1
 **/

public abstract class XletSystemCall {

    /**
     * Create a new XletSystemCall
     */
    protected XletSystemCall() {
    }

    /**
     * Register this instance of XletSystemCall with the system.
     *
     * @param p The Plugin that services calls made by the xlet, i.e.
     *		the Plugin of which this instance of XletSystemCall is
     *		a part.
     *
     * @param ctx The XletContext of the Xlet making the calls
     *
     * @throws NullPointerException if p or ctx is null
     *
     * @see #unregister
     **/
    public final void register(Plugin p, XletContext ctx) {
    }

    /**
     * Unregister this instance of XletSystemCall with the system. When
     * an interoperable Plugin terminates, of an Xlet managed by a Plugin
     * is asked to terminate, the Plugin must unregister any relevant
     * XletSystemCall instances.
     *
     * @param p The Plugin that services calls made by the xlet, i.e.
     *		the Plugin of which this instance of XletSystemCall is
     *		a part.
     *
     * @param ctx The XletContext of the Xlet making the calls
     *
     * @throws NullPointerException if p or ctx is null
     *
     * @see #register
     **/
    public final void unregister(Plugin p, XletContext ctx) {
    }

    /**
     * Called when the Xlet calls 
     * javax.tv.graphics.TVContainer.getRootContainer().
     *
     * @param ctx The context of the Xlet making the request; it shall be identical
     *		to the XletContext used to create this instance of 
     *		XletSystemCall.
     *
     * @return a container object to be returned to the embedded xlet
     *
     * @see javax.tv.graphics.TVContainer#getRootContainer
     **/
    public abstract java.awt.Container getRootContainer(XletContext ctx);
}

