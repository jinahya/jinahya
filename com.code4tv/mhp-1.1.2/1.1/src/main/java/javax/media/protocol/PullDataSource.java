/*
 * @(#)PullDataSource.java	1.7 98/03/28
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
 * Abstracts a media data-source that  only supports
 * pull data-streams.
 *
 * @see javax.media.Manager
 * @see javax.media.Player
 * @see DefaultPlayerFactory
 * @see DataSource
 * @version 1.7, 98/03/28.
 */


public abstract class PullDataSource extends DataSource {

    /**
     * Get the collection of streams that this source
     * manages. The collection of streams is entirely
     * content dependent. The  MIME type of this
     * <CODE>DataSource</CODE> provides the only indication of
     * what streams can be available on this connection.
     *
     * @return The collection of streams for this source.
     */
    public abstract PullSourceStream[] getStreams();

}
