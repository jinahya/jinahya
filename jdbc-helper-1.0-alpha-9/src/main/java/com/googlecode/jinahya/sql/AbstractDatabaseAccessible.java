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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.annotation.XmlTransient;


/**
 * Abstract DBAccessible implementation.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class AbstractDatabaseAccessible implements DatabaseAccessible {


    /**
     * Reads a Long value from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @return read Long value or null if null
     * @throws SQLException if an SQL error occurs.
     */
    protected static Long getLong(final ResultSet resultSet,
                                  final String columnLabel)
        throws SQLException {

        final Long value = resultSet.getLong(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }

        return value;
    }


    /**
     * Reads a long value from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @param defaultValue default value
     * @return read long value or default value if null
     * @throws SQLException if an SQL error occurs.
     */
    protected static long getLong(final ResultSet resultSet,
                                  final String columnLabel,
                                  final long defaultValue)
        throws SQLException {

        final Long value = getLong(resultSet, columnLabel);
        if (resultSet.wasNull()) {
            return defaultValue;
        }

        return value.longValue();
    }


    /**
     * Reads an Integer from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @return read Integer value or null if null
     * @throws SQLException if an SQL error occurs.
     */
    protected static Integer getInt(final ResultSet resultSet,
                                    final String columnLabel)
        throws SQLException {

        final Integer value = resultSet.getInt(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }

        return value;
    }


    /**
     * Reads an int value from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @param defaultValue default value
     * @return read int value or defalutValue if null
     * @throws SQLException if an SQL error occurs.
     */
    protected static int getInt(final ResultSet resultSet,
                                final String columnLabel,
                                final int defaultValue)
        throws SQLException {

        final Integer value = getInt(resultSet, columnLabel);
        if (value == null) {
            return defaultValue;
        }

        return value.intValue();
    }


    /**
     * Reads an Float from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @return read Float value or null if database value is null
     * @throws SQLException if an SQL error occurs.
     */
    protected static Float getFloat(final ResultSet resultSet,
                                    final String columnLabel)
        throws SQLException {

        final Float value = resultSet.getFloat(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }

        return value;
    }


    /**
     * Reads a float value from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @param defaultValue default value
     * @return read float value or defalutValue if database value is null
     * @throws SQLException if an SQL error occurs.
     */
    protected static float getFloat(final ResultSet resultSet,
                                    final String columnLabel,
                                    final float defaultValue)
        throws SQLException {

        final Float value = getFloat(resultSet, columnLabel);
        if (value == null) {
            return defaultValue;
        }

        return value.floatValue();
    }


    /**
     * Reads an Float from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @return read Double value or null if database value is null
     * @throws SQLException if an SQL error occurs.
     */
    protected static Double getDouble(final ResultSet resultSet,
                                      final String columnLabel)
        throws SQLException {

        final Double value = resultSet.getDouble(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }

        return value;
    }


    /**
     * Reads a double value from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @param defaultValue default value
     * @return read double value or defalutValue if database value is null
     * @throws SQLException if an SQL error occurs.
     */
    protected static double getDouble(final ResultSet resultSet,
                                      final String columnLabel,
                                      final float defaultValue)
        throws SQLException {

        final Double value = getDouble(resultSet, columnLabel);
        if (value == null) {
            return defaultValue;
        }

        return value.doubleValue();
    }


    /**
     * Creates a new instance.
     *
     * @param tableName table name
     * @param idColumnName id column name
     */
    public AbstractDatabaseAccessible(final String tableName,
                                      final String idColumnName) {
        super();

        if (tableName == null) {
            throw new NullPointerException("null tableName");
        }
        if (tableName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty tableName");
        }

        if (idColumnName == null) {
            throw new NullPointerException("null idColumnName");
        }
        if (idColumnName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty idColumnName");
        }

        this.tableName = tableName;
        this.idColumnName = idColumnName;
    }


    @Override
    public Long getId() {
        return id;
    }


    @Override
    public void setId(final Long id) {
        this.id = id;
    }


    @Override
    public final String getTableName() {
        return tableName;
    }


    @Override
    public final String getIdColumnName() {
        return idColumnName;
    }


    @Override
    public boolean select(final Connection connection) throws SQLException {
        return DatabaseAccessibleHelper.select(connection, this);
    }


    @Override
    public boolean delete(final Connection connection) throws SQLException {
        return DatabaseAccessibleHelper.delete(connection, this);
    }


    /** table name. */
    protected final String tableName;


    /** id column name. */
    protected final String idColumnName;


    /** id. */
    private Long id;


}

