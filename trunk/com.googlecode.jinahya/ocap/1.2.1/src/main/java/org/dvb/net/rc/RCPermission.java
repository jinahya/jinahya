package org.dvb.net.rc;

/**
 * This class is for return channel set-up permissions. An RCPermission 
 * contains a name, but no actions list.
 * <p>
 * The permission name can be "target:default", which indicates
 * the permission to use the default connection parameters.
 * <p>
 * The permission name can also be "target:&lt;phone number>", which
 * indicates the permission to use the specified phone number in
 * the connection set-up (ConnectionRCInterface.setTarget(ConnectionParameters) 
 * method).
 * <p>
 * A wildcard may be used at the end of the permission name. In that
 * case, all phone numbers starting with the number before the wildcard
 * are included in the permission. 
 * A "+" may be used at the start of the phone number to indicate a phone number 
 * including the international country code.
 * <p>
 * Examples:
 * <UL>
 * <LI>
 * target:0206234342  (Permission to dial the specified phone number)
 * </LI>
 * <LI>
 * target:020*  (Permission to dial phone numbers starting with 020)
 * </LI>
 * <LI>
 * target:*  (Permission to dial all phone numbers, including the default)
 * </LI>
 * </UL>
 * <p>
 * Note: ConnectionRCInterface.reserve(ResourceClient, Object) will throw a
 * SecurityException if the application is not allowed to set-up a connection
 * over the return channel at all (i.e., there is no valid target allowed).
 */

public class RCPermission
	extends java.security.BasicPermission
{
	/**
	 * Creates a new RCPermission with the specified name. The 
	 * name is the symbolic name of the RCPermission.
	 * 
	 * @param name the name of the RCPermission
	 */
	public RCPermission(String name)
	{
		super(name);
	}

	/** 
	 * Creates a new RCPermission object with the specified 
	 * name. The name is the symbolic name of the RCPermission, 
	 * and the actions String is unused and should be null. 
	 * This constructor exists for use by the Policy object to 
	 * instantiate new Permission objects.
	 *
	 * @param name the name of the RCPermission
	 * @param actions should be null.
	*/
	public RCPermission(String name, String actions)
	{
		super(name,actions);
	}
   /**
     * Checks if this RCPermission "implies" the specified Permission. 
     * <p>
     * More specifically, this returns true if and only if:
     * <ul>
     * <li> p is an instance of RCPermission, and
     * <li> p's name is implied by the name of this permission, as described
     *      by the wildcarding rules specified in the the description of
     *      this class.
     * </ul>
     *
     * @param p The Permission to check against.
     * @return true if the specified Permission is implied by this object; 
     *          false otherwise.
     **/
    public boolean implies(java.security.Permission p) {  return false; }
}

