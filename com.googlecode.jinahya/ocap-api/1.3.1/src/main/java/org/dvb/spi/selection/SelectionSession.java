
package org.dvb.spi.selection;
import org.davic.net.Locator;

/**
 * A session for presentation of one or more services over a period of
 * time by a SelectionProvider.  The first operation on a new session will 
 * always be selection of a service in a service context, and all subsequent 
 * operations will pertain to the same service context.
 * @since MHP 1.1.3
 **/
public interface SelectionSession {

    /**
     * Sets up delivery of a stream representing the service, and delivers
     * a locator for reception of that stream.  For example, either
     * a unicast locator or a frequency locator might be returned,
     * under the apprlpriate circumstances.
     * @return a locator for where the stream can be found
     **/
    public Locator select();

    /**
     * Called by the platform when the service bound to this session is no longer
     * being used by the implementation. The selection provider should do any needed
     * clean-up here, e.g. informing a server that the service is not needed any
     * more. Once destroy is called, the terminal implementation
     * discards the SelectionSession.
     **/
    public void destroy();

    /**
     * Called when the implementation is ready to receive content on
     * the locator returned by the select method.
     * When using unicast protocols where the content must only be sent
     * when the MHP terminal is ready, it is now safe to request the
     * content be sent.
     * <p>
     * A SelectionSession might be destroyed after a select, but before
     * this method is called.  In this case, this method may not be
     * called.  Applications should therefore not expect this method
     * to be called after destroy() is called on this session.
     *
     * @see #destroy()
     **/
    public void selectionReady();

    /**
     * Sets the speed of playback.  If this session does not support trick
     * modes, this method returns 1 and has no effect.  Calling this method
     * with the value 1.0f shall always succeed, and shall result in a return
     * value of 1.0f.
     *
     * @param  newRate   New playback rate.  Implementations shall make
     *			 a best effort to approximate this rate.
     * 
     * @return  The new rate, if known.  If not known, the value
     *		java.lang.Float.NEGATIVE_INFINITY is returned.
     **/
    public float setRate(float newRate);

    /**
     * Set the position within the media.  If this session does not support
     * trick modes, this method returns -1 and has no effect.
     *
     * @param	position	The position within the program, in milliseconds
     *
     * @return	The new position, or -1 if position setting isn't supported.
     **/
    public long setPosition(long position);
}

