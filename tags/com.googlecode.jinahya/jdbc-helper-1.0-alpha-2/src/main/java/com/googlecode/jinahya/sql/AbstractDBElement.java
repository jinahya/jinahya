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

import javax.xml.bind.annotation.XmlTransient;


/**
 * Abstract DBElement implementation.
 *
 * @author Jin Kwon <jinahya at gmail.com>
 */
@XmlTransient
public abstract class AbstractDBElement implements DBElement {


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
     * @param idColumnType id column type
     */
    public AbstractDBElement(final String tableName,
                             final String idColumnName,
                             final int idColumnType) {
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

        this.idColumnType = idColumnType;
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
    public final int getIdColumnType() {
        return idColumnType;
    }


    @Override
    public boolean select(final Connection connection) throws SQLException {
        return DBElementHelper.select(connection, this);
    }


    @Override
    public boolean delete(final Connection connection) throws SQLException {
        return DBElementHelper.delete(connection, this);
    }


    /** table name. */
    private final String tableName;


    /** id column name. */
    private final String idColumnName;


    /** id column type. */
    private final int idColumnType;


    /** id. */
    private Long id;


}

