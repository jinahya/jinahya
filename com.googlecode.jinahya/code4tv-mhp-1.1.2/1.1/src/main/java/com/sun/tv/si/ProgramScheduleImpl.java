/*
 * @(#)ProgramScheduleImpl.java	1.18 00/01/14
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
 * This interface represents a collection of program events for a given
 * Service ordered by time. It provides the current, next and future
 * events.<BR>
 *
 * Note that all time values (java.util.Date) are in UTC time.
 *
 * @see java.util.Date
 *
 * @see java.util.Calendar
 */
public class ProgramScheduleImpl implements ProgramSchedule {

	Service service = null;
	private static Hashtable listenerTable = new Hashtable();

	public ProgramScheduleImpl(Service service) {
		this.service = service;
	}
  
  /**
   * Retrieves the current <code>ProgramEvent</code>.  The resulting
   * <code>ProgramEvent</code> will be available for immediate viewing.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieveal request.
   *
   * @see ProgramEvent
   */
  public SIRequest retrieveCurrentProgramEvent(SIRequestor requestor) {
        if ( requestor == null )
                throw new NullPointerException("SIRequestor null");
	int reqKind = Settings.REQ_CURRENT_PROGRAM_EVENT;

	return new SIRequestImpl(requestor, service.getLocator(), reqKind);
  }

  /**
   * Retrieves the program event for the specified time. The program event
   * which contains the specified time (the specified time falls between the
   * program event's start time and end time) will be returned.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param time The time which the program event should be searched for.
   * This is a UTC time.
   *
   * This method delivers its results asynchronously.
   *	
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieveal request.
   *
   * @throws SIException If <code>time</code> does not represent a
   * future time value.
   *
   * @see ProgramEvent
   */
  public SIRequest retrieveFutureProgramEvent(Date time, SIRequestor requestor)
		throws SIException {

        if ( requestor == null )
                throw new NullPointerException("SIRequestor null");
        if ( time == null )
                throw new NullPointerException("null Date");
        if ( !(time.after(new Date())) )
                throw new SIException("not a future time value");


	int reqKind = Settings.REQ_FUTURE_PROGRAM_EVENT;

	return new SIRequestImpl(requestor, service.getLocator(), reqKind, time);
  }
  
  /**
   * Retrieves all known program events on this service for the specified time
   * interval.<p>
   *
   * This method returns data asynchronously.
   *
   * @param begin Time identifying the beginning of the interval.
   * This is UTC time.
   *
   * @param end Time identifying the end of the interval. This is
   * UTC time.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieveal request.
   *
   * @throws SIException If <code>end</code> represents a time value
   * before <code>begin</code>, or if <code>end</code> does not
   * represent a future time value.
   *
   * @see ProgramEvent
   */
  public SIRequest retrieveFutureProgramEvents(Date begin, Date end, SIRequestor requestor)
		throws SIException {
        if ( requestor == null )
                throw new NullPointerException("SIRequestor null");
        if ( begin == null || end == null )
                throw new NullPointerException("null date");
        if (end.after(new Date()) == false) 
                throw new SIException("not a valid time value (end before current)" ); 
        if (end.before( begin ) )
                throw new SIException("not a valid time value (end before begin)" ); 

	int reqKind = Settings.REQ_FUTURE_PROGRAM_EVENTS;

	return new SIRequestImpl(requestor, service.getLocator(), reqKind, begin, end);
  }
  
  /**
   * Retrieves a program event matching the locator. Note that
   * the event must be part of this schedule.<p>
   * 
   * This method returns data asynchronously.
   *
   * @param locator Locator referencing the <code>ProgramEvent</code>
   * of interest.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieveal request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>ProgramEvent</code>.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see ProgramEvent
   * @see javax.tv.service.ReadPermission
   */
  public SIRequest retrieveProgramEvent(Locator locator, SIRequestor requestor)
		 throws InvalidLocatorException, SecurityException {

        if ( requestor == null )
                throw new NullPointerException("SIRequestor null");

        if ( locator == null )
                throw new NullPointerException("Locator null");

	if (LocatorImpl.isProgramEvent(locator) == false) {
		throw new InvalidLocatorException(locator);
	}

	if (!LocatorImpl.getServiceName(service.getLocator()).equals(
                        LocatorImpl.getServiceName(locator))) {

		throw new InvalidLocatorException(locator, "not part of ProgramSchedule");
	}

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		sm.checkPermission(new ReadPermission(locator));
	}

