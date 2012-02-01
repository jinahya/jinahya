package org.dvb.si;

/**
  * This event is sent in response to a SI retrieval request when
  * the request was made with the FROM_CACHE_ONLY mode and the requested data 
  * is not present in the cache.
  * @see SIRetrievalListener
  */

public class SINotInCacheEvent extends SIRetrievalEvent {
			      			      
  /** The constructor for the event
    * @param appData the application data passed in the request method call
    * @param request the SIRequest instance which is the source of the event
    */
  public SINotInCacheEvent(Object appData, SIRequest request) {
    super(appData, request);
  }

}

