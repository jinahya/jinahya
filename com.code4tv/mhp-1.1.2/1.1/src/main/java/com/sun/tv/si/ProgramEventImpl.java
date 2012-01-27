/*
 * @(#)ProgramEventImpl.java	1.2  04/04/2000
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

import com.sun.tv.*;

import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.guide.*;
import java.util.*;

public class ProgramEventImpl implements javax.tv.service.guide.ProgramEvent {

	private String programName; 
	private String serviceName; 
	private Date startTime;
	private Date endTime;
	private Locator locator; 
	private ServiceInformationType siType;
	private Date updatedTime;
	private ContentRatingAdvisory rating;

	public ProgramEventImpl(
		String programName, 
		String serviceName,
		Date startTime,
		Date endTime,
		ServiceInformationType siType,
		ContentRatingAdvisory rating,
		Date updatedTime) {

		this.programName = programName;
		this.serviceName = serviceName;
		this.startTime = startTime;
		this.endTime = endTime;
		this.siType = siType;
		this.updatedTime = updatedTime;
		this.rating = rating;
	
		try {
			String locatorStr = 
				LocatorImpl.ProgramEventProtocol + programName +
				LocatorImpl.ServiceProtocol + serviceName;
			this.locator = LocatorFactory.getInstance().createLocator(locatorStr);
		} catch (Exception e) {
			;
		}
	}

 /**
  * Returns the start time of this program event. The start time is in UTC
  * time.
  *
  * @return This program's start time (UTC).
  */
  public Date getStartTime() {
	return startTime;
  }
	
 /**
  * Returns the end time of this program event. The end time is in UTC time.
  *
  * @return This program's end time (UTC).
  */
  public Date getEndTime() {
	return endTime;
  }
	
 /**
  * Returns the duration of this program event in seconds.
  *
  * @return This program's duration in seconds.
  */
  public long getDuration() {
	return (endTime.getTime() - startTime.getTime()) / 1000;
  }
	
 /**
  * Returns the program event title. This inforamtion may be obtained in
  * the ATSC EIT table or the DVB Short Event Descriptor.
  *
  * @return A string representing this program's title.
  */
  public String getName() {
	return programName;
  }
  
  /**
   * Retrieves a textual description of the event.  An empty string
   * will be returned when no additional description is available for
   * this event.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieveal request.
   *
   * @see ProgramEventDescription */
  public SIRequest retrieveDescription(SIRequestor requestor) 
   {
        if ( requestor == null )
                throw new NullPointerException("SIRequestor null");


	Locator descLocator = LocatorImpl.transformToProgramEventDescription(getLocator());
	return new SIRequestImpl(requestor, descLocator);
  }
	
 /**
  * Returns a Content Advisory information associated with this program for
  * the local rating region.
  *
  * @see ContentRatingAdvisory
  */
  public ContentRatingAdvisory getRating() {
	return this.rating;
  }
	
 /**
  * Returns the Service this program event is associated with.
  *
  * @return The Service this program is delivered on.
  */
  public Service getService() {
	try {
		String str = LocatorImpl.ServiceProtocol + serviceName;
		Locator serviceLoc = LocatorFactory.getInstance().createLocator(str);
		return ((SIManagerImpl)SIManagerImpl.createInstance()).getService(serviceLoc);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return null;
  }
	
  /**
   * Retrieves a list of service components which are part of this program.
   * Component information may not always be available. A current program
   * may provide components associated with the Service this program is on.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieveal request.
   * 
   * @see javax.tv.service.ServiceComponent
   */
  public SIRequest retrieveComponents(SIRequestor requestor) {
        if ( requestor == null )
                throw new NullPointerException("SIRequestor null");

	int reqKind = com.sun.tv.receiver.Settings.REQ_SERVICE_COMPONENT;
	return new SIRequestImpl(requestor, getLocator(), reqKind);
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
     * Compares this program event to the provided date.
     *
     * @param   compareDate   the <code>Date</code> to be compared.
     * @return  the number of milliseconds until the program event starts, if
     *          program event is after the current date; the negative number of
     *          milliseconds since the program event ended (should not occur);
     * 		or 0 if the program event is within the current date;
     */
	public long compareTo(Date current) {
		long startTime = getStartTime().getTime();
		long endTime = getEndTime().getTime() - 1000;
		long curTime = current.getTime();

		long dxTime = startTime - curTime;
		if (dxTime > 0) {
			return dxTime;
		}

		dxTime = endTime - curTime;
		if (dxTime < 0) {
			return dxTime;
		}
		return 0;
	}

	public boolean isIncluded(Date current) {
        // return true if current is equal/after the startTime and
        // before the endTime 
    		return ( (current.getTime() >= getStartTime().getTime()) 
       			&& (current.getTime() < getEndTime().getTime()) );
  	}
}
