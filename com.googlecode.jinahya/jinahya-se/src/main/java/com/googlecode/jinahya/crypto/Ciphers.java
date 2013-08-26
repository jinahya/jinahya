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
     * A map of transformations and lists of available key sizes that every
     * implementation of the Java platform is required to support.
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
     * Updates and finishes a multi-part encryption or description operation.
     *
     * @param cipher the cipher
     * @param input the input
     * @param output the output
     * @param inbuf the input buffer
     * @param length the maximum number of bytes to process; any negative for
     * all available bytes.
     *
     * @return the actual number of bytes processed
     *
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException if this cipher is a block cipher, no
     * padding has been requested (only in encryption mode), and the total input
     * length of the data processed by this cipher is not a multiple of block
     * size; or if this encryption algorithm is unable to process the input data
     * provided. <i>Description copied from
     * {@link Cipher#doFinal(byte[], int)}.</i>
     * @throws BadPaddingException if this cipher is in decryption mode, and
     * (un)padding has been requested, but the decrypted data is not bounded by
     * the appropriate padding bytes. <i>Description copied from
     * {@link Cipher#doFinal(byte[], int)}.</i>
     *
     * @see Cipher#update(byte[], int, int, byte[], int)
     * @see Cipher#doFinal(byte[], int)
     */
    public static long doFinal(final Cipher cipher, final InputStream input,
                               final OutputStream output, final byte[] inbuf,
                               final long length)
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

        long count = 0L;

        byte[] outbuf = new byte[cipher.getOutputSize(inbuf.length)];
        int outlen;

        for (int inlen; length < 0L || count < length; count += inlen) {
            final int inoff = length < 0L ? inbuf.length
                              : (int) Math.min(inbuf.length, length - count);
            inlen = input.read(inbuf, 0, inoff);
            if (inlen == -1) {
                break;
            }
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

        return count;
    }


    /**
     * Updates and finishes a multi-part encryption or description.
     *
     * @param cipher the cipher
     * @param input the input
     * @param output the output
     * @param inbuf the input buffer
     * @param length the maximum number of bytes to process; any negative for
     * all available bytes.
     *
     * @return the actual number of bytes processed
     *
     * @throws IOException if an I/O error occurs
     * @throws IllegalBlockSizeException if this cipher is a block cipher, no
     * padding has been requested (only in encryption mode), and the total input
     * length of the data processed by this cipher is not a multiple of block
     * size; or if this encryption algorithm is unable to process the input data
     * provided. <i>Description copied from
     * {@link Cipher#doFinal(java.nio.ByteBuffer, java.nio.ByteBuffer)}.</i>
     * @throws BadPaddingException if there is insufficient space in the output
     * buffer. <i>Description copied from
     * {@link Cipher#doFinal(java.nio.ByteBuffer, java.nio.ByteBuffer)}.</i>
     *
     * @see Cipher#update(java.nio.ByteBuffer, java.nio.ByteBuffer)
     * @see Cipher#doFinal(java.nio.ByteBuffer, java.nio.ByteBuffer)
     */
    public static long doFinal(final Cipher cipher,
                               final ReadableByteChannel input,
                               final WritableByteChannel output,
                               final ByteBuffer inbuf, final long length)
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
            throw new IllegalArgumentException("inbuf.capacity == 0");
        }

        long count = 0L;

        ByteBuffer outbuf =
            ByteBuffer.allocate(cipher.getOutputSize(inbuf.capacity()));

        for (int read; length < 0L || count < length; count += read) {
            inbuf.clear(); // position -> 0, limit -> capacity
            if (length >= 0L) {
                final long remained = length - count;
                if (remained < inbuf.remaining()) {
                    inbuf.limit((int) remained);
                }
            }
            read = input.read(inbuf);
            if (read == -1) {
                break;
            }
            inbuf.flip(); // limit -> position, position -> 0
            outbuf.clear(); // position -> 0, limit -> capacity
            while (true) {
                try {
                    cipher.update(inbuf, outbuf);
                    break;
                } catch (ShortBufferException sbe) {
                    outbuf = ByteBuffer.allocate(outbuf.capacity() * 2);
                }
            }
            outbuf.flip(); // limit -> position, position -> 0
            while (outbuf.hasRemaining()) {
                output.write(outbuf);
            }
        }

        inbuf.limit(0); // input's remaining must be equal to block-size
        outbuf.clear(); // position -> 0, limit -> capacity
        while (true) {
            try {
                cipher.doFinal(inbuf, outbuf);
                break;
            } catch (ShortBufferException sbe) {
                outbuf = ByteBuffer.allocate(outbuf.capacity() * 2);
            }
        }
        outbuf.flip(); // limit -> position, position -> 0
        while (outbuf.hasRemaining()) {
            output.write(outbuf);
        }

        return count;
    }


    /**
     * Creates a new instance.
     */
    protected Ciphers() {

        super();
    }


}
