/*
 * @(#)MediaThread.java	1.6 98/10/20
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

package com.sun.tv.media.util;

//A.D.import com.sun.tv.media.BuildInfo;


/**
 * This class is a part of the porting layer implementation for JavaTV.
 * A thread class where all JMF created threads should based on.
 */
public class MediaThread extends Thread {

    static protected MediaThreadGroup mediaThreadGroup;

    static {
	    try {
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadArgs);
		JMFSecurity.enablePrivilege.invoke(JMFSecurity.privilegeManager,
						   JMFSecurity.threadGroupArgs);
	    } catch (Exception e) {}

/*A.D.	
	if (BuildInfo.usePureJava())
	    mediaThreadGroup = null;
	else
	A.D.*/
	    mediaThreadGroup = new MediaThreadGroup();
    }

    public MediaThread() {
	this("JMF thread");
    }

    public MediaThread(String name) {
//A.D.	super(BuildInfo.usePureJava() ? null : mediaThreadGroup, name);
			super(mediaThreadGroup, name); //A.D.
    }

    public MediaThread(Runnable r) {
	this(r, "JMF thread");
    }

    public MediaThread(Runnable r, String name) {
//A.D.	super(BuildInfo.usePureJava() ? null : mediaThreadGroup, r,  name);
			super(mediaThreadGroup, r,  name); //A.D.
    }

    static public MediaThreadGroup getMediaThreadGroup() {
	return mediaThreadGroup;
    }

    /**
     * This should be used for Manager, events threads etc. -- the mechanism
     * to maintain the players.
     */
    public void useControlPriority() {
//A.D.	if (!BuildInfo.usePureJava())
	    setPriority(mediaThreadGroup.getControlPriority());
	// System.err.println("set thread priority: " + getName() + " : " + getPriority());
    }

    /**
     * This should be used for threads handling the audio medium.
     */
    public void useAudioPriority() {
//A.D.	if (!BuildInfo.usePureJava())
	    setPriority(mediaThreadGroup.getAudioPriority());
	// System.err.println("set thread priority: " + getName() + " : " + getPriority());
    }

    /**
     * This should be used for threads handling the video medium.
     */
    public void useVideoPriority() {
//A.D.	if (!BuildInfo.usePureJava())
	    setPriority(mediaThreadGroup.getVideoPriority());
	// System.err.println("set thread priority: " + getName() + " : " + getPriority());
    }

    /**
     * This should be used for threads handling network packets. e.g. RTP
     */
    public void useNetworkPriority() {
//A.D.	if (!BuildInfo.usePureJava())
	    setPriority(mediaThreadGroup.getNetworkPriority());
	// System.err.println("set thread priority: " + getName() + " : " + getPriority());
    }
}

