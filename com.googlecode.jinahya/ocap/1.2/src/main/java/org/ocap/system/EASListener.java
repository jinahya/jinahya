package org.ocap.system;

import java.util.EventListener;

/**
 * This interface represents a listener that will receive EAS events.
 */
public interface EASListener extends EventListener
{

    /**
     * Warns an application that an EAS message has been received and parsed and
     * resources will soon be allocated to it.  The application can get the reason
     * for the event from the event parameter.
     * 
     * @param event EAS event received.
     */
    public void warn(EASEvent event);

    /**
     * Notifies an application that an EAS message has started, is in-progress,
     * or has completed.  The application can get the reason for the event from
     * the event parameter.
     * 
     * @param event EAS event received.
     */
    public void notify(EASEvent event);
    

}
