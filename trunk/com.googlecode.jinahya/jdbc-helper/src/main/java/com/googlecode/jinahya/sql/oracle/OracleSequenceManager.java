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


package com.googlecode.jinahya.sql.oracle;


import com.googlecode.jinahya.sql.SequenceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;


/**
 * Default implementation.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class OracleSequenceManager extends SequenceManager {


    private static final int DEFAULT_MINIMUM_COUNT = 10;


    private static final int DEFAULT_MAXIMUM_COUNT = 100;


    /**
     * Creates a new instance.
     *
     * @param dataSource data source
     */
    public OracleSequenceManager(final DataSource dataSource) {
        this(dataSource, DEFAULT_MINIMUM_COUNT, DEFAULT_MAXIMUM_COUNT);
    }


    /**
     * Creates a new instance.
     *
     * @param dataSource data source
     * @param minimumCount minimum count
     * @param maximumCount maximum count
     */
    public OracleSequenceManager(final DataSource dataSource,
                                 final int minimumCount,
                                 final int maximumCount) {

        super(dataSource, minimumCount, maximumCount);
    }


    @Override
    protected void fetchNextValues(final Connection connection,
                                   final String sequenceName,
                                   final List<Long> sequenceValues)
        throws SQLException {

        if (sequenceValues.size() >= getMaximumSize()) {
            return;
        }

        final PreparedStatement preparedStatement = connection.prepareCall(
            "SELECT LEVEL, " + sequenceName + ".NEXTVAL FROM DUAL"
            + " CONNECT BY LEVEL <= ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setInt(++parameterIndex,
                                     getMaximumSize() - sequenceValues.size());

            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    sequenceValues.add(resultSet.getLong(sequenceName + ".NEXTVAL"));
                }
            } finally {
                resultSet.close();
            }
        } finally {
            preparedStatement.close();
        }
    }


}

