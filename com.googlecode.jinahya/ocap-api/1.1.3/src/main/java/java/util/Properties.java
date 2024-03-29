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


  


package java.util;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Hashtable;

/** 
 * The <code>Properties</code> class represents a persistent set of
 * properties. The <code>Properties</code> can be saved to a stream
 * or loaded from a stream. Each key and its corresponding value in
 * the property list is a string.
 * <p>
 * A property list can contain another property list as its
 * "defaults"; this second property list is searched if
 * the property key is not found in the original property list.
 * <p>
 * Because <code>Properties</code> inherits from <code>Hashtable</code>, the
 * <code>put</code> and <code>putAll</code> methods can be applied to a
 * <code>Properties</code> object.  Their use is strongly discouraged as they
 * allow the caller to insert entries whose keys or values are not
 * <code>Strings</code>.  The <code>setProperty</code> method should be used
 * instead.  If the <code>store</code> or <code>save</code> method is called
 * on a "compromised" <code>Properties</code> object that contains a
 * non-<code>String</code> key or value, the call will fail.
 * <p>
 * <a name="encoding"></a>
 * When saving properties to a stream or loading them from a stream, the
 * ISO 8859-1 character encoding is used. For characters that cannot be directly
 * represented in this encoding,
 * <a href="http://java.sun.com/docs/books/jls/html/3.doc.html#100850">Unicode escapes</a>
 * are used; however, only a single 'u' character is allowed in an escape sequence.
 * The native2ascii tool can be used to convert property files to and from
 * other character encodings.
 * 
 * @see <a href="../../../tooldocs/solaris/native2ascii.html">native2ascii tool for Solaris</a>
 * @see <a href="../../../tooldocs/windows/native2ascii.html">native2ascii tool for Windows</a>
 *
 * @author  Arthur van Hoff
 * @author  Michael McCloskey
 * @version 1.61, 05/03/00
 * @since   JDK1.0
 */
public class Properties extends java.util.Hashtable
{
    /**
     * use serialVersionUID from JDK 1.1.X for interoperability
     */
     private static final long serialVersionUID = 4112578634029874840L;

    /** 
     * A property list that contains default values for any keys not
     * found in this property list.
     *
     * @serial
     */
    protected Properties defaults;

    /** 
     * Creates an empty property list with no default values.
     */
    public Properties() { }

    /** 
     * Creates an empty property list with the specified defaults.
     *
     * @param   defaults   the defaults.
     */
    public Properties(Properties defaults) { }

    /** 
     * Calls the <tt>Hashtable</tt> method <code>put</code>. Provided for
     * parallelism with the <tt>getProperty</tt> method. Enforces use of
     * strings for property keys and values. The value returned is the
     * result of the <tt>Hashtable</tt> call to <code>put</code>.
     *
     * @param key the key to be placed into this property list.
     * @param value the value corresponding to <tt>key</tt>.
     * @return     the previous value of the specified key in this property
     *             list, or <code>null</code> if it did not have one.
     * @see #getProperty
     * @since    1.2
     */
    public synchronized Object setProperty(String key, String value) {
        return null;
    }

