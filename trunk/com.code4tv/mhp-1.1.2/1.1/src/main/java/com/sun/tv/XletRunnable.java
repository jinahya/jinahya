/*
 * @(#)XletRunnable.java	1.3 99/05/13
 * 
 * Copyright (c) 1996-2000 Sun Microsystems, Inc. All Rights Reserved.
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
import java.io.IOException;

/* Request class:
 * 
 * This class represents the Thread to perform an Xlet lifecycle
 * action.  It tries to pick an action request from a request Holder
 * and put a result back to the result holder after executing this request.
 *
 * It can be stopped by changing its "shouldRun" variable. 
 */
public class XletRunnable implements Runnable {

    Holder reqHolder, resultHolder;

    // To control the termination of this thread
    public boolean shouldRun = true;

    XletRunnable(Holder reqHolder, Holder resultHolder) {
	if ( reqHolder != null && resultHolder != null) {
	    this.reqHolder = reqHolder;
	    this.resultHolder = resultHolder;
	} else {
	    throw new IllegalArgumentException("Req/result holders shouldnot be null");
	}
    }

    public void run() {
	while (shouldRun) {
	    Request req = (Request) reqHolder.get();
	    Result result = req.execReq();
//	    resultHolder.put(result); // TOm Nevin
	}
    }
}
