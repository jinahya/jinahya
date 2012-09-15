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
import java.lang.reflect.InvocationTargetException;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@MappedSuperclass
public class MappedStorageLocator {


    /**
     * Creates a new instance of given
     * <code>storageLocatorType</code>.
     *
     * @param <L> storage locator type parameter
     * @param storageLocatorType storage locator type
     * @param containerNamePrefix container name prefix
     * @param objectNamePrefix object name prefix
     * @param sequenceNumber sequence number
     *
     * @return a new instance of <code>storageLocatorType</code>.
     */
    public static <L extends MappedStorageLocator> L newInstance(
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
                final L instance = constructor.newInstance();
                instance.setContainerName(
                    getContainerName(containerNamePrefix, sequenceNumber));
                instance.setObjectName(
                    getObjectName(objectNamePrefix, sequenceNumber));
                return instance;
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
     * The delimiter.
     */
    private static final char PREFIX_SEQUENCE_DELIMITER = '*';


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
    public static String getContainerName(final String containerNamePrefix,
                                          final long sequenceNumber) {

        return (containerNamePrefix == null
                ? "" : (containerNamePrefix + PREFIX_SEQUENCE_DELIMITER))
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
    public static String getObjectName(final String objectNamePrefix,
                                       final long sequenceNumber) {

        return (objectNamePrefix == null
                ? "" : (objectNamePrefix + PREFIX_SEQUENCE_DELIMITER))
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
    public void setContainerName(final String containerName) {

        if (containerName != null && containerName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty containerName");
        }

        this.containerName = containerName;
    }


    /**
     * Sets
     * <code>containerName</code> with given
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

        if (objectName != null && objectName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty objectName");
        }

        this.objectName = objectName;
    }


    /**
     * Sets
     * <code>objectName</code> with given
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


    @Override
    public String toString() {
        return containerName + "/" + objectName;
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

