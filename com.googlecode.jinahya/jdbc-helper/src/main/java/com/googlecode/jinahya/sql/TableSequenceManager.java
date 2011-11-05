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
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;


/**
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
public class TableSequenceManager extends SequenceManager {


    /** result set type. */
    private static final int TYPE = ResultSet.TYPE_SCROLL_SENSITIVE;


    /** result set concurrency. */
    private static final int CONCURRENCY = ResultSet.CONCUR_UPDATABLE;


    /**
     * Creates a new instance.
     *
     * @param dataSource data source
     * @param minimumSize minimum size
     * @param maximumSize maximum size
     * @param table table name
     * @param pkColumnName key column name
     * @param valueColumnName value column name
     */
    public TableSequenceManager(final DataSource dataSource,
                                final int minimumSize, final int maximumSize,
                                final String table, final String pkColumnName,
                                final String valueColumnName) {

        super(dataSource, minimumSize, maximumSize);

        this.table = table;
        this.pkColumnName = pkColumnName;
        this.valueColumnName = valueColumnName;
    }


    @Override
    protected void fetchNextValues(final Connection connection,
                                   final String sequenceName,
                                   final List<Long> sequenceValues,
                                   final int fetchCount)
        throws SQLException {

        final DatabaseMetaData metaData = connection.getMetaData();

        final boolean commit = connection.getAutoCommit();
        final int isolation = connection.getTransactionIsolation();
        try {
            connection.setAutoCommit(false);
            if (false && metaData.supportsTransactionIsolationLevel(
                Connection.TRANSACTION_SERIALIZABLE)) {
                connection.setTransactionIsolation(
                    Connection.TRANSACTION_SERIALIZABLE);
            } else {
                connection.setTransactionIsolation(
                    Connection.TRANSACTION_REPEATABLE_READ);
            }
            try {
                if (metaData.supportsResultSetConcurrency(TYPE, CONCURRENCY)) {
                    fetchConcurrently(connection, sequenceName, sequenceValues,
                                      fetchCount);
                } else {

                    fetchSeparately(connection, sequenceName, sequenceValues,
                                    fetchCount);
                }
                connection.commit();
            } catch (SQLException sqle) {
                try {
                    connection.rollback();
                } catch (SQLException sqle2) {
                }
                throw sqle;
            }
        } finally {
            connection.setAutoCommit(commit);
            connection.setTransactionIsolation(isolation);
        }
    }


    /**
     * Fetches concurrently.
     *
     * @param connection connection
     * @param sequenceName sequence name
     * @param sequenceValues sequence value
     * @param fetchCount number of sequence values to fetch
     * @throws SQLException if an SQL error occurs.
     */
    private void fetchConcurrently(final Connection connection,
                                   final String sequenceName,
                                   final List<Long> sequenceValues,
                                   final int fetchCount)
        throws SQLException {

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM " + table + " WHERE " + pkColumnName + " = ?"
            + " FOR UPDATE",
            ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        try {
            int parameterIndex = 0;
            preparedStatement.setString(++parameterIndex, sequenceName);

            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if (resultSet.next()) {
                    long sequenceValue = resultSet.getLong(valueColumnName);
                    for (int i = 0; i < fetchCount; i++) {
                        sequenceValues.add(++sequenceValue);
                    }
                    resultSet.updateLong(valueColumnName, sequenceValue);
                    resultSet.updateRow();
                    //connection.commit();
                    return;
                }

                long sequenceValue = -1L;
                for (int i = 0; i < fetchCount; i++) {
                    sequenceValues.add(++sequenceValue);
                }
                resultSet.moveToInsertRow();
                resultSet.updateString(pkColumnName, sequenceName);
                resultSet.updateLong(valueColumnName, sequenceValue);
                resultSet.insertRow();
                //connection.commit();
                return;

            } finally {
                resultSet.close();
            }
        } finally {
            preparedStatement.close();
        }
    }


    /**
     * Selects current sequence value.
     *
     * @param connection connection
     * @param sequenceName sequence name
     * @param sequenceValues sequence value list
     * @param fetchCount number of values to fetch
     * @throws SQLException if an SQL error occurs.
     */
    private void fetchSeparately(final Connection connection,
                                 final String sequenceName,
                                 final List<Long> sequenceValues,
                                 final int fetchCount)
        throws SQLException {

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM " + table + " WHERE " + pkColumnName + " = ?"
            + " FOR UPDATE");
        try {
            int parameterIndex = 0;
            preparedStatement.setString(++parameterIndex, sequenceName);

            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if (resultSet.next()) {
                    insert(connection, sequenceName);
                    fetchSeparately(connection, sequenceName, sequenceValues,
                                    fetchCount);
                }

                long sequenceValue = resultSet.getLong(valueColumnName);
                for (int i = 0; i < fetchCount; i++) {
                    sequenceValues.add(++sequenceValue);
                }
                update(connection, sequenceName, sequenceValue);
                return;

            } finally {
                resultSet.close();
            }
        } finally {
            preparedStatement.close();
        }
    }


    /**
     * Inserts a new row.
     *
     * @param connection connection
     * @param sequenceName sequence name
     * @throws SQLException if an SQL error occurs.
     */
    private void insert(final Connection connection, final String sequenceName)
        throws SQLException {

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "INSERT INO " + table
            + " (" + pkColumnName + ", " + valueColumnName + ")"
            + " VALUES (?, ?)");
        try {
            int parameterIndex = 0;
            preparedStatement.setString(++parameterIndex, sequenceName);
            preparedStatement.setLong(++parameterIndex, -1L);

            final int result = preparedStatement.executeUpdate();
            if (result != 1) {
                throw new SQLException("insert result(" + result + ") != 1");
            }

        } finally {
            preparedStatement.close();
        }
    }


    /**
     * Updates sequence value.
     *
     * @param connection connection
     * @param sequenceName sequence name
     * @param sequenceValue new sequence value
     * @throws SQLException if an SQL error occurs.
     */
    private void update(final Connection connection, final String sequenceName,
                        final long sequenceValue)
        throws SQLException {

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "UPDATE " + table
            + " SET " + valueColumnName + " = ?"
            + " WHERE " + pkColumnName + " = ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setLong(++parameterIndex, sequenceValue);
            preparedStatement.setString(++parameterIndex, sequenceName);

            final int result = preparedStatement.executeUpdate();
            if (result != 1) {
                throw new SQLException("update result(" + result + ") != 1");
            }

        } finally {
            preparedStatement.close();
        }
    }


    /** table name. */
    private final String table;


    /** key column name. */
    private final String pkColumnName;


    /** value column name. */
    private final String valueColumnName;


}

