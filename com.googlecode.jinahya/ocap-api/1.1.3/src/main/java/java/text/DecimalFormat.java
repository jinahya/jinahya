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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

/** 
 * <code>DecimalFormat</code> is a concrete subclass of
 * <code>NumberFormat</code> that formats decimal numbers. It has a variety of
 * features designed to make it possible to parse and format numbers in any
 * locale, including support for Western, Arabic, and Indic digits.  It also
 * supports different kinds of numbers, including integers (123), fixed-point
 * numbers (123.4), scientific notation (1.23E4), percentages (12%), and
 * currency amounts ($123).  All of these can be localized.
 *
 * <p>To obtain a <code>NumberFormat</code> for a specific locale, including the
 * default locale, call one of <code>NumberFormat</code>'s factory methods, such
 * as <code>getInstance()</code>.  In general, do not call the
 * <code>DecimalFormat</code> constructors directly, since the
 * <code>NumberFormat</code> factory methods may return subclasses other than
 * <code>DecimalFormat</code>. If you need to customize the format object, do
 * something like this:
 *
 * <blockquote><pre>
 * NumberFormat f = NumberFormat.getInstance(loc);
 * if (f instanceof DecimalFormat) {
 *     ((DecimalFormat) f).setDecimalSeparatorAlwaysShown(true);
 * }
 * </pre></blockquote>
 *
 * <p>A <code>DecimalFormat</code> comprises a <em>pattern</em> and a set of
 * <em>symbols</em>.  The pattern may be set directly using
 * <code>applyPattern()</code>, or indirectly using the API methods.  The
 * symbols are stored in a <code>DecimalFormatSymbols</code> object.  When using
 * the <code>NumberFormat</code> factory methods, the pattern and symbols are
 * read from localized <code>ResourceBundle</code>s.
 *
 * <h4>Patterns</h4>
 *
 * <code>DecimalFormat</code> patterns have the following syntax:
 * <blockquote><pre>
 * <i>Pattern:</i>
 *         <i>PositivePattern</i>
 *         <i>PositivePattern</i> ; <i>NegativePattern</i>
 * <i>PositivePattern:</i>
 *         <i>Prefix<sub>opt</sub></i> <i>Number</i> <i>Suffix<sub>opt</sub></i>
 * <i>NegativePattern:</i>
 *         <i>Prefix<sub>opt</sub></i> <i>Number</i> <i>Suffix<sub>opt</sub></i>
 * <i>Prefix:</i>
 *         any Unicode characters except &#92;uFFFE, &#92;uFFFF, and special characters
 * <i>Suffix:</i>
 *         any Unicode characters except &#92;uFFFE, &#92;uFFFF, and special characters
 * <i>Number:</i>
 *         <i>Integer</i> <i>Exponent<sub>opt</sub></i>
 *         <i>Integer</i> . <i>Fraction</i> <i>Exponent<sub>opt</sub></i>
 * <i>Integer:</i>
 *         <i>MinimumInteger</i>
 *         #
 *         # <i>Integer</i>
 *         # , <i>Integer</i>
 * <i>MinimumInteger:</i>
 *         0
 *         0 <i>MinimumInteger</i>
 *         0 , <i>MinimumInteger</i>
 * <i>Fraction:</i>
 *         <i>MinimumFraction<sub>opt</sub></i> <i>OptionalFraction<sub>opt</sub></i>
 * <i>MinimumFraction:</i>
 *         0 <i>MinimumFraction<sub>opt</sub></i>
 * <i>OptionalFraction:</i>
 *         # <i>OptionalFraction<sub>opt</sub></i>
 * <i>Exponent:</i>
 *         E <i>MinimumExponent</i>
 * <i>MinimumExponent:</i>
 *         0 <i>MinimumExponent<sub>opt</sub></i>
 * </pre></blockquote>
 *
 * <p>A <code>DecimalFormat</code> pattern contains a positive and negative
 * subpattern, for example, <code>"#,##0.00;(#,##0.00)"</code>.  Each
 * subpattern has a prefix, numeric part, and suffix. The negative subpattern
 * is optional; if absent, then the positive subpattern prefixed with the
 * localized minus sign (code>'-'</code> in most locales) is used as the
 * negative subpattern. That is, <code>"0.00"</code> alone is equivalent to
 * <code>"0.00;-0.00"</code>.  If there is an explicit negative subpattern, it
 * serves only to specify the negative prefix and suffix; the number of digits,
 * minimal digits, and other characteristics are all the same as the positive
 * pattern. That means that <code>"#,##0.0#;(#)"</code> produces precisely
 * the same behavior as <code>"#,##0.0#;(#,##0.0#)"</code>.
 *
 * <p>The prefixes, suffixes, and various symbols used for infinity, digits,
 * thousands separators, decimal separators, etc. may be set to arbitrary
 * values, and they will appear properly during formatting.  However, care must
 * be taken that the symbols and strings do not conflict, or parsing will be
 * unreliable.  For example, either the positive and negative prefixes or the
 * suffixes must be distinct for <code>DecimalFormat.parse()</code> to be able
 * to distinguish positive from negative values.  (If they are identical, then
 * <code>DecimalFormat</code> will behave as if no negative subpattern was
 * specified.)  Another example is that the decimal separator and thousands
 * separator should be distinct characters, or parsing will be impossible.
 *
 * <p>The grouping separator is commonly used for thousands, but in some
 * countries it separates ten-thousands. The grouping size is a constant number
 * of digits between the grouping characters, such as 3 for 100,000,000 or 4 for
 * 1,0000,0000.  If you supply a pattern with multiple grouping characters, the
 * interval between the last one and the end of the integer is the one that is
 * used. So <code>"#,##,###,####"</code> == <code>"######,####"</code> ==
 * <code>"##,####,####"</code>.
 *
 * <h4>Special Pattern Characters</h4>
 *
 * <p>Many characters in a pattern are taken literally; they are matched during
 * parsing and output unchanged during formatting.  Special characters, on the
 * other hand, stand for other characters, strings, or classes of characters.
 * They must be quoted, unless noted otherwise, if they are to appear in the
 * prefix or suffix as literals.
 *
 * <p>The characters listed here are used in non-localized patterns.  Localized
 * patterns use the corresponding characters taken from this formatter's
 * <code>DecimalFormatSymbols</code> object instead, and these characters lose
 * their special status.  Two exceptions are the currency sign and quote, which
 * are not localized.
 *
 * <blockquote>
 * <table border=0 cellspacing=3 cellpadding=0 summary="Chart showing symbol,
 *  location, localized, and meaning.">
 *     <tr bgcolor="#ccccff">
 *          <th align=left>Symbol
 *          <th align=left>Location
 *          <th align=left>Localized?
 *          <th align=left>Meaning
 *     <tr valign=top>
 *          <td><code>0</code>
 *          <td>Number
 *          <td>Yes
 *          <td>Digit
 *     <tr valign=top bgcolor="#eeeeff">
 *          <td><code>#</code>
 *          <td>Number
 *          <td>Yes
 *          <td>Digit, zero shows as absent
 *     <tr valign=top>
 *          <td><code>.</code>
 *          <td>Number
 *          <td>Yes
 *          <td>Decimal separator or monetary decimal separator
 *     <tr valign=top bgcolor="#eeeeff">
 *          <td><code>-</code>
 *          <td>Number
 *          <td>Yes
 *          <td>Minus sign
 *     <tr valign=top>
 *          <td><code>,</code>
 *          <td>Number
 *          <td>Yes
 *          <td>Grouping separator
 *     <tr valign=top bgcolor="#eeeeff">
 *          <td><code>E</code>
 *          <td>Number
 *          <td>Yes
 *          <td>Separates mantissa and exponent in scientific notation.
 *              <em>Need not be quoted in prefix or suffix.</em>
 *     <tr valign=top>
 *          <td><code>;</code>
 *          <td>Subpattern boundary
 *          <td>Yes
 *          <td>Separates positive and negative subpatterns
 *     <tr valign=top bgcolor="#eeeeff">
 *          <td><code>%</code>
 *          <td>Prefix or suffix
 *          <td>Yes
 *          <td>Multiply by 100 and show as percentage
 *     <tr valign=top>
 *          <td><code>&#92;u2030</code>
 *          <td>Prefix or suffix
 *          <td>Yes
 *          <td>Multiply by 1000 and show as per mille
 *     <tr valign=top bgcolor="#eeeeff">
 *          <td><code>&#164;</code> (<code>&#92;u00A4</code>)
 *          <td>Prefix or suffix
 *          <td>No
 *          <td>Currency sign, replaced by currency symbol.  If
 *              doubled, replaced by international currency symbol.
 *              If present in a pattern, the monetary decimal separator
 *              is used instead of the decimal separator.
 *     <tr valign=top>
 *          <td><code>'</code>
 *          <td>Prefix or suffix
 *          <td>No
 *          <td>Used to quote special characters in a prefix or suffix,
 *              for example, <code>"'#'#"</code> formats 123 to
 *              <code>"#123"</code>.  To create a single quote
 *              itself, use two in a row: <code>"# o''clock"</code>.
 * </table>
 * </blockquote>
 *
 * <h4>Scientific Notation</h4>
 *
 * <p>Numbers in scientific notation are expressed as the product of a mantissa
 * and a power of ten, for example, 1234 can be expressed as 1.234 x 10^3.  The
 * mantissa is often in the range 1.0 <= x < 10.0, but it need not be.
 * <code>DecimalFormat</code> can be instructed to format and parse scientific
 * notation <em>only via a pattern</em>; there is currently no factory method
 * that creates a scientific notation format.  In a pattern, the exponent
 * character immediately followed by one or more digit characters indicates
 * scientific notation.  Example: <code>"0.###E0"</code> formats the number
 * 1234 as <code>"1.234E3"</code>.
 *
 * <ul>
 * <li>The number of digit characters after the exponent character gives the
 * minimum exponent digit count.  There is no maximum.  Negative exponents are
 * formatted using the localized minus sign, <em>not</em> the prefix and suffix
 * from the pattern.  This allows patterns such as <code>"0.###E0 m/s"</code>.
 *
 * <li>The minimum and maximum number of integer digits are interpreted
 * together:
 *
 * <ul>
 * <li>If the maximum number of integer digits is greater than their minimum number
 * and greater than 1, it forces the exponent to be a multiple of the maximum
 * number of integer digits, and the minimum number of integer digits to be
 * interpreted as 1.  The most common use of this is to generate
 * <em>engineering notation</em>, in which the exponent is a multiple of three,
 * e.g., <code>"##0.#####E0"</code>. Using this pattern, the number 12345
 * formats to <code>"12.345E3"</code>, and 123456 formats to
 * <code>"123.456E3"</code>.
 *
 * <li>Otherwise, the minimum number of integer digits is achieved by adjusting the
 * exponent.  Example: 0.00123 formatted with <code>"00.###E0"</code> yields
 * <code>"12.3E-4"</code>.
 * </ul>
 *
 * <li>The number of significant digits in the mantissa is the sum of the
 * <em>minimum integer</em> and <em>maximum fraction</em> digits, and is
 * unaffected by the maximum integer digits.  For example, 12345 formatted with
 * <code>"##0.##E0"</code> is <code>"12.3E3"</code>. To show all digits, set
 * the significant digits count to zero.  The number of significant digits
 * does not affect parsing.
 *
 * <li>Exponential patterns may not contain grouping separators.
 * </ul>
 *
 * <h4>Rounding</h4>
 *
 * <code>DecimalFormat</code> uses half-even rounding (see
 * {@link java.math.BigDecimal#ROUND_HALF_EVEN ROUND_HALF_EVEN}) for
 * formatting.
 * 
 * <h4>Digits</h4>
 *
 * For formatting, <code>DecimalFormat</code> uses the ten consecutive
 * characters starting with the localized zero digit defined in the
 * <code>DecimalFormatSymbols</code> object as digits. For parsing, these
 * digits as well as all Unicode decimal digits, as defined by
 * {@link Character#digit Character.digit}, are recognized.
 *
 * <h4>Special Values</h4>
 *
 * <p><code>NaN</code> is formatted as a single character, typically
 * <code>&#92;uFFFD</code>.  This character is determined by the
 * <code>DecimalFormatSymbols</code> object.  This is the only value for which
 * the prefixes and suffixes are not used.
 *
 * <p>Infinity is formatted as a single character, typically
 * <code>&#92;u221E</code>, with the positive or negative prefixes and suffixes
 * applied.  The infinity character is determined by the
 * <code>DecimalFormatSymbols</code> object.
 *
 * <p>Negative zero (<code>"-0"</code>) parses to <code>Double(-0.0)</code>,
 * unless <code>isParseIntegerOnly()</code> is true, in which case it parses to
 * <code>Long(0)</code>.
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 *
 * <p>
 * Decimal formats are generally not synchronized.
 * It is recommended to create separate format instances for each thread.
 * If multiple threads access a format concurrently, it must be synchronized
 * externally.
 *
 * <h4>Example</h4>
 *
 * <blockquote><pre>
 * <strong>// Print out a number using the localized number, integer, currency,
 * // and percent format for each locale</strong>
 * Locale[] locales = NumberFormat.getAvailableLocales();
 * double myNumber = -1234.56;
 * NumberFormat form;
 * for (int j=0; j<4; ++j) {
 *     System.out.println("FORMAT");
 *     for (int i = 0; i < locales.length; ++i) {
 *         if (locales[i].getCountry().length() == 0) {
 *            continue; // Skip language-only locales
 *         }
 *         System.out.print(locales[i].getDisplayName());
 *         switch (j) {
 *         case 0:
 *             form = NumberFormat.getInstance(locales[i]); break;
 *         case 1:
 *             form = NumberFormat.getIntegerInstance(locales[i]); break;
 *         case 2:
 *             form = NumberFormat.getCurrencyInstance(locales[i]); break;
 *         default:
 *             form = NumberFormat.getPercentInstance(locales[i]); break;
 *         }
 *         if (form instanceof DecimalFormat) {
 *             System.out.print(": " + ((DecimalFormat) form).toPattern());
 *         }
 *         System.out.print(" -> " + form.format(myNumber));
 *         try {
 *             System.out.println(" -> " + form.parse(form.format(myNumber)));
 *         } catch (ParseException e) {}
 *     }
 * }
 * </pre></blockquote>
 *
 * @see          <a href="http://java.sun.com/docs/books/tutorial/i18n/format/decimalFormat.html">Java Tutorial</a>
 * @see          NumberFormat
 * @see          DecimalFormatSymbols
 * @see          ParsePosition
 * @version      1.65, 01/12/04
 * @author       Mark Davis
 * @author       Alan Liu
 */
