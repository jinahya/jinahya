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

/** 
 * Checked exception thrown to indicate that a string could not be parsed as a
 * URI reference.
 *
 * @author Mark Reinhold
 * @version 1.5, 05/03/12
 * @see URI
 */
public class URISyntaxException extends Exception
{
    private String input;

    private int index;

    /** 
     * Constructs an instance from the given input string, reason, and error
     * index.
     *
     * @param  input   The input string
     * @param  reason  A string explaining why the input could not be parsed
     * @param  index   The index at which the parse error occurred,
     *                 or <tt>-1</tt> if the index is not known
     *
     * @throws  NullPointerException
     *          If either the input or reason strings are <tt>null</tt>
     *
     * @throws  IllegalArgumentException
     *          If the error index is less than <tt>-1</tt>
     */
    public URISyntaxException(String input, String reason, int index) { }

    /** 
     * Constructs an instance from the given input string and reason.  The
     * resulting object will have an error index of <tt>-1</tt>.
     *
     * @param  input   The input string
     * @param  reason  A string explaining why the input could not be parsed
     *
     * @throws  NullPointerException
     *          If either the input or reason strings are <tt>null</tt>
     */
    public URISyntaxException(String input, String reason) { }

    /** 
     * Returns the input string.
     *
     * @return  The input string
     */
    public String getInput() {
        return null;
    }

    /** 
     * Returns a string explaining why the input string could not be parsed.
     *
     * @return  The reason string
     */
    public String getReason() {
        return null;
    }

    /** 
     * Returns an index into the input string of the position at which the
     * parse error occurred, or <tt>-1</tt> if this position is not known.
     *
     * @return  The error index
     */
    public int getIndex() {
        return 0;
    }

    /** 
     * Returns a string describing the parse error.  The resulting string
     * consists of the reason string followed by a colon character
     * (<tt>':'</tt>), a space, and the input string.  If the error index is
     * defined then the string <tt>" at index "</tt> followed by the index, in
     * decimal, is inserted after the reason string and before the colon
     * character.
     *
     * @return  A string describing the parse error
     */
    public String getMessage() {
        return null;
    }
}
