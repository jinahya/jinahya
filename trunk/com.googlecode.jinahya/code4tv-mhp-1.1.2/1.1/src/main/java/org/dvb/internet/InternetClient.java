package org.dvb.internet;

import javax.tv.locator.Locator;

/**
 * Base interface for the internet clients. Access to those methods common to all running 
 * instances of the client (e.g. operations on bookmark lists) are all carried out through the 
 * service objects associated with the <code>InternetClient</code> object in question. These are 
 * accessed using the <code>getService()</code> method.
 */
public interface InternetClient extends javax.tv.service.selection.ServiceContentHandler
{
	/**
	 * Get the service object which matches this internet client. In the case of a web 
	 * browser, for example, this would be an instance of the WWWBrowserService class.
	 * 
	 * @return the service which matches the <code>InternetClient</code> object.
	 */
	public InternetClientService getService();
	
	/**
	 * Add a listener for <code>InternetClientEvents</code>. If the listener is already registered, 
	 * or the client is not running, then calls to this method have no effect.
	 * 
  	 * @param l the listener to be added.
	 */
	public void addInternetClientListener(InternetClientListener l);

	/**
	 * Remove a listener for <code>InternetClientEvents</code>. If the listener is not registered, 
	 * or the client is not running, then calls to this method have no effect.
	 * 
  	 * @param l the listener to be added.
	 */
	public void removeInternetClientListener(InternetClientListener l);
	
	/**
	 * Reports the portions of the service on which this handler operates.
	 * 
	 * @return An array of length 1, containing the locator representing the internet client. This
	 * shall be the same locator returned by calls to <code>getService().getLocator()</code>.
	 */
	public Locator[] getServiceContentLocators();
	
}

