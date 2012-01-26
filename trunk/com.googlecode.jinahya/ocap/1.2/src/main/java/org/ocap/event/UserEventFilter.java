//
// UserEventFilter.java
//
// Created October 24, 2001, 11:50 AM

package org.ocap.event;

/**
 * Only one instance of the class that implements this interface can be 
 * registered to EventManager via the EventManager.setUserEventFilter() 
 * method. EventManager calls UserEventFilter.filterUserEvent() before a
 * UserEvent is forwarded to any listening applications for events specified 
 * by the UserEventRepository set via the EventManager.setFilteredRepository() 
 * method.
 */
public interface UserEventFilter
{
    /**
     * Called by the platform to filter user input events specified in the 
     * UserEventRepository when the event is received. The EventFilter may 
     * modify values in the UserEvent and/or change the applications to which 
     * the event is forwarded. The UserEventFilter.filterUserEvent() returns 
     * a UserEventAction instance which specifies the event to be forwarded
     * and the list of AppIDs to which the event is forwarded. If it returns 
     * null, the event is consumed, i.e., the event is not forwarded to any 
     * applications.
     *
     *@param e the UserEvent which was received
     *
     *@return null if the event is to be terminated.  Otherwise, a 
     * UserEventAction object that contains the event to distribute,
     * and a list of AppIDs to which to distribute it.
     */
    public UserEventAction filterUserEvent(UserEvent e);
}
