/*
 * @(#)AWTVideoSize.java	1.12 00/08/06
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

package javax.tv.media;

import java.awt.Rectangle;
import java.awt.Point;

/**
 * <code>AWTVideoSize</code> is a data holder that represents the
 * position, scaling, and clipping of a JMF Player, as controlled via
 * an AWTVideoSizeControl.  All coordinates are expressed in the same
 * coordinate space as AWT components.  Because background video might
 * be larger than the addressible AWT area, some of the positions
 * might be negative.
 *
 * <p> An AWTVideoSize represents a transformation
 * of video where the video is first positioned, then scaled, and then
 * clipped.  A rectangle (in the screen's coordinate system) of the
 * source video is translated, scaled and clipped to fit within a
 * rectangle specified in the screen's coordinate system.
 *
 * @version     1.12, 08/06/00
 *
 * @see javax.tv.media.AWTVideoSizeControl */

public class AWTVideoSize {

    /**
     * Constructs a new <code>AWTVideoSize</code> instance.  This
     * <code>AWTVideoSize</code> represents a transformation where the
     * rectangle <code>source</code> in the source video is scaled and
     * clipped to be within the rectangle <code>dest</code>.
     *
     * <p> The instance of AWTVideoSize created with this constructor
     * will not maintain a reference to either of the constructor's
     * parameters.
     *
     * @param source The rectangle representing the portion of the source
     * video to display, in the coordinate system of the screen.
     *
     * @param dest The rectangle representing where the video is to be
     * displayed, in the coordinate system of the screen.  */
    public AWTVideoSize(Rectangle source, Rectangle dest) {
    }

    /**
     * Return a copy of the rectangle representing the portion of the source
     * video to display, in the coordinate system of the screen.
     *
     * @return The source <code>Rectangle</code>.
     */
    public Rectangle getSource() {
	return null;
    }

    /**
     * Return a copy of the rectangle representing where the video is to be
     * displayed, in the coordinate system of the screen.
     *
     * @return The destination <code>Rectangle</code>.
     */
    public Rectangle getDestination() {
	return null;
    }

    /**
     * Give the scaling factor applied to the video in the horizontal
     * dimension, i.e.,
     * <code>getDestination().width / getSource().width</code>.
     *
     * @return The horizontal scaling factor applied to the video.
     */
    public float getXScale() {
	return 0f;
    }

    /**
     * Give the scaling factor applied to the video in the vertical
     * dimension, i.e.,
     * <code>getDestination().height / getSource().height</code>.
     *
     * @return The vertical scaling factor applied to the video.
     */
    public float getYScale() {
	return 0f;
    }

    /**
     * Generates a hash code value for this <code>AWTVideoSize</code>.
     * Two <code>AWTVideoSize</code> instances for which
     * <code>AWTVideoSize.equals()</code> is <code>true</code> will
     * have identical hash code values.
     *
     * @return The hashcode value for this <code>AWTVideoSize</code>.
     **/
    public int hashCode() {
	return 0;
    }

    /**
     * Compares this <code>AWTVideoSize</code> with the given object
     * for equality.  Returns <code>true</code> if and only if the
     * given object is also of type <code>AWTVideoSize</code> and
     * contains data members equal to those of this
     * <code>AWTVideoSize</code.
     *
     * @param other The object with which to test for equality.
     *
     * @return <code>true</code> if the two AWTVideoSize instances are
     * equal; <code>false</code> otherwise.
     **/
    public boolean equals(Object other) {
	return false;
    }

    /**
     * Returns a string representation of this
     * <code>AWTVideoSize</code> and its values.
     *
     * @return A string representation of this object.
     **/
    public String toString() {
	return	null;
    }
}

