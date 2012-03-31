/*
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2007 Sun Microsystems, Inc. All Rights Reserved.
<p>
Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below. For
example, no permission is given for you to incorporate this file, in
whole or in part, in an implementation of a Java specification.
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative, as opposed to normative, use. The file and any
files generated from it may be used to generate other informative
documentation, such as a unified set of documents of API signatures for
a platform that includes technologies expressed as Java APIs. The file
may also be used to produce "compilation stubs," which allow
applications to be compiled and validated for such platforms.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.awt;

// import java.awt.geom.Point2D;

/** 
 * A point representing a location in (x, y) coordinate space, specified
 * in integer precision.
 *
 * @version 	1.37, 01/23/03
 * @author 	Sami Shaio
 * @since       JDK1.0
 */
// public class Point extends Point2D implements java.io.Serializable
public class Point implements java.io.Serializable, Cloneable
{
    /** 
     * The <i>x</i> coordinate.
     * If no <i>x</i> coordinate is set it will default to 0.
     *
     * @serial
     * @see #getLocation()
     * @see #move(int, int)
     */
    public int x;

    /** 
     * The <i>y</i> coordinate. 
     * If no <i>y</i> coordinate is set it will default to 0.
     *
     * @serial
     * @see #getLocation()
     * @see #move(int, int)
     */
    public int y;

    /*
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = -5276940640259749850L;

    /** 
     * Constructs and initializes a point at the origin 
     * (0,&nbsp;0) of the coordinate space. 
     * @since       JDK1.1
     */
    public Point() { }

    /** 
     * Constructs and initializes a point with the same location as
     * the specified <code>Point</code> object.
     * @param       p a point
     * @since       JDK1.1
     */
    public Point(Point p) { }

    /** 
     * Constructs and initializes a point at the specified 
     * (<i>x</i>,&nbsp;<i>y</i>) location in the coordinate space. 
     * @param       x   the <i>x</i> coordinate
     * @param       y   the <i>y</i> coordinate
     */
    public Point(int x, int y) { }

    // /** 
     // * Returns the X coordinate of the point in double precision.
     // * @return the X coordinate of the point in double precision
     // */
    // public double getX() { }
// 
    // /** 
     // * Returns the Y coordinate of the point in double precision.
     // * @return the Y coordinate of the point in double precision
     // */
    // public double getY() { }

    /** 
     * Returns the location of this point.
     * This method is included for completeness, to parallel the
     * <code>getLocation</code> method of <code>Component</code>.
     * @return      a copy of this point, at the same location
     * @see         java.awt.Component#getLocation
     * @see         java.awt.Point#setLocation(java.awt.Point)
     * @see         java.awt.Point#setLocation(int, int)
     * @since       JDK1.1
     */
    public Point getLocation() { return null; }

    /** 
     * Sets the location of the point to the specified location.
     * This method is included for completeness, to parallel the
     * <code>setLocation</code> method of <code>Component</code>.
     * @param       p  a point, the new location for this point
     * @see         java.awt.Component#setLocation(java.awt.Point)
     * @see         java.awt.Point#getLocation
     * @since       JDK1.1
     */
    public void setLocation(Point p) { }

    /** 
     * Changes the point to have the specified location.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setLocation</code> method of <code>Component</code>.
     * Its behavior is identical with <code>move(int,&nbsp;int)</code>.
     * @param       x  the <i>x</i> coordinate of the new location
     * @param       y  the <i>y</i> coordinate of the new location
     * @see         java.awt.Component#setLocation(int, int)
     * @see         java.awt.Point#getLocation
     * @see         java.awt.Point#move(int, int)
     * @since       JDK1.1
     */
    public void setLocation(int x, int y) { }
// 
    // /** 
     // * Sets the location of this point to the specified double coordinates.
     // * The double values will be rounded to integer values.
     // * Any number smaller than <code>Integer.MIN_VALUE</code>
     // * will be reset to <code>MIN_VALUE</code>, and any number
     // * larger than <code>Integer.MAX_VALUE</code> will be
     // * reset to <code>MAX_VALUE</code>.
     // *
     // * @param x the <i>x</i> coordinate of the new location
     // * @param y the <i>y</i> coordinate of the new location
     // * @see #getLocation
     // */
    // public void setLocation(double x, double y) { }

    /** 
     * Moves this point to the specified location in the 
     * (<i>x</i>,&nbsp;<i>y</i>) coordinate plane. This method
     * is identical with <code>setLocation(int,&nbsp;int)</code>.
     * @param       x  the <i>x</i> coordinate of the new location
     * @param       y  the <i>y</i> coordinate of the new location
     * @see         java.awt.Component#setLocation(int, int)
     */
    public void move(int x, int y) { }

    /** 
     * Translates this point, at location (<i>x</i>,&nbsp;<i>y</i>), 
     * by <code>dx</code> along the <i>x</i> axis and <code>dy</code> 
     * along the <i>y</i> axis so that it now represents the point 
     * (<code>x</code>&nbsp;<code>+</code>&nbsp;<code>dx</code>, 
     * <code>y</code>&nbsp;<code>+</code>&nbsp;<code>dy</code>). 
     * @param       dx   the distance to move this point 
     *                            along the <i>x</i> axis
     * @param       dy    the distance to move this point 
     *                            along the <i>y</i> axis
     */
    public void translate(int dx, int dy) { }

// PBP/PP
// [6187230]

    /** 
     * Determines whether or not two points are equal. Two instances of
     * <code>Point</code> are equal if the values of their 
     * <code>x</code> and <code>y</code> member fields, representing
     * their position in the coordinate space, are the same.
     * @param obj an object to be compared with this <code>Point</code>
     * @return <code>true</code> if the object to be compared is
     *         an instance of <code>Point</code> and has
     *         the same values; <code>false</code> otherwise.
     */
    public boolean equals(Object obj) { return false; }

    /** 
     * Returns a string representation of this point and its location 
     * in the (<i>x</i>,&nbsp;<i>y</i>) coordinate space. This method is 
     * intended to be used only for debugging purposes, and the content 
     * and format of the returned string may vary between implementations. 
     * The returned string may be empty but may not be <code>null</code>.
     * 
     * @return  a string representation of this point
     */
    public String toString() { return null; }

    /**
     * Creates a new object of the same class and with the
     * same contents as this object.
     * @return     a clone of this instance.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        java.lang.Cloneable
     * @since      1.2
     */
    public Object clone() {
      return null;
    }

// PBP/PP
// [6187230]

    /** 
     * Returns the hashcode for this <code>Point</code>.
     * @return      a hash code for this <code>Point</code>.
     */
    public int hashCode() {
        return 0;
    }
}
