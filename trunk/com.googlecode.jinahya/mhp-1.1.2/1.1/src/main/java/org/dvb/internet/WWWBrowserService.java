package org.dvb.internet;

/**
 * Service representing a resident WWW browser
 */
public interface WWWBrowserService extends InternetClientService
{
	
	/**
	 * Returns an array of supported MIME types, e.g. "application/vnd.rn-realmedia"
	 * 
	 * The returned MIME types shall include at least one entry with type "text/html".
	 * Each entry of this type shall have a 'version' parameter that indicates the 
	 * version of HTML that is supported, for example 
	 * text/html; version = "4.0".
	 * A browser may claim to support an HTML version while only implementing a subset 
	 * of the features - this is implementation-dependent.
	 * 
	 * A browser that wishes to explicitly indicate support for multiple HTML versions 
	 * shall return multiple entries of type "text/html" with different values for the 
	 * 'version' parameter.
	 * 
	 * @return an array of MIME types supported by the web browser
	 */	
	public String[] getAcceptedMediaTypes();

	/**
	 * Returns an array of names of installed plug-ins. The name in each string is defined by 
	 * the plug-in provider
	 * 
	 * @return an array of plugin names
	 */
	public String[] getSupportedPlugins();

	/**
	 * Returns the string used in the HTTP "User-Agent" header.
	 * 
	 * @return the string identifying the user agent
	 */
	public String getUserAgent();

	/**
	 * Check whether frames are supported by the browser and enabled.
	 * 
	 * @return true if the browser supports frames and frame support is enabled by the user, 
	 * false otherwise.
	 */
	public boolean areFramesSupported();

	/**
	 * Set the home page for the web browser.
	 * 
	 * @param defaultUrl the URL to be used as the default when the application is launched 
	 * with no starting URL.
	 * 
	 * @exception SecurityException if the caller does not have a <code>HomePagePermission</code>
	 * @exception IllegalArgumentException if the URL scheme is not supported by the 
	 * application. Different classes which implement or extend this interface may implement 
	 * different schemes.
	 * 
	 */
	public void setHomepage(java.net.URL defaultUrl);
	
	/**
	 * Add a bookmark to the list of bookmarks in the current application. As a side-effect
	 * a bookmark previously added by this method may be lost. Implementations may restrict 
	 * the number of bookmarks a single MHP application or source of applications may add.
	 *
	 * @param bookmarkUrl the URL that should be added to the bookmarks list.
	 * @param name the name that should be displayed for that URL in the bookmarks list.
	 * @exception EntryExistsException if a bookmark with both the same name 
	 * and the same URL already exists in the bookmarks list.
	 * @exception java.lang.IllegalArgumentException if the URL scheme is not supported 
	 * by the application. 
	 * @exception IOException if no more bookmarks can be added due to a lack of storage 
	 * space or other limitation in the client
	 */

	public void addBookmark (java.net.URL bookmarkUrl,java.lang.String name)
                  throws EntryExistsException,java.io.IOException;

/**
 * Set the initial URL to be used when the WWW browser starts.
 * This URL is specific to this instance of WWWBrowserService and will not impact any other instance and is only valid for the lifetime of this instance. Calling this method and then selecting the WWWBrowserService instance is equivalent to selecting the WWWBrowserService instance, obtaining the WWWBrowser and then calling the goToURL method there. If the application calling this method is still running when the specified initial page is displayed (or fails) and has registered an InternetClientListener then the appropriate InternetClientEvent shall be sent corresponding to the success or failure of the operation to display the specified initial URL.
 * 
 * @param initialUrl the URL to use
 * @throws java.lang.IllegalArgumentException if the URL scheme is not supported by the application. Different classes which implement or extend this interface may implement different schemes.
 * @exception SecurityException if the caller does not have a <code>SocketPermission</code>
 * for the host part of the specified URL
 */
public void setInitialURL(java.net.URL initialUrl);

}

