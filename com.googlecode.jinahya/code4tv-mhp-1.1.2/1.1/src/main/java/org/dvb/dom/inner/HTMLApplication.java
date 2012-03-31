package org.dvb.dom.inner;

import java.net.URL;

import org.dvb.application.inner.InnerApplication;

/**
 * Encapsulates the information needed to define a DVB-HTML application.
 */

public class HTMLApplication extends InnerApplication {
	/**
	 * Constructor for instances of the class. The semantics of these
	 * shall be as defined in the main body of the present document for
	 * the equivalent information when provided through an AIT.
	 *
	 * @param physicalRoot the physical root of the application entry point.
	 * @param initialPathBytes a string specifying the URL path component 
	 * to the entry point document. This path is relative to the root defined in 
	 * the physicalRoots parameter.
	 * @param parameters the string that is appended to the application initial 
	 * path as parameters
	 */
	public HTMLApplication(URL physicalRoot, String initialPathBytes,
		String parameters){}

	/**
	 * Constructor for instances of the class. The semantics of these
	 * shall be as defined in the main body of the present document for
	 * the equivalent information when provided through an AIT.
	 *
	 * @param physicalRoot the physical root of the application entry point.
	 * @param initialPathBytes a string specifying the URL path component 
	 * to the entry point document. This path is relative to the root defined in 
	 * the physicalRoots parameter.
	 * @param parameters the string that is appended to the application initial 
	 * path as parameters
 	 * @param label an array of one or more strings specifying a label that is associated 
 	 * with the set of data identified by the regular expression. This label can be used for 
	 * pre-fetching in a transport specific manner.
	 * @param regex an array of one or more strings specifying a regular expressions
	 * that can generate all URLs that are in the domain of the application.
	 *
	 * @throws IllegalArgumentException if either the regex or label parameters are an 
	 * array of length zero or if the lengths of these two arrays are not the same
	 */
	public HTMLApplication(URL physicalRoot, String initialPathBytes,
		String parameters, String[] label, String[] regex) {}

}

