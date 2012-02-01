/*
 * @(#)URLSourceStream.java	1.3 98/03/28
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

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import com.sun.tv.media.protocol.*;

/**
 * Create a PullSourceStream from a URLConnection.
 * 
 **/
class URLSourceStream extends InputSourceStream {

    protected URLConnection conn;

    public URLSourceStream(URLConnection conn, ContentDescriptor type)
	throws IOException {
	/* Initialize with the stream. */
	super(conn.getInputStream(), type);
	this.conn = conn;
    }

    /**
     * Obtain the number of bytes available on this stream.
     *
     * @return the content length for this stream.
     */
    public long getContentLength() {
	/* See if we actually know the content length */
	long len = conn.getContentLength();
	len =  (len == -1) ? SourceStream.LENGTH_UNKNOWN : len;

	return len;
    }
}
