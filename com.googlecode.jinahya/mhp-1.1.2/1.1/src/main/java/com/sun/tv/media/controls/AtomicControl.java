/*
 * @(#)AtomicControl.java	1.4 98/03/28
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


package com.sun.tv.media.controls;

import javax.media.*;
import com.sun.tv.media.*;

/**
* This iterface is a part of the porting layer implementation for JavaTV.
* Some stuff that could be common to all controls. ???
*/
public interface AtomicControl extends Control {

    /**
     * Returns true if this control is available on the default control
     * panel returned for the player in question.
     */
    public boolean isDefault();

    /**
     * Specify whether this control should be available on the control panel.
     * 
     * @param visible if true, the control is visible
     */
    public void setVisible(boolean visible);

    /**
     * Returns true if this control is visible on the control panel. ???
     */
    public boolean getVisible();

    /**
     * Set the enabled/disabled state of the control. Can be useful to
     * temporarily gray out a control due to some constraints.
     *
     * @param enabled if true, the control is enabled
     */
    public void setEnabled(boolean enabled);

    /**
     * Returns the enabled/disabled state of the control.
     */
    public boolean getEnabled();

    /**
     * Returns the control group to which this control belongs, if any. Otherwise
     * it returns null.
     */
    public Control getParent();

    /**
     * Adds a listener that should be informed if any state of this control
     * changes.
     *
     * @param ccl listener to be added
     */
    public void addControlChangeListener(ControlChangeListener ccl);

    /**
     * Remove an already added listener. Does nothing if the listener was not
     * previously added.
     *
     * @param listener to be removed
     */
    public void removeControlChangeListener(ControlChangeListener ccl);

    /**
     * Returns true if the control is a read-only control and no value can
     * be set on it. For example, progress controls that display status
     * information will mostly be read-only.
     */
    public boolean isReadOnly();

    /**
     * <B> Sun specific - </B> Returns the description string for this control. 
     */
    public String getTip();

    /**
     * <B> Sun specific - </B>
     * Sets the description string for this control. Should be short since it
     * will be displayed as a tool tip when the mouse hovers over the control
     * for a few seconds.
     */
    public void setTip(String tip);
}
