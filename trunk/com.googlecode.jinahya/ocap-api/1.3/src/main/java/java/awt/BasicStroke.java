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

// import java.awt.geom.GeneralPath;
// import java.awt.geom.PathIterator;
// import sun.dc.path.FastPathProducer;
// import sun.dc.path.PathConsumer;
// import sun.dc.path.PathException;
// import sun.dc.pr.PathStroker;
// import sun.dc.pr.PathDasher;
// import sun.dc.pr.Rasterizer;

/** 
 * The <code>BasicStroke</code> class defines a basic set of rendering
 * attributes for the outlines of graphics primitives, which are rendered
 * with a {@link Graphics2D} object that has its Stroke attribute set to 
 * this <code>BasicStroke</code>.
 * The rendering attributes defined by <code>BasicStroke</code> describe 
 * the shape of the mark made by a pen drawn along the outline of a 
 * {@link Shape} and the decorations applied at the ends and joins of 
 * path segments of the <code>Shape</code>.
 * These rendering attributes include:
 * <dl compact>
 * <dt><i>width</i>
 * <dd>The pen width, measured perpendicularly to the pen trajectory.
 * <dt><i>end caps</i>
 * <dd>The decoration applied to the ends of unclosed subpaths.  
 * Lines that start and end on the same point are
 * considered unclosed.
 * The three different decorations are: {@link #CAP_BUTT},
 * {@link #CAP_ROUND}, and {@link #CAP_SQUARE}.
 * <dt><i>line joins</i>
 * <dd>The decoration applied at the intersection of two path segments.
 *
 * The three different decorations are: {@link #JOIN_BEVEL}, 
 * {@link #JOIN_MITER}, and {@link #JOIN_ROUND}.
 * <dt><i>miter limit</i>
 * <dd>The limit to trim a line join that has a JOIN_MITER decoration.
 * A line join is trimmed when the ratio of miter length to stroke
 * width is greater than the miterlimit value.  The miter length is  
 * the diagonal length of the miter, which is the distance between 
 * the inside corner and the outside corner of the intersection.
 * The smaller the angle formed by two line segments, the longer   
 * the miter length and the sharper the angle of intersection.  The
 * default miterlimit value of 10.0f causes all angles less than 
 * 11 degrees to be trimmed.  Trimming miters converts
 * the decoration of the line join to bevel.
 * </dl>
 * <p>
 * For more information on the user space coordinate system and the 
 * rendering process, see the <code>Graphics2D</code> class comments.
 *
 * @see Graphics2D
 * @version 1.39, 03/19/03
 * @author Jim Graham
 */
public class BasicStroke implements Stroke
{
    /** 
     * Joins path segments by extending their outside edges until
     * they meet.
     */
    public static final int JOIN_MITER = 0;

    /** 
     * Joins path segments by rounding off the corner at a radius
     * of half the line width.
     */
    public static final int JOIN_ROUND = 1;

    /** 
     * Joins path segments by connecting the outer corners of their
     * wide outlines with a straight segment.
     */
    public static final int JOIN_BEVEL = 2;

    /** 
     * Ends unclosed subpaths with no added
     * decoration.
     */
    public static final int CAP_BUTT = 0;

    /** 
     * Ends unclosed subpaths with a round
     * decoration that has a radius equal to half of the width
     * of the pen.
     */
    public static final int CAP_ROUND = 1;

    /** 
     * Ends unclosed subpaths with a square
     * projection that extends beyond the end of the segment
     * to a distance equal to half of the line width.
     */
    public static final int CAP_SQUARE = 2;

     // /** 
      // * Constructs a new <code>BasicStroke</code> with the specified
      // * attributes.
      // * @param width the width of this <code>BasicStroke</code>.  The
      // *         width must be greater than or equal to 0.0f.  If width is
      // *         set to 0.0f, the stroke is rendered as the thinnest
      // *         possible line for the target device and the antialias
      // *         hint setting.
      // * @param cap the decoration of the ends of a <code>BasicStroke</code>
      // * @param join the decoration applied where path segments meet
      // * @param miterlimit the limit to trim the miter join.  The miterlimit
      // *        must be greater than or equal to 1.0f.
      // * @param dash the array representing the dashing pattern
      // * @param dash_phase the offset to start the dashing pattern
      // * @throws IllegalArgumentException if <code>width</code> is negative
      // * @throws IllegalArgumentException if <code>cap</code> is not either
      // *         CAP_BUTT, CAP_ROUND or CAP_SQUARE
      // * @throws IllegalArgumentException if <code>miterlimit</code> is less
      // *         than 1 and <code>join</code> is JOIN_MITER
      // * @throws IllegalArgumentException if <code>join</code> is not
      // *         either JOIN_ROUND, JOIN_BEVEL, or JOIN_MITER
      // * @throws IllegalArgumentException if <code>dash_phase</code>
      // *         is negative and <code>dash</code> is not <code>null</code>
      // * @throws IllegalArgumentException if the length of
      // *         <code>dash</code> is zero
      // * @throws IllegalArgumentException if dash lengths are all zero.
      // */
     // public BasicStroke(float width, int cap, int join, float miterlimit, float[]
        // dash, float dash_phase)
    // { }
    
