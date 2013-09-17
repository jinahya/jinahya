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
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <L> storage locator type parameter
 */
@MappedSuperclass
@XmlTransient
public class MappedStorageReference<L extends MappedStorageLocator> {


    /**
     * Creates a new instance of given {@code storageReferencetype} with
     * specified {@code storageObject}.
     *
     * @param <R> storage reference type parameter
     * @param <L> storage locator type parameter
     * @param storageReferenceType storage reference type
     * @param storageLocator storage locator
     *
     * @return a new instance of given {@code storageReferenceType}.
     */
    protected static <R extends MappedStorageReference<L>, L extends MappedStorageLocator> R newInstance(
        final Class<R> storageReferenceType, final L storageLocator) {

        if (storageReferenceType == null) {
            throw new NullPointerException("storageReferenceType");
        }

        if (Modifier.isAbstract(storageReferenceType.getModifiers())) {
            throw new IllegalArgumentException(
                "abstract storageReferenceType: "
                + storageReferenceType.getName());
        }

        if (storageLocator == null) {
            throw new IllegalArgumentException("null storageLocator");
        }

        try {
            final Constructor<R> constructor =
                storageReferenceType.getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            try {
                final R instance = constructor.newInstance();
                try {
                    final Field field = MappedStorageReference.class.
                        getDeclaredField("storageLocator");
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    field.set(instance, storageLocator);
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


    // ------------------------------------------------------ STORAGE_LOCATOR_ID
    /**
     * Returns storageLocator.
     *
     * @return storageLocator
     */
    protected L getStorageLocator() {

        return storageLocator;
    }


    protected void setStorageLocator(final L storageLocator) {

        this.storageLocator = storageLocator;
    }


    /**
     * storage locator.
     */
    @JoinColumn(name = "STORAGE_LOCATOR_ID", nullable = false,
                updatable = false)
    @OneToOne(optional = false)
    @NotNull
    @XmlTransient
    private L storageLocator;


}
