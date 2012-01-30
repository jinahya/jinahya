package org.dvb.ui;

/**
 * Thrown when attempt is made to read a file describing a font when the
 * contents of that file are not valid.
 **/
public class FontFormatException extends Exception {
    /**
     * Constructs a <code>FontFormatException</code> with <code>null</code>
     * as its error detail message.
     */
    public FontFormatException() {
        super();
    }

    /**
     * Constructs a <code>FontFormatException</code> with the specified detail
     * message. The error message string <code>s</code> can later be
     * retrieved by the <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param   s   the detail message.
     */
    public FontFormatException(String s) {
        super(s);
    }
}




