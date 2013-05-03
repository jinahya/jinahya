

package com.googlecode.jinahya.book;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author jkwon
 */
public class ExtendedFileInputStream extends FileInputStream {


    public ExtendedFileInputStream(final File file)
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

        System.out.println(getClass().getSimpleName() + "-> close()");

        super.close();
    }


}

