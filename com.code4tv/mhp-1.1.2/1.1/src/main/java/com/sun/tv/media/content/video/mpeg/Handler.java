/*
 * @(#)Handler.java	1.4 99/07/23
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

package com.sun.tv.media.content.video.mpeg;

import javax.media.*;
import javax.media.protocol.*;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.net.*;
import com.sun.tv.media.*;
import com.sun.tv.media.util.*;
/** Tom Nevin import com.sun.tv.media.ui.*; **/
import com.sun.media.amovie.*;
import com.sun.tv.media.controls.*;

/**
 * A MPEG player implementation for windows that runs on top of Active Movie.
 */

public class Handler extends MediaPlayer {

    /*************************************************************************
     * Variables
     *************************************************************************/
    
// TOM NEVIN    private DefaultControlPanel controlPanel = null;

    protected AMController amController = null;

    //private TimeBase masterTimeBase = mixerTimeBase;

    /*************************************************************************
     * Methods
     *************************************************************************/
    
    public Handler() {
	System.out.println("Handler Constructor...");
    }

    public void setSource(DataSource source) throws IOException, IncompatibleSourceException {
	super.setSource(source);
	try {
		if (amController == null) {
			amController = new AMController(this);
			addNode(amController);
/** Tom Nevin		amController.setRegionControl(regionControl); **/
			amController.setSource(source);
		}
	} catch (Exception e) {
	    throw new IncompatibleSourceException("Mpeg handler...");
	}
    }

    protected TimeBase getMasterTimeBase() {
	return amController.getTimeBase();
    }
    
    public void updateStats() {
    }
    
    /**
     * Get the Component this player will output its visual media to.  If
     * this player has no visual component (e.g. audio only)
     * getVisualComponent() will return null.
     *
     * @return the media display component.
     */
    public Component getVisualComponent() {
	if (state == Unrealized || state == Realizing) {
	    throw new javax.media.NotRealizedError("Cannot get visual component from an unrealized player.");
	}
	return amController.getVisualComponent();
    }


    /**
     * Get the object for controlling audio gain. Returns null
     * if this player does not have a GainControl (e.g. no audio).
     *
     * @return the GainControl object for this player.
    */
    public GainControl getGainControl() {
	return super.getGainControl();
    }

    /**
     * Get the Component with the default user interface for controlling
     * this player.
     * If this player has no default control panel null is
     * returned.
     *
     * @return the default control panel GUI.
     */
    public Component getControlPanelComponent() {
	return super.getControlPanelComponent();
    }

    protected boolean createNodes() {
	return true;
    }

    protected boolean connectNodes() {
	// addControls(amController.getControls());
	return true;
    }

    protected void createGainControl() {
	// called by MediaPlayer.doRealize()
    }

    protected synchronized boolean doRealize() {
	return super.doRealize();
    }
    
    public void doClose() {
	super.doClose();
	amController = null;
    }

    public boolean audioEnabled() {
	return amController.audioEnabled();
    }

    public boolean videoEnabled() {
	return amController.videoEnabled();
    }

    public void processEvent(ControllerEvent evt) {
	super.processEvent(evt);
    }

}
