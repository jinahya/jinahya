package org.dvb.internet;

/**
 * Event indicating that an operation on an internet client succeeded
 */
public class InternetClientSuccessEvent extends InternetClientEvent
{

	/**
	 * Construct a new <code>InternetClientSuccessEvent</code>
	 * 
	 * @param source the source of the event
	 * @param url the URL to which the event relates.
	 */
	public InternetClientSuccessEvent(Object source, java.net.URL url) {
		super(source, url);
	}
	
}

