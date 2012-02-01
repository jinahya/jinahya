package org.dvb.dsmcc;

/**

 * Sent to listeners on a DSMCCStream object when a broadcast timeline newly appears for that DSMCC stream  

 * when it was not previously present. This is specific to the particular timebase for this 
 * stream.
 * @since MHP 1.0.1
 */

public class NPTPresentEvent extends NPTStatusEvent {
	/**
	 * Construct an event.

	 * @param source the DSMCCStream for which the broadcast timeline appeared.

	 */
	public NPTPresentEvent( DSMCCStream source ){super(source);}
}

