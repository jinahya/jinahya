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

// import java.awt.geom.AffineTransform;
// import java.awt.geom.PathIterator;
// import java.awt.geom.Point2D;
// import java.awt.geom.Rectangle2D;
// import sun.awt.geom.Crossings;

// PBP/PP
// [6187229]

/** 
 * The <code>Polygon</code> class encapsulates a description of a 
 * closed, two-dimensional region within a coordinate space. This 
 * region is bounded by an arbitrary number of line segments, each of 
 * which is one side of the polygon. Internally, a polygon
 * comprises of a list of (<i>x</i>,&nbsp;<i>y</i>) 
 * coordinate pairs, where each pair defines a <i>vertex</i> of the 
 * polygon, and two successive pairs are the endpoints of a 
 * line that is a side of the polygon. The first and final
 * pairs of (<i>x</i>,&nbsp;<i>y</i>) points are joined by a line segment 
 * that closes the polygon.  This <code>Polygon</code> is defined with
 * an even-odd winding rule.

<em>(The even-odd rule specifies that a point lies inside the  path if a ray drawn in any direction from that point to  infinity is crossed by path segments an odd number of times.)
</em>
 * This class's hit-testing methods, which include the 
 * <code>contains</code>, <code>intersects</code> and <code>inside</code>
 * methods, use the <i>insideness</i> definition described in the 
 * {@link Shape} class comments.
 *
 * @version     1.26, 07/24/98
 * @author 	Sami Shaio
 * @see Shape
 * @author      Herb Jellinek
 * @since       JDK1.0
 */
public class Polygon implements Shape, java.io.Serializable
{
    /** 
     * The total number of points.  The value of <code>npoints</code>
     * represents the number of valid points in this <code>Polygon</code>
     * and might be less than the number of elements in 
     * {@link #xpoints xpoints} or {@link #ypoints ypoints}.
     * This value can be NULL.
     *
     * @serial
     * @see #addPoint(int, int)
     */
    public int npoints;

    /** 
     * The array of <i>x</i> coordinates.  The number of elements in 
     * this array might be more than the number of <i>x</i> coordinates 
     * in this <code>Polygon</code>.  The extra elements allow new points
     * to be added to this <code>Polygon</code> without re-creating this
     * array.  The value of {@link #npoints npoints} is equal to the
     * number of valid points in this <code>Polygon</code>.
     *
     * @serial
     * @see #addPoint(int, int)
     */
    public int[] xpoints;

    /** 
     * The array of <i>y</i> coordinates.  The number of elements in
     * this array might be more than the number of <i>y</i> coordinates   
     * in this <code>Polygon</code>.  The extra elements allow new points    
     * to be added to this <code>Polygon</code> without re-creating this
     * array.  The value of <code>npoints</code> is equal to the
     * number of valid points in this <code>Polygon</code>. 
     *
     * @serial
     * @see #addPoint(int, int)
     */
    public int[] ypoints;

// PBP/PP
    /** 
     * Bounds of the polygon.
     * This value can be NULL.
     * Please see the javadoc comments getBounds().
     * 
     * @serial

     * @see #getBounds()
     */
    protected Rectangle bounds;

    /* 
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = -6460061437900069969L;

    /** 
     * Creates an empty polygon.
     */
    public Polygon() { }

    /** 
     * Constructs and initializes a <code>Polygon</code> from the specified 
     * parameters. 
     * @param xpoints an array of <i>x</i> coordinates
     * @param ypoints an array of <i>y</i> coordinates
     * @param npoints the total number of points in the    
     *				<code>Polygon</code>
     * @exception  NegativeArraySizeException if the value of
     *                       <code>npoints</code> is negative.
     * @exception  IndexOutOfBoundsException if <code>npoints</code> is
     *             greater than the length of <code>xpoints</code>
     *             or the length of <code>ypoints</code>.
     * @exception  NullPointerException if <code>xpoints</code> or
     *             <code>ypoints</code> is <code>null</code>.
     */
    public Polygon(int[] xpoints, int[] ypoints, int npoints) { }

