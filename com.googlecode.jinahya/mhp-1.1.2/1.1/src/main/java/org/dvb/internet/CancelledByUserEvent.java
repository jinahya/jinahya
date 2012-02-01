 
package org.dvb.internet;


import org.dvb.internet.InternetClientFailureEvent;

/**
 *	
 *	Event indicating that an operation on an internet client failed because the operation
 *	was canceled by the user
 */
public class CancelledByUserEvent extends InternetClientFailureEvent
{
	/**
	 *	Construct a new <code>CancelledByUserEvent</code>
	 *	@param source the source of the event
	 * 	@param url the URL to which the event relates. 
	 *	
	 */
	public CancelledByUserEvent(java.lang.Object source, java.net.URL url)
	{
		super(source,url);
	}





}

