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
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Abstract class for various database metadata.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class EntrySet {


    /**
     * Marshals given <code>entrySet</code> to specified <code>output</code>.
     *
     * @param <S> EntrySet type parameter
     * @param <O> output type parameter
     * @param entrySet entrySet to marshal
     * @param outputType output type
     * @param output output
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <S extends EntrySet, O> void marshal(
        final S entrySet, final Class<O> outputType, final O output)
        throws JAXBException {

        marshal(entrySet, null, outputType, output);
    }


    /**
     * Marshals given <code>metadata</code> to specified <code>output</code>.
     *
     * @param <S> EntrySet type parameter
     * @param entrySet EntrySet
     * @param properties marshaller properties
     * @param outputType marshal output type
     * @param output marshal output
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <S extends EntrySet, O> void marshal(
        final S entrySet, final Properties properties,
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
                method.invoke(marshaller, entrySet, output);
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
     * Un
     */
    /**
     * Unmarshals a new instance of given <code>entrySetType</code> from
     * specified <code>input</code>.
     *
     * @param <S> EntrySet type parameter
     * @param <I> input type parameter
     * @param entrySetType EntrySet type
     * @param inputType input type
     * @param input input
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <S extends EntrySet, I> void unmarshal(
        final Class<S> entrySetType, final Class<I> inputType, final I input)
        throws JAXBException {

        unmarshal(entrySetType, null, inputType, input);
    }


    /**
     * Unmarshals a new instance of given <code>entrySetType</code> from
     * specified <code>input</code>.
     *
     * @param <S> EntrySet type parameter
     * @param entrySetType EntrySet type
     * @param properties unmarshaller properties
     * @param inputType input type
     * @param input input
     * @throws JAXBException if a JAXB error occurs.
     */
    public static <S extends EntrySet, I> void unmarshal(
        final Class<S> entrySetType, final Properties properties,
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
     * Creates a new instance of <code>entrySetType</code> and reads information
     * from specified <code>resultSet</code>.
     *
     * @param <S> EntrySet type parameter
     * @param entrySetType EntrySet type
     * @param resultSet result set
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     */
    public static <S extends EntrySet> S newInstance(
        final Class<S> entrySetType, final ResultSet resultSet)
        throws SQLException {

        try {
            final S metadata = entrySetType.newInstance();
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
    @XmlJavaTypeAdapter(EntriesAdapter.class)
    public final Map<String, Entry> getEntries() {

        if (entries == null) {
            entries = new LinkedHashMap<String, Entry>();
        }

        return entries;
    }


    /**
     * Returns the value of an entry mapped to given <code>key</code>.
     *
     * @param key entry key
     * @return entry value
     */
    public final String getValue(final String key) {

        if (key == null) {
            throw new NullPointerException("null key");
        }

        final Entry entry = getEntries().get(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }


    /**
     * Sets the value of an entry mapped to given <code>key</code>. A new entry
     * will be created if there is not entry.
     *
     * @param key entry key
     * @param value entry value.
     */
    public final void setValue(final String key, final String value) {

        if (key == null) {
            throw new NullPointerException("null key");
        }

        Entry entry = getEntries().get(key);

        if (entry == null) {
            entry = Entry.newIntance(key, value);
            getEntries().put(key, entry);
            return;
        }

        entry.setValue(value);
    }


    /** entries. */
    private Map<String, Entry> entries;


}

