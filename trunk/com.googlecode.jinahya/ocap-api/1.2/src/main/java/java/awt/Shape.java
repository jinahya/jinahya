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

// PBP/PP
/** 
 * The <code>Shape</code> interface provides definitions for objects 
 * that represent some form of geometric shape.

 * <p>
 * <b>Definition of insideness:</b>
 * A point is considered to lie inside a 
 * <code>Shape</code> if and only if:
 * <ul>
 * <li> it lies completely
 * inside the<code>Shape</code> boundary <i>or</i> 
 * <li>
 * it lies exactly on the <code>Shape</code> boundary <i>and</i> the 
 * space immediately adjacent to the
 * point in the increasing <code>X</code> direction is 
 * entirely inside the boundary <i>or</i>
 * <li>
 * it lies exactly on a horizontal boundary segment <b>and</b> the
 * space immediately adjacent to the point in the 
 * increasing <code>Y</code> direction is inside the boundary.
 * </ul>
 * <p>The <code>contains</code> and <code>intersects</code> methods
 * consider the interior of a <code>Shape</code> to be the area it
 * encloses as if it were filled.  This means that these methods
 * consider
 * unclosed shapes to be implicitly closed for the purpose of
 * determining if a shape contains or intersects a rectangle or if a
 * shape contains a point.
 * 
 * @see 
 *
 * @version 1.19 06/24/98
 * @author Jim Graham
 */
public interface Shape
{

// PBP/PP
    /** 
     * Returns an integer {@link Rectangle} that completely encloses the
     * <code>Shape</code>.  Note that there is no guarantee that the
     * returned <code>Rectangle</code> is the smallest bounding box that
     * encloses the <code>Shape</code>, only that the <code>Shape</code>
     * lies entirely within the indicated  <code>Rectangle</code>.  The
     * returned <code>Rectangle</code> might also fail to completely
     * enclose the <code>Shape</code> if the <code>Shape</code> overflows
     * the limited range of the integer data type.

     *
     * @return an integer <code>Rectangle</code> that completely encloses
     *                 the <code>Shape</code>.

     */
    public Rectangle getBounds();

