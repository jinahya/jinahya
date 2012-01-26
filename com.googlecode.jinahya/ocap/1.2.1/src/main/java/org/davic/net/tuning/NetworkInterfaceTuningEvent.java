
package org.davic.net.tuning;

/** This event signals that a particular NetworkInterface has started 
  * to tune to another transport stream.
  */
public class NetworkInterfaceTuningEvent extends NetworkInterfaceEvent {

  /** Constructor for the event
    * @param networkInterface the network interface which has started to tune.
    */
  public NetworkInterfaceTuningEvent(Object networkInterface)
	{
		super(networkInterface);
	}
}


