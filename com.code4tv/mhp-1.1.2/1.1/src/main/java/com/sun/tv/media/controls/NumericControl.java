/*
 * @(#)NumericControl.java	1.5 98/03/28
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

import com.sun.tv.media.*;

/**
 * This interface is a part of the porting layer implementation for JavaTV.
 * A control that represents the state by a numeric value. The value can be any
 * number represented as a float as long as it falls within the range and
 * has the specified granularity/accuracy. For example, if the lower limit is
 * 0 and the upper limit is 255 and the granularity is 1, then it can be assigned
 * any integer value from 0 to 255.
 */
public interface NumericControl extends AtomicControl {

    /**
     * The smallest value assignable to this control.
     */
    public float getLowerLimit();

    /**
     * The largest value assignable to this control.
     */
    public float getUpperLimit();

    /**
     * Returns the value that the control currently represents.
     */
    public float getValue();

    /**
     * Sets the value on the control and returns the value that was actually
     * set.
     */
    public float setValue(float value);

    /**
     * Returns the value that is the default for this control.
     */
    public float getDefaultValue();

    /**
     * Sets the default value for the control.
     */
    public float setDefaultValue(float value);

    /**
     * Returns the granularity of the numeric value.
     */
    public float getGranularity();

    /**
     * ???
     */
    public boolean isLogarithmic();

    /**
     * ???
     */
    public float getLogarithmicBase();
}

