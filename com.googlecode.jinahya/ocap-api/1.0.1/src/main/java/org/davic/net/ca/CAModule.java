package org.davic.net.ca;

import org.davic.resources.*;
import org.davic.mpeg.*;

/** CAModule class represents a physical CA Module
  */
public abstract class CAModule {

  /**
   * This constructor is provided for the use of implementations and specifications which 
   * extend the present document. Applications shall not define sub-classes of this class.
   * Implementations are not required to behave correctly if any such application defined 
   * sub-classes are used.
   */

  CAModule() {}

  /** Constant value for the CA0 (Common Interface) CA module type.
    * Values from 0x02 to 0xFE are reserved for future use.
    * @see CAModule#getModuleType
    */ 
  public final static int CA0 = 0x00;

  /** Constant value for the CA1 (DAVIC specified smart card) CA module type.
    * Values from 0x02 to 0xFE are reserved for future use.
    * @see CAModule#getModuleType
    */ 
  public final static int CA1 = 0x01;

  /** Constant value for the proprietary CA module types.
    * Values from 0x02 to 0xFE are reserved for future use.
    * @see CAModule#getModuleType
    */
  public final static int PROPRIETARY = 0xFF;
  
  /** Constant value for a return value of queryEntitlement(). 
    * ENTITLEMENT_UNKNOWN shall be returned if all necessary information
    * for returning a more specific answer is not available (e.g. because
    * the STU is not tuned to the relevant transport stream).
    * In the DVB common interface protocol, this constant is mapped to the
    * following CA_enable values of the ca_pmt_response message and event_status
    * values of the event_reply message.<ul>
    * <li> CA_enable value  Descrambling not possible (for technical reasons)  (0x05)
    * <li> all other CA_enable values not having an explicit mapping in this section
    * <li> event_status value  entitlement_unknown   (0x00)
    * <li> event_status value  mmi_complete_unknown   (0x04)
    * <li> all other event_status values not having an explicit mapping in this section
    * </ul>
    * @see CAModule#queryEntitlement
    */
  public final static int ENTITLEMENT_UNKNOWN = 0x00;

  /** Constant value for a return value of queryEntitlement(). 
    * ENTITLEMENT_AVAILABLE is returned when the entitlement is definitely
    * available and all information needed to verify this is available.
    * In the DVB common interface protocol, this constant is mapped to the
    * following CA_enable values of the ca_pmt_response message and event_status
    * values of the event_reply message.<ul>
    * <li>CA_enable value  Descrambling possible (0x01)
    * <li>event_status value  entitlement_available (0x01)
    * <li>event_status value  mmi_complete_available (0x05)
    * </ul>
    * @see CAModule#queryEntitlement
    */
  public final static int ENTITLEMENT_AVAILABLE = 0x01;

  /** Constant value for a return value of queryEntitlement(). 
    * ENTITLEMENT_NOT_AVAILABLE is returned when the entitlement is definitely
    * not available and all information needed to verify this is available.
    * In the DVB common interface protocol, this constant is mapped to the
    * following CA_enable values of the ca_pmt_response message and event_status
    * values of the event_reply message.<ul>
    * <li> CA_enable value  Descrambling not possible (because no entitlement)  (0x04)
    * <li> event_status value  entitlement_not_available   (0x02)
    * <li> event_status value  mmi_complete_not_available   (0x06)
    * </ul>
    * @see CAModule#queryEntitlement
    */
  public final static int ENTITLEMENT_NOT_AVAILABLE = 0x02;

  /** Constant value for a return value of queryEntitlement(). 
    * MMI_DIALOGUE_REQUIRED is returned when the entitlement may become 
    * available after an MMI dialogue (e.g. a purchase dialogue or a 
    * maturity rating verfication dialogue).
    * In the DVB common interface protocol, this constant is mapped to the
    * following CA_enable values of the ca_pmt_response message and event_status
    * values of the event_reply message.<ul>
    * <li> CA_enable value  Descrambling possible under conditions (purchase dialogue)  (0x02)
    * <li> CA_enable value  Descrambling possible under conditions (technical dialogue)  (0x03)
    * <li> event_status value  mmi_dialogue_required   (0x03)
    * </ul>
    * @see CAModule#queryEntitlement    
    */
  public final static int MMI_DIALOGUE_REQUIRED = 0x03;
  
  
  /** Returns if descrambling is possible for specified 
    * service or future event (specified by a Locator). <p>
    * Return values are specified by constants in this class. 
    
    * In case of DVB Common Interface, this maps onto CI messages as follows: <ul>
    * <li>when the Locator points to a service and the terminal is currently receiving the 
    * transport stream that this service is carried in and this transport stream is available 
    * to this CA module, then this method is mapped to a ca_pmt message with ca_pmt_cmd_id set 
    * to query. The value returned in the ca_pmt_reply is mapped as defined in the documentation
    * of the constants in the class. If the module is currently descrambling the service and the 
    * terminal is aware of this, ENTITLEMENT_AVAILABLE shall be returned immediately without 
    * communicating with the module.
    * <li>when the Locator points to a service that is not carried in a currently received 
    * transport stream, ENTITLEMENT_UNKNOWN shall be returned.
    * <li>when the Locator points to an event, this maps onto event_query with event_cmd_id = query
    * (Common Interface specification, section B.4.1.1). The return values is mapped as defined 
    * in the documentation of the constants in this class.
    * </ul>
    * @param locator a Locator that points to a broadcast service or an event 
    *            of a service
    * @exception NoFreeCapacityException raised if the CAModule does not 
    *                                    have available capacity to perform
    *                                    this action now
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @exception SecurityException raised if the application does not have an
    * instance of <code>CAPermission</code> with the "entitlementInfo" name.
    * @exception org.davic.net.InvalidLocatorException if the locator does not 
    * point to a valid service or event.
    * @return descrambling status code
    */
  public int queryEntitlement(org.davic.net.Locator locator) throws CAException,
	org.davic.net.InvalidLocatorException {
    return 0;
  }
  
