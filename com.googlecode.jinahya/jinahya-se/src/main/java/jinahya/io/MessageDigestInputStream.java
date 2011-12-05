/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package jinahya.io;


import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.MessageDigest;


/**
 * An OutputStream for Checksum.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MessageDigestInputStream extends FilterInputStream {


    /**
     * Creates a new instance.
     *
     * @param input underlying input
     * @param digest the digest to be updated
     */
    public MessageDigestInputStream(final InputStream input,
                                    final MessageDigest digest) {
        super(input);

        if (digest == null) {
            throw new NullPointerException("null digest");
        }

        this.digest = digest;
    }


    @Override
    public boolean markSupported() {
        return Boolean.FALSE.booleanValue();
    }


    @Override
    public int read() throws IOException {

        final int b = super.read();

        if (b != -1) {
            digest.update((byte) b);
        }

        return b;
    }


    @Override
    public int read(final byte[] b) throws IOException {

        final int read = super.read(b);

        if (read != -1) {
            digest.update(b, 0, read);
        }

        return read;
    }


    @Override
    public int read(final byte[] b, final int off, final int len)
        throws IOException {

        final int read = super.read(b, off, len);

        if (read != -1) {
            digest.update(b, off, read);
        }

        return read;
    }


    /**
     * Completes the hash computation by performing final operations such as
     * padding. The underlying digest is reset after this call is made.
     *
     * @return the array of bytes for the resulting hash value.
     * @see MessageDigest#digest()
     */
    public byte[] digest() {
        return digest.digest();
    }


    /**
     * Resets the underlying digest for further use.
     */
    public void resetDigest() {
        digest.reset();
    }


    /** messageDigest. */
    protected final MessageDigest digest;


}

