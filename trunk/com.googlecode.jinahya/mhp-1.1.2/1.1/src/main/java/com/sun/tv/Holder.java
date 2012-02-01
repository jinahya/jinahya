/*
 * @(#)Holder.java	1.2 99/10/04
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

/**
 * Holder:
 * 
 * An object holder for the producer/consumer model of request/result access.
 * It currently holds one object at a time. Perhaps this needs to be a vector
 * at some point...
 * 
 */
class Holder extends Object {

    private boolean available = false;
    private Object obj = null;

    /**
     * put -- synchronized put method will wait until there is nothing
     *        in this holder. As soon as it's notified that the holder
     *        is empty, it'll change the availability to true and
     *        set the object to be the one to be held.
     *
     * @param The object that needs to be placed in the holder.  
     */
    public synchronized void put(Object obj) {
	while (available == true) {
	    try {
		wait();
	    } catch (InterruptedException e) {
	    }
	}
	this.obj = obj;
	available = true;
	notifyAll();
    }

    /**
     * get -- synchronized get method will wait until there is something
     *        in this holder. As soon as it's notified of the availability,
     *        it'll change the availability to false and return the object.
     *
     * @return The object that's placed in the holder.
     */
    public synchronized Object get()  {
	while (available == false) {
	    try {
		wait();
	    } catch (InterruptedException e) {
	    }
	}
	available = false;
	notifyAll();
	return this.obj;
    }
}
