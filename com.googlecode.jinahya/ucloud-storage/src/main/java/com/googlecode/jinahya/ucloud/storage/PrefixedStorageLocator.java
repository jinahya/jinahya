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
import java.lang.reflect.Modifier;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@MappedSuperclass
public abstract class PrefixedStorageLocator extends MappedStorageLocator {


    /**
     *
     * @param <L>
     * @param prefixedStorageLocatorType
     * @param containerNamePrefix
     * @param objectNamePrefix
     * @return
     */
    protected static <L extends PrefixedStorageLocator> L newInstance(
        final Class<L> prefixedStorageLocatorType,
        final String containerNamePrefix, final String objectNamePrefix) {

        if (prefixedStorageLocatorType == null) {
            throw new IllegalArgumentException(
                "null prefixedStorageLocatorType");
        }

        if (Modifier.isAbstract(prefixedStorageLocatorType.getModifiers())) {
            throw new IllegalArgumentException(
                "abstract prefixedStorageLocatorType");
        }

        try {
            final Constructor<L> constructor =
                prefixedStorageLocatorType.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                final L instance = constructor.newInstance();
                instance.setContainerNamePrefix(containerNamePrefix);
                instance.setObjectNamePrefix(objectNamePrefix);
                return instance;
            } catch (InstantiationException ie) {
                throw new RuntimeException(ie.getMessage());
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae.getMessage());
            } catch (InvocationTargetException ite) {
                throw new RuntimeException(ite.getMessage());
            }
        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme.getMessage());
        }
    }


    // --------------------------------------------------- CONTAINER_NAME_PREFIX
    public String getContainerNamePrefix() {
        return containerNamePrefix;
    }


    protected void setContainerNamePrefix(final String containerNamePrefix) {
        this.containerNamePrefix = containerNamePrefix;
    }


    // ------------------------------------------------------ OBJECT_NAME_PREFIX
    public String getObjectNamePrefix() {
        return objectNamePrefix;
    }


    protected void setObjectNamePrefix(final String objectNamePrefix) {
        this.objectNamePrefix = objectNamePrefix;
    }


    // ------------------------------------------------------------- @PrePersist
    @PrePersist
    protected void _PrePersist() {

        final Long sequenceNumber = getSequenceNumber();

        if (sequenceNumber != null && getContainerName() == null) {
            setContainerName(containerNamePrefix, sequenceNumber.longValue());
        }

        if (sequenceNumber != null && getObjectName() == null) {
            setObjectName(objectNamePrefix, sequenceNumber.longValue());
        }
    }


    /**
     * Returns the
     * <code>sequenceNumber</code> assigned to this entity instance.
     *
     * @return the sequenceNumber or <code>null</code> if not available
     */
    protected abstract Long getSequenceNumber();


    /**
     * containerNamePrefix.
     */
    @Basic
    @Column(name = "CONTAINER_NAME_PREFIX", updatable = false)
    @Size(min = CONTAINER_NAME_PREFIX_SIZE_MIN,
          max = CONTAINER_NAME_PREFIX_SIZE_MAX)
    @XmlTransient
    private String containerNamePrefix;


    /**
     * objectNamePrefix.
     */
    @Basic
    @Column(name = "OBJECT_NAME_PREFIX", updatable = false)
    @Size(min = OBJECT_NAME_PREFIX_SIZE_MIN, max = OBJECT_NAME_PREFIX_SIZE_MAX)
    @XmlTransient
    private String objectNamePrefix;


}

