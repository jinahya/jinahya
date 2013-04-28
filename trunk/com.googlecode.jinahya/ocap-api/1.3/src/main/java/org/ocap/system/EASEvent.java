package org.ocap.system;

import java.util.EventObject;

/**
 * This class represents an EAS event.
 */
public class EASEvent extends EventObject
{

    /**
     * This event is generated when an EAS table is detected by the implementation
     * and the table requires a forced tune.
     */
    public final static int EAS_DETAILS_CHANNEL = 1;

    /**
     * This event is generated when an EAS table is detected by the implementation
     * and the table requires textual display.
     */
    public final static int EAS_TEXT_DISPLAY = 2;

    /**
     * The event is generated when an EAS message completes.
     */
    public final static int EAS_COMPLETE = 3;

    /**
     * Constructs a EASEvent with the specified source and reason.
     * 
     * @param source Implementation specific source object
     * @param reason The reason that caused this event.  See constants in this class
     *      for possible reasons.
     */
    public EASEvent(Object source, int reason)
    {
        super(source);
    }

    /**
     * Gets the reason passed to the constructor.
     * 
     * @return Reason for this event.
     */
    public int getReason()
    {
        return 0;
    }
}