    /** 
     * Reads a property list (key and element pairs) from the input
     * stream.  The stream is assumed to be using the ISO 8859-1
     * character encoding; that is each byte is one Latin1 character.
     * Characters not in Latin1, and certain special characters, can
     * be represented in keys and elements using escape sequences
     * similar to those used for character and string literals (see <a
     * href="http://java.sun.com/docs/books/jls/second_edition/html/lexical.doc.html#100850">&sect;3.3</a>
     * and <a
     * href="http://java.sun.com/docs/books/jls/second_edition/html/lexical.doc.html#101089">&sect;3.10.6</a>
     * of the <i>Java Language Specification</i>).
     *
     * The differences from the character escape sequences used for
     * characters and strings are:
     *
     * <ul>
     * <li> Octal escapes are not recognized.
     *
     * <li> The character sequence <code>\b</code> does <i>not</i>
     * represent a backspace character.
     *
     * <li> The method does not treat a backslash character,
     * <code>\</code>, before a non-valid escape character as an
     * error; the backslash is silently dropped.  For example, in a
     * Java string the sequence <code>"\z"</code> would cause a
     * compile time error.  In contrast, this method silently drops
     * the backslash.  Therefore, this method treats the two character
     * sequence <code>"\b"</code> as equivalent to the single
     * character <code>'b'</code>.
     *
     * <li> Escapes are not necessary for single and double quotes;
     * however, by the rule above, single and double quote characters
     * preceded by a backslash still yield single and double quote
     * characters, respectively.
     *
     * </ul>
     *
     * An <code>IllegalArgumentException</code> is thrown if a
     * malformed Unicode escape appears in the input.
     *
     * <p>
     * This method processes input in terms of lines.  A natural line
     * of input is terminated either by a set of line terminator
     * characters (<code>\n</code> or <code>\r</code> or
     * <code>\r\n</code>) or by the end of the file.  A natural line
     * may be either a blank line, a comment line, or hold some part
     * of a key-element pair.  The logical line holding all the data
     * for a key-element pair may be spread out across several adjacent
     * natural lines by escaping the line terminator sequence with a
     * backslash character, <code>\</code>.  Note that a comment line
     * cannot be extended in this manner; every natural line that is a
     * comment must have its own comment indicator, as described
     * below.  If a logical line is continued over several natural
     * lines, the continuation lines receive further processing, also
     * described below.  Lines are read from the input stream until
     * end of file is reached.
     *
     * <p>
     * A natural line that contains only white space characters is
     * considered blank and is ignored.  A comment line has an ASCII
     * <code>'#'</code> or <code>'!'</code> as its first non-white
     * space character; comment lines are also ignored and do not
     * encode key-element information.  In addition to line
     * terminators, this method considers the characters space
     * (<code>' '</code>, <code>'&#92;u0020'</code>), tab
     * (<code>'\t'</code>, <code>'&#92;u0009'</code>), and form feed
     * (<code>'\f'</code>, <code>'&#92;u000C'</code>) to be white
     * space.
     *
     * <p>
     * If a logical line is spread across several natural lines, the
     * backslash escaping the line terminator sequence, the line
     * terminator sequence, and any white space at the start the
     * following line have no affect on the key or element values.
     * The remainder of the discussion of key and element parsing will
     * assume all the characters constituting the key and element
     * appear on a single natural line after line continuation
     * characters have been removed.  Note that it is <i>not</i>
     * sufficient to only examine the character preceding a line
     * terminator sequence to to see if the line terminator is
     * escaped; there must be an odd number of contiguous backslashes
     * for the line terminator to be escaped.  Since the input is
     * processed from left to right, a non-zero even number of
     * 2<i>n</i> contiguous backslashes before a line terminator (or
     * elsewhere) encodes <i>n</i> backslashes after escape
     * processing.
     *
     * <p>
     * The key contains all of the characters in the line starting
     * with the first non-white space character and up to, but not
     * including, the first unescaped <code>'='</code>,
     * <code>':'</code>, or white space character other than a line
     * terminator. All of these key termination characters may be
     * included in the key by escaping them with a preceding backslash
     * character; for example,<p>
     *
     * <code>\:\=</code><p>
     *
     * would be the two-character key <code>":="</code>.  Line
     * terminator characters can be included using <code>\r</code> and
     * <code>\n</code> escape sequences.  Any white space after the
     * key is skipped; if the first non-white space character after
     * the key is <code>'='</code> or <code>':'</code>, then it is
     * ignored and any white space characters after it are also
     * skipped.  All remaining characters on the line become part of
     * the associated element string; if there are no remaining
     * characters, the element is the empty string
     * <code>&quot;&quot;</code>.  Once the raw character sequences
     * constituting the key and element are identified, escape
     * processing is performed as described above.
     *
     * <p>
     * As an example, each of the following three lines specifies the key
     * <code>"Truth"</code> and the associated element value
     * <code>"Beauty"</code>:
     * <p>
     * <pre>
     * Truth = Beauty
     *	Truth:Beauty
     * Truth			:Beauty
     * </pre>
     * As another example, the following three lines specify a single
     * property:
     * <p>
     * <pre>
     * fruits                           apple, banana, pear, \
     *                                  cantaloupe, watermelon, \
     *                                  kiwi, mango
     * </pre>
     * The key is <code>"fruits"</code> and the associated element is:
     * <p>
     * <pre>"apple, banana, pear, cantaloupe, watermelon, kiwi, mango"</pre>
     * Note that a space appears before each <code>\</code> so that a space
     * will appear after each comma in the final result; the <code>\</code>,
     * line terminator, and leading white space on the continuation line are
     * merely discarded and are <i>not</i> replaced by one or more other
     * characters.
     * <p>
     * As a third example, the line:
     * <p>
     * <pre>cheeses
     * </pre>
     * specifies that the key is <code>"cheeses"</code> and the associated
     * element is the empty string <code>""</code>.<p>
     *
     * @param      inStream   the input stream.
     * @exception  IOException  if an error occurred when reading from the
     *               input stream.
     * @throws	   IllegalArgumentException if the input stream contains a
     * 		   malformed Unicode escape sequence.
     */
    public synchronized void load(InputStream inStream) throws IOException { }

