package org.dvb.application;

/**
 * This class represents a Permission to control the lifecycle
 * of another application.
 */

public final class AppsControlPermission extends java.security.BasicPermission {

	/**
	 * Creates a new AppsControlPermission.
         * There is a simple mapping between the Application control Permission 
         * requests and the way the AppsControlPermission are granted. This mapping
	 * is defined in the main body of the present document.
	 */
	public AppsControlPermission()
	{super("toto");
	}

	/**
	 * Creates a new AppsControlPermission.
         * There is a simple mapping between the Application control Permission 
	 * requests and the way the AppsControlPermission are granted. This mapping is 
	 * defined in the main body of the present document. 
	 * The actions string is currently unused and should be null.
	 * The name string is currently unused and should be empty.
	 * This constructor 
	 * exists for use by the java.security.Policy object to instantiate new 
	 * permission objects.
	 *
	 * @param name the name of the permission
	 * @param actions the actions string
         */
	public AppsControlPermission(String name, String actions)
	{super(name);
	}

        /**
	 * Returns the list of actions that had been passed to the
         * constructor - it shall return null.
	 *
	 * @return a null String.
 	 */
	public String getActions() { return null;}

	/**
	 * Checks if this AppsControlPermission object "implies" the specified permission. 
	 * @param permission the specified permission to check.
	 * @return true if and only if the specified permission is an instanceof AppsControlPermission
	 */
	public boolean implies(java.security.Permission permission) {return true;}

	/**
	 * Checks for equality against this AppsControlPermission object. 
	 *
 	 * @param obj the object to test for equality with this AppsControlPermission object.
	 * @return true if and only if obj is an AppsControlPermission
         */
	public boolean equals(Object obj) { return true;}

	/**
	 * Returns the hash code value for this object.
	 *
	 * @return the hash code value for this object.
         */
	public int hashCode() { return 0 ;}


}

