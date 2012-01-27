package org.dvb.internet;

import java.net.URL;

/**
 * Base class for all status events from internet clients.
 */
public class InternetClientEvent extends java.util.EventObject
{
	
	URL url;
	
	/**
	 * Construct a new <code>InternetClientEvent</code>
	 * 
	 * @param source the source of the event 
	 * @param url the URL to which the event relates.
	 */
	public InternetClientEvent(Object source, java.net.URL url) {
		super(source);
		this.url = url;
	}
	
	/**
	 * Get the URL for which this event was generated. In the case that a URL was not specified 
	 * when the event was constructed, null shall be returned.
	 *
	 * @return an instance of <code>java.net.URL</code> or null
	 */
	public java.net.URL getUrl() {
		return url;
	}
}

