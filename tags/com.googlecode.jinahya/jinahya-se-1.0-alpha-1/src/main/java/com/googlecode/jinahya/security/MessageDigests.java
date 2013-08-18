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


    public static final List<String> SUPPORTED_ALGORITHMS =
        Arrays.asList("MD5", "SHA-1", "SHA-256");


    /**
     * Digests all byte from {@code input}.
     *
     * @param digest digest
     * @param input input
     * @param buffer buffer
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] digest(final MessageDigest digest,
                                final InputStream input, final byte[] buffer)
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

        for (int read; (read = input.read(buffer)) != -1;) {
            digest.update(buffer, 0, read);
        }

        return digest.digest();
    }


    /**
     * Digests all bytes from {@code input}.
     *
     * @param digest digest
     * @param input input
     * @param buffer buffer
     *
     * @return digest result
     *
     * @throws IOException if an I/O error occurs.
     */
    public static byte[] digest(final MessageDigest digest,
                                final ReadableByteChannel input,
                                final ByteBuffer buffer)
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

        while (true) {
            buffer.clear(); // position -> 0, limit -> capacity
            if (input.read(buffer) == -1) {
                break;
            }
            buffer.flip(); // limit -> position, position -> 0
            digest.update(buffer); // position -> limit
        }

        return digest.digest();
    }


    protected MessageDigests() {

        super();
    }


}
