package org.dvb.net.ca;

/**
 * This class is for CA permissions. A CAPermission contains a name, 
 * but no actions list.
 * <p>
 * A CAPermission contains a range of CA system ids and a specific 
 * permission for that range of CA system ids. Instead of a range of
 * CA system ids, the CAPermission can also refer to a single CA
 * system id.
 * <p>
 * The name has the following syntax:
 * <p>
 *  CASystemIdRange ":" Permission
 * <p>
 * where CASystemIdRange = CASystemId [ "-" CASystemId ] | "*"
 * <BR>
 * and Permission = "MMI" | "buy" | "entitlementInfo" | "messagePassing" | "*"
 * <p>
 * Examples:
 * <UL>
 * <LI>
 * 0x1200-0x120A:buy  (The permission to buy entitlement for all the CA
 * systems with ids between 0x1200 and 0x120A inclusive.)
 * </LI>
 * <LI>
 *   0x1201:entitlementInfo (The permission to get entitlement information
 * for the CA system with id 0x1201)
 * </LI>
 * <LI>
 *   0x120d:* (This wildcard expresses all the permissions for the CA system
 * with id 0x120d).
 * </LI>
 * </UL>
 * <p>
 * Note: The CASystemId is expressed as a hexadecimal value.
 * <p>
 * The permission "MMI" corresponds with the SecurityException on 
 * CAModuleManager.addMMIListener(). The permission "buy" corresponds with
 * the SecurityException on CAModule.buyEntitlement(). The permission
 * "entitlementInfo" corresponds with the SecurityException on
 * CAModule.queryEntitlement() and CAModule.listEntitlements(). The
 * permission "messagePassing" corresponds with 
 * CAModule.openMessageSession(MessageListener)
 */

public class CAPermission
	extends java.security.BasicPermission
{
	/**
	 * Creates a new CAPermission with the specified name. The 
	 * name is the symbolic name of the CAPermission.
	 * 
	 * @param name the name of the CAPermission
	 */
	public CAPermission(String name)
	{
		super(name);
	}

	/** 
	 * Creates a new CAPermission object with the specified 
	 * name. The name is the symbolic name of the CAPermission, 
	 * and the actions String is unused and should be null. 
	 * This constructor exists for use by the Policy object to 
	 * instantiate new Permission objects.
	 *
	 * @param name the name of the CAPermission
	 * @param actions should be null.
	*/
	public CAPermission(String name, String actions)
	{
		super(name,actions);
	}
  /**
   * Checks if the specified permission is "implied" by this object.
   * <p>
   * @param p the permission to check against.
   * @return true if the passed permission is equal to or implied by
   * this permission, false otherwise.
   */
   public boolean implies (java.security.Permission p) {
     return false;
   }

}

