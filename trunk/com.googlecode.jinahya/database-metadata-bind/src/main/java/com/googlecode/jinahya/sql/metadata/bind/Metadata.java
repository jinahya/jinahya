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
import javax.xml.bind.annotation.XmlTransient;


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
     * @throws NoSuchMethodException if output is unknown
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static <M extends Metadata, O> void marshal(
        final M metadata, final Properties properties,
        final Class<O> outputType, final O output)
        throws JAXBException, NoSuchMethodException, IllegalAccessException,
               InvocationTargetException {

        final Marshaller marshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createMarshaller();

        if (properties != null) {
            for (String name : properties.stringPropertyNames()) {
                marshaller.setProperty(name, properties.get(name));
            }
        }

        final Method method = Marshaller.class.getMethod(
            "marshal", Object.class, outputType);

        method.invoke(marshaller, metadata, output);
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
     * @throws NoSuchMethodException if input is unknown
     * @throws IllegalAccessException
     * @throws InvocationTargetException 
     */
    public static <M extends Metadata, I> void unmarshal(
        final Class<M> metadataType, final Properties properties,
        final Class<I> inputType, final I input)
        throws JAXBException, NoSuchMethodException, IllegalAccessException,
               InvocationTargetException {

        final Unmarshaller unmarshaller =
            DatabaseMetadataBindConstants.JAXB_CONTEXT.createUnmarshaller();

        if (properties != null) {
            for (String name : properties.stringPropertyNames()) {
                unmarshaller.setProperty(name, properties.get(name));
            }
        }

        final Method method = Marshaller.class.getMethod(
            "unmarshal", inputType);

        method.invoke(unmarshaller, input);
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
            throw new SQLException(ie);
        } catch (IllegalAccessException iae) {
            throw new SQLException(iae);
        }
    }


    /**
     * Reads information from given <code>resultSet</code>.
     *
     * @param resultSet result set
     * @throws SQLException if an SQL error occurs.
     */
    protected void read(final ResultSet resultSet) throws SQLException {

        final ResultSetMetaData meta = resultSet.getMetaData();
        final int count = meta.getColumnCount();
        for (int column = 1; column <= count; column++) {
            final String label = meta.getColumnLabel(column);
            Object value = resultSet.getObject(label);
            if (resultSet.wasNull()) {
                value = null;
            }
            final Entry entry = new Entry();
            entry.setLabel(label);
            entry.setValue(value);
            getEntries().put(label, entry);
        }
    }


    /**
     * 
     * @return 
     */
    public Map<String, Entry> getEntries() {

        if (entries == null) {
            entries = new LinkedHashMap<String, Entry>();
        }

        return entries;
    }


    /**
     * 
     * @param label
     * @return 
     */
    protected Object getValue(final String label) {
        final Entry entry = getEntries().get(label);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }


    /**
     * 
     * @param label
     * @param value 
     */
    protected void setValue(final String label, final Object value) {

        if (!getEntries().containsKey(label)) {
            addEntry(label, value);
            return;
        }

        getEntries().get(label).setValue(value);
    }


    /**
     * 
     * @param <V> value type parameter
     * @param type type
     * @param label label
     * @return 
     */
    protected <V> V getValue(final Class<V> type, final String label) {
        return type.cast(getValue(label));
    }


    /**
     * 
     * @param <V> value type parameter
     * @param type type
     * @param label label
     * @param value value
     */
    protected <V> void setValue(final Class<V> type, final String label,
                                final V value) {

        if (type == null) {
            throw new NullPointerException("null valueType");
        }

        if (value != null && !type.isInstance(value)) {
            throw new IllegalArgumentException(
                value + " is not an instance of " + type);
        }

        setValue(label, value);
    }


    /**
     * Adds a new entry.
     *
     * @param label entry label
     * @param value entry value
     * @return previous entry mapped to given <code>label</code>
     */
    public Entry addEntry(final String label, final Object value) {

        return addEntry(Entry.newIntance(label, value));
    }


    /**
     * Adds a new entry.
     *
     * @param entry entry
     * @return previous entry
     */
    public Entry addEntry(final Entry entry) {

        return getEntries().put(entry.getLabel(), entry);
    }


    public void print(final PrintStream out) {
        for (Entry entry : getEntries().values()) {
            out.print(entry.getLabel() + ": " + entry.getValue());
            if (entry.getValue() != null) {
                out.print(" " + entry.getValue().getClass());
            }
            out.println();
        }
    }


    /** entries. */
    private Map<String, Entry> entries;


}

