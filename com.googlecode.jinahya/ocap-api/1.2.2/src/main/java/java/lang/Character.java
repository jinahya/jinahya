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


  


package java.lang;

/** 
 * The <code>Character</code> class wraps a value of the primitive
 * type <code>char</code> in an object. An object of type
 * <code>Character</code> contains a single field whose type is
 * <code>char</code>.
 * <p>
 * In addition, this class provides several methods for determining
 * a character's category (lowercase letter, digit, etc.) and for converting
 * characters from uppercase to lowercase and vice versa.
 * <p>
 * Character information is based on the Unicode Standard, version 3.0.
 * <p>
 * The methods and data of class <code>Character</code> are defined by
 * the information in the <i>UnicodeData</i> file that is part of the
 * Unicode Character Database maintained by the Unicode
 * Consortium. This file specifies various properties including name
 * and general category for every defined Unicode code point or
 * character range.
 * <p>
 * The file and its description are available from the Unicode Consortium at:
 *
 * <ul>
 * <li><a href="http://www.unicode.org">http://www.unicode.org</a>
 * </ul>
 *
 * @author  Lee Boynton
 * @author  Guy Steele
 * @author  Akira Tanaka
 * @since   1.0
 */
public final class Character
    implements java.io.Serializable, java.lang.Comparable
{
    /** 
     * The minimum radix available for conversion to and from strings.
     * The constant value of this field is the smallest value permitted
     * for the radix argument in radix-conversion methods such as the
     * <code>digit</code> method, the <code>forDigit</code>
     * method, and the <code>toString</code> method of class
     * <code>Integer</code>.
     *
     * @see     java.lang.Character#digit(char, int)
     * @see     java.lang.Character#forDigit(int, int)
     * @see     java.lang.Integer#toString(int, int)
     * @see     java.lang.Integer#valueOf(java.lang.String)
     */
    public static final int MIN_RADIX = 2;

    /** 
     * The maximum radix available for conversion to and from strings.
     * The constant value of this field is the largest value permitted
     * for the radix argument in radix-conversion methods such as the
     * <code>digit</code> method, the <code>forDigit</code>
     * method, and the <code>toString</code> method of class
     * <code>Integer</code>.
     *
     * @see     java.lang.Character#digit(char, int)
     * @see     java.lang.Character#forDigit(int, int)
     * @see     java.lang.Integer#toString(int, int)
     * @see     java.lang.Integer#valueOf(java.lang.String)
     */
    public static final int MAX_RADIX = 36;

    /** 
     * The constant value of this field is the smallest value of type
     * <code>char</code>, <code>'&#92;u0000'</code>.
     *
     * @since   1.0.2
     */
    public static final char MIN_VALUE = 0;

    /** 
     * The constant value of this field is the largest value of type
     * <code>char</code>, <code>'&#92;uFFFF'</code>.
     *
     * @since   1.0.2
     */
    public static final char MAX_VALUE = 65535;

    /** 
     * The <code>Class</code> instance representing the primitive type
     * <code>char</code>.
     *
     * @since   1.1
     */
    public static final java.lang.Class TYPE = null;

    /** 
     * General category "Cn" in the Unicode specification.
     * @since   1.1
     */
    public static final byte UNASSIGNED = 0;

    /** 
     * General category "Lu" in the Unicode specification.
     * @since   1.1
     */
    public static final byte UPPERCASE_LETTER = 1;

    /** 
     * General category "Ll" in the Unicode specification.
     * @since   1.1
     */
    public static final byte LOWERCASE_LETTER = 2;

    /** 
     * General category "Lt" in the Unicode specification.
     * @since   1.1
     */
    public static final byte TITLECASE_LETTER = 3;

    /** 
     * General category "Lm" in the Unicode specification.
     * @since   1.1
     */
    public static final byte MODIFIER_LETTER = 4;

    /** 
     * General category "Lo" in the Unicode specification.
     * @since   1.1
     */
    public static final byte OTHER_LETTER = 5;

    /** 
     * General category "Mn" in the Unicode specification.
     * @since   1.1
     */
    public static final byte NON_SPACING_MARK = 6;

    /** 
     * General category "Me" in the Unicode specification.
     * @since   1.1
     */
    public static final byte ENCLOSING_MARK = 7;

    /** 
     * General category "Mc" in the Unicode specification.
     * @since   1.1
     */
    public static final byte COMBINING_SPACING_MARK = 8;

    /** 
     * General category "Nd" in the Unicode specification.
     * @since   1.1
     */
    public static final byte DECIMAL_DIGIT_NUMBER = 9;

    /** 
     * General category "Nl" in the Unicode specification.
     * @since   1.1
     */
    public static final byte LETTER_NUMBER = 10;

    /** 
     * General category "No" in the Unicode specification.
     * @since   1.1
     */
    public static final byte OTHER_NUMBER = 11;

    /** 
     * General category "Zs" in the Unicode specification.
     * @since   1.1
     */
    public static final byte SPACE_SEPARATOR = 12;

    /** 
     * General category "Zl" in the Unicode specification.
     * @since   1.1
     */
    public static final byte LINE_SEPARATOR = 13;

    /** 
     * General category "Zp" in the Unicode specification.
     * @since   1.1
     */
    public static final byte PARAGRAPH_SEPARATOR = 14;

    /** 
     * General category "Cc" in the Unicode specification.
     * @since   1.1
     */
    public static final byte CONTROL = 15;

    /** 
     * General category "Cf" in the Unicode specification.
     * @since   1.1
     */
    public static final byte FORMAT = 16;

    /** 
     * General category "Co" in the Unicode specification.
     * @since   1.1
     */
    public static final byte PRIVATE_USE = 18;

    /** 
     * General category "Cs" in the Unicode specification.
     * @since   1.1
     */
    public static final byte SURROGATE = 19;

    /** 
     * General category "Pd" in the Unicode specification.
     * @since   1.1
     */
    public static final byte DASH_PUNCTUATION = 20;

    /** 
     * General category "Ps" in the Unicode specification.
     * @since   1.1
     */
    public static final byte START_PUNCTUATION = 21;

    /** 
     * General category "Pe" in the Unicode specification.
     * @since   1.1
     */
    public static final byte END_PUNCTUATION = 22;

    /** 
     * General category "Pc" in the Unicode specification.
     * @since   1.1
     */
    public static final byte CONNECTOR_PUNCTUATION = 23;

    /** 
     * General category "Po" in the Unicode specification.
     * @since   1.1
     */
    public static final byte OTHER_PUNCTUATION = 24;

    /** 
     * General category "Sm" in the Unicode specification.
     * @since   1.1
     */
    public static final byte MATH_SYMBOL = 25;

    /** 
     * General category "Sc" in the Unicode specification.
     * @since   1.1
     */
    public static final byte CURRENCY_SYMBOL = 26;

    /** 
     * General category "Sk" in the Unicode specification.
     * @since   1.1
     */
    public static final byte MODIFIER_SYMBOL = 27;

    /** 
     * General category "So" in the Unicode specification.
     * @since   1.1
     */
    public static final byte OTHER_SYMBOL = 28;

    /** 
     * General category "Pi" in the Unicode specification.
     * @since   1.4
     */
    public static final byte INITIAL_QUOTE_PUNCTUATION = 29;

    /** 
     * General category "Pf" in the Unicode specification.
     * @since   1.4
     */
    public static final byte FINAL_QUOTE_PUNCTUATION = 30;

    /** 
     * Undefined bidirectional character type. Undefined <code>char</code>
     * values have undefined directionality in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_UNDEFINED = -1;

    /** 
     * Strong bidirectional character type "L" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT = 0;

    /** 
     * Strong bidirectional character type "R" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT = 1;

    /** 
     * Strong bidirectional character type "AL" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = 2;

    /** 
     * Weak bidirectional character type "EN" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER = 3;

    /** 
     * Weak bidirectional character type "ES" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = 4;

    /** 
     * Weak bidirectional character type "ET" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = 5;

    /** 
     * Weak bidirectional character type "AN" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_ARABIC_NUMBER = 6;

    /** 
     * Weak bidirectional character type "CS" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = 7;

    /** 
     * Weak bidirectional character type "NSM" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_NONSPACING_MARK = 8;

    /** 
     * Weak bidirectional character type "BN" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_BOUNDARY_NEUTRAL = 9;

    /** 
     * Neutral bidirectional character type "B" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_PARAGRAPH_SEPARATOR = 10;

    /** 
     * Neutral bidirectional character type "S" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_SEGMENT_SEPARATOR = 11;

    /** 
     * Neutral bidirectional character type "WS" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_WHITESPACE = 12;

    /** 
     * Neutral bidirectional character type "ON" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_OTHER_NEUTRALS = 13;

    /** 
     * Strong bidirectional character type "LRE" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = 14;

    /** 
     * Strong bidirectional character type "LRO" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = 15;

    /** 
     * Strong bidirectional character type "RLE" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = 16;

    /** 
     * Strong bidirectional character type "RLO" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = 17;

    /** 
     * Weak bidirectional character type "PDF" in the Unicode specification.
     * @since 1.4
     */
    public static final byte DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = 18;

    /** 
     * The value of the <code>Character</code>.
     *
     * @serial
     */
    private char value;

    /** use serialVersionUID from JDK 1.0.2 for interoperability */
    private static final long serialVersionUID = 3786198910865385080L;

    /** 
     * Constructs a newly allocated <code>Character</code> object that
     * represents the specified <code>char</code> value.
     *
     * @param  value   the value to be represented by the 
     *			<code>Character</code> object.
     */
    public Character(char value) { }

    /** 
     * Returns the value of this <code>Character</code> object.
     * @return  the primitive <code>char</code> value represented by
     *          this object.
     */
    public char charValue() {
        return ' ';
    }

    /** 
     * Returns a hash code for this <code>Character</code>.
     * @return  a hash code value for this object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Compares this object against the specified object.
     * The result is <code>true</code> if and only if the argument is not
     * <code>null</code> and is a <code>Character</code> object that
     * represents the same <code>char</code> value as this object.
     *
     * @param   obj   the object to compare with.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     */
    public boolean equals(java.lang.Object obj) {
        return false;
    }

    /** 
     * Returns a <code>String</code> object representing this
     * <code>Character</code>'s value.  The result is a string of
     * length 1 whose sole component is the primitive
     * <code>char</code> value represented by this
     * <code>Character</code> object.
     *
     * @return  a string representation of this object.
     */
    public java.lang.String toString() {
        return null;
    }

    /** 
     * Returns a <code>String</code> object representing the
     * specified <code>char</code>.  The result is a string of length
     * 1 consisting solely of the specified <code>char</code>.
     *
     * @param c the <code>char</code> to be converted
     * @return the string representation of the specified <code>char</code>
     * @since 1.4
     */
    public static java.lang.String toString(char c) {
        return null;
    }

    /** 
     * Determines if the specified character is a lowercase character.
     * <p>
     * A character is lowercase if its general category type, provided
     * by <code>Character.getType(ch)</code>, is
     * <code>LOWERCASE_LETTER</code>.
     * <p>
     * The following are examples of lowercase characters:
     * <p><blockquote><pre>
     * a b c d e f g h i j k l m n o p q r s t u v w x y z
     * '&#92;u00DF' '&#92;u00E0' '&#92;u00E1' '&#92;u00E2' '&#92;u00E3' '&#92;u00E4' '&#92;u00E5' '&#92;u00E6' 
     * '&#92;u00E7' '&#92;u00E8' '&#92;u00E9' '&#92;u00EA' '&#92;u00EB' '&#92;u00EC' '&#92;u00ED' '&#92;u00EE'
     * '&#92;u00EF' '&#92;u00F0' '&#92;u00F1' '&#92;u00F2' '&#92;u00F3' '&#92;u00F4' '&#92;u00F5' '&#92;u00F6'
     * '&#92;u00F8' '&#92;u00F9' '&#92;u00FA' '&#92;u00FB' '&#92;u00FC' '&#92;u00FD' '&#92;u00FE' '&#92;u00FF'
     * </pre></blockquote>
     * <p> Many other Unicode characters are lowercase too.
     * <p>
     *
     * @param   ch   the character to be tested.
     * @return  <code>true</code> if the character is lowercase;
     *          <code>false</code> otherwise.
     * @see     java.lang.Character#isLowerCase(char)
     * @see     java.lang.Character#isTitleCase(char)
     * @see     java.lang.Character#toLowerCase(char)
     * @see     java.lang.Character#getType(char)
     */
    public static boolean isLowerCase(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is an uppercase character.
     * <p>
     * A character is uppercase if its general category type, provided by
     * <code>Character.getType(ch)</code>, is <code>UPPERCASE_LETTER</code>.
     * <p>
     * The following are examples of uppercase characters:
     * <p><blockquote><pre>
     * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
     * '&#92;u00C0' '&#92;u00C1' '&#92;u00C2' '&#92;u00C3' '&#92;u00C4' '&#92;u00C5' '&#92;u00C6' '&#92;u00C7'
     * '&#92;u00C8' '&#92;u00C9' '&#92;u00CA' '&#92;u00CB' '&#92;u00CC' '&#92;u00CD' '&#92;u00CE' '&#92;u00CF'
     * '&#92;u00D0' '&#92;u00D1' '&#92;u00D2' '&#92;u00D3' '&#92;u00D4' '&#92;u00D5' '&#92;u00D6' '&#92;u00D8'
     * '&#92;u00D9' '&#92;u00DA' '&#92;u00DB' '&#92;u00DC' '&#92;u00DD' '&#92;u00DE'
     * </pre></blockquote>
     * <p> Many other Unicode characters are uppercase too.<p>
     *
     * @param   ch   the character to be tested.
     * @return  <code>true</code> if the character is uppercase;
     *          <code>false</code> otherwise.
     * @see     java.lang.Character#isLowerCase(char)
     * @see     java.lang.Character#isTitleCase(char)
     * @see     java.lang.Character#toUpperCase(char)
     * @see     java.lang.Character#getType(char)
     * @since   1.0
     */
    public static boolean isUpperCase(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is a titlecase character.
     * <p> 
     * A character is a titlecase character if its general
     * category type, provided by <code>Character.getType(ch)</code>,
     * is <code>TITLECASE_LETTER</code>.
     * <p>
     * Some characters look like pairs of Latin letters. For example, there
     * is an uppercase letter that looks like "LJ" and has a corresponding
     * lowercase letter that looks like "lj". A third form, which looks like "Lj",
     * is the appropriate form to use when rendering a word in lowercase
     * with initial capitals, as for a book title.
     * <p>
     * These are some of the Unicode characters for which this method returns
     * <code>true</code>:
     * <ul>
     * <li><code>LATIN CAPITAL LETTER D WITH SMALL LETTER Z WITH CARON</code>
     * <li><code>LATIN CAPITAL LETTER L WITH SMALL LETTER J</code>
     * <li><code>LATIN CAPITAL LETTER N WITH SMALL LETTER J</code>
     * <li><code>LATIN CAPITAL LETTER D WITH SMALL LETTER Z</code>
     * </ul>
     * <p> Many other Unicode characters are titlecase too.<p>
     *
     * @param   ch   the character to be tested.
     * @return  <code>true</code> if the character is titlecase;
     *          <code>false</code> otherwise.
     * @see     java.lang.Character#isLowerCase(char)
     * @see     java.lang.Character#isUpperCase(char)
     * @see     java.lang.Character#toTitleCase(char)
     * @see     java.lang.Character#getType(char)
     * @since   1.0.2
     */
    public static boolean isTitleCase(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is a digit.
     * <p>
     * A character is a digit if its general category type, provided
     * by <code>Character.getType(ch)</code>, is
     * <code>DECIMAL_DIGIT_NUMBER</code>.
     * <p>
     * Some Unicode character ranges that contain digits:
     * <ul>
     * <li><code>'&#92;u0030'</code> through <code>'&#92;u0039'</code>, 
     *	   ISO-LATIN-1 digits (<code>'0'</code> through <code>'9'</code>)
     * <li><code>'&#92;u0660'</code> through <code>'&#92;u0669'</code>,
     *	   Arabic-Indic digits
     * <li><code>'&#92;u06F0'</code> through <code>'&#92;u06F9'</code>,
     * 	   Extended Arabic-Indic digits
     * <li><code>'&#92;u0966'</code> through <code>'&#92;u096F'</code>,
     *	   Devanagari digits
     * <li><code>'&#92;uFF10'</code> through <code>'&#92;uFF19'</code>,
     *	   Fullwidth digits
     * </ul>
     *
     * Many other character ranges contain digits as well.
     *
     * @param   ch   the character to be tested.
     * @return  <code>true</code> if the character is a digit;
     *          <code>false</code> otherwise.
     * @see     java.lang.Character#digit(char, int)
     * @see     java.lang.Character#forDigit(int, int)
     * @see     java.lang.Character#getType(char)
     */
    public static boolean isDigit(char ch) {
        return false;
    }

    /** 
     * Determines if a character is defined in Unicode.
     * <p>
     * A character is defined if at least one of the following is true:
     * <ul>
     * <li>It has an entry in the UnicodeData file.
     * <li>It has a value in a range defined by the UnicodeData file.
     * </ul>
     *
     * @param   ch   the character to be tested
     * @return  <code>true</code> if the character has a defined meaning
     *          in Unicode; <code>false</code> otherwise.
     * @see     java.lang.Character#isDigit(char)
     * @see     java.lang.Character#isLetter(char)
     * @see     java.lang.Character#isLetterOrDigit(char)
     * @see     java.lang.Character#isLowerCase(char)
     * @see     java.lang.Character#isTitleCase(char)
     * @see     java.lang.Character#isUpperCase(char)
     * @since   1.0.2
     */
    public static boolean isDefined(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is a letter.
     * <p>
     * A character is considered to be a letter if its general
     * category type, provided by <code>Character.getType(ch)</code>,
     * is any of the following:
     * <ul>
     * <li> <code>UPPERCASE_LETTER</code>
     * <li> <code>LOWERCASE_LETTER</code>
     * <li> <code>TITLECASE_LETTER</code>
     * <li> <code>MODIFIER_LETTER</code>
     * <li> <code>OTHER_LETTER</code>
     * </ul>
     *
     * Not all letters have case. Many characters are
     * letters but are neither uppercase nor lowercase nor titlecase.
     *
     * @param   ch   the character to be tested.
     * @return  <code>true</code> if the character is a letter;
     *          <code>false</code> otherwise.
     * @see     java.lang.Character#isDigit(char)
     * @see     java.lang.Character#isJavaIdentifierStart(char)
     * @see     java.lang.Character#isJavaLetter(char)
     * @see     java.lang.Character#isJavaLetterOrDigit(char)
     * @see     java.lang.Character#isLetterOrDigit(char)
     * @see     java.lang.Character#isLowerCase(char)
     * @see     java.lang.Character#isTitleCase(char)
     * @see     java.lang.Character#isUnicodeIdentifierStart(char)
     * @see     java.lang.Character#isUpperCase(char)
     */
    public static boolean isLetter(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is a letter or digit.
     * <p>
     * A character is considered to be a letter or digit if either
     * <code>Character.isLetter(char ch)</code> or
     * <code>Character.isDigit(char ch)</code> returns
     * <code>true</code> for the character.
     *
     * @param   ch   the character to be tested.
     * @return  <code>true</code> if the character is a letter or digit;
     *          <code>false</code> otherwise.
     * @see     java.lang.Character#isDigit(char)
     * @see     java.lang.Character#isJavaIdentifierPart(char)
     * @see     java.lang.Character#isJavaLetter(char)
     * @see     java.lang.Character#isJavaLetterOrDigit(char)
     * @see     java.lang.Character#isLetter(char)
     * @see     java.lang.Character#isUnicodeIdentifierPart(char)
     * @since   1.0.2
     */
    public static boolean isLetterOrDigit(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is
     * permissible as the first character in a Java identifier.
     * <p>
     * A character may start a Java identifier if and only if
     * one of the following conditions is true:
     * <ul>
     * <li> {@link #isLetter(char) isLetter(ch)} returns <code>true</code>
     * <li> {@link #getType(char) getType(ch)} returns <code>LETTER_NUMBER</code>
     * <li> ch is a currency symbol (such as "$")
     * <li> ch is a connecting punctuation character (such as "_").
     * </ul>
     *
     * @param   ch the character to be tested.
     * @return  <code>true</code> if the character may start a Java identifier;
     *          <code>false</code> otherwise.
     * @see     java.lang.Character#isJavaIdentifierPart(char)
     * @see     java.lang.Character#isLetter(char)
     * @see     java.lang.Character#isUnicodeIdentifierStart(char)
     * @since   1.1
     */
    public static boolean isJavaIdentifierStart(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character may be part of a Java
     * identifier as other than the first character.
     * <p>
     * A character may be part of a Java identifier if any of the following
     * are true:
     * <ul>
     * <li>  it is a letter
     * <li>  it is a currency symbol (such as <code>'$'</code>)
     * <li>  it is a connecting punctuation character (such as <code>'_'</code>)
     * <li>  it is a digit
     * <li>  it is a numeric letter (such as a Roman numeral character)
     * <li>  it is a combining mark
     * <li>  it is a non-spacing mark
     * <li> <code>isIdentifierIgnorable</code> returns
     * <code>true</code> for the character
     * </ul>
     *
     * @param   ch	the character to be tested.
     * @return <code>true</code> if the character may be part of a
     * 		Java identifier; <code>false</code> otherwise.
     * @see     java.lang.Character#isIdentifierIgnorable(char)
     * @see     java.lang.Character#isJavaIdentifierStart(char)
     * @see     java.lang.Character#isLetterOrDigit(char)
     * @see     java.lang.Character#isUnicodeIdentifierPart(char)
     * @since   1.1
     */
    public static boolean isJavaIdentifierPart(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is permissible as the
     * first character in a Unicode identifier.
     * <p>
     * A character may start a Unicode identifier if and only if
     * one of the following conditions is true:
     * <ul>
     * <li> {@link #isLetter(char) isLetter(ch)} returns <code>true</code>
     * <li> {@link #getType(char) getType(ch)} returns 
     *      <code>LETTER_NUMBER</code>.
     * </ul>
     * @param   ch	the character to be tested.
     * @return  <code>true</code> if the character may start a Unicode 
     *          identifier; <code>false</code> otherwise.
     * @see     java.lang.Character#isJavaIdentifierStart(char)
     * @see     java.lang.Character#isLetter(char)
     * @see     java.lang.Character#isUnicodeIdentifierPart(char)
     * @since   1.1
     */
    public static boolean isUnicodeIdentifierStart(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character may be part of a Unicode
     * identifier as other than the first character.
     * <p>
     * A character may be part of a Unicode identifier if and only if
     * one of the following statements is true:
     * <ul>
     * <li>  it is a letter
     * <li>  it is a connecting punctuation character (such as <code>'_'</code>)
     * <li>  it is a digit
     * <li>  it is a numeric letter (such as a Roman numeral character)
     * <li>  it is a combining mark
     * <li>  it is a non-spacing mark
     * <li> <code>isIdentifierIgnorable</code> returns
     * <code>true</code> for this character.
     * </ul>
     *
     * @param   ch	the character to be tested.
     * @return  <code>true</code> if the character may be part of a 
     *          Unicode identifier; <code>false</code> otherwise.
     * @see     java.lang.Character#isIdentifierIgnorable(char)
     * @see     java.lang.Character#isJavaIdentifierPart(char)
     * @see     java.lang.Character#isLetterOrDigit(char)
     * @see     java.lang.Character#isUnicodeIdentifierStart(char)
     * @since   1.1
     */
    public static boolean isUnicodeIdentifierPart(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character should be regarded as
     * an ignorable character in a Java identifier or a Unicode identifier.
     * <p>
     * The following Unicode characters are ignorable in a Java identifier
     * or a Unicode identifier:
     * <ul>
     * <li>ISO control characters that are not whitespace
     * <ul>
     * <li><code>'&#92;u0000'</code> through <code>'&#92;u0008'</code>
     * <li><code>'&#92;u000E'</code> through <code>'&#92;u001B'</code>
     * <li><code>'&#92;u007F'</code> through <code>'&#92;u009F'</code>
     * </ul>
     *
     * <li>all characters that have the <code>FORMAT</code> general
     * category value
     * </ul>
     *
     * @param   ch	the character to be tested.
     * @return 	<code>true</code> if the character is an ignorable control 
     *          character that may be part of a Java or Unicode identifier;
     *		 <code>false</code> otherwise.
     * @see     java.lang.Character#isJavaIdentifierPart(char)
     * @see     java.lang.Character#isUnicodeIdentifierPart(char)
     * @since   1.1
     */
    public static boolean isIdentifierIgnorable(char ch) {
        return false;
    }

    /** 
     * Converts the character argument to lowercase using case
     * mapping information from the UnicodeData file.
     * <p>
     * Note that
     * <code>Character.isLowerCase(Character.toLowerCase(ch))</code>
     * does not always return <code>true</code> for some ranges of
     * characters, particularly those that are symbols or ideographs.
     *
     * @param   ch   the character to be converted.
     * @return  the lowercase equivalent of the character, if any;
     *          otherwise, the character itself.
     * @see     java.lang.Character#isLowerCase(char)
     * @see     java.lang.Character#isUpperCase(char)
     * @see     java.lang.Character#toTitleCase(char)
     * @see     java.lang.Character#toUpperCase(char)
     */
    public static char toLowerCase(char ch) {
        return ' ';
    }

    /** 
     * Converts the character argument to uppercase using case mapping
     * information from the UnicodeData file.
     * <p>
     * Note that
     * <code>Character.isUpperCase(Character.toUpperCase(ch))</code>
     * does not always return <code>true</code> for some ranges of
     * characters, particularly those that are symbols or ideographs.
     *
     * @param   ch   the character to be converted.
     * @return  the uppercase equivalent of the character, if any;
     *          otherwise, the character itself.
     * @see     java.lang.Character#isLowerCase(char)
     * @see     java.lang.Character#isUpperCase(char)
     * @see     java.lang.Character#toLowerCase(char)
     * @see     java.lang.Character#toTitleCase(char)
     */
    public static char toUpperCase(char ch) {
        return ' ';
    }

    /** 
     * Converts the character argument to titlecase using case mapping
     * information from the UnicodeData file. If a character has no
     * explicit titlecase mapping and is not itself a titlecase char
     * according to UnicodeData, then the uppercase mapping is
     * returned as an equivalent titlecase mapping. If the
     * <code>char</code> argument is already a titlecase
     * <code>char</code>, the same <code>char</code> value will be
     * returned.
     * <p>
     * Note that
     * <code>Character.isTitleCase(Character.toTitleCase(ch))</code>
     * does not always return <code>true</code> for some ranges of
     * characters.
     *
     * @param   ch   the character to be converted.
     * @return  the titlecase equivalent of the character, if any;
     *          otherwise, the character itself.
     * @see     java.lang.Character#isTitleCase(char)
     * @see     java.lang.Character#toLowerCase(char)
     * @see     java.lang.Character#toUpperCase(char)
     * @since   1.0.2
     */
    public static char toTitleCase(char ch) {
        return ' ';
    }

    /** 
     * Returns the numeric value of the character <code>ch</code> in the
     * specified radix.
     * <p>
     * If the radix is not in the range <code>MIN_RADIX</code>&nbsp;&lt;=
     * <code>radix</code>&nbsp;&lt;= <code>MAX_RADIX</code> or if the
     * value of <code>ch</code> is not a valid digit in the specified
     * radix, <code>-1</code> is returned. A character is a valid digit
     * if at least one of the following is true:
     * <ul>
     * <li>The method <code>isDigit</code> is <code>true</code> of the character
     *     and the Unicode decimal digit value of the character (or its
     *     single-character decomposition) is less than the specified radix.
     *     In this case the decimal digit value is returned.
     * <li>The character is one of the uppercase Latin letters
     *     <code>'A'</code> through <code>'Z'</code> and its code is less than
     *     <code>radix&nbsp;+ 'A'&nbsp;-&nbsp;10</code>.
     *     In this case, <code>ch&nbsp;- 'A'&nbsp;+&nbsp;10</code>
     *     is returned.
     * <li>The character is one of the lowercase Latin letters
     *     <code>'a'</code> through <code>'z'</code> and its code is less than
     *     <code>radix&nbsp;+ 'a'&nbsp;-&nbsp;10</code>.
     *     In this case, <code>ch&nbsp;- 'a'&nbsp;+&nbsp;10</code>
     *     is returned.
     * </ul>
     *
     * @param   ch      the character to be converted.
     * @param   radix   the radix.
     * @return  the numeric value represented by the character in the
     *          specified radix.
     * @see     java.lang.Character#forDigit(int, int)
     * @see     java.lang.Character#isDigit(char)
     */
    public static int digit(char ch, int radix) {
        return 0;
    }

    /** 
     * Returns the <code>int</code> value that the specified Unicode
     * character represents. For example, the character
     * <code>'&#92;u216C'</code> (the roman numeral fifty) will return
     * an int with a value of 50.
     * <p>
     * The letters A-Z in their uppercase (<code>'&#92;u0041'</code> through
     * <code>'&#92;u005A'</code>), lowercase
     * (<code>'&#92;u0061'</code> through <code>'&#92;u007A'</code>), and
     * full width variant (<code>'&#92;uFF21'</code> through
     * <code>'&#92;uFF3A'</code> and <code>'&#92;uFF41'</code> through
     * <code>'&#92;uFF5A'</code>) forms have numeric values from 10
     * through 35. This is independent of the Unicode specification,
     * which does not assign numeric values to these <code>char</code>
     * values.
     * <p>
     * If the character does not have a numeric value, then -1 is returned.
     * If the character has a numeric value that cannot be represented as a
     * nonnegative integer (for example, a fractional value), then -2
     * is returned.
     *
     * @param   ch	the character to be converted.
     * @return  the numeric value of the character, as a nonnegative <code>int</code>
     *           value; -2 if the character has a numeric value that is not a
     *          nonnegative integer; -1 if the character has no numeric value.
     * @see     java.lang.Character#forDigit(int, int)
     * @see     java.lang.Character#isDigit(char)
     * @since   1.1
     */
    public static int getNumericValue(char ch) {
        return 0;
    }

    /** 
     * Determines if the specified character is a Unicode space character.
     * A character is considered to be a space character if and only if
     * it is specified to be a space character by the Unicode standard. This
     * method returns true if the character's general category type is any of
     * the following:
     * <ul>
     * <li> <code>SPACE_SEPARATOR</code>
     * <li> <code>LINE_SEPARATOR</code>
     * <li> <code>PARAGRAPH_SEPARATOR</code>
     * </ul>
     *
     * @param   ch	the character to be tested.
     * @return 	<code>true</code> if the character is a space character; 
     *		<code>false</code> otherwise.
     * @see     java.lang.Character#isWhitespace(char)
     * @since   1.1
     */
    public static boolean isSpaceChar(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is white space according to Java.
     * A character is a Java whitespace character if and only if it satisfies
     * one of the following criteria:
     * <ul>
     * <li> It is a Unicode space character (<code>SPACE_SEPARATOR</code>,
     * 	    <code>LINE_SEPARATOR</code>, or <code>PARAGRAPH_SEPARATOR</code>) 
     *      but is not also a non-breaking space (<code>'&#92;u00A0'</code>,
     *      <code>'&#92;u2007'</code>, <code>'&#92;u202F'</code>).
     * <li> It is <code>'&#92;u0009'</code>, HORIZONTAL TABULATION.
     * <li> It is <code>'&#92;u000A'</code>, LINE FEED.
     * <li> It is <code>'&#92;u000B'</code>, VERTICAL TABULATION.
     * <li> It is <code>'&#92;u000C'</code>, FORM FEED.
     * <li> It is <code>'&#92;u000D'</code>, CARRIAGE RETURN.
     * <li> It is <code>'&#92;u001C'</code>, FILE SEPARATOR.
     * <li> It is <code>'&#92;u001D'</code>, GROUP SEPARATOR.
     * <li> It is <code>'&#92;u001E'</code>, RECORD SEPARATOR.
     * <li> It is <code>'&#92;u001F'</code>, UNIT SEPARATOR.
     * </ul>
     *
     * @param   ch the character to be tested.
     * @return  <code>true</code> if the character is a Java whitespace
     *          character; <code>false</code> otherwise.
     * @see     java.lang.Character#isSpaceChar(char)
     * @since   1.1
     */
    public static boolean isWhitespace(char ch) {
        return false;
    }

    /** 
     * Determines if the specified character is an ISO control
     * character.  A character is considered to be an ISO control
     * character if its code is in the range <code>'&#92;u0000'</code>
     * through <code>'&#92;u001F'</code> or in the range
     * <code>'&#92;u007F'</code> through <code>'&#92;u009F'</code>.
     *
     * @param   ch	the character to be tested.
     * @return  <code>true</code> if the character is an ISO control character;
     *          <code>false</code> otherwise.
     *
     * @see     java.lang.Character#isSpaceChar(char)
     * @see     java.lang.Character#isWhitespace(char)
     * @since   1.1
     */
    public static boolean isISOControl(char ch) {
        return false;
    }

    /** 
     * Returns a value indicating a character's general category.
     *
     * @param   ch      the character to be tested.
     * @return  a value of type <code>int</code> representing the 
     *		character's general category.
     * @see     java.lang.Character#COMBINING_SPACING_MARK
     * @see     java.lang.Character#CONNECTOR_PUNCTUATION
     * @see     java.lang.Character#CONTROL
     * @see     java.lang.Character#CURRENCY_SYMBOL
     * @see     java.lang.Character#DASH_PUNCTUATION
     * @see     java.lang.Character#DECIMAL_DIGIT_NUMBER
     * @see     java.lang.Character#ENCLOSING_MARK
     * @see     java.lang.Character#END_PUNCTUATION
     * @see     java.lang.Character#FINAL_QUOTE_PUNCTUATION
     * @see     java.lang.Character#FORMAT
     * @see     java.lang.Character#INITIAL_QUOTE_PUNCTUATION
     * @see     java.lang.Character#LETTER_NUMBER
     * @see     java.lang.Character#LINE_SEPARATOR
     * @see     java.lang.Character#LOWERCASE_LETTER
     * @see     java.lang.Character#MATH_SYMBOL
     * @see     java.lang.Character#MODIFIER_LETTER
     * @see     java.lang.Character#MODIFIER_SYMBOL
     * @see     java.lang.Character#NON_SPACING_MARK
     * @see     java.lang.Character#OTHER_LETTER
     * @see     java.lang.Character#OTHER_NUMBER
     * @see     java.lang.Character#OTHER_PUNCTUATION
     * @see     java.lang.Character#OTHER_SYMBOL
     * @see     java.lang.Character#PARAGRAPH_SEPARATOR
     * @see     java.lang.Character#PRIVATE_USE
     * @see     java.lang.Character#SPACE_SEPARATOR
     * @see     java.lang.Character#START_PUNCTUATION
     * @see     java.lang.Character#SURROGATE
     * @see     java.lang.Character#TITLECASE_LETTER
     * @see     java.lang.Character#UNASSIGNED
     * @see     java.lang.Character#UPPERCASE_LETTER
     * @since   1.1
     */
    public static int getType(char ch) {
        return 0;
    }

    /** 
     * Determines the character representation for a specific digit in
     * the specified radix. If the value of <code>radix</code> is not a
     * valid radix, or the value of <code>digit</code> is not a valid
     * digit in the specified radix, the null character
     * (<code>'&#92;u0000'</code>) is returned.
     * <p>
     * The <code>radix</code> argument is valid if it is greater than or
     * equal to <code>MIN_RADIX</code> and less than or equal to
     * <code>MAX_RADIX</code>. The <code>digit</code> argument is valid if
     * <code>0&nbsp;&lt;=digit&nbsp;&lt;&nbsp;radix</code>.
     * <p>
     * If the digit is less than 10, then
     * <code>'0'&nbsp;+ digit</code> is returned. Otherwise, the value
     * <code>'a'&nbsp;+ digit&nbsp;-&nbsp;10</code> is returned.
     *
     * @param   digit   the number to convert to a character.
     * @param   radix   the radix.
     * @return  the <code>char</code> representation of the specified digit
     *          in the specified radix.
     * @see     java.lang.Character#MIN_RADIX
     * @see     java.lang.Character#MAX_RADIX
     * @see     java.lang.Character#digit(char, int)
     */
    public static char forDigit(int digit, int radix) {
        return ' ';
    }

    /** 
     * Returns the Unicode directionality property for the given
     * character.  Character directionality is used to calculate the
     * visual ordering of text. The directionality value of undefined
     * <code>char</code> values is <code>DIRECTIONALITY_UNDEFINED</code>.
     *
     * @param  ch <code>char</code> for which the directionality property 
     *            is requested.
     * @return the directionality property of the <code>char</code> value.
     *
     * @see Character#DIRECTIONALITY_UNDEFINED
     * @see Character#DIRECTIONALITY_LEFT_TO_RIGHT
     * @see Character#DIRECTIONALITY_RIGHT_TO_LEFT
     * @see Character#DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC
     * @see Character#DIRECTIONALITY_EUROPEAN_NUMBER
     * @see Character#DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR
     * @see Character#DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR
     * @see Character#DIRECTIONALITY_ARABIC_NUMBER
     * @see Character#DIRECTIONALITY_COMMON_NUMBER_SEPARATOR
     * @see Character#DIRECTIONALITY_NONSPACING_MARK
     * @see Character#DIRECTIONALITY_BOUNDARY_NEUTRAL
     * @see Character#DIRECTIONALITY_PARAGRAPH_SEPARATOR
     * @see Character#DIRECTIONALITY_SEGMENT_SEPARATOR
     * @see Character#DIRECTIONALITY_WHITESPACE
     * @see Character#DIRECTIONALITY_OTHER_NEUTRALS
     * @see Character#DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING
     * @see Character#DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE
     * @see Character#DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING
     * @see Character#DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE
     * @see Character#DIRECTIONALITY_POP_DIRECTIONAL_FORMAT
     * @since 1.4
     */
    public static byte getDirectionality(char ch) {
        return ' ';
    }

    /** 
     * Determines whether the character is mirrored according to the
     * Unicode specification.  Mirrored characters should have their
     * glyphs horizontally mirrored when displayed in text that is
     * right-to-left.  For example, <code>'&#92;u0028'</code> LEFT
     * PARENTHESIS is semantically defined to be an <i>opening
     * parenthesis</i>.  This will appear as a "(" in text that is
     * left-to-right but as a ")" in text that is right-to-left.
     *
     * @param  ch <code>char</code> for which the mirrored property is requested
     * @return <code>true</code> if the char is mirrored, <code>false</code>
     *         if the <code>char</code> is not mirrored or is not defined.
     * @since 1.4
     */
    public static boolean isMirrored(char ch) {
        return false;
    }

    /** 
     * Compares two <code>Character</code> objects numerically.
     *
     * @param   anotherCharacter   the <code>Character</code> to be compared.
     *
     * @return  the value <code>0</code> if the argument <code>Character</code> 
     *          is equal to this <code>Character</code>; a value less than 
     *          <code>0</code> if this <code>Character</code> is numerically less 
     *          than the <code>Character</code> argument; and a value greater than 
     *          <code>0</code> if this <code>Character</code> is numerically greater 
     *	        than the <code>Character</code> argument (unsigned comparison).  
     *	        Note that this is strictly a numerical comparison; it is not 
     *		locale-dependent.
     * @since   1.2
     */
    public int compareTo(java.lang.Character anotherCharacter) {
        return 0;
    }

    /** 
     * Compares this <code>Character</code> object to another object.
     * If the object is a <code>Character</code>, this function
     * behaves like <code>compareTo(Character)</code>.  Otherwise, it
     * throws a <code>ClassCastException</code> (as
     * <code>Character</code> objects are comparable only to other
     * <code>Character</code> objects).
     *
     * @param   o the <code>Object</code> to be compared.
     * @return  the value <code>0</code> if the argument is a <code>Character</code>
     *		numerically equal to this <code>Character</code>; a value less than
     *		<code>0</code> if the argument is a <code>Character</code> numerically
     *		greater than this <code>Character</code>; and a value greater than
     *		<code>0</code> if the argument is a <code>Character</code> numerically
     *		less than this <code>Character</code>.
     * @exception <code>ClassCastException</code> if the argument is not a
     *		  <code>Character</code>.
     * @see     java.lang.Comparable
     * @since 1.2 
     */
    public int compareTo(java.lang.Object o) {
        return 0;
    }

    /** 
     * Instances of this class represent particular subsets of the Unicode
     * character set.  The only family of subsets defined in the
     * <code>Character</code> class is <code>{@link Character.UnicodeBlock
     * UnicodeBlock}</code>.  Other portions of the Java API may define other
     * subsets for their own purposes.
     *
     * @since 1.2
     */
    public static class Subset
    {

        /** 
         * Constructs a new <code>Subset</code> instance.
         *
         * @exception NullPointerException if name is <code>null</code>
         * @param  name  The name of this subset
         */
        protected Subset(java.lang.String name) { }

        /** 
         * Compares two <code>Subset</code> objects for equality.
         * This method returns <code>true</code> if and only if
         * <code>this</code> and the argument refer to the same
         * object; since this method is <code>final</code>, this
         * guarantee holds for all subclasses.
         */
        public final boolean equals(java.lang.Object obj) {
            return false;
        }

        /** 
         * Returns the standard hash code as defined by the
         * <code>{@link Object#hashCode}</code> method.  This method
         * is <code>final</code> in order to ensure that the
         * <code>equals</code> and <code>hashCode</code> methods will
         * be consistent in all subclasses.
         */
        public final int hashCode() {
            return 0;
        }

        /** 
         * Returns the name of this subset.
         */
        public final java.lang.String toString() {
            return null;
        }
    }

    /** 
     * A family of character subsets representing the character blocks in the
     * Unicode specification. Character blocks generally define characters
     * used for a specific script or purpose. A character is contained by
     * at most one Unicode block.
     *
     * @since 1.2
     */
    public static final class UnicodeBlock extends java.lang.Character.Subset
    {
        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock BASIC_LATIN = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock LATIN_1_SUPPLEMENT
            = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock LATIN_EXTENDED_A =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock LATIN_EXTENDED_B =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock IPA_EXTENSIONS =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            SPACING_MODIFIER_LETTERS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            COMBINING_DIACRITICAL_MARKS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock GREEK = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock CYRILLIC = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock ARMENIAN = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock HEBREW = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock ARABIC = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock DEVANAGARI = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock BENGALI = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock GURMUKHI = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock GUJARATI = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock ORIYA = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock TAMIL = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock TELUGU = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock KANNADA = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock MALAYALAM = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock THAI = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock LAO = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock TIBETAN = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock GEORGIAN = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock HANGUL_JAMO = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            LATIN_EXTENDED_ADDITIONAL = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock GREEK_EXTENDED =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock GENERAL_PUNCTUATION
            = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            SUPERSCRIPTS_AND_SUBSCRIPTS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock CURRENCY_SYMBOLS =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            COMBINING_MARKS_FOR_SYMBOLS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock LETTERLIKE_SYMBOLS
            = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock NUMBER_FORMS =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock ARROWS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            MATHEMATICAL_OPERATORS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            MISCELLANEOUS_TECHNICAL = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock CONTROL_PICTURES =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            OPTICAL_CHARACTER_RECOGNITION = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            ENCLOSED_ALPHANUMERICS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock BOX_DRAWING = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock BLOCK_ELEMENTS =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock GEOMETRIC_SHAPES =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            MISCELLANEOUS_SYMBOLS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock DINGBATS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            CJK_SYMBOLS_AND_PUNCTUATION = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock HIRAGANA = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock KATAKANA = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock BOPOMOFO = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            HANGUL_COMPATIBILITY_JAMO = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock KANBUN = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            ENCLOSED_CJK_LETTERS_AND_MONTHS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock CJK_COMPATIBILITY =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            CJK_UNIFIED_IDEOGRAPHS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock HANGUL_SYLLABLES =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock SURROGATES_AREA =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock PRIVATE_USE_AREA =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            CJK_COMPATIBILITY_IDEOGRAPHS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            ALPHABETIC_PRESENTATION_FORMS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            ARABIC_PRESENTATION_FORMS_A = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            COMBINING_HALF_MARKS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            CJK_COMPATIBILITY_FORMS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock SMALL_FORM_VARIANTS
            = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            ARABIC_PRESENTATION_FORMS_B = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock
            HALFWIDTH_AND_FULLWIDTH_FORMS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         */
        public static final java.lang.Character.UnicodeBlock SPECIALS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock SYRIAC = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock THAANA = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock SINHALA = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock MYANMAR = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock ETHIOPIC = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock CHEROKEE = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock
            UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock OGHAM = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock RUNIC = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock KHMER = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock MONGOLIAN = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock BRAILLE_PATTERNS =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock
            CJK_RADICALS_SUPPLEMENT = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock KANGXI_RADICALS =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock
            IDEOGRAPHIC_DESCRIPTION_CHARACTERS = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock BOPOMOFO_EXTENDED =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock
            CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A = null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock YI_SYLLABLES =
            null;

        /** 
         * Constant for the Unicode character block of the same name.
         *
         * @since 1.4
         */
        public static final java.lang.Character.UnicodeBlock YI_RADICALS = null;

        /*
         * This hidden constructor does not necessarily correspond to
         * a constructor in the original source file -- it keeps javadoc
         * from generating an inappropriate default constructor.
         */
        private UnicodeBlock() { 
            super(null);
        }

        /** 
         * Returns the object representing the Unicode block containing the
         * given character, or <code>null</code> if the character is not a
         * member of a defined block.
         *
         * @param   c  The character in question
         * @return  The <code>UnicodeBlock</code> instance representing the
         *          Unicode block of which this character is a member, or
         *          <code>null</code> if the character is not a member of any
         *          Unicode block
         */
        public static java.lang.Character.UnicodeBlock of(char c) {
            return null;
        }
    }
}
