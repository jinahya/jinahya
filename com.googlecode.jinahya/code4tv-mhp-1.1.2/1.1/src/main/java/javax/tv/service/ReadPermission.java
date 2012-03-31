/*
 * @(#)ReadPermission.java	1.5 00/08/28%
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

package javax.tv.service;

import java.security.Permission;
import javax.tv.locator.Locator;
import java.io.Serializable;

/**
 * This class represents permission to read the data referenced by a given
 * <code>Locator</code>.
 *
 * @version  1.5, 08/28/00
 */

public final class ReadPermission extends Permission implements Serializable {

    String actions = "";

    /**
     * Creates a new ReadPermission object for the specified locator.
     *
     * @param locator The locator.  Null indicates permission for all locators.
     */
    public ReadPermission(Locator locator) {
	super(locator == null ? "*" : locator.toExternalForm());
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
     * @param actions Should be <code>null</code>. */
    public ReadPermission(String locator, String actions) {
	//super(locator == null ? "*" : locator);
	super(locator);
        if (locator == null) {
           throw new NullPointerException("Locator string is null");
        }
	//this.actions = actions;
    }


  /**
   * Checks if this ReadPermission object "implies" the specified
   * permission. <p>
   *
   * More specificially, this method returns true if: <p>
   * <ul>
   * <li><i>p</i> is an instanceof ReadPermission, and
   * <li><i>p</i>'s locator's external form is matches this object's locator
   *   string, or this object's locator string is "*".
   * </ul>
   *
   * @param p The permission to check against.
   *
   * @return <code>true</code> if the specified permission is
   * implied by this object, <code>false</code> if not.
   */
  public boolean implies(Permission p) {
	if (p == null) {
		throw new NullPointerException();
	}

	// Implementation is highly dependant on organization of locator
	if (!(p instanceof ReadPermission)) 
		return false;

	// For now assume the implementation has read access to all data indexed
	// by a locator. TBD: check read access of FilePermission. 
        // if locator is equal or "*", return true
	
	return (getName().equals("*") || 
                getName().equals(((ReadPermission)p).getName()));
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
     * this <code>ReadPermission</code> object.  */
    public boolean equals(Object other) {
	if (other == this) {
	    return true;
	}
	if (! (other instanceof ReadPermission)) {
	    return false;
	}
	ReadPermission that = (ReadPermission) other;
	return getName().equals(that.getName());
    }

    /**
     * Returns the hash code value for this object.
     *
     * @return A hash code value for this object.
     */
    public int hashCode() {
	return getName().hashCode();
    }

    /**
     * Returns the canonical string representation of the actions,
     * which currently is the empty string "", since there are no actions for
     * a ReadPermission.
     *
     * @return the empty string "".
     */
    public String getActions()
    {
        return this.actions;
    }
}
