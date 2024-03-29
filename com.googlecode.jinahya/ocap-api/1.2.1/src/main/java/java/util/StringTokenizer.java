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

/** 
 * The string tokenizer class allows an application to break a 
 * string into tokens. The tokenization method is much simpler than 
 * the one used by the <code>StreamTokenizer</code> class. The 
 * <code>StringTokenizer</code> methods do not distinguish among 
 * identifiers, numbers, and quoted strings, nor do they recognize 
 * and skip comments. 
 * <p>
 * The set of delimiters (the characters that separate tokens) may 
 * be specified either at creation time or on a per-token basis. 
 * <p>
 * An instance of <code>StringTokenizer</code> behaves in one of two 
 * ways, depending on whether it was created with the 
 * <code>returnDelims</code> flag having the value <code>true</code> 
 * or <code>false</code>: 
 * <ul>
 * <li>If the flag is <code>false</code>, delimiter characters serve to 
 *     separate tokens. A token is a maximal sequence of consecutive 
 *     characters that are not delimiters. 
 * <li>If the flag is <code>true</code>, delimiter characters are themselves 
 *     considered to be tokens. A token is thus either one delimiter 
 *     character, or a maximal sequence of consecutive characters that are 
 *     not delimiters.
 * </ul><p>
 * A <tt>StringTokenizer</tt> object internally maintains a current 
 * position within the string to be tokenized. Some operations advance this 
 * current position past the characters processed.<p>
 * A token is returned by taking a substring of the string that was used to 
 * create the <tt>StringTokenizer</tt> object.
 * <p>
 * The following is one example of the use of the tokenizer. The code:
 * <blockquote><pre>
 *     StringTokenizer st = new StringTokenizer("this is a test");
 *     while (st.hasMoreTokens()) {
 *         System.out.println(st.nextToken());
 *     }
 * </pre></blockquote>
 * <p>
 * prints the following output:
 * <blockquote><pre>
 *     this
 *     is
 *     a
 *     test
 * </pre></blockquote>
 *
 * <p>
 * <tt>StringTokenizer</tt> is a legacy class that is retained for
 * compatibility reasons although its use is discouraged in new code. It is
 * recommended that anyone seeking this functionality use the <tt>split</tt>
 * method of <tt>String</tt> or the java.util.regex package instead.
 * <p>
 * The following example illustrates how the <tt>String.split</tt>
 * method can be used to break up a string into its basic tokens:
 * <blockquote><pre>
 *     String[] result = "this is a test".split("\\s");
 *     for (int x=0; x&lt;result.length; x++)
 *         System.out.println(result[x]);
 * </pre></blockquote>
 * <p>
 * prints the following output:
 * <blockquote><pre>
 *     this
 *     is
 *     a
 *     test
 * </pre></blockquote>
 *
 * @author  unascribed
 * @version 1.25, 02/02/00
 * @see     java.io.StreamTokenizer
 * @since   JDK1.0
 */
public class StringTokenizer implements Enumeration
{

    /** 
     * Constructs a string tokenizer for the specified string. All  
     * characters in the <code>delim</code> argument are the delimiters 
     * for separating tokens. 
     * <p>
     * If the <code>returnDelims</code> flag is <code>true</code>, then 
     * the delimiter characters are also returned as tokens. Each 
     * delimiter is returned as a string of length one. If the flag is 
     * <code>false</code>, the delimiter characters are skipped and only 
     * serve as separators between tokens. 
     * <p>
     * Note that if <tt>delim</tt> is <tt>null</tt>, this constructor does
     * not throw an exception. However, trying to invoke other methods on the
     * resulting <tt>StringTokenizer</tt> may result in a 
     * <tt>NullPointerException</tt>.
     *
     * @param   str            a string to be parsed.
     * @param   delim          the delimiters.
     * @param   returnDelims   flag indicating whether to return the delimiters
     *                         as tokens.
     */
    public StringTokenizer(String str, String delim, boolean returnDelims) { }

    /** 
     * Constructs a string tokenizer for the specified string. The 
     * characters in the <code>delim</code> argument are the delimiters 
     * for separating tokens. Delimiter characters themselves will not 
     * be treated as tokens.
     *
     * @param   str     a string to be parsed.
     * @param   delim   the delimiters.
     */
    public StringTokenizer(String str, String delim) { }

    /** 
     * Constructs a string tokenizer for the specified string. The 
     * tokenizer uses the default delimiter set, which is 
     * <code>"&nbsp;&#92;t&#92;n&#92;r&#92;f"</code>: the space character, 
     * the tab character, the newline character, the carriage-return character,
     * and the form-feed character. Delimiter characters themselves will 
     * not be treated as tokens.
     *
     * @param   str   a string to be parsed.
     */
    public StringTokenizer(String str) { }

    /** 
     * Tests if there are more tokens available from this tokenizer's string. 
     * If this method returns <tt>true</tt>, then a subsequent call to 
     * <tt>nextToken</tt> with no argument will successfully return a token.
     *
     * @return  <code>true</code> if and only if there is at least one token 
     *          in the string after the current position; <code>false</code> 
     *          otherwise.
     */
    public boolean hasMoreTokens() {
        return false;
    }

    /** 
     * Returns the next token from this string tokenizer.
     *
     * @return     the next token from this string tokenizer.
     * @exception  NoSuchElementException  if there are no more tokens in this
     *               tokenizer's string.
     */
    public String nextToken() {
        return null;
    }

    /** 
     * Returns the next token in this string tokenizer's string. First, 
     * the set of characters considered to be delimiters by this 
     * <tt>StringTokenizer</tt> object is changed to be the characters in 
     * the string <tt>delim</tt>. Then the next token in the string
     * after the current position is returned. The current position is 
     * advanced beyond the recognized token.  The new delimiter set 
     * remains the default after this call. 
     *
     * @param      delim   the new delimiters.
     * @return     the next token, after switching to the new delimiter set.
     * @exception  NoSuchElementException  if there are no more tokens in this
     *               tokenizer's string.
     */
    public String nextToken(String delim) {
        return null;
    }

    /** 
     * Returns the same value as the <code>hasMoreTokens</code>
     * method. It exists so that this class can implement the
     * <code>Enumeration</code> interface. 
     *
     * @return  <code>true</code> if there are more tokens;
     *          <code>false</code> otherwise.
     * @see     java.util.Enumeration
     * @see     java.util.StringTokenizer#hasMoreTokens()
     */
    public boolean hasMoreElements() {
        return false;
    }

    /** 
     * Returns the same value as the <code>nextToken</code> method,
     * except that its declared return value is <code>Object</code> rather than
     * <code>String</code>. It exists so that this class can implement the
     * <code>Enumeration</code> interface. 
     *
     * @return     the next token in the string.
     * @exception  NoSuchElementException  if there are no more tokens in this
     *               tokenizer's string.
     * @see        java.util.Enumeration
     * @see        java.util.StringTokenizer#nextToken()
     */
    public Object nextElement() {
        return null;
    }

    /** 
     * Calculates the number of times that this tokenizer's 
     * <code>nextToken</code> method can be called before it generates an 
     * exception. The current position is not advanced.
     *
     * @return  the number of tokens remaining in the string using the current
     *          delimiter set.
     * @see     java.util.StringTokenizer#nextToken()
     */
    public int countTokens() {
        return 0;
    }
}
