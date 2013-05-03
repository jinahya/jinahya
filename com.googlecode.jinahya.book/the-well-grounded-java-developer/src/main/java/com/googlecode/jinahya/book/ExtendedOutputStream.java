

package com.googlecode.jinahya.book;


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class ExtendedOutputStream extends FilterOutputStream {


    public ExtendedOutputStream(final OutputStream out,
                                final boolean errorOnClose) {
        super(out);

        this.errorOnClose = errorOnClose;
    }


    /**
     * Overridden for simple logging.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void close() throws IOException {

        if (errorOnClose) {
            throw new IOException("errorOnClose");
        }

        System.out.println(getClass().getSimpleName() + " -> close()");

        super.close();
    }


    private final boolean errorOnClose;


}

