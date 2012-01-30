package org.dvb.dsmcc;

/**
 * Sent when an MHP terminal detects a permanent discontinuity in NPT
 * as defined in the main body of the present document. This represents an 
 * error condition in the incoming broadcast.<p>
 * This event shall be sent following a PCR discontinuity when the MHP
 * terminal has enough information to determine that there will be an
 * NPT discontinuity. If the <code>NPTDiscontinuityEvent</code> is because of invalid data in a new
 * NPTReferenceDescriptor then the event will be generated when that
 * new NPTReferenceDescriptor is detected by the MHP terminal. If the <code>NPTDiscontinuityEvent</code>
 * is because no new NPTReferenceDescriptor is detected within the time
 * allowed by the main body of the present document then it will be generated
 * when that time interval has elapsed.
 * @since MHP 1.0.1
 */

public class NPTDiscontinuityEvent extends NPTStatusEvent {
	/**
	 * Construct an event. The <code>before</code> and <code>after</code> values 
	 * used shall be the values at the time when the receiver determined that a
	 * NPT discontinuity has happened. If the <code>NPTDiscontinuityEvent</code> is because of invalid data in a
 	 * new NPTReferenceDescriptor then this is the time when that new
	 * descriptor was known to be invalid. If <code>NPTDiscontinuityEvent</code> is because of the absence of
	 * a new NPTReferenceDescriptor then this will be when the MHP terminal
	 * detects that the time interval allowed by the present document for such
	 * new descriptors has elapsed. Where an NPT value is unknown or cannot be computed, -1 shall be used.
	 * @param source the stream whose NPT suffered a discontinuity
	 * @param before the last NPT value detected before the discontinuity
	 * @param after the first NPT value detected after the discontinuity
	 */
	public NPTDiscontinuityEvent( DSMCCStream source, long before, long after )
	{super(source);}

	/**
	 * Return the last known stable value of NPT before the discontinuity
	 * @return an NPT value
	 */	
	public long getLastNPT() { return 0;}

	/**
	 * Return the first known stable value of NPT after the discontinuity
	 * @return an NPT value
	 */	
	public long getFirstNPT() { return 0;}

}

