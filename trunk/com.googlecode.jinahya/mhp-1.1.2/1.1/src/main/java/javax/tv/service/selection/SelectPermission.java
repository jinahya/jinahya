/*
 * @(#)SelectPermission.java	1.20 00/10/09
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
public final class SelectPermission extends Permission implements Serializable {

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
     **/
    public SelectPermission(Locator locator, String actions) {
	super(locator == null ? "*" : locator.toExternalForm());
	this.actions = actions;
	if (actions == null) {
	    throw new NullPointerException();
	}
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
     **/
    public SelectPermission(String locator, String actions) {
	super(locator == null ? "*" : locator);
	this.actions = actions;
	if (actions == null) {
	    throw new NullPointerException();
	}
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
     **/
    public boolean implies(Permission p) {
	if (p == null) {
		throw new NullPointerException();
	}
	if (!(p instanceof SelectPermission))
	    return false;

	SelectPermission sp = (SelectPermission)p;

	// TBD: Implementation is highly dependant on organization of locator
     	//      Use locator.equals() in the future? 
        boolean isName   = ( (getName().equals(sp.getName())) 
                            || (getName().equals("*")));
        boolean isAction = ( (getActions().equals(sp.getActions())) 
                            || (getActions().equals("*")));
        return ( isName && isAction );
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
     **/
    public boolean equals(Object other) {
	if (other == this) {
	    return true;
	}
	if (! (other instanceof SelectPermission)) {
	    return false;
	}
	SelectPermission that = (SelectPermission) other;
	return (getName().equals(that.getName()) 
                && getActions().equals(that.getActions()));
    }

    /**
     * Returns the hash code value for this object.
     *
     * @return A hash code value for this object.
     */
    public int hashCode() {
	return getName().hashCode() ^ actions.hashCode();
    }

    /**
     * Returns the canonical string representation of the actions.
     *
     * @return The canonical string representation of the actions.
     */
    public String getActions()
    {
        return actions;
    }
}
