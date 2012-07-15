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


  


package java.net;

import java.io.*;

/** 
 * Utility class for HTML form decoding. This class contains static methods
 * for decoding a String from the <CODE>application/x-www-form-urlencoded</CODE>
 * MIME format.
 * <p>
 * To conversion process is the reverse of that used by the URLEncoder class. It is assumed
 * that all characters in the encoded string are one of the following: 
 * &quot;<code>a</code>&quot; through &quot;<code>z</code>&quot;,
 * &quot;<code>A</code>&quot; through &quot;<code>Z</code>&quot;,
 * &quot;<code>0</code>&quot; through &quot;<code>9</code>&quot;, and
 * &quot;<code>-</code>&quot;, &quot;<code>_</code>&quot;,
 * &quot;<code>.</code>&quot;, and &quot;<code>*</code>&quot;. The
 * character &quot;<code>%</code>&quot; is allowed but is interpreted
 * as the start of a special escaped sequence.
 * <p>
 * The following rules are applied in the conversion:
 * <p>
 * <ul>
 * <li>The alphanumeric characters &quot;<code>a</code>&quot; through
 *     &quot;<code>z</code>&quot;, &quot;<code>A</code>&quot; through
 *     &quot;<code>Z</code>&quot; and &quot;<code>0</code>&quot; 
 *     through &quot;<code>9</code>&quot; remain the same.
 * <li>The special characters &quot;<code>.</code>&quot;,
 *     &quot;<code>-</code>&quot;, &quot;<code>*</code>&quot;, and
 *     &quot;<code>_</code>&quot; remain the same. 
 * <li>The plus sign &quot;<code>+</code>&quot; is converted into a
 *     space character &quot;<code>&nbsp;</code>&quot; .
 * <li>A sequence of the form "<code>%<i>xy</i></code>" will be
 *     treated as representing a byte where <i>xy</i> is the two-digit
 *     hexadecimal representation of the 8 bits. Then, all substrings
 *     that contain one or more of these byte sequences consecutively
 *     will be replaced by the character(s) whose encoding would result
 *     in those consecutive bytes. 
 *     The encoding scheme used to decode these characters may be specified, 
 *     or if unspecified, the default encoding of the platform will be used.
 * </ul>
 * <p>
 * There are two possible ways in which this decoder could deal with
 * illegal strings.  It could either leave illegal characters alone or
 * it could throw an <tt>{@link java.lang.IllegalArgumentException}</tt>.
 * Which approach the decoder takes is left to the
 * implementation.
 *
 * @author  Mark Chamness
 * @author  Michael McCloskey
 * @version 1.9, 02/02/00
 * @since   1.2
 */
public class URLDecoder
{

    public URLDecoder() { }

    /** 
     * Decodes a <code>x-www-form-urlencoded</code> string.
     * The platform's default encoding is used to determine what characters 
     * are represented by any consecutive sequences of the form 
     * "<code>%<i>xy</i></code>".
     * @param s the <code>String</code> to decode
     * @deprecated The resulting string may vary depending on the platform's
     *          default encoding. Instead, use the decode(String,String) method
     *          to specify the encoding.
     * @return the newly decoded <code>String</code>
     */
    public static String decode(String s) {
        return null;
    }

    /** 
     * Decodes a <code>application/x-www-form-urlencoded</code> string using a specific 
     * encoding scheme.
     * The supplied encoding is used to determine
     * what characters are represented by any consecutive sequences of the
     * form "<code>%<i>xy</i></code>".
     * <p>
     * <em><strong>Note:</strong> The <a href=
     * "http://www.w3.org/TR/html40/appendix/notes.html#non-ascii-chars">
     * World Wide Web Consortium Recommendation</a> states that
     * UTF-8 should be used. Not doing so may introduce
     * incompatibilites.</em>
     *
     * @param s the <code>String</code> to decode
     * @param enc   The name of a supported 
     *    <a href="../lang/package-summary.html#charenc">character
     *    encoding</a>. 
     * @return the newly decoded <code>String</code>
     * @exception  UnsupportedEncodingException
     *             If the named encoding is not supported
     * @see URLEncoder#encode(java.lang.String, java.lang.String)
     * @since 1.4
     */
    public static String decode(String s, String enc)
        throws UnsupportedEncodingException
    {
        return null;
    }
}
