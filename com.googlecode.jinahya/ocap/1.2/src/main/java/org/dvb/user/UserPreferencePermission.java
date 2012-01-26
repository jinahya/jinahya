package org.dvb.user;

/**
 * This class is for user preference and setting permissions. A
 * UserPreferencePermission contains a name, 
 * but no actions list.
 * <p>
 * The permission name can either be "read" or "write". The
 * "read" permission allows an application to read the user
 * preferences and settings (using <code>UserPreferenceManager.read</code>) for
 * which read access is not always granted. Access to
 * the following settings/preferences is always granted:
 * "User Language", "Parental Rating", "Default Font Size" and "Country Code" 
 * <p>
 * The "write" permission allows
 * an application to modify user preferences and settings
 * (using <code>UserPreferenceManager.write</code>).
 */

public class UserPreferencePermission
	extends java.security.BasicPermission
{
	/**
	 * Creates a new UserPreferencePermission with the 
         * specified name. The name is the symbolic name of the 
         * UserPreferencePermission.
	 * 
	 * @param name the name of the UserPreferencePermission
	 */
	public UserPreferencePermission(String name)
	{
		super(name);
	}

	/** 
	 * Creates a new UserPreferencePermission object with the specified 
	 * name. The name is the symbolic name of the UserPreferencePermission, 
	 * and the actions String is unused and should be null. 
	 * This constructor exists for use by the Policy object to 
	 * instantiate new Permission objects.
	 *
	 * @param name the name of the UserPreferencePermission
	 * @param actions should be null.
	*/
	public UserPreferencePermission(String name, String actions)
	{
		super(name,actions);
	}
}