public class DecimalFormat extends NumberFormat
{
    /** 
     * The symbol used as a prefix when formatting positive numbers, e.g. "+".
     *
     * @serial
     * @see #getPositivePrefix
     */
    private String positivePrefix;

    /** 
     * The symbol used as a suffix when formatting positive numbers.
     * This is often an empty string.
     *
     * @serial
     * @see #getPositiveSuffix
     */
    private String positiveSuffix;

    /** 
     * The symbol used as a prefix when formatting negative numbers, e.g. "-".
     *
     * @serial
     * @see #getNegativePrefix
     */
    private String negativePrefix;

    /** 
     * The symbol used as a suffix when formatting negative numbers.
     * This is often an empty string.
     *
     * @serial
     * @see #getNegativeSuffix
     */
    private String negativeSuffix;

    /** 
     * The prefix pattern for non-negative numbers.  This variable corresponds
     * to <code>positivePrefix</code>.
     *
     * <p>This pattern is expanded by the method <code>expandAffix()</code> to
     * <code>positivePrefix</code> to update the latter to reflect changes in
     * <code>symbols</code>.  If this variable is <code>null</code> then
     * <code>positivePrefix</code> is taken as a literal value that does not
     * change when <code>symbols</code> changes.  This variable is always
     * <code>null</code> for <code>DecimalFormat</code> objects older than
     * stream version 2 restored from stream.
     *
     * @serial
     * @since 1.3
     */
    private String posPrefixPattern;

