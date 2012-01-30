package org.davic.net.ca;

/** This event sent to the resource status event listeners
  * when the descrambling is stopped because of the application 
  * itself stopping it or because the descrambling resources
  * were revoked by some other part of the system. <p>
  * This event is generated only when the descrambling is actually
  * stopped. If two applications are requesting to descramble the 
  * same service, this event is generated only after both of them
  * have requested to stop the descrambling and the descrambling 
  * actually stops.
  */
public class DescramblingStoppedEvent extends org.davic.resources.ResourceStatusEvent {

  /** Constructor for the event
    * @param caModule the CAModule object controlling the descrambling
    * which has just stopped.
    */
  public DescramblingStoppedEvent(Object caModule) {
    super(caModule);
  }

  /** Returns a Locator pointing to the service components of which 
    * have been descrambled.
    * @return a Locator pointing to a broadcast service
    */
  public org.davic.net.Locator getServiceLocator() {
    return null;
  }

  /** Returns the CAModule object controlling the descrambling
    * which has just stopped.
    */
  public Object getSource() {
    return null;
  }

}

