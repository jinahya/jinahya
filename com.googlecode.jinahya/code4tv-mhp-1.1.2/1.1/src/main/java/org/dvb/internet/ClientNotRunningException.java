package org.dvb.internet;

/**
 * Exception thrown when a method of <code>InternetClient</code> or one of its subclasses is called 
 * while the client is not running, for instance if the client has been terminated by the user.
 */
public class ClientNotRunningException extends java.lang.Exception
{
	/**
	 * Construct a <code>ClientNotRunningException</code> with no detail message
	 */
	public ClientNotRunningException() {
		super();
	}

	/**
	 * Construct a <code>ClientNotRunningException</code> with the specified detail message
	 * @param reason the reason why the exception was thrown
	 */
	public ClientNotRunningException(String reason) {
		super(reason);
	}


}

