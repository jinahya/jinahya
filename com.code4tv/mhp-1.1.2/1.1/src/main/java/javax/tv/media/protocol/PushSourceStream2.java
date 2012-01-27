/*
 * @(#)PushSourceStream2.java	1.14 00/10/09
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

package javax.tv.media.protocol;

import javax.media.protocol.PushSourceStream;
import java.io.IOException;


/**
 *
 * The <code>PushSourceStream2</code> interface identifies a
 * <code>SourceStream</code> that pushes asynchronous data.
 *
 * <p> Note that a <code>PushSourceStream2</code> provides no
 * guarantees of the length of time that incoming data will be
 * buffered before being discarded or overwritten with new data.
 * Because of the time-dependent nature of the stream, clients should
 * read the pending data immediately upon notification.
 *
 * <p> This interface is functionally identical to
 * <code>javax.media.protocol.PushSourceStream</code>, except that it
 * provides the <code>readStream()</code> method that throws
 * exceptions.  Instances of <code>PushSourceStream2</code> may be
 * obtained through the JMF method
 * <code>javax.media.protocol.PushDataSource.getStreams()</code>.  In
 * Java TV implementations, objects returned by this method will be of
 * type <code>PushSourceStream2</code>.  Instances of
 * <code>PushDataSource</code> are obtained by means of
 * <code>javax.media.Manager.createDataSource(javax.media.MediaLocator)</code>.
 * If access to broadcast asynchronous data is not supported by the
 * system, this method will throw
 * <code>javax.media.NoDataSourceException</code>.
 *
 * @see javax.media.protocol.PushDataSource#getStreams javax.media.protocol.PushDataSource.getStreams()
 *
 * @author Jon Courtney courtney@eng.sun.com */
public interface PushSourceStream2 extends PushSourceStream {
	
  /**
   * Reads pending data from the stream without blocking.
   *
   * @param buffer The buffer to read bytes into.
   * @param offset The offset into the buffer at which to begin writing
   * data.
   * @param length The number of bytes to read.
   * 
   * @throws IOException If an I/O error occurs.
   * 
   * @throws DataLostException If data from the stream has been lost.
   *
   * @throws ArrayIndexOutOfBoundsException If <code>offset <
   * 0</code>, <code>length < 0</code>, or <code>offset+length >
   * buffer.length</code>.
   * 
   * @return The number of bytes read or -1 when the end of stream is
   * reached.
   */
  public int readStream(byte[] buffer, int offset, int length)
      throws IOException, DataLostException;

}
