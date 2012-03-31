package org.dvb.media;

/**
 * Event signalling that the decoder format conversion being used 
 * has changed
 */
public class DFCChangedEvent extends VideoFormatEvent
{
	/**
	 * Construct the event
	 * 
	 * @param source the source of the event
	 * @param newDFC the new decoder format conversion being used
	 */
	public DFCChangedEvent(Object source, int newDFC) {
		super(source);
	}
	
	/**
	 * Get the new decoder format conversion
	 * 
	 * @return the new decoder format conversion.  The value of this is 
	 * represented by one of the constants from the VideoFormatControl class
         * and shall be the value passed into the constructor of the event.
	 */
	public int getNewDFC() {
		return 0;
	}
}

