
/*
 * @(#)ServiceDetailsImpl.java	1.21 00/01/19
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.si;

import java.util.*;
import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.guide.*;
import javax.tv.service.navigation.*;
import com.sun.tv.*;
import com.sun.tv.receiver.*;

/**
 * This interface provides access to Service meta-data. It provides more
 * information about a <code>Service</code> object and represents a
 * specific instance of a service bound to a transport stream. <p>
 *
 * A <code>ServiceDetailsImpl</code> object may optionally implement the
 * <code>ServiceNumber</code> interface to report service numbers as
 * assigned by the broadcaster of the <code>Service</code> <p>
 *
 * A <code>ServiceDetailsImpl</code> object may optionally implement the
 * <code>ServiceProviderInformation</code> interface to report information
 * concerning the service provider.
 *
 * @see javax.tv.service.Service
 *
 * @see javax.tv.service.ServiceNumber
 *
 * @see ServiceProviderInformation
 */
public class ServiceDetailsImpl implements
		ServiceDetails, ServiceProviderInformation {

	private static Hashtable listenerTable = new Hashtable();

	private ServiceImpl service;
	private Date updatedTime;
	private ProgramSchedule schedule = null;
	private DeliverySystemType deliverySystemType;
	private String longname;
	private int caSystemIDs[];
	private String providerName;

	public ServiceDetailsImpl(
		ServiceImpl service,
		String providerName,
		DeliverySystemType deliverySystemType,
		String longname,
		int caSystemIDs[],
		Date updatedTime) {

		this.service = service;
		this.providerName = providerName;
		this.deliverySystemType = deliverySystemType;
		this.longname = longname;
		this.caSystemIDs = caSystemIDs;
		this.updatedTime = updatedTime;
		this.schedule = new ProgramScheduleImpl(service);
	}

  /**
   * Retrieves a textual description of this service if available.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @see ServiceDescription
   */
  public SIRequest retrieveServiceDescription(SIRequestor requestor) {
	if (requestor == null) {
           throw new NullPointerException("SIRequestor is null");
    	}

	Locator locatorDesc = LocatorImpl.transformToServiceDescription(getLocator());

	return new SIRequestImpl(requestor, locatorDesc);
  }
	
  /**
   * Retuns the type of this service. (E.g. "digital television", "digital
   * radio", "NVOD", etc.) These values can be mapped to the ATSC service
   * type in the VCT table and the DVB service type in the Service
   * Descriptor.
   *
   * @return Service type of this Service.
   */
  public ServiceType getServiceType() {
	return service.getServiceType();
  }
  
  /**
   * Retrieves a list of elementary components which are part of this
   * service.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @see javax.tv.service.ServiceComponent
   */
  public SIRequest retrieveComponents(SIRequestor requestor) {
	if (requestor == null) {
           throw new NullPointerException("SIRequestor is null");
    	}

        int reqKind = Settings.REQ_SERVICE_COMPONENT;
	return new SIRequestImpl(requestor, getLocator(), reqKind);
  }
  
  /**
   * Returns a schedule of program events associated with this Service.
   *
   * @return The Program Schedule for this Service.
   */
  public ProgramSchedule getProgramSchedule() {
	return this.schedule;
  }
  
  /**
   * Called to obtain a full service name. This information may be
   * delivered in the ATSC Extended Channel Name Descriptor, the DVB
   * Service Descriptor or the DVB Multilingual Service Name Descriptor.
   *
   * @return A string representing the full service name.
   */
  public String getLongName() {
	return this.longname;
  }
  
  /**
   * Returns the Service this ServiceDetailsImpl object is associated with.
   *
   * @return The Service this program is delivered on.
   */
  public Service getService() {
	return this.service;
  }
	
 /**
  * Returns an array of CA System IDs associated with this object. This
  * information may be obtained from the CAT MPEG message or a system
  * specific conditional access descriptor (such as defined by Simulcrypt
  * or ATSC).
  *
  * @return An array of CA System IDs. An empty array is returned when no
  * CA System IDs are available.
  */
  public int[] getCASystemIDs() {
	return this.caSystemIDs;
  }
  
  /**
   * Provides information about conditional access of this object.
   *
   * @return TRUE if this Service is not protected by a conditional access;
   * FALSE if one or more components may be protected by conditional
   * access.
   */
  public boolean isFree() {
	return (this.caSystemIDs == null || caSystemIDs.length == 0);
  }

 /**
  * This method provides information about the type of delivery mechanism
  * used for the content described by this object.
  *
  * @return The delivery system type
  */
  public DeliverySystemType getDeliverySystemType() {
	return this.deliverySystemType;
  }
 /**
  * Gets the complete Locator of this SI Element. Each SI Element (such as
  * BroadcastService, ProgramEvent, etc.) in the MPEG-2 domain is
  * identified by a Locator. This identification is encapsulated by the
  * Locator object which may use a URL format, specific MPEG numbers, such
  * as network ID, etc., or other mechanisms.
  *
  * @return Locator representing this SI Element
  */
  public Locator getLocator() {
	return getService().getLocator();
  }

 /**
  * Returns the time when this object was last updated from data in
  * the broadcast.
  *
  * @return The date of the last update in UTC format, or <code>null</code>
  * if unknown.
  */
  public Date getUpdateTime() {
	return this.updatedTime;
  }

 /**
  * Reports the service information format of this object.
  *
  * @return The service information format.
  */
  public ServiceInformationType getServiceInformationType() {
	return service.getServiceInformationType();
  }

 /**
  * Returns the name of the service provider. It can be retrieved from the
  * DVB Service Descriptor or the Multilingual Service Name Descriptor.
  *
  * @return A string representing the service provider's name. It returns
  * an empty string if no provider information is available.
  */
  public String getProviderName() {
	return this.providerName;
  }

  /**
   * Registers a <code>ServiceComponentChangeListener</code> to be notified of
   * changes to a <code>ServiceComponent</code> that is part of
   * this <code>ServiceDetails</code>. Subsequent notification is made via
   * <code>ServiceComponentChangeEvent</code> with this
   * <code>ServiceDetails</code> instance as the event source.<p>
   * 
   * This method is only a request for notification.  No guarantee is
   * provided that the SI database will detect all, or even any, SI
   * changes or whether such changes will be detected in a timely
   * fashion.<p>
   * 
   * If the specified <code>ServiceComponentChangeListener</code> is
   * already registered, no action is performed.
   *
   * @param listener A <code>ServiceComponentChangeListener</code> to be
   * notified about changes related to a <code>ServiceComponent</code>
   * in this <code>ServiceDetails</code>.
   *
   * @see ServiceComponentChangeEvent 
   * @see javax.tv.service.ReadPermission
   **/
  public void addServiceComponentChangeListener(ServiceComponentChangeListener listener)
     throws SecurityException {

        if ( listener == null ) {
                throw new NullPointerException("ServiceComponentChangeListener null");
        }

	Vector listeners = getListeners();

	listeners.removeElement(listener);
	listeners.addElement(listener);
  }
    
  /**
   * Called to unregister an
   * <code>ServiceComponentChangeListener</code>.  If the specified
   * <code>ServiceComponentChangeListener</code> is not registered, no
   * action is performed.
   *
   * @param listener A previously registered listener.  */
  public void removeServiceComponentChangeListener(ServiceComponentChangeListener listener) {
       	if ( listener == null ) {
       		throw new NullPointerException("ServiceComponentChangeListener null");
       	}

	Vector listeners = getListeners();

	listeners.removeElement(listener);
  }

  private Vector getListeners() {
	String key = getLocator().toExternalForm();
	Vector listeners = (Vector)listenerTable.get(key);
	if (listeners == null) {
		listeners = new Vector();
		listenerTable.put(key, listeners);
	}
	return listeners;
  }

  /**
   * Notify all listeners that ServiceComponent has been changed.
   */
  public void notifyListeners(ServiceComponentChangeEvent event) {
	Vector listeners = getListeners();
	for (int i = 0; i < listeners.size(); i++) {
		ServiceComponentChangeListener listener = (ServiceComponentChangeListener)listeners.elementAt(i);
		if (listener == null)
			continue;
		notifyAsyncListener(event, listener);
	}
  }

  private void notifyAsyncListener(
		ServiceComponentChangeEvent event,
		ServiceComponentChangeListener listener) {

	if (listener == null || event == null)
		return;

	NotifyServiceComponentChangeThread thread = new NotifyServiceComponentChangeThread(event, listener);
	if (thread != null) 
		thread.start();
  }
}

class NotifyServiceComponentChangeThread extends Thread {
	ServiceComponentChangeListener listener = null;
	ServiceComponentChangeEvent event = null;

  public NotifyServiceComponentChangeThread(
		ServiceComponentChangeEvent event,
		ServiceComponentChangeListener listener) {

	super("NotifyServiceComponentChangeThread");

	this.listener = listener;
	this.event = event;
  }

  public void run() {
	if (this.listener == null || this.event == null)
		return;

	listener.notifyChange(this.event);
  }
}
