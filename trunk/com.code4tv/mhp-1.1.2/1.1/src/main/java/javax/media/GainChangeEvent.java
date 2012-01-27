/*
 * @(#)GainChangeEvent.java	1.17 98/03/28
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

/**
 * A <code>GainChangeEvent</code> is posted by a
 * <code>GainControl</code> when its state has been updated.
 *
 * <h2>Java Beans support </h2>
 * Any implementation of this object is required to be subclassed
 * from either java.util.EventObject or sunw.util.EventObject.
 *
 * @see GainControl
 * @see GainChangeListener
 *
 * @version 1.17, 98/03/28
 */
public class GainChangeEvent extends java.util.EventObject
    implements MediaEvent {

    GainControl eventSrc;
    boolean newMute;
    float newDB;
    float newLevel;

    public GainChangeEvent(GainControl from,
			  boolean mute, float dB, float level) {
	super(from);
	eventSrc = from;
	newMute = mute;
	newDB = dB;
	newLevel = level;
    }

    /**
     * Get the object that posted this event.
     *
     * @return The object that posted this event.
     */
     public Object getSource() {
	 return eventSrc;
     }

    /**
     * Get the <code>GainControl</code> that posted this event.  
     *
     * @return The <code>GainControl</code> that posted this event.
     */
    public GainControl getSourceGainControl() {
	return eventSrc;
    }

    /**
     * Get the <code>GainControl's</code> new gain value in dB.
     *
     * @return The <code>GainControl's</code> new gain value, in dB.
     */
    public float getDB() {
	return newDB;
    }

    /**
     * Get the <code>GainControl's</code> new gain value in the level scale.
     *
     * @return The <code>GainControl's</code> new gain, in the level scale.
    */
    public float getLevel() {
	return newLevel;
    }

    /**
     * Get the <code>GainControl's</code> new mute value.
     *
     * @return The <code>GainControl's</code> new mute value.
    */
    public boolean getMute() {
	return newMute;
    }

}
