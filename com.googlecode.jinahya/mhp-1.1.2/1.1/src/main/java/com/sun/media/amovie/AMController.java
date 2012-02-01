/*
 * @(#)AMController.java	1.12 00/09/19
 *
 * Copyright 1996-1999 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.media.amovie;

import javax.media.*;
import javax.media.protocol.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
/**
 ** import javax.media.Format;
 ** import javax.media.format.AudioFormat;
 ** import javax.media.format.VideoFormat;
 ** import javax.media.control.FormatControl;
 **/
import com.sun.tv.media.*;
import com.sun.tv.media.util.LoopThread;
import com.sun.tv.media.util.MediaThread;
import com.sun.tv.media.util.JMFSecurity;

/** Tom Nevin
 ** import com.sun.tv.media.util.jdk12;
 ** import com.sun.tv.media.ui.*;
 **/
import com.sun.tv.media.controls.*;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

/** Tom Nevin
 ** import com.sun.tv.media.JMFSecurityManager;
 ** import com.ms.security.PermissionID;
 ** import com.ms.security.PolicyEngine;
 **/


/**
 * Controller for Active Movie based MPEG player.
 */
public class AMController extends MediaController {

    /*************************************************************************
     * Constants
     *************************************************************************/
    
    final int MINVOLUME = -10000;

    /*************************************************************************
     * Variables
     *************************************************************************/

    // ActiveMovie wrapper
    ActiveMovie amovie = null;

    protected static final int TRYSET_DONE    = 0;
    protected static final int TRYSET_CANT    = 1;
    protected static final int TRYSET_PASTEOM = 2;

    private Integer closeLock = new Integer(0);
    private Integer sourceLock = new Integer(1);
    private boolean closed = false;
    private TimeBase amTimeBase = null;
    
    private int appletWindowHandle = 0;
    int pwidth = -1;
    int pheight = -1;
    int outWidth;
    int outHeight;
    private int width;
    private int height;
    boolean peerExists = false;
    private boolean muted = false;

    private boolean outputSizeSet = false;
    
    private EventThread eventThread = null;
    //private ProgressThread progressThread = null;
    
    private boolean isFileStream = false;
    private boolean isRandomAccess = false;
    private boolean isSeekable = false;
    private boolean hasAudio = false;
    private boolean hasVideo = false;
    private boolean deallocated = false;
    private boolean sourceIsOn = false;
    private javax.media.protocol.DataSource source = null;
    private PullSourceStream stream = null;
    private SourceStream originalStream = null;
    private String mpegFile = null;
    private com.sun.tv.media.content.video.mpeg.Handler player;
    private Component visualComponent = null;
    //private VSC vsc = null;
    private boolean seekFailed = false;
    private Time timeWhenMediaStopped = null;
    private boolean mediaTimeChanged = false;
    private Time requestedMediaTime = new Time(0);
    private Time lastMediaTime = new Time(0);

/**
 **    private VideoFormat videoFormat = null;
 **    private AudioFormat audioFormat = null;
 **/
    
    private String id = "JavaActiveMovie_" + hashCode();

    // Caching related

    //private MediaCachingControl mcc = null;
    //private CachingInputStream cis = null;
    private int contentLength = -1;
    private boolean doneDownloading = false;
    //private NewRFCachingInputStream nis = null;
    private boolean blockedOnRead = false;
    private Thread blockThread = null;
    private boolean abortingRealize = false;

    private boolean setRatePending = false;
    private float   setRateValue = (float) 1.0;
    
    private GainControl gc = null;
    private Control [] controls = null;

/**
 **    private FormatControl audioControl = null;
 **    private FormatControl videoControl = null;
 **/

    private static boolean libraryLoaded = false;
    private static JMFSecurity jmfSecurity = null;
    private static boolean securityPrivelege=false;
    private Method m[] = new Method[1];
    private Class cl[] = new Class[1];
    private Object args[][] = new Object[1][0];

/** Tom Nevin
 **    static {
 **	try {
 **	    jmfSecurity = JMFSecurityManager.getJMFSecurity();
 **	    securityPrivelege = true;
 **	} catch (SecurityException e) {
 **	}
 **   }
 **/

    /*************************************************************************
     * Methods
     *************************************************************************/

