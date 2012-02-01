package org.dvb.internet;

import java.net.URL;

/**
 * This interface supports the operations required on a Usenet news client.
 * 
 * <P>Any URLs passed to methods in this interface should correspond to the usenet 
 * news URL format specified in RFC 1738
 * 
 */
public interface UsenetClient extends InternetClient {
	
	/**
	 * Select and display a message with the given message ID. If the news client 
	 * is not running, then this method will not cause it to be started and 
	 * the call will fail.
	 * 
 	 * <P>This is an asynchronous operation, whose success or failure will be indicated by an 
	 * <code>InternetClientSuccessEvent</code> or <code>InternetClientFailureEvent</code> or 
	 * one of their subclasses.
	 * 
	 * @param message the URL of the message. This may or may not include 
	 * the address of a news server.
	 * 
	 * @exception SecurityException if the caller does not have a <code>SocketPermission</code>
	 * for the host part of the specified URL
	 * @exception IllegalArgumentException if the specified URL does not include
	 * a Usenet news message ID or does not correspond to the Usenet news URL 
	 * format specified in RFC 1738
 	 * @exception ClientNotRunningException if the client is not currently running.
	 */
	public void selectMessage(URL message) throws ClientNotRunningException;

	/**
	 * Select and display messages in the specified newsgroup. If the news client 
	 * is not running, then this method will not cause it to be started and 
	 * the call will fail.
	 * 
 	 * <P>This is an asynchronous operation, whose success or failure will be indicated by an 
	 * <code>InternetClientSuccessEvent</code> or <code>InternetClientFailureEvent</code> or 
	 * one of their subclasses.
	 * 
	 * @param group the URL of the group. This may or may not include 
	 * the address of a news server.
	 * 
	 * @exception SecurityException if the caller does not have a <code>SocketPermission</code>
	 * for the host part of the specified URL.
	 * @exception IllegalArgumentException if the specified URL does not 
	 * correspond to the Usenet news URL format specified in RFC 1738 
 	 * @exception ClientNotRunningException if the client is not currently running.
	 */
	public void selectGroup(URL group) throws ClientNotRunningException;

}

