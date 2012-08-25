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
import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Embeddable
@MappedSuperclass
public class ContentLocator {


    private static final int OBJECT_NAME_BITS = 20;


    private static final long OBJECT_IN_A_CONTAINER =
        ((long) Math.pow(2.0d, OBJECT_NAME_BITS)) - 1L;


    /**
     * Constant for an unknown content type.
     */
    public static final String UNKNOWN_CONTENT_TYPE =
        "application/octet-stream";


    /**
     * Constant for an unknown content length.
     */
    public static final long UNKNOWN_CONTENT_LENGTH = -1L;


    /**
     * Returns container name prefix.
     *
     * @return container name prefix
     */
    public String getContainerNamePrefix() {
        return containerNamePrefix;
    }


    /**
     * Sets container name prefix.
     *
     * @param containerNamePrefix container name prefix
     */
    public void setContainerNamePrefix(final String containerNamePrefix) {
        this.containerNamePrefix = containerNamePrefix;
        if (this.containerNamePrefix == null) {
            this.containerNamePrefix = "";
        }
    }


    /**
     * Sets sequence number.
     *
     * @param sequenceNumber sequence number
     */
    public void setSequenceNumber(final long sequenceNumber) {

        containerName = containerNamePrefix
                        + Long.toString(sequenceNumber >>> OBJECT_NAME_BITS);

        objectName = Long.toString(sequenceNumber & OBJECT_IN_A_CONTAINER);
    }


    // ---------------------------------------------------------- CONTAINER_NAME
    /**
     * Returns container name.
     *
     * @return container name
     */
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
        this.contentLength = contentLength;
    }


    @Override
    public String toString() {
        return containerName + "|" + objectName + "|" + contentType + "|"
               + contentLength;
    }


    @Transient
    @XmlTransient
    private String containerNamePrefix = "";


    @Basic
    @Column(name = "CONTAINER_NAME")
    //@NotNull
    @XmlElement(required = true)
    private String containerName;


    @Basic
    @Column(name = "OBJECT_NAME")
    //@NotNull
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

