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

import java.util.Vector;
import java.util.Locale;

/** 
 * The <code>RuleBasedCollator</code> class is a concrete subclass of
 * <code>Collator</code> that provides a simple, data-driven, table
 * collator.  With this class you can create a customized table-based
 * <code>Collator</code>.  <code>RuleBasedCollator</code> maps
 * characters to sort keys.
 *
 * <p>
 * <code>RuleBasedCollator</code> has the following restrictions
 * for efficiency (other subclasses may be used for more complex languages) :
 * <ol>
 * <li>If a special collation rule controlled by a &lt;modifier&gt; is
 *      specified it applies to the whole collator object.
 * <li>All non-mentioned characters are at the end of the
 *     collation order.
 * </ol>
 *
 * <p>
 * The collation table is composed of a list of collation rules, where each
 * rule is of one of three forms:
 * <pre>
 *    &lt;modifier&gt;
 *    &lt;relation&gt; &lt;text-argument&gt;
 *    &lt;reset&gt; &lt;text-argument&gt;
 * </pre>
 * The definitions of the rule elements is as follows:
 * <UL Type=disc>
 *    <LI><strong>Text-Argument</strong>: A text-argument is any sequence of
 *        characters, excluding special characters (that is, common
 *        whitespace characters [0009-000D, 0020] and rule syntax characters
 *        [0021-002F, 003A-0040, 005B-0060, 007B-007E]). If those
 *        characters are desired, you can put them in single quotes
 *        (e.g. ampersand => '&'). Note that unquoted white space characters
 *        are ignored; e.g. <code>b c</code> is treated as <code>bc</code>.
 *    <LI><strong>Modifier</strong>: There are currently two modifiers that 
 *        turn on special collation rules.
 *        <UL Type=square>
 *            <LI>'@' : Turns on backwards sorting of accents (secondary
 *                      differences), as in French.
 *            <LI>'!' : Turns on Thai/Lao vowel-consonant swapping.  If this
 *                      rule is in force when a Thai vowel of the range
 *                      &#92;U0E40-&#92;U0E44 precedes a Thai consonant of the range
 *                      &#92;U0E01-&#92;U0E2E OR a Lao vowel of the range &#92;U0EC0-&#92;U0EC4
 *                      precedes a Lao consonant of the range &#92;U0E81-&#92;U0EAE then
 *                      the vowel is placed after the consonant for collation
 *                      purposes.
 *        </UL>
 *        <p>'@' : Indicates that accents are sorted backwards, as in French.
 *    <LI><strong>Relation</strong>: The relations are the following:
 *        <UL Type=square>
 *            <LI>'&lt;' : Greater, as a letter difference (primary)
 *            <LI>';' : Greater, as an accent difference (secondary)
 *            <LI>',' : Greater, as a case difference (tertiary)
 *            <LI>'=' : Equal
 *        </UL>
 *    <LI><strong>Reset</strong>: There is a single reset
 *        which is used primarily for contractions and expansions, but which
 *        can also be used to add a modification at the end of a set of rules.
 *        <p>'&' : Indicates that the next rule follows the position to where
 *            the reset text-argument would be sorted.
 * </UL>
 *
 * <p>
 * This sounds more complicated than it is in practice. For example, the
 * following are equivalent ways of expressing the same thing:
 * <blockquote>
 * <pre>
 * a &lt; b &lt; c
 * a &lt; b &amp; b &lt; c
 * a &lt; c &amp; a &lt; b
 * </pre>
 * </blockquote>
 * Notice that the order is important, as the subsequent item goes immediately
 * after the text-argument. The following are not equivalent:
 * <blockquote>
 * <pre>
 * a &lt; b &amp; a &lt; c
 * a &lt; c &amp; a &lt; b
 * </pre>
 * </blockquote>
 * Either the text-argument must already be present in the sequence, or some
 * initial substring of the text-argument must be present. (e.g. "a &lt; b &amp; ae &lt; 
 * e" is valid since "a" is present in the sequence before "ae" is reset). In
 * this latter case, "ae" is not entered and treated as a single character;
 * instead, "e" is sorted as if it were expanded to two characters: "a"
 * followed by an "e". This difference appears in natural languages: in
 * traditional Spanish "ch" is treated as though it contracts to a single
 * character (expressed as "c &lt; ch &lt; d"), while in traditional German
 * a-umlaut is treated as though it expanded to two characters
 * (expressed as "a,A &lt; b,B ... &amp;ae;&#92;u00e3&amp;AE;&#92;u00c3").
 * [&#92;u00e3 and &#92;u00c3 are, of course, the escape sequences for a-umlaut.]
 * <p>
 * <strong>Ignorable Characters</strong>
 * <p>
 * For ignorable characters, the first rule must start with a relation (the
 * examples we have used above are really fragments; "a &lt; b" really should be
 * "&lt; a &lt; b"). If, however, the first relation is not "&lt;", then all the all
 * text-arguments up to the first "&lt;" are ignorable. For example, ", - &lt; a &lt; b"
 * makes "-" an ignorable character, as we saw earlier in the word
 * "black-birds". In the samples for different languages, you see that most
 * accents are ignorable.
 *
 * <p><strong>Normalization and Accents</strong>
 * <p>
 * <code>RuleBasedCollator</code> automatically processes its rule table to
 * include both pre-composed and combining-character versions of
 * accented characters.  Even if the provided rule string contains only
 * base characters and separate combining accent characters, the pre-composed
 * accented characters matching all canonical combinations of characters from
 * the rule string will be entered in the table.
 * <p>
 * This allows you to use a RuleBasedCollator to compare accented strings
 * even when the collator is set to NO_DECOMPOSITION.  There are two caveats,
 * however.  First, if the strings to be collated contain combining
 * sequences that may not be in canonical order, you should set the collator to
 * CANONICAL_DECOMPOSITION or FULL_DECOMPOSITION to enable sorting of
 * combining sequences.  Second, if the strings contain characters with
 * compatibility decompositions (such as full-width and half-width forms),
 * you must use FULL_DECOMPOSITION, since the rule tables only include
 * canonical mappings.
 *
 * <p><strong>Errors</strong>
 * <p>
 * The following are errors:
 * <UL Type=disc>
 *     <LI>A text-argument contains unquoted punctuation symbols
 *        (e.g. "a &lt; b-c &lt; d").
 *     <LI>A relation or reset character not followed by a text-argument
 *        (e.g. "a &lt; ,b").
 *     <LI>A reset where the text-argument (or an initial substring of the
 *         text-argument) is not already in the sequence.
 *         (e.g. "a &lt; b &amp; e &lt; f")
 * </UL>
 * If you produce one of these errors, a <code>RuleBasedCollator</code> throws
 * a <code>ParseException</code>.
 * 
 * <p><strong>Examples</strong>
 * <p>Simple:     "&lt; a &lt; b &lt; c &lt; d"
 * <p>Norwegian:  "&lt; a,A&lt; b,B&lt; c,C&lt; d,D&lt; e,E&lt; f,F&lt; g,G&lt; h,H&lt; i,I&lt; j,J
 *                 &lt; k,K&lt; l,L&lt; m,M&lt; n,N&lt; o,O&lt; p,P&lt; q,Q&lt; r,R&lt; s,S&lt; t,T
 *                 &lt; u,U&lt; v,V&lt; w,W&lt; x,X&lt; y,Y&lt; z,Z
 *                 &lt; &#92;u00E5=a&#92;u030A,&#92;u00C5=A&#92;u030A
 *                 ;aa,AA&lt; &#92;u00E6,&#92;u00C6&lt; &#92;u00F8,&#92;u00D8"
 *
 * <p>
 * Normally, to create a rule-based Collator object, you will use
 * <code>Collator</code>'s factory method <code>getInstance</code>.
 * However, to create a rule-based Collator object with specialized
 * rules tailored to your needs, you construct the <code>RuleBasedCollator</code>
 * with the rules contained in a <code>String</code> object. For example:
 * <blockquote>
 * <pre>
 * String Simple = "&lt; a&lt; b&lt; c&lt; d";
 * RuleBasedCollator mySimple = new RuleBasedCollator(Simple);
 * </pre>
 * </blockquote>
 * Or:
 * <blockquote>
 * <pre>
 * String Norwegian = "&lt; a,A&lt; b,B&lt; c,C&lt; d,D&lt; e,E&lt; f,F&lt; g,G&lt; h,H&lt; i,I&lt; j,J" +
 *                 "&lt; k,K&lt; l,L&lt; m,M&lt; n,N&lt; o,O&lt; p,P&lt; q,Q&lt; r,R&lt; s,S&lt; t,T" +
 *                 "&lt; u,U&lt; v,V&lt; w,W&lt; x,X&lt; y,Y&lt; z,Z" +
 *                 "&lt; &#92;u00E5=a&#92;u030A,&#92;u00C5=A&#92;u030A" +
 *                 ";aa,AA&lt; &#92;u00E6,&#92;u00C6&lt; &#92;u00F8,&#92;u00D8";
 * RuleBasedCollator myNorwegian = new RuleBasedCollator(Norwegian);
 * </pre>
 * </blockquote>
 *
 * <p>
 * Combining <code>Collator</code>s is as simple as concatenating strings.
 * Here's an example that combines two <code>Collator</code>s from two
 * different locales:
 * <blockquote>
 * <pre>
 * // Create an en_US Collator object
 * RuleBasedCollator en_USCollator = (RuleBasedCollator)
 *     Collator.getInstance(new Locale("en", "US", ""));
 * // Create a da_DK Collator object
 * RuleBasedCollator da_DKCollator = (RuleBasedCollator)
 *     Collator.getInstance(new Locale("da", "DK", ""));
 * // Combine the two
 * // First, get the collation rules from en_USCollator
 * String en_USRules = en_USCollator.getRules();
 * // Second, get the collation rules from da_DKCollator
 * String da_DKRules = da_DKCollator.getRules();
 * RuleBasedCollator newCollator =
 *     new RuleBasedCollator(en_USRules + da_DKRules);
 * // newCollator has the combined rules
 * </pre>
 * </blockquote>
 *
 * <p>
 * Another more interesting example would be to make changes on an existing
 * table to create a new <code>Collator</code> object.  For example, add
 * "&amp;C&lt; ch, cH, Ch, CH" to the <code>en_USCollator</code> object to create
 * your own:
 * <blockquote>
 * <pre>
 * // Create a new Collator object with additional rules
 * String addRules = "&amp;C&lt; ch, cH, Ch, CH";
 * RuleBasedCollator myCollator =
 *     new RuleBasedCollator(en_USCollator + addRules);
 * // myCollator contains the new rules
 * </pre>
 * </blockquote>
 *
 * <p>
 * The following example demonstrates how to change the order of
 * non-spacing accents,
 * <blockquote>
 * <pre>
 * // old rule
 * String oldRules = "=&#92;u0301;&#92;u0300;&#92;u0302;&#92;u0308"    // main accents
 *                 + ";&#92;u0327;&#92;u0303;&#92;u0304;&#92;u0305"    // main accents
 *                 + ";&#92;u0306;&#92;u0307;&#92;u0309;&#92;u030A"    // main accents
 *                 + ";&#92;u030B;&#92;u030C;&#92;u030D;&#92;u030E"    // main accents
 *                 + ";&#92;u030F;&#92;u0310;&#92;u0311;&#92;u0312"    // main accents
 *                 + "&lt; a , A ; ae, AE ; &#92;u00e6 , &#92;u00c6"
 *                 + "&lt; b , B &lt; c, C &lt; e, E & C &lt; d, D";
 * // change the order of accent characters
 * String addOn = "& &#92;u0300 ; &#92;u0308 ; &#92;u0302";
 * RuleBasedCollator myCollator = new RuleBasedCollator(oldRules + addOn);
 * </pre>
 * </blockquote>
 *
 * <p>
 * The last example shows how to put new primary ordering in before the
 * default setting. For example, in Japanese <code>Collator</code>, you
 * can either sort English characters before or after Japanese characters,
 * <blockquote>
 * <pre>
 * // get en_US Collator rules
 * RuleBasedCollator en_USCollator = (RuleBasedCollator)Collator.getInstance(Locale.US);
 * // add a few Japanese character to sort before English characters
 * // suppose the last character before the first base letter 'a' in
 * // the English collation rule is &#92;u2212
 * String jaString = "& &#92;u2212 &lt; &#92;u3041, &#92;u3042 &lt; &#92;u3043, &#92;u3044";
 * RuleBasedCollator myJapaneseCollator = new
 *     RuleBasedCollator(en_USCollator.getRules() + jaString);
 * </pre>
 * </blockquote>
 *
 * @see        Collator
 * @see        CollationElementIterator
 * @version    1.25 07/24/98
 * @author     Helena Shih, Laura Werner, Richard Gillam
 */
