/*
 * RebootEvent.java
 * Created on February 20, 2004, 3:26 PM
 */

package org.ocap.system.event;

import java.lang.*;

/**
 * This class represents an event returned by the system when a reboot is instigated.
 * Reboot event type codes are defined in this class.  Implementations may use the reboot
 * type codes in this class or proprietary class codes that are understood by the network.
 */
public class RebootEvent extends SystemEvent
{

    /**
     * Reboot instigated by implementation; no error encountered.
     */
    public final static int REBOOT_BY_IMPLEMENTATION = BEGIN_SYS_REBOOT_RESERVED_EVENT_TYPES;

    /**
     * Reboot instigated by implementation; unrecoverable system error encountered.
     */
    public final static int REBOOT_FOR_UNRECOVERABLE_SYS_ERROR =
                                                BEGIN_SYS_REBOOT_RESERVED_EVENT_TYPES + 1;

    /**
     * Reboot instigated by the implementation; unrecoverable hardware error encountered.
     * For hardware errors only, not firmware.  If indistinguishable between software or
     * firmware errors in certain implementations,
     * {@link #REBOOT_FOR_UNRECOVERABLE_SYS_ERROR} MUST be generated instead.
     */
    public final static int REBOOT_FOR_UNRECOVERABLE_HW_ERROR =
                                                BEGIN_SYS_REBOOT_RESERVED_EVENT_TYPES + 2;

    /**
     * Reboot instigated by trusted application.
     * @see org.ocap.hardware.Host#reboot()
     */
    public final static int REBOOT_BY_TRUSTED_APP = BEGIN_SYS_REBOOT_RESERVED_EVENT_TYPES + 3;


    /**
     * System event constructor assigns an eventId, Date, and ApplicationIdentifier.
     *
     * @param typeCode - Unique event type.
     * @param message - Readable message specific to the event generator.
     *
     * @throws IllegalArgumentException if the typeCode is not in a defined application range
     * when the event is created by an application.
     */
    public RebootEvent(int typeCode, String message)
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
    public RebootEvent(int typeCode,
                       String message,
                       long date,
                       org.dvb.application.AppID appId){
        super(typeCode, message, date, appId);
    }
}
