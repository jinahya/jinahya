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


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedContentProducer implements ContentProducer {


    public BufferedContentProducer(final byte[] data) {
        this("application/octet-stream", data);
    }


    /**
     * Creates a new instance.
     *
     * @param type content type
     * @param data content data
     */
    public BufferedContentProducer(final String type, final byte[] data) {
        super();

        if (type == null) {
            throw new IllegalArgumentException("null type");
        }

        if (type.trim().isEmpty()) {
            throw new IllegalArgumentException("empty type");
        }

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }

        this.type = type;
        this.data = data;
    }


    @Override
    public String getType() {
        return type;
    }


    @Override
    public long getLength() {
        return data.length;
    }


    @Override
    public InputStream getData() throws IOException {
        return new ByteArrayInputStream(data);
    }


    private final String type;


    private final byte[] data;


}

