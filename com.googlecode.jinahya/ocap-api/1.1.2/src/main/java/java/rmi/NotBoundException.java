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
 * A <code>NotBoundException</code> is thrown if an attempt
 * is made to lookup or unbind in the registry a name that has
 * no associated binding.
 * 
 * @version 1.11, 01/23/03
 * @since   JDK1.1
 * @author  Ann Wollrath
 * @author  Roger Riggs
 * @see     
 * @see     
 * @see     java.rmi.registry.Registry#lookup(String)
 * @see     java.rmi.registry.Registry#unbind(String) 
 */
public class NotBoundException extends Exception
{

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = -1857741824849069317L;

    /** 
     * Constructs a <code>NotBoundException</code> with no
     * specified detail message.
     * @since JDK1.1
     */
    public NotBoundException() { }

    /** 
     * Constructs a <code>NotBoundException</code> with the specified
     * detail message.
     *
     * @param s the detail message
     * @since JDK1.1
     */
    public NotBoundException(String s) { }
}
