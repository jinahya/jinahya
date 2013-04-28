package org.ocap.event;

import org.dvb.application.*;

/**
 * UserEventAction is returned by the UserEventFilter.filterUserEvent() 
 * method in order to inform the EventManager the value of the event and
 * to which applications the event shall be forwarded. See the 
 * org.ocap.event.UserEventFilter.filterUserEvent() method for further details. 
 * UserEventAction has separate methods to provide the list of AppIDs and 
 * the modified UserEvent instance. The modified UserEvent instance will be 
 * forwarded to the applications specified by AppIDs by EventManager. 
 * If the list of AppIDs is null, the EventManager shall forward the event 
 * to all registered UserEventListeners. If the list of AppIDs is not null, 
 * the EventManager shall forward the event to the registered 
 * UserEventListeners that match the AppIDs in the list. Note that if 
 * UserEventFilter.filterUserEvent() returns null, the event is not sent 
 * to any applications.
 */
public class UserEventAction
{
        /**
     * Creates a UserEventAction instance.<p>
     *
     * The event passed to this constructor SHOULD NOT be an
     * application-defined subclass of UserEvent.
     * If it is an application-defined subclass, then when the platform
     * dispatches the event the platform MUST extract the parameters
     * of the event (e.g., source, type, code etc.) and construct a
     * new instance of the UserEvent class with those parameters.
     * I.e., the EventManager MUST NOT deliver the application-defined
     * subclass.  (NOTE: This translation is done by the platform, NOT
     * by this class).
     *
     * @param event The event to forward, or null for none.
     * @param appIDs The AppIDs to which the filtered event will be
     *        forwarded, or null for default handling.
     */
    public UserEventAction(UserEvent event, AppID[] appIDs)
    {}

    /**
     * Sets the event returned by this class.<p>
     *
     * The event passed to this function SHOULD NOT be an
     * application-defined subclass of UserEvent.
     * If it is an application-defined subclass, then when the platform
     * dispatches the event the platform MUST extract the data and
     * construct a real UserEvent instance.  (NOTE: This translation is
     * done by the platform, NOT by this class.).
     *
     * @param event The event to forward, or null for none.
     */
    public void setEvent(UserEvent event)
    {}

    /**
     * Sets the application IDs returned by this class.
     *
     * @param appIDs The AppIDs to which the filtered event will be
     *        forwarded, or null for default handling.
     */
    public void setAppIDs(AppID[] appIDs)
    {}

    /**
     * Get the event to be forwarded.  The event may be modified while 
     * filtering. EventManager shall forward this modified event 
     * instead of the original user input event.
     *
     *@return The event to be forwarded. 
     *        If null, no event is forwarded to any application.
     */
    public UserEvent getEvent(){
        return null; 
    }

    /**
     *Get the AppIDs to which the filtered event will be forwarded.
     *
     *@return The AppIDs to which the filtered event will be forwarded. 
     *          If null, the EventManager shall forward the event to all 
     *          registered UserEventListeners.
     */
    public org.dvb.application.AppID[] getAppIDs() { 
        return null; 
    }
}
