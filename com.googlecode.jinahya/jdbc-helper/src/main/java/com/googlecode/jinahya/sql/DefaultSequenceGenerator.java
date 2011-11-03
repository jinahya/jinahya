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
public abstract class DefaultSequenceGenerator
    extends AbstractSequenceGenerator {


    /**
     * Creates a new instance.
     *
     * @param dataSource data source
     * @param minCount min count
     * @param maxCount max count
     */
    public DefaultSequenceGenerator(final DataSource dataSource,
                                    final int minCount, final int maxCount) {

        super(dataSource, minCount, maxCount);
    }


    @Override
    protected void fetch(final Connection connection, final String name,
                         final List<Long> list, final int maxCount)
        throws SQLException {

        if (list.size() >= maxCount) {
            return;
        }

        final PreparedStatement preparedStatement = connection.prepareCall(
            "SELECT LEVEL, " + name + ".NEXTVAL FROM DUAL"
            + " CONNECT BY LEVEL <= ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setInt(++parameterIndex, maxCount - list.size());

            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                while (resultSet.next()) {
                    list.add(resultSet.getLong(name + ".NEXTVAL"));
                }
            } finally {
                resultSet.close();
            }
        } finally {
            preparedStatement.close();
        }
    }


}

