/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.googlecode.jinahya.io;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * IO Utilities.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class IO {


    /** default buffer size. */
    private static final int DEFAULT_BUFFER_SIZE = 8192;


    public static long copy(final File file, final OutputStream output)
        throws IOException {

        return copy(file, output, new byte[DEFAULT_BUFFER_SIZE]);
    }


    public static long copy(final File file, final OutputStream output,
                            final byte[] buffer)
        throws IOException {

        if (file == null) {
            throw new NullPointerException("null file");
        }

        if (!file.isFile()) {
            throw new IllegalArgumentException("file is not an existing file");
        }

        final InputStream input =
            new BufferedInputStream(new FileInputStream(file));
        try {
            return copy(input, output, buffer);
        } finally {
            input.close();
        }
    }


    /**
     * Copies bytes from <code>input</code> to <code>file</code>.
     *
     * @param input source input
     * @param file target file
     * @return the number of bytes copied
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final InputStream input, final File file)
        throws IOException {

        return copy(input, file, new byte[DEFAULT_BUFFER_SIZE]);
    }


    /**
     * Copies bytes from <code>input</code> to <code>file</code>.
     *
     * @param input source input
     * @param file target file
     * @param buffer buffer to use
     * @return the number of bytes copied.
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final InputStream input, final File file,
                            final byte[] buffer)
        throws IOException {

        final BufferedOutputStream output =
            new BufferedOutputStream(new FileOutputStream(file));
        try {
            return copy(input, output, buffer);
        } finally {
            output.flush();
            output.close();
        }
    }


    /**
     * Copy bytes from <code>input</code> to <code>output</code>.
     *
     * @param input source input
     * @param output target output
     * @return the number of bytes copied
     * @throws IOException if an I/O error occurs.
     */
    private static long copy(final InputStream input, final OutputStream output)
        throws IOException {

        return copy(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }


    /**
     * Copy bytes from <code>input</code> to <code>output</code>.
     *
     * @param input source input
     * @param output target output
     * @param buffer buffer to use
     * @return the number of bytes copied
     * @throws IOException if an I/O error occurs.
     */
    private static long copy(final InputStream input, final OutputStream output,
                             final byte[] buffer)
        throws IOException {

        if (input == null) {
            throw new IllegalArgumentException("null input");
        }

        if (output == null) {
            throw new IllegalArgumentException("null output");
        }

        if (buffer == null) {
            throw new IllegalArgumentException("null buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException("zero-length buffer");
        }

        long count = 0L;

        for (int read = -1; (read = input.read(buffer)) != -1; count += read) {
            output.write(buffer, 0, read);
        }

        return count;
    }


    /**
     * Creates a new instance.
     */
    protected IO() {
        super();
    }


}

