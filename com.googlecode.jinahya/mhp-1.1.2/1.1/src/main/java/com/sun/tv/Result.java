/*
 * @(#)Result.java	1.1 99/10/04
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

/* Result class:
 * 
 * This class represents the result from performing an Xlet lifecycle
 * action.  It's constructed after the action and put in a Holder for
 * the XletManager thread to pick up.  The result is basically either
 * successful or a XletStateChangeException is thrown -- unsuccessful.  
 *
 */
class Result extends Object {

    private boolean success;
    private XletStateChangeException sce;

    Result() {
	this.success = true;
    }
    Result(XletStateChangeException sce) {
	this.sce = sce;
	this.success = false;
    }

    public boolean getSuccess() {
	return success;
    }

    public XletStateChangeException getException() {
	return sce;
    }
}
