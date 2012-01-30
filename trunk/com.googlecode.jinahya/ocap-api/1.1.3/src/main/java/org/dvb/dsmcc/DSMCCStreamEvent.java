

package org.dvb.dsmcc;

import java.io.*;

/** 
 * The DSMCCStreamEvent class is used to manage DSMCC StreamEvent Objects.
 * Applications wishing to monitor changes in the list of events which are
 * part of this stream event should use <code>DSMCCObject.addObjectChangeEventListener</code>
 * on the <code>DSMCCObject</code> representing which describes this set of stream events.
 * The BIOP::StreamEvent message shall be read from the network once only, before the constructor 
 * of this class returns. Hence methods which return information from that message shall not 
 * be effected by any subsequent changes to that information.<p>
 * The subscribe method only verifies that the event name can be bound to an eventId but it 
 * does not require that the stream event descriptors for that event id can be received at 
 * that moment. While the event listener is registered the MHP terminal shall filter the 
 * stream event descriptors as specified in the "Monitoring stream events" clause in the main body of the 
 * specification.
*/

public class DSMCCStreamEvent extends DSMCCStream {
  /**
	* Create a <code>DSMCCStreamEvent</code> from a <code>DSMCCObject</code>. 
	* The Object has to be a DSMCC StreamEvent.
	* @param aDSMCCObject the DSMCC object which describes the stream.
	* @exception NotLoadedException the DSMCCObject is not loaded.
	* @exception IllegalObjectTypeException the DSMCCObject does not lead to a DSMCC StreamEvent.
	*/
  public DSMCCStreamEvent(DSMCCObject aDSMCCObject)
		throws NotLoadedException, IllegalObjectTypeException
	{ super (aDSMCCObject);

	}

 /**
	* Create a DSMCCStreamEvent Object from its pathname. 
	* The path has to lead to a DSMCCStreamEvent.
	*
	* @param path the pathname of the DSMCCStreamEvent object
	* @exception IOException An IO error has occurred.
	* @exception IllegalObjectTypeException the path does not lead to a DSMCC StreamEvent.
	*/
  public DSMCCStreamEvent(String path)
		throws IOException, IllegalObjectTypeException
	{super(path);
	}
  
  
  /**
	* Create a DSMCCStreamEvent from its pathname. For an object Carousel, this
	* method will block until the module which contains the object is loaded. 
	* The path has to lead to a DSMCC Stream Event
	* @param path the directory path.
	* @param name the name of the DSMCCStreamEvent Object.
	* @exception IOException If an IO error occurred.
	* @exception IllegalObjectTypeException the path does not lead to a DSMCC StreamEvent.
	*/
  public DSMCCStreamEvent(String path, String name)
		throws IOException, IllegalObjectTypeException
	{super(path, name);
	}  
  
  
  
  /**
	* This function is used to subscribe to an event of a DSMCC
	* StreamEvent object.
	* @param eventName the name of the event.
	* @param l an object that implements the StreamEventListener Interface.
	* @return The event Identifier.
	* @exception UnknownEventException the event cannot be found at this time
	* @exception InsufficientResourcesException if resources needed to perform
        * the subscription are not available
	*/
  public synchronized int subscribe(String eventName, StreamEventListener l)
		throws UnknownEventException, InsufficientResourcesException
	{return 0;
	}
  
   /**
	* This function is used to cancel the subscription to an event of a
	* DSMCCEvent object.
	* @param eventId Identifier of the event.
	* @param l an object that implements the StreamEventListener Interface.
	* @exception UnknownEventException The event can not be found.
	*/
  public synchronized void unsubscribe(int eventId, StreamEventListener l)
		throws UnknownEventException
	{
	}
  
	/**
		* This function is used to cancel the subscription to an event of a
		* DSMCCEvent object.
		* @param eventName the name of the event.
		* @param l an object that implements the StreamEventListener Interface.
		* @exception UnknownEventException The event can not be found.
		*/
  public synchronized void unsubscribe(String eventName, StreamEventListener l)
		throws UnknownEventException
	{
	}
  
  
  /**
		* This function is used to get the list of the events of
		* the DSMCCStreamEvent object.
		* @return The list of the eventName.
		*/
  public String [] getEventList()
	{ return null;
	}

}


