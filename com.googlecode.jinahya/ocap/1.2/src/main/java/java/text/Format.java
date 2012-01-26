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

import java.io.Serializable;

/** 
 * <code>Format</code> is an abstract base class for formatting locale-sensitive
 * information such as dates, messages, and numbers.
 *
 * <p>
 * <code>Format</code> defines the programming interface for formatting
 * locale-sensitive objects into <code>String</code>s (the
 * <code>format</code> method) and for parsing <code>String</code>s back
 * into objects (the <code>parseObject</code> method).
 *
 * <p>
 * Generally, a format's <code>parseObject</code> method must be able to parse
 * any string formatted by its <code>format</code> method. However, there may 
 * be exceptional cases where this is not possible. For example, a
 * <code>format</code> method might create two adjacent integer numbers with
 * no separator in between, and in this case the <code>parseObject</code> could
 * not tell which digits belong to which number.
 *
 * <h4>Subclassing</h4>
 *
 * <p>
 * The Java 2 platform provides three specialized subclasses of <code>Format</code>--
 * <code>DateFormat</code>, <code>MessageFormat</code>, and
 * <code>NumberFormat</code>--for formatting dates, messages, and numbers,
 * respectively.
 * <p>
 * Concrete subclasses must implement three methods:
 * <ol>
 * <li> <code>format(Object obj, StringBuffer toAppendTo, FieldPosition pos)</code>
 * <li> <code>formatToCharacterIterator(Object obj)</code>
 * <li> <code>parseObject(String source, ParsePosition pos)</code>
 * </ol>
 * These general methods allow polymorphic parsing and formatting of objects
 * and are used, for example, by <code>MessageFormat</code>.
 * Subclasses often also provide additional <code>format</code> methods for
 * specific input types as well as <code>parse</code> methods for specific
 * result types. Any <code>parse</code> method that does not take a
 * <code>ParsePosition</code> argument should throw <code>ParseException</code>
 * when no text in the required format is at the beginning of the input text.
 *
 * <p>
 * Most subclasses will also implement the following factory methods:
 * <ol>
 * <li>
 * <code>getInstance</code> for getting a useful format object appropriate
 * for the current locale
 * <li>
 * <code>getInstance(Locale)</code> for getting a useful format
 * object appropriate for the specified locale
 * </ol>
 * In addition, some subclasses may also implement other
 * <code>getXxxxInstance</code> methods for more specialized control. For
 * example, the <code>NumberFormat</code> class provides
 * <code>getPercentInstance</code> and <code>getCurrencyInstance</code>
 * methods for getting specialized number formatters.
 *
 * <p>
 * Subclasses of <code>Format</code> that allow programmers to create objects
 * for locales (with <code>getInstance(Locale)</code> for example)
 * must also implement the following class method:
 * <blockquote>
 * <pre>
 * public static Locale[] getAvailableLocales()
 * </pre>
 * </blockquote>
 *
 * <p>
 * And finally subclasses may define a set of constants to identify the various
 * fields in the formatted output. These constants are used to create a FieldPosition
 * object which identifies what information is contained in the field and its
 * position in the formatted result. These constants should be named
 * <code><em>item</em>_FIELD</code> where <code><em>item</em></code> identifies
 * the field. For examples of these constants, see <code>ERA_FIELD</code> and its
 * friends in {@link DateFormat}.
 *
 * <h4><a name="synchronization">Synchronization</a></h4>
 *
 * <p>
 * Formats are generally not synchronized.
 * It is recommended to create separate format instances for each thread.
 * If multiple threads access a format concurrently, it must be synchronized
 * externally.
 *
 * @see          java.text.ParsePosition
 * @see          java.text.FieldPosition
 * @see          java.text.NumberFormat
 * @see          java.text.DateFormat
 * @see          java.text.MessageFormat
 * @version      1.28, 01/19/00
 * @author       Mark Davis
 */
public abstract class Format implements Serializable, Cloneable
{
    private static final long serialVersionUID = -299282585814624189L;

    public Format() { }

