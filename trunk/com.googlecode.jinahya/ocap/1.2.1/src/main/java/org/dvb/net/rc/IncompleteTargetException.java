package org.dvb.net.rc;

/**
 * Thrown when the target for a connection is incompletely specified. 
 * This is thrown either when the default connection target is incompletely
 * defined in the device or when an application provides an incompletely 
 * defined connection target to the device or when the connection target
 * is badly formed, e.g. includes illegal characters in a number parameter.
 */

public class IncompleteTargetException extends java.lang.Exception
{
	/**
	 * Default constructor for the exception
	 */
	public IncompleteTargetException()
	{
	}
	/**
	 * Constructor for the exception with a specified reason
	 *
	 * @param reason the reason why the exception was raised
	 */
	public IncompleteTargetException(String reason)
	{
	}
}

