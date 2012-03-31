/*
 * @(#)EncapIPStream.java	1.4 00/01/26
 *
 * Copyright 1999 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 *
 * SUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. SUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */


package com.sun.tv.net;

import javax.media.protocol.PushSourceStream;
import javax.media.protocol.ContentDescriptor;
import javax.media.protocol.SourceTransferHandler;
import javax.media.protocol.Controls;
import javax.tv.media.protocol.PushSourceStream2;
import javax.tv.media.protocol.DataLostException;
import javax.tv.locator.Locator;
import javax.tv.locator.InvalidLocatorException;
import java.io.*;

import com.sun.tv.*;

/**
   The interface <code>PushSourceStream2</code> identifies a
   <code>SourceStream</code> that pushes asynchronous data.

  <p>
  Note that a <code>PushSourceStream2</code> provides no guarantees
  of the length of time that incoming data will be buffered before
  being discarded or overwritten with new data.  Because of the
  time-dependent nature of the stream, clients should read the pending
  data immediately upon notification.
*/
public class EncapIPStream implements PushSourceStream2 {

    private SourceTransferHandler handler;
    private boolean transferData = false;
    private boolean open = false;
    private static final int MTU_LLCSNAP = 4080;
    private static final int MTU_no_LLCSNAP = 4074;

    private FileInputStream tmpFileStream;
    private boolean endOfStream = false;

    public EncapIPStream(Locator loc) throws InvalidLocatorException {
	// Here is the porting layer impl -- just open a file for now. XXX

	try {

	    //File tmp = new File(loc.toExternalForm());
	    String fileStr = LocatorImpl.getMediaFile(loc);
            if (fileStr != null && fileStr.startsWith("file:/")) {
	       fileStr = fileStr.substring(new String("file:/").length());
            } 

	    //File tmp = new File(LocatorImpl.getMediaFile(loc).toExternalFor);

	    File tmp = new File(fileStr);

	    if (tmp.canRead()) {
		tmpFileStream = new FileInputStream(tmp);
	    } else {
		throw new InvalidLocatorException(loc, "can not read file at locator");
	    }
	} catch (Exception e) {
            e.printStackTrace();
	    throw new InvalidLocatorException(loc, "locator is not an valid file name");
	}
    }

    public static boolean isIPStreamLocator(Locator loc) {

	    String fileStr = LocatorImpl.getMediaFile(loc);
            if (fileStr != null && fileStr.startsWith("file:/")) {
	       fileStr = fileStr.substring(new String("file:/").length());
            } 

	    File tmp = new File(fileStr);

	if (tmp.canRead() && tmp.isFile()) {
	    return true;
	} else {
	    return false;
	}
	
    }

    public int read(byte[] buffer, int offset, int length) {
	try {
	    return readStream(buffer,offset,length);
	} catch (Exception ioe) {
	    return 0;
	}
    }
  /**
  
     Reads pending data from the stream without blocking.

  @param buffer The buffer to read bytes into.
  @param offset The offset into the buffer at which to begin writing
  data.
  @param length The number of bytes to read.

  @throws IOException If an I/O error occurs.

  @throws DataLostException If data from the stream has been lost.

  @return The number of bytes read or -1 when the end of stream is
  reached.
  */
    public int readStream(byte[] buffer, int offset, int length)
	throws IOException, DataLostException {
	/**
	 * for now we'll read from a file with one IP packet
	 */
	System.out.println("EncapIPStream, readingStream"+"offset="+offset+"length="+length);
	int bytesRead = tmpFileStream.read(buffer, offset, length);
	System.out.println("EncapIPStream, readingStream"+"bytesRead="+bytesRead);
	if (bytesRead == -1) {
	    endOfStream = true;
	}

	return bytesRead;
    }

    public void setTransferHandler(SourceTransferHandler transferHandler) {
	this.handler = transferHandler;
    }

    public int getMinimumTransferSize() {
	//if ( hasLLCSNAPheader() )
	return MTU_LLCSNAP; // This should really be decided by the length of 
	                    // the packet, but we'll fix it at 4080 for now.XXX
	    //else 
	    //return MTU_no_LLCSNAP;
    }

    /**
     *       Find out if the end of the stream has been reached. 
     */
    public boolean endOfStream() {
	return endOfStream;
    }

    /**
     *      Get the current content type for this stream. 
     */
    public ContentDescriptor getContentDescriptor() {
	return new ContentDescriptor("Encapsulated_IP_DATAGRAM");
    }
    /**
     *Get the size, in bytes, of the content on this stream. 
     */
    public long getContentLength() {
	return LENGTH_UNKNOWN;
    }
      
    /**
     * Obtain the collection of objects that control the object that
     * implements this interface.
     * If no controls are supported, a zero length array is returned.  
     */
    public Object[] getControls() {
	return new Object[0]; // no controls defined in this stream
    }

    /**
     * Obtain the object that implements the specified Class or
     * Interface The full class or interface name must be used.
     * If the control is not supported then null is returned.
     */
    public Object getControl(String controlType) {
	return null;// no controls defined in this stream
    }

    /**
     * This is a method for the DataSource corresponding to the stream to 
     * control the start/stop of the data transfer.
     * 
     * @param transfer -- true to start, false to stop
     */
    public synchronized void setTransfer(boolean transfer) {
	this.transferData = transfer;
    }

    /**
     * this is for the DataSource or whoever constructs this stream to
     * open the stream.
     * 
     */
    public synchronized void setOpen(boolean open) {
	if (open && this.open)  /* command open=true, 
				   stream reading thread already exist. */
	    return;
	else {
	    this.open = open;
	    if (open)  /* if thread doesn't exist and the command open=true
			  start new thread
		       */
		(new Thread(new StreamReadingThread(this))).start();
	}
    }


    public synchronized boolean getOpen() {
	return this.open;
    }

    class StreamReadingThread implements Runnable {
	private EncapIPStream stream;

	StreamReadingThread(EncapIPStream ipStream) {
	    System.out.println("StreamReadingThread forming a new thread");
	    stream = ipStream;
	}

	public void run() {
	    while ( open ) {
		if (transferData) { // This is controlled by DataSource object
		                    // For now assume always true
		    if (newData() && (handler != null)) { 
			handler.transferData(stream);
		    }
		    try {
			Thread.sleep(5);
		    } catch (InterruptedException ie) {
			// do nothing
		    }
		}
	    }
	}

	/**
	 * supposed to poll the interface to see if there is no data,
	 * maybe should be an event dispatching mechanism.
	 * 
	 */
	private boolean newData() {
	    return true;
	}
    }
}
