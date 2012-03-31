package org.dvb.io.persistent;

/**
 * This class encapsulates file access permissions, world, Organisation and owner.
 * World means all applications authorised to access persistent storage.
 * Owner means the application which created the file. Organisation is defined
 * as applications with the same organisation id as defined elsewhere in the
 * present document.
 */

public class FileAccessPermissions {
	
	/**
	 * This constructor encodes all the file access permissions as a set of booleans.
 	 *
	 * @param readWorldAccessRight read access for all applications
	 * @param writeWorldAccessRight write access for all applications
	 * @param readOrganisationAccessRight read access for organisation
	 * @param writeOrganisationAccessRight write access for organisation
	 * @param readApplicationAccessRight read access for the owner
	 * @param writeApplicationAccessRight write access for the owner
	 */
	public FileAccessPermissions(boolean readWorldAccessRight, boolean writeWorldAccessRight, 
		boolean readOrganisationAccessRight, boolean writeOrganisationAccessRight,
		boolean readApplicationAccessRight, boolean writeApplicationAccessRight) 
	{ return; }
	
	/**
	 * Query whether this permission includes read access for the world.
	 *
	 * @return true if all applications can have read access, otherwise false.
	 */
	public boolean hasReadWorldAccessRight() { return false;}
	
	/**
	 * Query whether this permission includes write access for the world.
	 *
	 * @return true if all applications can have write access, otherwise false.
	 */
	public boolean hasWriteWorldAccessRight() {return false;}
	
	/**
	 * Query whether this permission includes read access for the organisation
	 *
	 * @return true if applications in this organisation can have read access, otherwise false.
	 */
	public boolean hasReadOrganisationAccessRight() {return false;}
	
	/**
	 * Query whether this permission includes write access for the organisation
	 *
	 * @return true if applications in this organisation can have read access, otherwise false.
	 */
	public boolean hasWriteOrganisationAccessRight() {return false;}
	
	/**
	 * Query whether this permission includes read access for the owning application
	 *
	 * @return true if the owning application can have read access, otherwise false.
	 */
	public boolean hasReadApplicationAccessRight() {return false;}
	
	/**
	 * Query whether this permission includes write access for the owning application
	 *
	 * @return true if the owning application can have write access, otherwise false.
	 */
	public boolean hasWriteApplicationAccessRight() {return false;}
	
	/**
	 * This method allows to modify the permissions on this instance of 
	 * the FileAccessPermission class.
	 *
	 * @param ReadWorldAccessRight read access for all applications
	 * @param WriteWorldAccessRight write access for all applications
	 * @param ReadOrganisationAccessRight read access for organisation
	 * @param WriteOrganisationAccessRight write access for organisation
	 * @param ReadApplicationAccessRight read access for the owner
	 * @param WriteApplicationAccessRight write access for the owner
	 */
	public void setPermissions(boolean ReadWorldAccessRight, 
		boolean WriteWorldAccessRight, boolean ReadOrganisationAccessRight, 
		boolean WriteOrganisationAccessRight, boolean ReadApplicationAccessRight, 
		boolean WriteApplicationAccessRight) {return;}
}

