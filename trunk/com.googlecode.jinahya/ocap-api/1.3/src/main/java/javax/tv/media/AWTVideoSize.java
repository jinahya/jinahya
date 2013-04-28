/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.media;

import java.awt.Rectangle;
import java.awt.Point;

/** 
 * <code>AWTVideoSize</code> is a data holder that represents the
 * position, scaling, and clipping of a JMF Player, as controlled via
 * an AWTVideoSizeControl.  All coordinates are expressed in the same
 * coordinate space as AWT components.  Because background video might
 * be larger than the addressable AWT area, some of the positions
 * might be negative.
 *
 * <p> An AWTVideoSize represents a transformation
 * of video where the video is first positioned, then scaled, and then
 * clipped.  A rectangle (in the screen's coordinate system) of the
 * source video is translated, scaled and clipped to fit within a
 * rectangle specified in the screen's coordinate system.
 *
 * @version     1.14, 10/09/00
 * @author      Bill Foote
 *
 * @see javax.tv.media.AWTVideoSizeControl 
 */
public class AWTVideoSize
{

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
     * displayed, in the coordinate system of the screen.  
     */
    public AWTVideoSize(Rectangle source, Rectangle dest) { }

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
        return 0.0f;
    }

    /** 
     * Give the scaling factor applied to the video in the vertical
     * dimension, i.e.,
     * <code>getDestination().height / getSource().height</code>.
     *
     * @return The vertical scaling factor applied to the video.
     */
    public float getYScale() {
        return 0.0f;
    }

    /** 
     * Generates a hash code value for this <code>AWTVideoSize</code>.
     * Two <code>AWTVideoSize</code> instances for which
     * <code>AWTVideoSize.equals()</code> is <code>true</code> will
     * have identical hash code values.
     *
     * @return The hashcode value for this <code>AWTVideoSize</code>.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Compares this <code>AWTVideoSize</code> with the given object
     * for equality.  Returns <code>true</code> if and only if the
     * given object is also of type <code>AWTVideoSize</code> and
     * contains data members equal to those of this
     * <code>AWTVideoSize</code>.
     *
     * @param other The object with which to test for equality.
     *
     * @return <code>true</code> if the two AWTVideoSize instances are
     * equal; <code>false</code> otherwise.
     */
    public boolean equals(Object other) {
        return false;
    }

    /** 
     * Returns a string representation of this
     * <code>AWTVideoSize</code> and its values.
     *
     * @return A string representation of this object.
     */
    public String toString() {
        return null;
    }
}
