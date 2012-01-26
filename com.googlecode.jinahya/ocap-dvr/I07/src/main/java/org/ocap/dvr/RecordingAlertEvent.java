package org.ocap.dvr;

import org.ocap.shared.dvr.RecordingRequest;
import java.util.EventObject;

/**
 * Event notifying that a scheduled recording is about to occur. This event
 * is triggered for <code>LeafRecordingRequest</code>s in pending states.
 */
public class RecordingAlertEvent extends EventObject
{
    /**
     * Constructs the event.
     *
     * @param source The <code>RecordingRequest</code> that
     * caused the event.
     */
    public RecordingAlertEvent(RecordingRequest source)
    {
        super(source);
    }

    /**
     * Returns the <code>RecordingRequest</code> that caused the event.
     *
     * @return The <code>RecordingRequest</code> that caused the event.
     */
    public RecordingRequest getRecordingRequest()
    {
        return(RecordingRequest)super.getSource();
    }
}

