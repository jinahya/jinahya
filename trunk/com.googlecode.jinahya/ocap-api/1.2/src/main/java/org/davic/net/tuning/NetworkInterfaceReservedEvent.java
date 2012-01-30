package org.davic.net.tuning;
/* import org.davic.resources.ResourceStatusEvent; */

/** This event informs that a particular network interface has been reserved
  * by an application or other entity in the system.
  */
public class NetworkInterfaceReservedEvent extends org.davic.resources.ResourceStatusEvent {
  
  /** Constructor for the event
    * @param ni the NetworkInterface object representing the network 
    *           interface that has been reserved.
    */
  public NetworkInterfaceReservedEvent(Object ni) {
    super(ni);
  }

  /** Returns the network interface that has been reserved
    * @return the NetworkInterface object representing the network interface 
    *         that has been reserved
    */
  public Object getSource() {
    return null;
  }
}

