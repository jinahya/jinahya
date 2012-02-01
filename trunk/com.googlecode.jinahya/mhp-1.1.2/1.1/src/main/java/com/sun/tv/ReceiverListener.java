/*
 * @(#)ReceiverListener.java	1.12 00/08/26
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

import java.util.EventListener;
import javax.tv.service.*;

/**
 * This interface is implemented by applications wishing to receive
 * notification of changes to <code>SIElement</code> data.
 */
public interface ReceiverListener extends EventListener {

public void notifyChange(SIChangeEvent event);

}
