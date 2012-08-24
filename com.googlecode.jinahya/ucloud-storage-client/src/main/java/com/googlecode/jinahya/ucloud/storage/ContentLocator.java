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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class ContentLocator {


    private static final int OBJECT_NAME_BITS = 20;


    private static final long OBJECT_IN_A_CONTAINER =
        ((long) Math.pow(2.0d, OBJECT_NAME_BITS)) - 1L;


    private static final String UNKNOWN_CONTENT_TYPE =
        "application/octet-stream";


    private static final long UNKNOWN_CONTENT_LENGTH = -1L;


    /**
     * Creates a new instance.
     *
     * @param containerNamePrefix container prefix
     * @param sequence id
     *
     * @return a new instance
     */
    public static ContentLocator newInstance(
        final String containerNamePrefix, final long sequence) {

        final ContentLocator instance =
            new ContentLocator();

        instance.containerName =
            containerNamePrefix + Long.toString(sequence >>> OBJECT_NAME_BITS);

        instance.objectName =
            Long.toString(sequence & OBJECT_IN_A_CONTAINER);

        return instance;
    }


    // ---------------------------------------------------------- CONTAINER_NAME
    public String getContainerName() {
        return containerName;
    }


    // ------------------------------------------------------------- OBJECT_NAME
    /**
     * Returns object name.
     *
     * @return object name
     */
    public String getObjectName() {
        return objectName;
    }


    // ------------------------------------------------------------ CONTENT_TYPE
    /**
     * Returns content type.
     *
     * @return content type
     */
    public String getContentType() {
        return contentType;
    }


    /**
     * Sets content type.
     *
     * @param contentType content type
     */
    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }


    // ---------------------------------------------------------- CONTENT_LENGTH
    /**
     * Returns content length.
     *
     * @return content length
     */
    public long getContentLength() {
        return contentLength;
    }


    /**
     * Sets content length.
     *
     * @param contentLength content length
     */
    public void setContentLength(final long contentLength) {

        if (contentType == null) {
            throw new IllegalArgumentException("null contentType");
        }

        this.contentLength = contentLength;
    }


    @Override
    public String toString() {
        return containerName + "|" + objectName + "|" + contentType + "|"
               + contentLength;
    }


    @Basic
    @Column(name = "CONTAINER_NAME")
    @NotNull
    @XmlElement(required = true)
    private String containerName;


    @Basic
    @Column(name = "OBJECT_NAME")
    @NotNull
    @XmlElement(required = true)
    private String objectName;


    @Basic
    @Column(name = "CONTENT_TYPE")
    @NotNull
    @XmlElement(required = true)
    private String contentType = UNKNOWN_CONTENT_TYPE;


    @Basic
    @Column(name = "CONTENT_LENGTH")
    @Min(UNKNOWN_CONTENT_LENGTH)
    @XmlElement(required = true)
    private long contentLength = UNKNOWN_CONTENT_LENGTH;


}

