package org.dvb.internet;

import java.net.URL;

/**
 * Event indicating that an operation on an internet client failed due to the specified URL not 
 * being accessible by the client due to access controls on the server.
 */
public class PermissionDeniedEvent extends InternetClientFailureEvent
{

	/**
	 * Construct a new <code>PermissionDeniedEvent</code>
	 * 
	 * @param source the source of the event
	 * @param url the URL to which the event relates.
	 */
	public PermissionDeniedEvent(Object source, java.net.URL url) {
		super(source, url);
	}

}
