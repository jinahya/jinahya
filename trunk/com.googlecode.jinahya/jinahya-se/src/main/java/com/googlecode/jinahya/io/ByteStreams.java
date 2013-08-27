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


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Utilities for InputStreams and OutputStreams.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ByteStreams {


    /**
     * Copies bytes from given input stream to given output stream.
     *
     * @param input the input stream
     * @param output the output stream
     * @param buffer a buffer
     * @param length the maximum number of bytes to copy; any negative value for
     * all.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final InputStream input, final OutputStream output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (output == null) {
            throw new NullPointerException("output");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException(
                "buffer.length(" + buffer.length + ") == 0");
        }

        long count = 0L;

        for (int read; length < 0L || count < length; count += read) {
            final int l = length < 0L ? buffer.length
                          : (int) Math.min(buffer.length, length - count);
            read = input.read(buffer, 0, l);
            if (read == -1) {
                break;
            }
            output.write(buffer, 0, read);
        }

        return count;
    }


    /**
     * Copies bytes from given input file to given output stream.
     *
     * @param input the input file
     * @param output the output stream
     * @param buffer a buffer
     * @param length the maximum number of bytes to copy; any negative for all
     * available bytes.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final File input, final OutputStream output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final InputStream input_ = new FileInputStream(input);
        try {
            return copy(input_, output, buffer, length);
        } finally {
            input_.close();
        }
    }


    /**
     * Copies bytes from given input stream to given output file.
     *
     * @param input the input stream
     * @param output the output file
     * @param buffer a buffer
     * @param length the maximum number of bytes to copy; any negative for all
     * available bytes.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final InputStream input, final File output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output");
        }

        final OutputStream output_ = new FileOutputStream(output);
        try {
            try {
                return copy(input, output_, buffer, length);
            } finally {
                output_.flush();
            }
        } finally {
            output_.close();
        }
    }


    /**
     * Copies bytes from given input file to given output file.
     *
     * @param input the input file
     * @param output the output file
     * @param buffer a buffer
     * @param length the maximum number of bytes to copy; any negative for all
     * available bytes.
     *
     * @return the actual number of bytes copied.
     *
     * @throws IOException if an I/O error occurs
     */
    public static long copy(final File input, final File output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output");
        }

        final InputStream input_ = new FileInputStream(input);
        try {
            final OutputStream output_ = new FileOutputStream(output);
            try {
                try {
                    return copy(input_, output_, buffer, length);
                } finally {
                    output_.flush();
                }
            } finally {
                output_.close();
            }
        } finally {
            input_.close();
        }
    }


    /**
     * Creates a new instance.
     */
    protected ByteStreams() {

        super();
    }


}
