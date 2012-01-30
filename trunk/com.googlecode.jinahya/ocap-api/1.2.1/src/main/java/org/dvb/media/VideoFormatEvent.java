package org.dvb.media;

/** 
 * The base class for all other events relating to changes in video format
 */
public abstract class VideoFormatEvent extends java.util.EventObject
{
	/**
	 * Constructor
	 *
	 * @param source the source of the event. The platform shall always
         * pass in the JMF Player presenting the video whose format changed.
	 */
	public VideoFormatEvent(Object source)
	{

		super(source);
	}
}

