package org.dvb.internet;

/**
 * Service representing the resident usenet news client
 *  
 */
public interface UsenetClientService extends InternetClientService
{
	/**
	 * Add a newsgroup to the list currently subscribed newsgroups. If the newsgroup is already
	 * in the list, then this method has no effect. As a side-effect a newsgroup previously
	 * subscribed to by this method may be unsubscribed. Implementations may restrict
	 *  the number of newsgroups a single MHP application or source of applications 
	 * may subscribe to.
	 *
	 * @param newsgroup the name of the newsgroup that should be subscribed to
	 * @exception IOException if no more newsgroups can be added due to a lack of storage space 
	 */

	public void subscribe(java.lang.String newsgroup)
                throws java.io.IOException;
/**
 * Set the initial message to be used when the news client starts.
 * This URL is specific to this instance of UsenetClientService and will not impact 
 * any other instance and is only valid for the lifetime of this instance. Calling 
 * this method and then selecting the UsenetClientService instance is equivalent to 
 * selecting the UsenetClientService instance, obtaining the UsenetClient and then 
 * calling the selectMessage method there. If the application calling this method is 
 * still running when the message is sent (or fails) and has registered an 
 * InternetClientListener then the appropriate InternetClientEvent shall be sent 
 * corresponding to the success or failure of the operation to send the message.
 * Calls to setInitialGroup shall cancel previous calls to setInitialMessage 
 * and vice-versa on the same UsenetClientService instance.
 * 
 * @param message the URL of the message. This may or may not include the address of a news server
 * @exception IllegalArgumentException if the specified URL does not include
 * a Usenet news message ID or does not correspond to the Usenet news URL 
 * format specified in RFC 1738
 */
public void setInitialMessage(java.net.URL message);

/**
 * Set the initial group to be displayed when the news client starts.
 * This URL is specific to this instance of UsenetClientService and will not impact 
 * any other instance and is only valid for the lifetime of this instance. Calling 
 * this method and then selecting the UsenetClientService instance is equivalent to 
 * selecting the UsenetClientService instance, obtaining the UsenetClient and then 
 * calling the selectMessage method there. If the application calling this method is 
 * still running when the message is sent (or fails) and has registered an 
 * InternetClientListener then the appropriate InternetClientEvent shall be sent 
 * corresponding to the success or failure of the operation to send the message.
 * Calls to setInitialGroup shall cancel previous calls to setInitialMessage 
 * and vice-versa on the same UsenetClientService instance.
 * @param group the URL of the message. This may or may not include the address of a news server
 * @exception IllegalArgumentException if the specified URL does not
 * correspond to the Usenet news URL format specified in RFC 1738
 */
public void setInitialGroup(java.net.URL group);

}