    public AMController(com.sun.tv.media.content.video.mpeg.Handler handler) {
	String library = "jmam";
	if (!libraryLoaded) {
	    try {
		JMFSecurity.loadLibrary(library); // Tom Nevin
		libraryLoaded = true;
	    } catch (Throwable t) {
		throw new RuntimeException("Unable to load native MPEG library");
	    }

	}
	player = handler;
    }

    public void setSource(javax.media.protocol.DataSource s)
	throws IncompatibleSourceException {

	if (s instanceof PullDataSource)
	    source = s;
	else
	    throw new IncompatibleSourceException("MPEG Controller requires a PullDataSource");
    }

    public void setTimeBase(TimeBase tb) throws IncompatibleTimeBaseException {
	super.setTimeBase(tb);

	// We can't support any other time base
	// if (tb != null && tb != amTimeBase)
	//    throw new IncompatibleTimeBaseException("the mpeg handler cannot handle the given timebase.");
    }

    public TimeBase getTimeBase() {
	if (amTimeBase == null)
	    amTimeBase = new AMTimeBase( this );
	return amTimeBase;
    }

    public boolean isConfigurable() {
	return false;
    }

    private void updateControls(ActiveMovie amovie) {
/**
 **	if (hasAudio) {
 **	    audioFormat = new AudioFormat(AudioFormat.MPEG);
 **	}
 **	if (hasVideo) {
 **	    Dimension size = new Dimension(amovie.getVideoWidth(),
 **					   amovie.getVideoHeight());
 **	    videoFormat = new VideoFormat(VideoFormat.MPEG,
 **					  size,
 **					  Format.NOT_SPECIFIED,
 **					  Format.byteArray,
 **					  Format.NOT_SPECIFIED);
 **	}
 **/
    }

    public Control [] getControls() {
	int n = 0;
	if (hasAudio) n++;
/**
 **	if (audioFormat != null) n++;
 **	if (videoFormat != null) n++;
 **/
	controls = new Control[n];
	n = 0;
	if (hasAudio) {
	    if (gc == null) {
		gc = new GCA();
	    }
	    controls[n++] = gc;
	}

/** Tom Nevin
 **	if (audioFormat != null) {
 **	    if (audioControl == null) {
 **		audioControl = new FormatAdapter(audioFormat,
 **						 new Format[] {audioFormat},
 **						 true,
 **						 false,
 **						 false);
 **	    }
 **	    controls[n++] = audioControl;
 **	}
 **	if (videoFormat != null) {
 **	    if (videoControl == null) {
 **		videoControl = new FormatAdapter(videoFormat,
 **						 new Format[] {videoFormat},
 **						 true,
 **						 false,
 **						 false);
 **	    }
 **	    controls[n++] = videoControl;
 **	}
 **/
	return controls;
    }
    
    private ActiveMovie createActiveMovie(javax.media.protocol.DataSource s) {
	URL url = null;
	MediaLocator ml = s.getLocator();
	
	if (ml != null) {
	    try {
		url = ml.getURL();
	    } catch (MalformedURLException e) {
		ml = null;	      // It's possible that its not a URL
				      // Bug reported after beta3.
	    }
	}
	
	if (ml != null && ml.getProtocol().equals("file")) {
	    int indexPipe;
	    
	    mpegFile = url.getFile();

	    if (mpegFile.startsWith("/"))
		mpegFile = mpegFile.substring(1); // Remove leading /
	    
	    // Strip off pipe symbols (workaround for Netscape's getURL())
	    // and replace them with ":"
	    // Hopefully there are no other weird symbols that Netscape adds.
	    while ((indexPipe = mpegFile.indexOf("|")) >= 0) {
		if (indexPipe > 0) {
		    mpegFile = mpegFile.substring(0, indexPipe) + ":" +
			       mpegFile.substring(indexPipe + 1);
		} else {
		    mpegFile = mpegFile.substring(1);
		}
	    }

            isFileStream = true;
	    isRandomAccess = true;
	    isSeekable = true;
            ActiveMovie am = new ActiveMovie(this, mpegFile);
	    hasVideo = am.hasVideo();
	    hasAudio = am.hasAudio();
	    updateControls(am);
	    return am;
	} else {
	    // Its a data source other than file. Lets open a stream
	    if (s instanceof PullDataSource) {
		
		PullSourceStream [] streams = 
		    (PullSourceStream []) ((PullDataSource)s).getStreams();
		if (streams != null && streams.length > 0) {
		    stream = streams[0];
		    originalStream = stream;
		    if (stream instanceof Seekable) {
			isSeekable = true;
			// ((Seekable)stream).seek(0);
			// Is it a random access stream?
			if (((Seekable)stream).isRandomAccess())
			    isRandomAccess = true;
			if (ml != null && ml.getProtocol().startsWith("http") &&
			    url != null)
			    isRandomAccess = false;
		    }
		    ActiveMovie am = new ActiveMovie(this, (PullSourceStream)stream,
						     isRandomAccess,
						     (int) originalStream.getContentLength());
		    hasVideo = am.hasVideo();
		    hasAudio = am.hasAudio();
		    updateControls(am);
		    return am;
		}
	    } else
		System.err.println("Mpeg Player can only handle pull data sources.");
	    
	    return null;
	}
    }

