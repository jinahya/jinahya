package org.davic.media;

/**
 * This interface is the base interface for both audio and subtitling language control. This  interface should never be implemented in a control alone, but always either as audio or subtitling  language control. If a language can not be selected because the user/application is not entitled to  access it, the language is reset to that before the invocation of the method. If more than one stream with the same language exists, the behaviour of  selectLanguage(String)is to select the first listed in the network signalling.  NOTE:This is equivalent to item b under 11.4.2.3,"Default media player behaviour".
 */
public interface LanguageControl extends javax.media.Control
{
	/**
	 * Provides the application a list of available languages.
	 * The returned strings contain three letter language codes according to ISO 639 standard.
	 * If there are no selectable languages, the method returns an array of length zero.
	 */
	public String[] listAvailableLanguages();

	/**
	 * Changes the language to the language given in the parameter. 
	 *
	 * @param lang the desired language code according to the ISO 639 standard.
	 * @exception LanguageNotAvailableException if the language given in the parameter is not available, 
	 * @exception org.davic.media.NotAuthorizedException if access to the required language is not 
	 * permitted
	*/
	public void selectLanguage(String lang) throws LanguageNotAvailableException, 
		org.davic.media.NotAuthorizedException;

	/**
	 * Returns the language code of the currently selected language. If this information is 
	 * not available, a String of length zero is returned. 
	 */
	public String getCurrentLanguage();

	/**
	 * Changes the language to the default language determined by the implementation.
	 *
	 * @return the language code of the default language.
	 * @exception org.davic.media.NotAuthorizedException  If access to the default language is not 
	 * permitted
	*/
	public String selectDefaultLanguage() throws org.davic.media.NotAuthorizedException;
}


