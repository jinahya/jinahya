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


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;


/**
 * Abstract implementation of DatabaseAccessible.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 * @param <I> id type parameter
 */
@XmlTransient
public abstract class AbstractDbAccessible<I> implements DbAccessible<I> {


    /**
     * Reads a Byte from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @return fetched Byte value or <code>null</code> if the column value is
     *         null
     * @throws SQLException if an SQL error occurs.
     */
    protected static Byte getByte(final ResultSet resultSet,
                                  final String columnLabel)
        throws SQLException {

        final Byte value = resultSet.getByte(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }

        return value;
    }


    /**
     * Reads a byte value from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @param defaultValue default value
     * @return fetched byte value or <code>defalutValue</code> if the column
     *         value is null
     * @throws SQLException if an SQL error occurs.
     */
    protected static byte getByte(final ResultSet resultSet,
                                  final String columnLabel,
                                  final byte defaultValue)
        throws SQLException {

        final Byte value = getByte(resultSet, columnLabel);
        if (value == null) {
            return defaultValue;
        }

        return value.byteValue();
    }


    /**
     * Reads a Short value from given ResultSet mapped to specified
     * <code>columnLabel</code>.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @return fetched Short value or <code>null</code> if the column value is
     *         null.
     * @throws SQLException if an SQL error occurs.
     */
    protected static Short getShort(final ResultSet resultSet,
                                    final String columnLabel)
        throws SQLException {

        final Short value = resultSet.getShort(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }

        return value;
    }


    /**
     * Reads a short value from given <code>resultSet</code>.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @param defaultValue default value
     * @return fetched short value or <code>defalutValue</code> if the column
     *         value is null.
     * @throws SQLException if an SQL error occurs.
     */
    protected static short getShort(final ResultSet resultSet,
                                    final String columnLabel,
                                    final short defaultValue)
        throws SQLException {

        final Integer value = getInt(resultSet, columnLabel);
        if (value == null) {
            return defaultValue;
        }

        return value.shortValue();
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
     * Reads an Float from given ResultSet.
     *
     * @param resultSet result set
     * @param columnLabel column label
     * @return read Float value or <code>null</code> if database value is null
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
     * @return read float value or <code>defalutValue</code> if database value
     *         is null
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
     * @return read Double value or <code>null</code> if database value is null
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
     * @return read double value or <code>defalutValue</code> if database value
     *         is null
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
     * Retrieves the value of the designated column in the current row of
     * specified <code>resultSet</code> object as a <code>java.util.Date</code>
     * object in the Java programming language.
     *
     * @param resultSet resultSet
     * @param columnLabel the label for the column specified with the SQL AS
     *        clause. If the SQL AS clause was not specified, then the label is
     *        the name of the column
     * @return the  column value; if the value id SQL <code>NULL</code>, the
     *         value returned is <code>null</code>
     * @throws SQLException if the columnLabel is not valid; if a database
     *         access error occurs or this method is called on a closed result
     *         set
     */
    protected static Date getTimestamp(final ResultSet resultSet,
                                       final String columnLabel)
        throws SQLException {

        final Timestamp value = resultSet.getTimestamp(columnLabel);
        if (value == null) {
            return null;
        }

        return new Date(value.getTime());
    }


    protected static Date getTimestamp(final ResultSet resultSet,
                                       final String columnLabel,
                                       final Calendar cal)
        throws SQLException {

        final Timestamp value = resultSet.getTimestamp(columnLabel, cal);
        if (value == null) {
            return null;
        }

        return new Date(value.getTime());
    }


    protected static void setTimestamp(
        final CallableStatement callableStatement, final int parameterIndex,
        final Long value)
        throws SQLException {

        if (value == null) {
            callableStatement.setNull(parameterIndex, Types.TIMESTAMP);
        } else {
            callableStatement.setTimestamp(
                parameterIndex, new Timestamp(value.longValue()));
        }
    }


    protected static void setTimestamp(
        final PreparedStatement preparedStatement, final int parameterIndex,
        final Long value)
        throws SQLException {

        if (value == null) {
            preparedStatement.setNull(parameterIndex, Types.TIMESTAMP);
        } else {
            preparedStatement.setTimestamp(
                parameterIndex, new Timestamp(value.longValue()));
        }
    }


    protected static void setTimestamp(
        final CallableStatement callableStatement, final int parameterIndex,
        final Date value)
        throws SQLException {

        if (value == null) {
            setTimestamp(callableStatement, parameterIndex, (Long) null);
        } else {
            setTimestamp(callableStatement, parameterIndex, value.getTime());
        }
    }


    protected static void setTimestamp(
        final PreparedStatement preparedStatement, final int parameterIndex,
        final Date value)
        throws SQLException {

        if (value == null) {
            setTimestamp(preparedStatement, parameterIndex, (Long) null);
        } else {
            setTimestamp(preparedStatement, parameterIndex, value.getTime());
        }
    }


    /**
     * Creates a new instance.
     *
     * @param tableName table name
     * @param idColumnName id column name
     * @param idType id type
     */
    public AbstractDbAccessible(final String tableName,
                                final String idColumnName,
                                final int idType) {
        super();

        if (tableName == null) {
            throw new NullPointerException("null tableName");
        }
        if (tableName.trim().length() == 0) { // no isEmpty() in PBP
            throw new IllegalArgumentException("empty tableName");
        }

        if (idColumnName == null) {
            throw new NullPointerException("null idColumnName");
        }
        if (idColumnName.trim().length() == 0) {
            throw new IllegalArgumentException("empty idColumnName");
        }

        this.tableName = tableName;
        this.idColumnName = idColumnName;

        this.idType = idType;
    }


    @Override
    public I getId() {
        return id;
    }


    @Override
    public void setId(final I id) {
        this.id = id;
    }


    @Override
    public final int getIdType() {
        return idType;
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

        if (connection == null) {
            throw new NullPointerException("null connection");
        }

        if (getId() == null) {
            throw new IllegalStateException("null id");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "SELECT * FROM " + tableName
            + " WHERE " + idColumnName + " = ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setObject(++parameterIndex, getId(), getIdType());

            final ResultSet resultSet = preparedStatement.executeQuery();
            try {
                if (!resultSet.next()) {
                    return false;
                }
                read(resultSet);
                return true;

            } finally {
                resultSet.close();
            }
        } finally {
            preparedStatement.close();
        }
    }


    @Override
    public boolean delete(final Connection connection) throws SQLException {

        if (connection == null) {
            throw new NullPointerException("null connection");
        }

        if (getId() == null) {
            throw new IllegalStateException("null id");
        }

        final PreparedStatement preparedStatement = connection.prepareStatement(
            "DELETE FROM " + tableName
            + " WHERE " + idColumnName + " = ?");
        try {
            int parameterIndex = 0;
            preparedStatement.setObject(++parameterIndex, id, idType);

            return preparedStatement.executeUpdate() == 1;

        } finally {
            preparedStatement.close();
        }
    }


    @Override
    public void read(final ResultSet resultSet) throws SQLException {
        read(resultSet, "");
    }


    /**
     * id.
     */
    private I id;


    /**
     * table name.
     */
    protected final String tableName;


    /**
     * id column name.
     */
    protected final String idColumnName;


    /**
     * idType.
     */
    private final int idType;


}