  /** 
    * Request to buy a specified service or future event (specified by a Locator)
    * from a conditional access system.<p>
    * Returns whether descrambling of the specified service or future 
    * event is possible after the user dialogue has been completed.
    * Return values are specified by constants in this class. 
    * If a dialog is required to buy an entitlement, this dialog may 
    * be started. <p>
    *
    * In case of DVB Common Interface, this maps onto CI messages as follows: 
    * <ul><li>when the Locator points to a service and the terminal is currently 
    * receiving the transport stream that this service is carried in and this transport 
    * stream is available to this CA module, then this method is mapped to a ca_pmt message 
    * with ca_pmt_cmd_id set to 'ok_mmi'. The value returned in the ca_pmt_reply is mapped 
    * as defined in the documentation of the constants in the class. If the module is currently 
    * descrambling the service and the terminal is aware of this, ENTITLEMENT_AVAILABLE shall 
    * be returned immediately without communicating with the module.
    * <li>when the Locator points to a service that is not carried in a currently received 
    * transport stream, NotTunedException is thrown
    * <li>when the Locator points to an event, this maps onto event_query message with 
    * event_cmd_id set to 'mmi' (Common Interface specification,section B.4.1.1). The value 
    * returned in the event_reply message is mapped as defined in the documentation of the 
    * constants in this class.
    * </ul>
    * @param locator Locator that points to a broadcast service or an event 
    *            of a service
    * @exception NoFreeCapacityException raised if the CAModule does not 
    *                                    have available capacity to perform
    *                                    this action now
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @exception NotTunedException raised if performing the action would
    *                              require being tuned to the transport
    *                              stream carrying the service and the receiver
    *                              is not currently tuned to it
    * @exception SecurityException raised if the application does not have an
    * instance of <code>CAPermission</code> with the "buy" name.
    * @return descrambling status code (similar as in queryEntitlement)
    */
  public int buyEntitlement(org.davic.net.Locator locator) throws CAException {
    return 0;
  }
  
  /** Returns the entitlements present in this module for display 
    * to the end-user. 
    * The strings returned are CA system dependent. 
    * The CA system and implementation of this API together should format 
    * these such that they can be presented to end users. 
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                              has been removed and is not
    *                                              available any more
    * @exception SecurityException raised if the application does not have an
    * instance of <code>CAPermission</code> with the "entitlementInfo" name.
    * @return array of entitlement strings
    */
  public String[] listEntitlements() throws CAException {
    return null;
  }
  
  /** Returns true if the given transport stream can be connected 
    * to this module 
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @exception NotTunedException raised if performing the action would
    *                              require being tuned to the transport
    *                              stream carrying the service and the receiver
    *                              is not currently tuned to it
    * @return true, if the transport stream can be connected to this
    *         CA module, false otherwise
    */
  public boolean isConnectable(TransportStream ts) throws CAException {
    return false;
  }
  
  /** Returns the number of the slot where module is connected.
    * The numbering of slots is dependent on the API implementation,
    * but should be such that if the number is presented to the end 
    * user, he can identify which physical slot it is.
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @return slot number
    */
  public int getSlotNumber() throws CAException {
    return 0;
  }

  /** Returns true if given service can be descrambled by this CAModule now.
    * The difference between this function and the queryEntitlement is 
    * that this function returns whether this CA module can descramble 
    * this service now, i.e. if it has both the entitlement as well as 
    * resources to perform it. 
    * In case of CA0 this maps onto ca_pmt with ca_pmt_cmd_id = query 
    * (Common Interface specification, section 8.4.3.4).
    * @exception NotTunedException raised if performing the action would
    *                              require being tuned to the transport
    *                              stream carrying the service and the receiver
    *                              is not currently tuned to it
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @return true, if descrambling of the service could be 
    *         performed now, false otherwise
    */
  public boolean isDescramblable(Service s) throws CAException {
    return false;
  }
  
