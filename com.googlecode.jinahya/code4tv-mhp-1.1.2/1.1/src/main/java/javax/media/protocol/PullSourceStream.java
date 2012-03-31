/*
 * @(#)PullSourceStream.java	1.10 98/03/28
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
import java.io.*;

/**
 * Abstracts a read interface that data is pulled from.
 *
 * @see PullDataSource
 * @version 1.10, 98/03/28.
 */
public interface  PullSourceStream extends SourceStream {

    /**
     * Find out if data is available now.
     * Returns <CODE>true</CODE> if a call to <CODE>read</CODE> would block
     * for data.
     *
     * @return Returns <CODE>true</CODE> if read would block; otherwise
     * returns <CODE>false</CODE>.
     */
    public boolean willReadBlock();

    /**
     * Block and read data from the stream.
     * <p>
     * Reads up to <CODE>length</CODE> bytes from the input stream into
     * an array of bytes.
     * If the first argument is <code>null</code>, up to
     * <CODE>length</CODE> bytes are read and discarded.
     * Returns -1 when the end
     * of the media is reached.
     *
     * This method  only returns 0 if it was called with
     * a <CODE>length</CODE> of 0.
     *
     * @param buffer The buffer to read bytes into.
     * @param offset The offset into the buffer at which to begin writing data.
     * @param length The number of bytes to read.
     * @return The number of bytes read, -1 indicating
     * the end of stream, or 0 indicating <CODE>read</CODE>
     * was called with <CODE>length</CODE> 0.
     * @throws IOException Thrown if an error occurs while reading. 
     */
    public int read(byte[] buffer, int offset, int length)
	throws IOException;

     
}
