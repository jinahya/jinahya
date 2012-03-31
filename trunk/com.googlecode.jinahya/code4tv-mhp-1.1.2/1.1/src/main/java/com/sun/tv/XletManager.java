/* 
 * @(#)XletManager.java	1.15 99/10/04
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
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

package com.sun.tv;

import javax.tv.xlet.*;
import java.util.*;
import java.io.File;

/*********************XletManager class************************************/

public class XletManager implements AppSignalEventListener {

	private static XletManager xletManager = null;

	private static Hashtable stateTable = new Hashtable();
	private static Hashtable proxyTable = new Hashtable();
	private static Hashtable idTable = new Hashtable();
	private static Hashtable signalTable = new Hashtable();
	private boolean xletArrived = false;

    	/**
         * XletManager constructor. Should wait for signal.
      	 */
    	public XletManager() {
    	}

	public static XletManager createInstance() {
		if (xletManager == null) {
			xletManager = new XletManager();
		}
		return xletManager;
	}
    
    /**
     * This is called when XletManager receive a an Xlet related signal. It
     * should look at the signal table to determine if the Xlet is 
     *
     * 1. to be started. Check if it's autostart or not. If it's autostart, 
     *    call prepareXlet(). 
     * 2. present. call prepareXlet(). 
     * 3. to be paused. Call pauseXlet().
     * 4. to be destroyed. Call destroyXlet().
     *
     * Note this is NOT directly calling Xlet's method. Instead private 
     * methods are called to handle each case.
     */
    public Xlet signalReceived(AppSignalEvent sigEvent) {

	Xlet xlet = null;

	switch (sigEvent.getControlCode()) {
	case AppSignalEvent.AUTOSTART: 
	    xlet = prepareXlet(sigEvent);
	    if (xlet != null)
		startXlet(xlet, ((XletProxy) proxyTable.get(xlet)).getXletContext());
	    break;
	
	case AppSignalEvent.STORE:
	    signalTable.put(sigEvent.getApplicationIdentifier(), sigEvent);
	    break;

	case AppSignalEvent.START: 
	    sigEvent = (AppSignalEvent)signalTable.get(sigEvent.getApplicationIdentifier());
	    xlet = prepareXlet(sigEvent);
	    if (xlet != null)
		startXlet(xlet, ((XletProxy) proxyTable.get(xlet)).getXletContext());
	    break;

	case AppSignalEvent.PRESENT:
	    xlet = prepareXlet(sigEvent);
	    break;

	case AppSignalEvent.PAUSE: 
	    xlet = (Xlet)idTable.get(sigEvent.getApplicationIdentifier());
	    pauseXlet(xlet);
	    break;

	case AppSignalEvent.DESTROY:
	    xlet = (Xlet)idTable.get(sigEvent.getApplicationIdentifier());
	    destroyXlet(xlet, false);
	    cleanupXlet(xlet);
	    break;

	case AppSignalEvent.KILL: 
	    xlet = (Xlet)idTable.get(sigEvent.getApplicationIdentifier());
	    destroyXlet(xlet, true);
	    cleanupXlet(xlet);
	    break;
	}
	return xlet;
    }

    /**
     * Preparation for starting an xlet, this includes:
     *
     * 1. create a classloader for this xlet.
     * 2. read and form the xlet class(es) from this loader.
     * 3. create a thread group for the xlet
     * 4. create an XletContext for the xlet
     * 5. start a new thread to perform any xlet lifecycle related actions
     *    (start, stop, etc) and wait for such a request. 
     * 6. Create reqHolder and resultHolder object for these actions.
     *    This is to set up producer/consumer scenario.
     * 7. put all these info in an XletProxy and stick it into the 
     *    xletProxyTable.
     *
     * 8. put Xlet's name and Xlet in a name table.
     *    Put them all in XletProxy.
     *
     * @param  signal event
     * @return an instance of Xlet, or null if it already exist in the runtime.
     */
    private Xlet prepareXlet(AppSignalEvent sigEvent) {

	if (sigEvent == null) 
		return null;

 	try {
		XletLoader xletLoader = new XletLoader("", "." + File.separator + "xlets" + File.separator);
		Class xletClass = xletLoader.loadClass(sigEvent.getClassName().trim());
		Object obj = xletClass.newInstance();
		if (!(obj instanceof Xlet))
			return null;

		Xlet myXletIns = (Xlet)obj;

		XletContextImpl ctx = new XletContextImpl(
			this, myXletIns, sigEvent.getServiceContext(), sigEvent.getArgs());

		ThreadGroup tg = new ThreadGroup("xlet name from signal");

	    /* The next section creates objects needed for the action thread
	     * for each Xlet. The action thread is responsible for watching
	     * for request and executing required actions related to Xlet's
	     * lifecycle.
	     */
	    Holder reqHolder = new Holder();
	    Holder resultHolder = new Holder();
 	    XletRunnable xletRun = new XletRunnable(reqHolder, resultHolder);
	    Thread xletActionThread = new Thread(tg, (xletRun));
	    xletActionThread.start();

	    // create a place to hold all of the above Xlet related objects.
	    XletProxy xletProxy = new XletProxy(xletLoader, this, ctx, 
					tg, xletRun, reqHolder, 
					resultHolder, myXletIns);

	    //  Put a reference of the Xlet and its related structures 
	    //  in a hashtable
	    proxyTable.put(myXletIns, xletProxy);

	    idTable.put(sigEvent.getApplicationIdentifier(), myXletIns);

	    XletState myXletState = new XletState();
	    myXletState.setState(XletState.LOADED);
	    stateTable.put(myXletIns, myXletState);

	    return myXletIns;

	} catch (Exception ex) {
		System.out.println("XletException: " + ex);
 	}
	return null;
    }

