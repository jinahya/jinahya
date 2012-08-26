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
public class BufferedContentDataProducer implements ContentDataProducer {


    /**
     * Creates a new instance.
     *
     * @param contentData content data
     */
    public BufferedContentDataProducer(final byte[] contentData) {
        super();

        if (contentData == null) {
            throw new IllegalArgumentException("null contentData");
        }

        this.contentData = contentData;
    }


    @Override
    public InputStream getContentData() throws IOException {
        return new ByteArrayInputStream(contentData);
    }


    /**
     * content data.
     */
    private final byte[] contentData;


}

