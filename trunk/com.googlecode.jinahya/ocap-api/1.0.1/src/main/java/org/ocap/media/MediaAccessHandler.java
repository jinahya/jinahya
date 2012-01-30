package org.ocap.media;

import javax.media.Player;
import org.davic.mpeg.ElementaryStream;
import org.ocap.net.OcapLocator;

/**
 * A class implementing this interface can prevent the presentation of A/V 
 * service components.
 * <p>Only one instance of the class that implements this interface can be 
 * registered to <code>{@link MediaAccessHandlerRegistrar}</code> via the 
 * <code>{@link MediaAccessHandlerRegistrar#registerMediaAccessHandler}</code> 
 * method. JMF calls checkMediaAccessAuthorization() before AV service
 * components presentation.  
 * <p>An application which has a MonitorAppPermission("mediaAccess") may 
 * implement this interface, and may set an instance of it in
 * <code>{@link MediaAccessHandlerRegistrar}</code>.</p>
 * <p>Note : this handler is only responsible for the presentation of A/V
 * service components and not for launching applications.</p>
 */
public interface MediaAccessHandler {

    /**
     * Checks if presentation of the locators parameter is acceptable.  
     * The OCAP implementation SHALL take action based on the 
     * <code>MediaAccessAuthorization</code> returned from this method.
     * 
     * @param locators The locators of the content to be presented.  The
     *      array MAY contain a single service locator or multiple service
     *      component locators where all the components are part of the
     *      same service.
     * @param isSourceDigital True if the source is digital, otherwise
     *      returns false.
     * 
     * @return a {@link MediaAccessAuthorization} defined by MediaAccessHandler
     *      for the given service components. 
     * 
     * @see MediaAccessAuthorization
     * @see AlternativeMediaPresentationReason
     */
    public MediaAccessAuthorization checkMediaAccessAuthorization(
                                                    boolean isSourceDigital,
                                                    OcapLocator [] locators);

    /**
     * Notifies the handler when a service is blocked based on ratings
     * set by a call to the
     * {@link MediaAccessHandlerRegistrar#setSignaledBlocking} and
     * {@link MediaAccessHandlerRegistrar#setNotRatedSignaledBlocking} methods.
     * 
     * @param locators The locators of the content to be presented.  The
     *      array MAY contain a single service locator or multiple service
     *      component locators where all the components are part of the
     *      same service.  The parameter SHALL represent locators equivalent
     *      to those used to select the service components.
     */
    public void notifySignaledBlocking(OcapLocator [] locators);
}
