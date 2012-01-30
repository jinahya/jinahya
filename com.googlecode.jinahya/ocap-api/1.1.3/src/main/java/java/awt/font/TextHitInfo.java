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


  


package java.awt.font;

import java.lang.String;

/** 
 * The <code>TextHitInfo</code> class represents a character position in a
 * text model, and a <b>bias</b>, or "side," of the character.  Biases are
 * either <EM>leading</EM> (the left edge, for a left-to-right character)
 * or <EM>trailing</EM> (the right edge, for a left-to-right character).
 * Instances of <code>TextHitInfo</code> are used to specify caret and
 * insertion positions within text.
 * <p>
 * For example, consider the text "abc".  TextHitInfo.trailing(1)
 * corresponds to the right side of the 'b' in the text.
<!-- PBP/PP -->

 * <p>
 * Sometimes it is convenient to construct a <code>TextHitInfo</code> with
 * the same insertion offset as an existing one, but on the opposite
 * character.  The <code>getOtherHit</code> method constructs a new
 * <code>TextHitInfo</code> with the same insertion offset as an existing 
 * one, with a hit on the character on the other side of the insertion offset. 
 * Calling <code>getOtherHit</code> on trailing(1) would return leading(2).
 * In general, <code>getOtherHit</code> for trailing(n) returns 
 * leading(n+1) and <code>getOtherHit</code> for leading(n) 
 * returns trailing(n-1).
<!-- PBP/PP -->

 *
<!-- PBP/PP -->
<!-- [6187217] -->
 * 
 * 
 */
public final class TextHitInfo
{

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private TextHitInfo() { }

    /** 
     * Returns the index of the character hit.
     * @return the index of the character hit.
     */
    public int getCharIndex() {
        return 0;
    }

    /** 
     * Returns <code>true</code> if the leading edge of the character was
     * hit.
     * @return <code>true</code> if the leading edge of the character was
     * hit; <code>false</code> otherwise.
     */
    public boolean isLeadingEdge() {
        return false;
    }

    /** 
     * Returns the insertion index.  This is the character index if 
     * the leading edge of the character was hit, and one greater 
     * than the character index if the trailing edge was hit.
     * @return the insertion index.
     */
    public int getInsertionIndex() {
        return 0;
    }

    /** 
     * Returns the hash code.
     * @return the hash code of this <code>TextHitInfo</code>, which is
     * also the <code>charIndex</code> of this <code>TextHitInfo</code>.
     */
    public int hashCode() {
        return 0;
    }

  // PBP/PP
  // [5093408] : Copied language from equals(TextHitInfo)

    /** 
     * Returns <code>true</code> if the specified <code>Object</code> is a
     * <code>TextHitInfo</code> and equals this <code>TextHitInfo</code>. 
     * <em>
     * A <code>TextHitInfo</code> is equal to this <code>TextHitInfo</code>
     * if and only if it has the same <code>charIndex</code> and
     * <code>isLeadingEdge</code>
     * as this <code>TextHitInfo</code>.  This is not the same as having
     * the same insertion offset.
     * </em>
     * @param obj the <code>Object</code> to test for equality
     * @return <code>true</code> if the specified <code>Object</code>
     * equals this <code>TextHitInfo</code>; <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Returns <code>true</code> if the specified <code>TextHitInfo</code>
     * has the same <code>charIndex</code> and <code>isLeadingEdge</code>
     * as this <code>TextHitInfo</code>.  This is not the same as having
     * the same insertion offset.
     * @param hitInfo a specified <code>TextHitInfo</code>
     * @return <code>true</code> if the specified <code>TextHitInfo</code>
     * has the same <code>charIndex</code> and <code>isLeadingEdge</code>
     * as this <code>TextHitInfo</code>.
     */
//     public boolean equals(TextHitInfo hitInfo) {
//         return false;
//     }

    /** 
     * Returns a <code>String</code> representing the hit for debugging
     * use only.
     * @return a <code>String</code> representing this
     * <code>TextHitInfo</code>.
     */
    public java.lang.String toString() {
        return null;
    }

    /** 
     * Creates a <code>TextHitInfo</code> on the leading edge of the
     * character at the specified <code>charIndex</code>.
     * @param charIndex the index of the character hit
     * @return a <code>TextHitInfo</code> on the leading edge of the
     * character at the specified <code>charIndex</code>.
     */
    public static TextHitInfo leading(int charIndex) {
        return null;
    }

    /** 
     * Creates a hit on the trailing edge of the character at 
     * the specified <code>charIndex</code>.
     * @param charIndex the index of the character hit
     * @return a <code>TextHitInfo</code> on the trailing edge of the
     * character at the specified <code>charIndex</code>.
     */
    public static TextHitInfo trailing(int charIndex) {
        return null;
    }

    /** 
     * Creates a <code>TextHitInfo</code> at the specified offset,
     * associated with the character before the offset.
     * @param offset an offset associated with the character before
     * the offset
     * @return a <code>TextHitInfo</code> at the specified offset.
     */
    public static TextHitInfo beforeOffset(int offset) {
        return null;
    }

    /** 
     * Creates a <code>TextHitInfo</code> at the specified offset,
     * associated with the character after the offset.
     * @param offset an offset associated with the character after
     * the offset
     * @return a <code>TextHitInfo</code> at the specified offset.
     */
    public static TextHitInfo afterOffset(int offset) {
        return null;
    }

    /** 
     * Creates a <code>TextHitInfo</code> on the other side of the
     * insertion point.  This <code>TextHitInfo</code> remains unchanged.
     * @return a <code>TextHitInfo</code> on the other side of the 
     * insertion point.
     */
    public TextHitInfo getOtherHit() {
        return null;
    }

    /** 
     * Creates a <code>TextHitInfo</code> whose character index is offset
     * by <code>delta</code> from the <code>charIndex</code> of this
     * <code>TextHitInfo</code>. This <code>TextHitInfo</code> remains
     * unchanged.
     * @param delta the value to offset this <code>charIndex</code>
     * @return a <code>TextHitInfo</code> whose <code>charIndex</code> is
     * offset by <code>delta</code> from the <code>charIndex</code> of 
     * this <code>TextHitInfo</code>.
     */
    public TextHitInfo getOffsetHit(int delta) {
        return null;
    }
}
