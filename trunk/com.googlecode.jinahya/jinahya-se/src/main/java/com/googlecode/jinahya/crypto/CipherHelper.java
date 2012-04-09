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


    public static void doFinal(final Cipher cipher, final InputStream is,
                               final OutputStream os)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        doFinal(cipher, is, os, new byte[1024]);
    }


    public static void doFinal(final Cipher cipher, final InputStream is,
                               final OutputStream os, final byte[] input)
        throws IOException, IllegalBlockSizeException, BadPaddingException {

        if (cipher == null) {
            throw new NullPointerException("null cipher");
        }

        if (is == null) {
            throw new NullPointerException("null is");
        }

        if (os == null) {
            throw new NullPointerException("null os");
        }

        if (input == null) {
            throw new NullPointerException("null input");
        }

        if (input.length == 0) {
            throw new NullPointerException("empty input");
        }

        byte[] output = new byte[cipher.getOutputSize(input.length)];
        int outputLen;

        for (int inputLen = -1; (inputLen = is.read(output)) != -1;) {
            while (true) {
                try {
                    outputLen = cipher.update(input, 0, inputLen, output, 0);
                    os.write(output, 0, outputLen);
                    break;
                } catch (ShortBufferException sbe) {
                    output = new byte[output.length * 2];
                }
            }
        }

        while (true) {
            try {
                outputLen = cipher.doFinal(output, 0);
                os.write(output, 0, outputLen);
                break;
            } catch (ShortBufferException sbe) {
                output = new byte[output.length * 2];
            }
        }
    }


    public CipherHelper() {
        super();
    }


}

