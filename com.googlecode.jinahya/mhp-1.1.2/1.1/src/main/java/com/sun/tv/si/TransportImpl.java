/*
 * @(#)TransportImpl.java	1.18 00/01/14
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
import com.sun.tv.*;
import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.transport.*;
import javax.tv.service.navigation.*;

/**
 *
 * This interface represents an individual content delivery mechanism.
 * <code>Transport</code> objects serve as an access point for acquiring
 * information about services and their groupings.<p>
 *
 * A <code>Transport</code> object may expose various types of
 * entities (e.g. Bouquets, Networks and/or TransportStreams) by
 * implementing additional optional interfaces
 * (i.e. <code>BouquetCollection</code>,
 * <code>NetworkCollection</code>, and/or
 * <code>TransportStreamCollection</code>), depending on the particular
 * SI format used and the presence of optional elements and tables in
 * the SI data being broadcast.
 *
 * @see BouquetCollection
 * @see NetworkCollection
 * @see TransportStreamCollection
 */
public class TransportImpl implements
	BouquetCollection, NetworkCollection, TransportStreamCollection {

	private static Hashtable detailsListeners = new Hashtable();
	private static Hashtable bouquetListeners = new Hashtable();
	private static Hashtable networkListeners = new Hashtable();
	private static Hashtable transportStreamListeners = new Hashtable();

        private int transportID = -1;
        private DeliverySystemType deliverySystemType;
        private Locator locator = null;

  public TransportImpl(
                int transportID,
                DeliverySystemType deliverySystemType) {

        this.transportID = transportID;
        this.deliverySystemType = deliverySystemType;
  }


  /**
   * Reports the type of mechanism by which this
   * <code>Transport</code> delivers content.
   *
   * @return The delivery system type of this transport.
   */
  public DeliverySystemType getDeliverySystemType() {
        return this.deliverySystemType;
  }

  /**
   * Registers a <code>ServiceDetailsChangeListener</code> to be notified of
   * changes to <code>ServiceDetails</code> that are carried on
   * this <code>Transport</code>. Subsequent notification is made via
   * <code>ServiceDetailsChangeEvent</code> with this
   * <code>Transport</code> instance as the event source.<p>
   * 
   * This method is only a request for notification.  No guarantee is
   * provided that the SI database will detect all, or even any, SI
   * changes or whether such changes will be detected in a timely
   * fashion.<p>
   * 
   * If the specified <code>ServiceDetailsChangeListener</code> is
   * already registered, no action is performed.
   *
   * @param listener An <code>ServiceDetailsChangeListener</code> to be
   * notified about changes related to <code>ServiceDetails</code>
   * carried on this <code>Transport</code>.
   *
   * @throws SecurityException If the caller does not have
   * <code>javax.tv.service.ReadPermission(locator)</code> for this
   * <code>Transport</code>.
   *
   * @see ServiceDetailsChangeEvent 
   * @see javax.tv.service.ReadPermission
   */
  public void addServiceDetailsChangeListener(ServiceDetailsChangeListener
					      listener) throws SecurityException {
        if ( listener == null ) {
                throw new NullPointerException("SIChangeListener null");
        }

	Vector listeners = getListeners(detailsListeners);
	listeners.removeElement(listener);
	listeners.addElement(listener);
  }
  
  /**
   * Called to unregister an
   * <code>ServiceDetailsChangeListener</code>.  If the specified
   * <code>ServiceDetailsChangeListener</code> is not registered, no
   * action is performed.
   *
   * @param listener A previously registered listener.
   */
  public void removeServiceDetailsChangeListener(ServiceDetailsChangeListener listener) {
       	if ( listener == null ) {
       		throw new NullPointerException("SIChangeListener null");
       	}

	Vector listeners = getListeners(detailsListeners);
	listeners.removeElement(listener);
  }

  /**
   * Notify all listeners that this transport has been changed.
   */
  public void notifyServiceDetailsListeners(ServiceDetailsChangeEvent event) {
	Vector listeners = getListeners(detailsListeners);

	for (int i = 0; i < listeners.size(); i++) {
		ServiceDetailsChangeListener listener = 
			(ServiceDetailsChangeListener)listeners.elementAt(i);
		if (listener == null)
			continue;
		notifyAsyncListener(event, listener);
	}
  }

  /**
   * Retrieves the specified <code>Bouquet</code> from the collection.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param locator Locator referencing the <code>Bouquet</code> of interest
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid bouquet.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see Bouquet
   * @see javax.tv.service.ReadPermission
   */
	public SIRequest retrieveBouquet(Locator locator, SIRequestor requestor)
			throws InvalidLocatorException, SecurityException {

        	if ( requestor == null ) {
               		throw new NullPointerException("SIRequestor null");
        	}
        	if ( locator == null ) {
               		throw new NullPointerException("Locator null");
        	}

		if (LocatorImpl.isBouquet(locator) == false) {
			throw new InvalidLocatorException(locator);
		}

		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(new ReadPermission(locator));
		}

		return new SIRequestImpl(requestor, locator);
	}
	
  /**
   *
   * Retrieves an array of all the <code>Bouquet</code> objects in
   * this <code>BouquetCollection</code>.  This array will only contain
   * <code>Bouquet</code> instances for which the caller has
   * <code>javax.tv.service.ReadPermission</code> on the underlying
   * locator.<p>
   *
   * This method delivers its results asynchronously.
   *   
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @see Bouquet
   */
	public SIRequest retrieveBouquets(SIRequestor requestor) {
        	if ( requestor == null ) {
               		throw new NullPointerException("SIRequestor null");
        	}


		Locator bouquetLocator = null;

		try {

			bouquetLocator = LocatorFactory.getInstance().createLocator(
				LocatorImpl.BouquetProtocol + "*");
		} catch (Exception e) {
			;
		}

		return new SIRequestImpl(requestor, bouquetLocator);
	}

  /**
   * Retrieves the specified <code>Network</code> from the collection.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param locator Locator referencing the <code>Network</code> of interest.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid network.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see Network
   * @see javax.tv.service.ReadPermission
   */
	public SIRequest retrieveNetwork(Locator locator, SIRequestor requestor)
			throws InvalidLocatorException, SecurityException {

        	if ( requestor == null ) {
               		throw new NullPointerException("SIRequestor null");
        	}

        	if ( locator == null ) {
               		throw new NullPointerException("Locator is null");
        	}

		if (LocatorImpl.isNetwork(locator) == false) {
			throw new InvalidLocatorException(locator);
		}

		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(new ReadPermission(locator));
		}

		return new SIRequestImpl(requestor, locator);
	}
  
  /**
   * Retrieves an array of all the <code>Network</code> objects in
   * this <code>NetworkCollection</code>.  The collection will only
   * contain instances for which the caller has
   * <code>javax.tv.service.ReadPermission</code> on the underlying
   * locator.<p>
   *
   * This method delivers its results asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @see Network
   */
	public SIRequest retrieveNetworks(SIRequestor requestor) {
        	if ( requestor == null ) {
               		throw new NullPointerException("SIRequestor null");
        	}


		Locator networkLocator = null;

		try {
			networkLocator = LocatorFactory.getInstance().createLocator(
				LocatorImpl.NetworkProtocol + "*");

		} catch (Exception e) {
			;
		}

		return new SIRequestImpl(requestor, networkLocator);
	}

  /**
   *
   * Retrieves the specified <code>TransportStream</code> from the collection.
   *
   * @param locator Locator referencing the
   * <code>TransportStream<code> of interest
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.  
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid transport stream.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see TransportStream
   * @see javax.tv.service.ReadPermission
   */
	public SIRequest retrieveTransportStream(Locator locator, SIRequestor requestor)
			throws InvalidLocatorException, SecurityException {

        	if ( requestor == null ) {
               		throw new NullPointerException("SIRequestor null");
        	}

        	if ( locator == null ) {
               		throw new NullPointerException("Locator is null");
        	}

		if (LocatorImpl.isTransportStream(locator) == false) {
			throw new InvalidLocatorException(locator);
		}

		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(new ReadPermission(locator));
		}

		return new SIRequestImpl(requestor, locator);
	}
  
  /**
   *
   * Retrieves an array of the <code>TransportStream</code> objects in
   * this <code>TransportStreamCollection</code>.  Only 
   * <code>TransportStream</code> instances for which the caller has
   * javax.tv.service.ReadPermission on the underlying locator will be
   * present in the array. <p>
   * 
   * This method delivers its results asynchronously.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @see TransportStream
   * @see javax.tv.service.ReadPermission
   */
	public SIRequest retrieveTransportStreams(SIRequestor requestor) {
        	if ( requestor == null ) {
               		throw new NullPointerException("SIRequestor null");
        	}


		Locator tsLocator = null;

		try {
			tsLocator = LocatorFactory.getInstance().createLocator(
				LocatorImpl.TransportStreamProtocol + "*");

		} catch (Exception e) {
			;
		}
		return new SIRequestImpl(requestor, tsLocator);
	}

  /**
   * Retrieves an array of <code>TransportStream</code> objects
   * representing the transport streams carried in the specified
   * <code>Network</code>.<p>
   * 
   * This method delivers its results asynchronously.
   *
   * @param locator A locator referencing a <code>Network</code> from
   * which to retrieve transport stream information.
   *
   * @param requestor The <code>SIRequestor</code> to be notified
   * when this retrieval operation completes.
   * 
   * @return An <code>SIRequest</code> object identifying this
   * asynchronous retrieval request.
   *
   * @throws InvalidLocatorException If <code>locator</code> does not
   * reference a valid <code>Network</code> on the <code>Transport</code>
   * implementing this interface.
   *
   * @throws SecurityException if the caller does not have
   *     javax.tv.service.ReadPermission(locator)
   *
   * @see TransportStream
   * @see Transport
   * @see javax.tv.service.ReadPermission
   */
	public SIRequest retrieveTransportStreams(Locator locator, SIRequestor requestor)
		throws InvalidLocatorException, SecurityException {

        	if ( requestor == null ) {
               		throw new NullPointerException("SIRequestor null");
        	}
        	if ( locator == null ) {
               		throw new NullPointerException("Locator is null");
        	}

		if (LocatorImpl.isNetwork(locator) == false) {
			throw new InvalidLocatorException(locator);
		}

		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(new ReadPermission(locator));
		}

		Locator transportLocator = LocatorImpl.transformToTransportStream(locator);
		if (transportLocator == null) {
			throw new InvalidLocatorException(transportLocator);
		}

		return new SIRequestImpl(requestor, transportLocator);
	}

  /**
   * Registers a <code>BouquetChangeListener</code> to be
   * notified of changes to a <code>Bouquet</code> that is
   * part of this <code>BouquetCollection</code>. Subsequent
   * notification is made via <code>BouquetChangeEvent</code>
   * with this <code>BouquetCollection</code> as the event
   * source.<p>
   * 
   * This method is only a request for notification.  No guarantee is
   * provided that the SI database will detect all, or even any, SI
   * changes or whether such changes will be detected in a timely
   * fashion.<p>
   * 
   * If the specified <code>BouquetChangeListener</code> is
   * already registered, no action is performed.
   *
   * @param listener A <code>BouquetChangeListener</code> to be
   * notified about changes related to <code>Bouquet</code>
   * carried on this <code>Transport</code>.
   *
   * @throws SecurityException If the caller does not have
   * <code>javax.tv.service.ReadPermission(locator)</code>.
   *
   * @see BouquetChangeEvent 
   * @see javax.tv.service.ReadPermission
   **/
  public void addBouquetChangeListener(BouquetChangeListener listener)
     throws SecurityException {
        if ( listener == null ) {
                throw new NullPointerException("BouquetChangeListener null");
        }

	Vector listeners = getListeners(bouquetListeners);
	listeners.removeElement(listener);
	listeners.addElement(listener);
  }

  /**
   * Called to unregister an
   * <code>BouquetChangeListener</code>.  If the specified
   * <code>BouquetChangeListener</code> is not registered, no
   * action is performed.
   *
   * @param listener A previously registered listener.  */
  public void removeBouquetChangeListener(BouquetChangeListener listener) {
       	if ( listener == null ) {
       		throw new NullPointerException("BouquetChangeListener null");
       	}

	Vector listeners = getListeners(bouquetListeners);
	listeners.removeElement(listener);
  }

  /**
   * Notify all listeners that the bouquet has changed.
   */
  public void notifyBouquetListeners(BouquetChangeEvent event) {

	Vector listeners = getListeners(bouquetListeners);
	for (int i = 0; i < listeners.size(); i++) {
		BouquetChangeListener listener = (BouquetChangeListener)listeners.elementAt(i);
		if (listener == null)
			continue;
		notifyAsyncListener(event, listener);
	}
  }

  /**
   * Registers a <code>NetworkChangeListener</code> to be
   * notified of changes to a <code>Network</code> that is
   * part of this <code>NetworkCollection</code>. Subsequent
   * notification is made via <code>NetworkChangeEvent</code>
   * with this <code>NetworkCollection</code> as the event
   * source.<p>
   * 
   * This method is only a request for notification.  No guarantee is
   * provided that the SI database will detect all, or even any, SI
   * changes or whether such changes will be detected in a timely
   * fashion.<p>
   * 
   * If the specified <code>NetworkChangeListener</code> is
   * already registered, no action is performed.
   *
   * @param listener A <code>NetworkChangeListener</code> to be
   * notified about changes related to <code>Network</code>
   * carried on this <code>Transport</code>.
   *
   * @throws SecurityException If the caller does not have
   * <code>javax.tv.service.ReadPermission(locator)</code>.
   *
   * @see NetworkChangeEvent 
   * @see javax.tv.service.ReadPermission
   **/
  public void addNetworkChangeListener(NetworkChangeListener listener)
     throws SecurityException {
        if ( listener == null ) {
                throw new NullPointerException("NetworkChangeListener null");
        }

	Vector listeners = getListeners(networkListeners);

	listeners.removeElement(listener);
	listeners.addElement(listener);
  }

  /**
   * Called to unregister an
   * <code>NetworkChangeListener</code>.  If the specified
   * <code>NetworkChangeListener</code> is not registered, no
   * action is performed.
   *
   * @param listener A previously registered listener.  */
  public void removeNetworkChangeListener(NetworkChangeListener listener) {
       	if ( listener == null ) {
       		throw new NullPointerException("NetworkChangeListener null");
       	}

	Vector listeners = getListeners(networkListeners);
	listeners.removeElement(listener);
  }

  /**
   * Notify all listeners that the bouquet has changed.
   */
  public void notifyNetworkListeners(NetworkChangeEvent event) {

	Vector listeners = getListeners(networkListeners);
	for (int i = 0; i < listeners.size(); i++) {
		NetworkChangeListener listener = (NetworkChangeListener)listeners.elementAt(i);
		if (listener == null)
			continue;
		notifyAsyncListener(event, listener);
	}
  }

  /**
   * Registers a <code>TransportStreamChangeListener</code> to be
   * notified of changes to a <code>TransportStream</code> that is
   * part of this <code>TransportStreamCollection</code>. Subsequent
   * notification is made via <code>TransportStreamChangeEvent</code>
   * with this <code>TransportStreamCollection</code> as the event
   * source.<p>
   * 
   * This method is only a request for notification.  No guarantee is
   * provided that the SI database will detect all, or even any, SI
   * changes or whether such changes will be detected in a timely
   * fashion.<p>
   * 
   * If the specified <code>TransportStreamChangeListener</code> is
   * already registered, no action is performed.
   *
   * @param listener A <code>TransportStreamChangeListener</code> to be
   * notified about changes related to <code>TransportStream</code>
   * carried on this <code>Transport</code>.
   *
   * @throws SecurityException If the caller does not have
   * <code>javax.tv.service.ReadPermission(locator)</code>.
   *
   * @see TransportStreamChangeEvent 
   * @see javax.tv.service.ReadPermission
   **/
  public void addTransportStreamChangeListener(TransportStreamChangeListener listener)
     throws SecurityException {
        if ( listener == null ) {
                throw new NullPointerException("TransportStreamChangeListener null");
        }

	Vector listeners = getListeners(transportStreamListeners);
	listeners.removeElement(listener);
	listeners.addElement(listener);
  }

  /**
   * Called to unregister an
   * <code>TransportStreamChangeListener</code>.  If the specified
   * <code>TransportStreamChangeListener</code> is not registered, no
   * action is performed.
   *
   * @param listener A previously registered listener.  */
  public void removeTransportStreamChangeListener(TransportStreamChangeListener listener) {
       	if ( listener == null ) {
       		throw new NullPointerException("TransportStreamChangeListener null");
       	}

	Vector listeners = getListeners(transportStreamListeners);
	listeners.removeElement(listener);
  }

  /**
   * Notify all listeners that the transport stream has changed.
   */
  public void notifyTransportStreamListeners(TransportStreamChangeEvent event) {

	Vector listeners = getListeners(transportStreamListeners);
	for (int i = 0; i < listeners.size(); i++) {
		TransportStreamChangeListener listener = (TransportStreamChangeListener)listeners.elementAt(i);
		if (listener == null)
			continue;
		notifyAsyncListener(event, listener);
	}
  }

  /**
   *
   */
  private Vector getListeners(Hashtable listenerTable) {
	String key = Integer.toString(transportID);
	Vector listeners = (Vector)listenerTable.get(key);
	if (listeners == null) {
		listeners = new Vector();
		listenerTable.put(key, listeners);
	}
	return listeners;
  }

  private void notifyAsyncListener(
		SIChangeEvent event,
		SIChangeListener listener) {

	if (listener == null || event == null)
		return;

	NotifySIChangeThread thread = new NotifySIChangeThread(event, listener);
	if (thread != null) 
		thread.start();
  }
}

