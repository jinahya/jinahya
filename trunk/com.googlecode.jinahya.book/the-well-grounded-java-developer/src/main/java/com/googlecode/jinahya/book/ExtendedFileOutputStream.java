

package com.googlecode.jinahya.book;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author jkwon
 */
public class ExtendedFileOutputStream extends FileOutputStream {


    public ExtendedFileOutputStream(final File file)
        throws FileNotFoundException {

        super(file);
    }


    /**
     * Overridden for simple logging.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void close() throws IOException {

        System.out.println(getClass().getSimpleName() + " -> close()");

        super.close();
    }


}

