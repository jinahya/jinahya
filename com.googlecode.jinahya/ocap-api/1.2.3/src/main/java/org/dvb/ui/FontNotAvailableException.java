package org.dvb.ui;

/**
 * Thrown when attempt is made to instantiate a font that cannot be located.
 **/
public class FontNotAvailableException extends Exception {
    /**
     * Constructs a <code>FontNotAvailableException</code> with <code>null</code>
     * as its error detail message.
     */
    public FontNotAvailableException() {
        super();
    }

    /**
     * Constructs a <code>FontNotAvailableException </code> with the specified detail
     * message. The error message string <code>s</code> can later be
     * retrieved by the <code>{@link java.lang.Throwable#getMessage}</code>
     * method of class <code>java.lang.Throwable</code>.
     *
     * @param   s   the detail message.
     */
    public FontNotAvailableException(String s) {
        super(s);
    }
}


