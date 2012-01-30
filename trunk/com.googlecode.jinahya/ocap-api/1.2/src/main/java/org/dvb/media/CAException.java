package org.dvb.media;

/**
 * This exception is thrown when access to a media stream is denied by the CA system. It will typically be thrown by calls to DataSource.start() when access to the stream accessed by the DataSource is denied.
 */

public class CAException extends java.io.IOException {
	/**
	 * Constructor without a reason
	 */
	public CAException()
	{
	}
	/**
	 * Constructor with a reason
	 *
	 * @param reason the reason why access to the stream failed
	 */
	public CAException (String reason)
	{
	}
}



