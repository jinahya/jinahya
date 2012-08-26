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


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class BufferedContentProducer extends BufferedContentDataProducer
    implements ContentProducer {


    /**
     * Creates a new instance.
     *
     * @param contentData content data
     */
    public BufferedContentProducer(final byte[] contentData) {
        this(null, contentData);
    }


    /**
     * Creates a new instance.
     *
     * @param contentType content type
     * @param contentData content data
     */
    public BufferedContentProducer(final String contentType,
                                   final byte[] contentData) {
        super(contentData);

        this.contentType = contentType;
        this.contentLength = contentData.length;
    }


    @Override
    public String getContentType() {
        return contentType;
    }


    @Override
    public long getContentLength() {
        return contentLength;
    }


    private final String contentType;


    private final long contentLength;


}