    /** 
     * The suffix pattern for non-negative numbers.  This variable corresponds
     * to <code>positiveSuffix</code>.  This variable is analogous to
     * <code>posPrefixPattern</code>; see that variable for further
     * documentation.
     *
     * @serial
     * @since 1.3
     */
    private String posSuffixPattern;

    /** 
     * The prefix pattern for negative numbers.  This variable corresponds
     * to <code>negativePrefix</code>.  This variable is analogous to
     * <code>posPrefixPattern</code>; see that variable for further
     * documentation.
     *
     * @serial
     * @since 1.3
     */
    private String negPrefixPattern;

    /** 
     * The suffix pattern for negative numbers.  This variable corresponds
     * to <code>negativeSuffix</code>.  This variable is analogous to
     * <code>posPrefixPattern</code>; see that variable for further
     * documentation.
     *
     * @serial
     * @since 1.3
     */
    private String negSuffixPattern;

    /** 
     * The multiplier for use in percent, permill, etc.
     *
     * @serial
     * @see #getMultiplier
     */
    private int multiplier;

    /** 
     * The number of digits between grouping separators in the integer
     * portion of a number.  Must be greater than 0 if
     * <code>NumberFormat.groupingUsed</code> is true.
     *
     * @serial
     * @see #getGroupingSize
     * @see java.text.NumberFormat#isGroupingUsed
     */
    private byte groupingSize;

