/*
 * Copyright 2013 Jin Kwon <onacit at gmail.com>.
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


package com.googlecode.jinahya.nio.channels;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;


/**
 *
 * @author Jin Kwon <onacit at gmail.com>
 */
public class ByteChannels {


    /**
     *
     * @param input
     * @param output
     * @param buffer
     * @param length number of bytes to copy. -1 for unlimited.
     *
     * @return the number of byte copied.
     *
     * @throws IOException if an I/O error occurs.
     */
    public static long copy(final ReadableByteChannel input,
                            final WritableByteChannel output,
                            final ByteBuffer buffer, final long length)
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

        if (buffer.capacity() == 0) {
            throw new IllegalArgumentException(
                "buffer.capacity(" + buffer.capacity() + ") == 0");
        }

        if (length != -1 && length < 0) {
            throw new IllegalArgumentException(
                "illegal length(" + length + ")");
        }

        long count = 0L;
        for (int read; length == -1 || count < length; count += read) {
            buffer.clear(); // position -> 0, limit -> capacity
            if (length != -1) {
                final long r = length - count;
                if (r < buffer.capacity()) {
                    buffer.limit((int) r);
                }
            }
            read = input.read(buffer);
            if (read == -1) {
                if (length == -1) {
                    break;
                } else {
                    throw new IOException("eof");
                }
            }
            buffer.flip(); // limit -> position, position -> 0
            while (buffer.hasRemaining()) {
                output.write(buffer);
            }
        }

        return count;
    }


    public static long copy(final File input, final WritableByteChannel output,
                            final ByteBuffer buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final FileChannel input_ = new FileInputStream(input).getChannel();
        try {
            try {
                return copy(input_, output, buffer, length);
            } finally {
                input_.force(false);
            }
        } finally {
            input_.close();
        }
    }


    public static long copy(final ReadableByteChannel input, final File output,
                            final ByteBuffer buffer, final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output");
        }

        final FileChannel output_ = new FileOutputStream(output).getChannel();
        try {
            try {
                return copy(input, output_, buffer, length);
            } finally {
                output_.force(false);
            }
        } finally {
            output_.close();
        }
    }


    public static long copy(final File input, final File output,
                            final ByteBuffer buffer, final long length)
        throws IOException {

        if (output == null) {
            throw new NullPointerException("output");
        }

        final ReadableByteChannel input_ =
            new FileInputStream(input).getChannel();
        try {
            final FileChannel output_ =
                new FileOutputStream(output).getChannel();
            try {
                try {
                    return copy(input, output_, buffer, length);
                } finally {
                    output_.force(false);
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
    protected ByteChannels() {

        super();
    }


}
