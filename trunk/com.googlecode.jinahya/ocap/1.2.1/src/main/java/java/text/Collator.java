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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/** 
 * The <code>Collator</code> class performs locale-sensitive
 * <code>String</code> comparison. You use this class to build
 * searching and sorting routines for natural language text.
 *
 * <p>
 * <code>Collator</code> is an abstract base class. Subclasses
 * implement specific collation strategies. One subclass,
 * <code>RuleBasedCollator</code>, is currently provided with
 * the Java 2 platform and is applicable to a wide set of languages. Other
 * subclasses may be created to handle more specialized needs.
 *
 * <p>
 * Like other locale-sensitive classes, you can use the static
 * factory method, <code>getInstance</code>, to obtain the appropriate
 * <code>Collator</code> object for a given locale. You will only need
 * to look at the subclasses of <code>Collator</code> if you need
 * to understand the details of a particular collation strategy or
 * if you need to modify that strategy.
 *
 * <p>
 * The following example shows how to compare two strings using
 * the <code>Collator</code> for the default locale.
 * <blockquote>
 * <pre>
 * // Compare two strings in the default locale
 * Collator myCollator = Collator.getInstance();
 * if( myCollator.compare("abc", "ABC") < 0 )
 *     System.out.println("abc is less than ABC");
 * else
 *     System.out.println("abc is greater than or equal to ABC");
 * </pre>
 * </blockquote>
 *
 * <p>
 * You can set a <code>Collator</code>'s <em>strength</em> property
 * to determine the level of difference considered significant in
 * comparisons. Four strengths are provided: <code>PRIMARY</code>,
 * <code>SECONDARY</code>, <code>TERTIARY</code>, and <code>IDENTICAL</code>.
 * The exact assignment of strengths to language features is
 * locale dependant.  For example, in Czech, "e" and "f" are considered
 * primary differences, while "e" and "?" are secondary differences,
 * "e" and "E" are tertiary differences and "e" and "e" are identical.
 * The following shows how both case and accents could be ignored for
 * US English.
 * <blockquote>
 * <pre>
 * //Get the Collator for US English and set its strength to PRIMARY
 * Collator usCollator = Collator.getInstance(Locale.US);
 * usCollator.setStrength(Collator.PRIMARY);
 * if( usCollator.compare("abc", "ABC") == 0 ) {
 *     System.out.println("Strings are equivalent");
 * }
 * </pre>
 * </blockquote>
 * <p>
 * For comparing <code>String</code>s exactly once, the <code>compare</code>
 * method provides the best performance. When sorting a list of
 * <code>String</code>s however, it is generally necessary to compare each
 * <code>String</code> multiple times. In this case, <code>CollationKey</code>s
 * provide better performance. The <code>CollationKey</code> class converts
 * a <code>String</code> to a series of bits that can be compared bitwise
 * against other <code>CollationKey</code>s. A <code>CollationKey</code> is
 * created by a <code>Collator</code> object for a given <code>String</code>.
 * <br>
 * <strong>Note:</strong> <code>CollationKey</code>s from different
 * <code>Collator</code>s can not be compared. See the class description
 * for {@link CollationKey}
 * for an example using <code>CollationKey</code>s.
 *
 * @see         RuleBasedCollator
 * @see         CollationKey
 * @see         CollationElementIterator
 * @see         Locale
 * @version     1.34, 01/27/03
 * @author      Helena Shih, Laura Werner, Richard Gillam
 */
public abstract class Collator implements java.util.Comparator, Cloneable
{
    /** 
     * Collator strength value.  When set, only PRIMARY differences are
     * considered significant during comparison. The assignment of strengths
     * to language features is locale dependant. A common example is for
     * different base letters ("a" vs "b") to be considered a PRIMARY difference.
     * @see java.text.Collator#setStrength
     * @see java.text.Collator#getStrength
     */
    public static final int PRIMARY = 0;

    /** 
     * Collator strength value.  When set, only SECONDARY and above differences are
     * considered significant during comparison. The assignment of strengths
     * to language features is locale dependant. A common example is for
     * different accented forms of the same base letter ("a" vs "?") to be
     * considered a SECONDARY difference.
     * @see java.text.Collator#setStrength
     * @see java.text.Collator#getStrength
     */
    public static final int SECONDARY = 1;

    /** 
     * Collator strength value.  When set, only TERTIARY and above differences are
     * considered significant during comparison. The assignment of strengths
     * to language features is locale dependant. A common example is for
     * case differences ("a" vs "A") to be considered a TERTIARY difference.
     * @see java.text.Collator#setStrength
     * @see java.text.Collator#getStrength
     */
    public static final int TERTIARY = 2;

