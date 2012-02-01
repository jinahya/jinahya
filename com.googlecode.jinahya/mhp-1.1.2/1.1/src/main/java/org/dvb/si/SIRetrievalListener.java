package org.dvb.si;

/**
  * This interface shall be implemented by application classes in order 
  * to receive events about completion of SI requests.
  * @see SIRetrievalEvent
  */

public interface SIRetrievalListener extends java.util.EventListener {

  /**
    * This method is called by the SI API implementation to notify the 
    * listener about completion of an SI request.
    * @param event The event object.
    * @see SIRetrievalEvent
    */
  public void postRetrievalEvent(SIRetrievalEvent event);

}

