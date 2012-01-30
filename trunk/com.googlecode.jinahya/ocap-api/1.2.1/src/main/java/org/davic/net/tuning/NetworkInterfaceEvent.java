
package org.davic.net.tuning;

/** Base class for events related to network interfaces.
  * Events that inherit from NetworkInterfaceEvent will
  * be sent to all registered listeners, when they are sent.
  */
public abstract class NetworkInterfaceEvent extends java.util.EventObject {

  /** The constructor for the event
    * @param networkInterface the network interface which the event
    * is generated for.
    */

  public NetworkInterfaceEvent(Object networkInterface) {
	super( (Object) networkInterface );
	}

  /** Returns the NetworkInterface that generated the event
    */
  public Object getSource() {
    return null;
  }
}




