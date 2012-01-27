/*
 * @(#)MediaSelectControlImpl.java	1.21 00/01/14
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

package com.sun.tv.media;

import java.awt.*;
import java.util.*;
import javax.media.*;
import javax.tv.*;
import javax.tv.media.*;
import javax.tv.locator.*;
import javax.tv.service.selection.*;

import com.sun.tv.*;

/**
 * Provides an interface to allow the selection of different kinds of
 * content from a running <code>Player</code>.  It serves as a high
 * level demultiplex control, where the selection is specified by
 * locators indicating one or more service components to present. <p>
 *
 * If the <code>Player</code> on which a
 * <code>MediaSelectControlImpl</code> operates is an instance of
 * <code>ServiceMediaHandler</code>, then
 * <code>MediaSelectControlImpl</code> is restricted to demultiplexing
 * only content from the <code>Service</code> with which the
 * <code>ServiceMediaHandler</code> is associated.
 *
 * @see ServiceMediaHandler
 */
public class MediaSelectControlImpl implements
		MediaSelectControl, ServiceContextListener  {
	
	private Vector listeners = null;
	private ServiceContextImpl context = null;
	private Handler handler = null;
	
       /**
	* Constructs a new media select control
	* @param player The player associated with the media select control.
	*/
	public MediaSelectControlImpl(Handler handler, ServiceContextImpl context)
	{
		this.context = context;
		this.handler = handler;

		try {
			context.addListener(this);
		} catch (Exception e) {
		 	;
		}
	}

    /**
     * Selects a new service component for presentation.  If some
     * content is currently playing, it is replaced in its entirety by
     * the specified selection.  This is an asynchronous operation
     * that is completed on receipt of a MediaSelectEvent.  Note that
     * for certain selections that imply a different time base or
     * otherwise change synchronization relationships a
     * RestartingEvent will be posted by the <code>Player</code>.
     * 
     * @param component A locator representing an individual service
     * component to present.
     *
     * @throws InvalidLocatorException If the locator does not reference
     * a selectable service component, or references a service component
     * that is not part of the <code>Service</code> to which the
     * <code>MediaSelectControlImpl</code> is restricted.
     *
     * @throws InsufficientResourcesException If the operation cannot be
     * completed due to a lack of system resources.
     *
     * @throws SecurityException If the caller does not have
     *		MediaSelectPermission(component) permission.
     */
  public void select(Locator component) throws InvalidLocatorException, 
                	InvalidServiceComponentException, 
			InsufficientResourcesException,
    			SecurityException {

	if (component == null) {
		throw new NullPointerException("Locator is null");
	}

	if (context == null) {
		throw new NullPointerException("context is null");
	}

	if (LocatorImpl.isServiceComponent(component) == false) {
		throw new InvalidLocatorException(component);
	}	

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
                sm.checkPermission(new MediaSelectPermission(component));
        }

	Locator components[] = new Locator[1];
	components[0] = component;

	context.select(components);	
  }

    /**
     *
     * Selects one or more service components for presentation.  If
     * some content is currently playing, it is replaced in its
     * entirety by the specified selection.  This is an asynchronous
     * operation that is completed on receipt of a MediaSelectEvent.
     * Note that for certain selections that imply a different time
     * base or otherwise change synchronization relationships a
     * RestartingEvent will be posted by the Player.
     *
     * @param components An array of locators representing a set of
     * individual service components to present together.
     *
     * @throws InvalidLocatorException If a locator provided does not
     * reference a selectable service component or references a
     * service component that is not part of the <code>Service</code>
     * to which the <code>MediaSelectControlImpl</code> is restricted .
     *
     * @throws InvalidServiceComponentException If the specified set
     * of service components cannot be presented as a coherent whole or
     * if the service components are not all available simultaneously.
     *
     * @throws InsufficientResourcesException If the operation cannot be
     * completed due to a lack of system resources.
     *
     * @throws SecurityException If the caller does not have
     *		MediaSelectPermission(components[i]) permission 
     *          for any valid i.
     */
  public void select(Locator[] components) throws InvalidLocatorException,
			InvalidServiceComponentException,
      			InsufficientResourcesException,
      			SecurityException {

	if (components == null) {
		throw new NullPointerException("Locator[] is null");
	}

	if (context == null) {
		throw new NullPointerException("context is null");
	}

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
		for (int i = 0; i < components.length; i++) {
                	sm.checkPermission(new MediaSelectPermission(components[i]));
		}
        }
	
	context.select(components);	
  }

    /**
     * 
     * Adds a service component (for example, sub-titles) to the
     * presentation.  Components whose addition would require Player
     * resynchronization are not permitted.
     * 
     * @param component The locator representing an individual service
     * component to add to the presentation.
     *
     * @throws InvalidLocatorException If the specified locator does
     * not reference a selectable service component, references a
     * service component whose addition would require
     * resynchronization of the Player, or references a service
     * component that is not part of the <code>Service</code> to which
     * the <code>MediaSelectControlImpl</code> is restricted.
     *
     * @throws InsufficientResourcesException If the operation cannot be
     * completed due to a lack of system resources.
     *
     * @throws SecurityException If the caller does not have
     *		MediaSelectPermission(component) permission.
     */
    public  synchronized void add(Locator component) 
               throws InvalidLocatorException, 
               InvalidServiceComponentException, 
               InsufficientResourcesException, 
               SecurityException {

	if (component == null) {
		throw new NullPointerException("Locator is null");
	}

	if (context == null) {
		throw new NullPointerException("context is null");
	}

	if (handler == null) {
		throw new NullPointerException("handler is null");
	}

	if (LocatorImpl.isServiceComponent(component) == false) {
		throw new InvalidLocatorException(component);
	}

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
                sm.checkPermission(new MediaSelectPermission(component));
        }
	
	Locator current[] = handler.getServiceContentLocators();
	Locator components[] = null;

	if (current == null) {
		components = new Locator[1];
		components[0] = component;
	} else { 
		components = new Locator[current.length+1];
		for (int i = 0; i < current.length; i++) {
			components[i] = current[i];
		}
		components[current.length] = component;
	}

