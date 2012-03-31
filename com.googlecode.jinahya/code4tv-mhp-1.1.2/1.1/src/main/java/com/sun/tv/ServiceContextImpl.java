/*
 * @(#)ServiceContext.java	1.27 00/01/14
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

package com.sun.tv;

import java.awt.*;
import java.util.*;
import javax.media.*;
import javax.media.protocol.*;
import javax.tv.xlet.*;
import javax.tv.locator.*;
import javax.tv.service.*;
import javax.tv.service.selection.*;
import javax.tv.service.navigation.*;

import com.sun.tv.si.*;
import com.sun.tv.receiver.*;

/**
 *
 * A <code>ServiceContext</code> represents an environment in which
 * services are presented in a broadcast receiver.  Applications may
 * use <code>ServiceContext</code> objects to select new services to
 * be presented.  Content associated with a selected service is
 * presented by one or more <code>ServiceContentHandler</code> objects
 * managed by the <code>ServiceContext</code>.<p>
 *
 * <code>ServiceContext</code>can exist in four states -
 * <em>presenting</em>, <em>not presenting</em>, <em>presentation
 * pending</em> and <em>destroyed</em>. The initial state is
 * <em>not presenting</em>. <p>
 *
 * The <code>select</code> method can be called from any state but
 * <em>destroyed</em>. Assuming no exception is thrown, the service
 * context then enters the <em>presentation pending</em> state. No
 * event is generated on this state transition. If a call to
 * <code>select</code> completes successfully, either a
 * <code>NormalContentEvent</code> or an
 * <code>AlternativeContentEvent</code> will be generated and the
 * service context moves into the <em>presenting</em> state. If the
 * service selection fails, a <code>SelectionFailedEvent</code> will
 * be generated. If the state before the <code>select</code> call was
 * <em>not presenting</em>, it will return to that state and a
 * <code>PresentationTerminatedEvent</code> generated. If the state
 * before the <code>select</code>call was <em>presenting</em>, it will
 * attempt to return to that previous state which can result in a
 * <code>NormalContentEvent</code> or
 * <code>AlternativeContentEvent</code> if this is possible or a
 * <code>PresentationTerminatedEvent</code> if it is not possible.<p>
 *
 * The <em>not presenting</em> state is entered due to service
 * presentation being stopped which is reported by the
 * <code>PresentationTerminatedEvent</code>. The stopping of service
 * presentation can be initiated by an application calling the
 * <code>stop</code> method or because some change in the environment
 * makes continued presentation impossible.<p>
 *
 * The <em>destroyed</em> state is entered by calling the
 * <code>destroy</code> method, and is signalled by the
 * <code>ServiceContextDestroyedEvent</code>. Once this state is
 * entered, the service context can no longer be used for any purpose.
 * A destroyed service context will be eligible for garbage collection
 * once all references to it by application(s) have been removed.<p>
 *
 * Note that the ability to select which service is presented does not
 * imply any ownership of the resources used for the presentation.<p>
 *
 * Applications may also use this interface to register for events
 * associated with service context state changes.<p>
 *
 * @see javax.tv.service.Service
 * @see ServiceContentHandler
 *
 * @see NormalContentEvent
 * @see AlternativeContentEvent
 * @see SelectionFailedEvent
 * @see PresentationTerminatedEvent
 * @see ServiceContextDestroyedEvent
 * @see ServiceContextListener 
 */

public class ServiceContextImpl implements ServiceContext, ControllerListener {

	private Locator serviceLocator = null;
	private Vector componentLocators = new Vector();
	private Hashtable serviceHandlers = new Hashtable();
	private Vector serviceListeners = new Vector();

	private static final int STATE_PRESENTING = 0;
	private static final int STATE_NOT_PRESENTING = 1;
	private static final int STATE_PRESENTATION_PENDING = 2;
	private static final int STATE_DESTROYED = 3;

	private int state = STATE_NOT_PRESENTING;

	private static final int PLAY_NORMAL = 0;
	private static final int PLAY_ALTERNATE = 1;
	private static final int PLAY_SELECTION_FAILED = 2;

	public ServiceContextImpl() throws Exception {
	}

private boolean waitTillRealized(Player player) {
	final int MAX_SLEEPS = 100;

	if (player.getState() == player.Unrealized)
		return false;

	if (player.getState() >= player.Realized)
		return true;

	if (player.getState() != player.Realizing)
		return false;

	for (int i = 0; i < MAX_SLEEPS; i++) {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			;
		}
		if (player.getState() >= player.Realized) {
			return true;
		}
	}
	return false;
}

