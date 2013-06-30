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



  


package javax.tv.media;

import java.security.Permission;
import javax.tv.locator.Locator;
import java.io.Serializable;

/** 
 * This class represents permission to select, via a
 * <code>MediaSelectControl</code>, the content that a JMF Player
 * presents.  A caller might have permission to select content
 * referenced by some locators, but not others.
 *
 * @version  1.12, 10/09/00
 * @author Bill Foote
 */
public final class MediaSelectPermission extends Permission
    implements Serializable
{

    /** 
     * Creates a new <code>MediaSelectPermission</code> object for the
     * specified <code>Locator</code>.
     *
     * @param locator The locator for which to create the permission.
     * A value of <code>null</code> indicates permission for all
     * locators.  
     */
    public MediaSelectPermission(Locator locator) { 
        super(locator.toExternalForm());
    }

    /** 
     * Creates a new <code>MediaSelectPermission</code> object for a
     * <code>Locator</code> with the given external form.  The actions
     * string is currently unused and should be <code>null</code>.
     * This constructor is used by the <code>Policy</code> class to
     * instantiate new <code>Permission</code> objects.
     *
     * @param locator The external form of the locator.  The string
     * "*" indicates all locators.  
     *
     * @param actions Should be <code>null</code>.
     */
    public MediaSelectPermission(String locator, String actions) { 
        super(locator);
    }

    /** 
     * Checks if this <code>MediaSelectPermission</code> "implies" the
     * specified <code>Permission</code>. <p>
     *
     * More specifically, this method returns true if: <p>
     * <ul>
     * <li><i>p</i> is an instance of MediaSelectPermission, and
     * <li><i>p</i>'s locator's external form matches this object's locator
     *   string, or this object's locator string is "*".
     * </ul>
     *
     * @param p The <code>Permission</code> to check against.
     *
     * @return <code>true</code> if the specified
     * <code>Permission</code> is implied by this object;
     * <code>false</code> otherwise.
     */
    public boolean implies(Permission p) {
        return false;
    }

    /** 
     * Tests two MediaSelectPermission objects for equality.  This
     * method tests that <code>other</code> is of type
     * <code>MediaSelectPermission</code>, and has the same
     * <code>Locator</code> as this object.
     *
     * @param other The object to test for equality.
     *
     * @return <code>true</code> if <code>other</code> is a
     * <code>MediaSelectPermission</code>, and has the same
     * <code>Locator</code> as this <code>MediaSelectPermission</code>.
     */
    public boolean equals(Object other) {
        return false;
    }

    /** 
     * Returns the hash code value for this object.
     *
     * @return The hash code value for this object.
     */
    public int hashCode() {
        return 0;
    }

    /** 
     * Reports the canonical string representation of the actions.
     * This is currently the empty string "", since there are no
     * actions for a <code>MediaSelectPermission</code>.
     *
     * @return The empty string "".  
     */
    public String getActions() {
        return null;
    }
}
