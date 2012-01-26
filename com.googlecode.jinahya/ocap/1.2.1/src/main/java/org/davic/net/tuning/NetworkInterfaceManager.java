

package org.davic.net.tuning;
import org.davic.resources.*;
import org.davic.mpeg.TransportStream; 

/** A network interface manager is an object that keeps track
  * of broadcast network interfaces that are connected to the 
  * receiver. <p>
  * There is only one instance of the network interface manager
  * in a receiver and this can be retrieved using the getInstance 
  * method.
  */

public class NetworkInterfaceManager implements ResourceServer {

  /* For javadoc to hide the non-public constructor. */
  NetworkInterfaceManager() {
  }

  /** Returns the instance of the NetworkInterfaceManager
    * @return network interface manager 
    */
  public static NetworkInterfaceManager getInstance() {
    return null;
  }


  /** Returns all network interfaces. <p>
    * If there are no network interfaces, returns
    * an array with the length of zero.
    * @return an array containing all network interfaces
    */
  public NetworkInterface[] getNetworkInterfaces() {
    return null;
  }

  /** Returns the NetworkInterface with which the specified
    * TransportStream object is associated. It neither tunes nor reserves the
    * NetworkInterface.
    * @param ts Transport stream object
    * @return network interface that is associated with the transport stream
    */
  public NetworkInterface getNetworkInterface(TransportStream ts) {
    return null;
  }

  /** Registers a resource status listener to receive resource status events
    * @param listener listener to be registered
    */
  public void addResourceStatusEventListener(ResourceStatusListener listener) {
  }
  
  /** Removes the registration of a registered listener so that 
    * it will not receive resource status events any more
    * @param listener listener whose registration is to be removed
    */
    public void removeResourceStatusEventListener(ResourceStatusListener listener) {
    }



 
}



