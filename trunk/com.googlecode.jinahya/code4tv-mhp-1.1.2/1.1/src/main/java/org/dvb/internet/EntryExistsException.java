package org.dvb.internet;

/**
 *	Exception generated when an application tries to add a bookmark or address book entry
 *	that already exists
 */
public class EntryExistsException extends java.lang.Exception
{
	/**
	 *	Construct a <code>EntryExistsException</code> with no detail message
	 */
	public EntryExistsException()
	{
	}

	/**
	 *	Construct a <code>EntryExistsException</code> with the specified detail message
	 * @param message the reason why the exception was thrown
	 */
	public EntryExistsException(java.lang.String message)
	{
		super(message);
	}





}


