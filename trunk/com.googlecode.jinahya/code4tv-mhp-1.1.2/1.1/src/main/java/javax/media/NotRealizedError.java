/*
 * @(#)NotRealizedError.java	1.10 98/03/28
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
 * <code>NotRealizedError</code> is thrown when a method that
 * requires a <code>Controller</code> to be in the <i>Realized</i> state is called 
 * and the <code>Controller</code> is not <i>Realized</i>.
 * <p>
 * 
 * For example, this can happen when
 * <code>getComponents</code> is called on an <i>Unrealized</i>&nbsp;
 * <code>Player</code>.
 *
 * @see Controller
 * @see Player
 * @version 1.10, 98/03/28.
 */

public class NotRealizedError extends MediaError {

    public NotRealizedError(String reason) {
       super(reason);
    }
}
