/*
 * @(#)AppSignalEventListener.java	1.4 99/07/08
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

import java.util.*;
import javax.tv.xlet.*;

/**
 * This interface is used by subsystems that are interested in
 * receiving events from the service selection module. These will 
 * be used to signal when new applications have been added to the
 * stream.
 */

public interface AppSignalEventListener extends EventListener {
    
    /**
     * Called when a new application signal event is created.
     */
    public abstract Xlet signalReceived(AppSignalEvent evt);

}

