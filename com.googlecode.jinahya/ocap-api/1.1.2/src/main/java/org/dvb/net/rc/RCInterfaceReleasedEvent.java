package org.dvb.net.rc;

import org.davic.resources.ResourceStatusEvent;

/** 
 * This event informs an application that a <code>RCInterface</code>
 * has been released by an application or other entity in the system. It is
 * generated when an application which had successfully reserved a 
 * <code>RCInterface</code> calls the 
 * <code>ConnectionRCInterface.release</code> method. It will also
 * be generated if any other entities in the system own such an interface
 * and then release that interface in such a way that it could then become
 * available to applications using this API.
 */

public class RCInterfaceReleasedEvent extends ResourceStatusEvent 
{
	/**
	 * Constructor for the event
	 * @param bg the <code>RCInterface</code> which has been released
	 */
	public RCInterfaceReleasedEvent(Object bg) 
	{
		super(bg);
	}

	/**
	 * Returns the device that has been released
	 *
	 * @return the <code>RCInterface</code> object representing the interface that has been released
	 */
	public Object getSource()
	{
		return null;
	}
}

