package org.dvb.spi;

/**
 * This class is for applications which wish to be able to install
 * providers. A ProviderPermission contains a name and an action string.
 * <p>
 * The permission name is the fully qualified class name of the
 * provider class to be installed. Hence applications which can 
 * install more than one provider will have multiple instances of
 * this permission.<p>
 * The actions list shall either be "xlet" or "system". "xlet"
 * means the right to install the provider as an xlet bound provider
 * and "system" as a system bound provider. No checking shall be performed
 * on whether the specified class name is consistent with the action.
 */

public class ProviderPermission
	extends java.security.BasicPermission
{
	/**
	 * Creates a new ProviderPermission with the specified name. The 
	 * name is the symbolic name of the ProviderPermission.
	 * 
	 * @param name the name of the ProviderPermission
	 */
	public ProviderPermission(String name)
	{
		super(name);
	}

	/** 
	 * Creates a new ProviderPermission object with the specified 
	 * name. The name is the symbolic name of the ProviderPermission.
	 * The actions string should be either "xlet" or "system".
	 * This constructor exists for use by the Policy object to 
	 * instantiate new Permission objects.
	 *
	 * @param name the name of the ProviderPermission
	 * @param actions the requested actions
	*/
	public ProviderPermission(String name, String actions)
	{
		super(name,actions);
	}
}


