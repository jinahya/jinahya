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


package com.googlecode.jinahya.sql;


import java.sql.Connection;
import java.sql.SQLException;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


/**
 * Abstract SequenceGenerator implementation.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public abstract class DBSequence {


    /**
     * Creates a new instance.
     *
     * @param dataSource data source
     * @param minimumSize min count
     * @param maximumSize max count
     */
    public DBSequence(final DataSource dataSource, final int minimumSize,
                      final int maximumSize) {
        super();

        if (dataSource == null) {
            throw new NullPointerException("null dataSource");
        }

        if (minimumSize <= 0) {
            throw new IllegalArgumentException(
                "minimumSize(" + minimumSize + ") <= 0");
        }

        if (maximumSize <= 0) {
            throw new IllegalArgumentException(
                "maximumSize(" + maximumSize + ") <=0");
        }

        if (minimumSize >= maximumSize) {
            throw new IllegalArgumentException(
                "minimumSize(" + maximumSize + ") >= maximumSize("
                + maximumSize + ")");
        }

        this.dataSource = dataSource;
        this.minimumSize = minimumSize;
        this.maximumSize = maximumSize;

        map = Collections.synchronizedMap(new HashMap<String, List<Long>>());
    }


    /**
     * Returns next id value identified by given <code>name</code>.
     *
     * @param name id name
     * @return next sequence
     * @throws SQLException if an SQL error occurs.
     */
    public Long getNextValue(final String name) throws SQLException {

        synchronized (map) {

            if (!map.containsKey(name)) {
                map.put(name, new LinkedList<Long>());
            }

            final List<Long> list = map.get(name);

            if (list.size() < minimumSize) {
                final Connection connection = dataSource.getConnection();
                try {
                    fetchNextValues(connection, name, list);
                } finally {
                    connection.close();
                }
            }

            return list.get(0);
        }
    }


    /**
     * Fetch sequence values.
     *
     * @param connection connection
     * @param name sequence name
     * @param list sequence value list
     * @throws SQLException if an SQL error occurs.
     */
    protected abstract void fetchNextValues(Connection connection, String name,
                                            List<Long> list)
        throws SQLException;


    public int getMinimumSize() {
        return minimumSize;
    }


    public int getMaximumSize() {
        return maximumSize;
    }


    /** data source. */
    private final DataSource dataSource;


    /** min count. */
    private final int minimumSize;


    /** max count. */
    private final int maximumSize;


    /** map. */
    private final Map<String, List<Long>> map;


}
