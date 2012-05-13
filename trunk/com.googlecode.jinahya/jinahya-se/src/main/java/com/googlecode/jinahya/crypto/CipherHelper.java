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
public class CipherHelper {


    /**
     *
     * @param cipher
     * @param input
     * @param output
     *
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static void doFinal(final Cipher cipher, final InputStream input,
                               final OutputStream output)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        doFinal(cipher, input, output, new byte[1024]);
    }


    /**
     *
     * @param cipher
     * @param input
     * @param output
     * @param inputBuf
     *
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static void doFinal(final Cipher cipher, final InputStream input,
                               final OutputStream output,
                               final byte[] inputBuf)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        if (cipher == null) {
            throw new NullPointerException("null cipher");
        }

        if (input == null) {
            throw new NullPointerException("null input");
        }

        if (output == null) {
            throw new NullPointerException("null os");
        }

        if (inputBuf == null) {
            throw new NullPointerException("null inputBuf");
        }
        if (inputBuf.length == 0) {
            throw new NullPointerException("empty inputBuf");
        }

        byte[] outputBuf = new byte[cipher.getOutputSize(inputBuf.length)];
        int outputLen;

        for (int inputLen = -1; (inputLen = input.read(outputBuf)) != -1;) {
            while (true) {
                try {
                    outputLen = cipher.update(
                        inputBuf, 0, inputLen, outputBuf, 0);
                    output.write(outputBuf, 0, outputLen);
                    break;
                } catch (ShortBufferException sbe) {
                    outputBuf = new byte[outputBuf.length * 2];
                }
            }
        }

        while (true) {
            try {
                outputLen = cipher.doFinal(outputBuf, 0);
                output.write(outputBuf, 0, outputLen);
                break;
            } catch (ShortBufferException sbe) {
                outputBuf = new byte[outputBuf.length * 2];
            }
        }
    }


    protected CipherHelper() {
        super();
    }


}