public class RuleBasedCollator extends Collator
{

    /** 
     * RuleBasedCollator constructor.  This takes the table rules and builds
     * a collation table out of them.  Please see RuleBasedCollator class
     * description for more details on the collation rule syntax.
     * @see java.util.Locale
     * @param rules the collation rules to build the collation table from.
     * @exception ParseException A format exception
     * will be thrown if the build process of the rules fails. For
     * example, build rule "a < ? < d" will cause the constructor to
     * throw the ParseException because the '?' is not quoted.
     */
    public RuleBasedCollator(String rules) throws ParseException { }

    /** 
     * Gets the table-based rules for the collation object.
     * @return returns the collation rules that the table collation object
     * was created from.
     */
    public String getRules() {
        return null;
    }

    /** 
     * Return a CollationElementIterator for the given String.
     * @see java.text.CollationElementIterator
     */
    public CollationElementIterator getCollationElementIterator(String source) {
        return null;
    }

    /** 
     * Return a CollationElementIterator for the given String.
     * @see java.text.CollationElementIterator
     * @since 1.2
     */
    public CollationElementIterator
        getCollationElementIterator(CharacterIterator source)
    {
        return null;
    }

    /** 
     * Compares the character data stored in two different strings based on the
     * collation rules.  Returns information about whether a string is less
     * than, greater than or equal to another string in a language.
     * This can be overriden in a subclass.
     */
    public synchronized int compare(String source, String target) {
        return 0;
    }

    /** 
     * Transforms the string into a series of characters that can be compared
     * with CollationKey.compareTo. This overrides java.text.Collator.getCollationKey.
     * It can be overriden in a subclass.
     */
    public synchronized CollationKey getCollationKey(String source) {
        return null;
    }

    /** 
     * Standard override; no change in semantics.
     */
    public Object clone() {
        return null;
    }

    /** 
     * Compares the equality of two collation objects.
     * @param obj the table-based collation object to be compared with this.
     * @return true if the current table-based collation object is the same
     * as the table-based collation object obj; false otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Generates the hash code for the table-based collation object
     */
    public int hashCode() {
        return 0;
    }
}
