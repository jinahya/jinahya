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
 * @param <S> EntrySet type parameter.
 */
@XmlTransient
public abstract class EntrySetWrapper<S extends EntrySet> {


    /**
     * Marshals given <code>wrapper</code> to specified <code>output</code>.
     *
     * @param <W> EntrySetWrapper type
     * @param <O> output type parameter
     * @param wrapper EntrySetWrapper
     * @param properties marshaller properties
     * @param outputType output type
     * @param output output
     * @throws JAXBException if JAXB error occurs
     */
    public static <W extends EntrySetWrapper, O> void marshal(
        final W wrapper, final Map<String, Object> properties,
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
                method.invoke(marshaller, wrapper, output);
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
     * @param <W> EntrySetWrapper type parameter
     * @param wrapperType EntrySetWrapper type
     * @param properties unmarshaller properties
     * @param inputType input type
     * @param input input
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <W extends EntrySetWrapper, I> W unmarshal(
        final Class<W> wrapperType, final Map<String, Object> properties,
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
                return wrapperType.cast(method.invoke(unmarshaller, input));
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
     * @param entrySetType EntrySet type
     */
    public EntrySetWrapper(final Class<S> entrySetType) {
        super();

        if (entrySetType == null) {
            throw new NullPointerException("null entrySetType");
        }

        if (!EntrySet.class.isAssignableFrom(entrySetType)) {
            throw new IllegalArgumentException(
                entrySetType + " is not assignable to " + EntrySet.class);
        }

        this.entrySetType = entrySetType;
    }


    /**
     * Returns the type of EntrySet.
     *
     * @return entrySetType
     */
    public Class<S> getEntrySetType() {
        return entrySetType;
    }


    /**
     * Returns entries.
     *
     * @return entries
     */
    protected Collection<S> getEntrySets() {

        if (entrySets == null) {
            entrySets = new ArrayList<S>();
        }

        return entrySets;
    }


    /**
     * EntrySet type.
     */
    protected final Class<S> entrySetType;


    /**
     * EntrySet collection.
     */
    private Collection<S> entrySets;


}