// TBD maybe we should check and see if the locator is already there?

	context.select(components);	
    }

    /**
     *
     * Removes a service component from the presentation.  Components
     * whose removal would require Player resynchronization are not
     * permitted.
     * 
     * @param component The locator representing an individual service
     * component to remove from the presentation.
     *
     * @throws InvalidLocatorException If the specified locator does
     * not reference a service component in the current selection,
     * references a service component whose addition would require
     * resynchronization of the Player, or references a service
     * component that is not part of the <code>Service</code> to which
     * the <code>MediaSelectControlImpl</code> is restricted.
     *
     * @throws SecurityException If the caller does not have
     *		MediaSelectPermission(component) permission.
     */
    public void remove(Locator component) 
                throws InvalidLocatorException,  
                       InvalidServiceComponentException, 
                       SecurityException {

	if (component == null) {
		throw new NullPointerException("Locator is null");
	}	

	if (context == null) {
		throw new NullPointerException("context is null");
	}

	if (handler == null) {
		throw new NullPointerException("handler is null");
	}

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
                sm.checkPermission(new MediaSelectPermission(component));
        }
		
	Locator current[] = handler.getServiceContentLocators();

	if (current == null || current.length == 0) {
		throw new InvalidLocatorException(component, "Locator not found");
	}

	boolean found = false;
	for (int i = 0; i < current.length; i++) {
		if (component.equals(current[i]) == true) {
			found = true;
			break;
		}
	}

	if (found == false) {
		throw new InvalidLocatorException(component, "Locator not found");
	}

	Locator components[] = new Locator[current.length-1];
	int counter = 0;
	for (int i = 0; i < current.length; i++) {
		if (component.equals(current[i]) == false) {
			components[counter] = current[i];
			counter++;
		}
	}

        try {
	   select(components);	
        } catch (InsufficientResourcesException e) {
           throw new InvalidServiceComponentException(component);
        }
    }

    /*
     *
     * Replaces a service component in the presentation.  Components
     * whose replacement would require Player resynchronization are
     * not permitted.
     * 
     * @param fromComponent The locator that represents the service
     * component to remove from the presentation.
     *
     * @param toComponent The locator that represents the service
     * component to add to the presentation.
     *
     * @throws InvalidLocatorException If <code>fromComponent</code>
     * does not reference a service component in the current
     * selection, if <code>toComponent</code> does not reference a
     * selectable service component, if <code>toComponent</code>
     * references a service component that is not part of the
     * <code>Service</code> to which the
     * <code>MediaSelectControlImpl</code> is restricted, or if the
     * locators reference service components for which the replacement
     * operation would require resynchronization of the Player.
     *
     * @throws InsufficientResourcesException If the operation cannot be
     * completed due to a lack of system resources.
     *
     * @throws SecurityException If the caller does not have
     *		MediaSelectPermission(fromComponent) and
     *		MediaSelectPermission(toComponent) permission.
     */
    public void replace(Locator fromComponent, Locator toComponent)
      		throws 	InvalidLocatorException, 
             		InvalidServiceComponentException, 
             		InsufficientResourcesException, 
             		SecurityException {

	if (fromComponent == null || toComponent == null) {
		throw new NullPointerException("Locator is null");
	}

	if (context == null) {
		throw new NullPointerException("context is null");
	}

	if (handler == null) {
		throw new NullPointerException("handler is null");
	}

	if (!(LocatorImpl.isServiceComponent(fromComponent))) { 
		throw new InvalidLocatorException(fromComponent);
	} else if (!(LocatorImpl.isServiceComponent(toComponent))) {
		throw new InvalidLocatorException(toComponent);
	}

        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new MediaSelectPermission(fromComponent));
            sm.checkPermission(new MediaSelectPermission(toComponent));
        }

	Locator current[] = handler.getServiceContentLocators();

	if (current == null || current.length == 0) {
		throw new InvalidServiceComponentException(fromComponent, "Locator not found");
	}

	Locator components[] = new Locator[current.length];
	boolean found = false;
	for (int i = 0; i < current.length; i++) {
		if (fromComponent.equals(current[i]) == true) {
			components[i] = toComponent;
			found = true;
		} else {
			components[i] = current[i];
		}
	}

	if (found == false) {
		throw new InvalidServiceComponentException(fromComponent, "Locator not found");
	}

	select(components);	
    }


    /**
     * Subscribes the specifed <code>MediaSelectListener</code> to
     * receive events related to media selection on this Player.
     *
     * @param listener The <code>MediaSelectListener</code> to which
     * to send events.
     */
    public void addMediaSelectListener(MediaSelectListener listener) {
	if (listener == null) {
		throw new NullPointerException("MediaSelectListener is null");
	}
	if (listeners == null)
		listeners = new Vector();
		
	listeners.removeElement(listener);
	listeners.addElement(listener);
    }


    /**
     * Unsubscribes the specifed <code>MediaSelectListener</code> from
     * receiving events related to media selection on this Player.
     *
     * @param listener The <code>MediaSelectListener</code> to unsubscribe.
     */
    public void removeMediaSelectListener(MediaSelectListener listener) {
	if (listener == null) {
		throw new NullPointerException("MediaSelectListener is null");
	}
	if (listeners != null)
		listeners.removeElement(listener);
    }

    /**
     * Reports the components of the current selection.
     *
     * @return An array of locators representing the service components
     * in the current selection.
     */
    public Locator[] getCurrentSelection() {
	if (handler == null) {
		return null;
	}

	return handler.getServiceContentLocators();
    }

    /**
     * Get the <code>Component</code> associated with this
     * <code>Control</code> object.
     * For example, this method might return
     * a slider for volume control or a panel containing radio buttons for 
     * CODEC control.
     * The <code>getControlComponent</code> method can return
     * <CODE>null</CODE> if there is no GUI control for
     * this <code>Control</code>.
     */
    public Component getControlComponent() {
	return null;
    }
    
   /**
    * 
    */
    public void receiveServiceContextEvent(ServiceContextEvent e) {
	MediaSelectEvent event = null;

	if (e instanceof PresentationTerminatedEvent) {
		event = new MediaSelectFailedEvent(handler, handler.getServiceContentLocators());
	} else if (e instanceof SelectionFailedEvent) {
		SelectionFailedEvent sfe = (SelectionFailedEvent)e;
		if (sfe.getReason() == sfe.CA_REFUSAL) {
			event = new MediaSelectCARefusedEvent(handler, handler.getServiceContentLocators());
		} else {
			event = new MediaSelectFailedEvent(handler, handler.getServiceContentLocators());
		}
	} else if (e instanceof ServiceContextDestroyedEvent) {
		event = new MediaSelectFailedEvent(handler, handler.getServiceContentLocators());
	} else {
		event = new MediaSelectSucceededEvent(handler, handler.getServiceContentLocators());
	}

	notifyListeners(event);
    }

   /**
    * informs all registered listeners about MediaSelectEvent
    */
    private synchronized void notifyListeners(MediaSelectEvent event) {
    	if (listeners == null) 
		return;

   	for (int i = 0; i < listeners.size(); i++) {
   		MediaSelectListener listener = (MediaSelectListener) listeners.elementAt(i);
		notifyAsyncListener(event, listener);
   	}
    }

  private void notifyAsyncListener(
		MediaSelectEvent event,
		MediaSelectListener listener) {

	if (listener == null || event == null)
		return;

	NotifyMediaSelectThread thread = new NotifyMediaSelectThread(event, listener);
	if (thread != null) 
		thread.start();
  }
}

class NotifyMediaSelectThread extends Thread {
	MediaSelectListener listener = null;
	MediaSelectEvent event = null;

  public NotifyMediaSelectThread(
		MediaSelectEvent event,
		MediaSelectListener listener) {

	super("NotifyMediaSelectThread");

	this.listener = listener;
	this.event = event;
  }

  public void run() {
	if (this.listener == null || this.event == null)
		return;

   	listener.selectionComplete(this.event);
  }
}
