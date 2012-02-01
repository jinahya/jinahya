/*
 * @(#)AWTVideoSizeControlImpl.java	1.10 00/04/03
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
 */

package com.sun.tv.media;

import java.awt.*;
import javax.tv.media.*;


/**
 * An interface that allows setting clipping, scaling, and translation
 * of a video stream in a simple, interoperable way.  Not all possible
 * combinations of positioning will be supported, so this interface
 * provides a mechanism to discover how closely the underlying platform
 * will approximate a request for positioning.
 * <p>
 * All interactions via AWTVideoSizeControlImpl happen in the coordinate space
 * of the screen.  For example, successfully setting the
 * video's position to the location reported by getLocationOnScreen() on the
 * xlet's root container will cause the upper-left hand corner of the
 * video and the root container to coincide.
 * <p>
 * The screen, in the context of AWT, is the area into which graphics
 * drawing operations are done.  Its size is given by
 * java.awt.Toolkit.getScreenSize(), and locations reported by
 * Component.getLocationOnScreen() are given in the screen's coordinate
 * system.
 * 
 * 
 * @version     1.10, 04/03/00
 *
 * @see javax.tv.media.AWTVideoSize
 */

public class AWTVideoSizeControlImpl implements AWTVideoSizeControl {

	private final static Dimension defSize = new Dimension(500, 500);
	private final static Rectangle rect = new Rectangle(0, 0, 500, 500);

	private AWTVideoSize size = new AWTVideoSize(rect, rect);
	private AWTVideoSize defaultSize = new AWTVideoSize(rect, rect);

    /**
     * Get the AWTVideoSize at which the Player is currently operating.
     *
     * @return a copy of the JMF Player's current video size, in the AWT
     *		coordinate space.
     */
    public AWTVideoSize getSize() {
	return size;
    }

    /**
     * Get the default AWTVideoSize for this control.  For the background
     * video plane, this will be the size that the video would be presented
     * at if no program had manipulated the video size.
     */
    public AWTVideoSize getDefaultSize() {
	return defaultSize;
    }

    /**
     * Get the size of the source video, in the screen's coordinat system.
     */
    public Dimension getSourceVideoSize() {
	return defSize;
    }

    /**
     * Set the video size.  If the size provided cannot be supported by the
     * underlying platform, do nothing and return false.
     *
     * @param sz The desired video size, in the AWT coordinate space.
     *
     * @return true if the size was successfully changed, false if the platform
     *		is incapable of supporting the given size.
     *
     * @see #checkSize(AWTVideoSize)
     */
    public boolean setSize(AWTVideoSize sz) {
        if ( sz == null ) { 	 
           throw new NullPointerException("AWTVideoSize null");
        }
 	
	this.size = sz;
	return true;
    }

    /**
     * Find out how closely the underlying platform can approximate a desired
     * video size.  If the underlying platform cannot support the given size,
     * this method gives the closest approximation the platform is capable
     * of.
     *
     * @param sz The desired video size.
     *
     * @return The actual size that the platform would be able to set.
     */
    public AWTVideoSize checkSize(AWTVideoSize sz) {
        if ( sz == null ) { 	 
           throw new NullPointerException("AWTVideoSize null");
        }
	return sz;
    }

    /**
     * Get the <code>Component</code> associated with this
     * <code>Control</code> object.
     * For example, this method might return
     * a slider for volume control or a panel containing radio buttons for 
     * CODEC control.
     * The <code>getControlComponent</code> method can return
     * <CODE>null</CODE> if there is no GUI control for
     * this <code>Control</code>.
     */
    public Component getControlComponent() {
	return null;
    }
}
