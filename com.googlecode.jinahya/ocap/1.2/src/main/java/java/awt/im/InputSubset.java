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


  


package java.awt.im;

/** 
 * Defines additional Unicode subsets for use by input methods.  Unlike the
 * UnicodeBlock subsets defined in the <code>{@link
 * java.lang.Character.UnicodeBlock}</code> class, these constants do not
 * directly correspond to Unicode code blocks.
 *
 * @version 1.10, 01/23/03
 * @since   1.2
 */
public final class InputSubset extends Character.Subset
{
    /** 
     * Constant for all Latin characters, including the characters
     * in the BASIC_LATIN, LATIN_1_SUPPLEMENT, LATIN_EXTENDED_A,
     * LATIN_EXTENDED_B Unicode character blocks.
     */
    public static final InputSubset LATIN = null;

    /** 
     * Constant for the digits included in the BASIC_LATIN Unicode character
     * block.
     */
    public static final InputSubset LATIN_DIGITS = null;

    /** 
     * Constant for all Han characters used in writing Traditional Chinese,
     * including a subset of the CJK unified ideographs as well as Traditional
     * Chinese Han characters that may be defined as surrogate characters.
     */
    public static final InputSubset TRADITIONAL_HANZI = null;

    /** 
     * Constant for all Han characters used in writing Simplified Chinese,
     * including a subset of the CJK unified ideographs as well as Simplified
     * Chinese Han characters that may be defined as surrogate characters.
     */
    public static final InputSubset SIMPLIFIED_HANZI = null;

    /** 
     * Constant for all Han characters used in writing Japanese, including a
     * subset of the CJK unified ideographs as well as Japanese Han characters
     * that may be defined as surrogate characters.
     */
    public static final InputSubset KANJI = null;

    /** 
     * Constant for all Han characters used in writing Korean, including a
     * subset of the CJK unified ideographs as well as Korean Han characters
     * that may be defined as surrogate characters.
     */
    public static final InputSubset HANJA = null;

    /** 
     * Constant for the halfwidth katakana subset of the Unicode halfwidth and
     * fullwidth forms character block.
     */
    public static final InputSubset HALFWIDTH_KATAKANA = null;

    /** 
     * Constant for the fullwidth ASCII variants subset of the Unicode halfwidth and
     * fullwidth forms character block.
     * @since 1.3
     */
    public static final InputSubset FULLWIDTH_LATIN = null;

    /** 
     * Constant for the fullwidth digits included in the Unicode halfwidth and
     * fullwidth forms character block.
     * @since 1.3
     */
    public static final InputSubset FULLWIDTH_DIGITS = null;

    /*
     * This hidden constructor does not necessarily correspond to
     * a constructor in the original source file -- it keeps javadoc
     * from generating an inappropriate default constructor.
     */
    private InputSubset() {super(null); }
}
