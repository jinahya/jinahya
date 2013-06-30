package org.ocap.shared.media;

import javax.media.Controller;
import javax.media.RateChangeEvent;

/**
 * EndOfContentEvent is a RateChangeEvent that is posted when the rate change is 
 * due to a forward playback hitting the end of the stored context, or a forward 
 * playback catching up with the live recording point.
 */
public class EndOfContentEvent extends RateChangeEvent
{
    /**
     * Create a EndOfContentEvent.  
     *
     * @param from the controller that is generating the event.
     * @param newRate the new rate following the rate change
     *
     */
    public EndOfContentEvent(Controller from, float newRate) 
	{
			super(from, newRate);
	}
}

