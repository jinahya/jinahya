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
import java.lang.reflect.Modifier;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@MappedSuperclass
public class MappedStorageReference {


    /**
     * Creates a new instance of given
     * <code>storageReferenceType</code>.
     *
     * @param R storage refernce type parameter
     * @param storageRefereceType storage reference type
     * @param containerName container name
     * @param objectName object name
     * @return a new instance of given <code>storageReferenceType</code>.
     */
    protected static <R extends MappedStorageReference> R newInstance(
        final Class<R> storageRefereceType, final String containerName,
        final String objectName) {

        if (storageRefereceType == null) {
            throw new IllegalArgumentException("null storageReferenceType");
        }

        if (Modifier.isAbstract(storageRefereceType.getModifiers())) {
            throw new IllegalArgumentException(
                "abstract storageReferenceType: "
                + storageRefereceType.getName());
        }

        if (containerName == null) {
            throw new IllegalArgumentException("null containerName");
        }

        if (containerName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty containerName");
        }

        if (objectName == null) {
            throw new IllegalArgumentException("null objectName");
        }

        if (objectName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty objectName");
        }

        try {
            final Constructor<R> constructor =
                storageRefereceType.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                final R instance = constructor.newInstance();
                try {
                    final Field field = MappedStorageReference.class.
                        getDeclaredField("containerName");
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    field.set(instance, containerName);
                } catch (NoSuchFieldException nsfe) {
                    throw new RuntimeException(nsfe);
                }
                try {
                    final Field field = MappedStorageReference.class.
                        getDeclaredField("objectName");
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    field.set(instance, objectName);
                } catch (NoSuchFieldException nsfe) {
                    throw new RuntimeException(nsfe);
                }
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


    @Override
    public String toString() {
        return containerName + "/" + objectName;
    }


    /**
     * container name.
     */
    @Basic(optional = false)
    @Column(name = "CONTAINER_NAME", updatable = false)
    @NotNull
    @Size(min = CONTAINER_NAME_SIZE_MIN, max = CONTAINER_NAME_SIZE_MAX)
    @XmlElement(nillable = true, required = true)
    private String containerName;


    /**
     * object name.
     */
    @Basic(optional = false)
    @Column(name = "OBJECT_NAME", updatable = false)
    @NotNull
    @Size(min = OBJECT_NAME_SIZE_MIN, max = OBJECT_NAME_SIZE_MAX)
    @XmlElement(nillable = true, required = true)
    private String objectName;


}

