/*
 * @(#)NotPrefetchedError.java	1.14 98/03/28
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
 * <code>NotPrefetchedError</code> is thrown when a method that
 * requires a <CODE>Controller</CODE> to be in the <I>Prefetched</I> state is called 
 * and the <CODE>Controller</CODE> has not been <i>Prefetched</i>.  
 * <p>
 * This typically happens
 * when <code>syncStart</code> is invoked on a <I>Stopped</I>&nbsp;<code>Controller</code>
 * that hasn't been <I>Prefetched</I>.
 *
 * @see Controller
 * @version 1.14, 98/03/28.
 */

public class NotPrefetchedError extends MediaError {

    public NotPrefetchedError(String reason) {
       super(reason);
    }
}
