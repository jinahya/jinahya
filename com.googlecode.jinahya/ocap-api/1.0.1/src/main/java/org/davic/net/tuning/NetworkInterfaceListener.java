
package org.davic.net.tuning;

/** The listener used to receive NetworkInterface related events.
  */

public interface NetworkInterfaceListener extends java.util.EventListener {

  /** This function is called to send an event to the listener
    * @param anEvent event that is sent to the listener
    */
  public abstract void receiveNIEvent(NetworkInterfaceEvent anEvent);
}




