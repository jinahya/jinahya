package org.davic.media;

/**
 * This exception indicates that either the freeze method or the resume method was unsuccessful.
 */
public class MediaFreezeException extends javax.media.MediaException 
{
	/**
	 * Construct an exception without a reason
	 */
	public MediaFreezeException () 
	{
	}
	/**
	 * Construct an exception with a reason
	 * @param reason the reason why the exception was thrown
	 */
	public MediaFreezeException (String reason)
	{
	}
}


