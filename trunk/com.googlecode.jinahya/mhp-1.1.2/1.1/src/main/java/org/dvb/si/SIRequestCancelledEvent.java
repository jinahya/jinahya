package org.dvb.si;

/**
  * This event is sent in response to a SI retrieval request when
  * the request is cancelled with the SIRequest.cancelRequest method call.
  * @see SIRequest
  * @see SIRetrievalListener
  */

public class SIRequestCancelledEvent extends SIRetrievalEvent {
			      			      
  /** The constructor for the event
    * @param appData the application data passed in the request method call
    * @param request the SIRequest instance which is the source of the event
    */
  public SIRequestCancelledEvent(Object appData, SIRequest request) {
    super(appData, request);
  }

}

