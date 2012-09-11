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


import java.io.OutputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class DefaultContentConsumer extends DefaultContentDataConsumer
    implements ContentConsumer {


    /**
     * Creates a new instance.
     *
     * @param contentData content data
     */
    public DefaultContentConsumer(final OutputStream contentData) {
        super(contentData);
    }


    /**
     * Returns content type.
     *
     * @return content type.
     */
    public String getContentType() {
        return contentType;
    }


    @Override
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }


    /**
     * Returns content length.
     *
     * @return content length
     */
    public long getContentLength() {
        return contentLength;
    }


    @Override
    public void setContentLength(final long contentLength) {
        this.contentLength = contentLength;
    }


    /**
     * content type.
     */
    private String contentType;


    /**
     * content length.
     */
    private long contentLength;


}

