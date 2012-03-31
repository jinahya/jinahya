package org.dvb.internet;

/**
 * This class is for permissions related to the ability for an MHP
 * application to set the home page of a WWW browser in the internet access profile.
 * If an application has this permission then shall be able to set the home page.
 */

public class HomePagePermission
	extends java.security.BasicPermission
{
	/**
	 * Creates a new <code>HomePagePermission</code>. The name parameter
	 * is not used and must be set to empty string "".
	 * Implementations of this version of the present document
	 * shall ignore the name, but it could be taken into use
	 * in future versions of the present document.
	 *
	 * @param name the name of the <code>HomePagePermission</code>. Not used,
	 * shall be "".
	 */
	public HomePagePermission(String name)
	{
		super(name);
	}

	/**
	 * Creates a new <code>HomePagePermission</code>. The name and actions
	 * parameters are not used.
	 * Implementations of this version of the present document
	 * shall ignore the name and actions, but they could be taken
	 * into use in future versions of the present document.
	 * This constructor exists for use by the Policy object to
	 * instantiate new Permission objects.
	 *
	 * @param name the name of the <code>HomePagePermission</code>. Not used,
	 * shall be "".
	 * @param actions Not used, shall be null.
	 */
	public HomePagePermission(String name, String actions)
	{
		super(name, actions);
	}
}


