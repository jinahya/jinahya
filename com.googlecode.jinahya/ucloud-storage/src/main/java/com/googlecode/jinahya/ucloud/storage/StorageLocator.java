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


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@MappedSuperclass
public class StorageLocator {


    /**
     * Creates a new instance of given
     * <code>storageLocatorType</code>.
     *
     * @param <L> storage locator type parameter
     * @param storageLocatorType storage locator type
     * @param containerNamePrefix container name prefix
     * @param objectNamePrefix object name prefix
     * @param sequenceNumber sequence number
     * @return a new instance of <code>storageLocatorType</code>.
     */
    public static <L extends StorageLocator> L newInstance(
        final Class<L> storageLocatorType, final String containerNamePrefix,
        final String objectNamePrefix, final long sequenceNumber) {

        if (storageLocatorType == null) {
            throw new IllegalArgumentException("null storageLocatorType");
        }

        try {
            final Constructor<L> constructor =
                storageLocatorType.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                final L storageLocator = constructor.newInstance();
                try {
                    final Field field = StorageLocator.class.
                        getDeclaredField("containerName");
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    field.set(storageLocator, getContainerName(
                        containerNamePrefix, sequenceNumber));
                } catch (NoSuchFieldException nsfe) {
                    throw new RuntimeException(nsfe);
                }
                try {
                    final Field field = StorageLocator.class.
                        getDeclaredField("objectName");
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    field.set(storageLocator, getObjectName(
                        objectNamePrefix, sequenceNumber));
                } catch (NoSuchFieldException nsfe) {
                    throw new RuntimeException(nsfe);
                }
                return storageLocator;
            } catch (InstantiationException ie) {
                throw new RuntimeException(ie);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            } catch (InvocationTargetException ite) {
                throw new RuntimeException(ite);
            }
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }


    // ------------------------------------------------------------ @contentType
    /**
     * Constant for an unknown content type.
     */
    public static final String UNKNOWN_CONTENT_TYPE =
        "application/octet-stream";


    /**
     * The minimum size of contentType.
     */
    public static final int CONTENT_TYPE_SIZE_MIN = 3; // a/b


    /**
     * The maximum size of contentType.
     */
    public static final int CONTENT_TYPE_SIZE_MAX = 255;


    // ---------------------------------------------------------- @contentLength
    /**
     * Constant for an unknown content length.
     */
    public static final long UNKNOWN_CONTENT_LENGTH = -1L;


    /**
     * The minimum value of contentLength.
     */
    public static final long CONTENT_LENGTH_MIN = UNKNOWN_CONTENT_LENGTH;


//    /**
//     * The maximum value of contentLength.
//     */
//    public static final long CONTENT_LENGTH_MAX = Long.MAX_VALUE;
    // ----------------------------------------------------------- @lastModified
    /**
     * Constant for an unknown content length.
     */
    public static final long UNKNOWN_LAST_MODIFIED = 0L;


    /**
     * The minimum value of contentLength.
     */
    public static final long LAST_MODIFIED_MIN = UNKNOWN_LAST_MODIFIED;


//    /**
//     * The maximum value of contentLength.
//     */
//    public static final long LAST_MODIFIED_MAX = Long.MAX_VALUE;
    // ---------------------------------------------------------- @containerName
    /**
     * The minimum size of containerName.
     */
    public static final int CONTAINER_NAME_SIZE_MIN = 1;


    /**
     * The maximum size of containerName.
     */
    public static final int CONTAINER_NAME_SIZE_MAX = 255;


    // ------------------------------------------------------------- @objectName
    /**
     * The minimum size of objectName.
     */
    public static final int OBJECT_NAME_SIZE_MIN = 1;


    /**
     * The maximum size of objectName.
     */
    public static final int OBJECT_NAME_SIZE_MAX = 255; // 1024?


    // ------------------------------------------------------- @prefix/@sequence
    /**
     * The delimiter.
     */
    private static final char PREFIX_SEQUENCE_DELIMITER = '*';


    /**
     * The number of lower bits for object names.
     */
    private static final int OBJECT_NAME_BITS = 20;


    /**
     * The maximum number of objects in a container.
     */
    private static final long OBJECT_NAME_MASK =
        ((long) Math.pow(2.0d, OBJECT_NAME_BITS)) - 1L;


    /**
     * the print format length for container names.
     */
    private static final int CONTAINER_NAME_FORMAT_WIDTH =
        Long.toString((-1L >>> OBJECT_NAME_BITS)).length();


    /**
     * the print format for container names.
     */
    private static final String CONTAINER_NAME_FORMAT =
        "%0" + CONTAINER_NAME_FORMAT_WIDTH + "d";


    /**
     * the print format length for object names.
     */
    private static final int OBJECT_NAME_FORMAT_WIDTH =
        Long.toString((Long.MAX_VALUE & OBJECT_NAME_MASK)).length();


    /**
     * the print format for object names.
     */
    private static final String OBJECT_NAME_FORMAT =
        "%0" + OBJECT_NAME_FORMAT_WIDTH + "d";


    /**
     * Makes a containerName with given
     * <code>containerNamePrefix</code> and
     * <code>sequenceNumber</code>.
     *
     * @param containerNamePrefix container name prefix
     * @param sequenceNumber sequence number
     *
     * @return a container name
     */
    private static String getContainerName(final String containerNamePrefix,
                                           final long sequenceNumber) {

        return ((containerNamePrefix == null
                 || containerNamePrefix.trim().isEmpty())
                ? "" : (containerNamePrefix.trim() + PREFIX_SEQUENCE_DELIMITER))
               + (String.format(CONTAINER_NAME_FORMAT,
                                (sequenceNumber >>> OBJECT_NAME_BITS)));
    }


    /**
     * Makes an objectName with given
     * <code>objectNamePrefix</code> and
     * <code>sequenceNumber</code>.
     *
     * @param objectNamePrefix object name prefix
     * @param sequenceNumber sequence number
     *
     * @return an object name
     */
    private static String getObjectName(final String objectNamePrefix,
                                        final long sequenceNumber) {

        return ((objectNamePrefix == null
                 || objectNamePrefix.trim().isEmpty())
                ? "" : (objectNamePrefix.trim() + PREFIX_SEQUENCE_DELIMITER))
               + (String.format(OBJECT_NAME_FORMAT,
                                (sequenceNumber & OBJECT_NAME_MASK)));
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
    protected void setContainerName(final String containerName) {
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

        setContainerName(getContainerName(containerNamePrefix, sequenceNumber));
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

        setObjectName(getObjectName(objectNamePrefix, sequenceNumber));
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


    // ----------------------------------------------------------- LAST_MODIFIED
    /**
     * Returns lastModified.
     *
     * @return lastModified
     */
    public long getLastModified() {
        return lastModified;
    }


    /**
     * Sets lastModified.
     *
     * @param lastModified lastModified
     */
    public void setLastModified(final long lastModified) {
        this.lastModified = lastModified;
    }


    @Override
    public String toString() {
        return containerName + "|" + objectName + "|" + contentType + "|"
               + contentLength + "|" + lastModified;
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
    //@Max(CONTENT_LENGTH_MAX)
    @XmlElement(required = true)
    private long contentLength = UNKNOWN_CONTENT_LENGTH;


    /**
     * last modified.
     */
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED", nullable = false)
    @Min(LAST_MODIFIED_MIN)
    //@Max(LAST_MODIFIED_MAX)
    @XmlElement(required = true)
    private long lastModified = UNKNOWN_LAST_MODIFIED;


}

