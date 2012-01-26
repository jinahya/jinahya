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

/** 
 * A <code>ChoiceFormat</code> allows you to attach a format to a range of numbers.
 * It is generally used in a <code>MessageFormat</code> for handling plurals.
 * The choice is specified with an ascending list of doubles, where each item
 * specifies a half-open interval up to the next item:
 * <blockquote>
 * <pre>
 * X matches j if and only if limit[j] &lt;= X &lt; limit[j+1]
 * </pre>
 * </blockquote>
 * If there is no match, then either the first or last index is used, depending
 * on whether the number (X) is too low or too high.  If the limit array is not
 * in ascending order, the results of formatting will be incorrect.  ChoiceFormat
 * also accepts <code>&#92;u221E</code> as equivalent to infinity(INF).
 *
 * <p>
 * <strong>Note:</strong>
 * <code>ChoiceFormat</code> differs from the other <code>Format</code>
 * classes in that you create a <code>ChoiceFormat</code> object with a
 * constructor (not with a <code>getInstance</code> style factory
 * method). The factory methods aren't necessary because <code>ChoiceFormat</code>
 * doesn't require any complex setup for a given locale. In fact,
 * <code>ChoiceFormat</code> doesn't implement any locale specific behavior.
 *
 * <p>
 * When creating a <code>ChoiceFormat</code>, you must specify an array of formats
 * and an array of limits. The length of these arrays must be the same.
 * For example,
 * <ul>
 * <li>
 *     <em>limits</em> = {1,2,3,4,5,6,7}<br>
 *     <em>formats</em> = {"Sun","Mon","Tue","Wed","Thur","Fri","Sat"}
 * <li>
 *     <em>limits</em> = {0, 1, ChoiceFormat.nextDouble(1)}<br>
 *     <em>formats</em> = {"no files", "one file", "many files"}<br>
 *     (<code>nextDouble</code> can be used to get the next higher double, to
 *     make the half-open interval.)
 * </ul>
 *
 * <p>
 * Here is a simple example that shows formatting and parsing:
 * <blockquote>
 * <pre>
 * double[] limits = {1,2,3,4,5,6,7};
 * String[] monthNames = {"Sun","Mon","Tue","Wed","Thur","Fri","Sat"};
 * ChoiceFormat form = new ChoiceFormat(limits, monthNames);
 * ParsePosition status = new ParsePosition(0);
 * for (double i = 0.0; i &lt;= 8.0; ++i) {
 *     status.setIndex(0);
 *     System.out.println(i + " -&gt; " + form.format(i) + " -&gt; "
 *                              + form.parse(form.format(i),status));
 * }
 * </pre>
 * </blockquote>
 * Here is a more complex example, with a pattern format:
 * <blockquote>
 * <pre>
 * double[] filelimits = {0,1,2};
 * String[] filepart = {"are no files","is one file","are {2} files"};
 * ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
 * Format[] testFormats = {fileform, null, NumberFormat.getInstance()};
 * MessageFormat pattform = new MessageFormat("There {0} on {1}");
 * pattform.setFormats(testFormats);
 * Object[] testArgs = {null, "ADisk", null};
 * for (int i = 0; i &lt; 4; ++i) {
 *     testArgs[0] = new Integer(i);
 *     testArgs[2] = testArgs[0];
 *     System.out.println(pattform.format(testArgs));
 * }
 * </pre>
 * </blockquote>
 * <p>
 * Specifying a pattern for ChoiceFormat objects is fairly straightforward.
 * For example:
 * <blockquote>
 * <pre>
 * ChoiceFormat fmt = new ChoiceFormat(
 *      "-1#is negative| 0#is zero or fraction | 1#is one |1.0&lt;is 1+ |2#is two |2&lt;is more than 2.");
 * System.out.println("Formatter Pattern : " + fmt.toPattern());
 *
 * System.out.println("Format with -INF : " + fmt.format(Double.NEGATIVE_INFINITY));
 * System.out.println("Format with -1.0 : " + fmt.format(-1.0));
 * System.out.println("Format with 0 : " + fmt.format(0));
 * System.out.println("Format with 0.9 : " + fmt.format(0.9));
 * System.out.println("Format with 1.0 : " + fmt.format(1));
 * System.out.println("Format with 1.5 : " + fmt.format(1.5));
 * System.out.println("Format with 2 : " + fmt.format(2));
 * System.out.println("Format with 2.1 : " + fmt.format(2.1));
 * System.out.println("Format with NaN : " + fmt.format(Double.NaN));
 * System.out.println("Format with +INF : " + fmt.format(Double.POSITIVE_INFINITY));
 * </pre>
 * </blockquote>
 * And the output result would be like the following:
 * <pre>
 * <blockquote>
 *   Format with -INF : is negative
 *   Format with -1.0 : is negative
 *   Format with 0 : is zero or fraction
 *   Format with 0.9 : is zero or fraction
 *   Format with 1.0 : is one
 *   Format with 1.5 : is 1+
 *   Format with 2 : is two
 *   Format with 2.1 : is more than 2.
 *   Format with NaN : is negative
 *   Format with +INF : is more than 2.
 * </pre>
 * </blockquote>
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 *
 * <p>
 * Choice formats are not synchronized.
 * It is recommended to create separate format instances for each thread.
 * If multiple threads access a format concurrently, it must be synchronized
 * externally.
 *
 *
 * @see          DecimalFormat
 * @see          MessageFormat
 * @version      1.33, 10/25/05
 */
