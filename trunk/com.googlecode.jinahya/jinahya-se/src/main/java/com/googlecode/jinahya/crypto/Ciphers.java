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
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class Ciphers {


    /**
     *
     * @param cipher
     * @param input
     * @param output
     * @param inbuf
     *
     * @throws IOException
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
     * Creates a new instance.
     */
    protected Ciphers() {
        super();
    }


}

