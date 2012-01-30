package org.dvb.dsmcc;

/**
 * Sent to listeners on a DSMCCStream object when NPT which was present for that DSMCC 
 * stream is removed. This is specific to the particular timebase for this stream.
 * @since MHP 1.0.1
 */

public class NPTRemovedEvent extends NPTStatusEvent {
	/**
	 * Construct an event.
	 * @param source the DSMCCStream from which the NPT was removed
	 */
	public NPTRemovedEvent( DSMCCStream source ){ super(source);}
}

