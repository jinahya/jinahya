/*
 * @(#)DataSource.java	1.18 98/12/08
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

package com.sun.tv.media.protocol.file;

import java.io.*;
import java.net.*;
import javax.media.*;
import javax.media.protocol.*;
import com.sun.tv.media.*;
import com.sun.tv.media.protocol.*;
import com.sun.tv.media.util.*;

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * This class implements a pull data source for file protocol. 
 * A new DataSource has to be provided for each new protocol.
*/
 public class DataSource  extends PullDataSource {

    protected RandomAccessFile raf;
    private boolean connected = false;
    protected long length = -1;
    private String contentType = null;
    private PullSourceStream[] pssArray = new PullSourceStream[1];


    public String getContentType() {
	if (!connected)
	    return null;
	return contentType;
    }

    public void connect() throws IOException {
	if (connected)
	    return;

	MediaLocator locator = getLocator();
	if (locator == null) {
	    System.err.println("medialocator is null");
	    throw(new IOException(this + ": connect() failed"));
	}

	//////////////////////////////////////////////////////////////////////
	// Getting the URL only to get the content type.
	// Is there a better way?
	URL url;
	try {
	    url = locator.getURL();
	} catch (MalformedURLException e) {
	    System.err.println(getLocator() +
			       ": Don't know how to deal with non-URL locator yet!");
	    throw(new IOException(this + ": connect() failed"));
	}
	
	try {
	    // Get Netscape permissions to read file
	    try {
	    	JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
	    					   JMFSecurity.readFileArgs);
	    } catch (Exception e) {}

	    URLConnection urlC = url.openConnection();
	    contentType = urlC.getContentType();
	    contentType = getCorrectedContentType(contentType);
	    contentType = ContentDescriptor.mimeTypeToPackageName(contentType);
	    // How do I close the URLConnection??
	    //////////////////////////////////////////////////////////////////////
	    
	    String fileName = locator.getRemainder();
	    

	    // Parse filename to de-normalize it.
	    String saved = fileName;
	    try {
		// Change %xy to a character
		int idx = 0;
		while ((idx = fileName.indexOf("%", idx)) >= 0) {
		    if (fileName.length() > idx + 2) {
			byte [] bytes = new byte[1];
			try {
			    bytes[0] = (byte)Integer.valueOf(
				fileName.substring(idx + 1, idx + 3), 16).intValue();
			    fileName = fileName.substring(0, idx) +
				new String(bytes) +
				fileName.substring(idx + 3);
			} catch (NumberFormatException ne) {
			}
		    }
		    idx++;
		}
		
		// Change | to :
		idx = 0;
		while ((idx = fileName.indexOf("|")) >= 0) {
		    if (idx > 0) {
			fileName = fileName.substring(0, idx) + ":" +
			    fileName.substring(idx + 1);
		    } else {
			fileName = fileName.substring(1);
		    }
		}

		// Strip off excess /'s 
		while (fileName.charAt(0) == '/' &&
		       (fileName.charAt(1) == '/' || fileName.charAt(2) == ':'))
		    fileName = fileName.substring(1);

	    } catch (Exception e) {
		fileName = saved;
	    }

	    raf = new RandomAccessFile(fileName, "r");
	    length = raf.length();
	    if (length < 0)
		length = SourceStream.LENGTH_UNKNOWN;
	    PullSourceStream pss = new RAFPullSourceStream();
	    pssArray[0] = pss;
	    
	    connected = true;
	} catch (IOException ioe) {
	    throw new IOException(JMFI18N.getResource("error.filenotfound"));
	}
    }


    public void disconnect() {
	try {
	    if (raf != null) {
		raf.close();
	    }
	} catch (IOException e) {
	}
	if ( pssArray != null ) {
	    pssArray[0] = null;
	}
	connected = false;
    }

    public void start() throws IOException {
    }

    public void stop() throws IOException {
    }

    public void setLocator (MediaLocator ml) {

	// If it's file protocol, we'll try to strip out special characters
	// in the URL syntax:
	// %xx = the ASCII represented by the hexadecimal number "xx".
	if (ml != null && ml.getProtocol() != null && ml.getProtocol().equals("file")) {
	    int idx;
	    MediaLocator saved = ml;
	    String file = ml.getRemainder();
	    boolean changed = false;

	    if (file == null) {
		super.setLocator(ml);
		return;
	    }
	    try {
		idx = 0;
		while ((idx = file.indexOf("%", idx)) >= 0) {
		    if (file.length() > idx + 2) {
			byte [] bytes = new byte[1];
			try {
			    bytes[0] = (byte)Integer.valueOf(
						file.substring(idx + 1, idx + 3), 16).intValue();
			    file = file.substring(0, idx) + new String(bytes) +
				file.substring(idx + 3);
			    changed = true;
			} catch (NumberFormatException ne) {
			}
		    }
		    idx++;
		}
		if (changed)
	    	    ml = new MediaLocator(ml.getProtocol() + ":" + file);
	    } catch (Exception e) {
		ml = saved;
	    }
	}

	super.setLocator(ml);
    }

    public PullSourceStream[] getStreams() {
	return pssArray;
    }

    public Time getDuration() {
	return null;
    }

    public Object[] getControls() {
	return new Object[0];

    }

    public Object getControl(String controlType) {
	return null;
    }

    protected String getCorrectedContentType(String contentType) {
        if (contentType != null) {
            if (contentType.equals("audio/wav")) {
                contentType = "audio/x-wav";
                //System.out.println("audio/wav ==> audio/x-wav");
            } else if (contentType.equals("audio/aiff")) {
                contentType = "audio/x-aiff";
                //System.out.println("audio/aiff ==> audio/x-aiff");
            } else if (contentType.equals("application/x-troff-msvideo")) {
                // $$ WORKAROUND DUE TO WRONG MIME TYPE GIVEN FOR AVI
                //System.out.print("MIME TYPE BUG: ");
                //System.out.println("application/x-troff-msvideo ==> video/x-msvideo");
                contentType = "video/x-msvideo";
            } else if (contentType.equals("video/msvideo")) {
                contentType = "video/x-msvideo";
            } else if (contentType.equals("video/avi")) {
                contentType = "video/x-msvideo";
            } else if (contentType.equals("audio/x-mpegaudio")) {
                contentType = "audio/mpeg";
            } else if (contentType.equals("content/unknown")) {
		// Catch a few well known types even if they are not defined
		// in the system MIME table.
		String type = guessContentType(getLocator());
		if (type != null)
		    contentType = type;
	    }
        } else {
	    contentType = "content/unknown";
	}
	return contentType;
    }

    // Guess the contentType based on the file extension.
    private String guessContentType(MediaLocator locator) {
	String path = locator.getRemainder();
	int i = path.lastIndexOf(".");
	if (i != -1) {
	    String ext = path.substring(i+1).toLowerCase();

	    if (ext.equals("mov"))
		return "video/quicktime";
	    else if (ext.equals("avi"))
		return "video/x_msvideo";
	    else if (ext.equals("mpg"))
		return "video/mpeg";
	    else if (ext.equals("mpv"))
		return "video/mpeg";
	    else if (ext.equals("viv"))
		return "video/vivo";
	    else if (ext.equals("au"))
		return "audio/basic";
	    else if (ext.equals("wav"))
		return "audio/x_wav";
	    else if (ext.startsWith("aif"))
		return "audio/x_aiff";
	    else if (ext.equals("mid") || ext.equals("midi"))
		return "audio/midi";
	    else if (ext.equals("rmf"))
		return "audio/rmf";
	    else if (ext.equals("gsm"))
		return "audio/x_gsm";
	    else if (ext.equals("mp2"))
		return "audio/mpeg";
	    else if (ext.equals("mp3"))
		return "audio/mpeg";
	    else if (ext.equals("mpa"))
		return "audio/mpeg";
	    else if (ext.equals("swf"))
		return "application/x-shockwave-flash";
	    else if (ext.equals("spl"))
		return "application/futuresplash";
	}
	return null;
    }

    class RAFPullSourceStream implements PullSourceStream, Seekable {

	public long seek(long where) {
	    try {
		raf.seek(where);
		return tell();
	    } catch (IOException e) {
		System.out.println("seek: " + e);
		return -1;
	    }

	}

	public long tell() {
	    try {
		return raf.getFilePointer();
	    } catch (IOException e) {
		System.out.println("tell: " + e);
		return -1;
	    }
	}

	public boolean isRandomAccess() {
	    return true;
	}

	public boolean willReadBlock() {
	    return false;
	}

	public int read(byte[] buffer, int offset, int length)
	    throws IOException {
	    return raf.read(buffer, offset, length);
	}

	// TODO
	public ContentDescriptor getContentDescriptor() {
	    // System.out.println("in getContentDescriptor"); // TODO
	    return null;
	}

	public long getContentLength() {
	    return length;
	}

	public boolean endOfStream() {
	    return false; // TODO
	}

	public Object[] getControls() {
	    return new Object[0];
	}

	public Object getControl(String controlType) {
	    return null;
	}

    }
}

