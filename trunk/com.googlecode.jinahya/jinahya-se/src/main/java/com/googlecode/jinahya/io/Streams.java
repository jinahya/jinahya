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
 * Utilities for InputStreams and OutputStreams.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Streams {


    /**
     * Copies all or some bytes from {@code input} to {@code output} using
     * specified {@code buffer}.
     *
     * @param input input
     * @param output output
     * @param buffer the buffer
     * @param length number of bytes to copy; -1L for unlimited.
     *
     * @return the number of bytes copied.
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

        long c = 0L;
        for (int r, l; length < 0L || c < length; c += r) {
            l = buffer.length;
            if (length >= 0) {
                l = (int) Math.min(l, (length - c));
            }
            r = input.read(buffer, 0, l);
            if (r == -1) {
                if (length < 0L) {
                    break;
                }
                throw new EOFException("eof");
            }
            output.write(buffer, 0, r);
        }

        return c;
    }


    /**
     * Copies all bytes from {@code input} to {@code output} using given
     * {@code buffer}.
     *
     * @param input the input
     * @param output the output
     * @param buffer the buffer
     *
     * @return number of bytes copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final InputStream input, final OutputStream output,
                            final byte[] buffer)
        throws IOException {

        return copy(input, output, buffer, -1L);
    }


    public static long copy(final File source, final OutputStream output,
                            final byte[] buffer, final long length)
        throws IOException {

        if (source == null) {
            throw new NullPointerException("source");
        }

        final InputStream input = new FileInputStream(source);
        try {
            return copy(input, output, buffer, length);
        } finally {
            input.close();
        }
    }


    public static long copy(final InputStream input, final File target,
                            final byte[] buffer, final long length)
        throws IOException {

        if (target == null) {
            throw new NullPointerException("target");
        }

        final OutputStream output = new FileOutputStream(target);
        try {
            try {
                return copy(input, output, buffer, length);
            } finally {
                output.flush();
            }
        } finally {
            output.close();
        }
    }


    /**
     * Creates a new instance.
     */
    protected Streams() {

        super();
    }


}

