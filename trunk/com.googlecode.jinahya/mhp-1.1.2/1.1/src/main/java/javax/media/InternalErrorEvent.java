/*
 * @(#)InternalErrorEvent.java	1.9 98/03/28
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

package javax.media;

/**
 * An <code>InternalErrorEvent</code> indicates that a <code>Controller</code> failed
 * for implementation-specific reasons.
 * This event indicates that there are problems with the implementation of the <code>Controller</code>.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.9, 98/03/28
 */
public class InternalErrorEvent extends ControllerErrorEvent {


    public InternalErrorEvent(Controller from) {
        super(from);
    }

    public InternalErrorEvent(Controller from, String message) {
        super(from, message);
    }

}
