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


import java.io.IOException;
import java.io.OutputStream;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public interface ContentProducer {


    /**
     * Produces a content type.
     *
     * @return a content type
     */
    String getContentType();


    /**
     * Produces a content length.
     *
     * @return a content length
     */
    long getContentLength();


    /**
     * Produces a content data.
     *
     * @param contentData a content data
     *
     * @throws IOException if an I/O error occurs.
     */
    void getContentData(OutputStream contentData) throws IOException;


}
