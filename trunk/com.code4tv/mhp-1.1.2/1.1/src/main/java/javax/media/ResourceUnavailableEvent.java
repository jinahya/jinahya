/*
 * @(#)ResourceUnavailableEvent.java	1.23 98/03/28
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
 * A <code>ResourceUnavailableEvent</code> indicates that a <code>Controller</code> was
 * unable to allocate a resource that it requires for operation.
 *
 * @see Controller
 * @see ControllerListener
 * @version 1.23, 98/03/28
 */
public class ResourceUnavailableEvent extends ControllerErrorEvent{


    public ResourceUnavailableEvent(Controller from) {
        super(from);
    }

    public ResourceUnavailableEvent(Controller from, String message) {
        super(from, message);
    }

}
