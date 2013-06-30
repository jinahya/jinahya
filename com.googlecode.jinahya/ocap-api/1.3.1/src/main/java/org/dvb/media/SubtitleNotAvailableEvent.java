package org.dvb.media;

import org.davic.media.SubtitlingLanguageControl;

/**
 * Inform an application that a subtitle stream has vanished from the network.
 * This event is not generated on service selection or other forms of 'zapping'. 
 * Its generation is restricted to changes in the composition of the subtitle 
 * aspects of the same broadcast stream.
 */

public class SubtitleNotAvailableEvent
		extends java.util.EventObject {
	/**
	 * Constructor.
	 *
	 * @param source the source of the event. The platform shall always pass
         * in the JMF player presenting the subtitles.
	 */

	public SubtitleNotAvailableEvent(Object source)
	{
		super(source);
	}

	/**
	 * Return the source of the event.
	 *
	 * @return the source of the event. This shall be the JMF player passed
         * in to the constructor of the event.
	 */
	public java.lang.Object getSource()
	{
		return null;
	}
} 


