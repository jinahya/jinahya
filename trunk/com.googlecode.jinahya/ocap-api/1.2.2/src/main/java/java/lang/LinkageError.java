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


  


package java.lang;

/** 
 * Subclasses of <code>LinkageError</code> indicate that a class has 
 * some dependency on another class; however, the latter class has 
 * incompatibly changed after the compilation of the former class. 
 *
 *
 * @author  Frank Yellin
 * @version 1.10, 02/02/00
 * @since   JDK1.0
 */
public class LinkageError extends java.lang.Error
{

    /** 
     * Constructs a <code>LinkageError</code> with no detail message. 
     */
    public LinkageError() { }

    /** 
     * Constructs a <code>LinkageError</code> with the specified detail 
     * message. 
     *
     * @param   s   the detail message.
     */
    public LinkageError(java.lang.String s) { }
}
