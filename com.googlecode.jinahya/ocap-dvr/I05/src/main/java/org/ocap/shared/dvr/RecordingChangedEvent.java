package org.ocap.shared.dvr;

import java.util.EventObject;

/**
 * Event used to notify listeners of changes in the list of recording requests maintained 
 * by the RecordingManager.
 * This event is not generated for changes other than those for which constants
 * are defined in this class.
 */
public class RecordingChangedEvent extends EventObject
{

    /**
     * A new <code>RecordingRequest</code> was added.
     */
    public static final int ENTRY_ADDED = 1;

    /**
     * A <code>RecordingRequest</code> was deleted.
     */
    public static final int ENTRY_DELETED = 2;

    /**
     * The state of a <code>RecordingRequest</code> changed
     */
    public static final int ENTRY_STATE_CHANGED = 3;

    /**
     * Constructs the event.
     * Events constructed with this constructor shall have a type of ENTRY_STATE_CHANGED.
     * @param source The <code>RecordingRequest</code> that
     * caused the event. 
     * @param newState the state the <code>RecordingRequest</code> is now in.
     * @param oldState the state the <code>RecordingRequest</code> was in before the state change.
     */
    public RecordingChangedEvent(RecordingRequest source,
                                 int newState, int oldState)
    {
        super(source);
    }

    /**
     * Constructs the event.
     *
     * @param source The <code>RecordingRequest</code> that
     * caused the event. 
     * @param newState the state the <code>RecordingRequest</code> is now in.
     * @param oldState the state the <code>RecordingRequest</code> was in before the state change.
     * @param type the type of change which caused this event to be generated
     */
    public RecordingChangedEvent(RecordingRequest source,
                                 int newState, int oldState, int type )
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

    /**
     * Returns the change to the <code>RecordingRequest</code>.
     *
     * @return the type of the change which caused the event
     */
    public int getChange()
    {
        return 0;
    }

    /**
     * Returns the new state for the <code>RecordingRequest</code>.
     *
     * @return The new state.
     */
    public int getState()
    {
        return 0;
    }

    /**
     * Returns the old state for the <code>RecordingRequest</code>.
     *
     * @return The old state.
     */
    public int getOldState()
    {
        return 0;
    }
}


