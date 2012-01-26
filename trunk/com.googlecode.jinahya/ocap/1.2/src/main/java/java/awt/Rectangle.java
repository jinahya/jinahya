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

// import java.awt.geom.Rectangle2D;

/** 
 * A <code>Rectangle</code> specifies an area in a coordinate space that is 
 * enclosed by the <code>Rectangle</code> object's top-left point 
 * (<i>x</i>,&nbsp;<i>y</i>) 
 * in the coordinate space, its width, and its height. 
 * <p>
 * A <code>Rectangle</code> object's <code>width</code> and
 * <code>height</code> are <code>public</code> fields. The constructors 
 * that create a <code>Rectangle</code>, and the methods that can modify 
 * one, do not prevent setting a negative value for width or height. 
 * <p>
 * A <code>Rectangle</code> whose width or height is negative is considered 
 * empty. If the <code>Rectangle</code> is empty, then the  
 * <code>isEmpty</code> method returns <code>true</code>. No point can be 
 * contained by or inside an empty <code>Rectangle</code>.  The 
 * values of <code>width</code> and <code>height</code>, however, are still 
 * valid.  An empty <code>Rectangle</code> still has a location in the 
 * coordinate space, and methods that change its size or location remain 
 * valid. The behavior of methods that operate on more than one 
 * <code>Rectangle</code> is undefined if any of the participating 
 * <code>Rectangle</code> objects has a negative 
 * <code>width</code> or <code>height</code>. These methods include 
 * <code>intersects</code>, <code>intersection</code>, and 
 * <code>union</code>. 
 *
 * @version 	1.65, 01/23/03
 * @author 	Sami Shaio
 * @since       JDK1.0
 */