    /**
     * XletAction
     *
     * Performe one of the Xlet lifecycle actions through the designated
     * exec thread that's allocated for this Xlet.
     * 
     * @param  Xlet -- used to get the XletProxy, which has other related 
     *         objects. This should never be null.
     * @param  Request -- the request whose 'execReq' method contains the
     *         real actions. This should never be null.
     * @return Result of the action -- usually has just the field successful
     *         set to true or false.
     */
    private Result XletAction(Xlet myXlet, Request req) {
	XletProxy myProxy = (XletProxy) proxyTable.get(myXlet);
	if ( myProxy == null ) 
		return null;

	Holder reqHolder = myProxy.getReqHolder();
	Holder resultHolder = myProxy.getResultHolder();
	    
	reqHolder.put(req);
//	return (Result)resultHolder.get();
	return null;
    }

    /**
     * Start Xlet
     *
     * Call Xlet's init and start methods and update the Xlet's state record.
     *
     * This is done through the action thread by putting a request in a 
     * commonly watched holder. The action thread will pick up the request
     * and execute it.
     * 
     * @param Xlet -- used to set up request, change state, etc.
     * @param XletContext -- used in initXlet().
     */
    private void startXlet(Xlet myXlet, XletContext myXletContext) {
	if (myXlet == null)
	    return;

	Request req = new Request(myXlet, myXletContext);
	Result result = XletAction(myXlet, req);

	changeXletState(myXlet, XletState.PAUSED);

	req = new Request(myXlet, Request.START);
	result = XletAction(myXlet, req);

	changeXletState(myXlet, XletState.ACTIVE);
    }

    /**
     * This is called if we receive a pauseXlet signal. We will call
     * Xlet's pause method through it's execution thread.
     *
     * @param Xlet
     */
    private void pauseXlet(Xlet myXlet) {
	if (myXlet == null)
	    return;

	Request req = new Request(myXlet, Request.PAUSE);
	Result result = XletAction(myXlet, req);

	changeXletState(myXlet, XletState.PAUSED);
    }	

    /**
     * This is called if we receive a destroyXlet signal. We will call
     * the destroy method of Xlet. If it's unsuccessful -- i.e. and Xlet-
     * ChangeException is returned, we might give more time if it's a
     * conditional destroy (depend on the "unconditional" variable.
     */
    private void destroyXlet(Xlet myXlet, boolean unconditional) {
	if (myXlet == null) 
	    return;

	Request req = new Request(myXlet, unconditional);
	Result result = XletAction(myXlet, req);

/**
 **	if (result == null) 
 **	    return; // xlet doesn't exist anymore, die silently.
 **
 **	// If not successful (=XletChangeException is thrown)
 **	if ( !result.getSuccess() ) {
 **	    // deal with xlet state change exception
 **	    if ( !unconditional) {
 **		try {
 **		    // Here we should allow Xlet more time to recover, then
 **		    // we should call destroy again.
 **		    Thread.sleep(10);
 **		} catch (InterruptedException ie) {
 **			;
 **		}
 **		// really destroy this time
 **		destroyXlet(myXlet, true);
 **	    } else {
 **		changeXletState(myXlet, XletState.DESTROYED);
 **	    }
 **
 **	} else {
 **	    changeXletState(myXlet, XletState.DESTROYED);
 **	}
 **/

 	changeXletState(myXlet, XletState.DESTROYED);
    }

    /**
     * Cleanup Xlet in XletManager.
     *
     * This will delete all Xlet related data from various tables in
     * XletManager, therefore xletLoader will
     * be dereferenced and xlet classes unloaded.
     * 
     */
    public void cleanupXlet(Xlet myXlet) {
      // If this Xlet exist in our table, remove it and its
      // classloader. This will clear any reference to its classloader.
	if (myXlet == null)
	    return;

	XletProxy myProxy = (XletProxy)proxyTable.get(myXlet);

	// Stop the action thread to Xlet.
	if (myProxy != null) {
		myProxy.getActionThread().shouldRun = false;
	}

	// To get rid of myXlet and its key XletID from the idtable
	if (idTable.contains(myXlet)) {
	    Enumeration enum = idTable.keys();
	    while (enum.hasMoreElements()) {
		String oldXletID = (String)enum.nextElement();
	    }
	} 
    }

    /**
     * Change Xlet state in XletManager
     *
     * Change the xlet's state in XletManager's state maintainance table.
     */
    public void changeXletState(Xlet xlet, int newState) {
	synchronized (xlet) {
	    if (stateTable.containsKey(xlet)) {

		XletState oldXletState = (XletState) stateTable.get(xlet);
		int oldState = oldXletState.getState();

		if (oldState == XletState.DESTROYED) 
		    return;

		oldXletState.setState(newState);
	    }
	}
    }

    public XletState getXletState(Xlet xlet) {
	return (XletState)stateTable.get(xlet);
    }
}
