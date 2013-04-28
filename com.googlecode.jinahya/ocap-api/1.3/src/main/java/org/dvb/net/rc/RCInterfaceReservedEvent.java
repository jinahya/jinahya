package org.dvb.net.rc;

import org.davic.resources.ResourceStatusEvent;

/** 
 * This event informs an application that a <code>RCInterface</code>
 * has been reserved by an application or other entity in the system. It is
 * generated when an application successfully reserves a <code>RCInterface</code>.
 * It will also be generated if any other entities in the system reserve such
 * an interface with the effect of something which was visible to applications
 * using this API becoming unavailable.
 */

public class RCInterfaceReservedEvent extends ResourceStatusEvent 
{
	/**
	 * Constructor for the event
	 * @param bg the <code>RCInterface</code> representing the device which has been reserved
	 */
	public RCInterfaceReservedEvent(Object bg) 
	{
		super(bg);
	}

	/**
	 * Returns the device that has been reserved
	 *
	 * @return an <code>RCInterface</code> representing the device that has been reserved
	 */
	public Object getSource()
	{
		return null;
	}
}

