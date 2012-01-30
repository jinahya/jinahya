/*
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
This work corresponds to the API signatures of JSR 217: Personal Basis
Profile 1.1. In the event of a discrepency between this work and the
JSR 217 specification, which is available at
http://www.jcp.org/en/jsr/detail?id=217, the latter takes precedence.
*/


  


package java.rmi;

// PBP/PP
/** 
 * An <code>AccessException</code> is thrown  to
 * indicate that the caller does not have permission to perform the action
 * requested by the method call.  If the method was invoked from a non-local
 * host, then an <code>AccessException</code> is thrown.
 * 
 * @version 1.11, 01/23/03
 * @author  Ann Wollrath
 * @author  Roger Riggs
 * @since   JDK1.1
 * @see     
 * @see    
 */
public class AccessException extends RemoteException
{

    /* indicate compatibility with JDK 1.1.x version of class */
     private static final long serialVersionUID = 6314925228044966088L;

    /** 
     * Constructs an <code>AccessException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     * @since JDK1.1
     */
    public AccessException(String s) { }

    /** 
     * Constructs an <code>AccessException</code> with the specified
     * detail message and nested exception.
     *
     * @param s the detail message
     * @param ex the nested exception
     * @since JDK1.1
     */
    public AccessException(String s, Exception ex) { }
}
