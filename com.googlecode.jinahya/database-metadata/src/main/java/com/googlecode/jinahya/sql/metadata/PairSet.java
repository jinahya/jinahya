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


package com.googlecode.jinahya.sql.metadata;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Abstract class for various database metadata.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class PairSet {


    /**
     * Creates a new instance of
     * <code>entrySetType</code> and reads information from specified
     * <code>resultSet</code>.
     *
     * @param <S> EntrySet type parameter
     * @param entrySetType EntrySet type
     * @param resultSet result set
     * @return a new instance
     * @throws SQLException if an SQL error occurs.
     */
    static <S extends PairSet> S newInstance(final Class<S> entrySetType,
                                              final ResultSet resultSet)
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
     * Reads information from given
     * <code>resultSet</code>.
     *
     * @param resultSet result set
     * @throws SQLException if an SQL error occurs.
     */
    final void read(final ResultSet resultSet) throws SQLException {

        final ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        final int columnCount = resultSetMetaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            final String key = resultSetMetaData.getColumnLabel(i);
            String value = String.valueOf(resultSet.getObject(key));
            if (resultSet.wasNull()) {
                value = null;
            }
            getPairs().put(key, Pair.newIntance(key, value));
        }
    }


    /**
     * Returns entries.
     *
     * @return entries
     */
    public final Map<String, Pair> getPairs() {
        if (pairs == null) {
            pairs = new LinkedHashMap<String, Pair>();
            //entries = new HashMap<String, Entry>();
        }
        return pairs;
    }


    /**
     * Returns the value of an entry mapped to given
     * <code>key</code>.
     *
     * @param key entry key
     * @return entry value
     */
    final String getValue(final String key) {

        if (key == null) {
            throw new NullPointerException("null key");
        }

        final Pair entry = getPairs().get(key);
        if (entry == null) {
            return null;
        }
        return entry.getValue();
    }


    /**
     * Sets the value of an entry mapped to given
     * <code>key</code>. A new entry will be created if there is not entry.
     *
     * @param key entry key
     * @param value entry value.
     */
    final void setValue(final String key, final String value) {

        if (key == null) {
            throw new NullPointerException("null key");
        }

        Pair entry = getPairs().get(key);

        if (entry == null) {
            entry = Pair.newIntance(key, value);
            getPairs().put(key, entry);
            return;
        }

        entry.setValue(value);
    }


    /**
     * Returns metadata.
     *
     * @return metadata
     */
    final Metadata getMetadata() {
        return metadata;
    }


    /**
     * Sets metadata.
     *
     * @param metadata metadata
     */
    void setMetadata(final Metadata metadata) {
        this.metadata = metadata;
    }


    /**
     * entries.
     */
    @XmlJavaTypeAdapter(PairsAdapter.class)
    private Map<String, Pair> pairs;


    /**
     * metadata.
     */
    @XmlTransient
    private Metadata metadata;


}

