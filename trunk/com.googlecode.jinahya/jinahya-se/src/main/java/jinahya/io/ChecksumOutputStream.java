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

import java.util.zip.Checksum;


/**
 * An InputStream for Checksum.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ChecksumOutputStream extends FilterOutputStream {


    /**
     * Creates a new instance.
     *
     * @param output underlying output
     * @param checksum checksum to be updated
     */
    public ChecksumOutputStream(final OutputStream output,
                                final Checksum checksum) {
        super(output);

        if (checksum == null) {
            throw new NullPointerException("null checksum");
        }

        this.checksum = checksum;
    }


    @Override
    public void write(int b) throws IOException {

        super.write(b);

        checksum.update(b);
    }


    @Override
    public void write(final byte[] b) throws IOException {

        super.write(b);

        checksum.update(b, 0, b.length);
    }


    @Override
    public void write(final byte[] b, final int off, final int len)
        throws IOException {

        super.write(b, off, len);

        checksum.update(b, off, len);
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


    /** checksum. */
    protected final Checksum checksum;


}

