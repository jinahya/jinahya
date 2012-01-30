/*
 * @(#)MediaSelectPermission.java	1.8 00/08/06
 *
 * Copyright 1998-2000 by Sun Microsystems, Inc.,
 * 901 San Antonio Road, Palo Alto, California, 94303, U.S.A.
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information
 * of Sun Microsystems, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Sun.
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
 * @version  1.8, 08/06/00
 **/
public final class MediaSelectPermission extends Permission implements Serializable {

    /**
     * Creates a new <code>MediaSelectPermission</code> object for the
     * specified <code>Locator</code>.
     *
     * @param locator The locator for which to create the permission.
     * A value of <code>null</code> indicates permission for all
     * locators.  
     **/
    public MediaSelectPermission(Locator locator) {
	super(null);
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
     **/
    public MediaSelectPermission(String locator, String actions) {
	super(null);   
    }


    /**
     * Checks if this <code>MediaSelectPermission</code> "implies" the
     * specified <code>Permission</code>. <p>
     *
     * More specificially, this method returns true if: <p>
     * <ul>
     * <li><i>p</i> is an instanceof MediaSelectPermission, and
     * <li><i>p</i>'s locator's external form is matches this object's locator
     *   string, or this object's locator string is "*".
     * </ul>
     *
     * @param p The <code>Permission</code> to check against.
     *
     * @return <code>true</code> if the specified
     * <code>Permission</code> is implied by this object;
     * <code>false</code> otherwise.
     **/

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
     **/
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
     * @return The empty string "".  */
    public String getActions()
    {
        return null;
    }

}
