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
 * An <code>AlreadyBoundException</code> is thrown if an attempt
 * is made to bind an object in the registry to a name that already
 * has an associated binding.
 * 
 * @version 1.11, 01/23/03
 * @since   JDK1.1
 * @author  Ann Wollrath
 * @author  Roger Riggs
 * @see     
 * @see     java.rmi.registry.Registry#bind(String, java.rmi.Remote)
 */
public class AlreadyBoundException extends Exception
{

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = 9218657361741657110L;

    /** 
     * Constructs an <code>AlreadyBoundException</code> with no
     * specified detail message.
     * @since JDK1.1
     */
    public AlreadyBoundException() { }

    /** 
     * Constructs an <code>AlreadyBoundException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     * @since JDK1.1
     */
    public AlreadyBoundException(String s) { }
}
