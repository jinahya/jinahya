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

import java.util.*;

import java.text.AttributedCharacterIterator.Attribute;

/** 
 * An AttributedString holds text and related attribute information. It
 * may be used as the actual data storage in some cases where a text
 * reader wants to access attributed text through the AttributedCharacterIterator
 * interface.
 *
 * @see AttributedCharacterIterator
 * @see Annotation
 * @since 1.2
 */
public class AttributedString
{

    /** 
     * Constructs an AttributedString instance with the given text.
     * @param text The text for this attributed string.
     */
    public AttributedString(String text) { }

    /** 
     * Constructs an AttributedString instance with the given text and attributes.
     * @param text The text for this attributed string.
     * @param attributes The attributes that apply to the entire string.
     * @exception IllegalArgumentException if the text has length 0
     * and the attributes parameter is not an empty Map (attributes
     * cannot be applied to a 0-length range).
     */
    public AttributedString(String text, Map attributes) { }

    /** 
     * Constructs an AttributedString instance with the given attributed
     * text represented by AttributedCharacterIterator.
     * @param text The text for this attributed string.
     */
    public AttributedString(AttributedCharacterIterator text) { }

    /** 
     * Constructs an AttributedString instance with the subrange of
     * the given attributed text represented by
     * AttributedCharacterIterator. If the given range produces an
     * empty text, all attributes will be discarded.  Note that any
     * attributes wrapped by an Annotation object are discarded for a
     * subrange of the original attribute range.
     *
     * @param text The text for this attributed string.
     * @param beginIndex Index of the first character of the range.
     * @param endIndex Index of the character following the last character
     * of the range.
     * @exception IllegalArgumentException if the subrange given by
     * beginIndex and endIndex is out of the text range.
     * @see java.text.Annotation
     */
    public AttributedString(AttributedCharacterIterator text, int beginIndex,
        int endIndex)
    { }

    /** 
     * Constructs an AttributedString instance with the subrange of
     * the given attributed text represented by
     * AttributedCharacterIterator.  Only attributes that match the
     * given attributes will be incorporated into the instance. If the
     * given range produces an empty text, all attributes will be
     * discarded. Note that any attributes wrapped by an Annotation
     * object are discarded for a subrange of the original attribute
     * range.
     *
     * @param text The text for this attributed string.
     * @param beginIndex Index of the first character of the range.
     * @param endIndex Index of the character following the last character
     * of the range.
     * @param attributes Specifies attributes to be extracted
     * from the text. If null is specified, all available attributes will
     * be used.
     * @exception IllegalArgumentException if the subrange given by
     * beginIndex and endIndex is out of the text range.
     * @see java.text.Annotation
     */
    public AttributedString(AttributedCharacterIterator text, int beginIndex,
        int endIndex, java.text.AttributedCharacterIterator.Attribute[]
        attributes)
    { }

    /** 
     * Adds an attribute to the entire string.
     * @param attribute the attribute key
     * @param value the value of the attribute; may be null
     * @exception IllegalArgumentException if the AttributedString has length 0
     * (attributes cannot be applied to a 0-length range).
     */
    public void addAttribute(java.text.AttributedCharacterIterator.Attribute
        attribute, Object value)
    { }

    /** 
     * Adds an attribute to a subrange of the string.
     * @param attribute the attribute key
     * @param value The value of the attribute. May be null.
     * @param beginIndex Index of the first character of the range.
     * @param endIndex Index of the character following the last character of the range.
     * @exception IllegalArgumentException if beginIndex is less then 0, endIndex is
     * greater than the length of the string, or beginIndex and endIndex together don't
     * define a non-empty subrange of the string.
     */
    public void addAttribute(java.text.AttributedCharacterIterator.Attribute
        attribute, Object value, int beginIndex, int endIndex)
    { }

    /** 
     * Adds a set of attributes to a subrange of the string.
     * @param attributes The attributes to be added to the string.
     * @param beginIndex Index of the first character of the range.
     * @param endIndex Index of the character following the last
     * character of the range.
     * @exception IllegalArgumentException if beginIndex is less then
     * 0, endIndex is greater than the length of the string, or
     * beginIndex and endIndex together don't define a non-empty
     * subrange of the string and the attributes parameter is not an
     * empty Map.
     */
    public void addAttributes(Map attributes, int beginIndex, int endIndex) { }

    /** 
     * Creates an AttributedCharacterIterator instance that provides access to the entire contents of
     * this string.
     *
     * @return An iterator providing access to the text and its attributes.
     */
    public AttributedCharacterIterator getIterator() {
        return null;
    }

    /** 
     * Creates an AttributedCharacterIterator instance that provides access to
     * selected contents of this string.
     * Information about attributes not listed in attributes that the
     * implementor may have need not be made accessible through the iterator.
     * If the list is null, all available attribute information should be made
     * accessible.
     *
     * @param attributes a list of attributes that the client is interested in
     * @return an iterator providing access to the text and its attributes
     */
    public AttributedCharacterIterator
        getIterator(java.text.AttributedCharacterIterator.Attribute[]
        attributes)
    {
        return null;
    }

    /** 
     * Creates an AttributedCharacterIterator instance that provides access to
     * selected contents of this string.
     * Information about attributes not listed in attributes that the
     * implementor may have need not be made accessible through the iterator.
     * If the list is null, all available attribute information should be made
     * accessible.
     *
     * @param attributes a list of attributes that the client is interested in
     * @param beginIndex the index of the first character
     * @param endIndex the index of the character following the last character
     * @return an iterator providing access to the text and its attributes
     * @exception IllegalArgumentException if beginIndex is less then 0,
     * endIndex is greater than the length of the string, or beginIndex is
     * greater than endIndex.
     */
    public AttributedCharacterIterator
        getIterator(java.text.AttributedCharacterIterator.Attribute[]
        attributes, int beginIndex, int endIndex)
    {
        return null;
    }
}