     /** 
      * Resets this <code>Polygon</code> object to an empty polygon.
      * The coordinate arrays and the data in them are left untouched
      * but the number of points is reset to zero to mark the old
      * vertex data as invalid and to start accumulating new vertex
      * data at the beginning.
      * All internally-cached data relating to the old vertices
      * are discarded.
      * Note that since the coordinate arrays from before the reset
      * are reused, creating a new empty <code>Polygon</code> might
      * be more memory efficient than resetting the current one if
      * the number of vertices in the new polygon data is significantly
      * smaller than the number of vertices in the data from before the
      * reset.
      * @see         java.awt.Polygon#invalidate
      * @since 1.4
      */
     public void reset() { }
// 
     /** 
      * Invalidates or flushes any internally-cached data that depends
      * on the vertex coordinates of this <code>Polygon</code>.
      * This method should be called after any direct manipulation
      * of the coordinates in the <code>xpoints</code> or
      * <code>ypoints</code> arrays to avoid inconsistent results
      * from methods such as <code>getBounds</code> or <code>contains</code>
      * that might cache data from earlier computations relating to
      * the vertex coordinates.
      * @see         java.awt.Polygon#getBounds
      * @since 1.4
      */
     public void invalidate() { }

    /** 
     * Translates the vertices of the <code>Polygon</code> by 
     * <code>deltaX</code> along the x axis and by 
     * <code>deltaY</code> along the y axis.
     * @param deltaX the amount to translate along the <i>x</i> axis
     * @param deltaY the amount to translate along the <i>y</i> axis
     * @since JDK1.1
     */
    public void translate(int deltaX, int deltaY) { }

    /** 
     * Appends the specified coordinates to this <code>Polygon</code>. 
     * <p>
     * If an operation that calculates the bounding box of this     
     * <code>Polygon</code> has already been performed, such as  
     * <code>getBounds</code> or <code>contains</code>, then this 
     * method updates the bounding box. 
     * @param       x the specified x coordinate
     * @param       y the specified y coordinate
     * @see         java.awt.Polygon#getBounds
     * @see         java.awt.Polygon#contains
     */
    public void addPoint(int x, int y) { }

    /** 
     * Gets the bounding box of this <code>Polygon</code>. 
     * The bounding box is the smallest {@link Rectangle} whose
     * sides are parallel to the x and y axes of the 
     * coordinate space, and can completely contain the <code>Polygon</code>.
     * @return a <code>Rectangle</code> that defines the bounds of this 
     * <code>Polygon</code>.
     * @since       JDK1.1
     */
    public Rectangle getBounds() { return null; }

//    /** 
//     * Returns the bounds of this <code>Polygon</code>.
//     * @return the bounds of this <code>Polygon</code>.
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>getBounds()</code>.
//     */
//    public Rectangle getBoundingBox() { return null; }

// PBP/PP
    /** 
     * Determines whether the specified {@link Point} is inside this 
     * <code>Polygon</code>.
     * @param p the specified <code>Point</code> to be tested
     * @return <code>true</code> if the <code>Polygon</code> contains the
     * 			<code>Point</code>; <code>false</code> otherwise.

     */
    public boolean contains(Point p) { return false; }

// PBP/PP
    /** 
     * Determines whether the specified coordinates are inside this 
     * <code>Polygon</code>.   
     * <p>
     * @param x the specified x coordinate to be tested
     * @param y the specified y coordinate to be tested
     * @return  <code>true</code> if this <code>Polygon</code> contains
     * 			the specified coordinates, (<i>x</i>,&nbsp;<i>y</i>);  
     * 			<code>false</code> otherwise.

     * @since      JDK1.1
     */
    public boolean contains(int x, int y) { return false; }

//    /** 
//     * Determines whether the specified coordinates are contained in this 
//     * <code>Polygon</code>.
//     * @param x the specified x coordinate to be tested
//     * @param y the specified y coordinate to be tested
//     * @return  <code>true</code> if this <code>Polygon</code> contains
//     * 		the specified coordinates, (<i>x</i>,&nbsp;<i>y</i>);  
//     * 		<code>false</code> otherwise.
//     * @see #contains(double, double)
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>contains(int, int)</code>.
//     */
//    public boolean inside(int x, int y) { return false; }

    // /** 
     // * Returns the high precision bounding box of the {@link Shape}.
     // * @return a {@link Rectangle2D} that precisely
     // *		bounds the <code>Shape</code>.
     // */
    // public Rectangle2D getBounds2D() { }
// 
    // /** 
     // * Determines if the specified coordinates are inside this 
     // * <code>Polygon</code>.  For the definition of
     // * <i>insideness</i>, see the class comments of {@link Shape}.
     // * @param x the specified x coordinate
     // * @param y the specified y coordinate
     // * @return <code>true</code> if the <code>Polygon</code> contains the
     // * specified coordinates; <code>false</code> otherwise.
     // */
    // public boolean contains(double x, double y) { }
// 
    // /** 
     // * Tests if a specified {@link Point2D} is inside the boundary of this 
     // * <code>Polygon</code>.
     // * @param p a specified <code>Point2D</code>
     // * @return <code>true</code> if this <code>Polygon</code> contains the 
     // * 		specified <code>Point2D</code>; <code>false</code>
     // *          otherwise.
     // * @see #contains(double, double)
     // */
    // public boolean contains(Point2D p) { }