/** 
 * This method is the execute for playing any locator provided to it,
 * If the locator is a service then play the locator, if a service
 * component then absed on the Stream Type perform the appropriate
 * PlayXXX method.
 */

private void Play(Locator locator, boolean autoRunOnly) 
		throws InvalidLocatorException, IllegalStateException {

	if (locator == null) 
		return;

	if (LocatorImpl.isServiceComponent(locator) == false)
		return;

	CacheManager siCache = CacheManager.getSICache();
	if (siCache == null)
		return;

	ServiceComponent sc = (ServiceComponent)siCache.get(locator);
	if (sc == null) {
		;

	} else if (sc.getStreamType() == StreamType.VIDEO) {
		PlayServiceComponent(getService(), locator);

	} else if (sc.getStreamType() == StreamType.AUDIO) {
		PlayServiceComponent(getService(), locator);

	} else if (sc.getStreamType() == StreamType.SUBTITLES) {
		PlayServiceComponent(getService(), locator);

	} else if (sc.getStreamType() == StreamType.DATA) {
		PlayServiceComponentData(locator, autoRunOnly);

	} else if (sc.getStreamType() == StreamType.SECTIONS) {
		PlayServiceComponent(getService(), locator);

	} else {
		PlayServiceComponent(getService(), locator);
	}
}

private void PlayService(Locator selection) 
		throws InvalidLocatorException, IllegalStateException {

	Handler player = (Handler)serviceHandlers.get(selection.toExternalForm());
	if (player == null) {
		player = new Handler(selection, this);
	}

	if (player == null || player.validHandler() == false) {
		throw new InvalidLocatorException(selection);
	}

	serviceHandlers.put(selection.toExternalForm(), player);
	player.addControllerListener(this); 
	player.start();

	if (waitTillRealized(player) == false) {
		state = STATE_PRESENTATION_PENDING;
	}
  }

private void PlayServiceComponent(Service service, Locator selection) 
		throws InvalidLocatorException, IllegalStateException {

        if (service == null) {
            service = SIManager.createInstance().getService(LocatorImpl.transformToService(selection));
        }

	Handler player = (Handler)serviceHandlers.get(service.getLocator().toExternalForm());
	if (player == null || player.validHandler() == false) {
		throw new InvalidLocatorException(selection);
	}

	componentLocators.removeElement(selection);
	componentLocators.addElement(selection);
  }

private void PlayServiceComponentData(Locator locator, boolean autoRunOnly) 
		throws InvalidLocatorException, IllegalStateException {

	String name = LocatorImpl.getServiceComponentName(locator);
	if (name == null)
		return;

	DisplayManager dispManager = DisplayManager.createInstance();
	if (dispManager == null) { // TBD: not the best error.
		throw new NullPointerException();
	}

	XletManager xletManager = XletManager.createInstance();
	if (xletManager == null) { // TBD: not the best error.
		throw new NullPointerException();
	}

	AppSignalEvent ase = new AppSignalEvent(dispManager.getRootFrame(),
		AppSignalEvent.START, null, null, name, this, null);

	Xlet xlet = xletManager.signalReceived(ase);
}

private void Stop(Locator locator) {
	if (locator == null) 
		return;

	if (LocatorImpl.isService(locator)) {
		StopService(locator);

		for (int i = 0; i < componentLocators.size(); i++) {
			Stop((Locator)componentLocators.elementAt(i));
		}

	} else if (LocatorImpl.isServiceComponent(locator)) {
		CacheManager siCache = CacheManager.getSICache();
		if (siCache == null)
			return;

		ServiceComponent sc = (ServiceComponent)siCache.get(locator);

		if (sc == null) {
			;

		} else if (sc.getStreamType() == StreamType.VIDEO) {
			StopServiceComponent(getService(), locator);

		} else if (sc.getStreamType() == StreamType.AUDIO) {
			StopServiceComponent(getService(), locator);

		} else if (sc.getStreamType() == StreamType.SUBTITLES) {
			StopServiceComponent(getService(), locator);

		} else if (sc.getStreamType() == StreamType.DATA) {
			StopServiceComponentData(locator);

		} else if (sc.getStreamType() == StreamType.SECTIONS) {
			StopServiceComponent(getService(), locator);

		} else {
			StopServiceComponent(getService(), locator);
		}
	}
}

private void StopService(Locator locator) {
	Handler oldPlayer = (Handler)serviceHandlers.get(locator.toExternalForm());
	if (oldPlayer == null) 
		return;

	DisplayManager dispManager = DisplayManager.createInstance();
	if (dispManager == null) { // TBD: not the best error.
		throw new NullPointerException();
	}

	//Frame frame = dispManager.getRootFrame();
	Container frame = dispManager.getRootFrame();
	if (frame == null) { // TBD: not the best error
		throw new NullPointerException();
	}

	if (waitTillRealized(oldPlayer) == true) {
		StopVisualComponent(frame, oldPlayer);

		oldPlayer.stop();
		oldPlayer.close();

		serviceHandlers.remove(locator.toExternalForm());
	}
}

