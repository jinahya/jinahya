/*
 * @(#)MediaSelectFailedEvent.java	1.11 00/10/09
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

package javax.tv.media;

import javax.tv.locator.*;
import javax.media.Controller;

/**
 * <code>MediaSelectFailedEvent</code> notifies a
 * <code>MediaSelectListener</code> that a selection operation failed.
 *
 * @see MediaSelectListener
 **/
public class MediaSelectFailedEvent extends MediaSelectEvent {

    /**
     * Creates a new <code>MediaSelectFailedEvent</code>.
     * 
     * @param source The Controller that generated this event.
     * 
     * @param selection The <code>Locator</code>instances on which
     * selection was attempted.
     */
    public MediaSelectFailedEvent(Controller source, Locator[] selection) {
      super(null, new Locator[0]);
    }
}
