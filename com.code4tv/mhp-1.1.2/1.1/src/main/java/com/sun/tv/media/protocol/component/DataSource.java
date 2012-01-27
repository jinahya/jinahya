/*
 * @(#)DataSource.java	1.2 98/03/28
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

import java.io.*;
import java.net.*;
import java.util.Hashtable;
import javax.media.*;
import javax.media.protocol.*;
import com.sun.tv.*;

public class DataSource extends javax.media.protocol.PushDataSource {

    protected boolean connected = false;
    protected boolean started = false;
    protected URL url = null;
    protected URLConnection urlConnection = null;
    protected ContentDescriptor contentType;
    protected PushSourceStream2Impl sources[] = null;

/**
 * Get the collection of streams that this source
 * manages. The collection of streams is entirely
 * content dependent. The <code>ContentDescriptor</code>
 * of this <CODE>DataSource</CODE> provides the only indication of
 * what streams can be available on this connection.
 *
 * @return The collection of streams for this source.
 */
public PushSourceStream[] getStreams() {
	if (sources != null && sources[0] != null) {
		return sources;
	}
	return new PushSourceStream2Impl[0];
}

/**
 * Get a string that describes the content-type of the media
 * that the source is providing.
 * <p>
 * It is an error to call <CODE>getContentType</CODE> if the source is
 * not connected.
 *
 * @return The name that describes the media content.
 */
public String getContentType() {
	if (!connected) {
		throw new java.lang.Error("Unconnected DataSource");
	}
	return contentType.getContentType();
}

/**
 * Open a connection to the source described by
 * the <CODE>MediaLocator</CODE>.
 * <p>
 *
 * The <CODE>connect</CODE> method initiates communication with the source.
 *
 * @exception IOException Thrown if there are IO problems
 * when <CODE>connect</CODE> is called.
 */
public void connect() throws IOException {
	// Convert the MediaLocator to a URL by pulling out the protocol.
	String urlPath = LocatorImpl.getMediaFile(getLocator().toExternalForm());
	try {
		url = new URL(urlPath);
	} catch (MalformedURLException mue) {
		throw new IOException("Invalid locator");
	}
	
	urlConnection = url.openConnection();
	urlConnection.connect();
	connected = true;

	// Figure out the content type.
	String mimeType = urlConnection.getContentType();

	if( mimeType == null) {
	    mimeType = ContentDescriptor.CONTENT_UNKNOWN;
	}
	
	contentType = new ContentDescriptor(
		ContentDescriptor.mimeTypeToPackageName(mimeType));

	// Create a source stream.
	sources = new PushSourceStream2Impl[1];
	sources[0] = new PushSourceStream2Impl(urlConnection, contentType);
}

/**
 * Close the connection to the source described by the locator.
 * <p>
 * The <CODE>disconnect</CODE> method frees resources used to maintain a
 * connection to the source.
 * If no resources are in use, <CODE>disconnect</CODE> is ignored.
 * If <CODE>stop</CODE> hasn't already been called,
 * calling <CODE>disconnect</CODE> implies a stop.
 *
 */
public void disconnect() {
	if(connected) {
		sources[0].close();
		connected = false;
	}
}

/**
 * Initiate data-transfer. The <CODE>start</CODE> method must be
 * called before data is available.
 *(You must call <CODE>connect</CODE> before calling <CODE>start</CODE>.)
 *
 * @exception IOException Thrown if there are IO problems with the source
 * when <CODE>start</CODE> is called.
 */
public void start() throws IOException {
	started = true;
}

/**
 * Stop the data-transfer.
 * If the source has not been connected and started,
 * <CODE>stop</CODE> does nothing.
 */
public void stop() throws IOException {
	started = false;
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
	Object controls[] = new Object[2];
	controls[0] = sources[0];
	controls[1] = this;
	return controls;
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
	if (controlType == null || sources == null || sources.length == 0) {
		return null;
	} else if (controlType.equals("javax.media.protocol.PushDataSource")) {
		return this;
	} else if (controlType.equals("javax.tv.media.protocol.PushSourceStream")) {
		return sources[0];
	} else if (controlType.equals("javax.tv.media.protocol.PushSourceStream2")) {
		return sources[0];
	}
	return null;
}

/**
 * Get the duration of the media represented
 * by this object.
 * The value returned is the media's duration
 * when played at the default rate.
 * If the duration can't be determined  (for example, the media object is presenting live
 * video)  <CODE>getDuration</CODE> returns <CODE>DURATION_UNKNOWN</CODE>.
 *
 * @return A <CODE>Time</CODE> object representing the duration or DURATION_UNKNOWN.
 */
public Time getDuration() {
	return Duration.DURATION_UNKNOWN;
}
}
