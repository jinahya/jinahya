package org.davic.net.ca;

/**
  * In systems based upon the DVB Common Interface this event is generated in response to the 
  * Host Control tune request. <p>
  * NOTE 1:This event is only guaranteed to be delivered to applications that survive the 
  * service selection caused by the Host Control tune request (see 11.6.4,"Conditional Access API"on page 125). <p>
  * NOTE 2:This event is for information only.The platform is responsible for implementing the service selection 
  * autonomously in response to the request from the CA system.
  */
public class TuneRequestEvent extends CAEvent {

  /** Constructor for the event
    * @param locator a Locator pointing to the replacement broadcast service
    * @param caModule the CAModule object which is the source of the event
    */
  public TuneRequestEvent(org.davic.net.Locator locator, Object caModule) {
  }

  /** Returns a Locator pointing to the service to which 
    * the CA module requests to tune to
    * @return a Locator pointing to a broadcast service
    */
  public org.davic.net.Locator getLocator() {
    return null;
  }

  /** Returns the CAModule that is the source of the event 
    */
  public Object getSource() {
    return null;
  }

}

