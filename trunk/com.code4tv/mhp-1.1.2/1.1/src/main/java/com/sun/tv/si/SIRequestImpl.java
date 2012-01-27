/*
 * @(#)SIRequestImpl.java	1.15 00/01/19
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

import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.guide.*;
import javax.tv.service.navigation.*;

import com.sun.tv.*;
import com.sun.tv.receiver.*;

/**
 *
 * This interface is implemented by application classes in order to
 * receive the results of asynchronous SI retrieval requests. The
 * <code>SIRequestImpl</code> registers itself at the time of the
 * asynchronous call for a single request and is automatically
 * unregistered when the request is completed.  Applications may
 * identify individual requests when the methods of this interface are
 * called by registering a unique <code>SIRequestImpl</code> for each
 * simultaneous asynchronous request.<p>
 *
 * The asynchronous SI retrieval mechanisms invoke the methods of this
 * interface using system threads that are guaranteed to not hold
 * locks on application objects.
 */
public class SIRequestImpl implements SIRequest {

	long requestTime = System.currentTimeMillis();

	SIRequestor requestor = null;
	Locator locator = null;
	int requestKind = Settings.REQ_GENERAL; 
	Object userData[] = null;

	public SIRequestImpl(SIRequestor requestor, Locator locator) {
		this.requestor = requestor;
		this.locator = locator;
		this.requestKind = Settings.REQ_GENERAL;
		this.userData = null;

		((SIManagerImpl)SIManager.createInstance()).makeRequest(this);
	}

	public SIRequestImpl(SIRequestor requestor, Locator locator, int requestKind) {

		this.requestor = requestor;
		this.locator = locator;
		this.requestKind = requestKind;
		this.userData = null;

		((SIManagerImpl)SIManager.createInstance()).makeRequest(this);
	}

	public SIRequestImpl(SIRequestor requestor, Locator locator, int requestKind,
			Object userData) {

		this.requestor = requestor;
		this.locator = locator;
		this.requestKind = requestKind;
		this.userData = new Object[1];
		this.userData[0] = userData;

		((SIManagerImpl)SIManager.createInstance()).makeRequest(this);
	}

	public SIRequestImpl(SIRequestor requestor, Locator locator, 
			int requestKind, Object userData1, Object userData2) {

		this.requestor = requestor;
		this.locator = locator;
		this.requestKind = requestKind;
		this.userData = new Object[2];
		this.userData[0] = userData1;
		this.userData[1] = userData2;

		((SIManagerImpl)SIManager.createInstance()).makeRequest(this);
	}

	public SIRequestImpl(SIRequestor requestor, Locator locator, 
			int requestKind, Object userData[]) {

		this.requestor = requestor;
		this.locator = locator;
		this.requestKind = requestKind;
		this.userData = userData;

		((SIManagerImpl)SIManager.createInstance()).makeRequest(this);
	}

	public Locator getLocator() {
		return locator;
	}

	public int getRequestKind() {
		return requestKind;
	}

	public Object getUserData(int slot) {
		if (slot < 0 || slot >= userData.length) {
			return null;
		}
		return userData[slot];
	}

	public boolean isExpired() {
		return ((System.currentTimeMillis() - requestTime) > Settings.REQUEST_DURATION);
	}

  /**
   * Notifies the <code>SIRequestImpl</code> of successful asynchronous
   * SI retrieval.
   * 
   * @param result The previously requested data.
   */
	public void notifySuccess(SIRetrievable[] result) {
		requestor.notifySuccess(result);
	}

  /**
   * Notifies the <code>SIRequestImpl</code> of unsuccessful asynchronous
   * SI retrieval.
   * 
   * @param reason The reason why the asynchronous request failed.
   */
	public void notifyFailure(SIRequestFailureType reason) {
		requestor.notifyFailure(reason);
	}

 /**
  *
  * Cancels a pending request.  If the request is still pending and
  * can be canceled then the <code>notifyFailture</code> method of
  * the <code>SIRequestor</code> that initiated the asynchronous
  * retrieval will be called with the
  * <code>SIRequestFailureType</code> code of
  * <code>CANCELED</code>. If the request is no longer pending then no
  * action is performed.
  *
  * @return <code>True</code> if the request was pending and
  * successfully canceled; <code>false</code> otherwise.
  *
  * @see SIRequestor#notifyFailure
  * @see SIRequestFailureType#CANCELED
  */
	public boolean cancel() {
		return ((SIManagerImpl)SIManager.createInstance()).cancel(this);
	}

	public boolean equals(SIElement element) {
		switch (requestKind) {
		case Settings.REQ_GENERAL:
		case Settings.REQ_SERVICE_DESCRIPTION:
		case Settings.REQ_PROGRAM_EVENT:
		default:
			String locatorStr2 = locator.toExternalForm();
			if (LocatorImpl.isSameProtocol(element.getLocator(), locator) && locatorStr2.endsWith(":/*")) 
				return true;
			return LocatorImpl.equals(element.getLocator(), locator);


		case Settings.REQ_SERVICE_COMPONENT: 
		{
			if (!(element instanceof ServiceComponentImpl))
				return false;

			Locator locator1 = locator;
			Locator locator2 = element.getLocator();

			String serviceName1 = LocatorImpl.getServiceName(locator1);
			String serviceName2 = LocatorImpl.getServiceName(locator2);

			if (equals(serviceName1, serviceName2) == false)
				return false;

			String compName1 = LocatorImpl.getServiceComponentName(locator1);
			String compName2 = LocatorImpl.getServiceComponentName(locator2);
			if (compName1 == null)
				return true;

			return equals(compName1, compName2);
		}

		case Settings.REQ_CURRENT_PROGRAM_EVENT:
		case Settings.REQ_FUTURE_PROGRAM_EVENT:
		case Settings.REQ_FUTURE_PROGRAM_EVENTS:
		case Settings.REQ_NEXT_PROGRAM_EVENT:
		{
			if (!(element instanceof ProgramEventImpl)) 
				return false;

			ProgramEventImpl pe = (ProgramEventImpl)element;

			Locator locator1 = locator;
			Locator locator2 = pe.getService().getLocator();

			String serviceName1 = LocatorImpl.getServiceName(locator1);
			String serviceName2 = LocatorImpl.getServiceName(locator2);

			return equals(serviceName1, serviceName2);
		}
		case Settings.REQ_TRANSPORT_STREAM:
		{
			if (!(element instanceof TransportStreamImpl)) 
				return false;
			TransportStreamImpl transportStream = 
				(TransportStreamImpl) element;

			NetworkImpl network = (NetworkImpl)getUserData(0);

			return (network != null && 
				network.getNetworkID() == transportStream.getNetworkID()); 
		}
		}
	
	}

	private boolean equals(String str1, String str2) {
		if (str1 == null || str2 == null)
			return false;

		return str1.equals(str2);
	}
}
