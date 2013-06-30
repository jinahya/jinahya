package org.davic.media;

/**
 * Generated if a Player which was stopped due to having its resources withdrawn has had those 
 * resources returned to it. The posting of this event does not interfere with the state machine 
 * of the player, it does not change state as a result of this event. This indicative event is 
 * only posted once after all the resources required for the current state of the player have become 
 * available again and it is performing as is expected in the current state. This event is only 
 * posted as a result of an earlier ResourceWithDrawnEvent.
 */
public class ResourceReturnedEvent extends javax.media.ControllerEvent{
	/**
	 * Build an event
	 * @param controller the which generates the event
	 */
	public ResourceReturnedEvent(javax.media.Controller controller)
	{
		super(controller);
	}
}


