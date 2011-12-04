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

import java.util.zip.Checksum;


/**
 * An OutputStream for Checksum.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ChecksumInputStream extends FilterInputStream {


    /**
     * Creates a new instance.
     *
     * @param input underlying input
     * @param checksum checksum to be updated
     */
    public ChecksumInputStream(final InputStream input,
                               final Checksum checksum) {
        super(input);

        if (checksum == null) {
            throw new NullPointerException("null checksum");
        }

        this.checksum = checksum;
    }


    @Override
    public boolean markSupported() {
        return Boolean.FALSE.booleanValue();
    }


    @Override
    public int read() throws IOException {

        final int b = super.read();

        if (b != -1) {
            checksum.update(b);
        }

        return b;
    }


    @Override
    public int read(final byte[] b) throws IOException {

        final int read = super.read(b);

        if (read != -1) {
            checksum.update(b, 0, read);
        }

        return read;
    }


    @Override
    public int read(final byte[] b, final int off, final int len)
        throws IOException {

        final int read = super.read(b, off, len);

        if (read != -1) {
            checksum.update(b, off, read);
        }

        return read;
    }


    /**
     * Returns the underlying checksum's current checksum value.
     *
     * @return the underlying checksum's current checksum value.
     */
    public long getChecksumValue() {
        return checksum.getValue();
    }


    /**
     * Resets the underlying checksum to its initial value.
     */
    public void resetChecksum() {
        checksum.reset();
    }


    /**
     * Returns the checksum.
     *
     * @return the checksum.
     */
    public Checksum getChecksum() {
        return checksum;
    }


    /** checksum. */
    protected final Checksum checksum;


}

