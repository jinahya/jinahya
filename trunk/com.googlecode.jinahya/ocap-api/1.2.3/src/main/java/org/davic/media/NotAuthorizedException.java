package org.davic.media;

/**
 * This exception indicates that the source can not be accessed in order to reference the new content, 
 * the source has not been accepted.
 */
public class NotAuthorizedException extends java.io.IOException 
{
	/**
	 * Constructor without reason
	 */
	public NotAuthorizedException()
	{
	}
	/**
	 * Constructor with reason
	 * @param reason the reason why the exception was thrown
	 */
	public NotAuthorizedException(String reason)
	{
	}
}


