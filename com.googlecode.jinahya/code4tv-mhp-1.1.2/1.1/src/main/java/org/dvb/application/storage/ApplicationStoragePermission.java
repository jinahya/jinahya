package org.dvb.application.storage;

/**
 * <p>
 * This class represents a permission to manage applications
 * stored in the MHP terminal. An ApplicationStoragePermission contains 
 * a name representing the organisation_id whose applications can 
 * be managed and an actions list representing the permitted 
 * actions, e.g. store and/or remove applications.
 * </p>
 * <p>The name of the permission contains the organisation_id
 * represented in hexadecimal form as defined in the section
 * "Text encoding of application identifiers" in the main body
 * of this specification.  Valid organization ids must be in
 * the range <code>"1"</code> to <code>"ffffffff"</code> inclusive.  Alternatively, the
 * value <code>"*"</code> indicates all organization ids.
 * </p>
 * <p>The actions string shall be a comma-separated list of one or
 * more of the following :-
 * <ul>
 * <li><code>"manageService"</code>, representing permission to query what
 *    applications are stored in a stored application service with the given
 *    organisation ID.  This permission is also necessary (but not sufficient)
 *    to store applications into and remove applications from a stored
 *    application service, where the stored application service has the given
 *    organisation ID.
 * <li><code>"storeService"</code>, representing permission to store an
 *    application in a stored application service, where the application has
 *    the given organisation ID.
 * <li><code>"removeService"</code>, representing permission to remove an
 *    application from a stored application service, where the application has
 *    the given organisation ID.
 * <li><code>"createService"</code>, representing permission to create a
 *    stored application service with a given organisation ID.
 * <li><code>"deleteService"</code>, representing permission to remove a
 *    stored application service with a given organisation ID.
 * <li><code>"manageCache"</code>, representing permission to query what applications
 *    are stored in an application cache with the given organisation ID.
 *    This permission is also necessary (but not sufficient) to store
 *    applications into and remove applications from an application cache,
 *    where the application cache has the given organisation ID.
 * <li><code>"storeCache"</code>, representing permission to store an
 *    application in a cache.
 * <li><code>"removeCache"</code>, representing permission to remove an
 *    application from a cache.
 * </ul>
 *
 * @since MHP1.1
 */
public class ApplicationStoragePermission
    extends java.security.Permission
{
    /** 
      * Creates a new <code>ApplicationStoragePermission</code> object with the
      * specified name and actions string. The name contains the
      * organisation_id of the applications that can be managed
      * and the actions String shall be a comma-separated list
      * of actions as defined above.
      *
      * Permission objects constructed with incorrectly encoded
      * parameters do not represent any permission and are 
      * ignored by the platform.
      *
      * @param name the organisation_id whose applications can be
      *             managed. This is encoded in hexadecimal representation
      *             as if by {@link java.lang.Integer#toHexString(int)}, and must be
      *             in the range <code>"1"</code> to <code>"ffffffff"</code>
      *             inclusive.  Alternatively, the value <code>"*"</code>
      *             indicates all organization ids.
      * @param actions Shall conform to the syntax described above.
      */
    public ApplicationStoragePermission(String name, String actions) {
	super(name);
    }

    /**
     * Checks if the specified permission's actions are "implied by"
     * this object's actions.
     * <p>
     * If <code>X</code> is an <code>ApplicationStoragePermission</code>, and
     * <code>Y</code> is any <code>Permission</code>, then <code>X.implies(Y)</code>
     * returns true if and only if all of the following hold:
     * <ul>
     * <li><code>Y</code> is an instance of <code>ApplicationStoragePermission</code>
     * <li><code>X</code> and <code>Y</code> have the same run-time type
     *    (i.e. <code>X.getClass() == Y.getClass()</code>)
     * <li><code>X</code> and <code>Y</code> were both constructed with
     *    correctly encoded parameters
     * <li><code>X</code> and <code>Y</code> both have the same organization
     *    ID, or <code>X</code> has the organization ID <code>"*"</code>
     * <li><code>X</code> contains all the actions requested by
     *    <code>Y</code>.  The order of the comma-separated actions list
     *    does not affect the results of this check.
     * </ul>
     *
     * @param permission the permission to check against.
     *
     * @return <code>true</code> if the specified permission is implied by this object,
     * <code>false</code> if not.
     */
    public boolean implies(java.security.Permission permission) {
        return false;
    }
 
    /**
     * Checks two permission objects for equality.
     * <P>
     * Do not use the <code>equals</code> method for making access control
     * decisions; use the <code>implies</code> method.
     * <p>
     * If <code>X</code> is an <code>ApplicationStoragePermission</code>, and
     * <code>Y</code> is any <code>Object</code>, then <code>X.equals(Y)</code>
     * returns true if and only if all of the following hold:
     * <ul>
     * <li><code>Y</code> is not <code>null</code>
     * <li><code>Y</code> is an instance of <code>ApplicationStoragePermission</code>
     * <li><code>X</code> and <code>Y</code> have the same run-time type
     *    (i.e. <code>X.getClass() == Y.getClass()</code>)
     * <li><code>X</code> and <code>Y</code> were both constructed with
     *    correctly encoded parameters
     * <li><code>X</code> and <code>Y</code> both have the same organization
     *    ID, or both have organization ID <code>"*"</code>.
     * <li><code>X</code> has the same actions as
     *    <code>Y</code>.  The order of the comma-separated actions list
     *    does not affect the results of this check.
     * </ul>
     *
     * @param obj the object we are testing for equality with this object.
     *
     * @return <code>true</code> if both <code>ApplicationStoragePermission</code>
     *         objects are equivalent.
     */
    public boolean equals(Object obj) {
        return false;
    }
 
    /**
     * Returns the hash code value for this <code>ApplicationStoragePermission</code>
     * object.
     * <P>
     * The required <code>hashCode</code> behavior for
     * <code>ApplicationStoragePermission</code> objects is the following: <p>
     * <ul>
     * <li>Whenever it is invoked on the same ApplicationStoragePermission
     *    object more than once during an execution of a Java application,
     *    the <code>hashCode</code> method
     *    must consistently return the same integer. This integer need not
     *    remain consistent from one execution of an application to another
     *    execution of the same application. <p>
     * <li>If two <code>ApplicationStoragePermission</code> objects are equal according to
     *    the {@link #equals(Object)}
     *    method, then calling the <code>hashCode</code> method on each of the
     *    two Permission objects must produce the same integer result.
     * </ul>
     *
     * @return a hash code value for this object.
     */
    public int hashCode() {
        return 0;
    }

    /**
     * Returns the actions as a <code>String</code>.
     * Must always return actions in canonical form.
     * <P>
     * Permission objects constructed with an incorrectly encoded
     * action parameter shall return an empty string.
     * <P>
     * Permission objects constructed with a correctly encoded
     * action parameter shall return a comma-separated list
     * of actions, with the actions sorted in the order given
     * by {@link java.lang.String#compareTo(String)}.
     *
     * @return the actions of this <code>Permission</code>.
     */
    public String getActions() {
        return null;
    }
 
    /**
     * Returns an empty <code>PermissionCollection</code> for
     * <code>ApplicationStoragePermission</code> objects.
     *
     * @return a new <code>PermissionCollection</code> object for
     *         <code>ApplicationStoragePermissions</code>.
     */
    public java.security.PermissionCollection newPermissionCollection() {
        return null;
    }
}




