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



  


package javax.tv.service;

import java.security.Permission;
import javax.tv.locator.Locator;
import java.io.Serializable;

/** 
 * This class represents permission to read the data referenced by a given
 * <code>Locator</code>.
 *
 * @version  1.8, 10/09/00
 * @author      Bill Foote
 */
public final class ReadPermission extends Permission implements Serializable
{

    /** 
     * Creates a new ReadPermission object for the specified locator.
     *
     * @param locator The locator.  Null indicates permission for all locators.
     */
    public ReadPermission(Locator locator) { 
        super(locator.toExternalForm());
    }

    /** 
     * Creates a new <code>ReadPermission</code> object for a locator
     * with the given external form.  The <code>actions</code> string
     * is currently unused and should be <code>null</code>.  This
     * constructor exists for use by the <code>Policy</code> object to
     * instantiate new <code>Permission</code> objects.
     *
     * @param locator The external form of the locator.  The string
     * "*" indicates all locators.
     * 
     * @param actions Should be <code>null</code>. 
     */
    public ReadPermission(String locator, String actions) { 
        super(null);    
    }

    /** 
     * Checks if this ReadPermission object "implies" the specified
     * permission. <p>
     *
     * More specifically, this method returns true if: <p>
     * <ul>
     * <li><i>p</i> is an instance of ReadPermission, and
     * <li><i>p</i>'s locator's external form matches this object's locator
     *   string, or this object's locator string is "*".
     * </ul>
     *
     * @param p The permission to check against.
     *
     * @return <code>true</code> if the specified permission is
     * implied by this object, <code>false</code> if not.
     */
    public boolean implies(Permission p) {
        return false;
    }

    /** 
     * Checks two ReadPermission objects for equality.  Checks that
     * <i>other</i> is a ReadPermission, and has the same locator
     * as this object.
     *
     * @param other the object we are testing for equality with this
     * object.
     *
     * @return <code>true</code> if <code>other</code> is of
     * type <code>ReadPermission</code> and has the same locator as
     * this <code>ReadPermission</code> object.  
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
     * Returns the canonical string representation of the actions,
     * which currently is the empty string "", since there are no actions for
     * a ReadPermission.
     *
     * @return the empty string "".
     */
    public String getActions() {
        return null;
    }
}
