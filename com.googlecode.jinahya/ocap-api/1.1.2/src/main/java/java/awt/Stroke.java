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

// PBP/PP
// [6187215]

/** 
 * The <code>Stroke</code> interface allows a 
 * {@link Graphics2D} object to obtain a {@link Shape} that is the 
 * decorated outline, or stylistic representation of the outline, 
 * of the specified <code>Shape</code>.
 * 
 * <p>
 * The objects of the classes implementing <code>Stroke</code>
 * must be read-only because <code>Graphics2D</code> does not 
 * clone these objects either when they are set as an attribute 
 * with the <code>setStroke</code> method or when the 
 * <code>Graphics2D</code> object is itself cloned.
 * If a <code>Stroke</code> object is modified after it is set in
 * the <code>Graphics2D</code> context then the behavior 
 * of subsequent rendering would be undefined.
 * 
 * <p>
 * <a name="note">
 * <h4>Note:</h4>
 * <em>
 * Implementations of <code>Stroke</code> in this Profile:
 * <ul>
 * <li>The method in this interface has been removed in this profile due
 * to performance concerns. This interface was retained in this profile
 * to maintain upward compatibility Java 2 Standard Edition.
 * </ul>
 * @see BasicStroke
 * @see Graphics2D#setStroke
 * @version 1.21, 01/23/03
 */
public interface Stroke
{

    // /** 
     // * Returns an outline <code>Shape</code> which encloses the area that 
     // * should be painted when the <code>Shape</code> is stroked according 
     // * to the rules defined by the
     // * object implementing the <code>Stroke</code> interface.
     // * @param p a <code>Shape</code> to be stroked
     // * @return the stroked outline <code>Shape</code>.
     // */
    // public Shape createStrokedShape(Shape p);
}
