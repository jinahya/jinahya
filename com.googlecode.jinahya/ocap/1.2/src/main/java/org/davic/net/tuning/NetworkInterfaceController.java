package org.davic.net.tuning;
import org.davic.resources.*;
import org.davic.mpeg.TransportStream;

/** NetworkInterfaceController represents a controller that 
  * can be used for tuning a network interface. 
  * Applications may create a network interface controller
  * object and use it to attempt to reserve the capability 
  * to tune a network interface.<p>
  * The capability to tune a network interface is a resource
  * and the network interface controller acts as a resource
  * proxy for this resource.
  */
  
public class NetworkInterfaceController implements ResourceProxy {

  /** Creates a NetworkInterfaceController
    * @param rc The ResourceClient that the controller is associated with
    */
  public NetworkInterfaceController(ResourceClient rc) {
  }

  /** Tunes asynchronously to the given stream (specified
    * by a Locator). This method causes the NetworkInterfaceTuningEvent and the
    * NetworkInterfaceTuningOverEvent to be sent to the listeners
    * of the NetworkInterface reserved by this NetworkInterfaceController.  <p> 
    * If tuning fails for one of the reasons which
    * generate an exception, the status of the network interface
    * will be unchanged and no events generated.
    * If failure of tuning is reported by the event code of the
    * NetworkInterfaceTuningOverEvent then the state of the network
    * interface is not defined and it may be tuned to any transport stream or
    * be left in a state where it is not tuned to any 
    * transport stream.
    * @param locator The locator describing the transport stream to tune to
    * @exception StreamNotFoundException raised if the specified locator
    *                                    does not point to any known 
    *                                    transport stream or the currently
    *                                    reserved NetworkInterface cannot
    *                                    tune to the specified transport stream
    * @exception IncorrectLocatorException raised if locator does not references a broadcast transport
    *                                  stream
    * @exception NotOwnerException raised if no network interface is reserved at 
    *                              the moment
    */	
  public synchronized void tune(org.davic.net.Locator locator) 
    throws NetworkInterfaceException {
    }
  
  /** Tunes asynchronously to the given transport stream. <p>
    * This method causes the NetworkInterfaceTuningEvent and the
    * NetworkInterfaceTuningOverEvent to be sent to the listeners
    * of the NetworkInterface reserved by this NetworkInterfaceController.
    * If tuning fails for one of the reasons which
    * generate an exception, the status of the network interface
    * will be unchanged and no events generated.
    * If failure of tuning is reported by the event code of the
    * NetworkInterfaceTuningOverEvent then the state of the network
    * interface is not defined and it may be tuned to any transport stream or
    * be left in a state where it is not tuned to any 
    * transport stream.
    * 
    * @param ts Transport stream object to tune to
    * @exception StreamNotFoundException raised if the specified
    *                                    transport stream is not associated
    *                                    with the currently reserved 
    *                                    network interface
    * @exception NotOwnerException raised if no network interface is 
    *                              reserved at the moment
    */
  public synchronized void tune(TransportStream ts) 
    throws NetworkInterfaceException {
    }
  
  /** Tries to reserve exclusively the control
    * over the specified network interface. <p>
    * If the reservation succeeds, a NetworkInterfaceReservedEvent is
    * sent to the listeners of the NetworkInterfaceManager.
    * If this NetworkInterfaceController has currently reserved another NetworkInterface, then it 
    * will either release that NetworkInterface and reserve an appropriate one, or throw an exception.  
    * If a NetworkInterface that is able to tune to the specified transport stream is currently 
    * reserved by this NetworkInterfaceController, then this method does nothing.
    *
    * @param ni Network Interface to be reserved
    * @param requestData Used by the Resource Notification
    *                    API in the requestRelease method
    *                    of the ResourceClient interface.
    *                    The usage of this parameter is optional and
    *                    a null reference may be supplied.
    * @exception NoFreeInterfaceException raised if the requested
    *                                     network interface can not 
    *                                     be reserved
    * @exception SecurityException raised if the application does not have
    * an instance of <code>TunerPermission</code>
    */
  public synchronized void reserve(NetworkInterface ni,
				   Object requestData)
    throws NetworkInterfaceException {
    }
  
  /** Tries to reserve exclusively the control over a network
    * interface that can receive the transport stream
    * specified by the locator parameter. <p>
    * The specific network interface is selected by the method
    * implementation. <p>
    * If the reservation succeeds, a
    * NetworkInterfaceReservedEvent is sent to the listeners of the
    * NetworkInterfaceManager.
    * If this NetworkInterfaceController has currently reserved another NetworkInterface, then it 
    * will either release that NetworkInterface and reserve an appropriate one, or throw an exception.  
    * If a NetworkInterface that is able to tune to the specified transport stream is currently 
    * reserved by this NetworkInterfaceController, then this method does nothing.
    * @param locator a Locator that points to a transport stream that 
    *                the reserved network interface should be able to 
    *                tune to
    * @param requestData Used by the Resource Notification
    *                    API in the requestRelease method
    *                    of the ResourceClient interface.
    *                    The usage of this parameter is optional and
    *                    a null reference may be supplied.
    * @exception NoFreeInterfaceException raised if a network interface
    *                                     can not be reserved
    * @exception StreamNotFoundException raised if the specified locator
    *                                    does not point to any known 
    *                                    transport stream 
    * @exception IncorrectLocatorException raised if the locator does not
    *                                  references a broadcast transport
    *                                  stream
    * @exception SecurityException raised if the application does not have
    * an instance of <code>TunerPermission</code>
    * 
    */
  public synchronized void reserveFor(org.davic.net.Locator locator,
				      Object requestData)
    throws NetworkInterfaceException {
    }
  
  /** Releases the tuner. <p>
    * This method causes a NetworkInterfaceReleasedEvent to be
    * sent to the listeners of the NetworkInterfaceManager.
    * @exception NotOwnerException raised if the controller does not 
    *                              currently have a network interface
    *                              reserved
    */
  public synchronized void release() 
    throws NetworkInterfaceException {
    }
  
  /** Returns the network interface associated with this	
    * controller.
    * @return the network interface associated with this controller or 
    * null if no network interface has been reserved.
    */
  public NetworkInterface getNetworkInterface(){
    return null;
  }
  
  /** Returns the resource client that is associated with
    * this NetworkInterfaceController.
    * This method implements getClient method of 
    * org.davic.resources.ResourceProxy.
    * @return the resource client associated with this controller
    */
  public ResourceClient getClient() {
    return null;
  }

}

