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
     * A constant value for all bytes.
     */
    public static final long ALL = -1L;


    /**
     * Copies all({@code length}=={@link #ALL}) or specified({@code length}>=0)
     * bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input stream
     * @param output the output stream
     * @param buffer the buffer
     * @param length number of bytes to copy; {@link #ALL} for all.
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

        if (length != ALL && length < 0) {
            throw new IllegalArgumentException(
                "illegal length(" + length + ")");
        }

        long count = 0L;
        for (int read; length == ALL || count < length; count += read) {
            int l = buffer.length;
            if (length != ALL) {
                final long r = length - count;
                if (r < buffer.length) {
                    l = (int) r;
                }
            }
            read = input.read(buffer, 0, l);
            if (read == -1) {
                if (length == ALL) {
                    break;
                }
                throw new EOFException("eof");
            }
            output.write(buffer, 0, read);
        }

        return count;
    }


    /**
     * Copies all bytes from {@code input} to {@code output} using given
     * {@code buffer}.
     *
     * @param input the input
     * @param output the output
     * @param buffer the buffer
     *
     * @return the number of bytes copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final InputStream input, final OutputStream output,
                            final byte[] buffer)
        throws IOException {

        return copy(input, output, buffer, ALL);
    }


    /**
     * Copies all({@code length}=={@link #ALL}) or specified({@code length}>=0)
     * bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input file
     * @param output the output stream
     * @param buffer the buffer
     * @param length number of bytes to copy; {@link #ALL} for all.
     *
     * @return the number of bytes copied.
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
     * Copies all({@code length}=={@link #ALL}) or specified({@code length}>=0)
     * bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input stream
     * @param output the output file
     * @param buffer the buffer
     * @param length number of bytes to copy; {@link #ALL} for all.
     *
     * @return the number of bytes copied.
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
     * Copies all({@code length}=={@link #ALL}) or specified({@code length}>=0)
     * bytes from {@code input} to {@code output} using specified
     * {@code buffer}.
     *
     * @param input the input stream
     * @param output the output file
     * @param buffer the buffer
     * @param length number of bytes to copy; {@link #ALL} for all.
     *
     * @return the number of bytes copied.
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
    protected Streams() {

        super();
    }


}