    /** 
     * Formats an object to produce a string. This is equivalent to
     * <blockquote>
     * {@link #format(Object, StringBuffer, FieldPosition) format}<code>(obj,
     *         new StringBuffer(), new FieldPosition(0)).toString();</code>
     * </blockquote>
     *
     * @param obj    The object to format
     * @return       Formatted string.
     * @exception IllegalArgumentException if the Format cannot format the given
     *            object
     */
    public final String format(Object obj) {
        return null;
    }

    /** 
     * Formats an object and appends the resulting text to a given string
     * buffer.
     * If the <code>pos</code> argument identifies a field used by the format,
     * then its indices are set to the beginning and end of the first such
     * field encountered.
     *
     * @param obj    The object to format
     * @param toAppendTo    where the text is to be appended
     * @param pos    A <code>FieldPosition</code> identifying a field
     *               in the formatted text
     * @return       the string buffer passed in as <code>toAppendTo</code>,
     *               with formatted text appended
     * @exception NullPointerException if <code>toAppendTo</code> or
     *            <code>pos</code> is null
     * @exception IllegalArgumentException if the Format cannot format the given
     *            object
     */
    public abstract StringBuffer format(Object obj, StringBuffer toAppendTo,
        FieldPosition pos);

    /** 
     * Formats an Object producing an <code>AttributedCharacterIterator</code>.
     * You can use the returned <code>AttributedCharacterIterator</code>
     * to build the resulting String, as well as to determine information
     * about the resulting String.
     * <p>
     * Each attribute key of the AttributedCharacterIterator will be of type
     * <code>Field</code>. It is up to each <code>Format</code> implementation
     * to define what the legal values are for each attribute in the
     * <code>AttributedCharacterIterator</code>, but typically the attribute
     * key is also used as the attribute value.
     * <p>The default implementation creates an
     * <code>AttributedCharacterIterator</code> with no attributes. Subclasses
     * that support fields should override this and create an
     * <code>AttributedCharacterIterator</code> with meaningful attributes.
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
     * Parses text from a string to produce an object.
     * <p>
     * The method attempts to parse text starting at the index given by
     * <code>pos</code>.
     * If parsing succeeds, then the index of <code>pos</code> is updated
     * to the index after the last character used (parsing does not necessarily
     * use all characters up to the end of the string), and the parsed
     * object is returned. The updated <code>pos</code> can be used to
     * indicate the starting point for the next call to this method.
     * If an error occurs, then the index of <code>pos</code> is not
     * changed, the error index of <code>pos</code> is set to the index of
     * the character where the error occurred, and null is returned.
     *
     * @param source A <code>String</code>, part of which should be parsed.
     * @param pos A <code>ParsePosition</code> object with index and error
     *            index information as described above.
     * @return An <code>Object</code> parsed from the string. In case of
     *         error, returns null.
     * @exception NullPointerException if <code>pos</code> is null.
     */
    public abstract Object parseObject(String source, ParsePosition pos);

    /** 
     * Parses text from the beginning of the given string to produce an object.
     * The method may not use the entire text of the given string.
     *
     * @param source A <code>String</code> whose beginning should be parsed.
     * @return An <code>Object</code> parsed from the string.
     * @exception ParseException if the beginning of the specified string
     *            cannot be parsed.
     */
    public Object parseObject(String source) throws ParseException {
        return null;
    }

    /** 
     * Creates and returns a copy of this object.
     *
     * @return a clone of this instance.
     */
    public Object clone() {
        return null;
    }

    /** 
     * Defines constants that are used as attribute keys in the
     * <code>AttributedCharacterIterator</code> returned
     * from <code>Format.formatToCharacterIterator</code> and as
     * field identifiers in <code>FieldPosition</code>.
     *
     * @since 1.4
     */
    public static class Field extends AttributedCharacterIterator.Attribute
    {

        /** 
         * Creates a Field with the specified name.
         *
         * @param name Name of the attribute
         */
        protected Field(String name) { 
            super(name);
        }
    }
}