    // /** 
     // * Tests if the interior of this <code>Polygon</code> intersects the 
     // * interior of a specified set of rectangular coordinates.
     // * @param x the x coordinate of the specified rectangular
     // *			shape's top-left corner
     // * @param y the y coordinate of the specified rectangular
     // *			shape's top-left corner
     // * @param w the width of the specified rectangular shape
     // * @param h the height of the specified rectangular shape
     // * @return <code>true</code> if the interior of this 
     // *			<code>Polygon</code> and the interior of the
     // *			specified set of rectangular 
     // * 			coordinates intersect each other;
     // *			<code>false</code> otherwise.
     // */
    // public boolean intersects(double x, double y, double w, double h) { }
// 
    // /** 
     // * Tests if the interior of this <code>Polygon</code> intersects the
     // * interior of a specified <code>Rectangle2D</code>.
     // * @param r a specified <code>Rectangle2D</code>
     // * @return <code>true</code> if this <code>Polygon</code> and the
     // * 			interior of the specified <code>Rectangle2D</code>
     // * 			intersect each other; <code>false</code>
     // * 			otherwise.
     // */
    // public boolean intersects(Rectangle2D r) { }
// 
    // /** 
     // * Tests if the interior of this <code>Polygon</code> entirely
     // * contains the specified set of rectangular coordinates.
     // * @param x the x coordinate of the top-left corner of the
     // * 			specified set of rectangular coordinates
     // * @param y the y coordinate of the top-left corner of the
     // * 			specified set of rectangular coordinates
     // * @param w the width of the set of rectangular coordinates
     // * @param h the height of the set of rectangular coordinates
     // * @return <code>true</code> if this <code>Polygon</code> entirely
     // * 			contains the specified set of rectangular
     // * 			coordinates; <code>false</code> otherwise.
     // */
    // public boolean contains(double x, double y, double w, double h) { }
// 
    // /** 
     // * Tests if the interior of this <code>Polygon</code> entirely
     // * contains the specified <code>Rectangle2D</code>.
     // * @param r the specified <code>Rectangle2D</code>
     // * @return <code>true</code> if this <code>Polygon</code> entirely
     // * 			contains the specified <code>Rectangle2D</code>;
     // *			<code>false</code> otherwise.
     // * @see #contains(double, double, double, double)
     // */
    // public boolean contains(Rectangle2D r) { }

    // /** 
     // * Returns an iterator object that iterates along the boundary of this 
     // * <code>Polygon</code> and provides access to the geometry
     // * of the outline of this <code>Polygon</code>.  An optional
     // * {@link AffineTransform} can be specified so that the coordinates 
     // * returned in the iteration are transformed accordingly.
     // * @param at an optional <code>AffineTransform</code> to be applied to the
     // * 		coordinates as they are returned in the iteration, or 
     // *		<code>null</code> if untransformed coordinates are desired
     // * @return a {@link PathIterator} object that provides access to the
     // *		geometry of this <code>Polygon</code>.      
     // */
    // public PathIterator getPathIterator(AffineTransform at) { }
// 
    // /** 
     // * Returns an iterator object that iterates along the boundary of
     // * the <code>Shape</code> and provides access to the geometry of the 
     // * outline of the <code>Shape</code>.  Only SEG_MOVETO, SEG_LINETO, and 
     // * SEG_CLOSE point types are returned by the iterator.
     // * Since polygons are already flat, the <code>flatness</code> parameter
     // * is ignored.  An optional <code>AffineTransform</code> can be specified 
     // * in which case the coordinates returned in the iteration are transformed
     // * accordingly.
     // * @param at an optional <code>AffineTransform</code> to be applied to the
     // * 		coordinates as they are returned in the iteration, or 
     // *		<code>null</code> if untransformed coordinates are desired
     // * @param flatness the maximum amount that the control points
     // * 		for a given curve can vary from colinear before a subdivided
     // *		curve is replaced by a straight line connecting the 
     // * 		endpoints.  Since polygons are already flat the
     // * 		<code>flatness</code> parameter is ignored.
     // * @return a <code>PathIterator</code> object that provides access to the
     // * 		<code>Shape</code> object's geometry.
     // */
    // public PathIterator getPathIterator(AffineTransform at, double flatness) { }
}
