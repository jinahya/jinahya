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
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author <a href="mailto:jinahya@gmail.com">Jin Kwon</a>
 * @param <T> cipher type
 */
public abstract class AbstractCipherWrapper<T> {


    /**
     *
     */
    protected static final int DEFAULT_BUFFER_LENGTH = 128;


    /**
     *
     * @param <T> cipher type
     */
    public static interface Initializer<T> {


        /**
         *
         * @param cipher
         * @param mode true for encryption, false for decryption
         * @throws Exception if any error occurs
         */
        void init(T cipher, boolean mode) throws Exception;
    }


    public AbstractCipherWrapper(final T cipher,
                                 final Initializer<T> initializer) {

        super();

        if (cipher == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (initializer == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        this.cipher = cipher;
        this.initializer = initializer;
    }


    /**
     *
     * @param in
     * @return
     * @throws Exception
     */
    public final byte[] encrypt(byte[] in) throws Exception {

        if (in == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        return encrypt(new ByteArrayInputStream(in));
    }


    /**
     *
     * @param in
     * @return
     * @throws Exception
     */
    public final byte[] encrypt(final InputStream in) throws Exception {

        if (in == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        encrypt(in, out);
        out.flush();

        return out.toByteArray();
    }


    /**
     *
     * @param in
     * @param out
     * @throws Exception
     */
    public final void encrypt(final byte[] in, final OutputStream out)
        throws Exception {

        if (in == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (out == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        encrypt(new ByteArrayInputStream(in), out);
    }


    /**
     *
     * @param in
     * @param out
     * @throws Exception
     */
    public final void encrypt(final InputStream in, final OutputStream out)
        throws Exception {

        if (in == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (out == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        initializer.init(cipher, true);

        process(cipher, in, out);
    }


    /**
     *
     * @param in
     * @return
     * @throws Exception
     */
    public final byte[] decrypt(final byte[] in) throws Exception {

        if (in == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        return decrypt(new ByteArrayInputStream(in));
    }


    /**
     *
     * @param in
     * @return
     * @throws Exception
     */
    public final byte[] decrypt(final InputStream in) throws Exception {

        if (in == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        decrypt(in, out);
        out.flush();

        return out.toByteArray();
    }


    /**
     *
     * @param in
     * @param out
     * @throws Exception
     */
    public final void decrypt(final byte[] in, final OutputStream out)
        throws Exception {

        if (in == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (out == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        decrypt(new ByteArrayInputStream(in), out);
    }


    /**
     *
     * @param in
     * @param out
     * @throws Exception
     */
    public final void decrypt(final InputStream in, final OutputStream out)
        throws Exception {

        if (in == null) {
            throw new IllegalArgumentException("param:0:: is null");
        }

        if (out == null) {
            throw new IllegalArgumentException("param:1:: is null");
        }

        initializer.init(cipher, false);
        process(cipher, in, out);
    }


    /**
     *
     * @param cipher
     * @param in
     * @param out
     * @throws Exception
     */
    protected abstract void process(T cipher, InputStream in, OutputStream out)
        throws Exception;


    /**
     *
     * @param buffer
     */
    public final synchronized void setBuffer(byte[] buffer) {

        if (buffer == null) {
            // OK
        }

        if (buffer.length == 0) {
            throw new IllegalArgumentException("zero length buffer");
        }

        this.buffer = buffer;
    }


    /**
     *
     * @return
     */
    protected final synchronized byte[] getBuffer() {
        if (buffer == null) {
            buffer = new byte[DEFAULT_BUFFER_LENGTH];
        }
        return buffer;
    }


    private T cipher;
    private Initializer<T> initializer;

    private transient byte[] buffer;
}
