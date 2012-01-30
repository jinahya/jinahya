/*
 * Created on Apr 25, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.ocap.system.event;

import java.lang.*;

/**
 * This class represents an event returned by the system when a deferred download is 
 * instigated by the receipt of a code version table with download_command = 0x01 (see
 * [CCIF 2.0]). 
 */

public class DeferredDownloadEvent extends SystemEvent{

	   /**
     * System event constructor assigns an eventId, Date, and ApplicationIdentifier.
     *
     * @param typeCode - Unique event type.
     *
     * @throws IllegalArgumentException if the typeCode is not in a defined application range
     * when the event is created by an application.
     */
	public DeferredDownloadEvent (int typeCode){
		super(typeCode);
	}
}
