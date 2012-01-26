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
 * Signals that a resource is missing.
 * @see java.lang.Exception
 * @see ResourceBundle
 * @version     1.12, 01/19/00
 * @author      Mark Davis
 * @since       JDK1.1
 */
public class MissingResourceException extends RuntimeException
{
    /** 
     * The class name of the resource bundle requested by the user.
     * @serial
     */
    private String className;

    /** 
     * The name of the specific resource requested by the user.
     * @serial
     */
    private String key;

    /** 
     * Constructs a MissingResourceException with the specified information.
     * A detail message is a String that describes this particular exception.
     * @param s the detail message
     * @param className the name of the resource class
     * @param key the key for the missing resource.
     */
    public MissingResourceException(String s, String className, String key) { }

    /** 
     * Gets parameter passed by constructor.
     *
     * @return the name of the resource class
     */
    public String getClassName() {
        return null;
    }

    /** 
     * Gets parameter passed by constructor.
     *
     * @return the key for the missing resource
     */
    public String getKey() {
        return null;
    }
}
