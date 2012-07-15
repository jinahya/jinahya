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

import java.io.InvalidObjectException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/** 
 * An AttributedCharacterIterator allows iteration through both text and
 * related attribute information.
 *
 * <p>
 * An attribute is a key/value pair, identified by the key.  No two
 * attributes on a given character can have the same key.
 *
 * <p>The values for an attribute are immutable, or must not be mutated
 * by clients or storage.  They are always passed by reference, and not
 * cloned.
 *
 * <p>A <em>run with respect to an attribute</em> is a maximum text range for
 * which:
 * <ul>
 * <li>the attribute is undefined or null for the entire range, or
 * <li>the attribute value is defined and has the same non-null value for the
 *     entire range.
 * </ul>
 *
 * <p>A <em>run with respect to a set of attributes</em> is a maximum text range for
 * which this condition is met for each member attribute.
 *
 * <p>The returned indexes are limited to the range of the iterator.
 *
 * <p>The returned attribute information is limited to runs that contain
 * the current character.
 *
 * <p>
 * Attribute keys are instances of AttributedCharacterIterator.Attribute and its
 * subclasses.
 *
 * @see AttributedCharacterIterator.Attribute
 * @see AttributedString
 * @see Annotation
 * @since 1.2
 */
public interface AttributedCharacterIterator extends CharacterIterator
{

    /** 
     * Returns the index of the first character of the run
     * with respect to all attributes containing the current character.
     */
    public int getRunStart();

    /** 
     * Returns the index of the first character of the run
     * with respect to the given attribute containing the current character.
     */
    public int getRunStart(java.text.AttributedCharacterIterator.Attribute
        attribute);

    /** 
     * Returns the index of the first character of the run
     * with respect to the given attributes containing the current character.
     */
    public int getRunStart(Set attributes);

    /** 
     * Returns the index of the first character following the run
     * with respect to all attributes containing the current character.
     */
    public int getRunLimit();

    /** 
     * Returns the index of the first character following the run
     * with respect to the given attribute containing the current character.
     */
    public int getRunLimit(java.text.AttributedCharacterIterator.Attribute
        attribute);

    /** 
     * Returns the index of the first character following the run
     * with respect to the given attributes containing the current character.
     */
    public int getRunLimit(Set attributes);

    /** 
     * Returns a map with the attributes defined on the current
     * character.
     */
    public Map getAttributes();

    /** 
     * Returns the value of the named attribute for the current character.
     * Returns null if the attribute is not defined.
     * @param attribute the key of the attribute whose value is requested.
     */
    public Object getAttribute(java.text.AttributedCharacterIterator.Attribute
        attribute);

    /** 
     * Returns the keys of all attributes defined on the
     * iterator's text range. The set is empty if no
     * attributes are defined.
     */
    public Set getAllAttributeKeys();

    /** 
     * Defines attribute keys that are used to identify text attributes. These
     * keys are used in AttributedCharacterIterator and AttributedString.
     * @see AttributedCharacterIterator
     * @see AttributedString
     */
    public static class Attribute implements Serializable
    {
        /** 
         * Attribute key for the language of some text.
         * <p> Values are instances of Locale.
         * @see java.util.Locale
         */
        public static final java.text.AttributedCharacterIterator.Attribute
            LANGUAGE = null;

        /** 
         * Attribute key for the reading of some text. In languages where the written form
         * and the pronunciation of a word are only loosely related (such as Japanese),
         * it is often necessary to store the reading (pronunciation) along with the
         * written form.
         * <p>Values are instances of Annotation holding instances of String.
         * @see Annotation
         * @see java.lang.String
         */
        public static final java.text.AttributedCharacterIterator.Attribute
            READING = null;

        /** 
         * Attribute key for input method segments. Input methods often break
         * up text into segments, which usually correspond to words.
         * <p>Values are instances of Annotation holding a null reference.
         * @see Annotation
         */
        public static final java.text.AttributedCharacterIterator.Attribute
            INPUT_METHOD_SEGMENT = null;

        /** 
         * The name of this Attribute. The name is used primarily by readResolve
         * to look up the corresponding predefined instance when deserializing
         * an instance.
         * @serial
         */
        private String name;

        /* Declare serialVersionUID for interoperability */
        private static final long serialVersionUID = -9142742483513960612L;

        /** 
         * Constructs an Attribute with the given name.
         */
        protected Attribute(String name) { }

        /** 
         * Compares two objects for equality. This version only returns true
         * for <code>x.equals(y)</code> if <code>x</code> and <code>y</code> refer
         * to the same object, and guarantees this for all subclasses.
         */
        public final boolean equals(Object obj) {
            return false;
        }

        /** 
         * Returns a hash code value for the object. This version is identical to
         * the one in Object, but is also final.
         */
        public final int hashCode() {
            return 0;
        }

        /** 
         * Returns a string representation of the object. This version returns the
         * concatenation of class name, "(", a name identifying the attribute and ")".
         */
        public String toString() {
            return null;
        }

        /** 
         * Returns the name of the attribute.
         */
        protected String getName() {
            return null;
        }

        /** 
         * Resolves instances being deserialized to the predefined constants.
         */
        protected Object readResolve() throws InvalidObjectException {
            return null;
        }

    }
}
