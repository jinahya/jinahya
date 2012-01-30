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
 * <code>FieldPosition</code> is a simple class used by <code>Format</code>
 * and its subclasses to identify fields in formatted output. Fields can
 * be identified in two ways:
 * <ul>
 *  <li>By an integer constant, whose names typically end with
 *      <code>_FIELD</code>. The constants are defined in the various
 *      subclasses of <code>Format</code>.
 *  <li>By a <code>Format.Field</code> constant, see <code>ERA_FIELD</code>
 *      and its friends in <code>DateFormat</code> for an example.
 * </ul>
 * <p>
 * <code>FieldPosition</code> keeps track of the position of the
 * field within the formatted output with two indices: the index
 * of the first character of the field and the index of the last
 * character of the field.
 *
 * <p>
 * One version of the <code>format</code> method in the various
 * <code>Format</code> classes requires a <code>FieldPosition</code>
 * object as an argument. You use this <code>format</code> method
 * to perform partial formatting or to get information about the
 * formatted output (such as the position of a field).
 *
 * <p>
 * If you are interested in the positions of all attributes in the
 * formatted string use the <code>Format</code> method
 * <code>formatToCharacterIterator</code>.
 *
 * @version     1.22 10/25/05
 * @author      Mark Davis
 * @see         java.text.Format
 */
public class FieldPosition
{

    /** 
     * Creates a FieldPosition object for the given field.  Fields are
     * identified by constants, whose names typically end with _FIELD,
     * in the various subclasses of Format.
     *
     * @see java.text.NumberFormat#INTEGER_FIELD
     * @see java.text.NumberFormat#FRACTION_FIELD
     * @see java.text.DateFormat#YEAR_FIELD
     * @see java.text.DateFormat#MONTH_FIELD
     */
    public FieldPosition(int field) { }

    /** 
     * Creates a FieldPosition object for the given field constant. Fields are
     * identified by constants defined in the various <code>Format</code>
     * subclasses. This is equivalent to calling
     * <code>new FieldPosition(attribute, -1)</code>.
     *
     * @param attribute Format.Field constant identifying a field
     * @since 1.4
     */
    public FieldPosition(Format.Field attribute) { }

    /** 
     * Creates a <code>FieldPosition</code> object for the given field.
     * The field is identified by an attribute constant from one of the
     * <code>Field</code> subclasses as well as an integer field ID
     * defined by the <code>Format</code> subclasses. <code>Format</code>
     * subclasses that are aware of <code>Field</code> should give precedence
     * to <code>attribute</code> and ignore <code>fieldID</code> if
     * <code>attribute</code> is not null. However, older <code>Format</code>
     * subclasses may not be aware of <code>Field</code> and rely on
     * <code>fieldID</code>. If the field has no corresponding integer
     * constant, <code>fieldID</code> should be -1.
     *
     * @param attribute Format.Field constant identifying a field
     * @param fieldID integer constantce identifying a field
     * @since 1.4
     */
    public FieldPosition(Format.Field attribute, int fieldID) { }

    /** 
     * Returns the field identifier as an attribute constant
     * from one of the <code>Field</code> subclasses. May return null if
     * the field is specified only by an integer field ID.
     *
     * @return Identifier for the field
     * @since 1.4
     */
    public Format.Field getFieldAttribute() {
        return null;
    }

    /** 
     * Retrieves the field identifier.
     */
    public int getField() {
        return 0;
    }

    /** 
     * Retrieves the index of the first character in the requested field.
     */
    public int getBeginIndex() {
        return 0;
    }

    /** 
     * Retrieves the index of the character following the last character in the
     * requested field.
     */
    public int getEndIndex() {
        return 0;
    }

    /** 
     * Sets the begin index.  For use by subclasses of Format.
     * @since 1.2
     */
    public void setBeginIndex(int bi) { }

    /** 
     * Sets the end index.  For use by subclasses of Format.
     * @since 1.2
     */
    public void setEndIndex(int ei) { }

    /** 
     * Overrides equals
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns a hash code for this FieldPosition.
     * @return a hash code value for this object
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Return a string representation of this FieldPosition.
     * @return  a string representation of this object
     */
    public String toString() {
        return null;
    }
}
