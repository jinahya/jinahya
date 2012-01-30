package org.ocap.media;

import javax.media.ControllerEvent;
import javax.media.Controller;
import org.davic.mpeg.ElementaryStream;
import org.ocap.net.OcapLocator;

/**
 * <code>MediaPresentationEvent</code> is a JMF event used as the parent
 * class of events indicating dynamic changes to the presentation of 
 * media components.
 */
public abstract class MediaPresentationEvent extends ControllerEvent
{
    /**
     * Constructor of MediaPresentationEvent
     * @see javax.media.TransitionEvent
     */ 
    protected MediaPresentationEvent(Controller from)
    {
        super(from);
    }

    /**
         * Indicates if the pending presentation is a digital service.
         * 
     * @return Returns true if the presented source is digital,
         *      false otherwise.
     */
    public boolean isSourceDigital()
    {
        return true;
    }

    /**
     * Gets the array of locators used for authorized presentation.
         * 
         * @return Returns the array of locators for presentation.
     */
    public OcapLocator [] getLocators()
    {
        return null;
    }
}
