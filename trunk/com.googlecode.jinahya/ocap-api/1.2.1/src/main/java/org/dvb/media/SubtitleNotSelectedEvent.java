package org.dvb.media;

import org.davic.media.SubtitlingLanguageControl;

/**
 * Report that subtitles are not now selected. Even if subtitles are 
 * available in the network, they will not be presented.
 * This event is generated when the combination of 
 * end user control of subtitles through the navigator and application control of 
 * subtitles through <code>SubtitlingLanguageControl.setSubtitling</code> changes 
 * whether subtitles are to be presented if they are available. It is not generated
 * for changes in the underlying availability of subtitles even if those cause 
 * changes in whether subtitles are presented or not.
 */
public class SubtitleNotSelectedEvent
		extends java.util.EventObject {
	/** 
	 * Constructor
	 *
	 * @param source the source of the event. The platform shall always pass
         * in the JMF player presenting the subtitles.
	 */
	public SubtitleNotSelectedEvent(Object source)
	{
		super( (Object) source );
	}
	/**
	 * Return the source of the event
	 *
	 * @return the source of the event. This shall be the JMF player passed
         * in to the constructor of the event.
	 */
	public java.lang.Object getSource()
	{
		return null;
	}
}



