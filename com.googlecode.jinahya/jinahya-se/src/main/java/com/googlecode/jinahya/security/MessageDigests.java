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
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;


/**
 * Utility class for {@link MessageDigest}s.
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
     * A constant value for unlimited length.
     */
    public static final long ALL = -1L;


    /**
     * Digest all({@code length}=={@link #ALL}) or specified
     * number({@code length}!={@link #ALL}) of bytes from {@code input} using
     * given {@code buffer}.
     *
     * @param digest digest
     * @param input input file
     * @param buffer the buffer.
     * @param length the number of byte to digest; {@link #ALL} for all.
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs; or {@code length} is not
     * {@link #ALL} and reached to EOF before processing specified number of
     * bytes.
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

        if (length != ALL && length < 0) {
            throw new IllegalArgumentException("illegal length: " + length);
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
                } else {
                    throw new IOException("eof");
                }
            }
            digest.update(buffer, 0, read);
        }

        return digest.digest();
    }


    /**
     * Digest all({@code length}=={@link #ALL}) or specified
     * number({@code length}!={@link #ALL}) of bytes from {@code input} using
     * given {@code buffer}.
     *
     * @param digest digest
     * @param input input file
     * @param buffer the buffer.
     * @param length the number of byte to digest; {@link #ALL} for all.
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs; or {@code length} is not
     * {@link #ALL} and reached to EOF before processing specified number of
     * bytes.
     */
    public static byte[] digest(final MessageDigest digest, final File input,
                                final byte[] buffer, final long length)
        throws IOException {

        if (digest == null) {
            throw new NullPointerException("digest");
        }

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
     * Digest all({@code length}=={@link #ALL}) or specified
     * number({@code length}!={@link #ALL}) of bytes from {@code input} using
     * given {@code buffer}.
     *
     * @param digest digest
     * @param input input channel
     * @param buffer the buffer.
     * @param length the number of byte to digest; {@link #ALL} for all.
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs; or {@code length} is not
     * {@link #ALL} and reached to EOF before processing specified number of
     * bytes.
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

        if (length != ALL && length < 0) {
            throw new IllegalArgumentException("illegal length: " + length);
        }

        long count = 0L;
        for (int read; length == ALL || count < length; count += read) {
            buffer.clear(); // position -> 0, limit -> capacity
            if (length != ALL) {
                final long r = length - count;
                if (r < buffer.capacity()) {
                    buffer.limit((int) r);
                }
            }
            read = input.read(buffer);
            if (read == -1) {
                if (length == ALL) {
                    break;
                } else {
                    throw new IOException("eof");
                }
            }
            buffer.flip(); // limit -> position, position -> 0
            digest.update(buffer); // position -> limit
        }

        return digest.digest();
    }


    /**
     * Digest all({@code length}=={@link #ALL}) or specified
     * number({@code length}!={@link #ALL}) of bytes from {@code input} using
     * given {@code buffer}.
     *
     * @param digest digest
     * @param input input file
     * @param buffer the buffer.
     * @param length the number of byte to digest; {@link #ALL} for all.
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
     * Creates a new instance.
     */
    protected MessageDigests() {

        super();
    }


}
