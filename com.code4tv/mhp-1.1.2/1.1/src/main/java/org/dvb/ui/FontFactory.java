package org.dvb.ui;

import java.io.IOException;

/**
 * Provides a mechanism for applications to instantiate fonts that
 * are not built into the system.
 * The two constructors of this class allow fonts to be downloaded either through 
 * the font index file of the application or directly from a font file in the 
 * format(s) specified in the main body of the present document.
 **/

public class FontFactory {
    /**
     * Constructs a FontFactory for the font index file bound to this application
     * in the application signalling. The call to the constructor is synchronous
     * and shall block until the font index file has been retrieved or an 
     * an exception is thrown.
     *
     *  @throws FontFormatException if there is an error in the font index
     *      file bound with the application.
     *  @throws IOException  if there is no font index file bound
     *       with the application, or if there is an
     *       error attempting to access the data in that
     *       file.
     **/
    public FontFactory() throws FontFormatException, IOException {
    }


    /**
     * Constructs a FontFactory for the font file found at the given location.
     * The call to the constructor is synchronous
     * and shall block until the font file has been retrieved or an exception
     * is thrown.
     *
     *  @param u  The location of the font file
     *
     *  @throws IOException  if there is an
     *       error attempting to access the data referenced
     *       by the URL
     *  @throws IllegalArgumentException if the URL is not both valid and supported
     *  @throws SecurityException    if access to the specified URL is denied by
     *       security policy
     *  @throws FontFormatException  if the file at that URL is not a valid
     *      font file as specified in the main body of the present document
     *
     **/
    public FontFactory(java.net.URL u) throws IOException,
       FontFormatException
		    
    {
    }


    /** 
     * Creates a font object from the font source associated with this
     * FontFactory. This font will remain valid even if the FontFactory
     * is no longer reachable from application code. The name returned
     * by Font.getName() might not be the same as the name supplied, for example,
     * it might have a string prepended to it that identifies the source FontFactory
     * in a platform-dependant manner. For FontFactory instances bound to the
     * font index file of an application, the call to the method is synchronous and
     * shall block until either an exception is thrown or any required network access
     * has completed.
     * <p>
     * The value of the style argument must be as defined in java.awt.Font.
     * Valid values are the following:
     *   <ul>
     * <li><code>java.awt.Font.PLAIN</code>
     * <li><code>java.awt.Font.BOLD</code>
     * <li><code>java.awt.Font.ITALIC</code>
     * <li><code>java.awt.Font.BOLD + java.awt.Font.ITALIC</code>
     *   </ul>
     *
     * @param name the font name
     * @param style the constant style used, such as java.awt.Font.PLAIN.
     * @param size the point size of the font
     *
     * @throws FontNotAvailableException  if a font with given parameters cannot
     *        be located or created.
     * @throws IOException if there is an error retrieving a font from the
     * network. Thrown only for font factory instances bound to the font index file of
     * an application.
     * @throws IllegalArgumentException   if the style parameter is not in the
     *        set of valid values, or if the size parameter
     *        is zero or negative.
     *  @throws FontFormatException  if the font file is not a valid 
     *      font file as specified in the main body of the present document.
     *      Thrown only for font factory instances bound to the font index file
     *      of an application.
     **/
    public java.awt.Font createFont(String name, int style, int size) 
    throws FontNotAvailableException,FontFormatException, IOException {
	return null;
    }
}

