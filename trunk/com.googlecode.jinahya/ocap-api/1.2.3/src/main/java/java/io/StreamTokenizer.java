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


  


package java.io;

/** 
 * The <code>StreamTokenizer</code> class takes an input stream and
 * parses it into "tokens", allowing the tokens to be
 * read one at a time. The parsing process is controlled by a table
 * and a number of flags that can be set to various states. The
 * stream tokenizer can recognize identifiers, numbers, quoted
 * strings, and various comment styles.
 * <p>
 * Each byte read from the input stream is regarded as a character
 * in the range <code>'&#92;u0000'</code> through <code>'&#92;u00FF'</code>.
 * The character value is used to look up five possible attributes of
 * the character: <i>white space</i>, <i>alphabetic</i>,
 * <i>numeric</i>, <i>string quote</i>, and <i>comment character</i>.
 * Each character can have zero or more of these attributes.
 * <p>
 * In addition, an instance has four flags. These flags indicate:
 * <ul>
 * <li>Whether line terminators are to be returned as tokens or treated
 *     as white space that merely separates tokens.
 * <li>Whether C-style comments are to be recognized and skipped.
 * <li>Whether C++-style comments are to be recognized and skipped.
 * <li>Whether the characters of identifiers are converted to lowercase.
 * </ul>
 * <p>
 * A typical application first constructs an instance of this class,
 * sets up the syntax tables, and then repeatedly loops calling the
 * <code>nextToken</code> method in each iteration of the loop until
 * it returns the value <code>TT_EOF</code>.
 *
 * @author  James Gosling
 * @version 1.37, 05/03/00
 * @see     java.io.StreamTokenizer#nextToken()
 * @see     java.io.StreamTokenizer#TT_EOF
 * @since   JDK1.0
 */
public class StreamTokenizer
{
    /** 
     * After a call to the <code>nextToken</code> method, this field
     * contains the type of the token just read. For a single character
     * token, its value is the single character, converted to an integer.
     * For a quoted string token (see , its value is the quote character.
     * Otherwise, its value is one of the following:
     * <ul>
     * <li><code>TT_WORD</code> indicates that the token is a word.
     * <li><code>TT_NUMBER</code> indicates that the token is a number.
     * <li><code>TT_EOL</code> indicates that the end of line has been read.
     *     The field can only have this value if the
     *     <code>eolIsSignificant</code> method has been called with the
     *     argument <code>true</code>.
     * <li><code>TT_EOF</code> indicates that the end of the input stream
     *     has been reached.
     * </ul>
     * <p>
     * The initial value of this field is -4.
     *
     * @see     java.io.StreamTokenizer#eolIsSignificant(boolean)
     * @see     java.io.StreamTokenizer#nextToken()
     * @see     java.io.StreamTokenizer#quoteChar(int)
     * @see     java.io.StreamTokenizer#TT_EOF
     * @see     java.io.StreamTokenizer#TT_EOL
     * @see     java.io.StreamTokenizer#TT_NUMBER
     * @see     java.io.StreamTokenizer#TT_WORD
     */
    public int ttype;

    /** 
     * A constant indicating that the end of the stream has been read.
     */
    public static final int TT_EOF = -1;

    /** 
     * A constant indicating that the end of the line has been read.
     */
    public static final int TT_EOL = 10;

    /** 
     * A constant indicating that a number token has been read.
     */
    public static final int TT_NUMBER = -2;

    /** 
     * A constant indicating that a word token has been read.
     */
    public static final int TT_WORD = -3;

    /** 
     * If the current token is a word token, this field contains a
     * string giving the characters of the word token. When the current
     * token is a quoted string token, this field contains the body of
     * the string.
     * <p>
     * The current token is a word when the value of the
     * <code>ttype</code> field is <code>TT_WORD</code>. The current token is
     * a quoted string token when the value of the <code>ttype</code> field is
     * a quote character.
     * <p>
     * The initial value of this field is null.
     *
     * @see     java.io.StreamTokenizer#quoteChar(int)
     * @see     java.io.StreamTokenizer#TT_WORD
     * @see     java.io.StreamTokenizer#ttype
     */
    public String sval;

    /** 
     * If the current token is a number, this field contains the value
     * of that number. The current token is a number when the value of
     * the <code>ttype</code> field is <code>TT_NUMBER</code>.
     * <p>
     * The initial value of this field is 0.0.
     *
     * @see     java.io.StreamTokenizer#TT_NUMBER
     * @see     java.io.StreamTokenizer#ttype
     */
    public double nval;

    /** 
     * Create a tokenizer that parses the given character stream.
     *
     * @param r  a Reader object providing the input stream.
     * @since   JDK1.1
     */
    public StreamTokenizer(Reader r) { }

