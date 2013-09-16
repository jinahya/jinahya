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


import java.io.InputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class DefaultContentProducer extends DefaultContentDataProducer
    implements ContentProducer {


    /**
     * Creates a new instance.
     *
     * @param contentType content type
     * @param contentLength content length
     * @param contentData content data
     */
    public DefaultContentProducer(final String contentType,
                                  final long contentLength,
                                  final InputStream contentData) {
        super(contentData);

        this.contentType = contentType;
        this.contentLength = contentLength;
    }


    @Override
    public String getContentType() {
        return contentType;
    }


    @Override
    public long getContentLength() {
        return contentLength;
    }


    /**
     * content type.
     */
    private final String contentType;


    /**
     * content length.
     */
    private final long contentLength;


}

