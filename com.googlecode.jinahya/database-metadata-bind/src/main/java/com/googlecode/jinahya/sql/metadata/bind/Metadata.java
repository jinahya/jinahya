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


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlRootElement
public abstract class Metadata {


    /**
     * Creates a new instance of <code>metadataType</code> and reads information
     * from specified <code>resultSet</code>.
     *
     * @param <T> metadata type parameter
     * @param metadataType metadata type
     * @param resultSet result set
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     */
    public static <T extends Metadata> T newInstance(
        final Class<T> metadataType, final ResultSet resultSet)
        throws SQLException {

        try {
            final T metadataSet = metadataType.newInstance();
            metadataSet.read(resultSet);
            return metadataSet;
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
            final MetadataEntry entry = new MetadataEntry();
            entry.setLabel(label);
            entry.setValue(value);
            getEntries().put(label, entry);
        }
    }


    /**
     * Returns entries.
     *
     * @return entries
     */
    @XmlJavaTypeAdapter(MetadataEntriesAdapter.class)
    public Map<String, MetadataEntry> getEntries() {

        if (entries == null) {
            entries = new LinkedHashMap<String, MetadataEntry>();
        }

        return entries;
    }


    public Object getValue(final String label) {
        final MetadataEntry entry = getEntries().get(label);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }


    public void setValue(final String label, final Object value) {

        if (!getEntries().containsKey(label)) {
            getEntries().put(label, MetadataEntry.newIntance(label, value));
        }

        getEntries().get(label).setValue(value);
    }


    public <T> T getValue(final Class<T> type, final String label) {
        return type.cast(getValue(label));
    }


    public <T> void setValue(final Class<T> valueType, final String label,
                             final T value) {

        if (valueType == null) {
            throw new NullPointerException("null valueType");
        }

        if (value != null && !valueType.isInstance(value)) {
            throw new IllegalArgumentException(
                value + " is not an instance of " + valueType);
        }

        setValue(label, value);
    }

    public void addEntry(final String label, final Object value) {

        getEntries().put(label, MetadataEntry.newIntance(label, value));
    }

    private Map<String, MetadataEntry> entries;


}

