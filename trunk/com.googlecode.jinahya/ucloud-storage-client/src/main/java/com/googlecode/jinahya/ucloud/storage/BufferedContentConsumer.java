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


package com.googlecode.jinahya.ucloud.storage;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedContentConsumer implements ContentConsumer {


    /**
     * Returns type.
     *
     * @return type
     */
    public String getType() {
        return type;
    }


    @Override
    public void setType(final String type) {

        if (type == null) {
            throw new IllegalArgumentException("null type");
        }

        this.type = type;
    }


    /**
     * Returns length.
     *
     * @return length
     */
    public long getLength() {
        return length;
    }


    @Override
    public void setLength(final long length) {

        if (length < -1L) {
            throw new IllegalArgumentException("length(" + length + ") < -1L");
        }

        this.length = length;
    }


    @Override
    public void setData(final InputStream data) throws IOException {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final byte[] buffer = new byte[8192];
        for (int read = -1; (read = data.read(buffer)) != -1;) {
            baos.write(buffer, 0, read);
        }
        baos.flush();

        this.data = baos.toByteArray();
    }


    /**
     * Returns buffered data.
     *
     * @return data
     */
    public byte[] getData() {
        return data;
    }


    /**
     * type.
     */
    private String type;


    /**
     * length.
     */
    private long length;


    /**
     * data.
     */
    private byte[] data;


}

