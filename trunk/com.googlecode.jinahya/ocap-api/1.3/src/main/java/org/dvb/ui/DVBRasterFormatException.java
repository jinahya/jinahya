package org.dvb.ui;

/**
 * This exception is thrown for some invalid operations on instances of 
 * <code>DVBBufferedImage</code>. The precise conditions are defined in the places
 * where this exception is thrown.
 *
 * @see org.dvb.ui.DVBBufferedImage
 * @since MHP 1.0.1
 */

public class DVBRasterFormatException extends java.lang.Exception {
	/**
	 * Constructs an instance of <code>DVBRasterFormatException</code>
	 * with the specified detail message.
	 * @param   s     the detail message
	 * @since   MHP1.0
	 */
	public DVBRasterFormatException(String s) {
		super (s);
	}
}