	return new SIRequestImpl(requestor, locator);
  }
  
 /**
  * Registers an <code>SIChangeListener</code> to be notified of changes to
  * program events on this <code>ProgramScheduleImpl</code>.<p>
  *
  * This method is only a request for notification.  No guarantee is
  * provided that the SI database will detect all, or even any,
  * changes to the <code>ProgramScheduleImpl</code>, or whether such changes
  * will be detected in a timely fashion.<p>
  * 
  * If the specified <code>SIChangeListener</code> is already
  * registered, no action is performed.
  *
  * @param listener An <code>SIChangeListener<code> to be notified of
  * changes to program events on this <code>ProgramScheduleImpl</code>.
  *
  * @see ProgramEvent
  */
  public void addListener(ProgramScheduleListener listener) {
	if (listener == null) {
		throw new NullPointerException("ProgramScheduleImpl: listener == null");
	}

	Vector listeners = getListeners();	
	listeners.removeElement(listener);
	listeners.addElement(listener);
  }
	
 /**
  * Called to unregister an <code>SIChangeListener</code>.  If the
  * specified <code>SIChangeListener</code> is not registered, no
  * action is performed.
  *
  * @param listener A previously registered listener.
  */
  public void removeListener(ProgramScheduleListener listener) {
	if (listener == null) {
		throw new NullPointerException("ProgramScheduleImpl: listener == null");
	}

	Vector listeners = getListeners();	
	listeners.removeElement(listener);
  }
	
  /**
   * Retrieves an event which follows the specified event.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param anEvent A reference event
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieveal request.
   *
   * @see ProgramEvent
   */
  public SIRequest retrieveNextProgramEvent(ProgramEvent anEvent, SIRequestor requestor)
	throws SIException {

        if ( requestor == null )
                throw new NullPointerException("SIRequestor null");
        if ( anEvent == null )
                throw new NullPointerException("ProgramEvent null");

	if (!LocatorImpl.getServiceName(service.getLocator()).equals(
                        LocatorImpl.getServiceName(anEvent.getLocator()))) {
	   throw new SIException("Not a part of this ProgramSchedule");
	}

	int reqKind = Settings.REQ_NEXT_PROGRAM_EVENT;

	return new SIRequestImpl(requestor, service.getLocator(), reqKind, anEvent);
  }

 /**
  * Reports the transport-dependent locator referencing the service to
  * which this <code>ProgramSchedule</code> belongs.  Note that
  * applications may use this method to establish the identity of
  * a <code>ProgramSchedule</code> after it has changed.
  *
  * @return The transport-dependent locator referencing the service to
  * which this <code>ProgramSchedule</code> belongs.
  *
  * @see ProgramScheduleEvent#getProgramSchedule
  **/

  public Locator getServiceLocator() {
  	return this.service.getLocator();
  }

  /**
   *
   */
  private Vector getListeners() {
	String key = service.getLocator().toExternalForm();
	Vector listeners = (Vector)listenerTable.get(key);
	if (listeners == null) {
		listeners = new Vector();
		listenerTable.put(key, listeners);
	}
	return listeners;
  }

  /**
   * Notify all listeners that this program schedule has been changed.
   */
  public void notifyListeners(ProgramScheduleEvent event) {

	Vector listeners = getListeners();	
	for (int i = 0; i < listeners.size(); i++) {
		ProgramScheduleListener listener = (ProgramScheduleListener)listeners.elementAt(i);
		if (listener == null)
			continue;

		notifyAsyncListener(event, listener);
	}
  }

  private void notifyAsyncListener(
		ProgramScheduleEvent event,
		ProgramScheduleListener listener) {

	if (listener == null || event == null)
		return;

	NotifyProgramScheduleThread thread = new NotifyProgramScheduleThread(event, listener);
	if (thread != null) 
		thread.start();
  }
}

class NotifyProgramScheduleThread extends Thread {
	ProgramScheduleListener listener = null;
	ProgramScheduleEvent event = null;

  public NotifyProgramScheduleThread(
		ProgramScheduleEvent event,
		ProgramScheduleListener listener) {

	super("NotifyProgramScheduleThread");

	this.listener = listener;
	this.event = event;
  }

  public void run() {
	if (this.listener == null || this.event == null)
		return;

	listener.notifyChange(this.event);
  }
}
