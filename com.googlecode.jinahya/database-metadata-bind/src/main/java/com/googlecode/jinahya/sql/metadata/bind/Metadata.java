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


import java.io.PrintStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class Metadata {


    /**
     * Marshals given <code>metadata</code> to specified <code>output</code>.
     *
     * @param <M> metadata type parameter
     * @param metadata metadata
     * @param properties marshaller properties
     * @param outputType marshal output type
     * @param output marshal output
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <M extends Metadata, O> void marshal(
        final M metadata, final Properties properties,
        final Class<O> outputType, final O output)
        throws JAXBException {

        final Marshaller marshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createMarshaller();

        if (properties != null) {
            for (String name : properties.stringPropertyNames()) {
                marshaller.setProperty(name, properties.get(name));
            }
        }

        try {
            final Method method = Marshaller.class.getMethod(
                "marshal", Object.class, outputType);
            try {
                method.invoke(marshaller, metadata, output);
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
     * @param <M> metadata type parameter
     * @param metadataType metadata type
     * @param properties unmarshaller properties
     * @param inputType input type
     * @param input input
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <M extends Metadata, I> void unmarshal(
        final Class<M> metadataType, final Properties properties,
        final Class<I> inputType, final I input)
        throws JAXBException {

        final Unmarshaller unmarshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createUnmarshaller();

        if (properties != null) {
            for (String name : properties.stringPropertyNames()) {
                unmarshaller.setProperty(name, properties.get(name));
            }
        }

        try {
            final Method method = Marshaller.class.getMethod(
                "unmarshal", inputType);
            try {
                method.invoke(unmarshaller, input);
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
     * Creates a new instance of <code>metadataType</code> and reads information
     * from specified <code>resultSet</code>.
     *
     * @param <M> metadata type parameter
     * @param metadataType metadata type
     * @param resultSet result set
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     */
    public static <M extends Metadata> M newInstance(
        final Class<M> metadataType, final ResultSet resultSet)
        throws SQLException {

        try {
            final M metadata = metadataType.newInstance();
            metadata.read(resultSet);
            return metadata;
        } catch (InstantiationException ie) {
            throw new RuntimeException(ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException(iae);
        }
    }


    /**
     * Reads information from given <code>resultSet</code>.
     *
     * @param resultSet result set
     * @throws SQLException if an SQL error occurs.
     */
    protected void read(final ResultSet resultSet) throws SQLException {

        final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        final int columnCount = resultSetMetaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            final String key = resultSetMetaData.getColumnLabel(column);
            String value = String.valueOf(resultSet.getObject(key));
            if (resultSet.wasNull()) {
                value = null;
            }
            getEntries().put(key, Entry.newIntance(key, value));
        }
    }


    /**
     * Returns entries.
     *
     * @return entries
     */
    @XmlElement(required = true, nillable = true)
    @XmlJavaTypeAdapter(EntriesAdapter.class)
    public Map<String, Entry> getEntries() {

        if (entries == null) {
            entries = new LinkedHashMap<String, Entry>();
        }

        return entries;
    }


    /**
     * 
     * @param key
     * @return 
     */
    protected String getValue(final String key) {
        final Entry entry = getEntries().get(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }


    /**
     * 
     * @param key
     * @param value 
     */
    protected void setValue(final String key, final String value) {

        if (!getEntries().containsKey(key)) {
            addEntry(key, value);
            return;
        }

        getEntries().get(key).setValue(value);
    }


    /**
     * Adds a new entry.
     *
     * @param key entry label
     * @param value entry value
     * @return previous entry mapped to given <code>label</code>
     */
    public Entry addEntry(final String key, final String value) {

        return addEntry(Entry.newIntance(key, value));
    }


    /**
     * Adds a new entry.
     *
     * @param entry entry
     * @return previous entry
     */
    public Entry addEntry(final Entry entry) {

        return getEntries().put(entry.getKey(), entry);
    }


    public void print(final PrintStream out) {
        for (Entry entry : getEntries().values()) {
            out.print(entry.getKey() + ": " + entry.getValue());
            if (entry.getValue() != null) {
                out.print(" " + entry.getValue().getClass());
            }
            out.println();
        }
    }


    /** entries. */
    private Map<String, Entry> entries;


}

