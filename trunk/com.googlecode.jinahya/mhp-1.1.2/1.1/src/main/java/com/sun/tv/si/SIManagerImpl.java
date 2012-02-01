/*
 * @(#)SIManagerImpl.java	1.8  04/05/2000
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
import javax.tv.service.guide.*;
import javax.tv.service.selection.*;
import javax.tv.service.navigation.*;
import javax.tv.service.transport.*;
import com.sun.tv.*;
import com.sun.tv.receiver.*;

/**
 * SI Manager represents the central managing entity which has a knowledge
 * of the entire network or a collection of networks and can create a
 * collection of Services based on the ServiceGroup filtering rules. It can
 * also be used to retrieve any SIElement. <p>
 *
 * The SIManager may be used to set some global parameters such as
 * preferred language for text information which may be delivered as
 * multilingual strings to the receiver.
 */

public class SIManagerImpl extends SIManager implements ReceiverListener, Runnable {

	private String language = Settings.PreferredLanguage;

	private static Vector requests = new Vector();
	private static Vector newRequests = new Vector();
	private static Thread timeoutThread = null;
	private static Hashtable FavoriteServices = new Hashtable();

 /*
  * Since there is a very vague description of what Transport
  * may be and what kinds of transport there might be, for now we allocate
  * only one element array. And due to the obscure hints found in the API
  * spec it's going to be a MPEG-2 transport...
  */
	private static Transport transports[] = new Transport[1];

	static {
		if (transports[0] == null) {
			transports[0] = new TransportImpl(1, DeliverySystemType.UNKNOWN);
		}
	}

	public static void putFavoriteServices(String localName, String shortNames[]) {
		if (shortNames == null || shortNames.length == 0)
			return;

		FavoriteServicesNameImpl list[] = new FavoriteServicesNameImpl[shortNames.length];
		for (int i = 0; i < shortNames.length; i++) {
			list[i] = new FavoriteServicesNameImpl(shortNames[i]);
		}
		FavoriteServices.put(localName, list);
	}

	public static FavoriteServicesName[] getFavoriteServices(String localName) {
		return (FavoriteServicesName[])FavoriteServices.get(localName);
	}

	public static FavoriteServicesName[] getFavoriteServicesNames() {
		FavoriteServicesName list[] = new FavoriteServicesName[FavoriteServices.size()];

		int count = 0;
		Enumeration keys = FavoriteServices.keys();
		while (keys.hasMoreElements()) {
			String name = (String)keys.nextElement();
			list[count++] = new FavoriteServicesNameImpl(name);
		}
		return list;
	}

  /**
   * Sets the language used to return any textual information from the SI
   * related classes and interfaces (e.g. Service name, etc.) if provided
   * as a multilingual string in multiple languages. If the specified
   * language is not available, the system-level preferred language is
   * used. If that language is not available either, the first available
   * language will be used.<p>
   *
   * This method is used to temporarily override the system-level preferred
   * language within the abstractSI package.
   *
   * @param language A string representing a language code defining the
   * language associated with this component. It contains a three-character
   * code as specified by ISO 639.2/B. If the input parameter is null, the
   * application set preference is cancelled.
   */
  public void setPreferredLanguage(String language) {
	if (language == null)
		this.language = Settings.PreferredLanguage;
	this.language = language;
  }
  
  /**
   * Called to determine the preferred language for returning string-type
   * values.
   *
   * @return A string representing a language code defining the language
   * used to retrieve multilingual strings. It contains a three-character
   * code as specified by ISO 639.2/B. Null is returned when there is no
   * language preference defined.
   */
  public String getPreferredLanguage() {
	return language;
  }
  
