package org.dvb.dsmcc;

/**
 * Sent only when the rate of an NPT time-base changes value.
 * @since MHP 1.0.1
 */

public class NPTRateChangeEvent extends java.util.EventObject {
	/**
	 * Construct an event.
	 * @param source the stream whose rate changed
	 * @param rate the new rate of that stream immediately following the change
	 */
	public NPTRateChangeEvent( DSMCCStream source, NPTRate rate ){super( (Object)source);}
	
	/**
	 * Return the stream whose rate changed.
	 * @return the <code>DSMCCStream</code> object on which the rate change has occurred.
	 */
	public java.lang.Object getSource() {return null;}

	/**
	 * Return the new rate of the stream immediately after the change.
	 * @return a <code>NPTRate</code> object encapsulating the new rate
	 */
	public NPTRate getRate() { return null;}
}

