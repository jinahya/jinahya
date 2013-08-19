/*
 * Copyright 2012 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.crypto;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;


/**
 * A utility class for {@link Cipher}s.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Ciphers {


    /**
     * Transformations and keysizes that every implementation of the Java
     * platform is required to support.
     */
    public static final Map<String, List<Integer>> SUPPORTED_TRANSFORMATIONS;


    static {
        final Map<String, List<Integer>> m =
            new HashMap<String, List<Integer>>();
        m.put("AES/CBC/NoPadding", Arrays.asList(128));
        m.put("AES/CBC/PKCS5Padding", Arrays.asList(128));
        m.put("AES/ECB/NoPadding", Arrays.asList(128));
        m.put("AES/ECB/PKCS5Padding", Arrays.asList(128));
        m.put("DES/CBC/NoPadding", Arrays.asList(56));
        m.put("DES/CBC/PKCS5Padding", Arrays.asList(56));
        m.put("DES/ECB/NoPadding", Arrays.asList(56));
        m.put("DES/ECB/PKCS5Padding", Arrays.asList(56));
        m.put("DESede/CBC/NoPadding", Arrays.asList(168));
        m.put("DESede/CBC/PKCS5Padding", Arrays.asList(168));
        m.put("DESede/ECB/NoPadding", Arrays.asList(168));
        m.put("DESede/ECB/PKCS5Padding", Arrays.asList(168));
        m.put("RSA/ECB/PKCS1Padding", Arrays.asList(1024, 2048));
        m.put("RSA/ECB/OAEPWithSHA-1AndMGF1Padding",
              Arrays.asList(1024, 2048));
        m.put("RSA/ECB/OAEPWithSHA-256AndMGF1Padding",
              Arrays.asList(1024, 2048));
        SUPPORTED_TRANSFORMATIONS = Collections.unmodifiableMap(m);
    }


    /**
     * Encrypts or decrypts all bytes from {@code input} and writes output to
     * {@code output}.
     *
     * @param cipher the cipher
     * @param input input
     * @param output output
     * @param inbuf the buffer
     *
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static void doFinal(final Cipher cipher, final InputStream input,
                               final OutputStream output, final byte[] inbuf)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        if (cipher == null) {
            throw new IllegalArgumentException("cipher");
        }

        if (input == null) {
            throw new IllegalArgumentException("input");
        }

        if (output == null) {
            throw new IllegalArgumentException("output");
        }

        if (inbuf == null) {
            throw new IllegalArgumentException("inbuf");
        }

        if (inbuf.length == 0) {
            throw new IllegalArgumentException("inbuf.length == 0");
        }

        byte[] outbuf = new byte[cipher.getOutputSize(inbuf.length)];

        int outlen;

        for (int inlen; (inlen = input.read(inbuf)) != -1;) {
            while (true) {
                try {
                    outlen = cipher.update(inbuf, 0, inlen, outbuf, 0);
                    break;
                } catch (ShortBufferException sbe) {
                    outbuf = new byte[outbuf.length * 2];
                }
            }
            output.write(outbuf, 0, outlen);
        }

        while (true) {
            try {
                outlen = cipher.doFinal(outbuf, 0);
                break;
            } catch (ShortBufferException sbe) {
                outbuf = new byte[outbuf.length * 2];
            }
        }
        output.write(outbuf, 0, outlen);
    }


    /**
     * Encrypts or decrypts all bytes from {@code input} and writes result to
     * {@code output}.
     *
     * @param cipher the cipher
     * @param input input
     * @param output output
     * @param inbuf input buffer
     *
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static void doFinal(final Cipher cipher,
                               final ReadableByteChannel input,
                               final WritableByteChannel output,
                               final ByteBuffer inbuf)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        if (cipher == null) {
            throw new IllegalArgumentException("cipher");
        }

        if (input == null) {
            throw new IllegalArgumentException("input");
        }

        if (output == null) {
            throw new IllegalArgumentException("output");
        }

        if (inbuf == null) {
            throw new NullPointerException("inbuf");
        }

        if (inbuf.capacity() == 0) {
            throw new IllegalArgumentException(
                "inbuf.capacity(" + inbuf.capacity() + ") == 0");
        }

        ByteBuffer outbuf =
            ByteBuffer.allocate(cipher.getOutputSize(inbuf.capacity()));

        while (true) {
            inbuf.clear(); // position -> 0, limit -> capacity
            if (input.read(inbuf) == -1) {
                break;
            }
            inbuf.flip(); // limit -> position, position -> 0
            outbuf.clear(); // position -> 0, limit -> capacity
            while (true) {
                try {
                    cipher.update(inbuf, outbuf);
                    outbuf.flip(); // limit -> position, position -> 0
                    while (outbuf.hasRemaining()) {
                        output.write(outbuf);
                    }
                    break;
                } catch (ShortBufferException sbe) {
                    outbuf = ByteBuffer.allocate(outbuf.capacity() * 2);
                }
            }
        }

        inbuf.limit(0); // input's remaining must be equal to block-size
        outbuf.clear(); // position -> 0, limit -> capacity
        while (true) {
            try {
                cipher.doFinal(inbuf, outbuf);
                outbuf.flip(); // limit -> position, position -> 0
                while (outbuf.hasRemaining()) {
                    output.write(outbuf);
                }
                break;
            } catch (ShortBufferException sbe) {
                outbuf = ByteBuffer.allocate(outbuf.capacity() * 2);
            }
        }
    }


    /**
     * Creates a new instance.
     */
    protected Ciphers() {

        super();
    }


}