    /****************************************************************
     * Stream related calls made from native code
     ****************************************************************/
    
    public int canRead(int bytes) {
	return bytes;
    }

    public long canSeek(long seekTo) {
	// TODO: Pause the controller if seek will block
	return seekTo;
    }

    public long seek(long seekTo) {
	if (abortingRealize) {
	    System.out.println("seek.0");
	    return 0;
	}

	synchronized (sourceLock) {
	    if (stream instanceof Seekable && sourceIsOn) {
		//System.err.println("seek.1");
		
		// Don't seek if already there.
		if (((Seekable)stream).tell() == seekTo)
		    return seekTo;
		
		if (((Seekable)stream).isRandomAccess()) {
		    long seeked = ((Seekable)stream).seek(seekTo);
		    return seeked;
		} else if (seekTo == 0)
		    return ((Seekable)stream).seek(seekTo);
	    }
	}
	//System.err.println("seek.2");
	return 0;
    }

    public int read(byte [] data, int offset, int length) {
	if (abortingRealize)
	    return -1;
	synchronized (sourceLock) {
	    if (stream != null && sourceIsOn) {
		try {
		    int readBytes = stream.read(data, offset, length);
		    return readBytes;
		} catch (IOException ioe) {
		    sendEvent(new ConnectionErrorEvent(this, ioe.getMessage()));
		    return -1;
		}
	    } else
		return -1;
	}
    }
    
    protected boolean doRealize() {
	abortingRealize = false;

	if (amovie != null) {
	    amTimeBase = null;
	    amovie.kill();
	    amovie.dispose();
	    amovie = null;
	}

	startSource(true, true);

	synchronized (closeLock) {
	
	    amovie = createActiveMovie(source);
	    if (amovie == null)
		return false;
	
	    if (!amovie.isRealized()) {
		amovie = null;
		return false;
	    }

	    if (abortingRealize) {
		doDeallocate();
		amovie.dispose();
		amovie = null;
		return false;
	    }
	}

	amovie.amStopWhenReady();
	amovie.doneRealize();
	if (amTimeBase == null)
	    amTimeBase = new AMTimeBase( this );
	setMediaLength((long)(amovie.getDuration() * 1e9));
	amovie.setVisible(0);

	startSource(false, false);
	return true;
    }

    protected void abortRealize() {
	// TODO: Abort downloading if caching is enabled
	// System.err.println("AMController.abortRealize()");
	abortingRealize = true;
	startSource(false, true);
    }

    protected void abortPrefetch() {
	// System.err.println("AMController.abortPrefetch()");
	startSource(false, true);
    }