private void StopServiceComponent(Service service, Locator locator) {
	try {
	        if (service == null) {
            		service = SIManager.createInstance().getService(LocatorImpl.transformToService(locator));
        	}

		Handler player = (Handler)serviceHandlers.get(service.getLocator().toExternalForm());
		if (player == null || player.validHandler() == false) {
			throw new InvalidLocatorException(locator);
		}

	} catch (Exception e) {
		;
	}
	componentLocators.removeElement(locator);
}

private void StopServiceComponentData(Locator locator) {
	String name = LocatorImpl.getServiceComponentName(locator);
	if (name == null)
		return;

	DisplayManager dispManager = DisplayManager.createInstance();
	if (dispManager == null) { // TBD: not the best error.
		throw new NullPointerException();
	}

	XletManager xletManager = XletManager.createInstance();
	if (xletManager == null) { // TBD: not the best error.
		throw new NullPointerException();
	}

	AppSignalEvent ase = new AppSignalEvent(dispManager.getRootFrame(),
		AppSignalEvent.DESTROY, null, null, name, this, null);

	xletManager.signalReceived(ase);
}

private void StopVisualComponent(Container frame, Player player) {
	if (player.getState() < player.Realized)
		return;

	try {
		Component comp = player.getVisualComponent();
		if (comp != null && comp.getParent() == frame) {
			frame.remove(comp);
		}
	} catch (Exception e) {
		;
	}
}

private boolean Contains(Locator component, Locator components[]) {
	if (components == null)
		return false;

	for (int i = 0; i < components.length; i++) {
		if (LocatorImpl.equals(component, components[i])) {
			return true;
		}
	}
	return false;
}

