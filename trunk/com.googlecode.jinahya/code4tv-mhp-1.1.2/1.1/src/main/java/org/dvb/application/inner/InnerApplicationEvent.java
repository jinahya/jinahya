package org.dvb.application.inner;

/**
 * InnerApplicationEvent is used to notify applications which have registered 
 * interest in events coming from inner applications that such an event has 
 * happened.
 */

public class InnerApplicationEvent extends java.util.EventObject {
	/**
	 * Constructor for an event.
	 *
	 * @param source the <code>InnerApplicationContainer</code> which generated the event.
	 */
	public InnerApplicationEvent(InnerApplicationContainer source){
		super((Object)source);
	}
}

