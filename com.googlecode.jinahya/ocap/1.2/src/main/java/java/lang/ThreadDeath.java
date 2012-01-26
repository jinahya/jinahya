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
 * An instance of <code>ThreadDeath</code> is thrown in the victim 
 * thread when the <code>stop</code> method with zero arguments in 
 * class <code>Thread</code> is called. 
 * <p>
 * An application should catch instances of this class only if it 
 * must clean up after being terminated asynchronously. If 
 * <code>ThreadDeath</code> is caught by a method, it is important 
 * that it be rethrown so that the thread actually dies. 
 * <p>
 * The top-level error handler does not print out a message if 
 * <code>ThreadDeath</code> is never caught. 
 * <p>
 * The class <code>ThreadDeath</code> is specifically a subclass of 
 * <code>Error</code> rather than <code>Exception</code>, even though 
 * it is a "normal occurrence", because many applications 
 * catch all occurrences of <code>Exception</code> and then discard 
 * the exception. 
 *
 * @author unascribed
 * @version 1.13, 05/03/00
 * @since   JDK1.0
 */
public class ThreadDeath extends java.lang.Error
{

    public ThreadDeath() { }
}
