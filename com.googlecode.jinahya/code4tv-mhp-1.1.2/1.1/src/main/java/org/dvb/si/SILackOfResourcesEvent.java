package org.dvb.si;

/**
  * This event is sent in response to a SI retrieval request when
  * the resources needed for retrieving the data are not available,
  * e.g. due to the necessary resources being all taken up by the 
  * calling application or other applications.
  * @see SIRetrievalListener
  */

public class SILackOfResourcesEvent extends SIRetrievalEvent {
			      			      
  /** The constructor for the event
    * @param appData the application data passed in the request method call
    * @param request the SIRequest instance which is the source of the event
    */
  public SILackOfResourcesEvent(Object appData, SIRequest request) {
    super(appData, request);
  }

}

