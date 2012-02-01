package org.dvb.internet;

/**
 * Event indicating that an operation on an internet client failed. Typically, internet clients
 * will post subclasses of this event detailing the reason why the operation failed.
 */
public class InternetClientFailureEvent extends InternetClientEvent
{

	/**
	 * Construct a new <code>InternetClientFailureEvent</code>
	 * 
	 * @param source the source of the event
	 * @param url the URL to which the event relates.
	 */
	public InternetClientFailureEvent(Object source, java.net.URL url) {
		super(source, url);
	}

}

