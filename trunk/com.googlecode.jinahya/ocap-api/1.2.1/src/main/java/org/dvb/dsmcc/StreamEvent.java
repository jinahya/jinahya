
package org.dvb.dsmcc;

import java.util.*;

/** 
  * This class describes a Stream event which is used to synchronize
  * an application with an MPEG Stream. 
  * NOTE: The NPT mechanism and scheduled stream events that depend on it 
  * are known to be vulnerable to disruption in many digital TV distribution 
  * networks. Existing deployed network equipment that re-generates the STC 
  * is unlikely to be aware of NPT and hence will not make the necessary 
  * corresponding modification to STC values inside NPT reference descriptors.
  * This may cause scheduled stream events to fire at the wrong time or to 
  * never fire at all. Applications should only use scheduled stream events 
  * where they are confident that the network where they are to be used does 
  * not have this problem.
  */

public class StreamEvent extends java.util.EventObject {


	/**
	 * Creates a StreamEvent object.	
	 *
	 * @param source The <code>DSMCCStreamEvent</code> that has generated the event. 
 	 * @param npt The value of the NPT (Normal Play Time) when the event is triggered. This 
 	 * value is equal to the field eventNPT in the DSMCC StreamEventDescriptor except where 
	 * the event is a "do it now" event in which case the value -1 is returned (as the value 
	 * of NPT may not be meaningful). 
	 * @param name The name of this event. The list of event names is located in the DSMCC 
	 * StreamEvent object. This list is returned by the method <code>DSMCCStreamEvent.getEventList</code>.
	 * @param eventId The eventId of this event. The list of event IDs is located in the 
	 * DSMCC StreamEvent object. 
	 * @param eventData The application specific data found in the DSMCC StreamEventDescriptor. 
	 */
	public StreamEvent(DSMCCStreamEvent source, long npt, String name, int eventId, byte[] eventData)
	{super(source);
	}
  
	/**
	 * This method returns the DSMCCStreamEvent that generated the event.
         *
	 * @return the DSMCCStreamEvent that generated the event.
	 */ 
	public java.lang.Object getSource() {return null;}



  /** 
	* This method is used to get the name of the StreamEvent 
	* @return the name of the StreamEvent 
	*/
  public String getEventName()
	{ return null;
	}
  
  /** 
	* This method is used to get the identifier of the StreamEvent.
	* @return The identifier of the StreamEvent.
	*/
  public int getEventId()
	{ return 0;
	}
  
  /**
	* This method is used to get the NPT of the Event in milliseconds.
	* @return The NPT of the Event in milliseconds.
	*/
  public long getEventNPT()
	{ return (long) 0;
	}
  
  /**
	* This method is used to retrieve the private data associated
	* with the event.
	* @return The private data associated with the event.
	*/
  public byte[] getEventData()
	{ return null;
	}

}

