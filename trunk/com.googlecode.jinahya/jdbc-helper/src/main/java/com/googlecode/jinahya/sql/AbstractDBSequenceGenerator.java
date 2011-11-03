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
public abstract class AbstractDBSequenceGenerator
    implements DBSequenceGenerator {


    /**
     * Creates a new instance.
     *
     * @param dataSource data source
     * @param minCount min count
     * @param maxCount max count
     */
    public AbstractDBSequenceGenerator(final DataSource dataSource,
                                       final int minCount, final int maxCount) {
        super();

        if (dataSource == null) {
            throw new NullPointerException("null dataSource");
        }

        if (minCount <= 0) {
            throw new IllegalArgumentException(
                "minCount(" + minCount + ") <= 0");
        }

        if (maxCount <= 0) {
            throw new IllegalArgumentException(
                "minCount(" + maxCount + ") <=0");
        }

        if (minCount >= maxCount) {
            throw new IllegalArgumentException(
                "minCount(" + maxCount + ") >= maxCount(" + maxCount + ")");
        }

        this.dataSource = dataSource;
        this.minCount = minCount;
        this.maxCount = maxCount;

        map = Collections.synchronizedMap(new HashMap<String, List<Long>>());
    }


    /**
     * Returns next id value identified by given <code>name</code>.
     *
     * @param name id name
     * @return next sequence
     * @throws SQLException if an SQL error occurs.
     */
    @Override
    public final Long getNextSequence(final String name) throws SQLException {

        synchronized (map) {

            if (!map.containsKey(name)) {
                map.put(name, new LinkedList<Long>());
            }

            final List<Long> list = map.get(name);

            if (list.size() < minCount) {
                final Connection connection = dataSource.getConnection();
                try {
                    fetch(connection, name, list, maxCount);
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
     * @param maxCount maximum count
     * @throws SQLException if an SQL error occurs.
     */
    protected abstract void fetch(Connection connection, String name,
                                  List<Long> list, int maxCount)
        throws SQLException;


    /** data source. */
    private final DataSource dataSource;


    /** min count. */
    private final int minCount;


    /** max count. */
    private final int maxCount;


    /** map. */
    private final Map<String, List<Long>> map;


}

