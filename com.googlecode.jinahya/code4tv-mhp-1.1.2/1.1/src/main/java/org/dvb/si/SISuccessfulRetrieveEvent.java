package org.dvb.si;

/**
  * This event is sent in response to a SI retrieval request when
  * the retrieve request was successfully completed. 
  * The result of the request can be obtained from the getResult method.
  * @see SIRetrievalListener
  */

public class SISuccessfulRetrieveEvent extends SIRetrievalEvent {
			      			      
  /** The constructor for the event
    * @param appData the application data passed in the request method call
    * @param request the SIRequest instance which is the source of the event
    * @param result an SIIterator containing the retrieved objects
    */
  public SISuccessfulRetrieveEvent(Object appData, SIRequest request, SIIterator result) {
    super(appData, request);
  }

  /** Returns the requested data in an SIIterator object.
    * @return An SIIterator containing the requested objects
    * 
    * @see SIObjectNotInTableEvent
    */
  public SIIterator getResult() {
    return (null);
  }


}