  /**
   * This method provides a hint to the SI Database that the application
   * would like to have as complete SI information as possible about the
   * specified SI element. The SI Database may tune to the transport stream
   * delivering the information and start caching it (depending on resource
   * availability). Note that this method returns immediately and that
   * there is no event signaling any kind of completion. Also note that
   * this is just a hint for cache optimization on the receiver and no
   * prescribed behavior is guaranteed.<p>
   * 
   * For example, if the locator points to a bouquet, the database may
   * start caching ServiceDetails information for services which are
   * part of the specified bouquet. If the locator points to a
   * Service, the database may start caching ProgramEvents for this
   * service.
   *
   * @param locator A locator referencing the SI element of interest.
   *
   * @param active A flag indicating whether this interest is active
   * or not. <code>True</code> means that the application is
   * interested in the data; <code>false</code> means that the
   * application wants to cancel a previously shown interest for the
   * same locator.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>SIElement</code> for expressing interest.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see javax.tv.service.ReadPermission
   */
  public void registerInterest(Locator locator, boolean active)
	throws InvalidLocatorException, SecurityException {

        if(locator == null) {
		throw new NullPointerException();
	}

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		sm.checkPermission(new ReadPermission(locator));
	}

// fix for 4337875 -- check if locator points to a SIElement
         if(!(LocatorImpl.isSIElement(locator))) {
          throw new InvalidLocatorException(locator,"Locator not referring to a valid SIElement");
	}