    /** 
     * Collator strength value.  When set, all differences are
     * considered significant during comparison. The assignment of strengths
     * to language features is locale dependant. A common example is for control
     * characters ("&#092;u0001" vs "&#092;u0002") to be considered equal at the
     * PRIMARY, SECONDARY, and TERTIARY levels but different at the IDENTICAL
     * level.  Additionally, differences between pre-composed accents such as
     * "&#092;u00C0" (A-grave) and combining accents such as "A&#092;u0300"
     * (A, combining-grave) will be considered significant at the tertiary
     * level if decomposition is set to NO_DECOMPOSITION.
     */
    public static final int IDENTICAL = 3;

    /** 
     * Decomposition mode value. With NO_DECOMPOSITION
     * set, accented characters will not be decomposed for collation. This
     * is the default setting and provides the fastest collation but 
     * will only produce correct results for languages that do not use accents.
     * @see java.text.Collator#getDecomposition
     * @see java.text.Collator#setDecomposition
     */
    public static final int NO_DECOMPOSITION = 0;

    /** 
     * Decomposition mode value. With CANONICAL_DECOMPOSITION
     * set, characters that are canonical variants according to Unicode 2.0
     * will be decomposed for collation. This 
     * should be used to get correct collation of accented characters.
     * <p>
     * CANONICAL_DECOMPOSITION corresponds to Normalization Form D as
     * described in 
     * <a href="http://www.unicode.org/unicode/reports/tr15/">Unicode 
     * Technical Report #15</a>.
     * @see java.text.Collator#getDecomposition
     * @see java.text.Collator#setDecomposition
     */
    public static final int CANONICAL_DECOMPOSITION = 1;

    /** 
     * Decomposition mode value. With FULL_DECOMPOSITION
     * set, both Unicode canonical variants and Unicode compatibility variants
     * will be decomposed for collation.  This causes not only accented
     * characters to be collated, but also characters that have special formats
     * to be collated with their norminal form. For example, the half-width and
     * full-width ASCII and Katakana characters are then collated together.
     * FULL_DECOMPOSITION is the most complete and therefore the slowest
     * decomposition mode.
     * <p>
     * FULL_DECOMPOSITION corresponds to Normalization Form KD as
     * described in 
     * <a href="http://www.unicode.org/unicode/reports/tr15/">Unicode 
     * Technical Report #15</a>.
     * @see java.text.Collator#getDecomposition
     * @see java.text.Collator#setDecomposition
     */
    public static final int FULL_DECOMPOSITION = 2;

    /** 
     * Default constructor.  This constructor is
     * protected so subclasses can get access to it. Users typically create
     * a Collator sub-class by calling the factory method getInstance.
     * @see java.text.Collator#getInstance
     */
    protected Collator() { }

    /** 
     * Gets the Collator for the current default locale.
     * The default locale is determined by java.util.Locale.getDefault.
     * @return the Collator for the default locale.(for example, en_US)
     * @see java.util.Locale#getDefault
     */
    public static synchronized Collator getInstance() {
        return null;
    }

    /** 
     * Gets the Collator for the desired locale.
     * @param desiredLocale the desired locale.
     * @return the Collator for the desired locale.
     * @see java.util.Locale
     * @see java.util.ResourceBundle
     */
    public static synchronized Collator getInstance(Locale desiredLocale) {
        return null;
    }

    /** 
     * Compares the source string to the target string according to the
     * collation rules for this Collator.  Returns an integer less than,
     * equal to or greater than zero depending on whether the source String is
     * less than, equal to or greater than the target string.  See the Collator
     * class description for an example of use.
     * <p>
     * For a one time comparison, this method has the best performance. If a
     * given String will be involved in multiple comparisons, CollationKey.compareTo
     * has the best performance. See the Collator class description for an example
     * using CollationKeys.
     * @param source the source string.
     * @param target the target string.
     * @return Returns an integer value. Value is less than zero if source is less than
     * target, value is zero if source and target are equal, value is greater than zero
     * if source is greater than target.
     * @see java.text.CollationKey
     * @see java.text.Collator#getCollationKey
     */
    public abstract int compare(String source, String target);

