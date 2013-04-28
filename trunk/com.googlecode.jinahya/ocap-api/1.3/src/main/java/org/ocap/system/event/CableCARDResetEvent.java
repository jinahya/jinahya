/*
 * CableCARDResetEvent.java
*/

package org.ocap.system.event;

import java.lang.*;
import java.util.*;

/**
 * Events that indicate the CableCARD is about to be reset and has successfully
*  completed initialization after a reset.
 */
public class CableCARDResetEvent extends SystemEvent
{
    /**
     * CableCARD reset request to Host
     */
    public final static int CABLECARD_RESET_BEGIN =  
                 BEGIN_SYS_CABLECARD_RESET_EVENT_TYPES;

    /**
     * CableCARD reset and initialization complete
     */
    public final static int CABLECARD_RESET_COMPLETE =
                                                BEGIN_SYS_CABLECARD_RESET_EVENT_TYPES + 1;
    /**
     * System event constructor assigns an eventId, Date, and ApplicationIdentifier.
     *
     * @param typeCode - Unique event type.
     *
     * @throws IllegalArgumentException if the typeCode is not in a defined application range
     * when the event is created by an application.  Since there are no defined
     * application ranges for resource depletion events, this exception will always be
     * thrown if this constructor is called by an application.
     */
    public CableCARDResetEvent(int typeCode)
    {
        super(typeCode);
    }
}
