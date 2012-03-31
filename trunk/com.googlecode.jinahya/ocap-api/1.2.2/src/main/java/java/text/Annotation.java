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
 * An Annotation object is used as a wrapper for a text attribute value if
 * the attribute has annotation characteristics. These characteristics are:
 * <ul>
 * <li>The text range that the attribute is applied to is critical to the
 * semantics of the range. That means, the attribute cannot be applied to subranges
 * of the text range that it applies to, and, if two adjacent text ranges have
 * the same value for this attribute, the attribute still cannot be applied to
 * the combined range as a whole with this value.
 * <li>The attribute or its value usually do no longer apply if the underlying text is
 * changed.
 * </ul>
 *
 * An example is grammatical information attached to a sentence:
 * For the previous sentence, you can say that "an example"
 * is the subject, but you cannot say the same about "an", "example", or "exam".
 * When the text is changed, the grammatical information typically becomes invalid.
 * Another example is Japanese reading information (yomi).
 *
 * <p>
 * Wrapping the attribute value into an Annotation object guarantees that
 * adjacent text runs don't get merged even if the attribute values are equal,
 * and indicates to text containers that the attribute should be discarded if
 * the underlying text is modified.
 *
 * @see AttributedCharacterIterator
 */
public class Annotation
{

    /** 
     * Constructs an annotation record with the given value, which
     * may be null.
     * @param value The value of the attribute
     */
    public Annotation(Object value) { }

    /** 
     * Returns the value of the attribute, which may be null.
     */
    public Object getValue() {
        return null;
    }

    /** 
     * Returns the String representation of this Annotation.
     */
    public String toString() {
        return null;
    }
}
