 /*
 * @(#)PushSourceStream2Impl.java
 *
 * Copyright 2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.media.protocol.component;

import java.util.*;
import java.io.*;
import java.net.*;
import javax.media.*;
import javax.media.protocol.*;
import javax.tv.media.protocol.*;

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
 * <code>javax.media.Manager.createDataSource()</code>.  If access to
 * broadcast asynchronous data is not supported by the system, this
 * method will throw <code>javax.media.NoDataSourceException</code>.
 *
 * @see javax.media.protocol.PushDataSource#getStreams
 *
 */
public class PushSourceStream2Impl implements PushSourceStream2 {

    protected boolean eos = false;
    protected long contentLength = LENGTH_UNKNOWN;
    protected SourceTransferHandler sth = null;
    protected ContentDescriptor contentType;
    protected InputStream inputStream;

public PushSourceStream2Impl(URLConnection urlConnection,
	ContentDescriptor contentDesc) throws IOException {

	contentType = contentDesc;
	contentLength = urlConnection.getContentLength();
	if (contentLength < 1) {
		contentLength = LENGTH_UNKNOWN;
	}
	inputStream = urlConnection.getInputStream();
}

public static void registerComponentProtocol() {
	String ComponentProtocol = "component";
	String ProtocolPrefix = "com.sun.tv";
	Vector list = PackageManager.getProtocolPrefixList();
	if (list == null) {
		list = new Vector();
	}

	boolean found = false;
	for (int i = 0; i < list.size(); i++) {
		String cur = (String)list.elementAt(i);
		if (ProtocolPrefix.equalsIgnoreCase(cur)) {
			found = true;
		}
	}

	if (found == false) {
		list.addElement(ProtocolPrefix);
	}

	PackageManager.setProtocolPrefixList(list);
}

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
	throws IOException, DataLostException {

        if ( buffer == null )
                throw new NullPointerException();

	if (offset < 0) 
		throw new ArrayIndexOutOfBoundsException("offset < 0");


	if (length < 0) 
		throw new ArrayIndexOutOfBoundsException("length < 0");

	if ((offset + length) > buffer.length) 
		throw new ArrayIndexOutOfBoundsException("(offset+length) > buffer.length");

	return read(buffer, offset, length);
}

/**
 * Read from the stream without blocking.
 * Returns -1 when the end of the media
 * is reached.
 *
 * @param buffer The buffer to read bytes into.
 * @param offset The offset into the buffer at which to begin writing data.
 * @param length The number of bytes to read.
 * @return The number of bytes read or -1
 * when the end of stream is reached.
 */
public int read(byte[] buffer, int offset, int length) {
	if (length < 0) 
	    return 0;

	if (endOfStream()) 
	    return -1;

	int canRead = buffer.length - offset;
	if (canRead <= 0) 
		return 0;

	if (canRead > length) 
		canRead = length;

	if (available() == 0) 
		return 0;

	if (available() < canRead) 
		canRead = available();

	try {
		return inputStream.read(buffer, offset, canRead);
	} catch (IOException ioe) {
		eos = true;
	}
	return -1;
}
    
/**
 * Determine the size of the buffer needed for the data transfer.
 * This method is provided so that a transfer handler
 * can determine how much data, at a minimum, will be
 * available to transfer from the source.
 * Overflow and data loss is likely to occur if this much
 * data isn't read at transfer time.
 *
 * @return The size of the data transfer.
 */
public int getMinimumTransferSize() {
	return 8192; // Arbitrary
}

/**
 * Register an object to component data transfers to this stream.
 * <p>
 * If a handler is already registered when
 * <CODE>setTransferHandler</CODE> is called,
 * the handler is replaced;
 * there can only be one handler at a time.
 * 
 * @param transferHandler The handler to transfer data to.
 */
public void setTransferHandler(SourceTransferHandler transferHandler) {
	if (transferHandler == null) 
		throw new NullPointerException("setTransferHandler(null)");

	sth = transferHandler;
}

/**
 * Get the current content type for this stream.
 *
 * @return The current <CODE>ContentDescriptor</CODE> for this stream.
 */
public ContentDescriptor getContentDescriptor() {
	return contentType;
}

/**
 * Get the size, in bytes, of the content on this stream.
 * LENGTH_UNKNOWN is returned if the length is not known.
 *
 * @return The content length in bytes.
 */
public long getContentLength() {
	return contentLength;
}

/**
 * Find out if the end of the stream has been reached.
 *
 * @return Returns <CODE>true</CODE> if there is no more data.
 */
public boolean endOfStream() {
	if (available() > 0) {
		return false;
	}
	return eos;
}

/**
 * Obtain the collection of objects that
 * control the object that implements this interface.
 * <p>
 *
 * If no controls are supported, a zero length
 * array is returned.
 *
 * @return the collection of object controls
 */
public Object[] getControls() {
	return new Control[0];
}

/**
 * Obtain the object that implements the specified
 * <code>Class</code> or <code>Interface</code>
 * The full class or interface name must be used.
 * <p>
 * 
 * If the control is not supported then <code>null</code>
 * is returned.
 *
 * @return the object that implements the control,
 * or <code>null</code>.
 */
public Object getControl(String controlType) {
	return null;
}

private int available() {
	if (inputStream == null) {
		return 0;
	}

	try {
		return inputStream.available();
	} catch (Exception e) {
		;
	}
	return 0;
}

public void close() {
	try {
		if (inputStream != null) {
			inputStream.close();
		}
	} catch (Exception e) {
		;
	}
}
}
