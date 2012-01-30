package org.ocap.system;


/**
 * This class represents a manager that allows applications to register listeners for
 * EAS events.
 */
public abstract class EASManager
{
    /**
     * Indicates an EAS message has been received and is about to be processed.  This
     * state MAY be entered before an EASListener receives an EAS event.
     */
    public final static int EAS_MESSAGE_RECEIVED_STATE = 0;

    /**
     * Indicates the implementation is processing the EAS message and EAS information
     * is being presented.  This state MAY coincide with resources being taken away
     * from applications.
     */
    public final static int EAS_MESSAGE_IN_PROGRESS_STATE = 1;
    
    /**
     * Indicates an EAS message is not being processed.  This state MAY be entered
     * before an EASListener receives an EAS_COMPLETE_EVENT.
     */
    public final static int EAS_NOT_IN_PROGRESS_STATE = 2;
    
    /**
     * Gets the instance of the EAS Manager class that may be used by the
     * application to register an EASListener.
     * 
     * @return The EAS manager.
     */
    public static EASManager getInstance()
    {
        return null;
    }
    
    /**
     * Adds a listener for EAS events.
     * 
     * @param listener The new EAS listener.
     */
    public abstract void addListener(EASListener listener);

    /**
     * Removes a listener from receiving EAS events.  If the parameter listener wasn't
     * previously added with the <code>addListener</code> method, this method does
     * nothing.
     * 
     * @param listener The EAS listener to be removed.
     */
    public abstract void removeListener(EASListener listener);


    /**
     * Gets the EAS state.  Possible return values are defined by state constants in
     * this class.
     * 
     * @return EAS state.
     */
    public int getState()
    {
        return EAS_NOT_IN_PROGRESS_STATE;
    }
}
