package org.ocap.shared.media;

import javax.media.Controller;
import javax.media.RateChangeEvent;

/**
 * BeginningOfContentEvent is a RateChangeEvent that is posted when the rate change is 
 * due to a rewind hitting the beginning of the media, or due to the time-shift 
 * buffer reaching maximum depth. 
 */
public class BeginningOfContentEvent extends RateChangeEvent
{
    /**
     * Create a BeginningOfContentEvent.  
     *
     * @param from the controller that is generating the event.
     * @param newRate the new rate following the rate change
     *
     */
    public BeginningOfContentEvent(Controller from, float newRate) 
	{
			super(from, newRate);
	}
}

