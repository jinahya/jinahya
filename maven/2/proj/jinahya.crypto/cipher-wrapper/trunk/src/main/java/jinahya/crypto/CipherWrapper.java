/*
 *  Copyright 2010 Jin Kwon.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package jinahya.crypto;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;



/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 */
public class CipherWrapper {


    /**
     *
     * @param input
     * @return
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] encrypt(final byte[] input)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        return encrypt(null, input);
    }


    /**
     *
     * @param params
     * @param input
     * @return
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] encrypt(final AlgorithmParameterSpec params,
                          final byte[] input)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        return doFinal(Cipher.ENCRYPT_MODE, params, input);
    }


    /**
     * 
     * @param input
     * @return
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] decrypt(final byte[] input)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        return decrypt(null, input);
    }


    /**
     *
     * @param params
     * @param input
     * @return
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] decrypt(final AlgorithmParameterSpec params,
                          final byte[] input)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        return doFinal(Cipher.DECRYPT_MODE, params, input);
    }


    /**
     *
     * @param opmode
     * @param params
     * @param input
     * @return
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private byte[] doFinal(final int opmode,
                           final AlgorithmParameterSpec params,
                           final byte[] input)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            doFinal(opmode, params, new ByteArrayInputStream(input), output);
            output.flush();
            return output.toByteArray();
        } finally {
            output.close();
        }
    }


    /**
     *
     * @param input
     * @param output
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public void encrypt(final InputStream input, final OutputStream output)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        encrypt(null, input, output);
    }


    /**
     *
     * @param params
     * @param input
     * @param output
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public void encrypt(final AlgorithmParameterSpec params,
                        final InputStream input, final OutputStream output)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        doFinal(Cipher.ENCRYPT_MODE, params, input, output);
    }


    /**
     *
     * @param input
     * @param output
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public void decrypt(final InputStream input, final OutputStream output)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        decrypt(null, input, output);
    }


    /**
     *
     * @pram params
     * @param input
     * @param output
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public void decrypt(final AlgorithmParameterSpec params,
                        final InputStream input, final OutputStream output)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        doFinal(Cipher.DECRYPT_MODE, params, input, output);
    }


    /**
     *
     * @param opmode
     * @param params
     * @param input
     * @param output
     * @throws InvalidKeyException
     * @throws IOException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private synchronized void doFinal(final int opmode,
                                      final AlgorithmParameterSpec params,
                                      final InputStream input,
                                      final OutputStream output)
        throws InvalidKeyException, IOException, IllegalBlockSizeException,
               BadPaddingException {

        cipher.init(opmode, key);

        final byte[] inputBuffer = new byte[inputBufferSize];
        byte[] outputBuffer = new byte[initialOutputBufferSize];

        outer:
        for (int read = -1; (read = input.read(inputBuffer)) != -1;) {
            while (true) {
                try {
                    final int length =
                        cipher.update(inputBuffer, 0, read, outputBuffer, 0);
                    output.write(outputBuffer, 0, length);
                    continue outer;
                } catch (ShortBufferException sbe) {
                    final int outputBufferSize =
                        (outputBuffer.length + outputBufferIncrementSize);
                    outputBuffer = new byte[outputBufferSize];
                }
            }
        }

        while (true) {
            try {
                final int length =
                    cipher.doFinal(inputBuffer, 0, 0, outputBuffer, 0);
                output.write(outputBuffer, 0, length);
                break;
            } catch (ShortBufferException sbe) {
                outputBuffer = new byte[outputBuffer.length
                    + outputBufferIncrementSize];
            }
        }

    }


    Key key;
    Cipher cipher;

    int inputBufferSize;
    int initialOutputBufferSize;
    int outputBufferIncrementSize;
}
