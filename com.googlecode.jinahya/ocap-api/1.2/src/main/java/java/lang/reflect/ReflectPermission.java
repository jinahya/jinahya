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
 * The Permission class for reflective operations.  A
 * ReflectPermission is a <em>named permission</em> and has no
 * actions.  The only name currently defined is <tt>suppressAccessChecks</tt>,
 * which allows suppressing the standard Java language access checks
 * -- for public, default (package) access, protected, and private
 * members -- performed by reflected objects at their point of use.
 * <P>
 * The following table
 * provides a summary description of what the permission allows,
 * and discusses the risks of granting code the permission.
 * <P>
 *
 * <table border=1 cellpadding=5 summary="Table shows permission target name, what the permission allows, and associated risks">
 * <tr>
 * <th>Permission Target Name</th>
 * <th>What the Permission Allows</th>
 * <th>Risks of Allowing this Permission</th>
 * </tr>
 *
 * <tr>
 *   <td>suppressAccessChecks</td>
 *   <td>ability to access
 * fields and invoke methods in a class. Note that this includes
 * not only public, but protected and private fields and methods as well.</td>
 *   <td>This is dangerous in that information (possibly confidential) and
 * methods normally unavailable would be accessible to malicious code.</td>
 * </tr>
 *
 * </table>
 *
 * @see java.security.Permission
 * @see java.security.BasicPermission
 * @see AccessibleObject
 * @see Field#get
 * @see Field#set
 * @see Method#invoke
 * @see Constructor#newInstance
 *
 * @since 1.2
 */
public final class ReflectPermission extends java.security.BasicPermission
{

    /** 
     * Constructs a ReflectPermission with the specified name.
     *
     * @param name the name of the ReflectPermission
     */
    public ReflectPermission(String name) { 
        super(name);
    }

    /** 
     * Constructs a ReflectPermission with the specified name and actions.
     * The actions should be null; they are ignored.
     *
     * @param name the name of the ReflectPermission
     * @param actions should be null.
     */
    public ReflectPermission(String name, String actions) { 
        super(name, actions);    
    }
}
