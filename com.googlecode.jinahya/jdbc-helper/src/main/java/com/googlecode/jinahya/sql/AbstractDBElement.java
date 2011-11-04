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
 * Abstract DBElement implementation.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class AbstractDBElement implements DBElement {


    protected static Long getLong(final ResultSet resultSet,
                                  final String columnLabel)
        throws SQLException {

        final Long value = resultSet.getLong(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }

        return value;
    }


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


    protected static Integer getInt(final ResultSet resultSet,
                                    final String columnLabel)
        throws SQLException {

        final Integer value = resultSet.getInt(columnLabel);
        if (resultSet.wasNull()) {
            return null;
        }

        return value;
    }


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
     * 
     * @param tableName
     * @param idColumnName
     * @return 
     */
    protected static String createQualifiedIdColumnName(
        final String tableName, final String idColumnName) {

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

        if (idColumnName.startsWith(tableName.toUpperCase() + "_")) {
            return idColumnName;
        }

        return (tableName + "_" + idColumnName).toUpperCase();
    }


    /**
     * 
     * @param idColumnName
     * @return 
     */
    protected static String createIdSequenceName(final String idColumnName) {

        if (idColumnName == null) {
            throw new NullPointerException("null idColumnName");
        }

        if (idColumnName.trim().isEmpty()) {
            throw new IllegalArgumentException("empty idColumnName");
        }

        return (idColumnName + "_SEQ").toUpperCase();
    }


    /**
     * Creates a new instance.
     *
     * @param tableName table name
     * @param idColumnName id column name
     */
    public AbstractDBElement(final String tableName,
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
    public final Long getId() {
        return id;
    }


    @Override
    public void setId(final Long id) {

        if (id == null) {
            throw new NullPointerException("null id");
        }

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
        return DBElementHelper.select(connection, this);
    }


    @Override
    public boolean delete(final Connection connection) throws SQLException {
        return DBElementHelper.delete(connection, this);
    }


    @Override
    public void read(final ResultSet resultSet, final String prefix)
        throws SQLException {

        if (resultSet == null) {
            throw new NullPointerException("null resultSet");
        }

        if (prefix == null) {
            throw new NullPointerException("null prefix");
        }
    }


    /** table name. */
    private final String tableName;


    /** id column name. */
    private final String idColumnName;


    /** id. */
    private Long id;


}

