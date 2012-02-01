package org.dvb.internet;

import java.net.URL;

/**
 * Event indicating that an operation on an internet client failed due to the specified URL not 
 * being available. This could be because a server is not available or because a file is not 
 * available on that server
 */
public class URLUnavailableEvent extends InternetClientFailureEvent
{

	/**
	 * Construct a new <code>URLUnavailableEvent</code>
	 * 
	 * @param source the source of the event
	 * @param url the URL to which the event relates.
	 */
	public URLUnavailableEvent(Object source, java.net.URL url) {
		super(source, url);
	}

}

