package org.ocap.shared.media;

import javax.media.Controller;
import javax.media.ControllerEvent;

/**
 * This event shall be generated during timeshift playback when the playback point
 * departs from the head of the buffer (where the content is the same as the
 * currently received content). Examples of this include the following;
 * <ul>
 * <li>Pause
 * <li>Forward play with rate < 1.0
 * <li>Rewind
 * <li>Playback jumps to a previous time
 * </ul>
 * LeavingLiveModeEvent is a ControllerEvent that is posted when the 
 * the controller is not playing back a live broadcast stream anymore. 
 * This event is sent to a registered ControllerListener in addition to
 * any RateChangeEvent or MediaTimeSetEvent.
 */
public class LeavingLiveModeEvent extends ControllerEvent
{
    /**
     * Create a LeavingLiveModeEvent.  
	 *
	 * @param from the controller that is generating the event.
     *
     */
    public LeavingLiveModeEvent(Controller from) 
	{
			super(from);
	}
}