class NotifySIChangeThread extends Thread {
	SIChangeListener listener = null;
	SIChangeEvent event = null;

  public NotifySIChangeThread(
		SIChangeEvent event,
		SIChangeListener listener) {

	super("NotifySIChangeThread");

	this.listener = listener;
	this.event = event;
  }

  public void run() {
	if (this.listener == null || this.event == null)
		return;

	//if (listener instanceof BouquetChangeListener) {
	if (listener instanceof BouquetChangeListener 
            && event instanceof BouquetChangeEvent) {
		BouquetChangeListener theListener = (BouquetChangeListener)listener;

		theListener.notifyChange((BouquetChangeEvent)this.event);

	}
        //if (listener instanceof NetworkChangeListener) {
        if (listener instanceof NetworkChangeListener 
            && event instanceof NetworkChangeEvent) {
		NetworkChangeListener theListener = (NetworkChangeListener)listener;

		theListener.notifyChange((NetworkChangeEvent)this.event);

	} 
        //if (listener instanceof ServiceDetailsChangeListener) {
        if (listener instanceof ServiceDetailsChangeListener
            && event instanceof ServiceDetailsChangeEvent) {
		ServiceDetailsChangeListener theListener = (ServiceDetailsChangeListener)listener;

		theListener.notifyChange((ServiceDetailsChangeEvent)this.event);

        }
	//if (listener instanceof TransportStreamChangeListener) {
	if (listener instanceof TransportStreamChangeListener
            && event instanceof TransportStreamChangeEvent) {
		TransportStreamChangeListener theListener = (TransportStreamChangeListener)listener;

		theListener.notifyChange((TransportStreamChangeEvent)this.event);
	}
  }
}
