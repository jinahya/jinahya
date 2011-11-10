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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
@XmlType(name = "collection")
public abstract class MetadataCollection<T extends Metadata> {


    /**
     * 
     * @param <C>
     * @param <O>
     * @param collection
     * @param properties
     * @param outputType
     * @param output
     * @throws JAXBException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static <C extends MetadataCollection, O> void marshal(
        final C collection, final Map<String, Object> properties,
        final Class<O> outputType, final O output)
        throws JAXBException, NoSuchMethodException, IllegalAccessException,
               InvocationTargetException {

        final Marshaller marshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createMarshaller();

        if (properties != null) {
            for (Entry<String, Object> entry : properties.entrySet()) {
                marshaller.setProperty(entry.getKey(), entry.getValue());
            }
        }

        final Method method = Marshaller.class.getMethod(
            "marshal", Object.class, outputType);

        method.invoke(marshaller, collection, output);
    }


    /**
     * Unmarshals a instance of given <code>metadataType</code> from specified
     * <code>input</code>.
     *
     * @param <C> collection type parameter
     * @param collectionType collection type
     * @param properties unmarshaller properties
     * @param inputType input type
     * @param input input
     * @throws JAXBException if a JAXB error occurs.
     * @throws NoSuchMethodException if input is unknown
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static <C extends MetadataCollection, I> C unmarshal(
        final Class<C> collectionType, final Map<String, Object> properties,
        final Class<I> inputType, final I input)
        throws JAXBException, NoSuchMethodException, IllegalAccessException,
               InvocationTargetException {

        final Unmarshaller unmarshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createUnmarshaller();

        if (properties != null) {
            for (Entry<String, Object> entry : properties.entrySet()) {
                unmarshaller.setProperty(entry.getKey(), entry.getValue());
            }
        }

        final Method method = Marshaller.class.getMethod(
            "unmarshal", inputType);

        return collectionType.cast(method.invoke(unmarshaller, input));
    }


    /**
     * Returns entries.
     *
     * @return entries
     */
    public Collection<T> getMetadata() {

        if (metadata == null) {
            metadata = new ArrayList<T>();
        }

        return metadata;
    }


    @XmlElement
    private Collection<T> metadata;


}

