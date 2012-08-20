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


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.validation.constraints.Min;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ContentLocatorEmbeddable {


    private static final int OBJECT_NAME_BITS = 20;


    private static final long MAXIMUM_OBJECTS_IN_A_CONTAINER = 1048575L;


    private static final String UNKNOWN_CONTENT_TYPE =
        "application/octet-stream";


    private static final long UNKNOWN_CONTENT_LENGTH = -1L;


    /**
     * Creates a new instance.
     *
     * @param containerPrefix container prefix
     * @param id id
     *
     * @return a new instance
     */
    public static ContentLocatorEmbeddable newInstance(
        String containerPrefix, final long id) {

        if (containerPrefix == null) {
            containerPrefix = "";
        }

        containerPrefix = containerPrefix.trim();

        final ContentLocatorEmbeddable instance =
            new ContentLocatorEmbeddable();

        instance.containerName =
            containerPrefix + Long.toString(id >>> OBJECT_NAME_BITS);

        instance.objectName =
            Long.toString(MAXIMUM_OBJECTS_IN_A_CONTAINER & id);

        return instance;
    }


    // ---------------------------------------------------------- CONTAINER_NAME
    public String getContainerName() {
        return containerName;
    }


    // ------------------------------------------------------------- OBJECT_NAME
    public String getObjectName() {
        return objectName;
    }


    // ------------------------------------------------------------ CONTENT_TYPE
    public String getContentType() {
        return contentType;
    }


    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }


    // ---------------------------------------------------------- CONTENT_LENGTH
    public long getContentLength() {
        return contentLength;
    }


    public void setContentLength(final long contentLength) {

        if (contentType == null) {
            throw new IllegalArgumentException("null contentType");
        }

        this.contentLength = contentLength;
    }


    @Override
    public String toString() {
        return containerName + "/" + objectName + "|" + contentType + "|"
               + contentLength;
    }


    @Basic
    @Column(name = "CONTAINER_NAME")
    private String containerName;


    @Basic
    @Column(name = "OBJECT_NAME")
    private String objectName;


    @Basic
    @Column(name = "CONTENT_TYPE")
    private String contentType = UNKNOWN_CONTENT_TYPE;


    @Basic
    @Column(name = "CONTENT_LENGTH")
    @Min(UNKNOWN_CONTENT_LENGTH)
    private long contentLength = UNKNOWN_CONTENT_LENGTH;


}

