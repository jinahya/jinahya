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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@Embeddable
@MappedSuperclass
public class EmbeddableContentLocator {


    /**
     * The number of lower bits for object names.
     */
    private static final int OBJECT_NAME_BITS = 20;


    /**
     * The maximum number of objects in a container.
     */
    private static final long OBJECTS_IN_A_CONTAINER =
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
     * The minimum size of containerName.
     */
    public static final int CONTAINER_NAME_SIZE_MIN = 1;


    /**
     * The maximum size of containerName.
     */
    public static final int CONTAINER_NAME_SIZE_MAX = 255;


    /**
     * The minimum size of objectName.
     */
    public static final int OBJECT_NAME_SIZE_MIN = 1;


    /**
     * The maximum size of objectName.
     */
    public static final int OBJECT_NAME_SIZE_MAX = 255;


    /**
     * The minimum size of contentType.
     */
    public static final int CONTENT_TYPE_SIZE_MIN = 1;


    /**
     * The maximum size of contentType.
     */
    public static final int CONTENT_TYPE_SIZE_MAX = 255;


    /**
     * The minimum value of contentLength.
     */
    public static final long CONTENT_LENGTH_MIN = UNKNOWN_CONTENT_LENGTH;


    /**
     * The maximum value of contentLength.
     */
    public static final long CONTENT_LENGTH_MAX = Long.MAX_VALUE;


    public static EmbeddableContentLocator newInstance(
        final String containerNamePrefix, final String objectNamePrefix,
        final long sequenceNumber) {

        final EmbeddableContentLocator instance = new EmbeddableContentLocator();

        instance.setContainerName(containerNamePrefix, sequenceNumber);
        instance.setObjectName(objectNamePrefix, sequenceNumber);

        return instance;
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


    /**
     * Sets containerName.
     *
     * @param containerName containerName
     */
    public void setContainerName(final String containerName) {
        this.containerName = containerName;
    }


    /**
     * Sets containerName with given
     * <code>containerNamePrefix</code> and
     * <code>sequenceNumber</code>.
     *
     * @param containerNamePrefix container name prefix
     * @param sequenceNumber sequence number
     */
    public void setContainerName(final String containerNamePrefix,
                                 final long sequenceNumber) {

        containerName =
            (containerNamePrefix == null ? "" : containerNamePrefix)
            + Long.toString(sequenceNumber >>> OBJECT_NAME_BITS);
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


    /**
     * Sets objectName.
     *
     * @param objectName objectName
     */
    public void setObjectName(final String objectName) {
        this.objectName = objectName;
    }


    /**
     * Sets objectName with given
     * <code>objectNamePrefix</code> and
     * <code>sequenceNumber</code>.
     *
     * @param objectNamePrefix object name prefix
     * @param sequenceNumber sequence number
     */
    public void setObjectName(final String objectNamePrefix,
                              final long sequenceNumber) {

        objectName = (objectNamePrefix == null ? "" : objectNamePrefix)
                     + Long.toString(sequenceNumber & OBJECTS_IN_A_CONTAINER);
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


    /**
     * container name.
     */
    @Basic
    @Column(name = "CONTAINER_NAME")
    @NotNull
    @Size(min = CONTAINER_NAME_SIZE_MIN, max = CONTAINER_NAME_SIZE_MAX)
    @XmlElement(required = true)
    private String containerName;


    /**
     * object name.
     */
    @Basic
    @Column(name = "OBJECT_NAME")
    @NotNull
    @Size(min = OBJECT_NAME_SIZE_MIN, max = OBJECT_NAME_SIZE_MAX)
    @XmlElement(required = true)
    private String objectName;


    /**
     * content type.
     */
    @Basic(optional = false)
    @Column(name = "CONTENT_TYPE", nullable = false)
    @Size(min = CONTENT_TYPE_SIZE_MIN, max = CONTENT_TYPE_SIZE_MAX)
    @XmlElement(required = true)
    private String contentType = UNKNOWN_CONTENT_TYPE;


    /**
     * content length.
     */
    @Basic(optional = false)
    @Column(name = "CONTENT_LENGTH", nullable = false)
    @Min(CONTENT_LENGTH_MIN)
    @Max(CONTENT_LENGTH_MAX)
    @XmlElement(required = true)
    private Long contentLength = UNKNOWN_CONTENT_LENGTH;


}

