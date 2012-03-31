package org.dvb.si;

/**
  * This event is sent in response to a SI retrieval request when
  * the SI table where the information about the requested object
  * should be located has been retrieved but the requested object 
  * is not present in it. 
  * The reason may be that the object corresponding to the requested
  * identifiers does not exist.
  * @see SIRetrievalListener
  */

public class SIObjectNotInTableEvent extends SIRetrievalEvent {
			      			      
  /** The constructor for the event
    * @param appData the application data passed in the request method call
    * @param request the SIRequest instance which is the source of the event
    */
  public SIObjectNotInTableEvent(Object appData, SIRequest request) {
    super(appData, request);
  }

}