    /** 
     * Calls the <code>store(OutputStream out, String header)</code> method
     * and suppresses IOExceptions that were thrown.
     *
     * @deprecated This method does not throw an IOException if an I/O error
     * occurs while saving the property list.  As of the Java 2 platform v1.2, the preferred
     * way to save a properties list is via the <code>store(OutputStream out,
     * String header)</code> method.
     *
     * @param   out      an output stream.
     * @param   header   a description of the property list.
     * @exception  ClassCastException  if this <code>Properties</code> object
     *             contains any keys or values that are not <code>Strings</code>.
     */
    public synchronized void save(OutputStream out, String header) { }

    /** 
     * Writes this property list (key and element pairs) in this
     * <code>Properties</code> table to the output stream in a format suitable
     * for loading into a <code>Properties</code> table using the
     * {@link #load(InputStream) load} method.
     * The stream is written using the ISO 8859-1 character encoding.
     * <p>
     * Properties from the defaults table of this <code>Properties</code>
     * table (if any) are <i>not</i> written out by this method.
     * <p>
     * If the header argument is not null, then an ASCII <code>#</code>
     * character, the header string, and a line separator are first written
     * to the output stream. Thus, the <code>header</code> can serve as an
     * identifying comment.
     * <p>
     * Next, a comment line is always written, consisting of an ASCII
     * <code>#</code> character, the current date and time (as if produced
     * by the <code>toString</code> method of <code>Date</code> for the
     * current time), and a line separator as generated by the Writer.
     * <p>
     * Then every entry in this <code>Properties</code> table is
     * written out, one per line. For each entry the key string is
     * written, then an ASCII <code>=</code>, then the associated
     * element string. Each character of the key and element strings
     * is examined to see whether it should be rendered as an escape
     * sequence. The ASCII characters <code>\</code>, tab, form feed,
     * newline, and carriage return are written as <code>\\</code>,
     * <code>\t</code>, <code>\f</code> <code>\n</code>, and
     * <code>\r</code>, respectively. Characters less than
     * <code>&#92;u0020</code> and characters greater than
     * <code>&#92;u007E</code> are written as
     * <code>&#92;u</code><i>xxxx</i> for the appropriate hexadecimal
     * value <i>xxxx</i>.  For the key, all space characters are
     * written with a preceding <code>\</code> character.  For the
     * element, leading space characters, but not embedded or trailing
     * space characters, are written with a preceding <code>\</code>
     * character. The key and element characters <code>#</code>,
     * <code>!</code>, <code>=</code>, and <code>:</code> are written
     * with a preceding backslash to ensure that they are properly loaded.
     * <p>
     * After the entries have been written, the output stream is flushed.  The
     * output stream remains open after this method returns.
     *
     * @param   out      an output stream.
     * @param   header   a description of the property list.
     * @exception  IOException if writing this property list to the specified
     *             output stream throws an <tt>IOException</tt>.
     * @exception  ClassCastException  if this <code>Properties</code> object
     *             contains any keys or values that are not <code>Strings</code>.
     * @exception  NullPointerException  if <code>out</code> is null.
     * @since 1.2
     */
    public synchronized void store(OutputStream out, String header)
        throws IOException
    { }

    /** 
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns
     * <code>null</code> if the property is not found.
     *
     * @param   key   the property key.
     * @return  the value in this property list with the specified key value.
     * @see     #setProperty
     * @see     #defaults
     */
    public String getProperty(String key) {
        return null;
    }

    /** 
     * Searches for the property with the specified key in this property list.
     * If the key is not found in this property list, the default property list,
     * and its defaults, recursively, are then checked. The method returns the
     * default value argument if the property is not found.
     *
     * @param   key            the hashtable key.
     * @param   defaultValue   a default value.
     *
     * @return  the value in this property list with the specified key value.
     * @see     #setProperty
     * @see     #defaults
     */
    public String getProperty(String key, String defaultValue) {
        return null;
    }

    /** 
     * Returns an enumeration of all the keys in this property list,
     * including distinct keys in the default property list if a key
     * of the same name has not already been found from the main
     * properties list.
     *
     * @return  an enumeration of all the keys in this property list, including
     *          the keys in the default property list.
     * @see     java.util.Enumeration
     * @see     java.util.Properties#defaults
     */
    public Enumeration propertyNames() {
        return null;
    }

    /** 
     * Prints this property list out to the specified output stream.
     * This method is useful for debugging.
     *
     * @param   out   an output stream.
     */
    public void list(PrintStream out) { }

    /** 
     * Prints this property list out to the specified output stream.
     * This method is useful for debugging.
     *
     * @param   out   an output stream.
     * @since   JDK1.1
     */
    public void list(PrintWriter out) { }
}
