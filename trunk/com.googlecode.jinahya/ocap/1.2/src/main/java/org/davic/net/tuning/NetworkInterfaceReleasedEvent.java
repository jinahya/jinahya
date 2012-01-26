package org.davic.net.tuning;

import org.davic.resources.ResourceStatusEvent;


/** This event informs that the NetworkInterface returned by 

  * the getNetworkInterface method has been released

  * by an application or other entity in the system.

  */

public class NetworkInterfaceReleasedEvent extends ResourceStatusEvent {

  

  /** Constructor for the event

    * @param ni the NetworkInterface object representing the network 

    *           interface that has been released.

    */

  public NetworkInterfaceReleasedEvent(Object ni) {

    super(ni);

  }

  

  /** Returns the network interface that has been released

    * @return the NetworkInterface object representing the network interface 

    *         that has been released	

    */

  public Object getSource() {

    return null;

  }

}


