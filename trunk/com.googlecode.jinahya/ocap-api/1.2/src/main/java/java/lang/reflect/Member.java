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


  


package java.lang.reflect;

/** 
 * Member is an interface that reflects identifying information about
 * a single member (a field or a method) or a constructor.
 *
 * @see	java.lang.Class
 * @see	Field
 * @see	Method
 * @see	Constructor
 *
 * @author Nakul Saraiya
 */
public interface Member
{
    /** 
     * Identifies the set of all public members of a class or interface,
     * including inherited members.
     * @see java.lang.SecurityManager#checkMemberAccess
     */
    public static final int PUBLIC = 0;

    /** 
     * Identifies the set of declared members of a class or interface.
     * Inherited members are not included.
     * @see java.lang.SecurityManager#checkMemberAccess
     */
    public static final int DECLARED = 1;

    /** 
     * Returns the Class object representing the class or interface
     * that declares the member or constructor represented by this Member.
     *
     * @return an object representing the declaring class of the
     * underlying member
     */
    public Class getDeclaringClass();

    /** 
     * Returns the simple name of the underlying member or constructor
     * represented by this Member.
     * 
     * @return the simple name of the underlying member
     */
    public String getName();

    /** 
     * Returns the Java language modifiers for the member or
     * constructor represented by this Member, as an integer.  The
     * Modifier class should be used to decode the modifiers in
     * the integer.
     * 
     * @return the Java language modifiers for the underlying member
     * @see Modifier
     */
    public int getModifiers();
}
