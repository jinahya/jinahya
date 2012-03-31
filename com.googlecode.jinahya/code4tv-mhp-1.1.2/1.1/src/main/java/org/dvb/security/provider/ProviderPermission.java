package org.dvb.security.provider;

/**
 * This class represents a Permission to install and remove cryptographic service providers.
 * Two target names are defined for this permission, insertProvider.{provider name} and
 * removeProvider.{provider name}.
 */

public final class ProviderPermission extends java.security.BasicPermission {

	/**
	 * Creates a new ProviderPermission.
	 */
	public ProviderPermission(String name)
	{super("toto");
	}

	/**
	 * Creates a new ProviderPermission.
 	 * 
	 * This constructor exists for use by the java.security.Policy 
	 * object to instantiate new permission objects.
	 *
	 * @param name the target name of the permission
	 * @param actions the actions string
         */
	public ProviderPermission(String name, String actions)
	{super(name);
	}

	/**
	 * Checks if this ProviderPermission object "implies" the specified permission. 
	 * @param permission the specified permission to check.
	 * @return true if and only if the specified permission is an instanceof ProviderPermission
	 */
	public boolean implies(java.security.Permission permission) {return true;}

	/**
	 * Checks for equality against this ProviderPermission object. 
	 *
 	 * @param obj the object to test for equality with this ProviderPermission object.
	 * @return true if and only if obj is an ProviderPermission
         */
	public boolean equals(Object obj) { return true;}

	/**
	 * Returns the hash code value for this object.
	 *
	 * @return the hash code value for this object.
         */
	public int hashCode() { return 0 ;}


}