    /** 
     * Resets this tokenizer's syntax table so that all characters are
     * "ordinary." See the <code>ordinaryChar</code> method
     * for more information on a character being ordinary.
     *
     * @see     java.io.StreamTokenizer#ordinaryChar(int)
     */
    public void resetSyntax() { }

    /** 
     * Specifies that all characters <i>c</i> in the range
     * <code>low&nbsp;&lt;=&nbsp;<i>c</i>&nbsp;&lt;=&nbsp;high</code>
     * are word constituents. A word token consists of a word constituent
     * followed by zero or more word constituents or number constituents.
     *
     * @param   low   the low end of the range.
     * @param   hi    the high end of the range.
     */
    public void wordChars(int low, int hi) { }

    /** 
     * Specifies that all characters <i>c</i> in the range
     * <code>low&nbsp;&lt;=&nbsp;<i>c</i>&nbsp;&lt;=&nbsp;high</code>
     * are white space characters. White space characters serve only to
     * separate tokens in the input stream.
     *
     * <p>Any other attribute settings for the characters in the specified
     * range are cleared.
     *
     * @param   low   the low end of the range.
     * @param   hi    the high end of the range.
     */
    public void whitespaceChars(int low, int hi) { }

    /** 
     * Specifies that all characters <i>c</i> in the range
     * <code>low&nbsp;&lt;=&nbsp;<i>c</i>&nbsp;&lt;=&nbsp;high</code>
     * are "ordinary" in this tokenizer. See the
     * <code>ordinaryChar</code> method for more information on a
     * character being ordinary.
     *
     * @param   low   the low end of the range.
     * @param   hi    the high end of the range.
     * @see     java.io.StreamTokenizer#ordinaryChar(int)
     */
    public void ordinaryChars(int low, int hi) { }

    /** 
     * Specifies that the character argument is "ordinary"
     * in this tokenizer. It removes any special significance the
     * character has as a comment character, word component, string
     * delimiter, white space, or number character. When such a character
     * is encountered by the parser, the parser treates it as a
     * single-character token and sets <code>ttype</code> field to the
     * character value.
     *
     * @param   ch   the character.
     * @see     java.io.StreamTokenizer#ttype
     */
    public void ordinaryChar(int ch) { }

    /** 
     * Specified that the character argument starts a single-line
     * comment. All characters from the comment character to the end of
     * the line are ignored by this stream tokenizer.
     *
     * <p>Any other attribute settings for the specified character are cleared.
     *
     * @param   ch   the character.
     */
    public void commentChar(int ch) { }

    /** 
     * Specifies that matching pairs of this character delimit string
     * constants in this tokenizer.
     * <p>
     * When the <code>nextToken</code> method encounters a string
     * constant, the <code>ttype</code> field is set to the string
     * delimiter and the <code>sval</code> field is set to the body of
     * the string.
     * <p>
     * If a string quote character is encountered, then a string is
     * recognized, consisting of all characters after (but not including)
     * the string quote character, up to (but not including) the next
     * occurrence of that same string quote character, or a line
     * terminator, or end of file. The usual escape sequences such as
     * <code>"&#92;n"</code> and <code>"&#92;t"</code> are recognized and
     * converted to single characters as the string is parsed.
     *
     * <p>Any other attribute settings for the specified character are cleared.
     *
     * @param   ch   the character.
     * @see     java.io.StreamTokenizer#nextToken()
     * @see     java.io.StreamTokenizer#sval
     * @see     java.io.StreamTokenizer#ttype
     */
    public void quoteChar(int ch) { }

    /** 
     * Specifies that numbers should be parsed by this tokenizer. The
     * syntax table of this tokenizer is modified so that each of the twelve
     * characters:
     * <blockquote><pre>
     *      0 1 2 3 4 5 6 7 8 9 . -
     * </pre></blockquote>
     * <p>
     * has the "numeric" attribute.
     * <p>
     * When the parser encounters a word token that has the format of a
     * double precision floating-point number, it treats the token as a
     * number rather than a word, by setting the the <code>ttype</code>
     * field to the value <code>TT_NUMBER</code> and putting the numeric
     * value of the token into the <code>nval</code> field.
     *
     * @see     java.io.StreamTokenizer#nval
     * @see     java.io.StreamTokenizer#TT_NUMBER
     * @see     java.io.StreamTokenizer#ttype
     */
    public void parseNumbers() { }

