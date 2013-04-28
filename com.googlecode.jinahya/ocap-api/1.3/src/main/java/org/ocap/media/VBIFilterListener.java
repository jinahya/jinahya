/*
 * VBIFilterListener.java
 *
 * Created on September 18, 2004, 1:37 PM
 */

package org.ocap.media;

import java.util.EventListener;

/**
 * <p>
 * This interface represents a VBI filter event listener. 
 * When a specific event happens, the {@link VBIFilterListener#filterUpdate} 
 * method is called with an event that has a proper event code to indicate 
 * the event. 
 * </p>
 *
 * @author  Shigeaki Watanabe (Panasonic)
 */
public interface VBIFilterListener extends EventListener
{
    /**
     * This method is called by the OCAP implementation to notify an 
     * application that a VBI event has occurred. 
     *
     * @param event  a VBIFilterEvent instance that contains a event code. 
     */
    public void filterUpdate(VBIFilterEvent event);
}

