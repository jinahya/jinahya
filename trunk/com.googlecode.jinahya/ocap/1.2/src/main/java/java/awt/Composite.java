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

import java.awt.image.ColorModel;

// PBP/PP
/** 
 * The <code>Composite</code> interface  defines the methods to compose a draw
 * primitive with the underlying graphics area.
 * After the <code>Composite</code> is set in the 
 * {@link Graphics2D} context, it combines a shape, text, or an image
 * being rendered with the colors that have already been rendered
 * according to pre-defined rules.
 * 
 * <em>The composition rules are provided by the classes implementing
 * this interface.</em>
 * 
 * <p>
 * Instances of classes implementing <code>Composite</code> must be 
 * immutable because the <code>Graphics2D</code> does not clone
 * these objects when they are set as an attribute with the 
 * <code>setComposite</code> method or when the <code>Graphics2D</code>
 * object is cloned.  This is to avoid undefined rendering behavior of 
 * <code>Graphics2D</code>, resulting from the modification of 
 * the <code>Composite</code> object after it has been set in the 
 * <code>Graphics2D</code> context.
 * <p>
 * 
 * <em>Note that use of <code>Composite</code> is
 * {@link Graphics2D#setComposite restricted} in this
 * Profile; only instances {@link AlphaComposite} may be used to set
 * the <code>Composite</code> of {@link Graphics2D}.</em>
 * @see AlphaComposite
 * 
 * @see Graphics2D#setComposite
 * @version 10 Feb 1997
 */
public interface Composite
{

    // /** 
     // * Creates a context containing state that is used to perform
     // * the compositing operation.  In a multi-threaded environment,
     // * several contexts can exist simultaneously for a single
     // * <code>Composite</code> object.
     // * @param srcColorModel  the {@link ColorModel} of the source
     // * @param dstColorModel  the <code>ColorModel</code> of the destination
     // * @param hints the hint that the context object uses to choose between
     // * rendering alternatives
     // * @return the <code>CompositeContext</code> object used to perform the
     // * compositing operation.
     // */
    // public CompositeContext createContext(ColorModel srcColorModel, ColorModel
        // dstColorModel, RenderingHints hints);
}
