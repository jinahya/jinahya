/*
 * @(#)ServiceMediaHandler.java	1.10 00/08/06
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

package javax.tv.service.selection;

import javax.media.Player;

/**
 * <code>ServiceMediaHandler</code> represents an handler of service
 * components that are real time media sharing the same clock.  A
 * <code>ServiceMediaHandler</code> is associated with the
 * <code>Service</code> currently selected in the
 * <code>ServiceContext</code> from which it was obtained.
 *
 * @see ServiceContext
 * @see javax.tv.media.MediaSelectControl
 */
public interface ServiceMediaHandler
extends Player, ServiceContentHandler {
}

