/*
 * SystemEventListener.java
 * Created on February 20, 2004, 4:02 PM
 */

package org.ocap.system.event;

import java.util.EventListener;

/**
 * System event handler implemented by a trusted application and registered with
 * {@link org.ocap.system.event.SystemEventManager}.
 */
public interface SystemEventListener extends EventListener
{
    /**
     * Receives error, resource depletion, or reboot events from the system when a trusted
     * application has registered as the event handler for the respective event type.
     *
     * @param event - The event encountered by the implementation.
     *
     */
    public void notifyEvent(SystemEvent event);
} 
