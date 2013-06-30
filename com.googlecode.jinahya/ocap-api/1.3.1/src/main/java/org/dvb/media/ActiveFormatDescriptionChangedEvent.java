package org.dvb.media;

/**
 * Event signalling that the transmitted active format definition has changed
 */
public class ActiveFormatDescriptionChangedEvent extends VideoFormatEvent
{
	/**
	 * Construct the event
	 * 
	 * @param source the source of the event
	 * @param newFormat the new active format description
	 */
	public ActiveFormatDescriptionChangedEvent(Object source, int newFormat) {
		super(source);
	}
	
	/**
	 * Get the new active format description
	 * 
	 * @return the new active format description.  The value of this is 
         * represented by one of the constants from {@link VideoFormatControl}
         * and shall be the value passed into the constructor of the event.
	 */
	public int getNewFormat() {
		return 0;
	}
}