    /** 
     * If true, forces the decimal separator to always appear in a formatted
     * number, even if the fractional part of the number is zero.
     *
     * @serial
     * @see #isDecimalSeparatorAlwaysShown
     */
    private boolean decimalSeparatorAlwaysShown;

    /** 
     * The <code>DecimalFormatSymbols</code> object used by this format.
     * It contains the symbols used to format numbers, e.g. the grouping separator,
     * decimal separator, and so on.
     *
     * @serial
     * @see #setDecimalFormatSymbols
     * @see java.text.DecimalFormatSymbols
     */
    private DecimalFormatSymbols symbols;

    /** 
     * True to force the use of exponential (i.e. scientific) notation when formatting
     * numbers.
     *
     * @serial
     * @since 1.2
     */
    private boolean useExponentialNotation;

    /** 
     * The minimum number of digits used to display the exponent when a number is
     * formatted in exponential notation.  This field is ignored if
     * <code>useExponentialNotation</code> is not true.
     *
     * @serial
     * @since 1.2
     */
    private byte minExponentDigits;

    /** 
     * The internal serial version which says which version was written.
     * Possible values are:
     * <ul>
     * <li><b>0</b> (default): versions before the Java 2 platform v1.2
     * <li><b>1</b>: version for 1.2, which includes the two new fields
     *      <code>useExponentialNotation</code> and <code>minExponentDigits</code>.
     * <li><b>2</b>: version for 1.3 and later, which adds four new fields:
     *      <code>posPrefixPattern</code>, <code>posSuffixPattern</code>,
     *      <code>negPrefixPattern</code>, and <code>negSuffixPattern</code>.
     * </ul>
     * @since 1.2
     * @serial
     */
    private int serialVersionOnStream;

