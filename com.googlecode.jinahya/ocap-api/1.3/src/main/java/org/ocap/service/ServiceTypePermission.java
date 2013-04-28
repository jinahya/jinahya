/*
 * ServiceTypePermission.java
 *
 *
 */
package org.ocap.service;

import java.security.BasicPermission;
import java.security.Permission;

// Imports for javadoc.
import javax.tv.service.selection.ServiceContext;
import javax.tv.service.selection.SelectPermission;

/**
 * <code>ServiceTypePermission</code> represents application permission to
 * select a specific service type using a {@link ServiceContext} accessible by the
 * application.
 * <p>
 * When this permission is evaluated, the <code>SecurityManager.checkPermission</code>
 * method must not fail when checking for
 * {@link javax.tv.service.selection.SelectPermission} on the accessed
 * <code>ServiceContext</code>.  Otherwise, the security manager check for this permission
 * will also fail.
 * </p><p>
 * Note that undefined service type strings may be provided to the constructor
 * of this class, but subsequent calls to <code>SecurityManager.checkPermission()</code>
 * with the resulting <code>ServiceTypePermission</code> object will fail.
 * </p>
 */
public final class ServiceTypePermission extends BasicPermission
{
    /**
     * Indicates an abstract service provided by the Host device manufacturer.
     */
    public static final String MFR = "abstract.manufacturer";
    
    /**
     * Indicates an abstract service provided by the HFC network provider (i.e., MSO).
     */
    public static final String MSO = "abstract.mso";
    
    /**
     * Indicates an inband broadcast service provided by a content provider.
     */
    public static final String BROADCAST = "broadcast";
    
    /**
     * Creates a new ServiceTypePermission object with the specified
     * service type name.
     *
     * @param type The name of the service type that can be selected.  Supported service
     *        types include "abstract.manufacturer", "abstract.mso", and "broadcast".  An
     *        asterisk may be used to signify a wildcard match.
     * @param actions The actions String is either "own" or "*".  The string "own" means
     *        the permission applies to your own service context, acquired via the
     *        <code>ServiceContextFactory.createServiceContext</code> or
     *        <code>ServiceContextFactory.getServiceContext</code> methods. The string "*"
     *        implies permission to these, plus permission for service contexts obtained from
     *        all other sources.
     */
    public ServiceTypePermission(String type, String actions)
    {
        super(type, actions);
    }
    
    /**
     * Checks if the specified permission is "implied" by this object. <p>
     *
     * Specifically, implies(Permission p) returns true if: <p>
     * <li><i>p</i> is an instance of ServiceTypePermission and
     * <li><i>p</i>'s action string matches this object's, or this object or <i>p</i>
     * has "*" as an action string, and <li><i>p</i>'s type string matches this
     * object's, or this object has "*" as a type string.
     * <p>In addition, implies(Permission p) returns true if:<p>
     * <li><i>p</i> is an instance of SelectPermission and,
     * <li><i>p's</i> locator contains an actual or implied source_id value which
     * corresponds to the type string in this object where [26] ISO/IEC 13818-1 defines
     * broadcast source_id values that correspond to a broadcast type string and table 11-4
     * defines abstract service values that correspond to abstract MSO and abstract manufacturer
     * type strings.
     * <li><i>p’s</i> action string matches this object’s, or this object has "*" as an action string.<p>
     *
     * @param p The permission against which to test.
     *
     * @return <code>true</code> if the specified permission is equal
     * to or implied by this permission; <code>false</code> otherwise.
     *
     */
    public boolean implies(Permission p)
    {
        return false;
    }
    
    /**
     * Tests two <code>ServiceTypePermission</code> objects for equality.
     * Returns <code>true</code> if and only if <code>obj</code>'s class is the
     * same as the class of this object, and <code>obj</code> has the same name
     * and actions string as this object.
     *
     * @param obj The object to test for equality.
     *
     * @return <code>true</code> if the two permissions are equal;
     * <code>false</code> otherwise.
     */
    public boolean equals(Object obj)
    {
        return false;
    }
    
    /**
     * Provides the hash code value of this object.  Two
     * <code>ServiceTypePermission</code> objects that are equal will
     * return the same hash code.
     *
     * @return The hash code value of this object.
     */
    public int hashCode()
    {
        return 0;
    }
    /**
     * Returns the canonical representation of the actions string.
     *
     * @return The actions string of this permission.
     */
     public String getActions()
     {
         return null;
     }
}