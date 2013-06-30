package org.ocap.event;

import org.davic.resources.*;
import org.dvb.event.*;
import org.dvb.application.*;

/**
 * <p>
 * The event manager allows an application to receive events coming from 
 * the user. These events can be sent exclusively to an application or can 
 * be shared between applications. The EventManager allows an application 
 * to ask for exclusive access to some events, these events being received 
 * either from the standard java.awt event mechanism or by the mechanism 
 * defined in this package. The EventManager is a singleton, and the 
 * instance is gotten from the getInstance() method. (Note that a type 
 * cast is necessary to gain reference to object of type 
 * org.ocap.event.EventManager.) 
 * </p><p>
 * The right to receive events is considered as the same resource regardless 
 * of whether it is being handled exclusively or shared. An application 
 * successfully obtaining exclusive access to an event results in all other 
 * applications losing access to that event, whether the access of those 
 * applications was shared or exclusive. 
 * </p><p>
 * If an UserEventFilter instance is set via 
 * EventManager.setUserEventFilter(), EventManager shall call the 
 * UserEventFilter.filterUserEvent() method before delivering events to the 
 * listening applications that are specified by the UserEventFilter. Note 
 * that EventManager shall call the filterUserEvent() method for only the 
 * events specified by an UserEventRepository instance which is set via 
 * EventManager.setFilteredRepository(). EventFilter may modify the key 
 * value of the userEvent and/or may direct the platform to forward the 
 * userEvent to a specific set of applications. Then EventManager gets the 
 * (possibly modified) event via UserEventAction.getEvent() and the list of 
 * AppIDs of  the  applications to receive the forwarded event via 
 * UserEventAction.getAppIDs(). EventManager shall call the 
 * UserEventListener.userEventReceived() of the applications which have the 
 * AppIDs specified by UserEventAction.getAppIDs(). For this purpose, 
 * EventManager shall track and keep the AppID of the applications which 
 * call the addExclusiveAccessToAWTEvent() and addUserEventListener() methods 
 * in a proprietary manner (manufacture dependent).
 * </p>
 */

public class EventManager extends org.dvb.event.EventManager
{
    /**
     * Constructor for an instance of this class. This constructor is 
     * provided for the use of implementations. Applications shall not 
     * define sub classes of this class. Implementations are not required 
     * to behave correctly if any such application defined sub classes 
     * are used.
     */
    protected EventManager() {
    };


    /**
     * This method returns the sole instance of the 
     * org.ocap.event.EventManager class. The EventManager instance is 
     * either a singleton for each OCAP application or a singleton for a 
     * whole OCAP implementation. Note that a type cast is necessary for 
     * the return value.
     *
     * @return the instance of org.ocap.event.EventManager.
     */
    public static org.dvb.event.EventManager getInstance() {
        return null;
    }


    /**
     * Get the current UserEventRepository which specify the events to be 
     * filtered. The monitorapplication permission is not necessary to call 
     * this method. This method is used to know which events are filtered 
     * at this moment. The UserEventRepository for event filtering is set 
     * via the setFilteredRepository() method. 
     *
     * @return the current UserEventRepository which specifies the events 
     *          to be filtered. EventManager maintains an empty 
     *          UserEventRepository by default, and this is returned if 
     *          setFilteredRepository() has not yet been called. If 
     *          setFilteredRepository() has been called with a null 
     *          UserEventRepository, then null is returned.
     */
    public org.dvb.event.UserEventRepository getFilteredRepository() {
        return null;
    }

    /**
     * Sets the repository which specifies the events to be filtered. Only 
     * one UserEventRepository instance can be set at a time. Multiple 
     * calls of this method will be result in an update of the 
     * UserEventReposiotry, i.e., the previous UserEventRepository is 
     * discarded and the new one is set. EventManager shall call the 
     * UserEventFilter.filterUserEvent() method only for the events 
     * specified by the UserEventRepository. By default, EventManager has 
     * an empty UserEventRepository, i.e., no 
     * UserEventFilter.filterUserEvent() method  is called. The 
     * monitorapplication permission is necessary to call this method.
     *
     * @param repository     a set of non-ordinary key events for calling 
     *               the UserEventFilter.filterUserEvent() method. 
     *               If null, the UserEventFilter.filterUserEvent() 
     *               method is called for all events except the 
     *               mandatory ordinary key events. 
     *
     * @throws SecurityException   if the caller does not have 
     *               monitorapplication permission("filterUserEvents") 
     *               permission.
     *
     * @throws IllegalArgumentException if UserEventRepository contains 
     *               Mandatory Ordinary keycodes.
     */
    public void setFilteredRepository(
                        org.dvb.event.UserEventRepository repository) {
    }

    /**
     * Set the specified UserEventFilter to modify or consume the event
     * and/or change the applications to deliver the event to.  Only 
     * one UserEventFilter instance can be sent at a time.  Multiple call of 
     * this method will result in update of the UserEventFilter, i.e., the 
     * previous UserEventFilter is discarded and the new one is set.  By
     * default, EventManager has no UserEventFilter (null).  The 
     * monitorapplication permission is necessary to call this method.
     *
     * @param filter    The filter to modify or consume the event and change
     *               the application to be delivered to. 
     *
     * @throws SecurityException   if the caller does not have 
     *               monitorapplication permission("filterUserEvents") permission.
     */
    public void setUserEventFilter(UserEventFilter filter) {
    }
}