    /** 
     * Creates a DecimalFormat using the default pattern and symbols
     * for the default locale. This is a convenient way to obtain a
     * DecimalFormat when internationalization is not the main concern.
     * <p>
     * To obtain standard formats for a given locale, use the factory methods
     * on NumberFormat such as getNumberInstance. These factories will
     * return the most appropriate sub-class of NumberFormat for a given
     * locale.
     *
     * @see java.text.NumberFormat#getInstance
     * @see java.text.NumberFormat#getNumberInstance
     * @see java.text.NumberFormat#getCurrencyInstance
     * @see java.text.NumberFormat#getPercentInstance
     */
    public DecimalFormat() { }

    /** 
     * Creates a DecimalFormat using the given pattern and the symbols
     * for the default locale. This is a convenient way to obtain a
     * DecimalFormat when internationalization is not the main concern.
     * <p>
     * To obtain standard formats for a given locale, use the factory methods
     * on NumberFormat such as getNumberInstance. These factories will
     * return the most appropriate sub-class of NumberFormat for a given
     * locale.
     *
     * @param pattern A non-localized pattern string.
     * @exception NullPointerException if <code>pattern</code> is null
     * @exception IllegalArgumentException if the given pattern is invalid.
     * @see java.text.NumberFormat#getInstance
     * @see java.text.NumberFormat#getNumberInstance
     * @see java.text.NumberFormat#getCurrencyInstance
     * @see java.text.NumberFormat#getPercentInstance
     */
    public DecimalFormat(String pattern) { }

    /** 
     * Creates a DecimalFormat using the given pattern and symbols.
     * Use this constructor when you need to completely customize the
     * behavior of the format.
     * <p>
     * To obtain standard formats for a given
     * locale, use the factory methods on NumberFormat such as
     * getInstance or getCurrencyInstance. If you need only minor adjustments
     * to a standard format, you can modify the format returned by
     * a NumberFormat factory method.
     *
     * @param pattern a non-localized pattern string
     * @param symbols the set of symbols to be used
     * @exception NullPointerException if any of the given arguments is null
     * @exception IllegalArgumentException if the given pattern is invalid
     * @see java.text.NumberFormat#getInstance
     * @see java.text.NumberFormat#getNumberInstance
     * @see java.text.NumberFormat#getCurrencyInstance
     * @see java.text.NumberFormat#getPercentInstance
     * @see java.text.DecimalFormatSymbols
     */
    public DecimalFormat(String pattern, DecimalFormatSymbols symbols) { }

    /** 
     * Formats a double to produce a string.
     * @param number    The double to format
     * @param result    where the text is to be appended
     * @param fieldPosition    On input: an alignment field, if desired.
     * On output: the offsets of the alignment field.
     * @return The formatted number string
     * @see java.text.FieldPosition
     */
    public StringBuffer format(double number, StringBuffer result, FieldPosition
        fieldPosition)
    {
        return null;
    }