    // /** 
     // * Returns a high precision and more accurate bounding box of
     // * the <code>Shape</code> than the <code>getBounds</code> method.
     // * Note that there is no guarantee that the returned 
     // * {@link Rectangle2D} is the smallest bounding box that encloses 
     // * the <code>Shape</code>, only that the <code>Shape</code> lies 
     // * entirely within the indicated <code>Rectangle2D</code>.  The 
     // * bounding box returned by this method is usually tighter than that 
     // * returned by the <code>getBounds</code> method and never fails due 
     // * to overflow problems since the return value can be an instance of 
     // * the <code>Rectangle2D</code> that uses double precision values to 
     // * store the dimensions.
     // * @return an instance of <code>Rectangle2D</code> that is a
     // *                 high-precision bounding box of the <code>Shape</code>.
     // * @see #getBounds
     // */
    // public Rectangle2D getBounds2D();
// 
    // /** 
     // * Tests if the specified coordinates are inside the boundary of the 
     // * <code>Shape</code>.
     // * @param x the specified x coordinate
     // * @param y the specified y coordinate
     // * @return <code>true</code> if the specified coordinates are inside 
     // *         the <code>Shape</code> boundary; <code>false</code>
     // *         otherwise.
     // */
    // public boolean contains(double x, double y);
// 
    // /** 
     // * Tests if a specified {@link Point2D} is inside the boundary
     // * of the <code>Shape</code>.
     // * @param p a specified <code>Point2D</code>
     // * @return <code>true</code> if the specified <code>Point2D</code> is 
     // *          inside the boundary of the <code>Shape</code>;
     // *		<code>false</code> otherwise.
     // */
    // public boolean contains(Point2D p);
// 
    // /** 
     // * Tests if the interior of the <code>Shape</code> intersects the 
     // * interior of a specified rectangular area.
     // * The rectangular area is considered to intersect the <code>Shape</code> 
     // * if any point is contained in both the interior of the 
     // * <code>Shape</code> and the specified rectangular area.
     // * <p>
     // * This method might conservatively return <code>true</code> when:
     // * <ul>
     // * <li>
     // * there is a high probability that the rectangular area and the
     // * <code>Shape</code> intersect, but
     // * <li>
     // * the calculations to accurately determine this intersection
     // * are prohibitively expensive.
     // * </ul>
     // * This means that this method might return <code>true</code> even
     // * though the rectangular area does not intersect the <code>Shape</code>.
     // * The {@link java.awt.geom.Area Area} class can be used to perform 
     // * more accurate computations of geometric intersection for any 
     // * <code>Shape</code> object if a more precise answer is required.
     // * @param x the x coordinate of the specified rectangular area
     // * @param y the y coordinate of the specified rectangular area
     // * @param w the width of the specified rectangular area
     // * @param h the height of the specified rectangular area
     // * @return <code>true</code> if the interior of the <code>Shape</code> and
     // * 		the interior of the rectangular area intersect, or are
     // * 		both highly likely to intersect and intersection calculations 
     // * 		would be too expensive to perform; <code>false</code> otherwise.
     // * @see java.awt.geom.Area
     // */
    // public boolean intersects(double x, double y, double w, double h);
// 
    // /** 
     // * Tests if the interior of the <code>Shape</code> intersects the 
     // * interior of a specified <code>Rectangle2D</code>.
     // * This method might conservatively return <code>true</code> when:
     // * <ul>
     // * <li>
     // * there is a high probability that the <code>Rectangle2D</code> and the
     // * <code>Shape</code> intersect, but
     // * <li>
     // * the calculations to accurately determine this intersection
     // * are prohibitively expensive.
     // * </ul>
     // * This means that this method might return <code>true</code> even
     // * though the <code>Rectangle2D</code> does not intersect the
     // * <code>Shape</code>. 
     // * @param r the specified <code>Rectangle2D</code>
     // * @return <code>true</code> if the interior of the <code>Shape</code> and  
     // * 		the interior of the specified <code>Rectangle2D</code>
     // *		intersect, or are both highly likely to intersect and intersection
     // *		calculations would be too expensive to perform; <code>false</code>
     // * 		otherwise.
     // * @see #intersects(double, double, double, double)
     // */
    // public boolean intersects(Rectangle2D r);
// 
    // /** 
     // * Tests if the interior of the <code>Shape</code> entirely contains 
     // * the specified rectangular area.  All coordinates that lie inside
     // * the rectangular area must lie within the <code>Shape</code> for the
     // * entire rectanglar area to be considered contained within the 
     // * <code>Shape</code>.
     // * <p>
     // * This method might conservatively return <code>false</code> when:
     // * <ul>
     // * <li>
     // * the <code>intersect</code> method returns <code>true</code> and
     // * <li>
     // * the calculations to determine whether or not the
     // * <code>Shape</code> entirely contains the rectangular area are
     // * prohibitively expensive.
     // * </ul>
     // * This means that this method might return <code>false</code> even
     // * though the <code>Shape</code> contains the rectangular area.
     // * The <code>Area</code> class can be used to perform more accurate 
     // * computations of geometric intersection for any <code>Shape</code>
     // * object if a more precise answer is required.
     // * @param x the x coordinate of the specified rectangular area
     // * @param y the y coordinate of the specified rectangular area
     // * @param w the width of the specified rectangular area
     // * @param h the height of the specified rectangular area
     // * @return <code>true</code> if the interior of the <code>Shape</code>
     // * 		entirely contains the specified rectangular area;
     // * 		<code>false</code> otherwise or, if the <code>Shape</code>    
     // *		contains the rectangular area and the   
     // *		<code>intersects</code> method returns <code>true</code> 
     // * 		and the containment calculations would be too expensive to
     // * 		perform.
     // * @see java.awt.geom.Area
     // * @see #intersects
     // */
    // public boolean contains(double x, double y, double w, double h);
// 
    // /** 
     // * Tests if the interior of the <code>Shape</code> entirely contains the 
     // * specified <code>Rectangle2D</code>.
     // * This method might conservatively return <code>false</code> when:
     // * <ul>
     // * <li>
     // * the <code>intersect</code> method returns <code>true</code> and
     // * <li>
     // * the calculations to determine whether or not the
     // * <code>Shape</code> entirely contains the <code>Rectangle2D</code>
     // * are prohibitively expensive.
     // * </ul>
     // * This means that this method might return <code>false</code> even   
     // * though the <code>Shape</code> contains the
     // * <code>Rectangle2D</code>.
     // * The <code>Area</code> class can be used to perform more accurate 
     // * computations of geometric intersection for any <code>Shape</code>  
     // * object if a more precise answer is required.
     // * @param r The specified <code>Rectangle2D</code>
     // * @return <code>true</code> if the interior of the <code>Shape</code>
     // *          entirely contains the <code>Rectangle2D</code>;
     // *          <code>false</code> otherwise or, if the <code>Shape</code>
     // *          contains the <code>Rectangle2D</code> and the
     // *          <code>intersects</code> method returns <code>true</code>
     // *          and the containment calculations would be too expensive to
     // *          perform. 
     // * @see #contains(double, double, double, double)
     // */
    // public boolean contains(Rectangle2D r);
// 
    // /** 
     // * Returns an iterator object that iterates along the 
     // * <code>Shape</code> boundary and provides access to the geometry of the 
     // * <code>Shape</code> outline.  If an optional {@link AffineTransform}
     // * is specified, the coordinates returned in the iteration are
     // * transformed accordingly.
     // * <p>
     // * Each call to this method returns a fresh <code>PathIterator</code>
     // * object that traverses the geometry of the <code>Shape</code> object
     // * independently from any other <code>PathIterator</code> objects in use
     // * at the same time.
     // * <p>
     // * It is recommended, but not guaranteed, that objects 
     // * implementing the <code>Shape</code> interface isolate iterations
     // * that are in process from any changes that might occur to the original
     // * object's geometry during such iterations.
     // * <p>
     // * Before using a particular implementation of the <code>Shape</code> 
     // * interface in more than one thread simultaneously, refer to its 
     // * documentation to verify that it guarantees that iterations are isolated 
     // * from modifications.
     // * @param at an optional <code>AffineTransform</code> to be applied to the
     // * 		coordinates as they are returned in the iteration, or 
     // *		<code>null</code> if untransformed coordinates are desired
     // * @return a new <code>PathIterator</code> object, which independently    
     // *		traverses the geometry of the <code>Shape</code>.
     // */
    // public PathIterator getPathIterator(AffineTransform at);
// 
    // /** 
     // * Returns an iterator object that iterates along the <code>Shape</code>
     // * boundary and provides access to a flattened view of the
     // * <code>Shape</code> outline geometry.
     // * <p>
     // * Only SEG_MOVETO, SEG_LINETO, and SEG_CLOSE point types are
     // * returned by the iterator.
     // * <p>
     // * If an optional <code>AffineTransform</code> is specified,
     // * the coordinates returned in the iteration are transformed
     // * accordingly.
     // * <p>
     // * The amount of subdivision of the curved segments is controlled
     // * by the <code>flatness</code> parameter, which specifies the
     // * maximum distance that any point on the unflattened transformed
     // * curve can deviate from the returned flattened path segments.
     // * Note that a limit on the accuracy of the flattened path might be
     // * silently imposed, causing very small flattening parameters to be
     // * treated as larger values.  This limit, if there is one, is
     // * defined by the particular implementation that is used.
     // * <p>
     // * Each call to this method returns a fresh <code>PathIterator</code>
     // * object that traverses the <code>Shape</code> object geometry 
     // * independently from any other <code>PathIterator</code> objects in use at
     // * the same time.
     // * <p>
     // * It is recommended, but not guaranteed, that objects 
     // * implementing the <code>Shape</code> interface isolate iterations
     // * that are in process from any changes that might occur to the original
     // * object's geometry during such iterations.
     // * <p>
     // * Before using a particular implementation of this interface in more
     // * than one thread simultaneously, refer to its documentation to
     // * verify that it guarantees that iterations are isolated from
     // * modifications.
     // * @param at an optional <code>AffineTransform</code> to be applied to the
     // * 		coordinates as they are returned in the iteration, or 
     // *		<code>null</code> if untransformed coordinates are desired
     // * @param flatness the maximum distance that the line segments used to
     // *          approximate the curved segments are allowed to deviate
     // *          from any point on the original curve
     // * @return a new <code>PathIterator</code> that independently traverses 
     // * 		the <code>Shape</code> geometry.
     // */
    // public PathIterator getPathIterator(AffineTransform at, double flatness);
}
