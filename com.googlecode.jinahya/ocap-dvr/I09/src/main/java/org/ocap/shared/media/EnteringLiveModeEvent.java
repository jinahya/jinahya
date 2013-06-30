package org.ocap.shared.media;

import javax.media.Controller;
import javax.media.ControllerEvent;

/**
 * EnteringLiveModeEvent is a ControllerEvent that is posted when the 
 * the controller has started playing back a live broadcast stream. 
 * This event is sent to a registered ControllerListener in addition to
 * any RateChangeEvent or MediaTimeSetEvent.
 */
public class EnteringLiveModeEvent extends ControllerEvent
{
    /**
     * Create a EnteringLiveModeEvent.  
	 *
	 * @param from the controller that is generating the event.
     *
     */
    public EnteringLiveModeEvent(Controller from) 
	{
			super(from);
	}
}