    protected boolean doPrefetch() {
	if (amovie == null)
	    if (!doRealize())
		return false;

	// If activemovie was recreated, reattach the AM window to java panel
	if (amovie != null && visualComponent != null && peerExists) {
	    setOwner(visualComponent);
	}
	// Restart the download thread if it was paused
	return true;
    }

    
    // Called from a separate thread called TimedStart thread.
    protected final void doStart() {
System.out.println("doStart() = " + getState());
	GainControl gc;
	if (amovie == null)
	    doPrefetch();

	startSource(true, false);
	amovie.restart();

	if (setRatePending) {
	    amovie.setRate(setRateValue);
	    setRatePending = false;
	    if ((float) amovie.getRate() != setRateValue) {
		sendEvent(new RateChangeEvent(this, (float) amovie.getRate()));
	    }
	}
	
	// Restart the download thread.
	if (mediaTimeChanged) {
	    int returnVal = trySetMediaTime(requestedMediaTime);
	    if (returnVal == TRYSET_CANT) {
		// Couldn't set the media time.
		super.stop();
		sendEvent((StopEvent) new SeekFailedEvent(this, Started,
							  Prefetched,
							  getTargetState(),
							  getMediaTime()));
		return;
	    } else if (returnVal == TRYSET_PASTEOM) {
		// Seeking beyond EOM
		super.stop();
		sendEvent(new EndOfMediaEvent(this, Started, Prefetched,
					      getTargetState(), getMediaTime()));
		return;
	    }
	}
	// We've succeeded in setting the media time.
	mediaTimeChanged = false;
	
	if ((gc = player.getGainControl()) != null)
	    amovie.setVolume((int)(gc.getDB() * 100));
	amovie.amRun();
	if (!peerExists)
	    amovie.setVisible(0);
	if (gc != null)
	    muteChange(gc.getMute());	
	if (eventThread == null) {
        	eventThread = new EventThread();
		eventThread.setController(this);
		eventThread.start();
	}
	eventThread.restart();
    }

    public void doStop() {
System.err.println("In doStop()" + eventThread);
	super.doStop();
	lastMediaTime = getMediaTime();
        if (amovie != null) {
	    amovie.amPause();
	    amovie.pause();
        }
	if (eventThread != null)
	    eventThread.pause();
	startSource(false, false);
	sendEvent((StopEvent)new StopByRequestEvent(this, Started,
						    Prefetched,
						    getTargetState(),
						    getMediaTime()));
    }

    protected synchronized void doDeallocate() {
	// Stop the source
	startSource(false, false);
	
	// Restart from Time(0).
	timeWhenMediaStopped = getMediaTime();
	mediaTimeChanged = true;
	requestedMediaTime = new Time(0);
	lastMediaTime = timeWhenMediaStopped;

	// Kill all threads and ActiveMovie
	if (amovie != null) {
	    blockedOnRead = false;
 	    if (amovie.getVolume() == MINVOLUME)  // Is Mute on?
	        amovie.setVolume(MINVOLUME / 2); // TURN MUTE OFF
	    amovie.kill();

	    if (eventThread != null) {
	        eventThread.kill();
	        eventThread = null;
	    }
        }
    }

    public void finalize() {
	if (amovie != null)
	    doClose();
    }

    protected void doClose() {

	if (getState() == Controller.Realizing)
	    abortRealize();
	
	synchronized (closeLock) {
	    // Do nothing if already closed.
	    if (closed)
		return;
	    
	    // Stop all the threads and active movie.
	    doDeallocate();
	    
	    // Kill active movie
	    if (amovie != null) {
		amovie.dispose();
		amTimeBase = null;
		amovie = null;
	    }
	    // Disconnect the data source
	    if (source != null) {
		source.disconnect();
	    }
	    source = null;
	    closed = true; // Dont come back!
	}
    }

    public void setMediaTime(Time time) {
	super.setMediaTime(time);
	synchronized (this) {
	    requestedMediaTime = time;
	    mediaTimeChanged = true;
	}

	if (stream == null) {
	    amovie.restart();
	    if (trySetMediaTime(time) == TRYSET_DONE) 
		mediaTimeChanged = false;
	}
    }
    
    protected int trySetMediaTime(Time time) {
	if (amovie != null) {
	    long duration = getDuration().getNanoseconds();
	    long now = time.getNanoseconds();

	    // Dont know the duration and not seeking to zero?
	    if (getDuration() == DURATION_UNKNOWN && now != 0)
		return TRYSET_CANT;

	    // Seeking beyond duration?
	    if (now < 0)
		return TRYSET_CANT;
	    if (now > duration)
		return TRYSET_PASTEOM;

	    if (!isSeekable)
		return TRYSET_CANT;
	    
	    double nowSeconds = (double) now * 1e-9;
	    
	    if (isRandomAccess) {
		amovie.setCurrentPosition(nowSeconds);
		return TRYSET_DONE;
	    } else if (stream != null) {
		if (now != 0) {
		    return TRYSET_CANT;
		}
		// Seeking to zero should be ok.
		((Seekable)stream).seek(0);
		amovie.setCurrentPosition( 0 );
		return TRYSET_DONE;
	    }
	}
	return TRYSET_CANT;
    }

