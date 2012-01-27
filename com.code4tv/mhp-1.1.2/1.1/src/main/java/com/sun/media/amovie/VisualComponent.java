/*
 * @(#)VisualComponent.java	1.1 99/07/14
 *
 * Copyright 1998-99 by Sun Microsystems, Inc.,
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

import java.awt.*;

public class VisualComponent extends Container {

    AMController amc;
    
    public VisualComponent(AMController amc) {
	this.amc = amc;
    }

    public void addNotify() {
	super.addNotify();
	amc.peerExists = true;
	amc.setOwner( this );
	if (amc.amovie != null) {
	    getPreferredSize();
	    amc.amovie.setWindowPosition(0, 0, amc.outWidth, amc.outHeight);
	}
    }

    public void removeNotify() {
	amc.peerExists = false;
	if (amc.amovie != null) {
	    amc.amovie.setVisible(0);
	    amc.amovie.setOwner(0);
	}
	super.removeNotify();
    }
	
    public Dimension getPreferredSize() {
	if (amc.amovie != null) {
	    int width = amc.amovie.getVideoWidth();
	    int height = amc.amovie.getVideoHeight();
	    return new Dimension(width, height);
	} else {
	    return new Dimension(1, 1);
	}
    }

    public Dimension getMinimumSize() {
	return new Dimension(1, 1);
    }

    public void layout() {
	if (amc.amovie != null) {
	    int w = amc.amovie.getVideoWidth();
	    int h = amc.amovie.getVideoHeight();
	    if (amc.pwidth == -1) {
		amc.pwidth = w;
		amc.pheight = h;
		amc.outWidth = amc.pwidth;
		amc.outHeight = amc.pheight;
		//amc.sendSizeChangeEvent(amc.outWidth, amc.outHeight, 1.0F);
	    }
	    if (amc.peerExists) {
		amc.setOwner( this );
		amc.amovie.setWindowPosition(0, 0, amc.outWidth, amc.outHeight);
	    }
	}
    }
}
