package org.davic.net.ca;

/** This event sent to the resource status event listeners
  * when the descrambling of a service is started. <p>
  * This event is generated only when the descrambling is actually
  * started. If two applications are requesting to descramble the 
  * same service, this event is generated only when the first application
  * first requests the descrambling and it actually starts.
  */
public class DescramblingStartedEvent extends org.davic.resources.ResourceStatusEvent {

  /** Constructor for the event
    * @param caModule the CAModule object controlling the descrambling
    * which has just started.
    */
  public DescramblingStartedEvent(Object caModule) {
    super(caModule);
  }

  /** Returns a Locator pointing to the service components of which 
    * are descrambled.
    * @return a Locator pointing to a broadcast service
    */
  public org.davic.net.Locator getServiceLocator() {
    return null;
  }

  /** Returns the CAModule object controlling the descrambling which
    * has just started.
    */
  public Object getSource() {
    return null;
  }

}