    /** 
     * Format a long to produce a string.
     * @param number    The long to format
     * @param result    where the text is to be appended
     * @param fieldPosition    On input: an alignment field, if desired.
     * On output: the offsets of the alignment field.
     * @return The formatted number string
     * @see java.text.FieldPosition
     */
    public StringBuffer format(long number, StringBuffer result, FieldPosition
        fieldPosition)
    {
        return null;
    }

    /** 
     * Formats an Object producing an <code>AttributedCharacterIterator</code>.
     * You can use the returned <code>AttributedCharacterIterator</code>
     * to build the resulting String, as well as to determine information
     * about the resulting String.
     * <p>
     * Each attribute key of the AttributedCharacterIterator will be of type
     * <code>NumberFormat.Field</code>, with the attribute value being the
     * same as the attribute key.
     *
     * @exception NullPointerException if obj is null.
     * @exception IllegalArgumentException when the Format cannot format the
     *            given object.
     * @param obj The object to format
     * @return AttributedCharacterIterator describing the formatted value.
     * @since 1.4
     */
    public AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return null;
    }

    /** 
     * Parses text from a string to produce a <code>Number</code>.
     * <p>
     * The method attempts to parse text starting at the index given by
     * <code>pos</code>.
     * If parsing succeeds, then the index of <code>pos</code> is updated
     * to the index after the last character used (parsing does not necessarily
     * use all characters up to the end of the string), and the parsed
     * number is returned. The updated <code>pos</code> can be used to
     * indicate the starting point for the next call to this method.
     * If an error occurs, then the index of <code>pos</code> is not
     * changed, the error index of <code>pos</code> is set to the index of
     * the character where the error occurred, and null is returned.
     * <p>
     * The most economical subclass that can represent the number given by the
     * string is chosen. Most integer values are returned as <code>Long</code>
     * objects, no matter how they are written: <code>"17"</code> and
     * <code>"17.000"</code> both parse to <code>Long(17)</code>. Values that
     * cannot fit into a <code>Long</code> are returned as
     * <code>Double</code>s. This includes values with a fractional part,
     * infinite values, <code>NaN</code>, and the value -0.0.
     * <code>DecimalFormat</code> does <em>not</em> decide whether to return
     * a <code>Double</code> or a <code>Long</code> based on the presence of a
     * decimal separator in the source string. Doing so would prevent integers
     * that overflow the mantissa of a double, such as
     * <code>"10,000,000,000,000,000.00"</code>, from being parsed accurately.
     * Currently, the only classes that <code>parse</code> returns are
     * <code>Long</code> and <code>Double</code>, but callers should not rely
     * on this. Callers may use the <code>Number</code> methods
     * <code>doubleValue</code>, <code>longValue</code>, etc., to obtain the
     * type they want.
     * <p>
     * <code>DecimalFormat</code> parses all Unicode characters that represent
     * decimal digits, as defined by <code>Character.digit()</code>. In
     * addition, <code>DecimalFormat</code> also recognizes as digits the ten
     * consecutive characters starting with the localized zero digit defined in
     * the <code>DecimalFormatSymbols</code> object.
     *
     * @param text the string to be parsed
     * @param pos A <code>ParsePosition</code> object with index and error
     *            index information as described above.
     * @return the parsed value, or <code>null</code> if the parse fails
     * @exception NullPointerException if <code>text</code> or
     *            <code>pos</code> is null.
     */
    public Number parse(String text, ParsePosition pos) {
        return null;
    }

    /** 
     * Returns the decimal format symbols, which is generally not changed
     * by the programmer or user.
     * @return desired DecimalFormatSymbols
     * @see java.text.DecimalFormatSymbols
     */
    public DecimalFormatSymbols getDecimalFormatSymbols() {
        return null;
    }

    /** 
     * Sets the decimal format symbols, which is generally not changed
     * by the programmer or user.
     * @param newSymbols desired DecimalFormatSymbols
     * @see java.text.DecimalFormatSymbols
     */
    public void setDecimalFormatSymbols(DecimalFormatSymbols newSymbols) { }

    /** 
     * Get the positive prefix.
     * <P>Examples: +123, $123, sFr123
     */
    public String getPositivePrefix() {
        return null;
    }

    /** 
     * Set the positive prefix.
     * <P>Examples: +123, $123, sFr123
     */
    public void setPositivePrefix(String newValue) { }

    /** 
     * Get the negative prefix.
     * <P>Examples: -123, ($123) (with negative suffix), sFr-123
     */
    public String getNegativePrefix() {
        return null;
    }

    /** 
     * Set the negative prefix.
     * <P>Examples: -123, ($123) (with negative suffix), sFr-123
     */
    public void setNegativePrefix(String newValue) { }

    /** 
     * Get the positive suffix.
     * <P>Example: 123%
     */
    public String getPositiveSuffix() {
        return null;
    }

    /** 
     * Set the positive suffix.
     * <P>Example: 123%
     */
    public void setPositiveSuffix(String newValue) { }

    /** 
     * Get the negative suffix.
     * <P>Examples: -123%, ($123) (with positive suffixes)
     */
    public String getNegativeSuffix() {
        return null;
    }

    /** 
     * Set the positive suffix.
     * <P>Examples: 123%
     */
    public void setNegativeSuffix(String newValue) { }

    /** 
     * Get the multiplier for use in percent, permill, etc.
     * For a percentage, set the suffixes to have "%" and the multiplier to be 100.
     * (For Arabic, use arabic percent symbol).
     * For a permill, set the suffixes to have "?" and the multiplier to be 1000.
     * <P>Examples: with 100, 1.23 -> "123", and "123" -> 1.23
     */
    public int getMultiplier() {
        return 0;
    }

    /** 
     * Set the multiplier for use in percent, permill, etc.
     * For a percentage, set the suffixes to have "%" and the multiplier to be 100.
     * (For Arabic, use arabic percent symbol).
     * For a permill, set the suffixes to have "?" and the multiplier to be 1000.
     * <P>Examples: with 100, 1.23 -> "123", and "123" -> 1.23
     */
    public void setMultiplier(int newValue) { }

    /** 
     * Return the grouping size. Grouping size is the number of digits between
     * grouping separators in the integer portion of a number.  For example,
     * in the number "123,456.78", the grouping size is 3.
     * @see #setGroupingSize
     * @see java.text.NumberFormat#isGroupingUsed
     * @see java.text.DecimalFormatSymbols#getGroupingSeparator
     */
    public int getGroupingSize() {
        return 0;
    }

    /** 
     * Set the grouping size. Grouping size is the number of digits between
     * grouping separators in the integer portion of a number.  For example,
     * in the number "123,456.78", the grouping size is 3.
     * @see #getGroupingSize
     * @see java.text.NumberFormat#setGroupingUsed
     * @see java.text.DecimalFormatSymbols#setGroupingSeparator
     */
    public void setGroupingSize(int newValue) { }

    /** 
     * Allows you to get the behavior of the decimal separator with integers.
     * (The decimal separator will always appear with decimals.)
     * <P>Example: Decimal ON: 12345 -> 12345.; OFF: 12345 -> 12345
     */
    public boolean isDecimalSeparatorAlwaysShown() {
        return false;
    }

    /** 
     * Allows you to set the behavior of the decimal separator with integers.
     * (The decimal separator will always appear with decimals.)
     * <P>Example: Decimal ON: 12345 -> 12345.; OFF: 12345 -> 12345
     */
    public void setDecimalSeparatorAlwaysShown(boolean newValue) { }

    /** 
     * Standard override; no change in semantics.
     */
    public Object clone() {
        return null;
    }

    /** 
     * Overrides equals
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Overrides hashCode
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Synthesizes a pattern string that represents the current state
     * of this Format object.
     * @see #applyPattern
     */
    public String toPattern() {
        return null;
    }

    /** 
     * Synthesizes a localized pattern string that represents the current
     * state of this Format object.
     * @see #applyPattern
     */
    public String toLocalizedPattern() {
        return null;
    }

    /** 
     * Apply the given pattern to this Format object.  A pattern is a
     * short-hand specification for the various formatting properties.
     * These properties can also be changed individually through the
     * various setter methods.
     * <p>
     * There is no limit to integer digits are set
     * by this routine, since that is the typical end-user desire;
     * use setMaximumInteger if you want to set a real value.
     * For negative numbers, use a second pattern, separated by a semicolon
     * <P>Example <code>"#,#00.0#"</code> -> 1,234.56
     * <P>This means a minimum of 2 integer digits, 1 fraction digit, and
     * a maximum of 2 fraction digits.
     * <p>Example: <code>"#,#00.0#;(#,#00.0#)"</code> for negatives in
     * parentheses.
     * <p>In negative patterns, the minimum and maximum counts are ignored;
     * these are presumed to be set in the positive pattern.
     *
     * @exception NullPointerException if <code>pattern</code> is null
     * @exception IllegalArgumentException if the given pattern is invalid.
     */
    public void applyPattern(String pattern) { }

    /** 
     * Apply the given pattern to this Format object.  The pattern
     * is assumed to be in a localized notation. A pattern is a
     * short-hand specification for the various formatting properties.
     * These properties can also be changed individually through the
     * various setter methods.
     * <p>
     * There is no limit to integer digits are set
     * by this routine, since that is the typical end-user desire;
     * use setMaximumInteger if you want to set a real value.
     * For negative numbers, use a second pattern, separated by a semicolon
     * <P>Example <code>"#,#00.0#"</code> -> 1,234.56
     * <P>This means a minimum of 2 integer digits, 1 fraction digit, and
     * a maximum of 2 fraction digits.
     * <p>Example: <code>"#,#00.0#;(#,#00.0#)"</code> for negatives in
     * parentheses.
     * <p>In negative patterns, the minimum and maximum counts are ignored;
     * these are presumed to be set in the positive pattern.
     *
     * @exception NullPointerException if <code>pattern</code> is null
     * @exception IllegalArgumentException if the given pattern is invalid.
     */
    public void applyLocalizedPattern(String pattern) { }

    /** 
     * Sets the maximum number of digits allowed in the integer portion of a
     * number. This override limits the integer digit count to 309.
     * @see NumberFormat#setMaximumIntegerDigits
     */
    public void setMaximumIntegerDigits(int newValue) { }

    /** 
     * Sets the minimum number of digits allowed in the integer portion of a
     * number. This override limits the integer digit count to 309.
     * @see NumberFormat#setMinimumIntegerDigits
     */
    public void setMinimumIntegerDigits(int newValue) { }

    /** 
     * Sets the maximum number of digits allowed in the fraction portion of a
     * number. This override limits the fraction digit count to 340.
     * @see NumberFormat#setMaximumFractionDigits
     */
    public void setMaximumFractionDigits(int newValue) { }

    /** 
     * Sets the minimum number of digits allowed in the fraction portion of a
     * number. This override limits the fraction digit count to 340.
     * @see NumberFormat#setMinimumFractionDigits
     */
    public void setMinimumFractionDigits(int newValue) { }

    /** 
     * Gets the currency used by this decimal format when formatting
     * currency values.
     * The currency is obtained by calling
     * {@link DecimalFormatSymbols#getCurrency DecimalFormatSymbols.getCurrency}
     * on this number format's symbols.
     *
     * @return the currency used by this decimal format, or <code>null</code>
     * @since 1.4
     */
    public Currency getCurrency() {
        return null;
    }

    /** 
     * Sets the currency used by this number format when formatting
     * currency values. This does not update the minimum or maximum
     * number of fraction digits used by the number format.
     * The currency is set by calling
     * {@link DecimalFormatSymbols#setCurrency DecimalFormatSymbols.setCurrency}
     * on this number format's symbols.
     *
     * @param currency the new currency to be used by this decimal format
     * @exception NullPointerException if <code>currency</code> is null
     * @since 1.4
     */
    public void setCurrency(Currency currency) { }

    /** 
     * First, read the default serializable fields from the stream.  Then
     * if <code>serialVersionOnStream</code> is less than 1, indicating that
     * the stream was written by JDK 1.1, initialize <code>useExponentialNotation</code>
     * to false, since it was not present in JDK 1.1.
     * Finally, set serialVersionOnStream back to the maximum allowed value so that
     * default serialization will work properly if this object is streamed out again.
     *
     * <p>If the minimum or maximum integer digit count is larger than
     * <code>DOUBLE_INTEGER_DIGITS</code> or if the minimum or maximum fraction
     * digit count is larger than <code>DOUBLE_FRACTION_DIGITS</code>, then the
     * stream data is invalid and this method throws an
     * <code>InvalidObjectException</code>.
     *
     * <p>Stream versions older than 2 will not have the affix pattern variables
     * <code>posPrefixPattern</code> etc.  As a result, they will be initialized
     * to <code>null</code>, which means the affix strings will be taken as
     * literal values.  This is exactly what we want, since that corresponds to
     * the pre-version-2 behavior.
     */
    private void readObject(ObjectInputStream stream)
        throws IOException, ClassNotFoundException
    { }

    static final long serialVersionUID = 864413376551465018L;
}
