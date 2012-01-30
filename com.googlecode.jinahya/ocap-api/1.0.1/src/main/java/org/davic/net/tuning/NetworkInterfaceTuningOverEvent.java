
package org.davic.net.tuning;

/** This event signals that a particular NetworkInterface has completed
  * its tuning action.
  */	
public class NetworkInterfaceTuningOverEvent 
extends NetworkInterfaceEvent {

  /** Constant for the status code that indicates that the Network Interface 
    * has been tuned to a new transport stream. 
    */
  public final static int SUCCEEDED = 0;

  /** Constant for the status code that indicates that the tuning action 
    * has failed. 
    */
  public final static int FAILED = 1;
  
  /** Constructor for the event
    * @param networkInterface the networkInterface which completed tuning
    * @param status Status code which shows if the tuning action succeeded
    */
  public NetworkInterfaceTuningOverEvent(Object networkInterface, int status)
   	{
		super(networkInterface);
	}

  /** Returns the status code of the tuning action
    * @return status code
    */
  public int getStatus() {
    return 0;
  }

}