    public float doSetRate(float factor) {
	if (amovie == null)
	    return 1.0F;
	if (factor < 0.1)
	    factor = 0.1f;
	if (factor > 10.0)
	    factor = 10.0f;
	if ((float) amovie.getRate()  != factor) {
	    setRatePending = true;
	    setRateValue = factor;
	}

	return factor;
    }

    Component createVisualComponent() {
	Component c = null;
	Class visclass = null;

/** Tom Nevin	
 **	    try {
 **		visclass = Class.forName("com.sun.media.amovie.MSVisualComponent");
 **	    } catch (Throwable t) {
 **	    }
 **/

	if (visclass == null) {
	    try {
		visclass = Class.forName("com.sun.media.amovie.VisualComponent");
	    } catch (Throwable th) {
		return null;
	    }
System.out.println("VisualClass = " + visclass);
	}

	Class params [] = { AMController.class };
	Constructor cons = null;
	try {
	    cons = visclass.getConstructor(params);
	    Object [] amparam = new AMController[1];
	    amparam[0] = this;
	    c = (Component) cons.newInstance(amparam);
	    return c;
	} catch (Throwable tr) {
	}
	return null;
    }
    
    public Component getVisualComponent() {
	if (amovie == null)
	    return null;
	if (visualComponent == null) {
	    if (amovie.getVideoWidth() == 0 ||
		amovie.getVideoHeight() == 0)
		visualComponent = null;
	    else {
		visualComponent = createVisualComponent();

		// Component resize listener
		visualComponent.addComponentListener(new ComponentAdapter() {
		    private int lastWidth = -1;
		    private int lastHeight = -1;
		    
		    public void componentResized(ComponentEvent ce) {
			if (amovie != null) {
			    Dimension csize = ce.getComponent().getSize();
			    if (csize.width == lastWidth &&
				csize.height == lastHeight)
				return;
			    lastWidth = csize.width;
			    lastHeight = csize.height;
			    outputSizeSet = true;
			    zoom(lastWidth, lastHeight);
			}
		    }
		} );
		// End resize listener
	    }
	}
	return visualComponent;
    }

    protected Time eomDuration = DURATION_UNKNOWN;
    
    public Time getDuration() {
	// If we've hit the end of media once, use that duration.
	if (eomDuration != DURATION_UNKNOWN)
	    return eomDuration;

	if (amovie == null)
	    return Duration.DURATION_UNKNOWN;
	else {
	    double amduration = amovie.getDuration(); // Get the duration in secs
	    if (isRandomAccess)
		return new Time((long) (amduration * 1E+9)); // To nanoseconds
	    else
		return DURATION_UNKNOWN;
	}
    }

    /**
     * Sets the parent for the activemovie window.
     */
    void setOwner(Component parent) {
	if (amovie != null && parent != null) {

/** Tom Nevin
 **	    appletWindowHandle = com.sun.tv.media.util.WindowUtil.getWindowHandle(parent);
 **	    if (appletWindowHandle == 0) {
 **		throw new NullPointerException("null peer");
 **	    }
 **	    amovie.setOwner(appletWindowHandle);
 **/
	    amovie.setVisible(1);
	    parent.getPreferredSize();
	    amovie.setWindowPosition(0, 0, outWidth, outHeight);
	}
    }

    void sendEOM() {
	if (amovie != null) {
	    amovie.amPause();
	    amovie.pause();
	}
	super.stop();
	Time earlier = eomDuration;
	eomDuration = new Time(getMediaTime().getNanoseconds());
	startSource(false, false);
	sendEvent(new EndOfMediaEvent(this, Started, Prefetched,
				      getTargetState(), getMediaTime()));
	if (earlier == DURATION_UNKNOWN)
	    sendEvent(new DurationUpdateEvent(this, eomDuration));

System.out.println("stream = " + stream);

/** Tom Nevin
 **	if (stream instanceof CachedStream && isRandomAccess == false) {
 **	    isRandomAccess = true;
 **	    if (amovie != null)
 **		amovie.setSeekable(true);
 **	}
 **/
    }

