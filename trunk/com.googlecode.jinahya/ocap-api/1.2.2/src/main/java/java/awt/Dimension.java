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

// import java.awt.geom.Dimension2D;

/** 
 * The <code>Dimension</code> class encapsulates the width and 
 * height of a component (in integer precision) in a single object. 
 * The class is 
 * associated with certain properties of components. Several methods 
 * defined by the <code>Component</code> class and the 
 * <code>LayoutManager</code> interface return a 
 * <code>Dimension</code> object. 
 * <p>
 * Normally the values of <code>width</code> 
 * and <code>height</code> are non-negative integers. 
 * The constructors that allow you to create a dimension do 
 * not prevent you from setting a negative value for these properties. 
 * If the value of <code>width</code> or <code>height</code> is 
 * negative, the behavior of some methods defined by other objects is 
 * undefined. 
 * 
 * @version 	1.31, 01/23/03
 * @author 	Sami Shaio
 * @author 	Arthur van Hoff
 * @see         java.awt.Component
 * @see         java.awt.LayoutManager
 * @since       JDK1.0
 */
// public class Dimension extends Dimension2D implements java.io.Serializable
public class Dimension implements java.io.Serializable, Cloneable
{
    /** 
     * The width dimension; negative values can be used. 
     *
     * @serial
     * @see #getSize
     * @see #setSize
     */
    public int width;

    /** 
     * The height dimension; negative values can be used. 
     *
     * @serial
     * @see #getSize
     * @see #setSize
     */
    public int height;

    /*
     * JDK 1.1 serialVersionUID 
     */
     private static final long serialVersionUID = 4723952579491349524L;

    /** 
     * Creates an instance of <code>Dimension</code> with a width 
     * of zero and a height of zero. 
     */
    public Dimension() { }

    /** 
     * Creates an instance of <code>Dimension</code> whose width  
     * and height are the same as for the specified dimension. 
     *
     * @param    d   the specified dimension for the 
     *               <code>width</code> and 
     *               <code>height</code> values
     */
    public Dimension(Dimension d) { }

    /** 
     * Constructs a <code>Dimension</code> and initializes
     * it to the specified width and specified height.
     *
     * @param width the specified width 
     * @param height the specified height
     */
    public Dimension(int width, int height) { }

    // /** 
     // * Returns the width of this dimension in double precision.
     // * @return the width of this dimension in double precision
     // */
    // public double getWidth() { }
// 
    // /** 
     // * Returns the height of this dimension in double precision.
     // * @return the height of this dimension in double precision
     // */
    // public double getHeight() { }

    // /** 
     // * Sets the size of this <code>Dimension</code> object to
     // * the specified width and height in double precision.
     // * Note that if <code>width</code> or <code>height</code>
     // * are larger than <code>Integer.MAX_VALUE</code>, they will
     // * be reset to <code>Integer.MAX_VALUE</code>.
     // *
     // * @param width  the new width for the <code>Dimension</code> object
     // * @param height the new height for the <code>Dimension</code> object
     // */
    // public void setSize(double width, double height) { }

    /** 
     * Gets the size of this <code>Dimension</code> object.
     * This method is included for completeness, to parallel the
     * <code>getSize</code> method defined by <code>Component</code>.
     *
     * @return   the size of this dimension, a new instance of 
     *           <code>Dimension</code> with the same width and height
     * @see      java.awt.Dimension#setSize
     * @see      java.awt.Component#getSize
     * @since    JDK1.1
     */
    public Dimension getSize() { return null;}

    /** 
     * Sets the size of this <code>Dimension</code> object to the specified size.
     * This method is included for completeness, to parallel the
     * <code>setSize</code> method defined by <code>Component</code>.
     * @param    d  the new size for this <code>Dimension</code> object
     * @see      java.awt.Dimension#getSize
     * @see      java.awt.Component#setSize
     * @since    JDK1.1
     */
    public void setSize(Dimension d) { }

    /** 
     * Sets the size of this <code>Dimension</code> object 
     * to the specified width and height.
     * This method is included for completeness, to parallel the
     * <code>setSize</code> method defined by <code>Component</code>.
     *
     * @param    width   the new width for this <code>Dimension</code> object
     * @param    height  the new height for this <code>Dimension</code> object
     * @see      java.awt.Dimension#getSize
     * @see      java.awt.Component#setSize
     * @since    JDK1.1
     */
    public void setSize(int width, int height) { }

    /** 
     * Checks whether two dimension objects have equal values.
     */
    public boolean equals(Object obj) { return false;}

    /** 
     * Returns the hash code for this <code>Dimension</code>.
     *
     * @return    a hash code for this <code>Dimension</code>
     */
    public int hashCode() { return 0; }

    /** 
     * Returns a string representation of the values of this 
     * <code>Dimension</code> object's <code>height</code> and 
     * <code>width</code> fields. This method is intended to be used only 
     * for debugging purposes, and the content and format of the returned 
     * string may vary between implementations. The returned string may be 
     * empty but may not be <code>null</code>.
     * 
     * @return  a string representation of this <code>Dimension</code> 
     *          object
     */
    public String toString() {return null; }


    // Copied from geom.Dimension2D
    /**
     * Creates a new object of the same class as this object.
     *
     * @return     a clone of this instance.
     * @exception  OutOfMemoryError            if there is not enough memory.
     * @see        java.lang.Cloneable
     * @since      1.2
     */
    public Object clone() {
	return null;
    }
  
}
