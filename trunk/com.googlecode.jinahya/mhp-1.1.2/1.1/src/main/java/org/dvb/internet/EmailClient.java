package org.dvb.internet;

/**
 * Interface supporting the operations required on an email client.
 */
public interface EmailClient extends InternetClient {
	
	/**
	 * Create a new email message. If any of the parameters are an empty string, the user 
	 * will be prompted for the missing information. 
	 * 
 	 * <P>This is an asynchronous operation, whose success or failure will be indicated by an 
	 * <code>InternetClientSuccessEvent</code> or <code>InternetClientFailureEvent</code> or one 
	 * of their subclasses.
	 * 
	 * @param to the address to which the email should be sent.
	 * @param subject the subject for the email.
	 * @param messageBody the body of the message.
	 * 
	 * @exception NullPointerException if any of the to address, subject or message 
	 * body are null.
 	 * @exception ClientNotRunningException if the client is not currently running.
 	 * @exception IllegalArgumentException if the destination address is an empty string.
	 */
	public void createMessage(String to, String subject, String messageBody) 
		throws ClientNotRunningException;
	
}

