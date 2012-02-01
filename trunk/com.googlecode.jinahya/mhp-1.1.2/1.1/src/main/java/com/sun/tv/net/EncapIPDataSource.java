package com.sun.tv.net;

import javax.media.protocol.*;
import javax.media.*;
import javax.tv.locator.*;
import javax.tv.media.protocol.*;
import java.io.IOException;

public class EncapIPDataSource extends PushDataSource {

    EncapIPStream stream = null;

    /**
     * Will create a stream for the locator
     */
    public EncapIPDataSource(MediaLocator mediaLoc) throws MalformedLocatorException {
	LocatorFactory locFact = LocatorFactory.getInstance();

	Locator tvLoc = locFact.createLocator(mediaLoc.toExternalForm());

	// catch exception on ctor
	try {
	    stream = new EncapIPStream(tvLoc);
	} catch(InvalidLocatorException e){
	    System.err.println(e);
	}
    }
	
    public String getContentType() {
	return new String("encapsulated IP datagram");
    }

    /**
     * Since EncapIPStream reads from a file, connect() is simulated
     * by starting the StreamReadingThread in EncapIPStream (calling
     * setOpen() on stream, if one doesn't exist (signalled by
     * open=false). This thread will wait till start() is called to
     * start reading from a file (related to locator), and will stop
     * reading on stop(). Disconnect() will stop the reading thread
     * (by calling setOpen(false). Another connect() will start the
     * thread again, meaning it'll read the file from the beginning.  
     */

    public void connect() throws IOException {
	if ( stream == null ) {
	    throw new IOException("no stream for the IP data source");
	}

	// Normally the stream is open when it was constructed, and connect 
	// will do nothing. But if this stream has been disconnected once,
	// we need to reconstruct the StreamReadingThread.
	stream.setOpen(true);
    }

    public void disconnect() {
	if ( stream != null ) {
	    stream.setOpen(false);
	}
    }
    
    public void start() throws IOException {
	if ( stream == null ) {
	    throw new IOException("no stream for the IP data source");
	}
	stream.setTransfer(true);
    }


    public void stop() throws IOException {
	if ( stream == null ) {
	    throw new IOException("no stream for the IP data source");
	}
	stream.setTransfer(false);
    }
    
    /*
     * Methods from inherited interfaces Controls and Duration.
     */
    public Object[] getControls() {
	return null;
    }

    public Object getControl(String control) {
	return null;
    }

    public Time getDuration() {
	return Duration.DURATION_UNKNOWN;
    }

    /*
     * Methods from PushDataSource
     */
    public PushSourceStream[] getStreams() {
	PushSourceStream[] streams = new PushSourceStream[1];
	if ( stream != null ) {
	    streams[0] = stream;
	    return streams;
	}
	else 
	    return null;
    }
}
