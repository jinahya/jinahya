/*
 * @(#)SeekFailedEvent.java	1.1 98/07/30
 *
 * Copyright 1996-1998 by Sun Microsystems, Inc.,
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

import javax.media.*;

/**
 * A <code>SeekFailedEvent</code> indicates that the <code>Controller</code> could not
 * start at the current media time (set using setMediaTime).
*/

public class SeekFailedEvent extends StopEvent {

    public SeekFailedEvent(Controller from,
			   int previous, int current, int target,
			   Time mediaTime) {
	super(from, previous, current, target, mediaTime);
    }
}
