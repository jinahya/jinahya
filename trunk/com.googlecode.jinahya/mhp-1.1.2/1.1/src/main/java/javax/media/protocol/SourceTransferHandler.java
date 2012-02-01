/*
 * @(#)SourceTransferHandler.java	1.7 98/03/28
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

package javax.media.protocol;

/**
 * Implements the callback from a <CODE>PushSourceStream</CODE>.
 *
 * @see PushSourceStream
 * @version 1.7, 98/03/28.
 */

public interface SourceTransferHandler {

    /**
     * Transfer new data from a <CODE>PushSourceStream</CODE>.
     *
     * @param stream The stream that is providing the data.
     */
     public void transferData(PushSourceStream stream);
}
