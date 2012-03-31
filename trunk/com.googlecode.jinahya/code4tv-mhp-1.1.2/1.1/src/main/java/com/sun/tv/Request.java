/*
 * @(#)Request.java	1.3 99/10/04
 * 
 * Copyright (c) 2000 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of Sun
 * Microsystems, Inc. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Sun.
 * 
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 * 
 * CopyrightVersion 1.0
 */

package com.sun.tv;

import javax.tv.xlet.*;
import java.lang.InterruptedException;
import java.lang.Thread;

/* Request class:
 * 
 * This class represents the request for performing an Xlet lifecycle action.
 * It's constructed and put in a Holder for the Xlet action thread to pick up.
 * The action thread will execution this request and give a result.
 */
class Request extends Object {

    private XletContext ctx;
    private int type;
    private Xlet myXlet;
    private boolean unconditional;

    /* request types handled by Thread */
    public final static int INIT = 1;
    public final static int DESTROY = 2;
    public final static int START = 3;
    public final static int PAUSE = 4;


    /* Construct a INIT request */
    Request(Xlet myXlet, XletContext ctx) {
	this.type = INIT;
	this.ctx = ctx;
	this.myXlet = myXlet;
    }

    /* Construct a START or PAUSE request */
    Request(Xlet myXlet, int type) {
	this.type = type;
	this.myXlet = myXlet;
    }

    /* Construct a DESTROY request */
    Request(Xlet myXlet, boolean unconditional) {
	this.type = DESTROY;
	this.myXlet = myXlet;
	this.unconditional = unconditional;
    }

    /**
     * execReq:
     *
     * This method is called when this request get picked up by the action 
     * thread. It will call the Xlet lifecycle methods and return a result.
     *
     * @return Result of the action. successful or not.
     */
    public synchronized Result execReq()  {
	Result result = null;

	// execute request
	try {
		switch (type) {
		case INIT:
			myXlet.initXlet(ctx);
    			result = new Result();
    			break;

		case START: 
			myXlet.startXlet();
			result = new Result();
			break;

		case PAUSE:
			myXlet.pauseXlet();
			result = new Result();
			break;

		case DESTROY: 
			myXlet.destroyXlet(unconditional);
			result = new Result();
			break;
		}
	} catch (XletStateChangeException sce) {
		result = new Result(sce);
	} catch (Exception e) {
		result = new Result();
	}

	type = 0; // label current request satisfied.
	return result;
    }
}