private void PlayComponents(Service service) throws InvalidLocatorException {
	boolean autoRunOnly = true;

	CacheManager siCache = CacheManager.getSICache();
	if (siCache == null)
		return;

	Enumeration elements = siCache.elements();
	while (elements.hasMoreElements()) {
		SIElement element = (SIElement)elements.nextElement();
		if (!(element instanceof ServiceComponentImpl))
			continue;

		Locator serviceLocator = service.getLocator();
		Locator componentLocator = element.getLocator();

		String serviceName1 = LocatorImpl.getServiceName(serviceLocator);
		String serviceName2 = LocatorImpl.getServiceName(componentLocator);
		if (serviceName1 == null || serviceName2 == null)
			continue;

		if (serviceName1.equals(serviceName2) == false)
			continue;

		Play(componentLocator, autoRunOnly);
	}
}

  /**
   * Selects a service to be presented in this
   * <code>ServiceContext</code>.  If the <code>ServiceContext</code>
   * is already presenting content, the new selection replaces the
   * content being presented. If the <code>ServiceContext</code> is
   * not presenting, successful conclusion of this method results in
   * the <code>ServiceContext</code> entering the <em>presenting</em>
   * state. <p>
   *
   * This method is asynchronous and successful completion of the
   * selection is notified by either a <code>NormalContentEvent</code>
   * or a <code>AlternativeContentEvent</code>.  If an exception is
   * thrown when this method is called, the state of the service
   * context does not change. In such a case, any service being
   * presented before this method was called will continue to be
   * presented.<p>
   *
   * If the selection process fails after this method returns, a
   * <code>SelectionFailedEvent</code> will be generated. In this
   * case, the system will attempt to return to presenting the
   * original service (if any). If this is not possible (due, for
   * example, to issues such as tuning or CA) the
   * <code>ServiceContext</code> will enter the <em>not
   * presenting</em> state and a
   * <code>PresentationTerminatedEvent</code> will be generated.<p>
   *
   * If the <code>ServiceContext</code> is currently presenting a
   * service and components of the current service are also to be
   * presented in the newly selected service, these components will
   * continue to be presented and will not be restarted.  If the
   * calling application is not a part of the newly selected service
   * and the application lifecycle policy on the receiver dictates
   * that the calling application be terminated, termination of the
   * application will be affected through the application lifecycle
   * API.<p>
   *
   * If the provided <code>Service</code> is transport-independent,
   * this method will resolve the <code>Service</code> to a
   * transport-dependent <code>Service</code> before performing the
   * selection. The actual <code>Service</code> selected will be
   * reported through the <code>getService()</code> method.<p>
   *
   * Successful completion of a select operation using this method
   * provides <code>ServiceContentHandler</code> instances for all
   * components that are signaled as "auto-start" in the selected
   * service.  Upon entering the <em>presenting</em> state, these
   * <code>ServiceContentHandler</code> instances will have begun
   * presenting their respective content;
   * <code>ServiceMediaHandler</code> instances will be in the
   * <em>started</em> state.
   * 
   * @param selection The <code>Service</code> the service to be
   * selected.
   *
   * @throws SecurityException If the caller owns this
   * <code>ServiceContext</code> but does not have
   * <code>SelectPermission(selection.getLocator(), "own")</code>, or if 
   * the caller
   * does not own this <code>ServiceContext</code> and does not have
   * <code>SelectPermission(selection.getLocator(), "*")</code>.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   *
   * @see NormalContentEvent
   * @see AlternativeContentEvent
   * @see SelectionFailedEvent
   * @see PresentationTerminatedEvent
   * @see javax.tv.service.Service
   * @see ServiceContext#getService
   * @see ServiceContentHandler
   * @see ServiceContext#destroy 
   **/
  
  public void select(Service selection) throws SecurityException {
	boolean autoRunOnly = false;

        if ( selection == null ) {
                throw new NullPointerException("service selection is null");
        }

	Locator locator = selection.getLocator();
        if ( locator == null ) {
                throw new NullPointerException("service locator is null");
        }

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		if (isContextOwned()) {
			sm.checkPermission(new SelectPermission(locator, "own"));
		} else {
			sm.checkPermission(new SelectPermission(locator, "*"));
		}
	}

	if (state == STATE_DESTROYED) {
		throw new IllegalStateException("ServiceContext has been destroyed.");
	}

        if ( LocatorImpl.isTIService(locator)) {
        	Locator[] locs = LocatorImpl.transformLocator(locator);
           	if (locs != null && locs.length > 0) {
              		locator = locs[0];
           	}
        }

	SIManager siManager = (SIManager)SIManager.createInstance();
	if (siManager == null) { // TBD: not the best error.
		throw new NullPointerException();
	}

	ServiceImpl service = (ServiceImpl)selection;
	int reason = service.getSelectionFailedReason();

	try {
		Stop(this.serviceLocator);

		if (reason != 0) {
			Locator alternate = LocatorImpl.transformToAlternate(locator);
			this.serviceLocator = alternate;
		} else {
			this.serviceLocator = locator;
		}

		PlayService(this.serviceLocator);

	} catch (Exception e) {
		notifyListeners(new SelectionFailedEvent(this, reason));
		this.serviceLocator = null;
	}

	try {
		PlayComponents(service);

	} catch (Exception e) {
		;
	}
  }

  /**
   * Selects content by specifying the parts of a service to be
   * presented.  If the <code>ServiceContext</code> is
   * already presenting content, the new selection replaces the
   * content being presented. If the
   * <code>ServiceContext</code> is not presenting,
   * successful conclusion of this method results in the
   * <code>ServiceContext</code> entering the
   * <em>presenting</em> state. <p>
   *
   * This method is asynchronous and successful completion of the
   * selection is notified by either a <code>NormalContentEvent</code>
   * or a <code>AlternativeContentEvent</code>.  If failure of the
   * selection can be determined when this method is called, an
   * exception will be generated and the state of the
   * <code>ServiceContext</code> will not change. In this case, any
   * service being presented before this method was called will
   * continue to be presented.<p>
   *
   * If failure of the selection is determined after this method
   * returns, a <code>SelectionFailedEvent</code> will be
   * generated. In this case, the implementation of the method will
   * try to return to presenting the original service (if any). If
   * this is not possible (due, for example, to issues such as tuning
   * or CA) the <code>ServiceContext</code> will enter the
   * <em>not presenting</em> state and a
   * <code>PresentationTerminatedEvent</code> will be generated.<p>
   *
   * If the <code>ServiceContext</code> is currently presenting a service and
   * components of the current service are also to be presented in the
   * newly selected content, these components will continue to be
   * presented and will not be restarted.  If the calling application
   * is not a part of the newly selected content and the application
   * lifecycle policy on the receiver dictates that the calling
   * application be terminated, termination of the application
   * will be affected through the application lifecycle API.<p>
   *
   * Successful completion of a select operation using this method
   * provides <code>ServiceContentHandler</code> instances for all
   * components that are indicated in the <code>components</code>
   * parameter.  Upon entering the <em>presenting</em> state, these
   * <code>ServiceContentHandler</code> instances will have begun
   * presenting their respective content;
   * <code>ServiceMediaHandler</code> instances will be in the
   * <em>started</em> state.  This method will not provide
   * <code>ServiceContentHandler</code> instances for service
   * components for which a locator is not specified.
   *
   * @param components An array of <code>Locator</code> instances
   * representing the parts of this service to be selected.  Each
   * array element must be a locator to either a
   * <code>ServiceComponent</code> or content within a service
   * component (such as an Xlet).
   *
   * @throws InvalidLocatorException If a <code>Locator</code> provided
   * does not reference a selectable service component or selectable
   * content within a service component.
   *
   * @throws InvalidServiceComponentException If a
   * specified service component must be presented in conjunction
   * with another service component not contained in
   * <code>components</code>, if the specified
   * components are not all members of the same service, or if the
   * specified set of components cannot be presented as a coherent
   * whole.
   *
   * @throws SecurityException If, for any valid <code>i</code>, the
   * caller owns this <code>ServiceContext</code> but does not have
   * <code>SelectPermission(components[i], "own")</code>, or if the caller
   * does not own this <code>ServiceContext</code> and does not have
   * <code>SelectPermission(components[i], "*")</code>.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   *
   * @see NormalContentEvent
   * @see AlternativeContentEvent
   * @see SelectionFailedEvent
   * @see PresentationTerminatedEvent
   * @see ServiceContentHandler
   * @see javax.tv.service.navigation.ServiceComponent
   */
  public void select(Locator[] components) throws
		InvalidLocatorException,
		InvalidServiceComponentException,
		SecurityException,
		IllegalStateException {

	if (components == null) {
		throw new NullPointerException();
	}

	if (components.length == 0) {
		throw new InvalidLocatorException(null);
	}

	String serviceName = null;
	for (int i = 0; i < components.length; i++) {
		if (LocatorImpl.isServiceComponent(components[i]) == false) {
			throw new InvalidLocatorException(components[i]);
		}

		if (serviceName == null) {
			serviceName = LocatorImpl.getServiceName(components[i]); 
		}

		if (serviceName == null) {
			throw new InvalidServiceComponentException(components[i]);
		}

		if (LocatorImpl.getServiceName(components[i]).equals(serviceName) == false) {
			throw new InvalidServiceComponentException(components[i]);
		}	
	}

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		for (int i = 0; i < components.length; i++ ) {
			if (isContextOwned()) {
				sm.checkPermission(new SelectPermission(components[i], "own"));
			} else {
				sm.checkPermission(new SelectPermission(components[i], "*"));
			}
		}
	}

	if (state == STATE_DESTROYED) {
		throw new IllegalStateException("ServiceContext has been destroyed.");
	}

	for (int i = 0; i < componentLocators.size(); i++) {
		Locator componentLocator = (Locator)componentLocators.elementAt(i);
		if (Contains(componentLocator, components) == false) {
			Stop(componentLocator);
		}
	}

	int reason = 0;
	try {
		String oldServiceName = LocatorImpl.getServiceName(this.serviceLocator);
		if (this.serviceLocator != null && serviceName.equals(oldServiceName) == false) {
			Stop(this.serviceLocator);
			this.serviceLocator = null;
		} 

		if (serviceLocator == null) {
			String locatorStr = LocatorImpl.ServiceProtocol + serviceName;
			Locator locator = LocatorFactory.getInstance().createLocator(locatorStr);

            		ServiceImpl service = (ServiceImpl)SIManager.createInstance().getService(locator);
			if (service == null)
				throw new NullPointerException("service == null");

			reason = service.getSelectionFailedReason();
			if (reason != 0) {
				Locator alternate = LocatorImpl.transformToAlternate(locator);
				this.serviceLocator = alternate;
			} else {
				this.serviceLocator = locator;
			}

			PlayService(this.serviceLocator);
		}

	} catch (Exception e) {
		notifyListeners(new SelectionFailedEvent(this, reason));
		this.serviceLocator = null;
	}

	for (int i = 0; i < components.length; i++) {
		if (componentLocators.indexOf(components[i]) == -1) {
			Play(components[i], false);
		}
	}

	notifyListeners(new NormalContentEvent(this));
  }

  /**
   * Causes the <code>ServiceContext</code> to stop presenting content
   * and enter the <em>not presenting</em> state.  Resources used
   * in the presentation will be released, associated
   * <code>ServiceContentHandlers</code> will cease presentation
   * (<code>ServiceMediaHandlers</code> will no longer be in the
   * <em>started</em> state), and a
   * <code>PresentationTerminatedEvent</code> will be posted.<p>
   *
   * This operation completes asynchronously. No action is performed
   * if the <code>ServiceContext</code> is already in the <em>not
   * presenting</em> state.
   *
   * @throws SecurityException If the caller owns this
   * <code>ServiceContext</code> but does not have
   * <code>ServiceContextPermission("stop", "own")</code>, or if the caller
   * does not own this <code>ServiceContext</code> and does not have
   * <code>SelectPermission("stop", "*")</code>.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   */
  public void stop() throws SecurityException, IllegalStateException {
	int reason = PresentationTerminatedEvent.USER_STOP;

	stop0(reason);
  }

  public void stop0(int reason) throws SecurityException, IllegalStateException {
	if (state == STATE_NOT_PRESENTING) {
		return;
	}

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		if (isContextOwned()) {
			sm.checkPermission(new ServiceContextPermission("stop", "own"));
		} else {
			sm.checkPermission(new ServiceContextPermission("stop", "*"));
		}
	}

	if (state == STATE_DESTROYED) {
		throw new IllegalStateException("ServiceContext has been destroyed.");
	}

	Enumeration list = serviceHandlers.elements();
	while (list.hasMoreElements()) {
		Player player = (Player)list.nextElement();
		if (player == null) {
			continue;
		}
		player.stop();
		player.close();
		serviceHandlers.remove(serviceLocator.toExternalForm());
		serviceLocator = null;
		
	}

	for (int i = 0; i < componentLocators.size(); i++) {
		Stop((Locator)componentLocators.elementAt(i));
	}

	state = STATE_NOT_PRESENTING;
        this.serviceLocator = null;

	notifyListeners(new PresentationTerminatedEvent(this, reason));
  }

  /** 
   * Causes the <code>ServiceContext</code> to release all resources
   * and enter the <em>destroyed</em> state. This method indicates
   * that the the <code>ServiceContext</code> must cease further
   * activity, and that it will no longer be used.  After completion
   * of this method, <code>ServiceMediaHandler</code> instances
   * associated with this <code>ServiceContext</code> will have become
   * <em>unrealized</em> or will have been closed.
   *
   * If the <code>ServiceContext</code> is currently in the
   * <em>presenting</em> or <em>presentation pending</em> state, this
   * method will first stop the <code>ServiceContext</code>, causing a
   * <code>PresentationTerminatedEvent</code> to be posted.  After the
   * <code>ServiceContext</code> has moved to the <em>destroyed</em>
   * state, a <code>ServiceContextDestroyedEvent</code> will be
   * posted.<p>
   *
   * This operation completes asynchronously.  No action is performed
   * if the <code>ServiceContext</code> is already in the
   * <em>destroyed</em> state.
   *
   * @throws SecurityException If the caller does not have permission
   * to call <code>stop()</code> on this <code>ServiceContext</code>,
   * or if the caller owns this <code>ServiceContext</code> but does
   * not have <code>ServiceContextPermission("destroy", "own")</code>,
   * or if the caller does not own this <code>ServiceContext</code>
   * and does not have <code>SelectPermission("destroy", "*")</code>.
   *
   * @see #stop
   */

  public void destroy() throws SecurityException {
	if (state == STATE_DESTROYED) {
		return;
	}

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		sm.checkPermission(new ServiceContextPermission("stop", "*"));

		if (isContextOwned()) {
			sm.checkPermission(new ServiceContextPermission("destroy", "own"));
		} else {
			sm.checkPermission(new ServiceContextPermission("destroy", "*"));
		}
	}

	if (state == STATE_PRESENTING || state == STATE_PRESENTATION_PENDING) {
		this.stop();
	}

	state = STATE_DESTROYED;
	notifyListeners(new ServiceContextDestroyedEvent(this));

	serviceHandlers = new Hashtable();
	serviceListeners = new Vector();
	componentLocators = new Vector();
	serviceLocator = null;
  }

  /**
   * Reports the current collection of ServiceContentHandlers.  A
   * zero-length array is returned if the <code>ServiceContext</code>
   * is in in the <em>not presenting</em> or <em>presentation
   * pending</em> states.
   *
   * @throws SecurityException If the caller owns this
   * <code>ServiceContext</code> but does not have
   * <code>ServiceContextPermission("getServiceContentHandlers",
   * "own")</code>, or if the caller does not own this
   * <code>ServiceContext</code> and does not have
   * <code>SelectPermission("getServiceContentHandlers", "*")</code>.
   *
   * @return The current <code>ServiceContentHandler</code> instances.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   */
  public ServiceContentHandler[] getServiceContentHandlers() throws SecurityException {

	if (state == STATE_DESTROYED)
		return null;

	SecurityManager sm = System.getSecurityManager();
	if (sm != null) {
		if (isContextOwned()) {
			sm.checkPermission(new ServiceContextPermission("getServiceContentHandlers", "own"));
		} else {
			sm.checkPermission(new ServiceContextPermission("getServiceContentHandlers", "*"));
		}
	}

	if (state != STATE_PRESENTING) 
		return new ServiceContentHandler[0];

	int count = serviceHandlers.size();

	ServiceContentHandler handlers[] = new ServiceContentHandler[count];
	if (handlers == null) 
		return null;

	int index = 0;
	Enumeration list = serviceHandlers.elements();
	while (list.hasMoreElements()) {
		handlers[index] = (ServiceContentHandler)list.nextElement();
		index++;
	}
	return handlers;
  }


  /**
   *
   * Provides a <code>Locator</code> to the service being presented in
   * this service context. If the service context is currently
   * presenting a service, the locator returned will be a
   * network-dependent locator to the service indicated in the last
   * successful <code>select</code> method call. If the service
   * context is not currently presenting a service or has been
   * destroyed, then <code>null</code> is returned.
   *
   * @return A locator for the service currently being presented or
   * <code>null</code> if no service is being presented.
   *
   * @see javax.tv.service.Service
   */
  public Locator getServiceLocator() {
	if (state == STATE_NOT_PRESENTING || state == STATE_DESTROYED) {
		return null;
	}

	return this.serviceLocator;
  }


  /**
   * Reports the <code>Service</code> being presented in this
   * <code>ServiceContext</code>. If the <code>ServiceContext</code>
   * is currently presenting a service, the <code>Service</code>
   * returned will be a network-dependent representation of the
   * <code>Service</code> indicated in the last successful
   * <code>select()</code> method call. If the
   * <code>ServiceContext</code> is not in the <em>presenting</em>
   * state then <code>null</code> is returned.
   *
   * @return The service currently being presented.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   */
  public Service getService() {
	SIManager siManager = (SIManager)SIManager.createInstance();
	if (siManager == null) {
		throw new NullPointerException();
	}

        if (state == STATE_DESTROYED) {
           throw new IllegalStateException("ServiceContext has been destroyed");
        }

	try {
		return siManager.getService(this.serviceLocator);
	} catch (Exception e) {
		;
	}
	return null;
  }


  /**
   *
   * Subscribes a listener to receive events related to this
   * ServiceContext.  If the specified listener is currently
   * subscribed then no action is performed.
   *
   * @param listener The ServiceContextListener to subscribe.
   *
   * @throws NullPointerException If <code>listener</code> is
   * <code>null</code>.
   *
   * @throws IllegalStateException If the ServiceContext has been
   * destroyed.
   *
   * @see ServiceContextEvent
   */
  public void addListener(ServiceContextListener listener) throws IllegalStateException {

	if (listener == null) {
		throw new NullPointerException();
	}

	if (state == STATE_DESTROYED) {
		throw new IllegalStateException("ServiceContext has been destroyed.");
	}

	if (serviceListeners.contains(listener) == false) {
		serviceListeners.addElement(listener);
	}
  }

  /** 
   * Unsubscribes a listener from receiving events related to this
   * ServiceContext.  If the specified listener is not currently
   * subscribed then no action is performed.
   *
   * @param listener The ServiceContextListener to unsubscribe.
   *
   * @throws NullPointerException if <code>listener</code> is
   * <code>null</code>.
   *
   * @throws IllegalStateException If the ServiceContext has been
   * destroyed.
   */
  public void removeListener(ServiceContextListener listener) throws IllegalStateException {
	if (listener == null) {
		throw new NullPointerException();
	}

	if (state == STATE_DESTROYED) {
		throw new IllegalStateException("ServiceContext has been destroyed.");
	}

	if (serviceListeners.contains(listener) == true) {
		serviceListeners.removeElement(listener);
	}
  }

  /**
   * This method notifies all listeners of this service context, of provied
   * service context event.
   */
  private void notifyListeners(ServiceContextEvent event) {
        Vector listeners = (Vector)serviceListeners.clone();

        //for (int i = 0; i < serviceListeners.size(); i++) {
        for (int i = 0; i < listeners.size(); i++) {
		ServiceContextListener listener = 
			(ServiceContextListener)listeners.elementAt(i);
		if (listener == null)
			continue;

		notifyAsyncListener(event, listener);
	}
  }

  private void notifyAsyncListener(
		ServiceContextEvent event,
		ServiceContextListener listener) {

	if (listener == null || event == null)
		return;

	NotifyServiceContextThread thread = new NotifyServiceContextThread(event, listener);
	if (thread != null) 
		thread.start();
  }

  public boolean isContextOwned() {
	try {
		ServiceContextFactory  factory = ServiceContextFactory.getInstance();
		if (factory == null)
			return false;

		ServiceContext list[] = factory.getServiceContexts();
		if (list == null)
			return false;

		for (int i = 0; i < list.length; i++) {
			if (list[i] == this) {
				return true;
			}
		}
	} catch (Exception e) {
		;
	}
	return false;
  }

  /**
   * Reports the current collection of Service Component Locators.  A
   * zero-length array is returned if the <code>ServiceContext</code>
   * is in in the <em>not presenting</em> or <em>presentation
   * pending</em> states.
   *
   * @return The current Service Component <code>Locator</code> instances.
   *
   * @throws IllegalStateException If the <code>ServiceContext</code>
   * has been destroyed.
   */
  public Locator[] getServiceComponentLocators() {

	if (state == STATE_DESTROYED)
		return null;

	if (componentLocators == null) 
		return new Locator[0];

	Locator locators[] = new Locator[componentLocators.size()];
	for (int i = 0; i < componentLocators.size(); i++) {
		locators[i] = (Locator)componentLocators.elementAt(i);
	}
	return locators;
  }


  /**
   * The isDestroyed method is not part of the JavaTV API, it is used to
   * indicate if this context is still valid within the RI. The ServiceContextFactory
   * needs to return a list of all non-destroyed ServiceContexts.
   */
  public boolean isDestroyed() {
	return (state == STATE_DESTROYED);
  }

  /**
   * Handle Controller/Player events for the player that's currently active.
   */
  public synchronized void controllerUpdate(ControllerEvent ce) {
	if (ce == null)
		return;

	Controller controller = ce.getSourceController();
	if (controller == null || !(controller instanceof Player)) 
		return;

	Player player = (Player)controller;
	if (ce instanceof RealizeCompleteEvent) {
		DisplayManager dispManager = DisplayManager.createInstance();
		if (dispManager == null)
			return;

		Container frame = dispManager.getRootFrame();
		if (frame == null)
			return;

		Component comp = player.getVisualComponent();
		if (comp != null && comp.getParent() == null) {
			frame.add(comp);
		}

		comp = player.getControlPanelComponent();
		frame.validate();

		if (LocatorImpl.isAlternate(this.serviceLocator)) {
			notifyListeners(new AlternativeContentEvent(this));

		} else {
			notifyListeners(new NormalContentEvent(this));
		}
		state = STATE_PRESENTING;

	} else if (ce instanceof PrefetchCompleteEvent) {
		;

 	// EndOfMediaEvent occurs when the media file has played till the end.
 	// The player is now in the stopped state.
 	} else if (ce instanceof EndOfMediaEvent) {
		player.setMediaTime(new Time(0)); //
		player.start(); //

	// If at any point the Player encountered an error - possibly in the data stream
	// and it could not recover from the error, it generates a ControllerErrorEvent
	} else if (ce instanceof ControllerErrorEvent) {
	    System.err.println("ControllerErrorEvent: " + ce);

 	// Occurs when a player is closed.
 	} else if (ce instanceof ControllerClosedEvent) {
		;

	// DurationUpdateEvent occurs when the player's duration changes or is
	// updated for the first time
	} else if (ce instanceof DurationUpdateEvent) {
		Time t = ((DurationUpdateEvent)ce).getDuration();

	// Caching control.
	} else if (ce instanceof CachingControlEvent) {
		;
	} else if (ce instanceof StartEvent) {
		;
	} else if (ce instanceof MediaTimeSetEvent) {
		;
	} else if (ce instanceof TransitionEvent) {
		;
	} else if (ce instanceof RateChangeEvent) {
		;
	} else if (ce instanceof StopTimeChangeEvent) {
		;
	} else if (ce instanceof DurationUpdateEvent) {
		System.out.println("Duration: " +
			((DurationUpdateEvent)ce).getDuration().getSeconds());

	} else {
	    // Catch implementation specific events here...

	    // UnsupportedFormatEvent is not a part of the JMF 1.0 spec.
	    // It is generates when the media contains something that is not
	    // supported by the underlying framework.  For example, a QuickTime
	    // movie with a VR track, an unsupported codec, etc.

	    // SizeChangeEvent is not a part of the JMF 1.0 spec. It is
	    // generated when the size of the video changes or right at the
	    // beginning of a video clip, to inform listeners about the
	    // dimensions of the video

	}
  }
}

class NotifyServiceContextThread extends Thread {
	ServiceContextListener listener = null;
	ServiceContextEvent event = null;

  public NotifyServiceContextThread(
		ServiceContextEvent event,
		ServiceContextListener listener) {

	super("NotifyServiceContextThread");

	this.listener = listener;
	this.event = event;
  }

  public void run() {
	if (this.listener == null || this.event == null)
		return;

	listener.receiveServiceContextEvent(event);
  }
}
