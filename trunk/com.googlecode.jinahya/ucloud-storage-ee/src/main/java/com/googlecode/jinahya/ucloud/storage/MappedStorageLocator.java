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
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@MappedSuperclass
@XmlTransient
public class MappedStorageLocator {


    // ---------------------------------------------------------- @containerName
    /**
     * The minimum size of containerName.
     */
    public static final int CONTAINER_NAME_SIZE_MIN = 1;


    /**
     * The maximum size of containerName.
     */
    public static final int CONTAINER_NAME_SIZE_MAX = 63; // = 256 / 4 - 1?


    // ------------------------------------------------------------- @objectName
    /**
     * The minimum size of objectName.
     */
    public static final int OBJECT_NAME_SIZE_MIN = 1;


    /**
     * The maximum size of objectName.
     */
    public static final int OBJECT_NAME_SIZE_MAX = 255; // = 1024 / 4 - 1?


    // ------------------------------------------------------- @prefix/@sequence
    /**
     * The number of lower bits for object names.
     */
    private static final int OBJECT_NAME_SEQUENCE_BITS = 20;


    /**
     * The maximum number of objects in a container.
     */
    private static final long OBJECT_NAME_SEQUENCE_MASK =
        ((long) Math.pow(2.0d, OBJECT_NAME_SEQUENCE_BITS)) - 1L;


    /**
     * the print format length for container names.
     */
    private static final int CONTAINER_NAME_SEQUENCE_FORMAT_WIDTH =
        Long.toString((-1L >>> OBJECT_NAME_SEQUENCE_BITS)).length();


    /**
     * the print format for container names.
     */
    private static final String CONTAINER_NAME_SEQUENCE_FORMAT =
        "%0" + CONTAINER_NAME_SEQUENCE_FORMAT_WIDTH + "d";


    /**
     * The delimiter.
     */
    private static final String PREFIX_SEQUENCE_DELIMITER = "*";


    /**
     * The minimum length of {@code containerNamePrefix}.
     */
    public static final int CONTAINER_NAME_PREFIX_SIZE_MIN = 0;


    /**
     * The maximum length of {@code containerNamePrefix}.
     */
    public static final int CONTAINER_NAME_PREFIX_SIZE_MAX = 48;


    /**
     * the print format length for object names.
     */
    private static final int OBJECT_NAME_SEQUENCE_FORMAT_WIDTH =
        Long.toString(OBJECT_NAME_SEQUENCE_MASK).length();


    /**
     * the print format for object names.
     */
    private static final String OBJECT_NAME_SEQUENCE_FORMAT =
        "%0" + OBJECT_NAME_SEQUENCE_FORMAT_WIDTH + "d";


    /**
     * The minimum length of {@code objectPrefixName}.
     */
    public static final int OBJECT_NAME_PREFIX_SIZE_MIN = 0;


    /**
     * The maximum length of {@code objectNamePrefix}.
     */
    public static final int OBJECT_NAME_PREFIX_SIZE_MAX = 247;


    /**
     * Formats a containerName with given {@code containerNamePrefix} and
     * {@code sequenceNumber}.
     *
     * @param containerNamePrefix the container name prefix
     * @param sequenceNumber the sequence number
     *
     * @return a formatted container name
     */
    public static String formatContainerName(final String containerNamePrefix,
                                             final long sequenceNumber) {

        return ((containerNamePrefix == null ? "" : containerNamePrefix)
                + PREFIX_SEQUENCE_DELIMITER
                + (String.format(
                   CONTAINER_NAME_SEQUENCE_FORMAT,
                   (sequenceNumber >>> OBJECT_NAME_SEQUENCE_BITS))));
    }


    /**
     * Makes an objectName with given {@code objectNamePrefix} and
     * {@code sequenceNumber}.
     *
     * @param objectNamePrefix object name prefix
     * @param sequenceNumber sequence number
     *
     * @return an object name
     */
    public static String formatObjectName(final String objectNamePrefix,
                                          final long sequenceNumber) {

        return ((objectNamePrefix == null ? "" : objectNamePrefix)
                + PREFIX_SEQUENCE_DELIMITER
                + (String.format(
                   OBJECT_NAME_SEQUENCE_FORMAT,
                   (sequenceNumber & OBJECT_NAME_SEQUENCE_MASK))));
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

        if (containerName != null && containerName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty containerName");
        }

        this.containerName = containerName;
    }


    /**
     * Sets the {@code containerName} with given {@code containerNamePrefix} and
     * {@code sequenceNumber}.
     *
     * @param containerNamePrefix container name prefix
     * @param sequenceNumber sequence number
     */
    public void setContainerName(final String containerNamePrefix,
                                 final long sequenceNumber) {

        setContainerName(
            formatContainerName(containerNamePrefix, sequenceNumber));
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

        if (objectName != null && objectName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty objectName");
        }

        this.objectName = objectName;
    }


    /**
     * Sets {@code objectName} with given {@code objectNamePrefix} and
     * {@code sequenceNumber}.
     *
     * @param objectNamePrefix object name prefix
     * @param sequenceNumber sequence number
     */
    public void setObjectName(final String objectNamePrefix,
                              final long sequenceNumber) {

        setObjectName(formatObjectName(objectNamePrefix, sequenceNumber));
    }


    @Override
    public String toString() {
        return super.toString() + "?containerName=" + containerName
               + "&objectName=" + objectName;
    }


    /**
     * container name.
     */
    @Basic
    @Column(name = "CONTAINER_NAME")
    //@NotNull
    @Size(min = CONTAINER_NAME_SIZE_MIN, max = CONTAINER_NAME_SIZE_MAX)
    @XmlElement(nillable = true, required = true)
    private String containerName;


    /**
     * object name.
     */
    @Basic
    @Column(name = "OBJECT_NAME")
    //@NotNull
    @Size(min = OBJECT_NAME_SIZE_MIN, max = OBJECT_NAME_SIZE_MAX)
    @XmlElement(nillable = true, required = true)
    private String objectName;


}
