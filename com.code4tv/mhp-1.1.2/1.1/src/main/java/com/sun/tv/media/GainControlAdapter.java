/*
 * @(#)GainControlAdapter.java	1.13 98/09/14
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


package com.sun.tv.media;

import javax.media.*;
import java.util.*;
//A.D.import com.sun.tv.media.ui.*;
import com.sun.tv.media.controls.*;
import java.awt.*;

/**
* This class is a part of the porting layer implementation for JavaTV
* GainControlAdapter implements javax.media.GainControl. 
* GainControlAdapter provides methods for
* 1. manipulating the amplitude of the audio signal and performing 
* math required to map linear gain specifiers to decibels.
* The relationship between a linear gain multiplier and the 
* gain specified in decibels is: value = pow(10.0, gainDB/20.0)
* 2. registering, unregistering and informing registered listeners
* about changes in gain value of the audio signal by posting a 
* GainChangeEvent
* 3. manipulating the muted state of the audio signal.
*/
public class GainControlAdapter implements GainControl {
    
    Vector listeners = null;
    boolean muteState;
    //A.D.Component component;
  //A.D.  NumericControl volumeControl;

    // JavaSound has level valued from 0.0 to 5.0.
    // 1.0 is the default for JS; mapped to 1.0db.
    // GainControl has level valued from 0.0 to 1.0.
    // So we have:
    // JS level == 1; GC level == 0.2; Gain == 0.0db.
    float dB = 0f;
    float level = DefLevel;
    static float DefLevel = 0.2f;

    /**
    * constructs a GainControlAdapter object. 
    * The gain initially is set to a default value of 0.2f using floating
    * point scale.
    * @param mute specifies whether audio signal is initially muted (true)
    * or unmuted (false)
    */
    public GainControlAdapter(boolean mute) {
	this.muteState = mute;
	setLevel(DefLevel);
	
    }

    /**
    * sets the muted state of the audio signal.
    * @param mute specifies whether audio is set to be muted (true) 
    * or unmuted (false)
    */
    public void setMute(boolean mute) {
	if (muteState != mute) {
	    muteState = mute;
	    informListeners();
	}
    }

    /**
    * retrieves the muted state of the audio signal.
    * @return muted state
    */
    public boolean getMute() {
	return muteState;
    }

    /**
    * sets the gain in decibels. 
    * @param gain if positive, the audio signal is amplified from
    * the default level; if gain is negative, the audio signal is 
    * attenuated from the default; if gain is 0, the audio signal 
    * is set to the default level
    * return the gain in dB.
    */
    public float setDB(float gain) {
	if (dB != gain) {
	    dB = gain;
	    float mult = (float) Math.pow(10.0, dB/20.0);
	    level = mult * DefLevel;
	    if (level < 0.0)
		setLevel(0.0f);
	    else if (level > 1.0)
		setLevel(1.0f);
	    else {  
		informListeners();
		/*A.D.
		if (volumeControl != null)
		    ((AtomicControlAdapter)volumeControl).informListeners();
		    A.D.*/
	    }
	}
	return dB;
    }

   /**
    * returns the current gain in dB.
    * @return The gain in dB.
    */
   public float getDB() {
		return dB;
    }

    
      /**
     * Set the gain using a floating point scale with values 
     * between 0.0 and 1.0.
     * 0.0 is silence; 1.0 is the loudest. Informs listeners about 
     * gain change
     * @param level The new gain value specified in the level scale.
     * @return The level that was actually set.
     */
  public float setLevel(float level) {
	if (level < 0.0)
	    level = 0.0f;
	if (level > 1.0)
	    level = 1.0f;
	if (this.level != level) {
	    this.level = level;
	    float mult = level/DefLevel;
	    dB = (float) (Math.log((double)((mult==0.0)?0.0001:mult))/Math.log(10.0) * 20.0);
	    informListeners();
	    /*A.D.
	    if (volumeControl != null)
		((AtomicControlAdapter)volumeControl).informListeners();
		A.D.*/
	}
	return this.level;
    }

   /**
   * Get the current gain as a value between 0.0 and 1.0 
   *
   * @return The gain in the level scale (0.0-1.0).
	 */
  public float getLevel() {
	return level;
    }

    /**
     * Registers a listener for gain change update events.
     * A GainChangeEvent is posted when the gain for the 
     * GainControlis changed
     *
     * @param listener The listener for gain change events
    */
    public synchronized void addGainChangeListener(GainChangeListener listener) {
	if (listener != null) {
	    if (listeners == null)
		listeners = new Vector();
	    listeners.addElement(listener);
	}
    }

    
    /**
     * Removes a listener from the list of listeners for
     * gain change events.
     *
     * @param listener The listener to be removed from the list
     * of listeners for gain change events
     */
    public synchronized void removeGainChangeListener(GainChangeListener listener) {
	if (listener != null && listeners != null)
	    listeners.removeElement(listener);
    }
 
    
    
   /**
   * This method has to be defined by non-abstarct classes that 
   * implement Control interface. Full JMF's implementation the method 
   * returns a slider control to manipulate the gain level for all players. 
   * JMF Lite's implementation returns null to allow a TV Player to implement
   * the control in a form most suitable for the TV Player.
   *
   * @return null
   */
   public java.awt.Component getControlComponent() {
//A.D. applications will probably want to create their own gain control
//components. They can do so and call the methods of the GainControlAdapter.    	
	/*A.D. 
    
	if (component == null) {
	    float detents[] = new float[0];
	    /*	    detents[0] = 0.5f;*/
/*A.D.	    component = new Slider(detents, new Color(128, 128, 128));
	    volumeControl = new VolumeControl(0.0f, 1.0f, 0.5f,
							     0.001f, false,
							     component, true, null);
	    ((Slider)component).setControl(volumeControl);
	}
	return component;
A.D.*/
			return null; //A.D.
    }


    /**
   * Sends all registered listeners a GainChangeEvent object notifying
   * the listeners about gain change in the GainControl
	*/
    protected synchronized void informListeners() {
	if (listeners != null) {
	    GainChangeEvent gce = new GainChangeEvent(this, muteState, dB, level);
	    for (int i = 0; i < listeners.size(); i++) {
		GainChangeListener gcl = (GainChangeListener) listeners.elementAt(i);
		gcl.gainChange(gce);
	    }
	}
    }
  
    /* A.D
    class VolumeControl extends NumericControlAdapter {
	int type;
	
	// VolumeControl::
	public VolumeControl(float ll, float ul, float dv, float gran,
			       boolean log, Component comp,
			       boolean def, Control paren) {
	    super(ll, ul, dv, gran, log, comp, def, paren);
	}

	// VolumeControl::
	public float setValue(float val) {
	    if (getLevel() != val) {
		setLevel(val);
		this.informListeners();
	    }
	    return val;
	}

	// GenericColorNCA::
	public float getValue() {
	    return getLevel();
	}
    }
A.D.*/
}