public class ChoiceFormat extends NumberFormat
{
    /** 
     * A list of lower bounds for the choices.  The formatter will return
     * <code>choiceFormats[i]</code> if the number being formatted is greater than or equal to
     * <code>choiceLimits[i]</code> and less than <code>choiceLimits[i+1]</code>.
     * @serial
     */
    private double[] choiceLimits;

    /** 
     * A list of choice strings.  The formatter will return
     * <code>choiceFormats[i]</code> if the number being formatted is greater than or equal to
     * <code>choiceLimits[i]</code> and less than <code>choiceLimits[i+1]</code>.
     * @serial
     */
    private String[] choiceFormats;

    /** 
     * Constructs with limits and corresponding formats based on the pattern.
     * @see #applyPattern
     */
    public ChoiceFormat(String newPattern) { }

    /** 
     * Constructs with the limits and the corresponding formats.
     * @see #setChoices
     */
    public ChoiceFormat(double[] limits, String[] formats) { }

    /** 
     * Sets the pattern.
     * @param newPattern See the class description.
     */
    public void applyPattern(String newPattern) { }

    /** 
     * Gets the pattern.
     */
    public String toPattern() {
        return null;
    }

    /** 
     * Set the choices to be used in formatting.
     * @param limits contains the top value that you want
     * parsed with that format,and should be in ascending sorted order. When
     * formatting X, the choice will be the i, where
     * limit[i] &lt;= X &lt; limit[i+1].
     * If the limit array is not in ascending order, the results of formatting
     * will be incorrect.
     * @param formats are the formats you want to use for each limit.
     * They can be either Format objects or Strings.
     * When formatting with object Y,
     * if the object is a NumberFormat, then ((NumberFormat) Y).format(X)
     * is called. Otherwise Y.toString() is called.
     */
    public void setChoices(double[] limits, String[] formats) { }

    /** 
     * Get the limits passed in the constructor.
     * @return the limits.
     */
    public double[] getLimits() {
        return null;
    }

    /** 
     * Get the formats passed in the constructor.
     * @return the formats.
     */
    public Object[] getFormats() {
        return null;
    }

    /** 
     * Specialization of format. This method really calls
     * <code>format(double, StringBuffer, FieldPosition)</code>
     * thus the range of longs that are supported is only equal to
     * the range that can be stored by double. This will never be
     * a practical limitation.
     */
    public StringBuffer format(long number, StringBuffer toAppendTo,
        FieldPosition status)
    {
        return null;
    }

    /** 
     * Returns pattern with formatted double.
     * @param number number to be formatted & substituted.
     * @param toAppendTo where text is appended.
     * @param status ignore no useful status is returned.
     */
    public StringBuffer format(double number, StringBuffer toAppendTo,
        FieldPosition status)
    {
        return null;
    }

    /** 
     * Parses a Number from the input text.
     * @param text the source text.
     * @param status an input-output parameter.  On input, the
     * status.index field indicates the first character of the
     * source text that should be parsed.  On exit, if no error
     * occured, status.index is set to the first unparsed character
     * in the source text.  On exit, if an error did occur,
     * status.index is unchanged and status.errorIndex is set to the
     * first index of the character that caused the parse to fail.
     * @return A Number representing the value of the number parsed.
     */
    public Number parse(String text, ParsePosition status) {
        return null;
    }

    /** 
     * Finds the least double greater than d.
     * If NaN, returns same value.
     * <p>Used to make half-open intervals.
     * @see #previousDouble
     */
    public static final double nextDouble(double d) {
        return 0.0d;
    }

    /** 
     * Finds the greatest double less than d.
     * If NaN, returns same value.
     * @see #nextDouble
     */
    public static final double previousDouble(double d) {
        return 0.0d;
    }

    /** 
     * Overrides Cloneable
     */
    public Object clone() {
        return null;
    }

    /** 
     * Generates a hash code for the message format object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Equality comparision between two
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Finds the least double greater than d (if positive == true),
     * or the greatest double less than d (if positive == false).
     * If NaN, returns same value.
     *
     * Does not affect floating-point flags,
     * provided these member functions do not:
     *          Double.longBitsToDouble(long)
     *          Double.doubleToLongBits(double)
     *          Double.isNaN(double)
     */
    public static double nextDouble(double d, boolean positive) {
        return 0.0d;
    }

    /** 
     * After reading an object from the input stream, do a simple verification
     * to maintain class invariants.
     * @throws InvalidObjectException if the objects read from the stream is invalid.
     */
    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    { }
}
