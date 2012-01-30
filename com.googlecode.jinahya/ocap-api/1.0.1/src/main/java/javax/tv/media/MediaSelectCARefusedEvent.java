/*
 * @(#)MediaSelectCARefusedEvent.java	1.11 00/08/26
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
 * <code>MediaSelectCARefusedEvent</code> is generated when a
 * media select operation fails due to lack of CA authorization.
 **/
public class MediaSelectCARefusedEvent extends MediaSelectFailedEvent {

    /**
     * Constructs the <code>MediaSelectCARefusedEvent</code>.
     * 
     * @param source The <code>Controller</code> that generated this
     * event.
     *
     * @param selection The <code>Locator</code> instances on which
     * selection failed.
     **/
    public MediaSelectCARefusedEvent(Controller source,
				     Locator[] selection) {
	super(null, new Locator[0]);
    }
}

