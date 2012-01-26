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

/** 
 * An <code>Insets</code> object is a representation of the borders 
 * of a container. It specifies the space that a container must leave 
 * at each of its edges. The space can be a border, a blank space, or 
 * a title. 
 *
 * @version 	1.28, 01/23/03
 * @author 	Arthur van Hoff
 * @author 	Sami Shaio
 * @see         java.awt.LayoutManager
 * @see         java.awt.Container
 * @since       JDK1.0
 */
public class Insets implements Cloneable, java.io.Serializable
{
    /** 
     * The inset from the top.
     * This value is added to the Top of the rectangle
     * to yield a new location for the Top.
     *
     * @serial
     * @see #clone()
     */
    public int top;

    /** 
     * The inset from the left.
     * This value is added to the Left of the rectangle
     * to yield a new location for the Left edge.
     *
     * @serial
     * @see #clone()
     */
    public int left;

    /** 
     * The inset from the bottom.
     * This value is subtracted from the Bottom of the rectangle
     * to yield a new location for the Bottom.
     *
     * @serial
     * @see #clone()
     */
    public int bottom;

    /** 
     * The inset from the right.
     * This value is subtracted from the Right of the rectangle
     * to yield a new location for the Right edge.
     *
     * @serial
     * @see #clone()
     */
    public int right;

    /*
     * JDK 1.1 serialVersionUID 
     */
    private static final long serialVersionUID = -2272572637695466749L;
  
    /** 
     * Creates and initializes a new <code>Insets</code> object with the 
     * specified top, left, bottom, and right insets. 
     * @param       top   the inset from the top.
     * @param       left   the inset from the left.
     * @param       bottom   the inset from the bottom.
     * @param       right   the inset from the right.
     */
    public Insets(int top, int left, int bottom, int right) { }

    /** 
     * Checks whether two insets objects are equal. Two instances 
     * of <code>Insets</code> are equal if the four integer values
     * of the fields <code>top</code>, <code>left</code>, 
     * <code>bottom</code>, and <code>right</code> are all equal.
     * @return      <code>true</code> if the two insets are equal;
     *                          otherwise <code>false</code>.
     * @since       JDK1.1
     */
    public boolean equals(Object obj) { return false;}

    /** 
     * Returns the hash code for this Insets.
     *
     * @return    a hash code for this Insets.
     */
    public int hashCode() { return 0;}

    /** 
     * Returns a string representation of this <code>Insets</code> object. 
     * This method is intended to be used only for debugging purposes, and 
     * the content and format of the returned string may vary between 
     * implementations. The returned string may be empty but may not be 
     * <code>null</code>.
     * 
     * @return  a string representation of this <code>Insets</code> object.
     */
    public String toString() {return null; }

    /** 
     * Create a copy of this object.
     * @return     a copy of this <code>Insets</code> object.
     */
    public Object clone() { return null;}
}
