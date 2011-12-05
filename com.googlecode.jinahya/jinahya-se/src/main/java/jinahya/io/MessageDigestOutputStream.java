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


import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.security.MessageDigest;


/**
 * An InputStream for Checksum.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class MessageDigestOutputStream extends FilterOutputStream {


    /**
     * Creates a new instance.
     *
     * @param output underlying output
     * @param digest the digest to be updated
     */
    public MessageDigestOutputStream(final OutputStream output,
                                     final MessageDigest digest) {
        super(output);

        if (digest == null) {
            throw new NullPointerException("null digest");
        }

        this.digest = digest;
    }


    @Override
    public void write(int b) throws IOException {

        super.write(b);

        digest.update((byte) b);
    }


    @Override
    public void write(final byte[] b) throws IOException {

        super.write(b);

        digest.update(b, 0, b.length);
    }


    @Override
    public void write(final byte[] b, final int off, final int len)
        throws IOException {

        super.write(b, off, len);

        digest.update(b, off, len);
    }


    /**
     * Completes the hash computation by performing final operations such as
     * padding. The underlying digest is reset after this call is made.
     *
     * @return the array of bytes for the resulting hash value.
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


    /** checksum. */
    protected final MessageDigest digest;


}

