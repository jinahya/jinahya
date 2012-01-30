package org.dvb.media;

import javax.media.ControllerEvent;
import javax.media.Controller;

/**
 * This event is generated whenever the content being presented by a player changes for reasons outside the control of the application. The state of the player does not change - only the content being presented.
 */

public class PresentationChangedEvent extends ControllerEvent 
{
	/**
 	 * Constructor for the event
	 *
	 * @param source the controller whose presentation changed
	 * @param stream the stream now being presented. 
	 * @param reason the reason for the change encoded as one of the constants in this class
	 */
	
	public PresentationChangedEvent (Controller source, 
		javax.media.MediaLocator stream, int reason) {
		super(source);
	}

	/**
	 * The stream being presented is no longer available in the transport stream.
	 * 
	 * @see PresentationChangedEvent#getReason
	 */

	public static final int STREAM_UNAVAILABLE = 0x00;

	/**
	 * Presentation changed due an action by the CA subsystem. Alternate content is being played, not the content selected by the user (e.g. adverts in place of a scrambled service)
	 * 
	 * @see PresentationChangedEvent#getReason
	 */

	public static final int CA_FAILURE = 0x01;
	
	/**
	 * Presentation changed due to an action by the CA subsystem. Normal 
	 * content is now being presented as requested by the user. This reason code 
	 * is used when the CA subsystem commands the MHP terminal to switch back 
	 * to the normal presentation after having previously selected an alternate content.
	 *
	 * @see PresentationChangedEvent#getReason
	 */
	public static final int CA_RETURNED = 0x02;

	/**
	 * This method returns the locator for the stream now being presented.
	 * 
	 * @return the locator for the stream now being presented
	 */
	public javax.media.MediaLocator getStream()
	{	
		return null;
	}

	/**
	 * This method returns the reason why access has been withdrawn.
	 * 
	 * @return the reason for the change specified when the event was constructed
	 */

	public int getReason()
	{
		return 0;
	}
}