    public boolean startSource(boolean on, boolean regardless) {
	if (sourceIsOn == on)
	    return true;
	synchronized (sourceLock) {
	    if (regardless /* || mcc == null*/ ) {
		try {
		    if (on) {
			source.start();
			if (amovie != null)
			    amovie.stopDataFlow(false);
		    } else {
			if (amovie != null)
			    amovie.stopDataFlow(true);
			source.stop();
		    }
		} catch (Exception ge) {
		    // System.err.println("Couldn't stop the data source");
		    return false;
		}
		sourceIsOn = on;
	    }
	    return true;
	}
    }

    private void zoomChanged() {
       if (amovie == null)
	 return;
       int width = amovie.getVideoWidth();
       int height = amovie.getVideoHeight();
       if (peerExists)
	   amovie.setWindowPosition(0, 0, outWidth, outHeight);
       if (pwidth != width || pheight != height) {
	   pwidth = width;
	   pheight = height;
	   //sendSizeChangeEvent(pwidth, pheight, 1.0F);
       }
    }

    private void zoom(int width, int height) {
	outWidth = width;
	if (outWidth < 120)
	    outWidth = 120;
	outHeight = height;
	if (outHeight < 1)
	    outHeight = 1;
	zoomChanged();
    }
    
    public boolean audioEnabled() {
	if (amovie != null) {
	    return amovie.hasAudio();
	} else
	    return true;
    }

    public boolean videoEnabled() {
	if (amovie != null)
	    return amovie.hasVideo();
	else
	    return true;
    }

    public void gainChange(float g) {
       	if (amovie != null && !muted && gc != null) {
	    float dB = gc.getDB();
	    if (dB > 0.0f) dB = 0.0f;
	    if (dB < -70f) dB = -100f; // silence is -10000 for active movie
	    amovie.setVolume( (int)(dB * 100));
	}
    }

    public void muteChange(boolean state) {
	if (amovie != null) {
	    if (state) {
		muted = true;
		amovie.setVolume(MINVOLUME);
	    } else {
		muted = false;
		try {
		    float dB = gc.getDB();
		    if (dB > 0) dB = 0;
		    if (dB < -70f) dB = -100f;
		    amovie.setVolume( (int)(dB * 100) );
		} catch (Exception e) {
		}
	    }
	}
    }

    /*************************************************************************
     * INNER CLASSES
     *************************************************************************/

    class AMTimeBase extends MediaTimeBase {

	private AMController controller;
	
	public AMTimeBase(AMController controller) {
	    this.controller = controller;
	}

	public long getNanoseconds() {
	    return getMediaTime();
	}
	
	public long getMediaTime() {
	    long time = 0;
	    if (controller.amovie != null)
		time = controller.amovie.getTime() * 1000;
	    return time;
	}
    }
    
    class GCA extends GainControlAdapter {

        GCA() {
	    super(false);
	    setDB(1.0f);
	}

	public void setMute(boolean mute) {
	    super.setMute(mute);
	    muteChange(mute);
	}

	public float setLevel(float g) {
	    float level = super.setLevel(g);
	    gainChange(g);
	    return level;
	}
    }

    class ProgressThread extends MediaThread {

	private com.sun.tv.media.content.video.mpeg.Handler mp;
	
	ProgressThread(com.sun.tv.media.content.video.mpeg.Handler player) {
	    mp = player;
	    setName("JMF AMController Progress Thread: " + getName());
	}

	public void start() {
	    if (!isAlive())
		super.start();
	}

	public void tryStop() {
//	    if (isAlive())
//		super.stop();
	}

	public void run() {
	}
    }
}

/**
 * This class used to be an inner class, which is the correct thing to do.
 * Changed it to a package private class because of jdk1.2 security.
 * For jdk1.2 and above applets, EventThread is created in a
 * privileged block using jdk12CreateThreadAction. jdk12CreateThreadAction
 * class is unable to create and instantiate an inner class 
 * in AMController class
 */
class EventThread extends LoopThread {
    
    private AMController amController;
    EventThread() {
	setName("JMF AMController Event Thread: " + getName());
	// 	    start();
    }
    
    void setController(AMController c) {
	amController = c;
    }
    
    public boolean process() {
	if (amController.amovie == null)
	    return false;
	boolean result = amController.amovie.waitForCompletion();
	if (result) {
	    // System.err.println("Got EOM!!!!!!");
	    amController.sendEOM();
	    pause();
	}
	try {
	    sleep(100);
	} catch (InterruptedException ie) {
	}
	return true;
    }
}

