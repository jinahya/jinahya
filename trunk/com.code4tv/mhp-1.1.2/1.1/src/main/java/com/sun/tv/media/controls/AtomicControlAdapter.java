/*
 * @(#)AtomicControlAdapter.java	1.7 98/03/28
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
import java.util.Vector;
import com.sun.tv.media.controls.*;
import java.awt.*;

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * An AtomicControl is one that can be treated as an individual control
 * and can have its own behaviour. It is a base class for other controls.
 */
public class AtomicControlAdapter implements AtomicControl {


    /*************************************************************************
     * VARIABLES
     *************************************************************************/
    
    protected Component component = null;
    private Vector listeners = null;
    protected boolean isdefault = false;
    protected Control parent = null;
    protected boolean enabled = true;

    /*************************************************************************
     * METHODS
     *************************************************************************/

    /**
    * constructs an atomic control adapter.
    *
    * @param c a component the atomic control adapter is associated with
    *
    * @param def specifies whether the control belongs to the default 
    * control panel
    *
    * @param parent parent control
    */
     public AtomicControlAdapter(Component c, boolean def, Control parent) {
	component = c;
	isdefault = def;
	this.parent = parent;
    }
    
    /*************************************************************************
     * IMPLEMENTATION FOR AtomicControl
     *************************************************************************/
    
    /**
     * Returns true if this control is available on the default control
     * panel returned for the player in question.
     */
    public boolean isDefault() {
	return isdefault;
    }

    /**
     * Specify whether this control should be available on the control panel.
     * ???
     * @param visible if true, the control is visible
     */
    public void setVisible(boolean visible) {
	// dummy
    }

    /**
     * Returns true if this control is available on the control panel. ???
     */
    public boolean getVisible() {
	return true;
    }

    /**
     * Set the enabled/disabled state of the control. Can be useful to
     * temporarily gray out a control due to some constraints.
     *
     * @param enabled if true, the control is enabled
     */
    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
	if (component != null)
	    component.setEnabled(enabled);
	informListeners();
    }

    /**
     * Returns the enabled/disabled state of the control.
		 *
     * @return the enabled/disabled state of the control.
		*/
    public boolean getEnabled() {
	return enabled;
    }

    /**
    * sets the parent control for the specified control
    *
    * @param p parent control
    */
    public void setParent(Control p) {
	parent = p;
    }

    /**
     * Returns the control group to which this control belongs, if any. Otherwise
     * it returns null.
     *
     * @return parent control
     */
    public Control getParent() {
	return parent;
    }

    /**
     * Add a listener that should be informed if any state of this control
     * changes.
     *
     * @param ccl listener to be added to the list of registered listeners
     */
    public void addControlChangeListener(ControlChangeListener ccl) {
	if (listeners == null) {
	    listeners = new Vector();
	}
	if (ccl != null) {
	    listeners.addElement(ccl);
	}
    }

    /**
     * Remove an already added listener. Does nothing if the listener was not
     * previously added.
     *
     * @param ccl listener to be removed from the list of registered listeners
     */
    public void removeControlChangeListener(ControlChangeListener ccl) {
	if (listeners != null && ccl != null)
	    listeners.removeElement(ccl);
    }

    /**
    * informs listeners about changes in control's state
    */
    public void informListeners() {
	if (listeners != null) {
	    for (int i = 0; i < listeners.size(); i++) {
		ControlChangeListener ccl =
		    (ControlChangeListener) listeners.elementAt(i);
		ccl.controlChanged( new ControlChangeEvent(this) );
	    }
	}
    }

    /**
     * <B> Sun specific - </B> Returns the description string for this control. 
     */
    public String getTip() {
	return null;
    }
    
    /**
     * <B> Sun specific - </B>
     * Sets the description string for this control. Should be short since it
     * will be displayed as a tool tip when the mouse hovers over the control
     * for a few seconds.
     *
     * @param tip tip string
     */
    public void setTip(String tip) {
	//dummy
    }

    /**
    * returns control component associated with the control adapter
		*
    * @return control component associated with the control adapter
		*/
    public Component getControlComponent() {
	return component;
    }

    /**
    * returns whether the control is read only
    *
    * return true if the control is read-only
    */
    public boolean isReadOnly() {
	return false;
    }
}


