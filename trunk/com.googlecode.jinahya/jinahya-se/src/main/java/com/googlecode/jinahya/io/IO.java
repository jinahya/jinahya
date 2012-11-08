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


import java.io.EOFException;
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


    public static long copy(final File input, final OutputStream output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("null file");
        }

        final InputStream fileInput = new FileInputStream(input);
        try {
            return copy(fileInput, output, buffer, length);
        } finally {
            fileInput.close();
        }
    }


    /**
     * Copies bytes from
     * <code>input</code> to
     * <code>file</code>.
     *
     * @param input source input
     * @param output target file
     * @param buffer buffer to use
     * @return the number of bytes copied.
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final InputStream input, final File output,
                            final byte[] buffer, final long length)
        throws IOException {

        final OutputStream fileOutput = new FileOutputStream(output);
        try {
            try {
                return copy(input, fileOutput, buffer, length);
            } finally {
                fileOutput.flush();
            }
        } finally {
            fileOutput.close();
        }
    }


//    /**
//     * Copy bytes from
//     * <code>input</code> to
//     * <code>output</code>.
//     *
//     * @param input source input
//     * @param output target output
//     * @param buffer buffer to use
//     * @return the number of bytes copied
//     * @throws IOException if an I/O error occurs.
//     */
//    public static long copy(final InputStream input, final OutputStream output,
//                            final byte[] buffer)
//        throws IOException {
//
//        return copy(input, output, buffer, -1L);
//    }
    /**
     *
     * @param input input
     * @param output output
     * @param buffer the buffer
     * @param length number of bytes to copy; -1L for unlimited.
     * @return the number of bytes copied.
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final InputStream input, final OutputStream output,
                            final byte[] buffer, final long length)
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
            throw new IllegalArgumentException(
                "buffer.length(" + buffer.length + ") == 0");
        }

        long count = 0L;
        int l = buffer.length;
        for (int read = -1; count < length; count += read) {
            if (length >/*=*/ 0 && l > (length - count)) {
                l = (int) (length - count);
            }
            read = input.read(buffer, 0, l);
            if (read == -1) {
                if (length >/*=*/ 0) {
                    throw new EOFException("eof");
                } else {
                    break;
                }
            }
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