    /** 
     * Compares its two arguments for order.  Returns a negative integer,
     * zero, or a positive integer as the first argument is less than, equal
     * to, or greater than the second.
     * <p>
     * This implementation merely returns
     *  <code> compare((String)o1, (String)o2) </code>.
     * 
     * @return a negative integer, zero, or a positive integer as the
     *         first argument is less than, equal to, or greater than the
     *         second. 
     * @exception ClassCastException the arguments cannot be cast to Strings.
     * @see java.util.Comparator
     * @since   1.2
     */
    public int compare(Object o1, Object o2) {
        return 0;
    }

    /** 
     * Transforms the String into a series of bits that can be compared bitwise
     * to other CollationKeys. CollationKeys provide better performance than
     * Collator.compare when Strings are involved in multiple comparisons.
     * See the Collator class description for an example using CollationKeys.
     * @param source the string to be transformed into a collation key.
     * @return the CollationKey for the given String based on this Collator's collation
     * rules. If the source String is null, a null CollationKey is returned.
     * @see java.text.CollationKey
     * @see java.text.Collator#compare
     */
    public abstract CollationKey getCollationKey(String source);

    /** 
     * Convenience method for comparing the equality of two strings based on
     * this Collator's collation rules.
     * @param source the source string to be compared with.
     * @param target the target string to be compared with.
     * @return true if the strings are equal according to the collation
     * rules.  false, otherwise.
     * @see java.text.Collator#compare
     */
    public boolean equals(String source, String target) {
        return false;
    }

    /** 
     * Returns this Collator's strength property.  The strength property determines
     * the minimum level of difference considered significant during comparison.
     * See the Collator class description for an example of use.
     * @return this Collator's current strength property.
     * @see java.text.Collator#setStrength
     * @see java.text.Collator#PRIMARY
     * @see java.text.Collator#SECONDARY
     * @see java.text.Collator#TERTIARY
     * @see java.text.Collator#IDENTICAL
     */
    public synchronized int getStrength() {
        return 0;
    }

    /** 
     * Sets this Collator's strength property.  The strength property determines
     * the minimum level of difference considered significant during comparison.
     * See the Collator class description for an example of use.
     * @param newStrength  the new strength value.
     * @see java.text.Collator#getStrength
     * @see java.text.Collator#PRIMARY
     * @see java.text.Collator#SECONDARY
     * @see java.text.Collator#TERTIARY
     * @see java.text.Collator#IDENTICAL
     * @exception  IllegalArgumentException If the new strength value is not one of
     * PRIMARY, SECONDARY, TERTIARY or IDENTICAL.
     */
    public synchronized void setStrength(int newStrength) { }

    /** 
     * Get the decomposition mode of this Collator. Decomposition mode
     * determines how Unicode composed characters are handled. Adjusting
     * decomposition mode allows the user to select between faster and more
     * complete collation behavior.
     * <p>The three values for decomposition mode are:
     * <UL>
     * <LI>NO_DECOMPOSITION,
     * <LI>CANONICAL_DECOMPOSITION
     * <LI>FULL_DECOMPOSITION.
     * </UL>
     * See the documentation for these three constants for a description
     * of their meaning.
     * @return the decomposition mode
     * @see java.text.Collator#setDecomposition
     * @see java.text.Collator#NO_DECOMPOSITION
     * @see java.text.Collator#CANONICAL_DECOMPOSITION
     * @see java.text.Collator#FULL_DECOMPOSITION
     */
    public synchronized int getDecomposition() {
        return 0;
    }

    /** 
     * Set the decomposition mode of this Collator. See getDecomposition
     * for a description of decomposition mode.
     * @param decompositionMode  the new decomposition mode.
     * @see java.text.Collator#getDecomposition
     * @see java.text.Collator#NO_DECOMPOSITION
     * @see java.text.Collator#CANONICAL_DECOMPOSITION
     * @see java.text.Collator#FULL_DECOMPOSITION
     * @exception IllegalArgumentException If the given value is not a valid decomposition
     * mode.
     */
    public synchronized void setDecomposition(int decompositionMode) { }

    /** 
     * Get the set of Locales for which Collators are installed.
     * @return the list of available locales which collators are installed.
     */
    public static synchronized Locale[] getAvailableLocales() {
        return null;
    }

    /** 
     * Overrides Cloneable
     */
    public Object clone() {
        return null;
    }

    /** 
     * Compares the equality of two Collators.
     * @param that the Collator to be compared with this.
     * @return true if this Collator is the same as that Collator;
     * false otherwise.
     */
    public boolean equals(Object that) {
        return false;
    }

    /** 
     * Generates the hash code for this Collator.
     */
    public abstract int hashCode();
}
