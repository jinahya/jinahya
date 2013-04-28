package org.dvb.net.rc;

/**
 * Thrown when an application calls a method which it does not
 * have permission to call at that time. 
 */

public class PermissionDeniedException extends java.lang.Exception
{
	/**
	 * Default constructor for the exception
	 */
	public PermissionDeniedException()
	{
	}
	/**
	 * Constructor for the exception with a specified reason
	 *
	 * @param reason the reason why the exception was raised
	 */
	public PermissionDeniedException(String reason)
	{
	}
}

