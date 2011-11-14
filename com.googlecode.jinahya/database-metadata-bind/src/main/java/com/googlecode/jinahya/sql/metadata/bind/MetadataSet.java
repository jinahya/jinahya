/*
 * Copyright 2011 Jin Kwon <jinahya at gmail.com>.
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


package com.googlecode.jinahya.sql.metadata.bind;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <M> metadata type parameter.
 */
@XmlTransient
public abstract class MetadataSet<M extends Metadata> {


    /**
     * 
     * @param <S> collection type parameter
     * @param <O> output type parameter
     * @param collection collection
     * @param properties marshaller properties
     * @param outputType output type
     * @param output output
     * @throws JAXBException if JAXB error occurs
     */
    public static <S extends MetadataSet, O> void marshal(
        final S collection, final Map<String, Object> properties,
        final Class<O> outputType, final O output)
        throws JAXBException {

        final Marshaller marshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createMarshaller();

        if (properties != null) {
            for (Entry<String, Object> entry : properties.entrySet()) {
                marshaller.setProperty(entry.getKey(), entry.getValue());
            }
        }

        try {
            final Method method = Marshaller.class.getMethod(
                "marshal", Object.class, outputType);

            try {
                method.invoke(marshaller, collection, output);
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            } catch (InvocationTargetException ite) {
                throw new RuntimeException(ite);
            }

        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }


    /**
     * Unmarshals a instance of given <code>metadataType</code> from specified
     * <code>input</code>.
     *
     * @param <S> collection type parameter
     * @param collectionType collection type
     * @param properties unmarshaller properties
     * @param inputType input type
     * @param input input
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <S extends MetadataSet, I> S unmarshal(
        final Class<S> collectionType, final Map<String, Object> properties,
        final Class<I> inputType, final I input)
        throws JAXBException {

        final Unmarshaller unmarshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createUnmarshaller();

        if (properties != null) {
            for (Entry<String, Object> entry : properties.entrySet()) {
                unmarshaller.setProperty(entry.getKey(), entry.getValue());
            }
        }

        try {
            final Method method = Marshaller.class.getMethod(
                "unmarshal", inputType);

            try {
                return collectionType.cast(method.invoke(unmarshaller, input));
            } catch (IllegalAccessException iae) {
                throw new RuntimeException(iae);
            } catch (InvocationTargetException ite) {
                throw new RuntimeException(ite);
            }

        } catch (NoSuchMethodException nsme) {
            throw new RuntimeException(nsme);
        }
    }


    /**
     * Creates a new instance.
     *
     * @param accessibleType accessibleType
     */
    public MetadataSet(final Class<M> accessibleType) {
        super();

        if (accessibleType == null) {
            throw new NullPointerException("null accessibleType");
        }

        if (!Metadata.class.isAssignableFrom(accessibleType)) {
            throw new IllegalArgumentException(
                accessibleType + " is not assignable to "
                + Metadata.class);
        }

        this.metadataType = accessibleType;
    }


    public Class<M> getMetadataType() {
        return metadataType;
    }


    /**
     * Returns entries.
     *
     * @return entries
     */
    protected Collection<M> getMetadata() {

        if (metadata == null) {
            metadata = new ArrayList<M>();
        }

        return metadata;
    }


    /**
     * Accessible type.
     */
    protected final Class<M> metadataType;


    /** Accessible collection. */
    private Collection<M> metadata;


}