public class Rectangle implements Shape, java.io.Serializable, Cloneable
// public class Rectangle extends Rectangle2D
    // implements Shape, java.io.Serializable
{
    /** 
     * The <i>x</i> coordinate of the <code>Rectangle</code>.
     *
     * @serial
     * @see #setLocation(int, int) 
     * @see #getLocation()
     */
    public int x;

    /** 
     * The <i>y</i> coordinate of the <code>Rectangle</code>.
     * 
     * @serial
     * @see #setLocation(int, int)
     * @see #getLocation()
     */
    public int y;

    /** 
     * The width of the <code>Rectangle</code>.
     * @serial
     * @see #setSize(int, int)
     * @see #getSize()
     * @since     JDK1.0.
     */
    public int width;

    /** 
     * The height of the <code>Rectangle</code>.
     * 
     * @serial
     * @see #setSize(int, int)
     * @see #getSize()
     */
    public int height;

    /*
     * JDK 1.1 serialVersionUID 
     */
     private static final long serialVersionUID = -4345857070255674764L;
       
    /** 
     * Constructs a new <code>Rectangle</code> whose top-left corner 
     * is at (0,&nbsp;0) in the coordinate space, and whose width and 
     * height are both zero. 
     */
    public Rectangle() { }

    /** 
     * Constructs a new <code>Rectangle</code>, initialized to match 
     * the values of the specified <code>Rectangle</code>.
     * @param r  the <code>Rectangle</code> from which to copy initial values
     *           to a newly constructed <code>Rectangle</code>
     * @since JDK1.1
     */
    public Rectangle(Rectangle r) { }

    /** 
     * Constructs a new <code>Rectangle</code> whose top-left corner is 
     * specified as
     * (<code>x</code>,&nbsp;<code>y</code>) and whose width and height 
     * are specified by the arguments of the same name. 
     * @param     x the specified x coordinate
     * @param     y the specified y coordinate
     * @param     width    the width of the <code>Rectangle</code>
     * @param     height   the height of the <code>Rectangle</code>
     */
    public Rectangle(int x, int y, int width, int height) { }

    /** 
     * Constructs a new <code>Rectangle</code> whose top-left corner 
     * is at (0,&nbsp;0) in the coordinate space, and whose width and 
     * height are specified by the arguments of the same name. 
     * @param width the width of the <code>Rectangle</code>
     * @param height the height of the <code>Rectangle</code>
     */
    public Rectangle(int width, int height) { }

    /** 
     * Constructs a new <code>Rectangle</code> whose top-left corner is 
     * specified by the {@link Point} argument, and
     * whose width and height are specified by the 
     * {@link Dimension} argument. 
     * @param p a <code>Point</code> that is the top-left corner of 
     * the <code>Rectangle</code>
     * @param d a <code>Dimension</code>, representing the 
     * width and height of the <code>Rectangle</code>
     */
    public Rectangle(Point p, Dimension d) { }

    /** 
     * Constructs a new <code>Rectangle</code> whose top-left corner is the  
     * specified <code>Point</code>, and whose width and height are both zero. 
     * @param p a <code>Point</code> that is the top left corner 
     * of the <code>Rectangle</code>
     */
    public Rectangle(Point p) { }

    /** 
     * Constructs a new <code>Rectangle</code> whose top left corner is  
     * (0,&nbsp;0) and whose width and height are specified  
     * by the <code>Dimension</code> argument. 
     * @param d a <code>Dimension</code>, specifying width and height
     */
    public Rectangle(Dimension d) { }

    // /** 
     // * Returns the X coordinate of the bounding <code>Rectangle</code> in 
     // * <code>double</code> precision.
     // * @return the x coordinate of the bounding <code>Rectangle</code>. 
     // */
    // public double getX() { }
// 
    // /** 
     // * Returns the Y coordinate of the bounding <code>Rectangle</code> in 
     // * <code>double</code> precision.
     // * @return the y coordinate of the bounding <code>Rectangle</code>.    
     // */
    // public double getY() { }

    // /** 
     // * Returns the width of the bounding <code>Rectangle</code> in 
     // * <code>double</code> precision.
     // * @return the width of the bounding <code>Rectangle</code>. 
     // */
    // public double getWidth() { }
// 
    // /** 
     // * Returns the height of the bounding <code>Rectangle</code> in 
     // * <code>double</code> precision.
     // * @return the height of the bounding <code>Rectangle</code>. 
     // */
    // public double getHeight() { }

    /** 
     * Gets the bounding <code>Rectangle</code> of this <code>Rectangle</code>.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>getBounds</code> method of 
     * {@link Component}.
     * @return    a new <code>Rectangle</code>, equal to the 
     * bounding <code>Rectangle</code> for this <code>Rectangle</code>.
     * @see       java.awt.Component#getBounds
     * @see       #setBounds(Rectangle)
     * @see       #setBounds(int, int, int, int)
     * @since     JDK1.1
     */
    public Rectangle getBounds() { return null; }

    // /** 
     // * Return the high precision bounding box of this rectangle.
     // * @since 1.2
     // */
    // public Rectangle2D getBounds2D() { }

    /** 
     * Sets the bounding <code>Rectangle</code> of this <code>Rectangle</code> 
     * to match the specified <code>Rectangle</code>.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setBounds</code> method of <code>Component</code>.
     * @param r the specified <code>Rectangle</code>
     * @see       #getBounds
     * @see	  java.awt.Component#setBounds(java.awt.Rectangle)
     * @since     JDK1.1
     */
    public void setBounds(Rectangle r) { }

    /** 
     * Sets the bounding <code>Rectangle</code> of this 
     * <code>Rectangle</code> to the specified 
     * <code>x</code>, <code>y</code>, <code>width</code>, 
     * and <code>height</code>.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setBounds</code> method of <code>Component</code>.
     * @param x the new x coordinate for the top-left
     *                    corner of this <code>Rectangle</code>
     * @param y the new y coordinate for the top-left
     *                    corner of this <code>Rectangle</code>
     * @param width the new width for this <code>Rectangle</code>
     * @param height the new height for this <code>Rectangle</code>
     * @see       #getBounds
     * @see       java.awt.Component#setBounds(int, int, int, int)
     * @since     JDK1.1
     */
    public void setBounds(int x, int y, int width, int height) { }

    // /** 
     // * Sets the bounds of this <code>Rectangle</code> to the specified 
     // * <code>x</code>, <code>y</code>, <code>width</code>, 
     // * and <code>height</code>.
     // * This method is included for completeness, to parallel the
     // * <code>setBounds</code> method of <code>Component</code>.
     // * @param x the x coordinate of the upper-left corner of 
     // *                  the specified rectangle
     // * @param y the y coordinate of the upper-left corner of 
     // *                  the specified rectangle
     // * @param width the new width for the <code>Dimension</code> object
     // * @param height  the new height for the <code>Dimension</code> object
     // */
    // public void setRect(double x, double y, double width, double height) { }

//    /** 
//     * Sets the bounding <code>Rectangle</code> of this 
//     * <code>Rectangle</code> to the specified 
//     * <code>x</code>, <code>y</code>, <code>width</code>, 
//     * and <code>height</code>.
//     * <p>
//     * @param x the new x coordinate for the top-left
//     *                    corner of this <code>Rectangle</code>
//     * @param y the new y coordinate for the top-left
//     *                    corner of this <code>Rectangle</code>
//     * @param width the new width for this <code>Rectangle</code>
//     * @param height the new height for this <code>Rectangle</code>
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>setBounds(int, int, int, int)</code>.
//     */
//    public void reshape(int x, int y, int width, int height) { }

    /** 
     * Returns the location of this <code>Rectangle</code>.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>getLocation</code> method of <code>Component</code>.
     * @return the <code>Point</code> that is the top-left corner of
     *			this <code>Rectangle</code>. 
     * @see       java.awt.Component#getLocation
     * @see       #setLocation(Point)
     * @see       #setLocation(int, int)
     * @since     JDK1.1
     */
    public Point getLocation() { return null; }

    /** 
     * Moves this <code>Rectangle</code> to the specified location.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setLocation</code> method of <code>Component</code>.
     * @param p the <code>Point</code> specifying the new location
     *                for this <code>Rectangle</code>
     * @see       java.awt.Component#setLocation(java.awt.Point)
     * @see       #getLocation
     * @since     JDK1.1
     */
    public void setLocation(Point p) { }

    /** 
     * Moves this <code>Rectangle</code> to the specified location.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setLocation</code> method of <code>Component</code>.
     * @param x the x coordinate of the new location
     * @param y the y coordinate of the new location
     * @see       #getLocation
     * @see	  java.awt.Component#setLocation(int, int)
     * @since     JDK1.1
     */
    public void setLocation(int x, int y) { }

//    /** 
//     * Moves this <code>Rectangle</code> to the specified location.
//     * <p>
//     * @param x the x coordinate of the new location
//     * @param y the y coordinate of the new location
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>setLocation(int, int)</code>.
//     */
//    public void move(int x, int y) { }

    /** 
     * Translates this <code>Rectangle</code> the indicated distance,
     * to the right along the x coordinate axis, and 
     * downward along the y coordinate axis.
     * @param x the distance to move this <code>Rectangle</code> 
     *                 along the x axis
     * @param y the distance to move this <code>Rectangle</code> 
     *                 along the y axis
     * @see       java.awt.Rectangle#setLocation(int, int)
     * @see       java.awt.Rectangle#setLocation(java.awt.Point)
     */
    public void translate(int x, int y) { }

    /** 
     * Gets the size of this <code>Rectangle</code>, represented by 
     * the returned <code>Dimension</code>.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>getSize</code> method of <code>Component</code>.
     * @return a <code>Dimension</code>, representing the size of
     *            this <code>Rectangle</code>.
     * @see       java.awt.Component#getSize
     * @see       #setSize(Dimension)
     * @see       #setSize(int, int)
     * @since     JDK1.1
     */
    public Dimension getSize() { return null; }

    /** 
     * Sets the size of this <code>Rectangle</code> to match the 
     * specified <code>Dimension</code>.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setSize</code> method of <code>Component</code>.
     * @param d the new size for the <code>Dimension</code> object
     * @see       java.awt.Component#setSize(java.awt.Dimension)
     * @see       #getSize
     * @since     JDK1.1
     */
    public void setSize(Dimension d) { }

    /** 
     * Sets the size of this <code>Rectangle</code> to the specified 
     * width and height.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setSize</code> method of <code>Component</code>.
     * @param width the new width for this <code>Rectangle</code>
     * @param height the new height for this <code>Rectangle</code>
     * @see       java.awt.Component#setSize(int, int)
     * @see       #getSize
     * @since     JDK1.1
     */
    public void setSize(int width, int height) { }

//    /** 
//     * Sets the size of this <code>Rectangle</code> to the specified 
//     * width and height.
//     * <p>
//     * @param width the new width for this <code>Rectangle</code>
//     * @param height the new height for this <code>Rectangle</code>
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>setSize(int, int)</code>.
//     */
//    public void resize(int width, int height) { }

    /** 
     * Checks whether or not this <code>Rectangle</code> contains the 
     * specified <code>Point</code>.
     * @param p the <code>Point</code> to test
     * @return    <code>true</code> if the <code>Point</code> 
     *            (<i>x</i>,&nbsp;<i>y</i>) is inside this 
     * 		  <code>Rectangle</code>; 
     *            <code>false</code> otherwise.
     * @since     JDK1.1
     */
    public boolean contains(Point p) { return false; }

    /** 
     * Checks whether or not this <code>Rectangle</code> contains the 
     * point at the specified location
     * (<i>x</i>,&nbsp;<i>y</i>).
     * @param  x the specified x coordinate
     * @param  y the specified y coordinate
     * @return    <code>true</code> if the point 
     *            (<i>x</i>,&nbsp;<i>y</i>) is inside this 
     *		  <code>Rectangle</code>; 
     *            <code>false</code> otherwise.
     * @since     JDK1.1
     */
    public boolean contains(int x, int y) { return false; }

    /** 
     * Checks whether or not this <code>Rectangle</code> entirely contains 
     * the specified <code>Rectangle</code>.
     * @param     r   the specified <code>Rectangle</code>
     * @return    <code>true</code> if the <code>Rectangle</code> 
     *            is contained entirely inside this <code>Rectangle</code>; 
     *            <code>false</code> otherwise.
     * @since     JDK1.1
     */
    public boolean contains(Rectangle r) { return false; }

    /** 
     * Checks whether this <code>Rectangle</code> entirely contains 
     * the <code>Rectangle</code>
     * at the specified location (<i>X</i>,&nbsp;<i>Y</i>) with the
     * specified dimensions (<i>W</i>,&nbsp;<i>H</i>).
     * @param     X the specified x coordinate
     * @param     Y the specified y coordinate
     * @param     W   the width of the <code>Rectangle</code>
     * @param     H   the height of the <code>Rectangle</code>
     * @return    <code>true</code> if the <code>Rectangle</code> specified by
     *            (<i>X</i>,&nbsp;<i>Y</i>,&nbsp;<i>W</i>,&nbsp;<i>H</i>)
     *            is entirely enclosed inside this <code>Rectangle</code>; 
     *            <code>false</code> otherwise.
     * @since     JDK1.1
     */
    public boolean contains(int X, int Y, int W, int H) { return false; }

//    /** 
//     * Checks whether or not this <code>Rectangle</code> contains the 
//     * point at the specified location
//     * (<i>X</i>,&nbsp;<i>Y</i>).
//     * @param  X the specified x coordinate
//     * @param  Y the specified y coordinate
//     * @return    <code>true</code> if the point 
//     *            (<i>X</i>,&nbsp;<i>Y</i>) is inside this 
//     *		  <code>Rectangle</code>; 
//     *            <code>false</code> otherwise.
//     * @deprecated As of JDK version 1.1,
//     * replaced by <code>contains(int, int)</code>.
//     */
//    public boolean inside(int X, int Y) { return false; }

    /** 
     * Determines whether or not this <code>Rectangle</code> and the specified 
     * <code>Rectangle</code> intersect. Two rectangles intersect if 
     * their intersection is nonempty. 
     *
     * @param r the specified <code>Rectangle</code>
     * @return    <code>true</code> if the specified <code>Rectangle</code> 
     *            and this <code>Rectangle</code> intersect; 
     *            <code>false</code> otherwise.
     */
    public boolean intersects(Rectangle r) { return false; }

    /** 
     * Computes the intersection of this <code>Rectangle</code> with the 
     * specified <code>Rectangle</code>. Returns a new <code>Rectangle</code> 
     * that represents the intersection of the two rectangles.
     * If the two rectangles do not intersect, the result will be
     * an empty rectangle.
     *
     * @param     r   the specified <code>Rectangle</code>
     * @return    the largest <code>Rectangle</code> contained in both the 
     *            specified <code>Rectangle</code> and in 
     *		  this <code>Rectangle</code>; or if the rectangles
     *            do not intersect, an empty rectangle.
     */
    public Rectangle intersection(Rectangle r) { return null; }

    /** 
     * Computes the union of this <code>Rectangle</code> with the 
     * specified <code>Rectangle</code>. Returns a new 
     * <code>Rectangle</code> that 
     * represents the union of the two rectangles
     * @param r the specified <code>Rectangle</code>
     * @return    the smallest <code>Rectangle</code> containing both 
     *		  the specified <code>Rectangle</code> and this 
     *		  <code>Rectangle</code>.
     */
    public Rectangle union(Rectangle r) { return null; }

    /** 
     * Adds a point, specified by the integer arguments <code>newx</code>
     * and <code>newy</code>, to this <code>Rectangle</code>. The 
     * resulting <code>Rectangle</code> is
     * the smallest <code>Rectangle</code> that contains both the 
     * original <code>Rectangle</code> and the specified point.
     * <p>
     * After adding a point, a call to <code>contains</code> with the 
     * added point as an argument does not necessarily return
     * <code>true</code>. The <code>contains</code> method does not 
     * return <code>true</code> for points on the right or bottom 
     * edges of a <code>Rectangle</code>. Therefore, if the added point 
     * falls on the right or bottom edge of the enlarged 
     * <code>Rectangle</code>, <code>contains</code> returns 
     * <code>false</code> for that point.
     * @param newx the x coordinate of the new point
     * @param newy the y coordinate of the new point
     */
    public void add(int newx, int newy) { }

    /** 
     * Adds the specified <code>Point</code> to this 
     * <code>Rectangle</code>. The resulting <code>Rectangle</code> 
     * is the smallest <code>Rectangle</code> that contains both the 
     * original <code>Rectangle</code> and the specified 
     * <code>Point</code>.
     * <p>
     * After adding a <code>Point</code>, a call to <code>contains</code> 
     * with the added <code>Point</code> as an argument does not 
     * necessarily return <code>true</code>. The <code>contains</code> 
     * method does not return <code>true</code> for points on the right 
     * or bottom edges of a <code>Rectangle</code>. Therefore if the added 
     * <code>Point</code> falls on the right or bottom edge of the 
     * enlarged <code>Rectangle</code>, <code>contains</code> returns 
     * <code>false</code> for that <code>Point</code>.
     * @param pt the new <code>Point</code> to add to this 
     *           <code>Rectangle</code>
     */
    public void add(Point pt) { }

    /** 
     * Adds a <code>Rectangle</code> to this <code>Rectangle</code>. 
     * The resulting <code>Rectangle</code> is the union of the two
     * rectangles. 
     * @param  r the specified <code>Rectangle</code>
     */
    public void add(Rectangle r) { }

    /** 
     * Resizes the <code>Rectangle</code> both horizontally and vertically.
     * <p>
     * This method modifies the <code>Rectangle</code> so that it is 
     * <code>h</code> units larger on both the left and right side, 
     * and <code>v</code> units larger at both the top and bottom. 
     * <p>
     * The new <code>Rectangle</code> has (<code>x&nbsp;-&nbsp;h</code>, 
     * <code>y&nbsp;-&nbsp;v</code>) as its top-left corner, a 
     * width of 
     * <code>width</code>&nbsp;<code>+</code>&nbsp;<code>2h</code>, 
     * and a height of 
     * <code>height</code>&nbsp;<code>+</code>&nbsp;<code>2v</code>. 
     * <p>
     * If negative values are supplied for <code>h</code> and 
     * <code>v</code>, the size of the <code>Rectangle</code> 
     * decreases accordingly. 
     * The <code>grow</code> method does not check whether the resulting 
     * values of <code>width</code> and <code>height</code> are 
     * non-negative. 
     * @param h the horizontal expansion
     * @param v the vertical expansion
     */
    public void grow(int h, int v) { }

    /** 
     * Determines whether or not this <code>Rectangle</code> is empty. A 
     * <code>Rectangle</code> is empty if its width or its height is less 
     * than or equal to zero. 
     * @return     <code>true</code> if this <code>Rectangle</code> is empty; 
     *             <code>false</code> otherwise.
     */
    public boolean isEmpty() { return false; }

    // /** 
     // * Determines where the specified coordinates lie with respect
     // * to this <code>Rectangle</code>.  
     // * This method computes a binary OR of the appropriate mask values
     // * indicating, for each side of this <code>Rectangle</code>, 
     // * whether or not the specified coordinates are on the same side of the
     // * edge as the rest of this <code>Rectangle</code>.
     // * @param x the specified x coordinate
     // * @param y the specified y coordinate
     // * @return the logical OR of all appropriate out codes.
     // * @see #OUT_LEFT
     // * @see #OUT_TOP
     // * @see #OUT_RIGHT
     // * @see #OUT_BOTTOM
     // * @since 1.2
     // */
    // public int outcode(double x, double y) { }

    // /** 
     // * Returns a new {@link Rectangle2D} object
     // * representing the intersection of this <code>Rectangle</code> with the 
     // * specified <code>Rectangle2D</code>.
     // * @param r the <code>Rectangle2D</code> to be intersected 
     // * 			with this <code>Rectangle</code>
     // * @return    the largest <code>Rectangle2D</code> contained in both the 
     // *            specified <code>Rectangle2D</code> and in 
     // *		  this <code>Rectangle</code>.
     // * @since 1.2
     // */
    // public Rectangle2D createIntersection(Rectangle2D r) { }
// 
    // /** 
     // * Returns a new <code>Rectangle2D</code> object representing the
     // * union of this <code>Rectangle</code> with the specified 
     // * <code>Rectangle2D</code>.
     // * @param r the <code>Rectangle2D</code> to be combined with
     // *           this <code>Rectangle</code>
     // * @return    the smallest <code>Rectangle2D</code> containing 
     // * 		  both the specified <code>Rectangle2D</code> and this 
     // *            <code>Rectangle</code>.
     // * @since 1.2
     // */
    // public Rectangle2D createUnion(Rectangle2D r) { }

    /** 
     * Checks whether two rectangles are equal.
     * <p>
     * The result is <code>true</code> if and only if the argument is not 
     * <code>null</code> and is a <code>Rectangle</code> object that has the 
     * same top-left corner, width, and height as this <code>Rectangle</code>. 
     * @param obj the <code>Object</code> to compare with
     *                this <code>Rectangle</code>
     * @return    <code>true</code> if the objects are equal; 
     *            <code>false</code> otherwise.
     */
    public boolean equals(Object obj) { return false; }

    /** 
     * Returns a <code>String</code> representing this 
     * <code>Rectangle</code> and its values.
     * @return a <code>String</code> representing this 
     *               <code>Rectangle</code> object's coordinate and size values.
     */
    public String toString() { return null; }

    /**
     * Creates a new object of the same class and with the same
     * contents as this object.
     * @return     a clone of this instance.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        java.lang.Cloneable
     * @since      1.2
     */
    public Object clone() {
	return null;
    }
    // PBP/PP 6213239
    /** 
     * Returns the hashcode for this <code>Rectangle</code>.
     * @return      a hash code for this <code>Rectangle</code>.
     */
    public int hashCode() {
        return 0;
    }

}
