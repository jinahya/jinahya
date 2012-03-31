package org.dvb.io.persistent;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * This class encapsulates the attributes of a file stored in persistent storage.
 * The default attributes for a file are low priority, owner read / write only permissions
 * and null expiration date.
 */

public class FileAttributes {

	/**
	 * Value for use as a file priority.
	 */
	public static final int PRIORITY_LOW=1;

	/**
	 * Value for use as a file priority.
	 */
	public static final int PRIORITY_MEDIUM=2;

	/**
	 * Value for use as a file priority.
	 */
	public static final int PRIORITY_HIGH=3;
	
	/**
	 * Constructor.
	 *
	 * @param expiration_date an expiration date or null
	 * @param p the access permissions to use
	 * @param priority the priority to use in persistent storage
	 *
	 */
	FileAttributes(Date expiration_date, FileAccessPermissions p, int priority) {}

	/**
	 * Returns the expiration date. It will return the value used
	 * by the platform, which need not be the same as the value set.
	 * 
	 * @return the expiration date
	 */
	public Date getExpirationDate() { return null; }
	
	/**
	 * Sets the expiration date. This field is a hint to the platform to identify the
	 * date after which a file is no longer useful as percieved by the application. The 
	 * platform may choose to use a different date than the one given as a parameter.
	 *
	 * @param d the expiration date
	 */
	public void setExpirationDate(Date d) { }
	
	/**
	 * Returns the file access permissions
  	 *
	 * @return the file access permissions 
	 */
	public FileAccessPermissions getPermissions() { return null;}
	
	/**
	 * Sets the file access permissions. 
	 *
	 * @param p the file access permissions
	 */
	public void setPermissions(FileAccessPermissions p) {}

	/**
	 * Returns the priority to use in persistent storage
	 *
	 * @return the priority 
	 */
	public int getPriority() { return 0; }
	
	/**
	 * Sets the priority to use in persistent storage
	 *
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {}

	/**
	 * Associate a set of file attributes with a file. 
	 *
	 * @param p the file attributes to use
	 * @param f the file to use
	 * @throws SecurityException if the application is either denied access
	 * to the file or directories needed to reach the file by security policy 
	 * or is not authorised to modify the attributes of the file.
	 * @throws IOException if access to the file fails due to an IO error or
	 * if the file reference is not to a valid location in persistent storage
	 */
	public static void setFileAttributes(FileAttributes p, File f)
		throws IOException
	{
	}

	/**
	 * Get the attributes of a file. 
	 *
	 * @param f the file to use
	 * @return a copy of the attributes of a file
	 * @throws SecurityException if the application is denied access
	 * to the file or to directories needed to reach the file by security policy
	 * @throws IOException if access to the file fails due to an IO error or
	 * if the file reference is not to a valid location in persistent storage
	 */
	public static FileAttributes getFileAttributes(File f) throws IOException
	{
		return null;
	}
}

