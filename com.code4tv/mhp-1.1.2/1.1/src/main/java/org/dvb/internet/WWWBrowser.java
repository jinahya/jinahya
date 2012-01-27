package org.dvb.internet;

import java.net.URL;

/**
 * This interface provides support for the operations required on an WWW 
 * browser.
 * 
 * <P>Any URLs passed to methods in this interface should correspond to the
 * HTTP or FTP URL schemes specified in RFC 1738. Other schemes or URL 
 * formats may be supported, but these are implementation-specific and additional 
 * supported schemes should be discovered by querying the capabilities of the 
 * web browser if they are required.
 * 
 */
public interface WWWBrowser extends InternetClient {
	

	/**
	 * Direct the web browser to display the page at the specified URL. If the web browser 
	 * is not already running, then this method will not cause the web browser to be 
	 * started and the call will fail.
	 * 
 	 * <P>This is an asynchronous operation, whose success or failure will be indicated by an 
	 * <code>InternetClientSuccessEvent</code> or <code>InternetClientFailureEvent</code> or 
	 * one of their subclasses.
	 * 
	 * @param url the URL to visit
	 * 
	 * @exception SecurityException if the caller does not have a <code>SocketPermission</code>
	 * for the host part of the specified URL
	 * @exception IllegalArgumentException if the URL scheme is not supported by the web 
	 * browser.
 	 * @exception ClientNotRunningException if the client is not currently running.
	 */
	public void goToURL(URL url) throws ClientNotRunningException;
		
}

