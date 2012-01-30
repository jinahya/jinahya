/*
<p>This is not an official specification document, and usage is restricted.
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
This work corresponds to the API signatures of JSR 219: Foundation
Profile 1.1. In the event of a discrepency between this work and the
JSR 219 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=219, the latter takes precedence.
*/


  


package java.text;

/** 
 * <code>ParsePosition</code> is a simple class used by <code>Format</code>
 * and its subclasses to keep track of the current position during parsing.
 * The <code>parseObject</code> method in the various <code>Format</code>
 * classes requires a <code>ParsePosition</code> object as an argument.
 *
 * <p>
 * By design, as you parse through a string with different formats,
 * you can use the same <code>ParsePosition</code>, since the index parameter
 * records the current position.
 *
 * @version     1.21, 10/25/05
 * @author      Mark Davis
 * @see         java.text.Format
 */
public class ParsePosition
{

    /** 
     * Create a new ParsePosition with the given initial index.
     */
    public ParsePosition(int index) { }

    /** 
     * Retrieve the current parse position.  On input to a parse method, this
     * is the index of the character at which parsing will begin; on output, it
     * is the index of the character following the last character parsed.
     */
    public int getIndex() {
        return 0;
    }

    /** 
     * Set the current parse position.
     */
    public void setIndex(int index) { }

    /** 
     * Set the index at which a parse error occurred.  Formatters
     * should set this before returning an error code from their
     * parseObject method.  The default value is -1 if this is not set.
     * @since 1.2
     */
    public void setErrorIndex(int ei) { }

    /** 
     * Retrieve the index at which an error occurred, or -1 if the
     * error index has not been set.
     * @since 1.2
     */
    public int getErrorIndex() {
        return 0;
    }

    /** 
     * Overrides equals
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns a hash code for this ParsePosition.
     * @return a hash code value for this object
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Return a string representation of this ParsePosition.
     * @return  a string representation of this object
     */
    public String toString() {
        return null;
    }
}
