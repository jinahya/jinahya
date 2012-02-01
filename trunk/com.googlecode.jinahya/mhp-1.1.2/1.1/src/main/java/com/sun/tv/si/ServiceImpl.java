/*
 * @(#)ServiceImpl.java	1.2 00/04/04
 *
 * Copyright 2000 by Sun Microsystems, Inc.,
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
import javax.tv.service.navigation.*;
import com.sun.tv.*;

public class ServiceImpl implements javax.tv.service.Service, SIElement, 
	ServiceNumber, ServiceMinorNumber {

	private String serviceName;
	private ServiceType sType;
	private Locator locator = null;
	private ServiceInformationType siType;
	private Date updatedTime;
	private int serviceNumber;
	private int minorNumber;

	private int presentationTerminatedReason = 0;
	private int selectionFailedReason = 0;

/**
 * The ServiceImpl constructor accepts all information to setup a service entry
 * in the SI database. However, the locator for this service information is created
 * by the contructor from the service name. The service number and minor number are
 * implemented within the service class, so that sorting can be done fairly quickly,
 * information within the service details class is retrieve asynchronously.
 */
	public ServiceImpl(
		String serviceName,
		boolean hasMultiples, 
		ServiceType sType,
		ServiceInformationType siType,
		int serviceNumber,
		int minorNumber,
		Date updatedTime) {

		this.serviceName = serviceName;
		this.sType = sType;
		this.siType = siType;
		this.serviceNumber = serviceNumber;
		this.minorNumber = minorNumber;
		this.updatedTime = updatedTime;
	}

 /**
  * Returns a short service name or an acronym. This information comes in
  * the PSIP VCT or the DVB Service Descriptor or the Multilingual Service
  * Name Descriptor.
  *
  * @return A string representing this service's short name.
  */
  public String getName() {
	return this.serviceName;
  }
  
 /**
  * This method indicates whether the service represented by this Service
  * is available on multiple transports. (E.g. the same content delivered
  * over terrestrial and cable network).
  *
  * @return TRUE indicates that multiple transports may carry the same
  * content identified by this Service; FALSE means that there is only one
  * instance of this service.
  */
  public boolean hasMultipleInstances() {
        return getLocator().hasMultipleTransformations();
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
	return this.sType;
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
	if (this.locator == null) {
		try {
			String locatorStr = LocatorImpl.ServiceProtocol + serviceName;
			this.locator = LocatorFactory.getInstance().createLocator(locatorStr);
		} catch (Exception e) {
			;
		}
	}
	return this.locator;
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
        return this.siType;
  }

  /**
   * Reports the service number of a service.
   *
   * @return The number of the Service.
   */
  public int getServiceNumber() {
	return this.serviceNumber;
  }
  /**
   * Reports the minor number of the service.
   *
   * @return The minor number of this service.
   */
  public int getMinorNumber() {
	return this.minorNumber;
  }

  /**
   * This method retrieves additional information about the Service. This
   * information is retrieved from the broadcast service information (also
   * called meta-data). <P>
   *
   * Note that if the content represented by this Service is delivered on
   * multiple transport-dependent streams, there may be multiple
   * ServiceDetails for it. This method returns one of them based on
   * availability or user preferences. If access to all of them is
   * required, the Service Locator can be transformed to
   * transport-dependent Locators and ServiceDetails can be requested for
   * each one of them. <P>
   *
   * This method returns data asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @see javax.tv.locator.Locator
   *
   * @see javax.tv.service.navigation.ServiceDetails
   */

   public SIRequest retrieveDetails(SIRequestor requestor) {
	if ( requestor == null ) 
		throw new NullPointerException("SIRequestor null");

        Locator loc = getLocator();
	if ( LocatorImpl.isTIService(locator)) {
           Locator[] locs = LocatorImpl.transformLocator(locator);
           if (locs != null && locs.length > 0) {
              loc = locs[0];
           } 
        }
	return new SIRequestImpl(requestor, loc);
   }

  /**
   * Tests two <code>Service</code> objects for equality.  Returns
   * <code>true</code> if and only if:
   * <ul>
   * <li><code>obj's</code> class is the
   * same as the class of this <code>Service</code>, and<p>
   * <li><code>obj's</code> <code>Locator</code> is equal to
   * the <code>Locator</code> of this <code>Service</code>
   * (as reported by
   * <code>Service.getLocator()</code>, and<p>
   * <li><code>obj</code> and this object encapsulate identical data.
   * </ul>
   *
   * @param obj The object against which to test for equality.
   *
   * @return <code>true</code> if the two <code>Service</code> objects
   * are equal; <code>false</code> otherwise.
   */
  public boolean equals(Object obj) {
     if (!(obj instanceof ServiceImpl)) {
        return false;
     } 

     Service s = (Service) obj;
     if (!(s.getLocator().equals(this.getLocator()))) {
        return false;
     }

     return (s.getName().equals(this.getName())
             && s.getServiceType() == this.getServiceType()
             && s.hasMultipleInstances() == this.hasMultipleInstances());
  }

  /**
   * Reports the hash code value of this <code>Service</code>.  Two
   * <code>Service</code> objects that are equal will have identical
   * hash codes.
   *
   * @return The hash code value of this <code>Service</code>.
   */
  public int hashCode() {
      return this.getLocator().hashCode();
  }

   public void setPresentationTerminatedReason(int reason) {
	this.presentationTerminatedReason = reason;
   }

   public int getPresentationTerminatedReason() {
	return this.presentationTerminatedReason;
   }

   public void setSelectionFailedReason(int reason) {
	this.selectionFailedReason = reason;
   }

   public int getSelectionFailedReason() {
	return this.selectionFailedReason;
   }
}
