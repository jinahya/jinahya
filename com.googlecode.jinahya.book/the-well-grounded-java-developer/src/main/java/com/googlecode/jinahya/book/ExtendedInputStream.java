

package com.googlecode.jinahya.book;


import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class ExtendedInputStream extends FilterInputStream {


    public ExtendedInputStream(final InputStream in,
                               final boolean errorOnClose) {
        super(in);

        this.errorOnClose = errorOnClose;
    }


    /**
     * Overridden for simple logging and conditional error.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void close() throws IOException {

        if (errorOnClose) {
            throw new IOException("errorOnClose");
        }

        System.out.println(getClass().getSimpleName() + "-> close()");

        super.close();
    }


    private final boolean errorOnClose;


}

