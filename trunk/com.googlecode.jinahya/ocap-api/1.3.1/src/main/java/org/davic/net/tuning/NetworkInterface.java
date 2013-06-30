package org.davic.net.tuning;
import org.davic.resources.*;
import org.davic.mpeg.TransportStream;

/** Objects of this class represent physical network interfaces
  * that can be used for receiving broadcast transport streams.
  */
public class NetworkInterface {

  /**
   * This constructor is provided for the use of implementations and specifications which 
   * extend the present document. Applications shall not define sub-classes of this class.
   * Implementations are not required to behave correctly if any such application defined 
   * sub-classes are used.
   */
  protected NetworkInterface() {
  }

  /** Returns the transport stream to which the network
    * Interface is currently tuned. Returns null
    * if the network interface is not currently tuned
    * to a transport stream, e.g. because it is performing
    * a tune action.
    * @return Transport stream to which the network interface 
    *         is currently tuned  
    */
  public TransportStream getCurrentTransportStream() {
    return null;
  }

  /** Returns the Locator of the transport stream to which the network 
    * interface is connected. Returns null if the network
    * interface is not currently tuned to a transport stream.
    * @return Locator of the transport stream to which the network interface
    *         is tuned 
    */
  public org.davic.net.Locator getLocator() {
    return null;
  }

  /**
    * @return true, if the network interface is reserved, otherwise false
    */
  public synchronized boolean isReserved() {
    return false;
  }
  
  /** 
    * @return true, if the network interface is local 
    * (i.e. embedded in the receiver), otherwise false
    */
  public boolean isLocal() {
    return false;
  }

  /** Lists the known transport streams that are accessible through
    * this network interface. If there are no such streams, returns
    * an array with length of zero.
    * @return array of transport streams accassible through this network 
    *         interface
    */
  public TransportStream[] listAccessibleTransportStreams() {
    return null;
  }

  /** This method returns the type of the delivery system
    * that this network interface is connected to.
    * @return delivery system type
    */
  public int getDeliverySystemType() {
    return 0;
  }

  /** Adds a listener for network interface events
    * @param listener listener object to be registered to receive network interface 
    *          events
    */
  public void addNetworkInterfaceListener(NetworkInterfaceListener listener) {
  }

  /** Removes a registered listener
    * @param listener listener object to be removed so that it will not receive
    *        network interface events in future
    */
  public void removeNetworkInterfaceListener(NetworkInterfaceListener listener) {
  }

}