     /** 
      * Constructs a solid <code>BasicStroke</code> with the specified 
      * attributes.
      *
      * @param width the width of the <code>BasicStroke</code>
      * @param cap the decoration of the ends of a <code>BasicStroke</code>
      * @param join the decoration applied where path segments meet
      * @param miterlimit the limit to trim the miter join
      * @throws IllegalArgumentException if <code>width</code> is negative
      * @throws IllegalArgumentException if <code>cap</code> is not either
      *         CAP_BUTT, CAP_ROUND or CAP_SQUARE
      * @throws IllegalArgumentException if <code>miterlimit</code> is less
      *         than 1 and <code>join</code> is JOIN_MITER
      * @throws IllegalArgumentException if <code>join</code> is not
      *         either JOIN_ROUND, JOIN_BEVEL, or JOIN_MITER
      */
     public BasicStroke(float width, int cap, int join, float miterlimit) { }

    /** 
     * Constructs a solid <code>BasicStroke</code> with the specified 
     * attributes.  The <code>miterlimit</code> parameter is 
     * unnecessary in cases where the default is allowable or the 
     * line joins are not specified as JOIN_MITER.
     * 
     * @param width the width of the <code>BasicStroke</code>
     * @param cap the decoration of the ends of a <code>BasicStroke</code>
     * @param join the decoration applied where path segments meet
     * @throws IllegalArgumentException if <code>width</code> is negative
     * @throws IllegalArgumentException if <code>cap</code> is not either
     *         CAP_BUTT, CAP_ROUND or CAP_SQUARE
     * @throws IllegalArgumentException if <code>join</code> is not
     *         either JOIN_ROUND, JOIN_BEVEL, or JOIN_MITER
     */
    public BasicStroke(float width, int cap, int join) { }

    /** 
     * Constructs a solid <code>BasicStroke</code> with the specified 
     * line width and with default values for the cap and join 
     * styles.
     * 
     * @param width the width of the <code>BasicStroke</code>
     * @throws IllegalArgumentException if <code>width</code> is negative
     */
    public BasicStroke(float width) { }

    /** 
     * Constructs a new <code>BasicStroke</code> with defaults for all 
     * attributes.
     * The default attributes are a solid line of width 1.0, CAP_SQUARE,
     * JOIN_MITER, a miter limit of 10.
     */
    public BasicStroke() { }

    // /** 
     // * Returns a <code>Shape</code> whose interior defines the 
     // * stroked outline of a specified <code>Shape</code>.
     // * @param s the <code>Shape</code> boundary be stroked
     // * @return the <code>Shape</code> of the stroked outline.
     // */
    // public Shape createStrokedShape(Shape s) { }

    /** 
     * Returns the line width.  Line width is represented in user space, 
     * which is the default-coordinate space used by Java 2D.  See the
     * <code>Graphics2D</code> class comments for more information on
     * the user space coordinate system.
     * @return the line width of this <code>BasicStroke</code>.
     * @see Graphics2D
     */
    public float getLineWidth() { return 0; }

    /** 
     * Returns the end cap style.
     * @return the end cap style of this <code>BasicStroke</code> as one
     * of the static <code>int</code> values that define possible end cap
     * styles.
     */
    public int getEndCap() { return 0; }

    /** 
     * Returns the line join style.
     * @return the line join style of the <code>BasicStroke</code> as one
     * of the static <code>int</code> values that define possible line
     * join styles.
     */
    public int getLineJoin() { return 0; }

    /** 
     * Returns the limit of miter joins.
     * @return the limit of miter joins of the <code>BasicStroke</code>.
     */
    public float getMiterLimit() { return 0; }

    // /** 
     // * Returns the array representing the lengths of the dash segments.
     // * Alternate entries in the array represent the user space lengths
     // * of the opaque and transparent segments of the dashes.
     // * As the pen moves along the outline of the <code>Shape</code>
     // * to be stroked, the user space
     // * distance that the pen travels is accumulated.  The distance
     // * value is used to index into the dash array.
     // * The pen is opaque when its current cumulative distance maps
     // * to an even element of the dash array and transparent otherwise.
     // * @return the dash array.
     // */
    // public float[] getDashArray() {return null;  }
// 
    // /** 
     // * Returns the current dash phase.
     // * The dash phase is a distance specified in user coordinates that 
     // * represents an offset into the dashing pattern. In other words, the dash 
     // * phase defines the point in the dashing pattern that will correspond to 
     // * the beginning of the stroke.
     // * @return the dash phase as a <code>float</code> value.
     // */
    // public float getDashPhase() { return 0; }

    /** 
     * Returns the hashcode for this stroke.
     * @return      a hash code for this stroke.
     */
    public int hashCode() {return 0;  }

    /** 
     * Tests if a specified object is equal to this <code>BasicStroke</code>
     * by first testing if it is a <code>BasicStroke</code> and then comparing 
* its width, join, cap, and miter limit attributes with
     * those of this <code>BasicStroke</code>.
     * @param  obj the specified object to compare to this 
     *              <code>BasicStroke</code>
     * @return <code>true</code> if the width, join, cap, miter limit, dash, and
     *            dash phase are the same for both objects;
     *            <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {return false;  }
}
