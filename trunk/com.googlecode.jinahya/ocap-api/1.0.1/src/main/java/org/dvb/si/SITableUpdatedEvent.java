package org.dvb.si;

/**
  * This event is sent in response to a SI descriptor retrieval 
  * request when the table carrying the information about the 
  * object has been updated and the set of descriptors consistent
  * with the old object can not be retrieved. The application should
  * in this case first update the SIInformation object and then
  * request the descriptors again.
  *
  * @see SIRetrievalListener
  */

public class SITableUpdatedEvent extends SIRetrievalEvent {
			      			      
  /** The constructor for the event
    * @param appData the application data passed in the request method call
    * @param request the SIRequest instance which is the source of the event
    */
  public SITableUpdatedEvent(Object appData, SIRequest request) {
    super(appData, request);
  }

}

