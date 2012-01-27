/*
 * @(#)CachingControl.java	1.20 98/03/28
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

package javax.media;

import java.awt.Component;

/**
 * <code>CachingControl</code> is an interface supported by <code>Players</code>
 * that are capable of reporting download progress.
 * Typically, this control is accessed through
 * the <code>Controller.getControls</code> method.
 * 
 * A <code>Controller</code> that supports this control will post
 * <code>CachingControlEvents</code> often enough to support the implementation
 * of custom progress GUIs.
 *
 * @see Controller
 * @see ControllerListener
 * @see CachingControlEvent
 * @see Player
 * @version 1.20, 98/03/28.
*/

public interface CachingControl extends Control {

    /**
     * Use to indicate that the <CODE>CachingControl</CODE> doesn't
     * know how long the content is.<p>
     * The definition is: LENGTH_UNKNOWN == Long.MAX_VALUE
     */
    public final static long LENGTH_UNKNOWN = Long.MAX_VALUE;
    
    /**
     * Check whether or not media is being downloaded.
     *
     * @return  Returns <CODE>true</CODE> if media is being downloaded; 
     * otherwise returns <CODE>false</CODE>.
.
    */
    public boolean isDownloading();

    /**
     * Get the total number of bytes in the media being downloaded. Returns
     * <code>LENGTH_UNKNOWN</code> if this information is not available.
     *
     * @return The media length in bytes, or <code>LENGTH_UNKNOWN</code>.
    */ 
    public long getContentLength();

    /**
     * Get the total number of bytes of media data that have been downloaded so far.
     *
     * @return The number of bytes downloaded.
    */
    public long getContentProgress();

    /**
     * Get a <CODE>Component</CODE> for displaying the download progress.
     *
     * @return Progress bar GUI.
    */
    public Component getProgressBarComponent();

    /**
     * Get a <CODE>Component</CODE> that provides additional download control.
     *
     * Returns <CODE>null</CODE> if only a progress bar is provided. 
     *
     * @return Download control GUI.
    */
    public Component getControlComponent();
}