    /** 
     * Determines whether or not ends of line are treated as tokens.
     * If the flag argument is true, this tokenizer treats end of lines
     * as tokens; the <code>nextToken</code> method returns
     * <code>TT_EOL</code> and also sets the <code>ttype</code> field to
     * this value when an end of line is read.
     * <p>
     * A line is a sequence of characters ending with either a
     * carriage-return character (<code>'&#92;r'</code>) or a newline
     * character (<code>'&#92;n'</code>). In addition, a carriage-return
     * character followed immediately by a newline character is treated
     * as a single end-of-line token.
     * <p>
     * If the <code>flag</code> is false, end-of-line characters are
     * treated as white space and serve only to separate tokens.
     *
     * @param   flag   <code>true</code> indicates that end-of-line characters
     *                 are separate tokens; <code>false</code> indicates that
     *                 end-of-line characters are white space.
     * @see     java.io.StreamTokenizer#nextToken()
     * @see     java.io.StreamTokenizer#ttype
     * @see     java.io.StreamTokenizer#TT_EOL
     */
    public void eolIsSignificant(boolean flag) { }

    /** 
     * Determines whether or not the tokenizer recognizes C-style comments.
     * If the flag argument is <code>true</code>, this stream tokenizer
     * recognizes C-style comments. All text between successive
     * occurrences of <code>/*</code> and <code>*&#47;</code> are discarded.
     * <p>
     * If the flag argument is <code>false</code>, then C-style comments
     * are not treated specially.
     *
     * @param   flag   <code>true</code> indicates to recognize and ignore
     *                 C-style comments.
     */
    public void slashStarComments(boolean flag) { }

    /** 
     * Determines whether or not the tokenizer recognizes C++-style comments.
     * If the flag argument is <code>true</code>, this stream tokenizer
     * recognizes C++-style comments. Any occurrence of two consecutive
     * slash characters (<code>'/'</code>) is treated as the beginning of
     * a comment that extends to the end of the line.
     * <p>
     * If the flag argument is <code>false</code>, then C++-style
     * comments are not treated specially.
     *
     * @param   flag   <code>true</code> indicates to recognize and ignore
     *                 C++-style comments.
     */
    public void slashSlashComments(boolean flag) { }

    /** 
     * Determines whether or not word token are automatically lowercased.
     * If the flag argument is <code>true</code>, then the value in the
     * <code>sval</code> field is lowercased whenever a word token is
     * returned (the <code>ttype</code> field has the
     * value <code>TT_WORD</code> by the <code>nextToken</code> method
     * of this tokenizer.
     * <p>
     * If the flag argument is <code>false</code>, then the
     * <code>sval</code> field is not modified.
     *
     * @param   fl   <code>true</code> indicates that all word tokens should
     *               be lowercased.
     * @see     java.io.StreamTokenizer#nextToken()
     * @see     java.io.StreamTokenizer#ttype
     * @see     java.io.StreamTokenizer#TT_WORD
     */
    public void lowerCaseMode(boolean fl) { }

    /** 
     * Parses the next token from the input stream of this tokenizer.
     * The type of the next token is returned in the <code>ttype</code>
     * field. Additional information about the token may be in the
     * <code>nval</code> field or the <code>sval</code> field of this
     * tokenizer.
     * <p>
     * Typical clients of this
     * class first set up the syntax tables and then sit in a loop
     * calling nextToken to parse successive tokens until TT_EOF
     * is returned.
     *
     * @return     the value of the <code>ttype</code> field.
     * @exception  IOException  if an I/O error occurs.
     * @see        java.io.StreamTokenizer#nval
     * @see        java.io.StreamTokenizer#sval
     * @see        java.io.StreamTokenizer#ttype
     */
    public int nextToken() throws IOException {
        return 0;
    }

    /** 
     * Causes the next call to the <code>nextToken</code> method of this
     * tokenizer to return the current value in the <code>ttype</code>
     * field, and not to modify the value in the <code>nval</code> or
     * <code>sval</code> field.
     *
     * @see     java.io.StreamTokenizer#nextToken()
     * @see     java.io.StreamTokenizer#nval
     * @see     java.io.StreamTokenizer#sval
     * @see     java.io.StreamTokenizer#ttype
     */
    public void pushBack() { }

    /** 
     * Return the current line number.
     *
     * @return  the current line number of this stream tokenizer.
     */
    public int lineno() {
        return 0;
    }

    /** 
     * Returns the string representation of the current stream token and
     * the line number it occurs on.
     *
     * <p>The precise string returned is unspecified, although the following
     * example can be considered typical:
     *
     * <blockquote><pre>Token['a'], line 10</pre></blockquote>
     *
     * @return  a string representation of the token
     * @see     java.io.StreamTokenizer#nval
     * @see     java.io.StreamTokenizer#sval
     * @see     java.io.StreamTokenizer#ttype
     */
    public String toString() {
        return null;
    }
}
