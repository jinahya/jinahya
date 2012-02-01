/*
 * @(#)XletProxy.java	1.6 99/10/04
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

/**
 * The XletProxy class provides a context for Xlet's running in
 * an Xlet manager. In particular it is provides the Xlet Manager 
 * with access to all the objects needed to support the 
 * normal operation of an Xlet.<p>
 *
 * SCCS VERSION: 99/10/04 @(#)XletProxy.java	1.6
 *
 * @version ifa
 */

public class XletProxy {
    private ClassLoader loader = null;
    private XletManager mgr = null;
    private XletContext ctx = null;
    private ThreadGroup tg = null;
    private Holder reqHolder = null;
    private Holder resultHolder = null;
    private XletRunnable execT = null;

    private Xlet app = null;

    /**
     * The constructor for XletProxy. In order for an instance
     * of this class to be of value, it needs to have <b>all</b>
     * of the objects mentioned in the constructor.
     *
     * @param loader The <code>ClassLoader</code> used to load the Xlet.
     * @param mgr The <code>XletManager</code> used to manage the Xlet.
     * @param ctx The <code>XletContext</code> passed to the Xlet.
     * @param tg The <code>ThreadGroup</code> used to manage the Xlet.
     * @param reqHolder The object Holder used to hold requests for execXlet
     *                  thread.
     * @param resultqHolder The object Holder used to hold results for execXlet
     *                  thread.
     * @param execT The <code>XletRunnable</code> used to exec Xlet commands.
     * @param app The <code>Xlet</code>
     */
    
    public XletProxy(ClassLoader loader,
		     XletManager mgr,
		     XletContext ctx,
		     ThreadGroup tg,
		     XletRunnable execT,
		     Holder reqHolder,
		     Holder resultHolder,
		     Xlet  app) {

	this.loader = loader;
	this.mgr = mgr;
	this.ctx = ctx;
	this.tg = tg;
	this.reqHolder = reqHolder;
	this.resultHolder = resultHolder;
	this.execT = execT;
	this.app = app;
    }

    /**
     * Returns a reference to the Xlet refered to by this Proxy object.
     *
     * @return The Xlet.
     */
    public Xlet getXlet(){
	return app;
    }
    
    /**
     * Returns a reference to the XletContext for the Xlet
     * @return The XletContext.
     */
    public XletContext getXletContext(){
	return ctx;
    }
    
    /**
     * Returns a reference to the ThreadGroup.
     * @return The ThreadGroup.
     */
    public ThreadGroup getThreadGroup(){
	return tg;
    }
    
    /**
     * Returns a reference to the ClassLoader for the Xlet.
     * @return The Loader.
     */
    public ClassLoader getClassLoader(){
	return loader;
    }
    
    /**
     * Returns a reference to the XletManager for the Xlet.
     * @return The XletManager.
     */
    public XletManager getXletManager(){
	return mgr;
    }

    /**
     * Returns a reference to the Xlet action command request holder -- 
     * reqHolder for the Xlet.
     * @return The reqHolder
     */
    public Holder getReqHolder(){
	return reqHolder;
    }


    /**
     * Returns a reference to the Xlet action command result holder -- 
     * resultHolder for the Xlet.
     * @return The resultHolder
     */
    public Holder getResultHolder(){
	return resultHolder;
    }

    /**
     * Returns a reference to the Xlet action runnable (XletRunnable)
     * for the Xlet.
     * @return The action runnable.
     */
    public XletRunnable getActionThread(){
	return execT;
    }

}
