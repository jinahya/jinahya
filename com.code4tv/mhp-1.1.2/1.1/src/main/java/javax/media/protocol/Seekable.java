/*
 * @(#)Seekable.java	1.8 98/03/28
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
 * A <code>SourceStream</code> will implement this interface
 * if it is capable of seeking to a particular position in the
 * stream.
 *
 * @see SourceStream
 * @version 1.8, 98/03/28.
 */

public interface Seekable {

    /**
     * Seek to the specified point in the stream.
     * @param where The position to seek to.
     * @return The new stream position.
     */
    long seek(long where);

    /**
     * Obtain the current point in the stream.
     */
    long tell();

   
    /**
     * Find out if this source can position anywhere in the
     * stream. If the stream is not random access, it can only be repositioned
     * to the beginning.
     *
     * @return Returns <CODE>true</CODE> if the stream is random access, <CODE>false</CODE> if the stream can only
     * be reset to the beginning.
     */
    boolean isRandomAccess();

}
