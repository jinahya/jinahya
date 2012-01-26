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
 * <code>StringCharacterIterator</code> implements the
 * <code>CharacterIterater</code> protocol for a <code>String</code>.
 * The <code>StringCharacterIterator</code> class iterates over the
 * entire <code>String</code>.
 *
 * @see CharacterIterator
 */
public final class StringCharacterIterator implements CharacterIterator
{

    /** 
     * Constructs an iterator with an initial index of 0.
     */
    public StringCharacterIterator(String text) { }

    /** 
     * Constructs an iterator with the specified initial index.
     *
     * @param  text   The String to be iterated over
     * @param  pos    Initial iterator position
     */
    public StringCharacterIterator(String text, int pos) { }

    /** 
     * Constructs an iterator over the given range of the given string, with the
     * index set at the specified position.
     *
     * @param  text   The String to be iterated over
     * @param  begin  Index of the first character
     * @param  end    Index of the character following the last character
     * @param  pos    Initial iterator position
     */
    public StringCharacterIterator(String text, int begin, int end, int pos) { }

    /** 
     * Reset this iterator to point to a new string.  This package-visible
     * method is used by other java.text classes that want to avoid allocating
     * new StringCharacterIterator objects every time their setText method
     * is called.
     *
     * @param  text   The String to be iterated over
     * @since 1.2
     */
    public void setText(String text) { }

    /** 
     * Implements CharacterIterator.first() for String.
     * @see CharacterIterator#first
     */
    public char first() {
        return ' ';
    }

    /** 
     * Implements CharacterIterator.last() for String.
     * @see CharacterIterator#last
     */
    public char last() {
        return ' ';
    }

    /** 
     * Implements CharacterIterator.setIndex() for String.
     * @see CharacterIterator#setIndex
     */
    public char setIndex(int p) {
        return ' ';
    }

    /** 
     * Implements CharacterIterator.current() for String.
     * @see CharacterIterator#current
     */
    public char current() {
        return ' ';
    }

    /** 
     * Implements CharacterIterator.next() for String.
     * @see CharacterIterator#next
     */
    public char next() {
        return ' ';
    }

    /** 
     * Implements CharacterIterator.previous() for String.
     * @see CharacterIterator#previous
     */
    public char previous() {
        return ' ';
    }

    /** 
     * Implements CharacterIterator.getBeginIndex() for String.
     * @see CharacterIterator#getBeginIndex
     */
    public int getBeginIndex() {
        return 0;
    }

    /** 
     * Implements CharacterIterator.getEndIndex() for String.
     * @see CharacterIterator#getEndIndex
     */
    public int getEndIndex() {
        return 0;
    }

    /** 
     * Implements CharacterIterator.getIndex() for String.
     * @see CharacterIterator#getIndex
     */
    public int getIndex() {
        return 0;
    }

    /** 
     * Compares the equality of two StringCharacterIterator objects.
     * @param obj the StringCharacterIterator object to be compared with.
     * @return true if the given obj is the same as this
     * StringCharacterIterator object; false otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Computes a hashcode for this iterator.
     * @return A hash code
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Creates a copy of this iterator.
     * @return A copy of this
     */
    public Object clone() {
        return null;
    }
}
