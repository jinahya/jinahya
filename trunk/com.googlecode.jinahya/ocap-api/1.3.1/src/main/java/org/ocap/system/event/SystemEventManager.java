/*
 * SystemEventManager.java
*/

package org.ocap.system.event;

import java.lang.*;
import java.security.*;

/**
 * Registration mechanism for trusted applications to set the error handler.
 */
public abstract class SystemEventManager
{
    /**
     * Identifies the system error event listener.
     * @see #setEventListener(int, org.ocap.system.event.SystemEventListener)
     */
    public final static int ERROR_EVENT_LISTENER = 0x0;

    /**
     *  Identifies the reboot event listener.
     * @see #setEventListener(int,org.ocap.system.event.SystemEventListener)
     */
    public final static int REBOOT_EVENT_LISTENER = 0x1;

    /**
     * Identifies the system resource depletion event listener.
     * @see #setEventListener(int,org.ocap.system.event.SystemEventListener)
     */
    public final static int RESOURCE_DEPLETION_EVENT_LISTENER = 0x2;

    /**
     *  Identifies the deferred download event listener.
     * @see #setEventListener(int, org.ocap.system.event.SystemEventListener)
     */
    public final static int DEFERRED_DOWNLOAD_EVENT_LISTENER = 0X03;
    
    /**
     *  Identifies the CableCARD reset event listener.
     * @see #setEventListener(int, org.ocap.system.event.SystemEventListener)
     */
    public final static int CABLE_CARD_EVENT_LISTENER = 0X04;
    
    /**
     * This constructor must not be used by OCAP applications.  It is only provided for
     * implementors of the OCAP APIs.
     */
 
    protected SystemEventManager()
    {
    }

    /**
     * Gets the singleton instance of the system event manager.
     *
     * @return The system event manager instance.
     */
    public static SystemEventManager getInstance()
    {
        return null;
    }

    /**
     * Set the system event listener specified by type and the new handler.
     * On a successful call, any previously set SystemEventListener for the same 
     * type is discarded. By default no SystemEventListener is set for any type. 
     *
     * @param type - {@link #ERROR_EVENT_LISTENER}, {@link #REBOOT_EVENT_LISTENER}, 
     * {@link #RESOURCE_DEPLETION_EVENT_LISTENER}, {@link #DEFERRED_DOWNLOAD_EVENT_LISTENER}, 
     * or {@link #CABLE_CARD_EVENT_LISTENER}
     *
     * @param sel - System event listener created by the registering application.
     *
     * @throws java.lang.SecurityException if the application does not have
     *      MonitorAppPermission("systemevent")
     * @throws java.lang.IllegalArgumentException if type is not one of
     *      {@link #ERROR_EVENT_LISTENER},
     *      {@link #REBOOT_EVENT_LISTENER}, 
     *      {@link #RESOURCE_DEPLETION_EVENT_LISTENER},
     *      {@link #DEFERRED_DOWNLOAD_EVENT_LISTENER}, or
     *      {@link #CABLE_CARD_EVENT_LISTENER}.     
     */
    public abstract void setEventListener(int type, SystemEventListener sel);

    /**
     * Unset the system event handler specified by type.
     *
     * @param type - One of {@link #ERROR_EVENT_LISTENER},
     *      {@link #REBOOT_EVENT_LISTENER}, 
     *      {@link #RESOURCE_DEPLETION_EVENT_LISTENER},
     *      {@link #DEFERRED_DOWNLOAD_EVENT_LISTENER}, or
     *      {@link #CABLE_CARD_EVENT_LISTENER}.
     *
     * @throws java.lang.SecurityException if the application does not have
     *      MonitorAppPermission("systemevent")
     */
    public abstract void unsetEventListener(int type);

    /**
     * Logs an event.  Checks the instance of the event and calls the appropriate error,
     * reboot, or resource depletion handler and passes the even to it.
     *
     * @param event - The event to log.
     *
     * @throws java.lang.IllegalArgumentException if the event parameter is an instance of
     *      an application defined class (i.e., applications cannot define their own subclasses
     *      of SystemEvent and use them with this method.  This is due to implementation and
     *      security issues).
     */
    public abstract void log(SystemEvent event);
}
