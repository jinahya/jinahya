/*
 * Copyright 2013 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.security;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;


/**
 * a utility class for {@link MessageDigest}s.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MessageDigests {


    /**
     * Algorithms that every implementation of the Java platform is required to
     * support.
     */
    public static final List<String> SUPPORTED_ALGORITHMS =
        Arrays.asList("MD5", "SHA-1", "SHA-256");


    /**
     * Digests on given {@code digest} with bytes read from given input stream
     * using specified {@code buffer}.
     *
     * @param digest the digest
     * @param input the input stream
     * @param buffer the buffer.
     * @param length the maximum number of bytes to digest; any negative value
     * for all available bytes.
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs
     */
    public static byte[] digest(final MessageDigest digest,
                                final InputStream input, final byte[] buffer,
                                final long length)
        throws IOException {

        if (digest == null) {
            throw new NullPointerException("digest");
        }

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer");
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException("buffer.length == 0");
        }

        long count = 0L;
        for (int read; length < 0L || count < length; count += read) {
            final int l = length < 0L ? buffer.length
                          : (int) Math.min(buffer.length, length - count);
            read = input.read(buffer, 0, l);
            if (read == -1) {
                break;
            }
            digest.update(buffer, 0, read);
        }

        return digest.digest();
    }


    /**
     * Digests on given {@code digest} with bytes read from given input file
     * using specified {@code buffer}.
     *
     * @param digest the digest
     * @param input the input file
     * @param buffer the buffer.
     * @param length the maximum number of byte to digest; any negative for all
     * available bytes.
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs
     *
     * @see #digest(MessageDigest, InputStream, byte[], long)
     */
    public static byte[] digest(final MessageDigest digest, final File input,
                                final byte[] buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final InputStream input_ = new FileInputStream(input);
        try {
            return digest(digest, input_, buffer, length);
        } finally {
            input_.close();
        }
    }


    /**
     * Digests on give {@code digest} with bytes read from given input channel
     * using specified {@code buffer}.
     *
     * @param digest the digest
     * @param input the input channel
     * @param buffer the buffer.
     * @param length the maximum number of byte to digest; any negative for all
     * available bytes.
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs
     */
    public static byte[] digest(final MessageDigest digest,
                                final ReadableByteChannel input,
                                final ByteBuffer buffer, final long length)
        throws IOException {

        if (digest == null) {
            throw new NullPointerException("digest");
        }

        if (input == null) {
            throw new NullPointerException("input");
        }

        if (buffer == null) {
            throw new NullPointerException("buffer");
        }

        if (buffer.capacity() == 0) {
            throw new IllegalArgumentException("buffer.capacity == 0");
        }

        long count = 0L;
        for (int read; length < 0L || count < length; count += read) {
            buffer.clear(); // position -> 0, limit -> capacity
            if (length >= 0L) {
                final long r = length - count;
                if (r < buffer.capacity()) {
                    buffer.limit((int) r);
                }
            }
            read = input.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip(); // limit -> position, position -> 0
            digest.update(buffer); // position -> limit
        }

        return digest.digest();
    }


    /**
     * Digests on given {@code digest} with bytes read from given input file
     * using specified {@code buffer}.
     *
     * @param digest the digest
     * @param input the input file
     * @param buffer the buffer.
     * @param length the maximum number of byte to digest; any negative for all
     * available bytes.
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs; or {@code length} is not
     * {@link #ALL} and reached to EOF before processing specified number of
     * bytes.
     */
    public static byte[] digest(final MessageDigest digest, final File input,
                                final ByteBuffer buffer, final long length)
        throws IOException {

        if (input == null) {
            throw new NullPointerException("input");
        }

        final ReadableByteChannel input_ =
            new FileInputStream(input).getChannel();
        try {
            return digest(digest, input_, buffer, length);
        } finally {
            input_.close();
        }
    }


    /**
     * Digests on given digest with bytes read from given input stream and
     * writes the result to given output stream.
     *
     * @param digest the digest
     * @param input the input stream
     * @param output the output stream
     * @param buffer the buffer.
     * @param length the maximum number of bytes to digest; any negative value
     * for all available bytes.
     *
     * @return the actual number of bytes digested
     *
     * @throws IOException if an I/O error occurs
     */
    public static long digest(final MessageDigest digest,
                              final InputStream input,
                              final OutputStream output, final byte[] buffer,
                              final long length)
        throws IOException {

        if (digest == null) {
            throw new NullPointerException("digest");
        }

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
            throw new IllegalArgumentException("buffer.length == 0");
        }

        long count = 0L;

        for (int read; length < 0L || count < length; count += read) {
            final int l = length < 0L ? buffer.length
                          : (int) Math.min(buffer.length, length - count);
            read = input.read(buffer, 0, l);
            if (read == -1) {
                break;
            }
            digest.update(buffer, 0, read);
        }

        output.write(digest.digest());

        return count;
    }


    /**
     * Digests on give digest with bytes read from given input channel
     * and writes the result to given output channel.
     *
     * @param digest the digest
     * @param input the input channel
     * @param output the output channel
     * @param buffer the buffer.
     * @param length the maximum number of byte to digest; any negative for all
     * available bytes.
     *
     * @return the actual number of bytes digested
     *
     * @throws IOException if an I/O error occurs
     */
    public static long digest(final MessageDigest digest,
                              final ReadableByteChannel input,
                              final WritableByteChannel output,
                              final ByteBuffer buffer, final long length)
        throws IOException {

        if (digest == null) {
            throw new NullPointerException("digest");
        }

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
            throw new IllegalArgumentException("buffer.capacity == 0");
        }

        long count = 0L;

        for (int read; length < 0L || count < length; count += read) {
            buffer.clear(); // position -> 0, limit -> capacity
            if (length >= 0L) {
                final long r = length - count;
                if (r < buffer.capacity()) {
                    buffer.limit((int) r);
                }
            }
            read = input.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip(); // limit -> position, position -> 0
            digest.update(buffer); // position -> limit
        }

        final ByteBuffer b = ByteBuffer.wrap(digest.digest());
        while (b.hasRemaining()) {
            output.write(b);
        }

        return count;
    }


    /**
     * Creates a new instance.
     */
    protected MessageDigests() {

        super();
    }


}
