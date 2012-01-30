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
 * Thrown to indicate that a thread has attempted to wait on an 
 * object's monitor or to notify other threads waiting on an object's
 * monitor without owning the specified monitor. 
 *
 * @author  unascribed
 * @version 1.9, 02/02/00
 * @see     java.lang.Object#notify()
 * @see     java.lang.Object#notifyAll()
 * @see     java.lang.Object#wait() 
 * @see     java.lang.Object#wait(long) 
 * @see     java.lang.Object#wait(long, int) 
 * @since   JDK1.0
 */
public class IllegalMonitorStateException extends java.lang.RuntimeException
{

    /** 
     * Constructs an <code>IllegalMonitorStateException</code> with no 
     * detail message. 
     */
    public IllegalMonitorStateException() { }

    /** 
     * Constructs an <code>IllegalMonitorStateException</code> with the 
     * specified detail message. 
     *
     * @param   s   the detail message.
     */
    public IllegalMonitorStateException(java.lang.String s) { }
}
