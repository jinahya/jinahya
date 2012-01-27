package org.dvb.si;

/**
  * This event is sent in response to a SI retrieval request when
  * the SI table that should contain the requested information could
  * not be retrieved. The reason may be that the requested table is 
  * not broadcast in the transport stream currently associated
  * with the SI database. 
  * @see SIRetrievalListener
  */

public class SITableNotFoundEvent extends SIRetrievalEvent {
			      			      
  /** The constructor for the event
    * @param appData the application data passed in the request method call
    * @param request the SIRequest instance which is the source of the event
    */
  public SITableNotFoundEvent(Object appData, SIRequest request) {
    super(appData, request);
  }

}