  /** Returns true if the given array of elementary services (which are 
    * components of the same service) can be descrambled by this CAModule.
    * The difference between this function and the queryEntitlement is that 
    * this function returns whether this CA module can descramble this 
    * service now, i.e. if it has both the entitlement as well as resources 
    * to perform it. 
    * In case of CA0 this maps onto ca_pmt with ca_pmt_cmd_id = query 
    * (Common interface specification, section 8.4.3.4).
    * @exception NotTunedException raised if performing the action would
    *                              require being tuned to the transport
    *                              stream carrying the service and the receiver
    *                              is not currently tuned to it
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @return true, if descrambling of these elementary streams could be 
    *         performed now or if an empty array was passed in, false otherwise
    */
  public boolean isDescramblable(ElementaryStream[] streams) 
    throws CAException {
      return false;
    }
  
	
  /** Returns all CASystemIDs of this CA module.
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @return array of CA system identifiers
    */
  public int[] getCASystemIDs() throws CAException {
    return null;
  }

  /** Returns the type of this module. 
    * Constants for the possible return values are defined in this class 
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @return module type
    */ 
  public int getModuleType() throws CAException {
    return 0;
  }
  /** This method allows an application to open a message session to 
    * this module.  The return value of this method is the
    * session_id that is a unique identification of the session
    * within the module. In a DVB CI/DAVIC CA0 system, this method can
    * only be used to address private resources and not public ones
    * as defined in <reference>.<p>
    * In systems based on the DVB common interface, messages sessions opened using this method 
    * shall be mapped onto the CA pipeline for the module represented by this CAModule 
    * instance as defined in section 6.8 of the common interface extensions specification.
    * Neither the module_id or the resource_id of the module are visible to the application.
    * It is the responsibility of the platform to perform the relevant mapping. 
    * NOTE:The document referred to as "common interface extensions specification."is TS 101 699.
    *
    * @param listener the listener which will receive the messages related to this
    * message session
    * @exception ModuleUnavailableException raised if the physical CA module
    * has been removed and is not available any more
    * @exception ModuleBusyException raised if the module is busy and 
    * is not able to handle a message session at the moment. This is CA system dependant.
    * @exception ModuleResourceNonExistentException raised if the specified resource is not 
    * present in the module.
    * @exception ModuleResourceVersionTooLowException raised if the version
    * of the module resource is too low. NOTE: The ModuleResourceVersionTooLowException shall 
    * never be thrown in this version of the present document.
    * @exception SecurityException raised if the application does not have an
    * instance of <code>CAPermission</code> with the "messagePassing" name.
    * @return the session identifier
    */
  public int openMessageSession(MessageListener listener) 
    throws CAException {
      return 0;
    }

  /** This method allows an application to close a session to
    * this module. 
    * After this method has returned, no new messages will be accepted in
    * this session. Messages that have already been submitted will however
    * be answered. After the last 'ModuleResponseEvent' a 
    * 'SessionClosedEvent' will follow.
    * @param session_id the session identifier returned by the 
    *                   openMessageSession method. 
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @exception UnknownSessionException raised if the specified session does
    *                                    not exist 
    */
  public void closeMessageSession(int session_id) throws CAException {
  }
  
  
  /** Sends a message to this module. The sending of the message is 
    * asynchronous. 
    * In systems based on the DVB common interface, messages sent using this method shall be 
    * mapped onto the CAPipelineRequest as defined in section 6.8.3 of the common interface 
    * extensions specification. Responses from the CA system reported through the CAPipelineResponse    
    * and CAPipelineNotification messages shall be mapped onto instances of ModuleResponseEvent. 
    * @param session_id the session identifier returned by the 
    *                   openMessageSession method
    * @param msg the message to be sent to the module
    * @exception ModuleUnavailableException raised if the physical CA module
    *                                       has been removed and is not 
    *                                       available any more
    * @exception UnknownSessionException raised if the specified session does
    *                                    not exist 
    * @exception BufferFullException raised if the message buffer is full
    */
  public void sendToModule(int session_id, CAMessage msg) throws CAException {
  }

  /**
   * Retrieves the Application Title String. 
   * This functionality is provided at low lowel (within MMI and Application Information 
   * sessions defined in EN50221) by the message application_info() message (Application 
   * Information resource, see 8. 4.2.2) 
   * @exception ModuleUnavailableException raised if the physical CA module 
   * has been removed and is not available any more 
   * @return the application title string 
   */ 
   public String getApplicationTitle() throws ModuleUnavailableException{return null;}

   /**
    * Requests the module to enter start the application and enter
    * the main application menu. This functionality is provided at low lowel (within MMI and 
    * Application Information sessions defined in EN50221) by the message enter_menu() 
    * message (Application Information resource, see 8.4.2.3)
    * @exception ModuleUnavailableException raised if the physical CA module
    * has been removed and is not available any more
    */
    public void enterApplication() throws ModuleUnavailableException{};

    /**
     * Requests the module to leave
     * the complete tree of the current high-level MMI dialogs. This functionality is 
     * provided at low lowel (within MMI and Application Information sessions defined in 
     * EN50221) by the message close_mmi() message (MMI resource, see 8.6.2.1) 
     * @exception ModuleUnavailableException raised if the physical CA module
     * has been removed and is not available any more
     */
     public void closeMMI() throws ModuleUnavailableException{}
}





