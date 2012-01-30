package org.davic.media;

/**
 * Generated if a Player has some or all of the resources used withdrawn for some reason. 
 * The Player remains in a potentially usable condition unlike javax.media.ResourceUnavailableEvent. 
 * The posting of this event does not interfere with the state machine of the player, it does not change 
 * state as a result of this event. If the player is in the playing state it still may be (partially) 
 * rendering the content. This indicative event is only posted once before the accompanying 
 * ResourceReturnedEvent is posted (even if sequentially different resources are lost at different 
 * points in time).
 */
public class ResourceWithdrawnEvent extends javax.media.ControllerEvent
{
	/**
	 * Build an event
	 * @param controller the controller which generates the event
	 */
	public ResourceWithdrawnEvent(javax.media.Controller controller)
	{
		super(controller);
	}
}


