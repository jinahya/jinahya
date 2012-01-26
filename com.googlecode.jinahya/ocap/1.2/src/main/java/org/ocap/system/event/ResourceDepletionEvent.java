/*
 * ResourceDepletionEvent.java
 * Created on February 20, 2004, 3:30 PM
 */

package org.ocap.system.event;

import java.lang.*;
import java.util.*;

/**
 * Event that indicates resources are low, and the system is about to destroy application(s) to
 * attempt to correct this.
 */
public class ResourceDepletionEvent extends SystemEvent
{
    /**
     * Overall system memory is depleted to an implementation specific threshold.
     * This indicates that the platform's application manager is about to destroy an application
     * to free system memory.
     */
    public final static int RESOURCE_SYS_MEM_DEPLETED = BEGIN_SYS_RES_DEP_RESERVED_EVENT_TYPES;

    /**
     * VM memory for an application is depleted to an implementation specific threshold.
     * This indicates that the platform's application manager is about to destroy an application
     * to free VM memory.

     */
    public final static int RESOURCE_VM_MEM_DEPLETED = BEGIN_SYS_RES_DEP_RESERVED_EVENT_TYPES + 1;

    /**
     * Available CPU cycles is depleted to an implementation specific threshold.
     * This indicates that the platform's application manager is about to destroy an application
     * to free CPU cycles.
     * <p>
     * Note that the presence of this event type does not imply that the platform's
     * application manager must destroy applications if CPU usage is too high; merely
     * that if it does then it must first send this event.
     */
    public final static int RESOURCE_CPU_BANDWIDTH_DEPLETED =
                                                    BEGIN_SYS_RES_DEP_RESERVED_EVENT_TYPES + 2;

    /**
     * Available reverse channel bandwidth is depleted to implementation specific threshold.
     * This indicates that the platform's application manager is about to destroy an application
     * to free return channel bandwidth.
     * <p>
     * Note that the presence of this event type does not imply that the platform's
     * application manager must destroy applications if return channel bandwidth usage is
     * too high; merely that if it does then it must first send this event.
     */
    public final static int RESOURCE_RC_BANDWIDTH_DEPLETED =
                                                    BEGIN_SYS_RES_DEP_RESERVED_EVENT_TYPES + 3;


    /**
     * System event constructor assigns an eventId, Date, and ApplicationIdentifier.
     *
     * @param typeCode - Unique event type.
     * @param message - Readable message specific to the event generator.
     *
     * @throws IllegalArgumentException if the typeCode is not in a defined application range
     * when the event is created by an application.  Since there are no defined
     * application ranges for resource depletion events, this exception will always be
     * thrown if this constructor is called by an application.
     */
    public ResourceDepletionEvent(int typeCode, String message)
    {
        super(typeCode, message);
    }

    /**
     * This constructor is provided for internal use by OCAP implementations;
     * applications SHOULD NOT call it.
     *
     * @param typeCode - The unique error type code.
     * @param message - Readable message specific to the event generator.
     * @param date - Event date in milli-seconds from midnight January 1, 1970 GMT.
     * @param appId - The Id of the application logging the event.
     *
     * @throws SecurityException if this constructor is called by any application.
     */
    public ResourceDepletionEvent(int typeCode,
                                  String message,
                                  long date,
                                  org.dvb.application.AppID appId) {
        super(typeCode, message, date, appId);
    }
}
