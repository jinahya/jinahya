/*
 * @(#)NumericControlAdapter.java	1.5 98/03/28
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
import com.sun.tv.media.controls.*;
import java.awt.*;

/**
 * This class is a part of the porting layer implementation for JavaTV.
 * A control that represents the state by a numeric value. The value can be any
 * number represented as a float as long as it falls within the range and
 * has the specified granularity/accuracy. For example, if the lower limit is
 * 0 and the upper limit is 255 and the granularity is 1, then it can be assigned
 * any integer value from 0 to 255.
 */
public class NumericControlAdapter extends AtomicControlAdapter
implements NumericControl {

    protected float lowerLimit;
    protected float upperLimit;
    protected float defaultValue;
    protected float granularity;
    protected boolean logarithmic;


    public NumericControlAdapter() {
	super(null, true, null);
	lowerLimit = 0f;
	upperLimit = 1.0f;
	defaultValue = 0.5f;
	granularity = 0.001f;
	logarithmic = false;
    }
    
    public NumericControlAdapter(float ll, float ul, float dv,
				 float gran, boolean log,
				 Component comp,
				 boolean def,
				 Control parent) {

	super(comp, def, parent);
	lowerLimit = ll;
	upperLimit = ul;
	defaultValue = dv;
	granularity = gran;
	logarithmic = log;
    }
    
    /**
     * The smallest value assignable to this control.
     */
    public float getLowerLimit() {
	return lowerLimit;
    }

    /**
     * The largest value assignable to this control.
     */
    public float getUpperLimit() {
	return upperLimit;
    }

    /**
     * Returns the value that the control currently represents.
     */
    public float getValue() {
	return (float) 0.0;
    }
    
    /**
     * Sets the value on the control and returns the value that was actually
     * set.
     */
    public float setValue(float value) {
	return value;
    }

    /**
     * Returns the value that is the default for this control.
     */
    public float getDefaultValue() {
	return defaultValue;
    }

    /**
     * Sets the default value for the control.
     */
    public float setDefaultValue(float value) {
	return (defaultValue = value);
    }

    /**
     * Returns the granularity of the numeric value.
     */
    public float getGranularity() {
	return granularity;
    }

    /**
     * ???
     */
    public boolean isLogarithmic() {
	return logarithmic;
    }

    /**
     * ???
     */
    public float getLogarithmicBase() {
	return (float) 0;
    }
}

