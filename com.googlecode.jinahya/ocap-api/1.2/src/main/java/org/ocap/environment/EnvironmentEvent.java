package org.ocap.environment;

import java.util.*;

/**
 * The <code>EnvironmentEvent</code> class is used to notify 
 * applications of events relating to environments.
 */
public abstract class EnvironmentEvent  extends EventObject {

	/**
	 * Simple constructor for these events.
	 * @param source the environment which is the source
	 * of this event.
	 */
	public EnvironmentEvent( Environment source )
	{
		super( (Object) source );
	}
}
