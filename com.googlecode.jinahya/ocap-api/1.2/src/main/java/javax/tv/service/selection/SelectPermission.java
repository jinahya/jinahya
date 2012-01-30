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
import javax.tv.locator.Locator;
import java.io.Serializable;

/** 
 * <code>SelectPermission</code> represents permission to perform a
 * <code>select()</code> operation on a <code>ServiceContext</code>.
 * A caller might have permission to select some content but not
 * others.
 *
 * <p>
 * <a name="actions"></a>
 * The <code>actions</code> string is either "own" or "*".  The
 * string "own" means the permission applies to your own service
 * context, acquired via
 * <code>ServiceContextFactory.createServiceContext()</code> or
 * <code>ServiceContextFactory.getServiceContext(javax.tv.xlet.XletContext)</code>.
 * The string "*" implies permission to these, plus permission for service
 * contexts obtained from all other sources.<p>
 *
 * Note that undefined actions strings may be provided to the
 * constructors of this class, but subsequent calls to
 * <code>SecurityManager.checkPermission()</code> with the resulting
 * <code>SelectPermission</code> object will fail.
 *
 * @version  1.20, 10/09/00
 * @author Bill Foote
 */
public final class SelectPermission extends Permission implements Serializable
{
    /** 
     * @serial the actions string
     */
    private String actions;

    /** 
     * Creates a new SelectPermission object for the specified locator.
     *
     * @param locator The locator.  A value of <code>null</code>
     * indicates permission for all locators.
     * 
     * @param actions The actions string, <a href="#actions">as
     * detailed in the class description</a>.
     */
    public SelectPermission(Locator locator, String actions) { 
        super(locator.toExternalForm());    
    }

    /** 
     * Creates a new SelectPermission object for a locator with the
     * given external form.  This constructor exists for use by the
     * <code>Policy</code> object to instantiate new Permission objects.
     *
     * @param locator The external form of the locator.  The string
     * "*" indicates all locators.  
     * 
     *@param actions The actions string, <a href="#actions">as
     * detailed in the class description</a>.  
     */
    public SelectPermission(String locator, String actions) { 
        super(locator);
    }

    /** 
     * Checks if this SelectPermission object "implies" the specified
     * permission.  More specifically, this method returns true if:
     * <ul>
     * <li><i>p</i> is an instance of SelectPermission, and
     * <li><i>p</i>'s action string matches this object's, or this object has
     *	"*" as an action string, and
     * <li><i>p</i>'s locator's external form matches this object's locator
     *   string, or this object's locator string is "*".
     * </ul>
     *
     * @param p The permission against which to check.
     *
     * @return <code>true</code> if the specified permission is
     * implied by this object, <code>false</code> if not.
     */
    public boolean implies(Permission p) {
        return false;
    }

    /** 
     * Checks two SelectPermission objects for equality.  Tests that
     * the given object is a <code>SelectPermission</code> and has the
     * same <code>Locator</code> and actions string as this
     * object.
     *
     * @param other The object to test for equality.
     *
     * @return <code>true</code> if other is a
     * <code>SelectPermission</code> and has the same locator and
     * actions string as this
     * <code>SelectPermission</code> object; <code>false</code> otherwise.
     */
    public boolean equals(Object other) {
        return false;
    }

    /** 
     * Returns the hash code value for this object.
     *
     * @return A hash code value for this object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Returns the canonical string representation of the actions.
     *
     * @return The canonical string representation of the actions.
     */
    public String getActions() {
        return null;
    }
}
