   package org.dvb.event ;

/**
 * The event manager allows an application to receive events coming from the 
 * user. These events can be sent exclusively to an application or can be 
 * shared between applications. The Event Manager allows also the application 
 * to ask for exclusive access to some events, these events being received 
 * either from the standard java.awt event mechanism or by the mechanism 
 * defined in this package. The EventManager is either a singleton for each MHP 
 * application or a singleton for the MHP terminal.<p>
 * The right to receive events is considered as the same resource regardless of
 * whether it is being handled exclusively or shared. An application successfully
 * obtaining exclusive access to an event results in all other applications losing
 * access to that event, whether the access of those applications was shared or
 * exclusive.
 */
public class EventManager implements org.davic.resources.ResourceServer {

        /**
         * Constructor for instances of this class. This constructor is provided for
         * the use of implementations and specifications which extend the present document.
         * Applications shall not define sub-classes of this class. Implementations are not
         * required to behave correctly if any such application defined sub-classes are used.
         */    
	protected EventManager() {}

    /**
     * Adds the specified listener to receive events coming from the user in 
     * an exclusive manner. The events the application wishes to receive are 
     * defined by the means of the UserEventRepository class. This repository 
     * is resolved at the time when this method call is made and adding or 
     * removing events from the repository after this method call does not 
     * affect the subscription to those events.
     * The ResourceClient parameter indicates that the application wants to 
     * have an exclusive access to the user event defined in the repository.
     * <p>The effect of multiple calls to this method by the same application with different 
     * instances of UserEventRepository shall be cumulative. If multiple calls to this
     * method succeed in acquiring the events in the specified repositories then the 
     * semantics of each successful method call shall be obeyed as specified. Note that
     * this can result in applications receiving the same event through more than one
     * event listener.
     *
     * @param listener the listener to receive the user events.
     * @param client resource client.
     * @param userEvents a class which contains the user events it wants to be
     * informed of.
     *
     * @return true if the events defined in the repository have been acquired,
     * false otherwise.
     *
     * @exception IllegalArgumentException if the client argument is set to 
     * null.
     */
    public boolean addUserEventListener
	(UserEventListener listener, 
	 org.davic.resources.ResourceClient client,
	 UserEventRepository userEvents) { return false;}
   
    /**
     * Adds the specified listener to receive events coming from the user. 
     * The events the application wishes to receive are defined by the means 
     * of the UserEventRepository class. This repository is resolved at the 
     * time when this method call is made and adding or removing events from 
     * the repository after this method call does not affect the subscription 
     * to those events.
     * <p>The effect of multiple calls to this method by the same application with different 
     * instances of UserEventRepository shall be cumulative. If multiple calls to this
     * method succeed in acquiring the events in the specified repositories then the 
     * semantics of each successful method call shall be obeyed as specified. Note that
     * this can result in applications receiving the same event through more than one
     * event listener.
     *
     * @param listener the listener to receive the user events.
     * @param userEvents a class which contains the user events it wants to be
     * informed of.
     */ 
    public void addUserEventListener 
	(UserEventListener listener, 
	 UserEventRepository userEvents) {}
    
    /**
     * An application should use this method to express its intend to have 
     * exclusive access to some events, but for these events to be received 
     * through the java.awt mechanism. The events the application wishes to 
     * receive are defined by the means of the UserEventRepository class. 
     * This repository is resolved at the time when this method call is made 
     * and adding or removing events from the repository after this method 
     * call does not affect the subscription to those events. An exclusive
     * event will be sent to the application if this latest is focused.
     * <p>The effect of multiple calls to this method by the same application with different 
     * instances of UserEventRepository shall be cumulative. If multiple calls to this
     * method succeed in acquiring the events in the specified repositories then the 
     * semantics of each successful method call shall be obeyed as specified. 
     *
     * @param client resource client.
     * @param userEvents the user events the application wants to be inform of.
     *
     * @return true if the events defined in the repository have been acquired,
     * false otherwise.
     *
     * @exception IllegalArgumentException if the client argument is set to
     * null.
     */
    public boolean addExclusiveAccessToAWTEvent 
	(org.davic.resources.ResourceClient client, 
	 UserEventRepository userEvents) {return false;}
    
    /**
     * Removes the specified listener so that it will no longer receives user 
     * events. If it is appropriate (i.e the application has asked for an 
     * exclusive access), the exclusive access is lost.
     * 
     * @param listener the user event listener.
     */
    public void removeUserEventListener 
	(UserEventListener listener) {}
   
    /**
     * The application should use this method to release its exclusive access 
     * to user events defined by the means of the addExclusiveAccessToAWTEvent
     * method.
     *
     * @param client the client that is no longer interested in events 
     * previously registered.
     */
    public void removeExclusiveAccessToAWTEvent 
	(org.davic.resources.ResourceClient client) {}
   	
    /**
     * This method returns the sole instance of the EventManager class. The 
     * EventManager class is a singleton.
     *
     * @return the instance of the EventManager.
     */
    public static EventManager getInstance () {return null;}

    /**
     * Adds the specified resource status listener so that an application
     * can be aware of any changes regarding exclusive access to some events.
     *
     * @param listener the resource status listener.
     */
    public void addResourceStatusEventListener 
	(org.davic.resources.ResourceStatusListener listener) {
    }

    /**
     * Removes the specified resource status listener.
     *
     * @param listener the listener to remove.
     */
    public void removeResourceStatusEventListener 
	(org.davic.resources.ResourceStatusListener listener) {
    }
}




