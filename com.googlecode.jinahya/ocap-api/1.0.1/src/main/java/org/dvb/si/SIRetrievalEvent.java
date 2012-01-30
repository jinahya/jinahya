package org.dvb.si;

/**
  * This class is the base class for events about completion of a SI retrieval request.
  * Exactly one event will be returned in response to an SI retrieval request.
  * @see SIRetrievalListener
  */

public abstract class SIRetrievalEvent extends java.util.EventObject {
			      			      
  /** The constructor for the event
    * @param appData the application data passed in the request method call
    * @param request the SIRequest instance which is the source of the event
    */
  public SIRetrievalEvent(Object appData, SIRequest request) {
	  super(request);
  }

  /** Returns the application data that was passed to the retrieve method
    * @return the application data
    */
  public Object getAppData() {
    return null;
  }

  /** Returns the SIRequest object that is the source of this event
    * @return the SIRequest object
    */
  public Object getSource() {
    return null;
  }

}