	// TBD:
	// This reference implementation caches everything, to register
	// interest has no effect, as all SIElements are of interest.
	// At least for the time being.
  
  }

  /**
   * Provides a list of names of available rating dimensions for the local
   * rating region.
   *
   * @return An array of Strings representing names of available rating
   * dimensions for this rating region.
   *
   * @see RatingDimension
   */
  public java.lang.String[] getSupportedDimensions() {
	return RatingDimensionImpl.getSupportedDimensions();
  }

  /**
   * Returns the Rating Dimension corresponding to the specified Dimension
   * name.
   *
   * @param name Name of the requested rating dimension
   *
   * @return The requested RatingDimension.
   */
  public RatingDimension getRatingDimension(String name) throws SIException {
	if (name == null)
	   throw new NullPointerException();

	RatingDimension dimension = RatingDimensionImpl.getRatingDimension(name);
	if (dimension == null) 
		throw new SIException("Dimension " + name + " not found.");

	return dimension;
  }
  
  
  /**
   * Reports the various content delivery mechanisms available on
   * this platform.
   *
   * @return An array of <code>Transport</code> objects representing
   * the content delivery mechanisms on this platform. 
   */
   public Transport[] getTransports() {
	return transports;
   }
  
  /**
   * This method retrieves an SIElement corresponding to the specified
   * Locator. If the locator identified more than one SIElement, all
   * matching SIElements are returned in the form of an array. <p>
   *
   * For example, multiple SIElements may be returned when the locator
   * represents identical content delivered over different media
   * (terrestrial, satellite, cable, etc) or a specific program event made
   * available at different times, possibly on different services. <p>
   *
   * This call may return different types of SIElements based on the
   * specified locator. E.g. if the locator is transport independent
   * locator, a corresponding Service is returned; if the locator is
   * transport dependent, ServiceDetails object is returned; if the locator
   * represents a program event; ProgramEvent object is returned; etc.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param locator A locator referencing one or more SIElements.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>SIElement</code>.
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   *
   * @see SIElement
   * @see javax.tv.service.guide.ProgramEvent
   */
  public SIRequest retrieveSIElement(Locator locator, SIRequestor requestor)
		throws InvalidLocatorException, SecurityException {

	if (locator == null || requestor == null) {
		throw new NullPointerException();
	}

	if (LocatorImpl.validLocator(locator) == false) {
		throw new InvalidLocatorException(locator);
	}

	if (LocatorImpl.isSIElement(locator) == false) {
		throw new InvalidLocatorException(locator);
	}

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		sm.checkPermission(new ReadPermission(locator));
	}
	return new SIRequestImpl(requestor, locator);
  }
  
  /**
   * This method can be used to obtain a specific Service based on a
   * Locator. <p>
   *
   * Note that the locator may point to an SIElement lower in the hierarchy
   * (such as a ProgramEvent). In such a case the Service which the
   * ProgramEvent is part of will be returned.
   *
   * @param locator A locator specifying a Service
   *
   * @return A Service object corresponding to the specified locator.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>Service</code>.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see javax.tv.service.ReadPermission
   */
  public Service getService(Locator locator) throws
		InvalidLocatorException, SecurityException { 

	if (locator == null) 
		throw new NullPointerException();

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) 
		sm.checkPermission(new ReadPermission(locator));

     	if (!LocatorImpl.isService(locator))
		throw new InvalidLocatorException(locator, locator.toExternalForm());

	CacheManager serviceCache = CacheManager.getServiceCache();
	if (serviceCache == null)
		throw new NullPointerException();

	Object object = serviceCache.get(locator);
	if (!(object instanceof Service))
		throw new InvalidLocatorException(locator, locator.toExternalForm());

	return (Service)object;
  }

  /**
   * This method can be used to retrieve specific ServiceDetails (meta-data
   * for a Service) based on a Locator. Note that the locator may point to
   * an SIElement lower in the hierarchy (such as a ProgramEvent). In such
   * a case the ServiceDetails which the ProgramEvent is part of will be
   * returned. <p>
   *
   * Also since a transport-independent locator may represent multiple
   * transport-dependent services, an array of ServiceDetails may be
   * returned. <p>
   *
   * If multiple ServiceDetails objects are available for the given locator
   * (e.i. the locator is transport independent), this call may return only
   * one of them (choice made by implementation based on e.g. user
   * preferences and availability). <p>
   *
   * This is because it will probably take a long time to retrieve all of
   * them while one could be returned very quickly. It may not even be
   * possible to always get all of them (e.g. tuning would be required).
   * <p>
   *
   * If the application really wants to get all of them, it can always
   * transform the trnsport-independent locator into multiple
   * transport-dependent locators and retrieve the ServiceDetails for each
   * one of them.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param locator A locator referencing a Service
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>Service</code>.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see Locator
   * @see ServiceDetails
   * @see javax.tv.service.ReadPermission
   */
  public SIRequest retrieveServiceDetails(Locator locator, SIRequestor requestor) 
		throws InvalidLocatorException, SecurityException {

	if (locator == null || requestor == null) {
		throw new NullPointerException();
	}

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		sm.checkPermission(new ReadPermission(locator));
	}

        Locator detailsLoc = null;

        if (LocatorImpl.isTIService(locator)) {
           Locator[] locs = LocatorImpl.transformLocator(locator);
           if (locs != null && locs.length > 0) {
              detailsLoc = locs[0];
           }
        } else {
	   detailsLoc = LocatorImpl.transformToService(locator);
        }

	if (detailsLoc == null || LocatorImpl.isService(detailsLoc) == false) {
		throw new InvalidLocatorException(locator, locator.toExternalForm());
	}

	String detailsStr = detailsLoc.toExternalForm();
	if (detailsStr == null) {
		throw new InvalidLocatorException(locator, locator.toExternalForm());
	}

	return new SIRequestImpl(requestor, detailsLoc);
  }
  
  /**
   * This method can be used to retrieve a specific ProgramEvent based on a
   * Locator. Note that since a transport-independent locator may represent
   * multiple instances of a ProgramEvent (e.g. the same movie shown at
   * different times and/or on different services), this method may return
   * multiple ProgramEvents.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param locator A locator referencing a ProgramEvent
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>ProgramEvent</code>.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see javax.tv.service.guide.ProgramEvent
   * @see javax.tv.service.ReadPermission
   */
  public SIRequest retrieveProgramEvent(Locator locator, SIRequestor requestor)
		throws InvalidLocatorException, SecurityException {

        if(locator == null || requestor == null)
	   throw new NullPointerException();

	if (LocatorImpl.isProgramEvent(locator) == false) {
		throw new InvalidLocatorException(locator);
	}

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		sm.checkPermission(new ReadPermission(locator));
	}

	return new SIRequestImpl(requestor, locator);
  }

  /**
   * Creates a ServiceList object based on the conditions
   * specified in a filter parameter. If the filter is
   * <code>null</code>, a list of all known services is
   * generated.  Only Service instances for which the caller has
   * javax.tv.service.ReadPermission on the underlying locator will
   * be present in the returned list.<p>
   * 
   * Note that the <code>accept</code> method of the given
   * <code>ServiceFilter</code> will be invoked for each
   * <code>Service</code> to be filtered using the same application
   * thread that invokes <code>filterServices</code>.
   *
   * @param filter A <code>ServiceFilter</code> by which to generate
   * the requested service list, or <code>null</code>.
   *
   * @return A <code>ServiceList</code> object created based on
   * the specified filtering rules.
   *
   * @see ServiceFilter#accept
   */
  public ServiceList filterServices(ServiceFilter filter) {
	Vector services = new Vector();

	CacheManager serviceCache = CacheManager.getServiceCache();
	if (serviceCache == null)
		return new ServiceListImpl(services);
	
	Enumeration list = serviceCache.elements();
	while (list.hasMoreElements()) {
		Object object = list.nextElement();
		if (!(object instanceof Service))
			continue;

		Service service = (Service)object;
		if (filter != null && filter.accept(service) == false)
			continue;

		try {
			SecurityManager sm = System.getSecurityManager();
			if (sm != null) {
				sm.checkPermission(new ReadPermission(service.getLocator()));
			}
			services.addElement(service);	
		} catch (Exception e) {
			;
		}
	}
	return new ServiceListImpl(services);
  }

	/**
	 * This method handles asynchronous SIElement requests, if the
	 * request can be full-filled by the existing cached data then the
	 * results are immediatley returned. Otherwise, the request is
	 * stored, and will be full-filled either when a new SIElement
	 * enters the system, or when the request expires.
	 * @note this method is not part of the SIManager API
	 */
	public void makeRequest(SIRequestImpl request) {
		if (timeoutThread == null) {
			timeoutThread = new Thread(this, "Request Timeout Thread");
			timeoutThread.start();
		}

		synchronized (newRequests) {
				newRequests.addElement(request);
		}
	}

	public boolean hasPermission(Locator locator) {
		SecurityManager sm = System.getSecurityManager();
		if (sm == null)
			return true;

		try {
			sm.checkPermission(new ReadPermission(locator));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * Find a match in the SI cache for the provided request; a variety
	 * of requests are served by this method. The request will be uniquely
	 * handled by the requestKind. Most requets can be handled by searching
	 * the SI Element cache.
	 * @param request the request for match is being found.
	 * @note this method is not part of the SIManager API
	 */
	public Vector findSIElements(SIRequestImpl request) {
		Vector results = new Vector();

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return results;

		Enumeration elements = siCache.elements();

		switch (request.getRequestKind()) {
		case Settings.REQ_GENERAL:
		case Settings.REQ_SERVICE_COMPONENT:
		case Settings.REQ_SERVICE_DESCRIPTION:
		case Settings.REQ_PROGRAM_EVENT:
		default:
			while (elements.hasMoreElements()) {
				SIElement element = (SIElement)elements.nextElement();
				if (request.equals(element) == false)
					continue;

				if (hasPermission(element.getLocator()) == false)
					continue;

				results.addElement(element);
			}
			break;

		case Settings.REQ_CURRENT_PROGRAM_EVENT:
			Date current = new Date(System.currentTimeMillis());
			while (elements.hasMoreElements()) {
				SIElement element = (SIElement)elements.nextElement();
				if (request.equals(element) == false)
					continue;

				ProgramEventImpl pe = (ProgramEventImpl)element;
				if (pe.compareTo(current) != 0)
					continue;

				if (hasPermission(pe.getLocator()) == false)
					continue;

				results.addElement(element);
				break;
			}
			break;

		case Settings.REQ_FUTURE_PROGRAM_EVENT:
			Date futureDate = (Date)request.getUserData(0);
			while (elements.hasMoreElements()) {
				SIElement element = (SIElement)elements.nextElement();
				if (request.equals(element) == false)
					continue;

				ProgramEventImpl pe = (ProgramEventImpl)element;
				if (pe.compareTo(futureDate) != 0)
					continue;

				if (hasPermission(pe.getLocator()) == false)
					continue;

				results.addElement(element);
				break;
			}
			break;

		case Settings.REQ_FUTURE_PROGRAM_EVENTS:
			Date begDate = (Date)request.getUserData(0);
			Date endDate = (Date)request.getUserData(1);
			while (elements.hasMoreElements()) {
				SIElement element = (SIElement)elements.nextElement();
				if (request.equals(element) == false)
					continue;

				ProgramEventImpl pe = (ProgramEventImpl)element;

				if (hasPermission(pe.getLocator()) == false)
					continue;

				if (pe.compareTo(begDate) >= 0  &&
				    pe.compareTo(endDate) <= 0) {
					results.addElement(element);
				}
			}
			break;

		case Settings.REQ_NEXT_PROGRAM_EVENT:
			ProgramEvent nextEvent = null;
			ProgramEvent curEvent = (ProgramEvent)request.getUserData(0);
			long nearest = 2147483647; // FIXME
			Date lastDate = curEvent.getEndTime();

			while (elements.hasMoreElements()) {
				SIElement element = (SIElement)elements.nextElement();
				if (request.equals(element) == false) 
					continue;

				ProgramEventImpl pe = (ProgramEventImpl)element;
				//if (hasPermission(pe.getLocator()) == false)
			//		continue;

				long dt = pe.compareTo(lastDate);
				if (dt >= 0 && dt < nearest) {
					nearest = dt;
					nextEvent = pe;
				}
			}

			if (nextEvent != null && hasPermission(nextEvent.getLocator())) {
				results.addElement(nextEvent);
			}
			break;

		case Settings.REQ_TRANSPORT_STREAM:
			while (elements.hasMoreElements()) {
				SIElement element = (SIElement)elements.nextElement();
				if (request.equals(element) == false) 
					continue;
			        if (hasPermission(element.getLocator())) {
				results.addElement(element);
			}
		}
		}

		return results;
	}

	/**
	 * This implements the cancel of an async. request made within JavaTV.
	 * The request to cancel is remove from the queue of requests, and the
	 * requestor is notified of the request failure. Requests are generally 
	 * cancelled, because they have been timed out.
	 * @note this method is not part of the SIManager API
	 */

	public boolean cancel(SIRequest toCancel) {
		synchronized (requests) {
			for (int i = 0; i < requests.size(); i++) {
				SIRequestImpl request = (SIRequestImpl)requests.elementAt(i);
				if (request == toCancel) {
					requests.removeElementAt(i);
					
					try {
						request.notifyFailure(SIRequestFailureType.CANCELED);
					} catch (Exception e) {
						;
					}
					return true;
				}
			}
			return false;
		}
	}

	public boolean SIElementExists(SIElement element) {
		if (element == null)
			return false;

		if (element instanceof Service) {
			CacheManager serviceCache = CacheManager.getServiceCache();
			if (serviceCache == null)
				return false;

			return serviceCache.containsKey(element.getLocator());

		} else {
			CacheManager siCache = CacheManager.getSICache();
			if (siCache == null)
				return false;

			return siCache.containsKey(element.getLocator());
		}
	}

	private void ReceiveSIElement(SIElement element) {
		if (element == null)
			return;

		if (element instanceof Service) {
			CacheManager serviceCache = CacheManager.getServiceCache();
			if (serviceCache == null)
				return;

			serviceCache.put(element.getLocator(), element);

		} else {
			CacheManager siCache = CacheManager.getSICache();
			if (siCache == null)
				return;

			siCache.put(element.getLocator(), element);
		}
	}

	private void RemoveSIElement(SIElement element) {
		if (element == null)
			return;

		if (element instanceof Service) {
			CacheManager serviceCache = CacheManager.getServiceCache();
			if (serviceCache == null)
				return;

			serviceCache.remove(element.getLocator());

		} else {
			CacheManager siCache = CacheManager.getSICache();
			if (siCache == null)
				return;

			siCache.remove(element.getLocator());
		}
	}

	private void TerminateService(SIElement element) {
		if (element == null || !(element instanceof ServiceImpl))
			return;

		try {
			ServiceImpl service = (ServiceImpl)element; 
			int reason = service.getPresentationTerminatedReason();
			if (reason == 0) {
				reason = PresentationTerminatedEvent.SERVICE_VANISHED;
			}
	
			Locator serviceLoc = service.getLocator();
			if (serviceLoc == null) {
				throw new Exception("service locator is null");
			}
	
			ServiceContextFactory scf = ServiceContextFactory.getInstance();
			if (scf == null) {
				throw new Exception("service context factory is null");
			}
	
			ServiceContext contexts[] = scf.getServiceContexts();
			if (contexts == null) {
				throw new Exception("service contexts is null");
			}

			for (int i = 0; i < contexts.length; i++) {
				ServiceContextImpl context = (ServiceContextImpl)contexts[i];
	
				Locator currentLoc = context.getServiceLocator();
				if (currentLoc == null) 
					continue;
	
				if (LocatorImpl.equals(currentLoc, serviceLoc) == true) {
					context.stop0(reason);
				}
			}
		} catch (Exception e) {
			System.out.println("TerminateService: " + e);
		}
	}

	/**
	 * This method notifies all requestors which are waiting in the requests queue,
	 * of the si element. Once notified remove the requestor from the queue.
	 */
	private void NotifyRequestors(SIElement element) {
		synchronized (requests) {
			for (int i = requests.size()-1; i >= 0; i--) {
				SIRequestImpl request = (SIRequestImpl)requests.elementAt(i);
	
				Vector results = findSIElements(request);

				if (results.size() <= 0) 
					continue;
	
				SIRetrievable response[] = new SIRetrievable[results.size()];
	
				for (int j = 0; j < results.size(); j++) {
					response[j] = (SIRetrievable)results.elementAt(j);
				}
	
				requests.removeElementAt(i);
	
				try {
					request.notifySuccess(response);
				} catch (Exception e) {
					;
				}
			}
		}
	}

	/**
	 * Notify the Program Schedule listeners when a program event
	 * change occurs within a program schedule.
	 */
	private void NotifyScheduleListeners(SIChangeEvent event) {
		if (event == null)
			return;

		SIElement element = event.getSIElement();
		if (element == null || !(element instanceof ProgramEvent))
			return;

		Locator locator = element.getLocator();
		if (locator == null) 
			return;

		if (LocatorImpl.isProgramEvent(locator) == false) 
			return;

		Locator detailsLoc = LocatorImpl.transformToService(locator);
		if (detailsLoc == null || LocatorImpl.isService(detailsLoc) == false)
			return;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		ServiceDetails details = (ServiceDetails)siCache.get(detailsLoc);
		if (details == null) 
			return;

		ProgramScheduleImpl schedule = (ProgramScheduleImpl)details.getProgramSchedule();
		if (schedule == null)
			return;

		ProgramEvent pe = (ProgramEvent)event.getSIElement();
		SIChangeType ct = event.getChangeType();

		ProgramScheduleEvent pce = new ProgramScheduleEvent(schedule, ct, pe);

		schedule.notifyListeners(pce);
	}

	/**
	 * Notify the ServiceDetails listeners when a service detail 
	 * change occurs within a transport.
	 */
	private void NotifyServiceDetailsListeners(SIChangeEvent event) {
		if (event == null)
			return;

		SIElement element = event.getSIElement();
		if (element == null || !(element instanceof ServiceDetails))
			return;

		Locator locator = element.getLocator();
		if (locator == null) 
			return;

		if (LocatorImpl.isService(locator) == false) 
			return;

		Locator detailsLoc = locator;

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		ServiceDetails details = (ServiceDetails)siCache.get(detailsLoc);
		if (details == null) 
			return;

		TransportImpl transport = (TransportImpl)transports[transports.length-1];
		if (transport == null)
			return;

		SIChangeType ct = event.getChangeType();
		ServiceDetailsChangeEvent sdce = new ServiceDetailsChangeEvent(
			transport, ct, details);

		transport.notifyServiceDetailsListeners(sdce);
	}

	/**
	 * Notify the Bouquet listeners
	 */
	private void NotifyBouquetListeners(SIChangeEvent event) {
		if (event == null)
			return;

		SIElement element = event.getSIElement();
		if (element == null || !(element instanceof Bouquet))
			return;

		TransportImpl transport = (TransportImpl)transports[transports.length-1];
		if (transport == null)
			return;

		SIChangeType ct = event.getChangeType();
		BouquetChangeEvent sdce = new BouquetChangeEvent(
			transport, ct, (Bouquet)element);

		transport.notifyBouquetListeners(sdce);
	}

	/**
	 * Notify the Network listeners
	 */
	private void NotifyNetworkListeners(SIChangeEvent event) {
		if (event == null)
			return;

		SIElement element = event.getSIElement();
		if (element == null || !(element instanceof Network))
			return;

		TransportImpl transport = (TransportImpl)transports[transports.length-1];
		if (transport == null)
			return;

		SIChangeType ct = event.getChangeType();
		NetworkChangeEvent sdce = new NetworkChangeEvent(
			transport, ct, (Network)element);

		transport.notifyNetworkListeners(sdce);
	}

	/**
	 * Notify the Transport Stream listeners
	 */
	private void NotifyTransportStreamListeners(SIChangeEvent event) {
		if (event == null)
			return;

		SIElement element = event.getSIElement();
		if (element == null || !(element instanceof TransportStream))
			return;

		TransportImpl transport = (TransportImpl)transports[transports.length-1];
		if (transport == null)
			return;

		SIChangeType ct = event.getChangeType();
		TransportStreamChangeEvent sdce = new TransportStreamChangeEvent(
			transport, ct, (TransportStream)element);

		transport.notifyTransportStreamListeners(sdce);
	}

	/**
	 * Notify the ServiceDetails listeners when a service detail 
	 * change occurs within a transport.
	 */
	private void NotifyServiceComponentListeners(SIChangeEvent event) {
		if (event == null)
			return;

		SIElement element = event.getSIElement();
		if (element == null || !(element instanceof ServiceComponent))
			return;

		Locator locator = element.getLocator();
		if (locator == null) 
			return;

		if (LocatorImpl.isServiceComponent(locator) == false)  {
System.out.println("NotifyServiceComponentListeners: " + locator);
			return;
		}

		Locator detailsLoc = LocatorImpl.transformToService(locator);
		if (detailsLoc == null || LocatorImpl.isService(detailsLoc) == false) {
System.out.println("NotifyServiceComponentListeners: detialsLoc == null ");
			return;
		}

		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null) {
System.out.println("NotifyServiceComponentListeners: siCache == null ");
			return;
		}

		ServiceDetailsImpl details = (ServiceDetailsImpl)siCache.get(detailsLoc);
		if (details == null) {
System.out.println("NotifyServiceComponentListeners: details == null ");
			return;
		}

		SIChangeType ct = event.getChangeType();
		ServiceComponentChangeEvent scce = new ServiceComponentChangeEvent(
			details, ct, (ServiceComponent)element);

		details.notifyListeners(scce);
	}

	/**
	 * This interface method handles SI events generated by the receiver,
	 * implementors are required to have their receivers generate the SI event
	 * passing the event to this method. This Reference Implementation uses the
	 * SIEmulator class to feed it the SIChangeEvent's.
	 */
	public void notifyChange(SIChangeEvent event) {
		SIChangeType changeType = event.getChangeType();
		SIElement element = event.getSIElement();

		if (changeType == null) 
			return;

		if (changeType.equals(SIChangeType.REMOVE)) {
			NotifyServiceDetailsListeners(event);
			NotifyServiceComponentListeners(event);
			NotifyBouquetListeners(event);
			NotifyNetworkListeners(event);
			NotifyTransportStreamListeners(event);
			NotifyScheduleListeners(event);

			TerminateService(element);
			RemoveSIElement(element);
	
		} else if (changeType.equals(SIChangeType.MODIFY)) {
			ReceiveSIElement(element);
			NotifyRequestors(element);

			NotifyServiceDetailsListeners(event);
			NotifyServiceComponentListeners(event);
			NotifyBouquetListeners(event);
			NotifyNetworkListeners(event);
			NotifyTransportStreamListeners(event);
			NotifyScheduleListeners(event);

		} else if (changeType.equals(SIChangeType.ADD)) {
			ReceiveSIElement(element);
			NotifyRequestors(element);

			NotifyServiceDetailsListeners(event);
			NotifyServiceComponentListeners(event);
			NotifyBouquetListeners(event);
			NotifyNetworkListeners(event);
			NotifyTransportStreamListeners(event);
			NotifyScheduleListeners(event);
		}
	}

	/**
	 * New Requests should be matched against the existing database first,
	 * and then fiull-filled if a match. Otherwise, new requests go onto
	 * a queue and wait for new SI Elements to enter the system.
	 */
	private void processNewRequests() throws Exception {
		if (newRequests == null || newRequests.size() == 0)
			return; 

	    synchronized(newRequests) {
		for (int i = 0; i < newRequests.size(); i++) {
			SIRequestImpl request = (SIRequestImpl)newRequests.elementAt(i);
			if (request == null)
				continue;

			Vector results = findSIElements(request);
			if (results.size() > 0) {
				SIRetrievable response[] = new SIRetrievable[results.size()];

				for (int j = 0; j < results.size(); j++) {
					response[j] = (SIRetrievable)results.elementAt(j);
				}

				try {
					request.notifySuccess(response);
				} catch (Exception e) {
					;
				}
			} else {
				synchronized (requests) {
					requests.addElement(request);
				}
			}
		}
		newRequests.removeAllElements();
	    }
	}

	/**
	 * Requests which haven't been fullfilled after an explicit time, should
	 * be timedout and the request cancelled. See the Settings class for the
	 * time out interval.
	 */
	private void removeOldRequests() throws Exception {
		if (requests == null || requests.size() == 0)
			return; 

		synchronized(requests) {
		for (int i = requests.size()-1; i >= 0; i--) {
			SIRequestImpl request = (SIRequestImpl)requests.elementAt(i);
			if (request.isExpired() == false)
				continue;

System.out.println("SIManagerImpl Expired: "+request.getLocator().toExternalForm());

			requests.removeElementAt(i);

			SIRequestFailureType reason =
				SIRequestFailureType.DATA_UNAVAILABLE;

			try {
				request.notifyFailure(reason);
			} catch (Exception e) {
				;
			}
		}
		}
	}

/**
 *  This async. method will process the cached SI requests checking for expired
 *  requests. Requests can currently expire by elapsed time, although, the could
 *  also expire by cache size limit. The requestor is notified of an expired 
 *  request via the notifyFailure method.
 *  Note: It's important that the requests vector is processed in descending order,
 *  so that if the a request is removed from the cache, then the for loop interator
 *  <code>i</code> will continue to work properly.
 */
	public void run() {
		final int pollingInterval = 1000;
		while (true) {
			try {
				processNewRequests();
			} catch (Exception e) {
				;
			}

			try {
				removeOldRequests();
			} catch (Exception e) {
				;
			}

			try {
				Thread.sleep(pollingInterval);
			} catch (Exception e) {
				;
			}
		}
	}
} 
