/**
<p>This is not an official specification document, and usage is restricted.
</p>
<a name="notice"><strong><center>
NOTICE
</center></strong><br>
<br>

(c) 2005-2008 Sun Microsystems, Inc. All Rights Reserved.
<p>

Neither this file nor any files generated from it describe a complete
specification, and they may only be used as described below.  
<p>
Sun Microsystems Inc. owns the copyright in this file and it is provided
to you for informative use only. For example, 
this file and any files generated from it may be used to generate other documentation, 
such as a unified set of documents of API signatures for a platform 
that includes technologies expressed as Java APIs. 
This file may also be used to produce "compilation stubs," 
which allow applications to be compiled and validated for such platforms. 
By contrast, no permission is given for you to incorporate this file, 
in whole or in part, in an implementation of a Java specification.
<p>
Any work generated from this file, such as unified javadocs or compiled
stub files, must be accompanied by this notice in its entirety.
<p>
This work corresponds to the API signatures of JSR 927: Java TV API 1.1.1.  
In the event of a discrepency between this work and the JSR 927 specification, 
which is available at http://www.jcp.org/en/jsr/detail?id=927, the latter takes precedence.
*/



  


package javax.tv.service.selection;

import java.security.Permission;
import java.security.BasicPermission;
import javax.tv.locator.Locator;
import java.io.Serializable;

/** 
 * <code>ServiceContextPermission</code> represents permission to
 * control a <code>ServiceContext</code>.  A
 * <code>ServiceContextPermission</code> contains a name (also
 * referred to as a "target name") and an actions string.
 *
 * <p> The target name is the name of the service context permission
 * (see the table below).  Each permission identifies a method.  A
 * wildcard match is signified by an asterisk, i.e., "*".
 *
 * <p><a name="actions"></a> The actions string is either "own" or
 * "*".
 * A caller's "own" service contexts are those which it has created through
 * {@link ServiceContextFactory#createServiceContext}.  In addition, an
 * Xlet's "own" ServiceContext is the one in which it is currently running
 * (see {@link ServiceContextFactory#getServiceContext(XletContext)}).
 * The string "own" means
 * the permission applies to your own service contexts; the string "*"
 * implies permission to these, plus permission for service contexts
 * obtained from all other sources.
 *
 * <p> The following table lists all the possible
 * <code>ServiceContextPermission</code> target names, and describes
 * what the permission allows for each.  <p>
 *
 * <table border=1 cellpadding=5>
 * <tr>
 * <th>Permission Target Name</th>
 * <th>What the Permission Allows</th>
 * </tr>
 *
 * <tr>
 *    <td>access</td>
 *    <td>Access to a <code>ServiceContext</code>, via <code>ServiceContextFactory.getServiceContexts()</code></td>
 * </tr>
 * 
 * <tr>
 *    <td>create</td>
 *    <td>Creation of a <code>ServiceContext</code>.</td>
 * </tr>
 *
 * <tr>
 *    <td>destroy</td>
 *    <td>Destruction of a <code>ServiceContext</code>.</td>
 * </tr>
 *
 * <tr>
 *    <td>getServiceContentHandlers</td>
 *    <td>Obtaining the service content handlers from a <code>ServiceContext</code>.</td>
 * </tr>
 *
 * <tr>
 *    <td>stop</td>
 *    <td>Stopping a <code>ServiceContext</code>.</td>
 * </tr>
 *
 * </table>
 *
 * <p>
 * The permission <code>ServiceContextPermission("access", "*")</code>
 * is intended to be granted only to special monitoring applications and
 * not to general broadcast applications.
 * In order to properly safeguard service context access, an Xlet's
 * {@link javax.microedition.xlet.XletContext} instance should only be
 * accessible to another application if that other application has
 * <code>ServiceContextPermission("access", "*")</code>.
 * <p>
 * 
 * Note that undefined target and actions strings may be provided to
 * the constructors of this class, but subsequent calls to
 * <code>SecurityManager.checkPermission()</code> with the resulting
 * <code>SelectPermission</code> object will fail.
 *
 * @see java.security.BasicPermission
 * @see java.security.Permission
 * @see ServiceContext
 * @see ServiceContextFactory
 *
 * @version  1.25, 11/01/05
 * @author Bill Foote
 */
public final class ServiceContextPermission extends BasicPermission
{
    /** 
     * @serial the actions string
     */
    private String actions;

    /** 
     * Creates a new ServiceContextPermission object with the specified
     * name.  The name is the symbolic name of the permission, such as
     * "create".  An asterisk may be used to signify a wildcard match.
     *
     * @param name The name of the <code>ServiceContextPermission</code>
     * 
     * @param actions The actions string, <a href="#actions">as
     * detailed in the class description</a>.
     */
    public ServiceContextPermission(String name, String actions) { 
        super(name);
    }

    /** 
     * Checks if the specified permission is "implied" by this object. <p>
     *
     * More specifically, this method returns true if: <p>
     * <ul>
     * <li><i>p</i> is an instance of ServiceContextPermission, and
     * <li><i>p</i>'s action string matches this object's, or this object has
     *  "*" as an action string, and
     * <li><i>p</i>'s locator's external form matches this object's locator
     *   string, or this object's locator string is "*".
     * </ul>
     *
     * @param p The permission against which to test.
     *
     * @return <code>true</code> if the specified permission is equal
     * to or implied by this permission; <code>false</code> otherwise.
     * 
     */
    public boolean implies(Permission p) {
        return false;
    }

    /** 
     * Tests two <code>ServiceContextPermission</code> objects for
     * equality. Returns <code>true</code> if and only if
     * <code>obj</code>'s class is the same as the class of this
     * object, and <code>obj</code> has the same name and actions
     * string as this object.
     * 
     * @param obj The object to test for equality.
     *
     * @return <code>true</code> if the two permissions are equal;
     * <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        return false;
    }

    /** 
     * Provides the hash code value of this object.  Two
     * <code>ServiceContextPermission</code> objects that are equal will
     * return the same hash code.
     *
     * @return The hash code value of this object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the canonical representation of the actions string.
     *
     * @return The actions string of this permission.
     */
    public String getActions() {
        return null;
    }
}
