package org.dvb.internet;

/**
 * Service representing the resident email client
 */
public interface EmailClientService extends InternetClientService
{
	/**
	 * Get the email address of the user. The returned value shall be the same as that obtained by reading the value of the "User @" 
	 * preference (see org.dvb.user.GeneralPreferences for details).
	 * 
	 * @return the email address of the user, or null if no email address is set or the email address is not available to the client.
	 * 
	 * @exception SecurityException if the caller does not have a <code>UserPreferencePermission</code>
         * with the name "read".
	 */
	public String getUserEmailAddress();
	
	/**
	 * Add an entry to the address book. As a side-effect an entry previously added by 
	 * this method may be lost. Implementations may restrict the number of entries a single 
	 * MHP application or source of applications may add.
	 *
	 * @param address the address to be added to the address book
	 * @param name the name that should be associated with that address
	 * @exception EntryExistsException if an entry with both the same name
	 * and the same address already exists in the address book.
	 * @exception IllegalArgumentException if the string passed as the email address does not conform
	 * to the internet email address format
	 * @exception IOException if no more address book entries can be added
	 * due to a lack of storage space or a limitation in the client
	 */
	public void addToAddressBook(String address, String name)
                       throws EntryExistsException,java.io.IOException;

/**
 * Set the initial recipient, subject & message body to be used when the email client starts.
 * This URL is specific to this instance of EmailClientService and will not impact any other instance and is only valid for the lifetime of this instance. Calling this method and then selecting the EmailClientService instance is equivalent to selecting the EmailClientService instance, obtaining the EmailClient and then calling the createMessage method there. If the application calling this method is still running when the message is sent (or fails) and has registered an InternetClientListener then the appropriate InternetClientEvent shall be sent corresponding to the success or failure of the operation to send the message.
 * 
 * @param to the address to which the email should be sent
 * @param subject the subject for the email
 * @param messageBody the body of the message.
 * @throws NullPointerException if any of the to address, subject or message body are null.
 * @throws java.lang.IllegalArgumentException if the destination address is an empty string.
 */
public void setInitialMessage(String to, String subject, String messageBody);

}

