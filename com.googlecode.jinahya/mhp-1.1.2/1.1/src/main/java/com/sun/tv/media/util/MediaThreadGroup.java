/*
 * @(#)MediaThreadGroup.java	1.7 98/06/16
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

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * A thread group class where all JMF threads are based on.
 */
public class MediaThreadGroup extends ThreadGroup {

    private int controlPriority;
    private int audioPriority;
    private int videoPriority;
    private int networkPriority;

    MediaThreadGroup() {
	super(MediaThreadGroup.getRootThreadGroup(), "JMF thread group");
	setMaxPriority(Thread.MAX_PRIORITY);

	controlPriority = getMaxPriority() - 1;

	// 	videoPriority = Thread.MIN_PRIORITY + 1;
	// 	audioPriority = videoPriority + 1;
	// 	networkPriority = audioPriority + 1;

	audioPriority = Thread.MAX_PRIORITY - 5;
	videoPriority = Thread.NORM_PRIORITY - 2;  /* To be less than the Appletpriority */
	networkPriority = audioPriority + 1;

    }

    /**
     * Recursively traverse up the thread group tree to find the root.
     * This will allow us to set the priority to the max possible.
     */
    static private ThreadGroup getRootThreadGroup() {
	ThreadGroup g = Thread.currentThread().getThreadGroup();
	for (; g.getParent() != null; g = g.getParent());
	return g;
    }

    /**
     * This should be used for Manager, events threads etc. -- the mechanism
     * to maintain the players.
     */
    public int getControlPriority() {
	return controlPriority;
    }

    /**
     * This should be used for threads handling the audio medium.
     */
    public int getAudioPriority() {
	return audioPriority;
    }

    /**
     * This should be used for threads handling the video medium.
     */
    public int getVideoPriority() {
	return videoPriority;
    }

    /**
     * This should be used for threads handling network packets. e.g. RTP
     */
    public int getNetworkPriority() {
	return networkPriority;
    }

}
