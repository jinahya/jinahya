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
import java.security.InvalidAlgorithmParameterException;
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
     * Creates a new instance.
     *
     * @param cipher the cipher to be wrapped.
     * @param inputBufferSize input buffer size
     * @param initialOutputBufferSize initial output buffer size
     * @param outputBufferIncrementSize output buffer increment size
     */
    public CipherWrapper(final Cipher cipher, final int inputBufferSize,
                         final int initialOutputBufferSize,
                         final int outputBufferIncrementSize) {

        super();

        if (cipher == null) {
            throw new IllegalArgumentException(
                "param:0:cipher:" + Cipher.class + " is null");
        }

        if (inputBufferSize <= 0) {
            throw new IllegalArgumentException(
                "param:1:inputBufferSize:" + Integer.TYPE + "("
                + inputBufferSize + ") <= 0");
        }

        if (initialOutputBufferSize <= 0) {
            throw new IllegalArgumentException(
                "param:1:initialOutputBufferSize:" + Integer.TYPE + "("
                + initialOutputBufferSize + ") <= 0");
        }

        if (outputBufferIncrementSize <= 0) {
            throw new IllegalArgumentException(
                "param:1:outputBufferIncrementSize:" + Integer.TYPE + "("
                + outputBufferIncrementSize + ") <= 0");
        }


        this.cipher = cipher;

        this.inputBuffer = new byte[inputBufferSize];
        this.outputBufferSize = initialOutputBufferSize;
        this.outputBufferIncrementSize = outputBufferIncrementSize;
    }


    /**
     * Encrypts given <code>input</code>.
     *
     * @param key the encryption key.
     * @param params the algorithm parameters.
     * @param input input
     * @return encrypted output
     * @throws InvalidKeyException if the given key is inappropriate for
     *         initializing this cipher, or if the wrapped cipher is being
     *         initialized for decryption and requires algorithm parameters that
     *         cannot be determined from the given key, or if the given key has
     *         a keysize that exceeds the maximum allowable keysize.
     * @throws InvalidAlgorithmParameterException if the given algorithm
     *         parameters are inappropriate for the wrapped cipher, or the
     *         wrapped cipher is being initialized for decryption and requires
     *         algorithm parameters and params is null, or the given algorithm
     *         parameters imply a cryptographic strength that would exceed the
     *         legal limits.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException if the wrapped cipher is a block
     *         cipher, no padding has been requested (only in encryption mode),
     *         and the total input length of the data processed by this cipher
     *         is not a multiple of block size.
     * @throws BadPaddingException if the wrapped cipher is in decryption mode,
     *         and (un)padding has been requested, but the decrypted data is not
     *         bounded by the appropriate padding bytes
     */
    public byte[] encrypt(final Key key, final AlgorithmParameterSpec params,
                          final byte[] input)
        throws InvalidKeyException, InvalidAlgorithmParameterException,
               IOException, IllegalBlockSizeException, BadPaddingException {

        return doFinal(Cipher.ENCRYPT_MODE, key, params, input);
    }


    /**
     * Decrypts given <code>input</code>.
     *
     * @param key the encryption key.
     * @param params the algorithm parameters.
     * @param input input to be decrypted.
     * @return an array of decrypted bytes.
     * @throws InvalidKeyException if the given key is inappropriate for
     *         initializing this cipher, or if the wrapped cipher is being
     *         initialized for decryption and requires algorithm parameters that
     *         cannot be determined from the given key, or if the given key has
     *         a keysize that exceeds the maximum allowable keysize.
     * @throws InvalidAlgorithmParameterException if the given algorithm
     *         parameters are inappropriate for the wrapped cipher, or the
     *         wrapped cipher is being initialized for decryption and requires
     *         algorithm parameters and params is null, or the given algorithm
     *         parameters imply a cryptographic strength that would exceed the
     *         legal limits.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException if the wrapped cipher is a block
     *         cipher, no padding has been requested (only in encryption mode),
     *         and the total input length of the data processed by this cipher
     *         is not a multiple of block size.
     * @throws BadPaddingException if the wrapped cipher is in decryption mode,
     *         and (un)padding has been requested, but the decrypted data is not
     *         bounded by the appropriate padding bytes
     */
    public byte[] decrypt(final Key key, final AlgorithmParameterSpec params,
                          final byte[] input)
        throws InvalidKeyException, InvalidAlgorithmParameterException,
               IOException, IllegalBlockSizeException, BadPaddingException {

        return doFinal(Cipher.DECRYPT_MODE, key, params, input);
    }


    /**
     * DoFinal.
     *
     * @param opmode the operation mode of the wrapped cipher (this is one of
     *        the following: ENCRYPT_MODE or DECRYPT_MODE)
     * @param key the encryption key.
     * @param params the algorithm paramters.
     * @param input input to be processed.
     * @return output output
     * @throws InvalidKeyException if the given key is inappropriate for
     *         initializing this cipher, or if the wrapped cipher is being
     *         initialized for decryption and requires algorithm parameters that
     *         cannot be determined from the given key, or if the given key has
     *         a keysize that exceeds the maximum allowable keysize.
     * @throws InvalidAlgorithmParameterException if the given algorithm
     *         parameters are inappropriate for the wrapped cipher, or the
     *         wrapped cipher is being initialized for decryption and requires
     *         algorithm parameters and params is null, or the given algorithm
     *         parameters imply a cryptographic strength that would exceed the
     *         legal limits.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException if the wrapped cipher is a block
     *         cipher, no padding has been requested (only in encryption mode),
     *         and the total input length of the data processed by this cipher
     *         is not a multiple of block size.
     * @throws BadPaddingException if the wrapped cipher is in decryption mode,
     *         and (un)padding has been requested, but the decrypted data is not
     *         bounded by the appropriate padding bytes
     */
    private byte[] doFinal(final int opmode, final Key key,
                           final AlgorithmParameterSpec params,
                           final byte[] input)
        throws InvalidKeyException, InvalidAlgorithmParameterException,
               IOException, IllegalBlockSizeException, BadPaddingException {

        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            doFinal(opmode, key, params, new ByteArrayInputStream(input),
                    output);
            output.flush();
            return output.toByteArray();
        } finally {
            output.close();
        }
    }


    /**
     * Encrypts given <code>input</code>.
     *
     * @param key the encryption key.
     * @param params the algorithm paramters.
     * @param input input to be processed.
     * @param output processed output.
     * @throws InvalidKeyException if the given key is inappropriate for
     *         initializing this cipher, or if the wrapped cipher is being
     *         initialized for decryption and requires algorithm parameters that
     *         cannot be determined from the given key, or if the given key has
     *         a keysize that exceeds the maximum allowable keysize.
     * @throws InvalidAlgorithmParameterException if the given algorithm
     *         parameters are inappropriate for the wrapped cipher, or the
     *         wrapped cipher is being initialized for decryption and requires
     *         algorithm parameters and params is null, or the given algorithm
     *         parameters imply a cryptographic strength that would exceed the
     *         legal limits.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException if the wrapped cipher is a block
     *         cipher, no padding has been requested (only in encryption mode),
     *         and the total input length of the data processed by this cipher
     *         is not a multiple of block size.
     * @throws BadPaddingException if the wrapped cipher is in decryption mode,
     *         and (un)padding has been requested, but the decrypted data is not
     *         bounded by the appropriate padding bytes
     */
    public void encrypt(final Key key, final AlgorithmParameterSpec params,
                        final InputStream input, final OutputStream output)
        throws InvalidKeyException, InvalidAlgorithmParameterException,
               IOException, IllegalBlockSizeException, BadPaddingException {

        doFinal(Cipher.ENCRYPT_MODE, key, params, input, output);
    }


    /**
     * Decrypts given <code>input</code>.
     *
     * @param key the encryption key.
     * @pram params the algorithm paramters.
     * @param input input to be processed.
     * @param output processed output.
     * @throws InvalidKeyException if the given key is inappropriate for
     *         initializing this cipher, or if the wrapped cipher is being
     *         initialized for decryption and requires algorithm parameters that
     *         cannot be determined from the given key, or if the given key has
     *         a keysize that exceeds the maximum allowable keysize.
     * @throws InvalidAlgorithmParameterException if the given algorithm
     *         parameters are inappropriate for the wrapped cipher, or the
     *         wrapped cipher is being initialized for decryption and requires
     *         algorithm parameters and params is null, or the given algorithm
     *         parameters imply a cryptographic strength that would exceed the
     *         legal limits.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException if the wrapped cipher is a block
     *         cipher, no padding has been requested (only in encryption mode),
     *         and the total input length of the data processed by this cipher
     *         is not a multiple of block size.
     * @throws BadPaddingException if the wrapped cipher is in decryption mode,
     *         and (un)padding has been requested, but the decrypted data is not
     *         bounded by the appropriate padding bytes
     */
    public void decrypt(final Key key, final AlgorithmParameterSpec params,
                        final InputStream input, final OutputStream output)
        throws InvalidKeyException, InvalidAlgorithmParameterException,
               IOException, IllegalBlockSizeException, BadPaddingException {

        doFinal(Cipher.DECRYPT_MODE, key, params, input, output);
    }


    /**
     * Encrypts or Decrypts.
     *
     * @param opmode the operation mode of the wrapped cipher (this is one of
     *        the following: ENCRYPT_MODE or DECRYPT_MODE)
     * @param key the encryption key.
     * @param params the algorithm parameters.
     * @param input input to be processed
     * @param output processed output
     * @throws InvalidKeyException if the given key is inappropriate for
     *         initializing the wrapped cipher, or its keysize exceeds the
     *         maximum allowable keysize.
     * @throws InvalidAlgorithmParameterException if the given algorithm
     *         parameters are inappropriate for the wrapped cipher, or the
     *         wrapped cipher is being initialized for decryption and requires
     *         algorithm parameters and params is null, or the given algorithm
     *         parameters imply a cryptographic strength that would exceed the
     *         legal limits.
     * @throws IOException if an I/O error occurs.
     * @throws IllegalBlockSizeException if the wrapped cipher is a block
     *         cipher, no padding has been requested (only in encryption mode),
     *         and the total input length of the data processed by this cipher
     *         is not a multiple of block size.
     * @throws BadPaddingException if the wrapped cipher is in decryption mode,
     *         and (un)padding has been requested, but the decrypted data is not
     *         bounded by the appropriate padding bytes.
     */
    private synchronized void doFinal(final int opmode, final Key key,
                                      final AlgorithmParameterSpec params,
                                      final InputStream input,
                                      final OutputStream output)
        throws InvalidKeyException, InvalidAlgorithmParameterException,
               IOException, IllegalBlockSizeException, BadPaddingException {

        /* InvalidKeyException, InvalidAlgorithmParameterException */
        cipher.init(opmode, key, params);

        byte[] outputBuffer = new byte[outputBufferSize];

        outer:
        for (int read = -1; (read = input.read(inputBuffer)) != -1;) {
            while (true) {
                try {
                    final int length =
                        cipher.update(inputBuffer, 0, read, outputBuffer, 0);
                    output.write(outputBuffer, 0, length);
                    continue outer;
                } catch (ShortBufferException sbe) {
                    outputBufferSize += outputBufferIncrementSize;
                    outputBuffer = new byte[outputBufferSize];
                }
            }
        }

        while (true) {
            try {
                /* IllegalBlockSizeException, BadPaddingException */
                final int length =
                    cipher.doFinal(inputBuffer, 0, 0, outputBuffer, 0);
                output.write(outputBuffer, 0, length);
                break;
            } catch (ShortBufferException sbe) {
                outputBufferSize += outputBufferIncrementSize;
                outputBuffer = new byte[outputBufferSize];
            }
        }

    }


    private Cipher cipher;

    private byte[] inputBuffer;

    private int outputBufferSize;
    private int outputBufferIncrementSize;
}
